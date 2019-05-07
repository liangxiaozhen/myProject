package com.ptpl.controller.manager;
 
import java.math.BigDecimal;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.ptpl.model.Award;
import com.ptpl.service.ActivityAwardRuleServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: ActivityAwardRuleController 
* @Package com.ptpl.controller 
* @Description: TODO(标的活动奖励规则 控制层 ) 
* @author chenjiaming
* @date 2016年08月26日 16:14:51
* @version V1.0
 */
@Controller
@RequestMapping("/admin/activityAwardRule")
public class ActivityAwardRuleController extends BaseController{
	
	 @Autowired
	 private ActivityAwardRuleServiceI activityAwardRuleService;
	 /**
	  * 奖品Service
	  */
	 @Autowired
	 private AwardServiceI awardService;
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(标的活动奖励规则查询通用方法) 
	 * @param @param activityAwardRule
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(ActivityAwardRule activityAwardRule){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<ActivityAwardRule> activityAwardRules = activityAwardRuleService.findActivityAwardRules(activityAwardRule);
       	 
      	 PageInfo<ActivityAwardRule> pagehelper = new PageInfo<ActivityAwardRule>(activityAwardRules);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/activityAwardRule/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(标的活动奖励规则模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param activityAwardRule
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,ActivityAwardRule activityAwardRule){
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
       	 List<ActivityAwardRule> activityAwardRules = activityAwardRuleService.findActivityAwardRules(activityAwardRule);
       	  
      	 PageInfo<ActivityAwardRule> pagehelper = new PageInfo<ActivityAwardRule>(activityAwardRules);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/activityAwardRule/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
	  * 
	 * @Title: update 
	 * @Description: TODO(标的活动奖励规则更新) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value = "/update",method = RequestMethod.POST)
	 public String update(ActivityAwardRule activityAwardRule,HttpServletResponse response){
  		 if(activityAwardRule !=null && activityAwardRule.getId() != null){
			 try {
				 String firsttendertime = request.getParameter("firsttendertime");
				 if(StringUtil.isNotEmpty(firsttendertime)){
					activityAwardRule.setFirsttendertime(StringUtil.getDateByString(firsttendertime, "yyyy-MM-dd"));
				 }
				 int count = 0;
				 count = activityAwardRuleService.updateById(activityAwardRule);
				 Map<String,String> map = new HashMap<String,String>();
				 if(count >0){
					 map.put("result", "success");
				 }else{
					 map.put("result", "fail");
				 }
				 String str = JSON.toJSONString(map);
				 StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
  				e.printStackTrace();
			}
		 }
 		 return null;
	 }
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看标的活动奖励规则详情信息) 
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
  		     ActivityAwardRule  activityAwardRule = activityAwardRuleService.findActivityAwardRuleById(new BigDecimal(opid));
   		     modelAndView.addObject("activityAwardRule", activityAwardRule);
  		     modelAndView.setViewName("admin/activityAwardRule/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	 
   	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(标的活动奖励规则 跳转编辑页面) 
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
  		     ActivityAwardRule  activityAwardRule =activityAwardRuleService.findActivityAwardRuleById(new BigDecimal(opid));
  		     /**查询全部奖品**/
	 		 List<Award> awards = awardService.selectByCondition(null);
  		     modelAndView.addObject("activityAwardRule", activityAwardRule);
			 modelAndView.addObject("awardList", awards);
   		     modelAndView.setViewName("admin/activityAwardRule/edit");
 			 return modelAndView; 
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
