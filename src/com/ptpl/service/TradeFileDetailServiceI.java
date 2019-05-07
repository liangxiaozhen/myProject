package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.TradeFileDetail;

/**
 * 交易明细流水Service
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年5月13日 上午10:54:35
 *
 */
public interface TradeFileDetailServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(TradeFileDetail record);

	int insertSelective(TradeFileDetail record);

	TradeFileDetail selectByPrimaryKey(BigDecimal id);

	List<TradeFileDetail> listTradeFileDetail(TradeFileDetail record);

	int updateByPrimaryKeySelective(TradeFileDetail record);

	int updateByPrimaryKey(TradeFileDetail record);
	/**
	 * 处理交易明细流水文件
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年5月13日 下午3:10:00 
	 * @param bacthFileRecord
	 * @return
	 */
	boolean tradeFileDetailDeal(BacthFileRecord bacthFileRecord);

}
