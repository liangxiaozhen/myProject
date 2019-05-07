package com.ptpl.controller.huifu;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huifu.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.model.UserGradeExp;
import com.ptpl.model.UserUpgradeRecord;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserGradeExpServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.UserUpgradeRecordServiceI;
import com.ptpl.web.util.HuifuParams;
/**
 * 汇付天下接口--用户账户支付
 * 
 * @author xiaoy
 *
 * @version 2016年12月7日 下午4:44:54
 */
@Controller
@RequestMapping("/huifu/userAccountPayment")
@Scope("prototype")
public class HuifuUserAccountPaymentController extends BaseController {
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserUpgradeRecordServiceI userUpgradeRecordService;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserGradeExpServiceI userGradeExpService;
	/**
	 * 用户账户支付-回调
	 * 
	 * @param params
	 */
	@RequestMapping(value = "/bgcallback")
	synchronized public void bgCallBack(HuifuParams params)
	{
		try
		{
			/*
			 * 消息类型 CmdId UsrAcctPay 应答返回码 RespCode 000成功 应答描述 RespDesc 订单号
			 * OrdId 用户客户号 UsrCustId 商户客户号 MerCustId 交易金额 TransAmt 入账子账户
			 * InAcctId 入账账户类型 InAcctType 页面返回 URL RetUrl 商户后台应答地址 BgRetUrl
			 * 商户私有域 MerPriv 签名 ChkValue CmdId + RespCode +OrdId + UsrCustId +
			 * MerCustId + TransAmt+ InAcctId + InAcctType + RetUrl + BgRetUrl+
			 * MerPriv
			 */
			System.out.println("进入用户账户支付回调页面");
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(params.getCmdId()))
					.append(StringUtils.trimToEmpty(params.getRespCode()))
					.append(StringUtils.trimToEmpty(params.getOrdId()))
					.append(StringUtils.trimToEmpty(params.getUsrCustId()))
					.append(StringUtils.trimToEmpty(params.getMerCustId()))
					.append(StringUtils.trimToEmpty(params.getTransAmt()))
					.append(StringUtils.trimToEmpty(params.getInAcctId()))
					.append(StringUtils.trimToEmpty(params.getInAcctType()))
					.append(StringUtils.trimToEmpty(params.getRetUrl()))
					.append(StringUtils.trimToEmpty(params.getBgRetUrl()))
					.append(StringUtils.trimToEmpty(params.getMerPriv()));
			String plainStr = buffer.toString();
			System.out.println(plainStr);
			boolean flag = false;
			flag = SignUtils.verifyByRSA(plainStr, params.getChkValue());
			if (!flag)
			{
				System.out.println("验证签名失败...");
			}
			if (StringUtils.isNotBlank(params.getOrdId()))
			{
				PrintWriter out = response.getWriter();
				out.print("RECV_ORD_ID_".concat(params.getOrdId()));
			}
			if (flag)
			{
				double transAmt = Double.valueOf(params.getTransAmt());
				String[] arr = params.getMerPriv().split(",");
				BigDecimal baseid = new BigDecimal(arr[0]);
				Short rankNo = new Short(arr[1]);
				Short type = new Short(arr[2]);
				UserGrade userGrade = new UserGrade();
				userGrade.setRankno(rankNo);
				Date expiryDate = null;
				if (type.equals((short) 2))
				{
					UserGradeExp userGradeExp = new UserGradeExp();
					userGradeExp.setUserGrade(userGrade);
					int day = userGradeExpService.selective(userGradeExp).get(0).getExpirytime();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					calendar.add(Calendar.DATE, day);
					expiryDate = calendar.getTime();
				}
				userGrade = userGradeService.selective(userGrade).get(0);
				Short uGrade = userGrade.getUgrade().shortValue();
				UserAccountSafeInfo uasi = userAccountSafeInfoService.selectByBaseId(baseid);
				/*
				 * 用户账户
				 */
				UserAccount userAccount = userAccountService.getUserAccountByBaseId(baseid);
				// 可用余额
				userAccount.setAvlbalance(userAccount.getAvlbalance() - transAmt);
				// 总余额
				userAccount.setBalance(userAccount.getBalance() - transAmt);
				userAccountService.updateUseraccount(userAccount);
				// 旧等级
				Short oldUgrade = uasi.getUgrade();
				// 设置新等级
				uasi.setUgrade(uGrade);
				// 会员类型 ：普通会员
				uasi.setUgradetype((short) type);
				userAccountSafeInfoService.update(uasi);
				/*
				 * 用户等级升级记录
				 */
				UserUpgradeRecord userUpgradeRecord = new UserUpgradeRecord();
				// baseid
				userUpgradeRecord.setBaseid(baseid);
				// 升级模式
				userUpgradeRecord.setDealmode(type);
				// 用户系统积分
				userUpgradeRecord.setBonuspoints(userAccount.getBonuspoints().longValue());
				// 扣除系统积分
				userUpgradeRecord.setDeductbonuspoints(0L);
				// 购买时间
				userUpgradeRecord.setDealtime(new Date());
				// 到期时间
				userUpgradeRecord.setExpirydate(expiryDate);
				// 购买方式
				userUpgradeRecord.setPaytype((short) 1);
				// 级别
				userUpgradeRecord.setGrade(uGrade.intValue());
				// 原来级别
				userUpgradeRecord.setOldgrade(oldUgrade.intValue());
				// 交易金额
				userUpgradeRecord.setPayamount(transAmt);
				// 备注
				userUpgradeRecord.setRemark(oldUgrade + "级升级到" + uGrade + "级");
				userUpgradeRecordService.insertSelective(userUpgradeRecord);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
