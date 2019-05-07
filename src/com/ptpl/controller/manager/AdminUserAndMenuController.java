package com.ptpl.controller.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleResource;
import com.ptpl.model.RoleUser;
import com.ptpl.service.RoleResourceServiceI;
import com.ptpl.service.RoleUserServiceI;
import com.ptpl.service.SystemResourceServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: AdminUserAndMenuController 
* @Package com.ptpl.controller 
* @Description: TODO(系统管理 系统用户可见菜单 控制中心) 
* @author chenjiaming
* @date 2016年7月6日 上午11:27:55 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/usermenu")
public class AdminUserAndMenuController {
  
 	 @Autowired
	 private SystemResourceServiceI systemResourceServiceI;
 	 /**
 	  * 
 	 * @Title: usermenu 
 	 * @Description: TODO(显示用户可见的菜单 可以使用的功能) 
 	 * @param @param response
 	 * @param @param request
 	 * @param @return  参数说明 
 	 * @return HashMap<String,Object>    返回类型 
 	 * @author chenjiaming
 	 * @throws
 	  */
	  @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	    public HashMap<String,Object> usermenu(HttpServletResponse response,HttpServletRequest request){
		  AdminUser  adminUser = (AdminUser)request.getSession().getAttribute(Session_Constant.ADMINUSER);
		  if(adminUser != null && adminUser.getId() != null){
    		try {
				  List<HashMap<String, Object>> maps  = systemResourceServiceI.findIndexSystemResources(request,adminUser);
				  String str = JSON.toJSONString(maps);
  				  StringUtil.sendJsonData(response, str);
 			  }catch (IOException e) {
 			  }
			  return null;
 		  }else{
	    	  return null;
 		  }
	    }
}
