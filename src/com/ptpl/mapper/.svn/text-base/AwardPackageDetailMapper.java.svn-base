package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AwardPackageDetail;

public interface AwardPackageDetailMapper{
	
	/**
	 * 向奖品包内容表里面插入数据
	 * @param apd
	 * @return
	 */
	int insertSelective(AwardPackageDetail apd);
	
	/**
	 * 根据奖品包的id获取奖品包内容列表    获取多个
	 * @param id
	 * @return
	 */
	List<AwardPackageDetail> getAwardPackageDetailByApId(BigDecimal id);
	
	/**
	 * 根据奖品包的id删除奖品包内容列表    删除多个
	 * @param id
	 * @return
	 */
	int deleteAwardPackageDetailByApId(BigDecimal bigDecimal);

	/**
	 * 根据奖品包的id获取奖品包内容列表 (联合奖品表查询)   获取多个
	 * @param id
	 * @return
	 */
	List<AwardPackageDetail> getAPDsByApId(BigDecimal id);

	/**
	 * 根据奖品的编号更新奖品的名称
	 * @param apd
	 * @return
	 */
	int updateAwardNameByAwardNO(AwardPackageDetail apd);
}