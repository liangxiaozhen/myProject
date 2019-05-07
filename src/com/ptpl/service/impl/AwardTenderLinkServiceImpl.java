package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AwardTenderLinkMapper;
import com.ptpl.model.AwardTenderLink;
import com.ptpl.service.AwardTenderLinkServiceI;

public class AwardTenderLinkServiceImpl implements AwardTenderLinkServiceI{

	@Autowired
	private AwardTenderLinkMapper awardTenderLinkMapper;
	
	/**
	 * 插入数据
	 */
	public int insertSelective(AwardTenderLink atl) {
		return awardTenderLinkMapper.insertSelective(atl);
	}

	/**
	 * 根据奖品id获取列表（获得多个）
	 * @param id
	 * @return
	 */
	public List<AwardTenderLink> getAwardTenderLinksByawardId(BigDecimal id) {
		return awardTenderLinkMapper.getAwardTenderLinksByawardId(id);
	}

	/**
	 * 根据奖品的id删除对应的记录
	 * @param id
	 * @return
	 */
	public int deleteAwardTenderLink(BigDecimal id) {
		return awardTenderLinkMapper.deleteAwardTenderLink(id);
	}

}
