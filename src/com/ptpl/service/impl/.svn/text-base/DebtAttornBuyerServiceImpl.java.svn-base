package com.ptpl.service.impl;

import com.ptpl.mapper.DebtAttornBuyerMapper;
import com.ptpl.model.DebtAttornBuyer;
import com.ptpl.service.DebtAttornBuyerServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 债权转让购买人设置impl
 */
public class DebtAttornBuyerServiceImpl extends BaseServiceImpl<DebtAttornBuyer> implements DebtAttornBuyerServiceI{
	
	@Autowired
	protected DebtAttornBuyerMapper debtattornbuyermapper;
	@Override
	public DebtAttornBuyer selectByTid(BigDecimal tid) {
		return debtattornbuyermapper.selectByTid(tid);
	}

	@Override
	public List<DebtAttornBuyer> selectListByTid(BigDecimal tid) {
		return debtattornbuyermapper.selectListByTid(tid);
	}

}
