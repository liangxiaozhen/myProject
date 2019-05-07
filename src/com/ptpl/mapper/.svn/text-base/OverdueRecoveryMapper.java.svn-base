package com.ptpl.mapper;

import com.ptpl.model.OverdueRecovery;
import java.math.BigDecimal;
/**
 * @author liuj
 * 标的逾期平台追偿设置
 */
import java.util.List;
public interface OverdueRecoveryMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    //添加
    int insert(OverdueRecovery record);
    //添加
    int insertSelective(OverdueRecovery record);
    //根据id查信息
    OverdueRecovery selectByPrimaryKey(BigDecimal id);
    //更新
    int updateByPrimaryKeySelective(OverdueRecovery record);
    //更新
    int updateByPrimaryKey(OverdueRecovery record);
    
    //根据tid查询信息
    List<OverdueRecovery> selectoverRecBytid(BigDecimal tid);
}