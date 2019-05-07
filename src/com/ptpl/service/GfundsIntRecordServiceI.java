package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.GfundsIntRecord;

/**
 * 标的站岗利息记录Service
 * @author zhenglm
 *
 */
public interface GfundsIntRecordServiceI {

    /**
     * 新增标的站岗利息记录
     * @param record
     * @return
     */
    int insertSelective(GfundsIntRecord record);

    /**
     * 查看标的站岗利息记录详情
     * @param id
     * @return
     */
    GfundsIntRecord selectByPrimaryKey(BigDecimal id);

    /**
     * 修改标的站岗利息记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GfundsIntRecord record);
    
    /**
     * 查询标的站岗利息记录列表
     * @param record
     * @return
     */
    List<GfundsIntRecord> findGfundsIntRecord(GfundsIntRecord record);

	/**
	 * 时间格式化
	 * @param funds
	 * @return 
	 */
    void dealTime(GfundsIntRecord funds);
    
    /**
     * 根据投资人id查询标的站岗利息记录
     * @param record
     * @return
     */
    List<GfundsIntRecord> findGfundsIntRecordByInvestorId(GfundsIntRecord record);
}
