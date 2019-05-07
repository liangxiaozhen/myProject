package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.Award;
import com.ptpl.model.AwardPackage;
import com.ptpl.model.AwardPackageDetail;
import com.ptpl.model.TenderItem;

public interface AwardPackageDetailServiceI{

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
