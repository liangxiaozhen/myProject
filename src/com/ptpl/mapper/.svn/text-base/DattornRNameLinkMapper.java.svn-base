package com.ptpl.mapper;

import com.ptpl.model.DattornRNameLink;
import java.math.BigDecimal;
/**
 * @author liuj
 * 债转排除名单mapper层
 */
public interface DattornRNameLinkMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(DattornRNameLink record);

    int insertSelective(DattornRNameLink record);

    DattornRNameLink selectByPrimaryKey(BigDecimal id);
    /**
     * 根据标的债转设置表id查询排除人名单
    * @Title: selectByDAttornId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param dattornId
    * @param @return  参数说明 
    * @return DattornRNameLink    返回类型 
    * @author jiangxueyou
    * @throws
     */
    DattornRNameLink selectByTid(BigDecimal tid);

    int updateByPrimaryKeySelective(DattornRNameLink record);

    int updateByPrimaryKey(DattornRNameLink record);
}