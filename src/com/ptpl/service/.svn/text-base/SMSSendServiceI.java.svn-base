package com.ptpl.service;

import java.math.BigDecimal;
import java.util.Map;

import com.ptpl.model.SMSSendRecord;

public interface SMSSendServiceI {
	/**
	 * 发送验证码短信
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:50:00 
	 * @param mobilephone  手机号
	 * @param code         验证码
	 * @return
	 */
	boolean SMSSend4Code(String mobilephone,String code);
	/**
	 * 发送验证码短信
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:50:19 
	 * @param mobilephone  手机号
	 * @param code         验证码
	 * @param uname        用户名
	 * @param baseid       id
	 * @return
	 */
	boolean SMSSend4Code(String mobilephone,String code,String uname,BigDecimal baseid);
	/**
	 * 提现短信通知
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:50:41 
	 * @param mobilephone
	 * @param uname
	 * @param amount
	 * @param baseid
	 * @return
	 */
	void SMSSend4WithdrawCash(String mobilephone,String uname,String amount,BigDecimal baseid);
	/**
	 * 充值短信通知
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:51:26 
	 * @param mobilephone
	 * @param uname
	 * @param amount
	 * @param baseid
	 * @return
	 */
	void SMSSend4Recharge(String mobilephone,String type,String uname,String amount,BigDecimal baseid);
	/**
	 * 开户短信通知
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:51:38 
	 * @param mobilpehone
	 * @param uname
	 * @return
	 */
	void SMSSend4OpenAccount(String mobilephone,String uname,BigDecimal baseid);
	/**
	 * 借款人还款短信通知
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:51:42 
	 * @param mobilephone
	 * @param uname
	 * @param amount
	 * @param tno
	 * @param baseid
	 * @return
	 */
	void SMSSend4RepayMent(String mobilephone,String uname,String amount,String tno,BigDecimal baseid);
	/**
	 * 投标短信通知
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:51:53 
	 * @param mobilephone
	 * @param uname
	 * @param amount
	 * @param tno
	 * @param baseid
	 * @return
	 */
	void SMSSend4Tender(String mobilephone,String uname,String amount,String tno,BigDecimal baseid);
	/**
	 * 投资人收款短信通知
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月13日 下午4:51:59 
	 * @param mobilephone
	 * @param uname
	 * @param amount
	 * @param tno
	 * @param baseid
	 * @return
	 */
	void SMSSend4Gathering(String mobilephone,String uname,String amount,String tno,BigDecimal baseid);
	/**
	 *  补发短信
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年6月6日 上午9:28:36 
	 * @param record   短信信息记录对象
	 * @return
	 */
	Map<String,String> SMSSendAgain(SMSSendRecord record);
}
