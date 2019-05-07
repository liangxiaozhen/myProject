package com.ptpl.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.util.Assert;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.BacthRepayMentController;
import com.ptpl.controller.huishang.HSRepayMentFreezeController;
import com.ptpl.controller.huishang.model.RepayMentParamters;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.GlobalSetting;
import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.model.RepaymentFrz;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.AheadRepayMentLogicI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.GlobalSettingServiceI;
import com.ptpl.service.InterestExpenseRecordServiceI;
import com.ptpl.service.RepayMentAheadInsertAccInExRecordI;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.RepayMentNormalInsertAccInExRecordI;
import com.ptpl.service.RepayMentOverDueInsertAccInExRecordI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.service.RepaymentFrzServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.ThirdRepayMentDealI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.DateUtil;
import com.ptpl.web.util.DividedPaymentsComparator;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: ThirdRepayMentDealImpl 
* @Description: TODO(第三方支付/银行,还款处理工具类) 
* @author cjm 
* @date 2017年5月22日 下午3:26:31 
*
 */
public class ThirdRepayMentDealImpl extends BaseController implements ThirdRepayMentDealI {

	@Autowired
	private OracleSequenceMaxValueIncrementer repaymentnumber;//批次号
	
	@Autowired
	private TenderItemServiceI tenderItemServiceI;//标的
	
	@Autowired
	private BacthFileRecordServiceI bacthFileRecordServiceI;//文件记录
	
	@Autowired
	private  RepayMentServiceI repayMentServiceI;//投资人还款计划
	
	@Autowired
	private  DividedPaymentsServiceI dividedPaymentsServiceI;//投资人还款计划
  	
	@Autowired
	private  UserFsAccountInfoServiceI userFsAccountInfoServiceI;//第三方托管账号
	
	@Autowired
	private RepayMentNormalInsertAccInExRecordI repaymentInsertAccInExRecordI; //正常还款成功时 投资人/借款人收支记录
 	
	@Autowired
	private RepayMentAheadInsertAccInExRecordI repayMentAheadInsertAccInExRecordI; //提前还款成功时 投资人/借款人收支记录
 	
	@Autowired
	private RepayMentOverDueInsertAccInExRecordI repayMentOverDueInsertAccInExRecordI; //逾期还款成功时 投资人/借款人收支记录
   	
 	@Autowired
	private GlobalSettingServiceI globalSettingServiceImpl;//全局设置
 	
 	@Autowired
	private	SMSSendServiceI SMSSendServiceImpl;//短信接口
 	
 	@Autowired
	private UserBaseAccountInfoServiceI  userBaseAccountInfoServiceI;//用户基本信息service
 	
	@Autowired
	private RepayMentBaseDealI repayMentBaseDealI;//还款通用处理接口
	
	@Autowired
	private  RepaymentFrzServiceI repaymentFrzServiceI;//还款批量冻结解冻记录
	
	@Autowired
	private  RepaymentDetailServiceI repaymentDetailServiceI;//批量详情service
	
	@Autowired
	private AheadRealRepaymentServiceI aheadRealRepaymentServiceI;//提前还款实际记录表
	
	@Autowired
	private  InterestExpenseRecordServiceI interestExpenseRecordServiceI;//利息管理费记录表
	/**
	 * 
	* @Title: settingUpBatchAheadRepayMent 
	* @Description: TODO(提前还款) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayment  借款人还款计划
	* @param @param repayMents  投资人还款计划集合（需和提前还款实际记录表连查）
	* @param @param rbatchno 还款批次号
	* @param @param Usrcustid 借款人电子账号
	* @param @param totalamount 本批次还款金额   注： 如果是及时还款 才传借款人电子账号，标号，本批次还款金额 
	* @param @param Tno  标号
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	* 注：1如果是 及时还款 才传借款人电子账号，标号，本批次还款金额   2冻结还款，客服审核通过后调用还款接口  不需要解冻还款金额 （标号，第三方电子账号，冻结金额传空值）
	 */
	@Override
	public Map<String,Object> settingUpBatchAheadRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno) {
		Map<String,Object> ResultMap = new HashMap<>();
		boolean norMalFlag = false;
 //		String dateStr = StringUtil.formatDate(new Date(), "yyyyMMdd");
		String dateStr = StringUtil.formatDate(StringUtil.getDateByString("20160622", "yyyyMMdd"), "yyyyMMdd");
		
		String filePathName = HSignUtil.FILEUPLOAD + HSignUtil.REPAYMENT + File.separator + dateStr;
		String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
		String productNo  = "-Z01-REPTRAN-";
 		String resuProductNo = "-Z01-REPTRAN-RESULT-";
		String fileName = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER + productNo + batchNo + "-" + dateStr;
		String getFileName  = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER  + resuProductNo + batchNo + "-" +  dateStr;

		RepayMentParamters repayMentParamter = new RepayMentParamters();
		repayMentParamter.setBatch(batchNo);//还款批次号
		repayMentParamter.setType("002");//到期还款还是代偿
		repayMentParamter.setDate(dateStr);
		repayMentParamter.setEndflg("0");//是否还款完成
		boolean flag = repayMentParametersAreWrittenIntoTheFileByAhead(filePathName, fileName, repayMents, repayMentParamter);//写进文件
  		if(!flag){//还款参数写进文件失败
 			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "还款参数写进文件失败!");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.WRITEFALG);
  			return ResultMap;
		}
 			  
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFilePath(filePathName);//文件路径    /batchfile/平台编号/业务名称/日期
		bacthFileRecord.setSendFileName(fileName);  //上传文件名称
		bacthFileRecord.setGetFileName(getFileName);  //下载文件名称
		bacthFileRecord.setCoinstCode(HSignUtil.COINSTCODE);  //平台编号  800114
		bacthFileRecord.setPName(StringUtil.PNNAME);  //平台名称
		bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
 		bacthFileRecord.setFileType((short)4);  //业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款 ....
		bacthFileRecord.setIsSend((short)0);  //是否已发送到银行     0.否   1.是
		bacthFileRecord.setSendResult((short)0);  //发送结果（是否成功）   0.失败    1.成功 
		bacthFileRecord.setIsDealResult((short)0);  //是否已处理结果文件     0.否   1.是
		bacthFileRecord.setDealResult((short)0);  //处理结果（是否成功）
		bacthFileRecord.setRemark("还款记录");  //备注
		bacthFileRecord.setBatchNo(rbatchno);//文件业务批次号
		int count = 0;
		count  = bacthFileRecordServiceI.insert(bacthFileRecord);
		if(!(count > 0)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】 第三方还款处理工具类,批量文件记录添加失败");
			}
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "批量文件记录添加失败!");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.BACTHFILERECORDFALG);
  			return ResultMap;
		}
		
		RepaymentFrz repaymentFrz2 = new RepaymentFrz();
		repaymentFrz2.setBatchno(rbatchno);//还款批次号
 		RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);//批次还款记录
		
 		repaymentFrz.setIsahead((short)1);//是否提前（0否，1是）
		repaymentFrz.setIsproxyrepay((short)0);//是否代偿（1是，0否）
 		
		//查找批次详情信息
		RepaymentDetail repaymentDetail2 = new RepaymentDetail();
		repaymentDetail2.setRbatchno(rbatchno);//批次号
		List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail2);
		
 		updateAllRepayStatusForWaitDeal(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为待处理
		
		Map<String,Object> fileUploadMap = null;
		try {
			fileUploadMap = BacthRepayMentController.repayMentFileUpload(filePathName, fileName, dateStr);//文件上传
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】第三方还款处理工具类,文件上传失败,失败原因："+e.getMessage());
			}
			e.printStackTrace();
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "还款文件上传失败!");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
  			return ResultMap;
		}
		
		if(fileUploadMap.size() > 0){
			norMalFlag = (boolean) fileUploadMap.get(BacthRepayMentController.REPAYMENTFLAG);
			String upResultCode = (String) fileUploadMap.get(BacthRepayMentController.REPAYMENTCODE);
			if(!norMalFlag){
				bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
				bacthFileRecordServiceI.update(bacthFileRecord);
				ResultMap.put(RepayMent_Constant.FALG, false);
				ResultMap.put(RepayMent_Constant.MSG, "还款文件上传失败!");
				ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
	  			return ResultMap;
			}
			
			updateAllRepayStatusForDealing(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为处理中
			
			bacthFileRecord.setIsSend((short)1);  //是否已发送到银行     0.否   1.是
			bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
			bacthFileRecord.setSendResult((short)1);  //发送结果（是否成功）   0.失败    1.成功 
			bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
			bacthFileRecordServiceI.update(bacthFileRecord);
		}
		
		ResultMap.put(RepayMent_Constant.FALG, true);
		ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传成功!");
		ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
  		return ResultMap;
	}
	

	/**
	 * 
	* @Title: settingUpBatchNormalRepayMent 
	* @Description: TODO(正常还款) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayment 借款人还款计划
	* @param @param repayMents  投资人还款计划集合
	* @param @param rbatchno  还款批次号
	* @param @param Usrcustid  第三方电子账号
	* @param @param totalamount  冻结金额
	* @param @param Tno  标号
	* @param @return    设定文件  
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	* 
	* 注：1如果是及时还款 才传借款人电子账号，标号，本批次还款金额   2冻结还款，客服审核通过后调用还款接口  不需要解冻还款金额 （标号，第三方电子账号，冻结金额传空值）
	 */ 
	@Override
	public Map<String,Object> settingUpBatchNormalRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno) {
		Map<String,Object> ResultMap = new HashMap<>();
		boolean norMalFlag = false;
 //		String dateStr = StringUtil.formatDate(new Date(), "yyyyMMdd");
		String dateStr = StringUtil.formatDate(StringUtil.getDateByString("20160622", "yyyyMMdd"), "yyyyMMdd");
 		String filePathName = HSignUtil.FILEUPLOAD + HSignUtil.REPAYMENT + File.separator + dateStr;
		String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
		String productNo  = "-Z01-REPTRAN-";
 		String resuProductNo = "-Z01-REPTRAN-RESULT-";
		String fileName = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER + productNo + batchNo + "-" + dateStr;
		String getFileName  = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER  + resuProductNo + batchNo + "-" +  dateStr;

		RepayMentParamters repayMentParamter = new RepayMentParamters();
		repayMentParamter.setBatch(batchNo);//还款批次号
		repayMentParamter.setType("002");//到期还款还是代偿
		repayMentParamter.setDate(dateStr);
		repayMentParamter.setEndflg("0");//是否还款完成
		boolean flag = repayMentParametersAreWrittenIntoTheFile(filePathName, fileName, repayMents, repayMentParamter);//写进文件
		if(!flag){//文件写进失败
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】写进文件失败！还款批次号是："+rbatchno);
			}
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "文件写进失败!");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.WRITEFALG);
  			return ResultMap;
 		}
		
		RepaymentFrz repaymentFrz2 = new RepaymentFrz();
		repaymentFrz2.setBatchno(rbatchno);//还款批次号
 		RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);//批次还款记录
		
 		repaymentFrz.setIsahead((short)0);//是否提前（0否，1是）
		repaymentFrz.setIsproxyrepay((short)0);//是否代偿（1是，0否）
		repaymentFrz.setIsoverdue((short)0); //是否逾期 0否，1是
  		
 		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFilePath(filePathName);//文件路径    /batchfile/平台编号/业务名称/日期
		bacthFileRecord.setSendFileName(fileName);  //上传文件名称
		bacthFileRecord.setGetFileName(getFileName);  //下载文件名称
		bacthFileRecord.setCoinstCode(HSignUtil.COINSTCODE);  //平台编号  800114
		bacthFileRecord.setPName(StringUtil.PNNAME);  //平台名称
		bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
 		bacthFileRecord.setFileType((short)4);  //业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款 ....
		bacthFileRecord.setIsSend((short)0);  //是否已发送到银行     0.否   1.是
		bacthFileRecord.setSendResult((short)0);  //发送结果（是否成功）   0.失败    1.成功 
		bacthFileRecord.setIsDealResult((short)0);  //是否已处理结果文件     0.否   1.是
		bacthFileRecord.setDealResult((short)0);  //处理结果（是否成功）
		bacthFileRecord.setRemark("还款记录");  //备注
		bacthFileRecord.setBatchNo(rbatchno);//批次号
		int count = 0;
		count  = bacthFileRecordServiceI.insert(bacthFileRecord);
		if(!(count > 0)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java, 到期还款【正常还款】批量文件记录添加失败,还款批次号是:"+rbatchno);
			}
 			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "批量文件记录添加失败!");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.BACTHFILERECORDFALG);
  			return ResultMap;
		}
		
		//查找批次详情信息
		RepaymentDetail repaymentDetail2 = new RepaymentDetail();
		repaymentDetail2.setRbatchno(rbatchno);//批次号
		List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail2);
 		updateAllRepayStatusForWaitDeal(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为待处理
		
 		Map<String,Object> fileUploadMap = null;
		try {
			fileUploadMap = BacthRepayMentController.repayMentFileUpload(filePathName, fileName, dateStr);//文件上传
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,文件上传失败,失败原因："+e.getMessage()+",上传的文件名称是:"+fileName);
			}
			e.printStackTrace();
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "还款文件上传失败!");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
  			return ResultMap;
		}
		
		if(fileUploadMap.size() > 0){
			norMalFlag = (boolean) fileUploadMap.get(BacthRepayMentController.REPAYMENTFLAG);
			String upResultCode = (String) fileUploadMap.get(BacthRepayMentController.REPAYMENTCODE);
			if(!norMalFlag){
				bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
				bacthFileRecordServiceI.update(bacthFileRecord);
				if(logger.isDebugEnabled()){
					logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,文件上传失败,上传的文件名称是:"+fileName);
				}
				ResultMap.put(RepayMent_Constant.FALG, false);
				ResultMap.put(RepayMent_Constant.MSG, "还款文件上传失败!");
				ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
	  			return ResultMap;
			}
			
			bacthFileRecord.setIsSend((short)1);  //是否已发送到银行     0.否   1.是
			bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
			bacthFileRecord.setSendResult((short)1);  //发送结果（是否成功）   0.失败    1.成功 
			bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
			bacthFileRecordServiceI.update(bacthFileRecord);
  			//更新还款状态为处理中
  			updateAllRepayStatusForDealing(repayMents, repayMentDetails, repaymentFrz);
		}
		
		ResultMap.put(RepayMent_Constant.FALG, true);
		ResultMap.put(RepayMent_Constant.MSG, "还款文件上传成功!");
		ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
		return ResultMap;
 	}

	/**
	 * 
	* @Title: settingUpBatchNormalRepayMent 
	* @Description: TODO(逾期还款) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayment 借款人还款计划
	* @param @param repayMents  投资人还款计划集合
	* @param @param rbatchno  还款批次号
	* @param @param Usrcustid  第三方电子账号
	* @param @param totalamount  冻结金额
	* @param @param Tno  标号
	* @param @return    设定文件  
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	* 
 	 */ 
	@Override
	public Map<String,Object> settingUpBatchOverdueRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno) {
		Map<String,Object> ResultMap = new HashMap<>();
 		boolean norMalFlag = false;
 //		String dateStr = StringUtil.formatDate(new Date(), "yyyyMMdd");
		String dateStr = StringUtil.formatDate(StringUtil.getDateByString("20160622", "yyyyMMdd"), "yyyyMMdd");
		
		String filePathName = HSignUtil.FILEUPLOAD + HSignUtil.REPAYMENT + File.separator + dateStr;
		String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
		String productNo  = "-Z01-REPTRAN-";
 		String resuProductNo = "-Z01-REPTRAN-RESULT-";
		String fileName = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER + productNo + batchNo + "-" + dateStr;
		String getFileName  = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER  + resuProductNo + batchNo + "-" +  dateStr;

		RepayMentParamters repayMentParamter = new RepayMentParamters();
		repayMentParamter.setBatch(batchNo);//还款批次号
		repayMentParamter.setType("002");//到期还款还是代偿
		repayMentParamter.setDate(dateStr);
		repayMentParamter.setEndflg("0");//是否还款完成
		boolean flag = repayMentParametersAreWrittenIntoTheFileByOverdue(filePathName, fileName, repayMents, repayMentParamter);
  		if(!flag){//还款参数写进文件失败
 			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "还款参数写进文件失败");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.WRITEFALG);
			return ResultMap;
		}
 			  
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFilePath(filePathName);//文件路径    /batchfile/平台编号/业务名称/日期
		bacthFileRecord.setSendFileName(fileName);  //上传文件名称
		bacthFileRecord.setGetFileName(getFileName);  //下载文件名称
		bacthFileRecord.setCoinstCode(HSignUtil.COINSTCODE);  //平台编号  800114
		bacthFileRecord.setPName(StringUtil.PNNAME);  //平台名称
		bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
 		bacthFileRecord.setFileType((short)4);  //业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款 ....
		bacthFileRecord.setIsSend((short)0);  //是否已发送到银行     0.否   1.是
		bacthFileRecord.setSendResult((short)0);  //发送结果（是否成功）   0.失败    1.成功 
		bacthFileRecord.setIsDealResult((short)0);  //是否已处理结果文件     0.否   1.是
		bacthFileRecord.setDealResult((short)0);  //处理结果（是否成功）
		bacthFileRecord.setRemark("还款记录");  //备注
		bacthFileRecord.setBatchNo(rbatchno);//文件业务批次号
		int count = 0;
		count  = bacthFileRecordServiceI.insert(bacthFileRecord);
		if(!(count > 0)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款 [逾期还款] 第三方还款处理工具类,批量文件记录添加失败,批次号是:"+rbatchno);
			}
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "批量文件记录添加失败");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.BACTHFILERECORDFALG);
			return ResultMap;
 		}
		

		RepaymentFrz repaymentFrz2 = new RepaymentFrz();
		repaymentFrz2.setBatchno(rbatchno);//还款批次号
 		RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);//批次还款记录
		
 		repaymentFrz.setIsoverdue((short)1);//是否逾期（0否，1是）
		repaymentFrz.setIsproxyrepay((short)0);//是否代偿（1是，0否）
 		
		//查找批次详情信息
		RepaymentDetail repaymentDetail2 = new RepaymentDetail();
		repaymentDetail2.setRbatchno(rbatchno);//批次号
		List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail2);
		
 		updateAllRepayStatusForWaitDeal(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为待处理
 		
		Map<String,Object> fileUploadMap = null;
		try {
			fileUploadMap = BacthRepayMentController.repayMentFileUpload(filePathName, fileName, dateStr);//文件上传
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款[逾期还款] 第三方还款处理工具类,文件上传失败,失败原因："+e.getMessage());
			}
			e.printStackTrace();
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传失败");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
			return ResultMap;
		}
		
		if(fileUploadMap.size() > 0){
			norMalFlag = (boolean) fileUploadMap.get(BacthRepayMentController.REPAYMENTFLAG);
			String upResultCode = (String) fileUploadMap.get(BacthRepayMentController.REPAYMENTCODE);
			if(!norMalFlag){
 				bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
				bacthFileRecordServiceI.update(bacthFileRecord);
				ResultMap.put(RepayMent_Constant.FALG, false);
				ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传失败");
				ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
				return ResultMap;
			}
			
			updateAllRepayStatusForDealing(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为处理中
			
			bacthFileRecord.setIsSend((short)1);  //是否已发送到银行     0.否   1.是
			bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
			bacthFileRecord.setSendResult((short)1);  //发送结果（是否成功）   0.失败    1.成功 
			bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
			bacthFileRecordServiceI.update(bacthFileRecord);
		}
		ResultMap.put(RepayMent_Constant.FALG, true);
		ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传成功");
		ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
		return ResultMap;
	}
	
	 
	/**
	 * 
	* @Title: settingUpBatchNormalRepayMent 
	* @Description: TODO(正常代偿还款) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayment 借款人还款计划
	* @param @param repayMents  投资人还款计划集合
	* @param @param rbatchno  还款批次号
	* @param @param Usrcustid  第三方电子账号
	* @param @param totalamount  冻结金额
	* @param @param Tno  标号
	* @param @return    设定文件  
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	* 
 	 */
	@Override
	public Map<String,Object> settingUpBatchNormalCompensatoryRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno) {
		Map<String,Object> ResultMap = new HashMap<>();
		boolean norMalFlag = false;
		
//		String dateStr = StringUtil.formatDate(new Date(), "yyyyMMdd");
		String dateStr = StringUtil.formatDate(StringUtil.getDateByString("20160622", "yyyyMMdd"), "yyyyMMdd");
		
		String filePathName = HSignUtil.FILEUPLOAD + HSignUtil.REPAYMENT + File.separator + dateStr;
		String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
		String productNo  = "-Z01-REPTRAN-";
 		String resuProductNo = "-Z01-REPTRAN-RESULT-";
		String fileName = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER + productNo + batchNo + "-" + dateStr;
		String getFileName  = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER  + resuProductNo + batchNo + "-" +  dateStr;

		RepayMentParamters repayMentParamter = new RepayMentParamters();
		repayMentParamter.setBatch(batchNo);//还款批次号
		repayMentParamter.setType("003");//到期还款还是代偿   002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
		repayMentParamter.setDate(dateStr);
		repayMentParamter.setEndflg("0");//是否还款完成
		boolean flag = normalCompensatoryRepayMentParametersAreWrittenIntoTheFile(filePathName, fileName, repayMents, repayMentParamter);
		if(!flag){//文件写进失败
 			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "文件写进失败");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.WRITEFALG);
			return ResultMap;
		}
		 
			  
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFilePath(filePathName);//文件路径    /batchfile/平台编号/业务名称/日期
		bacthFileRecord.setSendFileName(fileName);  //上传文件名称
		bacthFileRecord.setGetFileName(getFileName);  //下载文件名称
		bacthFileRecord.setCoinstCode(HSignUtil.COINSTCODE);  //平台编号  800114
		bacthFileRecord.setPName(StringUtil.PNNAME);  //平台名称
		bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
 		bacthFileRecord.setFileType((short)4);  //业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款 ....
		bacthFileRecord.setIsSend((short)0);  //是否已发送到银行     0.否   1.是
		bacthFileRecord.setSendResult((short)0);  //发送结果（是否成功）   0.失败    1.成功 
		bacthFileRecord.setIsDealResult((short)0);  //是否已处理结果文件     0.否   1.是
		bacthFileRecord.setDealResult((short)0);  //处理结果（是否成功）
		bacthFileRecord.setRemark("还款记录");  //备注
		bacthFileRecord.setBatchNo(rbatchno);//文件业务批次号
		int count = 0;
		count  = bacthFileRecordServiceI.insert(bacthFileRecord);
		if(!(count > 0)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款[正常代偿还款] 第三方还款处理工具类,批量文件记录添加失败!");
			}
			
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "批量文件记录添加失败");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.BACTHFILERECORDFALG);
			return ResultMap;
		}
		
		RepaymentFrz repaymentFrz2 = new RepaymentFrz();
		repaymentFrz2.setBatchno(rbatchno);//还款批次号
 		RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);//批次还款记录
		
 		repaymentFrz.setIsproxyrepay((short)1);//是否代偿（1是，0否）
		
 		//查找批次详情信息
		RepaymentDetail repaymentDetail2 = new RepaymentDetail();
		repaymentDetail2.setRbatchno(rbatchno);//批次号
		List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail2);
		
 		updateAllRepayStatusForWaitDeal(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为待处理
 		
 		Map<String,Object> fileUploadMap = null;
		try {
			fileUploadMap = BacthRepayMentController.repayMentFileUpload(filePathName, fileName, dateStr);//文件上传
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款[正常代偿还款] 第三方还款处理工具类,文件上传失败,失败原因："+e.getMessage());
			}
			e.printStackTrace();
			ResultMap.put(RepayMent_Constant.FALG, false);
			ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传失败");
			ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
			return ResultMap;
		}
		
		if(fileUploadMap.size() > 0){
			norMalFlag = (boolean) fileUploadMap.get(BacthRepayMentController.REPAYMENTFLAG);
			String upResultCode = (String) fileUploadMap.get(BacthRepayMentController.REPAYMENTCODE);
			if(!norMalFlag){
				bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
				bacthFileRecordServiceI.update(bacthFileRecord);
				ResultMap.put(RepayMent_Constant.FALG, false);
				ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传失败");
				ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.FILEUPLOADFALG);
				return ResultMap;
			}
		 
			updateAllRepayStatusForDealing(repayMents, repayMentDetails, repaymentFrz);//更新还款状态为处理中

			
			bacthFileRecord.setIsSend((short)1);  //是否已发送到银行     0.否   1.是
			bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
			bacthFileRecord.setSendResult((short)1);  //发送结果（是否成功）   0.失败    1.成功 
			bacthFileRecord.setUpResultCode(upResultCode);//银行返回码  上传文件
			bacthFileRecordServiceI.update(bacthFileRecord);
		}
		
		ResultMap.put(RepayMent_Constant.FALG, true);
		ResultMap.put(RepayMent_Constant.MSG, "到期还款文件上传成功");
		ResultMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
		return ResultMap;
	}
  
	/**
	 * 
	* @Title: repayMentParametersAreWrittenIntoTheFile 
	* @Description: TODO(把还款参数写进 文件   正常还款) 
	* @param @param filePathName  文件目录
	* @param @param fileName	写进的文件名
	* @param @param repayMents  还款计划
	* @param @param repayMentParamter 通用参数设置   必须设置 Batch，Type，Date，Endflg
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws   String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
	 */
	public boolean repayMentParametersAreWrittenIntoTheFile(String filePathName,String fileName,List<RepayMent> repayMents,RepayMentParamters repayMentParamter){
		boolean flag = false;
 		if(!(repayMents.size() > 0)){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile  还款参数写进文件失败  投资人的还款计划为空");
 			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(filePathName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款参数写进文件失败,失败原因：文件目录名为空");
			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(fileName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile,还款参数写进文件失败,失败原因：文件名为空");
			}
			return flag;
		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getBatch())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款批次号为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getType())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款类型为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getDate())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款日期为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getEndflg())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款标识为空");
 			}
 			return flag;
 		}
   		
  		UserFsAccountInfo outUserFsAccountInfo = null;
 		UserFsAccountInfo inUserFsAccountInfo = null;
  		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMents.get(0).getTenderid());
 		if(tenderItem == null || tenderItem.getTno() == null){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 标的对象或标的编号为空");
 			}
 			return flag;
 		}
 		 
 		
 	 	File file = new File(filePathName);
 		if(!file.exists()){
			file.mkdirs();
		} 
 		
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(file,fileName));
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		}
		
		PrintWriter out = null;
		DecimalFormat df1 = new DecimalFormat("######################0.00");
		boolean checkFlag = true;
		try {
			out = new PrintWriter(new OutputStreamWriter(fileOutputStream,"GBK"), true);
			//新文档
			Double Ramount 		= 0.00;//还款本金
			Double Rinterest 	= 0.00;//还款利息
			Double OUTFEEAMT 	= 0.00;//转出方手续费扣款金额
			Double INFEEAMT 	= 0.00;//转入方手续费扣款金额
 			for(RepayMent repayMent : repayMents){
 				if(!repayMent.getPlanstatus().equals((short)1)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款计划是否有效状态错误,必须有效才能还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
  				
 				if(repayMent.getRepaystatus().equals((short)4) || repayMent.getRepaystatus().equals((short)3) ){
 					if(logger.isDebugEnabled()){
 						logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款计划还款状态错误,已经提交的不能再次还款,还款流水号是:"+repayMent.getRorderno());
 					}
 					file.delete();
 					checkFlag = false;
 					break;
 				}
 				
 				if(repayMent.getRepaystatus().equals((short)5)){
 					if(logger.isDebugEnabled()){
 						logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 还款计划还款状态错误,已经还款的不能再次还款,还款流水号是:"+repayMent.getRorderno());
 					}
 					file.delete();
 					checkFlag = false;
 					break;
 				}
 				 
 				outUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getOutaccountid());
	  			if(outUserFsAccountInfo == null || outUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 借款人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
	  			
	  			inUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getInaccountid());
	  			if(inUserFsAccountInfo == null || inUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 投资人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
	  			
	  			if(!(outUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				outUserFsAccountInfo = BaseController.getDecryptionUserFsAccountInfoDetail(outUserFsAccountInfo);//解密加密后的数据
	  			}
	  			
	  			if(!(inUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				inUserFsAccountInfo  = BaseController.getDecryptionUserFsAccountInfoDetail(inUserFsAccountInfo);//解密加密后的数据
	  			}
 	  			
	  			if(StringUtil.isEmpty(repayMent.getAuthcode())){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 投标授权码为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
 	  			
	  		 
  				if(StringUtil.isEmpty(repayMent.getUtorderno())){
  					if(logger.isDebugEnabled()){
  						logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFile 投标/债权流水号为空,还款流水号是:"+repayMent.getRorderno());
  					}
  					file.delete();
  					checkFlag = false;
  					break;
  				}
 	  			 
	  			Ramount   =  repayMent.getRamount() + repayMent.getRvoucher() + repayMent.getDisablevoucher();//本 + 类 + 作废类
	  			Rinterest =  repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint();//本息 + 类息 + 作废类息
	  			INFEEAMT  =  repayMent.getDisablevoucher() + repayMent.getDisablevoucherint() + repayMent.getInterestexpense();//作废类 + 作废类息 + 利息管理费
	  			
 				out.print(StringUtil.stringRightPading(6,repayMentParamter.getBatch(),2));//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
				out.print(StringUtil.stringRightPading(3,repayMentParamter.getType(),2));//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
				out.print(StringUtil.stringRightPading(8,repayMentParamter.getDate(),2));//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
				out.print(StringUtil.stringRightPading(19,outUserFsAccountInfo.getUsrcustid(),2));//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
				String[] array = df1.format(Ramount).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array[0] + array[1],1));//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
				String[] array1 = df1.format(Rinterest).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array1[0] + array1[1],1));//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
				out.print(StringUtil.stringRightPading(19,inUserFsAccountInfo.getUsrcustid(),2));//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
				out.print(StringUtil.stringRightPading(3,"156",2));//币种	CURR	N	3	80	82	M	156：人民币；
				out.print(StringUtil.stringRightPading(1,"0",2));//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
 				String[] array2 = df1.format(OUTFEEAMT).split("\\.");
 			    out.print(StringUtil.stringLeftPading(11,array2[0] + array2[1],1));//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringLeftPading(1,"0",2));//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
 				String[] array3 = df1.format(INFEEAMT).split("\\.");
 				out.print(StringUtil.stringLeftPading(11,array3[0] + array3[1],1));//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringRightPading(6,tenderItem.getTno(),2));//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
				out.print(StringUtil.stringRightPading(20,repayMent.getAuthcode(),2));//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
				out.print(StringUtil.stringRightPading(1,repayMentParamter.getEndflg(),2));//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
				out.print(StringUtil.stringRightPading(100,repayMent.getRorderno(),2));//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
  				out.print(StringUtil.stringRightPading(40,repayMent.getUtorderno(),2));//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
 				out.print(StringUtil.stringRightPading(60,"",2));//保留域	RESE	A	60	274	333	C	
				out.print("\n");
			}
		} catch (UnsupportedEncodingException e) {
 			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常还款】 还款参数写进文件失败,失败原因："+e.getMessage());
			}
 			e.printStackTrace();
 			
		}finally{
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				
			}
			
		}
		
		if(!checkFlag){
			return checkFlag;
		}
		
		flag = true;
 		return flag;
	}
	
	/**
	 * 
	* @Title: repayMentParametersAreWrittenIntoTheFileByAhead
	* @Description: TODO(把还款参数写进 文件   提前还款) 
	* @param @param filePathName  文件目录
	* @param @param fileName	写进的文件名
	* @param @param repayMents  还款计划
	* @param @param repayMentParamter 通用参数设置   必须设置 Batch，Type，Date，Endflg
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws   String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
	 */
	public boolean repayMentParametersAreWrittenIntoTheFileByAhead(String filePathName,String fileName,List<RepayMent> repayMents,RepayMentParamters repayMentParamter){
		boolean flag = false;
 		if(!(repayMents.size() > 0)){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead  还款参数写进文件失败  投资人的还款计划为空");
 			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(filePathName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款参数写进文件失败,失败原因：文件目录名为空");
			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(fileName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead,还款参数写进文件失败,失败原因：文件名为空");
			}
			return flag;
		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getBatch())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款批次号为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getType())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款类型为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getDate())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款日期为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getEndflg())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款标识为空");
 			}
 			return flag;
 		}
   	 
  		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMents.get(0).getTenderid());
 		if(tenderItem == null || tenderItem.getTno() == null){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 标的对象或标的编号为空");
 			}
 			return flag;
 		}
 		 
 		
 	 	File file = new File(filePathName);
 		if(!file.exists()){
			file.mkdirs();
		} 
 		
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(file,fileName));
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		}
		
		PrintWriter out = null;
		DecimalFormat df1 = new DecimalFormat("######################0.00");
		boolean checkFlag = true;
		try {
			out = new PrintWriter(new OutputStreamWriter(fileOutputStream,"GBK"), true);
			//新文档
			Double Ramount 		= 0.00;//还款本金
			Double Rinterest 	= 0.00;//还款利息
			Double OUTFEEAMT 	= 0.00;//转出方手续费扣款金额
			Double INFEEAMT 	= 0.00;//转入方手续费扣款金额
			AheadRealRepayment aheadRealRepayment = null;//提前还款实际利息记录表
 			for(RepayMent repayMent : repayMents){
 				aheadRealRepayment = repayMent.getAheadRealRepayment();
 				if(aheadRealRepayment == null){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 提前还款实际记录实体类信息未找到,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				
 				if(!repayMent.getPlanstatus().equals((short)1)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款计划是否有效状态错误,必须有效才能还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				
 				if(repayMent.getRepaystatus().equals((short)3) || repayMent.getRepaystatus().equals((short)4) ){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款计划还款状态错误,已经提交的不能再次还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				 
 				if(repayMent.getRepaystatus().equals((short)5)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款计划还款状态错误,已经还款的不能再次还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				
  				UserFsAccountInfo	outUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getOutaccountid());
	  			if(outUserFsAccountInfo == null || outUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 借款人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
	  			
	  			UserFsAccountInfo	inUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getInaccountid());
	  			if(inUserFsAccountInfo == null || inUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 投资人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
	  			
	  			if(!(outUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				outUserFsAccountInfo = BaseController.getDecryptionUserFsAccountInfoDetail(outUserFsAccountInfo);//解密加密后的数据
	  			}
	  			
	  			if(!(inUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				inUserFsAccountInfo  = BaseController.getDecryptionUserFsAccountInfoDetail(inUserFsAccountInfo);//解密加密后的数据
	  			}
	  			
	  			if(StringUtil.isEmpty(repayMent.getAuthcode())){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 投标授权码为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
 	  			 
  				if(StringUtil.isEmpty(repayMent.getUtorderno())){
  					if(logger.isDebugEnabled()){
  						logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 投标/债权转让流水号为空,还款流水号是:"+repayMent.getRorderno());
  					}
  					file.delete();
  					checkFlag = false;
  					break;
  				}
   				
	  			Ramount   =  aheadRealRepayment.getRptotalamount() + aheadRealRepayment.getRvoucher() + repayMent.getDisablevoucher();//本 + 类 + 作废类
	  			Rinterest =  aheadRealRepayment.getRinterest() + aheadRealRepayment.getRvoucherint() + aheadRealRepayment.getDiscardvoucherint();//本息 + 类息 + 作废类息
	  			INFEEAMT  =  repayMent.getDisablevoucher() + aheadRealRepayment.getDiscardvoucherint() + aheadRealRepayment.getInterestexpense();//作废类 + 作废类息 + 利息管理费
	  			
 				out.print(StringUtil.stringRightPading(6,repayMentParamter.getBatch(),2));//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
				out.print(StringUtil.stringRightPading(3,repayMentParamter.getType(),2));//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
				out.print(StringUtil.stringRightPading(8,repayMentParamter.getDate(),2));//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
				out.print(StringUtil.stringRightPading(19,outUserFsAccountInfo.getUsrcustid(),2));//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
				String[] array = df1.format(Ramount).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array[0] + array[1],1));//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
				String[] array1 = df1.format(Rinterest).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array1[0] + array1[1],1));//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
				out.print(StringUtil.stringRightPading(19,inUserFsAccountInfo.getUsrcustid(),2));//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
				out.print(StringUtil.stringRightPading(3,"156",2));//币种	CURR	N	3	80	82	M	156：人民币；
				out.print(StringUtil.stringRightPading(1,"0",2));//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
 				String[] array2 = df1.format(OUTFEEAMT).split("\\.");
 			    out.print(StringUtil.stringLeftPading(11,array2[0] + array2[1],1));//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringLeftPading(1,"0",2));//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
 				String[] array3 = df1.format(INFEEAMT).split("\\.");
 				out.print(StringUtil.stringLeftPading(11,array3[0] + array3[1],1));//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringRightPading(6,tenderItem.getTno(),2));//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
				out.print(StringUtil.stringRightPading(20,repayMent.getAuthcode(),2));//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
				out.print(StringUtil.stringRightPading(1,repayMentParamter.getEndflg(),2));//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
				out.print(StringUtil.stringRightPading(100,repayMent.getRorderno(),2));//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
  				out.print(StringUtil.stringRightPading(40,repayMent.getUtorderno(),2));//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
 				out.print(StringUtil.stringRightPading(60,"",2));//保留域	RESE	A	60	274	333	C	
				out.print("\n");
 				
 			}
		} catch (UnsupportedEncodingException e) {
 			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【提前还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByAhead 还款参数写进文件失败,失败原因："+e.getMessage());
			}
 			e.printStackTrace();
 			
		}finally{
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				
			}
			
		}
		
		if(!checkFlag){
			return checkFlag;
		}
		
		flag = true;
 		return flag;
	}
	
	/**
	 * 
	* @Title: repayMentParametersAreWrittenIntoTheFileByOverdue 
	* @Description: TODO(把还款参数写进 文件  逾期还款 ) 
	* @param @param filePathName  文件目录
	* @param @param fileName	写进的文件名
	* @param @param repayMents  还款计划
	* @param @param repayMentParamter 通用参数设置   必须设置 Batch，Type，Date，Endflg
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws   String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
	 */
	public boolean repayMentParametersAreWrittenIntoTheFileByOverdue(String filePathName,String fileName,List<RepayMent> repayMents,RepayMentParamters repayMentParamter){
		boolean flag = false;
 		if(!(repayMents.size() > 0)){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue  还款参数写进文件失败  投资人的还款计划为空");
 			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(filePathName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款参数写进文件失败,失败原因：文件目录名为空");
			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(fileName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue,还款参数写进文件失败,失败原因：文件名为空");
			}
			return flag;
		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getBatch())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款批次号为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getType())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款类型为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getDate())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款日期为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getEndflg())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款标识为空");
 			}
 			return flag;
 		}
   		
  		UserFsAccountInfo outUserFsAccountInfo = null;
 		UserFsAccountInfo inUserFsAccountInfo = null;
  		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMents.get(0).getTenderid());
 		if(tenderItem == null || tenderItem.getTno() == null){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 标的对象或标的编号为空");
 			}
 			return flag;
 		}
 		 
 		
 	 	File file = new File(filePathName);
 		if(!file.exists()){
			file.mkdirs();
		} 
 		
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(file,fileName));
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		}
		
		PrintWriter out = null;
		DecimalFormat df1 = new DecimalFormat("######################0.00");
		boolean checkFlag = true;
		try {
			out = new PrintWriter(new OutputStreamWriter(fileOutputStream,"GBK"), true);
			//新文档
			Double Ramount 		= 0.00;//还款本金
			Double Rinterest 	= 0.00;//还款利息
			Double OUTFEEAMT 	= 0.00;//转出方手续费扣款金额
			Double INFEEAMT 	= 0.00;//转入方手续费扣款金额
 			for(RepayMent repayMent : repayMents){
 				if(!repayMent.getPlanstatus().equals((short)1)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款计划是否有效状态错误,必须有效才能还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
  				
 				if(repayMent.getRepaystatus().equals((short)4) || repayMent.getRepaystatus().equals((short)3)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款计划还款状态错误,已经提交的不能再次还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				
 				if(repayMent.getRepaystatus().equals((short)5)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】  还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 还款计划还款状态错误,已经还款的不能再次还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				
 				outUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getOutaccountid());
	  			if(outUserFsAccountInfo == null || outUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 借款人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
	  			
	  			inUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getInaccountid());
	  			if(inUserFsAccountInfo == null || inUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 投资人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
	  			
	  			if(!(outUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				outUserFsAccountInfo = BaseController.getDecryptionUserFsAccountInfoDetail(outUserFsAccountInfo);//解密加密后的数据
	  			}
	  			
	  			if(!(inUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				inUserFsAccountInfo  = BaseController.getDecryptionUserFsAccountInfoDetail(inUserFsAccountInfo);//解密加密后的数据
	  			}
	  			
 	  			if(StringUtil.isEmpty(repayMent.getAuthcode())){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 投标授权码为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
 	  		 
  				if(StringUtil.isEmpty(repayMent.getUtorderno())){
  					if(logger.isDebugEnabled()){
  						logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】   还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue 投标/债权转让流水号为空,还款流水号是:"+repayMent.getRorderno());
  					}
  					file.delete();
  					checkFlag = false;
  					break;
  				}
 	  			 
	  			Ramount   =  repayMent.getRamount() + repayMent.getRvoucher() + repayMent.getDisablevoucher();//本 + 类 + 作废类
	  			Rinterest =  repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint() + repayMent.getOverdueamount();//本息 + 类息 + 作废类息 + 逾期滞纳金
	  			INFEEAMT  =  repayMent.getDisablevoucher() + repayMent.getDisablevoucherint() + repayMent.getInterestexpense();//作废类 + 作废类息 + 利息管理费
	  			
 				out.print(StringUtil.stringRightPading(6,repayMentParamter.getBatch(),2));//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
				out.print(StringUtil.stringRightPading(3,repayMentParamter.getType(),2));//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
				out.print(StringUtil.stringRightPading(8,repayMentParamter.getDate(),2));//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
				out.print(StringUtil.stringRightPading(19,outUserFsAccountInfo.getUsrcustid(),2));//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
				String[] array = df1.format(Ramount).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array[0] + array[1],1));//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
				String[] array1 = df1.format(Rinterest).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array1[0] + array1[1],1));//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
				out.print(StringUtil.stringRightPading(19,inUserFsAccountInfo.getUsrcustid(),2));//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
				out.print(StringUtil.stringRightPading(3,"156",2));//币种	CURR	N	3	80	82	M	156：人民币；
				out.print(StringUtil.stringRightPading(1,"0",2));//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
 				String[] array2 = df1.format(OUTFEEAMT).split("\\.");
 			    out.print(StringUtil.stringLeftPading(11,array2[0] + array2[1],1));//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringLeftPading(1,"0",2));//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
 				String[] array3 = df1.format(INFEEAMT).split("\\.");
 				out.print(StringUtil.stringLeftPading(11,array3[0] + array3[1],1));//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringRightPading(6,tenderItem.getTno(),2));//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
				out.print(StringUtil.stringRightPading(20,repayMent.getAuthcode(),2));//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
				out.print(StringUtil.stringRightPading(1,repayMentParamter.getEndflg(),2));//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
				out.print(StringUtil.stringRightPading(100,repayMent.getRorderno(),2));//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
  				out.print(StringUtil.stringRightPading(40,repayMent.getUtorderno(),2));//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
 				out.print(StringUtil.stringRightPading(60,"",2));//保留域	RESE	A	60	274	333	C	
				out.print("\n");
			}
		} catch (UnsupportedEncodingException e) {
 			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【逾期还款】,还款参数写进文件方法repayMentParametersAreWrittenIntoTheFileByOverdue  还款参数写进文件失败  ,失败原因："+e.getMessage());
			}
 			e.printStackTrace();
 			
		}finally{
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				
			}
			
		}
		
		if(!checkFlag){
			return checkFlag;
		}
		
		flag = true;
 		return flag;
	}
	
	
	
	/**
	 * 
	* @Title: normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 
	* @Description: TODO(把还款参数写进 文件   正常代偿还款) 
	* @param @param filePathName  文件目录
	* @param @param fileName	写进的文件名
	* @param @param repayMents  还款计划
	* @param @param repayMentParamter 通用参数设置   必须设置 Batch，Type，Date，Endflg
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws   String batchNo = StringUtil.stringLeftPading(6, repaymentnumber.nextStringValue(), 1);
	 */
	public boolean normalCompensatoryRepayMentParametersAreWrittenIntoTheFile(String filePathName,String fileName,List<RepayMent> repayMents,RepayMentParamters repayMentParamter){
		boolean flag = false;
 		if(!(repayMents.size() > 0)){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile  还款参数写进文件失败  投资人的还款计划为空");
 			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(filePathName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款参数写进文件失败,失败原因：文件目录名为空");
			}
			return flag;
		}
 		
 		if(StringUtil.isEmpty(fileName)){
			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile,还款参数写进文件失败,失败原因：文件名为空");
			}
			return flag;
		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getBatch())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款批次号为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getType())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款类型为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getDate())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款日期为空");
 			}
 			return flag;
 		}
 		
 		if(repayMentParamter == null || StringUtil.isEmpty(repayMentParamter.getEndflg())){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款标识为空");
 			}
 			return flag;
 		}
   		
  		UserFsAccountInfo inUserFsAccountInfo = null;
  		TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMents.get(0).getTenderid());
 		if(tenderItem == null || tenderItem.getTno() == null){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 标的对象或标的编号为空");
 			}
 			return flag;
 		}
 		 
 		
 		if(tenderItem.getCompensatoryman() == null || tenderItem.getCompensatoryman().length() < 19){
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 标的代偿人电子账号为空");
 			}
 			return flag;
 		}
 		
 	 	File file = new File(filePathName);
 		if(!file.exists()){
			file.mkdirs();
		} 
 		
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(file,fileName));
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		}
		
		PrintWriter out = null;
		DecimalFormat df1 = new DecimalFormat("######################0.00");
		boolean checkFlag = true;
		try {
			out = new PrintWriter(new OutputStreamWriter(fileOutputStream,"GBK"), true);
			//新文档
			Double Ramount 		= 0.00;//还款本金
			Double Rinterest 	= 0.00;//还款利息
			Double OUTFEEAMT 	= 0.00;//转出方手续费扣款金额
			Double INFEEAMT 	= 0.00;//转入方手续费扣款金额
 			for(RepayMent repayMent : repayMents){
 				if(!repayMent.getPlanstatus().equals((short)1)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】  还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款计划是否有效状态错误,必须有效才能还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
 				
 				 
 				
 				if(repayMent.getRepaystatus().equals((short)4) || repayMent.getRepaystatus().equals((short)3)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】  还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款计划还款状态错误,已经提交的不能再次还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
  				
 				if(repayMent.getRepaystatus().equals((short)5)){
 					if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】  还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 还款计划还款状态错误,已经还款的不能再次还款,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
 				}
  	  			
	  			inUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getInaccountid());
	  			if(inUserFsAccountInfo == null || inUserFsAccountInfo.getUsrcustid() == null){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】  还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 投资人第三方账号为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
  	  			if(!(inUserFsAccountInfo.getUsrcustid().length() == 19)){//电子账号 等于19位不进行解密操作
 	  				inUserFsAccountInfo  = BaseController.getDecryptionUserFsAccountInfoDetail(inUserFsAccountInfo);//解密加密后的数据
	  			}
	  			if(StringUtil.isEmpty(repayMent.getAuthcode())){
	  				if(logger.isDebugEnabled()){
	  	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】   还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 投标授权码为空,还款流水号是:"+repayMent.getRorderno());
	  	 			}
	  				file.delete();
	  				checkFlag = false;
 	  				break;
	  			}
 	  			
  				if(StringUtil.isEmpty(repayMent.getUtorderno())){
  					if(logger.isDebugEnabled()){
  						logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】   还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile 投标/债权转让流水号为空,还款流水号是:"+repayMent.getRorderno());
  					}
  					file.delete();
  					checkFlag = false;
  					break;
  				}
	  			 
	  			 
	  			Ramount   =  repayMent.getRamount() + repayMent.getRvoucher() + repayMent.getDisablevoucher();//本 + 类 + 作废类
	  			Rinterest =  repayMent.getRinterest() + repayMent.getRvoucherint() + repayMent.getDisablevoucherint();//本息 + 类息 + 作废类息
	  			INFEEAMT  =  repayMent.getDisablevoucher() + repayMent.getDisablevoucherint() + repayMent.getInterestexpense();//作废类 + 作废类息 + 利息管理费
	  			
 				out.print(StringUtil.stringRightPading(6,repayMentParamter.getBatch(),2));//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
				out.print(StringUtil.stringRightPading(3,repayMentParamter.getType(),2));//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
				out.print(StringUtil.stringRightPading(8,repayMentParamter.getDate(),2));//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
				out.print(StringUtil.stringRightPading(19,tenderItem.getCompensatoryman(),2));//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
				String[] array = df1.format(Ramount).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array[0] + array[1],1));//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
				String[] array1 = df1.format(Rinterest).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array1[0] + array1[1],1));//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
				out.print(StringUtil.stringRightPading(19,inUserFsAccountInfo.getUsrcustid(),2));//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
				out.print(StringUtil.stringRightPading(3,"156",2));//币种	CURR	N	3	80	82	M	156：人民币；
				out.print(StringUtil.stringRightPading(1,"0",2));//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
 				String[] array2 = df1.format(OUTFEEAMT).split("\\.");
 			    out.print(StringUtil.stringLeftPading(11,array2[0] + array2[1],1));//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringLeftPading(1,"0",2));//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
 				String[] array3 = df1.format(INFEEAMT).split("\\.");
 				out.print(StringUtil.stringLeftPading(11,array3[0] + array3[1],1));//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				out.print(StringUtil.stringRightPading(6,tenderItem.getTno(),2));//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
				out.print(StringUtil.stringRightPading(20,repayMent.getAuthcode(),2));//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
				out.print(StringUtil.stringRightPading(1,repayMentParamter.getEndflg(),2));//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
				out.print(StringUtil.stringRightPading(100,repayMent.getRorderno(),2));//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
  				out.print(StringUtil.stringRightPading(40,repayMent.getUtorderno(),2));//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
 				out.print(StringUtil.stringRightPading(60,"",2));//保留域	RESE	A	60	274	333	C	
				out.print("\n");
			}
		} catch (UnsupportedEncodingException e) {
 			if(logger.isDebugEnabled()){
				logger.debug("ThirdRepayMentDealImpl.java 到期还款【正常代偿还款】 还款参数写进文件失败,还款参数写进文件方法normalCompensatoryRepayMentParametersAreWrittenIntoTheFile   失败原因："+e.getMessage());
			}
 			e.printStackTrace();
 			
		}finally{
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				
			}
			
		}
		
		if(!checkFlag){
			return checkFlag;
		}
		
		flag = true;
 		return flag;
	}
	
	/**
	 * 
	* @Title: repayMentFileUpload 
	* @Description: TODO(到期还款 文件上传处理接口) 
    * @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws    
	 */
	@Override
	public boolean repayMentFileUpload(BacthFileRecord bacthFileRecord) {
		boolean flag = false;
		if(bacthFileRecord == null 
				|| bacthFileRecord.getIsSend() == null
				|| StringUtil.isEmpty(bacthFileRecord.getFilePath())
				|| StringUtil.isEmpty(bacthFileRecord.getSendFileName())
				|| bacthFileRecord.getIsSend().equals((short)1)
				 ){
			 return flag;
		}
		 
		String fileName = bacthFileRecord.getSendFileName();
		Map<String,Object> hashMap = null;
		try {
			hashMap = BacthRepayMentController.repayMentFileUpload(bacthFileRecord.getFilePath(), bacthFileRecord.getSendFileName(), fileName.substring(fileName.length() - 8,fileName.length()));
		} catch (NoSuchAlgorithmException e) {
 			e.printStackTrace();
		} catch (Exception e) {
 			e.printStackTrace();
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java ,文件上传处理接口repayMentFileUpload ,到期还款文件上传失败，上传的文件是："+bacthFileRecord.getSendFileName());
 			}
 			return flag;
  		}
		if(hashMap.size() > 0){
			flag = (boolean) hashMap.get(BacthRepayMentController.REPAYMENTFLAG);
			if(flag){
				
				RepaymentFrz repaymentFrz1 = new RepaymentFrz();
				repaymentFrz1.setBatchno(bacthFileRecord.getBatchNo());//批次号
				RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz1);
				if(repaymentFrz == null){
					if(logger.isDebugEnabled()){
		 				logger.debug("ThirdRepayMentDealImpl.java,文件上传处理接口repayMentFileUpload , 批次还款信息 'repaymentFrz' 找不到！");
		 			}
					return false;
				}
				
				Map<String,Object> maps = new HashMap<>();
 				maps.put("rbatchno", repaymentFrz.getBatchno());//批次号
				maps.put("repaystatus", (short)3);//1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败 7审核失败
				maps.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
 				List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(maps);
 				if(!(repayMents.size() > 0)){
 					if(logger.isDebugEnabled()){
		 				logger.debug("ThirdRepayMentDealImpl.java,文件上传处理接口 repayMentFileUpload , 具体还款计划信息 'repayMents' 找不到！");
		 			}
					return false;
 				}
 				RepaymentDetail repaymentDetail = new RepaymentDetail();
 				repaymentDetail.setRbatchno(repaymentFrz.getBatchno());//批次号
  				List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
  				if(!(repayMents.size() > 0)){
 					if(logger.isDebugEnabled()){
		 				logger.debug("ThirdRepayMentDealImpl.java,文件上传处理接口 repayMentFileUpload , 批次还款明细计划信息 'repayMentDetails' 找不到！");
		 			}
					return false;
 				}
  				
				updateAllRepayStatusForDealing(repayMents, repayMentDetails, repaymentFrz);//修改状态为处理中
				
 				bacthFileRecord.setIsSend((short)1);//是否已发送到银行     0.否   1.是
				bacthFileRecord.setSendResult((short)1);//发送结果（是否成功）   0.失败    1.成功 
 				bacthFileRecordServiceI.update(bacthFileRecord);//更新
 				
			}else{
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款文件上传失败，上传的文件是："+bacthFileRecord.getSendFileName());
	 			}
			}
		}
   		return flag;
	}

	/**
	 * 
	* @Title: repayMentFileDeal 
	* @Description: TODO(到期还款 文件下载处理) 
 	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws   
	 */
	@Override
	synchronized public boolean repayMentFileDeal(BacthFileRecord bacthFileRecord) {
		boolean flag = false;
		if(bacthFileRecord == null 
				|| bacthFileRecord.getIsSend() == null
				|| StringUtil.isEmpty(bacthFileRecord.getFilePath())
				|| StringUtil.isEmpty(bacthFileRecord.getGetFileName())
				|| StringUtil.isEmpty(bacthFileRecord.getSendFileName())
				|| bacthFileRecord.getIsDealResult().equals((short)1)
				 ){
			 return flag;
		}
		String fileName = bacthFileRecord.getSendFileName();
		Map<String,Object> hashMap = null;
		try {
			//文件下载
			hashMap = BacthRepayMentController.repayMentDownload(bacthFileRecord.getFilePath(), bacthFileRecord.getGetFileName(), fileName.substring(fileName.length() - 8,fileName.length()));  
		} catch (Exception e) {
 			e.printStackTrace();
 			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 到期还款文件下载处理失败，下载的文件是："+bacthFileRecord.getGetFileName()+"失败原因："+e.getMessage());
 			}
 			return flag;
		}
		
		if(hashMap.size() > 0){
			flag = (boolean) hashMap.get(BacthRepayMentController.REPAYMENTFLAG);
			String upResultCode = (String) hashMap.get(BacthRepayMentController.REPAYMENTCODE);
			if(upResultCode != null){
 				bacthFileRecord.setDownResultCode(upResultCode);//银行返回码  下载文件
				bacthFileRecordServiceI.update(bacthFileRecord);
			}
			
			if(!flag){
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 到期还款文件下载处理失败，下载的文件是："+bacthFileRecord.getGetFileName());
	 			}
				return flag;
			}
			
			List<RepayMentParamters> repayMentParamters = null;
			try {
				  //获取下载文件参数
				  repayMentParamters = BacthRepayMentController.getRepayMentParamtersByDoladFileName(bacthFileRecord.getFilePath(), bacthFileRecord.getGetFileName());
			} catch (IOException e) {
 				e.printStackTrace();
 				return flag;
			} 
			
 			if(repayMentParamters.size() > 0){
 				String thdrese = "";//还款流水号
 				String rspcode = "";//处理响应码	00  成功
 				RepayMent repayMent = null;
  				Set<RepayMent> repayMentSet = new HashSet<>();
  				String rbatchno = "";//批次号
 				for(RepayMentParamters mentParamters : repayMentParamters){
 					 rspcode = mentParamters.getRspcode();
					 thdrese = mentParamters.getThdrese();
					 repayMent = repayMentServiceI.findRepayMentByRorderno(thdrese.trim());
					 if(repayMent != null){
						 //批次详情信息
						 RepaymentDetail repaymentDetail2 = new RepaymentDetail ();
						 repaymentDetail2.setRorderno(repayMent.getRorderno());//还款流水号
						 repaymentDetail2.setRbatchno(repayMent.getRbatchno());//还款批次号
						 RepaymentDetail repaymentDetail = repaymentDetailServiceI.findRepaymentDetail(repaymentDetail2);
						 
						 if(StringUtil.isEmpty(rbatchno)){
							 rbatchno = repayMent.getRbatchno();//批次号
						 }
						 
						 if(repayMent.getRepaystatus().equals((short)4)){//1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败
							 if(rspcode.equals("00")){//还款成功
								 repayMent.setRepaystatus((short)5);
								 repayMent.setRetcode(rspcode);
								 repayMent.setRprealtime(new Date());/*实际到账日期*/
								 
								 if(repaymentDetail != null){
									 repaymentDetail.setRetcode(rspcode);
									 repaymentDetail.setRepaystatus((short)5);
									 repaymentDetailServiceI.updateById(repaymentDetail);
								 } 
								 
								 int count = 0;
								 count = repayMentServiceI.updateById(repayMent);
								 if(count > 0){
 									 try{
 										InterestExpenseRecord record = new InterestExpenseRecord();
 										record.setRorderno(repayMent.getRorderno());//还款流水号
 										record.setBatchno(repayMent.getRbatchno());//批次号
 										InterestExpenseRecord record2 = interestExpenseRecordServiceI.findInterestExpenseRecord(record);
 										if(record2 != null){
 											record2.setIsdeal((short)1);//是否处理（0否，1是,2处理中）
 											record2.setPayoutdate(new Date());//清算时间
 											interestExpenseRecordServiceI.updateById(record2);
 										}
 									 }catch(Exception e){
										 e.printStackTrace();
									 }
									 
									 if(repayMent.getIsproxyrepay().equals((short)1)){//是否代偿（1是，0否）
										 
									 }else if(repayMent.getIsoverdue().equals((short)1)){//是否逾期 0否，1是
										 repayMentOverDueInsertAccInExRecordI.InsertOverdueRepayMentCountAccInExRecord(repayMent);//添加逾期还款收支记录
	 									 Double amount = Double.valueOf(df1.format(
												 repayMent.getRamount() + repayMent.getRinterest() +
												 repayMent.getRvoucher() + repayMent.getRvoucherint()));
	 									 try{
 											 sendSSMByRepayMent(repayMent, amount.toString());//短信通知
 										 }catch(Exception e){
											 e.printStackTrace();
										 }
										 
 									 }else if(repayMent.getIsahead().equals((short)1)){//是否提前（0否，1是）
										 AheadRealRepayment aheadRealRepayment = aheadRealRepaymentServiceI.
												 findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(), repayMent.getRbatchno());
										 repayMent.setAheadRealRepayment(aheadRealRepayment);
										 repayMentAheadInsertAccInExRecordI.InsertAheadRepayMentCountAccInExRecord(repayMent);//添加提前还款收支记录
										 Double amount = Double.valueOf(df1.format(
												 repayMent.getAheadRealRepayment().getRptotalamount() + repayMent.getAheadRealRepayment().getRvoucher() +
												 repayMent.getAheadRealRepayment().getRinterest() + repayMent.getAheadRealRepayment().getRvoucherint()));
										 try{
 											 sendSSMByRepayMent(repayMent, amount.toString());//短信通知
 										 }catch(Exception e){
											 e.printStackTrace();
										 }
										 
									 }else if(repayMent.getIsproxyrepay().equals((short)0)
											 && repayMent.getIsoverdue().equals((short)0)
											 && repayMent.getIsahead().equals((short)0)){//正常还款
 										 repaymentInsertAccInExRecordI.InsertNormalRepayMentCountAccInExRecord(repayMent);//添加正常还款收支记录
										 Double amount = Double.valueOf(df1.format(
												 repayMent.getRamount() + repayMent.getRinterest() +
												 repayMent.getRvoucher() + repayMent.getRvoucherint()));
										 try{
											 sendSSMByRepayMent(repayMent, amount.toString());//短信通知
 										 }catch(Exception e){
											 e.printStackTrace();
										 }
									 }
								 }
 							 }else{
								 repayMent.setRepaystatus((short)6);
								 repayMent.setRetcode(rspcode);
								 repayMentServiceI.updateById(repayMent);
								 
								 if(repaymentDetail != null){
									 repaymentDetail.setRetcode(rspcode);
									 repaymentDetail.setRepaystatus((short)6);
									 repaymentDetailServiceI.updateById(repaymentDetail);
								 }  
							 }
   						 }  
						 
						 if(repayMentSet.size() > 0){//这里添加RepayMent用于后续的借款人还款状态和标的状态更新
			      			 for(RepayMent ment4 : repayMentSet){
			     				 if(!repayMent.getTenderid().equals(ment4.getTenderid())){
			     					 if(!repayMent.getPeriods().equals(ment4.getPeriods())){
			      						 repayMentSet.add(repayMent);
			     					 }
			     				 }
			     			 }
			     		 }else{
			     			repayMentSet.add(repayMent);
			     		 }
						 
 					 }
				}
				
				if(repayMentSet.size() > 0){//这里进行 借款人还款计划的还款状态更新
 					for(RepayMent repayMent2 : repayMentSet){
 						updateDividedPaymentsByRepayMent(repayMent2.getTenderid(), repayMent2.getPeriods());//更新借款人的还款状态和标的还款状态
					}
				}
				
				if(StringUtil.isNotEmpty(rbatchno)){//更新批次处理状态
					updateAllRepayStatus(rbatchno);
				} 
				
				bacthFileRecord.setDealTime(new Date());//处理文件时间（到银行获取文件）
 				bacthFileRecord.setIsDealResult((short)1);//是否已处理结果文件     0.否   1.是
				bacthFileRecord.setDealResult((short)1);///处理结果（是否成功）0.否  1.是
				int count = 0;
				count = bacthFileRecordServiceI.update(bacthFileRecord);
				if(count > 0){
					flag = true;
 				}
				
			}
 		}
  		return flag;
	}

	
	private void updateAllRepayStatus(String rbatchno){
		RepaymentFrz repaymentFrz = new RepaymentFrz();
		repaymentFrz.setBatchno(rbatchno);//还款批次号
		RepaymentFrz repaymentFrz2 = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz);
		if(repaymentFrz2 != null){
			RepaymentDetail repaymentDetail = new RepaymentDetail();
			repaymentDetail.setRbatchno(rbatchno);//还款批次号
			List<RepaymentDetail> repaymentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
			int successCount = 0;//成功条数
			int failCount = 0;//失败条数
			if(repaymentDetails.size() > 0){
				for(RepaymentDetail repaymentDetail2 : repaymentDetails){
					if(repaymentDetail2.getRepaystatus().equals((short)5)){//1待还款  2审核中 3待处理  4处理中  5已还款 6还款失败
						successCount ++;
					}
				}
				
				failCount = repaymentDetails.size() - successCount;
				repaymentFrz2.setSuccesscount(successCount);
				repaymentFrz2.setFailcount(failCount);
				repaymentFrz2.setStatus((short)6);//0初始 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8废弃 )
				repaymentFrzServiceI.updateById(repaymentFrz2);
			}
		}
	}
	
	/**
	 * 
	* @Title: updateDividedPaymentsByRepayMent 
	* @Description: TODO(判断 是否还款完成 ) 
 	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws    
	 */
	@Override
	public void updateDividedPaymentsByRepayMent(BigDecimal tenderid,Integer periods) {
 		 Assert.notNull(tenderid);
		if(tenderid == null){
			throw new IllegalArgumentException("tenderid 不能为空");
 		}
		
		if(periods == null || periods < 0){
			throw new IllegalArgumentException("tenderid 不能为空");
		}
		
 		Map<String,Object> hashMap = new HashMap<String,Object>();
 		hashMap.put("tenderid", tenderid);//标号ID
		hashMap.put("periods", periods);//还款期数（第几期）
		hashMap.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
		List<RepayMent> repayMents3 = null;
 		repayMents3 =  repayMentServiceI.findListRepayMentByConditions(hashMap);
 		Map<String,Object> hashMap2 = new HashMap<String,Object>();
 		hashMap2.put("tenderid", tenderid);
 		hashMap2.put("periods", periods);
  		DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap2);
 		if(repayMents3.size() > 0 && dividedPayments != null){
 			boolean partFlag = false;
 			boolean allFlag  = false;
 			for(RepayMent ment : repayMents3){
  				if(ment.getRepaystatus().equals((short)5)){
 					if(!partFlag){
 						partFlag = true;
 					}
 				}else{
 					if(!allFlag){
 						allFlag = true;
 					}
 				}
  			}
 			
 			if(partFlag && allFlag){//部分还款
 				dividedPayments.setIscomplete((short)2);    /*是否还款完成0未还款 1已还款2部分还款(标的截标时生成时默认0)*/
 				dividedPaymentsServiceI.updateById(dividedPayments);
  			}else if(partFlag && !allFlag){//全部还款
   				dividedPayments.setIscomplete((short)1);    /*是否还款完成0未还款 1已还款 2部分还款(标的截标时生成时默认0)*/
 				dividedPaymentsServiceI.updateById(dividedPayments);
  			}
 			
 			TenderItem tenderItem = tenderItemServiceI.findTenderItemById(tenderid);
 			if(tenderItem == null || tenderItem.getTstatus().equals((short)8)){//还款已完成
 				return ;
 			}
 			
 			DividedPayments dividedPayments2 = new DividedPayments();
 			dividedPayments2.setTenderid(tenderid);
 			List<DividedPayments> dividedPayments3 = dividedPaymentsServiceI.findDividedPaymentss(dividedPayments2);
 			if(dividedPayments3.size() > 0){
 				boolean divPartFlag = false;
 	 			boolean divAllFlag  = false;
 				for(DividedPayments dividedPayments4 : dividedPayments3){
 					if(dividedPayments4.getIscomplete().equals((short)1)){/*是否还款完成0未还款 1已还款 2部分还款(标的截标时生成时默认0)*/
  	 					if(!divPartFlag){
  	 						divPartFlag = true;
 	 					}
 	 				}else{
 	 					if(!divAllFlag){
 	 						divAllFlag = true;
 	 					}
 	 				}
 				}
 				
 				 if(divPartFlag && !divAllFlag){//全部还款
  	   				 tenderItem.setTstatus((short)8);//完成还款
 	   				 tenderItemServiceI.update(tenderItem);
 	  			}
 			}
 		}
	}
	
	/**
	 * 
	* @Title: timTaskRepayMent 
	* @Description: TODO(定时还款任务) 
	* @param     设定文件 
	* @return void    返回类型 
	* @author   cjm  
	* @throws
	 */
	@Override
	synchronized public void timTaskRepayMent(){
		//查询符合条件的标的信息
		TenderItem tenderItem = new TenderItem();
		tenderItem.setRepaysetmode((short)1);//还款设置方式（0手动，1自动）
		tenderItem.setTstatus((short)7);//还款中
 		List<TenderItem> tenderItems = tenderItemServiceI.selectByCondition(tenderItem);
		if(!(tenderItems.size() > 0 )){
			if(logger.isDebugEnabled()){
 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款没有找到符合条件的标的信息！不继续执行自动还款");
			}
			return ;
		}
		Date date = new Date();//当期时间
		Date datePoint  = StringUtil.stringforDatePoint(StringUtil.formatDate(date, "hh:mm:ss"));//当期执行时间点
				
		//筛选符合条件的还款计划信息
		for(TenderItem tenderItem2 : tenderItems){
			if(tenderItem2.getRepaytimepoint() == null ) {
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款没有找到标的还款时间点！不继续执行自动还款！！标号是："+tenderItem2.getTno());
				}
				continue;//跳出本次自动还款  
			}
			
			if(tenderItem2.getAheadperiod() == null ) {//还款宽限期(还款日往前)单位：天
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款没有找到标的还款宽限期(还款日往前)！不继续执行自动还款！！标号是："+tenderItem2.getTno());
				}
				continue;//跳出本次自动还款  
			}
			
			if(tenderItem2.getGraceperiod() == null ) {//逾期宽限期(还款日第二天算起)
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款没有找到标的逾期宽限期(还款日第二天算起)！不继续执行自动还款！！标号是："+tenderItem2.getTno());
				}
				continue;//跳出本次自动还款  
			}
			
			if(tenderItem2.getCompensatoryman() == null ) {//代偿人
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款,没有找到标的代偿人！不继续执行自动还款！！标号是："+tenderItem2.getTno());
				}
				continue;//跳出本次自动还款  
			}
			
			if(datePoint.getTime() < StringUtil.stringforDatePoint(tenderItem2.getRepaytimepoint()).getTime()){ 
				continue;//设置的标的还款时间点在当前时间点之后  跳出本次自动还款  
			}
			
			DividedPayments dividedPayments = getTheCurrentPeriodDividedPayments(tenderItem2, date);
			if(dividedPayments == null){
				continue;//跳出本次自动还款  
			}
			
			boolean falg =  repayMentBaseDealI.checkDividedPaymentsNormalRepayMent(dividedPayments);
			if(!falg){//存在跳期还款
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,存在跳期还款！不继续执行自动还款！！标号是："+tenderItem2.getTno()+"标的还款流水号是："+dividedPayments.getDporderno());
				}
				continue;//跳出本次自动还款  
			}
  					
			Map<String,Object> maps = new HashMap<>();
			maps.put("tenderid", dividedPayments.getTenderid());//标号ID
			maps.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
			maps.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
			maps.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
			List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(maps);
			if(!(repayMents.size() > 0)){
				continue;//跳出本次自动还款  
			}
			
			 
			Double totalAmount = 0.00;//本次还款总金额
 			for(RepayMent ment : repayMents){
  				totalAmount = Double.valueOf(df1.format(ment.getRamount() + ment.getRvoucher() + ment.getDisablevoucher() +
  						ment.getRinterest() + ment.getRvoucherint() + ment.getDisablevoucherint() + totalAmount));
			}
 			
 			String rbatchno = StringUtil.getNo();//还款批次号
 			/*这里进行 次数和时间间隔 逻辑判断*/
			if(dividedPayments.getRptimes() != null 
					&& dividedPayments.getRptimes() > 0
					&& dividedPayments.getOperatetime() != null){//提交次数大于0 进行次数比较和判断 ，超出次数限制，调用代偿还款。
 				List<GlobalSetting> globalSettings = globalSettingServiceImpl.allData();
	 			 
 				if(!(globalSettings.size() > 0)){
 					if(logger.isDebugEnabled()){
 		 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,全局设置信息找不到！不继续执行自动还款！！标号是："+tenderItem2.getTno()+"标的还款流水号是："+dividedPayments.getDporderno());
 					}
 	 				continue;//跳出本次自动还款  
 				}
 				
 				GlobalSetting globalSetting = globalSettings.get(0);
 				if(globalSetting.getAutorptimesltd() == null || globalSetting.getAutorpstinvl() == null){
 					if(logger.isDebugEnabled()){
 		 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,系统自动提交还款次数限制信息或系统自动提交还款提交间隔（单位 分钟）信息找不到！不继续执行自动还款！！标号是："+tenderItem2.getTno()+"标的还款流水号是："+dividedPayments.getDporderno());
 					}
 	 				continue;//跳出本次自动还款  
 				}
 				
 				long autorpstinvl = (globalSetting.getAutorpstinvl() * 60 * 1000 );//时间间隔
 				if(date.getTime() - dividedPayments.getOperatetime().getTime() < autorpstinvl){//提交的时间间隔不符合条件  不继续执行自动还款
 					continue;
 				}
  				
 				if(globalSetting.getAutorptimesltd() > dividedPayments.getRptimes()){//自动还款提交失败次数超过系统设置的次数限制 ，调用代偿人还款
 					if(tenderItem2.getIscompensatory() != null && tenderItem2.getIscompensatory().equals((short)1)){//正常还款代偿开关(1:开,0:关)
 						CompensatoryRepayMentTask(repayMents, dividedPayments,rbatchno ,tenderItem2.getCompensatoryman(), totalAmount.toString(), 
 								tenderItem2.getTno(),tenderItem2.getRepaymenttype());//调用代偿还款
   					}
 					continue;//不往下继续执行代码 跳出本次自动还款
 				}
 				
			}
			 
 			UserFsAccountInfo accountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMents.get(0).getOutaccountid());//借款人银行电子账号
 			if(accountInfo == null || accountInfo.getUsrcustid() == null){
 				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,借款人第三方电子账号信息找不到！不继续执行自动还款！！标号是："+tenderItem2.getTno()+"标的还款流水号是："+dividedPayments.getDporderno());
				}
 				continue;//跳出本次自动还款  
 			}
  
 			accountInfo = getDecryptionUserFsAccountInfoDetail(accountInfo);//解密加密的数据
  			Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(accountInfo.getUsrcustid(),totalAmount.toString(), tenderItem2.getTno(), rbatchno,(short)0,(short)0,(short)0);
 			boolean fg = (boolean) resMap.get(RepayMent_Constant.FALG);
 			String tre = (String) resMap.get(RepayMent_Constant.RESULT);
 			if(!fg){
 				if(tre.equals("retcodeFlag")){
 					if(logger.isDebugEnabled()){
 		 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,还款冻结失败！失败原因："+resMap.get(RepayMent_Constant.MSG)+",标号是："+tenderItem2.getTno()+"标的还款流水号是："+dividedPayments.getDporderno());
 					}
 					
 					
   					//这里进行短信通知借款人
 					
 					
 					//这里进行失败记录
 					if(dividedPayments.getRptimes() == null){
 						dividedPayments.setRptimes(1);/*提交还款次数*/
 						dividedPayments.setOperatetime(new Date());/*提交还款时间（具体操作的时间）*/
 						dividedPaymentsServiceI.updateById(dividedPayments);
 					}else {
 						if(dividedPayments.getRptimes() == 0){
 							dividedPayments.setRptimes(1);/*提交还款次数*/
 	 						dividedPayments.setOperatetime(new Date());/*提交还款时间（具体操作的时间）*/
 	 						dividedPaymentsServiceI.updateById(dividedPayments);
 						}else{
  							dividedPayments.setRptimes(dividedPayments.getRptimes() + 1);/*提交还款次数*/
 							dividedPayments.setOperatetime(new Date());/*提交还款时间（具体操作的时间）*/
 							dividedPaymentsServiceI.updateById(dividedPayments);
 						}
  					}
 					
  	 				continue;//跳出本次自动还款  
 	 				
 				}else{//提示：操作失败,因网络响应不及时,还款冻结失败,请重新操作或联系客服！
 					if(logger.isDebugEnabled()){
 		 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,因网络响应不及时,借款人还款冻结失败！不继续执行自动还款！！标号是："+tenderItem2.getTno()+"标的还款流水号是："+dividedPayments.getDporderno());
 					}
 					
 					//这里进行失败记录
 	 				continue;//跳出本次自动还款  
 	 				
 				}
  			}
 			 
  			 Date submittime = new Date();
  			 for(RepayMent repayMent : repayMents){
 				repayMent.setIsproxyrepay((short)1);//是否代偿（1是，0否）
         		repayMent.setIsahead((short)0);//是否提前（0否，1是）
        		repayMent.setSubmittime(submittime);//借款人提交还款时间  注这个时间同批次提交的时候添加必须一致  
        		repayMent.setAuditman("系统");//审核人  及时还款 写系统
        		repayMent.setAudittime(new Date());//审核时间 
        		repayMent.setRbatchno(rbatchno);//还款批次号
        		repayMent.setRmode((short)2);//还款模式（0初始  1人工，2系统，3线下） 
         		repayMentServiceI.updateById(repayMent);
         		
         		repayMentBaseDealI.calculateNormalRepayemInterestManageFee(repayMent);//计算利息管理费并更新
         		
         		RepaymentDetail repaymentDetail = repayMentBaseDealI.copyRepaymentDetailByRepayMent(repayMent);//生成快照
        		if(repaymentDetail != null){
        			repaymentDetailServiceI.insertSelective(repaymentDetail);//添加批次还款详情快照
        		}
 			 }
  			 
 			settingUpBatchNormalRepayMent(repayMents, rbatchno, accountInfo.getUsrcustid(), totalAmount.toString(), tenderItem2.getTno());//借款人提交还款
 		}
 		 
	}
	 
	/**
	 * 人工解冻处理
	 * @return
	 */
	@Override
	public Map<String,Object> unfreezeDeal(RepaymentFrz repayMentFrz){
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put(RepayMent_Constant.FALG, false);
		hashMap.put(RepayMent_Constant.MSG, "解冻失败！");
		hashMap.put(RepayMent_Constant.RESULT, "fail");
  		if(repayMentFrz != null){
			if(repayMentFrz.getStatus().equals((short)0) || repayMentFrz.getStatus().equals((short)1)){
 				Map<String,Object> resMap = HSRepayMentFreezeController.repayMentunFreeze(repayMentFrz.getCardnbr(), repayMentFrz.getAmount().toString(), repayMentFrz.getProduct(), 
						repayMentFrz.getBatchno(), repayMentFrz.getSerino());//解冻接口调用
			   boolean flag2 = (boolean) resMap.get(RepayMent_Constant.FALG);
			   if(!flag2){//解冻失败
				   try{
 					   if(resMap.get(RepayMent_Constant.RESULT).equals("retcodeFlag")){
 						  if(resMap.get(RepayMent_Constant.RETCODE).equals("CA110112")){//订单未存在
 							 if(StringUtil.isNotEmpty(repayMentFrz.getBatchno())){
 								   Map<String,Object> maps = new HashMap<>();
 								   maps.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
 								   maps.put("rbatchno",  repayMentFrz.getBatchno());//还款批次号
 								   List<RepayMent> repayMentS = repayMentServiceI.findListRepayMentByConditions(maps);
 								   if(repayMentS.size() > 0){
 									   for(RepayMent repayMent2 : repayMentS){
 										   if(repayMent2.getRepaystatus().equals((short)2)){
 											   repayMent2.setRepaystatus((short)1);
 											   repayMentServiceI.updateById(repayMent2);
 										   }
 									   }
 								   }
 				 				}
 				 				   
 				 			   //解冻成功后进行更新
 							   repayMentFrz.setStatus((short)8);//0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8解冻成功 
 							   repayMentFrz.setIsmanblending((short)1);//是否人工勾兑
 							   repayMentFrz.setManbtime(new Date());//人工勾兑时间
 							   repaymentFrzServiceI.updateById(repayMentFrz);
								hashMap.put(RepayMent_Constant.FALG, true);
								hashMap.put(RepayMent_Constant.MSG, "解冻成功！");
								hashMap.put(RepayMent_Constant.RESULT, "success");
 							   return hashMap;
 						  }
					   }
				   }catch(Exception e){
					   e.printStackTrace();
				   }
 				   return resMap;
			   } 
			   
			   if(StringUtil.isNotEmpty(repayMentFrz.getBatchno())){
				   Map<String,Object> maps = new HashMap<>();
				   maps.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
				   maps.put("rbatchno",  repayMentFrz.getBatchno());//还款批次号
				   List<RepayMent> repayMentS = repayMentServiceI.findListRepayMentByConditions(maps);
				   if(repayMentS.size() > 0){
					   for(RepayMent repayMent2 : repayMentS){
						   if(repayMent2.getRepaystatus().equals((short)2)){
							   repayMent2.setRepaystatus((short)1);
							   repayMentServiceI.updateById(repayMent2);
						   }
					   }
				   }
 				}
 				   
 			   //解冻成功后进行更新
			   repayMentFrz.setStatus((short)8);//0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8解冻成功 
			   repayMentFrz.setIsmanblending((short)1);//是否人工勾兑
			   repayMentFrz.setManbtime(new Date());//人工勾兑时间
			   repaymentFrzServiceI.updateById(repayMentFrz);
 			   return resMap;
 			}
  		}
 		return hashMap;
	}
	
	/**
	 * 调用代偿还款
	 * @param repayMents
	 * @param rbatchno
	 * @param Usrcustid
	 * @param totalAmount
	 * @param Tno
	 */
	synchronized public void CompensatoryRepayMentTask(List<RepayMent> repayMents,DividedPayments dividedPayments,String rbatchno,String Usrcustid,String totalAmount,String Tno,Short repayMentType){
		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(Usrcustid,totalAmount.toString(), Tno, rbatchno,(short)0,(short)1,(short)0);
		boolean fg = (boolean) resMap.get(RepayMent_Constant.FALG);
		String tre = (String) resMap.get(RepayMent_Constant.RESULT);
		if(!fg){
			if(tre.equals("retcodeFlag")){
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,代偿人还款冻结失败！失败原因："+resMap.get(RepayMent_Constant.MSG)+",标号是："+Tno+",标的还款流水号是："+dividedPayments.getDporderno());
				}
				
				//这里进行失败记录
				if(dividedPayments.getRptimes() == null){
					dividedPayments.setRptimes(1);/*提交还款次数*/
					dividedPayments.setOperatetime(new Date());/*提交还款时间（具体操作的时间）*/
					dividedPaymentsServiceI.updateById(dividedPayments);
				}else {
					if(dividedPayments.getRptimes() == 0){
						dividedPayments.setRptimes(1);/*提交还款次数*/
 						dividedPayments.setOperatetime(new Date());/*提交还款时间（具体操作的时间）*/
 						dividedPaymentsServiceI.updateById(dividedPayments);
					}else{
 						dividedPayments.setRptimes(dividedPayments.getRptimes() + 1);/*提交还款次数*/
						dividedPayments.setOperatetime(new Date());/*提交还款时间（具体操作的时间）*/
						dividedPaymentsServiceI.updateById(dividedPayments);
					}
					
				}
 				
			}else{//提示：操作失败,因网络响应不及时,还款冻结失败,请重新操作或联系客服！
				if(logger.isDebugEnabled()){
	 				logger.debug("ThirdRepayMentDealImpl.java 定时自动还款 ,因网络响应不及时,代偿人还款冻结失败！失败原因："+resMap.get(RepayMent_Constant.MSG)+",标号是："+Tno+",标的还款流水号是："+dividedPayments.getDporderno());
				}
			}
		}
		
		if(fg){
			 Date submittime = new Date();
  			 for(RepayMent repayMent : repayMents){
 				repayMent.setIsproxyrepay((short)1);//是否代偿（1是，0否）
         		repayMent.setIsahead((short)0);//是否提前（0否，1是）
        		repayMent.setSubmittime(submittime);//借款人提交还款时间  注这个时间同批次提交的时候添加必须一致  
        		repayMent.setAuditman("系统");//审核人  及时还款 写系统
        		repayMent.setAudittime(new Date());//审核时间 
        		repayMent.setRbatchno(rbatchno);//还款批次号
        		repayMent.setRmode((short)2);//还款模式（0初始  1人工，2系统，3线下） 
         		repayMentServiceI.updateById(repayMent);
         		
         		repayMentBaseDealI.calculateNormalRepayemInterestManageFee(repayMent);//计算利息管理费并更新
         		
         		RepaymentDetail repaymentDetail = repayMentBaseDealI.copyRepaymentDetailByRepayMent(repayMent);//生成快照
        		if(repaymentDetail != null){
        			repaymentDetailServiceI.insertSelective(repaymentDetail);//添加批次还款详情快照
        		}
 			 }
 			//这里调用代偿人还款  
			settingUpBatchNormalCompensatoryRepayMent(repayMents, rbatchno, Usrcustid, totalAmount.toString(), Tno);//调用代偿还款
		}
		
	}
	  
	/**
	 * 根据标的信息和当前时间获取当期标的还款计划
	 * @param tenderItem
	 * @param date
	 * @return
	 */
	public DividedPayments getTheCurrentPeriodDividedPayments(TenderItem tenderItem,Date date){
		if(tenderItem == null || date == null){
			return null;
		}
		
		Date dateFormat = StringUtil.stringforDateTwo(StringUtil.formatDate(date, "yyyy-MM-dd"));//格式化当前日期
		DividedPayments dividedPayment = new DividedPayments();
		dividedPayment.setTenderid(tenderItem.getId());
		dividedPayment.setIscomplete((short)0);/*是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0)*/
		List<DividedPayments> dividedPayments = dividedPaymentsServiceI.findDividedPaymentss(dividedPayment);
		Collections.sort(dividedPayments,new DividedPaymentsComparator());//排序
		DividedPayments dividedPayments2 = null;
		if(dividedPayments.size() > 0){
			for(DividedPayments dividedPayments3 : dividedPayments){
				Date DivDate = StringUtil.stringforDateTwo(StringUtil.formatDate(dividedPayments3.getRepayday(), "yyyy-MM-dd"));
				if(DivDate.getTime() == dateFormat.getTime()){//还款日当天
					//当期日期等于还款日期 
					dividedPayments2 = dividedPayments3;
					break;
				}else if(DivDate.getTime() <= dateFormat.getTime() && dateFormat.getTime() <= DateUtil.getOverdueNumDay(tenderItem, dividedPayments3.getRepayday()).getTime()){//还款日宽限日
					//当前日期 大于或等于 还款日 并且  还款日 小于或等于 还款宽限日期
					dividedPayments2 = dividedPayments3;
					break;
				}
			}
		}
 		return dividedPayments2;
	}
	
	/**
	 * 投资人收款短信通知
	 * @param repayMent
	 */
	public void sendSSMByRepayMent(RepayMent repayMent,String amount){
		UserBaseAccountInfo baseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());//投资人短信通知
 		if(baseAccountInfo == null){
			if(logger.isDebugEnabled()){
				logger.debug("投资人收款短信通知失败！失败原因：投资人用户信息没找到！还款流水号是："+repayMent.getRorderno());
			}
			return ;
 		}
		
		TenderItem item = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
		if(item == null){
			if(logger.isDebugEnabled()){
				logger.debug("投资人收款短信通知失败！失败原因：标的信息没找到！还款流水号是："+repayMent.getRorderno());
			}
			return ;
 		}
		
 		baseAccountInfo = getDecryptionUserBaseAccountInfoDetail(baseAccountInfo);
		SMSSendServiceImpl.SMSSend4Gathering(baseAccountInfo.getMobilephone(), baseAccountInfo.getLoginname(), amount, item.getTno(), baseAccountInfo.getId());
		
	}
	 
	//修改还款状态为待处理
	private void updateAllRepayStatusForWaitDeal(List<RepayMent> repayMents,List<RepaymentDetail> repayMentDetails,RepaymentFrz repaymentFrz){
 		//更新批次号表状态为待处理
		repaymentFrz.setStatus((short)4);//0初始 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败【解冻成功】 8废弃 
 		repaymentFrz.setIssubmit((short)0);//是否提交（1是，0否）
		repaymentFrzServiceI.updateById(repaymentFrz);
 		//更新标的具体还款计划还款状态为待处理
		repayMentBaseDealI.updateRepayMentRepayStatusForWaitDeal(repayMents);
		//更新批次详情还款状态为待处理
		repayMentBaseDealI.updateRepayMentDetailRepayStatusForWaitDeal(repayMentDetails);
	}
	
	//修改还款状态为处理中
	private void updateAllRepayStatusForDealing(List<RepayMent> repayMents,List<RepaymentDetail> repayMentDetails,RepaymentFrz repaymentFrz){
		//更新批次号表还款状态为处理中
 		repaymentFrz.setStatus((short)5);//0初始 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败【解冻成功】
 		repaymentFrz.setIssubmit((short)1);//是否提交（1是，0否）
		repaymentFrzServiceI.updateById(repaymentFrz);
		//更新标的具体还款计划为处理中
		repayMentBaseDealI.updateRepayMentRepayStatusForDealing(repayMents);
		//更新批次详情表还款状态为处理中
		repayMentBaseDealI.updateRepayMentDetailRepayStatusForDealing(repayMentDetails);
	}
}
