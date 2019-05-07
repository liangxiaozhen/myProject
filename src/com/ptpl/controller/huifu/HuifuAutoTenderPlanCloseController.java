package com.ptpl.controller.huifu;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserAutoTender;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserAutoTenderServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 汇付天下接口-自动投标计划关闭
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/huifu/autoTenderPlanClose")
public class HuifuAutoTenderPlanCloseController extends BaseController {	
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/** 自动投标计划Service */
	@Autowired
	UserAutoTenderServiceI userAutoTenderService;
	
	/**
	 * 
	* @Title: test 
	* @Description: TODO(汇付测试 无用) 
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author zhenglm
	* @throws
	 */
	@RequestMapping("/test")
	public String test(){
		return "huifuhuizong/AutoTenderPlan";
	}

	@RequestMapping("/hfAutoTenderPlanClose")
	public void hfAutoTenderPlan() throws Exception{
		HuifuParams huifuparam = new HuifuParams();
		huifuparam.setCmdId("AutoTenderPlanClose"); // 消息类型
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		if(userFsAccountInfo != null){
			huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid()); // 获取对应用户托管账户信息  用户客户号
		}
		huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid()); // 用户客户号
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuparam.setRetUrl(basePath+"/huifu/autoTenderPlanClose/autoTenderPlanCloseReturl.action");
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))					// 版本号
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))					// 消息类型
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))				// 商户客户号
				.append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))				// 用户客户号
				.append(StringUtils.trimToEmpty(huifuparam.getRetUrl()));				// 页面返回URL
		String plainStr = buffer.toString();
		System.out.println("组装绑卡加签字符串原文:========="+plainStr);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			request.setAttribute("Version", huifuparam.getVersion());
			request.setAttribute("CmdId", huifuparam.getCmdId());
			request.setAttribute("MerCustId", huifuparam.getMerCustId());
			request.setAttribute("UsrCustId", huifuparam.getUsrCustId());
			request.setAttribute("RetUrl", huifuparam.getRetUrl());
			request.setAttribute("ChkValue", ChkValue);
			request.getRequestDispatcher("/WEB-INF/pages/AutoTenderPlanClose/AutoTenderPlanClose.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/autoTenderPlanCloseReturl")
	public void autoTenderPlanCloseReturl(HuifuParams huifuParam) throws Exception{
		String RespCode = huifuParam.getRespCode();
		String RespDesc = URLDecoder.decode(huifuParam.getRespDesc(), "UTF-8");
		request.setAttribute("RespCode", RespCode);
		request.setAttribute("RespDesc", RespDesc);
		if(RespCode.equalsIgnoreCase("000")){
			UserFsAccountInfo usrCustId = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParam.getUsrCustId());
			UserAutoTender uat = new UserAutoTender();
			uat.setBaseid(usrCustId.getBaseid());
			uat.setStatus((short) 0);
			userAutoTenderService.updateByBaseIdSelective(uat);
		}
		request.getRequestDispatcher("/WEB-INF/pages/AutoTenderPlanClose/AutoTenderPlanClose_Success.jsp").forward(request, response);
	}
	
	
	/**
	 * 自动投标计划关闭测试
	 * @param request
	 * @param response
	 * @param huifuParams
	 * @return
	 */
	@RequestMapping(value="/autoTenderPlanTestClose",method={RequestMethod.POST,RequestMethod.GET})
	public String autoTenderPlanTestClose(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		System.out.println("-------------------自动投标计划关闭进来了");
		huifuParams.setCmdId("AutoTenderPlanClose");	
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()))			// 版本号
		.append(StringUtils.trimToEmpty(huifuParams.getCmdId()))					// 消息类型
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()))				// 商户客户号
		.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()))				// 用户客户号
		.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));					// 页面返回URL
		String plainStr = buffer.toString();
		System.out.println("组装自动投标计划加签字符串原文:========="+plainStr);
  		String ChkValue;
		try {
			ChkValue = SignUtils.encryptByRSA(plainStr);
 			if(StringUtil.isNotEmpty(ChkValue)){
 				huifuParams.setChkValue(ChkValue);
 			}
		} catch (Exception e) {
 			e.printStackTrace();
		}
		System.out.println("组装主动投标加签签文============"+huifuParams.getChkValue());
		request.setAttribute("Version", huifuParams.getVersion());
		request.setAttribute("CmdId", huifuParams.getCmdId());
		request.setAttribute("MerCustId", huifuParams.getMerCustId());
		request.setAttribute("UsrCustId", huifuParams.getUsrCustId());
		request.setAttribute("RetUrl", huifuParams.getRetUrl());
		request.setAttribute("ChkValue", huifuParams.getChkValue());
		
		return "huifu/autoTenderPlan";
  }
}
