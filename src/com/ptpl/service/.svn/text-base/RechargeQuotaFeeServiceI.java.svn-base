package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RechargeQuotaFee;

public interface RechargeQuotaFeeServiceI {
	 	int deleteByPrimaryKey(BigDecimal id);

	    int insert(RechargeQuotaFee record);

	    int insertSelective(RechargeQuotaFee record);

	    RechargeQuotaFee selectByPrimaryKey(BigDecimal id);

	    int updateByPrimaryKeySelective(RechargeQuotaFee record);

	    int updateByPrimaryKey(RechargeQuotaFee record);
	    /**
	     * 根据充值费率id查询到手续费率标的数据
	     * @param @param id
	     * @param @return
	     * @return List<RechargeQuotaFee>
	     * @author jiangxueyou
	     */
	    List<RechargeQuotaFee> selectByrrid(BigDecimal id);
}
