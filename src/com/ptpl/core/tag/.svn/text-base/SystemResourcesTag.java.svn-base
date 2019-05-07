package com.ptpl.core.tag;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ptpl.model.SystemResource;
import com.ptpl.service.SystemResourceServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: SystemResourcesTagUserGradeUsernameTag 
* @Package com.ptpl.core.tag 
* @Description: TODO(系统模块 菜单 管理 显示子菜单自定义标签) 
* @author chenjiaming
* @date 2016年6月29日 下午3:22:56 
* @version V1.0
 */
public class SystemResourcesTag extends BodyTagSupport{

	private Integer opid;
	private String var;
	
	Iterator<SystemResource> iterator = null;
 	 
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()){
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_AGAIN;
		}
 			return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		iterator = null;
		opid = null;
		var = null;
 		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
 		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext ct = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		SystemResourceServiceI systemResourceService = (SystemResourceServiceI) ct.getBean("systemResourceService");
		if(StringUtil.isEmpty(var))var="systemResource";
 		List<SystemResource> systemResource = (List<SystemResource>) systemResourceService.findChildrenSystemResources(new BigDecimal(opid.toString()));
 		if(systemResource !=null && systemResource.size() >0){
 			iterator = systemResource.iterator();
 			pageContext.setAttribute(var, iterator.next());
 			return EVAL_BODY_INCLUDE;
  		}else{
  			return SKIP_BODY;
  		}
    }

	@Override
	public void release() {
 		super.release();
	}
 	 
 	public void setOpid(Integer opid) {
		this.opid = opid;
	}
 
	public void setVar(String var) {
		this.var = var;
	}

	public Iterator<SystemResource> getIterator() {
		return iterator;
	}

	public void setIterator(Iterator<SystemResource> iterator) {
		this.iterator = iterator;
	}
 
}
