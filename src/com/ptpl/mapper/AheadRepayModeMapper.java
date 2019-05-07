package com.ptpl.mapper;

import com.ptpl.model.AheadRepayMode;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AheadRepayModeMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);

    int deleteByTid(BigDecimal tid);
    //添加
    int insert(AheadRepayMode record);
    //添加
    int insertSelective(AheadRepayMode record);
    //根据id查信息
    AheadRepayMode selectByPrimaryKey(BigDecimal id);
    //跟新
    int updateByPrimaryKeySelective(AheadRepayMode record);
    //更新
    int updateByPrimaryKey(AheadRepayMode record);
    

    List<AheadRepayMode> selectModeBytid(BigDecimal tid);
    //按条件查询和查询所有
    List<AheadRepayMode> selectModeByStyle(AheadRepayMode aheadRepayMode);
    //时间条件查询
    List<AheadRepayMode> selectModeByMap(Map<String,Object> map);
}