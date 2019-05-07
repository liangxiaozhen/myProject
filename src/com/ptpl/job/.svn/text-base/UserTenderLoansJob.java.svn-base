package com.ptpl.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.huishang.FileHttpDownload;
import com.ptpl.controller.huishang.model.FinTranResult;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.BacthFileRecord;
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
import com.ptpl.service.BacthFileRecordServiceI;
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
import com.ptpl.web.util.StringUtil;

/**
 * P2P产品融资扣款文件接口
 * @ClassName: UserTenderLoansJob
 * @Description: TODO(P2P产品融资扣款文件接口)
 * @author zhenglm
 * @date 2017年4月24日 下午4:41:50
 *
 */
public class UserTenderLoansJob extends QuartzJobBean {
	
	@Autowired
	@Qualifier("bacthFileRecordService")
    private BacthFileRecordServiceI bacthFileRecordService; // 批量文件记录表
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountServiceI userAccountService; // 用户账户表

	@Autowired
	@Qualifier("accInExRecordService")
	private AccInExRecordServiceI accInExRecordService; // 账户收支记录表
	
	@Autowired
	@Qualifier("accInExRecordService")
	private UserTenderServiceI userTenderService; // 投标记录表
	
	@Autowired
	@Qualifier("mediacyFeeRecordService")
	private MediacyFeeRecordServiceI mediacyFeeRecordService; // 标的居间费记录表
	
	@Autowired
	@Qualifier("guaranteeFeeRecordService")
	private GuaranteeFeeRecordServiceI guaranteeFeeRecordService; // 标的担保费记录表
	
	@Autowired
	@Qualifier("riskGuarantyFeeRecordService")
	private RiskGuarantyFeeRecordServiceI riskGuarantyFeeRecordService; // 标的风险保证金记录表
	
	@Autowired
	@Qualifier("mediacyFeeService")
	private MediacyFeeServiceI mediacyFeeService; // 标的居间费设置表
	
	@Autowired
	@Qualifier("guaranteeFeeService")
	private GuaranteeFeeServiceI guaranteeFeeService; // 标的担保费设置表
	
	@Autowired
	@Qualifier("riskGuarantyMoneyService")
	private RiskGuarantyMoneyServiceI riskGuarantyMoneyService; // 标的风险保证金设置表
	
	@Autowired
	@Qualifier("loanappService")
	private loanappServiceI loanappService; // 借款申请记录表
	
	@Autowired
	@Qualifier("tenderItemService")
	private TenderItemServiceI tenderItemService; // 标的设置表
	
	@Autowired
	@Qualifier("userTenderPlusLinkService")
	private UserTenderPlusLinkServiceI userTenderPlusLinkService; // 投标增益使用关联表
	
	@Autowired
	@Qualifier("userRedEnvelopeService")
	private UserRedEnvelopeServiceI userRedEnvelopeService; // 用户红包表
	
	@Autowired
	@Qualifier("userInterestRateCouponService")
	private UserInterestRateCouponServiceI userInterestRateCouponService; // 用户使用券表
	
	@Autowired
	@Qualifier("userMakeALoanService")
	private UserMakeALoanServiceI userMakeALoanService; // 投标放款记录表

	@Override
	protected void executeInternal(JobExecutionContext job) throws JobExecutionException {
		//从批量文件记录表中查找出已成功发送，没有处理的记录
		BacthFileRecord bfr = new BacthFileRecord();
		bfr.setCoinstCode("800114");//平台编号
		bfr.setFileType((short) 3);//业务文件类型  1.开户   2.红包转账  3.融资扣款
		bfr.setSendResult((short) 1);//发送结果（是否成功）   0.失败    1.成功   
		bfr.setIsDealResult((short) 0);//是否已处理结果文件     0.否   1.是
		List<BacthFileRecord> bfrList = bacthFileRecordService.getBacthFileRecords(bfr);
		for(BacthFileRecord r : bfrList){
			try {
				String return_code = new FileHttpDownload().testHttDownload(r.getGetFileName(), "20160510");
				if(return_code.equals("0000")){
//					String filePath = new FileHttpDownload().httpDownloadforTar(r.getGetFileName(),r);
					FileInputStream fis = new FileInputStream(HSignUtil.FILEUPLOAD+HSignUtil.FINTRAN+File.separator+StringUtil.formatDate(new Date(), "yyyyMMdd")+File.separator+r.getGetFileName());
					BufferedReader br = new BufferedReader(new InputStreamReader(fis, "GBK"));
					String line = null;
					while ((line = br.readLine()) != null) {
						FinTranResult ft = readRedResult(line);
						if (!"00".equals(ft.getRspcode())) {
							System.out.println("处理失败");
							return;
						}
						saveLoansRecord(ft.getSerialno().trim());
					}
					// 关闭流
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveLoansRecord(String utorderno){
		List<UserMakeALoan> userMakeALoan = userMakeALoanService.selectByOrderNo(utorderno);
		for(UserMakeALoan umal : userMakeALoan){
			UserTender usertender = userTenderService.findUserTenderByOrderno(utorderno);
			TenderItem tenderItem = tenderItemService.findTenderItemById(usertender.getTenderid());
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
			if(umal.getMalstatus()==0){
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
			umal.setMalendtime(new Date()); 				// 放款完成时间
			umal.setMalstatus((short) 1); 					// 放款的状态（0.失败，1.成功） 
			umal.setIsthaw((short) 1);						// 是否解冻（0.否，1.是）
			int number = 0;
			number = userMakeALoanService.updateByMLoanOrderNoSelective(umal);
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

	private static FinTranResult readRedResult(String line) throws IOException {
		FinTranResult ft = new FinTranResult();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");

		byte[] data = line.getBytes("GBK");
		byte[] BATCH = new byte[6];
		System.arraycopy(data, 0, BATCH, 0, 6);
		String batch = new String(BATCH);
		ft.setBatch(batch);
		System.out.println("批次号（6）：" + batch);

		byte[] TYPE = new byte[3];
		System.arraycopy(data, 6, TYPE, 0, 3);
		String type = new String(TYPE);
		ft.setType(type);
		System.out.println("业务类别（3）：" + type);

		byte[] DATE = new byte[8];
		System.arraycopy(data, 9, DATE, 0, 8);
		String date = new String(DATE);
		try {
			ft.setDate(sf1.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("日期（8）：" + date);

		byte[] RSPCODE = new byte[2];
		System.arraycopy(data, 17, RSPCODE, 0, 2);
		String rspcode = new String(RSPCODE);
		ft.setRspcode(rspcode);
		System.out.println("处理响应码（2）：" + rspcode);

		byte[] CARDNBRO = new byte[19];
		System.arraycopy(data, 19, CARDNBRO, 0, 19);
		String cardnnbro = new String(CARDNBRO);
		ft.setCardnbro(cardnnbro);
		System.out.println("扣款电子账号（19）：" + cardnnbro);

		byte[] AMOUNT = new byte[12];
		System.arraycopy(data, 38, AMOUNT, 0, 12);
		String str = new String(AMOUNT);
		Double amount = new Double(str.substring(0, 10) + "." + str.substring(10));
		ft.setAmount(amount);
		System.out.println("扣账（本金）金额（12）：" + amount);

		byte[] INTAMT = new byte[12];
		System.arraycopy(data, 50, INTAMT, 0, 12);
		String str1 = new String(INTAMT);
		Double intamt = new Double(str1.substring(0, 10) + "." + str1.substring(10));
		ft.setIntamt(Double.valueOf(intamt));
		System.out.println("扣账利息金额（12）：" + intamt);

		byte[] BILLAMOUNT = new byte[12];
		System.arraycopy(data, 62, BILLAMOUNT, 0, 12);
		String str2 = new String(BILLAMOUNT);
		Double billamount = new Double(str2.substring(0, 10) + "." + str2.substring(10));
		ft.setBillamount(Double.valueOf(billamount));
		System.out.println("实际扣账金额（12）：" + billamount);

		byte[] CARDNBRI = new byte[19];
		System.arraycopy(data, 74, CARDNBRI, 0, 19);
		String cardnbri = new String(CARDNBRI);
		ft.setCardnbri(cardnbri);
		System.out.println("转入电子账号（19）：" + cardnbri);

		byte[] CURR = new byte[3];
		System.arraycopy(data, 93, CURR, 0, 3);
		String curr = new String(CURR);
		ft.setCurr(curr);
		System.out.println("币种（3）：" + curr);

		byte[] OUTFEEWAY = new byte[1];
		System.arraycopy(data, 96, OUTFEEWAY, 0, 1);
		String outfeeway = new String(OUTFEEWAY);
		ft.setOutfeeway(Short.valueOf(outfeeway));
		System.out.println("转出方手续费扣款方式（1）：" + outfeeway);

		byte[] OUTFEEAMT = new byte[11];
		System.arraycopy(data, 97, OUTFEEAMT, 0, 11);
		String outfeeamt = new String(OUTFEEAMT);
		ft.setOutfeeamt(Double.valueOf(outfeeamt));
		System.out.println("转出方手续费扣款金额（11）：" + outfeeamt);

		byte[] ROUTFEEAMT = new byte[11];
		System.arraycopy(data, 108, ROUTFEEAMT, 0, 11);
		String routfeeamt = new String(ROUTFEEAMT);
		ft.setRoutfeeamt(Double.valueOf(routfeeamt));
		System.out.println("转出方手续费实际扣款金额（11）：" + routfeeamt);

		byte[] INFEEWAY = new byte[1];
		System.arraycopy(data, 119, INFEEWAY, 0, 1);
		String infeeway = new String(INFEEWAY);
		ft.setInfeeway(Short.valueOf(infeeway));
		System.out.println("转入方手续费扣款方式（1）：" + infeeway);

		byte[] INFEEAMT = new byte[11];
		System.arraycopy(data, 120, INFEEAMT, 0, 11);
		String infeeamt = new String(INFEEAMT);
		ft.setInfeeamt(Double.valueOf(infeeamt));
		System.out.println("转入方手续费扣款金额（11）：" + infeeamt);

		byte[] RIFEEAMT = new byte[11];
		System.arraycopy(data, 131, RIFEEAMT, 0, 11);
		String rifeeamt = new String(RIFEEAMT);
		ft.setRifeeamt(Double.valueOf(rifeeamt));
		System.out.println("转出方手续费实际扣款金额（11）：" + rifeeamt);

		byte[] PRODUCT = new byte[6];
		System.arraycopy(data, 142, PRODUCT, 0, 6);
		String product = new String(PRODUCT);
		ft.setProduct(product);
		System.out.println("标的编号（6）：" + product);

		byte[] SERIALNO = new byte[40];
		System.arraycopy(data, 148, SERIALNO, 0, 40);
		String serialno = new String(SERIALNO);
		ft.setSerialno(serialno);
		System.out.println("投标申请流水号（40）：" + serialno);

		byte[] THDRESE = new byte[100];
		System.arraycopy(data, 188, THDRESE, 0, 100);
		String thdrese = new String(THDRESE);
		ft.setThdrese(thdrese);
		System.out.println("第三方保留域（100）：" + thdrese);

		byte[] RESE = new byte[100];
		System.arraycopy(data, 288, RESE, 0, 100);
		String rese = new String(RESE);
		ft.setRese(rese);
		System.out.println("保留域（100）：" + rese);
		System.out.println("--------分割线---------");
		return ft;
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

	public BacthFileRecordServiceI getBacthFileRecordService() {
		return bacthFileRecordService;
	}

	public void setBacthFileRecordService(BacthFileRecordServiceI bacthFileRecordService) {
		this.bacthFileRecordService = bacthFileRecordService;
	}

	public UserAccountServiceI getUserAccountService() {
		return userAccountService;
	}

	public void setUserAccountService(UserAccountServiceI userAccountService) {
		this.userAccountService = userAccountService;
	}

	public AccInExRecordServiceI getAccInExRecordService() {
		return accInExRecordService;
	}

	public void setAccInExRecordService(AccInExRecordServiceI accInExRecordService) {
		this.accInExRecordService = accInExRecordService;
	}

	public UserTenderServiceI getUserTenderService() {
		return userTenderService;
	}

	public void setUserTenderService(UserTenderServiceI userTenderService) {
		this.userTenderService = userTenderService;
	}

	public MediacyFeeRecordServiceI getMediacyFeeRecordService() {
		return mediacyFeeRecordService;
	}

	public void setMediacyFeeRecordService(MediacyFeeRecordServiceI mediacyFeeRecordService) {
		this.mediacyFeeRecordService = mediacyFeeRecordService;
	}

	public GuaranteeFeeRecordServiceI getGuaranteeFeeRecordService() {
		return guaranteeFeeRecordService;
	}

	public void setGuaranteeFeeRecordService(GuaranteeFeeRecordServiceI guaranteeFeeRecordService) {
		this.guaranteeFeeRecordService = guaranteeFeeRecordService;
	}

	public RiskGuarantyFeeRecordServiceI getRiskGuarantyFeeRecordService() {
		return riskGuarantyFeeRecordService;
	}

	public void setRiskGuarantyFeeRecordService(RiskGuarantyFeeRecordServiceI riskGuarantyFeeRecordService) {
		this.riskGuarantyFeeRecordService = riskGuarantyFeeRecordService;
	}

	public MediacyFeeServiceI getMediacyFeeService() {
		return mediacyFeeService;
	}

	public void setMediacyFeeService(MediacyFeeServiceI mediacyFeeService) {
		this.mediacyFeeService = mediacyFeeService;
	}

	public GuaranteeFeeServiceI getGuaranteeFeeService() {
		return guaranteeFeeService;
	}

	public void setGuaranteeFeeService(GuaranteeFeeServiceI guaranteeFeeService) {
		this.guaranteeFeeService = guaranteeFeeService;
	}

	public RiskGuarantyMoneyServiceI getRiskGuarantyMoneyService() {
		return riskGuarantyMoneyService;
	}

	public void setRiskGuarantyMoneyService(RiskGuarantyMoneyServiceI riskGuarantyMoneyService) {
		this.riskGuarantyMoneyService = riskGuarantyMoneyService;
	}

	public loanappServiceI getLoanappService() {
		return loanappService;
	}

	public void setLoanappService(loanappServiceI loanappService) {
		this.loanappService = loanappService;
	}

	public TenderItemServiceI getTenderItemService() {
		return tenderItemService;
	}

	public void setTenderItemService(TenderItemServiceI tenderItemService) {
		this.tenderItemService = tenderItemService;
	}

	public UserTenderPlusLinkServiceI getUserTenderPlusLinkService() {
		return userTenderPlusLinkService;
	}

	public void setUserTenderPlusLinkService(UserTenderPlusLinkServiceI userTenderPlusLinkService) {
		this.userTenderPlusLinkService = userTenderPlusLinkService;
	}

	public UserRedEnvelopeServiceI getUserRedEnvelopeService() {
		return userRedEnvelopeService;
	}

	public void setUserRedEnvelopeService(UserRedEnvelopeServiceI userRedEnvelopeService) {
		this.userRedEnvelopeService = userRedEnvelopeService;
	}

	public UserInterestRateCouponServiceI getUserInterestRateCouponService() {
		return userInterestRateCouponService;
	}

	public void setUserInterestRateCouponService(UserInterestRateCouponServiceI userInterestRateCouponService) {
		this.userInterestRateCouponService = userInterestRateCouponService;
	}

	public UserMakeALoanServiceI getUserMakeALoanService() {
		return userMakeALoanService;
	}

	public void setUserMakeALoanService(UserMakeALoanServiceI userMakeALoanService) {
		this.userMakeALoanService = userMakeALoanService;
	}
}
