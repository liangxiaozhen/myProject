package com.ptpl.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 
* @ClassName: SpringContextHolder 
* @Package com.ptpl.controller 
* @Description: TODO(ApplicationContext 工具类) 
* @author cjm
* @date 2016年10月15日 下午4:25:22 
* @version V1.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
 	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanID){
		return (T) applicationContext.getBean(beanID);
	}
 	
	public static <T> T getBean(Class<T> classType){
 		return applicationContext.getBean(classType);
 		
 	}
}
