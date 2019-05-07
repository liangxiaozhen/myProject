package com.ptpl.controller.manager;
 

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
import com.ptpl.model.AdminUser;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: AdminUserController 
* @Package com.ptpl.controller 
* @Description: TODO(系统管理员模块 控制层 ) 
* @author chenjiaming
* @date 2016年6月27日 上午10:50:37 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/adminuser")
public class AdminUserController extends BaseController{
	
	 @Autowired
	 private AdminUserServiceI adminUserService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(查询系统用户方法) 
	 * @param @param adminUser
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(AdminUser adminUser){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<AdminUser> adminusers = adminUserService.findAdminUsers(adminUser);
       	 for(AdminUser user:adminusers){
	       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       		//防止程序报空指针异常
	       		if(user.getLogintime() !=null){
	       			user.setLogintimestr(sdf.format(user.getLogintime()));
 	       		}
	       		//防止程序报空指针异常
	       		if(user.getRegdate() !=null){
 	       			user.setRegdatestr(sdf.format(user.getRegdate()));
	       		}
       	 }
      	 PageInfo<AdminUser> pagehelper = new PageInfo<AdminUser>(adminusers);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/adminuser/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param adminUser
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,AdminUser adminUser){
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
       	 List<AdminUser> adminusers = adminUserService.findAdminUsers(adminUser);
       	 for(AdminUser user:adminusers){
	       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       		//防止程序报空指针异常
	       		if(user.getLogintime() !=null){
	       			user.setLogintimestr(sdf.format(user.getLogintime()));
 	       		}
	       		//防止程序报空指针异常
	       		if(user.getRegdate() !=null){
 	       			user.setRegdatestr(sdf.format(user.getRegdate()));
	       		}
       	 }
      	 PageInfo<AdminUser> pagehelper = new PageInfo<AdminUser>(adminusers);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/adminuser/template");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
	  * 
	 * @Title: update 
	 * @Description: TODO(更改系统管理员是否禁止登录状态操作) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value = "/update",method = RequestMethod.POST)
	 public String update(AdminUser adminUser,HttpServletResponse response){
  		 if(adminUser !=null){
			 try {
				 int count = 0;
				 count = adminUserService.updateAdminUser(adminUser);
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
 }
