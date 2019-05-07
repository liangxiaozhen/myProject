package com.ptpl.service.impl;

import com.ptpl.mapper.DebtAttornFeeMapper;
import com.ptpl.model.DebtAttornFee;
import com.ptpl.service.DebtAttornFeeServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 标的债权转让手续费设置impl
 */
public class DebtAttornFeeServiceImpl extends BaseServiceImpl<DebtAttornFee> implements DebtAttornFeeServiceI{
	@Autowired
	DebtAttornFeeMapper debtAttornFeeMapper;

	@Override
	public List<DebtAttornFee> selectByTid(BigDecimal tid) {
		return debtAttornFeeMapper.selectByTid(tid);
	}

	@Override
	public List<String> selectGradeByTid(BigDecimal tid) {
		return debtAttornFeeMapper.selectGradeByTid(tid);
	}

}
