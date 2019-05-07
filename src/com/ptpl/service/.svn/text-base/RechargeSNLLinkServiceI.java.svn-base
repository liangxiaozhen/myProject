package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.mapper.RechargeSNLLinkMapper;
import com.ptpl.model.RechargeSNLLink;

public interface RechargeSNLLinkServiceI {
		int deleteByPrimaryKey(BigDecimal id);

	    int insert(RechargeSNLLink record);

	    int insertSelective(RechargeSNLLink record);

	    RechargeSNLLink selectByPrimaryKey(BigDecimal id);

	    int updateByPrimaryKeySelective(RechargeSNLLink record);

	    int updateByPrimaryKey(RechargeSNLLink record);
	    /**
	     * 根据充值费率表id查询排出人名单
	     * @param @param id
	     * @param @return
	     * @return List<RechargeSNLLinkMapper>
	     * @author jiangxueyou
	     */
	    List<RechargeSNLLink> selectByrrid(BigDecimal id);
}
