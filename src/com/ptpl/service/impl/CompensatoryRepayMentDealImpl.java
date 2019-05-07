package com.ptpl.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.controller.BaseController;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.TenderItem;
import com.ptpl.service.CompensatoryRepayMentDealI;
import com.ptpl.service.TenderItemServiceI;
/**
 * 
* @ClassName: CompensatoryRepayMentDealImpl 
* @Description: TODO(代偿还款 实现类) 
* @author cjm 
* @date 2017年7月17日 下午2:09:25 
*
 */
public class CompensatoryRepayMentDealImpl extends BaseController implements CompensatoryRepayMentDealI{
	
	@Autowired
	private TenderItemServiceI tenderItemServiceI;
	/**
	  * 
	 * @Title: normalCompensatoryRepayMent 
	 * @Description: TODO(正常代偿还款) 
	 * @param @param dividedPayments
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	public Map<String,String> normalCompensatoryRepayMent(DividedPayments dividedPayments,TenderItem tenderItem){
		Map<String,String> hashMap = new HashMap<>();
		if(dividedPayments == null || dividedPayments.getTenderid() == null){
			if(logger.isDebugEnabled()){
				logger.debug("CompensatoryRepayMentDealImpl.java 代偿还款处理类,正常代偿还款处理失败！借款人还款计划信息 'dividedPayments' 不能为空！");
			}
			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！借款人还款计划信息 'dividedPayments' 不能为空！");
			return hashMap;
		}
		
 		if(tenderItem == null){
			if(logger.isDebugEnabled()){
				logger.debug("CompensatoryRepayMentDealImpl.java 代偿还款处理类,正常代偿还款处理失败！借款人还款计划信息'dividedPayments' 不能为空！");
			}
			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的信息'TenderItem' 不能为空！");
			return hashMap;
		}
		
		
		 
		
		
		
		
		
		return hashMap ;
	}
}
