package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserTender;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

/**
 * 乾多多投标转账接口
 * @ClassName: MMMUserTenderController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhenglm
 * @date 2017年2月23日 下午2:39:46
 * http://113.99.87.125:8882/ptpjx/moneymoremore/UserTender/userBid.action
 */
@Controller
@RequestMapping("/moneymoremore/UserTender")
public class MMMUserTenderController extends BaseController {
	
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;

	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	
	/**
	 * 乾多多投标请求
	 * @Title: userBid
	 * @Description: TODO()
	 * @param request
	 * @param response
	 * @return void    返回类型
	 */
	@RequestMapping(value = "/userBid",method = {RequestMethod.POST,RequestMethod.GET})
	public void userBid(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("发起投标转账请求");
		
		String LoanOutMoneymoremore = "m111832"; // 付款人乾多多标识
		String LoanInMoneymoremore = "m111808"; // 收款人乾多多标识
		String OrderNo = StringUtil.getNo(); // 网贷平台订单号
		String BatchNo = "GJ201702271411"; // 网贷平台标号
		Double Amount = 600.00; // 金额
		Double FullAmount = 1000.00; // 满标标额（所有标号相同的转账记录中，以第一笔转账成功的记录中的标额为准，之后的转账可以不填标额）
		String TransferName = "投标"; // 用途（投标）
		String Remark = "乾多多投标转账测试"; // 备注
//		String SecondaryJsonList = "{[]}";
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore; // 平台乾多多标识
		String LoanJsonList  = "{\"LoanOutMoneymoremore\":\""+LoanOutMoneymoremore+"\",\"LoanInMoneymoremore\":\""+LoanInMoneymoremore+"\",\"OrderNo\":\""+OrderNo+"\",\"BatchNo\":\""+BatchNo+"\",\"Amount\":\""+Amount+"\",\"FullAmount\":\""+FullAmount+"\",\"TransferName\":\""+TransferName+"\",\"Remark\":\""+Remark+"\"}"; // 转账列表
		Integer TransferAction = 1; // 转账类型-1.投标,2.还款,3.其他
		Integer Action = 1; // 操作类型-1.手动转账，2.自动转账
		Integer TransferType = 2; // 转账方式-1.桥连，2.直连
		Integer ArrivalTime = 1; // 到账时间-空.实时转账，1.普通转账，2.次日转账
		Integer NeedAudit = 1; // 通过是否需要审核-空.需要审核，1.自动通过
//		Integer DelayTransfer = 1; // 是否半自动批处理(暂不可用)-空.否,1.是（启用该功能的条件:1.直连，2.付款人唯一，3.公共账户余额不参与转账）
		
		String Remark1 = ""; // 自定义备注
		String Remark2 = ""; // 自定义备注
		String Remark3 = ""; // 自定义备注
		String ReturnURL = StringUtil.getBasePath(request)+"/moneymoremore/UserTender/userTenderReturnUrl.action"; // 页面返回网址
		String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/UserTender/userTenderCallBack.action"; // 后台通知网址
  		 
		/*LoanJsonList + PlatformMoneymoremore + TransferAction + 
		  Action + TransferType + ArrivalTime + NeedAudit + 
		  DelayTransfer + RandomTimeStamp + Remark1 + 
		  Remark2 + Remark3 + ReturnURL + NotifyURL*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(LoanJsonList))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(TransferAction)
				.append(Action)
				.append(TransferType)
				.append(ArrivalTime)
				.append(NeedAudit)
//				.append(DelayTransfer)
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
 		String plainStr = buffer.toString();
        System.out.println("投标转账提交参数原文：=============="+plainStr);
 		//私钥签名
 		String privateResult = "";
 		
 		RsaHelper rsa = RsaHelper.getInstance();
 		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
 		
		request.setAttribute("LoanJsonList", URLEncoder.encode(LoanJsonList, "UTF-8"));
		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
		request.setAttribute("TransferAction", TransferAction);
		request.setAttribute("Action", Action);
		request.setAttribute("TransferType", TransferType);
		request.setAttribute("ArrivalTime", ArrivalTime);
		request.setAttribute("NeedAudit", NeedAudit);
//		request.setAttribute("DelayTransfer", DelayTransfer);
		request.setAttribute("Remark1", Remark1);
		request.setAttribute("Remark2", Remark2);
		request.setAttribute("Remark3", Remark3);
		request.setAttribute("ReturnURL", ReturnURL);
		request.setAttribute("NotifyURL", NotifyURL);
		request.setAttribute("SignInfo", privateResult);
		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/UserTender/UserTender.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
 			e.printStackTrace();
		}
	} 

	/**
	 * 乾多多投标后台应答地址
	 * @Title: userTenderCallBack
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return void    返回类型
	 */
	@RequestMapping(value= "/userTenderCallBack")
	public void userTenderCallBack(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("进入乾多多投标后台应答！！！！！！！！！！！！！！！！！！！！！！！！！");
 		RsaHelper rsa = RsaHelper.getInstance();
 		
		// 返回参数
		String LoanJsonList = URLDecoder.decode(request.getParameter("LoanJsonList"), "utf-8"); // 转账列表（交易列表转换成的JSON对象列表，具体字段见下表参加签名的是原串，接收的时候要进行UrlDecode，编码为utf-8）
		System.out.println("转账列表======="+LoanJsonList);
		JSONArray jsonArray = JSONObject.parseArray(LoanJsonList); // 解析返回的转账列表
		Iterator<Object> it = jsonArray.iterator();
		String Amount = "";
		String OrderNo = "";
		String LoanNo = "";
		while(it.hasNext()){
			JSONObject json = (JSONObject) it.next();
			Amount = json.getString("Amount"); // 转账金额
			LoanNo = json.getString("LoanNo"); // 乾多多流水号
			OrderNo = json.getString("OrderNo"); // 网贷平台订单号
		}
		System.out.println("网贷平台订单号"+OrderNo);
		System.out.println("投标金额"+Amount);
		System.out.println("乾多多流水号"+LoanNo);
		String PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore"); // 平台乾多多标识
		String Action = request.getParameter("Action"); // 操作类型-为空表示转账，1表示通过，2表示退回，3表示同意二次分配，4表示不同意二次分配
		System.out.println("操作类型======="+Action);
		String RandomTimeStamp = request.getParameter("RandomTimeStamp"); // 随机时间戳
		String Remark1 = request.getParameter("Remark1"); // 自定义备注
		String Remark2 = request.getParameter("Remark2"); // 自定义备注
		String Remark3 = request.getParameter("Remark3"); // 自定义备注
		String ResultCode = request.getParameter("ResultCode"); // 返回码（88表示成功）
		System.out.println("返回码======="+ResultCode);
		String Message = request.getParameter("Message"); // 返回信息
		System.out.println("返回信息======="+Message);
		String ReturnTimes = request.getParameter("ReturnTimes"); // 返回次数
		System.out.println("返回次数======="+ReturnTimes);
		String SignInfo = request.getParameter("SignInfo"); // 签名信息
		System.out.println(SignInfo);
		
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(LoanJsonList))
        		.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
        		.append(StringUtils.trimToEmpty(Action))
        		.append(StringUtils.trimToEmpty(RandomTimeStamp))
        		.append(StringUtils.trimToEmpty(Remark1))
        		.append(StringUtils.trimToEmpty(Remark2))
        		.append(StringUtils.trimToEmpty(Remark3))
        		.append(StringUtils.trimToEmpty(ResultCode));
        String plainStr = buffer.toString();
        System.out.println("投标转账应答获取的返回参数拼接：=============="+plainStr);
        boolean flag = false;
        try {
     		flag = rsa.verifySignature(SignInfo, plainStr, RsaHelper.publicString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
        	UserTender userTender = userTenderService.findUserTenderByOrderno(OrderNo); // 根据投标订单号查询投标记录
        	if(ResultCode.equals("88")){ // 返回码88成功
        		System.out.println("开始更新数据！！！！！！！！！！！");
            	if(userTender.getTstatus()==0){ // 防止重复更新数据
                	// 更新标的设置
                	updateTenderItem(userTender.getTenderid(), Amount);
            		// 更新投资人用户账户表-冻结余额
            		updateInvestorAccount(userTender.getOutaccountid(), Amount);
            	}
        		// 更新投标记录
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); 	// 投标状态-待审核
        		userTender.setTendtime(new Date()); 								// 转账完成时间
        		userTender.setLoanno(LoanNo); 										// 乾多多流水号
        	}else{
        		userTender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL); 			// 投标状态-失败
        	}
    		userTenderService.updateByOrderNO(userTender);  	// 根据投标订单号更新投标记录
        	response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			try {
				// 后台通知结果后，将判断http响应码为200且页面上含有“SUCCESS”字符串方为通知成功
				response.getWriter().write("SUCCESS");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
	}
	
	/**
	 * 更新标的设置，已完成投标金额
	 * @param huifuParam
	 * @throws Exception 
	 */
	private void updateTenderItem(BigDecimal tid, String Amount) throws Exception {
		// 修改标的设置表
		TenderItem item = tenderItemService.findTenderItemById(tid);	// 获得标的设置详情
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
	private void updateInvestorAccount(BigDecimal outAccountId, String Amount) {
		UserAccount account = new UserAccount();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(outAccountId); 												// 根据baseid查询用户账户信息
		double balance = userAccount.getBalance();																						// 用户总资产不变
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), Double.valueOf(Amount)); 											// 用户可用余额 = 用户可用余额-投标金额
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
	
	@RequestMapping(value= "/userTenderReturnUrl")
	public void userTenderReturnUrl(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String ResultCode = request.getParameter("ResultCode");
		System.out.println("返回码="+ResultCode);
		String Message = URLDecoder.decode(request.getParameter("Message"), "UTF-8");
		request.setAttribute("Message", Message);
		request.getRequestDispatcher("/WEB-INF/MMMPages/UserTender/UserTender_Success.jsp").forward(request, response);
	}
}
