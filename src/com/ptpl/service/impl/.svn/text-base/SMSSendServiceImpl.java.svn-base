package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.chuanglan.sms.response.SmsSendResponse;
import com.ipi.cloud.sms.access.vo.response.SmsBatchSumbitResponse;
import com.ptpl.constant.SMSSend_Constant;
import com.ptpl.model.MessageTemplate;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.SMSSendRecord;
import com.ptpl.model.SySNoticeBiz;
import com.ptpl.service.MessageTemplateServiceI;
import com.ptpl.service.SMSChannelServiceI;
import com.ptpl.service.SMSSendRecordServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.SysNoticeBizServiceI;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.StringUtil;

/**
 * 短信Service
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年6月1日 下午5:14:22
 *
 */
public class SMSSendServiceImpl implements SMSSendServiceI {

	@Autowired
	private SysNoticeBizServiceI sysNoticeBizService;// 系统业务Service
	@Autowired
	private SMSChannelServiceI smsChannelService; // 短信通道Service
	@Autowired
	private MessageTemplateServiceI messageTemplateService;// 短信模板Service
	@Autowired
	private SMSSendRecordServiceI sMSSendRecordService; // 短信信息Service

	private MessageTemplate messageTemplate;// 短信信息模板

	private SMSChannel smsChannel; // 短信通道

	private SMSSendRecord record; // 短信记录
	/*
	 * 短信参数
	 */
	private String interUrl;// 接口地址
	private String entNo; // 企业号
	private String account; // 用户名
	private String password; // 密码
	private String channelName; // 通道公司
	private String remark ="短信发送成功。"; // 备注

	/**
	 * 短信发送
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午5:58:00
	 * @param mobilephone
	 * @param msg
	 */
	private boolean SMSSend(String mobilephone, String msg) {
		boolean flag=true;
		if (channelName.equals("短信通")) {
			SmsBatchSumbitResponse DXTRsp = SMSSend.smsSendDXT(interUrl, entNo, account, password, mobilephone, msg);
			if (DXTRsp.isSuccess()) {
				record.setStatus((short) 1);
			} else {
				remark = "短信发送失败," + DXTRsp.getErrorInfo().getErrorMsg();
				flag=false;
			}
		}
		if (channelName.equals("创蓝云通讯")) {
			SmsSendResponse CLRsp = SMSSend.smsSendCL(interUrl, account, password, mobilephone, msg);
			// 判断返回状态码
			if ("0".equals(CLRsp.getCode())) {
				record.setStatus((short) 1);
			} else {
				remark = "短信发送失败," + CLRsp.getErrorMsg();
				flag=false;
			}
		}
		return flag;
	}

	/**
	 * 验证短信通道相关设置
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午5:06:00
	 * @param type
	 * @return
	 */
	private boolean checkChannel(BigDecimal type) {
		boolean flag = true;
		record = new SMSSendRecord();
		record.setSendtime(new Date());// 发送时间
		record.setSendtype((short) 2);// 发送类型
		record.setStatus((short) 0); // 初始发送状态
		/*
		 * 系统通知业务
		 */
		SySNoticeBiz sysNoticeBiz = sysNoticeBizService.selectByPrimaryKey(type);
		if (sysNoticeBiz == null) {
			remark = SMSSend_Constant.SMS_MAP.get(type.shortValue()) + "业务未设置。";
			return false;
		}
		String sName = sysNoticeBiz.getBizname();
		if (sysNoticeBiz.getIsopen().equals((short) 0)) {
			remark = sName + "业务开关已关闭。";
			return false;
		}
		/*
		 * 短信通道
		 */
		smsChannel = smsChannelService.selectByPrimaryKey(sysNoticeBiz.getSmscid());
		if (smsChannel == null) {
			remark = sName + "短信通道未设置。";
			return false;
		}
		/*
		 * 短信模板
		 */
		messageTemplate = messageTemplateService.selectByPrimaryKey(type);
		if (messageTemplate == null) {
			remark = sName + "业务，短信模板未设置。";
			return false;
		}
		if (StringUtils.isEmpty(messageTemplate.getContent())) {
			remark = sName + "业务，短信发送内容未设置。";
			return false;
		}
		interUrl = smsChannel.getSmsurl();// 接口地址
		entNo = smsChannel.getExtno(); // 企业号
		account = smsChannel.getPusername(); // 用户名
		password = smsChannel.getPpassword(); // 密码
		channelName = smsChannel.getSmsccompany();// 短信通道
		if (StringUtil.isEmpty(interUrl)) {
			remark = "接口地址未设置";
			return false;
		}

		return flag;
	}

	@Override
	public Map<String, String> SMSSendAgain(SMSSendRecord record) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "0");
		String smsccompany = record.getSmsccompany();
		SMSChannel smsChannel = new SMSChannel();
		smsChannel.setSmsccompany(smsccompany);

		if (StringUtils.isEmpty(smsccompany)) {
			map.put("msg", "短信记录不完全，通道缺失。");
			return map;
		}

		List<SMSChannel> list = smsChannelService.selective(smsChannel);
		if (list.isEmpty()) {
			map.put("msg", "短信通道未设置。");
			return map;
		}

		smsChannel = list.get(0);
		/*
		 * 短信参数
		 */
		String interUrl = smsChannel.getSmsurl();// 接口地址
		String entNo = smsChannel.getExtno(); // 企业号
		String account = smsChannel.getPusername(); // 用户名
		String password = smsChannel.getPpassword(); // 密码
		/*
		 * 手机号 ，内容
		 */
		String mobilephone = record.getMobile();
		String msg = record.getSmscontent();

		record.setReissuetime(new Date());
		record.setSendtype((short) 1);
		String remark = "补发短信成功。";
		if ("短信通".equals(smsccompany)) {
			SmsBatchSumbitResponse DXTRsp = SMSSend.smsSendDXT(interUrl, entNo, account, password, mobilephone, msg);
			if (DXTRsp.isSuccess()) {
				record.setStatus((short) 1);
			} else {
				record.setStatus((short) 0);
				remark = "补发短信失败," + DXTRsp.getErrorInfo().getErrorMsg();
			}
		}
		if ("创蓝云通讯".equals(smsccompany)) {
			SmsSendResponse CLRsp = SMSSend.smsSendCL(interUrl, account, password, mobilephone, msg);
			if ("0".equals(CLRsp.getCode())) {
				record.setStatus((short) 1);
			} else {
				record.setStatus((short) 0);
				remark = "补发短信失败," + CLRsp.getErrorMsg();
			}
		}
		record.setRemark(remark);
		sMSSendRecordService.updateByPrimaryKeySelective(record);
		map.put("msg", remark);
		map.put("status", record.getStatus().toString());
		return map;
	}

	/**
	 * 充值短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:56:12
	 * @param msg
	 * @param uname
	 * @param type
	 * @param amount
	 * @return
	 */
	private String getSMSMsg4Recharge(String msg, String uname, String type, String amount) {
		return msg.replaceAll("\\{A\\}", uname).replaceAll("\\{B\\}", type).replaceAll("\\{C\\}", amount);
	}

	/**
	 * 验证码短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:56:30
	 * @param msg
	 * @param code
	 * @return
	 */
	private String getSMSMsg4Code(String msg, String code) {
		return msg.replaceAll("\\{C\\}", code);
	}

	/**
	 * 提现短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:56:34
	 * @param msg
	 * @param uname
	 * @param amount
	 * @return
	 */
	private String getSMSMsg4WithdrawCash(String msg, String uname, String amount) {
		return msg.replaceAll("\\{A\\}", uname).replaceAll("\\{B\\}", amount);
	}

	/**
	 * 账户开立短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:56:39
	 * @param msg
	 * @param uname
	 * @return
	 */
	private String getSMSMsg4OpenAccount(String msg, String uname) {
		return msg.replaceAll("\\{A\\}", uname);
	}

	/**
	 * 借款人还款短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:56:47
	 * @param msg
	 * @param uname
	 * @param tno
	 * @param amount
	 * @return
	 */
	private String getSMSMsg4RepayMent(String msg, String uname, String tno, String amount) {
		return msg.replaceAll("\\{A\\}", uname).replaceAll("\\{B\\}", tno).replaceAll("\\{C\\}", amount);
	}

	/**
	 * 投标短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:56:57
	 * @param msg
	 * @param uname
	 * @param tno
	 * @param amount
	 * @return
	 */
	private String getSMSMsg4Tender(String msg, String uname, String tno, String amount) {
		return msg.replaceAll("\\{A\\}", uname).replaceAll("\\{B\\}", tno).replaceAll("\\{C\\}", amount);
	}

	/**
	 * 投资人收款短信内容
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月13日 下午3:57:19
	 * @param msg
	 * @param uname
	 * @param tno
	 * @param amount
	 * @return
	 */
	private String getSMSMsg4Gathering(String msg, String uname, String tno, String amount) {
		return msg.replaceAll("\\{A\\}", uname).replaceAll("\\{B\\}", tno).replaceAll("\\{C\\}", amount);
	}

	@Override
	public boolean SMSSend4Code(String mobilephone, String code) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.CODE));
		if (channelFlag) {
			String msg = getSMSMsg4Code(messageTemplate.getContent(), code);
			record.setMobile(mobilephone);// 手机号码
			record.setVercode(code); // 验证码
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			channelFlag=SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
		return channelFlag;
	}

	@Override
	public boolean SMSSend4Code(String mobilephone, String code, String uname, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.CODE));
		if (channelFlag) {
			String msg = getSMSMsg4Code(messageTemplate.getContent(), code);
			record.setMobile(mobilephone);// 手机号码
			record.setVercode(code); // 验证码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			channelFlag=SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
		return channelFlag;
	}

	@Override
	public void SMSSend4WithdrawCash(String mobilephone, String uname, String amount, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.WITHDRAWCASH));
		if (channelFlag) {
			String msg = getSMSMsg4WithdrawCash(messageTemplate.getContent(), uname, amount);
			record.setMobile(mobilephone);// 手机号码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
	}

	@Override
	public void SMSSend4Recharge(String mobilephone, String type, String uname, String amount, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.RECHARGE));
		if (channelFlag) {
			String msg = getSMSMsg4Recharge(messageTemplate.getContent(), uname, type, amount);
			record.setMobile(mobilephone);// 手机号码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
	}

	@Override
	public void SMSSend4OpenAccount(String mobilephone, String uname, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.OPENACCOUNT));
		if (channelFlag) {
			String msg = getSMSMsg4OpenAccount(messageTemplate.getContent(), uname);
			record.setMobile(mobilephone);// 手机号码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
	}

	@Override
	public void SMSSend4RepayMent(String mobilephone, String uname, String amount, String tno, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.REPAYMENT));
		if (channelFlag) {
			String msg = getSMSMsg4RepayMent(messageTemplate.getContent(), uname, tno, amount);
			record.setMobile(mobilephone);// 手机号码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
	}

	@Override
	public void SMSSend4Tender(String mobilephone, String uname, String amount, String tno, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.TENTER));
		if (channelFlag) {
			String msg = getSMSMsg4Tender(messageTemplate.getContent(), uname, tno, amount);
			record.setMobile(mobilephone);// 手机号码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
	}

	@Override
	public void SMSSend4Gathering(String mobilephone, String uname, String amount, String tno, BigDecimal baseid) {
		boolean channelFlag = checkChannel(new BigDecimal(SMSSend_Constant.GATHERING));
		if (channelFlag) {
			String msg = getSMSMsg4Gathering(messageTemplate.getContent(), uname, tno, amount);
			record.setMobile(mobilephone);// 手机号码
			record.setUsername(uname);// 用户名
			record.setBaseid(baseid); // baseid
			record.setSmsccompany(channelName);// 短信通道公司
			record.setSmscontent(msg);// 短信内容

			SMSSend(mobilephone, msg);
		}
		record.setRemark(remark);// 备注
		sMSSendRecordService.insertSelective(record);// 插入短信记录
	}
}
