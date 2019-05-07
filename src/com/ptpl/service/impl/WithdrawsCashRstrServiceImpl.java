package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.WithdrawsCashRstrMapper;
import com.ptpl.model.WithdrawsCashRstr;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.WithdrawsCashRstrServiceI;

/**
 * 
 * @author xiaoy
 *
 * @version 2016年7月7日 上午11:03:06
 */
public class WithdrawsCashRstrServiceImpl implements WithdrawsCashRstrServiceI {
	@Autowired
	private WithdrawsCashRstrMapper withdrawsCashRstrMapper;
	@Autowired
	private UserGradeServiceI userGradeService;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return withdrawsCashRstrMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WithdrawsCashRstr record) {
		return withdrawsCashRstrMapper.insertSelective(record);
	}

	@Override
	public WithdrawsCashRstr selectByPrimaryKey(BigDecimal id) {
		WithdrawsCashRstr wdcRstr = withdrawsCashRstrMapper.selectByPrimaryKey(id);
		setUgradeName(wdcRstr);
		return wdcRstr;
	}

	@Override
	public List<WithdrawsCashRstr> selective(WithdrawsCashRstr record) {
		List<WithdrawsCashRstr> list = withdrawsCashRstrMapper.selective(record);
		for (WithdrawsCashRstr wdcRstr : list) {
			setUgradeName(wdcRstr);
		}
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(WithdrawsCashRstr record) {
		return withdrawsCashRstrMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 设置会员等级名称
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月26日 上午9:58:43
	 * @param wdcRstr
	 */
	private void setUgradeName(WithdrawsCashRstr wdcRstr) {
		Short ugrade = wdcRstr.getUgrade();
		if (99 == ugrade.intValue()) {
			wdcRstr.setUgradeStr("全部会员等级");
		} else {
			String ugradeName = userGradeService.getCodeByUserGradeName(new BigDecimal(ugrade));
			wdcRstr.setUgradeStr(ugradeName);
		}
	}

	@Override
	public int getCount() {
		return withdrawsCashRstrMapper.getCount();
	}
}
