package com.ptpl.controller;
 
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
import com.ptpl.model.RepayMent;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: UserRepayMentController 
* @Package com.ptpl.controller 
* @Description: TODO(用户还款记录 控制层 ) 
* @author chenjiaming
* @date 2016年09月12日 10:40:35
* @version V1.0
 */
@Controller
@RequestMapping("/user/repayMent")
public class UserRepayMentController extends BaseController{
	
	 @Autowired
	 private RepayMentServiceI repayMentService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(用户还款记录查询通用方法) 
	 * @param @param repayMent
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(){
		 UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		 ModelAndView modelAndView = new ModelAndView();	
		 if(baseAccountInfo == null){
			 modelAndView.setViewName("user/login");
		 }else{
 			 int num = 1;
			 int pageSize = 20;
			 String sort = "id.desc";
			 Order.formString(sort);
			 PageHelper.startPage(num, pageSize);
			 RepayMent repayMent = new RepayMent();
			 repayMent.setOutaccountid(baseAccountInfo.getId());
			 List<RepayMent> repayMents = repayMentService.findRepayMentsByBaseId(repayMent);
			 
			 PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
			 pagehelper.setFirstPage(1);
			 int lasePageNum = 0;
			 if(pagehelper.getTotal() % pageSize ==0){
				 lasePageNum = (int)pagehelper.getTotal() / pageSize;
			 }else{
				 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
			 }
			 pagehelper.setLastPage(lasePageNum);
			 
			 modelAndView.setViewName("user/userRepayMent/list");
			 modelAndView.addObject("pagehelper", pagehelper);
		 }
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(还款记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param repayMent
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping(value="/template" ,method=RequestMethod.POST)
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,RepayMent repayMent){
		 UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		 ModelAndView modelAndView = new ModelAndView();	
		 if(baseAccountInfo == null){
			 modelAndView.setViewName("user/login");
		 }else{
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
 			 repayMent.setOutaccountid(baseAccountInfo.getId());
			 List<RepayMent> repayMents = repayMentService.findRepayMentsByBaseId(repayMent);
	       	  
	      	 PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
	      	 pagehelper.setFirstPage(1);
	      	 int lasePageNum = 0;
	      	 if(pagehelper.getTotal() % pageSize ==0){
	      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
	      	 }else{
	      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
	      	 }
	      	 pagehelper.setLastPage(lasePageNum);
	      	 
 	      	 modelAndView.setViewName("user/userRepayMent/listTemplate");
	      	 modelAndView.addObject("pagehelper", pagehelper);
		 }
 		 return modelAndView;
 	 }
	 
	 /**
	  * 
	 * @Title: update 
	 * @Description: TODO(还款记录更新) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value = "/update",method = RequestMethod.POST)
	 public String update(RepayMent repayMent,HttpServletResponse response){
  		 if(repayMent !=null){
			 try {
				 int count = 0;
				 count = repayMentService.updateById(repayMent);
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
	 * @Description: TODO(查看还款记录详情信息) 
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
  		     RepayMent  repayMent = repayMentService.findRepayMentById(new BigDecimal(opid));
   		     modelAndView.addObject("repayMent", repayMent);
  		     modelAndView.setViewName("admin/repayMent/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	 
   	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(还款记录 跳转编辑页面) 
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
  		     RepayMent  repayMent =repayMentService.findRepayMentById(new BigDecimal(opid));
   		     modelAndView.addObject("repayMent", repayMent);
  		     modelAndView.setViewName("admin/repayMent/edit");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
 }
