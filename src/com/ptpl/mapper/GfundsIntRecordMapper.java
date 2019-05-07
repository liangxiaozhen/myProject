package com.ptpl.mapper;

import com.ptpl.model.GfundsIntRecord;
import java.math.BigDecimal;
import java.util.List;

/**
 * 标的站岗利息记录Mapper
 * @author zhenglm
 *
 */
public interface GfundsIntRecordMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(GfundsIntRecord record);

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

    int updateByPrimaryKey(GfundsIntRecord record);
    
    /**
     * 查询标的站岗利息记录列表
     * @param record
     * @return
     */
    List<GfundsIntRecord> findGfundsIntRecord(GfundsIntRecord record);
    
    /**
     * 根据投资人id查询标的站岗利息记录
     * @param record
     * @return
     */
    List<GfundsIntRecord> findGfundsIntRecordByInvestorId(GfundsIntRecord record);
}