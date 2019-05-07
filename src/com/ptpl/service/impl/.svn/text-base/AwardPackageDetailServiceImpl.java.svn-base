package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AwardPackageDetailMapper;
import com.ptpl.mapper.AwardPackageMapper;
import com.ptpl.model.AwardPackageDetail;
import com.ptpl.service.AwardPackageDetailServiceI;


public class AwardPackageDetailServiceImpl implements AwardPackageDetailServiceI {
	@Autowired
	private AwardPackageDetailMapper awardPackageDetailMapper;

	@Override
	public int insertSelective(AwardPackageDetail apd) {
		
		return awardPackageDetailMapper.insertSelective(apd);
	}

	/**
	 * 根据奖品包的id获取奖品包内容列表    获取多个
	 * @param id
	 * @return
	 */
	public List<AwardPackageDetail> getAwardPackageDetailByApId(BigDecimal id) {
		
		return awardPackageDetailMapper.getAwardPackageDetailByApId(id);
	}

	/**
	 * 根据奖品包的id删除奖品包内容列表    删除多个
	 * @param id
	 * @return
	 */
	public int deleteAwardPackageDetailByApId(BigDecimal bigDecimal) {
		return awardPackageDetailMapper.deleteAwardPackageDetailByApId(bigDecimal);
	}

	/**
	 * 根据奖品包的id获取奖品包内容列表 (联合奖品表查询)   获取多个
	 * @param id
	 * @return
	 */
	public List<AwardPackageDetail> getAPDsByApId(BigDecimal id) {
		return awardPackageDetailMapper.getAPDsByApId(id);
	}

	/**
	 * 根据奖品的编号更新奖品的名称
	 * @param apd
	 * @return
	 */
	public int updateAwardNameByAwardNO(AwardPackageDetail apd) {
		return awardPackageDetailMapper.updateAwardNameByAwardNO(apd);
	}

	
}
