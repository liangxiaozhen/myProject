package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.AheadRepayMode;

public interface AheadRepayModeServiceI {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    //添加
    int insertSelective(AheadRepayMode record);
    //根据id查信息
    AheadRepayMode selectByPrimaryKey(BigDecimal id);
    //跟新
    int updateByPrimaryKeySelective(AheadRepayMode record);
    
    //更新
    List<AheadRepayMode> selectModeBytid(BigDecimal tid);
    
  //按条件查询和查询所有
    List<AheadRepayMode> selectModeByStyle(AheadRepayMode aheadRepayMode);
    
    List<AheadRepayMode> selectModeByMap(Map<String,Object> map);
}
