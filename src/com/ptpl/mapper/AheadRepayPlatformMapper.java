package com.ptpl.mapper;

import com.ptpl.model.AheadRepayPlatform;
import java.math.BigDecimal;
/**
 * @author liuj
 * 提前还款奖励平台设置
 */
import java.util.List;
import java.util.Map;
public interface AheadRepayPlatformMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    //添加
    int insert(AheadRepayPlatform record);
    //添加
    int insertSelective(AheadRepayPlatform record);
    //根据id查信息
    AheadRepayPlatform selectByPrimaryKey(BigDecimal id);
    //跟新
    int updateByPrimaryKeySelective(AheadRepayPlatform record);
    //更新
    int updateByPrimaryKey(AheadRepayPlatform record);
    
    //根据tid查询信息
    List<AheadRepayPlatform> selectPayplatBytid(BigDecimal tid);
    
    //根据条件查询补偿凭条的列表
    List<AheadRepayPlatform> selectAheadRepayByStyle(AheadRepayPlatform aheadRepayPlatform);
    
    List<AheadRepayPlatform> selectAheadPlatMap(Map<String,Object> map);
}