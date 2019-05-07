package com.ptpl.mapper;

import com.ptpl.model.PromoTotalRestrict;
import java.math.BigDecimal;

public interface PromoTotalRestrictMapper {

    PromoTotalRestrict selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(PromoTotalRestrict record);
}