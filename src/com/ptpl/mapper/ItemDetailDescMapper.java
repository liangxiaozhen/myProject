package com.ptpl.mapper;

import com.ptpl.model.ItemDetailDesc;
import java.math.BigDecimal;
import java.util.List;
 
public interface ItemDetailDescMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(ItemDetailDesc record);

    int insertSelective(ItemDetailDesc record);

    ItemDetailDesc selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(ItemDetailDesc record);

    int updateByPrimaryKey(ItemDetailDesc record);
    
    //乾多多 前段调用  通过模块类别来查数据
    List<ItemDetailDesc> callbackitemDesc(String moduletype,BigDecimal tid);
    
    //乾多多 前端调用  查询有哪些模块
    List<ItemDetailDesc> callbackmoduleType();
}