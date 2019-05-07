package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.moneymoremore.model.MoneyMoreMoreRecharge;
import com.ptpl.model.AccInExRecord;
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
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;
/**用户后台充值*/
@Controller
@RequestMapping("/moneymoremore/userRecharge")
public class MMMUserBackstageRechargeController extends BaseController{
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
	/**用户债转service,主要是用于定向名单的确定*/
	@Autowired
	UserDebtAttornServiceI userDebtAttornService;
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	UserAuthorizationServiceI userAuthorizationService;
	/**
	 * 充值列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unused")
	@RequestMapping(value = "/rechargeList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取登录的用户UserAccountSafeInfo
		//userBaseAccountInfoService.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(609));
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		
		//获取用户授权记录表
		UserAuthorization  userAuthorization = userAuthorizationService.findUserAuthorizationByBaseId(user.getId());
		ModelAndView modelAndView = new ModelAndView();
		// 假如用户是没有开户的就跳转到开户页面
		UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		
	
		// 判断用户有无登录,没有登录的情况下跳转到登录页面去
		if (user != null) {
		 if(userAuthorization!=null){
			if(userAuthorization.getAuthorizestatus().endsWith("1")){//如果是以1结尾,说明开通了授权
				//用户类型 1.个人 0.企业
				short accountType = 0;
				//充值记录对象
				UserRecharge ur = new UserRecharge();
					if (ufs == null) {
						modelAndView.setViewName("redirect:/user/securitycenter/list.action");
						return modelAndView;
					}
					ur.setUbai(user);
					accountType = user.getAccounttype(); // 判断用户类型 1,个人/0,企业
					String pageNum = request.getParameter("pageNum");
					Map map = new HashMap(); initPage(map, pageNum, "20");
					//获取当前对象的充值记录
					List<UserRecharge> userRechargeList = userRechargeService.getAll(ur);

					/**1.根据对象的充值记录对象获取到充值方式+会员等级+启用,来查询充值设置表对象
					 * 2.获取到充值设置表对象后,查询此对象的手续费收取标准,是0:不收取 还是1:充值人自付 还是2:平台付*/
					for (UserRecharge userRecharge : userRechargeList) {
						  double theAmountCredited = 0;//实际到账
						  if(userRecharge.getFeeobjflag().equals("U")){//自付
							  theAmountCredited = Arith.sub(userRecharge.getAmount(), userRecharge.getRecharfee());
						  }else{//他付或者不付
							  theAmountCredited = userRecharge.getAmount();
						  }
						  userRecharge.setTheamountcredited(theAmountCredited);
						  System.out.println(userRecharge.getUuid());
						  UserRecharge zUserRecharge = userRechargeService.select(userRecharge.getUuid());
						  if(zUserRecharge!=null){
							  if(userRecharge.getStatus()==1){//充值成功
								  zUserRecharge.setUrid("1");
								  userRechargeService.update(zUserRecharge);
							  }
						  }
					}
					
					PageInfo<Object> pagehelper = initPagehelper(map, userRechargeList);

					/** 获取用户账户余额 */
					UserAccount uc = userAccountService.getUserAccountByBaseId(user.getId());

					if (uc == null) {// 假如没有这个账户,
						uc = new UserAccount();
						uc.setAvlbalance(0.0);
						uc.setBalance(0.0);
						uc.setFreezebalance(0.0);
					}
					modelAndView.addObject("pagehelper", pagehelper);
					modelAndView.addObject("accountType", accountType);
					modelAndView.addObject("uc", uc);
					modelAndView.addObject("df", df);
					modelAndView.setViewName("user/recharge/rechargeList4");//列表页面
					return modelAndView;
				 }
					modelAndView.setViewName("user/recharge/auth");
					return modelAndView;
				}
			modelAndView.setViewName("user/recharge/auth");
			return modelAndView;
				
		 }
			modelAndView.setViewName("redirect:/user/tologin.action");
			return modelAndView;
	}
	/**
	 * 充值授权
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */

	@RequestMapping(value = "/auth", method = { RequestMethod.POST, RequestMethod.GET })
	public void auth(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取登录的用户
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		// 假如用户是没有开户的就跳转到开户页面
		UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		//
		MMMAuthorizationController.doRepayMentAuthorizationController(request, response, ufs.getMoneymoremoreid(), "1,2,3", "", user.getId().toString(), "", "");
	}
	/**
	 * 充值记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rechargeRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 从session中拿到登录用户
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//UserBaseAccountInfo user = userBaseAccountInfoService.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(609));
		UserRecharge ur = new UserRecharge();
		String status = request.getParameter("status");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String pageNum = request.getParameter("pageNum");
		if (user != null) {
			ur.setUbai(user);
			if (status != null && status != "") {
				ur.setStatus(new Short(status));
			}
			if (starttime != null && starttime != "") {
				ur.setStarttime(StringUtil.stringforDate(starttime));
			}
			if (endtime != null && endtime != "") {
				ur.setEndtime(StringUtil.stringforDate(endtime));
			}
		}
		
		Map map = new HashMap();
		initPage(map, pageNum, "20");
		List<UserRecharge> userRechargeList = userRechargeService.getAll(ur);
		for (UserRecharge userRecharge : userRechargeList) {
			  double theAmountCredited = 0;//实际到账
			  if(userRecharge.getFeeobjflag().equals("U")){
				  theAmountCredited = Arith.sub(userRecharge.getAmount(), userRecharge.getRecharfee());
			  }else{
				  theAmountCredited = userRecharge.getAmount();
			  }
			  userRecharge.setTheamountcredited(theAmountCredited);
			  System.out.println(userRecharge.getUuid());
			  UserRecharge zUserRecharge = userRechargeService.select(userRecharge.getUuid());
			  if(zUserRecharge!=null){
				  if(userRecharge.getStatus()==1){//充值成功
					  zUserRecharge.setUrid("1");
					  userRechargeService.update(zUserRecharge);
				  }
			  }
		}
		PageInfo<Object> pagehelper = initPagehelper(map, userRechargeList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		ur.setStarttimeStr(starttime);
		ur.setEndtimeStr(endtime);
		modelAndView.addObject("ur", ur);
		modelAndView.addObject("df", df);
		modelAndView.setViewName("user/recharge/rechargeRecord4");
		return modelAndView;
	}
	
	/**
	 * 
	* @Title: remitRecharge 
	* @Description: TODO(汇款充值) 
	* @param @param request
	* @param @param response
	* @param @param userRecharge
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/remitRecharge", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView remitRecharge(HttpServletRequest  request
			,HttpServletResponse response) throws Exception {
		/*String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
	*/
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//PageInfo<Object> pagehelper = init(map, userRechargeList);
		ModelAndView modelAndView  = new ModelAndView();
		
		//modelAndView.addObject("pagehelper", pagehelper);
		//modelAndView.addObject("userRecharge", userRecharge);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user/recharge/remitRecharge");
		return modelAndView;
	}
	/**
	 * 
	* @Title: saveremitRecharge 
	* @Description: TODO(汇款充值保存) 
	* @param @param request
	* @param @param response
	* @param @param userRecharge
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/saveremitRecharge", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView saveremitRecharge(HttpServletRequest  request
			,HttpServletResponse response,UserRecharge userRecharge) throws Exception {
		HuifuParams huifuParams = new HuifuParams();
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		userRecharge.setRecharfee(0.0);//充值手续费
		userRecharge.setStarttime(new Date());//充值开始时间
		if(user!=null){
			userRecharge.setApplyman(user.getLoginname());//充值人
			userRecharge.setOriginclient((short)1);//充值设备来源:1位pc端
			//userRecharge.setOriginclient(user.getOriginclient());//充值设备来源:1位pc端
		}
		userRecharge.setMercustid(huifuParams.getMerCustId());//商户客户号
		userRecharge.setFeeobjflag("无");//手续费类型
		userRecharge.setRechargetype((short)4);//4表示汇款充值
		userRecharge.setIsblending((short)0);//是否系统勾兑
		userRecharge.setIsmanblending((short)0);//是否人工勾兑
		userRecharge.setIsexceptions((short)0);//是否异常
		userRecharge.setStatus((short)4);//充值状态 4位待充值
		ModelAndView modelAndView  = new ModelAndView();
		userRechargeService.add(userRecharge);
		modelAndView.setViewName("redirect:/user/userRecharge/rechargeList.action");
		return modelAndView;
	}



	/**再次充值*/
	@RequestMapping(value = "/RechargeAgain", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargeAgain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		BigDecimal uid = new BigDecimal(id);
		//根据id查询需要再次充值的当前信息
		UserRecharge userRecharge = userRechargeService.queryKey(uid);
		//需要充值的那条数据的充值订单号
		String soOrderNo = userRecharge.getRechargeno();
		
		//得到登录用户
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//获取托管账户信息
		UserFsAccountInfo  userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
	
		
		if(userRecharge!=null){
			//充值类型
			String RechargeType = "";
			if(userRecharge.getRechargetype()!=0){//表示网银
				RechargeType = userRecharge.getRechargetype().toString();
			}
			//手续费类型 :从充值记录表中FeeObjFlag字段判断 I 为不付,就是"", U为自付 就是1, M就是他付 就是2
			String  FeeType="";
			if(userRecharge.getFeeobjflag().equals("U")){
				FeeType = "1";
			}else if(userRecharge.getFeeobjflag().equals("M")){
				FeeType = "2";
			}
			String Amount =  df1.format(Double.valueOf(userRecharge.getAmount()));
			String  RechargeMoneymoremore  = "";
			String PlatformMoneymoremore = "";
			if(userFsAccount!=null){
				RechargeMoneymoremore  = userFsAccount.getMoneymoremoreid();//"m111972";// 充值测试用户m111817,充值人乾多多标识 ,id 为599
				PlatformMoneymoremore = userFsAccount.getMercustid();//"p2089";//平台乾多多标识
			}
			
			System.out.println(RechargeMoneymoremore+"充值人乾多多标识");
			System.out.println(PlatformMoneymoremore+"平台乾多多标识");
			String OrderNo = StringUtil.getOrderNo2(user.getId().longValue());//平台充值订单号
			
			String CardNo = "";//银行卡号
			String RandomTimeStamp ="";//随机时间戳
			String Remark1 =String.valueOf(user.getId());//自定义备注:存入user.getId(),用户baseid
			String Remark2 = "";
			String Remark3 = "";
			String ReturnURL =  StringUtil.getBasePath(request)+"/moneymoremore/userRecharge/RechargeReturnURL.action";//页面返回地址
			String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/userRecharge/TTRechargeCallBack.action";//后台通知地址
			
			StringBuffer buffer = new StringBuffer();
			buffer.append(RechargeMoneymoremore).append(StringUtils.trimToEmpty(PlatformMoneymoremore))
			.append(StringUtils.trimToEmpty(OrderNo)).append(StringUtils.trimToEmpty(Amount))
			.append(StringUtils.trimToEmpty(RechargeType)).append(StringUtils.trimToEmpty(FeeType))
			.append(StringUtils.trimToEmpty(CardNo)).append(StringUtils.trimToEmpty(RandomTimeStamp))
			.append(StringUtils.trimToEmpty(Remark1)).append(StringUtils.trimToEmpty(Remark2))
			.append(StringUtils.trimToEmpty(Remark3))
			.append(StringUtils.trimToEmpty(ReturnURL))
			.append(StringUtils.trimToEmpty(NotifyURL));
			String plainStr = buffer.toString();
			System.out.println(plainStr);
			//私钥签名
			String privateResult = "";
			
			RsaHelper rsa = RsaHelper.getInstance();
			privateResult = rsa.signData(plainStr, RsaHelper.privateString);
			System.out.println(privateResult);
			
			request.setAttribute("RechargeMoneymoremore", RechargeMoneymoremore);
			request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
			request.setAttribute("OrderNo", OrderNo);
			request.setAttribute("Amount", Amount);
			request.setAttribute("RechargeType", RechargeType);
			request.setAttribute("FeeType", FeeType);
			request.setAttribute("CardNo", CardNo);
			request.setAttribute("RandomTimeStamp",RandomTimeStamp);
			request.setAttribute("Remark1", Remark1);
			request.setAttribute("Remark2", Remark2);
			request.setAttribute("Remark3", Remark3);
			request.setAttribute("ReturnURL", ReturnURL);
			request.setAttribute("NotifyURL", NotifyURL);
			request.setAttribute("SignInfo", privateResult);
			//保存充值订单号到mmmRecharge对象中
			MoneyMoreMoreRecharge mmmRecharge = new MoneyMoreMoreRecharge();
			mmmRecharge.setOrderNo(OrderNo);
			
			
			System.out.println(OrderNo+"新生成的订单号********************************");
			userRecharge.setRechargeno(OrderNo);//充值订单号
			userRecharge.setStarttime(new Date());//充值时间
			userRecharge.setUrid("0");//再次充值的标识
			userRecharge.setUuid(soOrderNo);//保存要充值的那条充值记录,到时候列表展示的根据这个查询原来的那条需要再次充值的记录
			userRechargeService.add(userRecharge);
			try {
				request.getRequestDispatcher("/WEB-INF/MMMPages/UserRecharge/recharge.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 点击确认按钮的时候跳转的页面
	 * 验证该用户在该等级下+充值方式下+充值限制启用状态的充值限制
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/dmoney", method = { RequestMethod.POST, RequestMethod.GET })
	public void dmoney(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取登录的用户
		//userBaseAccountInfoService.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(609));
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		// 创建一个充值设置表对象
		RechargeRstr rrObject = new RechargeRstr();
		if (user != null) {
			/** 验证充值限额的 */
			/*
			 * 空.网银充值 1.代扣充值(暂不可用) 2.快捷支付 3.汇款充值 4.企业网银     页面传回数字分别是 0 1 2 3 4
			 */
			String select = request.getParameter("select");
			// 根据baseid查询到用户的会员等级
			UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(user.getId());
			String indexStr = String.valueOf(userAccountSafeInfo.getUgrade());
			//获取托管账户信息对象:从中获取充值人乾多多标识和平台乾多多标识,用于判断用户有无开户
			UserFsAccountInfo  userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());

			/**
			 * 步骤:1.先查询当前充值用户的会员等级是什么会员等级,然后加上充值方式,再加上是否启用来查询到充值设置表的数据,如果没有查到数据则返回0 
			 * 2.匹配到之后拿到定向名单编号
			 * 3.根据定向名单编号查询,如果在定向名单中,说明是当前会员等级是可以用的,调用债转UserDebtAttornServiceI 的
			 * boolean ugradeFalsePublic(BigDecimal snlid,BigDecimal baseid);
			 * 4.如果在定向名单中!说明是可以用的
			 * 
			 */
			//判断用户有没有开通托管账户,如果开通了才会有下面一系列的操作
			String data = "";
			if(userFsAccount!=null){
				if(userFsAccount.getIsopenfsinfo()==1){//表示已经开通了 托管账户
					/** 充值记录对象*/
					UserRecharge userR = new UserRecharge();
					System.out.println(indexStr);
					if (indexStr != null && !"".equals(indexStr)) {
						int length = Integer.parseInt(indexStr);
						String index = "";
						for (int i = 0; i < length; i++) {
							index = index + "_";
						}
						String ugrade = index + "1%";
						// 查找想要的充值设置记录
						/** 充值设置对象 */
						RechargeRstr rr = new RechargeRstr();
						rr.setIsuse((short) 1);// 是否启用
						rr.setUgrade(ugrade); // 会员等级
						if (select.equals("0")) {// 网银
							rr.setRechartype((short) 0);// 充值方式:网银,往充值设置对象里面塞值
							userR.setRechargetype((short) 0);// 往充值记录里面塞值
						} else if (select.equals("1")) {// 代扣充值.暂时不可用
							rr.setRechartype((short) 1);
							userR.setRechargetype((short) 1);
						} else if (select.equals("2")) {// 快捷充值
							rr.setRechartype((short) 2);
							userR.setRechargetype((short) 2);
						} else if (select.equals("3")) {// 汇款充值
							rr.setRechartype((short) 3);
							userR.setRechargetype((short) 3);
						} else if (select.equals("4")) {// 企业网银
							rr.setRechartype((short) 4);
							userR.setRechargetype((short) 4);
						}
						
						// 获取充值设置表记录对象
						rrObject = rechargeRstrService.getUgradeAndRecharTypeAndIsuseForRechargeRstr(rr);
						
						// 查询当天充值成功的充值记录
						userR.setBaseid(user.getId());
						List<UserRecharge> UR = userRechargeService.selectAmountList(userR);
						Double totalAmount = 0.0;
						for (UserRecharge userRecharge : UR) {
							totalAmount += userRecharge.getAmount();
						}
						
						if (rrObject != null) {
							//将查询到的充值设置对象保存进对象
							request.getSession().setAttribute("rrObject", rrObject);
							// 判断当前用户是不是在定向名单允许的范围内
							boolean flag = true;//userDebtAttornService.ugradeFalsePublic(rrObject.getSnlid(), user.getId());
							// true 成功就是 0 ,false 就是 1
							int frm = 0;
							if (!flag) {
								frm = 1;
							}
							rrObject.setFlagremovename(frm);/** 验证充值人是否在定向名单中,如果是1说明不在(表示不行的),如果是0表示可以对该用户进行操作 */
							rrObject.setTotalamoount(totalAmount);
							data = JSON.toJSONString(rrObject);
						} else {
							data = "0";//表示充值设置表中没有当前会员等级的设置,又或许没有当前充值方式的设置
						}
						sendJsonData(response, data);
					}
				}else{
					data = "isopenfsinfofalse";//表示没有开通托管账户
					sendJsonData(response, data);
				}
			}else{
				data = "ufsfalse";//表示本地数据库根本没有查询到托管账户信息
				sendJsonData(response, data);
			}
		}

	}
	/**
	 * 确认充值(请求参数列表)
	 * http://113.99.87.125:8883/ptpjx/testmoneymoremore/userRecharge/RechargeConfirmation.action
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 需要从页面获取的参数有 充值方式(充值类型),充值金额
	 */
	@RequestMapping(value = "/RechargeConfirmation", method = { RequestMethod.POST, RequestMethod.GET })
	public synchronized void rechargeConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//保存充值记录
			UserRecharge userRecharge = new UserRecharge();
			//获取页面的充值方式    空.网银充值1.代扣充值(暂不可用)2.快捷支付3.汇款充值4.企业网银
			String RechargeType = request.getParameter("select");
			userRecharge.setRechargetype(new Short(RechargeType));
			if(RechargeType.equals("0")){//假如页面选择的是0,说明是网银,双乾那边是""
				RechargeType ="";
			}
			//获取充值金额
			String  Amount = df1.format(Double.valueOf(request.getParameter("transAmt")));
			System.out.println(Amount+"充值金额");
			
			/* 从session中拿到登录用户:临时用查询的方法得到用户 */
			//userBaseAccountInfoService.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(609));
			UserBaseAccountInfo user =(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			System.out.println(user.getId()+"*****************************");
			//获取托管账户信息对象:从中获取充值人乾多多标识和平台乾多多标识
			UserFsAccountInfo  userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
			
			// 从session中取到充值设置对象
			RechargeRstr rrObject = (RechargeRstr) request.getSession().getAttribute("rrObject");
			//手续费类型 :从充值设置表中取
			String  FeeType="";
			if(rrObject.getFeetype().intValue()!=0){//假如手续费类型为0,说明不收费,双乾那边是""
				FeeType = String.valueOf(rrObject.getFeetype());
			}
			if(FeeType.endsWith("")){
				userRecharge.setFeeobjflag("I");
			}else if(FeeType.endsWith("1")){
				userRecharge.setFeeobjflag("U");
			}else{
				userRecharge.setFeeobjflag("M");
			}
		    String  RechargeMoneymoremore  = userFsAccount.getMoneymoremoreid();//"m111972";// 充值测试用户m111817,充值人乾多多标识 ,id 为599
		    String PlatformMoneymoremore = userFsAccount.getMercustid();//"p2089";//平台乾多多标识
		    
		    System.out.println(RechargeMoneymoremore+"充值人乾多多标识");
		    System.out.println(PlatformMoneymoremore+"平台乾多多标识");
		    String OrderNo = StringUtil.getOrderNo2(user.getId().longValue());//平台充值订单号
		    
		    String CardNo = "";//银行卡号
		    String RandomTimeStamp ="";//随机时间戳
		    String Remark1 =String.valueOf(user.getId());//自定义备注:存入user.getId(),用户baseid
		    String Remark2 = "";
			String Remark3 = "";
		    String ReturnURL =  StringUtil.getBasePath(request)+"/moneymoremore/userRecharge/RechargeReturnURL.action";//页面返回地址
		    String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/userRecharge/RechargeCallBack.action";//后台通知地址
		    
		    StringBuffer buffer = new StringBuffer();
			buffer.append(RechargeMoneymoremore).append(StringUtils.trimToEmpty(PlatformMoneymoremore))
					.append(StringUtils.trimToEmpty(OrderNo)).append(StringUtils.trimToEmpty(Amount))
					.append(StringUtils.trimToEmpty(RechargeType)).append(StringUtils.trimToEmpty(FeeType))
					.append(StringUtils.trimToEmpty(CardNo)).append(StringUtils.trimToEmpty(RandomTimeStamp))
					.append(StringUtils.trimToEmpty(Remark1)).append(StringUtils.trimToEmpty(Remark2))
					.append(StringUtils.trimToEmpty(Remark3))
					.append(StringUtils.trimToEmpty(ReturnURL))
					.append(StringUtils.trimToEmpty(NotifyURL));
	 		String plainStr = buffer.toString();
	 		System.out.println(plainStr);
	 		//私钥签名
	 		String privateResult = "";
	 		
	 		RsaHelper rsa = RsaHelper.getInstance();
	 		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
	 		System.out.println(privateResult);
	 		
	  		request.setAttribute("RechargeMoneymoremore", RechargeMoneymoremore);
			request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
			request.setAttribute("OrderNo", OrderNo);
			request.setAttribute("Amount", Amount);
			request.setAttribute("RechargeType", RechargeType);
			request.setAttribute("FeeType", FeeType);
			request.setAttribute("CardNo", CardNo);
			request.setAttribute("RandomTimeStamp",RandomTimeStamp);
			request.setAttribute("Remark1", Remark1);
			request.setAttribute("Remark2", Remark2);
			request.setAttribute("Remark3", Remark3);
			request.setAttribute("ReturnURL", ReturnURL);
			request.setAttribute("NotifyURL", NotifyURL);
			request.setAttribute("SignInfo", privateResult);
			//保存充值订单号到mmmRecharge对象中
			MoneyMoreMoreRecharge mmmRecharge = new MoneyMoreMoreRecharge();
			mmmRecharge.setOrderNo(OrderNo);
			
			
			userRecharge.setRechargeno(OrderNo);//充值订单号
			userRecharge.setBaseid(user.getId());//保存用户id
			userRecharge.setAmount(Double.valueOf(Amount));//充值金额
			userRecharge.setStarttime(new Date()); //充值开始时间
			userRecharge.setApplyman(user.getLoginname()); //充值人!也就是登陆的用户
			userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
			userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
			userRecharge.setPaycompany("双乾支付"); //充值通道公司
			userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); //表示充值状态取消
			userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
			userRecharge.setMercustid(PlatformMoneymoremore);//保存商户号
			userRecharge.setRecharfee(0.00);//充值手续费刚开始为0.00,因为还没有付款,汇付那边还没有返回,等有返回就替换
			//userRecharge.setUuid(StringUtil.getRechargeNo());
			userRecharge.setBankname("未知");//快捷充值的时候的卡号
			userRecharge.setUrid("0");
			userRechargeService.add(userRecharge);
			try {
				request.getRequestDispatcher("/WEB-INF/MMMPages/UserRecharge/recharge.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
	 			e.printStackTrace();
			}
			
	}
	
	@RequestMapping(value= "/RechargeCallBack")
	public synchronized void UserAccountRechargeCallBack(HttpServletRequest request,HttpServletResponse response,MoneyMoreMoreRecharge mmmRecharge){
		System.out.println(mmmRecharge);
		StringBuffer buffer = new StringBuffer();
		String SignInfo = request.getParameter("SignInfo");
//		RechargeMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + Fee + FeePlatform 
//		+ RechargeType + FeeType + CardNoList + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode
		buffer.append(StringUtils.trimToEmpty(mmmRecharge.getRechargeMoneymoremore()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getPlatformMoneymoremore()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getLoanNo()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getOrderNo()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getAmount()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getFee()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getFeePlatform()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getRechargeType()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getFeeType()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getCardNoList()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getRandomTimeStamp()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getRemark1()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getRemark2()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getRemark3()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getResultCode()));
 		String str = buffer.toString();
		 
 		RsaHelper rs = RsaHelper.getInstance();
		boolean falg = rs.verifySignature(SignInfo, str, RsaHelper.publicString);//验证签名
 		if(falg){//验证签名成功
 			if(mmmRecharge.getResultCode().equals("88")){
			request.setAttribute("RespDesc",mmmRecharge.getMessage());
			//根据订单号查询本地数据库的值
    		UserRecharge userRecharge = userRechargeService.select(mmmRecharge.getOrderNo());
			//request.getRequestDispatcher("/WEB-INF/MMMPages/UserRegister/success.jsp").forward(request, response);
			/**防止重复充值生成重复的收支记录*/
			UserRecharge userR = new UserRecharge();
			userR.setRechargeno(mmmRecharge.getOrderNo());
			userR.setBankreturnno(mmmRecharge.getLoanNo());
		    UserRecharge userRechargeGet = userRechargeService.getBankReturnNo(userR);
		    if(userRechargeGet==null){
				if(!mmmRecharge.getRemark1().equals("") && mmmRecharge.getRemark1()!=null){
					//金额校验,如果返回的金额和本地数据库不同,那么就充值失败,不加入收支记录,也不加入手续费收支记录,也不加入充值记录
					if(df1.format(Double.valueOf(mmmRecharge.getAmount())).equals(df1.format(Double.valueOf(userRecharge.getAmount())))){
						// 根据id获取用户基本信息
						UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(mmmRecharge.getRemark1())); 
						//用户账户信息安全表
						UserAccountSafeInfo usf = userAccountSafeInfoService.selectByBaseId(new BigDecimal(mmmRecharge.getRemark1()));
						/**保存收支记录,只进不出*/
						//1.查询当前用户的用户账户记录,查询总金额+余额+冻结金额
						UserAccount userAccount = userAccountService.getUserAccountByBaseId(new BigDecimal(mmmRecharge.getRemark1()));
						//冻结余额
						double freezebalance = 0;
						//可用余额
						double avlbalance = 0;
						//可用余额
						double avlbalanceK = 0;
						//总资产
						double balanceZ = 0; 
						if(userAccount!=null){
							//冻结金额
							freezebalance = userAccount.getFreezebalance();
							//可用余额
							avlbalance = userAccount.getAvlbalance();
						}
						//获取当笔充值金额
						double amount = Double.valueOf(mmmRecharge.getAmount());
						//算出可用余额和金额
						avlbalanceK = Arith.add(avlbalance,amount);//可用余额
						balanceZ = Arith.add(avlbalanceK,freezebalance);//总金额=可用余额+冻结金额
						//存入收支记录
						AccInExRecord accInExRecord = new AccInExRecord();//存收支记录的对象
						Date date = new Date();
						accInExRecord.setOutamount(0.0);//用户支出
						accInExRecord.setStatus((short)1);//业务状态成功
						accInExRecord.setTotalbalance(balanceZ);//用户总金额
						accInExRecord.setFreebalance(freezebalance);//冻结金额
						accInExRecord.setBaseid(new BigDecimal(mmmRecharge.getRemark1()));
						accInExRecord.setType((short)1);//type 为1 表示业务类型为充值
						accInExRecord.setInamount(amount);//用户收入
						accInExRecord.setBalance(avlbalanceK);//可用余额
						accInExRecord.setRecordtime(date);//时间
						accInExRecord.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
						accInExRecord.setDescription("充值说明");
						accInExRecord.setRemark("备注");
						System.out.println(System.currentTimeMillis()+"插入时间-------------------------");
						accInExRecordService.insertSelective(accInExRecord);
						/**保存收支记录,只出不进,手续费*/
						//判断是平台处手续费还是用户自己出手续费,根据字段Fee和FeePlatform来判断
						//直接判断FeeType就可以了1.充值成功时从充值人账户全额扣除 2.充值成功时从平台自有账户全额扣除 
						AccInExRecord accInExRecord2 = new AccInExRecord();//存手续费记录的对象
						if(mmmRecharge.getFeeType().equals("") || mmmRecharge.getFeeType()==null){//表示不用扣任何手续费
							accInExRecord2.setPinamount(0.0);//表示金额为平台收入
							accInExRecord2.setPoutamount(0.0);//表示金额为平台支出
							accInExRecord2.setTotalbalance(balanceZ);//用户总金额
							accInExRecord2.setBalance(avlbalanceK);//可用余额
							accInExRecord2.setOutamount(0.0);//用户支出
							accInExRecord2.setInamount(0.0);//用户收入
						}
						if(mmmRecharge.getFeeType().equals("1")){//表示从充值人账户全额扣除
							accInExRecord2.setPinamount(0.0);//平台收入
							accInExRecord2.setPoutamount(0.0);//平台支出
							//算总金额和可用余额
							avlbalanceK = Arith.sub(avlbalanceK, Double.valueOf(mmmRecharge.getFee()));
							balanceZ = Arith.add(avlbalanceK, freezebalance);
							accInExRecord2.setTotalbalance(balanceZ);//用户总金额
							accInExRecord2.setBalance(avlbalanceK);//可用余额
							accInExRecord2.setOutamount(Double.valueOf(mmmRecharge.getFee()));//用户支出
							accInExRecord2.setInamount(0.0);//用户收入
						}
						if(mmmRecharge.getFeeType().equals("2")){//表示从平台账户全额扣除
							accInExRecord2.setPinamount(0.0);//平台收入
							accInExRecord2.setPoutamount(Double.valueOf(mmmRecharge.getFeePlatform()));//平台支出
							//算总金额和可用余额
							avlbalanceK = Arith.sub(avlbalanceK, Double.valueOf(mmmRecharge.getFeePlatform()));
							balanceZ = Arith.add(avlbalanceK, freezebalance);
							accInExRecord2.setTotalbalance(balanceZ);//用户总金额
							accInExRecord2.setBalance(avlbalanceK);//可用余额
							accInExRecord2.setOutamount(0.0);//用户支出
							accInExRecord2.setInamount(0.0);//用户收入
						}
						accInExRecord2.setRecordtime(date);
						accInExRecord2.setType((short)2);//类型为:充值手续费
						accInExRecord2.setStatus((short)1);
						accInExRecord2.setFreebalance(freezebalance);//冻结余额
						accInExRecord2.setBaseid(new BigDecimal(mmmRecharge.getRemark1()));
						accInExRecord2.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
						accInExRecord2.setDescription("充值手续费说明");
						accInExRecord2.setRemark("充值手续费备注");
						accInExRecordService.insertSelective(accInExRecord2);
						/**修改用户账户金额*/
						if(userAccount==null){//假如数据库没有这条数据的话就直接插入
							userAccount = new UserAccount();
							userAccount.setBaseid(new BigDecimal(mmmRecharge.getRemark1()));
							userAccount.setBalance(balanceZ);
							userAccount.setAvlbalance(avlbalanceK);
							userAccount.setFreezebalance(freezebalance);
							userAccountService.insert(userAccount);
						}else{//假如数据库有这条数据就修改
							userAccount.setBaseid(new BigDecimal(mmmRecharge.getRemark1()));
							userAccount.setBalance(balanceZ);
							userAccount.setAvlbalance(avlbalanceK);
							userAccount.setFreezebalance(freezebalance);
							userAccountService.updateUseraccount(userAccount);
						}
						/**保存充值记录*/
						//1.首先判断充值类型
						
						if(mmmRecharge.getRechargeType()==null || mmmRecharge.getRechargeType().equals("")){//表示是网银充值
							userRecharge.setRechargetype((short)0);
						}else{
							if(mmmRecharge.getRechargeType().equals(1)){//代扣充值
								userRecharge.setRechargetype((short)1);
							}
							if(mmmRecharge.getRechargeType().equals(2)){//快捷支付
								userRecharge.setRechargetype((short)2);
							}
							if(mmmRecharge.getRechargeType().equals(3)){//汇款充值
								userRecharge.setRechargetype((short)3);
							}
							if(mmmRecharge.getRechargeType().equals(4)){//企业网银
								userRecharge.setRechargetype((short)4);
							}
							
						}
						//判断手续费是自付还是他付
						if(mmmRecharge.getFeeType().equals("") || mmmRecharge.getFeeType()==null){//不收费
							userRecharge.setRecharfee(0.0); //充值手续费
							userRecharge.setRecharrate(0.0); //充值费率
							userRecharge.setFeeobjflag("I"); //不付
						}
						if(mmmRecharge.getFeeType().equals("1")){//充值成功时从充值人账户全额扣除,自付
							userRecharge.setFeeobjflag("U"); //手续费自付或代付
							userRecharge.setRecharfee(Double.valueOf(mmmRecharge.getFee())); //充值手续费
							Double  recharRate = Arith.div(Double.valueOf(mmmRecharge.getFee()), Double.valueOf(mmmRecharge.getAmount()), 5);
							userRecharge.setRecharrate(recharRate); //充值费率
						}
						if(mmmRecharge.getFeeType().equals("2")){//充值成功时从平台自有账户全额扣除,他付
							userRecharge.setFeeobjflag("M"); //手续费自付或代付
							userRecharge.setRecharfee(Double.valueOf(mmmRecharge.getFeePlatform())); //充值手续费
							Double  recharRate = Arith.div(Double.valueOf(mmmRecharge.getFeePlatform()), Double.valueOf(mmmRecharge.getAmount()), 5);
							userRecharge.setRecharrate(recharRate); //充值费率
						}
						userRecharge.setEndtime(new Date());//充值结束时间
						userRecharge.setStatus(UserRecharge_Constant.STATUS_ONE);//充值状态
						userRecharge.setBankreturnno(mmmRecharge.getLoanNo()); //银行返回充值订单号
						userRecharge.setOriginclient(userBaseAccountInfo.getOriginclient());//充值来源 pc/手机
						if(mmmRecharge.getFeeType().equals("")){
							userRecharge.setFeeobjflag("I");//手续费类型
						}else if(mmmRecharge.getFeeType().equals("1")){
							userRecharge.setFeeobjflag("U");//手续费类型
						}else{
							userRecharge.setFeeobjflag("M");//手续费类型
						}
						
						userRecharge.setRemark("充值成功");
						userRecharge.setCardno("未知");//快捷充值的时候的卡号
						userRecharge.setBankname("未知");//快捷充值的时候的卡号
						userRecharge.setEndtime(new Date());//充值结束时间
						
						userRechargeService.update(userRecharge);
					}else{//说明金额比对失败,本地数据库金额和充值成功的金额不同,只有通过对账来修改数据
						request.getSession().setAttribute("message", "金额比对失败");
					}
				}
 		  }
 		}
	}

		/*System.out.println("返回码:"+ResultCode+"========================");
		System.out.println("返回码信息:"+Message+"========================");*/
		
	}
	@RequestMapping(value= "/RechargeReturnURL")
	public ModelAndView RechargeReturnURL(HttpServletRequest request,HttpServletResponse response,MoneyMoreMoreRecharge mmmRecharge){
		String message = (String) request.getSession().getAttribute("message");
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		mav.addObject("ordId", mmmRecharge.getOrderNo());
		mav.addObject("transAmt", mmmRecharge.getAmount()); 
		mav.addObject("feeAmt", mmmRecharge.getFee());
		mav.addObject("FeePlatform", mmmRecharge.getFeePlatform());
		mav.setViewName("/user/recharge/recallback");
		return mav;
	}
}
