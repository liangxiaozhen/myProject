package com.ptpl.controller.manager;

 import java.io.IOException;
import java.math.BigDecimal;
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
import com.ptpl.controller.BaseController;
import com.ptpl.model.SystemResource;
import com.ptpl.service.SystemResourceServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: RoleMenuController 
* @Package com.ptpl.controller 
* @Description: TODO(系统 菜单模块 控制中心) 
* @author chenjiaming
* @date 2016年7月4日 下午2:49:16 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/memu")
public class RoleMenuController extends BaseController{

	@Autowired
	private SystemResourceServiceI systemResourceServiceI;
	
	/**
	 * 
	* @Title: list 
	* @Description: TODO(进入菜单主页面) 
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView();
		List<SystemResource> maps = systemResourceServiceI.findSystemResources2();
		modelAndView.addObject("maps",maps);
		modelAndView.setViewName("admin/menu/list");
		return modelAndView;
	}
	
	/**
	 * 
	* @Title: save 
	* @Description: TODO(菜单添加方法) 
	* @param @param request
	* @param @param response
	* @param @param resource
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request,HttpServletResponse response,SystemResource resource){
  		try {
  			HashMap<String,String> maps = new HashMap<String,String>();
  			if(resource !=null){
   				if(StringUtil.isNotEmpty(resource.getMenuname())){
     				int count = 0;
  				    count = systemResourceServiceI.insertSelective(resource);
  					if(count > 0){
  						maps.put("result", "success");
  					}else{
  						maps.put("result", "fail");
  					}
  				}else{
  					maps.put("result", "resource_null");
  				}
  				String str = JSON.toJSONString(maps);
  				StringUtil.sendJsonData(response, str);
  			}
		} catch (IOException e) {
 			e.printStackTrace();
		}
   			return null;
	}
	
	/**
	 * 
	* @Title: update 
	* @Description: TODO(菜单更新操作) 
	* @param @param request
	* @param @param response
	* @param @param resource
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String update(HttpServletRequest request,HttpServletResponse response,SystemResource resource){
  		try {
  			HashMap<String,String> maps = new HashMap<String,String>();
  			if(resource !=null){
   				if(StringUtil.isNotEmpty(resource.getMenuname())){
     				int count = 0;
  					count = systemResourceServiceI.updateById(resource);
  					if(count > 0){
  						maps.put("result", "update_success");
  					}else{
  						maps.put("result", "update_fail");
  					}
  				}else{
  					maps.put("result", "resource_null");
  				}
  				String str = JSON.toJSONString(maps);
  				StringUtil.sendJsonData(response, str);
  			}
		} catch (IOException e) {
 			e.printStackTrace();
		}
   			return null;
	}
	/**
	 * 
	* @Title: delete 
	* @Description: TODO(菜单删除方法) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public String delete(HttpServletRequest request,HttpServletResponse response){
 		try {
			String opid = request.getParameter("opid");
 			HashMap<String,String> maps = new HashMap<String,String>();
			if(StringUtil.isNotEmpty(opid)){
				int count = 0;
				systemResourceServiceI.deleteByFathernumber(opid);//删除子菜单
				count = systemResourceServiceI.deleteById(new BigDecimal(opid));
				if(count > 0){
					maps.put("result", "success");
				}else{
					maps.put("result", "fail");
				}
			}else{
				maps.put("result", "opid_null");
			}
			String str = JSON.toJSONString(maps);
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
   			return null;
	}
	
	/**
	 * 
	* @Title: template 
	* @Description: TODO(进入添加页面) 
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping(value="/add",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView template(HttpServletRequest request,SystemResource resource){
		ModelAndView modelAndView = new ModelAndView();
		if(resource !=null){
 			modelAndView.addObject("resource",resource);
 		}
 		modelAndView.setViewName("admin/menu/add");
		return modelAndView;
	}
	
	
}
