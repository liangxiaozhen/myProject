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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 汇付天下-自动投标
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/huifu/autoTender")
public class HuifuAutoTenderController extends BaseController {
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;

	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/**
	 * 汇付天下-自动投标接口请求参数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/hfAutoTender")
	public void hfAutoTender(HuifuParams huifuparam) throws Exception {
		huifuparam.setVersion("20"); 															// 版本号
		huifuparam.setCmdId("AutoTender"); 														// 消息类型AutoTender
		huifuparam.setOrdId(StringUtil.getNo());												// 订单号
		huifuparam.setOrdDate(StringUtil.formatDate(new Date(), "yyyyMMdd")); 					// 订单日期
		huifuparam.setTransAmt(""); 															// 交易金额
		huifuparam.setUsrCustId(""); 															// 用户客户号
		huifuparam.setMaxTenderRate("0.50"); 													// 最大投资手续费率
		huifuparam.setBorrowerDetails("[{\"BorrowerCustId\":\"6000060005290553\",\"BorrowerAmt\":\"1000.00\",\"BorrowerRate\":\"0.00\",\"ProId\":\"gjbh201609051627\"}]"); // 借款人信息
		huifuparam.setBorrowerCustId(""); 														// 借款人客户号
		huifuparam.setBorrowerAmt(huifuparam.getTransAmt()); 									// 借款金额
		huifuparam.setBorrowerRate("1.00"); 													// 借款手续费率
		huifuparam.setProId(""); 																// 项目ID
		huifuparam.setIsFreeze("Y"); 															// 是否冻结
		huifuparam.setFreezeOrdId(huifuparam.getOrdId()); 										// 冻结订单号
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
//		huifuparam.setRetUrl(basePath+"/huifu/autoTender/callbackPage.action"); 				// 页面返回URL
		huifuparam.setBgRetUrl(basePath+"/huifu/autoTender/autoTenderCallBack.action"); 		// 商户后台应答地址
		// 若为中文，请用Base64转码
		huifuparam.setMerPriv(HttpClientHandler.getBase64Encode("11")); // 将用户基本信息id放入商户私有域
//		huifuparam.setReqExt(""); // 入参扩展域
//		huifuparam.setAcctId(""); // 代金券出账子账户
//		huifuparam.setVocherAmt(""); // 代金券金额
		getAutoTenderParams(huifuparam);
	}
	
	/**
	 * 汇付天下-自动投标接口请求参数
	 * @param huifuparam
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getAutoTenderParams(HuifuParams huifuparam) throws Exception {
		System.out.println("=================主动投标请求进来了！=================");
		System.out.println("Version========================="+huifuparam.getVersion());
		System.out.println("CmdId========================="+huifuparam.getCmdId());
		System.out.println("MerCustId========================="+huifuparam.getMerCustId());
		System.out.println("OrdId========================="+huifuparam.getOrdId());
		System.out.println("OrdDate========================="+huifuparam.getOrdDate());
		System.out.println("TransAmt========================="+huifuparam.getTransAmt());
		System.out.println("UsrCustId========================="+huifuparam.getUsrCustId());
		System.out.println("MaxTenderRate========================="+huifuparam.getMaxTenderRate());
		System.out.println("BorrowerDetails========================="+huifuparam.getBorrowerDetails());
		System.out.println("BorrowerCustId========================="+huifuparam.getBorrowerCustId());
		System.out.println("BorrowerAmt========================="+huifuparam.getBorrowerAmt());
		System.out.println("BorrowerRate========================="+huifuparam.getBorrowerRate());
		System.out.println("ProId========================="+huifuparam.getProId());
		System.out.println("IsFreeze========================="+huifuparam.getIsFreeze());
		System.out.println("FreezeOrdId========================="+huifuparam.getFreezeOrdId());
		System.out.println("BgRetUrl========================="+huifuparam.getBgRetUrl());
		System.out.println("MerPriv========================="+huifuparam.getMerPriv());
		System.out.println("ReqExt========================="+huifuparam.getReqExt());
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", huifuparam.getVersion());
		params.put("CmdId", huifuparam.getCmdId());
		params.put("MerCustId", huifuparam.getMerCustId());
		params.put("OrdId", huifuparam.getOrdId());
		params.put("OrdDate", huifuparam.getOrdDate());
		params.put("TransAmt", huifuparam.getTransAmt());
		params.put("UsrCustId", huifuparam.getUsrCustId());
		params.put("MaxTenderRate", huifuparam.getMaxTenderRate());
		params.put("BorrowerDetails", huifuparam.getBorrowerDetails());
		params.put("BorrowerCustId", huifuparam.getBorrowerCustId());
		params.put("BorrowerAmt", huifuparam.getBorrowerAmt());
		params.put("BorrowerRate", huifuparam.getBorrowerRate());
		params.put("ProId", huifuparam.getProId());
		params.put("IsFreeze", huifuparam.getIsFreeze());
		params.put("FreezeOrdId", huifuparam.getFreezeOrdId());
		params.put("BgRetUrl", huifuparam.getBgRetUrl());
		params.put("MerPriv", huifuparam.getMerPriv());
		params.put("ReqExt", huifuparam.getReqExt());
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getOrdDate()))
				.append(StringUtils.trimToEmpty(huifuparam.getTransAmt()))
				.append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMaxTenderRate()))
				.append(StringUtils.trimToEmpty(huifuparam.getBorrowerDetails()))
				.append(StringUtils.trimToEmpty(huifuparam.getIsFreeze()))
				.append(StringUtils.trimToEmpty(huifuparam.getFreezeOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getBgRetUrl()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerPriv()))
				.append(StringUtils.trimToEmpty(huifuparam.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装自动投标加签字符串原文:========="+plainStr);
		saveTenderRecord(huifuparam);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			params.put("ChkValue", ChkValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	/**
	 * TODO 测试无用
	 * @param request
	 * @param response
	 * @param huifuParams
	 * @return
	 */
	@RequestMapping(value="/autoTenderTest",method={RequestMethod.POST,RequestMethod.GET})
	public String autoTenderTest(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		System.out.println("-------------------自动投标进来了");
 		huifuParams.setVersion("20");											//版本号 Version  30
		huifuParams.setCmdId("AutoTender");								//消息类型 CmdId 此处是InitiativeTender
		huifuParams.setOrdId(StringUtil.getNo());								//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
		huifuParams.setOrdDate(StringUtil.formatDate(new Date(),"yyyyMMdd"));	//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
//		String transamt = df1.format(huifuParams.getTransAmt());
//		System.out.println("交易金额，投标金额==================="+transamt);
//		huifuParams.setTransAmt(transamt);			//交易金额，投标金额
		String BorrowerDetails = "[{&quot;BorrowerCustId&quot;:&quot;"+huifuParams.getBorrowerCustId()+"&quot;,&quot;BorrowerAmt&quot;:&quot;"+huifuParams.getBorrowerAmt()+"&quot;,&quot;BorrowerRate&quot;:&quot;"+huifuParams.getBorrowerRate()+"&quot;,&quot;ProId&quot;:&quot;"+huifuParams.getProId()+"&quot;}]";
		huifuParams.setBorrowerDetails("[{\"BorrowerCustId\":\""+huifuParams.getBorrowerCustId()+"\",\"BorrowerAmt\":\""+huifuParams.getBorrowerAmt()+"\",\"BorrowerRate\":\""+huifuParams.getBorrowerRate()+"\",\"ProId\":\""+huifuParams.getProId()+"\"}]"); // 借款人信息
		if(huifuParams.getIsFreeze().trim().equals("Y")){
			huifuParams.setFreezeOrdId(huifuParams.getOrdId()); // 冻结订单号
		}
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuParams.setRetUrl(basePath+"/huifu/initiativeTender/initiativeTenderRetUrl.action"); // 页面返回URL
		huifuParams.setBgRetUrl(basePath+"/huifu/initiativeTender/initiativeTenderCallBack.action"); // 商户后台应答地址
		String ReqExt = "";
		if(huifuParams.getAcctId() != null && !huifuParams.getAcctId().trim().equals("") && huifuParams.getVocherAmt() != null && !huifuParams.getVocherAmt().trim().equals("")){
//			String vocheramt = df1.format(huifuParams.getVocherAmt());
//			System.out.println("代金券金额==================="+vocheramt);
//			huifuParams.setVocherAmt(vocheramt); // 代金券金额（金额格式必须是###.00）比如 2.00，2.01
			System.out.println("进来了入参扩展域！！！！！！！！！！！！！！！！！！");
			ReqExt = "{&quot;Vocher&quot;:{&quot;AcctId&quot;:&quot;"+huifuParams.getAcctId()+"&quot;,&quot;VocherAmt&quot;:&quot;"+huifuParams.getVocherAmt()+"&quot;}}";
			huifuParams.setReqExt("{\"Vocher\":{\"AcctId\":\""+huifuParams.getAcctId()+"\",\"VocherAmt\":\""+huifuParams.getVocherAmt()+"\"}}"); // 入参扩展域
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()))			// 版本号
		.append(StringUtils.trimToEmpty(huifuParams.getCmdId()))					// 消息类型
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()))				// 商户号
		.append(StringUtils.trimToEmpty(huifuParams.getOrdId()))
		.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()))
		.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()))
		.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()))
		.append(StringUtils.trimToEmpty(huifuParams.getMaxTenderRate()))
		.append(StringUtils.trimToEmpty(huifuParams.getBorrowerDetails()))
		.append(StringUtils.trimToEmpty(huifuParams.getIsFreeze()))
		.append(StringUtils.trimToEmpty(huifuParams.getFreezeOrdId()))
//		.append(StringUtils.trimToEmpty(huifuparam.getRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()))
		.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装绑卡加签字符串原文:========="+plainStr);
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
		request.setAttribute("OrdId", huifuParams.getOrdId());
		request.setAttribute("OrdDate", huifuParams.getOrdDate());
		request.setAttribute("TransAmt", huifuParams.getTransAmt());
		request.setAttribute("UsrCustId", huifuParams.getUsrCustId());
		request.setAttribute("MaxTenderRate", huifuParams.getMaxTenderRate());
		request.setAttribute("BorrowerDetails", BorrowerDetails);
		request.setAttribute("BorrowerCustId", huifuParams.getBorrowerCustId());
		request.setAttribute("BorrowerAmt", huifuParams.getBorrowerAmt());
		request.setAttribute("BorrowerRate", huifuParams.getBorrowerRate());
		request.setAttribute("ProId", huifuParams.getProId());
		request.setAttribute("IsFreeze", huifuParams.getIsFreeze());
		request.setAttribute("FreezeOrdId", huifuParams.getFreezeOrdId());
//		request.setAttribute("RetUrl", huifuparam.getRetUrl());
		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());
//		request.setAttribute("MerPriv", huifuparam.getMerPriv());
		request.setAttribute("ReqExt", ReqExt);
		request.setAttribute("AcctId", huifuParams.getAcctId());
		request.setAttribute("VocherAmt", huifuParams.getVocherAmt());
		request.setAttribute("ChkValue", huifuParams.getChkValue());
		
		UserTender ut = new UserTender();
		ut.setOrderno(huifuParams.getOrdId());								 // 投标订单号
		ut.setAmount(Double.valueOf(huifuParams.getTransAmt()));			// 投标金额
		ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL);			// 投标方式
		ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO);				// 还款完成
		ut.setFeerate(Double.valueOf(huifuParams.getMaxTenderRate()));		// 手续费率（默认0）
		ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT);		// 转账类型
		if(huifuParams.getIsFreeze().trim().equals("N")){
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_NOT_FREEZE);			// 不冻结
		}else if(huifuParams.getIsFreeze().trim().equals("Y")){
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_FREEZE);			// 冻结
		}
		ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL);
		ut.setPaycompany("汇付天下");											// 投标通道公司
		userTenderService.insertSelective(ut);								// 保存部分投标记录
		
		return "huifu/initiativeTender";
  }
	
	/**
	 * 汇付天下-自动投标后台应答地址
	 * @param request
	 * @param response
	 * @param huifuParam
	 * @throws Exception
	 */
	@RequestMapping("/autoTenderCallBack")
	public synchronized void  autoTenderCallBack(HttpServletRequest request, HttpServletResponse response, HuifuParams huifuParam) throws Exception {
		System.out.println("进来了！！！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println("消息类型：CmdId=================================="+huifuParam.getCmdId());
		System.out.println("应答返回码：RespCode=================================="+huifuParam.getRespCode());
		System.out.println("商户用户号：MerCustId=================================="+huifuParam.getMerCustId());
		System.out.println("订单号：OrdId=================================="+huifuParam.getOrdId());
		System.out.println("订单日期：OrdDate=================================="+huifuParam.getOrdDate());
		System.out.println("交易金额：TransAmt=================================="+huifuParam.getTransAmt());
		System.out.println("用户客户号:UsrCustId=================================="+huifuParam.getUsrCustId());
		System.out.println("本平台交易唯一标识:TrxId=================================="+huifuParam.getTrxId());
		System.out.println("是否冻结:IsFreeze=================================="+huifuParam.getIsFreeze());
		System.out.println("冻结订单号:FreezeOrdId=================================="+huifuParam.getFreezeOrdId());
		System.out.println("冻结标识：FreezeTrxId=================================="+huifuParam.getFreezeTrxId());
		System.out.println("商户后台应答地址：BgRetUrl=================================="+huifuParam.getBgRetUrl());
		// 从session中获取当前登录用户基本信息
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
//	
//		if(userBaseAccountInfo == null){
//			userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(huifuParam.getMerPriv())); // 根据id获取用户基本信息
//		}
        request.setCharacterEncoding("UTF-8");
//		BigDecimal baseid = (BigDecimal) (userBaseAccountInfo.getId() == null ? huifuParam.getMerPriv() : userBaseAccountInfo.getId());
        String chkValue = huifuParam.getChkValue();
        System.out.println("自动投标应答获取的签名：=============="+chkValue);
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(huifuParam.getCmdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespCode()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOrdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOrdDate()))
        		.append(StringUtils.trimToEmpty(huifuParam.getTransAmt()))
        		.append(StringUtils.trimToEmpty(huifuParam.getUsrCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getTrxId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getIsFreeze()))
        		.append(StringUtils.trimToEmpty(huifuParam.getFreezeOrdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getFreezeTrxId()))
//        		.append(StringUtils.trimToEmpty(huifuParam.getRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getBgRetUrl()));
//        		.append(StringUtils.trimToEmpty(huifuParam.getMerPriv()));
//        		.append(StringUtils.trimToEmpty(huifuParam.getRespExt()));
        String plainStr = buffer.toString();
        System.out.println("自动投标应答获取的返回参数拼接：=============="+huifuParam.getFreezeTrxId());
        System.out.println("自动投标应答获取的返回参数拼接：=============="+plainStr);
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
    		UserTender ut = new UserTender();
        	if(huifuParam.getRespCode().equals("000")){ // 返回码000成功
        		System.out.println("开始更新数据！！！！！！！！！！！");
        		// 更新投标记录
        		ut.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
        		ut.setFreezetrxid(huifuParam.getFreezeTrxId());				// 冻结标识
        		ut.setOrderno(huifuParam.getOrdId());						// 投标订单号
        		ut.setTendtime(new Date()); 								// 转账完成时间
            	// 更新标的设置
            	updateTenderItem(huifuParam, request);
        		// 更新投资人用户账户表-冻结余额
        		updateInvestorAccount(huifuParam);
        	}else{
        		ut.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
        	}
    		int count = 0;
    		count = userTenderService.updateByOrderNO(ut);  	// 根据投标订单号更新投标记录
    		if(count > 0){
        		System.out.println("更新投标记录成功！！！！！！！！！！！");
                try {
                    if (StringUtils.isNotBlank(huifuParam.getOrdId())) { // 商户实现后台方式的时候，需要在页面上打印 RECV_ORD_ID_和 OrdId
                        PrintWriter out = response.getWriter();
                        out.print("RECV_ORD_ID_".concat(huifuParam.getOrdId()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
    		}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
	}
	
	/**
	 * 更新标的设置，已完成投标金额
	 * @param huifuParam
	 * @throws Exception 
	 */
	private void updateTenderItem(HuifuParams huifuParam, HttpServletRequest request) throws Exception {
		// 修改标的设置表
        String MerPriv = HttpClientHandler.getBase64Decode(huifuParam.getMerPriv());
		System.out.println("商户私有域-----------标的设置表id================"+MerPriv);
		TenderItem item = tenderItemService.findTenderItemById(new BigDecimal(MerPriv));						// 获得标的设置详情
		item.setFinishtamount(Arith.add(item.getFinishtamount(), Double.valueOf(huifuParam.getTransAmt())));	// 已完成投标金额=已完成金额+当前投标金额
		int count = 0;
		count = tenderItemService.update(item);
		if(count > 0){
			System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}

	/**
	 * 更新投资人用户账户表-冻结余额
	 * @param huifuParam
	 */
	private void updateInvestorAccount(HuifuParams huifuParam) {
		UserAccount account = new UserAccount();
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParam.getUsrCustId()); 	// 根据投资人用户客户号查询托管账户
		BigDecimal baseId = userFsAccountInfo.getBaseid(); 																				// 用户baseid
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(baseId); 													// 根据baseid查询用户账户信息
		double balance = userAccount.getBalance();																						// 用户总资产不变
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), Double.valueOf(huifuParam.getTransAmt())); 							// 用户可用余额 = 用户可用余额-投标金额
		double freezeBalance = Arith.add(userAccount.getFreezebalance(), Double.valueOf(huifuParam.getTransAmt())); 					// 用户冻结余额 = 用户冻结余额+投标金额
		account.setBaseid(baseId);																										// baseId
		account.setBalance(balance);																									// 用户总资产
		account.setAvlbalance(avlbalance);																								// 可用余额
		account.setFreezebalance(freezeBalance);																						// 冻结余额
		int count = 0;
		count = userAccountService.updateUseraccount(account); // 根据basid更新用户账户表
		if(count > 0){
			System.out.println("更新投资人用户账户成功！！！！！！！！！！！！！！！！！！！！！！！！");
			System.out.println("账户总资产============================="+account.getBalance());
		}
	}
	
	/**
	 * 保存部分投标记录
	 * @param huifuParam
	 */
	private void saveTenderRecord(HuifuParams huifuParam) {
		UserTender ut = new UserTender();
		ut.setTenderid(new BigDecimal("")); 												// 标的号id
		ut.setOrderno(huifuParam.getOrdId()); 												// 投标订单号
		ut.setUtproperty((short) 1);														// 投标属性-原始投标
		ut.setOutaccountid(new BigDecimal("")); 											// 投资方ID
		ut.setInaccountid(new BigDecimal("")); 												// 借款方ID
		ut.setAmount(Double.valueOf(huifuParam.getTransAmt())); 							// 投标金额
		ut.setIsda(TenderRecord_Constant.ISDA_NO); 											// 是否债转
		ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL); 							// 投标方式,此处为手动投标
		ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO); 								// 还款完成
		ut.setTbegintime(new Date()); 														// 投标开始时间
		ut.setFee(null); 																	// 手续费
		ut.setMediacyfee(null);																// 居间费
		ut.setGuaranteefee(null);															// 担保费
		ut.setRiskguarantyfee(null);														// 风险保证金
		ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT); 					// 转账类型
		ut.setOriginclient(null); 															// 投标设备来源
		ut.setTproperty(null); 																// 标的属性
		ut.setAppointtenderpass(null); 														// 约标码
		ut.setIsfreeze(null); 																// 是否冻结
		ut.setFreezetrxid(null);															// 冻结标识
		ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL); 								// 投标的状态
		ut.setIsblending(TenderRecord_Constant.ISBLENDING_NO);								// 是否系统勾兑
		ut.setIsmanblending(TenderRecord_Constant.ISMANBLENDING_NO);						// 是否人工勾兑
		ut.setPaycompany("汇付天下"); 															// 投标通道公司
		ut.setIsaudit(TenderRecord_Constant.ISAUDIT_NO);									// 是否审核
		ut.setRemark(null);																	// 备注
		userTenderService.insertSelective(ut);
	}
	
}
