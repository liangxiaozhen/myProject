package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.AheadRepayPlatform;

/**
 * @author liuj
 * 提前还款奖励平台
 */
public interface AheadRepayPlatformServiceI {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    //添加
    int insertSelective(AheadRepayPlatform record);
    //根据id查信息
    AheadRepayPlatform selectByPrimaryKey(BigDecimal id);
    //跟新
    int updateByPrimaryKeySelective(AheadRepayPlatform record);
    //根据tid查询信息
    List<AheadRepayPlatform> selectPayplatBytid(BigDecimal tid);
    //根据条件查询补偿凭条的列表
    List<AheadRepayPlatform> selectAheadRepayByStyle(AheadRepayPlatform aheadRepayPlatform);
    
    List<AheadRepayPlatform> selectAheadPlatMap(Map<String,Object> map);
}
