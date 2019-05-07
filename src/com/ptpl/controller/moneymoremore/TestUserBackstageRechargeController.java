package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.moneymoremore.model.MoneyMoreMoreRecharge;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.RechargeRstr;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBalanceQueryServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;
/**用户后台充值*/
@Controller
@RequestMapping("/testmoneymoremore/userRecharge")
public class TestUserBackstageRechargeController extends BaseController{
	
	/** 充值记录*/
	@Autowired
	UserRechargeServiceI userRechargeService;
	/** 用户基本信息  ServiceI */
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/**银行卡信息*/
	@Autowired
    UserBankCardServiceI userBankCardService;
	/**用户账户表*/
	@Autowired
	UserAccountServiceI userAccountService;
	/**用户收支明细记录*/
	@Autowired
	AccInExRecordServiceI accInExRecordService;
	@Autowired
	UserAccountSafeInfoServiceI  userAccountSafeInfoService;
	@Autowired
	RechargeRstrServiceI rechargeRstrService;
	/** 托管账户信息*/
	@Autowired
	UserFsAccountInfoServiceI  userFsAccountInfoService;
	/**用户债转service,主要是用于定向名单的确定*/
	@Autowired
	UserDebtAttornServiceI userDebtAttornService;
	@Autowired
	UserBalanceQueryServiceI userBalanceQueryService;
	
	
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

			/**
			 * 步骤:1.先查询当前充值用户的会员等级是什么会员等级,然后加上充值方式,再加上是否启用来查询到充值设置表的数据,如果没有查到数据则返回0 
			 * 2.匹配到之后拿到定向名单编号
			 * 3.根据定向名单编号查询,如果在定向名单中,说明是当前会员等级是可以用的,调用债转UserDebtAttornServiceI 的
			 * boolean ugradeFalsePublic(BigDecimal snlid,BigDecimal baseid);
			 * 4.如果在定向名单中!说明是可以用的
			 * 
			 */

			/** 充值记录对象*/
			UserRecharge userR = new UserRecharge();
			System.out.println(indexStr);
			if (indexStr != null && !"".equals(indexStr)) {
				int length = Integer.parseInt(indexStr) - 1;
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

				String data = "";
				if (rrObject != null) {
					//将查询到的充值设置对象保存进对象
					request.getSession().setAttribute("rrObject", rrObject);
					// 判断当前用户是不是在定向名单允许的范围内
					boolean flag = userDebtAttornService.ugradeFalsePublic(rrObject.getSnlid(), user.getId());
					// true 成功就是 0 ,false 就是 1
					int frm = 0;
					if (!flag) {
						frm = 1;
					}
					rrObject.setFlagremovename(frm);/** 验证充值人是否在排出人名单里面 */
					rrObject.setTotalamoount(totalAmount);
					data = JSON.toJSONString(rrObject);
				} else {
					data = "0";
				}
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
	public void rechargeConfirmation(HttpServletRequest request, HttpServletResponse response) throws Exception {
			/* 从session中拿到登录用户 */
			//UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			//保存充值记录
			//UserRecharge userRecharge = new UserRecharge();
			//获取托管账户信息对象
			UserFsAccountInfo  userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(new BigDecimal(599));
			
			String result = userBalanceQueryService.getUserBlanaceParams(userFsAccount);
			System.out.println(result);
			String [] str = result.split("\\|");
			System.out.println(str[0]+"*********"+str[1]+"---------"+str[2]);
			System.out.println("*************************************");
			/*//获取用户账户信息安全对象
			//UserAccountSafeInfo usf = userAccountSafeInfoService.selectByBaseId(user.getId());
			// 从session中取到充值设置对象
			RechargeRstr rrObject = (RechargeRstr) request.getSession().getAttribute("rrObject");
			
		    String  RechargeMoneymoremore  = "m111836";// m111817,m111714充值人乾多多标识
		    String PlatformMoneymoremore = "p2089";//平台乾多多标识
		    String OrderNo = StringUtil.getOrderNo2(new Long(602));//平台充值订单号
		    //充值金额:直接从页面获取
		    Double Amount = 50000.01;//df1.format( Double.valueOf(request.getParameter("Amount")));
		    //充值类型   空.网银充值1.代扣充值(暂不可用)2.快捷支付3.汇款充值4.企业网银
		    int RechargeType=2; //request.getParameter("RechargeType")
		    //手续费类型 :从充值设置表中取
		    int FeeType=2; //rrObject.getFeeType();
		   // String CardNo = "";//银行卡号
		   // String RandomTimeStamp ="";//随机时间戳
		    String Remark1 = "602";//自定义备注:存入user.getId(),用户baseid
		    //String Remark2 = "";
			//String Remark3 = "";
		    String ReturnURL =  StringUtil.getBasePath(request)+"/testmoneymoremore/userRecharge/RechargeReturnURL.action";//页面返回地址
		    String NotifyURL = StringUtil.getBasePath(request)+"/testmoneymoremore/userRecharge/HHRechargeCallBack.action";//后台通知地址
		    
		    StringBuffer buffer = new StringBuffer();
			buffer.append(RechargeMoneymoremore).append(StringUtils.trimToEmpty(PlatformMoneymoremore))
					.append(StringUtils.trimToEmpty(OrderNo)).append(Amount)//.append(RechargeType)
					//.append(FeeType)//.append(StringUtils.trimToEmpty(CardNo))//.append(StringUtils.trimToEmpty(RandomTimeStamp))
					.append(StringUtils.trimToEmpty(Remark1))//.append(StringUtils.trimToEmpty(Remark2))//.append(StringUtils.trimToEmpty(Remark3))
					.append(StringUtils.trimToEmpty(ReturnURL)).append(StringUtils.trimToEmpty(NotifyURL));
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
			//request.setAttribute("Remark2", Remark2);
			//request.setAttribute("Remark3", Remark3);
			request.setAttribute("ReturnURL", ReturnURL);
			request.setAttribute("NotifyURL", NotifyURL);
			request.setAttribute("SignInfo", privateResult);
			//保存充值订单号到mmmRecharge对象中
			MoneyMoreMoreRecharge mmmRecharge = new MoneyMoreMoreRecharge();
			mmmRecharge.setOrderNo(OrderNo);
			
			userRecharge.setBaseid(new BigDecimal(602));//保存用户id
			userRecharge.setRechargeno(OrderNo);//充值订单号
			userRecharge.setAmount(Amount);//充值金额
			userRecharge.setStarttime(new Date()); //充值开始时间
			//userRecharge.setApplyman(user.getLoginname()); //充值人!也就是登陆的用户
			userRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
			userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
			userRecharge.setPaycompany("双乾支付"); //充值通道公司
			userRecharge.setStatus(UserRecharge_Constant.STATUS_THREE); //表示充值状态取消
			userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
			userRecharge.setMercustid(PlatformMoneymoremore);//保存商户号
			userRecharge.setRecharfee(0.00);//充值手续费刚开始为0.00,因为还没有付款,汇付那边还没有返回,等有返回就替换
			userRecharge.setUuid(StringUtil.getRechargeNo());
			userRecharge.setUrid("0");
			userRechargeService.add(userRecharge);
			try {
				request.getRequestDispatcher("/WEB-INF/MMMPages/UserRecharge/recharge.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
	 			e.printStackTrace();
			}*/
			
	}
	
	@RequestMapping(value= "/HHRechargeCallBack")
	public synchronized void UserAccountRechargeCallBack(HttpServletRequest request,HttpServletResponse response,MoneyMoreMoreRecharge mmmRecharge){
		System.out.println(mmmRecharge);
		//String SignInfo = request.getParameter("SignInfo");
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(mmmRecharge.getRechargeMoneymoremore()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getPlatformMoneymoremore()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getLoanNo()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getOrderNo()))
				.append(mmmRecharge.getAmount())
				//.append(mmmRecharge.getFee())
				//.append(mmmRecharge.getFeePlatform())
				//.append(mmmRecharge.getRechargeType())
				//.append(mmmRecharge.getFeeType())
				//.append(StringUtils.trimToEmpty(mmmRecharge.getCardNoList()))
				//.append(StringUtils.trimToEmpty(mmmRecharge.getRandomTimeStamp()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getRemark1()))
				//.append(StringUtils.trimToEmpty(mmmRecharge.getRemark2()))
				//.append(StringUtils.trimToEmpty(mmmRecharge.getRemark3()))
				.append(StringUtils.trimToEmpty(mmmRecharge.getResultCode()));
 		String str = buffer.toString();
		 
 		RsaHelper rs = RsaHelper.getInstance();
		boolean falg = rs.verifySignature(mmmRecharge.getSignInfo(), str, RsaHelper.publicString);//验证签名
 		if(falg && mmmRecharge.getResultCode().equals("88")){//验证签名成功
			request.setAttribute("RespDesc",mmmRecharge.getMessage());
			//request.getRequestDispatcher("/WEB-INF/MMMPages/UserRegister/success.jsp").forward(request, response);
			if(!mmmRecharge.getRemark1().equals("") && mmmRecharge.getRemark1()!=null){//假如用户id有的情况下才进行下一步
				// 根据id获取用户基本信息
				UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(mmmRecharge.getRemark1())); 
				//用户账户信息安全表
				//UserAccountSafeInfo usf = userAccountSafeInfoService.selectByBaseId(new BigDecimal(mmmRecharge.getRemark1()));
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
	    		if(mmmRecharge.getFeeType()==null){//表示不用扣任何手续费
	    			accInExRecord2.setPinamount(0.0);//表示金额为平台收入
	    			accInExRecord2.setPoutamount(0.0);//表示金额为平台支出
	    			accInExRecord2.setTotalbalance(balanceZ);//用户总金额
	    			accInExRecord2.setBalance(avlbalanceK);//可用余额
	    			accInExRecord2.setOutamount(0.0);//用户支出
	    			accInExRecord2.setInamount(0.0);//用户收入
	    		}else{
	    			if(mmmRecharge.getFeeType().equals(1)){//表示从充值人账户全额扣除
	    				accInExRecord2.setPinamount(0.0);//平台收入
	    				accInExRecord2.setPoutamount(0.0);//平台支出
	    				//算总金额和可用余额
	    				avlbalanceK = Arith.sub(avlbalanceK,Double.valueOf(mmmRecharge.getFee()));
	    				balanceZ = Arith.add(avlbalanceK, freezebalance);
	    				accInExRecord2.setTotalbalance(balanceZ);//用户总金额
	    				accInExRecord2.setBalance(avlbalanceK);//可用余额
	    				accInExRecord2.setOutamount(Double.valueOf(mmmRecharge.getFee()));//用户支出
	    				accInExRecord2.setInamount(0.0);//用户收入
	    			}
	    			if(mmmRecharge.getFeeType().equals(2)){//表示从平台账户全额扣除
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
	    		//根据订单号查询本地数据库的值
	    		UserRecharge userRecharge = userRechargeService.select(mmmRecharge.getOrderNo());
	    		if(mmmRecharge.getRechargeType()==null){//表示是网银充值
	    			userRecharge.setRechargetype((short)0);
	    		}else{
	    			if(mmmRecharge.getRechargeType().equals(1)){//代扣充值
	    				userRecharge.setRechargetype((short)0);
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
	    		Double  recharRate = 0.0;
	    		if(mmmRecharge.getFeeType()==null){//不收费
	    			userRecharge.setRecharfee(0.0); //充值手续费
					userRecharge.setRecharrate(recharRate); //充值费率
	    		}else{
	    			if(mmmRecharge.getFeeType().equals(1)){//充值成功时从充值人账户全额扣除,自付
	    				userRecharge.setFeeobjflag("U"); //手续费自付或代付
	    				userRecharge.setRecharfee(Double.valueOf(mmmRecharge.getFee())); //充值手续费
	    				recharRate= Arith.div(Double.valueOf(mmmRecharge.getFee()), Double.valueOf(mmmRecharge.getAmount()), 5);
	    				userRecharge.setRecharrate(recharRate); //充值费率
	    			}
	    			if(mmmRecharge.getFeeType().equals(2)){//充值成功时从平台自有账户全额扣除,他付
	    				userRecharge.setFeeobjflag("M"); //手续费自付或代付
	    				userRecharge.setRecharfee(Double.valueOf(mmmRecharge.getFeePlatform())); //充值手续费
	    				recharRate = Arith.div(Double.valueOf(mmmRecharge.getFeePlatform()), Double.valueOf(mmmRecharge.getAmount()), 5);
	    				userRecharge.setRecharrate(recharRate); //充值费率
	    			}
	    		}
	    		userRecharge.setEndtime(new Date());//充值结束时间
	    		userRecharge.setStatus(UserRecharge_Constant.STATUS_ONE);//充值状态
				userRecharge.setBankreturnno(mmmRecharge.getLoanNo()); //银行返回充值订单号
				userRecharge.setOriginclient(userBaseAccountInfo.getOriginclient());//充值来源 pc/手机
			
				userRecharge.setRemark("充值成功");
				userRecharge.setCardno("未知");//快捷充值的时候的卡号
				userRecharge.setEndtime(new Date());//充值结束时间
				userRechargeService.update(userRecharge);
			}
		}	
	}
	@RequestMapping(value= "/RechargeReturnURL")
	public void RechargeReturnURL(HttpServletRequest request,HttpServletResponse response){
		System.out.println("急急急急急急军军军军");
	}
}
