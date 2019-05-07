package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AwardMapper;
import com.ptpl.model.Award;
import com.ptpl.service.AwardServiceI;

public class AwardServiceImpl extends BaseServiceImpl<Award> implements AwardServiceI {
	@Autowired
	private AwardMapper awardMapper;
	
	@Override
	public List<Award> getawearType() {
		// TODO Auto-generated method stub
		return awardMapper.getawearType();
	}

	@Override
	public List<Award> getawearName(String aType) {
		// TODO Auto-generated method stub
		return awardMapper.getawearName(aType);
	}

	@Override
	public List<Award> getawearGoods(String attribute) {
		// TODO Auto-generated method stub
		return awardMapper.getawearGoods(attribute);
	}

	@Override
	public Award getaNo(String aname) {
		// TODO Auto-generated method stub
		return awardMapper.getaNo(aname);
	}	
	@Override
	public int update(Award award) {
		// TODO Auto-generated method stub
		return awardMapper.update(award);
	}

	/**
	 * @author pxl
	 * 在奖品列表中删除某一个奖品
	 */
	public int deleteSomeAward(Award award) {
		return awardMapper.deleteSomeAward(award);
	}

	/**
	 * @author pxl
	 * @date 2016-11-12
	 * 根据奖品的名称获取奖品的属性或其它
	 */
	public Award getAwardByName(String awardname) {
		return awardMapper.getAwardByName(awardname);
	}

	/**
	 * @author pxl
	 * @date 2016-11-25
	 * 根据奖品的属性获得某个奖品
	 */
	public Award getAward(Award award) {
		return awardMapper.getAward(award);
	}

	@Override
	public Award selectByAwardNo(String ano) {
		return awardMapper.selectByAwardNo(ano);
	}

	@Override
	public Award selectByPrimaryKey(BigDecimal id) {
		return awardMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据奖品类型获得其所有的奖品
	 * @param apAwardType
	 * @return
	 */
	public List<Award> getAwardNamesByAtype(Short apAwardType) {
		return awardMapper.getAwardNamesByAtype(apAwardType);
	}

	/**
	 * 更新奖品时，防止重名
	 * @param a
	 * @return
	 */
	public Award gainAward(Award a) {
		return awardMapper.gainAward(a);
	}

	/*更新奖品*/
	public int updateAward(Award award) {
		
		return awardMapper.updateAward(award);
	}	
}

