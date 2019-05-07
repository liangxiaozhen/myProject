package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

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
import com.ptpl.model.RoleUser;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.service.RoleUserServiceI;
import com.ptpl.web.util.StringUtil;

import oracle.net.aso.r;

/**
 * 
* @ClassName: RoleUserController 
* @Package com.ptpl.controller 
* @Description: TODO(分配角色模块 控制层) 
* @author chenjiaming
* @date 2016年6月28日 下午2:00:20 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/roleuser")
public class RoleUserController extends BaseController {
	
	 @Autowired
	 private AdminUserServiceI adminUserService;
	 
	 @Autowired
	 private RoleUserServiceI roleUserServiceI;
  	
	 /**
	  * 
	 * @Title: index 
	 * @Description: TODO(进入list列表) 
	 * @param @param request
	 * @param @param adminUser
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	@RequestMapping("/list")
	public ModelAndView index(HttpServletRequest request,AdminUser adminUser){
 		int num = 1;
		 int pageSize = 20;
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
      	 List<AdminUser> adminusers = adminUserService.findAdminUsers(adminUser);
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
     	 modelAndView.setViewName("admin/systemrole/roleuser");
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
      	 modelAndView.setViewName("admin/systemrole/roleusertemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 /**
	  * 
	 * @Title: save 
	 * @Description: TODO(角色分配用户) 
	 * @param @param request
	 * @param @param response
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/save",method=RequestMethod.POST)
	 public String save(HttpServletRequest request,HttpServletResponse response){
		 String params = request.getParameter("params");
		 String roleId = request.getParameter("roleId");
  		 try {
			 HashMap<String, String> maps = new HashMap<String,String>();
			 if(StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(params)){
	 			 String[] string = params.split(",");
	 			 for(String str:string){
  	 				 RoleUser roleUser = new RoleUser();
	 				 roleUser.setRoleId(new BigDecimal(roleId));
	 				 roleUser.setUserId(new BigDecimal(str));
	 				 //先删后分配
	 				 roleUserServiceI.deleteByRoleIdAndUserId(roleUser);
	 				 roleUserServiceI.insertSelective(roleUser);
  	  			 }
	 			maps.put("result", "success");
	  		 }else{
	 			 maps.put("result", "fail");
	 		 }
			 String str2 = JSON.toJSONString(maps);
			 StringUtil.sendJsonData(response, str2);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 return null;
 	 }
	 
	 @RequestMapping(value="/havelist",method ={ RequestMethod.POST,RequestMethod.GET})
	public ModelAndView have(HttpServletRequest request,AdminUser adminUser){
		 String opid = request.getParameter("opid");
		 System.out.println("===========dsfdsfd======"+opid);
		 ModelAndView modelAndView = new ModelAndView();	
		 if(StringUtil.isNotEmpty(opid)){
			 Integer id = Integer.valueOf(opid);
 			 int num = 1;
			 int pageSize = 20;
			 String sort = "id.desc";
			 Order.formString(sort);
			 PageHelper.startPage(num, pageSize);
			 List<AdminUser> adminusers = adminUserService.findUserByRoleId(id);
			 PageInfo<AdminUser> pagehelper = new PageInfo<AdminUser>(adminusers);
			 pagehelper.setFirstPage(1);
			 int lasePageNum = 0;
			 if(pagehelper.getTotal() % pageSize ==0){
				 lasePageNum = (int)pagehelper.getTotal() / pageSize;
			 }else{
				 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
			 }
			 pagehelper.setLastPage(lasePageNum);
			 modelAndView.setViewName("admin/systemrole/findroleuser");
			 modelAndView.addObject("pagehelper", pagehelper);
		 }
 		 return modelAndView;
 	}
	
	/**
	 * 
	* @Title: havetemplate 
	* @Description: TODO(已分配用户 分页查询方法) 
	* @param @param request
	* @param @param adminUser
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/havetemplate",method = RequestMethod.POST)
	public ModelAndView havetemplate(HttpServletRequest request,AdminUser adminUser){
		 String opid = request.getParameter("opid");
 		 ModelAndView modelAndView = new ModelAndView();	
		 if(StringUtil.isNotEmpty(opid)){
			 Integer id = Integer.valueOf(opid);
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
			 String sort = "id.desc";
			 Order.formString(sort);
			 PageHelper.startPage(num, pageSize);
			 List<AdminUser> adminusers = adminUserService.findUserByRoleId(id);
			 PageInfo<AdminUser> pagehelper = new PageInfo<AdminUser>(adminusers);
			 pagehelper.setFirstPage(1);
			 int lasePageNum = 0;
			 if(pagehelper.getTotal() % pageSize ==0){
				 lasePageNum = (int)pagehelper.getTotal() / pageSize;
			 }else{
				 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
			 }
			 pagehelper.setLastPage(lasePageNum);
			 modelAndView.setViewName("admin/systemrole/findroleuser");
			 modelAndView.addObject("pagehelper", pagehelper);
			 modelAndView.addObject("opid",id);
		 }
 		 return modelAndView;
 	}
	/**
	 * 
	* @Title: delete 
	* @Description: TODO(根据角色ID和用户ID删除  已经分配的权限) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,HttpServletResponse response){
		String opid = request.getParameter("opid");
		String roleId = request.getParameter("roleId");
		if(StringUtil.isNotEmpty(roleId) && StringUtil.isNotEmpty(opid)){
			try {
				RoleUser roleUser = new RoleUser();
				roleUser.setRoleId(new BigDecimal(roleId));
				roleUser.setUserId(new BigDecimal(opid));
				int count = 0;
				count = roleUserServiceI.deleteByRoleIdAndUserId(roleUser);
				HashMap<String,String> hashMap = new HashMap<String,String>();
				if(count >0){
					hashMap.put("result","success");
				}else{
					hashMap.put("result","fail");
				}
				String str = JSON.toJSONString(hashMap);
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
		}
 		return null;
	}
}
