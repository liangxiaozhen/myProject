package com.ptpl.core.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.controller.ui.MySessionContext;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.web.util.MyMapSessionId;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: UserInterceptor 
* @Package com.ptpl.core.interceptor 
* @Description: TODO(用户后台拦截器 ) 
* @author chenjiaming
* @date 2016年7月14日 下午5:10:31 
* @version V1.0
 */
public class UserInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
 		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
 		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
 		String requestType = request.getHeader("X-Requested-With");
 		UserBaseAccountInfo  userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		UserAccountSafeInfo  userAccountSafeInfo = (UserAccountSafeInfo) request.getSession().getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
		if(userBaseAccountInfo != null && userAccountSafeInfo != null){
			HttpSession session = request.getSession();
			String Id = session.getId();//获取当前会话sessionId
 			String loginname = userBaseAccountInfo.getLoginname();
			MyMapSessionId m = MyMapSessionId.getInstance();
 			String sessionId = m.getSessionId(loginname);//获取保存的sessionId
 			if (sessionId!=null && sessionId.equals(Id)) {
				return true;
			} else {
				session.invalidate();
				String str="yideng";
				response.sendRedirect(request.getContextPath() + "/user/tologin.action?ab="+str);
			}
			return false;
		}else{
			//检查是否是ajax请求
			if(StringUtil.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")){
				response.getWriter().print("logout");
			}else{
				response.sendRedirect(request.getContextPath()+"/user/tologin.action");
			}
 			return false;
		}
		 
		 
	}
 
}
