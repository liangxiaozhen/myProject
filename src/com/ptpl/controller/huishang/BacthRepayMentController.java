package com.ptpl.controller.huishang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.BacthFileRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.model.RepayMentParamters;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.ThirdRepayMentDealI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: BacthRepayMentController 
* @Description: TODO(批量还款 徽商银行) 
* @author cjm 
* @date 2017年4月7日 上午10:38:43 
*
 */
@RequestMapping("/huishang/BacthRepayMent")
@Controller
public class BacthRepayMentController extends BaseController{
	
	public static final String REPAYMENTCODE = "code";
	
	public static final String REPAYMENTFLAG = "flag";
	
	@Autowired
   	private  OracleSequenceMaxValueIncrementer repaymentnumber;
	
//	@RequestMapping("/test")
//	public void test(){
//		System.out.println(repaymentnumber);
//	}
//
	public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		
		Map<String,Object> hash = repayMentDownload("d:/test/", "3004-ALEVE0054-20160513", "20160513");
		System.out.println(hash);
//		Test(null, null);
//		repayMentDownload(bacthFileRecord.get(0).getFilePath(), bacthFileRecord.get(0).getGetFileName(), "20160510");
		
//		getRepayMentParamtersByDoladFileName(bacthFileRecord.get(0).getFilePath(), bacthFileRecord.get(0).getGetFileName());
	}
	
//	public static void Test(HttpServletRequest request ,HttpServletResponse response) throws NoSuchAlgorithmException, Exception{
// 		//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
//		//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
//		//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
//		//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
//		//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
//		//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
//		//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
//		//币种	CURR	N	3	80	82	M	156：人民币；
//		//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
//		//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
//		//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
//		//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
//		//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
//		//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
//		//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
//		//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
//		//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
//		//保留域	RESE	A	60	274	333	C	
//		
//		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
//		OracleSequenceMaxValueIncrementer incrementer = (OracleSequenceMaxValueIncrementer) beanFactory.getBean("repaymentnumber");
//		TenderItemServiceI itemServiceI =  beanFactory.getBean(TenderItemServiceI.class);
// 		UserTenderServiceI userTenderServiceI = beanFactory.getBean(UserTenderServiceI.class);
//		RepayMentServiceI  repayMentServiceI = beanFactory.getBean(RepayMentServiceI.class);
//		DividedPaymentsServiceI dividedPaymentsServiceI = beanFactory.getBean(DividedPaymentsServiceI.class);
//		UserFsAccountInfoServiceI userFsAccountInfoServiceI = beanFactory.getBean(UserFsAccountInfoServiceI.class);
// 		TenderItem TenderItem = itemServiceI.findTenderItemById(new BigDecimal(1923));
// 		Map<String,Object> hashMap1 = new HashMap<String,Object>();
// 		hashMap1.put("tenderid", TenderItem.getId());//标号ID
// 		hashMap1.put("periods", 1);//还款期数（第几期）
// 		DividedPayments dividedPayments = dividedPaymentsServiceI.findDividedPaymentsByConditions(hashMap1);
// 		 
// 		Map<String,Object> hashMap = new HashMap<String,Object>();
//  		hashMap.put("tenderid", dividedPayments.getTenderid());//标号ID
// 		hashMap.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
// 		hashMap.put("repaystatus", (short)1);/* 还款状态(1未还款，2已还款，3已提前还款，4正常还款处理中,5提前还款审核中，6提前还款处理中) */
// 		hashMap.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
// 		
// 		List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap);
// 		List<RepayMentParamters> repayMentParamters2 = new ArrayList<>();
// 		UserTender userTender = null;
// 		UserFsAccountInfo outUserFsAccountInfo = null;
// 		UserFsAccountInfo inUserFsAccountInfo = null;
// 		String batchNo = StringUtil.stringLeftPading(6, incrementer.nextStringValue(), 1);
//// 		String batchNo = "";
// 		for(RepayMent repayMent : repayMents){
//  			outUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getOutaccountid());
// 			inUserFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(repayMent.getInaccountid());
//  			RepayMentParamters repayMentParamters = new RepayMentParamters();
//			repayMentParamters.setBatch(batchNo);//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
//			repayMentParamters.setType("002");//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
//			repayMentParamters.setDate("20160510");//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
//			repayMentParamters.setCardnbro(outUserFsAccountInfo.getUsrcustid());//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
//			repayMentParamters.setAmount(repayMent.getRamount());//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
//			repayMentParamters.setIntamt(repayMent.getRinterest());//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
//			repayMentParamters.setCardnbri(inUserFsAccountInfo.getUsrcustid());//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
//			repayMentParamters.setCurr("156");//币种	CURR	N	3	80	82	M	156：人民币；
//			repayMentParamters.setOutfeeway("0");//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
//			repayMentParamters.setOutfeeamt(1.00);//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
//			repayMentParamters.setInfeeway("0");//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
//			repayMentParamters.setInfeeamt(12.00);//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
//			repayMentParamters.setProduct(TenderItem.getTno());//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
//			repayMentParamters.setAuthcode(repayMent.getAuthcode());//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
//			repayMentParamters.setEndflg("0");//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
//			repayMentParamters.setThdrese(repayMent.getRorderno());//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
//			repayMentParamters.setSerino(repayMent.getUtorderno());//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
//			repayMentParamters.setRese("");//保留域	RESE	A	60	274	333	C
//			repayMentParamters2.add(repayMentParamters);
// 		}
// 		String productNo  = "-Z01-REPTRAN-";
// 		String resuProductNo = "-Z01-REPTRAN-RESULT-";
// 		String TRXDATE 	  = StringUtil.formatDate(StringUtil.getDateByString("2016-05-10", "yyyy-MM-dd"), "yyyyMMdd");
// //		File file = new File(HSignUtil.FILEUPLOAD + HSignUtil.REPAYMENT + File.separator + TRXDATE);
//		String filePathName = HSignUtil.FILEUPLOAD + HSignUtil.REPAYMENT + File.separator + TRXDATE;
//		String fileName = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER + productNo + batchNo + "-" + TRXDATE;
//		//$$$$-??-Z01-REPTRAN-RESULT-XXXXXX-YYYYMMDD
//		String getFileName  = HSignUtil.FILEBANKCODE + HSignUtil.FUISSUER  + resuProductNo + batchNo + "-" +  TRXDATE;
//		boolean falg = ParametersAreWrittenIntoTheFile(filePathName,fileName, repayMentParamters2);
//		BacthFileRecord bacthFileRecord = new BacthFileRecord();
//		bacthFileRecord.setFilePath(filePathName);//文件路径    /batchfile/平台编号/业务名称/日期
//		bacthFileRecord.setSendFileName(fileName);  //上传文件名称
//		bacthFileRecord.setGetFileName(getFileName);  //下载文件名称
//		bacthFileRecord.setCoinstCode(HSignUtil.COINSTCODE);  //平台编号  800114
//		bacthFileRecord.setPName("干将");  //平台名称
//		bacthFileRecord.setSubmitTime(new Date());  //上传文件时间   干将—》银行
// 		bacthFileRecord.setFileType((short)4);  //业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款 ....
//		bacthFileRecord.setIsSend((short)0);  //是否已发送到银行     0.否   1.是
//		bacthFileRecord.setSendResult((short)0);  //发送结果（是否成功）   0.失败    1.成功 
//		bacthFileRecord.setIsDealResult((short)0);  //是否已处理结果文件     0.否   1.是
//		bacthFileRecord.setDealResult((short)0);  //处理结果（是否成功）
//		bacthFileRecord.setRemark("还款记录");  //备注
//		beanFactory.getBean(BacthFileRecordServiceI.class).insert(bacthFileRecord);
//		if(falg){
//			bacthFileRecord.setIsSend((short)1);
//			Map<String,Object> hasMap = repayMentFileUpload(filePathName,fileName,"20160510");
//			boolean falg3 = (boolean) hasMap.get("flag");
//			if(falg3){
//				bacthFileRecord.setSendResult((short)1);
//			}
//			beanFactory.getBean(BacthFileRecordServiceI.class).update(bacthFileRecord);
// 		}
//	}
	
	/**
	 * 
	* @Title: ParametersAreWrittenIntoTheFile 
	* @Description: TODO(把还款参数写进文件) 
	* @param @param fileName 写进的文件名
	* * @param @param filePathName 写进的文件目录
	* @param @param repayMentParamters    设定文件 
	* @return void    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static boolean ParametersAreWrittenIntoTheFile(String filePathName,String fileName,List<RepayMentParamters> repayMentParamters){
		 boolean flag = false;
		/*$$$$-??-Z01-REPTRAN-XXXXXX-YYYYMMDD，
				$$$$为银行编号，??为P2P平台代号，YYYYMMDD为8位日期,XXXXXX为批次号，从000001开始递增；*/
		if(StringUtil.isEmpty(fileName)){
			if(logger.isDebugEnabled()){
				logger.debug("BacthRepayMentController.java 还款参数写进文件失败,失败原因：文件名为空");
			}
			return flag;
		}
		
		if(StringUtil.isEmpty(filePathName)){
			if(logger.isDebugEnabled()){
				logger.debug("BacthRepayMentController.java 还款参数写进文件失败,失败原因：文件目录名为空");
			}
			return flag;
		}
		
		if(!(repayMentParamters.size() > 0)){
			if(logger.isDebugEnabled()){
				logger.debug("BacthRepayMentController.java  还款参数写进文件失败,失败原因：还款参数实体类RepayMentParamters为空");
			}
			return flag;
		}
		
		 
		
		boolean fl = checkRepayMentParamters(repayMentParamters);
		if(!fl){
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
		try {
			out = new PrintWriter(new OutputStreamWriter(fileOutputStream,"GBK"), true);
			//新文档
			for(RepayMentParamters repayMentParamter : repayMentParamters){
 				out.print(StringUtil.stringRightPading(6,repayMentParamter.getBatch(),2));//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
				out.print(StringUtil.stringRightPading(3,repayMentParamter.getType(),2));//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
				out.print(StringUtil.stringRightPading(8,repayMentParamter.getDate(),2));//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
				out.print(StringUtil.stringRightPading(19,repayMentParamter.getCardnbro(),2));//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
				String[] array = df1.format(repayMentParamter.getAmount()).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array[0] + array[1],1));//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
				String[] array1 = df1.format(repayMentParamter.getIntamt()).split("\\.");
				out.print(StringUtil.stringLeftPading(12,array1[0] + array1[1],1));//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
				out.print(StringUtil.stringRightPading(19,repayMentParamter.getCardnbri(),2));//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
				out.print(StringUtil.stringRightPading(3,repayMentParamter.getCurr(),2));//币种	CURR	N	3	80	82	M	156：人民币；
				out.print(StringUtil.stringRightPading(1,repayMentParamter.getOutfeeway(),2));//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
				if(repayMentParamter.getOutfeeway().equals("0")){
					String[] array2 = df1.format(repayMentParamter.getOutfeeamt()).split("\\.");
 					out.print(StringUtil.stringLeftPading(11,array2[0] + array2[1],1));//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				}else{
 					out.print(StringUtil.stringLeftPading(11,"0",1));//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
				}
				out.print(StringUtil.stringLeftPading(1,repayMentParamter.getInfeeway(),2));//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
				if(repayMentParamter.getInfeeway().equals("0")){
					String[] array3 = df1.format(repayMentParamter.getInfeeamt()).split("\\.");
 					out.print(StringUtil.stringLeftPading(11,array3[0] + array3[1],1));//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
 				}else{
  					out.print(StringUtil.stringLeftPading(11,"0",1));//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
				}
				out.print(StringUtil.stringRightPading(6,repayMentParamter.getProduct(),2));//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
				out.print(StringUtil.stringRightPading(20,repayMentParamter.getAuthcode(),2));//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
				out.print(StringUtil.stringRightPading(1,repayMentParamter.getEndflg(),2));//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
				out.print(StringUtil.stringRightPading(100,repayMentParamter.getThdrese(),2));//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
				out.print(StringUtil.stringRightPading(40,repayMentParamter.getSerino(),2));//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
				out.print(StringUtil.stringRightPading(60,repayMentParamter.getRese(),2));//保留域	RESE	A	60	274	333	C	
				out.print("\n");
			}
		} catch (UnsupportedEncodingException e) {
			
			if(logger.isDebugEnabled()){
				logger.debug("BacthRepayMentController.java 还款参数写进文件失败,失败原因："+e.getMessage());
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
		flag = true;
 		return flag;
	}
	
	
	
	 /**
	  * 
	 * @Title: repayMentFileUpload 
	 * @Description: TODO(到期还款 文件上传) 
	 * @param @param filePathName   文件跟路径
	 * @param @param repayMentFileName  上传文件名
	 * @param @param dateStr  时间
	 * @param @return
	 * @param @throws NoSuchAlgorithmException
	 * @param @throws Exception    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	public static Map<String,Object> repayMentFileUpload(String filePathName,String repayMentFileName,String dateStr) throws NoSuchAlgorithmException, Exception{
		Map<String,Object> hashMap = new HashMap<String,Object>();
 		if(StringUtil.isEmpty(filePathName)){
 			if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 调用到期还款文件上传repayMentFileUpload方法,文件路径不能为空");
            }
 			hashMap.put(REPAYMENTFLAG, false);
        	hashMap.put(REPAYMENTCODE, "filePathName_null");
        	return hashMap;
 		}
 		
 		if(StringUtil.isEmpty(repayMentFileName)){
 			if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 调用到期还款文件上传repayMentFileUpload方法,文件名不能为空");
            }
 			
 			hashMap.put(REPAYMENTFLAG, false);
        	hashMap.put(REPAYMENTCODE, "repayMentFileName_null");
        	return hashMap;
 		}
 		
 		PostMethod method;
        HttpClient httpClient = new HttpClient();
        StringPart sp = null;
        FilePart fp = null;
        String[] filePath = {filePathName};
        String[] fileName = {repayMentFileName};
        String return_code = null;
        JSONObject map = null;
        Map<String,Object>params = null;
        JSONObject paramsJson = null;
        String pam = null;
        Part[] parts = null;
        String TMP = ".tmp";
        for(int i=0;i < filePath.length;i++)
        {
            params = new HashMap<String,Object>();
            params.put("coinstCode", HSignUtil.COINSTCODE);
            params.put("bankCode",HSignUtil.BANKCODE);
            params.put("transDate", dateStr);
            params.put("fileName",fileName[i]);
            params.put("fileType",	BacthFileRecord_Constant.DAOQIHUANKUAN);
            
            
            File file1 = new File(filePathName,repayMentFileName);
            String md5encryptionPath = HSignUtil.ENCRYPTPATH;
            String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);	//获取加密公钥字符串
            File tmp = new File(file1.getPath()+".bak");
            tmp.createNewFile();
            HSignUtil.transFormToTmpFile(file1, tmp);
            String m = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));
            String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(tmp), encryptionKey4Server);//进行摘要并对摘要进行加密
            params.put("sign", encryptData);
            
            
            paramsJson = JSONObject.fromObject(params);
            pam = paramsJson.toString();
            sp = new StringPart("parameters",pam);
            sp.setCharSet("GBK");//这里要设置字符编码，不然会乱码
             
            File file2 = new File(filePathName,repayMentFileName+TMP);
            File fi = new File(HSignUtil.ENCRYPTION_FILE);
            FileReader fre = new FileReader(fi);
            BufferedReader bre = new BufferedReader(fre);
            String privateKey = bre.readLine();
            
            HSignUtil.encryptHand(file1, file2, privateKey);//加密处理
            if (file1.exists())
            {
                //file1.renameTo(new File(oldFile));
            }
            if(file2.exists())
            {
                 fp = new FilePart("file",file2);
            }
            
            String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(file1)));

            if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 调用到期还款文件上传repayMentFileUpload方法,上传文件："+file1);
            }
            
            method = new PostMethod(HSignUtil.BATCREQHURL);
            parts = new Part[]{fp,sp};
            method.getParams().setContentCharset("UTF-8");
            method.setRequestEntity(new MultipartRequestEntity(parts,method.getParams()));
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.executeMethod(method);

            String responseMaps = method.getResponseBodyAsString();
            if(StringUtil.isEmpty(responseMaps)){
            	if(logger.isDebugEnabled()){
     				logger.debug("BacthRepayMentController.java 调用到期还款文件上传repayMentFileUpload方法,上传的文件："+file1+",因网络原因,httpClient 发送失败");
     			}
            }
            
            map = JSONObject.fromObject(responseMaps);
            return_code = (String) map.get("return_code");
            if (return_code.equals("0000")){
            	hashMap.put(REPAYMENTFLAG, true);
            	hashMap.put(REPAYMENTCODE, return_code);
                System.out.println("文件上传成功");
                if(logger.isDebugEnabled()){
     				logger.debug("BacthRepayMentController.java 到期还款文件上传成功,返回码是："+return_code+",上传的文件："+file1);
     			 }
             }else if(return_code.equals("0004")){
            	hashMap.put(REPAYMENTFLAG, true);
            	hashMap.put(REPAYMENTCODE, return_code);
                System.out.println("文件已上传，请勿重复上传");
                 if(logger.isDebugEnabled()){
     				logger.debug("BacthRepayMentController.java 到期还款文件上传失败， 失败原因：文件已上传，请勿重复上传,返回码是："+return_code+",上传的文件："+file1);
     			 }
             }else if(return_code.equals("0003")){
            	hashMap.put(REPAYMENTFLAG, false);
            	hashMap.put(REPAYMENTCODE, return_code);
            	if(logger.isDebugEnabled()){
    				logger.debug("BacthRepayMentController.java 到期还款文件上传失败， 失败原因：上传文件过大,返回码是："+return_code+",上传的文件："+file1);
    			}
                 System.out.println("上传文件过大");
             }else{
            	 hashMap.put(REPAYMENTFLAG, false);
             	 hashMap.put(REPAYMENTCODE, return_code);
             	 if(logger.isDebugEnabled()){
     				logger.debug("BacthRepayMentController.java 到期还款文件上传失败， 失败原因：上传文件过大,返回码是："+return_code+",上传的文件："+file1);
     			} 
             }
        }
		return hashMap;
	}
	
	
	
	/**
	 * 
	* @Title: repayMentDownload 
	* @Description: TODO(到期还款  文件下载 ) 
	* @param @param filePathName  文件目录
	* @param @param repayMentFileName  文件名
	* @param @param dataStr  时间
	* @param @return 
	* @param @throws Exception    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static Map<String,Object> repayMentDownload(String filePathName,String repayMentFileName,String dataStr) throws Exception{
		Map<String,Object> hashMap = new HashMap<String,Object>();
  		if(StringUtil.isEmpty(filePathName)){
 			if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 调用到期还款结果处理文件repayMentDownload方法,下载失败，文件路径不能为空");
            }
 			
 			hashMap.put(REPAYMENTFLAG, false);
        	hashMap.put(REPAYMENTCODE, "filePathName_null");
        	return hashMap;
 		}
 		
 		if(StringUtil.isEmpty(repayMentFileName)){
 			if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 调用到期还款文件结果处理文件repayMentDownload方法,下载失败，文件名不能为空");
            }
 			
 			hashMap.put(REPAYMENTFLAG, false);
        	hashMap.put(REPAYMENTCODE, "repayMentFileName_null");
        	return hashMap;
 		}
 		
 		String BAK = ".bak";
 		String[] fileName = {repayMentFileName};
        String return_code = null;
        JSONObject map = null;
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < fileName.length; i++) {
            params.put("coinstCode", HSignUtil.COINSTCODE);
            params.put("bankCode", HSignUtil.BANKCODE);
            params.put("transDate", dataStr);
            params.put("fileName", fileName[i]);
            JSONObject paramsJson = JSONObject.fromObject(params);
            String pam = paramsJson.toString();
            HttpClient httpClient;
            PostMethod method;
            httpClient = new HttpClient();
            method = new PostMethod(HSignUtil.BATCHRESURL);
            method.getParams().setContentCharset("GBK");
            method.setParameter("parameters", pam);
            httpClient.executeMethod(method);

            String responseMap = method.getResponseBodyAsString();
            map = JSONObject.fromObject(responseMap);
            FileOutputStream out = null;
            return_code = (String) map.get("return_code");
             if(return_code.equals("0002")){
                if(logger.isDebugEnabled()){
                	logger.debug("BacthRepayMentController.java 调用到期还款文件结果处理文件repayMentDownload方法,下载失败,下载文件名："+repayMentFileName+"失败原因：下载请求参数无法识别,返回码："+return_code);
                }
     			
     			hashMap.put(REPAYMENTFLAG, false);
            	hashMap.put(REPAYMENTCODE, return_code);
            	return hashMap;
            }
            
            String sign = (String) map.get("sign");
            String decryptKeyPath = HSignUtil.DECRYPTPATH;	//拼接解密私钥路径
        	String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        	String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign,decryptKey4Server)));
             
            if (return_code.equals("0000")) {
                String file = (String)map.get("file");
                byte[] _file = file.getBytes("GBK");
                File newFile = new File(filePathName);
                File file1 = new File(newFile,fileName[i]);
                File file2 = new File(newFile,fileName[i] + BAK);
                String newFileName = file1.getPath();
                if  (!newFile .exists()  && !newFile .isDirectory())//判断文件夹是否存在，不存在就创建
                {
                    newFile.mkdirs();
                }
                out = new FileOutputStream(newFile + File.separator + fileName[i]);
                String str = new String(_file,"GBK");
                out.write(str.getBytes());
                File fi = new File(HSignUtil.ENCRYPTION_FILE);
                FileReader fre = new FileReader(fi);
                BufferedReader bre = new BufferedReader(fre);
                String privateKey = bre.readLine();
                HSignUtil.decryptHand(file1, file2, privateKey);
                out.flush();
                out.close();
                File tmp = new File(file2.getPath() + BAK);
                tmp.createNewFile();
                HSignUtil.transFormToTmpFile(file2, tmp);
                String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));
                
                if(!md5.equals(signdecrypt)){
                    if(logger.isDebugEnabled()){
                    	logger.debug("BacthRepayMentController.java 调用到期还款文件结果处理文件repayMentDownload方法,文件验签失败,下载文件名："+repayMentFileName+",返回码："+return_code);
                    }
         			
         			hashMap.put(REPAYMENTFLAG, false);
                	hashMap.put(REPAYMENTCODE, return_code);
                	return hashMap;
            	}
                
                if (file1.exists())
                {
                    file1.delete();
                }
                
                if(file2.exists())
                {
                    file2.renameTo(new File(newFileName));
                }

                hashMap.put(REPAYMENTFLAG, true);
            	hashMap.put(REPAYMENTCODE, return_code);
            	if(logger.isDebugEnabled()){
                	logger.debug("BacthRepayMentController.java 调用到期还款文件结果处理文件repayMentDownload方法,下载成功,下载文件名："+repayMentFileName+"返回码："+return_code);
                }
            }else if(return_code.equals("0002"))
            {
                System.out.println("下载请求参数无法识别");
                if(logger.isDebugEnabled()){
                	logger.debug("BacthRepayMentController.java 调用到期还款文件结果处理文件repayMentDownload方法,下载失败,下载文件名："+repayMentFileName+"失败原因：下载请求参数无法识别,返回码："+return_code);
                }
     			
     			hashMap.put(REPAYMENTFLAG, false);
            	hashMap.put(REPAYMENTCODE, return_code);
            	return hashMap;
            }else{
            	hashMap.put(REPAYMENTFLAG, false);
            	hashMap.put(REPAYMENTCODE, return_code);
            	if(logger.isDebugEnabled()){
                	logger.debug("BacthRepayMentController.java 调用到期还款文件结果处理文件repayMentDownload方法,下载失败,下载文件名："+repayMentFileName+"失败原因：未知,返回码："+return_code);
                }
            	return hashMap;
            }
        }
 		return hashMap;
	}
	
	/**
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
 	* @Title: getRepayMentParamtersByDoladFileName 
	* @Description: TODO(获取结果处理文件参数) 
	* @param @param filePathName   文件目录
	* @param @param repayMentFileName 文件名称
	* @param @return    设定文件 
	* @return List<RepayMentParamters>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static List<RepayMentParamters>  getRepayMentParamtersByDoladFileName(String filePathName,String repayMentFileName) throws IOException{
		if(StringUtil.isEmpty(filePathName)){
 			if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,文件路径不能为空");
            }
         	return null;
 		}
 		
 		if(StringUtil.isEmpty(repayMentFileName)){
 			if(logger.isDebugEnabled()){
            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,文件名不能为空");
            }
           	return null;
 		}
 		
 		List<RepayMentParamters> repayMentParamters = new ArrayList<>();
  		File file = new File(filePathName,repayMentFileName);
 		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
  		while((line = bufferedReader.readLine()) != null){
  			String batch 		= line.substring(0, 6);//1	批次号	BATCH	N	6	1	6	M	同请求
  			String type 		= line.substring(6, 9);//2	业务类别	TYPE	N	3	7	9	M	同请求
  			String date 		= line.substring(9, 17);//3	日期	DATE	N	8	10	17	M	同请求
  			String rspcode 		= line.substring(17, 19);//4	处理响应码	RSPCODE	A	2	18	19	M	"‘00’：成功；//其它为失败；"
  			String cardnbro 	= line.substring(19, 38);//5	扣款电子账号	CARDNBRO	A	19	20	38	M	同请求
  			String amount 		= line.substring(38, 50);//6	扣账(本金)金额	AMOUNT	N	12	39	50	C	同请求
  			String intamt 		= line.substring(50, 62);//7	扣账利息金额	INTAMT	N	12	51	62	C	同请求
  			String billamount 	= line.substring(62, 74);//8	实际扣账金额	BILLAMOUNT	N	12	63	74	C	两位小数
  			String cardnbri 	= line.substring(74, 93);//9	转入电子账号	CARDNBRI	A	19	75	93	C	同请求
  			String curr 		= line.substring(93, 96);//10	币种	CURR	N	3	94	96	C	同请求
  			String outfeeway 	= line.substring(96, 97);//11	转出方手续费扣款方式	OUTFEEWAY	N	1	97	97	C	同请求
  			String outfeeamt 	= line.substring(97, 108);//12	转出方手续费扣款金额	OUTFEEAMT	N	11	98	108	C	同请求
  			String routfeeamt 	= line.substring(108, 119);//13	转出方手续费实际扣款金额	ROUTFEEAMT	N	11	109	119	C	两位小数
  			String infeeway 	= line.substring(119, 120);//14	转入方手续费扣款方式	INFEEWAY	N	1	120	120	C	同请求
  			String infeeamt 	= line.substring(120, 131);//15	转入方手续费扣款金额	INFEEAMT	N	11	121	131	C	同请求
  			String rifeeamt 	= line.substring(132, 142);//16	转入方手续费实际扣款金额	RIFEEAMT	N	11	132	142	C	两位小数
  			String product 		= line.substring(142, 148);//17	标的编号	PRODUCT	A	6	143	148	C	同请求
  			String serino 		= line.substring(148, 188);//18	投标申请流水号	SERIAL-NO	A	40	149	188	C	同请求
  			String thdrese 		= line.substring(188, 288);//19	第三方保留域	THDRESE	A	100	189	288	C	第三方机构扩展使用，同请求
  			String rese 		= line.substring(288, 388);//20	保留域	RESE	A	100	289	388	C	
  			System.out.println("========batch==========="+batch);
  			System.out.println("========type==========="+type);
  			System.out.println("========rspcode==========="+rspcode);
  			System.out.println("========cardnbro==========="+cardnbro);
  			System.out.println("========amount==========="+amount);
  			System.out.println("========intamt==========="+intamt);
  			System.out.println("========billamount==========="+billamount);
  			System.out.println("========cardnbri==========="+cardnbri);
  			System.out.println("========curr==========="+curr);
  			System.out.println("========outfeeway==========="+outfeeway);
  			System.out.println("========outfeeamt==========="+outfeeamt);
  			System.out.println("========routfeeamt==========="+routfeeamt);
  			System.out.println("========infeeway==========="+infeeway);
  			System.out.println("========infeeamt==========="+infeeamt);
  			System.out.println("========rifeeamt==========="+rifeeamt);
  			System.out.println("========product==========="+product);
  			System.out.println("========serino==========="+serino);
  			System.out.println("========thdrese==========="+thdrese);
  			System.out.println("========rese==========="+rese);
  			System.out.println("\n");
  			if(StringUtil.isEmpty(rspcode)){
  				if(logger.isDebugEnabled()){
  	            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,处理响应码不能为空,文件名是："+repayMentFileName);
  	            }
  				repayMentParamters.clear();
  				break;
  			}
  			
			if(StringUtil.isEmpty(cardnbro)){
				if(logger.isDebugEnabled()){
	            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,扣款电子账号不能为空,文件名是："+repayMentFileName);
	            }
				repayMentParamters.clear();
  				break;		
			}
			
			if(StringUtil.isEmpty(cardnbri)){
				if(logger.isDebugEnabled()){
	            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,转入电子账号不能为空,文件名是："+repayMentFileName);
	            }
				repayMentParamters.clear();
  				break;
			}
			
			if(StringUtil.isEmpty(product)){
				if(logger.isDebugEnabled()){
	            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,标的编号不能为空,文件名是："+repayMentFileName);
	            }
				repayMentParamters.clear();
  				break;
			}
			
			if(StringUtil.isEmpty(serino)){
				if(logger.isDebugEnabled()){
	            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,投标申请流水号不能为空,文件名是："+repayMentFileName);
	            }
				repayMentParamters.clear();
  				break;
			}
			
			if(StringUtil.isEmpty(thdrese)){
				if(logger.isDebugEnabled()){
	            	logger.debug("BacthRepayMentController.java 获取结果处理文件参数getRepayMentParamtersByDoladFileName方法,还款流水号不能为空,文件名是："+repayMentFileName);
	            }
				repayMentParamters.clear();
  				break;
			}
			
    		RepayMentParamters mentParamters = new RepayMentParamters();
  			mentParamters.setBatch(batch);  // 批次号 BATCH
  			mentParamters.setType(type);  //业务类别 TYPE 
  			mentParamters.setDate(date);  //日期 DATE
  			mentParamters.setCardnbro(cardnbro);  //扣款账号 CARDNBRO
  			mentParamters.setAmount(Double.valueOf(amount));  //扣账(本金)金额 AMOUNT
  			mentParamters.setIntamt(Double.valueOf(intamt));  //扣账利息金额 INTAMT
  			mentParamters.setCardnbri(cardnbri);  //转入账号 CARDNBRI
  			mentParamters.setCurr(curr);  //币种 CURR
  			mentParamters.setOutfeeway(outfeeway);  //转出方手续费扣款方式 OUTFEEWAY
  			mentParamters.setOutfeeamt(Double.valueOf(outfeeamt));  //转出方手续费扣款金额 OUTFEEAMT
  			mentParamters.setInfeeway(infeeway);  //转入方手续费扣款方式
  			mentParamters.setInfeeamt(Double.valueOf(infeeamt));  //转入方手续费扣款金额 INFEEAMT
  			mentParamters.setProduct(product);  //标的编号 PRODUCT
//  			mentParamters.setAuthcode(authcode);  //投标申请授权码 AUTHCODE
//  			mentParamters.setEndflg(endflg);  //还款结束标志
  			mentParamters.setThdrese(thdrese);  //第三方保留域 THDRESE 
  			mentParamters.setSerino(serino);  //投标申请流水号 SERINO 
  			mentParamters.setRese(rese);  //保留域 RESE
  			mentParamters.setRspcode(rspcode); //处理响应码	RSPCODE
  			mentParamters.setBillamount(Double.valueOf(billamount));  //实际扣账金额
  			mentParamters.setRifeeamt(Double.valueOf(rifeeamt));  //转入方手续费实际扣款金额
  			mentParamters.setRoutfeeamt(Double.valueOf(routfeeamt));  //转出方手续费实际扣款金额
   			repayMentParamters.add(mentParamters);
 		}
        bufferedReader.close();
  		inputStreamReader.close();
  		fileInputStream.close();
 		return repayMentParamters;
		 
	}
	
	/**
	 * 
	* @Title: checkRepayMentParamters 
	* @Description: TODO(检查 还款参数是否为空) 
	* @param @param repayMentParamters
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static boolean checkRepayMentParamters(List<RepayMentParamters> repayMentParamters){
		boolean flag = true;
		if(!(repayMentParamters.size() > 0)){
			flag = false;
			if(logger.isDebugEnabled()){
				logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   repayMentParamters为空");
			}
			return flag;
		}
		
		for(RepayMentParamters repayMentParamter : repayMentParamters){
			if(StringUtil.isEmpty(repayMentParamter.getBatch())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   批次号 BATCH 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getType())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   业务类别 TYPE 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getDate())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   日期 DATE 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getCardnbro())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败  扣款账号 CARDNBRO 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getCardnbri())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败    转入账号 CARDNBRI 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getCurr())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   币种 CURR 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getCurr())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   币种 CURR 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getOutfeeway())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   转出方手续费扣款方式 OUTFEEWAY 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getInfeeway())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败  转入方手续费扣款方式 INFEEWAY 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getProduct())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   标的编号 PRODUCT 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getAuthcode())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   投标申请授权码 AUTHCODE 不能为空");
				}
				break;
			}
			
			if(StringUtil.isEmpty(repayMentParamter.getSerino())){
				flag = false;
				if(logger.isDebugEnabled()){
					logger.debug("BacthRepayMentController.java RepayMentParamters还款参数写进文件校验失败   投标申请流水号 SERINO 不能为空");
				}
				break;
			}
		}
 		return flag;
	}
	
			//批次号	BATCH	N	6	1	6	M	对于文件名称中“XXXXXX”
			//业务类别	TYPE	N	3	7	9	M	002-到期还款                                   003-平台逾期代偿/担保公司代偿
			//日期	DATE	N	8	10	17	M	YYYYMMDD，需与文件名中的日期一致
			//扣款账号	CARDNBRO	A	19	18	36	M	002到期还款-实际借款人/名义借款人 003代偿-担保人电子账号/平台备付金账号  
			//扣账(本金)金额	AMOUNT	N	12	37	48	C	"整数10位，小数2位；还款时此处填写还款本金；"
			//扣账利息金额	INTAMT	N	12	49	60	C	仅到期还款时有效,整数10位，小数2位；
			//转入账号	CARDNBRI	A	19	61	79	M	向左对齐，右补空白
			//币种	CURR	N	3	80	82	M	156：人民币；
			//转出方手续费扣款方式	OUTFEEWAY	N	1	83	83	M	"0：指定金额；1：同产品设置；"
			//转出方手续费扣款金额	OUTFEEAMT	N	11	84	94	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
			//转入方手续费扣款方式	INFEEWAY	N	1	95	95	M	"0：指定金额；1：同产品设置；"
			//转入方手续费扣款金额	INFEEAMT	N	11	96	106	C	"2位小数；转出方手续费扣账方式为指定金额时有效，可为0"
			//标的编号	PRODUCT	A	6	107	112	M	投标时使用的标的编号一致
			//投标申请授权码	AUTHCODE	A	20	113	132	M	"非自动投标时必送；自动投标时爱钱帮模式必送；"
			//还款结束标志	ENDFLG	A	1	133	133	C	"1：已结清；其它：还款中；此标志仅还款时使用；"
			//第三方保留域	THDRESE	A	100	134	233	C	第三方机构扩展使用，结果文件原样返回
			//投标申请流水号	SERINO	A	40	234	273	M	投标上送流水号
			//保留域	RESE	A	60	274	333	C	
}
