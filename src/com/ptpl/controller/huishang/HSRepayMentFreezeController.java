package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishang.util.HSignUtil;
import com.huishang.util.MessageUtil;
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.RepaymentFrz;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.RepaymentFrzServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: HSRepayMentFreezeController 
* @Description: TODO(徽商还款冻结解冻 Controller 类) 
* @author cjm 
* @date 2017年4月21日 下午2:02:13 
*
 */
@RequestMapping
@Controller
public class HSRepayMentFreezeController extends BaseController{
	
 	 
	/**
	 * 
	* @Title: repayMentFreeze 
	* @Description: TODO(还款冻结请求) 
	* @param @param CARDNBR   冻结用户电子账户
	* @param @param AMOUNT    冻结金额
	* @param @param PRODUCT    标的编号
	* @param @param batchNo    还款批次号
 	* @return void    返回类型 
	* @author   cjm  
	* @throws
 	 */
	public static Map<String,Object> repayMentFreeze(String CARDNBR,String AMOUNT,String PRODUCT,String batchNo,Short Isahead,Short Isproxyrepay,Short Isoverdue){
 		Map<String,Object> hashMap = new HashMap<>();
		if(StringUtil.isEmpty(CARDNBR)){
			hashMap.put(RepayMent_Constant.RESULT, "cardnbr_null");//冻结用户电子账户不能为空;
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "还款冻结,冻结用户电子账户不能为空");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'cardnbr_null' 还款冻结 冻结用户电子账户 不能为空");
	    	}
	    	return hashMap;
		}
		
		if(StringUtil.isEmpty(AMOUNT)){
			hashMap.put(RepayMent_Constant.RESULT, "amount_null");//冻结金额不能为空;
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "还款冻结,冻结金额不能为空");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'amount_null' 还款冻结 冻结金额不能为空");
	    	}
	    	return hashMap;
		}
		
		if(StringUtil.isEmpty(PRODUCT)){
			hashMap.put(RepayMent_Constant.RESULT, "product_null");//标的编号不能为空;
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "还款冻结,标的编号不能为空");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'product_null' 还款冻结 标的编号不能为空");
	    	}
	    	return hashMap;
		}
		
		if(StringUtil.isEmpty(batchNo)){
			hashMap.put(RepayMent_Constant.RESULT, "batchno_null");//流水号不能为空;
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "还款冻结,流水号不能为空");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'batchno_null' 还款冻结 批次号不能为空");
	    	}
	    	return hashMap;
		}
		
		RepaymentFrzServiceI repaymentFrzServiceI  = SpringContextHolder.getBean(RepaymentFrzServiceI.class);
 		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		String SEQNO   = StringUtil.getNo();//交易流水号
		String SERI_NO = HSignUtil.COINSTCODE + batchNo;//冻结申请流水号
 		LinkedHashMap<String,String> reqMap = new LinkedHashMap<>();
		reqMap.put("TRXCODE", "5816");//交易代码	TRXCODE
	    reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE
 	    reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
	    reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
	    reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
	    reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
	    reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
	    reqMap.put("SOURCE", HSignUtil.SOURCE);//ESB内部渠道	SOURCE
	    reqMap.put("RETCODE", "");//应答码	RETCODE
	    reqMap.put("RETMSG", "");//应答码描述	RETMSG
	    reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED
	    
 	    reqMap.put("CARDNBR", CARDNBR);//电子账号	CARDNBR	A	19	M		
	    reqMap.put("SERI_NO", SERI_NO);//申请流水号	SERI_NO	A	40	M	"必填；用于交易的唯一性标识，需前台保证唯一性 还款冻结交易申请流水号 六位合作单位编号+各平台流水号生成规则"	0
	    reqMap.put("AMOUNT", AMOUNT);//冻结金额	AMOUNT	N	13,2	M		空格
	    reqMap.put("FUISSUER", HSignUtil.FUISSUER);//产品发行方	FUISSUER	A	4	M		银联数据分配
	    reqMap.put("PRODUCT",PRODUCT); //标的编号	PRODUCT	A	6	M	"由产品发行方定义"	
	    reqMap.put("REMARK", "");//备注	REMARK	A	100	C	允许为空	

	    UserFsAccountInfo fsAccountInfo = SpringContextHolder.getBean(UserFsAccountInfoServiceI.class).findUserFsAccountInfoByUsrCustId(BaseController.setEncrypt(CARDNBR));
 	    if(fsAccountInfo == null){
	    	hashMap.put(RepayMent_Constant.RESULT, "fsaaccount_null");//托管账号未找到;
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "还款冻结,托管账号对象信息没有找到");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'fsaaccount_null' 还款冻结提交失败！ 托管账号对象信息没有找到 , 还款批次号是："+batchNo);
	    	}
	    	return hashMap;
	    }
	    
 	    RepaymentFrz repaymentFrz = new RepaymentFrz();
	    repaymentFrz.setBaseid(fsAccountInfo.getBaseid()); //用户ID
 		repaymentFrz.setFrztime(new Date()); //冻结时间
  		repaymentFrz.setSerino(SERI_NO); //冻结申请流水号  
  		repaymentFrz.setBatchno(batchNo);//还款批次号 作为还款记录表批次号
		repaymentFrz.setAmount(new BigDecimal(AMOUNT)); //冻结金额
 		repaymentFrz.setProduct(PRODUCT); //标的编号
 		repaymentFrz.setCardnbr(CARDNBR);//电子账号
		repaymentFrz.setRemark("还款冻结"); //备注
		repaymentFrz.setIssubmit((short)0);//是否提交（1是，0否）
		repaymentFrz.setIsblending((short)0);//是否系统勾兑（0未勾兑，1已勾兑）
		repaymentFrz.setIsmanblending((short)0);//是否人工勾兑（0未勾兑，1已勾兑）
		repaymentFrz.setIsahead(Isahead);//是否提前（0否，1是）
		repaymentFrz.setIsproxyrepay(Isproxyrepay);//是否代偿（1是，0否）
		repaymentFrz.setIsoverdue(Isoverdue); //是否逾期 0否，1是
		
		
   		repaymentFrz.setStatus((short)0); //交易状态 0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败
   		int count = 0;
   		count = repaymentFrzServiceI.insertSelective(repaymentFrz);
  		if(!(count > 0)){
  			hashMap.put(RepayMent_Constant.RESULT, "network");// "因网络响应不及时,处理失败";
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "因网络响应不及时,操作失败");
 	    	if(logger.isDebugEnabled()){
	    		logger.debug("'network' 还款冻结失败   RepaymentFrz 记录添加失败！ ,还款批次号是："+batchNo);
	    	}
	    	return hashMap;
  		}
  		
	    String result = "";
	    try {
			  result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
 			hashMap.put(RepayMent_Constant.RESULT, "network");// "因网络响应不及时,处理失败";
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "因网络响应不及时,操作失败");
	    	
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'network' 还款冻结失败,还款冻结 httpClient发送失败,冻结申请流水号是："+SERI_NO);
	    	}
	    	
	    	return hashMap;
		} catch (Exception e) {
 			e.printStackTrace();
 		}
	    
	    if(StringUtil.isEmpty(result)){
	    	hashMap.put(RepayMent_Constant.RESULT, "result_null");// "因网络响应不及时,处理失败";
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG, "还款冻结,没有返回回调信息");
	    	
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'result_null' 还款冻结 httpClient没有返回回调信息 ,冻结申请流水号是："+SERI_NO);
	    	}
	    	return hashMap;
	    }
   		hashMap = freezeCallBack(result,SERI_NO,repaymentFrzServiceI);
	    return  hashMap;
	}
	
	/**
	 * 
	* @Title: callBack 
	* @Description: TODO(还款冻结处理) 
	* @param @param result
	* @param @param Frzorderno
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static Map<String,Object> freezeCallBack(String result,String serino,RepaymentFrzServiceI repaymentFrzServiceI){
 		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<String> userRegList = new ArrayList<>();
  	   	userRegList.add("CARDNBR");//电子账号	CARDNBR	A	19	M	Frzorderno
 	   	userRegList.add("NAME");//姓名	NAME	A	60	C	
 	   	userRegList.add("FUISSUER");//产品发行方	FUISSUER	A	4	C	同请求
 	   	userRegList.add("PRODUCT");//标的编号	PRODUCT	A	6	C	同请求
 	   	userRegList.add("AMOUNT");//冻结金额	AMOUNT	N	13,2	C	同请求
//	  	userRegList.add("RESERVED");//保留域	RESERVED	A	200	C	

	  
		String resresult = HSignUtil.getDecryptRSAByte(result);//解密
		Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resresult);//数据转化Map
		  
	 	List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5816接口返回报文参数和1到11域拼接一起
	 	RepaymentFrz repaymentFrz2 = new RepaymentFrz();
	 	repaymentFrz2.setSerino(serino);//冻结申请流水号
 	 	RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);
 	 	if(repaymentFrz == null){
 	 		if(logger.isDebugEnabled()){
		    	  logger.debug("'params_error' 还款冻结  返回处理  ,没有找到repaymentFrz的信息,结申请流水号是："+serino);
		      }
 	 	}
	    int listLength = resColumnList.size();
	   
	      StringBuffer responseMapMerged = new StringBuffer();
	      for (int i = 0; i < listLength; i++) {
	      	Object columnvalue = responseMap.get(resColumnList.get(i));
	      	if(columnvalue != null){
	      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
	      	}
	      }
 	      //验签
	      String responseSign = (String) responseMap.get("SIGN");
	      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
	      if(!verifyResult){//验证签名失败
 	    	  if(logger.isDebugEnabled()){
		    	  logger.debug("'params_error' 还款冻结  返回处理  验证签名失败,冻结申请流水号是："+serino+",返回码是："+responseMap.get("RETCODE")+", 返回描述："+responseMap.get("RETMSG"));
		      }
 	    	 hashMap.put(RepayMent_Constant.RESULT, "verifyResultFlag");//
 	    	 hashMap.put(RepayMent_Constant.FALG, false);
 	    	 hashMap.put(RepayMent_Constant.MSG, "还款冻结，返回处理  验证签名失败");
 	    	 repaymentFrz.setStatus((short)2);//交易状态 0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败
	    	 int count = 0;
	    	 count = repaymentFrzServiceI.updateById(repaymentFrz);
	    	 if(!(count > 0)){
	  	 	 		if(logger.isDebugEnabled()){
	  			    	  logger.debug("还款冻结  返回处理  ,repaymentFrz信息更新失败,冻结申请流水号是："+serino+",冻结银行返回码是："+responseMap.get("RETCODE").toString());
	  			      }
	  	 	 }
  	    	 return hashMap;
	      }
	      
	      if (!"00000000".equals(responseMap.get("RETCODE"))){//这里进行给用户提示
   			 hashMap.put(RepayMent_Constant.RESULT, "retcodeFlag");//
   			 hashMap.put(RepayMent_Constant.RETCODE, responseMap.get("RETCODE"));//
  	    	 hashMap.put(RepayMent_Constant.FALG, false);
 	    	 hashMap.put(RepayMent_Constant.MSG, "还款冻结失败,失败原因："+MessageUtil.getMassage(responseMap.get("RETCODE").toString())+",返回码是: "+responseMap.get("RETCODE").toString());
 	    	 repaymentFrz.setRetcode(responseMap.get("RETCODE").toString());//冻结银行返回码
 	    	 repaymentFrz.setStatus((short)2);//交易状态 0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败
   	    	 int count = 0;
	    	 count = repaymentFrzServiceI.updateById(repaymentFrz);
	    	 if(!(count > 0)){
	  	 	 		if(logger.isDebugEnabled()){
	  			    	  logger.debug("还款冻结  返回处理  ,repaymentFrz信息更新失败,冻结申请流水号是："+serino+",冻结银行返回码是："+responseMap.get("RETCODE").toString());
	  			      }
	  	 	 }
	    	 
 	    	 if(logger.isDebugEnabled()){
		    	  logger.debug("'params_error' 还款冻结  返回处理  处理失败 ,冻结申请流水号是："+serino+",返回码是："+responseMap.get("RETCODE")+" ,返回描述："+responseMap.get("RETMSG")+"");
		     }
    	     return hashMap;
	  	  }
 	 
		    JSONObject json 	= JSONObject.fromObject(responseMap);
 		    String PRMNO 		= json.getString("PRODUCT");//标的编号	PRODUCT	A	6	C	同请求
		    String FRZAMT 		= json.getString("AMOUNT");//冻结金额	AMOUNT	N	13,2	C	同请求
		    String CARDNBR      = json.getString("CARDNBR");//电子账号
   
  			repaymentFrz.setReturntime(new Date()); //银行冻结返回时间
   			repaymentFrz.setAmount(new BigDecimal(FRZAMT)); //冻结金额
  			repaymentFrz.setProduct(PRMNO); //标的编号
  			repaymentFrz.setCardnbr(CARDNBR);//电子账号
	    	repaymentFrz.setStatus((short)1);//交易状态 0取消 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败
   			repaymentFrz.setRetcode(responseMap.get("RETCODE").toString());//冻结银行返回码
  			int count = 0;
  			count = repaymentFrzServiceI.updateById(repaymentFrz);
  			if(!(count > 0)){
  	 	 		if(logger.isDebugEnabled()){
  			    	  logger.debug("还款冻结  返回处理  ,repaymentFrz信息更新失败,冻结申请流水号是："+serino+",冻结银行返回码是："+responseMap.get("RETCODE").toString());
  			      }
  	 	 	}
		    hashMap.put(RepayMent_Constant.RESULT, "success");
		    hashMap.put(RepayMent_Constant.FALG, true);
		    hashMap.put(RepayMent_Constant.MSG,"还款冻结成功！");
  			return hashMap;
	}
	
	
	/**
	 * 
	* @Title: repayMentUnfreeze 
	* @Description: TODO(还款解冻) 
	* @param @param CARDNBR 电子账号
	* @param @param AMOUNT  原冻结金额
	* @param @param PRODUCT 标的编号
	* @param @param batchNo 申请流水号
	* @param @param OLDSEQNO 原申请流水号
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static Map<String,Object> repayMentunFreeze(String CARDNBR,String AMOUNT,String PRODUCT,String batchNo,String OLDSEQNO){
   		Assert.hasText(CARDNBR, "'CARDNBR' 冻结用户电子账户 不能为空");
		Assert.hasText(AMOUNT, "'AMOUNT' 原冻结金额不能为空");
		Assert.hasText(PRODUCT, "'PRODUCT' 标的编号 不能为空");
		Assert.hasText(batchNo, "'batchNo' 流水号不能为空");
		Assert.hasText(OLDSEQNO, "'OLDSEQNO' 原交易流水号不能为空");
		
		RepaymentFrzServiceI repaymentFrzServiceI  = SpringContextHolder.getBean(RepaymentFrzServiceI.class);
  		Map<String,Object> hashMap = new HashMap<>();
  		hashMap.put(RepayMent_Constant.RESULT, "fail");// "因网络响应不及时,处理失败";
    	hashMap.put(RepayMent_Constant.FALG, false);
    	hashMap.put(RepayMent_Constant.MSG,"因网络响应不及时,操作失败！请重新操作 ");
    	
 		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
		String SEQNO   = StringUtil.getNo();//交易流水号
		
		String SERI_NO = HSignUtil.COINSTCODE + batchNo;//申请流水号
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<>();
		reqMap.put("TRXCODE", "5821");//交易代码	TRXCODE
	    reqMap.put("BANKCODE", HSignUtil.BANKCODE);//银行代码	BANKCODE
 	    reqMap.put("TRXDATE", TRXDATE);//交易日期	TRXDATE
	    reqMap.put("TRXTIME", TRXTIME);//交易时间	TRXTIME
	    reqMap.put("COINSTCODE",HSignUtil.COINSTCODE);//合作单位编号	COINSTCODE
	    reqMap.put("COINSTCHANNEL","000002");//合作单位渠道	COINSTCHANNEL
	    reqMap.put("SEQNO",SEQNO);//交易流水号	SEQNO
	    reqMap.put("SOURCE", HSignUtil.SOURCE);//ESB内部渠道	SOURCE
	    reqMap.put("RETCODE", "");//应答码	RETCODE
	    reqMap.put("RETMSG", "");//应答码描述	RETMSG
	    reqMap.put("HEADRESERVED", "");//报文头保留域	HEADRESERVED
	    
 	    reqMap.put("CARDNBR", CARDNBR);//电子账号	CARDNBR	A	19	M		
	    reqMap.put("SERI_NO", SERI_NO);//申请流水号	SERI_NO	A	40	M	"必填；用于交易的唯一性标识，需前台保证唯一性 还款冻结交易申请流水号 六位合作单位编号+各平台流水号生成规则"	0
	    reqMap.put("OLDSEQNO",OLDSEQNO);//原交易申请流水号 OLDSEQNO
	    reqMap.put("AMOUNT", AMOUNT);//原冻结金额	AMOUNT	N	13,2	M		空格
	    reqMap.put("FUISSUER", HSignUtil.FUISSUER);//产品发行方	FUISSUER	A	4	M		银联数据分配
	    reqMap.put("PRODUCT",PRODUCT); //标的编号	PRODUCT	A	6	M	"由产品发行方定义"	
	    reqMap.put("REMARK", "");//备注	REMARK	A	100	C	允许为空	
 
	    
	    RepaymentFrz repaymentFrz2 = new RepaymentFrz();
	    repaymentFrz2.setSerino(OLDSEQNO);//冻结申请流水号
 	    RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);
 	    if(repaymentFrz == null){
 	    	hashMap.put(RepayMent_Constant.RESULT, "network");// "因网络响应不及时,处理失败";
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG,"因网络响应不及时,操作失败！请重新操作 ");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'network' 还款解冻失败, 没有找到RepaymentFrz 信息 , 原冻结流水号是："+OLDSEQNO);
	    	}
	    	return hashMap;
 	    }
 	    
     	repaymentFrz.setThawtime(new Date()); //解冻时间
  		repaymentFrz.setThawserino(SERI_NO); //解冻申请流水号
    	int count = 0 ; 
    	count = repaymentFrzServiceI.updateById(repaymentFrz);
 	    if(!(count > 0)){
 	    	hashMap.put(RepayMent_Constant.RESULT, "network");// "因网络响应不及时,处理失败";
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG,"因网络响应不及时,操作失败！请重新操作 ");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'network' 还款解冻失败, RepaymentFrz 信息更新失败 , 原冻结流水号是："+OLDSEQNO);
	    	}
	    	return hashMap;
 	    }
 	    
	    String result = "";
	    try {
			  result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
 			hashMap.put(RepayMent_Constant.RESULT, "network");// "因网络响应不及时,处理失败";
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG,"还款解冻失败, httpClient请求发送失败 ");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'network' 还款解冻失败,httpClient请求发送失败 ,申请流水号是："+SERI_NO);
	    	}
	    	return hashMap;
		} catch (Exception e) {
 			e.printStackTrace();
		}
	    
	    if(StringUtil.isEmpty(result)){
	    	hashMap.put(RepayMent_Constant.RESULT, "result_null");
	    	hashMap.put(RepayMent_Constant.FALG, false);
	    	hashMap.put(RepayMent_Constant.MSG,"还款解冻失败, httpClient请求没有返回回调信息 ");
	    	if(logger.isDebugEnabled()){
	    		logger.debug("'result_null' 还款解冻失败, httpClient请求没有返回回调信息  ,申请流水号是："+SERI_NO);
	    	}
	    	return hashMap;
	    }
 	    
  		hashMap = unFreezeCallBack(result,OLDSEQNO, repaymentFrzServiceI);
	    return  hashMap;
	}
	
 	/**
	 * 
	* @Title: unFreezeCallBack 
	* @Description: TODO(还款解冻处理) 
	* @param @param result  回调数据
	* @param @param Frzorderno 交易流水号
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @author   cjm  
	* @throws
	 */
	public static Map<String,Object> unFreezeCallBack(String result,String OLDSEQNO,RepaymentFrzServiceI repaymentFrzServiceI){
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<String> userRegList = new ArrayList<>();
  	   	userRegList.add("CARDNBR");//电子账号	CARDNBR	A	19	M	
 	   	userRegList.add("NAME");//姓名	NAME	A	60	C	
 	   	userRegList.add("FUISSUER");//产品发行方	FUISSUER	A	4	C	同请求
 	   	userRegList.add("PRMNO");//标的编号	PRMNO	A	6	C	同请求
 	   	userRegList.add("FRZAMT");//冻结金额	FRZAMT	N	13,2	C	同请求
 	    userRegList.add("STATE");//记录状态 STATE	N	13,2	C	同请求
  	  
		String resresult = HSignUtil.getDecryptRSAByte(result);//解密
		Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resresult);//数据转化Map
 	 	List<String>  resColumnList = HSignUtil.getResponseHead(userRegList);//把5816接口返回报文参数和1到11域拼接一起
	 	RepaymentFrz repaymentFrz2 = new RepaymentFrz();
		repaymentFrz2.setSerino(OLDSEQNO);//解冻申请流水号
 	 	RepaymentFrz repaymentFrz = repaymentFrzServiceI.findRepaymentFrz(repaymentFrz2);
 	 	if(repaymentFrz == null){
 	 		if(logger.isDebugEnabled()){
		    	  logger.debug("还款解冻  返回处理  没有找到repaymentFrz 信息,原冻结申请流水号是："+OLDSEQNO);
		      }
 	 	}
	    int listLength = resColumnList.size();
 	      StringBuffer responseMapMerged = new StringBuffer();
	      for (int i = 0; i < listLength; i++) {
	      	Object columnvalue = responseMap.get(resColumnList.get(i));
	      	if(columnvalue != null){
	      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
	      	}
	      }
	      
	      //验签
	      String responseSign = (String) responseMap.get("SIGN");
	      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
	      if(!verifyResult){//验证签名失败
 	    	  if(logger.isDebugEnabled()){
		    	  logger.debug("'verifyResultFlag' 还款解冻  返回处理  验证签名失败,原冻结申请流水号是："+OLDSEQNO);
		      }
  	    	 hashMap.put(RepayMent_Constant.RESULT, "verifyResultFlag");//
 	    	 hashMap.put(RepayMent_Constant.FALG, false);
 	    	 hashMap.put(RepayMent_Constant.MSG, "还款解冻  返回处理  验证签名失败");
   	    	 return hashMap;
	      }
	      
	        
 	  	  if (!"00000000".equals(responseMap.get("RETCODE"))){//这里进行给用户提示
 	  		 repaymentFrz.setThawretcode(responseMap.get("RETCODE").toString());//解冻银行返回码
  	    	 repaymentFrzServiceI.updateById(repaymentFrz);
 	    	 if(logger.isDebugEnabled()){
		    	  logger.debug("'params_error' 还款解冻 返回处理  处理失败 ,原冻结申请流水号是："+OLDSEQNO+",返回码是："+responseMap.get("RETCODE")+" 返回描述："+responseMap.get("RETMSG")+"");
		     }
 	    	 
 	  		 hashMap.put(RepayMent_Constant.RESULT, "retcodeFlag");//
 	  		 hashMap.put(RepayMent_Constant.RETCODE, responseMap.get("RETCODE").toString());//返回码
  	    	 hashMap.put(RepayMent_Constant.FALG, false);
 	    	 hashMap.put(RepayMent_Constant.MSG, "还款解冻失败！,原冻结申请流水号是："+OLDSEQNO+",失败原因："+MessageUtil.getMassage(responseMap.get("RETCODE").toString())+",返回码是："+responseMap.get("RETCODE"));
   	    	 return hashMap;
	  	  } 
  	  	  
 	  	    repaymentFrz.setThawretcode(responseMap.get("RETCODE").toString());//解冻银行返回码 	 
 			repaymentFrz.setThawreturntime(new Date());/*银行解冻返回时间*/
   		    int count = 0;
	    	count = repaymentFrzServiceI.updateById(repaymentFrz);
	    	if(!(count > 0)){
  	 	 		if(logger.isDebugEnabled()){
  			    	  logger.debug("还款解冻  返回处理  ,repaymentFrz信息更新失败,原冻结申请流水号是："+OLDSEQNO+",冻结银行返回码是："+responseMap.get("RETCODE").toString());
  			     }
	  	 	 }
 		    hashMap.put(RepayMent_Constant.RESULT, RepayMent_Constant.SUCCESS);
		    hashMap.put(RepayMent_Constant.FALG, true);
		    hashMap.put(RepayMent_Constant.MSG, "还款解冻成功");
		    
  			return hashMap;
	}
	
	
	
	public static void main(String[] args) {
		ApplicationContext beanFactory = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		OracleSequenceMaxValueIncrementer incrementer =  (OracleSequenceMaxValueIncrementer) beanFactory.getBean("repaymentnumber");
		System.out.println(StringUtil.stringLeftPading(6, incrementer.nextStringValue(), 1) );
		
// 		UserBaseAccountInfo accountInfo = new UserBaseAccountInfo();
//		accountInfo.setLoginname("test12342");
// 		UserBaseAccountInfoServiceI baseAccountInfoServiceI = beanFactory.getBean(UserBaseAccountInfoServiceI.class);
//		UserBaseAccountInfo accountInfo2 = baseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(accountInfo);
//		UserFsAccountInfo userFsAccountInfo = beanFactory.getBean(UserFsAccountInfoServiceI.class).findUserFsAccountInfoByBaseId(accountInfo2.getId());
//		
//		TenderItem tenderItem = beanFactory.getBean(TenderItemServiceI.class).findTenderItemById(new BigDecimal(1902));
// 		HSRepayMentFreezeController.repayMentFreeze(userFsAccountInfo.getUsrcustid(), "12", tenderItem.getTno(),"20170421162540770918");
		 
//		Map<String,Object> fadg = HSRepayMentFreezeController.repayMentFreeze("9930040290000600013", "11.00", "GJ2re5", "20170804181459962539");
// 		System.out.println(fadg);
// 		
// 		Map<String,Object> fadg2 = HSRepayMentFreezeController.repayMentunFreeze("9930040290000600013", "11.00", "GJ2re5", "20170804151459924549", "00020020170804181459962539");
// 		System.out.println(fadg2);
	}
	
	
}
