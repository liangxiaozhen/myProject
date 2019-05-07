package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ItemDetailDescMapper;
import com.ptpl.model.ItemDetailDesc;
import com.ptpl.service.ItemDetailDescServiceI;

public class ItemDetailDescServiceImpl implements ItemDetailDescServiceI{

	@Autowired
	private ItemDetailDescMapper itemdetaildescmapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return itemdetaildescmapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ItemDetailDesc record) {
		return itemdetaildescmapper.insert(record);
	}

	@Override
	public int insertSelective(ItemDetailDesc record) {
		return itemdetaildescmapper.insertSelective(record);
	}

	@Override
	public ItemDetailDesc selectByPrimaryKey(BigDecimal id) {
		return itemdetaildescmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ItemDetailDesc record) {
		return itemdetaildescmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ItemDetailDesc record) {
		return itemdetaildescmapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ItemDetailDesc> callbackmoduleType() {
		return itemdetaildescmapper.callbackmoduleType();
	}

	@Override
	public List<ItemDetailDesc> callbackitemDesc(String moduletype, BigDecimal tid) {
		return itemdetaildescmapper.callbackitemDesc(moduletype, tid);
	}

}
