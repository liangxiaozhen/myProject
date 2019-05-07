package com.ptpl.controller.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.ptpl.constant.Award_Constant;
import com.ptpl.model.DiffAwardSwitch;
import com.ptpl.model.Award;
import com.ptpl.model.AwardPackageDetail;
import com.ptpl.model.AwardSTimeLink;
import com.ptpl.model.AwardTenderLink;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.SpecialTime;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.AwardPackageDetailServiceI;
import com.ptpl.service.AwardSTimeLinkServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.AwardTenderLinkServiceI;
import com.ptpl.service.DiffAwardSwitchServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.SpecialTimeServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.qq.connect.utils.json.JSONArray;
import com.qq.connect.utils.json.JSONException;
/**
 * @author：liuqh
 * @Description:奖品设置的Controller层
 * @date 2016-7-12 上午10:50:48
 */ 
@Controller
@RequestMapping("/admin/award")
public class AwardController {
	@Autowired
	private AwardServiceI awardService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;
	@Autowired
	private TenderItemServiceI tenderItemService;
	@Autowired
	private AwardTenderLinkServiceI awardTenderLinkService;
	@Autowired
	private AwardPackageDetailServiceI awardPackageDetailService;
	@Autowired
	private SpecialTimeServiceI specialTimeService;//特定时间  
	@Autowired
	private AwardSTimeLinkServiceI awardSTimeLinkService;//奖品特定时间关联表
	
	// 转发到增加奖品页面
	@RequestMapping(value = "/insert_Award_Ui", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_Award_Ui(Award award) {
		
		//生成奖品编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String ano = "GJHB"+sdf.format(new Date());
		award.setAno(ano);//奖品编号
		ModelAndView modelAndView = new ModelAndView();
		
		//所有的会员
		List<UserGrade> ugList=userGradeService.selective(null);
		Map<BigDecimal, String> map = new HashMap<BigDecimal, String>();
		for(UserGrade u:ugList){
			map.put(u.getUgrade(), u.getUgradename());
		}
		modelAndView.addObject("map",map);
		
		//需要获取定向名单的编号
		SpecialNameList snl = new SpecialNameList();
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			if(systemBusType.charAt(10)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
		
		//需要获得奖品的特定时间表
		SpecialTime st = new SpecialTime();
		st.setTimetype("奖品");
		List<SpecialTime> stList = specialTimeService.selective(st);
		
		modelAndView.addObject("award",award);
		modelAndView.addObject("snlList",snlList);
		modelAndView.addObject("stList",stList);
		//modelAndView.addObject("readycash",Award_Constant.READY_CASH);//加息对象 1.真金 ，2.类现金，3.假现金
		modelAndView.addObject("biao_attribute",Award_Constant.BIAO_ATTRIBUTE);//投标属性限制
		modelAndView.addObject("atype_map",Award_Constant.ATYPE_MAP);
		modelAndView.addObject("tradetype_map",Award_Constant.tradetype_map);
		modelAndView.addObject("isdefinition",Award_Constant.isdefinition);
		modelAndView.setViewName("admin/award/Award_Insert");
		return modelAndView;
	}

	// 增加奖品
	@RequestMapping(value = "/insertAward", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertAward(Award award,SpecialNameList snl,String[] specialtnos) {
		award.setAddtime(new Date());
		System.out.println("specialtnos>>>"+specialtnos);
		System.out.println("award======="+award);
		//System.out.println("特定时间包的编号====="+timeNo);
		//根据编号获取对应的定向名单列表
		snl = specialNameListService.getSnlsByNoOrName(snl);
		award.setSNLId(snl.getId());
		
		/*//处理加息对象   1.真金 ，2.类现金，3.假现金
		String raiseIntObject = award.getRaiseIntObject();
		if(raiseIntObject!=null && !"".equals(raiseIntObject)){
			String[] rioArr = raiseIntObject.split(",");
			StringBuilder rioStr = new StringBuilder("000");
			for(int i=0;i<rioArr.length;i++){
				int rio = Integer.parseInt(rioArr[i].trim());
				rioStr.replace(rio-1, rio, "1");
			}
			award.setRaiseIntObject(rioStr.toString());
		}*/
		
		//处理投标属性限制
		String  tattribute = award.getTattribute();
		if(tattribute!=null && !"".equals(tattribute)){
			StringBuilder tbeStr = new StringBuilder("000");
			for(int i=0;i<16;i++){
				tbeStr = tbeStr.append("000");
			}
			String[] tbeArr = tattribute.split(",");
			for(int i=0;i<tbeArr.length;i++){
				int tbe = Integer.parseInt(tbeArr[i]);
				tbeStr.replace(tbe-1, tbe, "1");
			}
			award.setTattribute(tbeStr.toString());
		}
		
		awardService.insert(award);
		System.out.println("award======"+award);
		//获取刚插入的奖品列表，获得奖品列表的id
		award = awardService.selectByAwardNo(award.getAno());
		
		//奖品设置指定标号关联
		AwardTenderLink atl = new AwardTenderLink();
		atl.setAwardId(award.getId());//奖品表的id
		
		if(specialtnos!=null && specialtnos.length>0){
			
			for(int i=0;i<specialtnos.length;i++){
				//根据标号获取对应的标的设置
				TenderItem ti=tenderItemService.seltendbytno(specialtnos[i]);
				atl.setTenderId(ti.getId());
				awardTenderLinkService.insertSelective(atl);
			}
		}
		
		return  new ModelAndView("redirect:/admin/award/selectAwardByCondition.action");
	}
	
	// 根据条件查找奖品并转发到奖品列表页面
	@RequestMapping(value = "/selectAwardByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectAwardByCondition(HttpServletRequest request,Award award) {

		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		
		System.out.println("奖品编号》》》"+award.getAno());
		System.out.println("奖品名称》》》"+award.getAname());
		System.out.println("奖品类型》》》》"+award.getAtype());
		
		int num = 1;
		int size = 9;
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		String sortString = "id.desc";
		Order.formString(sortString);

		PageHelper.startPage(num, size);

		String aname = award.getAname();
		
		if(!StringUtil.isEmpty(aname)){
			String aname1 = aname.trim();
			award.setAname("%"+aname1+"%");
		}
		// 调用service层的方法得到对象列表
		List<Award> AwardList = awardService.selectByCondition(award);
		award.setAname(aname);
		PageInfo<Award> pagehelper = new PageInfo<Award>(AwardList);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		// 把对象放进modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.addObject("pageNum",pageNum);
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("award", award);
		modelAndView.addObject("atype_map",Award_Constant.ATYPE_MAP);
		modelAndView.addObject("attributeData_map",Award_Constant.attributeData_map);
		// 指定视图
		modelAndView.setViewName("admin/award/Award_List");
		return modelAndView;
	}

	// 根据id查找奖品并返回奖品的详细页面
	@RequestMapping(value = "/selectAwardByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectAwardByPrimaryKey(String id,String pageNum) {
		
		Award award = new Award();
		award.setId(new BigDecimal(id));
		award = awardService.getAward(award);
		/*//处理加息对象
		String raiseIntObject = award.getRaiseIntObject();
		Set<String> cashSet = new HashSet<String>();//存放加息对象
		if(raiseIntObject!=null && !"".equals(raiseIntObject)){
			
			for(int i=0;i<raiseIntObject.length();i++){
				char c = raiseIntObject.charAt(i);
				if(c=='1'){
					cashSet.add(Award_Constant.READY_CASH.get((short)(i+1)));
				}
			}
		}*/
		
		//处理投标属性限制
		String tattribute = award.getTattribute();
		Set<String> bidSet = new HashSet<String>();
		if(tattribute!=null && !"".equals(tattribute)){
			
			for(int i=0;i<tattribute.length();i++){
				char c = tattribute.charAt(i);
				if(c=='1'){
					bidSet.add(Award_Constant.BIAO_ATTRIBUTE.get((short)(i+1)));
				}
			}
		}
		
		//处理指定标的名称
		List<AwardTenderLink> atlList = awardTenderLinkService.getAwardTenderLinksByawardId(award.getId());
		List<String> tiList = new ArrayList<String>();
		if(atlList!=null && !atlList.isEmpty()){
			
			for(AwardTenderLink a:atlList){
				//获取标号的id
				TenderItem ti = tenderItemService.findTenderItemById(a.getTenderId());
				if(ti!=null){
					tiList.add(ti.getTname());
				}
			}
		}
		
		//获取买家的定向名单
		SpecialNameList s = new SpecialNameList();
		s.setId(award.getSNLId());
		s = specialNameListService.getSpecialNameList(s);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageNum", pageNum);
		modelAndView.addObject("award", award);
		
		modelAndView.addObject("bidSet", bidSet);
		
		modelAndView.addObject("tiList", tiList);
		//modelAndView.addObject("cashSet", cashSet);
		
		modelAndView.addObject("s", s);
		modelAndView.addObject("tradetype_map",Award_Constant.tradetype_map);
		modelAndView.addObject("attributeData_map",Award_Constant.attributeData_map);
		modelAndView.addObject("atype_map",Award_Constant.ATYPE_MAP);
		modelAndView.addObject("isdefinition",Award_Constant.isdefinition);
		modelAndView.setViewName("admin/award/Award_Detail");
		return modelAndView;
	}

	// 转发到修改奖品页面 还未修改保存
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(HttpServletRequest request,String id,String pageNum) {
		ModelAndView modelAndView = selectAwardByPrimaryKey(id,pageNum);
		Map<String, Object> model = modelAndView.getModel();
		Award award = (Award) model.get("award");
		//处理定向标题
		SpecialNameList snl = new SpecialNameList();
		snl.setId(award.getSNLId());
		snl = specialNameListService.getSpecialNameList(snl);
		System.out.println("snl:"+snl);
		List<SpecialNameList> snlList = null;
		
		//需要获取定向名单的编号
		SpecialNameList s = new SpecialNameList();
		s.setIsUse((short)1);
		s.setBusinessType((short)1);//系统业务才可引用
		snlList = specialNameListService.getSpecialNameListByNo(s);
		List<SpecialNameList> snlList_ = new ArrayList<SpecialNameList>();
		for(SpecialNameList ss:snlList){
			String systemBusType = ss.getSystemBusType();
			if(systemBusType.charAt(10)=='1'){
				//系统业务为手动颁奖
				snlList_.add(ss);
			}
		}
		
		/*//处理加息对象
		String raiseIntObject = award.getRaiseIntObject();
		Set<Integer> cashSet = new HashSet<Integer>();//存放加息对象的value值
		if(raiseIntObject!=null && !"".equals(raiseIntObject)){
			for(int i=0;i<raiseIntObject.length();i++){
				if(raiseIntObject.charAt(i)=='1'){
					cashSet.add(i+1);
				}
			}
		}*/
		
		//处理投标属性限制
		String tattribute = award.getTattribute(); 
		Set<Integer> bidSet = new HashSet<Integer>();//投标属性的value值
		if(tattribute!=null && !"".equals(tattribute)){
			
			for(int i=0;i<tattribute.length();i++){
				if(tattribute.charAt(i)=='1'){
					bidSet.add(i+1);
				}
			}
		}
		
		//根据奖品设置表的id获得对应的奖品设置指定标号表
		List<AwardTenderLink> atlList = awardTenderLinkService.getAwardTenderLinksByawardId(award.getId());
		Map<String,String> tiMap = new HashMap<String,String>();
		if(atlList!=null && !atlList.isEmpty()){
			
			for(AwardTenderLink a:atlList){
				//获取标号的id
				TenderItem ti = tenderItemService.findTenderItemById(a.getTenderId());
				if(ti!=null){
					tiMap.put(ti.getTno(),ti.getTname());
				}
			}
		}
		
		Short key = award.getAtype();//奖品类型(1站内虚拟，2站外虚拟，3站外实物)
		Map<Short, String> attributeData_map = Award_Constant.atype_attributeData.get(key);
		modelAndView.addObject("pageNum",pageNum);
		//modelAndView.addObject("cashSet",cashSet);
		modelAndView.addObject("bidSet",bidSet);
		
		modelAndView.addObject("tiMap",tiMap);
		
		modelAndView.addObject("snl",snl);
		modelAndView.addObject("snlList",snlList_);
		
		modelAndView.addObject("atype_map",Award_Constant.ATYPE_MAP);
		modelAndView.addObject("attributeData_map",attributeData_map);
		modelAndView.addObject("biao_attribute",Award_Constant.BIAO_ATTRIBUTE);//投标属性限制
		modelAndView.addObject("tradetype_map",Award_Constant.tradetype_map);
		modelAndView.addObject("isdefinition",Award_Constant.isdefinition);
		modelAndView.setViewName("admin/award/Award_Update");
		return modelAndView;
	}

	// 修改奖品
	@RequestMapping(value = "/updateAward", method = RequestMethod.POST)
	public String updateAward(HttpServletRequest request,Award award,SpecialNameList snl,String[] specialtnos) {
		
		award.setAddtime(new Date());
		//System.out.println("award==="+award);
		
		//根据编号获取对应的定向名单列表
		snl = specialNameListService.getSnlsByNoOrName(snl);
		if(snl!=null){
			award.setSNLId(snl.getId());
		}
		
		/*//处理加息对象   1.真金 ，2.类现金，3.假现金
		String raiseIntObject = award.getRaiseIntObject();
		if(raiseIntObject!=null && !"".equals(raiseIntObject)){
			String[] rioArr = raiseIntObject.split(",");
			StringBuilder rioStr = new StringBuilder("000");
			for(int i=0;i<rioArr.length;i++){
				int rio = Integer.parseInt(rioArr[i]);
				rioStr.replace(rio-1, rio, "1");
			}
			award.setRaiseIntObject(rioStr.toString());
		}*/
		
		//处理投标属性限制
		String  tattribute = award.getTattribute();
		StringBuilder tbeStr = new StringBuilder("000");
		if(tattribute !=null && !"".equals(tattribute)){
			
			for(int i=0;i<16;i++){
				tbeStr = tbeStr.append("000");
			}
			String[] tbeArr = tattribute.split(",");
			for(int i=0;i<tbeArr.length;i++){
				int tbe = Integer.parseInt(tbeArr[i]);
				tbeStr.replace(tbe-1, tbe, "1");
			}
			award.setTattribute(tbeStr.toString());
		}
		
		String pageNum = request.getParameter("pageNum");
		
		awardService.updateAward(award);
		//同时可能需要修改奖品包内容的奖品名称
		AwardPackageDetail apd = new AwardPackageDetail();
		apd.setAwardNO(award.getAno());
		apd.setAwardName(award.getAname());
		awardPackageDetailService.updateAwardNameByAwardNO(apd);
		
		//奖品设置指定标号关联
		//根据奖品的id删除奖品设置指定标号关联表，然后重新插入
		awardTenderLinkService.deleteAwardTenderLink(award.getId());
		AwardTenderLink atl = new AwardTenderLink();
		atl.setAwardId(award.getId());//奖品表的id
	//	System.out.println("specialtnos==="+specialtnos);
		if(specialtnos!= null && specialtnos.length>0){
			for(int i=0;i<specialtnos.length;i++){
				System.out.println("specialtnos[i]===="+specialtnos[i]);
				//根据标号获取对应的标的设置
				TenderItem ti=tenderItemService.seltendbytno(specialtnos[i]);
				atl.setTenderId(ti.getId());
				awardTenderLinkService.insertSelective(atl);
			}
		}
		
		return "redirect:/admin/award/selectAwardByCondition.action?pageNum="+pageNum;
	}
	
	//根据奖品类型查找奖品属性
	@RequestMapping(value="/findAttributeDataByAtype")
	public void findAttributeDataByAtype(HttpServletResponse response, String atype) throws Exception{
		String jsonString = null;
		if("1".equals(atype)){
				jsonString = JSON.toJSONString(Award_Constant.atype_attributeData.get(Short.parseShort(atype)));
		}else if("2".equals(atype)){
				jsonString = JSON.toJSONString(Award_Constant.atype_attributeData.get(Short.parseShort(atype)));
		}else if("3".equals(atype)){
				jsonString = JSON.toJSONString(Award_Constant.atype_attributeData.get(Short.parseShort(atype)));
		}
		sendJsonData(response,jsonString);
	}
	
	@SuppressWarnings("unused")
	private void sendJsonData(HttpServletResponse response, String data)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out = response.getWriter();
		out.println(data);
		out.flush();
		out.close();
	}

	/**
	 * @author pxl
	 * 在奖品列表中删除某一个奖品
	 * @throws IOException 
	 */
	@RequestMapping("/deleteSomeAward")
	public void deleteSomeAward(HttpServletResponse response,Award award) throws IOException{
		int rows=awardService.deleteSomeAward(award);
		Map<String,String> map = new HashMap<String,String>();
		if(rows>0){
			map.put("result", "删除成功");
		}else{
			map.put("result", "删除失败");
		}
		String jsonStr=JSON.toJSONString(map);
		sendJsonData(response,jsonStr);
	}
	
	/**
	 * 根据指定的标号获取对应的名称
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@RequestMapping("/getTNameByTNo")
	public void getTNameByTNo(HttpServletResponse response,String specialtno) throws IOException, JSONException{
		
		TenderItem tenderItem=tenderItemService.seltendbytno(specialtno);
		
		String jsonStr = null;
		if(tenderItem!=null){
			jsonStr = JSON.toJSONString(tenderItem.getTname());
			com.ptpl.web.util.StringUtil.sendJsonData(response, jsonStr);
		}else{
			jsonStr = JSON.toJSONString("该标号错误");
			com.ptpl.web.util.StringUtil.sendJsonData(response, jsonStr);
		}
	}
	
	/**
	 * 插入奖品时，判断奖品名称的唯一性
	 * @throws IOException 
	 */
	@RequestMapping("/insertAnameOnly")
	public void insertAnameOnly(HttpServletRequest request,HttpServletResponse response,Award a) throws IOException{
		String aname = URLDecoder.decode(request.getParameter("aname"),"UTF-8");
		a.setAname(aname);
		Award award = awardService.getAward(a);
		String jsonStr = null;
		if(award!=null){
			jsonStr = JSON.toJSONString("名称已存在");
		}
		com.ptpl.web.util.StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 更新奖品时，判断奖品名称的唯一性
	 * @throws IOException 
	 */
	@RequestMapping("/updateAnameOnly")
	public void updateAnameOnly(HttpServletRequest request,HttpServletResponse response,Award a) throws IOException{
		String aname = URLDecoder.decode(request.getParameter("aname"),"UTF-8");
		a.setAname(aname);
		Award award = awardService.gainAward(a);
		String jsonStr = null;
		if(award!=null){
			jsonStr = JSON.toJSONString("名称已存在");
		}
		com.ptpl.web.util.StringUtil.sendJsonData(response, jsonStr);
	}
	
}
