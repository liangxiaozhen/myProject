package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AwardTenderLink;

public interface AwardTenderLinkServiceI {

	/**
	 * 插入数据
	 * @param atl
	 */
	int insertSelective(AwardTenderLink atl);

	/**
	 * 根据奖品id获取列表（获得多个）
	 * @param id
	 * @return
	 */
	List<AwardTenderLink> getAwardTenderLinksByawardId(BigDecimal id);

	/**
	 * 根据奖品的id删除对应的记录
	 * @param id
	 * @return
	 */
	int deleteAwardTenderLink(BigDecimal id);

}
