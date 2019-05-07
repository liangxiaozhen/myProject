package com.ptpl.mapper;

import com.ptpl.model.OverdueFeeRate;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 逾期滞纳金费率设置
 */
public interface OverdueFeeRateMapper {
	
    int deleteByPrimaryKey(BigDecimal id);

    int insert(OverdueFeeRate record);

    int insertSelective(OverdueFeeRate record);

    OverdueFeeRate selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(OverdueFeeRate record);

    int updateByPrimaryKey(OverdueFeeRate record);
    
    /**
     * 
    * @Title: findOverdueFeeRatesByTid 
    * @Description: TODO(根据标ID查找逾期滞纳金费率信息) 
    * @param @return  参数说明 
    * @return List<OverdueFeeRate>    返回类型 
    * @author cjm
    * @throws
     */
    List<OverdueFeeRate> findOverdueFeeRatesByTid(BigDecimal Tid);
}