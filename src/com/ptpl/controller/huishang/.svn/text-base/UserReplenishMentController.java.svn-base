package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.BankCardUtil;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 徽商开户补单 操作类
 * @author admin
 *
 */
public class UserReplenishMentController extends BaseController{
  
	/**
	 * 徽商开户 调单处理
	 * @param IDNO
	 * @return
	 */
	public static Map<String,String> userReplenishMentDeal(String IDNO,String phone,String bankCark){
		Map<String,String> hashMap = new HashMap<>();
		hashMap.put("result", "fail");
		hashMap.put("Msg", "因网络响应不及时！提交银行处理失败！请重新操作！");
		String result = QueryUserCardnbrByCarNo.doParams(IDNO);//根据身份证号码查询电子账号
		if(StringUtil.isEmpty(result)){
			hashMap.put("result", "fail");
			hashMap.put("Msg", "因网络响应不及时！提交银行处理失败！请重新操作！");
			return hashMap;
		}
		
		Map<String,Object>  queryUserCardnbrByCarNoMap = QueryUserCardnbrByCarNo.callBackDeal(result);
		if(!(boolean) queryUserCardnbrByCarNoMap.get("falg")){
			hashMap.put("result", "fail");
			hashMap.put("Msg", (String) queryUserCardnbrByCarNoMap.get("Msg")+", 提交银行处理失败！请重新操作！");
			return hashMap;
		}
		
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) queryUserCardnbrByCarNoMap.get("list");
		if(userFsAccountInfo != null){
			System.out.println("=================="+userFsAccountInfo.getChannelidentifier());
			System.out.println("=================="+userFsAccountInfo.getUsrcustid());
			System.out.println("=================="+userFsAccountInfo.getMercustid());
			System.out.println("=================="+userFsAccountInfo.getTrxid());
			System.out.println("=================="+userFsAccountInfo.getRespcode());
			System.out.println("=================="+userFsAccountInfo.getRespdesc());
			System.out.println("=================="+userFsAccountInfo.getIsopenfsinfo());
			System.out.println("=================="+userFsAccountInfo.getOpeningtime());
			System.out.println("=================="+userFsAccountInfo.getRemark());
			System.out.println("=================="+userFsAccountInfo.getUsrname());
			System.out.println("=================="+userFsAccountInfo.getAccounttype());
			System.out.println("=================="+userFsAccountInfo.getAccPurpose());
			System.out.println("=================="+userFsAccountInfo.getFsmobile());
			String res = QueryUserPhoneByCardnbr.doParams(userFsAccountInfo.getUsrcustid());//根据电子账号查询手机号码
			if(StringUtil.isEmpty(res)){
				return hashMap;
			}
			
			Map<String,Object> queryUserPhoneByCardnbrMap = QueryUserPhoneByCardnbr.callBack(res);
			if(!(boolean) queryUserPhoneByCardnbrMap.get("falg")){//查询失败
				hashMap.put("result", "fail");
				hashMap.put("Msg", (String) queryUserCardnbrByCarNoMap.get("Msg")+", 提交银行处理失败！请重新操作！");
				return hashMap;
			}
			String phone2 = (String)queryUserPhoneByCardnbrMap.get("list");
			userFsAccountInfo.setFsmobile(BaseController.setEncrypt(phone2));//手机号码
			System.out.println("手机号码："+phone2);
			String res2 = QueryUserBankCarByCardnbr.doParams(userFsAccountInfo.getUsrcustid());//根据电子账号查询银行卡信息
			if(StringUtil.isEmpty(res)){
				return hashMap;
			}
			
			Map<String,Object>  queryUserBankCarByCardnbrMap = QueryUserBankCarByCardnbr.callBack(res2);//根据电子账号查询银行卡信息
			if(!(boolean) queryUserBankCarByCardnbrMap.get("falg")){//查询失败
				hashMap.put("result", "fail");
				hashMap.put("Msg", (String) queryUserCardnbrByCarNoMap.get("Msg")+", 提交银行处理失败！请重新操作！");
				return hashMap;
			}
			
			String bankcard = (String) queryUserBankCarByCardnbrMap.get("list");//银行卡账号
			System.out.println("银行卡号码："+bankcard);
			UserFsAccountInfoServiceI userFsAccountInfoServiceI = SpringContextHolder.getBean(UserFsAccountInfoServiceI.class);//托管service
			UserBankCardServiceI userBankCardServiceI = SpringContextHolder.getBean(UserBankCardServiceI.class);//银行卡service
			if(userFsAccountInfoServiceI == null || userBankCardServiceI == null){
				hashMap.put("result", "fail");
				hashMap.put("Msg", (String) queryUserCardnbrByCarNoMap.get("Msg")+", 提交银行处理失败！请重新操作！");
				return hashMap;
			}
			
			UserFsAccountInfo userFsAccountInfo2 = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userFsAccountInfo.getBaseid());
			if(userFsAccountInfo2 != null){
				if(userFsAccountInfo2.getIsopenfsinfo().equals((short)0)){//未开户
 					userFsAccountInfo.setId(userFsAccountInfo2.getId());
 					userFsAccountInfo.setUsrcustid(BaseController.setEncrypt(userFsAccountInfo.getUsrcustid()));
					userFsAccountInfoServiceI.updateById(userFsAccountInfo);//更新第三方账号表
				}
				
				UserBankCard card = new UserBankCard();
				card.setBaseid(userFsAccountInfo.getBaseid());
				card.setCardno(bankcard);
				List<UserBankCard> userBankCards = userBankCardServiceI.findUserBankInfo(card);
				if(!(userBankCards.size() > 0)){
					UserBankCard card2 = new UserBankCard();
					String bankname = "";
					try{
						bankname = BankCardUtil.getNameOfBank(bankcard).substring(0, BankCardUtil.getNameOfBank(bankcard).indexOf("·"));//银行名称
					}catch(Exception e){

					}finally{
						card2.setBaseid(userFsAccountInfo.getBaseid()); // 账户基本信息表id
						card2.setUsername(userFsAccountInfo.getUsrname()); //姓名
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
			    String Msg = "";
				if(!bankcard.equalsIgnoreCase(bankCark)){
					String s2 = bankcard.substring(0,3)+"*******"+bankcard.substring(bankcard.length()-4);
					Msg = "本次绑定的是前期输入的银行卡信息,卡号是："+s2;
				}

				if(!phone2.equalsIgnoreCase(phone)){
					String s3 = phone2.substring(0,3)+"*******"+phone2.substring(phone2.length()-4);				
					Msg ="本次绑定的是前期输入的手机号,手机号码是："+s3;
				}
				
				if(!phone2.equalsIgnoreCase(phone) && !bankcard.equalsIgnoreCase(bankCark)){
					String s2 = bankcard.substring(0,3)+"*******"+bankcard.substring(bankcard.length()-4);
					String s3 = phone2.substring(0,3)+"*******"+phone2.substring(phone2.length()-4);
					Msg ="本次绑定的是前期输入的手机号、银行卡信息。手机号是："+s3+"银行卡号是："+s2;
				}
				
				hashMap.put("result", "success");
				hashMap.put("Msg", "开户成功！"+Msg);
 			}
 		}
  		return hashMap;
	}
		
  
  
}
