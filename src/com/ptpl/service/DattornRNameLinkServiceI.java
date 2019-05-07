package com.ptpl.service;

import java.math.BigDecimal;

import com.ptpl.model.DattornRNameLink;
/**
 * @author liuj
 * 债转排除名单service层
 */
public interface DattornRNameLinkServiceI {
	    //删除
	    int deleteByPrimaryKey(BigDecimal id);
        //添加
	    int insert(DattornRNameLink record);
	    //添加
	    int insertSelective(DattornRNameLink record);
	    //通过id查信息
	    DattornRNameLink selectByPrimaryKey(BigDecimal id);
	    //更新
	    int updateByPrimaryKeySelective(DattornRNameLink record);
	    //更新
	    int updateByPrimaryKey(DattornRNameLink record);
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
}
