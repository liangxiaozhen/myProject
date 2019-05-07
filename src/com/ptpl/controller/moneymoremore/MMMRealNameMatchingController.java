package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.moneymoremore.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/moneymoremore/RealNameMatch")
public class MMMRealNameMatchingController extends BaseController{

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	
	@Autowired
	private AccInExRecordServiceI accInExRecordServiceI;
	
	/**
	 * 
	* @Title: doRealNameMatch 
	* @Description: TODO(姓名匹配接口 调用) 
	* @param @param request
	* @param @param realName
	* @param @param realNameNumber
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	public static  String doRealNameMatch(HttpServletRequest request,String realName,String realNameNumber){
		Assert.notNull(realName, "姓名不能为null");
		Assert.notNull(realNameNumber, "身份证号码不能为null");
  		String PlatformMoneymoremore  = MMMParams.PlatformMoneymoremore;//平台标识
		String IdentityJsonList = "{\"realName\":\""+realName+"\",\"identificationNo\":\""+realNameNumber+"\"}";	//姓名匹配列表
		System.out.println("======"+IdentityJsonList);
		String IdentityJsonList2 = Common.UrlEncoder(IdentityJsonList, "utf-8");
		String RandomTimeStamp	= "";//随机时间戳
		String NotifyURL = StringUtil.getBasePath(request)+ "/moneymoremore/RealNameMatch/RealNameMatchCallBack.action";//后台通知网址
		/*
		 * PlatformMoneymoremore + IdentityJsonList +RandomTimeStamp + NotifyURL
		 * */
		String SignInfo = "";//签名信息
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
		buffer.append(StringUtils.trimToEmpty(IdentityJsonList));
		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
		buffer.append(StringUtils.trimToEmpty(NotifyURL));
		String str = buffer.toString();
		
		RsaHelper helper = RsaHelper.getInstance();
		SignInfo = helper.signData(str, MMMParams.privateString);//RSA 加签
		
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
		hashMap.put("IdentityJsonList", IdentityJsonList2);
		hashMap.put("RandomTimeStamp", RandomTimeStamp);
		hashMap.put("NotifyURL", NotifyURL);
		hashMap.put("SignInfo", SignInfo);
		
		String resultStr = "";
		try {
			resultStr = SignUtils.doPost(hashMap, MMMParams.REALNAMECHECKACTION);//HttpClient 提交
		} catch (ClientProtocolException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return resultStr;
	}
	
	/**
	 * 
	* @Title: RealNameMatchCallBack 
	* @Description: TODO(姓名匹配 接口 回调地址) 
	* @param @param request
	* @param @param response
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value = "/RealNameMatchCallBack",method = {RequestMethod.POST,RequestMethod.GET})
	synchronized public String RealNameMatchCallBack(HttpServletRequest request,HttpServletResponse response){
  		String PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore"); //平台标识
		String IdentityJsonList = request.getParameter("IdentityJsonList");//姓名匹配列表
		String IdentityFailJsonList = request.getParameter("IdentityFailJsonList");//非法姓名匹配列表
		String Amount = request.getParameter("Amount");//匹配费用
		String RandomTimeStamp = request.getParameter("RandomTimeStamp");//随机时间戳
		String ResultCode = request.getParameter("ResultCode");//返回码
		String Message = request.getParameter("Message");//返回信息
		String ReturnTimes = request.getParameter("ReturnTimes");//返回次数
		String SignInfo = request.getParameter("SignInfo");//签名信息
		System.out.println("====PlatformMoneymoremore========="+PlatformMoneymoremore);
		System.out.println("=====IdentityJsonList========"+IdentityJsonList);
		System.out.println("======IdentityFailJsonList======="+IdentityFailJsonList);
		System.out.println("======Amount======="+Amount);
		System.out.println("==RandomTimeStamp==========="+RandomTimeStamp);
		System.out.println("====ResultCode========="+ResultCode);
		System.out.println("=====Message========"+Message);
		System.out.println("=====ReturnTimes========"+ReturnTimes);
		System.out.println("======SignInfo======="+SignInfo);
	 
		  
		String IdentityJsonList2 = Common.UrlDecoder(IdentityJsonList, "utf-8");//姓名匹配列表
		String IdentityFailJsonList2 = Common.UrlDecoder(IdentityFailJsonList, "utf-8");//非法姓名匹配列表
 
 		/**
		 * PlatformMoneymoremore + IdentityJsonList + 
		 * IdentityFailJsonList + RandomTimeStamp + Amount + ResultCode
		 */
 		StringBuffer buffer = new StringBuffer();
 		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
 		buffer.append(StringUtils.trimToEmpty(IdentityJsonList2));
 		buffer.append(StringUtils.trimToEmpty(IdentityFailJsonList2));
 		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
 		buffer.append(StringUtils.trimToEmpty(Amount));
 		buffer.append(StringUtils.trimToEmpty(ResultCode));
  		String str = buffer.toString();
 		
  		RsaHelper helper = RsaHelper.getInstance();
  		boolean falg = helper.verifySignature(SignInfo, str, MMMParams.publicString);
  		if(falg){//验签成功
  			if(ResultCode.equals("88")){//成功才进来
  				if(StringUtil.isNotEmpty(IdentityJsonList2)){
  	  				JSONArray jsonArray = JSONArray.parseArray(IdentityJsonList2);
  	  				for(int i = 0 ; i < jsonArray.size() ;i++){
  	  					JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());//姓名
  	  					String realName = jsonObject.getString("realName");// 姓名
  	  					String identificationNo = jsonObject.getString("identificationNo");//身份证号
  	  					String state = jsonObject.getString("state");//匹配状态
  	  					String AuthFee = jsonObject.getString("AuthFee");//认证金额（单笔）
  	  					
   	  					UserBaseAccountInfo	ubai = new UserBaseAccountInfo(identificationNo,realName);
  	  					UserBaseAccountInfo accountInfo = userBaseAccountInfoServiceI
  	  							.getUserBaseAccountInfoByOneCondition(ubai);
  	  					if(accountInfo != null){
  	  						if(state != null && state.equals("1")){//0、匹配失败 1、匹配成功3、待处理
  	  							accountInfo.setIsreally((short)1);/*是否实名认证: 1已认证 0 未认证*/
  	  							userBaseAccountInfoServiceI.updateByPrimaryKeySelective(accountInfo);
  	  						}
  	  					}
  	  					
   	  					if(accountInfo != null 
  	  							&& AuthFee != null 
  	  							&& Integer.valueOf(AuthFee) > 0){
    	  					AccInExRecord accInExRecord = new AccInExRecord();
    	  					accInExRecord.setBaseid(accountInfo.getId()); //用户ID
    	  					accInExRecord.setPaccount(MMMParams.PlatformMoneymoremore); ///** 平台账户 */
    	  					accInExRecord.setPinamount(0.00); //平台进账
    	  					accInExRecord.setPoutamount(Double.valueOf(AuthFee)); //平台出账
    	  					accInExRecord.setType((short)33); //业务类型
    	  					accInExRecord.setAmount(0.00); //平台账户金额，平台产生费用，进账为+ 出账为-
    	  					accInExRecord.setBalance(0.00); //用户可用余额
    	  					accInExRecord.setInamount(0.00); //收入
    	  					accInExRecord.setOutamount(0.00); //支出
    	  					accInExRecord.setRecordtime(new Date()); //发生时间
    	  					accInExRecord.setDescription("姓名匹配"); //说明
    	  					accInExRecord.setRemark("姓名匹配"); //备注
    	  					accInExRecord.setFreebalance(0.00); //用户冻结余额
    	  					accInExRecord.setTotalbalance(0.00); //用户总余额
    	  					accInExRecord.setAieorderno(StringUtil.getNo()); //流水号
     	  					accInExRecordServiceI.insertSelective(accInExRecord) ;					
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
		return null;
	}
}
