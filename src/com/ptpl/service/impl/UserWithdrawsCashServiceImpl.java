package com.ptpl.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishang.util.HSignUtil;
import com.ptpl.mapper.UserWithdrawsCashMapper;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

/**
 * 
 * @author xiaoy
 * 
 * @version 2016年5月27日 下午4:41:19
 */
public class UserWithdrawsCashServiceImpl implements UserWithdrawsCashServiceI {
	@Autowired
	private UserWithdrawsCashMapper uwcMapper;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private AccInExRecordServiceI accInExRecordService;
	
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;
	
	@Autowired
	private UserBankCardServiceI userBankCardService;
	@Override
	public int updateByCashNo(UserWithdrawsCash userWithdrawsCash) {
		return uwcMapper.updateByCashNo(userWithdrawsCash);
	}

	@Override
	public UserWithdrawsCash selectByCashNo(String cashNo) {
		return uwcMapper.selectByCashNo(cashNo);
	}

	@Override
	public List<UserWithdrawsCash> selective(UserWithdrawsCash userWithdrawsCash) {
		return uwcMapper.selective(userWithdrawsCash);
	}

	/**
	 * 获取用户提现记录
	 */
	@Override
	public List<UserWithdrawsCash> getUseWithdrawsCashNote(UserWithdrawsCash uwdCash) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<UserWithdrawsCash> list = uwcMapper.getUseWithdrawsCashNote(uwdCash);
		for (UserWithdrawsCash cash : list) {
			if (cash.getApplydate() != null)
				cash.setApplydateStr(df.format(cash.getApplydate()));
			if (cash.getAuditdate() != null)
				cash.setAuditdateStr(df.format(cash.getAuditdate()));
		}
		return list;
	}

	/**
	 * 获取日提现金额，日提现次数
	 */
	@Override
	public UserWithdrawsCash selectCountAmountForId(BigDecimal baseid) {
		return uwcMapper.selectCountAmountForId(baseid);
	}

	@Override
	public List<UserWithdrawsCash> selectContrast(UserWithdrawsCash userWithdrawsCash) {
		return uwcMapper.selectContrast(userWithdrawsCash);
	}

	/**
	 * 调用第三方提现接口后，保存用户提现记录
	 */
	@Override
	public int insertSelective(UserWithdrawsCash uwdCash) {
		return uwcMapper.insertSelective(uwdCash);
	}

	@Override
	public int updateForSuccess(UserWithdrawsCash uwdCash) {
		double amount = uwdCash.getAmount();
		double fee = uwdCash.getFee();
		double sum = Arith.add(amount, fee);
		/*
		 * 用户账户表
		 */
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(uwdCash.getBaseid());
		// 总余额
		double balance = userAccount.getBalance();
		// 可用余额
		double avlbalance = userAccount.getAvlbalance();
		// 总余额
		userAccount.setBalance(Arith.sub(balance, sum));
		// 可用余额
		userAccount.setAvlbalance(Arith.sub(avlbalance, sum));
		userAccountService.updateUseraccount(userAccount);
		/*
		 * 资金明细
		*/
		AccInExRecord accInExRecord = new AccInExRecord();
		// baseid
		accInExRecord.setBaseid(uwdCash.getBaseid());
		// 业务类型 3 提现
		accInExRecord.setType((short) 3);
		// 状态 1成功
		accInExRecord.setStatus((short) 1);
		// 发生时间
		accInExRecord.setRecordtime(new Date());
		// 流水号
		accInExRecord.setAieorderno(StringUtil.getNo());
		// 支出
		accInExRecord.setOutamount(amount);
		// 收入
		accInExRecord.setInamount(0d);
		// 可用余额
		avlbalance = Arith.sub(avlbalance, amount);
		accInExRecord.setBalance(avlbalance);
		// 用户总余额
		balance = Arith.sub(balance, amount);
		accInExRecord.setTotalbalance(balance);
		// 说明
		accInExRecord.setDescription("用户提现");
		accInExRecordService.insertSelective(accInExRecord); 
		
		/*
		 * 手续费
		*/
		if (fee != 0) {
			AccInExRecord feeAccInExRecord = new AccInExRecord();
			// baseid
			feeAccInExRecord.setBaseid(uwdCash.getBaseid());
			// 类型 4提现手续费
			feeAccInExRecord.setType((short) 4);
			// 状态
			feeAccInExRecord.setStatus((short) 1);
			// 发生时间
			feeAccInExRecord.setRecordtime(new Date());
			// 流水号
			feeAccInExRecord.setAieorderno(StringUtil.getNo());
			// 收入
			feeAccInExRecord.setInamount(0d);
			// 支出
			feeAccInExRecord.setOutamount(fee);
			// 平台账户
			feeAccInExRecord.setPaccount("9930040050240300018");
			// 可用余额
			feeAccInExRecord.setBalance(Arith.sub(avlbalance, fee));
			// 总余额
			feeAccInExRecord.setTotalbalance(Arith.sub(balance, fee));
			// 冻结余额
			feeAccInExRecord.setFreebalance(userAccount.getFreezebalance());
			// 说明
			feeAccInExRecord.setDescription("提现手续费");
			accInExRecordService.insertSelective(feeAccInExRecord);
		} 
		return uwcMapper.updateByCashNo(uwdCash);
	}

	@Override
	public void withdrawsCash(HttpServletRequest request,HttpServletResponse response,Double money, Double fee, UserBaseAccountInfo userInfo,Short originClient) {
		DecimalFormat df1 = new DecimalFormat("##########0.00");
		BigDecimal baseID = userInfo.getId();
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(baseID);
		UserBankCard userBankCard = new UserBankCard();
		userBankCard.setBaseid(baseID);
		userBankCard.setIsdefaultcard((short) 1);
		userBankCard = userBankCardService.findIsDefaultCard(userBankCard);
		Date date = new Date();
		String bankCode =userBankCard.getCardno();
		String decryptBankCode = AES.getDecrypt(bankCode);
		String orderNo = StringUtil.getNo();
		// 订单号
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String[] dateArr = sdf.format(date).split("-");
		LinkedHashMap<String, Object> reqMap = new LinkedHashMap<String, Object>();
		// 接口版本号
		reqMap.put("VERSION", "v20160602");
		// 订单编号
		reqMap.put("ORDERNO", orderNo);
		// 交易日期
		reqMap.put("TRXDATE", dateArr[0]);
		// 交易时间
		reqMap.put("TRXTIME", dateArr[1]);
		// 银行代码
		reqMap.put("BANKCODE", HSignUtil.BANKCODE);
		// 合作单位编号
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);
		// 合作单位渠道
		reqMap.put("COINSTCHANNEL", HSignUtil.COINSTCHANNEL_WEB);
		// 账户
		reqMap.put("CARDNBR", AES.getDecrypt(userFsAccountInfo.getUsrcustid()));
		// reqMap.put("CARDNBR", "9930040050240500013");
		// 银行名称
		reqMap.put("BANKNAME", "");
		// 绑定卡号
		reqMap.put("CARD_BIND",decryptBankCode);
		// reqMap.put("CARD_BIND", "6228480402564890018");
		// 姓名
		reqMap.put("NAME", AES.getDecrypt(userBankCard.getUsername()));
		// reqMap.put("NAME", "孔庆芝");
		// 证件号码
		reqMap.put("IDNO", userInfo.getCertificationnumber());
		// reqMap.put("IDNO", "370284198606081529");
		// 证件类型
		reqMap.put("IDTYPE", userInfo.getCertificationtype());
		// reqMap.put("IDTYPE", "01");
		// 手机号码
		reqMap.put("PHONE", AES.getDecrypt(userFsAccountInfo.getFsmobile()));
		// reqMap.put("PHONE", "13568956921");
		// 提现金额
		reqMap.put("AMOUNT", df1.format(money));
		// 手续费
		reqMap.put("FEE", df1.format(fee));
		// 忘记密码跳转链接
		reqMap.put("FORGERPWD_URL",
				StringUtil.getBasePath(request) + "/huishang/tradePassword/tradePsswordList.action");
		// 后台响应链接
		reqMap.put("BACKGROUND_URL",
				StringUtil.getBasePath(request) + "/huishang/userwithdrawscash/callback.action");
		// 交易页面跳转链
		reqMap.put("TRANSACTION_URL",
				StringUtil.getBasePath(request) + "/user/userwithdrawscash/withdraw.action");
		// 是否指定通道
		reqMap.put("ROUT_FLAG", "N");
		// 通道标识
		reqMap.put("ROUT_CODE", HSignUtil.SOURCE);
		// 开户银行联行号
		reqMap.put("BANK_CNAPS", "");
		// 开户银行代码
		reqMap.put("BANK_CODE", "");
		// 开户银行英文缩写
		reqMap.put("BANK_NAME_EN", "");
		// 开户银行中文名称
		reqMap.put("BANK_NAME_CN", "");
		// 开户行省份
		reqMap.put("ISS_BANK_PROVINCE", "");
		// 开户行城市
		reqMap.put("ISS_BANK_CITY", "");
		try {
			String sign = HSignUtil.getRASSign(reqMap);
			// 签名
			reqMap.put("SIGN", sign);
			//交易流水
			reqMap.put("SEQNO", orderNo);
			System.out.println(reqMap.toString());
			request.setAttribute("map", reqMap);
			request.setAttribute("action", HSignUtil.WITHHOLDING);
			request.getRequestDispatcher("/WEB-INF/pages/UserWithdraw/UserWithdraw.jsp").forward(request,
					response);
			/// WEB-INF/pages/UserWithdraw/UserWithdraw.jsp
			UserWithdrawsCash uwdCash = new UserWithdrawsCash();
			// 订单号
			uwdCash.setCashno(orderNo);
			// 申请时间
			uwdCash.setApplydate(new Date());
			// baseid
			uwdCash.setBaseid(baseID);
			// 电子账户
			uwdCash.setUsrcustid(userFsAccountInfo.getUsrcustid());
			// 交易金额
			uwdCash.setAmount(Double.valueOf(money));
			// 平台手续费
			uwdCash.setFee(fee);
			// 银行手续费
			uwdCash.setServfee(2d);
			// 银行卡号
			uwdCash.setCardno(bankCode);
			// 银行名称
			uwdCash.setBankname("中国工商银行");
			// 开户省份
			// uwdCash.setProvince(bean.getProvince());
			// 开户城市
			// uwdCash.setCity(bean.getCity());
			// 开户支行
			// uwdCash.setBranchbankname(bean.getBranchBankName());
			// 用户登录名
			uwdCash.setUsername(AES.getEncrypt(userInfo.getLoginname()));
			// 提现状态 0取消 1成功 2失败
			uwdCash.setStatus((short) 0);
			// 客户端
			uwdCash.setOriginclient((short) 1);
			// 取现方式 FAST快速取现 GENERAL一般提现
			uwdCash.setCashchl("GENERAL");
			// 系统勾兑状态
			uwdCash.setIsblending((short) 0);
			// 人工勾兑状态
			uwdCash.setIsmanblending((short) 0);
			// 勾兑是否异常
			uwdCash.setIsexceptions((short) 0);
			// 付款方式
			uwdCash.setFeeobjflag("U");
			int iden = insertSelective(uwdCash);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
