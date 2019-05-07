package com.ptpl.service;

import java.util.Map;

import com.ptpl.model.DividedPayments;
import com.ptpl.model.TenderItem;

/**
 * 
* @ClassName: CompensatoryRepayMentDealI 
* @Description: TODO(代偿 还款类) 
* @author cjm 
* @date 2017年7月17日 下午2:07:25 
*
 */
public interface CompensatoryRepayMentDealI {

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
	Map<String,String> normalCompensatoryRepayMent(DividedPayments dividedPayments,TenderItem tenderItem);
}
