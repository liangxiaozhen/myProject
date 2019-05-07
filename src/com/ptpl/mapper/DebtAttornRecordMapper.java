package com.ptpl.mapper;

import com.ptpl.model.DebtAttornRecord;
import java.math.BigDecimal;

public interface DebtAttornRecordMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(DebtAttornRecord record);

    int insertSelective(DebtAttornRecord record);

    DebtAttornRecord selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(DebtAttornRecord record);

    int updateByPrimaryKey(DebtAttornRecord record);
}