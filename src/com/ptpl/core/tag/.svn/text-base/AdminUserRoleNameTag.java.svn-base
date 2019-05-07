package com.ptpl.core.tag;

import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ptpl.model.AdminUser;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: SystemRoleTag 
* @Package com.ptpl.core.tag 
* @Description: TODO(用户角色名称自定义标签) 
* @author chenjiaming
* @date 2016年6月29日 下午3:22:56 
* @version V1.0
 */
public class AdminUserRoleNameTag extends BodyTagSupport{

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
		AdminUserServiceI adminUserServiceI = (AdminUserServiceI) ct.getBean("adminUserService");
  		AdminUser adminUser = adminUserServiceI.findRoleByAdminUserId(opid);
		if(StringUtil.isEmpty(var))var="adminuser";
		pageContext.setAttribute(var, adminUser);
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
