package com.ptpl.controller.manager;
 

import java.io.IOException;
import java.math.BigDecimal;
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
import com.ptpl.model.Award;
import com.ptpl.model.UserGrade;
import com.ptpl.model.WCCAwardRule;
import com.ptpl.model.WithdrawsCashCoupon;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.WCCAwardRuleServiceI;
import com.ptpl.service.WithdrawsCashCouponServiceI;
import com.ptpl.web.util.StringUtil;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
/**
 * 
* @ClassName: WCCAwardRuleController 
* @Package com.ptpl.controller 
* @Description: TODO(提抵卷活动奖励规则设置 控制层 ) 
* @author chenjiaming
* @date 2016年07月12日 10:16:47
* @version V1.0
 */
@Controller
@RequestMapping("/admin/wCCAwardRule")
public class WCCAwardRuleController extends BaseController{
	
	/**
	  * 奖品Service
	  */
	 @Autowired
	 private AwardServiceI awardService;
	 
	 /**
	  *会员等级Service
	  */
 	 @Autowired
	 private UserGradeServiceI userGradeServiceI; 
 	/**
 	 * 提抵卷活动规则Service
 	 */
 	 @Autowired
 	 private WithdrawsCashCouponServiceI withdrawsCashCouponService;
 	 
	/**
	 * 提低卷奖励规则 service
	 */
	 @Autowired
	 private WCCAwardRuleServiceI wCCAwardRuleService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(提抵卷活动奖励规则设置查询通用方法) 
	 * @param @param wCCAwardRule
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(WCCAwardRule wCCAwardRule){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<WCCAwardRule> wCCAwardRules = wCCAwardRuleService.findWCCAwardRules(wCCAwardRule);
       	 PageInfo<WCCAwardRule> pagehelper = new PageInfo<WCCAwardRule>(wCCAwardRules);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/wCCAwardRule/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(提抵卷活动奖励规则设置模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param wCCAwardRule
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,WCCAwardRule wCCAwardRule){
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
       	 List<WCCAwardRule> wCCAwardRules = wCCAwardRuleService.findWCCAwardRules(wCCAwardRule);
       	  
      	 PageInfo<WCCAwardRule> pagehelper = new PageInfo<WCCAwardRule>(wCCAwardRules);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/wCCAwardRule/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
	  * 
	 * @Title: update 
	 * @Description: TODO(提抵卷活动奖励规则设置更新) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value = "/update",method = RequestMethod.POST)
	 public String update(WCCAwardRule wCCAwardRule,HttpServletResponse response){
    		 if(wCCAwardRule !=null ){
			 try {
				//获取选中的会员等级 方式 1为全部会员等级 2为部分会员等级
				 String ugrade= wCCAwardRule.getUgrade(); 
				 //获取选中的部分会员等级
				 String[] ugrade2 = wCCAwardRule.getUgradesx();
	 			 if(ugrade.equals("1")){
					 //生成长度未30位0的字符串0000000000000000000000000000000000
				 	ugrade = StringUtil.getPlaceholder(30);
	  				 //查询全部会员等级
					 List<UserGrade> userGrades2 = userGradeServiceI.getAll(null);
					 for(UserGrade userGrade3: userGrades2){
						 ugrade =StringUtil.setPlaceholder(ugrade, userGrade3.getUgrade().intValue()-1);	
					 }
				 }else if(ugrade.equals("2")){
 					 //选择的部分会员等级
					 ugrade = StringUtil.setPlaceholderForArr(ugrade2, 30);
				 }
	 			 //赋值会员等级编号
	 			wCCAwardRule.setUgrade(ugrade);
				 //选中 奖励方式 1 为定额奖励方式 2 为投资金额百分比
				 Short awardtype =wCCAwardRule.getAwardtype();
				//定额奖励方式
				 if(awardtype ==1){ 
					 //清空百分比数
					 wCCAwardRule.setAwardratio(0.00);
					//清空奖励最低值
					 wCCAwardRule.setAwardmin(0.00);
					//清空奖励最高值
					 wCCAwardRule.setAwardmax(0.00);
					//投资金额百分比
		 		 }else if(awardtype ==2){
		 			wCCAwardRule.setQuota(0.00);
				 }
				 int count = 0;
				 count = wCCAwardRuleService.update(wCCAwardRule);
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
	 * @Description: TODO(查看提抵卷活动奖励规则设置 详情信息) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/detail")
	 public ModelAndView detail(HttpServletRequest request){
 		 String opid = request.getParameter("opid");
 		 String actid = request.getParameter("actid");
 		 if(StringUtil.isNotEmpty(opid)){
 			 ModelAndView modelAndView = new ModelAndView();
  		     WCCAwardRule  wCCAwardRule = wCCAwardRuleService.selectByPrimaryKey(new BigDecimal(opid));
  		     if(StringUtil.isNotEmpty(actid)){
  		    	 WithdrawsCashCoupon withdrawsCashCoupon = withdrawsCashCouponService.selectByPrimaryKey(new BigDecimal(actid));
  	   		     modelAndView.addObject("coupon", withdrawsCashCoupon);
   		     }
   		     modelAndView.addObject("wCCAwardRule", wCCAwardRule);
  		     modelAndView.setViewName("admin/wCCAwardRule/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
	 
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(提抵卷活动奖励规则设置 跳转编辑页面) 
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
 			/**
 			 * 查询全部奖品
 			 */
  	 		 List<Award> awards = awardService.selectByCondition(null);
  	 		/*查询全部会员等级*/
  			 List<UserGrade> userGrades =userGradeServiceI.getAll(null);
  		     WCCAwardRule  wCCAwardRule = wCCAwardRuleService.selectByPrimaryKey(new BigDecimal(opid));
  		     String params = "";
  		     if(wCCAwardRule !=null){
  		    	 String ugrade = wCCAwardRule.getUgrade();
  		    	 List<Integer> lists = StringUtil.pars(ugrade);
  		    	 for(Integer lit:lists){
  		    		params +=lit+",";
  		    	 }
   		     }
    		 modelAndView.addObject("wCCAwardRule", wCCAwardRule);
   		     modelAndView.addObject("awardList", awards);
   		     modelAndView.addObject("userGrades", userGrades);
   		     modelAndView.addObject("params", params);
    		     modelAndView.setViewName("admin/wCCAwardRule/edit");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
	 
	 /**
	  * 
	 * @Title: findAname 
	 * @Description: TODO(根据奖品ID查询奖品名称) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/findAname")
	 public void findAname(Award award,HttpServletResponse response){
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
