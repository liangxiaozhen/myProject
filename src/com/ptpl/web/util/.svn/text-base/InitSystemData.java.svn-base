package com.ptpl.web.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.huishang.test.TransactionDemoEncrypted;
import com.ptpl.model.DataDesc;
import com.ptpl.service.DataDescServiceI;

public class InitSystemData extends HttpServlet{
	public static Log logger = LogFactory.getLog(InitSystemData.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InitSystemData() {
		super();
	}
	//web服务启动时,最先初始化的servlet
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		DataDescServiceI dataDescService = (DataDescServiceI)ac.getBean("dataDescService");
		List<DataDesc> pdataDesc =  dataDescService.getDataDesc("professionType");
		String jsonStr = JSON.toJSONString(pdataDesc);
		sc.setAttribute("sysData", jsonStr);
		logger.info("系统启动马上加载---------------"+jsonStr);
	}
}
