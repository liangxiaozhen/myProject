package com.ptpl.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
* @ClassName: Token 
* @Description: TODO(token 令牌注解) 
* @author cjm 
* @date 2017年6月14日 下午7:43:03 
*
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

	boolean save() default false;
	
	boolean remove() default false;
}
