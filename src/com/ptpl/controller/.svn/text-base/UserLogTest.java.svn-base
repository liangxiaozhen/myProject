package com.ptpl.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.model.UserLog;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.web.util.StringUtil;


@Controller
@RequestMapping("/test")
public class UserLogTest {

	
	
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
     	 modelAndView.setViewName("test/list");
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
     	 modelAndView.setViewName("test/listTemplate");
     	 modelAndView.addObject("pagehelper", pagehelper);
		 return modelAndView;
	 }
	 
	 @RequestMapping("/subittest")
	 public String sumit(HttpServletRequest request,HttpServletResponse response){
		 String opid = request.getParameter("opid");
		 String username = request.getParameter("username");
		 String opercontent = request.getParameter("opercontent");
		 String remark = request.getParameter("remark");
		 
		 
		 Map<String,String> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "opid不能为空");
			 hashMap.put("resultCode", "opid_null");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 			 return null;
		 }
		 
		 if(StringUtil.isEmpty(username)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "用户名不能为空");
			 hashMap.put("resultCode", "username_null");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 			 return null;
		 }
		 
		 if(StringUtil.isEmpty(opercontent)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "操作内容opercontent不能为空");
			 hashMap.put("resultCode", "opercontent_null");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 			 return null;
		 }
		 
		 
		 UserLog userLog = new UserLog();
		 userLog.setId(new BigDecimal(opid));//主键id
		 userLog.setOpercontent(opercontent);//操作内容
		 userLog.setUsername(username);//用户名
		 
		 if(StringUtil.isNotEmpty(remark) && remark.length() < 20){//备注不为空，并且字数不超过20字
			 
			 userLog.setRemark(remark);//备注
		 }
		 int count = 0;
		 count = userLogService.updateById(userLog);
		 
		 if(count > 0){//修改成功
			 hashMap.put("result", "success");
			 hashMap.put("Msg", "修改成功");
			 hashMap.put("resultCode", "success");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 			 return null;
		 }
		 
		 //修改失败
		 hashMap.put("result", "fail");
		 hashMap.put("Msg", "因网络响应不及时，修改失败");
		 hashMap.put("resultCode", "success");
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
				e.printStackTrace();
		}
		 return null;
		  
	 }
}
