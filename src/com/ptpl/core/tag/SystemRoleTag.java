package com.ptpl.core.tag;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ptpl.model.SystemRole;
import com.ptpl.service.SystemRoleServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: SystemRoleTag 
* @Package com.ptpl.core.tag 
* @Description: TODO(角色对应资源自定义标签) 
* @author chenjiaming
* @date 2016年6月29日 下午3:22:56 
* @version V1.0
 */
public class SystemRoleTag extends BodyTagSupport{

	private Integer opid;
	private String var;
 	 
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	
	@Override
	public int doAfterBody() throws JspException {
 		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		opid = null;
		var = null;
 		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
 		ServletContext context = this.pageContext.getServletContext();
		WebApplicationContext ct = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		SystemRoleServiceI systemRoleServiceI = (SystemRoleServiceI) ct.getBean("systemRoleService");
		SystemRole systemRole = systemRoleServiceI.findSystemRoleById(opid);
		if(StringUtil.isEmpty(var))var="role";
		pageContext.setAttribute(var, systemRole);
		return EVAL_BODY_INCLUDE;
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

	

}
