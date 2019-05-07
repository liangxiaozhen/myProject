package com.ptpl.controller.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.ptpl.constant.AwardPackage_Constant;
import com.ptpl.constant.Award_Constant;
import com.ptpl.model.Award;
import com.ptpl.model.AwardPackage;
import com.ptpl.model.AwardPackageDetail;
import com.ptpl.service.AwardPackageDetailServiceI;
import com.ptpl.service.AwardPackageServiceI;
import com.ptpl.service.AwardServiceI;

/**
 * @author：liuqh
 * @Description:奖品包的Controller层
 */ 
@Controller
@RequestMapping("/admin/awardPackage")
public class AwardPackageController {
	@Autowired
	private AwardPackageServiceI awardPackageService;
	@Autowired
	private AwardServiceI awardService;
	@Autowired
	private AwardPackageDetailServiceI awardPackageDetailService;
	
	// 转发到增加奖品包页面
	@RequestMapping(value = "/insert_AwardPackage_Ui", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_Award_Ui(AwardPackage ap) {
		
		//生成奖品包的编号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String apno = "GJJPB"+sdf.format(new Date());
		ap.setApno(apno);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ap",ap);
		modelAndView.addObject("atype_map", Award_Constant.ATYPE_MAP);
		modelAndView.setViewName("admin/awardPackage/AwardPackage_Insert");
		return modelAndView;
	}
	
	// 增加奖品包
	@RequestMapping(value = "/insertAwardPackage", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertAward(AwardPackage ap,AwardPackageDetail apd) {
		ap.setAddtime(new Date());
		System.out.println("addTime===="+ap.getAddtime());//Fri Feb 10 11:30:24 CST 2017
		awardPackageService.insert(ap);
		//System.out.println("ap2======="+ap);
		//保存奖品包数据
		//将刚存进去的奖品包取出来
		ap = awardPackageService.getAwardPackage(ap);
		apd.setApId(ap.getId());//奖品包id
		List<AwardPackageDetail> apdList = apd.getApdList();
		for(AwardPackageDetail a:apdList){
			if(a.getApAwardType()!=null && a.getAwardName()!=null && a.getAwardQTY()!=null){
				
				apd.setApAwardType(a.getApAwardType());//奖品类型
				apd.setAwardQTY(a.getAwardQTY());//奖品份数
				//根据奖品的名称获得奖品的编号
				Award award = awardService.getAwardByName(a.getAwardName());
				apd.setAwardNO(award.getAno());//奖品的编号
				//apd.setAwardAttr((short)apd_list.size());//奖品的种类
				apd.setAwardName(a.getAwardName());//奖品名称
				awardPackageDetailService.insertSelective(apd);
			}
		}
		return  new ModelAndView("redirect:/admin/awardPackage/selectAwardPackageByCondition.action");
	}
		
		
	// 根据条件查找奖品包名单并转发到获奖列表页面 
	@RequestMapping(value = "/selectAwardPackageByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectAwardPackageByCondition(HttpServletRequest request,AwardPackage awardPackage) {
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
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
		String apname = awardPackage.getApname();
		if(!StringUtil.isEmpty(apname)){
			String apname1="%"+apname.trim()+"%";
			awardPackage.setApname(apname1);
		}
		// 调用service层的方法得到对象列表
		List<AwardPackage> apl = awardPackageService.selectByCondition(awardPackage);
		awardPackage.setApname(apname);
		for (AwardPackage aal : apl) {
			System.out.println(aal);
			
		}
		PageInfo<AwardPackage> pagehelper = new PageInfo<AwardPackage>(apl);
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
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("awardPackage",awardPackage);
		modelAndView.addObject("iscancelmap", AwardPackage_Constant.iscancel_map);
		modelAndView.addObject("istempletmap", AwardPackage_Constant.ISTEMPLET_MAP);
		// 指定视图
		modelAndView.setViewName("admin/awardPackage/AwardPackage_List");
		return modelAndView;
	}

	// 根据id查找奖品包详细页面
	@RequestMapping(value = "/selectAwardPackageByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectAwardByPrimaryKey(String id) {
		AwardPackage awardPackage = new AwardPackage();
		awardPackage.setId(new BigDecimal(id));
		AwardPackage ap= awardPackageService.getAwardPackage(awardPackage);
		
		//根据奖品表的id获得对应的奖品包内容表的id
		List<AwardPackageDetail> apdList = awardPackageDetailService.getAwardPackageDetailByApId(ap.getId());
		Set<AwardPackageDetail> apdSet = new HashSet<AwardPackageDetail>();
		for(AwardPackageDetail a:apdList){
			apdSet.add(a);
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ap", ap);
		modelAndView.addObject("apdSet", apdSet);
		modelAndView.addObject("atypemap", Award_Constant.ATYPE_MAP);//奖品类型(1站内虚拟，2站外虚拟，3站外实物)
		modelAndView.addObject("iscancelmap", AwardPackage_Constant.iscancel_map);// 是否下架（1代表上架，0代表下架）
		modelAndView.addObject("istempletmap", AwardPackage_Constant.ISTEMPLET_MAP);// 是否为模板（1是，2否）
		modelAndView.setViewName("admin/awardPackage/AwardPackage_Detail");
		return modelAndView;
	}

	// 转发到修改奖品包页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(String id) {
		ModelAndView modelAndView = selectAwardByPrimaryKey(id);
		modelAndView.setViewName("admin/awardPackage/AwardPackage_Update");
		return modelAndView;
	}

	// 修改
	@RequestMapping(value = "/updateAwardPackage", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateAward(AwardPackage ap,AwardPackageDetail apd) {
		ap.setAddtime(new Date());		
		awardPackageService.update(ap);
		//根据奖品包的id将奖品包奖品内容表先删除  然后再重新插入
		awardPackageDetailService.deleteAwardPackageDetailByApId(ap.getId());
		List<AwardPackageDetail> apdList = apd.getApdList();
		apd.setApId(ap.getId());
		for(AwardPackageDetail a:apdList){
			if(a.getApAwardType()!=null && a.getAwardName()!=null && a.getAwardQTY()!=null){
				apd.setApAwardType(a.getApAwardType());//奖品类型
				apd.setAwardQTY(a.getAwardQTY());//奖品份数
				//根据奖品的名称获得奖品的编号
				Award award = awardService.getAwardByName(a.getAwardName());
				apd.setAwardNO(award.getAno());//奖品的编号
				//apd.setAwardAttr((short)apd_list.size());//奖品的种类
				apd.setAwardName(a.getAwardName());//奖品名称
				awardPackageDetailService.insertSelective(apd);
			}
		}
		return "redirect:/admin/awardPackage/selectAwardPackageByCondition.action";
	}
	
	//删除
	@RequestMapping(value = "/deleteAwardPackage", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteAwardPackage(String id){
		awardPackageService.delete(new BigDecimal(id));
		//需要删除对应的奖品包内容列表
		awardPackageDetailService.deleteAwardPackageDetailByApId(new BigDecimal(id));
		
		return "redirect:/admin/awardPackage/selectAwardPackageByCondition.action";
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
	 * 根据奖品的类型获取所有奖品的名称
	 * @throws IOException 
	 */
	@RequestMapping("/getAwardNamesByAtype")
	public void getAwardNamesByAtype(HttpServletResponse response,String apAwardType) throws IOException{
		List<Award> aList = awardService.getAwardNamesByAtype(new Short(apAwardType));
		Iterator<Award> it = aList.iterator();
		/*将奖品金额为空的站内奖品过滤掉，不可添加进奖品包*/
		while(it.hasNext()){
			Award a = it.next();
			//System.out.println("a===="+a);
			//System.out.println("atype===="+a.getAtype());
			if(a.getAtype()==1){//站内奖品
			//	System.out.println("cashorvoucher===="+a.getCashorvoucher());
				if(a.getCashorvoucher()==null || "".equals(a.getCashorvoucher())){
					it.remove();
				}
			}
		}
		String jsonStr = JSON.toJSONString(aList);
		com.ptpl.web.util.StringUtil.sendJsonData(response,jsonStr);
	}
	
	/**
	 * 插入奖品包时，检查奖品包名是否唯一
	 * @throws IOException 
	 */
	@RequestMapping("/checkApnameOnly")
	public void checkApnameOnly(HttpServletResponse response,AwardPackage ap) throws IOException{
		AwardPackage awardPackage = awardPackageService.getAwardPackage(ap);
		String jsonStr = null;
		if(awardPackage!=null){
			jsonStr= "false";
		}else{
			jsonStr = "true";
		}
		com.ptpl.web.util.StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 更新奖品包时，检查奖品包名是否唯一
	 * @throws IOException 
	 */
	@RequestMapping("/updateApnameOnly")
	public void updateApnameOnly(HttpServletResponse response,HttpServletRequest request,AwardPackage ap) throws IOException{
		String apname = URLDecoder.decode(request.getParameter("apname"),"UTF-8");
		ap.setApname(apname);
		AwardPackage awardPackage = awardPackageService.gainAwardPackage(ap);
		String jsonStr = null;
		if(awardPackage!=null){
			jsonStr = JSON.toJSONString("名称已存在");
		}
		com.ptpl.web.util.StringUtil.sendJsonData(response, jsonStr);
	}
}
