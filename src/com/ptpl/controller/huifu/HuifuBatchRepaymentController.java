//package com.ptpl.controller.huifu;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.huifu.util.SignUtils;
//import com.huifu.util.httpClient.HttpClientHandler;
//import com.ptpl.controller.BaseController;
//import com.ptpl.mapper.RepayMentMapper;
//import com.ptpl.model.AccInExRecord;
//import com.ptpl.model.DividedPayments;
//import com.ptpl.model.InterestExpenseRecord;
//import com.ptpl.model.RepayMent;
//import com.ptpl.model.TenderItem;
//import com.ptpl.model.UserFsAccountInfo;
//import com.ptpl.model.UserTender;
//import com.ptpl.model.loanapp;
//import com.ptpl.service.AccInExRecordServiceI;
//import com.ptpl.service.DividedPaymentsServiceI;
//import com.ptpl.service.InterestExpenseRecordServiceI;
//import com.ptpl.service.InterestExpenseServiceI;
//import com.ptpl.service.RepayMentNormalInsertAccInExRecordI;
//import com.ptpl.service.RepayMentServiceI;
//import com.ptpl.service.TenderItemServiceI;
//import com.ptpl.service.UserAccountSafeInfoServiceI;
//import com.ptpl.service.UserFsAccountInfoServiceI;
//import com.ptpl.service.UserGradeServiceI;
//import com.ptpl.service.UserTenderServiceI;
//import com.ptpl.service.loanappServiceI;
//import com.ptpl.web.util.HuifuParams;
//import com.ptpl.web.util.MD5;
//import com.ptpl.web.util.StringUtil;
///**
// * 
//* @ClassName: HuifuBatchRepaymentController 
//* @Package com.ptpl.controller.huifu 
//* @Description: TODO(汇付天下 批量还款类) 
//* @author cjm
//* @date 2016年10月12日 上午9:40:44 
//* @version V1.0
// */
//@RequestMapping("/huifu/BatchRepayment")
//@Controller
//public class HuifuBatchRepaymentController extends BaseController{
//	private static Log huifuBatchLog = LogFactory.getLog(HuifuBatchRepaymentController.class);
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
// 	public  String doHuifuBatchRepayment(HttpServletRequest request){
//		HuifuParams huifuParams = new HuifuParams();
//		huifuParams.setVersion("20");//版本号 Version 必须
//		huifuParams.setCmdId("BatchRepayment");//消息类型 CmdId 变长 String 必须 此 处 为 BatchRepayment
////		huifuParams.setMerCustId("6000060004166478");//商户客户号 MerCustId 必须  
//		huifuParams.setOutCustId("6000060005590060");//出账客户号 OutCustId 必须   
//		huifuParams.setProId("gjbd2016101112");//项目 ID ProId 可选 若 4.3.5 主动投标/4.3.6 自动投标的借款人信息 BorrowerDetails 字段的项目 ID ProId 有值，则必填，否则为可选
//		huifuParams.setOutAcctId("MDT000001");//出账客户号  出账子账户 OutAcctId 可选 出款客户为商户时此字段必款
//		huifuParams.setBatchId(StringUtil.getNo());//还款批次号 BatchId 变长 20 位 必须 由商户生成，在商户下唯一，打印此字段用来结束异步消息接收
//		huifuParams.setMerOrdDate(StringUtil.formatDate(new Date(), "yyyyMMdd"));//商户还款订单日期 MerOrdDate 定长 8 位 必须 批量还款订单日期
//		huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/BatchRepayment/BatchRepaymentCallBack.action");//商户后台应答地址 BgRetUrl  必须
////		huifuParams.setMerPriv("");//商户私有域 MerPriv 可选
////		huifuParams.setReqExt("");//入参扩展域 ReqExt 可选
////		huifuParams.setOrdId(StringUtil.getNo());//还款订单号 OrdId 必须 InDetails 二级参数由商户的系统生成，必须保证唯一，请使用纯数字
////		huifuParams.setSubOrdId("20161010170110155958");//原投标订单号 SubOrdId 必须
////		huifuParams.setInCustId("6000060005590319");//入账客户号 InCustId 必须 InDetails 二级参数，入账客户号，由汇付生成，用户的唯一性标识
////		huifuParams.setInAcctId("MDT000001");//入账子账号 InAcctId 可选 InDetails 二级参数，用户在汇付的虚拟资金账户号，入款账户为商户时此字段必填
////		huifuParams.setTransAmt("1.00");//交易金额 TransAmt 必须 InDetails 二级参数，泛指交易金额，如充值、支付、
////		huifuParams.setFeeObjFlag("I");//手续费收取对象标志 I/O  FeeObjFlag 可选 InDetails 二级参数， I--向入款客户号 InCustId 收取O--向出款客户号 OutCustId 收取扣款手续费 Fee
////		huifuParams.setFee("0.10");//收取扣款手续费 Fee 可选 InDetails 二级参数，放款或扣款的手续费，此字段为空时 FeeObjFlag，DivDetails 可不填分账账户串 DivDetails
////		huifuParams.setDivCustId("6000060004166478");//分账商户号 DivCustId 必须 DivDetails 参数下的二级参数分账商户号
////		huifuParams.setDivAcctId("MDT000001");//分账账户号 DivAcctId 变长 String 必须 DivDetails 参数下的二级参数分账客户号
////		huifuParams.setDivAmt("0.10");//分账金额 DivAmt   必须 DivDetails 参数下的二级参数分账金额，保留两位小数
////		huifuParams.setDivDetails("");//分账账户串 DivDetails
// 		//huifuParams.setDzBorrCustId("");//被垫资人客户号 DzBorrCustId 可选 InDetails 二级参数当为垫资还款此参数必款，商户指明为哪个用户垫资，其它还款时为空
//  		StringBuffer buffer = new StringBuffer();
//		buffer.append("{\"InDetails\":[");
//		Map<String,Object> condition = new HashMap<String,Object>();
//		condition.put("tenderid", new BigDecimal(336)); 
//		List<UserTender> userTenders = userTenderServiceI.findUserTenderRecord(condition);
//		for(UserTender userTender : userTenders){
//			UserFsAccountInfo outaccountid = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(userTender.getOutaccountid());//投资人
//			String str =  "{\"InCustId\":\""+outaccountid.getUsrcustid()+"\",\"InAcctId\":\"MDT000001\","+
//						  "\"OrdId\":\""+StringUtil.getNo()+"\",\"SubOrdId\":\""+userTender.getOrderno()+"\","+
//						  "\"FeeObjFlag\":\"I\",\"TransAmt\":\"1.00\",\"Fee\":\"1.00\","+
//						  "\"DivDetails\":[{\"DivCustId\":\"6000060004166478\","+
//						  "\"DivAcctId\":\"MDT000001\",\"DivAmt\":\"1.00\"}]}";
//			buffer.append(str);
//			buffer.append(",");
//		}
//		buffer.delete(buffer.length()-1, buffer.length());
//		buffer.append("]}");
//		String InDetailsStr = buffer.toString();
//		huifuParams.setInDetails(InDetailsStr);//还款账户串 InDetails 变长 String 必须
//		String result = doHuifuBatchRepayment(huifuParams, request);
//		System.out.println(result);
//		return result;
//		
//	}
//	/**
//	 * 
//	* @Title: doBatchRepayment 
//	* @Description: TODO(后台提交 批量还款) 
//	* @param @param huifuParams  参数说明 (批量还款 参数设置)
//	* @return void    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	public  String doHuifuBatchRepayment(HuifuParams huifuParams,HttpServletRequest request){
// 		Assert.notNull(huifuParams,"huifuParams 不能为null");
// 		String result = "";
//   		/*Version+CmdId + MerCustId + OutCustId +OutAcctId+ BatchId+ MerOrdDate 
//		 + InDetails+ BgRetUrl+ MerPriv + ReqExt+ ProId*/
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getBatchId()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerOrdDate()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getInDetails()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
//		buffer.append(StringUtils.trimToEmpty(huifuParams.getProId()));
//		String str = buffer.toString();
//		MD5 md5 = new MD5();
//		String str1 = md5.getMD5Info(str).toLowerCase();
//  		String ChkValue;
//  		try {
//			ChkValue = SignUtils.encryptByRSA(str1);
//			if(StringUtil.isNotEmpty(ChkValue)){
//				huifuParams.setChkValue(ChkValue);
//			}
//		} catch (Exception e) {
// 			e.printStackTrace();
//		}
//  		Map<String,String> hashMap = new HashMap<String,String>();
//  		hashMap.put("Version", huifuParams.getVersion());//版本号 Version 必须
//  		hashMap.put("CmdId", huifuParams.getCmdId());//消息类型 CmdId 变长 String 必须 此 处 为 BatchRepayment
//  		hashMap.put("MerCustId", huifuParams.getMerCustId());//商户客户号 MerCustId 必须  
//  		hashMap.put("OutCustId", huifuParams.getOutCustId());//出账客户号 OutCustId 必须   
//  		hashMap.put("OutAcctId", huifuParams.getOutAcctId());//出账客户号  出账子账户 OutAcctId 可选 出款客户为商户时此字段必款
//  		hashMap.put("BatchId", huifuParams.getBatchId());//还款批次号 BatchId 变长 20 位 必须 由商户生成，在商户下唯一，打印此字段用来结束异步消息接收
//  		hashMap.put("MerOrdDate", huifuParams.getMerOrdDate());//商户还款订单日期 MerOrdDate 定长 8 位 必须 批量还款订单日期
//  		hashMap.put("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址 BgRetUrl  必须
//  		hashMap.put("MerPriv", huifuParams.getMerPriv());//商户私有域 MerPriv 可选
//  		hashMap.put("ReqExt", huifuParams.getReqExt());//入参扩展域 ReqExt 可选
//  		hashMap.put("ProId", huifuParams.getProId());//项目 ID ProId 可选 若 4.3.5 主动投标/4.3.6 自动投标的借款人信息 BorrowerDetails 字段的项目 ID ProId 有值，则必填，否则为可选
//  		hashMap.put("InDetails", huifuParams.getInDetails());//还款账户串 InDetails 变长 String 必须
//  		hashMap.put("ChkValue", huifuParams.getChkValue());//签名 ChkValue 定长 256 位的 String 必须 加签验签的时候，商户需告知汇付其系统编码，汇付在验签的时候进行相应的编码转换验签 
//  		hashMap.put("CharSet", huifuParams.getCharSet());
//   		try {
//			  result = SignUtils.doPost(hashMap);
// 		} catch (ClientProtocolException e) {
// 			e.printStackTrace();
//		} catch (IOException e) {
// 			e.printStackTrace();
//		}
//   		return result;
//	}
//
//	/**
//	 * 
//	* @Title: BatchAheadRepaymentCallBack 
//	* @Description: TODO(提前还款  批量还款 回调地址) 
//	* @param @param request
//	* @param @param response
//	* @param @param huifuParams  参数说明 
//	* @return void    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/BatchAheadRepaymentCallBack")
//	public synchronized void BatchAheadRepaymentCallBack(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
//		 System.out.println("===========getCmdId==================="+huifuParams.getCmdId());
//		 System.out.println("===========getRespCode==================="+huifuParams.getRespCode());
//		 System.out.println("===========getRespDesc==================="+huifuParams.getRespDesc());
//		 System.out.println("===========getMerCustId==================="+huifuParams.getMerCustId());
//		 System.out.println("===========getOutCustId==================="+huifuParams.getOutCustId());
//		 System.out.println("===========getOutAcctId==================="+huifuParams.getOutAcctId());
//		 System.out.println("===========getBatchId==================="+huifuParams.getBatchId());
//		 System.out.println("===========getMerOrdDate==================="+huifuParams.getMerOrdDate());
//		 System.out.println("===========getSucNum==================="+huifuParams.getSucNum());
//		 System.out.println("===========getFailNum==================="+huifuParams.getFailNum());
//		 System.out.println("===========getErrMsg==================="+huifuParams.getErrMsg());
//		 System.out.println("===========getItemCode==================="+huifuParams.getItemCode());
//		 System.out.println("===========getBgRetUrl==================="+huifuParams.getBgRetUrl());
//		 System.out.println("===========getMerPriv==================="+huifuParams.getMerPriv());
//		 System.out.println("===========getProId==================="+huifuParams.getProId());
//		 System.out.println("===========getRespExt==================="+huifuParams.getRespExt());
//		 System.out.println("===========getChkValue==================="+huifuParams.getChkValue());
//		 /*CmdId + RespCode + MerCustId + OutCustId +OutAcctId + BatchId + MerOrdDate +
//			BgRetUrl+ MerPriv + SucNum + FailNum +ErrMsg + ProId+ ReqExt*/
//		 StringBuffer buffer = new StringBuffer();
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getBatchId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerOrdDate()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getSucNum()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getFailNum()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getErrMsg()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getProId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
//		 String str = buffer.toString();
//		 boolean flag = false;
//		 try {
//			flag = SignUtils.verifyByRSA(str, huifuParams.getChkValue());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 
//		 if(flag){//验签成功
//			 if(huifuParams.getRespCode() != null && huifuParams.getRespCode().equals("000")){//批量还款成功才调用还款对账接口
//	 				String pageNum = "1";//页数
//					String pageSize = "500";//条数
//					int lastPageNum = 0;
//					String totalSize = getAheadRepayMentReconciliationQuery(huifuParams.getMerOrdDate(), huifuParams.getMerOrdDate(), pageNum, pageSize);
//					if(Integer.valueOf(totalSize) % Integer.valueOf(pageSize) == 0){
//						lastPageNum = Integer.valueOf(totalSize) / Integer.valueOf(pageSize);
//					}else{
//						lastPageNum = Integer.valueOf(totalSize) / Integer.valueOf(pageSize) + 1;
//					}
//	 				for(int i = 1;i<lastPageNum;i++){
//						pageNum  = String.valueOf(i+1);
//						getAheadRepayMentReconciliationQuery(huifuParams.getMerOrdDate(), huifuParams.getMerOrdDate(), pageNum, pageSize);
//					}
//	 				
//	 				try {
//	 					PrintWriter printWriter = response.getWriter();
//	 					printWriter.println("RECV_ORD_ID_".concat(huifuParams.getBatchId()));
//	 					if(StringUtil.isNotEmpty(huifuParams.getErrMsg())){//记录日志
//	 						huifuBatchLog.info("批量提前还款回调成功条数"+huifuParams.getSucNum());
//	 						huifuBatchLog.info("批量提前还款回调错误条数"+huifuParams.getFailNum());
//	 		 				huifuBatchLog.info("批量提前还款回调错误条数信息"+huifuParams.getErrMsg());
//	 					}
//	 				} catch (IOException e) {
//	 	 				e.printStackTrace();
//	 				}finally{
//	 					if(huifuParams.getProId() != null){ 
//	 						TenderItem tender = tenderItemServiceI.seltendbytno(huifuParams.getProId());//标的信息
//	 						if(tender != null && huifuParams.getMerPriv() != null){
//	  							Map<String,Object> maps = new HashMap<String,Object>();
//	 							maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//	 							maps.put("tenderid", tender.getId());//标号ID
//	 							maps.put("periods", new Short(huifuParams.getMerPriv()));//还款期数（第几期）
//	  							List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(maps);
//	 							boolean repayMentsFlag 	   = true;//是否全部还款 
//	 							boolean repayMentsF        = false;//是否部分还款 
//	 							/**
//	 							 * 还款数据拼接 调用汇付还款接口 返回000 后 更新借款人还款计划的还款状态为：2处理中，然后调用还款对账接口，如果本期全部还款完成，
//	 							 * 就修改借款人当期的还款计划为1已还款，如果有部分还款成功就更新为3部分还款
//	 							 */
//	 							int count = 0;
//	 							int errorCount = 0;
//	 							for(RepayMent repayMent : repayMents){
// 	 								if(repayMent.getRepaystatus().equals((short)2)
//	 										|| repayMent.getRepaystatus().equals((short)3)){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//	  									count ++;
//	   								}else{
//	   									errorCount ++;
//	   								}
//	 								
//	 								if(count > 0 &&  errorCount > 0){
//	 									break;//跳出循环 本次还款是部分还款
//	 								}
//	  							}
//	 							
//	 							if(count > 0 &&  errorCount > 0){
//	 								repayMentsF = true;
//	 							}
//	 							
//	 							if(repayMentsF){
//	 								Map<String,Object> hashMap = new HashMap<String,Object>();
//	 								hashMap.put("tenderid", tender.getId());
//	 								hashMap.put("periods", new Short(huifuParams.getMerPriv()));
//	  								DividedPayments dividedPayments = dividedPaymentsServiceI
//	  										.findDividedPaymentsByConditions(hashMap);
//	 								if(dividedPayments != null){
//	 									dividedPayments.setIscomplete((short)3);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款
//	 									dividedPaymentsServiceI.updateById(dividedPayments);
//	 								}
//	 							}
//	 							
//	 							for(RepayMent repayMent : repayMents){
//	 								 
//	  								if(!(repayMent.getRepaystatus().equals((short)2)
//	 										|| repayMent.getRepaystatus().equals((short)3))){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//	 									repayMentsFlag = false;//没有完成还款
//	 									break;
//	 								}
//	 							}
//	 							
//	 							if(repayMentsFlag){//本期全部还款成功
//	  								Map<String,Object> hashMap = new HashMap<String,Object>();
//	 								hashMap.put("tenderid", tender.getId());
//	 								hashMap.put("periods", new Short(huifuParams.getMerPriv()));
//	  								DividedPayments dividedPayments = dividedPaymentsServiceI
//	  										.findDividedPaymentsByConditions(hashMap);
//	 								if(dividedPayments != null){
//	 									dividedPayments.setIscomplete((short)1);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款
//	 									dividedPaymentsServiceI.updateById(dividedPayments);
//	 								}
//	 								DividedPayments dividedPayment = new DividedPayments();
//	 								dividedPayment.setTenderid(tender.getId());
//	 								List<DividedPayments> dividedPayments2 = dividedPaymentsServiceI.
//	 										findDividedPaymentss(dividedPayment);
//	 								boolean paymentBoolean = true;
//	 								for(DividedPayments dividedPayment2 : dividedPayments2){
//	 									if(dividedPayment2.getIscomplete().equals((short)2)
//	 											||dividedPayment2.getIscomplete().equals((short)0)){
//	 										//是否还款完成0没有完成还款 1已完成还款 2处理中 3 部分还款 (标的截标时生成时默认0)
//	 										paymentBoolean = false;
//	 										break;
//	 									}
//	 								}
//	 								if(paymentBoolean){
//	 									loanapp loanapp = null;
//	 									if(tender.getLoanappid() != null){
//	  										 loanapp = loanappServiceI.selectByPrimaryKey(tender.getLoanappid());
//	 									}
//	 									if(loanapp != null){
//	 										loanapp.setAppstatus((short)7);//借款申请状态 0审核中 1成功 2失败 3投标中 4流标 5还款中 6已发布 7 已还款
//	 										loanappServiceI.updateByPrimaryKeySelective(loanapp);
//	 									}
//	 								}
//	 							}
//	 						}
//	 					}
//	 				}
// 				}
//		 }
//	}
//	/**
//	 * 
//	* @Title: BatchRepaymentCallBack 
//	* @Description: TODO(批量还款接口 回调地址（正常还款）) 
//	* @param @param request
//	* @param @param response
//	* @param @param huifuParams  参数说明 
//	* @return void    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping("/BatchRepaymentCallBack")
//	public synchronized void BatchRepaymentCallBack(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
//		 System.out.println("===========getCmdId==================="+huifuParams.getCmdId());
//		 System.out.println("===========getRespCode==================="+huifuParams.getRespCode());
//		 System.out.println("===========getRespDesc==================="+huifuParams.getRespDesc());
//		 System.out.println("===========getMerCustId==================="+huifuParams.getMerCustId());
//		 System.out.println("===========getOutCustId==================="+huifuParams.getOutCustId());
//		 System.out.println("===========getOutAcctId==================="+huifuParams.getOutAcctId());
//		 System.out.println("===========getBatchId==================="+huifuParams.getBatchId());
//		 System.out.println("===========getMerOrdDate==================="+huifuParams.getMerOrdDate());
//		 System.out.println("===========getSucNum==================="+huifuParams.getSucNum());
//		 System.out.println("===========getFailNum==================="+huifuParams.getFailNum());
//		 System.out.println("===========getErrMsg==================="+huifuParams.getErrMsg());
//		 System.out.println("===========getItemCode==================="+huifuParams.getItemCode());
//		 System.out.println("===========getBgRetUrl==================="+huifuParams.getBgRetUrl());
//		 System.out.println("===========getMerPriv==================="+huifuParams.getMerPriv());
//		 System.out.println("===========getProId==================="+huifuParams.getProId());
//		 System.out.println("===========getRespExt==================="+huifuParams.getRespExt());
//		 System.out.println("===========getChkValue==================="+huifuParams.getChkValue());
// 		 /*CmdId + RespCode + MerCustId + OutCustId +OutAcctId + BatchId + MerOrdDate +
//			BgRetUrl+ MerPriv + SucNum + FailNum +ErrMsg + ProId+ ReqExt*/
//		 StringBuffer buffer = new StringBuffer();
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getBatchId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerOrdDate()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getSucNum()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getFailNum()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getErrMsg()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getProId()));
//		 buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
//		 String str = buffer.toString();
//		 boolean flag = false;
//		 try {
//			flag = SignUtils.verifyByRSA(str, huifuParams.getChkValue());
//		} catch (Exception e) {
// 			e.printStackTrace();
//		}
//		 
//		if(flag){//验签成功
//  			if(huifuParams.getRespCode() != null && huifuParams.getRespCode().equals("000")){//批量还款成功才调用还款对账接口
// 				String pageNum = "1";//页数
//				String pageSize = "500";//条数
//				int lastPageNum = 0;
//				String totalSize = getRepayMentReconciliationQuery(huifuParams.getMerOrdDate(), huifuParams.getMerOrdDate(), pageNum, pageSize);
//				if(Integer.valueOf(totalSize) % Integer.valueOf(pageSize) == 0){
//					lastPageNum = Integer.valueOf(totalSize) / Integer.valueOf(pageSize);
//				}else{
//					lastPageNum = Integer.valueOf(totalSize) / Integer.valueOf(pageSize) + 1;
//				}
// 				for(int i = 1;i<lastPageNum;i++){
//					pageNum  = String.valueOf(i+1);
//					getRepayMentReconciliationQuery(huifuParams.getMerOrdDate(), huifuParams.getMerOrdDate(), pageNum, pageSize);
//				}
// 				
// 				try {
// 					PrintWriter printWriter = response.getWriter();
// 					printWriter.println("RECV_ORD_ID_".concat(huifuParams.getBatchId()));
// 					if(StringUtil.isNotEmpty(huifuParams.getErrMsg())){//记录日志
// 						huifuBatchLog.info("批量正常还款回调成功条数"+huifuParams.getSucNum());
// 						huifuBatchLog.info("批量正常还款回调错误条数"+huifuParams.getFailNum());
// 		 				huifuBatchLog.info("批量正常还款回调错误条数信息"+huifuParams.getErrMsg());
// 					}
// 				} catch (IOException e) {
// 	 				e.printStackTrace();
// 				}finally{
// 					if(huifuParams.getProId() != null){ 
// 						TenderItem tender = tenderItemServiceI.seltendbytno(huifuParams.getProId());//标的信息
// 						if(tender != null && huifuParams.getMerPriv() != null){
//  							Map<String,Object> maps = new HashMap<String,Object>();
// 							maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
// 							maps.put("tenderid", tender.getId());//标号ID
// 							maps.put("periods", new Short(huifuParams.getMerPriv()));//还款期数（第几期）
//  							List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(maps);
// 							boolean repayMentsFlag 	   = true;//是否全部还款 
// 							boolean repayMentsF        = false;//是否部分还款 
// 							/**
// 							 * 还款数据拼接 调用汇付还款接口 返回000 后 更新借款人还款计划的还款状态为：2处理中，然后调用还款对账接口，如果本期全部还款完成，
// 							 * 就修改借款人当期的还款计划为1已还款，如果有部分还款成功就更新为3部分还款
// 							 */
// 							int count = 0;
// 							int errorCount = 0;
// 							for(RepayMent repayMent : repayMents){
// 								/**
// 								 * 还款提交汇付成功时，还款状态更新为4，还款对账如果还款成功时 更新为2
// 								 */
// 								if(repayMent.getRepaystatus().equals((short)2)
// 										|| repayMent.getRepaystatus().equals((short)3)){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//  									count ++;
//   								}else{
//   									errorCount ++;
//   								}
// 								
// 								if(count > 0 &&  errorCount > 0){
// 									break;//跳出循环 本次还款是部分还款
// 								}
//  							}
// 							
// 							if(count > 0 &&  errorCount > 0){
// 								repayMentsF = true;
// 							}
// 							
// 							if(repayMentsF){
// 								Map<String,Object> hashMap = new HashMap<String,Object>();
// 								hashMap.put("tenderid", tender.getId());
// 								hashMap.put("periods", new Short(huifuParams.getMerPriv()));
//  								DividedPayments dividedPayments = dividedPaymentsServiceI
//  										.findDividedPaymentsByConditions(hashMap);
// 								if(dividedPayments != null){
// 									dividedPayments.setIscomplete((short)3);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款
// 									dividedPaymentsServiceI.updateById(dividedPayments);
// 								}
// 							}
// 							
// 							for(RepayMent repayMent : repayMents){
// 								/**
// 								 * 还款提交汇付成功时，还款状态更新为4，还款对账如果还款成功时 更新为2
// 								 */
//  								if(repayMent.getRepaystatus().equals((short)4)
// 										|| repayMent.getRepaystatus().equals((short)1)){//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
// 									repayMentsFlag = false;//没有完成还款
// 									break;
// 								}
// 							}
// 							
// 							if(repayMentsFlag){//本期全部还款成功
//  								Map<String,Object> hashMap = new HashMap<String,Object>();
// 								hashMap.put("tenderid", tender.getId());
// 								hashMap.put("periods", new Short(huifuParams.getMerPriv()));
//  								DividedPayments dividedPayments = dividedPaymentsServiceI
//  										.findDividedPaymentsByConditions(hashMap);
// 								if(dividedPayments != null){
// 									dividedPayments.setIscomplete((short)1);//是否还款完成0没有完成还款 1已完成还款 2处理中 3部分还款
// 									dividedPaymentsServiceI.updateById(dividedPayments);
// 								}
// 								DividedPayments dividedPayment = new DividedPayments();
// 								dividedPayment.setTenderid(tender.getId());
// 								List<DividedPayments> dividedPayments2 = dividedPaymentsServiceI.
// 										findDividedPaymentss(dividedPayment);
// 								boolean paymentBoolean = true;
// 								for(DividedPayments dividedPayment2 : dividedPayments2){
// 									if(dividedPayment2.getIscomplete().equals((short)2)
// 											||dividedPayment2.getIscomplete().equals((short)0)){
// 										//是否还款完成0没有完成还款 1已完成还款 2处理中 3 部分还款 (标的截标时生成时默认0)
// 										paymentBoolean = false;
// 										break;
// 									}
// 								}
// 								if(paymentBoolean){
// 									loanapp loanapp = null;
// 									if(tender.getLoanappid() != null){
//  										 loanapp = loanappServiceI.selectByPrimaryKey(tender.getLoanappid());
// 									}
// 									if(loanapp != null){
// 										loanapp.setAppstatus((short)7);//借款申请状态 0审核中 1成功 2失败 3投标中 4流标 5还款中 6已发布 7 已还款
// 										loanappServiceI.updateByPrimaryKeySelective(loanapp);
// 									}
// 								}
// 							}
// 						}
// 					}
// 				}
// 				
//			}
//  		}
//	}
//	
//	/**
//	 * 
//	* @Title: getRepayMentReconciliationQuery 
//	* @Description: TODO(汇付还款对账 返回数据处理) 
//	* @param @param beginDateStr 开始日期
//	* @param @param endDate 结束日期
//	* @param @param pageNum 页数
//	* @param @param pageSize 条数
//	* @param @return  参数说明 
//	* @return String    返回类型  返回总条数
//	* @author cjm
//	* @throws
//	 */
//	 public String getAheadRepayMentReconciliationQuery(String beginDateStr,
//				String endDate,String pageNum,String pageSize){
//  		 String result = HuifuRepayMentReconciliationQuery.
//					doRepayMentReconciliationQuery(beginDateStr, 
//							endDate, pageNum, pageSize);
// 			JSONObject jsonObject = JSONObject.parseObject(result);
//			String respCode =  jsonObject.getString("RespCode");//应答返回码
//			String TotalItems = "";
//			if(respCode != null && respCode.equals("000")){
//  				TotalItems =   jsonObject.getString("TotalItems");//记录总条数
//  		    	JSONArray jsonArray = jsonObject.getJSONArray("ReconciliationDtoList");
//			 	Iterator<Object> ites =  jsonArray.iterator();
//			 	while(ites.hasNext()){
//			    	JSONObject jsonObject2 = (JSONObject) ites.next();
//			  		String OrdId = jsonObject2.getString("OrdId");//订单号
//   			  		String TransStat = jsonObject2.getString("TransStat");//汇付交易状态
//  			     	if(TransStat != null && TransStat.equals("P") && OrdId != null){//还款成功
//				   			RepayMent repayMent1 = repayMentServiceI.findJoinCheckRepayMentByRorderno(OrdId);
//				  			if(repayMent1 != null){
// 				  				//更新还款状态
// 				  				if(repayMent1.getRepaystatus().equals((short)1)
//				  						|| repayMent1.getRepaystatus().equals((short)6)){
// 				  					repayMent1.setRepaystatus((short)3);//还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) 
//				  					repayMentServiceI.updateById(repayMent1);//更新还款状态
//				  				}
// 				  				//更新利息管理费记录
//				  				InterestExpenseRecord  interestExpenseRecord = interestExpenseRecordServiceI.findInterestExpenseRecordByRorderno(OrdId); 
// 				  			    if(interestExpenseRecord != null){
// 				  			    	if(interestExpenseRecord.getIsdeal().equals((short)2)){
//  				  			    		interestExpenseRecord.setIsdeal((short)1);//是否处理（0否，1是,2处理中）
// 				  			    		interestExpenseRecord.setPayoutdate(new Date());//清算时间
// 				  			    		interestExpenseRecordServiceI.updateById(interestExpenseRecord);
// 				  			    	}
//				  			    }
//				  				//添加收支记录
// 				  			 List<AccInExRecord> accInExRecord = accInExRecordServiceI.findAccInExRecordByBorderno(OrdId);
//  				  			 if(!(accInExRecord.size() > 0 )){
//  				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByInterestexpenseAccInExRecord(repayMent1);//投资人利息管理费收支记录
//  				  				
//  				  				repaymentInsertAccInExRecordI.InsertAheadOutAccountByRamountAccInExRecord(repayMent1);//借款人本金收支记录
//   				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByRamountAccInExRecord(repayMent1);//投资人本金收支记录
//   				  				
//   				  				repaymentInsertAccInExRecordI.InsertAheadOutAccountByRinterestAccInExRecord(repayMent1);//借款人本金利息收支记录
//   				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByRinterestAccInExRecord(repayMent1);//投资人本金利息收支记录
//   				  				
// 	  				  			repaymentInsertAccInExRecordI.InsertAheadOutAccountByRvoucherAccInExRecord(repayMent1);//借款人类现金收支记录
// 	  				  			repaymentInsertAccInExRecordI.InsertAheadInAccountByRvoucherAccInExRecord(repayMent1);//投资人类现金收支记录
// 	  				  			
// 		  				  		repaymentInsertAccInExRecordI.InsertAheadOutAccountByRvoucherintAccInExRecord(repayMent1);//借款人类现金利息收支记录
// 			  				  	repaymentInsertAccInExRecordI.InsertAheadInAccountByRvoucherintAccInExRecord(repayMent1);//投资人类现金利息收支记录
// 			  				  	
// 				  				repaymentInsertAccInExRecordI.InsertAheadInAccountByRlvoucherintAccInExRecord(repayMent1);//投资人假现金的利息收支记录
// 					  			repaymentInsertAccInExRecordI.InsertAheadInAccountByRintfeeAccInExRecord(repayMent1);//投资人加息劵利息收支记录
// 					  			
// 						  		repaymentInsertAccInExRecordI.InsertAheadInAccountByDisablevoucherAccInExRecord(repayMent1);//投资人失效类现金收支记录
// 							  	repaymentInsertAccInExRecordI.InsertAheadInAccountByDisablevoucherintAccInExRecord(repayMent1);//投资人失效类现金利息收支记录
//   				  			 }
// 				  		} 
//			  		}
//			 	} 
//			}
//			return TotalItems;
//	 }
//	 
//	/**
//	 * 
//	* @Title: getRepayMentReconciliationQuery 
//	* @Description: TODO(汇付还款对账 返回数据处理) 
//	* @param @param beginDateStr 开始日期
//	* @param @param endDate 结束日期
//	* @param @param pageNum 页数
//	* @param @param pageSize 条数
//	* @param @return  参数说明 
//	* @return String    返回类型  返回总条数
//	* @author cjm
//	* @throws
//	 */
//	 public String getRepayMentReconciliationQuery(String beginDateStr,
//				String endDate,String pageNum,String pageSize){
//  		 String result = HuifuRepayMentReconciliationQuery.
//					doRepayMentReconciliationQuery(beginDateStr, 
//							endDate, pageNum, pageSize);
// 			JSONObject jsonObject = JSONObject.parseObject(result);
//			String respCode =  jsonObject.getString("RespCode");//应答返回码
//			String TotalItems = "";
//			if(respCode != null && respCode.equals("000")){
//  				TotalItems =   jsonObject.getString("TotalItems");//记录总条数
//  		    	JSONArray jsonArray = jsonObject.getJSONArray("ReconciliationDtoList");
//			 	Iterator<Object> ites =  jsonArray.iterator();
//			 	while(ites.hasNext()){
//			    	JSONObject jsonObject2 = (JSONObject) ites.next();
//			  		String OrdId = jsonObject2.getString("OrdId");//订单号
//   			  		String TransStat = jsonObject2.getString("TransStat");//汇付交易状态
//  			     	if(TransStat != null && TransStat.equals("P") && OrdId != null){//还款成功
//				   			RepayMent repayMent1 = repayMentServiceI.findRepayMentByRorderno(OrdId);
//				  			if(repayMent1 != null){
// 				  				//更新还款状态
// 				  				if(repayMent1.getRepaystatus().equals((short)1)
//				  						|| repayMent1.getRepaystatus().equals((short)4)){
// 				  					repayMent1.setRepaystatus((short)2);//还款状态(1未还款，2已还款，3已提前还款，4处理中)
//				  					repayMentServiceI.updateById(repayMent1);//更新还款状态
//				  				}
// 				  				//更新利息管理费记录
//				  				InterestExpenseRecord  interestExpenseRecord = interestExpenseRecordServiceI.findInterestExpenseRecordByRorderno(OrdId); 
// 				  			    if(interestExpenseRecord != null){
// 				  			    	if(interestExpenseRecord.getIsdeal().equals((short)2)){
//  				  			    		interestExpenseRecord.setIsdeal((short)1);//是否处理（0否，1是,2处理中）
// 				  			    		interestExpenseRecord.setPayoutdate(new Date());//清算时间
// 				  			    		interestExpenseRecordServiceI.updateById(interestExpenseRecord);
// 				  			    	}
//				  			    }
//				  				//添加收支记录
// 				  			 List<AccInExRecord> accInExRecord = accInExRecordServiceI.findAccInExRecordByBorderno(OrdId);
//  				  			 if(!(accInExRecord.size() > 0 )){
//  				  				repaymentInsertAccInExRecordI.InsertInAccountByInterestexpenseAccInExRecord(repayMent1);//投资人利息管理费收支记录
//  				  				
//  				  				repaymentInsertAccInExRecordI.InsertOutAccountByRamountAccInExRecord(repayMent1);//借款人本金收支记录
//   				  				repaymentInsertAccInExRecordI.InsertInAccountByRamountAccInExRecord(repayMent1);//投资人本金收支记录
//   				  				
//   				  				repaymentInsertAccInExRecordI.InsertOutAccountByRinterestAccInExRecord(repayMent1);//借款人本金利息收支记录
//   				  				repaymentInsertAccInExRecordI.InsertInAccountByRinterestAccInExRecord(repayMent1);//投资人本金利息收支记录
//   				  				
// 	  				  			repaymentInsertAccInExRecordI.InsertOutAccountByRvoucherAccInExRecord(repayMent1);//借款人类现金收支记录
// 	  				  			repaymentInsertAccInExRecordI.InsertInAccountByRvoucherAccInExRecord(repayMent1);//投资人类现金收支记录
// 	  				  			
// 		  				  		repaymentInsertAccInExRecordI.InsertOutAccountByRvoucherintAccInExRecord(repayMent1);//借款人类现金利息收支记录
// 			  				  	repaymentInsertAccInExRecordI.InsertInAccountByRvoucherintAccInExRecord(repayMent1);//投资人类现金利息收支记录
// 			  				  	
// 				  				repaymentInsertAccInExRecordI.InsertInAccountByRlvoucherintAccInExRecord(repayMent1);//投资人假现金的利息收支记录
// 					  			repaymentInsertAccInExRecordI.InsertInAccountByRintfeeAccInExRecord(repayMent1);//投资人加息劵利息收支记录
// 					  			
// 						  		repaymentInsertAccInExRecordI.InsertInAccountByDisablevoucherAccInExRecord(repayMent1);//投资人失效类现金收支记录
// 							  	repaymentInsertAccInExRecordI.InsertInAccountByDisablevoucherintAccInExRecord(repayMent1);//投资人失效类现金利息收支记录
//   				  			 }
// 				  		} 
//			  		}
//			 	} 
//			}
//			return TotalItems;
//	 }
//    }
