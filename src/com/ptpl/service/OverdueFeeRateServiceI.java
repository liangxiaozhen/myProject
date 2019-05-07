package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.OverdueFeeRate;

/**
 * @author liuj
 * 逾期滞纳金费率设置
 */
public interface OverdueFeeRateServiceI {
	// 删除
	int deleteByPrimaryKey(BigDecimal id);
    
	//添加
    int insert(OverdueFeeRate record);
    
    //添加
    int insertSelective(OverdueFeeRate record);
    
    //根据tid查询信息
    OverdueFeeRate selectByPrimaryKey(BigDecimal id);
    
    //跟新
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
