package com.ptpl.controller.huishang;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserLog;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserLogServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: TradePsswordController 
* @Description: TODO(交易密码设置和修改) 
* @author cjm 
* @date 2017年4月7日 上午11:45:26 
*
 */
@RequestMapping("/huishang/tradePassword")
@Controller
public class TradePsswordController extends BaseController{

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	
	@Autowired
	private UserLogServiceI userLogServiceI;
	
	@Autowired
	private SMSSendServiceI sMSSendServiceI;
	/**
	 * 
	* @Title: tradePsswordList 
	* @Description: TODO(跳转 修改交易密码页面) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping("/tradePsswordList")
	public String tradePsswordList(HttpServletRequest request ,HttpServletResponse response){
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(accountInfo == null){
			return "redirect:/user/tologin.action";
		}
		
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(accountInfo.getId()); 
		if(userFsAccountInfo == null 
				|| !userFsAccountInfo.getIsopenfsinfo().equals((short)1)  
				|| userFsAccountInfo.getFsmobile() == null ){
 			return "redirect:/user/securitycenter/list.action";
		}
		
		if(userFsAccountInfo.getTradepass() != null && userFsAccountInfo.getTradepass().equals((short)1)){
 			request.setAttribute("message", "修改");
		}else{
			request.setAttribute("message", "设置");
		}
		return "user/manager/securitycenter/tradePassword";
	}
	
	/**
	 * 
	* @Title: sendSSM 
	* @Description: TODO(交易密码修改 发送短信) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping(value = "/sendSSm",method = RequestMethod.POST)
	public String sendSSM(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(accountInfo == null){
			return "redirect:/user/tologin.action";
		}
		
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(accountInfo.getId()); 
		Map<String,String> hashMap = new HashMap<>();
		if(userFsAccountInfo == null || !userFsAccountInfo.getIsopenfsinfo().equals((short)1)  ||userFsAccountInfo.getFsmobile() == null ){
			hashMap.put("result", "params_error");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
//		Random random = new Random();
//		String code = String.valueOf(random.nextInt(899999)+100000);
//    	boolean falg = sMSSendServiceI.SMSSend4Code(accountInfo.getMobilephone(), code, accountInfo.getLoginname(), accountInfo.getId());
 		
		String code = "111111";
	    boolean falg = true;
	    if(falg){
			session.setAttribute(Session_Constant.TRADEPASSWORDPHONESSMCODE, code);//短信验证码
 	    	hashMap.put("result", "success"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }else{
	    	hashMap.put("result", "fail"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
	    }
 	}
	
	/**
	 * 
	* @Title: isTradePssword 
	* @Description: TODO(跳转 徽商交易密码设置) 
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping("/dotradePassword")
	public  String isTradePssword(HttpServletRequest request,HttpServletResponse response){
		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
		if(accountInfo == null){
			return "redirect:/user/tologin.action";
		}
		
		 String code = request.getParameter("code");
 		 String tocode = (String) request.getSession().getAttribute(Session_Constant.TRADEPASSWORDPHONESSMCODE);
		 Map<String,String> hashMap = new HashMap<>();
 		 if(StringUtil.isEmpty(code)){
 			hashMap.put("result", "code_null"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
 		 }
 		 
 		if(StringUtil.isEmpty(tocode)){
 			hashMap.put("result", "tocode_null"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
 		 }
 		
 		if(!tocode.equals(code)){
 			hashMap.put("result", "code_error"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
 		 }
  		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(accountInfo.getId());
  		userFsAccountInfo = BaseController.getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//敏感数据解密
  		if(userFsAccountInfo == null 
  				|| !userFsAccountInfo.getIsopenfsinfo().equals((short)1)
  				|| userFsAccountInfo.getUsrcustid() == null
  				|| accountInfo.getCertificationnumber() == null
  				|| accountInfo.getCertificationtype() == null
  				|| userFsAccountInfo.getFsmobile() == null
  				|| accountInfo.getRealname() == null){
  			hashMap.put("result", "params_error"); 
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
	 			e.printStackTrace();
			}
			 return null;
  		}
 		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
 		
		String VERSION 			= "v20160602";//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
 		String CARDNBR 			= userFsAccountInfo.getUsrcustid();//CARDNBR	账户	A(19)	是	
		String IDNO 			= accountInfo.getCertificationnumber();//IDNO	证件号码	A(18)	是	
		String IDTYPE 			= accountInfo.getCertificationtype();//IDTYPE	证件类型	A(2)	是	01-身份证18位
										//02-身份证15位	
										//20-其它
										//25-企业社会信用代码
										//注：企业开户时上送20或社会信用号25
		String PHONE 			= userFsAccountInfo.getFsmobile();//PHONE	手机号码	A(12)	是	
		String NAME 			= accountInfo.getRealname();//NAME	姓名	A(60)	是	
		
		String BANKCODE 		= HSignUtil.BANKCODE;//BANKCODE	银行代码	A(8)	是	
		String RESETPWD_SURL 	= StringUtil.getBasePath(request)+"/user/tologin.action";//RESETPWD_SURL	密码设置成功跳转链接	A(256)	是	密码设置成功后跳转的url
		String RESETPWD_FURL 	= StringUtil.getBasePath(request)+"/user/tologin.action";//RESETPWD_FURL	密码设置失败跳转链接	A(256)	是	密码设置失败后跳转的url
		String BACKGROUND_URL 	= StringUtil.getBasePath(request)+"/huishang/tradePassword/CallBack.action";//BACKGROUND_URL	后台响应链接	A(256)	否	用于接收后台响应
		String COINSTCODE 		= HSignUtil.COINSTCODE;//COINSTCODE	合作单位编号	A(6)	是	
		String COINSTCHANNEL 	= "000002";//COINSTCHANNEL	合作单位渠道	A(6)	是	
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
        String input = "";
        Object entry = null;
        for (Iterator it =  reqMap.keySet().iterator();it.hasNext();){
        		  entry = it.next();
        		  input +="<input type=\"hidden\" name=\""+entry+"\" value=\""+reqMap.get(entry)+"\" />";
          }
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("<form id=\"myform\" action=\""+HSignUtil.PINSETTING+"\" method=\"post\">");
        buffer.append(input);
        buffer.append("<input type=\"submit\" value=\"提交\"></input>");
         buffer.append("</form>");	
        hashMap.put("result", "success");
        hashMap.put("list", buffer.toString());
        session.removeAttribute(Session_Constant.TRADEPASSWORDPHONESSMCODE); 
        String str = JSON.toJSONString(hashMap);
        try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
         return null;
        
	}
  	
	/**
	 * 
	* @Title: CallBack 
	* @Description: TODO(交易密码 设置) 
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author   cjm  
	* @throws
	 */
	@RequestMapping("/CallBack")
	public String CallBack(HttpServletRequest request,HttpServletResponse response){
		System.out.println("==================进来了=======================");
		String bgData = request.getParameter("bgData");
 		if(StringUtil.isEmpty(bgData)){
			return null;
		}
 		JSONObject jsonObject   = JSONObject.fromObject(bgData);
		String VERSION 			= jsonObject.getString("VERSION");//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
		String TRXDATE 			= jsonObject.getString("TRXDATE");///TRXDATE	交易日期	A(8)	是	系统日期
		String TRXTIME 			= jsonObject.getString("TRXTIME");//TRXTIME	交易时间	A(6)	是	系统时间
		String BANKCODE 		= jsonObject.getString("BANKCODE");//BANKCODE	银行代码	A(8)	是	
		String COINSTCODE 		= jsonObject.getString("COINSTCODE");//COINSTCODE	合作单位编号	A(6)	是	
		String COINSTCHANNEL 	= jsonObject.getString("COINSTCHANNEL");//COINSTCHANNEL	合作单位渠道	A(6)	是	
		String CARDNBR 			= jsonObject.getString("CARDNBR");//CARDNBR	账户	A(19)	是	
		String NAME 			= jsonObject.getString("NAME");//NAME	姓名	A(60)	是	
		String RETCODE 			= jsonObject.getString("RETCODE");//RETCODE	应答码	A(8)	是	
		String RETMSG 			= jsonObject.getString("RETMSG");//RETMSG	应答码描述	A(60)	是	
		
		String SIGN 			= jsonObject.getString("SIGN");
		System.out.println("========VERSION=============="+VERSION);
		System.out.println("========TRXDATE=============="+TRXDATE);
		System.out.println("========TRXTIME=============="+TRXTIME);
		System.out.println("========BANKCODE=============="+BANKCODE);
		System.out.println("========COINSTCODE=============="+COINSTCODE);
		System.out.println("========COINSTCHANNEL=============="+COINSTCHANNEL);
		System.out.println("========CARDNBR=============="+CARDNBR);
		System.out.println("========NAME=============="+NAME);
		System.out.println("========RETCODE=============="+RETCODE);
		System.out.println("========RETMSG=============="+RETMSG);
//		VERSION,TRXDATE,TRXTIME,BANKCODE,COINSTCODE,COINSTCHANNEL,CARDNBR,NAME,RETCODE,RETMSG
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(VERSION));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(CARDNBR));
		buffer.append(StringUtils.trimToEmpty(NAME));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		String str = buffer.toString();
		boolean falg = HSignUtil.getVerify(str, SIGN);
		
		if(!falg){//验证签名失败
			System.out.println("=======================验证签名失败");
			return null;
		}
		
		if(RETCODE != null && RETCODE.equals("00000000")){
 			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByUsrCustId(setEncrypt(CARDNBR));
			if(userFsAccountInfo != null){
				UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.selectByPrimaryKey(userFsAccountInfo.getBaseid());
				if(!(userFsAccountInfo.getTradepass().equals((short)1))){
 					userFsAccountInfo.setTradepass((short)1);/*是否设置交易密码 0 未设置 1 已设置*/
					userFsAccountInfoServiceI.updateById(userFsAccountInfo);//更新
					insertUserLogDateil(request,getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo), "交易密码设置");
				}else{
					insertUserLogDateil(request,getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo), "交易密码修改");
				}
  			}
		}
		
		try {
			response.getWriter().println("success");
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
 		return null;
	}
	
	
	/**
   	 * 保存用户操作日志信息
   	 * @param request
   	 * @param userBaseAccountInfo
   	 * @param opercontent
   	 */
   	public void insertUserLogDateil(HttpServletRequest request,
   			UserBaseAccountInfo userBaseAccountInfo,String opercontent){
   		if(StringUtil.isEmpty(opercontent)){
   			if(logger.isDebugEnabled()){
   				logger.debug("UserSecurityCenterController.class 安全中心用户操作,保存用户操作日志内容不能为空  'opercontent' 不能为空");
   			}
   			return ;
   		}
   		if(userBaseAccountInfo == null){
   			if(logger.isDebugEnabled()){
   				logger.debug("UserSecurityCenterController.class 安全中心用户操作,保存用户操作日志内容不能为空  'userBaseAccountInfo' 不能为空 ");
   			}
   			return ;
   		}
		// 更新最后登录ip
		String ipAddr = StringUtil.getIpAddr(request);
		UserLog userLog = new UserLog();
		userLog.setBaseid(userBaseAccountInfo.getId()); //用户Id
		userLog.setUsername(userBaseAccountInfo.getLoginname()); //用户名
		userLog.setUsertype((short)1); //用户类型 1普通用户  2管理员用户
		if(StringUtil.isNotEmpty(ipAddr)){
			userLog.setIp(ipAddr); //IP
		}
		
		Cookie[] cookies = request.getCookies();
		String cookieStr = "";
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name != null && name.equals("JSESSIONID")) {
					String value = cookie.getValue();
					if (value != null) {
						cookieStr = value;
						break;
					}
				}
			}
		}
		if(StringUtil.isNotEmpty(cookieStr)){
 			// 更新最后登录cookie值
			userLog.setCookie(cookieStr); //cookie
		}
		Date date = new Date();
		userLog.setOpercontent(opercontent); //操作内容（涉及修改）
		userLog.setOpertime(date); //操作时间
		int count = 0;
		count = userLogServiceI.insertSelective(userLog);
		if(!(count > 0)){
			if(logger.isDebugEnabled()){
   				logger.debug("UserSecurityCenterController.class 安全中心用户操作,用户日志保存数据库失败！！！！保存的Baseid是："
			+userBaseAccountInfo.getId()+",Username是："+userBaseAccountInfo.getLoginname()
			+",ip是："+ipAddr+",cookie是："+cookieStr+",opercontent是："+opercontent+",Opertime是:"+StringUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
   			}
		}
   		 
   	}
	
	//VERSION	接口版本号	A(4)	是	调用的接口版本，固定1.1
			//TRXDATE	交易日期	A(8)	是	
			//TRXTIME	交易时间	A(6)	是	
			//CARDNBR	账户	A(19)	是	
			//IDNO	证件号码	A(18)	是	
			//IDTYPE	证件类型	A(2)	是	01-身份证18位
			//02-身份证15位
			//20-其它
			//25-企业社会信用代码
			//注：企业开户时上送20或社会信用号25
			//PHONE	手机号码	A(12)	是	
			//NAME	姓名	A(60)	是	
			//BANKCODE	银行代码	A(8)	是	
			//RESETPWD_SURL	密码设置成功跳转链接	A(256)	是	密码设置成功后跳转的url
			//RESETPWD_FURL	密码设置失败跳转链接	A(256)	是	密码设置失败后跳转的url
			//BACKGROUND_URL	后台响应链接	A(256)	否	用于接收后台响应
			//COINSTCODE	合作单位编号	A(6)	是	
			//COINSTCHANNEL	合作单位渠道	A(6)	是	
			//SIGN 	签名		是
	
	/**
	 * 
	* @Title: queryIsTradePssword 
	* @Description: TODO(查询是否设置交易密码) 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static boolean queryIsTradePssword(String usrcustid){
		boolean fag = false;
		if(StringUtil.isEmpty(usrcustid)){
			return fag;
		}
 			  Date date = new Date();
			  String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
			  String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
			  String SEQNO   = StringUtil.getNo();//交易流水号
			  LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		      reqMap.put("TRXCODE", "5004");//交易代码	TRXCODE
		      reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE
		
		      reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
		      reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
		      reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
		      reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
		      reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
		      reqMap.put("SOURCE", "A0");//ESB内部渠道	SOURCE
		      reqMap.put("RETCODE", "");//应答码	RETCODE
		      reqMap.put("RETMSG", "");//应答码描述	RETMSG
		      reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED
		      
		      reqMap.put("CARDNO", usrcustid);//电子账号 CARDNO
		      String respoResult = "";
		      try {
		    	  respoResult = HSignUtil.HttpClientTask(reqMap, "v20160602");
			} catch (UnsupportedEncodingException e) {
 				e.printStackTrace();
			} catch (Exception e) {
 				e.printStackTrace();
			}
		      
		  if(StringUtil.isEmpty(respoResult)){ 
			  return fag;
          }
		  
  		  List<String> userRegList = new ArrayList<>();
		  userRegList.add("PIN_RSTS");//是否已重置取现密码 0：无密码  1：有密码
		  
 		  String result = HSignUtil.getDecryptRSAByte(respoResult);//解密
 		  
		  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(result);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5810接口返回报文参数和1到11域拼接一起
	 	   
	      int listLength = resColumnList.size();
 	      if(responseMap.get("RETCODE") == null){
	    	  return fag;
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
	  	    	return fag;
	  	  }
	  	    
	  	 if(!"00000000".equals(responseMap.get("RETCODE"))){
	  	    	return fag;
  	     }
	  	    
  	     JSONObject json 	= JSONObject.fromObject(responseMap);
	     String PIN_RSTS 	= json.getString("PIN_RSTS"); //是否已重置取现密码 0：无密码  1：有密码
	     if(PIN_RSTS.equals("1")){
	    	fag = true;
	    	return fag;
	     }
  		  
 	      return fag;
	}
	
	public static void main(String[] args) {
		boolean falg = queryIsTradePssword("9930040050240100038");
		System.out.println(falg);
	}
}
