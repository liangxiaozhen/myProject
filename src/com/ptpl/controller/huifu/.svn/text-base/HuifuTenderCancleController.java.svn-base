package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 汇付天下接口-投标撤销
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/huifu/tenderCancle")
public class HuifuTenderCancleController extends BaseController {
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;

	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	static Boolean flag = false;
	
	/**
	 * 投标撤销校验
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tenderCancleCheck")
	public String tenderCancleCheck(String orderNo) throws Exception{
		// 从session中获取当前登录用户用户账户安全信息
		UserAccountSafeInfo userAccountSafeInfo = getUserAccountSafeInfo();
		if(userAccountSafeInfo == null)
			return "redirect:/user/tologin.action";
		Map<String, String> map = new HashMap<String, String>();
		String message = "success";
		UserTender userTender = userTenderService.findUserTenderByOrderno(orderNo);	// 根据投标订单号查询投标记录
		if(userTender == null){
			message = "订单号不存在！";
		}else{
			if(userTender.getTstatus() == 3){
				message = "此订单已撤销！";
			}else if(userTender.getTstatus() == 4){
				message = "投标已放款！";
			}else if(userTender.getTstatus() == 1){
				TenderItem tenderItem = tenderItemService.findTenderItemById(userTender.getTenderid());
				if(tenderItem.getIsacancel() != null){
					if(tenderItem.getIsacancel()==0){
						message = "此标不允许投标撤回！";
					}else{
						Short indexStr = userAccountSafeInfo.getUgrade();
						char[] allowcugrade = tenderItem.getAllowcugrade().toCharArray();
						if(allowcugrade[indexStr-1] == '0'){
							message = "你所在等级不允许投标撤回！";
						}
					}
				}
			}
		}
		map.put("message", message);
		if(message.equalsIgnoreCase("success")){
			flag = true;
		}
		sendJsonData(response, JSON.toJSONString(map));
		return null;
	}
	
	/**
	 * 汇付天下接口-投标撤销请求参数
	 * @param huifuparam
	 * @param orderNo
	 * @throws Exception
	 */
	@RequestMapping(value = "/tenderCancle", method = { RequestMethod.POST, RequestMethod.GET })
	public void tenderCancle(HuifuParams huifuparam, String orderNo) throws Exception{
		if(!flag)
			return;
		flag = false;
		huifuparam.setVersion("20");																// 版本号
		huifuparam.setCmdId("TenderCancle"); 														// 消息类型TenderCancle
		huifuparam.setOrdId(orderNo);																// 订单号（投标订单号）
		UserTender userTender = userTenderService.findUserTenderByOrderno(orderNo);	// 根据投标订单号查询投标记录
		huifuparam.setOrdDate(StringUtil.formatDate(userTender.getTendtime(), "yyyyMMdd"));			// 订单日期（投标订单日期）
		huifuparam.setTransAmt(df1.format(userTender.getAmount()));									// 交易金额（投标金额）
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid());									// 用户客户号
		huifuparam.setIsUnFreeze("Y");																// 是否解冻
		huifuparam.setUnFreezeOrdId(StringUtil.getNo());											// 解冻订单号
		huifuparam.setFreezeTrxId(userTender.getFreezetrxid());										// 冻结标识
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuparam.setRetUrl(basePath+"/huifu/tenderCancle/tenderCancleRetUrl.action");				// 页面返回URL
		huifuparam.setBgRetUrl(basePath+"/huifu/tenderCancle/tenderCancleCallBack.action");			// 商户后台应答地址
		hfTenderCancle(huifuparam);
	}
	
	
	/**
	 * 汇付天下接口-投标撤销请求参数拼接加签
	 * @param huifuparam
	 * @throws Exception
	 */
	public void hfTenderCancle(HuifuParams huifuparam) throws Exception{
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))					// 版本号
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))					// 消息类型
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))				// 商户客户号
				.append(StringUtils.trimToEmpty(huifuparam.getOrdId()))					// 订单号
				.append(StringUtils.trimToEmpty(huifuparam.getOrdDate()))				// 订单日期
				.append(StringUtils.trimToEmpty(huifuparam.getTransAmt()))				// 交易金额
				.append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))				// 用户客户号
				.append(StringUtils.trimToEmpty(huifuparam.getIsUnFreeze()))			// 是否解冻
				.append(StringUtils.trimToEmpty(huifuparam.getUnFreezeOrdId()))			// 解冻订单号
				.append(StringUtils.trimToEmpty(huifuparam.getFreezeTrxId()))			// 冻结标识
				.append(StringUtils.trimToEmpty(huifuparam.getRetUrl()))				// 页面返回URL
				.append(StringUtils.trimToEmpty(huifuparam.getBgRetUrl()))				// 商户后台应答地址
				.append(StringUtils.trimToEmpty(huifuparam.getMerPriv()))				// 商户后台应答地址
				.append(StringUtils.trimToEmpty(huifuparam.getRespExt()));				// 入参扩展域
		String plainStr = buffer.toString();
		System.out.println("组装投标撤销加签字符串原文:========="+plainStr);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			request.setAttribute("Version", huifuparam.getVersion());
			request.setAttribute("CmdId", huifuparam.getCmdId());
			request.setAttribute("MerCustId", huifuparam.getMerCustId());
			request.setAttribute("OrdId", huifuparam.getOrdId());
			request.setAttribute("OrdDate", huifuparam.getOrdDate());
			request.setAttribute("TransAmt", huifuparam.getTransAmt());
			request.setAttribute("UsrCustId", huifuparam.getUsrCustId());
			request.setAttribute("IsUnFreeze", huifuparam.getIsUnFreeze());
			request.setAttribute("UnFreezeOrdId", huifuparam.getUnFreezeOrdId());
			request.setAttribute("FreezeTrxId", huifuparam.getFreezeTrxId());
			request.setAttribute("RetUrl", huifuparam.getRetUrl());
			request.setAttribute("BgRetUrl", huifuparam.getBgRetUrl());
			request.setAttribute("MerPriv", huifuparam.getMerPriv());
			request.setAttribute("RespExt", huifuparam.getRespExt());
			request.setAttribute("ChkValue", ChkValue);
			request.setAttribute("CharSet", huifuparam.getCharSet());
			request.getRequestDispatcher("/WEB-INF/pages/TenderCancle/TenderCancle.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/tenderCancleCallBack")
	public synchronized void tenderCancleCallBack(HuifuParams huifuParam) throws Exception {
		System.out.println("投标撤销回调进来了！！！！！！！！！！！！！！！！！！！！！！！！！");
		System.out.println("消息类型：CmdId=================================="+huifuParam.getCmdId());
		System.out.println("应答返回码：RespCode=================================="+huifuParam.getRespCode());
		System.out.println("应答描述======================================"+huifuParam.getRespDesc());
		System.out.println("商户用户号：MerCustId=================================="+huifuParam.getMerCustId());
		System.out.println("订单号：OrdId=================================="+huifuParam.getOrdId());
		System.out.println("订单日期：OrdDate=================================="+huifuParam.getOrdDate());
		System.out.println("交易金额：TransAmt=================================="+huifuParam.getTransAmt());
		System.out.println("用户客户号:UsrCustId=================================="+huifuParam.getUsrCustId());
		System.out.println("是否解冻:IsUnFreeze=================================="+huifuParam.getIsUnFreeze());
		System.out.println("解冻订单号:UnFreezeOrdId=================================="+huifuParam.getUnFreezeOrdId());
		System.out.println("冻结标识：FreezeTrxId=================================="+huifuParam.getFreezeTrxId());
		System.out.println("商户后台应答地址：BgRetUrl=================================="+huifuParam.getBgRetUrl());
        String chkValue = huifuParam.getChkValue();
        System.out.println("投标撤销应答获取的签名：=============="+chkValue);
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(huifuParam.getCmdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespCode()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOrdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOrdDate()))
        		.append(StringUtils.trimToEmpty(huifuParam.getTransAmt()))
        		.append(StringUtils.trimToEmpty(huifuParam.getUsrCustId()))
				.append(StringUtils.trimToEmpty(huifuParam.getIsUnFreeze()))			// 是否解冻
				.append(StringUtils.trimToEmpty(huifuParam.getUnFreezeOrdId()))			// 解冻订单号
				.append(StringUtils.trimToEmpty(huifuParam.getFreezeTrxId()))			// 冻结标识
        		.append(StringUtils.trimToEmpty(huifuParam.getRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getBgRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerPriv()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespExt()));
        String plainStr = buffer.toString();
        System.out.println("投标撤销冻结标识：=============="+huifuParam.getFreezeTrxId());
        System.out.println("投标撤销应答获取的返回参数拼接：=============="+plainStr);
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
        	if(huifuParam.getRespCode().equals("000")){ // 返回码000成功
        		UserTender userTender = userTenderService.findUserTenderByOrderno(huifuParam.getOrdId());
        		if(userTender.getTstatus() != 3){
            		// 修改投标设置已完成金额
            		updateTenderItem(userTender);
            		// 修改投资人账户，解冻投标冻结金额
            		updateUserAccount(huifuParam);
        		}
        		// 根据投标订单号修改投标状态
        		updateUserTender(userTender);
                try {
                    if (StringUtils.isNotBlank(huifuParam.getOrdId())) {
                        PrintWriter out = response.getWriter();
                        out.print("RECV_ORD_ID_".concat(huifuParam.getOrdId()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        	}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
	}


	/**
	 * 根据投标订单号修改投标状态
	 * @param userTender
	 */
	private void updateUserTender(UserTender userTender) {
		userTender.setTstatus(TenderRecord_Constant.TSTATUS_REVOKE); // 投标的状态-撤销
		int count = 0;
		count = userTenderService.updateByOrderNO(userTender);
		if(count > 0){
			System.out.println("修改投标记录投标状态成功！");
		}
	}

	/**
	 * 修改投标设置已完成金额
	 * @param tender
	 */
	private void updateTenderItem(UserTender tender) {
		TenderItem item = tenderItemService.findTenderItemById(tender.getTenderid());						// 获得标的设置详情
		item.setFinishtamount(Arith.sub(item.getFinishtamount(), tender.getAmount()));						// 已完成投标金额=已完成金额-投标撤销金额
		if(Arith.sub(item.getFinishtamount(), item.getTamount())<0){
			item.setTstatus((short) 4);
		}
		int count = 0;
		count = tenderItemService.update(item);
		if(count > 0){
			System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}

	/**
	 * 修改投资人账户，解冻投标冻结金额
	 * @param huifuParam
	 */
	private void updateUserAccount(HuifuParams huifuParam) {
		// 更新投资人用户账户表-解冻冻结余额
		UserAccount account = new UserAccount();
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(huifuParam.getUsrCustId()); 	// 根据投资人用户客户号查询托管账户
		BigDecimal baseId = userFsAccountInfo.getBaseid(); 																				// 用户baseid
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(baseId); 													// 根据baseid查询用户账户信息
		double balance = userAccount.getBalance();																						// 用户总资产不变
		double avlbalance = Arith.add(userAccount.getAvlbalance(), Double.valueOf(huifuParam.getTransAmt())); 							// 用户可用余额 = 用户可用余额+投标金额
		double freezeBalance = Arith.sub(userAccount.getFreezebalance(), Double.valueOf(huifuParam.getTransAmt())); 					// 用户冻结余额 = 用户冻结余额-投标金额
		account.setBaseid(baseId);																										// baseId
		account.setBalance(balance);																									// 用户总资产
		account.setAvlbalance(avlbalance);																								// 可用余额
		account.setFreezebalance(freezeBalance);																						// 冻结余额
		int count = 0;																
		count = userAccountService.updateUseraccount(account);
		if(count > 0){
			System.out.println("更新投资人用户账户成功！！！！！！！！！！！！！！！！！！！！！！！！");
			System.out.println("账户总资产============================="+account.getBalance());
		}
	}

	/**
	 * 投标撤销页面返回地址
	 * @param huifuParam
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/tenderCancleRetUrl", method = {RequestMethod.POST})
	public void tenderCancleRetUrl(HuifuParams huifuParam) throws ServletException, IOException{
		System.out.println("投标撤销页面返回URL进来了！！！！！！！！！！！！！！！！！！！！！！！！！");
		String RespCode = huifuParam.getRespCode();
		String RespDesc = URLDecoder.decode(huifuParam.getRespDesc(),"UTF-8");
		request.setAttribute("RespCode", RespCode);
		request.setAttribute("RespDesc", RespDesc);
		System.out.println("应答描述===================="+RespDesc);
		request.getRequestDispatcher("/WEB-INF/pages/TenderCancle/TenderCancle_Success.jsp").forward(request, response);
	}
}
