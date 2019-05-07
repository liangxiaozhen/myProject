package com.ptpl.core.tag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ptpl.model.SystemResource;
import com.ptpl.model.UserGrade;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: UserGradeUserNameTag 
* @Package com.ptpl.core.tag 
* @Description: TODO(获取会员等级名称 自定义标签) 
* @author chenjiaming
* @date 2016年7月11日 下午3:22:56 
* @version V1.0
 */
public class UserGradeUserNameTag extends BodyTagSupport{

	private String opid;
	private String var;
 	
	Iterator<UserGrade> iterator = null;
 	 
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
		UserGradeServiceI userGradeService = (UserGradeServiceI) ct.getBean("userGradeService");
		if(StringUtil.isEmpty(var))var="userGrade";
		List<Integer> lists = StringUtil.pars(opid);
		List<UserGrade> userGrades = new ArrayList<UserGrade>();
  		for(Integer list:lists){
			UserGrade userGrade = new UserGrade();
			String lit = list.toString();
			if(lit !=null){
				userGrade = userGradeService.selectByUgrade(new BigDecimal(lit));
				if(userGrade !=null){
   					userGrades.add(userGrade);
 				}
 			}
		}
  		if(userGrades !=null && userGrades.size() >0){
 			iterator = userGrades.iterator();
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
 	 
 	public void setOpid(String opid) {
		this.opid = opid;
	}
 
	public void setVar(String var) {
		this.var = var;
	}

	public Iterator<UserGrade> getIterator() {
		return iterator;
	}

	public void setIterator(Iterator<UserGrade> iterator) {
		this.iterator = iterator;
	}
 
}
