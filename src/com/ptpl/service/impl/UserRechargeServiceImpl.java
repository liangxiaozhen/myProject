package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishang.util.HSignUtil;
import com.huishang.util.MessageUtil;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.mapper.AccInExRecordMapper;
import com.ptpl.mapper.RechargeQuotaFeeMapper;
import com.ptpl.mapper.RechargeRateMapper;
import com.ptpl.mapper.RechargeSNLLinkMapper;
import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.mapper.UserBankCardMapper;
import com.ptpl.mapper.UserRechargeMapper;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.RechargeQuotaFee;
import com.ptpl.model.RechargeRate;
import com.ptpl.model.RechargeSNLLink;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

public class UserRechargeServiceImpl implements UserRechargeServiceI {

	@Autowired
	private UserRechargeMapper userRechargeMapper;
	@Autowired
	private UserAccountMapper userAccountMapper;
	@Autowired
	private UserBankCardMapper userBankCardMapper;
	@Autowired
	private RechargeRateMapper rechargeRateMapper;
	@Autowired
	private RechargeSNLLinkMapper rechargeSNLLinkMapper;
	@Autowired
	private UserDebtAttornServiceI userDebtAttornService;
	@Autowired
	private AccInExRecordMapper accInExRecordMapper;
	@Autowired
	private RechargeQuotaFeeMapper rechargeQuotaFeeMapper;
	@Autowired
	private SMSSendServiceI SMSSendService;
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public List<UserRecharge> getAll(UserRecharge record) throws Exception {
		List<UserRecharge> list = userRechargeMapper.getAll(record);
		// 针对日期进行处理的
		for (UserRecharge userRecharge : list) {
			  if(userRecharge.getStarttime()!=null){
				  userRecharge.setStarttimeStr(sf.format(userRecharge.getStarttime()));
			  }
			  if(userRecharge.getEndtime()!=null){
				  userRecharge.setEndtimeStr(sf.format(userRecharge.getEndtime()));
			  }
			  if(userRecharge.getSyschktime()!=null){
				  userRecharge.setSyschktimeStr(sf.format(userRecharge.getSyschktime()));
			  }
			  if(userRecharge.getChecktime()!=null){
				  userRecharge.setChecktimeStr(sf.format(userRecharge.getChecktime()));
			  }
			  if(userRecharge.getSysrectime()!=null){
				  userRecharge.setSysrectimeStr(sf.format(userRecharge.getSysrectime()));
			  }
				cleared(userRecharge);
		}
		return list;
	}

	public void cleared(UserRecharge userRecharge) throws Exception {
		parsing(userRecharge);

	}

	// 条件封装
	public void parsing(UserRecharge userRecharge) {
		// 充值方式
		userRecharge.setRechargetypeStr(UserRecharge_Constant.RECHARGETYPE_MAP.get(userRecharge.getRechargetype()));
		// 是否系统勾兑
		userRecharge.setIsblendingStr(UserRecharge_Constant.ISBLENDING_MAP.get(userRecharge.getIsblending()));
		// 是否人工勾兑
		userRecharge.setIsmanblendingStr(UserRecharge_Constant.ISMANBLENDING_MAP.get(userRecharge.getIsmanblending()));
		// 是否异常
		userRecharge.setIsexceptionsStr(UserRecharge_Constant.ISEXCEPTIONS_MAP.get(userRecharge.getIsexceptions()));
		// 充值状态
		userRecharge.setStatusStr(UserRecharge_Constant.STATUS_MAP.get(userRecharge.getStatus()));
		// 充值银行
		userRecharge.setBanknameStr(UserRecharge_Constant.BANK_MAP.get(userRecharge.getBankname()));
		// 设备来源
		userRecharge.setOriginclientStr(UserRecharge_Constant.ORIGINCLIENT_MAP.get(userRecharge.getOriginclient()));
	}

	@Override
	public UserRecharge queryKey(BigDecimal id) {

		return userRechargeMapper.queryKey(id);
	}

	@Override
	public int add(UserRecharge userRecharge) {

		return userRechargeMapper.insertSelective(userRecharge);
	}

	@Override
	public UserRecharge select(String rechargeNo) {
		
		return userRechargeMapper.getRechargeNo(rechargeNo);
	}

	@Override
	public int update(UserRecharge userRecharge) {
		parsing(userRecharge);
		return userRechargeMapper.update(userRecharge);
	}

	@Override
	public List<UserRecharge> selectContrast(UserRecharge userRecharge) {
		
		return userRechargeMapper.selectContrast(userRecharge);
	}

	@Override
	public void delete(String rechargeno) {
		userRechargeMapper.delete(rechargeno);
	}

	@Override
	public UserRecharge getBankReturnNo(UserRecharge  record) {
		
		return userRechargeMapper.getBankReturnNo(record);
	}

	@Override
	public List<UserRecharge> selectAmountList(UserRecharge userRecharge) {
		return userRechargeMapper.selectAmountList(userRecharge);
	}

	@Override
	public List<UserRecharge> getUrid(String urid) {
		return userRechargeMapper.getUrid(urid);
	}

	@Override
	public List<UserRecharge> getIsblendingAndIsmanblendingAndStatus() {
	
		return userRechargeMapper.getIsblendingAndIsmanblendingAndStatus();
	}

	@Override
	public UserRecharge getUuidAndId(UserRecharge userRecharge) {
		
		return userRechargeMapper.getUuidAndId(userRecharge);
	}

	@Override
	public List<UserRecharge> getUuid(String uuid) {
	
		return userRechargeMapper.getUuid(uuid);
	}

	@Override
	public List<UserRecharge> getAllList(BigDecimal baseid) {
		return userRechargeMapper.getAllList(baseid);
	}
	/**
	 * 获取当前时间前六个月的那天
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	public Date sixMonth(Date date){
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		c.add(Calendar.MONTH, -6);
		Date d  = c.getTime();
		return d;
		
	}
	/**
	 * 获取当前时间前三个月的那天
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	public Date threeMonth(Date date){
		Calendar c = Calendar.getInstance(); 
   	 	c.setTime(date);
	   	c.add(Calendar.MONTH, -3);
	   	Date d  = c.getTime();
		return d;
       
	}
	/**
	 * 获取当前时间的前一个月的第一天,eg: 3.17 最近一个月的交易就是2.17号00:00:00到系统3.17的当前时间
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	public Date oneMonth(Date date){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
		return  calendar.getTime();
	}
	
	
	
	/**
	 * 获取当前时间往前推一个星期的那天,eg: 3.17 号 往前推一个星期 得出的结论是3.10号
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	public Date oneWeek(Date date){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
		return calendar.getTime();
	} 
	
	//获取当天的0点和23点59分59秒
	private  Date weeHours(Date date, int flag) {    
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);    
        int hour = cal.get(Calendar.HOUR_OF_DAY);    
        int minute = cal.get(Calendar.MINUTE);    
        int second = cal.get(Calendar.SECOND);    
        //时分秒（毫秒数）    
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;    
        //凌晨00:00:00    
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);    
             
        if (flag == 0) {    
            return cal.getTime();    
        } else if (flag == 1) {    
            //凌晨23:59:59    
            cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);    
        }    
        return cal.getTime();    
    }
	/**
	 * 获取当天的零点零时零分
	 * @param @return
	 * @return Long
	 * @author jiangxueyou
	 */
	public Date DateZero(Date date){
		Date  dateZero= weeHours(date, 0);
		return dateZero;
	}
	/**
	 * 获取当天的23点59分59秒
	 * @param @return
	 * @return Long
	 * @author jiangxueyou
	 */
	public Long DateMax(Date date){
		Date  dateMax= weeHours(date, 1);
		return dateMax.getTime();
	}
	
	public List<UserRecharge> getRechargeList(){
		return null;
		
	}

	@Override
	public List<UserRecharge> getLableSelect(UserRecharge userRecharge) {
		
		return userRechargeMapper.getLableSelect(userRecharge);
	}

	@Override
	public List<UserRecharge> getAllCode(UserRecharge record) {
		return userRechargeMapper.getAllCode(record);
	}
	
	/**
	 * 余额查询发送
	 * @param @param usercustid
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	public  Map<String,String> queryBanlance(String usercustid) throws Exception {
		
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		//请求头
		String orderno = StringUtil.getNo();//交易流水号
		reqMap.put("TRXCODE", "5863");
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
        reqMap.put("CARDNBR",usercustid); //电子账号 也就是用户客户号 "9930040050240500013"
        reqMap.put("PINFLAG", "0");//0-不检查密码     1-检查取现密码
        reqMap.put("PIN", "");//密码 ANSI98格式，详见“信息安全处理”PIN加密部分
        reqMap.put("RESERVED", ""); //保留域
        reqMap.put("TRDRESV", ""); //第三方机构使用，原样返回
        
        String result = HSignUtil.HttpClientTask(reqMap, "");
        Map<String,String> hashMap2 = blanceCallBack(result);
		return hashMap2;
	}
	/**
	 * 查询余额返回
	 * @param @param result
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	public Map<String,String> blanceCallBack(String result){
		System.out.println(result);
		 Map<String,String> hashMap = new HashMap<>();
		  List<String> kjList = new ArrayList<>();
		  kjList.add("CARDNBR");//电子账户
		  kjList.add("NAME");//持卡人姓名
		  kjList.add("AVAIL_BAL");//可用余额
		  kjList.add("CURR_BAL");//账面余额
		  kjList.add("RESERVED");//保留域
		  kjList.add("TRDRESV");//第三方保留域
		 String resultdata = HSignUtil.getDecryptRSAByte(result);//解密
         System.out.println("*************"+resultdata+"************");
		  Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resultdata);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(kjList);//把3031接口返回报文参数和1到11域拼接一起
	 	  
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
	    
	    JSONObject json = JSONObject.fromObject(resultdata);
	    if(json.getString("RETCODE").equals("00000000")){
	    	hashMap.put("CARDNBR", json.getString("CARDNBR"));
	    	hashMap.put("NAME", json.getString("NAME"));
	    	hashMap.put("AVAIL_BAL", json.getString("AVAIL_BAL"));
	    	hashMap.put("CURR_BAL", json.getString("CURR_BAL"));
	    }
		return hashMap;
		
	}
	
	/**
	 * 后台提交
	 * @param @param reqMap
	 * @param @param version
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	public String fenzhuang(LinkedHashMap<String,String> reqMap,String version) throws Exception{
		String TaskResult = "";
		if(reqMap.size() > 0){
			   Map<String,Object> map = new HashMap<>();
			   map.put("BANKCODE",reqMap.get("BANKCODE"));//为银行代码
		       map.put("COINSTCODE",reqMap.get("COINSTCODE"));//为机构号
		       map.put("APIVERSION",version);//为报文版本号
		       
		       String sign = HSignUtil.getRASSign(reqMap);//加签名
		       reqMap.put("SIGN", sign);
		       	
		       String data = HSignUtil.getEncryptRSAByte(reqMap);//进行摘要并对摘要进行加密
		       
		       map.put("reqMap",data);//加密后的json参数
		       TaskResult = HSignUtil.doHttpClient(map);//发送HttpClient请求
		}
		return TaskResult;
	}
	/**
	 * 更新用户账户表数据,用于在跳转到充值页面的时候就开始发余额接口开始更新
	 */
	@Override
	public int updateUserAccount(Map<String, String> map, UserAccount userAccount) {
		 userAccount.setBalance(Double.valueOf(map.get("CURR_BAL")));
		 userAccount.setAvlbalance( Double.valueOf(map.get("AVAIL_BAL")));
		 userAccount.setFreezebalance(Arith.sub(Double.valueOf(map.get("CURR_BAL")), Double.valueOf(map.get("AVAIL_BAL"))));
		 int a = userAccountMapper.updateUseraccount(userAccount);
		return a;
	}
	/**
	 * 余额查询
	 * @param @param ufs
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	public Map<String,String> queryBlane(UserFsAccountInfo  ufs){
		Map<String,String> map = new HashMap<String, String>();
		if(ufs!=null){
			try {
				Map<String, String> map2 = queryBanlance(ufs.getUsrcustid());
				if(map2.size()>0){
					map.put("status", "1");
					map.put("availbal", map2.get("AVAIL_BAL"));//可用余额
					map.put("currbal", map2.get("CURR_BAL"));//总余额
				}else{
					map.put("status", "0");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//查询余额
		}else{
			map.put("status", "0");
		}
		return map;
	}
	
	/**
	 * 点击充值跳转页面返回给app的参数
	 * @param @param baseid
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	public Map<String,String> getUserInfo(UserBaseAccountInfo user,UserFsAccountInfo ufs){
		//4.声明一个接收值的map
		Map<String,String> map = new HashMap<String, String>();
		//2,获取绑定卡的卡号对象
		String dataStr = "";
		UserBankCard ucard = userBankCardMapper.selectBoundCardByBaseId(user.getId());
		if(ucard!=null){
			map.put("subbranch",ucard.getSubbranch());//开户行
			map.put("bankname", ucard.getBankname());//银行名字
			map.put("cardno", ucard.getCardno());//银行卡号
		}else{
			dataStr = "ucardfalse";
			map.put("dataStr", dataStr);
		}
		if(ufs!=null){
			map.put("username", ufs.getUsrname()); //开户人;
			map.put("usrcustid",ufs.getUsrcustid()); //电子账号
			map.put("phone", ufs.getFsmobile());//手机号码
			try {
				Map<String, String>  map2 = queryBanlance(ufs.getUsrcustid());
				if(map2.size()>0){
					map.put("querystatus", "1");
					map.put("availbal", map2.get("AVAIL_BAL"));//可用余额
					map.put("currbal", map2.get("CURR_BAL"));//总余额
				}else{
					map.put("querystatus", "0");//查询余额失败
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//查询余额
		}else{
			dataStr = "ufsfalse";
			map.put("dataStr", dataStr);
		}
		return map;
	}
	/**
	 * 短信发送并返回:返回值包含应答码+应答时间+验证码有效时长+短信发送时间
	 * 应答码是00000000表示成功
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@Override
	public Map<String, String> getmessage(UserBaseAccountInfo user, UserFsAccountInfo ufs) {
		Map<String,String> hashMap =new HashMap<String, String>();
		//2,获取绑定卡的卡号对象
	   UserBankCard ucard = userBankCardMapper.selectBoundCardByBaseId(user.getId());
		String  FUNCTION = "7601";
		String  MOBILE =  AES.getDecrypt(ufs.getFsmobile());
		String  CARD_BIND =  AES.getDecrypt(ucard.getCardno());
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
		//String  RESERVE = "";
		String  orderno = StringUtil.getNo();//交易流水号
		System.out.println(orderno);
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		reqMap.put("TRXCODE", "3031");
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);
        reqMap.put("TRXDATE", TRXDATE);
        reqMap.put("TRXTIME", TRXTIME);
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);
        reqMap.put("COINSTCHANNEL",HSignUtil.COINSTCHANNEL_WEB);
        reqMap.put("SEQNO",orderno);//交易流水号
        reqMap.put("SOURCE", HSignUtil.SOURCE);
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
     /*   reqMap.put("HEADRESERVED", "");*/
        
        reqMap.put("FUNCTION", FUNCTION);
        reqMap.put("MOBILE", MOBILE);
        reqMap.put("CARD_BIND", CARD_BIND);
       /* reqMap.put("RESERVE", RESERVE);*/
        Map<String,String> map = new HashMap<String,String>();
		try {
			 String result = HSignUtil.HttpClientTask(reqMap, "");
			hashMap = callBack(result);
			if(hashMap.get("RETCODE").equals("00000000")){
				map.put("status", "1");
			}else{
				map.put("status", "0");
			}
			map.put("send_time", hashMap.get("SEND_TIME")); 
			map.put("sms_seq", hashMap.get("SMS_SEQ"));
		    map.put("valid_time", hashMap.get("VALID_TIME"));
		    map.put("reserve", hashMap.get("RESERVE"));
		    map.put("retmsg", hashMap.get("RETMSG"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
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
		 /* phoneList.add("RESERVE");// 保留域*/		 
		  String resultdata = HSignUtil.getDecryptRSAByte(result);//解密
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
	    System.out.println(json.getString("SMS_SEQ")+"============="); //应答描述
	    hashMap.put("SEND_TIME", json.getString("SEND_TIME")); 
	    hashMap.put("SMS_SEQ", json.getString("SMS_SEQ"));
	    hashMap.put("VALID_TIME", json.getString("VALID_TIME"));
	    hashMap.put("RESERVE", json.getString("RESERVE"));
	    hashMap.put("RETCODE", json.getString("RETCODE"));
	    hashMap.put("RETMSG", json.getString("RETMSG"));
		return hashMap;
	} 
	
	/**
	 * 点击确认按钮的时候跳转的页面
	 * 验证该用户在该等级下+充值方式下+充值限制启用状态的充值限制
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String dmoney(String rechargeType,String money,UserBaseAccountInfo user){
		String data = "";
		if (user != null) {
			/**充值记录对象new*/
			UserRecharge userR = new UserRecharge();
			/**获取充值费率表*/
			RechargeRate rechargeRate  = rechargeRateMapper.selectRechartypeByRechargeRate(new Short(rechargeType));
			userR.setBaseid(user.getId());
			//查询当天充值成功的充值记录
			List<UserRecharge> UR = userRechargeMapper.selectAmountList(userR);
			//查询当天的充值限额
			Double totalAmount = 0.0;
			for (UserRecharge userRecharge : UR) {
				totalAmount+=userRecharge.getAmount();
			}
			if(rechargeRate!=null){
				//查询是否在
				List<RechargeSNLLink> rechargeSNLLinkList = rechargeSNLLinkMapper.selectByrrid(rechargeRate.getId());
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
				}else{
					data="nosnllink";
				}
				
				if(data.equals("has")){
					//查询
					if(rechargeRate.getChargetype()==1){//定额
						//查询定额手续费表的数据
						List<RechargeQuotaFee>  rechargeQuotaFeeList =  rechargeQuotaFeeMapper.selectByrrid(rechargeRate.getId());
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
	 * 网银充值的时候,金额校验的时候提示信息
	 * @param @param sum
	 * @param @param money
	 * @param @param user
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	public String dmoney2(String sum,String money,UserBaseAccountInfo user) {
		String data = "";
		if (user != null) {
			/**充值记录对象new*/
			UserRecharge userR = new UserRecharge();
			/**获取充值费率表*/
			RechargeRate rechargeRate  =  rechargeRateMapper.selectRechartypeByRechargeRate(new Short(sum));
			userR.setBaseid(user.getId());
			//查询当天充值成功的充值记录
			List<UserRecharge> UR = userRechargeMapper.selectAmountList(userR);
			//查询当天的充值限额
			Double totalAmount = 0.0;
			for (UserRecharge userRecharge : UR) {
				totalAmount+=userRecharge.getAmount();
			}
			if(rechargeRate!=null){
				//查询是否在
				List<RechargeSNLLink> rechargeSNLLinkList = rechargeSNLLinkMapper.selectByrrid(rechargeRate.getId());
				List<String> ruslut = new ArrayList<String>();
				if(rechargeSNLLinkList!=null && rechargeSNLLinkList.size()>0){
					for (RechargeSNLLink rechargeSNLLink : rechargeSNLLinkList) {
						boolean flag = userDebtAttornService.ugradeFalsePublic(rechargeSNLLink.getSnlid(), user.getId());//true 不能充值
						if(flag){
							ruslut.add("0");//表示在名单中,在名单中是不允许充值的
						}else{
							ruslut.add("1");//表示不在名单中,是可以允许充值的
						}
					}
					String dataStr = "has";//默认是可以充值的
					for (String str : ruslut) {//如果这个集合中有一个在名单中,说明不能充值
						if(str.equals("0")){
							dataStr = "nohas";
							break;
						}
					}
					
					if(dataStr.equals("nohas")){
						data="对不起,你不在可充值的名单中";//表示在排除人名单中
					}else{
						data = "has"; //如果为false,说明就在派出人名单之外,说明是可以充值的
					}
				}else{
					data="请管理员设置充值费率";
				}
				
				if(data.equals("has")){
					//查询
					if(rechargeRate.getChargetype()==1){//定额
						//查询定额手续费表的数据
						List<RechargeQuotaFee>  rechargeQuotaFeeList =  rechargeQuotaFeeMapper.selectByrrid(rechargeRate.getId());
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
							data = "充值金额小于最小值,请重新输入";
						}
						if(Double.valueOf(money)>max){
							data = "充值金额大于最大值,请重新输入";
						}
						if(Double.valueOf(money)>=min && Double.valueOf(money)<=max){
							data = "成功";
						}
					}else{//百分比
						data="成功";
						
					}
				}
			}else{
				data="没有充值费率表,请联系管理员设置";//没有充值费率表,请联系管理员设置
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
	public Map<String,String> confirmRecharge(UserBaseAccountInfo user,UserFsAccountInfo ufs,HttpServletRequest request){
		String money = request.getParameter("money");// 充值金额
		String rechargeType = request.getParameter("rechargeType");// 充值方式
		String sms_code = request.getParameter("sms_code");// 验证码
		String sms_seq = request.getParameter("sms_seq");// 验证码序号
		//4.声明一个返回值的map
		Map<String, String> map = new HashMap<String, String>();
		//对金额进行处理,并且返回
		String re = "([1-9]\\d*|0)(\\.\\d{1,2})?";
		if(money.matches(re)){//为true,说明手机端输入金额正确
			if(rechargeType!=null){
				map.put("typestatus", "1");
				String falg = dmoney(rechargeType,money,user);
				if(falg.equals("success")){
					map.put("moneychecking", falg);
					if(rechargeType.equals("1")){//网银
						
					}
					if(rechargeType.equals("2")){//快捷
						if(sms_code!=null){
							map.put("codestatus", "1");
							if(sms_seq!=null){
								map.put("seqstatus", "1");
								/*Map<String,String> hashMap  = shortcut(user,ufs,money,rechargeType,sms_code,sms_seq);
								if(hashMap.get("RETCODE").equals("00000000")){
									map.put("status", "1");//状态码
								}else{
									map.put("status", "0");//状态码
								}
								map.put("RETMSG", hashMap.get("RETMSG"));//应答描述
*/							}else{
								map.put("seqstatus", "0");
							}
						}else{
							map.put("codestatus", "0");
						}
					}
					if(rechargeType.equals("4")){//汇款充值
						
					}
				}else{
					map.put("moneychecking", falg);//金额校验没有通过
				}
			}else{
				map.put("typestatus", "0");
			}
				
		}else{
			map.put("moneychecking", "moneyfalse");
		}
		return map;
	
}
	/**
	 * 快捷支付
	 * @param @return 
	 * @return Map<String,String>
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	public   String shortcut(UserBaseAccountInfo user,UserFsAccountInfo ufs,HttpServletRequest request,String sms_seq){
		String money = request.getParameter("money");
		String rechargeType = request.getParameter("sum");
		String sms_code = request.getParameter("sms_code");	
		String data = dmoney(rechargeType,money,user);
		if(data.equals("success")){
			DecimalFormat df1 = new DecimalFormat("##########0.00");
			//2,获取绑定卡的卡号对象
			UserBankCard ucard = userBankCardMapper.selectBoundCardByBaseId(user.getId());
			Date date = new Date();
			String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
			String TRXTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
			String BgRetUrl = "http://my.ganjiangps.com/user/userRecharge/UserRechargeCallback.action";
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
			reqMap.put("CARDNBR", AES.getDecrypt(ufs.getUsrcustid())); //电子账号 也就是用户客户号 "9930040050240500013"
			reqMap.put("CARD_BIND",  AES.getDecrypt(ucard.getCardno()));//绑定卡卡号 需要从UserBankCard 根据baseid查询 ucard.getCardNo()
			reqMap.put("CURRENCY", "156");
			reqMap.put("AMOUNT", df1.format(Double.valueOf(money))); //充值金额从页面获取
			reqMap.put("KEYTYPE", "01"); 
			reqMap.put("IDNO",  user.getCertificationnumber()); //身份证号 从userBaseAccountInfo 用户基本信息表中获取certificationNumber
			reqMap.put("NAME",  user.getRealname());  //用户真实姓名也从用户基本信息表中获取
			reqMap.put("MOBILE",  AES.getDecrypt(ufs.getFsmobile())); //电话号码从用户托管账户表中获取fsmobile
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
			if(sms_seq==null){
				sms_seq="";
			}
			reqMap.put("SMS_SEQ", sms_seq);//短信序列号
			reqMap.put("USR_IP", user.getId().toString());//客户序列号
			reqMap.put("RESERVED", "");//保留域
			Map<String,String> hashMap = new HashMap<String,String>();
			/**在发数据去银行之前保存一部分充值数据到本地数据库*/
			save(rechargeType,money,user,ucard,ufs,orderno,reqMap);
			//发送请求去徽商银行
			try {
				String result =  HSignUtil.HttpClientTask(reqMap, "v20160907");
				hashMap = callBack2(result);
				if(hashMap.get("RETCODE").equals("00000000")){//充值成功
					SMSSendService.SMSSend4Recharge(AES.getDecrypt(ufs.getFsmobile()), "快捷支付", user.getLoginname(), df1.format(Double.valueOf(money)), user.getId());
				}else{//返回错误码的时候把错误信息存进备注信息中
					UserRecharge usRecharge = select(orderno);
					usRecharge.setRemark(MessageUtil.getMassage(hashMap.get("RETCODE")));
					update(usRecharge);
				}
				/**处理返回值*/
				data = updateMessage(hashMap,user.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
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
		JSONObject json = JSONObject.fromObject(resultdata);
		hashMap.put("RETCODE", json.getString("RETCODE")); // 应答码
		hashMap.put("RETMSG", json.getString("RETMSG")); // 应答码描述
		if(json.getString("RETCODE").equals("00000000")){
			hashMap.put("SEQNO", json.getString("SEQNO")); // 交易流水号
			hashMap.put("CARDNBR", json.getString("CARDNBR")); // 电子账户
			hashMap.put("CURRENCY", json.getString("CURRENCY"));// 币种
			hashMap.put("AMOUNT", json.getString("AMOUNT"));// 金额
			hashMap.put("RESERVED", json.getString("RESERVED"));// 保留域
		}
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
	public void save(String rechargeType,String money,UserBaseAccountInfo user,UserBankCard ucard,UserFsAccountInfo  ufs,String orderno,LinkedHashMap<String,String> reqMap){
		UserRecharge userRecharge = new UserRecharge();
		RechargeRate rechargeRate = rechargeRateMapper.selectRechartypeByRechargeRate(new Short(rechargeType));
		// 声明充值费率
		Double recharrate = 0.00;
		if (rechargeRate != null) {
			if (rechargeRate.getChargetype() == 1) {// 定额
				List<RechargeQuotaFee> rechargeQuotaFeeList = rechargeQuotaFeeMapper.selectByrrid(rechargeRate.getId());
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
		userRecharge.setMercustid(ufs.getUsrcustid());// 保存用户电子账号
		userRecharge.setBaseid(user.getId());// 保存用户id
		userRecharge.setRecharrate(recharrate);// 保存充值费率
		userRecharge.setAmount(Double.valueOf(money));// 充值金额
		userRecharge.setStarttime(new Date()); // 充值开始时间
		userRecharge.setRechargetype((short) 2);// 充值方式
		userRecharge.setApplyman(AES.getEncrypt(user.getLoginname())); // 充值人!也就是登陆的用户
		userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); // 表示系统未勾兑
		userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);// 表示人工未勾兑
		userRecharge.setPaycompany("徽商银行"); // 充值通道公司
		short originclient = user.getOriginclient() == null ? 1 : user.getOriginclient();
		userRecharge.setOriginclient(originclient);// 充值来源
		userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); // 表示充值状态取消
		userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);// 充值是正常
		userRecharge.setCardno(ucard.getCardno());
		userRecharge.setReqquerydata(reqMap.toString());
		userRecharge.setUuid(StringUtil.getRechargeNo());
		userRecharge.setUrid("0");
		add(userRecharge);
	}
	/**
	 * 修改充值记录,添加收支记录,修改账户金额
	 * @param @param hashMap
	 * @param @param baseid
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
public String updateMessage(Map<String, String> hashMap, BigDecimal baseid){
	String data = "";
	if(hashMap.size()>0){
		if (hashMap.get("RETCODE").equals("00000000")) {// 表示徽商那边充值成功了
			data = "success";
			/** 1.接下来修改充值记录 ***********************/
			// 根据请求头中的充值订单号查询本地库中的充值记录
			UserRecharge usRecharge = select(hashMap.get("SEQNO"));
			if (usRecharge.getBankreturnno() == null) {// 避免重复加入充值记录和更新用户余额还有收支记录
				Double AMOUNT = Double.valueOf(hashMap.get("AMOUNT"));

				// 主要修改充值状态变为充值成功
				usRecharge.setAmount(AMOUNT);
				usRecharge.setEndtime(new Date());// 充值结束时间
				usRecharge.setStatus((short) 1);// 充值成功
				usRecharge.setBankreturnno(hashMap.get("SEQNO"));// 银行返回充值订单号存入充值订单号
				update(usRecharge);

				/** 3(准备).修改用户账户金额 ***************/
				UserAccount userAccount = userAccountMapper.getUserAccountByBaseId(baseid);
				/** 获取徽商银行的余额 */
				Map<String, String> mapCount = new HashMap<String,String>();
				try {
					mapCount = queryBanlance(hashMap.get("CARDNBR"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 充值金额
				double amount = Double.valueOf(AMOUNT);

				/** 2.添加收支记录 **************/
				AccInExRecord accInExRecord = new AccInExRecord();// 存收支记录的对象
				accInExRecord.setOutamount(0.0);// 用户支出
				accInExRecord.setStatus((short) 1);
				accInExRecord.setTotalbalance(Double.valueOf(mapCount.get("CURR_BAL")));// 用户总金额
				accInExRecord.setFreebalance(Arith.sub(Double.valueOf(mapCount.get("CURR_BAL")), Double.valueOf(mapCount.get("AVAIL_BAL"))));// 冻结金额
				accInExRecord.setBaseid(baseid);
				accInExRecord.setType((short) 1);// type 为1 表示业务类型为充值
				accInExRecord.setInamount(amount);// 用户收入
				accInExRecord.setBalance(Double.valueOf(mapCount.get("AVAIL_BAL")));// 可用余额
				accInExRecord.setRecordtime(new Date());// 时间
				accInExRecord.setAieorderno(StringUtil.getNo()); // 保存收支明细流水号
				accInExRecord.setDescription("充值说明");
				accInExRecord.setRemark("备注");
				accInExRecordMapper.insertSelective(accInExRecord);

				/** 3(修改).修改用户账户金额 ***************/
				userAccount.setBaseid(baseid);
				userAccount.setBalance(Double.valueOf(mapCount.get("CURR_BAL")));
				userAccount.setAvlbalance(Double.valueOf(mapCount.get("AVAIL_BAL")));
				userAccount.setFreezebalance(Arith.sub(Double.valueOf(mapCount.get("CURR_BAL")), Double.valueOf(mapCount.get("AVAIL_BAL"))));
				userAccountMapper.updateUseraccount(userAccount);
			}else{
				data = "rechargeFalse";//表示充值失败
			}
		}else{
			data = MessageUtil.getMassage(hashMap.get("RETCODE"));
		}
	}
		return data;
	}
    public int saveUserRecharge(String sum,String money,String orderno,UserBaseAccountInfo user,UserFsAccountInfo  ufs,LinkedHashMap<String,String> reqMap){
    	//声明一个保存充值记录的对象
    	UserRecharge usRecharge = new  UserRecharge();
    	//先做定向名单的判断
    	RechargeRate rechargeRate  = rechargeRateMapper.selectRechartypeByRechargeRate(new Short(sum));
    	//声明充值费率
    	Double recharrate = 0.00;
    	if(rechargeRate.getChargetype()==1){//定额
    		List<RechargeQuotaFee>  rechargeQuotaFeeList =  rechargeQuotaFeeMapper.selectByrrid(rechargeRate.getId());
    		if(rechargeQuotaFeeList!=null && rechargeQuotaFeeList.size()>0){
    			for (RechargeQuotaFee rechargeQuotaFee : rechargeQuotaFeeList) {
    				if(Double.valueOf(money)>rechargeQuotaFee.getMinmoney() && Double.valueOf(money)<= rechargeQuotaFee.getMaxmoney()){
    					usRecharge.setRecharfee(rechargeQuotaFee.getQuotafee());
    					break;
    				}
    			}
    		}
    	}else{
    		//算出百分比算出的金额
    		Double fee = Arith.mul(Double.valueOf(money), rechargeRate.getCharrate());
    		if(fee<rechargeRate.getMinfee()){
    			usRecharge.setRecharfee(rechargeRate.getMinfee());
    		}else if(fee>rechargeRate.getMinfee()){
    			usRecharge.setRecharfee(rechargeRate.getMaxfee());
    		}else{
    			usRecharge.setRecharfee(fee);
    		}
    		recharrate = rechargeRate.getCharrate();
    	}
    	
    	
    	//在发数据去银行之前保存一部分充值数据到本地数据库
    	usRecharge.setRechargeno(orderno);//充值订单号
    	usRecharge.setRechargetype((short)1);//充值方式
    	usRecharge.setBaseid(user.getId());//保存用户id
    	usRecharge.setStarttime(new Date()); //充值开始时间
    	usRecharge.setAmount(Double.valueOf(money));//充值金额
    	usRecharge.setMercustid(ufs.getUsrcustid());//商户客户号
    	usRecharge.setRecharrate(recharrate);//保存充值费率
    	usRecharge.setApplyman(AES.getEncrypt(user.getLoginname())); //充值人!也就是登陆的用户
    	usRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
    	usRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
    	usRecharge.setPaycompany("徽商银行"); //充值通道公司
    	short originclient = user.getOriginclient()==null? 1:user.getOriginclient();
    	usRecharge.setOriginclient(originclient);//充值来源
    	usRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); //表示充值状态取消 
    	usRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
    	//usRecharge.setCardno(ucard.getCardNo());
    	usRecharge.setUuid(StringUtil.getRechargeNo());
    	usRecharge.setReqquerydata(reqMap.toString());
    	usRecharge.setUrid("0");
    	int s = add(usRecharge);
    	return s;
    }
    
    //姓名加密
    public String nameEncryption(String str){
		String name = str.substring(1,str.length());
		String nameStr = "*"+name;
		return nameStr;
    }
    //电子账户号加密
    public String curstrnoEncryption(String str){
    	String name = "9930040050240450029";
		String str1 = str.substring(0,4);//前四位
		String str2 = str.substring(15,str.length());//后四位
		String str3 = str.substring(4,str.length()-4);//中间数字
		String str4 = "";
		for (int i = 0; i < str3.length(); i++) {
			str4+="*";
		}
		String numStr = str1+str4+str2;
    	return numStr;
    }

}
