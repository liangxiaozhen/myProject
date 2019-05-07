//package com.ptpl.controller.moneymoremore;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.client.ClientProtocolException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.moneymoremore.util.Common;
//import com.moneymoremore.util.MMMParams;
//import com.moneymoremore.util.RsaHelper;
//import com.moneymoremore.util.SignUtils;
//import com.ptpl.controller.BaseController;
//import com.ptpl.model.AccInExRecord;
//import com.ptpl.model.DividedPayments;
//import com.ptpl.model.RepayMent;
//import com.ptpl.model.TenderItem;
//import com.ptpl.model.loanapp;
//import com.ptpl.service.AccInExRecordServiceI;
//import com.ptpl.service.DividedPaymentsServiceI;
//import com.ptpl.service.InterestExpenseRecordServiceI;
//import com.ptpl.service.RepayMentNormalInsertAccInExRecordI;
//import com.ptpl.service.RepayMentServiceI;
//import com.ptpl.service.TenderItemServiceI;
//import com.ptpl.service.UserFsAccountInfoServiceI;
//import com.ptpl.service.UserTenderServiceI;
//import com.ptpl.service.loanappServiceI;
//import com.ptpl.web.util.StringUtil;
///**
// * 
// * @ClassName: MMMRepayMentController 
// * @Package com.ptpl.controller.moneymoremore 
//* @Description: TODO(乾多多 还款 接口调用和回调处理Controller) 
//* @author cjm
//* @date 2017年2月25日 下午3:05:58 
//* @version V1.0
// */
//@Controller
//@RequestMapping(value = "/moneymoremore/repayMent")
//public class MMMRepayMentController extends BaseController{
//	
//	private static Log MMMRepayMentControllerLog = LogFactory.getLog(MMMRepayMentController.class);
//	
//	@Autowired
//	private UserTenderServiceI userTenderServiceI;
// 	@Autowired
//	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
// 	@Autowired
//	private InterestExpenseRecordServiceI interestExpenseRecordServiceI; 
// 	@Autowired
//	private RepayMentServiceI repayMentServiceI;
// 	@Autowired
//	private TenderItemServiceI tenderItemServiceI;
// 	@Autowired
//	private DividedPaymentsServiceI dividedPaymentsServiceI;
// 	@Autowired
//	private loanappServiceI loanappServiceI;
// 	@Autowired
//	private AccInExRecordServiceI accInExRecordServiceI;
//	@Autowired
//	private RepayMentNormalInsertAccInExRecordI  repaymentInsertAccInExRecordI;
//	 
//	@RequestMapping(value = "/doManualRepayMentTransfer",method = {RequestMethod.GET,RequestMethod.POST})
//	public void doManualRepayMentTransfer(HttpServletRequest request ,HttpServletResponse response){
// 		/**
//		 * 提交的LoanJsonList具体参数 最多有200笔转账
//		 * LoanOutMoneymoremore  付款人乾多多标识 必填
//		 * LoanInMoneymoremore  收款人乾多多标识 必填
//		 * OrderNo	网贷平台订单号 必填
//		 * BatchNo 网贷平台标号 必填
//		 * ExchangeBatchNo 流转标标号 选填
//		 * AdvanceBatchNo 垫资标号  选填
//		 * Amount 金额 必填
//		 * FullAmount 满标标额 选填
//		 * TransferName 用途 选填
//		 * Remark 备注 选填
//		 * SecondaryJsonList  二次分配列表 选填
//		 * 
//		 * 提交的SecondaryJsonList具体参数 最多10笔
// 		 * LoanInMoneymoremore  二次收款人乾多多标识  必填
//		 * Amount   二次分配金额  必填
//		 * TransferName  用途  选填
//		 * Remark  备注  选填
// 		 */
//		
////		String LoanOutMoneymoremore = ""; //付款人乾多多标识 
////		String LoanInMoneymoremore = ""; //收款人乾多多标识 必填
////		String OrderNo = "";//网贷平台订单号 必填
////		String BatchNo = "";//网贷平台标号 必填
////		String ExchangeBatchNo = "";//流转标标号 选填
////		String AdvanceBatchNo = "";//垫资标号  选填
////		String Amount  = "";//金额 必填
////		String FullAmount = "";//满标标额 选填
////		String TransferName = "";//用途 选填
////		String Remark = "";//备注 选填
////		
////		String LoanInMoneymoremore22 = "";//二次收款人乾多多标识  必填
////		String Amount22	="";//二次分配金额  必填
////		String TransferName22 = "";//二次收款人乾多多标识  必填
////		String Remark22 = "";//二次收款人乾多多标识  必填
//		
//		String LoanOutMoneymoremore = "m111973"; //付款人乾多多标识 
//		String LoanInMoneymoremore = "m111972"; //收款人乾多多标识 必填
//		String OrderNo = "20170227105900468827";//网贷平台订单号 必填
//		String BatchNo = "20170227105900468829";//网贷平台标号 必填
//		String ExchangeBatchNo = "";//流转标标号 选填
//		String AdvanceBatchNo = "";//垫资标号  选填
//		String Amount  = "1000.00";//金额 必填
//		String FullAmount = "";//满标标额 选填
//		String TransferName = "";//用途 选填
//		String Remark = "转账";//备注  选填
//		
//		String LoanInMoneymoremore22 = "m111869";//二次收款人乾多多标识  必填
//		String Amount22	="200.00";//二次分配金额  必填
//		String TransferName22 = "";//用途  选填
//		String Remark22 = "";//备注  选填
//		
//		StringBuffer buffer2 = new StringBuffer();
//		String str2 = "{\\\"LoanInMoneymoremore\\\":\\\""+LoanInMoneymoremore22+"\\\",\\\"Amount\\\":\\\""+Amount22+"\\\",\\\"TransferName\\\":\\\""+TransferName22+"\\\",\\\"Remark\\\":\\\""+Remark22+"\\\"}";
//		String SecondaryJsonList = "";//二次分配列表 选填
//		SecondaryJsonList = str2 ;
// 		StringBuffer buffer = new StringBuffer();
// 		buffer.append("[");
//		String str = 
//				"{\"LoanOutMoneymoremore\":\""+LoanOutMoneymoremore+"\",\"LoanInMoneymoremore\":\""+LoanInMoneymoremore+"\","+
//				"\"OrderNo\":\""+OrderNo+"\",\"BatchNo\":\""+BatchNo+"\",\"ExchangeBatchNo\":\""+ExchangeBatchNo+"\","+
//				"\"AdvanceBatchNo\":\""+AdvanceBatchNo+"\",\"Amount\":\""+Amount+"\",\"FullAmount\":\""+FullAmount+"\","+
//				"\"TransferName\":\""+TransferName+"\",\"Remark\":\""+Remark+"\",\"SecondaryJsonList\":\"["+SecondaryJsonList+"]\"}";
//		buffer.append(str);
////		buffer.append(",");
////		buffer.delete(buffer.length()-1, buffer.length());
//  		buffer.append("]");
//		String LoanJsonList = buffer.toString();
//		System.out.println(LoanJsonList);
//		String ArrivalTime  = "";//到账时间空.实时转账1.普通转账2.次日转账
//		String NeedAudit    = "1";//通过是否需要审核 空.需要审核1.自动通过
//		String Remark1		= "";//自定义备注
//		String Remark2		= "";//自定义备注
//		String Remark3		= "";//自定义备注
// 		ManualRepayMentTransfer(request, response, LoanJsonList, ArrivalTime, NeedAudit, Remark1, Remark2, Remark3);
//	}
//	
//	/**
//	 * 
//	* @Title: ManualRepayMentTransfer 
//	* @Description: TODO(还款 转账 手动提交方法) 
//	* @param @param request
//	* @param @param response
//	* @param @param LoanJsonList  转账列表
//	* @param @param ArrivalTime  到账时间
//	* @param @param NeedAudit  是否需要审核
//	* @param @param Remark1 	自定义备注
//	* @param @param Remark2		自定义备注
//	* @param @param Remark3  	自定义备注
//	* @return void    返回类型 
//	* @author cjm
//	* @throws  
//	 */
//	public static void ManualRepayMentTransfer(HttpServletRequest request ,HttpServletResponse response,
//			String LoanJsonList,String ArrivalTime,String NeedAudit,String Remark1,String Remark2,String Remark3){
// 		Assert.notNull(LoanJsonList, "'转账列表不允许为空'");
//   		String LoanJsonList2 				= Common.UrlEncoder(LoanJsonList, "utf-8");
//		//转账列表 必填  转账列表转换成的JSON对象列表，具体字段见下表参加签名的是原串，
//		//提交的时候要进行UrlEncode，编码为utf-8；一次最多有200笔转账
//		String PlatformMoneymoremore  = MMMParams.PlatformMoneymoremore; //平台乾多多标识 必填
//		String TransferAction  = "2"; //转账类型 1.投标 2.还款 3.其他
//		String Action  = "1"; //操作类型1.手动转账 2.自动转账
//		String TransferType  = "2"; //转账方式 1.桥连2.直连
////		String ArrivalTime  = "" ; //到账时间 空.实时转账1.普通转账 2.次日转账
////		String NeedAudit  = "" ; //通过是否需要审核   空.需要审核 1.自动通过
//		String DelayTransfer  = ""; //是否半自动批处理(暂不可用) 空.否1.是启用该功能的条件:1.直连2.付款人唯一3.公共账户余额不参与转账
//		String RandomTimeStamp  = ""; //随机时间戳 格式为2位随机数加yyyyMMddHHmmssSSS格式的当前时间；未启用防抵赖时必须为空
////		String Remark1  = "" ; //自定义备注
////		String Remark2  = "" ; //自定义备注
////		String Remark3  = "" ; //自定义备注
//		String ReturnURL  = StringUtil.getBasePath(request) + "/moneymoremore/repayMent/repayMentCallBackUrl.action"; //页面返回网址  手动转账必填
//		String NotifyURL  = StringUtil.getBasePath(request) + "/moneymoremore/repayMent/repayMentCallBack.action"; //后台通知网址 必填
//		String SignInfo  = ""; //签名信息  必填
//		/**
//		 *  LoanJsonList + PlatformMoneymoremore + TransferAction +
//		 *  Action + TransferType + ArrivalTime + NeedAudit + 
//		 *  DelayTransfer + RandomTimeStamp + Remark1 + 
//		 *  Remark2 + Remark3 + ReturnURL + NotifyURL
//		 */
//		
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(LoanJsonList));
//		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
//		buffer.append(StringUtils.trimToEmpty(TransferAction));
//		buffer.append(StringUtils.trimToEmpty(Action));
//		buffer.append(StringUtils.trimToEmpty(TransferType));
//		buffer.append(StringUtils.trimToEmpty(ArrivalTime));
//		buffer.append(StringUtils.trimToEmpty(NeedAudit));
//		buffer.append(StringUtils.trimToEmpty(DelayTransfer));
//		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
//		buffer.append(StringUtils.trimToEmpty(Remark1));
//		buffer.append(StringUtils.trimToEmpty(Remark2));
//		buffer.append(StringUtils.trimToEmpty(Remark3));
//		buffer.append(StringUtils.trimToEmpty(ReturnURL));
//		buffer.append(StringUtils.trimToEmpty(NotifyURL));
//	    String str = buffer.toString();
//	    System.out.println(str+"====拼接字符串=====");
//	    RsaHelper helper = RsaHelper.getInstance();
//	    SignInfo =  helper.signData(str, MMMParams.privateString);//加签
// 	    System.out.println("加密后的 SignInfo========"+SignInfo);
//	    
//	    System.out.println("===========LoanJsonList==提交参数======="+LoanJsonList2);
//	    System.out.println("===========PlatformMoneymoremore==提交参数======="+PlatformMoneymoremore);
//	    System.out.println("===========TransferAction==提交参数======="+TransferAction);
//	    System.out.println("===========Action==提交参数======="+Action);
//	    System.out.println("===========TransferType==提交参数======="+TransferType);
//	    System.out.println("===========ArrivalTime==提交参数======="+ArrivalTime);
//	    System.out.println("===========NeedAudit==提交参数======="+NeedAudit);
//	    System.out.println("===========DelayTransfer==提交参数======="+DelayTransfer);
//	    System.out.println("===========RandomTimeStamp==提交参数======="+RandomTimeStamp);
//	    System.out.println("===========Remark1==提交参数======="+Remark1);
//	    System.out.println("===========Remark2==提交参数======="+Remark2);
//	    System.out.println("===========Remark3==提交参数======="+Remark3);
//	    System.out.println("===========ReturnURL==提交参数======="+ReturnURL);
//	    System.out.println("===========NotifyURL==提交参数======="+NotifyURL);
//  		
//		request.setAttribute("repayMentAction", MMMParams.TRANSFERACTION);
//		request.setAttribute("LoanJsonList", LoanJsonList2);
//		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
//		request.setAttribute("TransferAction", TransferAction);
//		request.setAttribute("Action", Action);
//		request.setAttribute("TransferType", TransferType);
//		request.setAttribute("ArrivalTime", ArrivalTime);
//		request.setAttribute("NeedAudit", NeedAudit);
//		request.setAttribute("DelayTransfer", DelayTransfer);
//		request.setAttribute("RandomTimeStamp", RandomTimeStamp);
//		request.setAttribute("Remark1", Remark1);
//		request.setAttribute("Remark2", Remark2);
//		request.setAttribute("Remark3", Remark3);
//		request.setAttribute("ReturnURL", ReturnURL);
//		request.setAttribute("NotifyURL", NotifyURL);
//		request.setAttribute("SignInfo", SignInfo);
//		
// 		try {
//			request.getRequestDispatcher("/WEB-INF/MMMPages/RepayMent/repayMent.jsp").forward(request, response);
//		} catch (ServletException e) {
// 			e.printStackTrace();
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
// 		 
//  	}
//	
// 	/**
//	 * 
//	* @Title: AutoRepayMentTransfer 
//	* @Description: TODO(还款 转账 自动提交方法(后台提交)  调用此接口需要开通 （还款自动授权）) 
//	* @param @param request
//	* @param @param response
//	* @param @param LoanJsonList  转账列表
//	* @param @param ArrivalTime  到账时间
//	* @param @param NeedAudit  是否需要审核
//	* @param @param Remark1 	自定义备注
//	* @param @param Remark2		自定义备注
//	* @param @param Remark3  	自定义备注
//	* @return void    返回类型 
//	* @author cjm
//	* @throws  
//	 */
//	public static String AutoRepayMentTransfer(HttpServletRequest request ,HttpServletResponse response,
//			String LoanJsonList,String ArrivalTime,String NeedAudit,String Remark1,String Remark2,String Remark3){
//		String result = "";
//		Assert.notNull(LoanJsonList, "'转账列表不允许为空'");
//   		String LoanJsonList2 				= Common.UrlEncoder(LoanJsonList, "utf-8");//URL 加密
//		//转账列表 必填  转账列表转换成的JSON对象列表，具体字段见下表参加签名的是原串，
//		//提交的时候要进行UrlEncode，编码为utf-8；一次最多有200笔转账
//		String PlatformMoneymoremore  = MMMParams.PlatformMoneymoremore ; //平台乾多多标识 必填
//		String TransferAction  = "2" ; //转账类型 1.投标 2.还款 3.其他
//		String Action  = "2" ; //操作类型1.手动转账 2.自动转账
//		String TransferType  = "1" ; //转账方式 1.桥连2.直连
////		String ArrivalTime  = "" ; //到账时间 空.实时转账1.普通转账 2.次日转账
////		String NeedAudit  = "" ; //通过是否需要审核   空.需要审核 1.自动通过
//		String DelayTransfer  = "" ; //是否半自动批处理(暂不可用) 空.否1.是启用该功能的条件:1.直连2.付款人唯一3.公共账户余额不参与转账
//		String RandomTimeStamp  = "" ; //随机时间戳 格式为2位随机数加yyyyMMddHHmmssSSS格式的当前时间；未启用防抵赖时必须为空
////		String Remark1  = "" ; //自定义备注
////		String Remark2  = "" ; //自定义备注
////		String Remark3  = "" ; //自定义备注
//		String ReturnURL  = "" ; //页面返回网址  手动转账必填
//		String NotifyURL  = StringUtil.getBasePath(request) + "/moneymoremore/repayMent/repayMentCallBack.action" ; //后台通知网址 必填
//		String SignInfo  = "" ; //签名信息  必填
//		/**
//		 *  LoanJsonList + PlatformMoneymoremore + TransferAction +
//		 *  Action + TransferType + ArrivalTime + NeedAudit + 
//		 *  DelayTransfer + RandomTimeStamp + Remark1 + 
//		 *  Remark2 + Remark3 + ReturnURL + NotifyURL
//		 */
//		
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(LoanJsonList));
//		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
//		buffer.append(StringUtils.trimToEmpty(TransferAction));
//		buffer.append(StringUtils.trimToEmpty(Action));
//		buffer.append(StringUtils.trimToEmpty(TransferType));
//		buffer.append(StringUtils.trimToEmpty(ArrivalTime));
//		buffer.append(StringUtils.trimToEmpty(NeedAudit));
//		buffer.append(StringUtils.trimToEmpty(DelayTransfer));
//		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
//		buffer.append(StringUtils.trimToEmpty(Remark1));
//		buffer.append(StringUtils.trimToEmpty(Remark2));
//		buffer.append(StringUtils.trimToEmpty(Remark3));
//		buffer.append(StringUtils.trimToEmpty(ReturnURL));
//		buffer.append(StringUtils.trimToEmpty(NotifyURL));
//	    String str = buffer.toString();
//	    
//	    RsaHelper helper = RsaHelper.getInstance();
//	    SignInfo =  helper.signData(str, MMMParams.privateString);//RSA加签
//		
//	    Map<String,String> hashMap = new HashMap<String,String>();
//	    hashMap.put("repayMentAction", MMMParams.TRANSFERACTION);
//	    hashMap.put("LoanJsonList", LoanJsonList2);
//		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
//		hashMap.put("TransferAction", TransferAction);
//		hashMap.put("Action", Action);
//		hashMap.put("TransferType", TransferType);
//		hashMap.put("ArrivalTime", ArrivalTime);
//		hashMap.put("NeedAudit", NeedAudit);
//		hashMap.put("DelayTransfer", DelayTransfer);
//		hashMap.put("RandomTimeStamp", RandomTimeStamp);
//		hashMap.put("Remark1", Remark1);
//		hashMap.put("Remark2", Remark2);
//		hashMap.put("Remark3", Remark3);
//		hashMap.put("ReturnURL", ReturnURL);
//		hashMap.put("NotifyURL", NotifyURL);
//		hashMap.put("SignInfo", SignInfo);
// 		try {
//			result = SignUtils.doPost(hashMap, MMMParams.TRANSFERACTION);//HttpClient 提交
//		} catch (ClientProtocolException e) {
// 			e.printStackTrace();
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//		return result;
//  	}
//	  
//
//	/**
//	 * 
//	* @Title: repayMentCallBack 
//	* @Description: TODO(乾多多 还款转账接口回调地址) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/repayMentCallBack",method = {RequestMethod.GET,RequestMethod.POST})
//	synchronized public String repayMentCallBack(HttpServletRequest request,HttpServletResponse response){
//		String LoanJsonList  = request.getParameter("LoanJsonList");//转账列表
//		String LoanJsonList2 = "";
//		if(StringUtil.isNotEmpty(LoanJsonList)){
// 			 LoanJsonList2 = Common.UrlDecoder(LoanJsonList, "utf-8");
//		}
//		String PlatformMoneymoremore  = request.getParameter("PlatformMoneymoremore");//平台乾多多标识
//		String Action  = request.getParameter("Action");//操作类型 为空表示转账1表示通过2表示退回3表示同意二次分配4表示不同意二次分配
//		String RandomTimeStamp  = request.getParameter("RandomTimeStamp");//随机时间戳
//		String Remark1  = request.getParameter("Remark1");//自定义备注
//		String Remark2  = request.getParameter("Remark2");//自定义备注
//		String Remark3  = request.getParameter("Remark3");//自定义备注
//		String ResultCode  = request.getParameter("ResultCode");//返回码
//		String Message  = request.getParameter("Message");//返回信息
//		String ReturnTimes  = request.getParameter("ReturnTimes");//转账列表
//		String SignInfo  = request.getParameter("SignInfo");//签名信息
//	 
//		System.out.println("=========LoanJsonList==========" +LoanJsonList);
//		System.out.println("=========PlatformMoneymoremore==========" +PlatformMoneymoremore);
//		System.out.println("=========Action==========" +Action);
//		System.out.println("=========RandomTimeStamp==========" +RandomTimeStamp);
//		System.out.println("=========Remark1==========" +Remark1);
//		System.out.println("=========Remark2==========" +Remark2);
//		System.out.println("=========Remark3==========" +Remark3);
//		System.out.println("=========ResultCode==========" +ResultCode);
//		System.out.println("=========Message==========" +Message);
//		System.out.println("=========ReturnTimes==========" +ReturnTimes);
//		System.out.println("=========SignInfo==========" +SignInfo);
// 		
//		/*LoanJsonList + PlatformMoneymoremore + 
//		Action + RandomTimeStamp + Remark1 +
//		Remark2 + Remark3 + ResultCode*/
//		
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(LoanJsonList2));
//		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
//		buffer.append(StringUtils.trimToEmpty(Action));
//		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
//		buffer.append(StringUtils.trimToEmpty(Remark1));
//		buffer.append(StringUtils.trimToEmpty(Remark2));
//		buffer.append(StringUtils.trimToEmpty(Remark3));
//		buffer.append(StringUtils.trimToEmpty(ResultCode));
// 		String str = buffer.toString();
// 		
// 		RsaHelper helper = RsaHelper.getInstance();
// 		boolean flag = helper.verifySignature(SignInfo, str, MMMParams.publicString);
//  		if(flag){
// 			System.out.println(flag +"=repayMentCallBackrepayMentCallBack=");
// 			if(ResultCode.equals("88")){
// 				if(Action != null && Action.equals("1")){
//  					String LoanOutMoneymoremoreStr = "";//付款人乾多多标识
//					String LoanInMoneymoremoreStr = "";//收款人乾多多标识
//					String LoanNoStr = "";//乾多多流水号
//					String OrderNoStr = "";//网贷平台订单号  还款流水号
//					String BatchNoStr = "";//网贷平台标号
//					String ExchangeBatchNoStr = "";//流转标标号
//					String AdvanceBatchNoStr = "";//垫资标号
//					String AmountStr = "";//金额
//					String TransferNameStr = "";//用途
//					String RemarkStr = "";//备注  投标订单号
//					String SecondaryJsonListStr2 = "";//二次分配列表
//					
//					String LoanInMoneymoremoreStr3 = "";//二次收款人乾多多标识
//					String AmountStr3 = "";//二次分配金额
//					String TransferNameStr3 = "";//用途
//					String RemarkStr3 = "";//备注 还款流水号
//					RepayMent repayMent = null;
//					RepayMent RepayMentJoin = null;
// 					if(StringUtil.isNotEmpty(LoanJsonList2)){
// 						JSONArray jsonArray2 = JSONArray.parseArray(LoanJsonList2);
//						for(int k = 0 ; k < jsonArray2.size() ; k++){
//							JSONObject jsonObject2 = JSONObject.parseObject(jsonArray2.get(k).toString());
//							LoanOutMoneymoremoreStr = jsonObject2.getString("LoanOutMoneymoremore");//付款人乾多多标识
//							LoanInMoneymoremoreStr = jsonObject2.getString("LoanInMoneymoremore");//收款人乾多多标识
//							LoanNoStr = jsonObject2.getString("LoanNo");//乾多多流水号
//							OrderNoStr = jsonObject2.getString("OrderNo");//网贷平台订单号  还款流水号
//							BatchNoStr = jsonObject2.getString("BatchNo");//网贷平台标号
//							ExchangeBatchNoStr = jsonObject2.getString("ExchangeBatchNo");//流转标标号
//							AdvanceBatchNoStr = jsonObject2.getString("AdvanceBatchNo");//垫资标号
//							AmountStr = jsonObject2.getString("Amount");//金额
//							TransferNameStr = jsonObject2.getString("TransferName");//金额
//							RemarkStr = jsonObject2.getString("Remark");//备注  投标订单号
//							SecondaryJsonListStr2 = jsonObject2.getString("SecondaryJsonList");//二次分配列表 投资人收支记录在后头应答地址进行处理
//							if(StringUtil.isNotEmpty(OrderNoStr)){
//								repayMent = repayMentServiceI.findRepayMentByRorderno(OrderNoStr);
//							}
//							
//							if(repayMent != null){
//								if(repayMent.getRepaystatus().equals((short)2)
//										|| repayMent.getRepaystatus().equals((short)3)){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//									//添加收支记录
//		 				  			 List<AccInExRecord> accInExRecord = accInExRecordServiceI.findAccInExRecordByBorderno(OrderNoStr);
//		  				  			 if(!(accInExRecord.size() > 0 )){
//		  				  				repaymentInsertAccInExRecordI.InsertInAccountByInterestexpenseAccInExRecord(repayMent);//投资人利息管理费收支记录
//		  				  				
//		  				  				repaymentInsertAccInExRecordI.InsertOutAccountByRamountAccInExRecord(repayMent);//借款人本金收支记录
//		   				  				repaymentInsertAccInExRecordI.InsertInAccountByRamountAccInExRecord(repayMent);//投资人本金收支记录
//		   				  				
//		   				  				repaymentInsertAccInExRecordI.InsertOutAccountByRinterestAccInExRecord(repayMent);//借款人本金利息收支记录
//		   				  				repaymentInsertAccInExRecordI.InsertInAccountByRinterestAccInExRecord(repayMent);//投资人本金利息收支记录
//		   				  				
//		 	  				  			repaymentInsertAccInExRecordI.InsertOutAccountByRvoucherAccInExRecord(repayMent);//借款人类现金收支记录
//		 	  				  			repaymentInsertAccInExRecordI.InsertInAccountByRvoucherAccInExRecord(repayMent);//投资人类现金收支记录
//		 	  				  			
//		 		  				  		repaymentInsertAccInExRecordI.InsertOutAccountByRvoucherintAccInExRecord(repayMent);//借款人类现金利息收支记录
//		 			  				  	repaymentInsertAccInExRecordI.InsertInAccountByRvoucherintAccInExRecord(repayMent);//投资人类现金利息收支记录
//		 			  				  	
//		 			  				  	//平台客服审核转账成功后添加记录 
////		 				  				repaymentInsertAccInExRecordI.InsertInAccountByRlvoucherintAccInExRecord(repayMent);//投资人假现金的利息收支记录
////		 					  			repaymentInsertAccInExRecordI.InsertInAccountByRintfeeAccInExRecord(repayMent);//投资人加息劵利息收支记录
//		 					  			
//		 						  		repaymentInsertAccInExRecordI.InsertInAccountByDisablevoucherAccInExRecord(repayMent);//投资人失效类现金收支记录
//		 							  	repaymentInsertAccInExRecordI.InsertInAccountByDisablevoucherintAccInExRecord(repayMent);//投资人失效类现金利息收支记录
//		   				  			 }
//								}else{
//									RepayMentJoin = repayMentServiceI.findJoinCheckRepayMentByRorderno(OrderNoStr);//查询是否是提前还款
// 									if(RepayMentJoin != null){
// 										repayMent.setLoanno(LoanNoStr);//乾多多还款流水号
//										repayMent.setRepaystatus((short)3);//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中)
//										repayMentServiceI.updateById(repayMent);
//										
//										//添加收支记录
//			 				  			 List<AccInExRecord> accInExRecord = accInExRecordServiceI.findAccInExRecordByBorderno(OrderNoStr);
//			  				  			 if(!(accInExRecord.size() > 0 )){
//			  				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByInterestexpenseAccInExRecord(RepayMentJoin);//投资人利息管理费收支记录
//			  				  				
//			  				  				repaymentInsertAccInExRecordI.InsertAheadOutAccountByRamountAccInExRecord(RepayMentJoin);//借款人本金收支记录
//			   				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByRamountAccInExRecord(RepayMentJoin);//投资人本金收支记录
//			   				  				
//			   				  				repaymentInsertAccInExRecordI.InsertAheadOutAccountByRinterestAccInExRecord(RepayMentJoin);//借款人本金利息收支记录
//			   				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByRinterestAccInExRecord(RepayMentJoin);//投资人本金利息收支记录
//			   				  				
//			 	  				  			repaymentInsertAccInExRecordI.InsertAheadOutAccountByRvoucherAccInExRecord(RepayMentJoin);//借款人类现金收支记录
//			 	  				  			repaymentInsertAccInExRecordI.InsertAheadInAccountByRvoucherAccInExRecord(RepayMentJoin);//投资人类现金收支记录
//			 	  				  			
//			 		  				  		repaymentInsertAccInExRecordI.InsertAheadOutAccountByRvoucherintAccInExRecord(RepayMentJoin);//借款人类现金利息收支记录
//			 			  				  	repaymentInsertAccInExRecordI.InsertAheadInAccountByRvoucherintAccInExRecord(RepayMentJoin);//投资人类现金利息收支记录
//			 			  				  	
//			 			  				  	//平台客服审核转账成功后添加记录
////			 				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByRlvoucherintAccInExRecord(RepayMentJoin);//投资人假现金的利息收支记录
////			 					  			repaymentInsertAccInExRecordI.InsertAheadInAccountByRintfeeAccInExRecord(RepayMentJoin);//投资人加息劵利息收支记录
//			 					  			
//			 						  		repaymentInsertAccInExRecordI.InsertAheadInAccountByDisablevoucherAccInExRecord(RepayMentJoin);//投资人失效类现金收支记录
//			 							  	repaymentInsertAccInExRecordI.InsertAheadInAccountByDisablevoucherintAccInExRecord(RepayMentJoin);//投资人失效类现金利息收支记录
//			   				  			 }
//			  				  			 
//									}else{
//										repayMent.setLoanno(LoanNoStr);//乾多多还款流水号
//										repayMent.setRepaystatus((short)2);//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中)
//										repayMentServiceI.updateById(repayMent);
//										
//										//添加收支记录
//			 				  			 List<AccInExRecord> accInExRecord = accInExRecordServiceI.findAccInExRecordByBorderno(OrderNoStr);
//			  				  			 if(!(accInExRecord.size() > 0 )){
//			  				  				repaymentInsertAccInExRecordI.InsertInAccountByInterestexpenseAccInExRecord(repayMent);//投资人利息管理费收支记录
//			  				  				
//			  				  				repaymentInsertAccInExRecordI.InsertOutAccountByRamountAccInExRecord(repayMent);//借款人本金收支记录
//			   				  				repaymentInsertAccInExRecordI.InsertInAccountByRamountAccInExRecord(repayMent);//投资人本金收支记录
//			   				  				
//			   				  				repaymentInsertAccInExRecordI.InsertOutAccountByRinterestAccInExRecord(repayMent);//借款人本金利息收支记录
//			   				  				repaymentInsertAccInExRecordI.InsertInAccountByRinterestAccInExRecord(repayMent);//投资人本金利息收支记录
//			   				  				
//			 	  				  			repaymentInsertAccInExRecordI.InsertOutAccountByRvoucherAccInExRecord(repayMent);//借款人类现金收支记录
//			 	  				  			repaymentInsertAccInExRecordI.InsertInAccountByRvoucherAccInExRecord(repayMent);//投资人类现金收支记录
//			 	  				  			
//			 		  				  		repaymentInsertAccInExRecordI.InsertOutAccountByRvoucherintAccInExRecord(repayMent);//借款人类现金利息收支记录
//			 			  				  	repaymentInsertAccInExRecordI.InsertInAccountByRvoucherintAccInExRecord(repayMent);//投资人类现金利息收支记录
//			 			  				  	
//			 			  				  	//平台客服审核转账成功后添加记录
////			 				  				repaymentInsertAccInExRecordI.InsertInAccountByRlvoucherintAccInExRecord(repayMent);//投资人假现金的利息收支记录
////			 					  			repaymentInsertAccInExRecordI.InsertInAccountByRintfeeAccInExRecord(repayMent);//投资人加息劵利息收支记录
//			 					  			
//			 						  		repaymentInsertAccInExRecordI.InsertInAccountByDisablevoucherAccInExRecord(repayMent);//投资人失效类现金收支记录
//			 							  	repaymentInsertAccInExRecordI.InsertInAccountByDisablevoucherintAccInExRecord(repayMent);//投资人失效类现金利息收支记录
//			   				  			 }
//									}
// 								}
//							}
// 						} 
//						
//						if(repayMent != null){
//								Map<String,Object> hashMap = new HashMap<>();
//								hashMap.put("tenderid", repayMent.getTenderid());
//								hashMap.put("periods", repayMent.getPeriods());
//		 						DividedPayments dividedPayment = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap);
//		 						
//		 						TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
//								
//		 	 					Map<String,Object> maps = new HashMap<String,Object>();
//			 		 			maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//			 					maps.put("tenderid", repayMent.getTenderid());//标号ID
//			 					maps.put("periods",repayMent.getPeriods());//还款期数（第几期）
//			 					List<RepayMent> repayMentStr = repayMentServiceI.findListRepayMentByConditions(maps);
//			 					
//			 					boolean repayMentsFlag 	   = true;//是否全部还款 
//			 					boolean repayMentsF        = false;//是否部分还款 
//			 					 
//			 					int count = 0;
//			 					int errorCount = 0;
//			 					for(RepayMent repayMentStr3 : repayMentStr){
//			 		 				if(repayMentStr3.getRepaystatus().equals((short)2)
//			 								|| repayMentStr3.getRepaystatus().equals((short)3)){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//			 							count ++;
//			 						}else{
//			 							errorCount ++;
//			 						}
//			 						
//			 						if(count > 0 &&  errorCount > 0){
//			 							break;//跳出循环 本次还款是部分还款
//			 						}
//			 					}
//			 					
//			 					if(count > 0 &&  errorCount > 0){
//			 						repayMentsF = true;
//			 					}
//			 					
//			 					if(repayMentsF){
//			 						dividedPayment.setIscomplete((short)3); //是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0)
//			 						dividedPaymentsServiceI.updateById(dividedPayment);
//			 					}
//			 					
//			 					for(RepayMent repayMentStr4 : repayMentStr){
//			 		 				if(repayMentStr4.getRepaystatus().equals((short)4)
//			 								|| repayMentStr4.getRepaystatus().equals((short)1)){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//			 							repayMentsFlag = false;//没有完成还款
//			 							break;
//			 						}
//			 					}
//			 					
//			 					if(repayMentsFlag){//本期全部还款成功
//			 						dividedPayment.setIscomplete((short)1); //是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0)
//			 						dividedPaymentsServiceI.updateById(dividedPayment);
//			 						
//			 						DividedPayments dividedPayment23 = new DividedPayments();
//			 						dividedPayment23.setTenderid(dividedPayment.getTenderid());
//			 						List<DividedPayments> dividedPayments23 = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment23);
//			 						
//			 						boolean paymentBoolean = true;//标的期数全部还款成功
//			 						for(DividedPayments dividedPayment2 : dividedPayments23){
//			 							if(dividedPayment2.getIscomplete().equals((short)2)
//			 									||dividedPayment2.getIscomplete().equals((short)0)){
//			 								//是否还款完成0没有完成还款 1已完成还款 2处理中 3 部分还款 (标的截标时生成时默认0)
//			 								paymentBoolean = false;
//			 								break;
//			 							}
//			 						}
//			 						
//			 						if(paymentBoolean){
//			 							loanapp loanapp2 = null;
//			 							if(tenderItem.getLoanappid() != null){
//			 								loanapp2 = loanappServiceI.selectByPrimaryKey(tenderItem.getLoanappid());
//			 							}
//			 							
//			 							if(loanapp2 != null){
//			 								loanapp2.setAppstatus((short)7);//借款申请状态 0审核中 1成功 2失败 3投标中 4流标 5还款中 6已发布 7 已还款
//			 								loanappServiceI.updateByPrimaryKeySelective(loanapp2);
//			 							}
//			 						}
//			 					}
//			  				}
//						}
//						 
//						
// 					}//LoanJsonList2 end
// 					
// 				}
//  			
// 			response.setContentType("text/plain;charset=utf-8");
// 			response.setCharacterEncoding("utf-8");
// 			try {
//				response.getWriter().write("SUCCESS");
//				response.getWriter().flush();
//				response.getWriter().close();
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 		}
//		return null;
//	}
//	
//	
//	
//	/**
//	 * 
//	* @Title: repayMentCallBackUrl 
//	* @Description: TODO(乾多多 还款转账接口页面回调地址) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/repayMentCallBackUrl",method = {RequestMethod.GET,RequestMethod.POST})
//	public String repayMentCallBackUrl(HttpServletRequest request,HttpServletResponse response){
//		String LoanJsonList  = request.getParameter("LoanJsonList");//转账列表
//		String LoanJsonList2 = "";
//		if(StringUtil.isNotEmpty(LoanJsonList)){
// 			 LoanJsonList2 = Common.UrlDecoder(LoanJsonList, "utf-8");
//		}
// 		String PlatformMoneymoremore  = request.getParameter("PlatformMoneymoremore");//平台乾多多标识
//		String Action  = request.getParameter("Action");//操作类型 为空表示转账1表示通过2表示退回3表示同意二次分配4表示不同意二次分配
//		String RandomTimeStamp  = request.getParameter("RandomTimeStamp");//随机时间戳
//		String Remark1  = request.getParameter("Remark1");//自定义备注
//		String Remark2  = request.getParameter("Remark2");//自定义备注
//		String Remark3  = request.getParameter("Remark3");//自定义备注
//		String ResultCode  = request.getParameter("ResultCode");//返回码
//		String Message  = request.getParameter("Message");//返回信息
//		String ReturnTimes  = request.getParameter("ReturnTimes");//转账列表
//		String SignInfo  = request.getParameter("SignInfo");//签名信息
//	 
//		System.out.println("=========LoanJsonList==========" +LoanJsonList);
//		System.out.println("=========PlatformMoneymoremore==========" +PlatformMoneymoremore);
//		System.out.println("=========Action==========" +Action);
//		System.out.println("=========RandomTimeStamp==========" +RandomTimeStamp);
//		System.out.println("=========Remark1==========" +Remark1);
//		System.out.println("=========Remark2==========" +Remark2);
//		System.out.println("=========Remark3==========" +Remark3);
//		System.out.println("=========ResultCode==========" +ResultCode);
//		System.out.println("=========Message==========" +Message);
//		System.out.println("=========ReturnTimes==========" +ReturnTimes);
//		System.out.println("=========SignInfo==========" +SignInfo);
//		
//		/*LoanJsonList + PlatformMoneymoremore + 
//		Action + RandomTimeStamp + Remark1 +
//		Remark2 + Remark3 + ResultCode*/
//		
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(LoanJsonList2));
//		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
//		buffer.append(StringUtils.trimToEmpty(Action));
//		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
//		buffer.append(StringUtils.trimToEmpty(Remark1));
//		buffer.append(StringUtils.trimToEmpty(Remark2));
//		buffer.append(StringUtils.trimToEmpty(Remark3));
//		buffer.append(StringUtils.trimToEmpty(ResultCode));
// 		String str = buffer.toString();
// 		
// 		RsaHelper helper = RsaHelper.getInstance();
// 		boolean flag = helper.verifySignature(SignInfo, str, MMMParams.publicString);
// 		if(flag){
// 			System.out.println(flag+"======repayMentCallBackUrlrepayMentCallBackUrl====");
//  			 
// 			
// 		}
//		return null;
//	}
//}
