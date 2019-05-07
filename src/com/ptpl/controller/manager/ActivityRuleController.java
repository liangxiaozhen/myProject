package com.ptpl.controller.manager;
 

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ptpl.controller.BaseController;
import com.ptpl.model.ActivityAwardRule;
import com.ptpl.model.ActivityRule;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.RemoveName;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.service.ActivityAwardRuleServiceI;
import com.ptpl.service.ActivityRuleServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: ActivityRuleController 
* @Package com.ptpl.controller 
* @Description: TODO(标的活动规则 控制层 ) 
* @author chenjiaming
* @date 2016年08月25日 12:03:21
* @version V1.0
 */
@Controller
@RequestMapping("/admin/activityRule")
public class ActivityRuleController extends BaseController{
	 /**
	  * 标的活动规则 Service
	  */
	 @Autowired
	 private ActivityRuleServiceI activityRuleService;
	 /**
	  * 标的活动奖励规则 Service
	  */
	 @Autowired
	 private ActivityAwardRuleServiceI activityAwardRuleService;
	 /**
	  * 会员等级Service
	  */
	 @Autowired
	 private UserGradeServiceI userGradeServiceI;
	 /**
	  * 排除人员名单 Service
	  */
	 @Autowired
	 private RemoveNameServiceI removeNameServiceI;
	 /**
	  * 标的  Service
	  */
	 @Autowired
	 private TenderItemServiceI tenderItemServiceI;
	 /**
	  * 奖品Service
	  */
	 @Autowired
	 private AwardServiceI awardService;
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(标的活动规则查询通用方法) 
	 * @param @param activityRule
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(ActivityRule activityRule){
 		ModelAndView modelAndView = new ModelAndView();	
 			int num = 1;
			int pageSize = 20;
			String sort = "id.desc";
			Order.formString(sort);
			PageHelper.startPage(num, pageSize);
			List<ActivityRule> activityRules = activityRuleService.findActivityRules(activityRule);
			PageInfo<ActivityRule> pagehelper = new PageInfo<ActivityRule>(activityRules);
			pagehelper.setFirstPage(1);
			int lasePageNum = 0;
			if(pagehelper.getTotal() % pageSize ==0){
				lasePageNum = (int)pagehelper.getTotal() / pageSize;
			}else{
				lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
			}
			pagehelper.setLastPage(lasePageNum);
			modelAndView.setViewName("admin/activityRule/list");
			modelAndView.addObject("pagehelper", pagehelper);
 		    return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(标的活动规则模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param activityRule
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,ActivityRule activityRule){
		 String pageS = request.getParameter("pageSize");
		 String pageNo = request.getParameter("pageNo");
    	 int num = 1;
		 int pageSize = 20;
		 if(StringUtil.isNotEmpty(pageS)){
			 pageSize = Integer.parseInt(pageS);
		 }
		 if(StringUtil.isNotEmpty(pageNo)){
			 num = Integer.parseInt(pageNo);
		 }
		 //根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 //查询全部用户方法
       	 List<ActivityRule> activityRules = activityRuleService.findActivityRules(activityRule);
       	 PageInfo<ActivityRule> pagehelper = new PageInfo<ActivityRule>(activityRules);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/activityRule/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
	 * @throws IOException 
	  * 
	 * @Title: update 
	 * @Description: TODO(标的活动规则更新) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value = "/update",method = RequestMethod.POST)
	 public String update(HttpServletRequest request,HttpServletResponse response,ActivityRule activityRule,ActivityAwardRule activityAwardRule) throws IOException{
		 AdminUser adminUser =  getAdminUser();
 		 if(adminUser == null)return "redirect:/login.action";
		 if(activityRule != null && activityAwardRule != null && activityRule.getId() != null){
 			 //会员等级选择方式
			 String ugrade = activityRule.getUgrade();
			 //排除名单选择方式
			 String removeName = activityRule.getRemovenameno();
 			 String[] tattributes = activityRule.getTattributes();//投标属性
 			 String[] crestricts =activityRule.getCrestricts();//客户端限制
 			 String[] canceltnos = activityRule.getCanceltnos();//排除标号
 			 String[] specifytnos = activityRule.getSpecifytnos();//定向标号
  			 String canceltno = StringUtil.getLowGrade(canceltnos);
  			 String specifytno = StringUtil.getLowGrade(specifytnos);
  			 if(StringUtil.isNotEmpty(canceltno)){//排除标号
 				activityRule.setCanceltno(canceltno);
 			 }
 			 if(StringUtil.isNotEmpty(specifytno)){//定向标号
 				activityRule.setSpecifytno(specifytno);
 			 }
  			 if(tattributes != null){//标的属性
 				 String str2 = StringUtil.setPlaceholderForArr(tattributes, 10);
 				 activityRule.setTattribute(str2);
 			 }
 			if(crestricts != null){//客户端限制
				 String str2 = StringUtil.setPlaceholderForArr(crestricts, 10);
				 activityRule.setCrestrict(str2);
			 }
			 if(ugrade.equalsIgnoreCase("1")){//全部会员等级
				 /*查询全部会员等级*/
	  			 List<UserGrade> userGrades =userGradeServiceI.getAll(null);
	  			 ugrade = StringUtil.getPlaceholder(30);
	  			 for(UserGrade userGrade : userGrades){
	  				ugrade =  StringUtil.setPlaceholder(ugrade, userGrade.getUgrade().intValue() - 1);
	  			 }
			 }else if(ugrade.equalsIgnoreCase("2")){//部分会员等级
				 ugrade = getUgrades(activityRule);
			 }
			 if(removeName.equalsIgnoreCase("1")){//全部排除名单
 				 /*查询全部排除名单*/
		 		List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
		 		removeName = "";
	 			for(RemoveName removeName2: removeNames){
	 				removeName += removeName2.getNameno()+",";
				}
 		 	 }else if(removeName.equalsIgnoreCase("2")){//部分排除名单
 		 		removeName = "";
 		 		removeName = getRemovenamenos(activityRule);
			 }
 			 if(adminUser.getUsername() != null){
 				 activityRule.setAddman(adminUser.getUsername());
			 } 
			 
 			 activityRule.setUgrade(ugrade);
			 activityRule.setRemovenameno(removeName);
    		 Map<String,String> hashMap = new HashMap<String,String>();
 			 int count = 0;
			 count = activityRuleService.updateById(activityRule);
 			 if(count > 0){
 				 String isaudit = request.getParameter("isaudit2");//是否审核
				 String remark2 = request.getParameter("remark2");//备注
				 String id = request.getParameter("activityAwardRule_id");//主键ID
				 String firsttendertime = request.getParameter("firsttendertime");
 				if(adminUser.getUsername() != null){
					 activityAwardRule.setAddman(adminUser.getUsername());
				 }
				if(StringUtil.isNotEmpty(isaudit)){
					activityAwardRule.setIsaudit(new Short(isaudit));			 
				 }
				if(StringUtil.isNotEmpty(remark2)){
					activityAwardRule.setRemark(remark2);
				}
				if(StringUtil.isNotEmpty(firsttendertime)){
					activityAwardRule.setFirsttendertime(StringUtil.getDateByString(firsttendertime, "yyyy-MM-dd"));
				}
				if(StringUtil.isNotEmpty(id)){
					activityAwardRule.setId(new BigDecimal(id));
				}
				if(activityAwardRule.getId() != null){
  					count = 0;
					count = activityAwardRuleService.updateById(activityAwardRule);
					if(count > 0){
						hashMap.put("result", "success");
					}else{
						hashMap.put("result", "fail");
					}
				}else{
					hashMap.put("result", "fail");
				}
  			 }else{
				 hashMap.put("result", "fail");
			 }
			 String str = JSON.toJSONString(hashMap);
			 StringUtil.sendJsonData(response, str);
		 }
		 return null;
	 }
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看标的活动规则详情信息) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/detail")
	 public ModelAndView detail(HttpServletRequest request){
 		 String opid = request.getParameter("opid");
 		 if(StringUtil.isNotEmpty(opid)){
 			 ModelAndView modelAndView = new ModelAndView();
  		     ActivityRule  activityRule = activityRuleService.findActivityRuleById(new BigDecimal(opid));
  		     ActivityAwardRule activityAwardRule = activityAwardRuleService.findActivityAwardRuleByActid(new BigDecimal(opid));
  		     if(activityRule != null){
  		    	 String str = activityRule.getCrestrict();
  		    	 String[] strs = {"PC","IOS","安卓"};
  		    	 String[] strs2 = {"新手标","担保标","信用标","抵押标"};
  		    	 String crestrict = StringUtil.getStrAnalytical(str, strs);
  		    	 String str2 = activityRule.getTattribute();
  		    	 String tattribute = StringUtil.getStrAnalytical(str2, strs2);
  		    	 if(StringUtil.isNotEmpty(crestrict)){
  		    		crestrict = crestrict.substring(0, crestrict.lastIndexOf(","));
  		    	 }
  		    	if(StringUtil.isNotEmpty(tattribute)){
  		    		tattribute = tattribute.substring(0, tattribute.lastIndexOf(","));
  		    	 }
  		    	modelAndView.addObject("tattribute", tattribute);
  		    	modelAndView.addObject("crestrict", crestrict);
  		     }
    		 modelAndView.addObject("activityRule", activityRule);
   		     modelAndView.addObject("activityAwardRule", activityAwardRule);
		     
    		 modelAndView.setViewName("admin/activityRule/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	 
   	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(标的活动规则 跳转编辑页面) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/edit")
	 public ModelAndView edit(HttpServletRequest request){
 		 String opid = request.getParameter("opid");
 		 if(StringUtil.isNotEmpty(opid)){
 			 ModelAndView modelAndView = new ModelAndView();
 			 /**查询全部奖品**/
	 		 List<Award> awards = awardService.selectByCondition(null);
 			/*查询全部会员等级*/
  			 List<UserGrade> userGrades =userGradeServiceI.getAll(null);
  			 /*查询全部标*/
  			List<TenderItem> tenderItem = tenderItemServiceI.selectByCondition(null);
  			/*查询全部排除名单*/
 			List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
  		    ActivityRule  activityRule =activityRuleService.findActivityRuleById(new BigDecimal(opid));
  		    ActivityAwardRule activityAwardRule  = activityAwardRuleService.findActivityAwardRuleByActid(new BigDecimal(opid));
  		    String params = "";
  		    String crestrictParams = "";
  		    String tattributeParams = "";
		    
		    if(activityRule != null){
		    	 String ugrade = activityRule.getUgrade();
		    	 String crestrict = activityRule.getCrestrict();
		    	 String tattribute = activityRule.getTattribute();
		    	 
		    	 String specifytno = activityRule.getSpecifytno();
		    	 String[] specifytnos = specifytno.split(",");
		    	 String canceltno = activityRule.getCanceltno();
		    	 String[] canceltnos = canceltno.split(",");
		    	 if(specifytnos.length > 1){
		    		 modelAndView.addObject("specifytnos", specifytnos.length);
 		    	 }
		    	 if(canceltnos.length > 1){
		    		 modelAndView.addObject("canceltnos", canceltnos.length);
 		    	 }
		    	 List<Integer> lists = StringUtil.pars(ugrade);
		    	 List<Integer> lists2 = StringUtil.pars(crestrict);
		    	 List<Integer> lists3 = StringUtil.pars(tattribute);
		    	 
 		    	 for(Integer lit:lists){
		    		params +=lit+",";
		    	 }
 		    	 for(Integer lit2:lists2){
 		    		crestrictParams +=lit2+",";
		    	 }
 		    	 for(Integer lit3:lists3){
 		    		tattributeParams +=lit3+",";
		    	 }
 		     }
   		     modelAndView.addObject("activityRule", activityRule);
   		     modelAndView.addObject("activityAwardRule", activityAwardRule);
		     modelAndView.addObject("userGrades", userGrades);
		     modelAndView.addObject("removeNames", removeNames);
		     modelAndView.addObject("tenderItems", tenderItem);
			 modelAndView.addObject("awardList", awards);
			 modelAndView.addObject("params", params);
			 modelAndView.addObject("crestrictParams", crestrictParams);
			 modelAndView.addObject("tattributeParams", tattributeParams);
 			 
    		 modelAndView.setViewName("admin/activityRule/edit");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
	 
	 /**
	 * @throws IOException 
	  * 
	 * @Title: save 
	 * @Description: TODO(标的活动设置和标的活动奖励设置 保存) 
	 * @param @param request
	 * @param @param response
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/save",method = RequestMethod.POST)
	 public String save(HttpServletRequest request,HttpServletResponse response,ActivityRule activityRule,ActivityAwardRule activityAwardRule) throws IOException{
 		 AdminUser adminUser =  getAdminUser();
 		 if(adminUser == null)return "redirect:/admin/login.action";
		 if(activityRule != null && activityAwardRule != null){
 			 //会员等级选择方式
			 String ugrade = activityRule.getUgrade();
			 //排除名单选择方式
			 String removeName = activityRule.getRemovenameno();
 			 String[] tattributes = activityRule.getTattributes();//投标属性
 			 String[] crestricts =activityRule.getCrestricts();//客户端限制
 			 String[] canceltnos = activityRule.getCanceltnos();//排除标号
 			 String[] specifytnos = activityRule.getSpecifytnos();//定向标号
  			 String canceltno = StringUtil.getLowGrade(canceltnos);
  			 String specifytno = StringUtil.getLowGrade(specifytnos);
  			 if(StringUtil.isNotEmpty(canceltno)){//排除标号
 				activityRule.setCanceltno(canceltno);
 			 }
 			 if(StringUtil.isNotEmpty(specifytno)){//定向标号
 				activityRule.setSpecifytno(specifytno);
 			 }
  			 if(tattributes != null){//标的属性
 				 String str2 = StringUtil.setPlaceholderForArr(tattributes, 10);
 				 activityRule.setTattribute(str2);
 			 }
 			if(crestricts != null){//客户端限制
				 String str2 = StringUtil.setPlaceholderForArr(crestricts, 10);
				 activityRule.setCrestrict(str2);
			 }
			 if(ugrade.equalsIgnoreCase("1")){//全部会员等级
				 /*查询全部会员等级*/
	  			 List<UserGrade> userGrades =userGradeServiceI.getAll(null);
	  			 ugrade = StringUtil.getPlaceholder(30);
	  			 for(UserGrade userGrade : userGrades){
	  				ugrade =  StringUtil.setPlaceholder(ugrade, userGrade.getUgrade().intValue() - 1);
	  			 }
			 }else if(ugrade.equalsIgnoreCase("2")){//部分会员等级
				 ugrade = getUgrades(activityRule);
			 }
			 if(removeName.equalsIgnoreCase("1")){//全部排除名单
 				 /*查询全部排除名单*/
		 		List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
		 		removeName = "";
	 			for(RemoveName removeName2: removeNames){
	 				removeName += removeName2.getNameno()+",";
				}
 		 	 }else if(removeName.equalsIgnoreCase("2")){//部分排除名单
 		 		removeName = "";
 		 		removeName = getRemovenamenos(activityRule);
			 }
			 String actnoStr = StringUtil.getActno("GJBHD");
			 if(adminUser.getUsername() != null){
 				 activityRule.setAddman(adminUser.getUsername());
			 } 
			 if(actnoStr != null){
 				 activityRule.setActno(actnoStr);
			 }
			 activityRule.setSettime(new Date());
			 activityRule.setUgrade(ugrade);
			 activityRule.setRemovenameno(removeName);
    		 Map<String,String> hashMap = new HashMap<String,String>();
 			 int count = 0;
			 count = activityRuleService.insertSelective(activityRule);
 			 if(count > 0){
 				 String isaudit = request.getParameter("isaudit2");//是否审核
				 String remark2 = request.getParameter("remark2");//备注
				 String firsttendertime = request.getParameter("firsttendertime");
 				 if(adminUser.getUsername() != null){
					 activityAwardRule.setAddman(adminUser.getUsername());
				 }
				if(StringUtil.isNotEmpty(isaudit)){
					activityAwardRule.setIsaudit(new Short(isaudit));			 
				 }
				if(StringUtil.isNotEmpty(remark2)){
					activityAwardRule.setRemark(remark2);
				}
				if(StringUtil.isNotEmpty(firsttendertime)){
					activityAwardRule.setFirsttendertime(StringUtil.getDateByString(firsttendertime, "yyyy-MM-dd"));
				}
				if(activityRule.getId() != null){
 					activityAwardRule.setActid(activityRule.getId());
					activityAwardRule.setAddtime(new Date());
					count = 0;
					count = activityAwardRuleService.insertSelective(activityAwardRule);
					if(count > 0){
						hashMap.put("result", "success");
					}else{
						hashMap.put("result", "fail");
					}
				}else{
					hashMap.put("result", "fail");
				}
  			 }else{
				 hashMap.put("result", "fail");
			 }
			 String str = JSON.toJSONString(hashMap);
			 StringUtil.sendJsonData(response, str);
		 }
		 return null;
	 }
	 
	 /**
	  * 
	 * @Title: insert 
	 * @Description: TODO(跳转标的活动设置页面) 
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/insert")
	 public ModelAndView insert(HttpServletRequest request){
			 ModelAndView modelAndView = new ModelAndView();
			 /**查询全部奖品**/
	 		 List<Award> awards = awardService.selectByCondition(null);
 		     /*查询全部会员等级*/
			 List<UserGrade> userGrades =userGradeServiceI.getAll(null);
  			/*查询全部排除名单*/
			List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
			/*查询全部标*/
			List<TenderItem> tenderItems = tenderItemServiceI.selectByCondition(null);
			modelAndView.addObject("userGrades", userGrades);
			modelAndView.addObject("removeNames", removeNames);
			modelAndView.addObject("awardList", awards);
			modelAndView.addObject("tenderItems", tenderItems);
   			modelAndView.setViewName("admin/activityRule/insert");
		    return modelAndView; 
	 }
	 
		 //获取选择的会员等级
		 public String getUgrades(ActivityRule activityRule){
			 if(activityRule != null && activityRule.getUgradesx() != null){
 				 return StringUtil.setPlaceholderForArr(activityRule.getUgradesx(), 30);
 			 }else{
	 			 return null;
			 }
		 }
	 
		 //获取选择的排除名单表
		 public String getRemovenamenos(ActivityRule activityRule){
			 if(activityRule != null && activityRule.getRemovenamenos() != null){
				 String[] removenamenos = activityRule.getRemovenamenos();
				 String params = "";
				 for(String removenameno : removenamenos){
					 params += removenameno +",";
				 }
				 return params;
			 }else{
				 return null;
			 }
		 }
		 
		 /**
		  * 
		 * @Title: findAwardName 
		 * @Description: TODO(根据奖品ID查询奖品名称) 
		 * @param   参数说明 
		 * @return void    返回类型 
		 * @author chenjiaming
		 * @throws
		  */
		 @RequestMapping("/findAwardName")
		 public void findAwardName(Award award,HttpServletResponse response){
	 		 if(award !=null && award.getId() !=null){
				 try {
	   				 List<Award> awards = awardService.selectByCondition(award);
						Award award1 =null;
						if(awards.size()>0){
							award1 = awards.get(0);
						}
					 String data = JSON.toJSONString(award1.getAname());
					 StringUtil.sendJsonData(response, data);
	 			} catch (IOException e) {
	 				e.printStackTrace();
				}
			 }
	 	 }
		 
  }
