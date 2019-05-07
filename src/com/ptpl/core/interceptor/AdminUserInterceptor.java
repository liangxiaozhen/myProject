package com.ptpl.core.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.web.util.StringUtil;
/**
 * adminUser 拦截器
 * 管理员后台拦截器
 * 
 */

public class AdminUserInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception arg3)
			throws Exception {
 	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView arg3)
			throws Exception {
 	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
 		//查询是否登录
		AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
 		if(adminUser != null){
			return true;
		}else{
 			String requestType = request.getHeader("X-Requested-With");
  			//检查是否是ajax请求
 			if(StringUtil.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
				response.getWriter().print("logout");
			}else{
				response.sendRedirect(StringUtil.getBasePath(request)+"/admin/login.action");
 			}
 			return false;
		}
 	}

}
