package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RiskGuarantyFeeRecord;

/**
 * 标的风险保证金记录表Service
 * @author zhenglm
 */
public interface RiskGuarantyFeeRecordServiceI {
	
	/**
	 * 根据主键ID删除标的风险保证金记录
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增标的风险保证金记录（参数可选）
     * @param record
     * @return
     */
    int insertSelective(RiskGuarantyFeeRecord record);

    /**
     * 根据主键ID查询标的风险保证金记录详情
     * @param id
     * @return
     */
    RiskGuarantyFeeRecord selectByPrimaryKey(BigDecimal id);

    /**
     * 根据主键ID更新标的风险保证金记录（参数可选）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RiskGuarantyFeeRecord record);
    
    /**
     * 条件查询标的风险保证金记录
     * @param record
     * @return
     */
    List<RiskGuarantyFeeRecord> selectAllByCondition(RiskGuarantyFeeRecord record);
    
    /**
     * 根据投标订单号查询标的风险保证金记录
     * @param orderno
     * @return
     */
    RiskGuarantyFeeRecord selectByUtOrderNo(String orderno);
}
