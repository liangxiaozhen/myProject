package com.ptpl.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AwardSTimeLinkMapper;
import com.ptpl.model.AwardSTimeLink;
import com.ptpl.service.AwardSTimeLinkServiceI;


public class AwardSTimeLinkServiceImpl implements AwardSTimeLinkServiceI {

	@Autowired
	private AwardSTimeLinkMapper awardSTimeLinkMapper;

	public int insertSelective(AwardSTimeLink asl) {
		return awardSTimeLinkMapper.insertSelective(asl);
	}

	public AwardSTimeLink getAwardSTimeLinkByAwardId(BigDecimal bigDecimal) {
		return awardSTimeLinkMapper.getAwardSTimeLinkByAwardId(bigDecimal);
	}

	
	public int updateAwardSTimeLinkByAwardId(AwardSTimeLink asl) {
		return awardSTimeLinkMapper.updateAwardSTimeLinkByAwardId(asl);
	}
	
}
