package com.ptpl.web.util;

import java.util.List;

import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.SMSChannel;
import com.ptpl.service.SMSChannelServiceI;

/**
 * 短信接口调用工具类
 * @ClassName: CommonSendSMSUtil
 * @Description: TODO(通用短信接口调用工具类)
 * @author zhenglm
 * @date 2017年3月17日 下午3:02:36
 *
 */
public class CommonSendSMSUtil {
	
	// 短信通道Service
	private static SMSChannelServiceI sMSChannelService = SpringContextHolder
			.getBean(SMSChannelServiceI.class);
	
	/**
	 * 随机获取一个短信通道发送短信
	 * @Title: SendSMSCode
	 * @Description: TODO(随机获取一个短信通道发送短信)
	 * @param mobilephone 发送短信的手机号码
	 * @param msgContent 短信类容
	 * @return boolean    返回类型
	 */
	public static boolean SendSMSCode(String mobilephone,String msgContent){
		boolean flag = false;
		SMSChannel channel = new SMSChannel();
		channel.setIsuse((short) 0);
		List<SMSChannel> SMSChannels = sMSChannelService.selective(channel);
		if(SMSChannels.size()>0){
			SMSChannel SMSChannel = StringUtil.getSMSChannel(SMSChannels);
			String company = SMSChannel.getSmsccompany(); // 短信接口公司
			if(company.trim().equals("短信通")){
				flag = SMSSend.smsSend(mobilephone, msgContent); // 调用短信通接口
			}
		}
		return flag;
	}
}
