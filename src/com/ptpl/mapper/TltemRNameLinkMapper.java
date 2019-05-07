package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.TltemRNameLink;

public interface TltemRNameLinkMapper {
	//新增
    int insert(TltemRNameLink record);

    //新增
    int insertSelective(TltemRNameLink record);
    
    /**
     * 根据标的ID查询投标排除名单
     * @param tid
     * @return
     */
    List<TltemRNameLink> findTenderRemoveNameByTid(BigDecimal tid);
}