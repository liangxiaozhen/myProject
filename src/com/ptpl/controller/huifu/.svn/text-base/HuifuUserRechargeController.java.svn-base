package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.RechargeRstr;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/HuifuUserRecharge")
public class HuifuUserRechargeController extends BaseController {
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
	/*CmdId +RespCode + MerCustId + UsrCustId + OrdId+OrdDate + TransAmt + TrxId + RetUrl+BgRetUrl+ MerPriv*/
	/**
	 * 充值汇付返回
	* @Title: userRechargeCallback 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param huifuParams  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/UserRechargeCallback")
	public synchronized void  userRechargeCallback(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		System.out.println("CallBack");
		try {
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//解密私有域
		String  merPriv =HttpClientHandler.getBase64Decode(huifuParams.getMerPriv()); 
		if(userBaseAccountInfo == null){
			userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(new BigDecimal(merPriv)); // 根据id获取用户基本信息
		}
		BigDecimal baseid = (BigDecimal) (userBaseAccountInfo.getId() == null ? merPriv : userBaseAccountInfo.getId());
		//获取用户账户信息安全对象
		UserAccountSafeInfo usf = userAccountSafeInfoService.selectByBaseId(baseid);
		/*返回值拼接*/
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(huifuParams.getCmdId())).append(StringUtils.trimToEmpty(huifuParams.getRespCode()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId())).append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()))
		.append(StringUtils.trimToEmpty(huifuParams.getOrdId())).append(StringUtils.trimToEmpty(huifuParams.getOrdDate()))
		.append(StringUtils.trimToEmpty(huifuParams.getTransAmt())).append(StringUtils.trimToEmpty(huifuParams.getTrxId()))
		.append(StringUtils.trimToEmpty(huifuParams.getRetUrl())).append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		String plainStr = sb.toString();
		System.out.println(plainStr);
		boolean flag = false; 
			flag = SignUtils.verifyByRSA(plainStr, huifuParams.getChkValue());
			if (!flag) {
			    System.out.println("验证签名失败...");
			}  
			if (StringUtils.isNotBlank(huifuParams.getOrdId())) {
				PrintWriter out = response.getWriter();
				out.print("RECV_ORD_ID_".concat(huifuParams.getOrdId()));
			}
		

			if(flag && "000".equals(huifuParams.getRespCode())){ //解签成功
				//根据登录的用户,根据baseid查询到时什么会员等级,再根据会员等级和充值方式和是否启用查询当前用户在充值设置表中是设置的是自付还是他付
				RechargeRstr rechargeRstr = new RechargeRstr();
				if(huifuParams.getGateBusiId().equals("B2C")){//个人网银
					rechargeRstr.setRechartype((short)1);
				}
				if(huifuParams.getGateBusiId().equals("B2B")){//企业网银
					rechargeRstr.setRechartype((short)2);
				}
				if(huifuParams.getGateBusiId().equals("QP")){//快捷
					rechargeRstr.setRechartype((short)3);
				}
				String ugrade = StringUtil.getIndex(usf.getUgrade());
				rechargeRstr.setUgrade(ugrade);
				rechargeRstr.setIsuse((short)1);//表示已经启用
				RechargeRstr rstr = rechargeRstrService.getUgradeAndRecharTypeAndIsuseForRechargeRstr(rechargeRstr);
				String feeObj = "";
				if(rstr!=null){
					if(rstr.getSelfpayratio()==100){//充值人自付比例为100%的时候就是自付
						feeObj="U";
					} 
					if(rstr.getProxypayratio()==100){
						feeObj="M";
					}
				}else{//假如充值设置表中没有找到相应的记录,就默认手续费收取对象是商户
					feeObj="M";
				}
				
				System.out.println(System.currentTimeMillis()+"接收时间-------------------------");
				UserRecharge userR = new UserRecharge();
				userR.setRechargeno(huifuParams.getOrdId());
				userR.setBankreturnno(huifuParams.getTrxId());
			    UserRecharge userRechargeGet = userRechargeService.getBankReturnNo(userR);//防止重复充值生成重复的收支记录
			    if(userRechargeGet==null){
			    	UserAccount userAccount = userAccountService.getUserAccountByBaseId(baseid);
			    	AccInExRecord accInExRecord = new AccInExRecord();//存收支记录的对象
			    	AccInExRecord accInExRecord2 = new AccInExRecord();//存手续费记录的对象
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
			    	
			    	//充值金额
			    	double amount = Double.valueOf(huifuParams.getTransAmt());
			    	//充值手续费
			    	double feeamount = Double.valueOf(huifuParams.getFeeAmt());
			    	//充值的时候直接先不算
	    			avlbalanceK = Arith.add(avlbalance,amount);//可用余额
	    			balanceZ = Arith.add(avlbalanceK,freezebalance);//总金额=可用余额+冻结金额
			    		//存入收支记录
			    		Date date = new Date();
			    		accInExRecord.setOutamount(0.0);//用户支出
			    		accInExRecord.setStatus((short)1);
			    		accInExRecord.setTotalbalance(balanceZ);//用户总金额
			    		accInExRecord.setFreebalance(freezebalance);//冻结金额
			    		accInExRecord.setBaseid(baseid);
			    		accInExRecord.setType((short)1);//type 为1 表示业务类型为充值
			    		accInExRecord.setInamount(amount);//用户收入
			    		accInExRecord.setBalance(avlbalanceK);//可用余额
			    		accInExRecord.setRecordtime(date);//时间
			    		accInExRecord.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
			    		accInExRecord.setDescription("充值说明");
			    		accInExRecord.setRemark("备注");
			    		System.out.println(System.currentTimeMillis()+"插入时间-------------------------");
			    		accInExRecordService.insertSelective(accInExRecord);
			    		
			    		if(userAccount==null){//假如数据库没有这条数据的话就直接插入
			    			userAccount = new UserAccount();
			    			if(feeObj.equals("M")){//充值手续费为他付,也就是扣平台的钱
			    				userAccount.setBaseid(baseid);
			    				userAccount.setBalance(balanceZ);
			    				userAccount.setAvlbalance(avlbalanceK);
			    				userAccount.setFreezebalance(freezebalance);
			    				userAccount.setBaseid(baseid);
			    			}else if(feeObj.equals("U")){//充值手续费为自付
			    				userAccount.setBaseid(baseid);
			    				userAccount.setBalance(Arith.add(Arith.sub(avlbalanceK,feeamount), freezebalance));
			    				userAccount.setAvlbalance(Arith.sub(avlbalanceK,feeamount));
			    				userAccount.setFreezebalance(freezebalance);
			    				userAccount.setBaseid(baseid);
			    			}
			    			userAccountService.insert(userAccount);
			    		}else{//假如数据库有这条数据就修改
			    			if(feeObj.equals("M")){//充值手续费为他付,也就是扣平台的钱
			    				userAccount.setBaseid(baseid);
			    				userAccount.setBalance(balanceZ);
			    				userAccount.setAvlbalance(avlbalanceK);
			    				userAccount.setFreezebalance(freezebalance);
			    				userAccount.setBaseid(baseid);
			    			}else if(feeObj.equals("U")){//充值手续费为自付
			    				userAccount.setBaseid(baseid);
			    				userAccount.setBalance(Arith.add(Arith.sub(avlbalanceK,feeamount), freezebalance));
			    				userAccount.setAvlbalance(Arith.sub(avlbalanceK,feeamount));
			    				userAccount.setFreezebalance(freezebalance);
			    				userAccount.setBaseid(baseid);
			    			}
			    			userAccountService.updateUseraccount(userAccount);
			    		}
			    		//存手续费记录的
			    		if(feeObj.equals("M")){
			    			accInExRecord2.setPinamount(0.0);//表示金额为平台收入
			    			accInExRecord2.setPoutamount(feeamount);//表示金额为平台支出
			    			accInExRecord2.setTotalbalance(balanceZ);//用户总金额
			    			accInExRecord2.setBalance(avlbalanceK);//可用余额
			    			accInExRecord2.setOutamount(0.0);//充值手续费为0
			    			accInExRecord2.setInamount(0.0);//用户收入
			    		}
			    		//表示扣款的是用户
			    		if(feeObj.equals("U")){
			    			accInExRecord2.setPinamount(0.0);//表示金额为平台收入
			    			accInExRecord2.setPoutamount(0.0);//表示金额为平台支出
			    			accInExRecord2.setInamount(0.0);//用户收入
			    			accInExRecord2.setOutamount(feeamount);//表示手续费金额为用户自己给 Arith.sub(amount, feeamount);
			    			balanceZ = Arith.add(Arith.sub(avlbalanceK,feeamount), freezebalance);//重新为总金额赋值
			    			avlbalanceK = Arith.sub(avlbalanceK,feeamount);//重新为可用余额赋值
			    			accInExRecord2.setTotalbalance(balanceZ);//假如扣款客户是用户自己,那么用户总金额=(可用余额-手续费)+冻结金额
			    			accInExRecord2.setBalance(avlbalanceK);//可用余额
			    		}
			    		
			    		accInExRecord2.setRecordtime(date);
			    		accInExRecord2.setType((short)2);
			    		accInExRecord2.setStatus((short)1);
			    		accInExRecord2.setFreebalance(freezebalance);//冻结余额
			    		accInExRecord2.setBaseid(baseid);
			    		accInExRecord2.setAieorderno(StringUtil.getNo()); //保存收支明细流水号
			    		accInExRecord2.setDescription("充值手续费说明");
			    		accInExRecord2.setRemark("充值手续费备注");
			    		/*if(feeamount!=0 || feeObj.equals("M")){//手续费为0或者手续费是平台付的时候就不插入手续费记录
			    			accInExRecordService.insertSelective(accInExRecord2);
			    		}*/
			    		if(feeObj.equals("U") && feeamount!=0){//手续费不为0并且者手续费是自付的时候才插入手续费记录
			    			accInExRecordService.insertSelective(accInExRecord2);
			    		}
			    		updateRecharge(huifuParams,userBaseAccountInfo,baseid);
			    }
			    
				}else{
					System.out.println(huifuParams.getRespDesc()+"***********************");
				}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 保存充值记录(修改请求的时候保存的充值记录,把充值状态给改了);
	 * @param huifuParams
	 * @param userBaseAccountInfo
	 * @throws Exception
	 */
	private void updateRecharge(HuifuParams huifuParams,UserBaseAccountInfo userBaseAccountInfo,BigDecimal baseid) throws Exception {
		//根据订单号查询本地数据库的值
		UserRecharge userRecharge = userRechargeService.select(huifuParams.getOrdId());
		
		userRecharge.setEndtime(new Date());//充值结束时间
		if(huifuParams.getGateBusiId().equals("B2C") || huifuParams.getGateBusiId().equals("B2B")){ //表示网银
		}
		if(huifuParams.getGateBusiId().equals("QP")){//表示快捷
			
			UserBankCard userBankCard2 = new UserBankCard();
			userBankCard2.setBaseid(baseid);
			userBankCard2.setIsfastbindcard((short)2);
			userBankCardService.findIsFastBindCard(userBankCard2);
			if(userBankCardService.findIsFastBindCard(userBankCard2)==null){
				DepositUserBankCard(userBaseAccountInfo,huifuParams);
			}
		}
		if(huifuParams.getRespCode().equals("000")){
			userRecharge.setStatus(UserRecharge_Constant.STATUS_ONE);//充值状态
			userRecharge.setBankreturnno(huifuParams.getTrxId()); //银行返回充值订单号
			userRecharge.setOriginclient(userBaseAccountInfo.getOriginclient());//充值来源 pc/手机
			userRecharge.setRecharfee(Double.valueOf(huifuParams.getFeeAmt())); //充值手续费
			Double  recharRate = Double.valueOf(huifuParams.getFeeAmt())/100;
			userRecharge.setRecharrate(recharRate); //充值费率
			userRecharge.setRemark("充值成功");
			userRecharge.setCardno(huifuParams.getCardId());//快捷充值的时候的卡号
			userRecharge.setEndtime(new Date());//充值结束时间
			userRechargeService.update(userRecharge);
		}
	}

	/**把相关数据存进UserBankCard数据库
	 * @throws Exception */
	public void DepositUserBankCard(UserBaseAccountInfo userBaseAccountInfo,HuifuParams huifuParams) throws Exception{
			
				QueryCardInfoController queryCard = new  QueryCardInfoController();
				UserBankCard userBankCard = new UserBankCard();
				Map<String, String> result = queryCard.getBankCardParams(huifuParams);
				String result2 = queryCard.doBankCardPost(result);
				
				JSONObject jsonObj = JSON.parseObject(result2);  
				JSONArray resultJson = jsonObj.getJSONArray("UsrCardInfolist"); 
				JSONObject jsObj = resultJson.getJSONObject(0);
				String CardId = jsObj.getString("CardId");  //卡号
				String BankId = jsObj.getString("BankId");	//银行卡代号0
				String ExpressFlag = jsObj.getString("ExpressFlag");	//是都是快捷卡标志 Y 是  N不是
				String UpdDateTime = jsObj.getString("UpdDateTime");	//绑定时间
				String RespCode = jsonObj.getString("RespCode");	//标识成功与否
				Date startDate = new SimpleDateFormat("yyyyMMddhhmmss").parse(UpdDateTime);  //处理绑定时间
				huifuParams.setDcFlag("D");
				if(RespCode.equals("000")){//标识绑卡成功
					/*cardtype卡类型为1储蓄卡   username   真实姓名(realName)  bindtime 绑卡时间  bindmode绑卡方式为1  bindstatus绑定状态为1  paycompany绑定通道为“汇付天下”  存进去*/
					if(huifuParams.getDcFlag().equals("D")){//储蓄卡
						userBankCard.setCardtype(Constant.CARDTYPE_JIEJIKA);  //借记卡
					}
					userBankCard.setCardtype(Constant.CARDTYPE_XINYONGKA); //信用卡
					if(ExpressFlag.equals("Y")){
						userBankCard.setIsfastbindcard(Constant.ISFASTBINDCARD_KUAIJIE); //userBankCard表中快捷卡的标识
					}
					userBankCard.setBindstatus(Constant.BINDSTATUS_BIND);//表示已经绑定
					userBankCard.setIsdefaultcard(Constant.ISDEFAULTCARD_YES); //是默认卡
					userBankCard.setUsername(userBaseAccountInfo.getRealname());  //保存真是姓名
					userBankCard.setBindtime(startDate);
					userBankCard.setBindmode(Constant.BINDMODE_JIEKOU);//绑卡方式 接口
					userBankCard.setPaycompany("汇付天下");
					userBankCard.setBaseid(userBaseAccountInfo.getId());
					userBankCard.setBankname(Constant.BANK_MAP.get(BankId));//银行代号
					userBankCard.setCardno(CardId); // 银行卡号
					userBankCardService.insertSelective(userBankCard);
					userBankCard.setBindstatus((short) 2);
					userBankCardService.updateBindStatus(userBankCard);//修改其余卡的绑定状态:已解绑
				}
				
	}
			//回调网页地址
			@RequestMapping(value = "/reCallback", method = {RequestMethod.POST})
			public ModelAndView recallBack(HttpServletRequest request, HttpServletResponse response,
					HuifuParams params) throws Exception{
				String ordId = params.getOrdId();
				String transAmt = params.getTransAmt();
				String feeAmt = params.getFeeAmt();
				String rechargeType = UserRecharge_Constant.BANK_MAP.get(params.getGateBankId());
				String starttime = params.getOrdDate();
				ModelAndView mav = new ModelAndView();
				mav.addObject("ordId", ordId);
				mav.addObject("transAmt", transAmt); 
				mav.addObject("feeAmt", feeAmt);
				mav.addObject("rechargeType", rechargeType);
				mav.addObject("respdesc", params.getRespDesc());
				mav.addObject("starttime", starttime);
				System.out.println(params.getRespDesc()+"*********************");
				mav.setViewName("/user/recharge/recallback");
				return mav;
			}
}
