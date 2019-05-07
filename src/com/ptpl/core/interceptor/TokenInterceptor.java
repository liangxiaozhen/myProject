package com.ptpl.core.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.ptpl.constant.Session_Constant;
import com.ptpl.core.annotation.Token;
 
public class TokenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if(annotation != null){
				boolean needSaveSession = annotation.save();
				if(needSaveSession){
					String str = UUID.randomUUID().toString();
 					WebUtils.setSessionAttribute(request, Session_Constant.SESSIONTOKEN, str);
				}
				
				boolean needRemoveSession = annotation.remove();
				if(needRemoveSession){
					if(isRepeatSubmit(request)){
						return false;
					}
					request.getSession(false).removeAttribute(Session_Constant.SESSIONTOKEN); 
				}
 			}
			return true;
		}else{
 			return super.preHandle(request, response, handler);
		}
	}
 	
	private boolean isRepeatSubmit(HttpServletRequest request){
 		String serverToken = (String) WebUtils.getSessionAttribute(request, Session_Constant.SESSIONTOKEN);
		if(serverToken == null){
			return true;
		}
		
		String clientToken = request.getParameter("token");
		if(clientToken == null){
			return true;
		}
		
		if(!serverToken.equals(clientToken)){
			return true;
		}
		return false;
 	}
}
