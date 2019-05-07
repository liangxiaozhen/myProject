package com.ptpl.mapper;

import com.ptpl.model.AheadRepayAward;
import java.math.BigDecimal;
/**
 * @author liuj
 * 提前还款个人奖品奖励mapper
 */
import java.util.List;
import java.util.Map;
public interface AheadRepayAwardMapper {
	
	//删除
    int deleteByPrimaryKey(BigDecimal id);

    //添加
    int insert(AheadRepayAward record);

    //添加
    int insertSelective(AheadRepayAward record);

    //根据id查信息
    AheadRepayAward selectByPrimaryKey(BigDecimal id);

    //跟新
    int updateByPrimaryKeySelective(AheadRepayAward record);

    //跟新
    int updateByPrimaryKey(AheadRepayAward record);
    
    //根据tid查询信息
    List<AheadRepayAward> selectahpayAwardBytid(BigDecimal tid);
    //查询增益列表
    List<AheadRepayAward> selectAheadRepayZY(AheadRepayAward aheadRepayAward);
    
    List<AheadRepayAward> selectAheadAwardMap(Map<String,Object> map);
}