package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.alibaba.fastjson.JSON;
import com.ptpl.model.RoleResource;
import com.ptpl.service.RoleResourceServiceI;
import com.ptpl.service.SystemResourceServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: SystemResourceController 
* @Package com.ptpl.controller 
* @Description: TODO(角色资源权限 控制器 加载角色菜单，增改查) 
* @author chenjiaming
* @date 2016年6月30日 上午11:33:16 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/systemResource")
public class SystemResourceController {
	
	
    @Autowired
	private SystemResourceServiceI systemResourceService;
    
    @Autowired
	private RoleResourceServiceI roleResourceServiceI;
    /**
     * 
    * @Title: test 
    * @Description: TODO(加载权限tree菜单) 
    * @param @param response
    * @param @return  参数说明 
    * @return HashMap<String,Object>    返回类型 
    * @author chenjiaming
    * @throws
     */
    @RequestMapping(value="/test",method={RequestMethod.POST,RequestMethod.GET})
    public HashMap<String,Object> test(HttpServletResponse response){
     	try {
	    	HashMap<String,Object>	maps = systemResourceService.findSystemResources();
  	     	String str = JSON.toJSONString(maps);
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 
    * @Title: findResources 
    * @Description: TODO(根据角色ID获取已经赋权了的角色资源ID) 
    * @param @param response
    * @param @param request
    * @param @return  参数说明 
    * @return String    返回类型 
    * @author chenjiaming
    * @throws
     */
    @RequestMapping(value="/findResourceId",method=RequestMethod.POST)
    public String findResources(HttpServletResponse response,HttpServletRequest request){
    	try {
	    	String roleId = request.getParameter("roleId");
 	    	RoleResource resource = roleResourceServiceI.findResourcesByRoleId(new BigDecimal(roleId));
 	    	HashMap<String,String> map = new HashMap<String,String>();
	    	map.put("result", resource.getResourceIdStr());
	    	String str = JSON.toJSONString(map);
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 
    * @Title: save 
    * @Description: TODO(保存角色授权资源) 
    * @param @param response
    * @param @param request
    * @param @return  参数说明 
    * @return String    返回类型 
    * @author chenjiaming
    * @throws
     */
    @RequestMapping("/save")
    public String save(HttpServletResponse response,HttpServletRequest request){
    	HashMap<String,String> map = new HashMap<String,String>();
    	try {
    		//拿到选中的角色资源ID
	    	String opids = request.getParameter("opids");
	    	//拿到操作的角色ID
	    	String role = request.getParameter("roleid");
  
	    	if(StringUtil.isNotEmpty(opids) && StringUtil.isNotEmpty(role)){
	    		BigDecimal roleId = new BigDecimal(role); 
 	    		//根据角色ID删除角色资源
	    		roleResourceServiceI.deleteByRoleId(roleId);
 	    		String[] strs = opids.split(",");
	    		//重新分配
	    		for(String str :strs){
	    			RoleResource roleResource = new RoleResource();
	    			roleResource.setResourceId(new BigDecimal(str));
	    			roleResource.setRoleId(roleId);
  	    			roleResourceServiceI.insertSelective(roleResource);
	    		}
	    		map.put("result", "success");
	     	}else{
	    		map.put("result", "fail");
	     	}
    		String str2 = JSON.toJSONString(map);
			StringUtil.sendJsonData(response, str2);
		} catch (IOException e) {
 			e.printStackTrace();
		}
    	return null;
    }
    
    //跳转页面
    @RequestMapping("/tree")
    public String tree(){
    	return "admin/systemrole/tree";
    }
}
