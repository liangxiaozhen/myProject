package com.ptpl.mapper;

import com.ptpl.model.GuaranteeInfo;
import java.math.BigDecimal;
import java.util.List;

public interface GuaranteeInfoMapper {
	
	/**
	 * 删除担保公司资料
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    int insert(GuaranteeInfo record);

    /**
     * 新增担保公司资料
     * @param record
     * @return
     */
    int insertSelective(GuaranteeInfo record);

    /**
     * 根据主键查询担保公司资料详情
     * @param id
     * @return
     */
    GuaranteeInfo selectByPrimaryKey(BigDecimal id);
    
    /**
     * （条件）获取担保公司资料列表信息
     * @param guarantee
     * @return
     */
    List<GuaranteeInfo> findGuaranteeList(GuaranteeInfo guarantee);

    /**
     * 修改担保公司资料
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GuaranteeInfo record);

    int updateByPrimaryKey(GuaranteeInfo record);
}