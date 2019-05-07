package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentFrz;
 
/**
 * 
* @ClassName: ThirdRepayMentDealI 
* @Description: TODO(第三方支付/银行  还款处理工具类) 
* @author cjm 
* @date 2017年5月22日 下午3:23:45 
*
 */
public interface ThirdRepayMentDealI {

	/**
	 * 
	* @Title: settingUpBatchAheadRepayMent 
	* @Description: TODO(提前还款) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayment  借款人还款计划
	* @param @param repayMents  投资人还款计划集合
	* @param @param rbatchno 还款批次号
	* @param @param Usrcustid 借款人电子账号
	* @param @param totalamount 本批次还款金额   注： 如果是及时还款 才传借款人电子账号，标号，本批次还款金额 
	* @param @param Tno  标号
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	* 注：如果是及时还款 才传借款人电子账号，标号，本批次还款金额   冻结还款，客服审核通过后调用还款接口  不需要解冻还款金额 （标号，第三方电子账号，冻结金额传空值）
	 */
	Map<String,Object>  settingUpBatchAheadRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno);
	
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
	* 注：如果是及时还款 才传借款人电子账号，标号，本批次还款金额   冻结还款，客服审核通过后调用还款接口  不需要解冻还款金额 （标号，第三方电子账号，冻结金额传空值）
	 */
	Map<String,Object>  settingUpBatchNormalRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno);
	
	/**
	 * 
	* @Title: settingUpBatchOverdueRepayMent 
	* @Description: TODO(逾期还款) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayment
	* @param @param repayMents
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	Map<String,Object>  settingUpBatchOverdueRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno);
	
	/**
	 * 
	* @Title: settingUpBatchNormalCompensatoryRepayMent 
	* @Description: TODO(正常代偿还款) 
	* @param @param repayMents
	* @param @param rbatchno
	* @param @param Usrcustid
	* @param @param totalamount
	* @param @param Tno
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author   cjm  
	* @throws
	 */
	public Map<String,Object> settingUpBatchNormalCompensatoryRepayMent(List<RepayMent> repayMents,String rbatchno,String Usrcustid,String totalamount,String Tno);
	/**
	 * 
	* @Title: repayMentFileUpload 
	* @Description: TODO(到期还款 文件上传接口 ) 
	* @param @param bacthFileRecord
	* @param @return    设定文件 
	* @return boolean    返回类型   true 上传成功
	* @author   cjm  
	* @throws
	 */
	boolean repayMentFileUpload(BacthFileRecord bacthFileRecord);
	
	/**
	 * 
	* @Title: repayMentFileDeal 
	* @Description: TODO(到期还款 结果文件处理接口) 
	* @param @param bacthFileRecord
	* @param @return    设定文件 
	* @return boolean    返回类型  返回true 处理成功
	* @author   cjm  
	* @throws
	 */
	boolean repayMentFileDeal(BacthFileRecord bacthFileRecord);
	
	/**
	 * 
	* @Title: updateDividedPaymentsByRepayMent 
	* @Description: TODO(更新 借款人还款状态) 
	* @param @param repayMent    设定文件 
	* @return void    返回类型 
	* @author   cjm  
	* @throws
	 */
	void updateDividedPaymentsByRepayMent(BigDecimal tenderid,Integer periods);
	
	/**
	 * 
	* @Title: timTaskRepayMent 
	* @Description: TODO(定时还款) 
	* @param     设定文件 
	* @return void    返回类型 
	* @author   cjm  
	* @throws
	 */
	void timTaskRepayMent();
	
	/**
	 * 解冻处理
	 * @return
	 */
	Map<String,Object> unfreezeDeal(RepaymentFrz repayMentFrz);
	 
}
