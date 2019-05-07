package com.ptpl.controller.huifu;

import java.math.BigDecimal;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.huifu.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserAutoTender;
import com.ptpl.service.UserAutoTenderServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 汇付天下接口-自动投标计划
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/huifu/autoTenderPlan")
public class HuifuAutoTenderPlanController extends BaseController {	
	
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
	
	
	/**
	 * 自动投标计划测试
	 * @param request
	 * @param response
	 * @param huifuParams
	 * @return
	 */
	@RequestMapping(value="/autoTenderPlanTest",method={RequestMethod.POST,RequestMethod.GET})
	public String autoTenderPlanTest(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		System.out.println("-------------------自动投标计划进来了");
		huifuParams.setCmdId("AutoTenderPlan");	
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()))			// 版本号
		.append(StringUtils.trimToEmpty(huifuParams.getCmdId()))					// 消息类型
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()))				// 商户客户号
		.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()))				// 用户客户号
		.append(StringUtils.trimToEmpty(huifuParams.getTenderPlanType()))			// 投标计划类型
		.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()));				// 交易金额
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
		request.setAttribute("TenderPlanType", huifuParams.getTenderPlanType());
		request.setAttribute("TransAmt", huifuParams.getTransAmt());
		request.setAttribute("ChkValue", huifuParams.getChkValue());
		
		return "huifu/autoTenderPlan";
	}
	
	/***
	 * 自动投标计划页面应答地址
	 * @param huifuParam
	 * @throws Exception
	 */
	@RequestMapping("autoTenderPlanRetUrl")
	public void autoTenderPlanRetUrl(HuifuParams huifuParam) throws Exception{
		System.out.println("自动投标计划页面返回URL进来了！！！！！！！！！！！！！！！！！！！！！！！！！");
		String RespCode = huifuParam.getRespCode();
		String RespDesc = URLDecoder.decode(huifuParam.getRespDesc(),"UTF-8");
		request.setAttribute("RespCode", RespCode);
		UserAutoTender uat = new UserAutoTender();
		uat.setBaseid(new BigDecimal(huifuParam.getMerPriv()));	// 自动投标计划表BASEID
		if(RespCode.equalsIgnoreCase("000")){
			uat.setStatus((short) 1);	// 投标计划状态
			uat.setTenderplantype(huifuParam.getTenderPlanType());	// 投标计划类型
			if(huifuParam.getTenderPlanType().equalsIgnoreCase("P")){
				uat.setTransamt(Double.valueOf(huifuParam.getTransAmt()));	// 投标限制金额
			}
			if(huifuParam.getTenderPlanType().equalsIgnoreCase("W")){
				uat.setTransamt(null);	// 投标限制金额
			}
		}
		userAutoTenderService.updateByBaseIdSelective(uat);	// 更新自动投标计划表
		request.setAttribute("RespDesc", RespDesc);
		System.out.println("应答描述===================="+RespDesc);
		request.getRequestDispatcher("/WEB-INF/pages/AutoTenderPlan/AutoTenderPlan_Success.jsp").forward(request, response);
	}
}
