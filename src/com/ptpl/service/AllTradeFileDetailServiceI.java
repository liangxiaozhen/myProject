package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AllTradeFileDetail;
import com.ptpl.model.BacthFileRecord;
/**
 * 全流水明细Service
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年5月13日 上午10:54:47 
 *
 */
public interface AllTradeFileDetailServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(AllTradeFileDetail record);

	int insertSelective(AllTradeFileDetail record);

	AllTradeFileDetail selectByPrimaryKey(BigDecimal id);

	List<AllTradeFileDetail> listAllTradeFileDetail(AllTradeFileDetail record);

	int updateByPrimaryKeySelective(AllTradeFileDetail record);

	int updateByPrimaryKey(AllTradeFileDetail record);
	/**
	 * 处理全流水文件
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年5月15日 上午11:35:08 
	 * @param bacthFileRecord
	 * @return
	 */
	boolean allTradeFileDetailDeal(BacthFileRecord bacthFileRecord);
}
