package com.ptpl.controller.huishang;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.huifu.util.httpClient.HttpClientHandler;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/huishang/bidApply")
public class BidApplyController {
	public static Log logger = LogFactory.getLog(BidApplyController.class);
	/** 投标记录Service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的设置Service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/** 用户账户Service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 用户托管账户信息Service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	public String doPost(Map<String, String> params) throws ClientProtocolException, IOException {
		String result = null;
		HttpPost httpPost = new HttpPost("http://116.30.193.140:8882/ptpjx/huishang/bidApply/bidApplyCallBack2.action");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if (params != null) {
			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
			httpPost.setEntity(formEntiry);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getReasonPhrase().equals("OK")
						&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		}
		return result;
	}
	
	@RequestMapping(value="bidApplyCallBack2")
	public void bidApplyCallBack2(HttpServletRequest request, HttpServletResponse response){
		String VERSION = request.getParameter("VERSION"); // 接口版本号
		String TRXDATE = request.getParameter("TRXDATE"); // 交易日期
		String TRXTIME = request.getParameter("TRXTIME"); // 交易时间
		String SERI_NO = request.getParameter("SERI_NO"); // 申请流水号
		String BANKCODE = request.getParameter("BANKCODE"); // 银行代码
		String COINSTCODE = request.getParameter("COINSTCODE"); // 合作单位编号
		String COINSTCHANNEL = request.getParameter("COINSTCHANNEL"); // 合作单位渠道
		String CARDNBR = request.getParameter("CARDNBR"); // 电子账号
		String FUISSUER = request.getParameter("FUISSUER"); // 产品发行方
		String PRODUCT = request.getParameter("PRODUCT"); // 标的编号
		String FRZAMT = request.getParameter("FRZAMT"); // 投标金额
		String FORINCOME = request.getParameter("FORINCOME"); // 预期收益
		String BUYDATE = request.getParameter("BUYDATE"); // 投标日期
		String STATE = request.getParameter("STATE"); // 记录状态
		String AUTHCODE = request.getParameter("AUTHCODE"); // 投标申请授权码
		String BIDNBR = request.getParameter("BIDNBR"); // 标的编号-40
		String TRDRESV = request.getParameter("TRDRESV"); // 第三方保留域
		String RETCODE = request.getParameter("RETCODE"); // 应答码
		String RETMSG = request.getParameter("RETMSG"); // 应答描述
		System.out.println("接口版本号VERSION=" + VERSION);
		System.out.println("交易日期TRXDATE=" + TRXDATE);
		System.out.println("交易时间TRXTIME=" + TRXTIME);
		System.out.println("申请流水号SERI_NO=" + SERI_NO);
		System.out.println("银行代码BANKCODE=" + BANKCODE);
		System.out.println("合作单位编号COINSTCODE=" + COINSTCODE);
		System.out.println("合作单位渠道COINSTCHANNEL=" + COINSTCHANNEL);
		System.out.println("电子账号CARDNBR=" + CARDNBR);
		System.out.println("产品发行方FUISSUER=" + FUISSUER);
		System.out.println("标的编号PRODUCT=" + PRODUCT);
		System.out.println("投标金额FRZAMT=" + FRZAMT);
		System.out.println("预期收益FORINCOME=" + FORINCOME);
		System.out.println("投标日期BUYDATE=" + BUYDATE);
		System.out.println("记录状态STATE=" + STATE);
		System.out.println("投标申请授权码AUTHCODE=" + AUTHCODE);
		System.out.println("标的编号-40BIDNBR=" + BIDNBR);
		System.out.println("第三方保留域TRDRESV=" + TRDRESV);
		System.out.println("应答码RETCODE=" + RETCODE);
		System.out.println("VERSIONRETMSG=" + RETMSG);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(VERSION));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(SERI_NO));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(CARDNBR));
		buffer.append(StringUtils.trimToEmpty(FUISSUER));
		buffer.append(StringUtils.trimToEmpty(PRODUCT));
		buffer.append(StringUtils.trimToEmpty(FRZAMT));

		buffer.append(StringUtils.trimToEmpty(FORINCOME));
		buffer.append(StringUtils.trimToEmpty(BUYDATE));
		buffer.append(StringUtils.trimToEmpty(STATE));
		buffer.append(StringUtils.trimToEmpty(AUTHCODE));
		buffer.append(StringUtils.trimToEmpty(BIDNBR));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		String str = buffer.toString();
		Boolean verifyFlag = HSignUtil.getVerify(str, request.getParameter("SIGN"));
		System.out.println("验签是否成功============="+verifyFlag);
		if(verifyFlag){
			UserTender userTender = userTenderService.findUserTenderByOrderno(SERI_NO);
			if(RETCODE.equals("00000000")){ // 返回码88成功
        		System.out.println("开始更新数据！！！！！！！！！！！");
    			if(userTender.getTstatus()==0){ // 防止重复更新数据
    				updateTenderItem(new BigDecimal(TRDRESV), FRZAMT);
    				updateInvestorAccount(AES.getEncrypt(CARDNBR), FRZAMT);
    			}
        		// 更新投标记录
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
        		userTender.setTendtime(new Date()); 								// 转账完成时间
        		userTender.setAuthcode(AUTHCODE); 									// 投标授权码
			}else{
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
        	}
			userTenderService.updateByPrimaryKeySelective(userTender);
		}
		try {
			response.getWriter().write("success");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/bidApplyCallBack")
	public void bidApplyCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("进入投标回调");
//		try {
//			String bgData=request.getParameter("bgData");
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().write("success");
//			Map<String, String> map = JSONObject.fromObject(bgData);
//			String str = doPost(map);
//			System.out.println(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String bgData=request.getParameter("bgData");
		System.out.println(bgData);
		Map<String, String> map = JSONObject.fromObject(bgData);
		System.out.println(com.alibaba.fastjson.JSON.toJSONString(map));
		String VERSION = map.get("VERSION"); // 接口版本号
		String TRXDATE = map.get("TRXDATE"); // 交易日期
		String TRXTIME = map.get("TRXTIME"); // 交易时间
		String SERI_NO = map.get("SERI_NO"); // 申请流水号
		String BANKCODE = map.get("BANKCODE"); // 银行代码
		String COINSTCODE = map.get("COINSTCODE"); // 合作单位编号
		String COINSTCHANNEL = map.get("COINSTCHANNEL"); // 合作单位渠道
		String CARDNBR = map.get("CARDNBR"); // 电子账号
		String FUISSUER = map.get("FUISSUER"); // 产品发行方
		String PRODUCT = map.get("PRODUCT"); // 标的编号
		String FRZAMT = map.get("FRZAMT"); // 投标金额
		String FORINCOME = map.get("FORINCOME"); // 预期收益
		String BUYDATE = map.get("BUYDATE"); // 投标日期
		String STATE = map.get("STATE"); // 记录状态
		String AUTHCODE = map.get("AUTHCODE"); // 投标申请授权码
		String BIDNBR = map.get("BIDNBR"); // 标的编号-40
		String TRDRESV = map.get("TRDRESV"); // 第三方保留域
		String RETCODE = map.get("RETCODE"); // 应答码
		String RETMSG = map.get("RETMSG"); // 应答描述
		System.out.println("接口版本号VERSION=" + VERSION);
		System.out.println("交易日期TRXDATE=" + TRXDATE);
		System.out.println("交易时间TRXTIME=" + TRXTIME);
		System.out.println("申请流水号SERI_NO=" + SERI_NO);
		System.out.println("银行代码BANKCODE=" + BANKCODE);
		System.out.println("合作单位编号COINSTCODE=" + COINSTCODE);
		System.out.println("合作单位渠道COINSTCHANNEL=" + COINSTCHANNEL);
		System.out.println("电子账号CARDNBR=" + CARDNBR);
		System.out.println("产品发行方FUISSUER=" + FUISSUER);
		System.out.println("标的编号PRODUCT=" + PRODUCT);
		System.out.println("投标金额FRZAMT=" + FRZAMT);
		System.out.println("预期收益FORINCOME=" + FORINCOME);
		System.out.println("投标日期BUYDATE=" + BUYDATE);
		System.out.println("记录状态STATE=" + STATE);
		System.out.println("投标申请授权码AUTHCODE=" + AUTHCODE);
		System.out.println("标的编号-40BIDNBR=" + BIDNBR);
		System.out.println("第三方保留域TRDRESV=" + TRDRESV);
		System.out.println("应答码RETCODE=" + RETCODE);
		System.out.println("VERSIONRETMSG=" + RETMSG);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(VERSION));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(SERI_NO));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(CARDNBR));
		buffer.append(StringUtils.trimToEmpty(FUISSUER));
		buffer.append(StringUtils.trimToEmpty(PRODUCT));
		buffer.append(StringUtils.trimToEmpty(FRZAMT));

		buffer.append(StringUtils.trimToEmpty(FORINCOME));
		buffer.append(StringUtils.trimToEmpty(BUYDATE));
		buffer.append(StringUtils.trimToEmpty(STATE));
		buffer.append(StringUtils.trimToEmpty(AUTHCODE));
		buffer.append(StringUtils.trimToEmpty(BIDNBR));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		String str = buffer.toString();
		Boolean verifyFlag = HSignUtil.getVerify(str, map.get("SIGN"));
		System.out.println("验签是否成功============="+verifyFlag);
		if(verifyFlag){
			UserTender userTender = userTenderService.findUserTenderByOrderno(SERI_NO);
			if(RETCODE.equals("00000000")){ // 返回码88成功
        		System.out.println("开始更新数据！！！！！！！！！！！");
    			if(userTender.getTstatus()==0){ // 防止重复更新数据
    				updateTenderItem(userTender.getTenderid(), FRZAMT);
    				updateInvestorAccount(AES.getEncrypt(CARDNBR), FRZAMT);
    			}
        		// 更新投标记录
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
        		userTender.setTendtime(new Date()); 								// 转账完成时间
        		userTender.setAuthcode(AUTHCODE); 									// 投标授权码
			}else{
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
        	}
			userTenderService.updateByPrimaryKeySelective(userTender);
		}
		try {
			response.getWriter().write("success");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/bidApplyCallBackToLocal")
	public void bidApplyCallBackToLocal(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.debug("进入投标回调本地");
//		try {
//			String bgData=request.getParameter("bgData");
//			response.setContentType("text/html;charset=UTF-8");
//			response.getWriter().write("success");
//			Map<String, String> map = JSONObject.fromObject(bgData);
//			String str = doPost(map);
//			System.out.println(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String bgData=request.getParameter("bgData");
		logger.debug(bgData);
		Map<String, String> map = JSONObject.fromObject(bgData);
		logger.debug(com.alibaba.fastjson.JSON.toJSONString(map));
		String VERSION = map.get("VERSION"); // 接口版本号
		String TRXDATE = map.get("TRXDATE"); // 交易日期
		String TRXTIME = map.get("TRXTIME"); // 交易时间
		String SERI_NO = map.get("SERI_NO"); // 申请流水号
		String BANKCODE = map.get("BANKCODE"); // 银行代码
		String COINSTCODE = map.get("COINSTCODE"); // 合作单位编号
		String COINSTCHANNEL = map.get("COINSTCHANNEL"); // 合作单位渠道
		String CARDNBR = map.get("CARDNBR"); // 电子账号
		String FUISSUER = map.get("FUISSUER"); // 产品发行方
		String PRODUCT = map.get("PRODUCT"); // 标的编号
		String FRZAMT = map.get("FRZAMT"); // 投标金额
		String FORINCOME = map.get("FORINCOME"); // 预期收益
		String BUYDATE = map.get("BUYDATE"); // 投标日期
		String STATE = map.get("STATE"); // 记录状态
		String AUTHCODE = map.get("AUTHCODE"); // 投标申请授权码
		String BIDNBR = map.get("BIDNBR"); // 标的编号-40
		String TRDRESV = map.get("TRDRESV"); // 第三方保留域
		String RETCODE = map.get("RETCODE"); // 应答码
		String RETMSG = map.get("RETMSG"); // 应答描述
		logger.debug("接口版本号VERSION=" + VERSION);
		logger.debug("交易日期TRXDATE=" + TRXDATE);
		logger.debug("交易时间TRXTIME=" + TRXTIME);
		logger.debug("申请流水号SERI_NO=" + SERI_NO);
		logger.debug("银行代码BANKCODE=" + BANKCODE);
		logger.debug("合作单位编号COINSTCODE=" + COINSTCODE);
		logger.debug("合作单位渠道COINSTCHANNEL=" + COINSTCHANNEL);
		logger.debug("电子账号CARDNBR=" + CARDNBR);
		logger.debug("产品发行方FUISSUER=" + FUISSUER);
		logger.debug("标的编号PRODUCT=" + PRODUCT);
		logger.debug("投标金额FRZAMT=" + FRZAMT);
		logger.debug("预期收益FORINCOME=" + FORINCOME);
		logger.debug("投标日期BUYDATE=" + BUYDATE);
		logger.debug("记录状态STATE=" + STATE);
		logger.debug("投标申请授权码AUTHCODE=" + AUTHCODE);
		logger.debug("标的编号-40BIDNBR=" + BIDNBR);
		logger.debug("第三方保留域TRDRESV=" + TRDRESV);
		logger.debug("应答码RETCODE=" + RETCODE);
		logger.debug("VERSIONRETMSG=" + RETMSG);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(VERSION));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(SERI_NO));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(CARDNBR));
		buffer.append(StringUtils.trimToEmpty(FUISSUER));
		buffer.append(StringUtils.trimToEmpty(PRODUCT));
		buffer.append(StringUtils.trimToEmpty(FRZAMT));

		buffer.append(StringUtils.trimToEmpty(FORINCOME));
		buffer.append(StringUtils.trimToEmpty(BUYDATE));
		buffer.append(StringUtils.trimToEmpty(STATE));
		buffer.append(StringUtils.trimToEmpty(AUTHCODE));
		buffer.append(StringUtils.trimToEmpty(BIDNBR));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		String str = buffer.toString();
		Boolean verifyFlag = HSignUtil.getVerify(str, map.get("SIGN"));
		logger.debug("验签是否成功============="+verifyFlag);
		if(verifyFlag){
//			UserTender userTender = userTenderService.findUserTenderByOrderno(SERI_NO);
//			if(RETCODE.equals("00000000")){ // 返回码88成功
//        		logger.debug("开始更新数据！！！！！！！！！！！");
//    			if(userTender.getTstatus()==0){ // 防止重复更新数据
//    				updateTenderItem(userTender.getTenderid(), FRZAMT);
//    				updateInvestorAccount(CARDNBR, FRZAMT);
//    			}
//        		// 更新投标记录
//        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
//        		userTender.setTendtime(new Date()); 								// 转账完成时间
//        		userTender.setAuthcode(AUTHCODE); 									// 投标授权码
//			}else{
//        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
//        	}
//			userTenderService.updateByPrimaryKeySelective(userTender);
			doHttpClient(map);
		}
		try {
			response.getWriter().write("success");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//数据发送本地地址
	public  static String doHttpClient(Map map){
		String result = "";
		String uri = "";
		if(map.size() > 0){
			JSONObject reqdata = JSONObject.fromObject(map);
			uri = (String)(map.get("TRDRESV")+"/huishang/bidApply/getBidApplyCallBack.action");
 			HttpClient httpClient = new HttpClient();
			PostMethod method = new PostMethod(uri);
 			
 			method.setParameter("reqdata", reqdata.toString());
 			try {
				httpClient.executeMethod(method);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 			try {
				result = method.getResponseBodyAsString();
			} catch (IOException e) {
 				e.printStackTrace();
			}
 		}
 		return result;
	}
	//本地接收服务器返回的测试数据（因为徽商后台通知只会通知服务器域名的地址 my.ganjiangps.com）
	@RequestMapping("/getBidApplyCallBack")
	public void getBidApplyCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String bgData=request.getParameter("reqdata");
		logger.debug(bgData);
		Map<String, String> map = JSONObject.fromObject(bgData);
		logger.debug(com.alibaba.fastjson.JSON.toJSONString(map));
		String VERSION = map.get("VERSION"); // 接口版本号
		String TRXDATE = map.get("TRXDATE"); // 交易日期
		String TRXTIME = map.get("TRXTIME"); // 交易时间
		String SERI_NO = map.get("SERI_NO"); // 申请流水号
		String BANKCODE = map.get("BANKCODE"); // 银行代码
		String COINSTCODE = map.get("COINSTCODE"); // 合作单位编号
		String COINSTCHANNEL = map.get("COINSTCHANNEL"); // 合作单位渠道
		String CARDNBR = map.get("CARDNBR"); // 电子账号
		String FUISSUER = map.get("FUISSUER"); // 产品发行方
		String PRODUCT = map.get("PRODUCT"); // 标的编号
		String FRZAMT = map.get("FRZAMT"); // 投标金额
		String FORINCOME = map.get("FORINCOME"); // 预期收益
		String BUYDATE = map.get("BUYDATE"); // 投标日期
		String STATE = map.get("STATE"); // 记录状态
		String AUTHCODE = map.get("AUTHCODE"); // 投标申请授权码
		String BIDNBR = map.get("BIDNBR"); // 标的编号-40
		String TRDRESV = map.get("TRDRESV"); // 第三方保留域
		String RETCODE = map.get("RETCODE"); // 应答码
		String RETMSG = map.get("RETMSG"); // 应答描述
		
		UserTender userTender = null;
		if(SERI_NO!=null){
			userTender = userTenderService.findUserTenderByOrderno(SERI_NO);
		}
				
		if(RETCODE.equals("00000000")){ // 返回码88成功
    		logger.debug("开始更新数据！！！！！！！！！！！");
			if(userTender.getTstatus()==0){ // 防止重复更新数据
				updateTenderItem(userTender.getTenderid(), FRZAMT);
				updateInvestorAccount(AES.getEncrypt(CARDNBR), FRZAMT);
			}
    		// 更新投标记录
    		userTender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
    		userTender.setTendtime(new Date()); 								// 转账完成时间
    		userTender.setAuthcode(AUTHCODE); 									// 投标授权码
		}else{
    		userTender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
    	}
		int i = userTenderService.updateByPrimaryKeySelective(userTender);
		logger.debug(i+"");
	}

	/**
	 * 更新标的设置，已完成投标金额
	 * @param huifuParam
	 * @throws Exception 
	 */
	private void updateTenderItem(BigDecimal tid, String Amount) {
		// 修改标的设置表
		TenderItem item = tenderItemService.findTenderItemById(tid);	// 获得标的设置详情
		if(item.getFinishtamount()==null)
			item.setFinishtamount((double) 0);
		item.setFinishtamount(Arith.add(item.getFinishtamount(), Double.valueOf(Amount)));	// 已完成投标金额=已完成金额+当前投标金额
		if(Arith.sub(item.getTamount(), item.getFinishtamount())<=0){
			item.setTstatus((short) 5); // 满标
		}
		int count = 0;
		count = tenderItemService.update(item);
		if(count > 0){
			System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 更新投资人用户账户表-冻结余额
	 * @param huifuParam
	 */
	private void updateInvestorAccount(String usrcustid, String Amount) {
		UserAccount account = new UserAccount();
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(usrcustid);
		BigDecimal outAccountId = userFsAccountInfo.getBaseid();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(outAccountId); 												// 根据baseid查询用户账户信息
		double balance = userAccount.getBalance();																						// 用户总资产不变
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), Double.valueOf(Amount)); 											// 用户可用余额 = 用户可用余额-投标金额
		if(userAccount.getFreezebalance() == null)
			userAccount.setFreezebalance((double) 0);
		double freezeBalance = Arith.add(userAccount.getFreezebalance(), Double.valueOf(Amount)); 										// 用户冻结余额 = 用户冻结余额+投标金额
		account.setBaseid(outAccountId);																								// baseId
		account.setBalance(balance);																									// 用户总资产
		account.setAvlbalance(avlbalance);																								// 可用余额
		account.setFreezebalance(freezeBalance);																						// 冻结余额
		int count = 0;
		count = userAccountService.updateUseraccount(account); // 根据basid更新用户账户表
		if(count > 0){
			System.out.println("更新投资人用户账户成功！！！！！！！！！！！！！！！！！！！！！！！！");
			System.out.println("账户总资产============================="+account.getBalance());
		}
	}
}
