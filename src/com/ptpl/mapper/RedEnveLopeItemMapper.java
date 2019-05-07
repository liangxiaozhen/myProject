package com.ptpl.mapper;

import com.ptpl.model.RedEnveLopeItem;
import java.math.BigDecimal;
import java.util.List;

public interface RedEnveLopeItemMapper {
	//删除对象
    int deleteByPrimaryKey(BigDecimal id);
    //添加数据
    int insert(RedEnveLopeItem record);
    //选择性添加
    int insertSelective(RedEnveLopeItem record);
    //根据Id查对象
    RedEnveLopeItem selectByPrimaryKey(BigDecimal id);
    //选择性修改
    int updateByPrimaryKeySelective(RedEnveLopeItem record);
    //修改数据
    int updateByPrimaryKey(RedEnveLopeItem record);
    
    //根据流水号查数据
    RedEnveLopeItem getByOrderNo(String OrderNo);
    //查询对账记录
    List<RedEnveLopeItem> selectByCondition(RedEnveLopeItem reli);
    
}