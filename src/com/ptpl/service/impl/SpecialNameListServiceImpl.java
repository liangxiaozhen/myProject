package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.SpecialNameListMapper;
import com.ptpl.model.SpecialNameList;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.web.util.StringUtil;

public class SpecialNameListServiceImpl implements SpecialNameListServiceI {

	@Autowired
	public SpecialNameListMapper specialNameListMapper;

	/**
	 * 获取定向名单列表
	 * 
	 * @param snl
	 * @return
	 */
	public List<SpecialNameList> getSpecialNameLists(SpecialNameList snl) {
		return specialNameListMapper.getSpecialNameLists(snl);
	}

	/**
	 * 插入定向名单列表
	 * 
	 * @param snl
	 */
	public int insertSelective(SpecialNameList snl) {
		return specialNameListMapper.insertSelective(snl);
	}

	/**
	 * 根据定向编号获取定向名单列表
	 */
	public SpecialNameList getSnlsByNoOrName(SpecialNameList snl) {
		return specialNameListMapper.getSnlsByNoOrName(snl);
	}

	/**
	 * 根据是否为模板来获取定向名单
	 */
	public List<SpecialNameList> getTemSpecialNameLists(SpecialNameList snl) {
		return specialNameListMapper.getTemSpecialNameLists(snl);
	}

	/**
	 * 根据id删除对应的定向名单
	 */
	public int deleteByPrimaryKey(BigDecimal id) {
		return specialNameListMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 更新定向名单列表的启用状态
	 */
	public int updateSpecialNameList(SpecialNameList snl) {
		return specialNameListMapper.updateSpecialNameList(snl);
	}

	/**
	 * 根据id获取定向名单列表
	 */
	public SpecialNameList getSpecialNameList(SpecialNameList snl) {
		return specialNameListMapper.getSpecialNameList(snl);
	}

	/**
	 * 根据启用状态来获取对应的定向名单编号
	 */
	public List<SpecialNameList> getSpecialNameListByNo(SpecialNameList snl) {
		return specialNameListMapper.getSpecialNameListByNo(snl);
	}

	/**
	 * 编辑定向名单时查询定向标题是否重复 自身除外
	 */
	public SpecialNameList getSNLByName(SpecialNameList snl) {
		return specialNameListMapper.getSNLByName(snl);
	}

	@Override
	public SpecialNameList selectSpecialNameListByCondition(SpecialNameList specialNameList) {
		// TODO Auto-generated method stub
		return specialNameListMapper.selectSpecialNameListByCondition(specialNameList);
	}

	@Override
	public List<SpecialNameList> getSpecialNameListForUserGradeExp() {
		SpecialNameList name = new SpecialNameList();
		name.setIsUse((short) 1);
		name.setBusinessType((short) 1);
		List<SpecialNameList> snameList = specialNameListMapper.getSpecialNameListByNo(name);
		List<SpecialNameList> expSNList = new ArrayList<SpecialNameList>();
		for (SpecialNameList nameList : snameList) {
			String systemBusType = nameList.getSystemBusType();
			if (StringUtil.isNotEmpty(systemBusType)) {
				if (systemBusType.substring(9, 10).equals("1")) {
					expSNList.add(nameList);
				}
			}
		}
		return expSNList;
	}

	@Override
	public List<SpecialNameList> getSpecialNameListForUserGrade() {
		SpecialNameList name = new SpecialNameList();
		name.setIsUse((short) 1);
		name.setBusinessType((short) 1);
		List<SpecialNameList> snameList = specialNameListMapper.getSpecialNameListByNo(name);
		List<SpecialNameList> normalList = new ArrayList<SpecialNameList>();
		for (SpecialNameList nameList : snameList) {
			String systemBusType = nameList.getSystemBusType();
			if (StringUtil.isNotEmpty(systemBusType)) {
				if (systemBusType.substring(8, 9).equals("1")) {
					normalList.add(nameList);
				}
			}
		}
		return normalList;
	}

}
