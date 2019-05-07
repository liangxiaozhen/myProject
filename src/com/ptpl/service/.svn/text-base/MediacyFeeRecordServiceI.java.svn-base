package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.MediacyFeeRecord;

/**
 * 标的居间费记录表Service
 * @author zhenglm
 */
public interface MediacyFeeRecordServiceI {
	
	/**
	 * 根据主键ID删除标的居间费记录
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增标的居间费记录（参数可选）
     * @param record
     * @return
     */
    int insertSelective(MediacyFeeRecord record);

    /**
     * 根据主键ID查询标的居间费记录详情
     * @param id
     * @return
     */
    MediacyFeeRecord selectByPrimaryKey(BigDecimal id);

    /**
     * 根据主键ID更新标的居间费记录（参数可选）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MediacyFeeRecord record);
    
    /**
     * 条件查询标的居间费记录
     * @param record
     * @return
     */
    List<MediacyFeeRecord> selectAllByCondition(MediacyFeeRecord record);
    
    /**
     * 根据投标订单号查询标的居间费记录
     * @param orderno
     * @return
     */
    MediacyFeeRecord selectByUtOrderNo(String orderno);
}
