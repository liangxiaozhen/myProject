package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.moneymoremore.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: MMMUserAccountRegisterController 
* @Package com.ptpl.controller.moneymoremore 
* @Description: TODO(乾多多  用户开户 Controller) 
* @author cjm
* @date 2017年2月21日 下午3:27:25 
* @version V1.0
* 
* /moneymoremore/UserAccountRegister/allAutoUserRegister.action
 */
@Controller
@RequestMapping("/moneymoremore/UserAccountRegister")
public class MMMUserAccountRegisterController extends BaseController{
	
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;//用户托管账号信息
	
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;//用户账号基本信息
	
	/**
	 * 
	* @Title: allAutoUserRegister 
	* @Description: TODO(乾多多 全自动开户  平台采集用户信息后开户) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
 	@RequestMapping("/allAutoUserRegister")
	public String allAutoUserRegister(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo baseAccountInfo = getUserBaseAccountInfo();
		if(baseAccountInfo == null){//用户没有登录 重定向到登录页面
			return "redirect:/user/tologin.action";
		}
		
		
		if( baseAccountInfo.getMobilephone() == null){
			try {
				StringUtil.sendJsonData(response, "请先完善手机信息");
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return  null;		 
		}
		
		if( baseAccountInfo.getRealname() == null  || baseAccountInfo.getCertificationnumber() == null){
			try {
				StringUtil.sendJsonData(response, "请先实名认证");
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return  null;
		}
 		
		String RegisterType = "1";//1表示全自动，2表示半自动
 		String AccountType = "";//空表示个人账户 1表示企业账户
		if(baseAccountInfo.getAccounttype().equals((short)0)){/*用户类型：（个人、企业）1个人 0企业*/
			AccountType = "1";
		}
		
		String Mobile = baseAccountInfo.getMobilephone();/*手机号：（唯一）*/
		String Email = "";
		if(baseAccountInfo.getEmail() != null){
			Email =  baseAccountInfo.getEmail();/*用户email地址（唯一）*/
		}
 		String MobileCode = "";
 		String EmailCode =  "";
 		
 		String RealName =  baseAccountInfo.getRealname(); /*用户名称（真实姓名）*/
		String IdentificationNo =  baseAccountInfo.getCertificationnumber();/*证件号码*/
		String LoanPlatformAccount = baseAccountInfo.getAccountnumber();//用户在网贷平台的账号
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore;//平台标识
		String RandomTimeStamp = "";//时间撮yyyyMMddHHmmssSSS格式
		String Remark1 = baseAccountInfo.getId().toString();//用户ID
		String Remark2 = RegisterType;//1表示全自动，2表示半自动
		String Remark3 = "";
		String ReturnURL = "";
		String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/UserAccountRegister/registerCallBack.action";//乾多多回调地址
 		/*RegisterType + AccountType + Mobile + MobileCode + 
		Email + EmailCode + RealName + IdentificationNo + 
		LoanPlatformAccount + PlatformMoneymoremore + 
		RandomTimeStamp + Remark1 + Remark2 + Remark3 
		+ ReturnURL + NotifyURL*/
		StringBuffer buffer = new StringBuffer();
		buffer
				.append(RegisterType)
				.append(StringUtils.trimToEmpty(AccountType))
				.append(StringUtils.trimToEmpty(Mobile))
				.append(StringUtils.trimToEmpty(MobileCode))
				.append(StringUtils.trimToEmpty(Email))
				.append(StringUtils.trimToEmpty(EmailCode))
				.append(StringUtils.trimToEmpty(RealName))
				.append(StringUtils.trimToEmpty(IdentificationNo))
				.append(StringUtils.trimToEmpty(LoanPlatformAccount))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(StringUtils.trimToEmpty(RandomTimeStamp))
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
 		String plainStr = buffer.toString();
 		//私钥签名
 		String privateResult = "";
 		
 		RsaHelper rsa = RsaHelper.getInstance();
 		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
 		
		Map<String,String> hashMap = new HashMap<String,String>();
 		hashMap.put("RegisterType", RegisterType);
		hashMap.put("AccountType", AccountType);
		hashMap.put("Mobile", Mobile);
		hashMap.put("Email", Email);
		hashMap.put("RealName", RealName);
		hashMap.put("IdentificationNo", IdentificationNo);
		hashMap.put("LoanPlatformAccount", LoanPlatformAccount);
		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
		hashMap.put("RandomTimeStamp",RandomTimeStamp);
		hashMap.put("Remark1", Remark1);
		hashMap.put("Remark2", Remark2);
		hashMap.put("Remark3", Remark3);
		hashMap.put("ReturnURL", ReturnURL);
		hashMap.put("NotifyURL", NotifyURL);
		hashMap.put("SignInfo", privateResult);
		String  resultStr = "";
 		try {
			 resultStr = SignUtils.doPost(hashMap, MMMParams.USERREGISTERACTION);
		} catch (ClientProtocolException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
   		
 		if(StringUtil.isNotEmpty(resultStr)){
 			JSONObject jsonObject = JSONObject.parseObject(resultStr);
 			String ResultCode = jsonObject.getString("ResultCode");
 			String Message    = jsonObject.getString("Message");
  			if(ResultCode != null && ResultCode.equals("88")){//开户成功
 				try {
					request.getRequestDispatcher("/WEB-INF/MMMPages/UserRegister/success.jsp").forward(request, response);
				} catch (ServletException e) {
 					e.printStackTrace();
				} catch (IOException e) {
 					e.printStackTrace();
				}
 			}else{
 				request.setAttribute("RespDesc", Message);
 				try {
					request.getRequestDispatcher("/WEB-INF/MMMPages/UserRegister/other.jsp").forward(request, response);
				} catch (ServletException e) {
 					e.printStackTrace();
				} catch (IOException e) {
 					e.printStackTrace();
				}
 			}
 			
 		}else{
 			request.setAttribute("RespDesc", "开户异常");
 			try {
				request.getRequestDispatcher("/WEB-INF/MMMPages/UserRegister/other.jsp").forward(request, response);
			} catch (ServletException e) {
 				e.printStackTrace();
			} catch (IOException e) {
 				e.printStackTrace();
			}
 		}
		return null;
	}
  	 
	/**
	 * 
	* @Title: UserAccountRegisterCallBack 
	* @Description: TODO(乾多多 用户开户返回结果处理) 
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value= "/registerCallBack")
	synchronized public void UserAccountRegisterCallBack(HttpServletRequest request,HttpServletResponse response){
		String AccountType = request.getParameter("AccountType");
		String AccountNumber = request.getParameter("AccountNumber");
		String Mobile = request.getParameter("Mobile");
		String Email = request.getParameter("Email");
		String RealName = request.getParameter("RealName");
		String IdentificationNo = request.getParameter("IdentificationNo");
		String LoanPlatformAccount = request.getParameter("LoanPlatformAccount");
		String MoneymoremoreId = request.getParameter("MoneymoremoreId");
		String PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore");
		String AuthFee = request.getParameter("AuthFee");
		String AuthState = request.getParameter("AuthState");
		String RandomTimeStamp = request.getParameter("RandomTimeStamp");
		String Remark1 = request.getParameter("Remark1");
		String Remark2 = request.getParameter("Remark2");
		String Remark3 = request.getParameter("Remark3");
		String ResultCode = request.getParameter("ResultCode");
		String Message = request.getParameter("Message");
		String ReturnTimes = request.getParameter("ReturnTimes");
		String SignInfo = request.getParameter("SignInfo");
		System.out.println("==========AccountType==============" + AccountType);
		System.out.println("==========AccountNumber==============" +AccountNumber );
		System.out.println("==========AccountType==============" + AccountType);
		System.out.println("==========Mobile==============" + Mobile);
 		System.out.println("==========Email==============" + Email);
		System.out.println("==========RealName==============" + RealName);
		System.out.println("==========IdentificationNo==============" + IdentificationNo);
		System.out.println("==========LoanPlatformAccount==============" + LoanPlatformAccount);
		System.out.println("==========MoneymoremoreId==============" + MoneymoremoreId);
		System.out.println("==========PlatformMoneymoremore==============" + PlatformMoneymoremore);
		System.out.println("==========AuthFee==============" + AuthFee);
		System.out.println("==========AuthState==============" + AuthState);
		System.out.println("==========RandomTimeStamp==============" + RandomTimeStamp);
		System.out.println("==========Remark1==============" + Remark1);
		System.out.println("==========Remark2==============" + Remark2);
		System.out.println("==========Remark3==============" + Remark3);
		System.out.println("==========ResultCode==============" +ResultCode );
		System.out.println("==========Message==============" + Message);
		System.out.println("==========ReturnTimes==============" + ReturnTimes);
		System.out.println("==========SignInfo==============" + SignInfo);
	  
		/**
		 * 验签格式
		 * AccountType + AccountNumber + Mobile + Email 
		 * + RealName + IdentificationNo + LoanPlatformAccount 
		 * + MoneymoremoreId + PlatformMoneymoremore + AuthFee 
		 * + AuthState + RandomTimeStamp + Remark1 + Remark2 
		 * + Remark3 + ResultCode
		 */
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(AccountType))
				.append(StringUtils.trimToEmpty(AccountNumber))
				.append(StringUtils.trimToEmpty(Mobile))
				.append(StringUtils.trimToEmpty(Email))
				.append(StringUtils.trimToEmpty(RealName))
				.append(StringUtils.trimToEmpty(IdentificationNo))
				.append(StringUtils.trimToEmpty(LoanPlatformAccount))
				.append(StringUtils.trimToEmpty(MoneymoremoreId))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(StringUtils.trimToEmpty(AuthFee))
				.append(StringUtils.trimToEmpty(AuthState))
				.append(StringUtils.trimToEmpty(RandomTimeStamp))
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ResultCode));
 		String str = buffer.toString();
		 
 		RsaHelper rs = RsaHelper.getInstance();
		boolean falg = rs.verifySignature(SignInfo, str, RsaHelper.publicString);//验证签名
		
		if(falg){//验签成功
 			if(ResultCode != null && ResultCode.equals("88") && Remark1 != null){//这里进行开户成功后的逻辑处理
 				UserBaseAccountInfo baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(Remark1));
				if(baseAccountInfo != null){
					UserFsAccountInfo	userFsAccountInfo2 = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(new BigDecimal(Remark1));
					if(userFsAccountInfo2 == null){
 						if(Email != null){
							baseAccountInfo.setEmail(Email);//用户email地址（唯一）
							baseAccountInfo.setIsemailverify((short)1);/*邮件验证 1已验证 0未验证*/
						}
						baseAccountInfo.setRealname(RealName);//用户名称（真实姓名）
						baseAccountInfo.setCertificationnumber(IdentificationNo);/*证件号码*/
						baseAccountInfo.setMobilephone(Mobile);/*手机号：（唯一）*/
 						UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
						userFsAccountInfo.setBaseid(new BigDecimal(Remark1));  //用户ID
						userFsAccountInfo.setUsrcustid(AccountNumber);  //用户商户号
						userFsAccountInfo.setMercustid(PlatformMoneymoremore);  //商户号
						userFsAccountInfo.setRespcode(ResultCode);  //应答返回码
						userFsAccountInfo.setRespdesc(Message);  //应答描述
						userFsAccountInfo.setChannelidentifier("乾多多");  //托管通道标识 如汇付天下 宝付
						userFsAccountInfo.setIsopenfsinfo((short)1);  //是否开通托管账户  1已开通 0未开通
						userFsAccountInfo.setOpeningtime(new Date());  //开通时间
						if(StringUtil.isNotEmpty(Remark3)){
							userFsAccountInfo.setRemark(Remark3);  //备注
						}
						userFsAccountInfo.setUsrname(RealName);  //用户真实名称
						if(AccountType != null && AccountType.equals(("1"))){
							userFsAccountInfo.setAccounttype((short)2);  //账户类型（1个人，2企业）
						}else{
							userFsAccountInfo.setAccounttype((short)1);  //账户类型（1个人，2企业）
						}
						userFsAccountInfo.setMoneymoremoreid(MoneymoremoreId);  //用户的乾多多标识
						userFsAccountInfo.setAuthfee(Double.valueOf(AuthFee));  //姓名匹配手续费
						userFsAccountInfo.setAuthstate(new Short(AuthState));  //实名认证状态 1.未实名认证  2.快捷支付认证 3.其他认证
						userFsAccountInfo.setRandomtimestamp(RandomTimeStamp);  //随机时间戳
						userFsAccountInfo.setRegistertype(new Short(Remark2));  //开户方式  1半自动    2全自动
						
						userFsAccountInfoServiceI.insertSelective(userFsAccountInfo);//添加用户托管信息
						
						userBaseAccountInfoServiceI.updateByPrimaryKeySelective(baseAccountInfo);//更新用户基本信息
						
					}else{
						if(!userFsAccountInfo2.getIsopenfsinfo().equals((short)1)){/*是否开通托管账户  1已开通 0未开通*/
 							userFsAccountInfo2.setUsrcustid(AccountNumber);  //用户商户号
							userFsAccountInfo2.setMercustid(PlatformMoneymoremore);  //商户号
							userFsAccountInfo2.setRespcode(ResultCode);  //应答返回码
							userFsAccountInfo2.setRespdesc(Message);  //应答描述
							userFsAccountInfo2.setChannelidentifier("乾多多");  //托管通道标识 如汇付天下 宝付
							userFsAccountInfo2.setIsopenfsinfo((short)1);  //是否开通托管账户  1已开通 0未开通
							userFsAccountInfo2.setOpeningtime(new Date());  //开通时间
							if(StringUtil.isNotEmpty(Remark3)){
								userFsAccountInfo2.setRemark(Remark3);  //备注
							}
							userFsAccountInfo2.setUsrname(RealName);  //用户真实名称
							if(AccountType != null && AccountType.equals(("1"))){
								userFsAccountInfo2.setAccounttype((short)2);  //账户类型（1个人，2企业）
							}else{
								userFsAccountInfo2.setAccounttype((short)1);  //账户类型（1个人，2企业）
							}
							userFsAccountInfo2.setMoneymoremoreid(MoneymoremoreId);  //用户的乾多多标识
							userFsAccountInfo2.setAuthfee(userFsAccountInfo2.getAuthfee() + Double.valueOf(AuthFee));  //姓名匹配手续费
							userFsAccountInfo2.setAuthstate(new Short(AuthState));  //实名认证状态 1.未实名认证  2.快捷支付认证 3.其他认证
							userFsAccountInfo2.setRandomtimestamp(RandomTimeStamp);  //随机时间戳
							userFsAccountInfo2.setRegistertype(new Short(Remark2));  //开户方式  1半自动    2全自动
							userFsAccountInfoServiceI.updateById(userFsAccountInfo2);//添加用户托管信息
						}
					}
  				}
  			}
			
			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().write("SUCCESS");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
 				e.printStackTrace();
			}
  		}
  	}
	
	
	
	
}
