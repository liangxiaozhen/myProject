package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ActiveObjectListMapper;
import com.ptpl.model.ActiveObjectList;
import com.ptpl.service.ActiveObjectListServiceI;

public class ActiveobjectListServiceImpl implements ActiveObjectListServiceI{

	@Autowired
	ActiveObjectListMapper activeObjectListmapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActiveObjectList record) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.insert(record);
	}

	@Override
	public int insertSelective(ActiveObjectList record) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.insertSelective(record);
	}

	@Override
	public ActiveObjectList selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActiveObjectList record) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActiveObjectList record) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ActiveObjectList> getlist(String actno) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.getlist(actno);
	}

	@Override
	public ActiveObjectList getuseradmin(ActiveObjectList record) {
		// TODO Auto-generated method stub
		return activeObjectListmapper.getuseradmin(record);
	}

	/**
     * @author pxl
     * @date 2016-11-28
     * 根据子活动编号删除相对应的活动对象
     */
	public int deleteActiveObject(String actno) {
		return activeObjectListmapper.deleteActiveObject(actno);
	}

	/**
	 * 根据定向名单列表的id获得对应的业务对象名单
	 */
	public List<ActiveObjectList> selectBySNLId(BigDecimal id) {
		return activeObjectListmapper.selectBySNLId(id);
	}

	/**
	 * 根据定向名单的id删除对应的业务对象名单
	 */
	public int deleteBySNLId(BigDecimal id) {
		return activeObjectListmapper.deleteBySNLId(id);
	}

	
	public List<ActiveObjectList> selectActiveObjectLists(ActiveObjectList aos) {
		return activeObjectListmapper.selectActiveObjectLists(aos);
	}


}
