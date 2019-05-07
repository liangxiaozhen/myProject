package com.ptpl.controller.huifu;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.AccountFreezeThaw;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.AccountFreezeThawServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;

/**
 * 冻结解冻返回
 * @author admin
 *
 */
@Controller
@RequestMapping("/HuifuUsrFreeze")
public class HuifuUsrFreezeController extends BaseController{
	
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserFsAccountInfoServiceI userFSAccountInfoService;
	@Autowired
	private AccountFreezeThawServiceI accountFreezeThawService;
	@Autowired
	private AccInExRecordServiceI accInExRecordService;
	/**
	 * @throws Exception 
	 * 
	* @Title: userRechargeCallback 
	* @Description: TODO(冻结后台返回) 
	* @param @param request
	* @param @param response
	* @param @param huifuParams  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value="/UsrFreezeBgCallback")
	public  synchronized void  usrFreezeBgCallback(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams) throws Exception{
		System.out.println("我来了Callck");
		/*返回值拼接*/
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(huifuParams.getCmdId())).append(StringUtils.trimToEmpty(huifuParams.getRespCode()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId())).append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()))
		.append(StringUtils.trimToEmpty(huifuParams.getSubAcctType())).append(StringUtils.trimToEmpty(huifuParams.getSubAcctId()))
		.append(StringUtils.trimToEmpty(huifuParams.getOrdId())).append(StringUtils.trimToEmpty(huifuParams.getOrdDate()))
		.append(StringUtils.trimToEmpty(huifuParams.getTransAmt())).append(StringUtils.trimToEmpty(huifuParams.getRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl())).append(StringUtils.trimToEmpty(huifuParams.getTrxId()))
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
			
			
			if(huifuParams.getRespCode().equals("000")){
				System.out.println("返回成功");
				System.out.println(huifuParams);
				UserAccount userAccount =  userAccountService.getUserAccountByBaseId(new BigDecimal(huifuParams.getMerPriv()));
				//double balance = userAccount.getBalance();//总资产
				double avlBalance = userAccount.getAvlbalance();//可用余额
				double freezeBalance = userAccount.getFreezebalance();//冻结金额
				String djamount = huifuParams.getTransAmt();//您要冻结的金额
				Double transAmt = Double.valueOf(djamount);
				//冻结之后的可用余额
				double  avlBalance2 =  Arith.sub(avlBalance, transAmt);
				//冻结之后的冻结余额
				double freezeBalance2 = Arith.add(freezeBalance, transAmt);
				userAccount.setAvlbalance(avlBalance2);
				userAccount.setFreezebalance(freezeBalance2);
				userAccountService.updateUseraccount(userAccount);
				//保存冻结记录,冻结表
				AccountFreezeThaw aft = accountFreezeThawService.getByOrdId(huifuParams.getOrdId());
				
				
				aft.setTrxid(huifuParams.getTrxId());//汇付返回唯一标识
				aft.setStatus((short)1);//状态1为已冻结,2为未冻结
				aft.setDescription("资金冻结");//说明
				
				aft.setAvlbalance(avlBalance2);
				aft.setFreezebalance(freezeBalance2);
				
				accountFreezeThawService.update(aft);
				String jsonStr = JSON.toJSONString(huifuParams.getRespCode());
				sendJsonData(response, jsonStr);
			}
		
	}
	/**
	 * 解冻后台返回
	* @Title: usrUnFreezeCallback 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param huifuParams
	* @param @throws Exception  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value="/UsrUnFreezeCallback")
	public  synchronized void  usrUnFreezeCallback(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams) throws Exception{
		System.out.println("我来了Callck");
		/*返回值拼接*/
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(huifuParams.getCmdId())).append(StringUtils.trimToEmpty(huifuParams.getRespCode()))
		.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()))
		.append(StringUtils.trimToEmpty(huifuParams.getOrdId())).append(StringUtils.trimToEmpty(huifuParams.getOrdDate()))
		.append(StringUtils.trimToEmpty(huifuParams.getTrxId())).append(StringUtils.trimToEmpty(huifuParams.getRetUrl()))
		.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()))
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
 		
		if(huifuParams.getRespCode().equals("000")){
			System.out.println("返回成功");
			System.out.println(huifuParams);
			//获取解冻对象
			AccountFreezeThaw accountFreezeThaw = accountFreezeThawService.getByTrxid(huifuParams.getTrxId());
 			
			//算用户账户的金额
			UserAccount userAccount = userAccountService.getUserAccountByBaseId(accountFreezeThaw.getBaseid());
			System.out.println(userAccount.getFreezebalance()+"冻结余额");//本来账户的冻结余额
			
			System.out.println(accountFreezeThaw.getAmount()+"解冻余额");//需要解冻金额
			
			System.out.println(userAccount.getAvlbalance()+"账户可用余额");
			
			double freezebalance = Arith.sub(userAccount.getFreezebalance(), accountFreezeThaw.getAmount());//冻结余额
			System.out.println(freezebalance+"解冻过后的冻结余额");
			
			double avlbalance = Arith.add(userAccount.getAvlbalance(), accountFreezeThaw.getAmount());//可用余额
			System.out.println(avlbalance+"解冻过后的可用余额");
			userAccount.setAvlbalance(avlbalance);
			userAccount.setFreezebalance(freezebalance);
			userAccountService.updateUseraccount(userAccount);
			
			System.out.println(request.getParameter("remark"));
			accountFreezeThaw.setStatus((short)3); //表示已解冻
			//accountFreezeThaw.setAvlbalance(accountFreezeThaw.getAvlbalance());
			accountFreezeThaw.setAvlbalance(avlbalance);
			accountFreezeThaw.setFreezebalance(freezebalance);
			//accountFreezeThaw.setAvlbalance(Arith.add(accountFreezeThaw.getAvlbalance(),accountFreezeThaw.getAmount()));
			//accountFreezeThaw.setFreezebalance(userAccount.getFreezebalance());
			//accountFreezeThaw.setFreezebalance(Arith.sub(accountFreezeThaw.getFreezebalance(),accountFreezeThaw.getAmount()));
			accountFreezeThaw.setDescription("解冻成功");
			accountFreezeThaw.setThawtime(new Date()); //解冻时间
			accountFreezeThaw.setRemark(request.getParameter("remark"));
			accountFreezeThawService.update(accountFreezeThaw);
		}
		
	}
	/**
	 * 
	* @Title: reCallback 
	* @Description: TODO(冻结页面返回) 
	* @param @param request
	* @param @param response
	* @param @param huifuParams  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	/*@RequestMapping(value="/reCallback")
	public  ModelAndView  reCallback(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		String ordId = huifuParams.getOrdId();
		String Trxid = huifuParams.getTrxId();
		String transAmt = huifuParams.getTransAmt();
		ModelAndView mav = new ModelAndView();
		mav.addObject("ordId", ordId);
		mav.addObject("transAmt", transAmt); 
		mav.addObject("Trxid", Trxid); 
		mav.addObject("respdesc", huifuParams.getRespDesc());
		System.out.println(huifuParams.getRespDesc()+"*********************");
		mav.setViewName("/admin/userAccount/recallback");
		return mav;
		
	}*/
	/**
	 * 解冻页面返回
	* @Title: reCallbackjd 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param huifuParams
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	/*@RequestMapping(value="/reCallbackjd")
	public  ModelAndView  reCallbackjd(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		String ordId = huifuParams.getOrdId();
		String Trxid = huifuParams.getTrxId();
		AccountFreezeThaw accountFreezeThaw = accountFreezeThawService.getByTrxid(huifuParams.getTrxId());
		String transAmt = String.valueOf(accountFreezeThaw.getAmount());
		ModelAndView mav = new ModelAndView();
		mav.addObject("ordId", ordId);
		mav.addObject("transAmt", transAmt); 
		mav.addObject("Trxid", Trxid); 
		mav.addObject("respdesc", huifuParams.getRespDesc());
		System.out.println(huifuParams.getRespDesc()+"*********************");
		mav.setViewName("/admin/usrFree/recallback");
		return mav;
		
	}*/
}
