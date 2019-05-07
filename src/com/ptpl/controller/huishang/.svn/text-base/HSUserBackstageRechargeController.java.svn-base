/*package com.ptpl.controller.huishang;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.moneymoremore.MMMAuthorizationController;
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
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.service.UserServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;
*//**用户后台充值*//*
@Controller
@RequestMapping("/HSuser/userRecharge")
public class HSUserBackstageRechargeController extends BaseController{
	*//** 充值记录*//*
	@Autowired
	UserRechargeServiceI userRechargeService;
	@Autowired
	UserServiceI userSrevice;
	*//** 用户基本信息  ServiceI *//*
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	*//** 托管账户信息*//*
	@Autowired
	UserFsAccountInfoServiceI  userFsAccountInfoService;
	*//**银行卡信息*//*
	@Autowired
	UserBankCardServiceI userBankCardService;
	*//**用户账户表*//*
	@Autowired
	UserAccountServiceI userAccountService;
	*//**用户收支明细记录*//*
	@Autowired 
	AccInExRecordServiceI accInExRecordService;
	*//**用户账户信息安全表*//*
	@Autowired
	UserAccountSafeInfoServiceI  userAccountSafeInfoService;
	*//**充值设置限制*//*
	@Autowired
	RechargeRstrServiceI rechargeRstrService;
	*//**排出人名单*//*
	@Autowired
	RemoveNameServiceI removeNameService;
	@Autowired
	UserAuthorizationServiceI userAuthorizationService;
	*//**
	 * 充值列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/rechargeList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// 获取登录的用户
		//UserBaseAccountInfo user = userBaseAccountInfoServiceI.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(599));
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			UserRecharge ur = new UserRecharge();
			//用户类型 1.个人 2.企业
			short accountType = 0;
			ModelAndView modelAndView = new ModelAndView();
			//获取用户授权记录表
			UserAuthorization  userAuthorization = userAuthorizationService.findUserAuthorizationByBaseId(user.getId());
			if (user != null) {
			 if(userAuthorization!=null){
					if(userAuthorization.getAuthorizestatus().endsWith("1")){//如果是以1结尾,说明开通了授权
				//假如用户是没有开户的就跳转到开户页面
				UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
				if(ufs==null || ufs.getIsopenfsinfo()==0){
					modelAndView.setViewName("redirect:/user/securitycenter/list.action");
					return modelAndView;
				}
				ur.setUbai(user);
				accountType = user.getAccounttype(); // 判断用户类型 1,个人/0,企业
				String pageNum = request.getParameter("pageNum");
				Map map = new HashMap();
				initPage(map, pageNum,"5");
				List<UserRecharge> userRecharge = userRechargeService.getAll(ur);
				for (UserRecharge userRecharge2 : userRecharge) {
					  double theAmountCredited = 0;//实际到账
					  if(userRecharge2.getFeeobjflag().equals("U")){
						  theAmountCredited = Arith.sub(userRecharge2.getAmount(), userRecharge2.getRecharfee());
					  }else{
						  theAmountCredited = userRecharge2.getAmount();
					  }
					  userRecharge2.setTheamountcredited(theAmountCredited);
				}
				PageInfo<Object> pagehelper = initPagehelper(map, userRecharge);
				
				*//**以下这段代码是获取快捷银行卡的*//*
				UserAccount uc = userAccountService.getUserAccountByBaseId(user.getId());//根据用户id查询用户账户对象
				//获取快捷银行卡号
				UserBankCard userBankCardNew = new UserBankCard(); 
				userBankCardNew.setBaseid(user.getId());
				userBankCardNew.setIsfastbindcard((short)2);
				UserBankCard userBankCard = userBankCardService.findIsFastBindCard(userBankCardNew);
				String bankname = "";
				String cardNo = "";
				if(userBankCard!=null){
					bankname = userBankCard.getBankname();
					cardNo = userBankCard.getCardno().replaceAll("(\\d{4})\\d*(\\d{4})", "$1***********$2");//给卡号加密
				}
				modelAndView.addObject("bankname", bankname);
				modelAndView.addObject("cardNo", cardNo);
				*//**以下这段代码是取消再次充值按钮的*//*
				UserRecharge urc = new UserRecharge();
				UserRecharge URC = new UserRecharge();	
				List<UserRecharge> uList = userRechargeService.getIsblendingAndIsmanblendingAndStatus();//查询已经勾兑并且状态为取消的数据
				for (UserRecharge uR : uList) {
					List<UserRecharge> uuidList = userRechargeService.getUuid(uR.getUuid()); //根据uuid查询数据表中的充值记录
					for (UserRecharge uuidObject : uuidList) {
						if(uuidObject.getStatus()==1){//如果有记录是成功的就把urid的值变为1
							urc.setUuid(uuidObject.getUuid());
							urc.setId(uR.getId());
							URC = userRechargeService.getUuidAndId(urc);
							URC.setUrid("1");
							userRechargeService.update(URC);
						}
					}
				}
			if(uc==null){
				uc = new UserAccount();
				uc.setAvlbalance(0.0);
				uc.setBalance(0.0);
				uc.setFreezebalance(0.0);
			}
			modelAndView.addObject("pagehelper", pagehelper);
			modelAndView.addObject("accountType", accountType);
			modelAndView.addObject("userBankCard", userBankCard);
			modelAndView.addObject("realname", user.getRealname());
			modelAndView.addObject("uc", uc);
			modelAndView.addObject("sf", sf);
			modelAndView.addObject("df", df);
			modelAndView.setViewName("user/manager/recharge/step");
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
	
	*//**
	 * 充值授权
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 *//*

	@RequestMapping(value = "/auth", method = { RequestMethod.POST, RequestMethod.GET })
	public void auth(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取登录的用户
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		// 假如用户是没有开户的就跳转到开户页面
		UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
		//
		MMMAuthorizationController.doRepayMentAuthorizationController(request, response, ufs.getMoneymoremoreid(), "1,2,3", "", user.getId().toString(), "", "");
	}
	
	*//**
	 * 资金记录中的充值记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/rechargeRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rechargeRecord(UserRecharge uRecharge) throws Exception {
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		//UserRecharge ur = new UserRecharge();
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
	*//**
	 * 充值记录简易版,账户中心的充值记录显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/rechargeDatails", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargeDatails(HttpServletRequest request, HttpServletResponse response,String type) throws Exception {
		System.out.println(type);
		//UserBaseAccountInfo user = userBaseAccountInfoServiceI.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(599));
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		UserRecharge ur = new UserRecharge();
		if (user != null) {
			ur.setUbai(user);
		}
		List<UserRecharge> userRecharge = userRechargeService.getAll(ur);
	
		String data=JSON.toJSONString(userRecharge);
	
	    sendJsonData(response, data);
		
	}


	*//**
	 * 点击确认按钮的时候跳转的页面
	 * 验证该用户在该等级下+充值方式下+充值限制启用状态的充值限制
	 * @param request
	 * @param response
	 * @param sum 充值类型 1
	 * @throws Exception
	 *//*
	@SuppressWarnings("unused")
	@RequestMapping(value = "/dmoney", method = { RequestMethod.POST, RequestMethod.GET })
	public void dmoney(HttpServletRequest request, HttpServletResponse response,String money,String sum) throws Exception {
			// 获取登录的用户
			UserBaseAccountInfo user = getUserBaseAccountInfo();
			RechargeRstr rrObject = new RechargeRstr();
		if (user != null) {
			*//**验证充值限额的*//*
			String select = request.getParameter("select"); //表示页面选择的是网银还是快捷,网银为1快捷为2
			String wy = request.getParameter("wy"); 		//表示页面选择的是个人网银还是企业网银  个人网银为1,企业网银为2
			System.out.println(wy);
			UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(user.getId());//根据baseid查询到用户安全等级
			*//**充值记录对象new*//*
			UserRecharge userR = new UserRecharge();
			String indexStr = String.valueOf(userAccountSafeInfo.getUgrade());
			System.out.println(indexStr);
			if (indexStr != null && !"".equals(indexStr)){
				int length = Integer.parseInt(indexStr)-1;
				String index="";
				for(int i=0;i<length;i++){
					index = index+"_";
				}
				String ugrade = index+"1%";
				//查找想要的充值设置记录
				*//**充值设置对象*//*
				RechargeRstr rr = new RechargeRstr();
				rr.setIsuse((short)1);
				rr.setUgrade(ugrade);
				
					if(select.equals("1")){//表示页面选择的是网银
						if(wy.equals("1")){//表示页面选择的是个人网银
							rr.setRechartype((short)1);//充值方式:个人网银,往充值设置对象里面塞值
							userR.setRechargetype((short)1);//往充值记录里面塞值
						}else{//表示页面选择的是企业网银
							rr.setRechartype((short)2);//充值方式:个人网银,往充值设置对象里面塞值
							userR.setRechargetype((short)2);//往充值记录里面塞值
						}
					}else{
						rr.setRechartype((short)3);//充值方式:快捷
						userR.setRechargetype((short)3);
					}
			   //获取充值设置表记录对象
			   rrObject = rechargeRstrService.getUgradeAndRecharTypeAndIsuseForRechargeRstr(rr);
				
				userR.setBaseid(user.getId());
				//查询当天充值成功的充值记录
				List<UserRecharge> UR = userRechargeService.selectAmountList(userR);
				Double totalAmount = 0.0;
				for (UserRecharge userRecharge : UR) {
					totalAmount+=userRecharge.getAmount();
				}
				//判断当前用户是不是在排出人名单当中
				String data = "";
				if(rrObject!=null){
					System.out.println(rrObject.getRemovenameno());//selective
					String nameStr = rrObject.getRemovenameno();
					System.out.println(nameStr);
					int frm = 0;//在排出人员名单中有无当前用户的标识,1为有,0为没有
					String[]  nameno = nameStr.split(",");
					boolean flag1=false;
					  for (int i = 0; i < nameno.length; i++) {//循环1:循环得到排出人员名单编号
						  boolean flag2=false;
				            System.out.println(nameno[i]);
				            List<RemoveName>  rnList = removeNameService.getRemove(nameno[i]);
				            for (RemoveName removeName : rnList) {//循环2:循环得到baseid
								if(removeName.getBaseid().equals(user.getId())){
									frm=1;
									flag2=true;
									flag1=true;
								}
								if(flag2)
									break;//跳出循环2
							}
				            if(flag1)
				            	break;//跳出循环1
				        }
					rrObject.setFlagremovename(frm);
					rrObject.setTotalamoount(totalAmount);
					data=JSON.toJSONString(rrObject);
				}else{
					data="0";
				}
				sendJsonData(response, data);
			}
		}
		
	}

	*//**
	 * 确认充值(请求参数列表)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/RechargeConfirmation", method = { RequestMethod.POST, RequestMethod.GET })
	public void rechargeConfirmation(HttpServletRequest request, HttpServletResponse response,String money) throws Exception {
		
		
		
		 从session中拿到登录用户 
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//这是获取页面选择是网银1 还是快捷2
		String select = request.getParameter("select"); 
		//这是获取页面选择的银行卡代号eg:ICBC  ABC
		String bankCode = request.getParameter("bankCode");//个人网银银行卡代号
		String bankCodeQy = request.getParameter("bankCodeQy");//企业网银代号
		//获取页面选择的是什么网银,是个人网银 1  还是企业网银2
		String wy = request.getParameter("wy");
		//保存充值记录
		UserRecharge userRecharge = new UserRecharge();
		//创建一个huifuParams实体类
		HuifuParams huifuParams = new HuifuParams();
		
		if(user!=null){
			//获取托管账户信息对象
			UserFsAccountInfo  userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(user.getId());
			//获取用户账户信息安全对象
			UserAccountSafeInfo usf = userAccountSafeInfoService.selectByBaseId(user.getId());
			
			huifuParams.setUsrCustId(userFsAccount.getUsrcustid());//设置用户客户号
			
			RechargeRstr rr = new RechargeRstr();
			  if(Integer.parseInt(select)==1){//表示页面选择的是网银
				    if(Integer.parseInt(wy)==1){//表示选择的是个人网银
				    	rr.setRechartype((short)1);
				    	huifuParams.setGateBusiId("B2C");
						huifuParams.setOpenBankId(bankCode); //设置银行卡代号
						userRecharge.setBankname(huifuParams.getOpenBankId());//保存个人银行代号
						userRecharge.setRechargetype((short)1);//保存充值方式
				    }else{//表示页面选择的是企业网银
				    	rr.setRechartype((short)2);
				    	huifuParams.setGateBusiId("B2B");
						huifuParams.setOpenBankId(bankCodeQy); //设置银行卡代号
						userRecharge.setBankname(huifuParams.getOpenBankId());//保存企业银行卡代号
						userRecharge.setRechargetype((short)2);//保存充值方式:企业网银
				    }
				}else{//这个表示页面选择的是快捷 
					rr.setRechartype((short)3);
					huifuParams.setGateBusiId("QP");
					userRecharge.setRechargetype((short)3);//保存充值方式:快捷
					//获取快捷充值下的充值银行卡代号和充值银行名称  根据用户baseid,还有isfast..查询出快捷银行卡信息
					 UserBankCard  userBankCard = new UserBankCard();
						userBankCard.setBaseid(user.getId());
						userBankCard.setIsfastbindcard((short)2);
						UserBankCard  userBankCard2= userBankCardService.findIsFastBindCard(userBankCard); 
						if(userBankCard2!=null){  //表示查到数据库有快捷卡的绑定
							huifuParams.setCardId(userBankCard2.getCardno());  //卡号
							System.out.println(UserRecharge_Constant.BANK_MAPTOCODE.get(userBankCard2.getBankname()));
							userRecharge.setBankname(UserRecharge_Constant.BANK_MAPTOCODE.get(userBankCard2.getBankname()));//获取充值银行
						}else{
							System.out.println("还没有快捷卡号,请绑定***********");  
						}
				}
			  //处理会员等级
				System.out.println(usf.getUgrade());
				String ugrade = StringUtil.getIndex(usf.getUgrade());
				rr.setUgrade(ugrade);
				rr.setIsuse((short)1);//表示已经启用
				RechargeRstr rechargeRstr = rechargeRstrService.getUgradeAndRecharTypeAndIsuseForRechargeRstr(rr);
				//入参扩展域的配置
				String flag="";
				String flag2="";
				String str = "";
				if(rechargeRstr!=null){
					if(rechargeRstr.getSelfpayratio()==100){//充值人自付比例为100%的时候就是自付
						flag="U";
						str = flag;
						System.out.println(str);
						userRecharge.setFeeobjflag(str);
					} 
					if(rechargeRstr.getProxypayratio()==100){
						flag="M";
						flag2 = rechargeRstr.getProxypayman();
						str =flag+","+flag2;
						System.out.println(str);
						userRecharge.setFeeobjflag(str);
					}
				}else{//充值设置表没有记录的时候就默认是下面的值
					flag="M";
					flag2="MDT000001";
					str = flag+","+flag2;
					userRecharge.setFeeobjflag(str);
				}
				
				
			huifuParams.setOrdId(StringUtil.getOrderNo2(user.getId().longValue()));  //生成订单号
			String merPriv = HttpClientHandler.getBase64Encode(user.getId().toString()); //商户私有域
			huifuParams.setMerPriv(merPriv);
			huifuParams.setCmdId("NetSave");
			huifuParams.setOrdDate(sd.format(new Date()));
			String TransAmt = df1.format( Double.valueOf(request.getParameter("transAmt")));
			huifuParams.setTransAmt(TransAmt);
			//商户后台应答地址
			String URL = "http://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath();
			System.out.println(URL);
			String BgRetUrl = URL+"/HuifuUserRecharge/UserRechargeCallback.action";
			String RetUrl = URL+"/HuifuUserRecharge/reCallback.action";
			huifuParams.setBgRetUrl(BgRetUrl);
			huifuParams.setRetUrl(RetUrl);
			huifuParams.setDcFlag("D");
			//入参扩展域
			String FeeObjFlag = "&quot;FeeObjFlag&quot;:&quot;"+flag+"&quot;";
			String FeeAcctId = "&quot;FeeAcctId&quot;:&quot;"+flag2+"&quot;";
			String ReqExt = "{" + FeeObjFlag + ","+FeeAcctId+"}";
			huifuParams.setReqExt(ReqExt);
			huifuParams.setCertId(user.getCertificationnumber());//身份证号码
			//fenzhuang(huifuParams);
		
			userRecharge.setBaseid(user.getId());//保存用户id
			userRecharge.setRechargeno(huifuParams.getOrdId());//充值订单号
			userRecharge.setAmount(Double.valueOf(huifuParams.getTransAmt()));//充值金额
			userRecharge.setStarttime(new Date()); //充值开始时间
			System.out.println(user.getLoginname()+"*************");
			userRecharge.setApplyman(user.getLoginname()); //充值人!也就是登陆的用户
			userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
			userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
			userRecharge.setPaycompany("汇付天下"); //充值通道公司
			userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); //表示充值状态取消 
			userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
			userRecharge.setMercustid(huifuParams.getMerCustId());//保存商户号
			userRecharge.setRecharfee(0.00);//充值手续费刚开始为0.00,因为还没有付款,汇付那边还没有返回,等有返回就替换
			userRecharge.setCardno(huifuParams.getCardId());
			userRecharge.setUuid(StringUtil.getRechargeNo());
			userRecharge.setUrid("0");
			userRechargeService.add(userRecharge);
			request.getRequestDispatcher("/WEB-INF/pages/recharge/recharge.jsp").forward(request, response);
		}
	}
	*//**模拟充值
	 * 确认充值(请求参数列表)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/simulatedRecharge", method = { RequestMethod.POST, RequestMethod.GET })
	public void simulatedRecharge(HttpServletRequest request, HttpServletResponse response,String money) throws Exception {
		boolean flag = false;
		String data = "";
		if(flag){
			//获取当前用户的baseid
			UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			//查询当前用户账户余额有多少,更新用户的可用余额
			UserAccount userAccount = userAccountService.getUserAccountByBaseId(user.getId());
			//冻结余额
	    	double freezebalance = 0;
	    	//可用余额
	    	double avlbalance = 0;
	    	double avlbalanceK = 0;//可用余额
	    	double balanceZ = 0;  //总资产
	    	
	    	if(userAccount!=null){
	    		//冻结金额
	    		freezebalance = userAccount.getFreezebalance();
		    	//可用余额
		    	avlbalance = userAccount.getAvlbalance();
	    	}
	    	avlbalance = Arith.add(avlbalance, Double.valueOf(money));
	    	balanceZ = Arith.add(avlbalance,freezebalance);
	    	userAccount.setAvlbalance(avlbalance);
	    	userAccount.setBalance(balanceZ);
	    	userAccountService.updateUseraccount(userAccount);
	    	
			//生成收支明细
	    	AccInExRecord accInExRecord = new AccInExRecord();//存收支记录的对象
	    	Date date = new Date();
			accInExRecord.setOutamount(0.0);//用户支出
			accInExRecord.setStatus((short)1);
			accInExRecord.setTotalbalance(balanceZ);//用户总金额
			accInExRecord.setFreebalance(freezebalance);//冻结金额
			accInExRecord.setBaseid(user.getId());
			accInExRecord.setType((short)1);//type 为1 表示业务类型为充值
			accInExRecord.setInamount(Double.valueOf(money));//用户收入
			accInExRecord.setBalance(avlbalance);//可用余额
			accInExRecord.setRecordtime(date);//时间
			accInExRecord.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
			accInExRecord.setDescription("充值说明");
			accInExRecord.setRemark("备注");
			System.out.println(System.currentTimeMillis()+"插入时间-------------------------");
			accInExRecordService.insertSelective(accInExRecord);
			//更新充值记录
			UserRecharge userRecharge = new UserRecharge();
			userRecharge.setBaseid(user.getId());//保存用户id
			userRecharge.setRechargeno(StringUtil.getOrderNo2(user.getId().longValue()));//充值订单号
			userRecharge.setAmount(Double.valueOf(money));//充值金额
			userRecharge.setStarttime(new Date()); //充值开始时间
			System.out.println(user.getLoginname()+"*************");
			userRecharge.setApplyman(user.getLoginname()); //充值人!也就是登陆的用户
			userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
			userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
			userRecharge.setPaycompany("汇付天下"); //充值通道公司
			userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); //表示充值状态取消
			userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
			//userRecharge.setMercustid(huifuParams.getMerCustId());//保存商户号
			userRecharge.setRecharfee(0.00);//充值手续费刚开始为0.00,因为还没有付款,汇付那边还没有返回,等有返回就替换
			//userRecharge.setCardno(huifuParams.getCardId());
			userRecharge.setUuid(StringUtil.getRechargeNo());
			userRecharge.setUrid("0");
			userRecharge.setStatus(UserRecharge_Constant.STATUS_ONE);//充值状态
			//userRecharge.setBankreturnno(huifuParams.getTrxId()); //银行返回充值订单号
			//userRecharge.setOriginclient((short)1);//充值来源 pc/手机
			//userRecharge.setRecharfee(Double.valueOf(huifuParams.getFeeAmt())); //充值手续费
			//Double  recharRate = Double.valueOf(huifuParams.getFeeAmt())/100;
			//userRecharge.setRecharrate(recharRate); //充值费率
			userRecharge.setRemark("充值成功");
			//userRecharge.setCardno(huifuParams.getCardId());//快捷充值的时候的卡号
			userRecharge.setEndtime(new Date());//充值结束时间
			userRechargeService.add(userRecharge);
			data = "success"+","+df.format(Double.valueOf(money)).toString();
		}
		data = "fail";
		sendJsonData(response, data);
		
	}
	*//**
	 * 模拟充值
	 * @param @param money 充值金额
	 * @param @param name 获取银行代码
	 * @param @param sum 充值类型 网银为1
	 * @param @param orderno 充值订单号
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 *//*
	@RequestMapping(value = "/simulatedRecharge2", method = { RequestMethod.POST, RequestMethod.GET })
	public void simulatedRecharge2(String money,String name,String sum,String orderno) throws Exception {
		  LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
	      reqMap.put("TRXCODE", "5810");//交易代码	TRXCODE
	      reqMap.put("BANKCODE", "30040000");//银行代码	BANKCODE
	      reqMap.put("TRXDATE", "20170331");//交易日期	TRXDATE
	      reqMap.put("TRXTIME", "102100");//交易时间	TRXTIME
	      reqMap.put("COINSTCODE", "800114");//合作单位编号	COINSTCODE
	      reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
	      reqMap.put("SEQNO","20170421133000001118");//交易流水号	SEQNO
	      reqMap.put("SOURCE", "A0");//ESB内部渠道	SOURCE
	      reqMap.put("RETCODE", "");//应答码	RETCODE
	      reqMap.put("RETMSG", "");//应答码描述	RETMSG
	      reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED
	      
	      reqMap.put("CARDNBR", "01");//电子账户
	      reqMap.put("CARD_BIND", "");//绑定卡卡号
	      reqMap.put("CURRENCY", "156");//币种
	      reqMap.put("AMOUNT", "100");//金额
	      reqMap.put("KEYTYPE", "");//证件类型
	      reqMap.put("IDNO", "630105198506020131");//证件号码 IDNO
	      reqMap.put("SURNAME", "\\u9676\\u71d5");//姓名 SURNAME
	      //reqMap.put("MOBILE", "18677771111");//手机号码 MOBILE
	      reqMap.put("MOBILE", "18677771114");//手机号码 MOBILE
	      reqMap.put("AUTH_FLAG", "N");//ESB代发实名认证标志
	      //下面为可选参数
	      reqMap.put("AUTHSEQ", "");//实名认证流水号
	      reqMap.put("BANK_CODE", "");//开户银行代码
	      reqMap.put("BANK_NAME_EN", "");//开户银行英文缩写
	      reqMap.put("BANK_NAME_CN", "");//开户银行中文名称
	      reqMap.put("ISS_BANK_PROVINCE", "");//开户行省份
	      reqMap.put("ISS_BANK_CITY", "");//开户行城市
	      reqMap.put("CALL_BACK_ADRRESS", "");//回调地址
	      reqMap.put("SMS_CODE", "");//短信验证码
	      reqMap.put("SMS_SEQ", "");//短信序列号
	      reqMap.put("USR_IP", "");//客户IP
	      reqMap.put("RESERVED", "");//保留域
	      
	      ArrayList<String> resColumnList = new ArrayList<String>();
	      resColumnList.add("CARDNBR");
	      resColumnList.add("CURRENCY");
	      resColumnList.add("AMOUNT");
	      resColumnList.add("RESERVED");

	      testCommon2(reqMap, resColumnList,null);
		
		boolean flag = true;
		String data = "";
		if(flag){
			//获取当前用户的baseid
			UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			//查询当前用户账户余额有多少,更新用户的可用余额
			UserAccount userAccount = userAccountService.getUserAccountByBaseId(user.getId());
			//冻结余额
	    	double freezebalance = 0;
	    	//可用余额
	    	double avlbalance = 0;
	    	double avlbalanceK = 0;//可用余额
	    	double balanceZ = 0;  //总资产
	    	
	    	if(userAccount!=null){
	    		//冻结金额
	    		freezebalance = userAccount.getFreezebalance();
		    	//可用余额
		    	avlbalance = userAccount.getAvlbalance();
	    	}
	    	avlbalance = Arith.add(avlbalance, Double.valueOf(money));
	    	balanceZ = Arith.add(avlbalance,freezebalance);
	    	userAccount.setAvlbalance(avlbalance);
	    	userAccount.setBalance(balanceZ);
	    	userAccountService.updateUseraccount(userAccount);
	    	
			//生成收支明细
	    	AccInExRecord accInExRecord = new AccInExRecord();//存收支记录的对象
	    	Date date = new Date();
			accInExRecord.setOutamount(0.0);//用户支出
			accInExRecord.setStatus((short)1);
			accInExRecord.setTotalbalance(balanceZ);//用户总金额
			accInExRecord.setFreebalance(freezebalance);//冻结金额
			accInExRecord.setBaseid(user.getId());
			accInExRecord.setType((short)1);//type 为1 表示业务类型为充值
			accInExRecord.setInamount(Double.valueOf(money));//用户收入
			accInExRecord.setBalance(avlbalance);//可用余额
			accInExRecord.setRecordtime(date);//时间
			accInExRecord.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
			accInExRecord.setDescription("充值说明");
			accInExRecord.setRemark("备注");
			System.out.println(System.currentTimeMillis()+"插入时间-------------------------");
			accInExRecordService.insertSelective(accInExRecord);
			//更新充值记录
			UserRecharge userRecharge = new UserRecharge();
			userRecharge.setBaseid(user.getId());//保存用户id
			userRecharge.setRechargeno(orderno);//充值订单号
			userRecharge.setAmount(Double.valueOf(money));//充值金额
			userRecharge.setStarttime(new Date()); //充值开始时间
			if(sum.equals("1")){
				userRecharge.setRechargetype((short)1);
			}
			if(sum.equals("2")){
				userRecharge.setRechargetype((short)3);
			}
			System.out.println(user.getLoginname()+"*************");
			userRecharge.setApplyman(user.getLoginname()); //充值人!也就是登陆的用户
			userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
			userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
			userRecharge.setPaycompany("汇付天下"); //充值通道公司
			userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); //表示充值状态取消
			userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
			//userRecharge.setMercustid(huifuParams.getMerCustId());//保存商户号
			userRecharge.setRecharfee(0.00);//充值手续费刚开始为0.00,因为还没有付款,汇付那边还没有返回,等有返回就替换
			//userRecharge.setCardno(huifuParams.getCardId());
			userRecharge.setUuid(StringUtil.getRechargeNo());
			userRecharge.setUrid("0");
			userRecharge.setStatus(UserRecharge_Constant.STATUS_ONE);//充值状态
			//userRecharge.setBankreturnno(huifuParams.getTrxId()); //银行返回充值订单号
			//userRecharge.setOriginclient((short)1);//充值来源 pc/手机
			//userRecharge.setRecharfee(Double.valueOf(huifuParams.getFeeAmt())); //充值手续费
			//Double  recharRate = Double.valueOf(huifuParams.getFeeAmt())/100;
			//userRecharge.setRecharrate(recharRate); //充值费率
			userRecharge.setRemark("充值成功");
			userRecharge.setBankname(name);//充值银行代号
			//userRecharge.setCardno(huifuParams.getCardId());//快捷充值的时候的卡号
			userRecharge.setEndtime(new Date());//充值结束时间
			userRechargeService.add(userRecharge);
			data = "success"+","+df.format(Double.valueOf(money)).toString();
		}
		
		sendJsonData(response, data);
		
	}

	*//**
	 *充值失败转化页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/fail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView fail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/manager/recharge/rechargeFail");
		return mav;
	}
	*//**
	 * 查看限额
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 *//*
	@RequestMapping(value = "/banklist", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView banklist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/manager/recharge/banklist");
		return mav;
	}
	*//**
	 * 充值成功跳转页面
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 *//*
	@RequestMapping(value = "/pay", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView pay(HttpServletRequest request, HttpServletResponse response,String money,String sum) throws Exception {
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		ModelAndView mav = new ModelAndView();
		mav.addObject("money", money);
		mav.addObject("sum", sum);
		mav.addObject("orderno", StringUtil.getOrderNo2(user.getId().longValue()));
		mav.setViewName("user/manager/recharge/pay");
		return mav;
	}
	
	*//**
	 *算出充值后余额为多少的方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/addmoney", method = { RequestMethod.POST, RequestMethod.GET })
	public void addmoney(HttpServletRequest request, HttpServletResponse response,String money) throws Exception {
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
	
}
*/