package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.web.util.BankCardUtil;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: UserRegisterController 
 * @Description: TODO(徽商银行 开户Controller) 
 * @author cjm 
 * @date 2017年3月31日 下午4:44:32 
 *
 */
@RequestMapping("/huishang/userRegister")
@Controller
public class UserRegisterController extends BaseController{

	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;

	@Autowired
	private UserBankCardServiceI userBankCardServiceI;

	@Autowired
	private UserPromoServiceI userPromoService; 

	@Autowired
	private SMSSendServiceI sMSSendServiceI;

	/**
	 * 
	 * @Title: doParams 
	 * @Description: TODO(徽商银行开户) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws UnsupportedEncodingException
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	 */
	@RequestMapping(value = "/OpenAnAccount",method = {RequestMethod.POST,RequestMethod.GET})
	public String doParams(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, Exception{
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		Map<String,String> hashMap = new HashMap<String,String>();
		if(accountInfo == null){
			hashMap.put("result", "logout");//用户未登陆
			hashMap.put("Msg", "操作超时！请重新登录！");
			String str = JSON.toJSONString(hashMap);
			StringUtil.sendJsonData(response, str);
			return null;
		}
		 

		if(StringUtil.isEmpty(accountInfo.getRealname()) || StringUtil.isEmpty(accountInfo.getCertificationnumber())){
			hashMap.put("result", "fail");//参数错误
			hashMap.put("Msg", "参数错误！身份证号码或身份证姓名找不到！");//
			String str = JSON.toJSONString(hashMap);
			StringUtil.sendJsonData(response, str);
			return null;
		}

		String bankcard  = request.getParameter("bankcard");//绑定卡号 RECARD
		String phone  = request.getParameter("phone");//绑定卡号 RECARD

		if(StringUtil.isEmpty(bankcard)){
			hashMap.put("result", "fail");//银行卡号为空
			hashMap.put("Msg", "参数错误！银行卡号不能为空！请输入银行卡号");
			String str = JSON.toJSONString(hashMap);
			StringUtil.sendJsonData(response, str);
			return null;
		}

		if(StringUtil.isEmpty(phone)){
			hashMap.put("result", "fail");//手机号码为空
			hashMap.put("Msg", "参数错误！手机号码不能为空！请输入手机号码");
			String str = JSON.toJSONString(hashMap);
			StringUtil.sendJsonData(response, str);
			return null;
		}

		// 		if(!StringUtil.checkBankCard(bankcard)){
		//			 hashMap.put("result", "fail");//银行卡号错误
		//			 hashMap.put("Msg", "参数错误！银行卡号格式错误！请输入正确的银行卡号");
		//			 String str = JSON.toJSONString(hashMap);
		//	    	 StringUtil.sendJsonData(response, str);
		//	    	 return null;
		//		}

		if(!StringUtil.isMobile(phone)){
			hashMap.put("result", "fail");//手机格式错误
			hashMap.put("Msg", "参数错误！手机格式错误！请输入正确的手机号");
			String str = JSON.toJSONString(hashMap);
			StringUtil.sendJsonData(response, str);
			return null;
		}



		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
		String SEQNO   = StringUtil.getNo();//交易流水号

		String IDNO	   = accountInfo.getCertificationnumber();//身份证号码
		String SURNAME = accountInfo.getRealname();//身份证姓名
		String USNO	   = accountInfo.getId().toString();//用户号
		String GENDER  = accountInfo.getSex().equals((short)1) ? "M" : "F";//性别 GENDER F 女  M 男
		String KEYTYPE = accountInfo.getCertificationtype();//证件类型 KEYTYPE

		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		reqMap.put("TRXCODE", "5810");//交易代码	TRXCODE
		reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE

		reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
		reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
		reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
		reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
		reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
		reqMap.put("SOURCE",HSignUtil.SOURCE);//ESB内部渠道	SOURCE
		reqMap.put("RETCODE", "");//应答码	RETCODE
		reqMap.put("RETMSG", "");//应答码描述	RETMSG
		reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED

		reqMap.put("KEYTYPE", KEYTYPE);//证件类型 KEYTYPE
		reqMap.put("IDNO", IDNO);//证件号码 IDNO
		reqMap.put("SURNAME", SURNAME);//姓名 SURNAME

		reqMap.put("MOBILE", phone);//手机号码 MOBILE

		reqMap.put("PRODUCT", HSignUtil.PRODUCT);//卡产品编号 PRODUCT
		reqMap.put("SMSFLAG", "1");//是否开通短信通知 SMSFLAG
		reqMap.put("RISK_YN", "1");//风险评估标志 RISK_YN
		reqMap.put("RISK_LEL", "");//风险评级等级 RISK_LEL
		reqMap.put("ACC_TYPE", "1");//账户类型 ACC_TYPE
		reqMap.put("FUCOMCODE", "");//基金公司代码 FUCOMCODE
		reqMap.put("ADNO", "recommandcode");//推荐人代码 ADNO
		reqMap.put("RECARD", bankcard);//绑定卡号 RECARD
		reqMap.put("GENDER", GENDER);//性别 GENDER F 女  M 男
		reqMap.put("USNO", USNO);//第三方平台用户编号 USNO
		//	      reqMap.put("RESERVED",bankcard);//保留域
		String result = HSignUtil.HttpClientTask(reqMap, "");
		if(StringUtil.isEmpty(result)){//网络异常，处理不成功
			hashMap.put("result", "fail");
			hashMap.put("Msg", "因网络响应不及时！操作失败！请重新操作！");
			String str = JSON.toJSONString(hashMap);
			StringUtil.sendJsonData(response, str);
			return null;
		}

		Map<String,String> hashMap2 = callBack(result,phone);
 		if(hashMap2.get("result").equals("success")){
			UserBankCard card = new UserBankCard();
			card.setBaseid(accountInfo.getId());
			card.setCardno(bankcard);
			List<UserBankCard> userBankCards = userBankCardServiceI.findUserBankInfo(card);
			if(!(userBankCards.size() > 0)){
				UserBankCard card2 = new UserBankCard();
				String bankname = "";
				try{
					bankname = BankCardUtil.getNameOfBank(bankcard).substring(0, BankCardUtil.getNameOfBank(bankcard).indexOf("·"));//银行名称
				}catch(Exception e){

				}finally{
					card2.setBaseid(accountInfo.getId()); // 账户基本信息表id
					card2.setUsername(BaseController.setEncrypt(accountInfo.getRealname())); //姓名
					card2.setCardno(BaseController.setEncrypt(bankcard)); //账户
					card2.setPaycompany("徽商银行"); //绑定通道
					card2.setBindstatus((short)1);// 绑定状态（1.已绑定，2.已解绑）
					card2.setIsdefaultcard((short)1);// 默认取现卡（1.默认，0非默认）
					card2.setIsfastbindcard((short)1);// 是否快捷绑卡
					card2.setBindtime(new Date());// 绑定银行卡时间
					card2.setBindmode((short)1);// 绑定方式（接口，人工）
					card2.setCardtype((short)2);// 卡类型（1信用卡、2借记卡）
					if(StringUtil.isNotEmpty(bankname)){
						card2.setBankname(bankname);
					}else{
						card2.setBankname("徽商银行");
					}
					userBankCardServiceI.insertSelective(card2);
				}
			}
			UserFsAccountInfo accountInfo2 = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(accountInfo.getId());
			if(accountInfo2 == null){
				UserFsAccountInfo accountInfo3 = new UserFsAccountInfo();
				accountInfo3.setBaseid(accountInfo.getId());  //用户ID
				accountInfo3.setAccounttype(accountInfo.getAccounttype());//账户类型（1个人，2企业）
				accountInfo3.setFsmobile(BaseController.setEncrypt(phone));//开卡手机号
				userFsAccountInfoServiceI.insertSelective(accountInfo3);
			}else{
				accountInfo2.setAccounttype(accountInfo.getAccounttype());//账户类型（1个人，2企业）
				accountInfo2.setFsmobile(BaseController.setEncrypt(phone));//开卡手机号
				userFsAccountInfoServiceI.updateById(accountInfo2);
			}
			sMSSendServiceI.SMSSend4OpenAccount(accountInfo.getMobilephone(), accountInfo.getLoginname(), accountInfo.getId());
		}
		
		if(hashMap2.get("result").equals("resiged")){//开户掉单处理
			Map<String,String> hashMap3 = UserReplenishMentController.userReplenishMentDeal(IDNO,phone,bankcard);
			String str = JSON.toJSONString(hashMap3);
			StringUtil.sendJsonData(response, str);
			return null;
		}
		
		String str = JSON.toJSONString(hashMap2);
		StringUtil.sendJsonData(response, str);
		return null;
	}

	public Map<String,String> callBack(String respoResult,String phone){
		Map<String,String> hashMap = new HashMap<>();
		List<String> userRegList = new ArrayList<>();
		userRegList.add("KEYTYPE");// 证件类型 KEYTYPE
		userRegList.add("IDNO");//证件号码	IDNO
		userRegList.add("NAME");// 姓名	NAME
		userRegList.add("CARDNBR");// 电子账户	CARDNBR
		userRegList.add("USNO");// 第三方平台用户编号	USNO
		//		  userRegList.add("RESERVED");// 保留域 RESERVED

		String result = HSignUtil.getDecryptRSAByte(respoResult);//解密
		Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(result);//数据转化Map

		List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5810接口返回报文参数和1到11域拼接一起
		int listLength = resColumnList.size();
		if(responseMap.get("RETCODE") == null)
		{
			hashMap.put("result", "fail");// "因网络响应不及时,处理失败";
			hashMap.put("Msg", "因网络响应不及时！操作失败！请重新操作！");
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

			hashMap.put("result", "fail");// "因网络响应不及时,处理失败";
			hashMap.put("Msg", "因网络响应不及时！操作失败！请重新操作！");
			return hashMap;
		}
		System.out.println(responseMap.get("RETCODE")+"=============");
		if (!"00000000".equals(responseMap.get("RETCODE"))){//这里进行给用户提示
			if("CA110150".equals(responseMap.get("RETCODE"))){//该手机已申领过电子账号
				hashMap.put("result", "fail");
				hashMap.put("Msg", "返回码："+responseMap.get("RETCODE")+", 该手机已申领过电子账号!请联系客服！");
			}else if("CA101068".equals(responseMap.get("RETCODE"))){//客户已申领过该理财卡产品
				hashMap.put("result", "fail");
				hashMap.put("Msg", "返回码："+responseMap.get("RETCODE")+", 该客户已申领过电子账号!请联系客服！");
			}else if("CA100766".equals(responseMap.get("RETCODE"))){//身份证(18位)校验位错误
				hashMap.put("result", "fail");
				hashMap.put("Msg", "返回码："+responseMap.get("RETCODE")+", 身份证(18位)校验位错误!请联系客服！");
			}else if("CA110130".equals(responseMap.get("RETCODE"))){//该第三方平台编号已关联
				hashMap.put("result", "resiged");
				hashMap.put("Msg", "返回码："+responseMap.get("RETCODE")+", 第三方平台编号已关联!请联系客服！");
 			}else{
				hashMap.put("result", "fail");//其他错误
				hashMap.put("Msg", "返回码："+responseMap.get("RETCODE")+", 其他错误!请联系客服！");
			}
			return hashMap;
		}

		JSONObject json 	= JSONObject.fromObject(responseMap);
		String COINSTCODE 	= json.getString("COINSTCODE"); //合作单位编号	COINSTCODE
		String SEQNO 		= json.getString("SEQNO");// 交易流水号
		String KEYTYPE  	= json.getString("KEYTYPE");// 证件类型 KEYTYPE
		String IDNO    	 	= json.getString("IDNO");//证件号码	IDNO
		String NAME     	= json.getString("NAME"); // 姓名	NAME
		String CARDNBR  	= json.getString("CARDNBR");// 电子账户	CARDNBR
		String USNO     	= json.getString("USNO");// 第三方平台用户编号	USNO

		//			System.out.println("=====KEYTYPE====="+KEYTYPE);
		//			System.out.println("=====IDNO====="+IDNO);
		//			System.out.println("=====NAME====="+NAME);
		//			System.out.println("=====USNO====="+USNO);
		//			System.out.println("=====CARDNBR====="+CARDNBR);
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(new BigDecimal(USNO));
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
			accountInfo.setTradepass((short)0); /*是否设置交易密码 0 未设置 1 已设置*/
			accountInfo.setMercustid(COINSTCODE);//商户号  乾多多版本 是平台标识号 徽商银行 是合作单位编号
			accountInfo.setAccPurpose((short)1);//账户用途（1普通，2红包，3手续费）
			int count = 0;
			count = userFsAccountInfoServiceI.insertSelective(accountInfo);
			if(count > 0){
				UserPromo up = userPromoService.selectByPrimaryKey(new BigDecimal(USNO));
				if(up != null){
					//真实姓名
					up.setName(BaseController.setEncrypt(NAME));
					//开通托管用户 0未开通 1开通
					up.setIsopenfsinfo((short)1);
					userPromoService.updateByPrimaryKey(up);
				}
				hashMap.put("result", "success");//开户成功;
				hashMap.put("Msg", "开户成功！");
				return hashMap;
			}else{
				hashMap.put("result", "fail");// 开户失败
				hashMap.put("Msg", "已开户成功！因网络响应不及时!银行返回数据保存失败！请联系客服处理！");
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
			userFsAccountInfo.setTradepass((short)0); /*是否设置交易密码 0 未设置 1 已设置*/
			userFsAccountInfo.setMercustid(COINSTCODE);//商户号  乾多多版本 是平台标识号 徽商银行 是合作单位编号
			userFsAccountInfo.setAccPurpose((short)1);//账户用途（1普通，2红包，3手续费）
			int count = 0;
			count = userFsAccountInfoServiceI.updateById(userFsAccountInfo);
			if(count > 0){
				UserPromo up = userPromoService.selectByPrimaryKey(new BigDecimal(USNO));
				if(up != null){
					//真实姓名
					up.setName(BaseController.setEncrypt(NAME));
					//开通托管用户 0未开通 1开通
					up.setIsopenfsinfo((short)1);
					userPromoService.updateByPrimaryKey(up);

				}
				hashMap.put("result", "success");//开户成功;
				hashMap.put("Msg", "开户成功！");
				return hashMap;
			}else{
				hashMap.put("result", "fail");//开户失败;
				hashMap.put("Msg", "已开户成功！因网络响应不及时!银行返回数据保存失败！请联系客服处理！");
				return hashMap;
			}
		}
	}




	/**
	 * 用户开户信息已提交徽商 并开户成功了，但是徽商没有返回信息  
	 * 
	 * 这里进行开户掉单处理  根据手机号码查询用户信息
	 */
	
}
