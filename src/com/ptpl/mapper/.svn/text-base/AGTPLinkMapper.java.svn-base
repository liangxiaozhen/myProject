package com.ptpl.mapper;

import com.ptpl.model.AGTPLink;
import java.math.BigDecimal;
import java.util.List;

public interface AGTPLinkMapper {
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