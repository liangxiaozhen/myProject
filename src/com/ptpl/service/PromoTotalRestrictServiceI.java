package com.ptpl.service;

import java.math.BigDecimal;

import com.ptpl.model.PromoTotalRestrict;

/**
 * 推广人数限制service
 * @author xiaoy
 * @date 2016年12月14日 下午6:22:46 
 * @Version 1.0
 */
public interface PromoTotalRestrictServiceI {

    PromoTotalRestrict selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(PromoTotalRestrict record);
}
