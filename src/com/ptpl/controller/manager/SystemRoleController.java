package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.RoleUser;
import com.ptpl.model.SystemRole;
import com.ptpl.service.RoleResourceServiceI;
import com.ptpl.service.RoleUserServiceI;
import com.ptpl.service.SystemRoleServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: SystemRoleController 
* @Package com.ptpl.controller 
* @Description: TODO(系统管理模块  systemrole角色控制器) 
* @author chenjiaming
* @date 2016年6月28日 下午5:22:31 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/systemrole")
public class SystemRoleController extends BaseController{

	@Autowired
	private SystemRoleServiceI systemRoleMapper;
	
	 @Autowired
	private RoleResourceServiceI roleResourceServiceI;
	 
	 @Autowired
	 private RoleUserServiceI roleUserServiceI;
	
	/**
	 * 
	* @Title: list 
	* @Description: TODO(查询全部用户) 
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/list")
	public ModelAndView list(){
 		int num = 1;
		int pageSize = 20;
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, pageSize);
		ModelAndView modelAndView =new ModelAndView();
		List<SystemRole> SystemRoles = systemRoleMapper.findSystemRoles();
		PageInfo<SystemRole> pagehelper = new PageInfo<SystemRole>(SystemRoles);
     	 pagehelper.setFirstPage(1);
     	 int lasePageNum = 0;
     	 if(pagehelper.getTotal() % pageSize ==0){
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
     	 }else{
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize +1 ;
     	 }
     	 pagehelper.setLastPage(lasePageNum);
		modelAndView.addObject("pagehelper",pagehelper);
		modelAndView.setViewName("admin/systemrole/list");
		return modelAndView;
	}
	
	/**
	 * 
	* @Title: template 
	* @Description: TODO(分页模板) 
	* @param @param request
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/template")
	 public ModelAndView template(HttpServletRequest request){
		 String pageS = request.getParameter("pageSize");
		 String pageNo = request.getParameter("pageNo");
   		 int num = 1;
		 int pageSize =20;
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
      	 List<SystemRole> adminusers = systemRoleMapper.findSystemRoles();
      	 
     	 PageInfo<SystemRole> pagehelper = new PageInfo<SystemRole>(adminusers);
     	 pagehelper.setFirstPage(1);
     	 int lasePageNum = 0;
     	 if(pagehelper.getTotal() % pageSize ==0){
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
     	 }else{
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
     	 }
     	 pagehelper.setLastPage(lasePageNum);
     	 
     	 ModelAndView modelAndView = new ModelAndView();	
     	 modelAndView.setViewName("admin/systemrole/template");
     	 modelAndView.addObject("pagehelper", pagehelper);
		 return modelAndView;
	 }
	
	@RequestMapping("/tree")
	public String tree(){
		return "admin/systemrole/tree";
	}
	/**
	 * 
	* @Title: save 
	* @Description: TODO(角色增加方法) 
	* @param @param response
	* @param @param systemRole
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(HttpServletResponse response, SystemRole systemRole){
 		if(systemRole.getRolename() !=null){
			Map<String,String> hashMap = new HashMap<String,String>();
			try {
				int count = 0;
				count = systemRoleMapper.insertSelective(systemRole);
				if(count > 0){
					hashMap.put("result", "success");
				}else{
					hashMap.put("result", "fail");
				}
				String str = JSON.toJSONString(hashMap);
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
		}
		return null;
	}
  	
	/**
	 * 
	* @Title: update 
	* @Description: TODO(角色更改方法) 
	* @param @param response
	* @param @param systemRole
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(HttpServletResponse response, SystemRole systemRole){
  		if(systemRole.getRolename() !=null){
			Map<String,String> hashMap = new HashMap<String,String>();
			try {
				int count = 0;
 				count = systemRoleMapper.updateById(systemRole);
				if(count > 0){
					hashMap.put("result", "success");
				}else{
					hashMap.put("result", "fail");
				}
				String str = JSON.toJSONString(hashMap);
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 
	* @Title: delete 
	* @Description: TODO(角色删除方法) 
	* @param @param response
	* @param @param request
	* @param @param systemRole
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(HttpServletResponse response,HttpServletRequest request,SystemRole systemRole){
    	Map<String,String> hashMap = new HashMap<String,String>();
 		if(systemRole.getId() !=null){
			try {
 				int count = 0;
 				RoleUser roleUser = new RoleUser();
 				roleUser.setRoleId(systemRole.getId());
 				roleUserServiceI.deleteByRoleId(roleUser);
 				//删除角色资源
				roleResourceServiceI.deleteByRoleId(systemRole.getId());
 				count = systemRoleMapper.deleteById(systemRole);
				if(count > 0){
					hashMap.put("result", "success");
	 			}else{
	 				hashMap.put("result", "fail");
				}
				String str = JSON.toJSONString(hashMap);
				StringUtil.sendJsonData(response, str);
				return null;
			} catch (IOException e) {
 				e.printStackTrace();
			}
		} 
 		return null;
   	}
	
	/**
	 * 
	* @Title: add 
	* @Description: TODO(添加增加操作页面) 
	* @param @param request
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request){
		String opid = request.getParameter("opid");
		String rolename = request.getParameter("rolename");
 		ModelAndView modelAndView = new ModelAndView();
		if(StringUtil.isNotEmpty(opid) && StringUtil.isNotEmpty(rolename)){
			SystemRole systemRole =new SystemRole();
 			systemRole.setRolename(rolename);
 			systemRole.setId(new BigDecimal(Integer.parseInt(opid)));
			modelAndView.addObject("systemRole",systemRole);
		}
		modelAndView.setViewName("admin/systemrole/add");
 		return modelAndView;
	}
}
