package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.ActAward_Constant;
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
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.model.UserTenderPlusLink;
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
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderPlusLinkServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/moneymoremore/userTenderAudit")
public class MMMUserTenderAuditController extends BaseController {
	
	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;

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
	
	/** 投标增益使用关联Service */
	@Autowired
	UserTenderPlusLinkServiceI userTenderPlusLinkService;
	
	/** 用户红包service */
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	/** 用户使用券service */
	@Autowired
	UserInterestRateCouponServiceI userInterestRateCouponService;
	
	/** 投标放款记录service */
	@Autowired
	UserMakeALoanServiceI userMakeALoanService;
	
	/**
	 * 乾多多投标审核请求
	 * @Title: userBid
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @return void    返回类型
	 */
	@RequestMapping(value = "/bidAudit",method = {RequestMethod.POST,RequestMethod.GET})
	public void bidAudit(HttpServletRequest request,HttpServletResponse response) {
		
		String LoanNoList = "LN26720911702251522348175266"; // 乾多多流水号列表
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore; // 平台乾多多标识
		Integer AuditType = 1; // 审核类型-1.转账通过，2.转账退回，3.二次分配同意，4.二次分配不同意，5.提现通过，6.提现退回
		String Remark1 = ""; // 自定义备注
		String Remark2 = ""; // 自定义备注
		String Remark3 = ""; // 自定义备注
		String ReturnURL = StringUtil.getBasePath(request)+"/moneymoremore/userTenderAudit/bidAuditReturnUrl.action"; // 页面返回网址
		String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/userTenderAudit/bidAuditCallBack.action"; // 后台通知网址
  		 
		/*LoanNoList + PlatformMoneymoremore + AuditType + 
		  Remark1 + Remark2 + Remark3 + ReturnURL + NotifyURL*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(LoanNoList))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(AuditType)
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
 		String plainStr = buffer.toString();
        System.out.println("投标转账提交参数原文：=============="+plainStr);
 		//私钥签名
 		String privateResult = "";
 		
 		RsaHelper rsa = RsaHelper.getInstance();
 		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
 		
		request.setAttribute("LoanNoList", LoanNoList);
		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
		request.setAttribute("AuditType", AuditType);
		request.setAttribute("Remark1", Remark1);
		request.setAttribute("Remark2", Remark2);
		request.setAttribute("Remark3", Remark3);
		request.setAttribute("ReturnURL", ReturnURL);
		request.setAttribute("NotifyURL", NotifyURL);
		request.setAttribute("SignInfo", privateResult);
		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/UserTenderAudit/UserTenderAudit.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
 			e.printStackTrace();
		}
	}  
	
	@RequestMapping(value= "/bidAuditCallBack")
	public void bidAuditCallBack(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
 		RsaHelper rsa = RsaHelper.getInstance();
		String LoanNoList = request.getParameter("LoanNoList"); // 乾多多流水号列表
		System.out.println("乾多多流水号列表=========="+LoanNoList);
		String LoanNoListFail = request.getParameter("LoanNoListFail"); // 有问题的的乾多多流水号列表
		System.out.println("有问题的的乾多多流水号列表=========="+LoanNoListFail);
		String PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore"); // 平台乾多多标识
		String AuditType = request.getParameter("AuditType"); // 审核类型-1.转账通过，2.转账退回，3.二次分配同意，4.二次分配不同意，5.提现通过，6.提现退回
		System.out.println("审核类型======="+AuditType);
		String Remark1 = request.getParameter("Remark1"); // 自定义备注
		System.out.println("放款订单号=========="+Remark1);
		String Remark2 = request.getParameter("Remark2"); // 自定义备注
		String Remark3 = request.getParameter("Remark3"); // 自定义备注
		String ResultCode = request.getParameter("ResultCode"); // 返回码（88表示成功）
		System.out.println("返回码======="+ResultCode);
		String Message = request.getParameter("Message"); // 返回信息
		System.out.println("返回信息======="+Message);
		String ReturnTimes = request.getParameter("ReturnTimes"); // 返回次数
		System.out.println("返回次数======="+ReturnTimes);
		String SignInfo = request.getParameter("SignInfo"); // 签名信息
		System.out.println(SignInfo);
		
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(LoanNoList))
				.append(StringUtils.trimToEmpty(LoanNoListFail))
        		.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
        		.append(StringUtils.trimToEmpty(AuditType))
        		.append(StringUtils.trimToEmpty(Remark1))
        		.append(StringUtils.trimToEmpty(Remark2))
        		.append(StringUtils.trimToEmpty(Remark3))
        		.append(StringUtils.trimToEmpty(ResultCode));
        String plainStr = buffer.toString();
        boolean flag = false;
        try {
     		flag = rsa.verifySignature(SignInfo, plainStr, RsaHelper.publicString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
        	if(ResultCode.equals("88")){ // 返回码88成功
        		UserMakeALoan userMakeALoan = userMakeALoanService.selectByMLoanOrderNo(Remark1);
        		String[] arr = LoanNoList.split(",");
        		for(String loanno : arr){
        			UserTender usertender = userTenderService.selectByLoanNo(loanno);
    				TenderItem tenderItem = tenderItemService.findTenderItemById(usertender.getTenderid());
//    				if(tenderItem.getValuedate() == null){
//    					// 更新标的设置表的结标日
//    					tenderItem.setValuedate(new Date());	// 结标日
//    					tenderItem.setTstatus((short) 9);
//    					int num = 0;
//    					num = tenderItemService.update(tenderItem);
//    					if(num > 0){
//    						System.out.println("更新标的设置表结标日成功！！！！！！！！！！！！");
//    					}
//    				}else{
//    					calculationValueDate(tenderItem, usertender);
//    				}
    				List<UserTenderPlusLink> userTenderPlusLink = userTenderPlusLinkService.findUserTenderPlusLinkByUtId(usertender.getId()); // 查询有无使用增益
    				if(userTenderPlusLink != null){
    					for(UserTenderPlusLink plusLink : userTenderPlusLink){
    						if(plusLink.getReid() != null){
    							UserRedEnvelope userRedEnvelope = userRedEnvelopeService.findUserRedEnvelopeById(plusLink.getReid());
    							userRedEnvelope.setIsuse(ActAward_Constant.AWARD_ALUSE);
    							userRedEnvelopeService.updateRedEnvById(userRedEnvelope);
    						}
    						if(plusLink.getIcid() != null){
    							UserInterestRateCoupon userInterestRateCoupon = userInterestRateCouponService.findUserInterestRateCouponById(plusLink.getIcid());
    							userInterestRateCoupon.setIsuse(ActAward_Constant.AWARD_ALUSE);
    							userInterestRateCouponService.updataCouponById(userInterestRateCoupon);
    						}
    					}
    				}
    				if(userMakeALoan.getMalstatus()==0){
    					updateInvestorAccount(usertender);
    					updateBorrowerAccount(usertender);
    					loanapp loanapp = loanappService.selectByPrimaryKey(tenderItem.getLoanappid());
    					loanapp.setReceiptsamount(Arith.add(loanapp.getReceiptsamount(), usertender.getAmount())); // 已入账借款金额
    					loanapp.setAppstatus((short) 5); // 0审核中 1成功 2失败 3投标中 4流标 5还款中 6已发布
    					int count = 0;
    					count = loanappService.updateByPrimaryKeySelective(loanapp);
    					if(count > 0){
    						System.out.println("更新借款申请已入账借款金额成功！！！！！！！！！！！！！！！！！！！！！！！！！！");
    					}
    					if(usertender.getMediacyfee() != 0){ // 扣除居间费
    						deductionBorrowerMediacyfee(usertender);
    					}
    					if(usertender.getGuaranteefee() != 0){ // 扣除担保费
    						deductionBorrowerGuaranteefee(usertender);
    					}
    					if(usertender.getRiskguarantyfee() != 0){ // 扣除风险保证金
    						deductionBorrowerRiskguarantyfee(usertender);
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
    				usertender.setTstatus(TenderRecord_Constant.TSTATUS_COMPLETED); // 投标状态
    				usertender.setIsfreeze(TenderRecord_Constant.ISFREEZE_UNFREEZE); // 已解冻
    				usertender.setIsrepayend(TenderRecord_Constant.ISREPAYEND_REPAYMENTING); // 还款中
    				int rows = 0;
    				rows = userTenderService.updateByOrderNO(usertender);
    				if(rows > 0){
    					System.out.println("更新投标记录成功");
    				}
//    				System.out.println("===========生成具体还款计划开始==============");
//    				AutoGenerateRepayMentUtil.AutoGenerateRepayMents(usertender.getTenderid());
//    				System.out.println("===========生成具体还款计划结束==============");
//    				System.out.println("===========生成分期还款计划开始==============");
//    				AutoGenerateRepayMentUtil.AutoGenerateDividedPayments(usertender.getTenderid());
//    				System.out.println("===========生成分期还款计划结束==============");
    				MediacyFeeRecord mediacyFeeRecord = mediacyFeeRecordService.selectByUtOrderNo(usertender.getOrderno());
    				if(mediacyFeeRecord == null){ // 避免重复新增记录
    					if(usertender.getMediacyfee() != 0){
    						System.out.println("===========生成标的居间费记录开始==============");
    						insertMediacyFeeRecord(usertender);
    						System.out.println("===========生成标的居间费记录结束==============");
    					}
    				}
    				GuaranteeFeeRecord guaranteeFeeRecord = guaranteeFeeRecordService.selectByUtOrderNo(usertender.getOrderno());
    				if(guaranteeFeeRecord == null){
    					if(usertender.getGuaranteefee() != 0){
    						System.out.println("===========生成标的担保费记录开始==============");
    						insertGuaranteeFeeRecord(usertender);
    						System.out.println("===========生成标的担保费记录结束==============");
    					}
    				}
    				RiskGuarantyFeeRecord riskGuarantyFeeRecord = riskGuarantyFeeRecordService.selectByUtOrderNo(usertender.getOrderno());
    				if(riskGuarantyFeeRecord == null){
    					if(usertender.getRiskguarantyfee() != 0){
    						System.out.println("===========生成标的风险保证金记录开始==============");
    						insertRiskGuarantyFeeRecord(usertender);
    						System.out.println("===========生成标的风险保证金记录结束==============");
    					}
    				}
        		}
        	}
        	response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			try {
				// 后台通知结果后，将判断http响应码为200且页面上含有“SUCCESS”字符串方为通知成功
				response.getWriter().write("SUCCESS");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
	}
	
	@RequestMapping(value= "/bidAuditReturnUrl")
	public void bidAuditReturnUrl(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String ResultCode = request.getParameter("ResultCode");
		System.out.println("返回码="+ResultCode);
		String Message = URLDecoder.decode(request.getParameter("Message"), "UTF-8");
		request.setAttribute("Message", Message);
		request.getRequestDispatcher("/WEB-INF/MMMPages/UserTenderAudit/UserTenderAudit_Success.jsp").forward(request, response);
	}
	
	
	/**
	 * 更新投资人用户账户表
	 * @param investor-投资人客户号
	 * @param transAmt-转账金额
	 */
	private void updateInvestorAccount(UserTender usertender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(usertender.getOutaccountid()); 							// 根据baseid查询投资人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), usertender.getAmount());												// 用户新总资产=用户原总资产-转账金额
		double freezebalance = Arith.sub(userAccount.getFreezebalance(), usertender.getAmount());									// 用户冻结余额=用户原冻结余额-转账金额
		userAccount.setBalance(balance);																							// 用户总资产
		userAccount.setFreezebalance(freezebalance);																				// 冻结余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新投资人用户账户表
		if(count > 0){
			System.out.println("更新投资人用户账户表成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			insertInvestorAccInExRecord(usertender.getAmount(), userAccount);
		}
	}
	
	/**
	 * 新增投资人账户收支记录-投标
	 */
	private void insertInvestorAccInExRecord(Double amount, UserAccount userAccount){
		AccInExRecord record = new AccInExRecord();
		record.setBaseid(userAccount.getBaseid());							// 投资人baseId
		record.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		record.setType((short) 5);											// 业务类型-投标
		record.setInamount((double) 0);										// 收入
		record.setOutamount(amount);		// 支出
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
	private void updateBorrowerAccount(UserTender usertender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(usertender.getInaccountid()); 						// 根据baseid查询借款人用户账户信息
		double balance = Arith.add(userAccount.getBalance(), usertender.getAmount());											// 用户新总资产=用户原总资产+转账金额
		double avlbalance = Arith.add(userAccount.getAvlbalance(), usertender.getAmount());										// 用户新可用余额=用户原可用余额+转账金额
		userAccount.setBalance(balance);																						// 用户总资产
		userAccount.setAvlbalance(avlbalance);																					// 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if(count > 0){
			System.out.println("更新借款人用户账户表成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			insertLoanAccInExRecord(usertender.getAmount(), userAccount);
		}
	}
	
	/**
	 * 扣除借款人居间费
	 * @param huifuParam
	 */
	private void deductionBorrowerMediacyfee(UserTender userTender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userTender.getInaccountid()); 							// 根据baseid查询借款人用户账户信息
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
	private void deductionBorrowerGuaranteefee(UserTender userTender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userTender.getInaccountid()); 							// 根据baseid查询借款人用户账户信息
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
	private void deductionBorrowerRiskguarantyfee(UserTender userTender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userTender.getInaccountid()); 							// 根据baseid查询借款人用户账户信息
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
	private void insertLoanAccInExRecord(Double amount, UserAccount userAccount){
		AccInExRecord record = new AccInExRecord();
		record.setBaseid(userAccount.getBaseid());							// 借款人baseId
		record.setAieorderno(StringUtil.getNo());							// 收支记录流水号
		record.setType((short) 5);											// 业务类型-投标
		record.setInamount(amount);											// 收入
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

	/***
	 * 计算起息日
	 * @param tenderItem
	 * @param userTender
	 * @throws Exception
	 */
	private void calculationValueDate(TenderItem tenderItem, UserTender userTender) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date valueDate = tenderItem.getValuedate(); // 结标日
		Date formatValueDate = sdf.parse(sdf.format(tenderItem.getValuedate())); // 获取结标日，格式化为时分秒，再转换为Date类型
		if(tenderItem.getValuerule() == 2){ // 结标日次日
			valueDate = sf.parse(sf.format(valueDate.getTime()+24*3600*1000));
		}else if(tenderItem.getValuerule() == 3){ // 结标日固定时间之后
			Date valuePoint = sdf.parse(tenderItem.getValuepoint()); // 固定时间点
			if(Arith.sub(formatValueDate.getTime(), valuePoint.getTime()) > 0){
				valueDate = sf.parse(sf.format(valueDate.getTime()+24*3600*1000));
			}
		}
		userTender.setValuedate(valueDate);
		int count = 0;
		count = userTenderService.updateValueDateByTenderId(userTender);
		if(count > 0){
			System.out.println("插入起息日成功！！！！！！！！！！！！！！！！！！");
		}
	}
}
