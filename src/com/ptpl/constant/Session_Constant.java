package com.ptpl.constant;
/**
 * 
* @ClassName: Session_Constant 
* @Package com.ptpl.constant 
* @Description: TODO(存放session 作用域的常量值) 
* @author chenjiaming
* @date 2016年7月14日 下午5:22:35 
* @version V1.0
 */
public class Session_Constant {
	
	/*防止表单提交Session  token 值*/
	public static final String SESSIONTOKEN= "SessToken";
  	
	/**
	 * 系统管理用户 session 值
	 */
	public static final String ADMINUSER="adminuser";
	
 	/**
	 * 普通用户 基本信息session 值
	 */
	public static final String USER="user"; 
	
	/**
	 * 用户托管账户信息 session 值
	 */
	public static final String USERFSACCOUNTINFO="userfsaccountinfo"; 
	
 	/**
	 * 普通用户 账号安全信息session 值
	 */
	public static final String USERACCOUNTSAFEINFO ="useraccountsafeinfo";
	
	/**
	 * 普通用户 短信 验证码 session 值
	 */
	public static final String USER_TELEPHONE_CODE="user_telephone_code";
	
	/**
	 * 安全中心  开户短信验证码session 值
	 */
	public static final String SECURITYCENTEROPENANACCOUNTSENDSSM = "securitycenteropenanaccountsendssm";
	
	/**
	 * 安全中心  开户 参数验证通过 session 值
	 */
	public static final String SECURITYCENTEROPENANACCOUNTCODE = "securitycenteropenanaccountcode";
	
	/**
	 * 安全中心  重置登录密码短信验证码session 值
	 */
	public static final String SECURITYCENTERLOGINPASSWORDSENDSSM = "securitycenterloginpasswordsendssm";
	
	/**
	 * 安全中心 手机号码验证/修改  短信验证码 session 值（根据原邮箱修改手机号  邮箱验证码session ）
	 */
	public static final String SECURITYCENTERPHONESENDSSM = "securitycenterphonesendssm";
	
	/**
	 * 安全中心 手机号码修改  新手机短信验证码 session 值
	 */
	public static final String SECURITYCENTERNEWPHONESENDSSM = "securitycenternewphonesendssm";
	
	/**
	 * 安全中心 手机号码验证/修改  手机号码 session 值
	 */
	public static final String SECURITYCENTERPHONE = "securitycenterphone";
	
	/**
	 * 安全中心 设置 密保问题  短信验证码session 值
	 */
	public static final String  SECURITYCENTERQUESTIONSM = "securitycenterquestionssm";
	
	/**
	 * 安全中心 设置 /修改 密保问题  短信验证码成功后保存的session 值
	 */
	public static final String  SECURITYCENTERQUESTIONCHECKSUCCESS = "securitycenterquestionchecksuccess";
	
 	
 	/**
	 * 安全中心 修改邮箱账号  验证原邮箱成功后保存，新邮箱发送绑定链接需要验证此值
	 */
	public static final String EMAILSUCCESSCODE = "emailsuccesscode";
	
	
	/**
	 * 通过手机找回密码   保存code的session
	 */
	public static String PHONERETRIEVEPASSWORD = "phone_retrieve_password";
	
	/**
	 * 通过手机找回密码  保存手机号码的session
	 */
	public static String MOBILEPHONESECURITY = "mobile_phone_security";
	
 	/**
	 * 安全中心 普通用户 更改手机号码  新手机号码短信验证码 session 值
	 */
	public static final String USER_NEW_TELEPHONE_CODE="user_new_telephone_code";
  	
	/**
	 *  安全中心 普通用户 重置邮箱  邮箱验证码 session 值
	 */
	public static final String USER_REGISTER_EMAIL_CODE="user_register_email_code";
	
	/**
	 *  安全中心 普通用户 重置邮箱  邮箱30位随机码 session 值
	 */
	public static final String USER_EMAILCODE_EMAIL_CODE="user_emailcode_email_code";
	
	/**
	 *  第三方登录 微博绑定session值
	 */
	public static final String USER_WEIBO_USER_CODE="user_weibo_user_code";
	
	/**
	 *  第三方登录 微博绑定 新用户注册 手机验证码session值
	 */
	public static final String USER_WEIBO_PHONE_CODE="user_weibo_phone_code";
	
	/**
	 *  第三方登录 微博绑定 新用户注册 邮箱验证证码session值
	 */
	public static final String USER_WEIBO_EMAIL_CODE="user_weibo_email_code";
	
	/**
	 *  第三方登录 QQ绑定session值
	 */
	public static final String USER_QQ_USER_CODE="user_qq_user_code";
	
	/**
	 *  第三方登录 QQ绑定 新用户注册 手机验证码session值
	 */
	public static final String USER_QQ_PHONE_CODE="user_qq_phone_code";
	
	/**
	 *  第三方登录 QQ绑定 新用户注册 邮箱验证证码session值
	 */
	public static final String USER_QQ_EMAIL_CODE="user_qq_email_code";
	
	/**
	 * 邮件补发 方法
	 */
	public static final String REPEAT_EMAIL ="repeatemail";
	
 	/**
	 * 提前还款 借款人还款计划 选择的借款人还款计划 期数ID
	 */
	public static final String AHEADREPAY_DIVMENTS = "aheadrepay_divments";
	
	/**
	 * 提前还款 借款人选择部分提前还款  选择的部分投资人的投标订单号
	 */
	public static final String AHEADPARTREPAY_INACCOUNTID_UTORDERNO = "aheadpartrepay_inaccountid_utorderno";
	
	/**
	 * 提前还款 借款人该次还款总金额
	 */
	public static final String AHEADREPAYMENT_RAMOUNTCOUNT = "aheadrepayment_ramountcount";
	
	/**
	 *  提前还款选择的期数id
	 */
	public static final String AHEADPARTREPAYMENT_DIVMENTS = "aheadpartrepayment_divments";
	
	/**
	 * 提前还款选择的期数id
	 */
	public static final String AHEADALLREPAYMENT_DIVMENTS = "aheadallrepayment_divments";
	
	/**
	 * 逾期还款选择期数ID
	 */
	public static final String OVERDUEREPAYMENT_DIVMENTS = "overduerepayment_divments";
	
	/**
	 * 逾期还款总金额
	 */
	public static final String OVERDUEREPAYMENT_COUNT = "overduerepayment_count";
	
	/**
	 * 用户找回密码  用户安全信息
	 */
	public static final String USERFINDPWDUSERSAFE = "userfindpwdusersafe";
	/**
	 * 用户找回密码  用户基本信息
	 */
	public static final String USERFINDPWDUSERBASE = "userfindpwduserbase";
	
	/**
	 * AES 加密解密key
	 */
	public static final String EMAILCODEKEY = "ed252e0d0b862d0888430d93175f862f";
	
	/**
	 * 交易密码 短信验证码
	 */
	public static final String TRADEPASSWORDPHONESSMCODE = "tradepasswordphonessmcode";
	
	/*response result 通用返回值*/
	public static final String RESULT = "result";
	/*response  resultCode 具体返回值*/
	public static final String RESULTCODE = "resultCode";
	/*response message 消息提示*/
	public static final String MESSAGE = "message";
	/*response  params_error*/
	public static final String PARAMSERROR = "params_error";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
 	public static final String LOGOUT = "logout";
 	 
	
}
