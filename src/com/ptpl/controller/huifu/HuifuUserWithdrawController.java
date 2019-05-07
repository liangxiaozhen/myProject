package com.ptpl.controller.huifu;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.MessageTemplate;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.SySNoticeBiz;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.MessageTemplateServiceI;
import com.ptpl.service.SMSChannelServiceI;
import com.ptpl.service.SysNoticeBizServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/huifuWithdraw")
public class HuifuUserWithdrawController extends BaseController {
	@Autowired
	UserWithdrawsCashServiceI userWithdrawsCashService;
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	UserAccountServiceI userAccountService;
	@Autowired
	AccInExRecordServiceI accInExRecordService;
	@Autowired
	private SysNoticeBizServiceI sysNoticeBizService;
	@Autowired
	private SMSChannelServiceI smsChannelService;
	@Autowired
	private MessageTemplateServiceI messageTemplateService;

	/**
	 * 用户后台取现回调
	 * 
	 * @param request
	 * @param res
	 * @param params
	 */
	@RequestMapping(value = "/bgCallBack", method = { RequestMethod.POST, RequestMethod.GET })
	public void bgCallBack(HttpServletRequest request, HttpServletResponse res, HuifuParams params) {
		// 1. 接收异步返回的消息
		// 2. 对于后台返回方式，为了表示商户订单系统已经收到交易应答，商户必须在应答接收页面输出一段特殊的字符串,
		// 特殊字符串的具体说明可参见《商户专属平台接口规范》的5.1.3交易类接口应答接受规范
		// 3. 注意，异步返回对信息做了url encode，在进行验签前需要先decode处理
		try {
			StringBuffer buffer = new StringBuffer();
			UserWithdrawsCash uwdCash = new UserWithdrawsCash();
			UserAccount userAccount = null;
			UserBaseAccountInfo ubai = null;
			AccInExRecord accInExRecord = new AccInExRecord();

			boolean respFlag = false;
			double amount = Double.valueOf(params.getRealTransAmt());
			// 用户baseid
			BigDecimal baseid = new BigDecimal(params.getMerPriv());
			userAccount = userAccountService.getUserAccountByBaseId(baseid);// 用户账户
			// double feeAmt = Double.valueOf(params.getFeeAmt());
			/* 异 步 对 账 */
			if (StringUtil.isNotEmpty(params.getRespType())) {
				/**
				 * RespType+ RespCode + MerCustId+ OrdId +UsrCustId + TransAmt+
				 * OpenAcctId +OpenBankId + RetUrl + BgRetUrl + MerPriv+RespExt
				 */
				respFlag = true;
				buffer.append(StringUtils.trimToEmpty(params.getRespType()))
						.append(StringUtils.trimToEmpty(params.getRespCode()))
						.append(StringUtils.trimToEmpty(params.getMerCustId()))
						.append(StringUtils.trimToEmpty(params.getOrdId()))
						.append(StringUtils.trimToEmpty(params.getUsrCustId()))
						.append(StringUtils.trimToEmpty(params.getTransAmt()))
						.append(StringUtils.trimToEmpty(params.getOpenAcctId()))
						.append(StringUtils.trimToEmpty(params.getOpenBankId()))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getBgRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getMerPriv(), "UTF-8")))
						.append(StringUtils.trimToEmpty(params.getRespExt()));
			} else { /**
						 * CmdId + RespCode + MerCustId+ OrdId +UsrCustId +
						 * TransAmt+ OpenAcctId + OpenBankId + FeeAmt +
						 * FeeCustId + FeeAcctId + ServFee + ServFeeAcctId
						 * +RetUrl + BgRetUrl+ MerPriv+ RespExt
						 */
				/* 同步异步返回参数 */
				buffer.append(StringUtils.trimToEmpty(params.getCmdId()))
						.append(StringUtils.trimToEmpty(params.getRespCode()))
						.append(StringUtils.trimToEmpty(params.getMerCustId()))
						.append(StringUtils.trimToEmpty(params.getOrdId()))
						.append(StringUtils.trimToEmpty(params.getUsrCustId()))
						.append(StringUtils.trimToEmpty(params.getTransAmt()))
						.append(StringUtils.trimToEmpty(params.getOpenAcctId()))
						.append(StringUtils.trimToEmpty(params.getOpenBankId()))
						.append(StringUtils.trimToEmpty(params.getFeeAmt()))
						.append(StringUtils.trimToEmpty(params.getFeeCustId()))
						.append(StringUtils.trimToEmpty(params.getFeeAcctId()))
						.append(StringUtils.trimToEmpty(params.getServFee()))
						.append(StringUtils.trimToEmpty(params.getServFeeAcctId()))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getBgRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getMerPriv(), "UTF-8")))
						.append(StringUtils.trimToEmpty(params.getRespExt()))
						.append(StringUtils.trimToEmpty(params.getRespType()));
			}
			String plainStr = buffer.toString();
			boolean flag = false;
			flag = SignUtils.verifyByRSA(plainStr, params.getChkValue());
			if (!flag) {
				System.out.println("验证签名失败...");
			}
			if (StringUtils.isNotBlank(params.getOrdId())) {
				PrintWriter out = response.getWriter();
				out.print("RECV_ORD_ID_".concat(params.getOrdId()));
			}
			if (!respFlag && flag && ("000".equals(params.getRespCode()) || "999".equals(params.getRespCode()))) {
				/* 扩展参数 */
				JSONArray json = JSONArray.parseArray(params.getRespExt());
				JSONObject obj = json.getJSONObject(0);
				String feeObjFlag = obj.getString("FeeObjFlag");
				String cashChl = obj.getString("CashChl");
				/*
				 * 用户账户表
				 */
				double balance = userAccount.getBalance();// 总余额
				double avlbalance = Arith.sub(userAccount.getAvlbalance(), amount);// 可用余额
				double freezebalance = Arith.add(userAccount.getFreezebalance(), amount);// 冻结余额
				userAccount.setAvlbalance(avlbalance);// 可用余额
				userAccount.setFreezebalance(freezebalance);// 冻结余额
				userAccountService.updateUseraccount(userAccount);
				/*
				 * 资金明细
				 */
				accInExRecord.setBaseid(baseid);
				accInExRecord.setType((short) 3);
				accInExRecord.setStatus((short) 0);
				accInExRecord.setRecordtime(new Date());
				accInExRecord.setAieorderno(StringUtil.getNo());
				accInExRecord.setOutamount(0d);
				accInExRecord.setInamount(0d);
				accInExRecord.setBalance(avlbalance);
				accInExRecord.setTotalbalance(balance);
				accInExRecord.setFreebalance(freezebalance);
				accInExRecordService.insertSelective(accInExRecord);
				// 订单号
				uwdCash.setCashno(params.getOrdId());
				// 申请提现时间
				uwdCash.setApplydate(new Date());
				// 开户银行代号
				uwdCash.setBankname(params.getOpenBankId());
				// 开户银行卡号
				uwdCash.setCardno(params.getOpenAcctId());
				// 状态
				uwdCash.setStatus((short) 0);
				// 审核状态
				uwdCash.setIsaudit((short) 0);
				// 手续费
				uwdCash.setFee(Double.valueOf(params.getFeeAmt()));

				// 手续费收取账户号
				if (feeObjFlag.equals("U"))
					uwdCash.setFeeacctid(params.getUsrCustId());
				if (feeObjFlag.equals("M"))
					uwdCash.setFeeacctid(params.getFeeAcctId());
				// 取现方式
				uwdCash.setCashchl(cashChl);
				// 手续费手续对象
				uwdCash.setFeeobjflag(feeObjFlag);
				// 用户客户号
				uwdCash.setUsrcustid(params.getUsrCustId());
				if (StringUtil.isNotEmpty(params.getServFee())) {
					// 平台服务费
					uwdCash.setServfee(Double.valueOf(params.getServFee()));
					// 服务费收取子账户号
					uwdCash.setServfeeacctid(params.getServFeeAcctId());
				}
				userWithdrawsCashService.updateByCashNo(uwdCash);
			} else if (respFlag && flag) {
				if ("000".equals(params.getRespCode())) {
					uwdCash.setCashno(params.getOrdId());
					uwdCash.setAmount(Double.valueOf(params.getTransAmt()));
					// 开户银行代号
					uwdCash.setBankname(params.getOpenBankId());
				}
				if ("400".equals(params.getRespCode())) {
					userAccount = userAccountService.getUserAccountByBaseId(baseid);// 用户账户
					userAccount.setAvlbalance(Arith.add(userAccount.getAvlbalance(), amount));// 可用余额
					userAccount.setFreezebalance(Arith.sub(userAccount.getFreezebalance(), amount));// 冻结余额
					userAccountService.updateUseraccount(userAccount);
					uwdCash.setRemark(params.getRespDesc());
					uwdCash.setStatus((short) 3);
					uwdCash.setIsaudit((short) 3);
					/*
					 * 资金明细
					 */
					accInExRecord.setBaseid(baseid);
					accInExRecord.setType((short) 3);
					accInExRecord.setStatus((short) 2);
					accInExRecord.setRecordtime(new Date());
					accInExRecord.setAieorderno(StringUtil.getNo());
					accInExRecord.setOutamount(0d);
					accInExRecord.setInamount(0d);
					accInExRecord.setBalance(userAccount.getAvlbalance());
					accInExRecord.setTotalbalance(userAccount.getBalance());
					accInExRecord.setFreebalance(userAccount.getFreezebalance());
					accInExRecordService.insertSelective(accInExRecord);
				}
				userWithdrawsCashService.updateByCashNo(uwdCash);
			}
			System.out.println("respCode=" + params.getRespCode() + ",描述=" + params.getRespDesc());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 取现成功发送短信
	 */
	private void smsSend(String username, String amount, String mobilephone, String time) {
		/*
		 * 系统通知业务
		 */
		SySNoticeBiz sysNoticeBiz = sysNoticeBizService.selectByPrimaryKey(new BigDecimal(1));
		if (sysNoticeBiz.getIsopen().equals((short) 0))
			return;
		/*
		 * 短信通道
		 */
		SMSChannel smsChannel = smsChannelService.selectByPrimaryKey(sysNoticeBiz.getSmscid());
		if (smsChannel == null)
			return;
		/*
		 * 短信模板
		 */
		MessageTemplate messageTemplate = messageTemplateService.selectByPrimaryKey(new BigDecimal(3));
		/*
		 * 短信参数
		 */
		String interUrl = smsChannel.getSmsurl();
		String entNo = smsChannel.getExtno();
		String account = smsChannel.getPusername();
		String password = smsChannel.getPpassword();
		String msg = messageTemplate.getContent().replaceAll("\\{A\\}", username).replaceAll("\\{B\\}", amount)
				.replaceAll("\\{C\\}", time);
		// 发送短信
		SMSSend.smsSend(interUrl, entNo, account, password, mobilephone, msg);
	}

	/**
	 * 取现复核回调
	 * 
	 * @param request
	 * @param response
	 * @param params
	 */
	@RequestMapping(value = "/transDetailCallBack", method = { RequestMethod.POST })
	public void transDetailCallBack(HttpServletRequest request, HttpServletResponse response, HuifuParams params) {
		StringBuffer buffer = new StringBuffer();
		boolean respFlag = false;
		String code = params.getRespCode();
		String auditFlag = params.getAuditFlag();
		String ordId = params.getOrdId();
		UserWithdrawsCash uwdCash = new UserWithdrawsCash();
		AccInExRecord accInExRecord = new AccInExRecord();
		UserAccount userAccount = null;
		UserBaseAccountInfo ubai = null;
		System.out.println("respCode=" + params.getRespCode() + ",描述=" + params.getRespDesc());
		double amount = Double.valueOf(params.getTransAmt());
		double feeAmt = Double.valueOf(params.getFeeAmt());
		try {
			if (StringUtil.isNotEmpty(params.getRespType())) {
				/*
				 * 异步对账 RespType+ RespCode + MerCustId+ OrdId + UsrCustId +
				 * TransAmt+ OpenAcctId + OpenBankId + RetUrl + BgRetUrl+
				 * MerPriv+ RespExt
				 */
				buffer.append(StringUtils.trimToEmpty(params.getRespType()))
						.append(StringUtils.trimToEmpty(params.getRespCode()))
						.append(StringUtils.trimToEmpty(params.getMerCustId()))
						.append(StringUtils.trimToEmpty(params.getOrdId()))
						.append(StringUtils.trimToEmpty(params.getUsrCustId()))
						.append(StringUtils.trimToEmpty(params.getTransAmt()))
						.append(StringUtils.trimToEmpty(params.getOpenAcctId()))
						.append(StringUtils.trimToEmpty(params.getOpenBankId()))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getBgRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(params.getMerPriv()))
						.append(StringUtils.trimToEmpty(params.getRespExt()));
			} else {
				/*
				 * CmdId + RespCode+MerCustId+ OrdId + UsrCustId + TransAmt+
				 * OpenAcctId + OpenBankId +AuditFlag + RetUrl + BgRetUrl +
				 * MerPriv
				 */
				buffer.append(StringUtils.trimToEmpty(params.getCmdId()))
						.append(StringUtils.trimToEmpty(params.getRespCode()))
						.append(StringUtils.trimToEmpty(params.getMerCustId()))
						.append(StringUtils.trimToEmpty(params.getOrdId()))
						.append(StringUtils.trimToEmpty(params.getUsrCustId()))
						.append(StringUtils.trimToEmpty(params.getTransAmt()))
						.append(StringUtils.trimToEmpty(params.getOpenAcctId()))
						.append(StringUtils.trimToEmpty(params.getOpenBankId()))
						.append(StringUtils.trimToEmpty(params.getAuditFlag()))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getRetUrl(), "UTF-8")))
						.append(StringUtils.trimToEmpty(URLDecoder.decode(params.getBgRetUrl(), "UTF-8")))
						.append(params.getMerPriv());
			}
			String plainStr = buffer.toString();
			boolean flag = false;
			flag = SignUtils.verifyByRSA(plainStr, params.getChkValue());
			String[] arr = HttpClientHandler.getBase64Decode(params.getMerPriv()).split(",");
			String remark = arr[0];
			BigDecimal baseid = new BigDecimal(arr[1]);
			userAccount = userAccountService.getUserAccountByBaseId(baseid);
			ubai = userBaseAccountInfoService.selectByPrimaryKey(baseid);
			if (!flag) {
				System.out.println("验证签名失败...");
			}
			if (StringUtils.isNotBlank(ordId)) {
				PrintWriter out = response.getWriter();
				out.print("RECV_ORD_ID_".concat(ordId));
			}
			if (!respFlag & flag && (code.equals("000") || code.equals("999"))) {
				if (auditFlag.equals("R")) {
					// 总额
					double avlbalance = Arith.add(userAccount.getAvlbalance(), amount);
					double freezebalance = Arith.sub(userAccount.getFreezebalance(), amount);

					userAccount.setAvlbalance(avlbalance);
					userAccount.setFreezebalance(freezebalance);
					uwdCash.setRemark(remark);
					uwdCash.setStatus((short) 2);
					uwdCash.setIsaudit((short) 2);

					/*
					 * 账户收支记录表
					 */
					accInExRecord.setBaseid(baseid);
					accInExRecord.setType((short) 3);
					accInExRecord.setStatus((short) 2);
					accInExRecord.setRecordtime(new Date());
					accInExRecord.setAieorderno(StringUtil.getNo());
					accInExRecord.setOutamount(0d);
					accInExRecord.setInamount(amount);
					// 可用余额
					accInExRecord.setBalance(avlbalance);
					// 总余额
					accInExRecord.setTotalbalance(userAccount.getBalance());
					// 冻结余额
					accInExRecord.setFreebalance(userAccount.getFreezebalance());
					accInExRecordService.insertSelective(accInExRecord);
				} else if (auditFlag.equals("S")) {
					// 总额
					double balance = Arith.sub(userAccount.getBalance(), amount);
					double avlbalance = userAccount.getAvlbalance();
					userAccount.setBalance(Arith.sub(balance, feeAmt));
					// 冻结余额
					userAccount.setFreezebalance(Arith.sub(userAccount.getFreezebalance(), amount));
					// 可用余额
					userAccount.setAvlbalance(Arith.sub(avlbalance, feeAmt));
					uwdCash.setRemark(remark);
					uwdCash.setStatus((short) 1);
					uwdCash.setIsaudit((short) 1);
					/*
					 * 账户收支记录表
					 */
					accInExRecord.setBaseid(baseid);
					accInExRecord.setType((short) 3);
					accInExRecord.setStatus((short) 1);
					accInExRecord.setRecordtime(new Date());
					accInExRecord.setAieorderno(StringUtil.getNo());
					accInExRecord.setOutamount(amount);
					accInExRecord.setInamount(0d);
					// 可用余额
					accInExRecord.setBalance(avlbalance);
					// 总余额
					accInExRecord.setTotalbalance(balance);
					// 冻结余额
					accInExRecord.setFreebalance(userAccount.getFreezebalance());
					accInExRecordService.insertSelective(accInExRecord);
					/*
					 * 手续费
					 */
					accInExRecord.setBaseid(baseid);
					accInExRecord.setType((short) 4);
					accInExRecord.setStatus((short) 1);
					accInExRecord.setRecordtime(new Date());
					accInExRecord.setAieorderno(StringUtil.getNo());
					accInExRecord.setInamount(0d);
					accInExRecord.setOutamount(feeAmt);
					accInExRecord.setBalance(Arith.sub(avlbalance, feeAmt));
					accInExRecord.setTotalbalance(Arith.sub(balance, feeAmt));
					accInExRecord.setFreebalance(userAccount.getFreezebalance());
					accInExRecordService.insertSelective(accInExRecord);
					smsSend(ubai.getLoginname(), String.valueOf(amount)+"元", ubai.getMobilephone(), "");
				}
				uwdCash.setCashno(params.getOrdId());
				uwdCash.setAuditdate(new Date());
				userWithdrawsCashService.updateByCashNo(uwdCash);
				userAccountService.updateUseraccount(userAccount);
			} else if (respFlag & flag) {
				if (code.equals("400")) {
					double avlBalance = Arith.add(userAccount.getAvlbalance(), amount);
					double freezeBalance = Arith.sub(userAccount.getFreezebalance(), amount);
					userAccount.setAvlbalance(Arith.add(avlBalance, feeAmt));
					userAccount.setFreezebalance(Arith.add(freezeBalance, feeAmt));
					uwdCash.setCashno(params.getOrdId());
					uwdCash.setStatus((short) 3);
					uwdCash.setIsaudit((short) 3);
					uwdCash.setRemark(params.getRespDesc());
					userWithdrawsCashService.updateByCashNo(uwdCash);
					userAccountService.updateUseraccount(userAccount);
					/*
					 * 账户收支记录表
					 */
					accInExRecord.setBaseid(baseid);
					accInExRecord.setType((short) 3);
					accInExRecord.setStatus((short) 2);
					accInExRecord.setRecordtime(new Date());
					accInExRecord.setAieorderno(StringUtil.getNo());
					accInExRecord.setInamount(amount);
					accInExRecord.setOutamount(0d);
					accInExRecord.setBalance(avlBalance);
					accInExRecord.setTotalbalance(userAccount.getBalance());
					accInExRecord.setFreebalance(freezeBalance);
					accInExRecordService.insertSelective(accInExRecord);
					/*
					 * 手续费
					 */
					accInExRecord.setType((short) 4);
					accInExRecord.setStatus((short) 2);
					accInExRecord.setRecordtime(new Date());
					accInExRecord.setAieorderno(StringUtil.getNo());
					accInExRecord.setInamount(feeAmt);
					accInExRecord.setOutamount(0d);
					accInExRecord.setBalance(Arith.add(avlBalance, feeAmt));
					accInExRecord.setTotalbalance(userAccount.getBalance());
					accInExRecord.setFreebalance(Arith.add(freezeBalance, feeAmt));
					accInExRecordService.insertSelective(accInExRecord);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取现页面返回
	 * 
	 * @param request
	 * @param response
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/callBack", method = { RequestMethod.POST })
	public ModelAndView callBack(HttpServletRequest request, HttpServletResponse response, HuifuParams params)
			throws Exception {
		String ordId = params.getOrdId();
		String transAmt = params.getTransAmt();
		String feeAmt = params.getFeeAmt();
		String servFee = params.getServFee();
		ModelAndView mav = new ModelAndView();
		mav.addObject("ordId", ordId);
		mav.addObject("transAmt", transAmt);
		mav.addObject("feeAmt", feeAmt);
		mav.addObject("servFee", servFee);
		mav.setViewName("/user/withdraw/callback");
		return mav;
	}
}
