package com.ptpl.controller.huishang;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cupdata.zicon.util.RSAEncryptUtil;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.huishang.util.HSignUtil;
import com.huishang.util.MessageUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.UserBackstageRechargeController;
import com.ptpl.controller.moneymoremore.MMMAuthorizationController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RechargeRate;
import com.ptpl.model.RechargeRstr;
import com.ptpl.model.RemoveName;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserAuthorization;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RechargeRateServiceI;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserAuthorizationServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.service.UserServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;
/**用户后台充值*/
@Controller
@RequestMapping("/huishang/userRecharge")
public class HuishangUserBackstageRechargeController extends BaseController{
	/** 充值记录*/
	@Autowired
	UserRechargeServiceI userRechargeService;
	@Autowired
	UserServiceI userSrevice;
	/** 用户基本信息  ServiceI */
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/** 托管账户信息*/
	@Autowired
	UserFsAccountInfoServiceI  userFsAccountInfoService;
	/**银行卡信息*/
	@Autowired
	UserBankCardServiceI userBankCardService;
	/**用户账户表*/
	@Autowired
	UserAccountServiceI userAccountService;
	/**用户收支明细记录*/
	@Autowired 
	AccInExRecordServiceI accInExRecordService;
	/**用户账户信息安全表*/
	@Autowired
	UserAccountSafeInfoServiceI  userAccountSafeInfoService;
	/**充值设置限制*/
	@Autowired
	RechargeRstrServiceI rechargeRstrService;
	/**排出人名单*/
	@Autowired
	RemoveNameServiceI removeNameService;
	@Autowired
	UserAuthorizationServiceI userAuthorizationService;
	@Autowired
	UserDebtAttornServiceI userDebtAttornService;
	@Autowired
	RechargeRateServiceI rechargeRateService;
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private SMSSendServiceI SMSSendService;
	@Autowired
	QueryBlaneServiceI queryBlaneService;
	
	/**页面返回
	 * 充值列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeList(String encryptData) throws Exception {
		UserRechargeCall(encryptData);
		UserFsAccountInfo ufs = new UserFsAccountInfo();
		UserBaseAccountInfo user = new UserBaseAccountInfo();
		if(encryptData!=null && encryptData!=""){
			 //解密
			 String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(HSignUtil.DECRYPTPATH);
			 String decryptResult = RSAUtils.decryptRSAs(encryptData,decryptKey4Server);
			 
			Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(decryptResult);//数据转化Map
			 //验签
			boolean verifyResult = attestation(responseMap);
			//将数据转为json对象
			JSONObject json = JSONObject.fromObject(decryptResult);
			
			 if(verifyResult && json.get("RETCODE").equals("00000000")){//假如验证签名成功并且
				// 通过保留域拿到交易流水号
				 String SEQNO = json.getString("PRIV1");
				 /**修改充值记录*/
				 UserRecharge usRecharge = userRechargeService.select(SEQNO);
				 if(usRecharge!=null){
					 ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(usRecharge.getBaseid());
					 user = userBaseAccountInfoService.getUserBaseAccountInfoById(usRecharge.getBaseid());
				 }
			 }
		} 
		
		ModelAndView modelAndView = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		Map map = new HashMap();
		initPage(map, pageNum,"5");
		UserRecharge ur = new UserRecharge();
		ur.setUbai(user);
		List<UserRecharge> userRecharge = userRechargeService.getAll(ur);
		PageInfo<Object> pagehelper = initPagehelper(map, userRecharge);
		
		/**以下这段代码是获取快捷银行卡的*/
		UserAccount uc = userAccountService.getUserAccountByBaseId(user.getId());//根据用户id查询用户账户对象
		//获取快捷银行卡号
		UserBankCard userBankCard = userBankCardService.selectBoundCardByBaseId(user.getId());
		String bankname = "";
		String cardNo = "";
		if(userBankCard!=null){
			bankname = userBankCard.getBankname();
			cardNo =  AES.getDecrypt(userBankCard.getCardno()).replaceAll("(\\d{4})\\d*(\\d{4})", "$1***********$2");//给卡号加密
		}
		if(uc==null){
			uc = new UserAccount();
			uc.setAvlbalance(0.0);
			uc.setBalance(0.0);
			uc.setFreezebalance(0.0);
		}
	       String str =  AES.getDecrypt(ufs.getFsmobile());
	       String str2 = str.substring(0,3); 
	       String str3 = str.substring(str.length()-3,str.length());
	       String phoneStr = str2+"******"+str3;
			modelAndView.addObject("bankname", bankname);
			modelAndView.addObject("cardNo", cardNo);
			modelAndView.addObject("pagehelper", pagehelper);
			modelAndView.addObject("phone",  AES.getDecrypt(ufs.getFsmobile()));
			modelAndView.addObject("phonestr", phoneStr);
			modelAndView.addObject("userBankCard", userBankCard);
			modelAndView.addObject("realname",  AES.getDecrypt(user.getRealname()));
			modelAndView.addObject("usercustid",  AES.getDecrypt(ufs.getUsrcustid()));
			modelAndView.addObject("uc", uc);
			modelAndView.addObject("that", "63");
			modelAndView.addObject("sf", sf);
			modelAndView.addObject("df", df);
			modelAndView.setViewName("user/manager/recharge/step");
		return modelAndView;
}
	/**
	 * 转发到本地
	 * @param @param params
	 * @param @return
	 * @param @throws ClientProtocolException
	 * @param @throws IOException
	 * @return String
	 * @author jiangxueyou
	 */
	public String doPost(Map params) throws ClientProtocolException, IOException
	{
		String result = null;
		HttpPost httpPost = new HttpPost("http://116.30.193.51:8883/ptpjx/huishang/userRecharge/UserRechargeCall.action");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if (params != null)
		{
			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
			httpPost.setEntity(formEntiry);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try
			{
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			} finally
			{
				response.close();
			}
		}
		return result;
	}
	/**
	 * 转发到本地
	 * @param 
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value="callback")
	public void callback(){
		Map<Object,String> map=new HashMap<Object,String>();
		map.put("encryptData", request.getParameter("encryptData"));
		map.put("COINSTCODE", request.getParameter("COINSTCODE"));
		map.put("TRXCODE", request.getParameter("TRXCODE"));
		response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().write("success");
				doPost(map);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 后台返回
	 * 回调地址:在这里面进行解签验签
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/UserRechargeCall", method = { RequestMethod.POST, RequestMethod.GET })
	public void UserRechargeCall(String encryptData) throws Exception {
		System.out.println(request.getParameter("COINSTCODE"));
		System.out.println(request.getParameter("TRXCODE"));
		if(encryptData!=null && encryptData!=""){
			 //解密
			 String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(HSignUtil.DECRYPTPATH);
			 String decryptResult = RSAUtils.decryptRSAs(encryptData,decryptKey4Server);
			 
			Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(decryptResult);//数据转化Map
			 //验签
			boolean verifyResult = attestation(responseMap);
			//将数据转为json对象
			JSONObject json = JSONObject.fromObject(decryptResult);
			logger.info("这是返回值"+json+"这是返回值");
			// 通过保留域拿到交易流水号
			String SEQNO = json.getString("PRIV1");
			/**根据订单号查询充值记录*/
			UserRecharge usRecharge = userRechargeService.select(SEQNO);
			
			 if(verifyResult && json.get("RETCODE").equals("00000000")){//假如验证签名成功并且充值成功
				 String data="";
				 /**修改充值记录*/
				 if(usRecharge!=null){
					 if(usRecharge.getBankreturnno()==null){//避免重复加入充值记录和更新用户余额还有收支记录
						 if(!df1.format(Double.valueOf(json.get("AMOUNT").toString())).equals(df1.format(usRecharge.getAmount()))){
							 //说明充值的金额和先前本地送过去的比一致
							 data = "theamountofamount"; 
							 return;
						 }
						 UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(usRecharge.getBaseid());
						 UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoById(usRecharge.getBaseid());
						 if(user.getAccounttype()==1){
							 SMSSendService.SMSSend4Recharge(AES.getDecrypt(ufs.getFsmobile()), "个人网银", AES.getDecrypt(user.getLoginname()), usRecharge.getAmount().toString(), user.getId());
							 logger.info("充值金额"+usRecharge.getAmount().toString());
						 }else{
							 SMSSendService.SMSSend4Recharge(AES.getDecrypt(ufs.getFsmobile()), "企业网银", AES.getDecrypt(user.getLoginname()), usRecharge.getAmount().toString(), user.getId());
						 }
						 
						 data = "success";
						 usRecharge.setEndtime(new Date());//充值结束时间
						 usRecharge.setStatus((short)1);//充值成功 
						 usRecharge.setRecresultdata(json.toString());
						 usRecharge.setBankreturnno(json.get("ORDERNO").toString());//银行返回充值订单号
						 userRechargeService.update(usRecharge);
						 
						 /**修改用户账户信息*/
						
						 Map<String,String> map = queryBlaneService.queryBlane(AES.getDecrypt(usRecharge.getMercustid()));
						 UserAccount userAccount = userAccountService.getUserAccountByBaseId(usRecharge.getBaseid());
						 userAccount.setBaseid(usRecharge.getBaseid());
						 userAccount.setBalance(Double.valueOf(map.get("CURR_BAL")));
						 userAccount.setAvlbalance( Double.valueOf(map.get("AVAIL_BAL")));
						 userAccount.setFreezebalance(Arith.sub(Double.valueOf(map.get("CURR_BAL")), Double.valueOf(map.get("AVAIL_BAL"))));
						 userAccountService.updateUseraccount(userAccount);
						 
						 /**添加收支记录:因为充值是不收用户的钱的!所以这里就不插入手续费收支记录了*/  
						 AccInExRecord accInExRecord = new AccInExRecord();//存收支记录的对象
						 accInExRecord.setOutamount(0.0);//用户支出
						 accInExRecord.setStatus((short)1);
						 accInExRecord.setTotalbalance(Double.valueOf(map.get("CURR_BAL")));//用户总金额
						 accInExRecord.setFreebalance(Arith.sub(Double.valueOf(map.get("CURR_BAL")), Double.valueOf(map.get("AVAIL_BAL"))));//冻结金额
						 accInExRecord.setBaseid(usRecharge.getBaseid());
						 accInExRecord.setType((short)1);//type 为1 表示业务类型为充值
						 accInExRecord.setInamount(usRecharge.getAmount());//用户收入
						 accInExRecord.setBalance(Double.valueOf(map.get("AVAIL_BAL")));//可用余额
						 accInExRecord.setRecordtime(new Date());//时间
						 accInExRecord.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
						 accInExRecord.setDescription("充值说明");
						 accInExRecord.setRemark("备注");
						 System.out.println(System.currentTimeMillis()+"插入时间-------------------------");
						 accInExRecordService.insertSelective(accInExRecord);
				     }
				 }
			 }else{//如果充值不成的情况下往备注里面添加错误信息
				 if(usRecharge!=null){
					 usRecharge.setRemark(MessageUtil.getMassage(json.get("RETCODE").toString()));
					 userRechargeService.update(usRecharge);
				 }
			 }
		}
	}
	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })	
	public void test(){
		 SMSSendService.SMSSend4Recharge("13708194451", "个人网银", "大王叫我来巡山", "500.00", new BigDecimal(1022));
	}

	/**
	 * 封装验签方法
	 * @param @param responseMap
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	public boolean attestation(Map<String,Object> responseMap){
		StringBuffer responseMapMerged = new StringBuffer();
		 String ORDERNO = (String) responseMap.get("ORDERNO");
		 String ACCT_NO = (String) responseMap.get("ACCT_NO");
		 String AMOUNT = (String) responseMap.get("AMOUNT");
		 String TRANSDATE = (String) responseMap.get("TRANSDATE");
		 String BANKCODE = (String) responseMap.get("BANKCODE");
		 String COINSTCODE = (String) responseMap.get("COINSTCODE");
		 String PRIV1 = (String) responseMap.get("PRIV1");
		 String RETCODE = (String) responseMap.get("RETCODE");
		 //拼接
		 responseMapMerged.append(ORDERNO).append(ACCT_NO)
		 .append(AMOUNT).append(TRANSDATE)
		 .append(BANKCODE).append(COINSTCODE)
		 .append(PRIV1).append(RETCODE);
		 String responseSign = (String) responseMap.get("SIGN");
		 //验签
		boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(),responseSign);//验证签名
		return verifyResult;
	}
	
}
