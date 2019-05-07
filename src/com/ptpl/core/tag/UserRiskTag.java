package com.ptpl.core.tag;

import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: UserRiskTag 
* @Package com.ptpl.core.tag 
* @Description: TODO(用户风险类型 tag) 
* @author chenjiaming
* @date 2016年9月6日 下午5:11:39 
* @version V1.0
 */
public class UserRiskTag extends BodyTagSupport{
	
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
 		UserAccountSafeInfoServiceI userAccountSafeInfoServiceI = (UserAccountSafeInfoServiceI) ct.getBean("userAccountSafeInfoService");
 		if(StringUtil.isEmpty(var))var="userrisk";
 		UserAccountSafeInfo UserAccountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(new BigDecimal(opid.toString()));
 		pageContext.setAttribute(var, UserAccountSafeInfo);
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
