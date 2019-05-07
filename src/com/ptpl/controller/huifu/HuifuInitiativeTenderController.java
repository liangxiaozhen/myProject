package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
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
import com.ptpl.model.UserMakeALoan;
import com.ptpl.model.UserTender;
import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserTenderPlusLinkServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 汇付天下接口-主动投标
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/huifu/initiativeTender")
public class HuifuInitiativeTenderController extends BaseController {
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;

	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 投标放款记录service */
	@Autowired
	UserMakeALoanServiceI userMakeALoanService;
	
	/** 投标增益使用关联service */
	@Autowired
	UserTenderPlusLinkServiceI userTenderPlusLinkService;
	
   /** 
	* @Title: test 
	* @Description: TODO(汇付测试 无用) 
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author zhenglm
	* @throws
	 */
	@RequestMapping("/test")
	public String test(){
		return "huifuhuizong/InitiativeTender";
	}
	
	/**
	 * 主动投标测试
	 * TODO(汇付测试 无用) 
	 * @param request
	 * @param response
	 * @param huifuParams
	 * @return
	 */
	@RequestMapping(value="/initiativeTenderTest",method={RequestMethod.POST,RequestMethod.GET})
	public String initiativeTenderTest(HuifuParams huifuParams){
		System.out.println("-------------------主动投标进来了---------------");
 		huifuParams.setVersion("20");											//版本号 Version  30
		huifuParams.setCmdId("InitiativeTender");								//消息类型 CmdId 此处是InitiativeTender
		huifuParams.setOrdId(StringUtil.getNo());								//订单号 OrdId   必须 由商户的系统生成，必须保证唯一，请使用纯数字
		Date tenderTime = new Date();
		huifuParams.setOrdDate(StringUtil.formatDate(tenderTime,"yyyyMMdd"));	//订单日期 OrdDate 必须 格式为 YYYYMMDD，例如：20130307
		String transamt = df1.format(Double.valueOf(huifuParams.getTransAmt()));
		System.out.println("交易金额，投标金额==================="+transamt);
		huifuParams.setTransAmt(transamt);										//交易金额，投标金额
		huifuParams.setBorrowerDetails("[{\"BorrowerCustId\":\""+huifuParams.getBorrowerCustId()+"\",\"BorrowerAmt\":\""+huifuParams.getBorrowerAmt()+"\",\"BorrowerRate\":\""+huifuParams.getBorrowerRate()+"\",\"ProId\":\""+huifuParams.getProId()+"\"}]"); // 借款人信息
		if(huifuParams.getIsFreeze().trim().equals("Y")){
			huifuParams.setFreezeOrdId(huifuParams.getOrdId()); // 冻结订单号
		}
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
//		huifuParams.setRetUrl(basePath+"/huifu/initiativeTender/initiativeTenderRetUrl.action"); // 页面返回URL
		huifuParams.setBgRetUrl(basePath+"/huifu/initiativeTender/initiativeTenderCallBack.action"); // 商户后台应答地址
		if(huifuParams.getAcctId() != null && !huifuParams.getAcctId().trim().equals("") && huifuParams.getVocherAmt() != null && !huifuParams.getVocherAmt().trim().equals("")){
			String vocheramt = df1.format(Double.valueOf(huifuParams.getVocherAmt()));
			System.out.println("代金券金额==================="+vocheramt);
			huifuParams.setVocherAmt(vocheramt); // 代金券金额（金额格式必须是###.00）比如 2.00，2.01
			System.out.println("进来了入参扩展域！！！！！！！！！！！！！！！！！！");
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
		.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()))
		.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装主动投标加签字符串原文:========="+plainStr);
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
		request.setAttribute("BorrowerDetails", huifuParams.getBorrowerDetails().replaceAll("\"", "&quot;"));
		request.setAttribute("BorrowerCustId", huifuParams.getBorrowerCustId());
		request.setAttribute("BorrowerAmt", huifuParams.getBorrowerAmt());
		request.setAttribute("BorrowerRate", huifuParams.getBorrowerRate());
		request.setAttribute("ProId", huifuParams.getProId());
		request.setAttribute("IsFreeze", huifuParams.getIsFreeze());
		request.setAttribute("FreezeOrdId", huifuParams.getFreezeOrdId());
		request.setAttribute("RetUrl", huifuParams.getRetUrl());
		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());
		request.setAttribute("MerPriv", huifuParams.getMerPriv());
		request.setAttribute("ReqExt", huifuParams.getReqExt().replaceAll("\"", "&quot;"));
		request.setAttribute("AcctId", huifuParams.getAcctId());
		request.setAttribute("VocherAmt", huifuParams.getVocherAmt());
		request.setAttribute("ChkValue", huifuParams.getChkValue());
		
		UserTender ut = new UserTender();
		ut.setOrderno(huifuParams.getOrdId());								// 投标订单号
		ut.setAmount(Double.valueOf(huifuParams.getTransAmt()));			// 投标金额
		ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL);			// 投标方式-手动
		ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO);				// 还款完成-否
		ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT);		// 转账类型
		if(huifuParams.getIsFreeze().trim().equals("N")){
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_NOT_FREEZE);		// 是否冻结
		}else if(huifuParams.getIsFreeze().trim().equals("Y")){
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_FREEZE);			// 冻结
		}
		ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL);
		ut.setPaycompany("汇付天下");											// 投标通道公司
		UserFsAccountInfo outaccountid = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getUsrCustId());
		UserFsAccountInfo inaccountid = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParams.getBorrowerCustId());
		ut.setOutaccountid(outaccountid.getBaseid());/** 投资方ID */
		ut.setInaccountid(inaccountid.getBaseid());/** 借款方ID */
		ut.setTendertime(new Date());
		ut.setTenderid(new BigDecimal(889));/** 标的号ID */
		ut.setRemark("889投标嘉明测试还款2.0测试10笔");
		ut.setIsda(TenderRecord_Constant.ISDA_NO);							// 是否债转-未债转
		ut.setTbegintime(new Date());
		ut.setOriginclient(TenderRecord_Constant.ORIGINCLIENT_PC);
		ut.setIsblending(TenderRecord_Constant.ISBLENDING_NO); // 是否系统勾兑
		ut.setIsmanblending(TenderRecord_Constant.ISMANBLENDING_NO); // 是否人工勾兑
		ut.setIsaudit(TenderRecord_Constant.ISAUDIT_NO); //  是否审核
		userTenderService.insertSelective(ut);								// 保存部分投标记录
		return "huifu/initiativeTender";
  }
	
	/**
	 * 主动投标后台应答地址
	 * 1.更新投标记录
	 * 2.更新标的设置
	 * 3.更新投资人账户
	 * 4.新增投资人增益使用关联
	 * @param request
	 * @param response
	 * @param huifuParam
	 * @throws Exception
	 */
	@RequestMapping("/initiativeTenderCallBack")
	public synchronized void initiativeTenderCallBack(HttpServletRequest request, HttpServletResponse response, HuifuParams huifuParam) throws Exception {
		System.out.println("进入主动投标后台应答！！！！！！！！！！！！！！！！！！！！！！！！！");
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
        request.setCharacterEncoding("UTF-8");
        String chkValue = huifuParam.getChkValue();
        System.out.println("主动投标应答获取的签名：=============="+chkValue);
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
        		.append(StringUtils.trimToEmpty(huifuParam.getRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getBgRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerPriv()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespExt()));
        String plainStr = buffer.toString();
        System.out.println("主动投标应答获取的返回参数拼接：=============="+plainStr);
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
        	UserTender userTender = userTenderService.findUserTenderByOrderno(huifuParam.getOrdId());
        	if(huifuParam.getRespCode().equals("000")){ // 返回码000成功
        		System.out.println("开始更新数据！！！！！！！！！！！");
            	if(userTender.getTstatus()==0){
                	// 更新标的设置
                	updateTenderItem(huifuParam, request);
            		// 更新投资人用户账户表-冻结余额
            		updateInvestorAccount(huifuParam);
            	}
        		// 更新投标记录
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
        		userTender.setFreezetrxid(huifuParam.getFreezeTrxId());				// 冻结标识
        		userTender.setTendtime(new Date()); 								// 转账完成时间
        	}else{
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
        	}
    		int count = 0;
    		count = userTenderService.updateByOrderNO(userTender);  	// 根据投标订单号更新投标记录
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
		if(Arith.sub(item.getTamount(), item.getFinishtamount())<=0){
			item.setTstatus((short) 5); // 满标
		}
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
	 * 新增投标增益使用关联
	 * TODO
	 */
	private void insertUserTenderPlusLink(){
		UserTenderPlusLink plusLink = new UserTenderPlusLink();
		plusLink.setUtid(null);					// 投标ID
		plusLink.setIcid(null);					// 加息券ID
		plusLink.setReid(null);					// 红包ID
		int count = 0;
		count = userTenderPlusLinkService.insertSelective(plusLink);
		if(count > 0){
			System.out.println("新增投标增益使用关联成功！！！！！！！！！");
		}
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
	public Map<String, String> gethfloansParams(HuifuParams tenderParams, TenderItem tenderItem) throws Exception {
		System.out.println("放款请求进来了！================+++++++++++++++++++++++++");
		HuifuParams huifuparam = new HuifuParams();
		huifuparam.setVersion("20"); 												// 版本号
		huifuparam.setCmdId("Loans"); 												// 消息类型
		huifuparam.setOrdId(StringUtil.getNo());									// 放款订单号
		Date loansTime = new Date();
		huifuparam.setOrdDate(StringUtil.formatDate(loansTime, "yyyyMMdd")); 		// 放款订单日期
		System.out.println("出账客户号-投资人客户号================="+tenderParams.getUsrCustId());
		huifuparam.setOutCustId(tenderParams.getUsrCustId()); 						// 出账客户号-投资人客户号
		System.out.println("交易金额-放款金额================="+tenderParams.getTransAmt());
		huifuparam.setTransAmt(tenderParams.getTransAmt()); 						// 交易金额-放款金额
		UserTender userTender = userTenderService.findUserTenderByOrderno(tenderParams.getOrdId());
		System.out.println("扣款手续费================="+userTender.getFee());
		huifuparam.setFee(df1.format(userTender.getFee())); 						// 扣款手续费 
		System.out.println("投标订单号================="+tenderParams.getOrdId());
		huifuparam.setSubOrdId(tenderParams.getOrdId()); 							// 投标订单号
		System.out.println("投标订单日期================="+tenderParams.getOrdDate());
		huifuparam.setSubOrdDate(tenderParams.getOrdDate()); 						// 投标订单日期
		BigDecimal baseid = userTender.getInaccountid();
		System.out.println("入账客户用户ID-借款人用户ID================="+baseid);
		System.out.println("用户托管账户信息service=============================================="+userFsAccountInfoService);
		UserFsAccountInfo userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(baseid);
//		UserFsAccountInfo userFsAccount = SpringContextHolder.getBean(UserFsAccountInfoServiceI.class).findUserFsAccountInfoByBaseId(baseid);
		huifuparam.setInCustId(userFsAccount.getUsrcustid()); 						// 入账客户号-借款人客户号
		if(userTender.getFee() != null){ // 判断是否收取手续费
			System.out.println("收取手续费！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			huifuparam.setDivCustId("6000060004166478");							// 分账商户号
			huifuparam.setDivAcctId("MDT000001");									// 分账账户号
			huifuparam.setDivDetails("[{\"DivCustId\":\""+huifuparam.getDivCustId()+"\",\"DivAcctId\":\""+huifuparam.getDivAcctId()+"\",\"DivAmt\":\""+df1.format(userTender.getMediacyfee())+"\"},{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\"MDT000001\",\"DivAmt\":\"2.00\"}]");
			huifuparam.setDivAmt(huifuparam.getFee()); 								// 分账金额
			huifuparam.setFeeObjFlag("I");											// 手续费收取对象标志-I--向入款客户号 InCustId 收取
		}
		huifuparam.setIsDefault("N");												// 是否默认 Y--默认添加资金池:这部分资金需要商户调用商户代取现接口，帮助用户做后台取现动作
																					// 		 N--不默认不添加资金池:这部分资金用户可以自己取现
		huifuparam.setIsUnFreeze("Y");												// 是否解冻
		huifuparam.setUnFreezeOrdId(StringUtil.getNo());							// 解冻订单号
		huifuparam.setFreezeTrxId(tenderParams.getFreezeTrxId());					// 冻结标识
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuparam.setBgRetUrl(basePath+"/huifu/loans/loansCallBack.action"); 		// 商户后台应答地址
		huifuparam.setReqExt("{\"ProId\":\""+tenderItem.getTno()+"\"}");		// 入参扩展域
		huifuparam.setProId(tenderItem.getTno());								// 项目ID
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
				.append(StringUtils.trimToEmpty(huifuparam.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装放款加签字符串原文:========="+plainStr);
		inserUserMakeALoan(huifuparam, userTender, loansTime);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			params.put("ChkValue", ChkValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	/**
	 * 新增投标放款记录
	 * @param huifuParams
	 * @param userTender
	 * @param tenderItem
	 * @param loansTime
	 */
	private void inserUserMakeALoan(HuifuParams huifuParams, UserTender userTender, Date loansTime){
		UserMakeALoan umal = new UserMakeALoan();
		umal.setTenderid(userTender.getTenderid()); 									// 标的号ID
		umal.setOrderno(userTender.getOrderno()); 										// 投标订单号
		umal.setMloanorderno(huifuParams.getOrdId()); 									// 放款订单号 
		umal.setOutaccountid(userTender.getOutaccountid()); 							// 投资方ID
		umal.setInaccountid(userTender.getInaccountid()); 								// 借款方ID
		umal.setAmount(Double.valueOf(huifuParams.getTransAmt())); 						// 放款金额
		umal.setMalbegintime(loansTime); 												// 放款开始时间
		umal.setFee(userTender.getFee()); 												// 手续费（默认0）
		umal.setMediacyfee(userTender.getMediacyfee()); 								// 居间费
		umal.setGuaranteefee(userTender.getGuaranteefee()); 							// 担保费
		umal.setRiskguarantyfee(userTender.getRiskguarantyfee()); 						// 风险保证金
		umal.setIsthaw((short) 1); 														// 是否解冻（0.否，1.是）
		umal.setUnfreezeordid(huifuParams.getUnFreezeOrdId()); 							// 解冻订单号
		umal.setFreezetrxid(huifuParams.getFreezeTrxId()); 								// 冻结标识
		umal.setMalstatus((short) 0); 													// 放款的状态（0.失败，1.成功） 
		umal.setIsblending((short) 0);													// 是否系统勾兑（0.未勾兑，1.已勾兑）
		umal.setIsmanblending((short) 0); 												// 是否人工勾兑（0.未勾兑，1.已勾兑）
		umal.setPaycompany("汇付天下"); 													// 放款通道公司
		umal.setIsaudit((short) 0); 													// 是否审核
		umal.setRemark("及时投标放款测试"); 													// 备注
		int count = 0;
		count = userMakeALoanService.insertSelective(umal);
		if(count > 0){
			System.out.println("及时投标放款成功！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 主动投标页面返回url
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/initiativeTenderRetUrl")
	public void initiativeTenderRetUrl(HttpServletRequest request, HttpServletResponse response, HuifuParams huifuParam) throws ServletException, IOException{
		String RespCode = huifuParam.getRespCode();
		String RespDesc = URLDecoder.decode(huifuParam.getRespDesc(), "UTF-8");
		request.setAttribute("RespCode", RespCode);
		request.setAttribute("RespDesc", RespDesc);
		request.getRequestDispatcher("/WEB-INF/pages/InitiativeTender/InitiativeTender_Success.jsp").forward(request, response);
	}

}
