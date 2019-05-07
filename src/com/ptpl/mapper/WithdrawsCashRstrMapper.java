package com.ptpl.mapper;

import com.ptpl.model.WithdrawsCashRstr;
import java.math.BigDecimal;
import java.util.List;

public interface WithdrawsCashRstrMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(WithdrawsCashRstr record);

	WithdrawsCashRstr selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(WithdrawsCashRstr record);

	List<WithdrawsCashRstr> selective(WithdrawsCashRstr record);
	
	int getCount();
}