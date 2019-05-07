package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.GuaranteeFee;
import com.ptpl.model.GuaranteeFeeRecord;
import com.ptpl.model.MediacyFee;
import com.ptpl.model.MediacyFeeRecord;
import com.ptpl.model.RiskGuarantyFeeRecord;
import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.model.UserTender;
import com.ptpl.model.loanapp;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.GuaranteeFeeRecordServiceI;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.MediacyFeeRecordServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.RiskGuarantyFeeRecordServiceI;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 自动扣款（放款）
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/huifu/loans")
public class HuifuLoansController extends BaseController {
	
	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 投标放款记录service */
	@Autowired
	UserMakeALoanServiceI userMakeALoanService;
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;

	/** 账户收支记录service */
	@Autowired
	AccInExRecordServiceI accInExRecordService;
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的居间费记录service */
	@Autowired
	MediacyFeeRecordServiceI mediacyFeeRecordService;
	
	/** 标的担保费记录service */
	@Autowired
	GuaranteeFeeRecordServiceI guaranteeFeeRecordService;
	
	/** 标的风险保证金记录service */
	@Autowired
	RiskGuarantyFeeRecordServiceI riskGuarantyFeeRecordService;
	
	/** 标的居间费设置service */
	@Autowired
	MediacyFeeServiceI mediacyFeeService;
	
	/** 标的担保费设置service */
	@Autowired
	GuaranteeFeeServiceI guaranteeFeeService;
	
	/** 标的风险保证金设置service */
	@Autowired
	RiskGuarantyMoneyServiceI riskGuarantyMoneyService;
	
	/** 借款申请记录Service */
	@Autowired
	loanappServiceI loanappService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
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
		return "huifuhuizong/Loans";
	}
	
	
	/**
	 * 页面提交放款
	 * TODO(汇付测试 无用) 
	 * @param request
	 * @param response
	 * @param huifuParams
	 * @return
	 */
	@RequestMapping(value="/loansTest",method={RequestMethod.POST,RequestMethod.GET})
	public String loansTest(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		System.out.println("-------------------放款进来了");
 		huifuParams.setVersion("20");											//版本号 Version  30
		huifuParams.setCmdId("Loans");											//消息类型 CmdId 此处是Loans
		huifuParams.setOrdId(StringUtil.getNo());								//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
		huifuParams.setOrdDate(StringUtil.formatDate(new Date(),"yyyyMMdd"));	//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
		String DivDetails = "";
		if(!huifuParams.getFee().trim().equals("0.00")){
			huifuParams.setDivDetails("[{&quot;DivCustId&quot;:&quot;"+huifuParams.getDivCustId()+"&quot;,&quot;DivAcctId&quot;:&quot;"+huifuParams.getDivAcctId()+"&quot;,&quot;DivAmt&quot;:&quot;2.00&quot;},{&quot;DivCustId&quot;:&quot;6000060005459337&quot;,&quot;DivAcctId&quot;:&quot;"+huifuParams.getDivAcctId()+"&quot;,&quot;DivAmt&quot;:&quot;1.00&quot;}]");
			DivDetails = "[{\"DivCustId\":\""+huifuParams.getDivCustId()+"\",\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\",\"DivAmt\":\"2.00\"},{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\",\"DivAmt\":\"1.00\"}]";
		}
		if(huifuParams.getIsUnFreeze().trim().equals("Y")){  // 是否解冻
			huifuParams.setUnFreezeOrdId(StringUtil.getNo());; // 解冻订单号
		}
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuParams.setBgRetUrl(basePath+"/huifu/loans/loansCallBack.action"); // 商户后台应答地址
		if(huifuParams.getLoansVocherAmt() != null && !huifuParams.getLoansVocherAmt().trim().equals("")){
			System.out.println("使用了代金券！！！！！！！！！！！！！！！！！！");
			huifuParams.setReqExt("{\"LoansVocherAmt\":\""+huifuParams.getLoansVocherAmt()+"\",\"ProId\":\""+huifuParams.getProId()+"\"}");
		} else {
			System.out.println("未使用代金券！！！！！！！！！！！！！！！！！！");
			huifuParams.setReqExt("{\"ProId\":\""+huifuParams.getProId()+"\"}");
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()))
		.append(StringUtils.trimToEmpty(huifuParams.getCmdId()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()))
		.append(StringUtils.trimToEmpty(huifuParams.getOrdId()))
		.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()))
		.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()))
		.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()))
		.append(StringUtils.trimToEmpty(huifuParams.getFee()))
		.append(StringUtils.trimToEmpty(huifuParams.getSubOrdId()))
		.append(StringUtils.trimToEmpty(huifuParams.getSubOrdDate()))
		.append(StringUtils.trimToEmpty(huifuParams.getInCustId()))
		.append(StringUtils.trimToEmpty(DivDetails))
		.append(StringUtils.trimToEmpty(huifuParams.getFeeObjFlag()))
		.append(StringUtils.trimToEmpty(huifuParams.getIsDefault()))
		.append(StringUtils.trimToEmpty(huifuParams.getIsUnFreeze()))
		.append(StringUtils.trimToEmpty(huifuParams.getUnFreezeOrdId()))
		.append(StringUtils.trimToEmpty(huifuParams.getFreezeTrxId()))
		.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装放款加签字符串原文:========="+plainStr);
  		String ChkValue;
		try {
			ChkValue = SignUtils.encryptByRSA(plainStr);
 			if(StringUtil.isNotEmpty(ChkValue)){
 				huifuParams.setChkValue(ChkValue);
 			}
		} catch (Exception e) {
 			e.printStackTrace();
		}
		System.out.println("组装放款加签签文============"+huifuParams.getChkValue());
		request.setAttribute("Version", huifuParams.getVersion());
		request.setAttribute("CmdId", huifuParams.getCmdId());
		request.setAttribute("MerCustId", huifuParams.getMerCustId());
		request.setAttribute("OrdId", huifuParams.getOrdId());
		request.setAttribute("OrdDate", huifuParams.getOrdDate());
		request.setAttribute("OutCustId", huifuParams.getOutCustId());
		request.setAttribute("TransAmt", huifuParams.getTransAmt());
		request.setAttribute("Fee", huifuParams.getFee());
		request.setAttribute("SubOrdId", huifuParams.getSubOrdId());
		request.setAttribute("SubOrdDate", huifuParams.getSubOrdDate());
		request.setAttribute("InCustId", huifuParams.getInCustId());
		request.setAttribute("DivDetails", huifuParams.getDivDetails());
		request.setAttribute("DivCustId", huifuParams.getDivCustId());
		request.setAttribute("DivAcctId", huifuParams.getDivAcctId());
		request.setAttribute("DivAmt", huifuParams.getDivAmt());
		request.setAttribute("FeeObjFlag", huifuParams.getFeeObjFlag());
		request.setAttribute("IsDefault", huifuParams.getIsDefault());
		request.setAttribute("IsUnFreeze", huifuParams.getIsUnFreeze());
		request.setAttribute("UnFreezeOrdId", huifuParams.getUnFreezeOrdId());
		request.setAttribute("FreezeTrxId", huifuParams.getFreezeTrxId());
		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());
		request.setAttribute("ProId", huifuParams.getProId());
		request.setAttribute("ReqExt", huifuParams.getReqExt().replaceAll("\"", "&quot;"));
		request.setAttribute("ChkValue", huifuParams.getChkValue());
		inserUserMakeALoan(huifuParams);
		return "huifu/loans";
  }
	
	/***
	 * 后台放款
	 * TODO(汇付测试 无用) 
	 * @param userTender
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/loansTest1")
	public void loansTest1(HuifuParams huifuParams) throws Exception {
		huifuParams.setVersion("20"); 																// 版本号
		huifuParams.setCmdId("Loans"); 																// 消息类型
		huifuParams.setOrdId(StringUtil.getNo());													// 放款订单号
		Date loansTime = new Date();
		huifuParams.setOrdDate(StringUtil.formatDate(loansTime, "yyyyMMdd")); 						// 放款订单日期
		huifuParams.setTransAmt(df1.format(Double.valueOf(huifuParams.getTransAmt()))); 			// 交易金额-放款金额
		huifuParams.setFee(df1.format(Double.valueOf(huifuParams.getFee()))); 						// 扣款手续费 
		if(!huifuParams.getFee().equalsIgnoreCase("0.00")){ // 判断是否收取手续费
			System.out.println("收取手续费！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			huifuParams.setDivCustId("6000060004166478");											// 分账商户号
			huifuParams.setDivAcctId("MDT000001");													// 分账账户号
			huifuParams.setDivDetails("[{\"DivCustId\":\""+huifuParams.getDivCustId()+"\",\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\",\"DivAmt\":\"2.00\"},{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\"MDT000001\",\"DivAmt\":\"2.00\"}]");
			huifuParams.setDivAmt(huifuParams.getFee()); 												// 分账金额
			huifuParams.setFeeObjFlag("I");															// 手续费收取对象标志-I--向入款客户号 InCustId 收取
		}
		huifuParams.setIsDefault("N");																// 是否默认 Y--默认添加资金池:这部分资金需要商户调用商户代取现接口，帮助用户做后台取现动作
																									// 		 N--不默认不添加资金池:这部分资金用户可以自己取现
		huifuParams.setIsUnFreeze("Y");																// 是否解冻
		huifuParams.setUnFreezeOrdId(StringUtil.getNo());											// 解冻订单号
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuParams.setBgRetUrl(basePath+"/huifu/loans/loansCallBack.action"); 						// 商户后台应答地址
		huifuParams.setMerPriv(HttpClientHandler.getBase64Encode("中国"));
		huifuParams.setReqExt("{\"ProId\":\""+huifuParams.getProId()+"\"}");											// 入参扩展域
		huifuParams.setProId(huifuParams.getProId());																	// 项目ID
		Map<String, String> Params = LoansParams(huifuParams);
		String result = doPost(Params);
		System.out.println("放款结果！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"+result);
		JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject6
		String RespCode = json.getString("RespCode");			// 投标放款应答返回码
		System.out.println("放款返回码========================================="+RespCode);
	}

	/**
	 * 汇付天下-自动扣款（放款）接口请求参数
	 * @param tenderParams
	 * @param request
	 * @param userTender
	 * @param tenderItem
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> LoansParams(HuifuParams huifuparam) throws Exception {
		System.out.println("=================放款请求进来了！=================");
		System.out.println("Version========================="+huifuparam.getVersion());
		System.out.println("CmdId========================="+huifuparam.getCmdId());
		System.out.println("MerCustId========================="+huifuparam.getMerCustId());
		System.out.println("OrdId========================="+huifuparam.getOrdId());
		System.out.println("OrdDate========================="+huifuparam.getOrdDate());
		System.out.println("OutCustId========================="+huifuparam.getOutCustId());
		System.out.println("TransAmt========================="+huifuparam.getTransAmt());
		System.out.println("Fee========================="+huifuparam.getFee());
		System.out.println("SubOrdId========================="+huifuparam.getSubOrdId());
		System.out.println("SubOrdDate========================="+huifuparam.getSubOrdDate());
		System.out.println("InCustId========================="+huifuparam.getInCustId());
		System.out.println("DivCustId========================="+huifuparam.getDivCustId());
		System.out.println("DivAcctId========================="+huifuparam.getDivAcctId());
		System.out.println("DivDetails========================="+huifuparam.getDivDetails());
		System.out.println("DivAmt========================="+huifuparam.getDivAmt());
		System.out.println("FeeObjFlag========================="+huifuparam.getFeeObjFlag());
		System.out.println("IsDefault========================="+huifuparam.getIsDefault());
		System.out.println("IsUnFreeze========================="+huifuparam.getIsUnFreeze());
		System.out.println("UnFreezeOrdId========================="+huifuparam.getUnFreezeOrdId());
		System.out.println("FreezeTrxId========================="+huifuparam.getFreezeTrxId());
		System.out.println("BgRetUrl========================="+huifuparam.getBgRetUrl());
		System.out.println("MerPriv========================="+huifuparam.getMerPriv());
		System.out.println("ReqExt========================="+huifuparam.getReqExt());
		System.out.println("ProId========================="+huifuparam.getProId());
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", huifuparam.getVersion());
		params.put("CmdId", huifuparam.getCmdId());
		params.put("MerCustId", huifuparam.getMerCustId());
		params.put("OrdId", huifuparam.getOrdId());
		params.put("OrdDate", huifuparam.getOrdDate());
		params.put("OutCustId", huifuparam.getOutCustId());
		params.put("TransAmt", huifuparam.getTransAmt());
		params.put("Fee", huifuparam.getFee());
		params.put("SubOrdId", huifuparam.getSubOrdId());
		params.put("SubOrdDate", huifuparam.getSubOrdDate());
		params.put("InCustId", huifuparam.getInCustId());
		params.put("DivDetails", huifuparam.getDivDetails());
		params.put("DivCustId", huifuparam.getDivCustId());
		params.put("DivAcctId", huifuparam.getDivAcctId());
		params.put("DivAmt", huifuparam.getDivAmt());
		params.put("FeeObjFlag", huifuparam.getFeeObjFlag());
		params.put("IsDefault", huifuparam.getIsDefault());
		params.put("IsUnFreeze", huifuparam.getIsUnFreeze());
		params.put("UnFreezeOrdId", huifuparam.getUnFreezeOrdId());
		params.put("FreezeTrxId", huifuparam.getFreezeTrxId());
		params.put("BgRetUrl", huifuparam.getBgRetUrl());
		params.put("MerPriv", huifuparam.getMerPriv());
		params.put("ProId", huifuparam.getProId());
		params.put("ReqExt", huifuparam.getReqExt());
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getOrdDate()))
				.append(StringUtils.trimToEmpty(huifuparam.getOutCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getTransAmt()))
				.append(StringUtils.trimToEmpty(huifuparam.getFee()))
				.append(StringUtils.trimToEmpty(huifuparam.getSubOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getSubOrdDate()))
				.append(StringUtils.trimToEmpty(huifuparam.getInCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getDivDetails()))
				.append(StringUtils.trimToEmpty(huifuparam.getFeeObjFlag()))
				.append(StringUtils.trimToEmpty(huifuparam.getIsDefault()))
				.append(StringUtils.trimToEmpty(huifuparam.getIsUnFreeze()))
				.append(StringUtils.trimToEmpty(huifuparam.getUnFreezeOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getFreezeTrxId()))
				.append(StringUtils.trimToEmpty(huifuparam.getBgRetUrl()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerPriv()))
				.append(StringUtils.trimToEmpty(huifuparam.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装放款加签字符串原文:========="+plainStr);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			params.put("ChkValue", ChkValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	@RequestMapping("/loansCallBack")
	public synchronized void  loansCallBack(HttpServletRequest request, HttpServletResponse response, HuifuParams huifuParam) throws Exception {
		System.out.println("放款回调进来了！！！！！！！！！！！！！！！！！！！！！！！！！");
        request.setCharacterEncoding("UTF-8");
        String chkValue = huifuParam.getChkValue();
        System.out.println("放款应答获取的签名：=============="+chkValue);
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(huifuParam.getCmdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespCode()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOrdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOrdDate()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOutCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOutAcctId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getTransAmt()))
        		.append(StringUtils.trimToEmpty(huifuParam.getFee()))
        		.append(StringUtils.trimToEmpty(huifuParam.getInCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getInAcctId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getSubOrdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getSubOrdDate()))
        		.append(StringUtils.trimToEmpty(huifuParam.getFeeObjFlag()))
        		.append(StringUtils.trimToEmpty(huifuParam.getIsDefault()))
        		.append(StringUtils.trimToEmpty(huifuParam.getIsUnFreeze()))
        		.append(StringUtils.trimToEmpty(huifuParam.getUnFreezeOrdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getFreezeTrxId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getBgRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerPriv()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespExt()));
        String plainStr = buffer.toString();
        System.out.println("放款应答获取的返回参数拼接：=============="+plainStr);
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
    		UserMakeALoan userMakeALoan = userMakeALoanService.selectByMLoanOrderNo(huifuParam.getOrdId());
        	if(huifuParam.getRespCode().equals("000")){ // 返回码000成功
        		String MerPriv = HttpClientHandler.getBase64Decode(huifuParam.getMerPriv());
        		System.out.println("商户私有域==============="+MerPriv);
				String orderno = huifuParam.getSubOrdId(); // 投标订单号
				UserTender tender = userTenderService.findUserTenderByOrderno(orderno);
				TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(MerPriv));
				if(tenderItem.getValuedate() == null){
					// 更新标的设置表的结标日
					tenderItem.setValuedate(new Date());	// 结标日
					int num = 0;
					num = tenderItemService.update(tenderItem);
					if(num > 0){
						System.out.println("更新标的设置表结标日成功！！！！！！！！！！！！");
					}
				}
				if(userMakeALoan.getMalstatus()==0){
					updateInvestorAccount(huifuParam);
					updateBorrowerAccount(huifuParam);
					loanapp loanapp = loanappService.selectByPrimaryKey(tenderItem.getLoanappid());
					loanapp.setReceiptsamount(Arith.add(loanapp.getReceiptsamount(), Double.valueOf(huifuParam.getTransAmt()))); // 已入账借款金额
					loanapp.setAppstatus((short) 5); // 0审核中 1成功 2失败 3投标中 4流标 5还款中 6已发布
					int count = 0;
					count = loanappService.updateByPrimaryKeySelective(loanapp);
					if(count > 0){
						System.out.println("更新借款申请已入账借款金额成功！！！！！！！！！！！！！！！！！！！！！！！！！！");
					}
					if(tender.getMediacyfee() != 0){ // 扣除居间费
						deductionBorrowerMediacyfee(huifuParam, tender);
					}
					if(tender.getGuaranteefee() != 0){
						deductionBorrowerGuaranteefee(huifuParam, tender);
					}
					if(tender.getRiskguarantyfee() != 0){
						deductionBorrowerRiskguarantyfee(huifuParam, tender);
					}
				}
        		userMakeALoan.setMalendtime(new Date()); 				// 放款完成时间
        		userMakeALoan.setMalstatus((short) 1); 					// 放款的状态（0.失败，1.成功） 
        		userMakeALoan.setIsthaw((short) 1);						// 是否解冻（0.否，1.是）
				int number = 0;
				number = userMakeALoanService.updateByMLoanOrderNoSelective(userMakeALoan);
				if(number > 0){
					System.out.println("更新投标放款记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
				}
				tender.setTstatus(TenderRecord_Constant.TSTATUS_COMPLETED); // 投标状态
				tender.setIsfreeze(TenderRecord_Constant.ISFREEZE_UNFREEZE); // 已解冻
				tender.setIsrepayend(TenderRecord_Constant.ISREPAYEND_REPAYMENTING); // 还款中
				int rows = 0;
				rows = userTenderService.updateByOrderNO(tender);
				if(rows > 0){
					System.out.println("更新投标记录成功");
				}
				System.out.println("===========生成具体还款计划开始==============");
				AutoGenerateRepayMentUtil.AutoGenerateRepayMents(new BigDecimal(MerPriv));
				System.out.println("===========生成具体还款计划结束==============");
				System.out.println("===========生成分期还款计划开始==============");
				AutoGenerateRepayMentUtil.AutoGenerateDividedPayments(new BigDecimal(MerPriv));
				System.out.println("===========生成分期还款计划结束==============");
				MediacyFeeRecord mediacyFeeRecord = mediacyFeeRecordService.selectByUtOrderNo(orderno);
				if(mediacyFeeRecord == null){ // 避免重复新增记录
					if(tender.getMediacyfee() != 0){
						System.out.println("===========生成标的居间费记录开始==============");
						insertMediacyFeeRecord(tender);
						System.out.println("===========生成标的居间费记录结束==============");
					}
				}
				GuaranteeFeeRecord guaranteeFeeRecord = guaranteeFeeRecordService.selectByUtOrderNo(orderno);
				if(guaranteeFeeRecord == null){
					if(tender.getGuaranteefee() != 0){
						System.out.println("===========生成标的担保费记录开始==============");
						insertGuaranteeFeeRecord(tender);
						System.out.println("===========生成标的担保费记录结束==============");
					}
				}
				RiskGuarantyFeeRecord riskGuarantyFeeRecord = riskGuarantyFeeRecordService.selectByUtOrderNo(orderno);
				if(riskGuarantyFeeRecord == null){
					if(tender.getRiskguarantyfee() != 0){
						System.out.println("===========生成标的风险保证金记录开始==============");
						insertRiskGuarantyFeeRecord(tender);
						System.out.println("===========生成标的风险保证金记录结束==============");
					}
				}
        	}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
        try {
            if (StringUtils.isNotBlank(huifuParam.getOrdId())) {
                PrintWriter out = response.getWriter();
                out.print("RECV_ORD_ID_".concat(huifuParam.getOrdId()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


	/**
	 * 新增投标放款记录
	 * @param huifuParams
	 */
	private void inserUserMakeALoan(HuifuParams huifuParams){
		UserMakeALoan umal = new UserMakeALoan();
		umal.setTenderid(new BigDecimal(236)); // 标的号ID
		umal.setOrderno(huifuParams.getSubOrdId()); // 投标订单号
		umal.setMloanorderno(huifuParams.getOrdId()); // 放款订单号 
		umal.setOutaccountid(new BigDecimal(324)); // 投资方ID
		umal.setInaccountid(new BigDecimal(319)); // 借款方ID
		umal.setAmount((double) 100); // 放款金额
		umal.setMalbegintime(new Date()); // 放款开始时间
		umal.setFee((double) 0); // 手续费（默认0）
		umal.setMediacyfee((double) 0); // 居间费
		umal.setGuaranteefee((double) 0); // 担保费
		umal.setRiskguarantyfee((double) 0); // 风险保证金
		umal.setIsthaw((short) 1); // 是否解冻（0.否，1.是）
		umal.setUnfreezeordid(huifuParams.getUnFreezeOrdId()); // 解冻订单号
		umal.setFreezetrxid(huifuParams.getFreezeTrxId()); // 冻结标识
		umal.setMalstatus((short) 1); // 放款的状态（成功，失败） 
		umal.setIsblending((short) 0); // 是否系统勾兑（0.未勾兑，1.已勾兑）
		umal.setIsmanblending((short) 0); // 是否人工勾兑（0.未勾兑，1.已勾兑）
		umal.setPaycompany("汇付天下"); // 放款通道公司
		umal.setIsaudit((short) 0); // 是否审核
		umal.setRemark("及时投标放款测试"); // 备注
		userMakeALoanService.insertSelective(umal);
	}
	

	public static String doPost(Map<String, String> params) throws ClientProtocolException, IOException
	{
		String result = null;
		HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if (params != null)
		{
			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
			httpPost.setEntity(formEntiry);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try
			{
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			} finally
			{
				response.close();
			}
		}
		return result;
	}
	
	
	/**
	 * 更新投资人用户账户表
	 * @param investor-投资人客户号
	 * @param transAmt-转账金额
	 */
	private void updateInvestorAccount(HuifuParams huifuParams) {
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getOutCustId());// 根据投资人客户号查询托管信息
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid()); 						// 根据baseid查询投资人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), Double.valueOf(huifuParams.getTransAmt()));							// 用户新总资产=用户原总资产-转账金额
		double freezebalance = Arith.sub(userAccount.getFreezebalance(), Double.valueOf(huifuParams.getTransAmt()));				// 用户冻结余额=用户原冻结余额-转账金额
		userAccount.setBalance(balance);																							// 用户总资产
		userAccount.setFreezebalance(freezebalance);																				// 冻结余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新投资人用户账户表
		if(count > 0){
			System.out.println("更新投资人用户账户表成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			insertInvestorAccInExRecord(huifuParams, userAccount);
		}
	}
	
	/**
	 * 新增投资人账户收支记录-投标
	 */
	private void insertInvestorAccInExRecord(HuifuParams huifuParams, UserAccount userAccount){
		AccInExRecord record = new AccInExRecord();
		record.setBaseid(userAccount.getBaseid());							// 投资人baseId
		record.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		record.setType((short) 5);											// 业务类型-投标
		record.setInamount((double) 0);										// 收入
		record.setOutamount(Double.valueOf(huifuParams.getTransAmt()));		// 支出
		record.setStatus((short) 1);										// 业务状态-成功
		record.setDescription("投标转账");										// 说明
		record.setBalance(userAccount.getAvlbalance());						// 用户可用余额
		record.setFreebalance(userAccount.getFreezebalance());				// 用户冻结余额
		record.setTotalbalance(userAccount.getBalance());					// 用户总金额
		record.setRecordtime(new Date());									// 发生时间
		record.setRemark("转账转出");											// 备注
		int count = 0;
		count = accInExRecordService.insertSelective(record);				// 新增投标转账转出收支记录明细
		if(count > 0){
			System.out.println("新增投资人账户收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 更新借款人用户账户表-转账金额
	 * @param huifuParam
	 */
	private void updateBorrowerAccount(HuifuParams huifuParams) {
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getInCustId()); // 根据借款人客户号查询托管信息
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid()); 						// 根据baseid查询借款人用户账户信息
		double balance = Arith.add(userAccount.getBalance(), Double.valueOf(huifuParams.getTransAmt()));							// 用户新总资产=用户原总资产+转账金额
		double avlbalance = Arith.add(userAccount.getAvlbalance(), Double.valueOf(huifuParams.getTransAmt()));						// 用户新可用余额=用户原可用余额+转账金额
		userAccount.setBalance(balance);																							// 用户总资产
		userAccount.setAvlbalance(avlbalance);																						// 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if(count > 0){
			System.out.println("更新借款人用户账户表成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			insertLoanAccInExRecord(huifuParams, userAccount);
		}
	}
	
	/**
	 * 扣除借款人居间费
	 * @param huifuParam
	 */
	private void deductionBorrowerMediacyfee(HuifuParams huifuParams, UserTender userTender) {
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getInCustId()); // 根据借款人客户号查询托管信息
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid()); 						// 根据baseid查询借款人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), userTender.getMediacyfee());											// 用户新总资产=用户原总资产-居间费
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), userTender.getMediacyfee());										// 用户新可用余额=用户原可用余额-居间费
		userAccount.setBalance(balance);																							// 用户总资产
		userAccount.setAvlbalance(avlbalance);																						// 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if(count > 0){
			System.out.println("扣除借款人居间费成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			chargeMediacyFee(userTender, userAccount);
		}
	}
	
	/**
	 * 扣除借款人担保费
	 * @param huifuParam
	 */
	private void deductionBorrowerGuaranteefee(HuifuParams huifuParams, UserTender userTender) {
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getInCustId()); // 根据借款人客户号查询托管信息
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid()); 						// 根据baseid查询借款人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), userTender.getGuaranteefee());											// 用户新总资产=用户原总资产-担保费
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), userTender.getGuaranteefee());									// 用户新可用余额=用户原可用余额-担保费
		userAccount.setBalance(balance);																							// 用户总资产
		userAccount.setAvlbalance(avlbalance);																						// 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if(count > 0){
			System.out.println("更新借款人担保费成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			chargeGuaranteeFee(userTender, userAccount);
		}
	}

	/**
	 * 扣除借款人风险保证金
	 * @param huifuParam
	 */
	private void deductionBorrowerRiskguarantyfee(HuifuParams huifuParams, UserTender userTender) {
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getInCustId()); // 根据借款人客户号查询托管信息
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid()); 						// 根据baseid查询借款人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), userTender.getRiskguarantyfee());										// 用户新总资产=用户原总资产-风险保证金
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), userTender.getRiskguarantyfee());								// 用户新可用余额=用户原可用余额-风险保证金
		userAccount.setBalance(balance);																							// 用户总资产
		userAccount.setAvlbalance(avlbalance);																						// 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if(count > 0){
			System.out.println("更新借款人风险保证金成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			chargeRiskGuarantyFee(userTender, userAccount);
		}
	}


	/**
	 * 新增借款人账户收支记录-投标
	 */
	private void insertLoanAccInExRecord(HuifuParams huifuParams, UserAccount userAccount){
		AccInExRecord record = new AccInExRecord();
		record.setBaseid(userAccount.getBaseid());							// 借款人baseId
		record.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		record.setType((short) 5);											// 业务类型-投标
		record.setInamount(Double.valueOf(huifuParams.getTransAmt()));		// 收入
		record.setOutamount((double) 0);									// 支出
		record.setStatus((short) 1);										// 业务状态-成功
		record.setDescription("投标转账");										// 说明
		record.setBalance(userAccount.getAvlbalance());						// 用户可用余额
		record.setFreebalance(userAccount.getFreezebalance());				// 用户冻结余额
		record.setTotalbalance(userAccount.getBalance());					// 用户总金额
		record.setRecordtime(new Date());									// 发生时间
		record.setRemark("转账转入");											// 备注
		int count = 0;
		count = accInExRecordService.insertSelective(record);				// 新增投标转账转入收支记录明细
		if(count > 0){
			System.out.println("新增借款人账户收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 收取居间费
	 * @param userTender
	 */
	private void chargeMediacyFee(UserTender userTender, UserAccount userAccount) {
		AccInExRecord accInExRecord = new AccInExRecord();
		accInExRecord.setBaseid(userTender.getInaccountid());						// 用户ID-借款人
		accInExRecord.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		accInExRecord.setType((short) 15);											// 业务类型-居间费
		accInExRecord.setInamount((double) 0);										// 收入
		accInExRecord.setOutamount(userTender.getMediacyfee());						// 支出
		accInExRecord.setPaccount("MDT000001");										// 平台账户
		accInExRecord.setPinamount(userTender.getMediacyfee());						// 平台收入
		accInExRecord.setPoutamount((double) 0);									// 平台支出
		accInExRecord.setStatus((short) 1);											// 业务状态
		accInExRecord.setDescription("收取居间费");										// 说明
		accInExRecord.setBalance(userAccount.getAvlbalance());						// 用户可用余额
		accInExRecord.setFreebalance(userAccount.getFreezebalance());				// 用户冻结余额
		accInExRecord.setTotalbalance(userAccount.getBalance());					// 用户总金额
		accInExRecord.setRecordtime(new Date());									// 发生时间
		accInExRecord.setRemark("收取居间费");											// 备注
		int count = 0;
		count = accInExRecordService.insertSelective(accInExRecord);
		if(count > 0){
			System.out.println("新增居间费收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 收取担保费
	 * @param userTender
	 * @param userAccount
	 */
	private void chargeGuaranteeFee(UserTender userTender, UserAccount userAccount) {
		AccInExRecord accInExRecord = new AccInExRecord();
		accInExRecord.setBaseid(userTender.getInaccountid());						// 用户ID-借款人
		accInExRecord.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		accInExRecord.setType((short) 16);											// 业务类型-担保费
		accInExRecord.setInamount((double) 0);										// 收入
		accInExRecord.setOutamount(userTender.getGuaranteefee());					// 支出
		accInExRecord.setPaccount("MDT000001");										// 平台账户
		accInExRecord.setPinamount(userTender.getGuaranteefee());					// 平台收入
		accInExRecord.setPoutamount((double) 0);									// 平台支出
		accInExRecord.setStatus((short) 1);											// 业务状态
		accInExRecord.setDescription("收取担保费");										// 说明
		accInExRecord.setBalance(userAccount.getAvlbalance());						// 用户可用余额
		accInExRecord.setFreebalance(userAccount.getFreezebalance());				// 用户冻结余额
		accInExRecord.setTotalbalance(userAccount.getBalance());					// 用户总金额
		accInExRecord.setRecordtime(new Date());									// 发生时间
		accInExRecord.setRemark("收取担保费");											// 备注
		int count = 0;
		count = accInExRecordService.insertSelective(accInExRecord);
		if(count > 0){
			System.out.println("新增担保费收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}

	/**
	 * 收取风险保证金
	 * @param userTender
	 * @param userAccount
	 */
	private void chargeRiskGuarantyFee(UserTender userTender, UserAccount userAccount) {
		AccInExRecord accInExRecord = new AccInExRecord();
		accInExRecord.setBaseid(userTender.getInaccountid());						// 用户ID-借款人
		accInExRecord.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		accInExRecord.setType((short) 18);											// 业务类型-担保费
		accInExRecord.setInamount((double) 0);										// 收入
		accInExRecord.setOutamount(userTender.getRiskguarantyfee());				// 支出
		accInExRecord.setPaccount("MDT000001");										// 平台账户
		accInExRecord.setPinamount(userTender.getRiskguarantyfee());				// 平台收入
		accInExRecord.setPoutamount((double) 0);									// 平台支出
		accInExRecord.setStatus((short) 1);											// 业务状态
		accInExRecord.setDescription("收取风险保证金");									// 说明
		accInExRecord.setBalance(userAccount.getAvlbalance());						// 用户可用余额
		accInExRecord.setFreebalance(userAccount.getFreezebalance());				// 用户冻结余额
		accInExRecord.setTotalbalance(userAccount.getBalance());					// 用户总金额
		accInExRecord.setRecordtime(new Date());									// 发生时间
		accInExRecord.setRemark("收取风险保证金");										// 备注
		int count = 0;
		count = accInExRecordService.insertSelective(accInExRecord);
		if(count > 0){
			System.out.println("新增风险保证金收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 新增标的居间费记录
	 * @param userTender
	 */
	private void insertMediacyFeeRecord(UserTender userTender){
		MediacyFeeRecord mediacyFeeRecord = new MediacyFeeRecord();
		mediacyFeeRecord.setMforderno(StringUtil.getNo());								// 居间费流水号
		mediacyFeeRecord.setUtorderno(userTender.getOrderno());							// 投标订单号
		BigDecimal mfId = userTender.getMfid();											// 居间费设置表ID
		mediacyFeeRecord.setMfid(mfId);
		mediacyFeeRecord.setTenderid(userTender.getTenderid());							// 标号ID
		MediacyFee mediacyFee = mediacyFeeService.selectByPrimaryKey(mfId); 
		mediacyFeeRecord.setMrecmanid(mediacyFee.getMrecmanid());						// 居间费收款人ID
		mediacyFeeRecord.setBmanid(userTender.getInaccountid());						// 借款人ID
		mediacyFeeRecord.setNodemarkamount(userTender.getAmount());						// 结标金额（投标金额）
		mediacyFeeRecord.setMediacyfee(userTender.getMediacyfee());						// 居间费
		if(mediacyFee.getMfquota() != null){
			mediacyFeeRecord.setMftype((short) 1);										// 居间费收取类型-1.定额
		}
		if(mediacyFee.getMfpercent() != null){
			mediacyFeeRecord.setMftype((short) 2);										// 居间费收取类型-2.百分比
		}
		if(mediacyFee.getMinmffee() != null && Arith.sub(userTender.getMediacyfee(), mediacyFee.getMinmffee()) <= 0){
			mediacyFeeRecord.setMftype((short) 3);										// 居间费收取类型-3.最低
		}
		if(mediacyFee.getMaxmffee() != null && Arith.sub(userTender.getMediacyfee(), mediacyFee.getMaxmffee()) >= 0){
			mediacyFeeRecord.setMftype((short) 4);										// 居间费收取类型-4.最高
		}
		mediacyFeeRecord.setChargetype(mediacyFee.getChargetype());						// 收费方式
		mediacyFeeRecord.setIsdeal((short) 1);											// 是否处理（0.否，1.是）
		mediacyFeeRecord.setIsaudit((short) 0);											// 是否审核（0.否，1.是）
		mediacyFeeRecord.setRemark("居间费收取测试");										// 备注
		int count = 0;
		count = mediacyFeeRecordService.insertSelective(mediacyFeeRecord);
		if(count > 0){
			System.out.println("新增标的居间费记录成功！！！！！！！！！");
		}
	}
	
	/**
	 * 新增标的担保费记录
	 * @param userTender
	 */
	private void insertGuaranteeFeeRecord(UserTender userTender){
		GuaranteeFeeRecord guaranteeFeeRecord = new GuaranteeFeeRecord();
		guaranteeFeeRecord.setGforderno(StringUtil.getNo());							// 担保费流水号
		guaranteeFeeRecord.setUtorderno(userTender.getOrderno());						// 投标订单号
		BigDecimal gfId = userTender.getGfid();											// 担保费设置表ID
		guaranteeFeeRecord.setGfid(gfId);
		GuaranteeFee guaranteeFee = guaranteeFeeService.selectByPrimaryKey(gfId);
		guaranteeFeeRecord.setTenderid(userTender.getTenderid());						// 标号ID
		guaranteeFeeRecord.setGuaranteemanid(guaranteeFee.getGfrecmanid());				// 担保费收款人ID
		guaranteeFeeRecord.setBmanid(userTender.getInaccountid());						// 借款人ID
		guaranteeFeeRecord.setNodemarkamount(userTender.getAmount());					// 结标金额（投标金额）
		guaranteeFeeRecord.setGuaranteefee(userTender.getGuaranteefee());				// 担保费
		if(guaranteeFee.getGfquota() != null){
			guaranteeFeeRecord.setGftype((short) 1);									// 担保费收取类型-1.定额
		}
		if(guaranteeFee.getGfpercent() != null){
			guaranteeFeeRecord.setGftype((short) 2);									// 担保费收取类型-2.百分比
		}
		if(guaranteeFee.getMingffee() != null && Arith.sub(userTender.getMediacyfee(), guaranteeFee.getMingffee()) <= 0){
			guaranteeFeeRecord.setGftype((short) 3);									// 担保费收取类型-3.最低
		}
		if(guaranteeFee.getMaxgffee() != null && Arith.sub(userTender.getMediacyfee(), guaranteeFee.getMaxgffee()) >= 0){
			guaranteeFeeRecord.setGftype((short) 4);									// 担保费收取类型-4.最高
		}
		guaranteeFeeRecord.setChargetype(guaranteeFee.getChargetype());					// 收费方式
		guaranteeFeeRecord.setIsdeal((short) 1);										// 是否处理
		guaranteeFeeRecord.setIsaudit((short) 0);										// 是否审核
		guaranteeFeeRecord.setRemark("担保费收取测试");										// 备注
		int count = 0;
		count = guaranteeFeeRecordService.insertSelective(guaranteeFeeRecord);
		if(count > 0){
			System.out.println("新增标的担保费记录成功！！！！！！！！！");
		}
	}
	
	/**
	 * 新增标的风险保证金记录
	 * @param userTender
	 */
	private void insertRiskGuarantyFeeRecord(UserTender userTender){
		RiskGuarantyFeeRecord riskGuarantyFeeRecord = new RiskGuarantyFeeRecord();
		riskGuarantyFeeRecord.setRgmorderno(StringUtil.getNo());						// 风险保证金流水号
		riskGuarantyFeeRecord.setUtorderno(userTender.getOrderno());					// 投标订单号
		BigDecimal rgmId = userTender.getRgmid();										// 风险保证金设置表ID
		riskGuarantyFeeRecord.setRgmid(rgmId);
		RiskGuarantyMoney riskGuarantyMoney = riskGuarantyMoneyService.selectByPrimaryKey(rgmId);
		riskGuarantyFeeRecord.setTenderid(userTender.getTenderid());					// 标号ID
		riskGuarantyFeeRecord.setRgmmanid(riskGuarantyMoney.getRgmrecmanid());			// 保证金收款人ID
		riskGuarantyFeeRecord.setBmanid(userTender.getInaccountid());					// 借款人ID
		riskGuarantyFeeRecord.setTenderamount(userTender.getAmount());					// 投标金额
		riskGuarantyFeeRecord.setGuarantyfee(userTender.getRiskguarantyfee());			// 保证金
		if(riskGuarantyMoney.getRgmquota() != null){
			riskGuarantyFeeRecord.setRgmtype((short) 1);								// 保证金费率收取类型-1.定额
		}
		if(riskGuarantyMoney.getRgmpercent() != null){
			riskGuarantyFeeRecord.setRgmtype((short) 2);								// 保证金费率收取类型-2.百分比
		}
		if(riskGuarantyMoney.getMaxrgmfee() != null && Arith.sub(riskGuarantyMoney.getMaxrgmfee(), userTender.getRiskguarantyfee()) >= 0){
			riskGuarantyFeeRecord.setRgmtype((short) 3);								// 保证金费率收取类型-3.最高
		}
		riskGuarantyFeeRecord.setChargetype(riskGuarantyMoney.getChargetype());			// 收费方式
		riskGuarantyFeeRecord.setIsdeal((short) 1);										// 是否处理
		riskGuarantyFeeRecord.setIsaudit((short) 0);									// 是否审核
		riskGuarantyFeeRecord.setRemark("风险保证金收取测试");								// 备注
		int count = 0;
		count = riskGuarantyFeeRecordService.insertSelective(riskGuarantyFeeRecord);
		if(count > 0){
			System.out.println("新增标的风险保证金记录成功！！！！！！！！！");
		}
	}
	
}
