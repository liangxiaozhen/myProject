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
import com.ptpl.model.UserLog;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: UserLogController 
* @Package com.ptpl.controller 
* @Description: TODO(用户日志表 控制层 ) 
* @author chenjiaming
* @date 2017年06月09日 18:42:57
* @version V1.0
 */
@Controller
@RequestMapping("/admin/userLog")
public class UserLogController extends BaseController{
	
	 @Autowired
	 private UserLogServiceI userLogService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(用户日志表查询通用方法) 
	 * @param @param userLog
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(UserLog userLog){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<UserLog> userLogs = userLogService.findUserLogs(userLog);
       	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       	 for(UserLog log : userLogs){
       		 if(log.getLogintime() != null){
       			log.setLogintimestr(sdf.format(log.getLogintime()));
       		 }
       		 
       		//防止程序报空指针异常
       		if(log.getOpertime() !=null){
       			log.setOpertimestr(sdf.format(log.getOpertime()));
       		}
       	 }
      	 PageInfo<UserLog> pagehelper = new PageInfo<UserLog>(userLogs);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/userLog/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(用户日志表模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param userLog
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,UserLog userLog){
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
       	 List<UserLog> userLogs = userLogService.findUserLogs(userLog);
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      	 for(UserLog log : userLogs){
      		 if(log.getLogintime() != null){
      			log.setLogintimestr(sdf.format(log.getLogintime()));
      		 }
      		 
      		//防止程序报空指针异常
      		if(log.getOpertime() !=null){
      			log.setOpertimestr(sdf.format(log.getOpertime()));
      		}
      	 }
      	 PageInfo<UserLog> pagehelper = new PageInfo<UserLog>(userLogs);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/userLog/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	  
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看用户日志表详情信息) 
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
  		     UserLog  userLog = userLogService.findUserLogById(new BigDecimal(opid));
  	     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  		     if(userLog.getLogintime() != null){
  		    	userLog.setLogintimestr(sdf.format(userLog.getLogintime()));
      		 }
      		 
      		//防止程序报空指针异常
      		if(userLog.getOpertime() !=null){
      			userLog.setOpertimestr(sdf.format(userLog.getOpertime()));
      		}
   		     modelAndView.addObject("userLog", userLog);
  		     modelAndView.setViewName("admin/userLog/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	 
  
 }
