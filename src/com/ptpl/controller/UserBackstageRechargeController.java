package com.ptpl.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RechargeRateServiceI;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.service.RemoveNameServiceI;
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
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;
/**用户后台充值*/
@Controller
@RequestMapping("/user/userRecharge")
public class UserBackstageRechargeController extends BaseController{
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
	QueryBlaneServiceI queryBlaneService;
	
	@RequestMapping(value = "/queryBlaneAndupdateAccount", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryBlaneAndupdateAccount() throws Exception{
		Map<String,String> hashMap  = new HashMap<String,String>();
		String data = "";
		//获取登录的用户
		UserBaseAccountInfo user =  getUserBaseAccountInfo();
		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		//查询本地账户表
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(user.getId());
		hashMap.put("result", "0");
		hashMap.put("msg", "未开户！");
		if(ufs!=null && ufs.getIsopenfsinfo()==1){
			Map<String,String> map= queryBlaneService.queryBlane(AES.getDecrypt(ufs.getUsrcustid()));//获取到余额查询的返回
			if(map.size()>0){
				//更新本地用户账户表记录
				queryBlaneService.updateUserAccount(map, userAccount);
				Double Freezebalance =Arith.sub(Double.valueOf(map.get("CURR_BAL")), Double.valueOf(map.get("AVAIL_BAL")));
				hashMap.put("result", "1");
				hashMap.put("msg", "查询成功");
				hashMap.put("balance", map.get("CURR_BAL"));
				hashMap.put("avlBalance", map.get("AVAIL_BAL"));
				hashMap.put("freezeBalance", Freezebalance.toString());
			}else{
				hashMap.put("result", "1");
				hashMap.put("msg", "接口查询失败");
				hashMap.put("balance", userAccount.getBalance().toString());
				hashMap.put("avlBalance", userAccount.getAvlbalance().toString());
				hashMap.put("freezeBalance", userAccount.getFreezebalance().toString());
			}
		}
		data = JSON.toJSONString(hashMap);
		sendJsonData(response, data); 
	}
	/**
	 * 充值前置,给用户提示
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/rechargePre", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargePre() throws Exception {
		//获取登录的用户
		UserBaseAccountInfo user =  getUserBaseAccountInfo();
		//获取托管账户信息
		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		String data = "";
		UserBankCard userBankCard = userBankCardService.selectBoundCardByBaseId(user.getId());
		//logger.info("这个是是否绑卡"+userBankCard.getCardno());
		logger.info("开始*******************************");
		//先实名认证 再开户 再绑卡
		if(user.getIsreally()==0){//表示没有进行实名认证
			data="0";
		}else{//进行了实名认证然后判断开户 绑卡一系列操作
			if(ufs!=null && ufs.getIsopenfsinfo()==1){
				if(userBankCard!=null && userBankCard.getCardno()!=null){
					data="2";
					UserAccount userAccount = userAccountService.getUserAccountByBaseId(user.getId());
					System.out.println(AES.getDecrypt(ufs.getUsrcustid()));
					Map<String,String> map= queryBlaneService.queryBlane(AES.getDecrypt(ufs.getUsrcustid()));//获取到余额查询的返回
					//写个更新接口更新用户账户表
					logger.info("这个是返回值******************"+map+"+++++++//////////////////////////////////");
					if(map.size()>0){
						userRechargeService.updateUserAccount(map,userAccount);
					}
				}else{
					data="3";//说明没有绑卡
				}
			}else{
				data="1";//没有开通托管账户
			}
		}
		logger.info(data+"结束*******************************");
	    sendJsonData(response, data); 
	}
	
	/**
	 * 充值列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeList() throws Exception {
		// 获取登录的用户
		UserBaseAccountInfo user = getUserBaseAccountInfo();
		// 获取用户开户信息
		UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		ModelAndView modelAndView = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		Map map = new HashMap();
		initPage(map, pageNum, "5");
		UserRecharge ur = new UserRecharge();
		ur.setUbai(user);
		List<UserRecharge> userRecharge = userRechargeService.getAll(ur);
		PageInfo<Object> pagehelper = initPagehelper(map, userRecharge);

		/** 以下这段代码是获取快捷银行卡的 */
		UserAccount uc = userAccountService.getUserAccountByBaseId(user.getId());// 根据用户id查询用户账户对象
		// 获取快捷银行卡号
		UserBankCard userBankCard = userBankCardService.selectBoundCardByBaseId(user.getId());
		String bankname = "";
		String cardNo = "";
		if (userBankCard != null) {
			bankname = userBankCard.getBankname();
			cardNo = AES.getDecrypt(userBankCard.getCardno()).replaceAll("(\\d{4})\\d*(\\d{4})", "$1***********$2");// 给卡号加密
		}
		if (uc == null) {
			uc = new UserAccount();
			uc.setAvlbalance(0.0);
			uc.setBalance(0.0);
			uc.setFreezebalance(0.0);
		}
		String phoneStr = "";
		logger.info("这个是对象"+ufs);
		logger.info("这个是电话号码"+ufs.getFsmobile());
		logger.info("这个是解密后的电话号码"+AES.getDecrypt(ufs.getFsmobile()));
		if(ufs.getFsmobile()!=null){
			String str =  AES.getDecrypt(ufs.getFsmobile());
			logger.info(str+"这个是电话号码***********");
			String str2 = str.substring(0, 3);
			String str3 = str.substring(str.length() - 3, str.length());
			phoneStr= str2 + "******" + str3;
		}
		modelAndView.addObject("bankname", bankname);
		modelAndView.addObject("cardNo", cardNo);
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("phone", AES.getDecrypt(ufs.getFsmobile()));
		modelAndView.addObject("phonestr", phoneStr);
		modelAndView.addObject("userBankCard", userBankCard);
		modelAndView.addObject("realname",  userRechargeService.nameEncryption(user.getRealname()));
		modelAndView.addObject("usercustid", AES.getDecrypt(ufs.getUsrcustid()));
		modelAndView.addObject("uc", uc);
		modelAndView.addObject("that", "63");
		modelAndView.addObject("sf", sf);
		modelAndView.addObject("df", df);
		modelAndView.setViewName("user/manager/recharge/step");
		return modelAndView;
	}
	
	

	/**我要充值页面会用到
	 * 充值记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeScreen", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeScreen(String moreCode,String sign) throws Exception {
		// 从session中拿到登录用户
		UserBaseAccountInfo user = getUserBaseAccountInfo();
		ModelAndView modelAndView = new ModelAndView();
		UserRecharge ur = new UserRecharge();
		String pageNum = request.getParameter("pageNum");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if (user != null) {
			Map map = new HashMap();
			initPage(map, pageNum, "5");

			List<UserRecharge> userRechargeList = null;
			if (moreCode.equals("search")) {// 表示是时间搜索
				ur.setUbai(user);
				if (starttime != null && starttime != "") {
					ur.setStart(StringUtil.stringforDateTwo(starttime));
				}
				if (endtime != null && endtime != "") {
					ur.setEnd(StringUtil.stringforDateTwo(endtime));
				}
				userRechargeList = userRechargeService.getAllCode(ur);
			} else {// 表示是标签搜索
				ur.setBaseid(user.getId());
				ur.setSign(moreCode);
				userRechargeList = userRechargeService.getLableSelect(ur);
			}

			PageInfo<Object> pagehelper = initPagehelper(map, userRechargeList);
			modelAndView.addObject("pagehelper", pagehelper);
			modelAndView.addObject("ur", ur);
			modelAndView.addObject("df", df);
			modelAndView.addObject("sf", sf);
			modelAndView.setViewName("user/manager/recharge/rechargeRecord");
		}
		return modelAndView;
	}
	
	/**充值页面的充值记录
	 * 充值记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeRechord", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeRechord(String that) throws Exception {
		UserBaseAccountInfo user = getUserBaseAccountInfo();
		// 获取用户开户信息
		UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		ModelAndView modelAndView = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		Map map = new HashMap();
		initPage(map, pageNum, "5");
		UserRecharge ur = new UserRecharge();
		ur.setUbai(user);
		List<UserRecharge> userRecharge = userRechargeService.getAll(ur);
		PageInfo<Object> pagehelper = initPagehelper(map, userRecharge);

		/** 以下这段代码是获取快捷银行卡的 */
		UserAccount uc = userAccountService.getUserAccountByBaseId(user.getId());// 根据用户id查询用户账户对象
		// 获取快捷银行卡号
		UserBankCard userBankCard = userBankCardService.selectBoundCardByBaseId(user.getId());
		String bankname = "";
		String cardNo = "";
		if (userBankCard != null) {
			bankname = userBankCard.getBankname();
			cardNo =  AES.getDecrypt(userBankCard.getCardno()).replaceAll("(\\d{4})\\d*(\\d{4})", "$1***********$2");// 给卡号加密
		}
		if (uc == null) {
			uc = new UserAccount();
			uc.setAvlbalance(0.0);
			uc.setBalance(0.0);
			uc.setFreezebalance(0.0);
		}
		String str =  AES.getDecrypt(ufs.getFsmobile());
		String str2 = str.substring(0, 3);
		String str3 = str.substring(str.length() - 3, str.length());
		String phoneStr = str2 + "******" + str3;
		modelAndView.addObject("bankname", bankname);
		modelAndView.addObject("cardNo", cardNo);
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("phone",  AES.getDecrypt(ufs.getFsmobile()));
		modelAndView.addObject("phonestr", phoneStr);
		modelAndView.addObject("userBankCard", userBankCard);
		modelAndView.addObject("realname",  user.getRealname());
		modelAndView.addObject("usercustid",  AES.getDecrypt(ufs.getUsrcustid()));
		modelAndView.addObject("uc", uc);
		modelAndView.addObject("that", that);
		modelAndView.addObject("sf", sf);
		modelAndView.addObject("df", df);
		modelAndView.setViewName("user/manager/recharge/step");
		return modelAndView;
	}
	/**
	 * 资金记录中的充值记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeRecord(UserRecharge uRecharge) throws Exception {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		// 当前页面，每页条数
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startdate = (String) request.getParameter("startdate");
		String enddate = (String) request.getParameter("enddate");
		
		uRecharge.setBaseid(ubai.getId());
		if (startdate != null && startdate != "") {
			uRecharge.setStart(StringUtil.stringforDateTwo(startdate));
		}
		if (enddate != null && enddate != "") {
			uRecharge.setEnd(StringUtil.stringforDateTwo(enddate));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserRecharge> userRechargeList = userRechargeService.getLableSelect(uRecharge);
		PageInfo<Object> pagehelper = initPagehelper(map, userRechargeList);
		// 可用余额
		String avlBalance = df.format(userAccount.getAvlbalance());
		String avlBalanceA = avlBalance.substring(0, avlBalance.length() - 2);
		String avlBalanceB = avlBalance.substring(avlBalance.length() - 2);
		// 冻结余额
		String freezeBalance = df.format(userAccount.getFreezebalance());
		String freezeBalanceA = freezeBalance.substring(0, freezeBalance.length() - 2);
		String freezeBalanceB = freezeBalance.substring(freezeBalance.length() - 2);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("avlBalanceA", avlBalanceA);
		mav.addObject("avlBalanceB", avlBalanceB);
		mav.addObject("freezeBalanceA", freezeBalanceA);
		mav.addObject("freezeBalanceB", freezeBalanceB);
		mav.addObject("sf", sf);
		mav.addObject("df", df);
		mav.addObject("startdate", startdate);
		mav.addObject("enddate", enddate); 
		mav.addObject("sign", uRecharge.getSign());
		mav.addObject("status", uRecharge.getStatus());
		mav.setViewName("user/manager/recharge/carryrecord");
		return mav;
	}
	@RequestMapping(value = "/result", method = { RequestMethod.POST, RequestMethod.GET })
	public void result(String money,String sum) throws Exception{
		UserBaseAccountInfo user = getUserBaseAccountInfo();
		String result = userRechargeService.dmoney2(sum,money,user);
		sendJsonData(response, JSON.toJSONString(result));
	}
	
	/**
	 * 这个是点击登录网上银行充值的时候跳转到的方法
	 * 确认充值(请求参数列表)
	 * @param request
	 * @param money  充值金额
	 * @param orderno 充值订单号
	 * @param sum 充值方式1 为网银 ,2 为快捷 3,为支付宝 4 为银行卡转账
	 * @param name 充值的银行代码eg:ICBC
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/RechargeConfirmation", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargeConfirmation(String money,String sum) throws Exception {
		UserBaseAccountInfo user = getUserBaseAccountInfo();
    	//获取托管账户信息
    	UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
    	//获取绑定卡的卡号对象
    	UserBankCard ucard = userBankCardService.selectBoundCardByBaseId(user.getId());
    	String orderno = StringUtil.getNo();
    	//获取绝对路径
    	String BgRetUrl = "http://my.ganjiangps.com/huishang/userRecharge/UserRechargeCall.action";
    	String rgRetUrl = "http://my.ganjiangps.com/huishang/userRecharge/rechargeList.action";
    	LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
    	
    	Date date = new Date();
    	String TRANSDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
    	String TRANSTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
    	System.out.println(df1.format(Double.valueOf(money)));
    	//请求参数
    	logger.info("这个是网银充值请求参数"+user+"*********************");
    	logger.info("这个是网银充值请求参数"+ufs+"===================");
    	logger.info("这个是银行卡请求参数"+ucard+"++++++++++++++++++++++");
    	reqMap.put("ACCT_NO", AES.getDecrypt(ufs.getUsrcustid())); //电子账号 也就是用户客户号 "9930040050240500013"
    	reqMap.put("BANKCODE",HSignUtil.BANKCODE);//银行代码
    	reqMap.put("CARD_BIND",  AES.getDecrypt(ucard.getCardno()));//绑定卡卡号 需要从UserBankCard 根据baseid查询 ucard.getCardNo()
    	reqMap.put("TRANSAMT", df1.format(Double.valueOf(money))); //充值金额从页面获取
    	reqMap.put("IDNO", user.getCertificationnumber()); //身份证号 从userBaseAccountInfo 用户基本信息表中获取certificationNumber
    	reqMap.put("SURNAME",  user.getRealname());  //用户真实姓名也从用户基本信息表中获取
    	reqMap.put("MOBILE",  AES.getDecrypt(ufs.getFsmobile())); //电话号码从用户托管账户表中获取fsmobile ufs.getFsmobile()
    	reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);//合作单位编号
    	reqMap.put("COINSTCHANNEL", HSignUtil.COINSTCHANNEL_WEB);//合作单位渠道
    	reqMap.put("TRANSDATE", TRANSDATE); //交易日期
    	reqMap.put("TRANSTIME", TRANSTIME); //交易時間 
    	reqMap.put("MERBGRETURL", BgRetUrl); //後台接收地址
    	reqMap.put("MERPAGERETURL", rgRetUrl); //前台接收地址
    	reqMap.put("PRIV1", orderno); //商户私有域
    	
    	String sign = HSignUtil.getRASSign(reqMap);//加签名
    	reqMap.put("SIGN", sign);
    	String encryptionKey4Server = RSAUtils.getVerifyKey4Client(HSignUtil.ENCRYPTPATH);	//获取加密公钥字符串
    	String encryptData=RSAUtils.encryptRSAs(JSONObject.fromObject(reqMap).toString(), encryptionKey4Server);
    	
    	Map<String,String> map = new HashMap<>();
    	map.put("COINSTCODE",HSignUtil.COINSTCODE);//合作單位編號
    	map.put("encryptData",encryptData);//请求参数的加密密文
    	
    	map.put("BANKCODE", HSignUtil.BANKCODE);//银行编号
    	map.put("APIVERSION","");//为报文版本号
    	map.put("TRXCODE","");//交易码
    	map.put("TRXDATE",TRANSDATE);//交易日期
    	map.put("TRXTIME",TRANSTIME);//交易时间
    	map.put("SEQNO",orderno);//交易流水
    	request.setAttribute("map", map);
    	request.setAttribute("url", HSignUtil.RECHARGE);
    	userRechargeService.saveUserRecharge(sum, money, orderno, user, ufs, reqMap);
    	request.getRequestDispatcher("/WEB-INF/pages/recharge/recharge.jsp").forward(request, response);
	}


	/**
	 * 发送短信验证码
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/phone", method = { RequestMethod.POST, RequestMethod.GET })
	public void phone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//得到登录用户
		UserBaseAccountInfo user = getUserBaseAccountInfo();
		//获取托管账户信息
		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		Map<String,String> map =  userRechargeService.getmessage(user, ufs);
		request.getSession().setAttribute("sms_seq", "");
		request.getSession().setAttribute("sms_seq", map.get("sms_seq"));
	    sendJsonData(response, map.get("sms_seq"));
	}
	/**
	 * 快捷充值
	 * 确认充值(请求参数列表)
	 * @param request
	 * @param money  充值金额
	 * @param orderno 充值订单号
	 * @param sum 充值方式1 为网银 ,2 为快捷 3,为支付宝 4 为银行卡转账
	 * @param name 充值的银行代码eg:ICBC
	 * @param response phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/RechargeConfirmation2", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargeConfirmation2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserBaseAccountInfo user = getUserBaseAccountInfo();
		//获取托管账户信息
		String sms_seq = (String) request.getSession().getAttribute("sms_seq");
		UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		String data = userRechargeService.shortcut(user, ufs, request,sms_seq);
		sendJsonData(response, JSON.toJSONString(data));
	}
	/**
	 *充值失败转化页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView fail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/manager/recharge/rechargeFail");
		return mav;
	}
	/**
	 * 查看限额
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/banklist", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView banklist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/manager/recharge/banklist");
		return mav;
	}
	
	
	
	/**
	 *算出充值后余额为多少的方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addmoney", method = { RequestMethod.POST, RequestMethod.GET })
	public void addmoney(String money) throws Exception {
		//获取当前用户的baseid
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(user.getId());
		
		double balance = 0;  //总资产
		
		if(userAccount!=null){
			
			balance = userAccount.getBalance();
		}
		balance = Arith.add(balance, Double.valueOf(money));
		String data = String.valueOf(df.format(balance));
		sendJsonData(response, data);
	}
	/**
	 * 充值记录简易版,账户中心的充值记录显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeDatails", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargeDatails(String type) throws Exception {
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		UserRecharge ur = new UserRecharge();
		if (user != null) {
			ur.setUbai(user);
		}
		List<UserRecharge> userRecharge = userRechargeService.getAll(ur);
		String data=JSON.toJSONString(userRecharge);
	    sendJsonData(response, data);
	}

}
