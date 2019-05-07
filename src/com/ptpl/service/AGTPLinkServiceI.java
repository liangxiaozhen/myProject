package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AGTPLink;
/**
 * 代理级别推广公司关联表
 * 
 * @author xiaoy
 *
 * @date 2016年11月5日 上午10:19:53
 */
public interface AGTPLinkServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(AGTPLink record);

	AGTPLink selectByPrimaryKey(BigDecimal id);
	
	AGTPLink selectByProxyGradeAndAGAPID(BigDecimal proxyGrade,BigDecimal AGPAID);

	List<AGTPLink> selectByProxyGrade(BigDecimal proxyGrade);
	
	List<AGTPLink> selectByAGPAID(BigDecimal AGPAID);

	int updateByPrimaryKeySelective(AGTPLink record);

	int deleteByAGPAID(BigDecimal agpaID);

	int deleteByProxyGrade(BigDecimal proxyGrade);
}
