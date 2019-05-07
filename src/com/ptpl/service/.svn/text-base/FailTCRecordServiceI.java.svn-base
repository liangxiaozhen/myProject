package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.FailTCRecord;

/**
 * 标的流标补偿记录Service
 * @author zhenglm
 *
 */
public interface FailTCRecordServiceI {
	
    /**
     * 新增标的流标补偿记录
     * @param record
     * @return
     */
    int insertSelective(FailTCRecord record);

    /**
     * 查看标的流标补偿记录详情
     * @param id
     * @return
     */
    FailTCRecord selectByPrimaryKey(BigDecimal id);

    /**
     * 根据流标补偿流水号修改标的流标补偿记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(FailTCRecord record);

    /**
     * 查看标的流标补偿记录列表
     * @param record
     * @return
     */
    List<FailTCRecord> findFailTCRecordList(FailTCRecord record);
    
    /**
     * 处理转换时间格式
     * @param record
     */
    void dealTime(FailTCRecord record);
    
    /**
     * 根据投资人id查询标的流标补偿记录
     * @param record
     * @return
     */
    List<FailTCRecord> findFailTCRecordByInvestorId(FailTCRecord record);
}
