package com.ptpl.controller.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ManualAward_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ActiveObjectList;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.service.ActiveObjectListServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * @author pxl
 * @date  2016-12-6
 * 定向名单
 *
 */
@Controller
@RequestMapping("/admin/nameList")
public class SpecialNameListController extends BaseController{

	@Autowired
	private SpecialNameListServiceI specialNameListService;//定向名单
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private ActiveObjectListServiceI  activeObjectListService;
	/**
	 * @author pxl
	 * @date  2016-12-6
	 * 定向名单列表查看
	 * @return
	 */
	@RequestMapping("/directionNameQuery")
	public ModelAndView directionNameQuery(SpecialNameList snl){
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
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, size);
		
		List<SpecialNameList> snlList = specialNameListService.getSpecialNameLists(snl);
		
		PageInfo<SpecialNameList> pagehelper = new PageInfo<SpecialNameList>(snlList);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if(pagehelper.getTotal() % size ==0){
			lasePageNum = (int)pagehelper.getTotal() / size;
		}else{
			lasePageNum = (int)pagehelper.getTotal() / size+1 ;
		}
		pagehelper.setLastPage(lasePageNum);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("snl",snl);
		mv.addObject("typemaps",ManualAward_Constant.DIRECTION_TYPE);//定向方式  1.大小名单  2.会员等级
		mv.addObject("moldmaps",ManualAward_Constant.DIRECTION_MOLD);//定向类型     1.系统业务  2.短信通知
		mv.addObject("quotemaps",ManualAward_Constant.QUOTE_STATUS);//是否被引用   1.是   2.否
		mv.addObject("usemaps",ManualAward_Constant.USE_STATUS);//启用状态  1.是   2.否
		mv.setViewName("admin/direction/directionNameList");
		return mv;
	}
	
	/**
	 * @author pxl
	 * @date  2016-12-6
	 * 手动新增：选择定向方式  大小名单  会员等级
	 * 定向类型:选择定向类型   系统业务  短信通知
	 * @return
	 */
	@RequestMapping("/directionManualAdd")
	public ModelAndView directionManualAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/direction/directionType");
		return mv;
	}
	
	/**
	 * @author pxl
	 * @date  2016-12-6
	 * 选择模板  生成定向名单列表
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/templetManualAdd")
	public ModelAndView templetManualAdd(HttpServletResponse response,SpecialNameList snl) throws IOException{
		
		//获取所有的定向编号（模板）
		snl.setIsTemplet((short)1);
		List<SpecialNameList> snlList = specialNameListService.getTemSpecialNameLists(snl);
		Set<String> snlSet = new HashSet<String>();
		for(SpecialNameList s:snlList){
			snlSet.add(s.getBusinessName());
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("snlSet",snlSet);
		mv.setViewName("admin/direction/TemplateCreate");
		return mv;
	}
	
	/**
	 * 手动新增 ：  选择定向方式   并确定
	 * @author pxl
	 */
	@RequestMapping("/sureCreate")
	public ModelAndView sureCreate(HttpServletRequest request,SpecialNameList snl){
		
		//生成定向编号
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		int x=(int)(Math.random()*99999);
		String businessNo="GJDXDD"+dateString+x;
		
		snl.setBusinessNo(businessNo);
		snl.setGenerateType((short)1);//手动生成方式
		ModelAndView mv = new ModelAndView();
		
		//大小名单  会员等级
		List<RemoveName> removeNames = removeNameService.getGroupNameType();//查询到说有的大名单
		//需要获得启用状态的会员
		List<UserGrade> ugList=userGradeService.selectiveForNormal/*selectEnableMembers*/(null);
		Map<BigDecimal, String> map = new HashMap<BigDecimal, String>();//等级   名称
		Map<Object,Object> map_ = new TreeMap<Object,Object>();// 序号  等级
		for(int i=0;i<ugList.size();i++){
			map.put(ugList.get(i).getUgrade(), ugList.get(i).getUgradename());//等级   名称
			map_.put(ugList.get(i).getRankno(), ugList.get(i).getUgrade());//序号  等级
		}
		mv.addObject("map",map);
		mv.addObject("map_",map_);
		mv.addObject("removeNames", removeNames);
		
		mv.addObject("snl",snl);
		mv.addObject("sbtype",ManualAward_Constant.SYSTEMBUSTYPE);//系统业务具体类型
		mv.addObject("smtype",ManualAward_Constant.SMSTYPE);//短信通知具体类型
		if(snl.getNameMode()==1){
			mv.setViewName("admin/direction/sizeListDirection");
		}else if(snl.getNameMode()==2){
			mv.setViewName("admin/direction/memberGradeDirection");
		}
		return mv;
	}
	
	/**
	 * 模板新增 ：  选择定向方式   并确定
	 * @author pxl
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/templateCreate")
	public ModelAndView templateCreate(HttpServletRequest request,SpecialNameList snl) throws UnsupportedEncodingException{
		String businessName=URLDecoder.decode(snl.getBusinessName(), "UTF-8");
		snl.setBusinessName(businessName);
		snl = specialNameListService.getSnlsByNoOrName(snl);
		
		Set<String> set1=new HashSet<String>();//获奖大名单
		Set<String> set2=new HashSet<String>();//获奖小名单
		Set<String> set3=new HashSet<String>();//获奖用户名
		Set<String> set4=new HashSet<String>();//排除大名单
		Set<String> set5=new HashSet<String>();//排除小名单
		Set<String> set6=new HashSet<String>();//排除用户名
		/*Set<String> set7=new HashSet<String>();//会员等级名称
		Set<String> set8=new HashSet<String>();//会员等级*/
		Map<Short,String> map3 = new HashMap<>();//用来添加会员等级，和会员名称
		Map<Short,String> map1=new HashMap<Short,String>();//系统业务
		Map<Short,String> map2=new HashMap<Short,String>();//短信通知
		ModelAndView mv = new ModelAndView();
		String systemBusType = snl.getSystemBusType();
		if(systemBusType!=null && !"".equals(systemBusType)){
			System.out.println("占位符的长度》》》》"+systemBusType.length());
			for(int i=0;i<systemBusType.length()-1;i++){
				char sbt = systemBusType.charAt(i);
				if(sbt=='1'){
					map1.put(new Short((short)(i+1)),ManualAward_Constant.SYSTEMBUSTYPE.get((short)(i+1)));
				}
			}
			
			if(systemBusType.charAt(systemBusType.length()-1)=='1'){
				//系统业务最后一个字符为1，即该定向名单为风险名单
				mv.addObject("isRisk","1");
			}
		}
		String smsType = snl.getSmsType();
		if(smsType!=null && !"".equals(smsType)){
			System.out.println("占位符的长度》》》》"+smsType.length());
			for(int i=0;i<smsType.length();i++){
				char st = smsType.charAt(i);
				System.out.println("st>>>"+st);
				if(st=='1'){
					map2.put(new Short((short)(i+1)),ManualAward_Constant.SMSTYPE.get((short)(i+1)));
				}
			}
		}
		
		//生成定向编号
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		int x=(int)(Math.random()*99999);
		String businessNo="GJDXDD"+dateString+x;
		snl.setBusinessNo(businessNo);
		
		snl.setAddTime(new Date());
		snl.setGenerateType((short)2);//名单生成方式（1.手动  2.模板）
		
		//根据定向名单列表的id获得对应的业务对象名单
		List<ActiveObjectList> aolList = activeObjectListService.selectBySNLId(snl.getId());
		for(ActiveObjectList aol:aolList){
			
			if(aol.getIsRightOrExcept()==1){
				if(aol.getNameType()==1){
					//获奖大名单
					set1.add(aol.getNameContent().trim());
				}
				if(aol.getNameType()==2){
					//获奖小名单
					RemoveName rn = removeNameService.selectNameAndNameType(aol.getNameContent().trim());
					set2.add(aol.getNameContent().trim()+"-"+rn.getName());
				}
				if(aol.getNameType()==3){
					//获奖用户名
					set3.add(getDecrypt(aol.getNameContent().trim()));
				}
				if(aol.getNameType()==4){//会员等级
					
					//需要对会员等级占位符进行解析
					String nc = aol.getNameContent().trim();
					for(int i=0;i<nc.length();i++){
						char c = nc.charAt(i);
						if(c=='1'){
							//根据会员等级获取会员等级的名称
							UserGrade userGrade=userGradeService.getusergrade(new BigDecimal(i));
							map3.put((short)i, userGrade.getUgradename());
						}
					}
				}
			}else if(aol.getIsRightOrExcept()==2){
				if(aol.getNameType()==1){
					//排除大名单
					set4.add(aol.getNameContent().trim());
				}
				if(aol.getNameType()==2){
					//排除小名单
					RemoveName rn = removeNameService.selectNameAndNameType(aol.getNameContent().trim());
					set5.add(aol.getNameContent().trim()+"-"+rn.getName());
				}
				if(aol.getNameType()==3){
					//排除用户名
					set6.add(getDecrypt(aol.getNameContent().trim()));
				}
			}
		}
		
		
		//大小名单  会员等级
		List<RemoveName> removeNames = removeNameService.getGroupNameType();//查询到说有的大名单
		//需要获得启用状态的会员
		List<UserGrade> ugList=userGradeService.selectiveForNormal/*selectEnableMembers*/(null);
		Map<BigDecimal, String> map = new HashMap<BigDecimal, String>();//等级   名称
		Map<Object, Object> map_ = new TreeMap<Object, Object>();//序号  等级
		for(UserGrade u:ugList){
			map.put(u.getUgrade(), u.getUgradename());//等级   名称
			map_.put(u.getRankno(), u.getUgrade());//序号  等级
		}
		mv.addObject("map",map);
		mv.addObject("map_",map_);
		mv.addObject("removeNames", removeNames);
		
		mv.addObject("set1",set1);//获奖大名单
		mv.addObject("set2",set2);//获奖小名单
		mv.addObject("set3",set3);//获奖用户名
		mv.addObject("set4",set4);//排除大名单
		mv.addObject("set5",set5);//排除小名单
		mv.addObject("set6",set6);//排除用户名
		
		mv.addObject("map3",map3);//存会员等级   会员名称
		mv.addObject("map1",map1);//系统业务
		mv.addObject("map2",map2);//短信通知
		
		mv.addObject("snl",snl);
		
		mv.addObject("sbtype",ManualAward_Constant.SYSTEMBUSTYPE);//系统业务具体类型
		mv.addObject("smtype",ManualAward_Constant.SMSTYPE);//短信通知具体类型
		
		if(snl.getNameMode()==1){
			mv.setViewName("admin/direction/sizeListDirection");
		}else if(snl.getNameMode()==2){
			mv.setViewName("admin/direction/memberGradeDirection");
		}
		return mv;
	}
	
	/**
	 * @author pxl
	 * @throws Exception 
	 * @date 2016-12-7
	 * 根据大名单查询出相对应的小名单
	 */
	@RequestMapping("/getNameList")
	public void getNameList(HttpServletResponse response,String addOrDebarText) throws Exception{
		if (addOrDebarText != null && !"".equals(addOrDebarText)){
			List<RemoveName> rnList = removeNameService.getGroupNameByNameType(addOrDebarText);
			String jsonStr = JSON.toJSONString(rnList);
			sendJsonData(response, jsonStr);
		}
	}
	
	/**
	 * @author pxl
	 * @throws Exception 
	 * @date 2016-12-7
	 * 检测该添加用户是否存在
	 */
	@RequestMapping("/isPersonExist")
	public void isPersonExist(HttpServletResponse response,String importPerson) throws Exception{
		if(importPerson!=null && !"".equals(importPerson)){
			//看看该手输用户是否存在
			UserBaseAccountInfo ubai = new UserBaseAccountInfo();
			ubai.setLoginname(setEncrypt(importPerson));
			ubai= userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai);
			if(ubai!=null){ 
				String jsonStr = JSON.toJSONString("添加成功");
				sendJsonData(response, jsonStr);
			}else{
				String jsonStr = JSON.toJSONString(importPerson+" 该用户不存在!");
				sendJsonData(response, jsonStr);
			}
		}
	}
	
	/**
	 * @author pxl
	 * @throws Exception 
	 * @date 2016-12-8
	 * 根据大小名单,会员等级设置定向  点击保存按钮
	 */
	@RequestMapping(value="/saveDirection",method={RequestMethod.POST,RequestMethod.GET})
	public void saveDirection(HttpServletResponse response,HttpServletRequest request,String businessName,String systemBusTypeStr,String isRisk,String smsTypeStr,String businessNo,String nameMode,
			String addMan,String isTemplet,String generateType,String bigList_show,String debarBigList_show,String addSmallList_show,
			String debarSmallList_show,String add_person_show,String debar_person_show,String remark,String memberGrade_show,String continueToInsert) throws Exception{
					
		SpecialNameList snl = new SpecialNameList();
		snl.setBusinessNo(businessNo);
		snl.setIsQuote((short)2);//是否被引用（1.是  2.否）
		snl.setBusinessName(businessName);
		if(nameMode!=null && nameMode.equals("1")){
			snl.setNameMode((short)1);//定向方式  1.大小名单，2.会员等级
		}
		if(nameMode!=null && nameMode.equals("2")){
			snl.setNameMode((short)2);//定向方式  1.大小名单，2.会员等级
		}
		
		snl.setIsUse((short)1);//是否启用（1.是  2.否） 默认启用
		if(generateType!=null && "手动生成".equals(generateType)){
			snl.setGenerateType((short)1);
		}else{
			//模板生成
			snl.setGenerateType((short)1);
		}
		snl.setIsTemplet(new Short(isTemplet));
		snl.setAddTime(new Date());
		snl.setAddMan(addMan);
		snl.setRemark(remark);
		
		String regex = ",|，|\\s+";
		StringBuilder s= new StringBuilder("00000");
		for(int i=0;i<9;i++){
			s=s.append("00000");
		}
		System.out.println("systemBusTypeStr :"+systemBusTypeStr);
		if(systemBusTypeStr!=null && !"".equals(systemBusTypeStr)){
			//系统业务  占位符
			snl.setBusinessType((short)1);
			String [] sbts = systemBusTypeStr.split(regex);
			for(int i=0;i<sbts.length;i++){
				System.out.println("sbts>>>"+sbts[i]);
				if(sbts[i]!=null && !"".equals(sbts[i])){
					s.replace(new Short(sbts[i].trim())-1, new Short(sbts[i].trim()), "1");
				}
			}
			
			if(isRisk.equals("1")){
				//风险名单
				s.replace(49, 50, "1");
			}
			snl.setSystemBusType(s.toString());
		}
		
		
		if(smsTypeStr!=null && !"".equals(smsTypeStr)){
			//短信通知
			snl.setBusinessType((short)2);
			String [] sts = smsTypeStr.split(regex);
			for(int i=0;i<sts.length;i++){
				if(sts[i]!=null && !"".equals(sts[i])){
					s.replace(new Short(sts[i].trim())-1, new Short(sts[i].trim()), "1");
				}
			}
			snl.setSmsType(s.toString());
		}
		int rows = specialNameListService.insertSelective(snl);
		
		//根据定向编号获取定向名单列表的id
		snl= specialNameListService.getSnlsByNoOrName(snl);
		ActiveObjectList aol = new ActiveObjectList();
		aol.setSNLId(snl.getId());
		//添加大名单
		if(bigList_show!=null && !"".equals(bigList_show)){
			aol.setIsRightOrExcept((short)1);//1包含，2排除
			aol.setNameType((short)1);//名单类型（1大名单，2小名单，3用户名，4会员等级）
			String [] palmMax = bigList_show.split(regex);//添加的大名单数组
			Set<String> blsSet = new HashSet<String>();
			for(int i=0;i<palmMax.length;i++){
				System.out.println("大名单》》》》"+palmMax[i]);
				if(palmMax[i]!=null && !"".equals(palmMax[i])){
					aol.setNameContent(palmMax[i].trim());
					activeObjectListService.insert(aol);
				}
			}
			
		}
		//排除大名单
		if(debarBigList_show!=null && !"".equals(debarBigList_show)){
			
			aol.setIsRightOrExcept((short)2);
			aol.setNameType((short)1);
			String [] removerMax=debarBigList_show.split(regex);//添加的大名单数组
			Set<String> dbsSet = new HashSet<String>();
			for(int i=0;i<removerMax.length;i++){
				System.out.println("排除大名单》》》"+removerMax[i]);
				if(removerMax[i]!=null && !"".equals(removerMax[i])){
					aol.setNameContent(removerMax[i].trim());
					activeObjectListService.insert(aol);
				}
			}
			
		}
		//添加小名单
		if(addSmallList_show !=null && !"".equals(addSmallList_show)){
			aol.setIsRightOrExcept((short)1);
			aol.setNameType((short)2);
			String [] palmMin=addSmallList_show.split(regex);//添加小名单数组
			for(int i=0;i<palmMin.length;i++){
				System.out.println("名单编号》》》"+palmMin[i]);
				if(StringUtils.isNotBlank(palmMin[i])){
					aol.setNameContent(palmMin[i]);
					activeObjectListService.insert(aol);
				}
			}
//			Set<String> aslSet = new HashSet<String>();
//			for(int i=0;i<palmMin.length;i++){
//				//获取每一个小标题下用户的用户名单编号
//				List<RemoveName> nameList =removeNameService.getUserNameMin(palmMin[i].trim());
//				System.out.println("小名单》》》"+palmMin[i]);
//				for(RemoveName rn:nameList){
//					aslSet.add(rn.getNameno());
//				}
//			}
//			Iterator<String> it =aslSet.iterator();
//			while(it.hasNext()){
//				String nameNo=it.next();
//				System.out.println("名单编号》》》"+nameNo);
//				aol.setNameContent(nameNo);
//				activeObjectListService.insert(aol);
//				
//			}
		}
		//排除小名单
		if(debarSmallList_show!=null && !"".equals(debarSmallList_show)){
			aol.setIsRightOrExcept((short)2);
			aol.setNameType((short)2);
			String [] removerMin=debarSmallList_show.split(regex);//排除的小名单数组
			for(int i=0;i<removerMin.length;i++){
				System.out.println("名单编号》》》"+removerMin[i]);
				if(StringUtils.isNotBlank(removerMin[i])){
					aol.setNameContent(removerMin[i]);
					activeObjectListService.insert(aol);
				}
			}
//			Set<String> dslSet = new HashSet<String>();
//			for(int i=0;i<removerMin.length;i++){
//				List<RemoveName> nameList =removeNameService.getUserNameMin(removerMin[i].trim());
//				System.out.println("排除小名单》》》"+removerMin[i]);
//				for(RemoveName rn:nameList){
//					dslSet.add(rn.getNameno());
//				}
//			}
//			Iterator<String> it =dslSet.iterator();
//			while(it.hasNext()){
//				String nameNo=it.next();
//				System.out.println("名单编号》》》"+nameNo);
//				aol.setNameContent(nameNo);
//				activeObjectListService.insert(aol);
//				
//			}
		}
		//添加个人展示
		if(add_person_show!=null && !"".equals(add_person_show)){
			aol.setIsRightOrExcept((short)1);
			aol.setNameType((short)3);
			String [] palmpersonage=add_person_show.split(regex);//添加个人数组
			for(int i=0;i<palmpersonage.length;i++){
				System.out.println("添加个人用户名===="+palmpersonage[i]);
				if(palmpersonage[i]!=null && !"".equals(palmpersonage[i])){
					System.out.println(palmpersonage[i].trim());
					aol.setNameContent(setEncrypt(palmpersonage[i].trim()));
					System.out.println(aol.getNameContent());
					activeObjectListService.insert(aol);
				}
			}
		}
		//排除个人展示
		if(debar_person_show!=null && !"".equals(debar_person_show)){
			aol.setIsRightOrExcept((short)2);
			aol.setNameType((short)3);
			String [] removerpersonage=debar_person_show.split(regex);//排除的用户数组
			for(int i=0;i<removerpersonage.length;i++){
				System.out.println("添加个人用户名===="+removerpersonage[i]);
				if(removerpersonage[i]!=null && !"".equals(removerpersonage[i])){
					System.out.println(removerpersonage[i].trim());
					aol.setNameContent(setEncrypt(removerpersonage[i].trim()));
					System.out.println(aol.getNameContent());
					activeObjectListService.insert(aol);
				}
			}
		}
		
		//添加会员等级
		if(memberGrade_show!=null && !"".equals(memberGrade_show)){
			aol.setIsRightOrExcept((short)1);
			aol.setNameType((short)4);
			String [] palmmember=memberGrade_show.split(",");//获奖的会员等级数组
			StringBuilder sb = new StringBuilder("0");
			for(int i=0;i<50;i++){
				sb=sb.append("0");
			}
			for(int i=0;i<palmmember.length;i++){
				System.out.println(palmmember[i].trim());
				int p = Integer.parseInt(palmmember[i].trim());
				for(int j=0;j<sb.length();j++){
					if(p==j){
						sb.replace(j,j+1,"1");
					}
				}
			}
			aol.setNameContent(sb.toString());
			activeObjectListService.insert(aol);
		}
			
		String jsonStr = null;
		if(continueToInsert!=null && continueToInsert.equals("1")){
			if(systemBusTypeStr!=null && !"".equals(systemBusTypeStr)){
				jsonStr = JSON.toJSONString("1");
			}else{
				jsonStr = JSON.toJSONString("2");
			}
			
		}else{
			if(rows>0){
				jsonStr = JSON.toJSONString("保存成功");
			}
		}
		sendJsonData(response, jsonStr);
	}
	
	/**
	 * 根据id删除对应的定向名单
	 * @throws Exception 
	 */
	@RequestMapping("/deleteSpecialNameList")
	public void deleteSpecialNameList(SpecialNameList snl) throws Exception{
		
		int rows=specialNameListService.deleteByPrimaryKey(snl.getId());
		//还需要删除对应的业务对象名单
		int lines = activeObjectListService.deleteBySNLId(snl.getId());
		
		String jsonStr = null;
		if(rows>0 && lines>0){
			jsonStr = JSON.toJSONString("删除成功");
		}else{
			jsonStr = JSON.toJSONString("删除失败");
		}
		sendJsonData(response, jsonStr);
	}
	
	/**
	 * 根据id改变定向名单的启用状态
	 * @throws Exception 
	 */
	@RequestMapping("/modifyIsUse")
	public void modifyIsUse(SpecialNameList snl) throws Exception{
		if(snl.getIsUse()==1){
			//将启用状态改为停用状态
			snl.setIsUse((short)2);
		}else if(snl.getIsUse()==2){
			//将停用状态改为启用状态
			snl.setIsUse((short)1);
		}
		int rows=specialNameListService.updateSpecialNameList(snl);
		String jsonStr = null;
		if(rows>0){
			jsonStr = JSON.toJSONString("操作成功");
		}else{
			jsonStr = JSON.toJSONString("操作失败");
		}
		sendJsonData(response, jsonStr);
	}
	
	/**
	 * 查看详情
	 */
	@RequestMapping("/examineDetail")
	public ModelAndView examineDetail(SpecialNameList snl){
		
		snl = specialNameListService.getSpecialNameList(snl);
		ModelAndView mv= new ModelAndView();
		
		String systemBusType = snl.getSystemBusType();
		StringBuilder sb = new StringBuilder();
		if(systemBusType!=null && !"".equals(systemBusType)){
			System.out.println("占位符的长度》》》》"+systemBusType.length());
			for(int i=0;i<systemBusType.length()-1;i++){
				char sbt = systemBusType.charAt(i);
				System.out.println("sbt>>>"+sbt);
				if(sbt=='1'){
					if(i==0){
						sb=new StringBuilder(ManualAward_Constant.SYSTEMBUSTYPE.get((short)1));
						sb=sb.append(",");
					}else{
						sb = sb.append(new StringBuilder(ManualAward_Constant.SYSTEMBUSTYPE.get((short)(i+1))));
						sb=sb.append(",");
					}
				}
			}
			snl.setSystemBusTypeStr(sb.toString().substring(0, sb.length()-1));
		}
		String smsType = snl.getSmsType();
		if(smsType!=null && !"".equals(smsType)){
			System.out.println("占位符的长度》》》》"+smsType.length());
			for(int i=0;i<smsType.length();i++){
				char st = smsType.charAt(i);
				System.out.println("st>>>"+st);
				if(st=='1'){
					if(i==0){
						sb=new StringBuilder(ManualAward_Constant.SMSTYPE.get((short)1));
						sb=sb.append(",");
					}else{
						sb = sb.append(new StringBuilder(ManualAward_Constant.SMSTYPE.get((short)(i+1))));
						sb=sb.append(",");
					}
				}
			}
			snl.setSmsTypeStr(sb.toString().substring(0, sb.length()-1));
		}
		
		mv.addObject("snl",snl);
		mv.addObject("directionmold",ManualAward_Constant.DIRECTION_MOLD);//定向类型
		mv.addObject("direction_type",ManualAward_Constant.DIRECTION_TYPE);//定向方式
		mv.addObject("use_status",ManualAward_Constant.USE_STATUS);//启用状态
		mv.addObject("quote_status",ManualAward_Constant.QUOTE_STATUS);//引用状态
		mv.addObject("rectype_map",ManualAward_Constant.RECTYPE_MAP);//生成方式
		mv.addObject("template_judge",ManualAward_Constant.TEMPLATE_JUDGE);//是否为模板
		mv.setViewName("admin/direction/specialNameListDetail");
		return mv;
	}
	
	/**
	 * 根据id编辑定向名单列表，只是进入编辑页面   还没保存
	 */
	@RequestMapping("/editSpecialNameList")
	public ModelAndView editSpecialNameList(SpecialNameList snl){
		
		snl = specialNameListService.getSpecialNameList(snl);
		Set<String> set1=new HashSet<String>();//获奖大名单
		Set<String> set2=new HashSet<String>();//获奖小名单
		Set<String> set3=new HashSet<String>();//获奖用户名
		Set<String> set4=new HashSet<String>();//排除大名单
		Set<String> set5=new HashSet<String>();//排除小名单
		Set<String> set6=new HashSet<String>();//排除用户名
		/*Set<String> set7=new HashSet<String>();//会员等级名称
		Set<String> set8=new HashSet<String>();//会员等级*/
		Map<Short,String> map3 = new HashMap<Short,String>();//会员
		Map<Short,String> map1=new HashMap<Short,String>();//系统业务
		Map<Short,String> map2=new HashMap<Short,String>();//短信通知
		ModelAndView mv = new ModelAndView();
		String systemBusType = snl.getSystemBusType();
		if(systemBusType!=null && !"".equals(systemBusType)){
			System.out.println("占位符的长度》》》》"+systemBusType.length());
			for(int i=0;i<systemBusType.length()-1;i++){
				char sbt = systemBusType.charAt(i);
				System.out.println("sbt>>>"+sbt);
				if(sbt=='1'){
					map1.put(new Short((short)(i+1)),ManualAward_Constant.SYSTEMBUSTYPE.get((short)(i+1)));
				}
			}
			
			if(systemBusType.charAt(systemBusType.length()-1)=='1'){
				//系统业务最后一个字符为1，即该定向名单为风险名单
				mv.addObject("isRisk","1");
			}
		}
		
		String smsType = snl.getSmsType();
		if(smsType!=null && !"".equals(smsType)){
			System.out.println("占位符的长度》》》》"+smsType.length());
			for(int i=0;i<smsType.length();i++){
				char st = smsType.charAt(i);
				System.out.println("st>>>"+st);
				if(st=='1'){
					map2.put(new Short((short)(i+1)),ManualAward_Constant.SMSTYPE.get((short)(i+1)));
				}
			}
		}
		
		snl.setAddTime(new Date());
		snl.setGenerateType((short)2);//名单生成方式（1.手动  2.模板）
		
		//根据定向名单列表的id获得对应的业务对象名单
		List<ActiveObjectList> aolList = activeObjectListService.selectBySNLId(snl.getId());
		for(ActiveObjectList aol:aolList){
			if(aol.getIsRightOrExcept()==1){
				if(aol.getNameType()==1){
					//获奖大名单
					set1.add(aol.getNameContent().trim());
				}
				if(aol.getNameType()==2){
					//获奖小名单
					RemoveName rn = removeNameService.selectNameAndNameType(aol.getNameContent().trim());
					set2.add(aol.getNameContent().trim()+"-"+rn.getName());
				}
				if(aol.getNameType()==3){
					System.out.println("个人名称==="+getDecrypt(aol.getNameContent().trim()));
					//获奖用户名
					set3.add(getDecrypt(aol.getNameContent().trim()));
				}
				if(aol.getNameType()==4){
					//需要对会员等级占位符进行解析
					String nc = aol.getNameContent().trim();
					for(int i=0;i<nc.length();i++){
						char c = nc.charAt(i);
						if(c=='1'){
							//根据会员等级获取会员等级的名称
							UserGrade userGrade=userGradeService.getusergrade(new BigDecimal(i));
							if(userGrade!=null){
								map3.put((short)i, userGrade.getUgradename());
								/*set8.add(String.valueOf(i));//会员等级
								set7.add(userGrade.getUgradename());*/
							}
						}
					}
				}
			}else if(aol.getIsRightOrExcept()==2){
				if(aol.getNameType()==1){
					//排除大名单
					set4.add(aol.getNameContent().trim());
				}
				if(aol.getNameType()==2){
					//排除小名单
					System.out.println(aol.getNameContent().trim());
					RemoveName rn = removeNameService.selectNameAndNameType(aol.getNameContent().trim());
					if(rn!=null){
						set5.add(aol.getNameContent().trim()+"-"+rn.getName());
					}
				}
				if(aol.getNameType()==3){
					//排除用户名
					set6.add(getDecrypt(aol.getNameContent().trim()));
				}
			}
		}
		
		
		
		//大小名单  会员等级
		List<RemoveName> removeNames = removeNameService.getGroupNameType();//查询到说有的大名单
		/*UserGrade ug=new UserGrade();
		//需要获得启用状态的会员
		ug.setStatus((short)1);*//** 状态 1 启用 0 停用  2废弃*/
		List<UserGrade> ugList=userGradeService.selectiveForNormal/*selectEnableMembers*/(null);
		Map<BigDecimal, String> map = new HashMap<BigDecimal, String>();//等级   名称
		Map<Object, Object> map_ = new TreeMap<Object, Object>();
		for(UserGrade u:ugList){
			map.put(u.getUgrade(), u.getUgradename());//等级   名称
			map_.put(u.getRankno(), u.getUgrade());//序号  等级
		}
		mv.addObject("map",map);
		mv.addObject("map_",map_);
		mv.addObject("removeNames", removeNames);
		
		mv.addObject("set1",set1);//获奖大名单
		mv.addObject("set2",set2);//获奖小名单
		mv.addObject("set3",set3);//获奖用户名
		mv.addObject("set4",set4);//排除大名单
		mv.addObject("set5",set5);//排除小名单
		mv.addObject("set6",set6);//排除用户名
		/*mv.addObject("set7",set7);//会员等级名称
		mv.addObject("set8",set8);//会员等级*/
		mv.addObject("map3",map3);//存会员等级   会员名称
		mv.addObject("map1",map1);//系统业务
		mv.addObject("map2",map2);//短信通知
		
		mv.addObject("snl",snl);
		
		mv.addObject("sbtype",ManualAward_Constant.SYSTEMBUSTYPE);//系统业务具体类型
		mv.addObject("smtype",ManualAward_Constant.SMSTYPE);//短信通知具体类型
		
		if(snl.getNameMode()==1){
			mv.setViewName("admin/direction/sizeListEdit");
		}else if(snl.getNameMode()==2){
			mv.setViewName("admin/direction/memberGradeEdit");
		}
		return mv;
	}
	
	/**
	 * @author pxl
	 * @throws Exception 
	 * @date 2016-12-8
	 * 编辑定向名单完成后   并保存
	 */
	@RequestMapping(value="/saveEditSNL",method={RequestMethod.POST,RequestMethod.GET})
	public void saveEditSNL(HttpServletResponse response,HttpServletRequest request,String businessName,String systemBusTypeStr,String smsTypeStr,String businessNo,
			String addMan,String isTemplet,String generateType,String bigList_show,String debarBigList_show,String addSmallList_show,String isRisk,
			String debarSmallList_show,String add_person_show,String debar_person_show,String remark,String memberGrade_show) throws Exception{
				
		//System.out.println("add_person_show===="+add_person_show);
		
		SpecialNameList snl = new SpecialNameList();
		snl.setBusinessNo(businessNo);
		snl.setBusinessName(businessName);
		snl.setIsTemplet(new Short(isTemplet));
		
		snl.setAddTime(new Date());
		snl.setAddMan(addMan);
		snl.setRemark(remark);
		
		/*String regex = ",|，|\\s+";*/
		String regex = ",";
		StringBuilder s= new StringBuilder("00000");
		for(int i=0;i<9;i++){
			s=s.append("00000");
		}
		if(systemBusTypeStr!=null && !"".equals(systemBusTypeStr)){
			//系统业务  占位符
			snl.setBusinessType((short)1);
			String [] sbts = systemBusTypeStr.split(regex);
			for(int i=0;i<sbts.length;i++){
				System.out.println("sbts>>>"+sbts[i]);
				if(sbts[i]!=null && !"".equals(sbts[i])){
					s.replace(new Short(sbts[i].trim())-1, new Short(sbts[i].trim()), "1");
				}
			}
			
			if("1".equals(isRisk)){
				s.replace(s.length()-1, s.length(), "1");
			}else if("0".equals(isRisk)){
				s.replace(s.length()-1, s.length(), "0");
			}
			snl.setSystemBusType(s.toString());
		}
		if(smsTypeStr!=null && !"".equals(smsTypeStr)){
			//短信通知
			snl.setBusinessType((short)2);
			String [] sts = smsTypeStr.split(regex);
			for(int i=0;i<sts.length;i++){
				if(sts[i]!=null && !"".equals(sts[i])){
					s.replace(new Short(sts[i].trim())-1, new Short(sts[i].trim()), "1");
					snl.setSmsType(s.toString());
				}
			}
		}
		int rows = specialNameListService.updateSpecialNameList(snl);
		
		//根据定向编号获取定向名单列表的id
		snl= specialNameListService.getSnlsByNoOrName(snl);
		ActiveObjectList aol = new ActiveObjectList();
		aol.setSNLId(snl.getId());
		//根据定向名单的id将对应的业务对象名单先删除掉
		int lines = activeObjectListService.deleteBySNLId(aol.getSNLId());
		//添加大名单
		if(bigList_show!=null && !"".equals(bigList_show)){
			aol.setIsRightOrExcept((short)1);//1包含，2排除
			aol.setNameType((short)1);//名单类型（1大名单，2小名单，3用户名，4会员等级）
			String [] palmMax = bigList_show.split(regex);//添加的大名单数组
			Set<String> blsSet = new HashSet<String>();
			for(int i=0;i<palmMax.length;i++){
				//获取每一个大标题下用户的用户名单编号
				System.out.println("大名单》》》》"+palmMax[i]);
				if(palmMax[i]!=null && !"".equals(palmMax[i])){
					aol.setNameContent(palmMax[i].trim());
					activeObjectListService.insert(aol);
				}
				
			}
			
		}
		//排除大名单
		if(debarBigList_show!=null && !"".equals(debarBigList_show)){
			
			aol.setIsRightOrExcept((short)2);
			aol.setNameType((short)1);
			String [] removerMax=debarBigList_show.split(regex);//添加的大名单数组
			Set<String> dbsSet = new HashSet<String>();
			for(int i=0;i<removerMax.length;i++){
				System.out.println("排除大名单》》》"+removerMax[i]);
				if(removerMax[i]!=null && !"".equals(removerMax[i])){
					aol.setNameContent(removerMax[i].trim());
					activeObjectListService.insert(aol);
				}
			}
		}
		//添加小名单
		if(addSmallList_show !=null && !"".equals(addSmallList_show)){
			aol.setIsRightOrExcept((short)1);
			aol.setNameType((short)2);
			String [] palmMin=addSmallList_show.split(regex);//添加小名单数组
			for(int i=0;i<palmMin.length;i++){
				System.out.println("名单编号》》》"+palmMin[i]);
				aol.setNameContent(palmMin[i]);
				activeObjectListService.insert(aol);
			}
//			Set<String> aslSet = new HashSet<String>();
//			for(int i=0;i<palmMin.length;i++){
//				//获取每一个小标题下用户的用户名单编号
//				List<RemoveName> nameList =removeNameService.getUserNameMin(palmMin[i].trim());
//				System.out.println("小名单》》》"+palmMin[i]);
//				for(RemoveName rn:nameList){
//					aslSet.add(rn.getNameno());
//				}
//			}
//			Iterator<String> it =aslSet.iterator();
//			while(it.hasNext()){
//				String nameNo=it.next();
//				System.out.println("名单编号》》》"+nameNo);
//				aol.setNameContent(nameNo);
//				activeObjectListService.insert(aol);
//				
//			}
		}
		//排除小名单
		if(debarSmallList_show!=null && !"".equals(debarSmallList_show)){
			aol.setIsRightOrExcept((short)2);
			aol.setNameType((short)2);
			String [] removerMin=debarSmallList_show.split(regex);//排除的小名单数组
			for(int i=0;i<removerMin.length;i++){
				System.out.println("名单编号》》》"+removerMin[i]);
				aol.setNameContent(removerMin[i]);
				activeObjectListService.insert(aol);
			}
//			Set<String> dslSet = new HashSet<String>();
//			for(int i=0;i<removerMin.length;i++){
//				List<RemoveName> nameList =removeNameService.getUserNameMin(removerMin[i].trim());
//				System.out.println("排除小名单》》》"+removerMin[i]);
//				for(RemoveName rn:nameList){
//					dslSet.add(rn.getNameno());
//				}
//			}
//			Iterator<String> it =dslSet.iterator();
//			while(it.hasNext()){
//				String nameNo=it.next();
//				System.out.println("名单编号》》》"+nameNo);
//				aol.setNameContent(nameNo);
//				activeObjectListService.insert(aol);
//				
//			}
		}
		//添加个人展示
		if(add_person_show!=null && !"".equals(add_person_show)){
			aol.setIsRightOrExcept((short)1);
			aol.setNameType((short)3);
			String [] palmpersonage=add_person_show.split(regex);//添加个人数组
			for(int i=0;i<palmpersonage.length;i++){
				//System.out.println("palmpersonage[i]==="+palmpersonage[i]);
				if(palmpersonage[i]!=null && !"".equals(palmpersonage[i])){
					aol.setNameContent(setEncrypt(palmpersonage[i].trim()));
					activeObjectListService.insert(aol);
				}
			}
		}
		//排除个人展示
		if(debar_person_show!=null && !"".equals(debar_person_show)){
			aol.setIsRightOrExcept((short)2);
			aol.setNameType((short)3);
			String [] removerpersonage=debar_person_show.split(regex);//排除的用户数组
			for(int i=0;i<removerpersonage.length;i++){
				if(removerpersonage[i]!=null && !"".equals(removerpersonage[i])){
					aol.setNameContent(setEncrypt(removerpersonage[i].trim()));
					activeObjectListService.insert(aol);
				}
			}
		}
		//添加会员等级
		if(memberGrade_show!=null && !"".equals(memberGrade_show)){
			aol.setIsRightOrExcept((short)1);
			aol.setNameType((short)4);
			String [] palmmember=memberGrade_show.split(regex);//获奖的会员等级数组
			StringBuilder sb = new StringBuilder("0");
			for(int i=0;i<50;i++){
				sb=sb.append("0");
			}
			for(int i=0;i<palmmember.length;i++){
				int p = Integer.parseInt(palmmember[i].trim());
				for(int j=0;j<sb.length();j++){
					if(p==j){
						sb.replace(j,j+1,"1");
					}
				}
			}
			aol.setNameContent(sb.toString());
			activeObjectListService.insert(aol);
		}
		if(rows>0){
			String jsonStr = JSON.toJSONString("保存成功");
			sendJsonData(response, jsonStr);
		}
	}
	
	/**
	 * 检验定向标题是否唯一
	 * @throws IOException 
	 */
	@RequestMapping("/businessNameIsOnly")
	public void businessNameIsOnly(HttpServletResponse response,SpecialNameList snl) throws IOException{
		//System.out.println("定向标题》》》"+snl.getBusinessName());
		snl = specialNameListService.getSnlsByNoOrName(snl);
		String jsonStr = null;
		if(snl!=null){
			jsonStr = JSON.toJSONString("定向标题已存在");
		}
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	
	/**
	 * 编辑时 检验定向标题是否唯一   标题不等于自身
	 * @throws IOException 
	 */
	@RequestMapping("/editBnIsOnly")
	public void editBnIsOnly(HttpServletResponse response,SpecialNameList snl) throws IOException{
		
		snl = specialNameListService.getSNLByName(snl);
		String jsonStr = null;
		if(snl!=null){
			jsonStr = JSON.toJSONString("定向标题已存在");
		}
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 查看定向名单的最终人员
	 */
	@RequestMapping("/finalPersonView")
	public ModelAndView finalPersonView(HttpServletRequest request,BigDecimal id){
		
		//根据定向名单列表的id获取业务对象名单
		List<ActiveObjectList> aolList = activeObjectListService.selectBySNLId(id);
		
		Set<String> palmSet=new HashSet<String>();//存放所有获奖的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
		Set<String> removeSet=new HashSet<String>();//存放所有排除的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
		Set<String>  ultimately=new HashSet<String>();//最终获奖人名单（注意不能省！因Set在迭代时不能删除元素）
		
		for(ActiveObjectList aol:aolList){
			if(aol.getIsRightOrExcept()==1){
				if(aol.getNameType()==1){
					//获奖大名单
					List<RemoveName> rnList = removeNameService.getUserNameMax(aol.getNameContent().trim());
					for(RemoveName rn : rnList){
						System.out.println("获奖大名单下的所有用户》》》"+rn.getLoginname());
						palmSet.add(rn.getLoginname());
						ultimately.add(rn.getLoginname());
					}
					System.out.println("获奖大名单用户总数为："+palmSet.size());
				}
				if(aol.getNameType()==2){
					//获奖小名单
					List<RemoveName> rnList = removeNameService.getRemoveNameByNameNo(aol.getNameContent().trim());
					for(RemoveName rn:rnList){
						System.out.println("获奖小名单下的所有用户》》》》》"+rn.getLoginname());
						palmSet.add(rn.getLoginname());
						ultimately.add(rn.getLoginname());
					}
					System.out.println("小名单之后的用户总数："+palmSet.size());	
				}
				if(aol.getNameType()==3){
					System.out.println("获奖用户名》》》》"+aol.getNameContent());
					//获奖用户名
					palmSet.add(aol.getNameContent().trim());
					ultimately.add(aol.getNameContent().trim());
				}
				if(aol.getNameType()==4){
					//获取每一个会员等级下的所有用户
					//需要对会员等级占位符进行解析
					String nc = aol.getNameContent().trim();
					for(int i=0;i<nc.length();i++){
						char c = nc.charAt(i);
						if(c=='1'){
							List<UserAccountSafeInfo> name=userAccountSafeInfoService.getuseradmin((short)i);
							for(UserAccountSafeInfo re : name){
								System.out.println("会员ID》》"+re.getBaseid());
								//获取用户名
								UserBaseAccountInfo us= userBaseAccountInfoService.getUserBaseAccountInfoById(re.getBaseid());
								//保存进Set集合当中
								if(us!=null){
									System.out.println("会员等级用户名》》》》"+us.getLoginname());
									if(us.getLoginname()!=null && !"".equals(us.getLoginname())){
										palmSet.add(us.getLoginname());
										ultimately.add(us.getLoginname());
									}
								}
							}
						}
					}
					
					System.out.println("会员等级的用户总数："+palmSet.size());
				}
				
				System.out.println("获奖的人数为》》》"+palmSet.size());
				
			}else if(aol.getIsRightOrExcept()==2){
				if(aol.getNameType()==1){
					//排除大名单
					List<RemoveName> rnList = removeNameService.getUserNameMax(aol.getNameContent().trim());
					for(RemoveName rn : rnList){
						System.out.println("排除大名单用户名》》》"+rn.getLoginname());
						removeSet.add(rn.getLoginname());
					}
					System.out.println("获奖大名单用户总数为："+removeSet.size());
				}
				
				if(aol.getNameType()==2){
					//排除小名单
					List<RemoveName> rnList = removeNameService.getRemoveNameByNameNo(aol.getNameContent().trim());
					for(RemoveName rn : rnList){
						System.out.println("排除小名单用户名》》》"+rn.getLoginname());
						removeSet.add(rn.getLoginname());
					}
					System.out.println("排除小名单后用户总数："+removeSet.size());
				}
				if(aol.getNameType()==3){
					//排除用户名
					System.out.println("排除用户名》》》"+aol.getNameContent().trim());
					removeSet.add(aol.getNameContent().trim());
				}
				System.out.println("排除的人数为》》》》"+removeSet.size());
			}
		}
		System.out.println("排除的总人数为》》》》"+removeSet.size());
		
		//将获奖用户名单与排除名单对比，一致时，移除出获奖名单
		for (String palm : palmSet){ 
		      for (String remove : removeSet){
		    	  if(palm!=null && !"".equals(palm)){
		    		  if(palm.equals(remove))
		    			  ultimately.remove(palm);				      				     
		    	  }
		      }	     
		}  
		
		/*用来存放最终的用户*/
		List<UserBaseAccountInfo> ubaiList = new ArrayList<UserBaseAccountInfo>();
		for(String loginname:ultimately){
			System.out.println("最终的获奖用户名单》》》"+loginname);
			//根据登录名获取对应的用户基本信息
			UserBaseAccountInfo ubai = userBaseAccountInfoService.getuserloginname(loginname);
			if(ubai!=null){
				ubaiList.add(getDecryptionUserBaseAccountInfoDetail(ubai));
			}
		}
		
		/*用来存放最终的排除用户*/
		List<UserBaseAccountInfo> removeList = new ArrayList<UserBaseAccountInfo>();
//		for(String loginname:removeSet){
//			System.out.println("最终的排除名单===="+loginname);
//			//根据登录名获取对应的用户基本信息
//			UserBaseAccountInfo ubai = userBaseAccountInfoService.getuserloginname(loginname);
//			if(ubai!=null){
//				removeList.add(getDecryptionUserBaseAccountInfoDetail(ubai));
//			}
//		}
		
		ModelAndView mv = new ModelAndView();
		request.setAttribute("ubaiList", ubaiList);//最终的获奖用户
		request.setAttribute("removeList", removeList);//最终的排除名单
		mv.setViewName("admin/direction/finalPersonView");
		return mv;
	}
	
}
