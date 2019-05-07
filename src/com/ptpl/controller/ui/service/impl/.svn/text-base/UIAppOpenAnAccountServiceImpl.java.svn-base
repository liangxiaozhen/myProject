package com.ptpl.controller.ui.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.controller.ui.service.UIAppOpenAnAccountServiceI;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.BankCardUtil;
import com.ptpl.web.util.IdcardValidator;
import com.ptpl.web.util.StringUtil;

public class UIAppOpenAnAccountServiceImpl extends BaseController implements UIAppOpenAnAccountServiceI{

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	
	@Autowired
	private  UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	 
  	@Autowired
	private UserPromoServiceI userPromoService;
 	
 	@Autowired
	private UserBankCardServiceI userBankCardServiceI;
 	
 	@Autowired
	private SMSSendServiceI sMSSendServiceI;
 	
	/**
	 * 
	* @Title: userOpenAnAccount 
	* @Description: TODO(徽商账号开立) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> userOpenAnAccount(HttpServletRequest request,HttpServletResponse response,
			UserBaseAccountInfo userBaseAccountInfo,HttpSession session){
		Map<String,String> hashMap = new HashMap<String,String>();
		UserFsAccountInfo userFsAccountInfostr = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
		if(userFsAccountInfostr != null && userFsAccountInfostr.getIsopenfsinfo() != null && userFsAccountInfostr.getIsopenfsinfo().equals((short)1)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "openfsinfoed");
			hashMap.put(AppSession_Constant.MESSAGE, "银行电子账号已开立！");
			return hashMap;
		}
		
  		String realName 	= request.getParameter("realName");//身份证姓名
		String cardNumber 	= request.getParameter("cardNumber");//身份号码
 		String phone 		= request.getParameter("phone");//银行卡手机号码
 		String bankCard 	= request.getParameter("bankCard");//银行卡号
  		if(userBaseAccountInfo.getIsreally().equals((short)1) 
 				&& userBaseAccountInfo.getCertificationnumber() != null
 				&& userBaseAccountInfo.getRealname() != null){
 			realName 	= userBaseAccountInfo.getRealname();
 			cardNumber  = userBaseAccountInfo.getCertificationnumber();
 		}else {
 			
 			if(StringUtil.isEmpty(realName)){
 				hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
 				hashMap.put(AppSession_Constant.RESULTCODE, "realName_null");
 				hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！身份证姓名不能为空！");
 				return hashMap;
 			}
 			
 			if(StringUtil.isEmpty(cardNumber)){
 				hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
 				hashMap.put(AppSession_Constant.RESULTCODE, "cardNumber_null");
 				hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！身份证号码不能为空！");
 				return hashMap;
 			}
  			
 			 IdcardValidator idcardValidator = new IdcardValidator();
 			 boolean falg = idcardValidator.isValidatedAllIdcard(cardNumber);//身份证检查
 			 if(!falg){//身份证检验失败
 	 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
 				 hashMap.put(AppSession_Constant.RESULTCODE, "cardNumber_error");
 				 hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！请输入正确的身份证号码或联系客服！");
 		 		 return hashMap;
 			 }
 			 
 			 UserBaseAccountInfo ubai = new UserBaseAccountInfo();
 			 ubai.setCertificationnumber(cardNumber);
 			 UserBaseAccountInfo userBaseAccountInfo45 =  userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
 			 if(userBaseAccountInfo45 != null){//身份证号已存在
 	 			 hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
 				 hashMap.put(AppSession_Constant.RESULTCODE, "cardNumber_binding");
 				 hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！您输入的身份证号码已经绑定过其他用户了！");
 		 		 return hashMap;
 			 }
  		}
  		
 		if(StringUtil.isEmpty(phone)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "phone_null");
			hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！银行预留手机号码不能为空！");
			return hashMap;
  		}
 		
 		if(!StringUtil.isMobile(phone)){
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "phone_error");
			hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！请输入正确的手机号码！");
			return hashMap;
 		}
 		
 		if(StringUtil.isEmpty(bankCard)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "bankCard_null");
			hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！银行卡号不能为空！");
			return hashMap;
  		}
 		
 		if(!StringUtil.checkBankCard(bankCard)){
 			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			hashMap.put(AppSession_Constant.RESULTCODE, "bankCard_error");
			hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败！请输入正确的银行卡号码！");
			return hashMap;
		}
 		
 		String certificationtype = "01";
 		if(StringUtils.trimToEmpty(cardNumber).length() < 18){
			certificationtype = "02";
		}
 		
 		Date date = new Date();
		String TRXDATE 		= StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME 		= StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		String SEQNOStr  	= StringUtil.getNo();//交易流水号
		
   		String USNOStr = userBaseAccountInfo.getId().toString();//用户号
   		String GENDER = "";
   		if(userBaseAccountInfo.getSex() != null && (userBaseAccountInfo.getSex().equals((short)1) || userBaseAccountInfo.getSex().equals((short)0))){
    			 GENDER  = userBaseAccountInfo.getSex().equals((short)1) ? "M" : "F";//性别 GENDER F 女  M 男
   		}else{
    		String notStr = cardNumber.substring(cardNumber.length()-2,cardNumber.length()-1);
   			 GENDER = Integer.valueOf(notStr) % 2 == 0 ? "F" : "M";
   		}
 		
	    LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
        reqMap.put("TRXCODE", "5810");//交易代码	TRXCODE
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE

        reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
        reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
        reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
        reqMap.put("COINSTCHANNEL","000001");//合作单位渠道	COINSTCHANNEL
										     /* 000001	手机APP
										      000002	网页
										      000003	微信
										      000004	柜面
										      000005	ESB（资金归集）*/
        reqMap.put("SEQNO",SEQNOStr);//交易流水号	SEQNO
        reqMap.put("SOURCE", "A0");//ESB内部渠道	SOURCE
        reqMap.put("RETCODE", "");//应答码	RETCODE
        reqMap.put("RETMSG", "");//应答码描述	RETMSG
        reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED

        reqMap.put("KEYTYPE", certificationtype);//证件类型 KEYTYPE
        reqMap.put("IDNO", cardNumber);//证件号码 IDNO
        reqMap.put("SURNAME", realName);//姓名 SURNAME

        reqMap.put("MOBILE", phone);//手机号码 MOBILE
      
        reqMap.put("PRODUCT", HSignUtil.PRODUCT);//卡产品编号 PRODUCT
        reqMap.put("SMSFLAG", "1");//是否开通短信通知 SMSFLAG
        reqMap.put("RISK_YN", "1");//风险评估标志 RISK_YN
        reqMap.put("RISK_LEL", "");//风险评级等级 RISK_LEL
        reqMap.put("ACC_TYPE", "1");//账户类型 ACC_TYPE
        reqMap.put("FUCOMCODE", "");//基金公司代码 FUCOMCODE
        reqMap.put("ADNO", "recommandcode");//推荐人代码 ADNO
        reqMap.put("RECARD", bankCard);//绑定卡号 RECARD
        reqMap.put("GENDER", GENDER);//性别 GENDER F 女  M 男
        reqMap.put("USNO", USNOStr);//第三方平台用户编号 USNO
        String respoResult = "" ;
	    try {
	    	respoResult = HSignUtil.HttpClientTask(reqMap, "");
	    } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
	    } catch (Exception e) {
			e.printStackTrace();
	   }
	    
       if(StringUtil.isEmpty(respoResult)){//网络异常，处理不成功
    	  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
    	  hashMap.put(AppSession_Constant.RESULTCODE, "network");
    	  hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,银行账号开立失败!请重新操作或联系客服！");
    	  return hashMap;
        }
        
       List<String> userRegList = new ArrayList<>();
		  userRegList.add("KEYTYPE");// 证件类型 KEYTYPE
		  userRegList.add("IDNO");//证件号码	IDNO
		  userRegList.add("NAME");// 姓名	NAME
		  userRegList.add("CARDNBR");// 电子账户	CARDNBR
		  userRegList.add("USNO");// 第三方平台用户编号	USNO
 		  
		  String result = HSignUtil.getDecryptRSAByte(respoResult);//解密
		  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(result);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5810接口返回报文参数和1到11域拼接一起
	      int listLength = resColumnList.size();
	      if(responseMap.get("RETCODE") == null)
	      {
	    	  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
	    	  hashMap.put(AppSession_Constant.RESULTCODE, "network");
	    	  hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,银行账号开立失败!请重新操作或联系客服！");
	    	  return hashMap;
	      }
	        
	      StringBuffer responseMapMerged = new StringBuffer();
	      for (int i = 0; i < listLength; i++) {
	      	Object columnvalue = responseMap.get(resColumnList.get(i));
	      	if(columnvalue != null){
	      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
	      	}
	      }
	      
	      //验签
	      String responseSign = (String) responseMap.get("SIGN");
	      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
	      if(!verifyResult){
	    	  if(logger.isDebugEnabled()){
	    		  logger.debug("UIAppOpenAnAccountServiceImpl.java userOpenAnAccount app银行开户方法  ,验证签名失败, 用户名是："+userBaseAccountInfo.getLoginname()+"用户手机号是："+userBaseAccountInfo.getMobilephone());
	    	  }
 	    	  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
	    	  hashMap.put(AppSession_Constant.RESULTCODE, "network");
	    	  hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,银行账号开立失败!请重新操作或联系客服！");
	    	  return hashMap;
	      }
	    
 	  	  if (!"00000000".equals(responseMap.get("RETCODE"))){//这里进行给用户提示
 	  		if(logger.isDebugEnabled()){
	    		  logger.debug("UIAppOpenAnAccountServiceImpl.java userOpenAnAccount app银行开户方法  ,开户失败！失败原因："+responseMap.get("RETMSG")+"返回码是："+responseMap.get("RETCODE")+", 用户名是："+userBaseAccountInfo.getLoginname()+",用户手机号是："+userBaseAccountInfo.getMobilephone());
	    	  }
	  		  if("CA110150".equals(responseMap.get("RETCODE"))){//该手机已申领过电子账号
	 	  		  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
		      	  hashMap.put(AppSession_Constant.RESULTCODE, "phone_registered");
		      	  hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败!该手机已申领过电子账号,请重新操作或联系客服！");
		      	  return hashMap;
	  		  }else if("CA101068".equals(responseMap.get("RETCODE"))){//客户已申领过该理财卡产品
	  			  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
		      	  hashMap.put(AppSession_Constant.RESULTCODE, "user_registered");
		      	  hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败!客户已申领过电子账号,请重新操作或联系客服！");
		      	  return hashMap;
	  		  }else if("CA100766".equals(responseMap.get("RETCODE"))){//身份证(18位)校验位错误
 	  			  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
		      	  hashMap.put(AppSession_Constant.RESULTCODE, "idCard_error");
		      	  hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败!身份证(18位)校验位错误,请重新操作或联系客服！");
		      	  return hashMap;
	  		  }else if("CA110130".equals(responseMap.get("RETCODE"))){//该第三方平台编号已关联
	  			  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
		      	  hashMap.put(AppSession_Constant.RESULTCODE, "userNo_error");
		      	  hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败!第三方平台编号已关联,请重新操作或联系客服！");
		      	  return hashMap;
	  		  }else{
 	  			  hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
		      	  hashMap.put(AppSession_Constant.RESULTCODE, "other_error");
		      	  hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立失败!其他未知错误,请重新操作或联系客服！");
		      	  return hashMap;
	  		  }
	  		  
	  	  }
	 
 	  	  	net.sf.json.JSONObject json 	= net.sf.json.JSONObject.fromObject(responseMap);
		    String COINSTCODE 	= json.getString("COINSTCODE"); //合作单位编号	COINSTCODE
			String SEQNO 		= json.getString("SEQNO");// 交易流水号
		    String KEYTYPE  	= json.getString("KEYTYPE");// 证件类型 KEYTYPE
		    String IDNO    	 	= json.getString("IDNO");//证件号码	IDNO
		    String NAME     	= json.getString("NAME"); // 姓名	NAME
		    String CARDNBR  	= json.getString("CARDNBR");// 电子账户	CARDNBR
		    String USNO     	= json.getString("USNO");// 第三方平台用户编号	USNO
		    
		    if(!userBaseAccountInfo.getIsreally().equals((short)1)){
	 			 UserBaseAccountInfo accountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(USNO));
	 			 accountInfo.setRealname(BaseController.setEncrypt(NAME));    /*用户名称（真实姓名）*/
	 			 accountInfo.setCertificationnumber(BaseController.setEncrypt(IDNO)); /*证件号码*/
	 			 accountInfo.setIsreally((short)1);/*是否实名认证: 1已认证 0 未认证*/
	 			 accountInfo.setCertificationtype(BaseController.setEncrypt(certificationtype));///*证件类型  01-身份证18位02-身份证15位20-其它25-企业社会信用代码 注：企业开户时上送20或社会信用号25*/
	 			 String notStr = IDNO.substring(IDNO.length()-2,IDNO.length()-1);
	 			 Short Nostr = (short) (Integer.valueOf(notStr) % 2 == 0 ?  0 : 1);
	 			 accountInfo.setSex(Nostr);/*性别 1男0女*/
	 			 userBaseAccountInfoServiceI.update(accountInfo);
	 		}
		    
		      UserBankCard card = new UserBankCard();
		      card.setBaseid(new BigDecimal(USNO));
		      card.setCardno(bankCard);
		      List<UserBankCard> userBankCards = userBankCardServiceI.findUserBankInfo(card);
		      if(!(userBankCards.size() > 0)){
		    	  UserBankCard card2 = new UserBankCard();
		    	  String bankname = BankCardUtil.getNameOfBank(bankCard).substring(0, BankCardUtil.getNameOfBank(bankCard).indexOf("·"));//银行名称
		    	  card2.setBaseid(new BigDecimal(USNO)); // 账户基本信息表id
		    	  card2.setUsername(BaseController.setEncrypt(NAME)); //姓名
		    	  card2.setCardno(BaseController.setEncrypt(bankCard)); //账户
		    	  card2.setPaycompany("徽商银行"); //绑定通道
		    	  card2.setBindstatus((short)1);// 绑定状态（1.已绑定，2.已解绑）
 		    	  card2.setIsdefaultcard((short)1);// 默认取现卡（1.默认，0非默认）
		    	  card2.setIsfastbindcard((short)1);// 是否快捷绑卡
		    	  card2.setBindtime(new Date());// 绑定银行卡时间
		    	  card2.setBindmode((short)1);// 绑定方式（接口，人工）
		    	  if(StringUtil.isNotEmpty(bankname)){
 		    		  card2.setBankname(bankname);
		    	  }
		    	  userBankCardServiceI.insertSelective(card2);
		      }
 			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(new BigDecimal(USNO));
 			sMSSendServiceI.SMSSend4OpenAccount(userBaseAccountInfo.getMobilephone(), userBaseAccountInfo.getLoginname(), userBaseAccountInfo.getId());//短信通知
			if(userFsAccountInfo == null){
				UserFsAccountInfo accountInfo = new UserFsAccountInfo();
				accountInfo.setBaseid(new BigDecimal(USNO)); //用户ID
				accountInfo.setUsrcustid(BaseController.setEncrypt(CARDNBR)); //用户商户号  乾多多版本 是多多号   徽商银行 是电子账户
				accountInfo.setRespcode(responseMap.get("RETCODE").toString()); //
				accountInfo.setChannelidentifier("徽商银行"); //托管通道标识 如汇付天下 宝付
				accountInfo.setIsopenfsinfo((short)1); //是否开通托管账户  1已开通 0未开通
				accountInfo.setOpeningtime(new Date()); //开通时间
				accountInfo.setUsrname(BaseController.setEncrypt(NAME)); //真实姓名
				accountInfo.setAuthstate((short)3); //实名认证状态 1.未实名认证  2.快捷支付认证 3.其他认证
				accountInfo.setTrxid(SEQNO);//交易唯一标识
				accountInfo.setFsmobile(BaseController.setEncrypt(phone));//开卡手机号
				accountInfo.setTradepass((short)0); /*是否设置交易密码 0 未设置 1 已设置*/
				accountInfo.setMercustid(COINSTCODE);//商户号  乾多多版本 是平台标识号 徽商银行 是合作单位编号
				accountInfo.setAccounttype(userBaseAccountInfo.getAccounttype());//账户类型（1个人，2企业）
 				accountInfo.setAccPurpose((short)1);//账户用途（1普通，2红包，3手续费）
 				int count = 0;
				count = userFsAccountInfoServiceI.insertSelective(accountInfo);
				if(count > 0){
 					session.setAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO, BaseController.getDecryptionUserFsAccountInfoDetail(accountInfo));
					UserPromo up = userPromoService.selectByPrimaryKey(new BigDecimal(USNO));
					if(up != null){
						//真实姓名
						up.setName(BaseController.setEncrypt(NAME));
						//开通托管用户 0未开通 1开通
						up.setIsopenfsinfo((short)1);
						userPromoService.updateByPrimaryKey(up);
					}
 					hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			      	hashMap.put(AppSession_Constant.RESULTCODE, "success");
			        hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立成功！");
			      	return hashMap;
				}else{
 					hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			      	hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			        hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,银行账号开立失败！请重新操作或联系客服！");
			      	return hashMap;
 				}
				
			}else{
 				userFsAccountInfo.setUsrcustid(BaseController.setEncrypt(CARDNBR)); //用户商户号  乾多多版本 是多多号   徽商银行 是电子账户
				userFsAccountInfo.setRespcode(responseMap.get("RETCODE").toString()); //
				userFsAccountInfo.setChannelidentifier("徽商银行"); //托管通道标识 如汇付天下 宝付
				userFsAccountInfo.setIsopenfsinfo((short)1); //是否开通托管账户  1已开通 0未开通
				userFsAccountInfo.setOpeningtime(new Date()); //开通时间
				userFsAccountInfo.setUsrname(BaseController.setEncrypt(NAME)); //真实姓名
				userFsAccountInfo.setAccounttype((short)1); //账户类型（1个人，2企业）
				userFsAccountInfo.setAuthstate((short)3); //实名认证状态 1.未实名认证  2.快捷支付认证 3.其他认证
				userFsAccountInfo.setTrxid(SEQNO);//交易唯一标识
				userFsAccountInfo.setFsmobile(BaseController.setEncrypt(phone));//开卡手机号
				userFsAccountInfo.setTradepass((short)0); /*是否设置交易密码 0 未设置 1 已设置*/
				userFsAccountInfo.setMercustid(COINSTCODE);//商户号  乾多多版本 是平台标识号 徽商银行 是合作单位编号
 				userFsAccountInfo.setAccounttype(userBaseAccountInfo.getAccounttype());//账户类型（1个人，2企业）
 				userFsAccountInfo.setAccPurpose((short)1);//账户用途（1普通，2红包，3手续费）
  				int count = 0;
				count = userFsAccountInfoServiceI.updateById(userFsAccountInfo);
				if(count > 0){
 					session.setAttribute(AppSession_Constant.APPUSERFSACCOUNTINFO, BaseController.getDecryptionUserFsAccountInfoDetail(userFsAccountInfo));
					UserPromo up = userPromoService.selectByPrimaryKey(new BigDecimal(USNO));
					if(up != null){
						//真实姓名
						up.setName(BaseController.setEncrypt(NAME));
						//开通托管用户 0未开通 1开通
						up.setIsopenfsinfo((short)1);
						userPromoService.updateByPrimaryKey(up);
						
					}
					hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
			      	hashMap.put(AppSession_Constant.RESULTCODE, "success");
			        hashMap.put(AppSession_Constant.MESSAGE, "银行账号开立成功！");
			      	return hashMap;
				}else{
					hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
			      	hashMap.put(AppSession_Constant.RESULTCODE, AppSession_Constant.FAIL);
			        hashMap.put(AppSession_Constant.MESSAGE, "因网络响应不及时,银行账号开立失败！请重新操作或联系客服！");
			      	return hashMap;
				}
			}
 	}
 	
	/**
	 * 
	* @Title: tradePassWord 
	* @Description: TODO(设置/修改交易密码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @param userFsAccountInfo
	* @param @param session
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,String> tradePassWord(HttpServletRequest request,HttpServletResponse response,
			UserBaseAccountInfo userBaseAccountInfo,UserFsAccountInfo userFsAccountInfo,HttpSession session){
		Map<String,String> hashMap = new HashMap<String,String>();
		
		String tradePassWordCheck = (String) session.getAttribute(AppSession_Constant.TRADEPASSWORDCHECK);//判断修改交易密码是否验证
		if(StringUtil.isEmpty(tradePassWordCheck)){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
	      	hashMap.put(AppSession_Constant.RESULTCODE, "check_error");
	        hashMap.put(AppSession_Constant.MESSAGE, "未通过验证！请先验证手机短信验证码！");
	      	return hashMap;
		}
 		
		if(userFsAccountInfo == null || userFsAccountInfo.getIsopenfsinfo() == null){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
	      	hashMap.put(AppSession_Constant.RESULTCODE, "openfsinfo_error");
	        hashMap.put(AppSession_Constant.MESSAGE, "银行电子账号未开通！请先开户！");
	      	return hashMap;
		}
 		
		if(!userFsAccountInfo.getIsopenfsinfo().equals((short)1) 
				|| userFsAccountInfo.getUsrcustid() == null 
				|| userBaseAccountInfo.getCertificationnumber() == null
				|| userBaseAccountInfo.getCertificationtype() == null
				|| userBaseAccountInfo.getRealname() == null
				|| userFsAccountInfo.getFsmobile() == null){
			hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.PARAMSERROR);
	      	hashMap.put(AppSession_Constant.RESULTCODE, "openfsinfo_error");
	        hashMap.put(AppSession_Constant.MESSAGE, "银行电子账号未开通！请先开户！");
	      	return hashMap;
		}
  		
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
 		String VERSION 			= "v20160602";//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
 		String CARDNBR 			= userFsAccountInfo.getUsrcustid();//CARDNBR	账户	A(19)	是	
		String IDNO 			= userBaseAccountInfo.getCertificationnumber();//IDNO	证件号码	A(18)	是	
		String IDTYPE 			= userBaseAccountInfo.getCertificationtype();//IDTYPE	证件类型	A(2)	是	01-身份证18位
										//02-身份证15位	
										//20-其它
										//25-企业社会信用代码
										//注：企业开户时上送20或社会信用号25
		String PHONE 			= userFsAccountInfo.getFsmobile();//PHONE	手机号码	A(12)	是	
		String NAME 			= userBaseAccountInfo.getRealname();//NAME	姓名	A(60)	是	
		
		String BANKCODE 		= HSignUtil.BANKCODE;//BANKCODE	银行代码	A(8)	是	
		String RESETPWD_SURL 	= StringUtil.getBasePath(request)+"/user/tologin.action";//RESETPWD_SURL	密码设置成功跳转链接	A(256)	是	密码设置成功后跳转的url
		String RESETPWD_FURL 	= StringUtil.getBasePath(request)+"/user/tologin.action";//RESETPWD_FURL	密码设置失败跳转链接	A(256)	是	密码设置失败后跳转的url
		String BACKGROUND_URL 	= StringUtil.getBasePath(request)+"/huishang/tradePassword/CallBack.action";//BACKGROUND_URL	后台响应链接	A(256)	否	用于接收后台响应
		String COINSTCODE 		= HSignUtil.COINSTCODE;//COINSTCODE	合作单位编号	A(6)	是	
		String COINSTCHANNEL 	= "000001";//COINSTCHANNEL	合作单位渠道	A(6)	是	
		String SIGN 			= "";//SIGN 	签名		是	
		
 		LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("VERSION", VERSION);//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
        reqMap.put("TRXDATE", TRXDATE);
        reqMap.put("TRXTIME", TRXTIME);
        reqMap.put("CARDNBR", CARDNBR);
        reqMap.put("IDNO", IDNO);
        reqMap.put("IDTYPE", IDTYPE);
        reqMap.put("PHONE", PHONE);
        reqMap.put("NAME", NAME);
        reqMap.put("BANKCODE", BANKCODE);
        reqMap.put("RESETPWD_SURL", RESETPWD_SURL);      
        reqMap.put("RESETPWD_FURL", RESETPWD_FURL);
        reqMap.put("BACKGROUND_URL", BACKGROUND_URL);
        reqMap.put("COINSTCODE", COINSTCODE);
        reqMap.put("COINSTCHANNEL",COINSTCHANNEL);
        try {
			SIGN =  HSignUtil.getRASSign(reqMap);
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		} catch (Exception e) {
 			e.printStackTrace();
		}
        reqMap.put("SIGN", SIGN);
        String  tradePassWordAction = "https://onlineuat.cupdata.com:50005/dbesbsit/app/to/pinsetting";
        
        String input = "";
        Object entry = null;
        for (Iterator it =  reqMap.keySet().iterator();it.hasNext();){
  		     entry = it.next();
   		     hashMap.put(entry.toString(), entry.toString());
   		     hashMap.put(entry.toString()+"Value", reqMap.get(entry).toString());
        }
        hashMap.put("SIGNValue", SIGN);
        hashMap.put("url", tradePassWordAction);
        hashMap.put("method", "post");
        hashMap.put(AppSession_Constant.RESULT, AppSession_Constant.SUCCESS);
      	hashMap.put(AppSession_Constant.RESULTCODE, "success");
        hashMap.put(AppSession_Constant.MESSAGE, "数据调用成功");
  		return hashMap;
	}
	
	/**
	 * 
	* @Title: tradePassWord 
	* @Description: TODO(设置/修改交易密码) 
	* @param @param request
	* @param @param response
	* @param @param userBaseAccountInfo
	* @param @param userFsAccountInfo
	* @param @param session
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public void tradePassWordHtml(HttpServletRequest request,HttpServletResponse response,
			UserBaseAccountInfo userBaseAccountInfo,UserFsAccountInfo userFsAccountInfo,HttpSession session){
 		String tradePassWordCheck = (String) session.getAttribute(AppSession_Constant.TRADEPASSWORDCHECK);//判断修改交易密码是否验证
		if(tradePassWordCheck == null){
			 try {
				 request.setAttribute("Msg", "未通过交易密码验证！请先验证短信验证码！");
				 request.getRequestDispatcher("/WEB-INF/pages/tradePassWord/tradePassWordError.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
 				e.printStackTrace();
			}
			 return;
		}
		
		if(!tradePassWordCheck.equals(AppSession_Constant.TRADEPASSWORDCHECK)){
			try {
				 request.setAttribute("Msg", "未通过交易密码验证！请先验证短信验证码！");
				 request.getRequestDispatcher("/WEB-INF/pages/tradePassWord/tradePassWordError.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			 return;
		}
 		
		if(userFsAccountInfo == null || userFsAccountInfo.getIsopenfsinfo() == null){
			 try {
				 request.setAttribute("Msg", "未开通银行电子账号！请先开通银行电子账号！");
				request.getRequestDispatcher("/WEB-INF/pages/tradePassWord/tradePassWordError.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
 				e.printStackTrace();
			}
			 return;
		}
 		
		if(!userFsAccountInfo.getIsopenfsinfo().equals((short)1) 
				|| userFsAccountInfo.getUsrcustid() == null 
				|| userBaseAccountInfo.getCertificationnumber() == null
				|| userBaseAccountInfo.getCertificationtype() == null
				|| userBaseAccountInfo.getRealname() == null
				|| userFsAccountInfo.getFsmobile() == null){
			 try {
				 request.setAttribute("Msg", "未开通银行电子账号！请先开通银行电子账号！");
				request.getRequestDispatcher("/WEB-INF/pages/tradePassWord/tradePassWordError.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
 				e.printStackTrace();
			}
			 return;
		}
  		
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
 		String VERSION 			= "v20160602";//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
 		String CARDNBR 			= userFsAccountInfo.getUsrcustid();//CARDNBR	账户	A(19)	是	
		String IDNO 			= userBaseAccountInfo.getCertificationnumber();//IDNO	证件号码	A(18)	是	
		String IDTYPE 			= userBaseAccountInfo.getCertificationtype();//IDTYPE	证件类型	A(2)	是	01-身份证18位
										//02-身份证15位	
										//20-其它
										//25-企业社会信用代码
										//注：企业开户时上送20或社会信用号25
		String PHONE 			= userFsAccountInfo.getFsmobile();//PHONE	手机号码	A(12)	是	
		String NAME 			= userBaseAccountInfo.getRealname();//NAME	姓名	A(60)	是	
		
		String BANKCODE 		= HSignUtil.BANKCODE;//BANKCODE	银行代码	A(8)	是	
		String RESETPWD_SURL 	= StringUtil.getBasePath(request)+"/user/tologin.action";//RESETPWD_SURL	密码设置成功跳转链接	A(256)	是	密码设置成功后跳转的url
		String RESETPWD_FURL 	= StringUtil.getBasePath(request)+"/user/tologin.action";//RESETPWD_FURL	密码设置失败跳转链接	A(256)	是	密码设置失败后跳转的url
		String BACKGROUND_URL 	= StringUtil.getBasePath(request)+"/huishang/tradePassword/CallBack.action";//BACKGROUND_URL	后台响应链接	A(256)	否	用于接收后台响应
		String COINSTCODE 		= HSignUtil.COINSTCODE;//COINSTCODE	合作单位编号	A(6)	是	
		String COINSTCHANNEL 	= "000001";//COINSTCHANNEL	合作单位渠道	A(6)	是	
		String SIGN 			= "";//SIGN 	签名		是	
		
 		LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("VERSION", VERSION);//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
        reqMap.put("TRXDATE", TRXDATE);
        reqMap.put("TRXTIME", TRXTIME);
        reqMap.put("CARDNBR", CARDNBR);
        reqMap.put("IDNO", IDNO);
        reqMap.put("IDTYPE", IDTYPE);
        reqMap.put("PHONE", PHONE);
        reqMap.put("NAME", NAME);
        reqMap.put("BANKCODE", BANKCODE);
        reqMap.put("RESETPWD_SURL", RESETPWD_SURL);      
        reqMap.put("RESETPWD_FURL", RESETPWD_FURL);
        reqMap.put("BACKGROUND_URL", BACKGROUND_URL);
        reqMap.put("COINSTCODE", COINSTCODE);
        reqMap.put("COINSTCHANNEL",COINSTCHANNEL);
        
        try {
			SIGN =  HSignUtil.getRASSign(reqMap);
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		} catch (Exception e) {
 			e.printStackTrace();
		}
        reqMap.put("SEQNO",StringUtil.getNo());//20170615 添加交易流水号  路由需要记录 不需要加密到sign
        reqMap.put("SIGN", SIGN);
        request.setAttribute("map", reqMap);
        try {
			request.getRequestDispatcher("/WEB-INF/pages/tradePassWord/tradePassWord.jsp").forward(request, response);
		} catch (ServletException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
        session.removeAttribute(AppSession_Constant.TRADEPASSWORDCHECK);
        return;
 	}
	
}
