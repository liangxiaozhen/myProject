package com.ptpl.controller.ui.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.RechargeQuotaFee;
import com.ptpl.model.RechargeRate;
import com.ptpl.model.RechargeSNLLink;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RechargeQuotaFeeServiceI;
import com.ptpl.service.RechargeRateServiceI;
import com.ptpl.service.RechargeSNLLinkServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;
@Controller
@Scope("prototype")
@RequestMapping("/ui/app/recharge")
public class UIAndroidUserRechargeController extends BaseController{
	/**绑定卡信息*/
	@Autowired
	UserBankCardServiceI userBankCardService;
	/** 托管账户信息*/
	@Autowired
	UserFsAccountInfoServiceI  userFsAccountInfoService;
	/**充值费率表*/
	@Autowired
	RechargeRateServiceI rechargeRateService;
	/**充值定额手续费表*/
	@Autowired
	private RechargeQuotaFeeServiceI rechargeQuotaFeeService;
	/**定向排除人名单表*/
	@Autowired
	private RechargeSNLLinkServiceI rechargeSNLLinkService;
	/** 充值记录*/
	@Autowired
	UserRechargeServiceI userRechargeService;
	/**用户账户表*/
	@Autowired
	UserAccountServiceI userAccountService;
	/**用户收支明细记录*/
	@Autowired 
	AccInExRecordServiceI accInExRecordService;
	/**用户收支明细记录*/
	@Autowired 
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	/**用户债转*/
	@Autowired
	UserDebtAttornServiceI userDebtAttornService;
	@Autowired
	QueryBlaneServiceI queryBlaneService;
	
	
	/**
	 * 余额更新接口,返回可用余额和总余额
	 * @param @param baseid
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/queryblane", method = {RequestMethod.POST, RequestMethod.GET})
	public void queryBlane(String baseid) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		Map<String,String> map2 = queryBlaneService.queryBlane(AES.getDecrypt(ufs.getUsrcustid()));
		map.put("availbal", map2.get("AVAIL_BAL"));//可用余额
		map.put("currbal", map2.get("CURR_BAL"));//总余额
		sendJsonData(response, JSON.toJSONString(map));
	}
	
	
	/**
	 * 点击充值跳转页面返回给app的参数
	 * @param @param baseid
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getUserInfo", method = {RequestMethod.POST, RequestMethod.GET})
	public void setUserInfo(String baseid) throws Exception{
		//4.声明一个接收值的map
		Map<String,String> map = new HashMap<String, String>();
		if(baseid!=null && baseid!=""){
			//根据用户id获取到用户基本信息
			UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
			String userfalse = "";
			if(user!=null){
				userfalse = "has";
				map.put("userfalse", userfalse);
				//2,获取绑定卡的卡号对象
				UserBankCard ucard = userBankCardService.selectBoundCardByBaseId(user.getId());
				String ucardfalse = "";
				String ufsfalse = "";
				if(ucard!=null){
					ucardfalse = "has";
					map.put("ucardfalse", ucardfalse);
					map.put("subbranch",ucard.getSubbranch());//开户行
					map.put("bankname", ucard.getBankname());//银行名字
					map.put("cardno", AES.getDecrypt(ucard.getCardno()));//银行卡号
				}else{
					ucardfalse = "nohas";
					map.put("ucardfalse", ucardfalse);
				}
				//3,获取托管账户信息
				UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
				if(ufs!=null){
					ufsfalse = "has";
					map.put("ufsfalse", ufsfalse);
					map.put("username", AES.getDecrypt(ufs.getUsrname())); //开户人;
					map.put("usrcustid",AES.getDecrypt(ufs.getUsrcustid())); //电子账号
					map.put("phone", AES.getDecrypt(ufs.getFsmobile()));//手机号码
					Map<String,String> map2 = queryBlaneService.queryBlane(AES.getDecrypt(ufs.getUsrcustid()));//查询余额
					map.put("availbal", map2.get("AVAIL_BAL"));//可用余额
					map.put("currbal", map2.get("CURR_BAL"));//总余额
				}else{
					ufsfalse = "nohas";
					map.put("ufsfalse", ufsfalse);
				}
				sendJsonData(response, JSON.toJSONString(map));
			}else{
				userfalse = "nohas";
				map.put("userfalse", userfalse);
				sendJsonData(response, JSON.toJSONString(map));
			}
		}
	}
	
	
	
	/**
	 * 短信发送并返回:返回值包含应答码+应答时间+验证码有效时长+短信发送时间
	 * 应答码是00000000表示成功
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/setmessage", method = {RequestMethod.POST, RequestMethod.GET})
	public void setMessage(String phone,String baseid) throws Exception{
		//1,得到登录用户
		//UserBaseAccountInfo user = //根据用户id获取到用户基本信息
	   UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
		//2,获取绑定卡的卡号对象
		UserBankCard ucard = userBankCardService.selectBoundCardByBaseId(user.getId());
		String  FUNCTION = "7601";
		String  MOBILE = phone;
		String  CARD_BIND = ucard.getCardno();
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		//String  RESERVE = "";
		String  orderno = StringUtil.getNo();//交易流水号
		System.out.println(orderno);
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		reqMap.put("TRXCODE", "3031");
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);
        reqMap.put("TRXDATE", TRXDATE);
        reqMap.put("TRXTIME", TRXTIME);
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);
        reqMap.put("COINSTCHANNEL",HSignUtil.COINSTCHANNEL_APP);
        reqMap.put("SEQNO",orderno);//交易流水号
        reqMap.put("SOURCE", HSignUtil.SOURCE);
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
     /*   reqMap.put("HEADRESERVED", "");*/
        
        reqMap.put("FUNCTION", FUNCTION);
        reqMap.put("MOBILE", MOBILE);
        reqMap.put("CARD_BIND", CARD_BIND);
       /* reqMap.put("RESERVE", RESERVE);*/
        String result = HSignUtil.HttpClientTask(reqMap, "");
        Map<String,String> hashMap2 = callBack(result);
        request.getSession().setAttribute("sms_seq", hashMap2.get("SMS_SEQ"));
        if(hashMap2.get("RETCODE").equals("00000000")){
        	hashMap2.put("status", "1");
        }
        sendJsonData(response, JSON.toJSONString(hashMap2));
	}
	/**
	 * 短信返回
	 * @param @param result
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	public  Map<String,String> callBack(String result){
		 Map<String,String> hashMap = new HashMap<>();
 		  List<String> phoneList = new ArrayList<>();
 		  phoneList.add("SEND_TIME");// 短信发送时间
 		  phoneList.add("SMS_SEQ");//短信序号
 		  phoneList.add("VALID_TIME");//验证码有效时长
 		 /* phoneList.add("RESERVE");// 保留域
*/		 String resultdata = HSignUtil.getDecryptRSAByte(result);//解密
		  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resultdata);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(phoneList);//把3031接口返回报文参数和1到11域拼接一起
	 	  
	 	 int listLength = resColumnList.size();
	     if(responseMap.get("RETCODE") == null){
	    	  hashMap.put("result", "network");// "因网络响应不及时,处理失败";
	    	  return hashMap;
	      }
	        
	      StringBuffer responseMapMerged = new StringBuffer();
	      for (int i = 0; i < listLength; i++) {
	      	Object columnvalue = responseMap.get(resColumnList.get(i));
	      	if(columnvalue != null){
	      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
	      	}
	      }
	      System.out.println(responseMap);
 	      //验签
	      String responseSign = (String) responseMap.get("SIGN");
 	      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
 	     if(!verifyResult){
   	    	hashMap.put("result", "network");// "因网络响应不及时,处理失败";
   	    	return hashMap;
   	    }
 	    System.out.println(responseMap.get("RETCODE")+"============="); //应答码
 	    System.out.println(responseMap.get("RETMSG")+"============="); //应答描述
 	    JSONObject json = JSONObject.fromObject(resultdata);
	    String SEND_TIME = json.getString("SEND_TIME"); 
		String SMS_SEQ 	= json.getString("SMS_SEQ");
		String VALID_TIME = json.getString("VALID_TIME");
	    String RESERVE  = json.getString("RESERVE");
	    String RETCODE  = json.getString("RETCODE");
	    String RETMSG  = json.getString("RETMSG");
	    
	    hashMap.put("SEND_TIME", SEND_TIME);
	    hashMap.put("SMS_SEQ", SMS_SEQ);
	    hashMap.put("VALID_TIME", VALID_TIME);
	    hashMap.put("RESERVE", RESERVE);
	    hashMap.put("RETCODE", RETCODE);
	    hashMap.put("RETMSG", RETMSG);
		return hashMap;
	} 
	
	
	/**
	 * 点击确认按钮的时候跳转的页面
	 * 验证该用户在该等级下+充值方式下+充值限制启用状态的充值限制
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String dmoney(String rechargeType,String money,String baseid) throws Exception {
		String data = "";
		// 获取登录的用户
		UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
		if (user != null) {
			/**充值记录对象new*/
			UserRecharge userR = new UserRecharge();
			/**获取充值费率表*/
			RechargeRate rechargeRate  = rechargeRateService.selectRechartypeByRechargeRate(new Short(rechargeType));
			userR.setBaseid(user.getId());
			//查询当天充值成功的充值记录
			List<UserRecharge> UR = userRechargeService.selectAmountList(userR);
			//查询当天的充值限额
			Double totalAmount = 0.0;
			for (UserRecharge userRecharge : UR) {
				totalAmount+=userRecharge.getAmount();
			}
			if(rechargeRate!=null){
				//查询是否在
				List<RechargeSNLLink> rechargeSNLLinkList = rechargeSNLLinkService.selectByrrid(rechargeRate.getId());
				List<String> ruslut = new ArrayList<String>();
				if(rechargeSNLLinkList!=null && rechargeSNLLinkList.size()>0){
					for (RechargeSNLLink rechargeSNLLink : rechargeSNLLinkList) {
						boolean flag = userDebtAttornService.ugradeFalsePublic(rechargeSNLLink.getSnlid(), user.getId());//如果为true,就说明在名单中,不允许充值
						if(flag){
							ruslut.add("0");//表示在名单中,不允许充值
						}else{
							ruslut.add("1");//表示不在名单中,可以充值
						}
					}
					String dataStr = "has";
					for (String str : ruslut) {
						if(str.equals("0")){
							dataStr = "nohas";
							break;
						}
					}
					
					if(dataStr.equals("nohas")){
						data="nohas";//表示在排除人名单中
					}else{
						data = "has"; //如果为false,说明就在派出人名单之外,说明是可以充值的
					}
				}
				
				if(data.equals("has")){
					//查询
					if(rechargeRate.getChargetype()==1){//定额
						//查询定额手续费表的数据
						List<RechargeQuotaFee>  rechargeQuotaFeeList =  rechargeQuotaFeeService.selectByrrid(rechargeRate.getId());
						List<Double> listmin = new ArrayList<Double>();
						List<Double> listmax = new ArrayList<Double>();
						if(rechargeQuotaFeeList!=null && rechargeQuotaFeeList.size()>0){
							for (RechargeQuotaFee rf : rechargeQuotaFeeList) {
								//先要判断充值的金额在哪个金额段之间,如果找到后就直接跳出循环,如果没有找到就报没有找到,然后返回false给前端
								listmin.add(rf.getMinmoney());
								listmax.add(rf.getMaxmoney());
							}
						}
						//遍历最小金额,得到值最小值
						Double min = Collections.min(listmin);
						//遍历最大金额,得到最大值
						Double max = Collections.max(listmax);
						if(Double.valueOf(money)<min){
							data = "minfalse";
						}
						if(Double.valueOf(money)>max){
							data = "maxfalse";
						}
						if(Double.valueOf(money)>=min && Double.valueOf(money)<=max){
							data = "success";
						}
					}else{//百分比
						data="success";
						
					}
				}
			}else{
				data="notRate";//没有充值费率表,请联系管理员设置
			} 
		}
		return data;	
		
	}
	/**
	 * 充值接口
	 * @param @param money 充值金额
	 * @param @param rechargeType 充值方式 网银1 快捷2 汇款4
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	@RequestMapping(value = "/userrecharge", method = {RequestMethod.POST, RequestMethod.GET})
	public synchronized void userRecharge(String money,String rechargeType,String sms_code,String baseid,String sms_seq) throws Exception{
		//4.声明一个返回值的map
		Map<String, String> map = new HashMap<String, String>();
		
		logger.debug(sms_seq+"验证码序号=======================");
		//对金额进行处理,并且返回
		String re = "([1-9]\\d*|0)(\\.\\d{1,2})?";
		if(money.matches(re)){//为true,说明手机端输入金额正确
			String falg = dmoney(rechargeType,money,baseid);
			if(falg.equals("success")){
				map.put("moneychecking", falg);
				if(rechargeType.equals("1")){//网银
					
				}
				if(rechargeType.equals("2")){//快捷
					Map<String,String> hashMap  = shortcut(money,rechargeType,sms_code,baseid,sms_seq);
					map.put("RETCODE", hashMap.get("RETCODE"));//应答码
					map.put("RETMSG", hashMap.get("RETMSG"));//应答描述
				}
			}else{
				map.put("moneychecking", falg);//金额校验没有通过
			}
		}else{
			map.put("moneychecking", "moneyfalse");
		}
		sendJsonData(response, JSON.toJSONString(map));
}
	/**
	 * 快捷支付
	 * @param @return 
	 * @return Map<String,String>
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	public synchronized  Map<String,String> shortcut(String money,String rechargeType,String sms_code,String baseid,String sms_seq) throws Exception{
		//1,得到登录用户
		UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
		//2,获取绑定卡的卡号对象
		UserBankCard ucard = userBankCardService.selectBoundCardByBaseId(user.getId());
		//3,获取托管账户信息
		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		//获取绝对路径
		String URL = "http://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath();
		String BgRetUrl = URL+"/user/userRecharge/UserRechargeCallback.action";
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		//请求头
		String orderno = StringUtil.getNo();//充值流水号
		reqMap.put("TRXCODE", "7601");
		reqMap.put("BANKCODE", HSignUtil.BANKCODE);
		reqMap.put("TRXDATE",TRXDATE);
		reqMap.put("TRXTIME", TRXTIME);
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);
		reqMap.put("COINSTCHANNEL",HSignUtil.COINSTCHANNEL_WEB);
		reqMap.put("SEQNO",orderno);//交易流水号
		reqMap.put("SOURCE", HSignUtil.SOURCE);
		reqMap.put("RETCODE", "");
		reqMap.put("RETMSG", "");
		reqMap.put("HEADRESERVED", "");
        //请求参数
        System.out.println(ufs.getUsrcustid()+"******************");
        reqMap.put("CARDNBR",AES.getDecrypt(ufs.getUsrcustid())); //电子账号 也就是用户客户号 "9930040050240500013"
        reqMap.put("CARD_BIND", AES.getDecrypt(ucard.getCardno()));//绑定卡卡号 需要从UserBankCard 根据baseid查询 ucard.getCardNo()
        reqMap.put("CURRENCY", "156");
        reqMap.put("AMOUNT", df1.format(Double.valueOf(money))); //充值金额从页面获取
        reqMap.put("KEYTYPE", "01"); 
        reqMap.put("IDNO", AES.getDecrypt(user.getCertificationnumber())); //身份证号 从userBaseAccountInfo 用户基本信息表中获取certificationNumber
        reqMap.put("NAME", AES.getDecrypt(user.getRealname()));  //用户真实姓名也从用户基本信息表中获取
        reqMap.put("MOBILE", AES.getDecrypt(ufs.getFsmobile())); //电话号码从用户托管账户表中获取fsmobile
        reqMap.put("AUTH_FLAG", "N"); //ESB代发实名认证标志
        //以下为可传参数
        reqMap.put("AUTHSEQ", ""); //实名认证流水号
        reqMap.put("BANK_CODE", ""); //开户银行代码
        reqMap.put("BANK_NAME_EN", "");//开户银行英文缩写
        reqMap.put("BANK_NAME_CN", ""); //开户银行中文名称
        reqMap.put("ISS_BANK_PROVINCE", ""); //开户行省份
        reqMap.put("ISS_BANK_CITY", ""); //开户行城市0.
        reqMap.put("CALL_BACK_ADRRESS",BgRetUrl);//回调地址
        reqMap.put("SMS_CODE", sms_code);//短信验证码
        reqMap.put("SMS_SEQ", sms_seq);//短信序列号
        reqMap.put("USR_IP", user.getId().toString());//客户序列号
        reqMap.put("RESERVED", "");//保留域
        
        /**在发数据去银行之前保存一部分充值数据到本地数据库*/
        save(rechargeType,money,user,ucard,ufs,orderno);
        logger.debug(reqMap);
        //发送请求去徽商银行
        String result = HSignUtil.HttpClientTask(reqMap, "v20160907");
        Map<String,String> hashMap = callBack2(result);
        /**处理返回值*/
        updateMessage(hashMap,baseid);
		return hashMap;
		
}
	/**
	 * 快捷支付返回
	 * @param @param result
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	public Map<String,String> callBack2(String result){
		Map<String,String> hashMap = new HashMap<>();
		List<String> kjList = new ArrayList<>();
		kjList.add("CARDNBR");// 电子账户
		kjList.add("CURRENCY");// 币种
		kjList.add("AMOUNT");// 金额
		/* phoneList.add("RESERVED");// 保留域 */
		String resultdata = HSignUtil.getDecryptRSAByte(result);// 解密
		Map<String, Object> responseMap = (HashMap<String, Object>) HSignUtil.parseJSON2Map(resultdata);// 数据转化Map
		List<String> resColumnList = HSignUtil.getResponseHead(kjList);// 把3031接口返回报文参数和1到11域拼接一起
		int listLength = resColumnList.size();
		if (responseMap.get("RETCODE") == null) {
			hashMap.put("result", "network");// "因网络响应不及时,处理失败";
			return hashMap;
		}
		StringBuffer responseMapMerged = new StringBuffer();
		for (int i = 0; i < listLength; i++) {
			Object columnvalue = responseMap.get(resColumnList.get(i));
			if (columnvalue != null) {
				responseMapMerged.append(responseMap.get(resColumnList.get(i)));
			}
		}
		// 验签
		String responseSign = (String) responseMap.get("SIGN");
		boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);// 验证签名
		if (!verifyResult) {
			hashMap.put("result", "network");// "验证签名失败";
			return hashMap;
		}
		System.out.println(responseMap.get("RETCODE") + "============="); // 应答码
		JSONObject json = JSONObject.fromObject(resultdata);
		hashMap.put("RETCODE", json.getString("RETCODE")); // 应答码
		hashMap.put("RETMSG", json.getString("RETMSG")); // 应答码描述
		hashMap.put("SEQNO", json.getString("SEQNO")); // 交易流水号
		hashMap.put("CARDNBR", json.getString("CARDNBR")); // 电子账户
		hashMap.put("CURRENCY", json.getString("CURRENCY"));// 币种
		hashMap.put("AMOUNT", json.getString("AMOUNT"));// 金额
		hashMap.put("RESERVED", json.getString("RESERVED"));// 保留域
		return hashMap;
	}
	/**
	 * 先期保存充值记录
	 * @param @param rechargeType
	 * @param @param money
	 * @param @param user
	 * @param @param ucard
	 * @param @param ufs
	 * @param @param orderno
	 * @return void
	 * @author jiangxueyou
	 */
	public void save(String rechargeType,String money,UserBaseAccountInfo user,UserBankCard ucard,UserFsAccountInfo  ufs,String orderno){
		UserRecharge userRecharge = new UserRecharge();
		RechargeRate rechargeRate = rechargeRateService.getRechargeRate(rechargeType);
		// 声明充值费率
		Double recharrate = 0.00;
		if (rechargeRate != null) {
			if (rechargeRate.getChargetype() == 1) {// 定额
				List<RechargeQuotaFee> rechargeQuotaFeeList = rechargeQuotaFeeService.selectByrrid(rechargeRate.getId());
				if (rechargeQuotaFeeList != null && rechargeQuotaFeeList.size() > 0) {
					for (RechargeQuotaFee rechargeQuotaFee : rechargeQuotaFeeList) {
						if (Double.valueOf(money) > rechargeQuotaFee.getMinmoney()
								&& Double.valueOf(money) <= rechargeQuotaFee.getMaxmoney()) {
							userRecharge.setRecharfee(rechargeQuotaFee.getQuotafee());
							break;
						}
					}
				}
			} else {
				// 算出百分比算出的金额
				Double fee = Arith.mul(Double.valueOf(money), rechargeRate.getCharrate());
				if (fee < rechargeRate.getMinfee()) {
					userRecharge.setRecharfee(rechargeRate.getMinfee());
				} else if (fee > rechargeRate.getMinfee()) {
					userRecharge.setRecharfee(rechargeRate.getMaxfee());
				} else {
					userRecharge.setRecharfee(fee);
				}
				recharrate = rechargeRate.getCharrate();
			}
		}
		userRecharge.setRechargeno(orderno);// 保存充值订单号
		userRecharge.setMercustid(AES.getDecrypt(ufs.getUsrcustid()));// 保存用户电子账号
		userRecharge.setBaseid(user.getId());// 保存用户id
		userRecharge.setRecharrate(recharrate);// 保存充值费率
		userRecharge.setAmount(Double.valueOf(money));// 充值金额
		userRecharge.setStarttime(new Date()); // 充值开始时间
		userRecharge.setRechargetype((short) 2);// 充值方式
		userRecharge.setApplyman(AES.getDecrypt(user.getLoginname())); // 充值人!也就是登陆的用户
		userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); // 表示系统未勾兑
		userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);// 表示人工未勾兑
		userRecharge.setPaycompany("徽商银行"); // 充值通道公司
		short originclient = user.getOriginclient() == null ? 1 : user.getOriginclient();
		userRecharge.setOriginclient(originclient);// 充值来源
		userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); // 表示充值状态取消
		userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);// 充值是正常
		userRecharge.setCardno(AES.getDecrypt(ucard.getCardno()));
		userRecharge.setUuid(StringUtil.getRechargeNo());
		userRecharge.setUrid("0");
		userRechargeService.add(userRecharge);
	}
	/**
	 * 修改充值记录,添加收支记录,修改账户金额
	 * @param @param hashMap
	 * @param @param baseid
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	public void updateMessage(Map<String, String> hashMap, String baseid) throws Exception {
		if (hashMap.get("RETCODE").equals("00000000")) {// 表示徽商那边充值成功了
			/** 1.接下来修改充值记录 ***********************/
			// 根据请求头中的充值订单号查询本地库中的充值记录
			UserRecharge usRecharge = userRechargeService.select(hashMap.get("SEQNO"));
			if (usRecharge.getBankreturnno() == null) {// 避免重复加入充值记录和更新用户余额还有收支记录
				Double AMOUNT = Double.valueOf(hashMap.get("AMOUNT"));

				// 主要修改充值状态变为充值成功
				usRecharge.setAmount(AMOUNT);
				usRecharge.setEndtime(new Date());// 充值结束时间
				usRecharge.setStatus((short) 1);// 充值成功
				usRecharge.setBankreturnno(hashMap.get("SEQNO"));// 银行返回充值订单号存入充值订单号
				userRechargeService.update(usRecharge);

				/** 3(准备).修改用户账户金额 ***************/
				UserAccount userAccount = userAccountService.getUserAccountByBaseId(new BigDecimal(baseid));
				/** 获取徽商银行的余额 */
				Map<String, String> mapCount = queryBlaneService.queryBlane(hashMap.get("CARDNBR"));
				// 充值金额
				double amount = Double.valueOf(AMOUNT);

				/** 2.添加收支记录 **************/
				AccInExRecord accInExRecord = new AccInExRecord();// 存收支记录的对象
				accInExRecord.setOutamount(0.0);// 用户支出
				accInExRecord.setStatus((short) 1);
				accInExRecord.setTotalbalance(Double.valueOf(mapCount.get("CURR_BAL")));// 用户总金额
				accInExRecord.setFreebalance(Arith.sub(Double.valueOf(mapCount.get("CURR_BAL")), Double.valueOf(mapCount.get("AVAIL_BAL"))));// 冻结金额
				accInExRecord.setBaseid(new BigDecimal(baseid));
				accInExRecord.setType((short) 1);// type 为1 表示业务类型为充值
				accInExRecord.setInamount(amount);// 用户收入
				accInExRecord.setBalance(Double.valueOf(mapCount.get("AVAIL_BAL")));// 可用余额
				accInExRecord.setRecordtime(new Date());// 时间
				accInExRecord.setAieorderno(StringUtil.getNo()); // 保存收支明细流水号
				accInExRecord.setDescription("充值说明");
				accInExRecord.setRemark("备注");
				accInExRecordService.insertSelective(accInExRecord);

				/** 3(修改).修改用户账户金额 ***************/
				userAccount.setBaseid(new BigDecimal(baseid));
				userAccount.setBalance(Double.valueOf(mapCount.get("CURR_BAL")));
				userAccount.setAvlbalance(Double.valueOf(mapCount.get("AVAIL_BAL")));
				userAccount.setFreezebalance(Arith.sub(Double.valueOf(mapCount.get("CURR_BAL")), Double.valueOf(mapCount.get("AVAIL_BAL"))));
				userAccountService.updateUseraccount(userAccount);
			}
		}
	}

	
	
	
	public static void main(String[] args) {
		 Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
	     Matcher match=pattern.matcher("0.12");   
	     if(match.matches()==false){   
	       System.out.println("输入错误");
	     }else{   
	    	 System.out.println("输入正确");
	     }  
	     String re = "([1-9]\\d*|0)(\\.\\d{1,2})?";
	     System.out.println("1.12".matches(re));//true
	     System.out.println("01.12".matches(re));//false
	     System.out.println("1.123".matches(re));//false
	     System.out.println("11.12".matches(re));//true
	     System.out.println("0.".matches(re));//false
	     System.out.println("01".matches(re));//false
	     
	}

}
