package com.ptpl.core.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.controller.ui.MySessionContext;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.web.util.StringUtil;

public class UserAppInterceptor  implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
 		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
 		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
 		String sessionId = request.getParameter("sessionId");
		if(StringUtil.isEmpty(sessionId)){
			 Map<String,String> hashMap = new HashMap<>();
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			 hashMap.put(AppSession_Constant.RESULTCODE, "sessionId_null");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示：sessionId 找不到！");
			 String str = JSON.toJSONString(hashMap);
			 StringUtil.sendJsonData(response, str);
	 			 return false;
		}
		
		HttpSession session = MySessionContext.getSession(sessionId);
		if(session == null){
			 Map<String,String> hashMap = new HashMap<>();
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.LOGOUT);
			 hashMap.put(AppSession_Constant.RESULTCODE, "logout");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示：因您操作超时！请重新登录！");
			 String str = JSON.toJSONString(hashMap);
			 StringUtil.sendJsonData(response, str);
	 		 return false;
		}
		
		UserBaseAccountInfo  userBaseAccountInfo = (UserBaseAccountInfo) session.getAttribute(AppSession_Constant.APPUSER);
		UserAccountSafeInfo  userAccountSafeInfo = (UserAccountSafeInfo) session.getAttribute(AppSession_Constant.APPUSERACCOUNTSAFEINFO);
		if(userBaseAccountInfo != null && userAccountSafeInfo != null){
			return true;
		}else{
			 Map<String,String> hashMap = new HashMap<>();
			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.LOGOUT);
			 hashMap.put(AppSession_Constant.RESULTCODE, "logout");
			 hashMap.put(AppSession_Constant.MESSAGE, "提示：因您操作超时！请重新登录！");
			 String str = JSON.toJSONString(hashMap);
			 StringUtil.sendJsonData(response, str);
	 		 return false;
		}
	}

}
