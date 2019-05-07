package com.ptpl.mapper;

import com.ptpl.model.GuaranteeFeeRecord;
import java.math.BigDecimal;
import java.util.List;

/**
 * 标的担保费记录表Mapper
 * @author zhenglm
 */
public interface GuaranteeFeeRecordMapper {
	
	/**
	 * 根据主键ID删除标的担保费记录
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增标的担保费记录（参数可选）
     * @param record
     * @return
     */
    int insertSelective(GuaranteeFeeRecord record);

    /**
     * 根据主键ID查询标的担保费记录详情
     * @param id
     * @return
     */
    GuaranteeFeeRecord selectByPrimaryKey(BigDecimal id);

    /**
     * 根据主键ID更新标的担保费记录（参数可选）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GuaranteeFeeRecord record);
    
    /**
     * 条件查询标的担保费记录
     * @param record
     * @return
     */
    List<GuaranteeFeeRecord> selectAllByCondition(GuaranteeFeeRecord record);
    
    /**
     * 根据投标订单号查询标的担保费记录
     * @param orderno
     * @return
     */
    GuaranteeFeeRecord selectByUtOrderNo(String orderno);
}