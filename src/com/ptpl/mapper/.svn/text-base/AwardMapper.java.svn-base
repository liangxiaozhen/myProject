package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.Award;

public interface AwardMapper extends BaseMapper<Award>{
	
	//查询类型（大名单）
	List<Award> getawearType();
	
	//查询奖品属性（小名单）
	List<Award> getawearName(String aType);
	
	//查出奖品名（根据小名单）
	List<Award> getawearGoods(String attribute);
	
	//拿到单个奖品编号(根据奖品名)
	Award getaNo(String aname);
	
	//修改奖品数量
	int update(Award award);
	
	/**
	 * @author pxl
	 * 删除奖品列表中的某个奖品
	 */
	int deleteSomeAward(Award award);
	
	/**
	 * @author pxl
	 * @date 2016-11-12
	 * 根据奖品的名称获取奖品的属性
	 */
	Award getAwardByName(String awardname);
	
	/**
	 * @author pxl
	 * @date 2016-11-25
	 * 根据奖品的属性获得某个奖品
	 */
	Award getAward(Award award);
	
	/**
	 * 根据奖品编号获取奖品的属性
	 * @param ano
	 * @return
	 */
	Award selectByAwardNo(String ano);
	
	/**
	 * 根据奖品ID获取奖品的属性
	 * @param id
	 * @return
	 */
	Award selectByPrimaryKey(BigDecimal id);
	
	/**
	 * 根据奖品类型获得其所有的奖品
	 * @param apAwardType
	 * @return
	 */
	List<Award> getAwardNamesByAtype(Short apAwardType);
	
	/**
	 * 更新奖品时，防止重名
	 * @param a
	 * @return
	 */
	Award gainAward(Award a);
	
	/*更新奖品*/
	int updateAward(Award award);
}