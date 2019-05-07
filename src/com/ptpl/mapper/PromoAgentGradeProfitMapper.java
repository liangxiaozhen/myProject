package com.ptpl.mapper;

import com.ptpl.model.PromoAgentGradeProfit;
import java.math.BigDecimal;
import java.util.List;

/**
 * 推广代理资质等级分润 设置 Mapper
 * @author xiaoy
 *
 * @version 2016年10月1日 上午10:40:46
 */
public interface PromoAgentGradeProfitMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insertSelective(PromoAgentGradeProfit record);

    PromoAgentGradeProfit selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(PromoAgentGradeProfit record);
    
    List<PromoAgentGradeProfit> selective(PromoAgentGradeProfit record);
   
    List<PromoAgentGradeProfit> getGradeName();
    
    
}