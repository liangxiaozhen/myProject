package com.ptpl.web.util;

import java.util.Date;

import com.ptpl.model.UserBaseAccountInfo;

public class EmailInfo {
			
	/**
	 * 获取密码邮件内容
	 * @param merchant
	 * @param generateRandStr
	 * @return
	 */
	public String getResetPwdByEmail(UserBaseAccountInfo user,String generateRandStr){
		StringBuffer setText = new StringBuffer();
		setText.append(user.getEmail()+" 用户您好！\n\n");
		setText.append("您的密码被重置为 : " + generateRandStr+"\n");
		setText.append("用户名 :"+user.getLoginname()+"\n\n");
		setText.append("联系方式:"+ "\n");												
		setText.append("在线客服:+86-13800138000"+ "\n");												
		setText.append("Email: csreason@moye.com"+ "\n");												
		setText.append("传真:  +86-0755-12345678"+ "\n");
		return setText.toString();
	}
	
	public static void main(String[] args) {
		EmailInfo ei = new EmailInfo();
		System.out.println(ei.getResetPwdByEmail(new UserBaseAccountInfo(), "21312312"));
	}
	
	
	
}

