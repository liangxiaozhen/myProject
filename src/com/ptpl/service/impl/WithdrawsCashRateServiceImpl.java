package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.WithdrawsCashRateMapper;
import com.ptpl.model.WithdrawsCashRate;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.WithdrawsCashRateServiceI;

/**
 * 提现费率接口实现
 * 
 * @author xiaoy
 *
 * @version 2016年5月31日 上午10:23:32
 */
public class WithdrawsCashRateServiceImpl implements WithdrawsCashRateServiceI {
	@Autowired
	private WithdrawsCashRateMapper wdcRateMapper;
	@Autowired
	private UserGradeServiceI userGradeService;

	@Override
	public int insert(WithdrawsCashRate wdcRate) {
		return wdcRateMapper.insert(wdcRate);
	}

	@Override
	public int insertSelective(WithdrawsCashRate wdcRate) {
		return wdcRateMapper.insertSelective(wdcRate);
	}

	@Override
	public WithdrawsCashRate selectByPrimaryKey(BigDecimal id) {
		WithdrawsCashRate wdcRate = wdcRateMapper.selectByPrimaryKey(id);
		setUgradeName(wdcRate);
		return wdcRate;
	}

	@Override
	public List<WithdrawsCashRate> selective(WithdrawsCashRate record) {
		List<WithdrawsCashRate> list = wdcRateMapper.selective(record);
		for (WithdrawsCashRate rate : list) {
			setUgradeName(rate);
		}
		return list;
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return wdcRateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WithdrawsCashRate record) {
		return wdcRateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WithdrawsCashRate record) {
		return wdcRateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int getCount() {
		return wdcRateMapper.getCount();
	}

	/**
	 * 设置会员等级名称
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月26日 上午9:57:37
	 * @param wdcRate
	 */
	private void setUgradeName(WithdrawsCashRate wdcRate) {
		Short ugrade = wdcRate.getUgrade();
		if (99 == ugrade.intValue()) {
			wdcRate.setUgradeStr("全部会员等级");
		} else {
			String ugradeName = userGradeService.getCodeByUserGradeName(new BigDecimal(ugrade));
			wdcRate.setUgradeStr(ugradeName);
		}
	}

	@Override
	public WithdrawsCashRate getWithdrawsCashRate(WithdrawsCashRate record) {
		return wdcRateMapper.getWithdrawsCashRate(record);
	}
}
