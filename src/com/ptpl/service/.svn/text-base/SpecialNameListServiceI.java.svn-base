package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.SpecialNameList;

public interface SpecialNameListServiceI {

	/**
	 * 获取定向名单列表
	 * @param snl
	 * @return
	 */
	public List<SpecialNameList> getSpecialNameLists(SpecialNameList snl);

	/**
	 * 插入定向名单列表
	 * @param snl
	 */
	public int insertSelective(SpecialNameList snl);

	/**
	 * 根据定向编号获取定向名单列表
	 * @param businessNo
	 * @return
	 */
	public SpecialNameList getSnlsByNoOrName(SpecialNameList snl);

	/**
	 * 根据是否为模板来获取定向名单
	 * @param snl
	 * @return
	 */
	public List<SpecialNameList> getTemSpecialNameLists(SpecialNameList snl);

	/**
	 * 根据id删除对应的定向名单
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(BigDecimal id);


	/**
	 * 更新定向名单列表的启用状态 
	 * @param id
	 * @return
	 */
	public int updateSpecialNameList(SpecialNameList snl);

	/**
	 * 根据id获取定向名单列表
	 * @param snl
	 * @return
	 */
	public SpecialNameList getSpecialNameList(SpecialNameList snl);

	/**
	 * 根据启用状态来获取对应的定向名单编号
	 * @param snl
	 * @return
	 */
	public List<SpecialNameList> getSpecialNameListByNo(SpecialNameList snl);

	/**
	 * 编辑定向名单时查询定向标题是否重复    自身除外
	 */
	public SpecialNameList getSNLByName(SpecialNameList snl);
	
	/**
	 * 通过单条条件查询整条信息
	 */
	 SpecialNameList selectSpecialNameListByCondition(SpecialNameList specialNameList);
	 /**
	  * 获取针对体验会员的名单
	  * @author 作者 xiaoy: 
	  * @version 创建时间：2017年4月17日 上午11:06:06 
	  * @return
	  */
	 public List<SpecialNameList> getSpecialNameListForUserGradeExp();
	 /**
	  * 获取针对会员的名单
	  * @author 作者 xiaoy: 
	  * @version 创建时间：2017年4月17日 上午11:06:29 
	  * @return
	  */
	 public List<SpecialNameList> getSpecialNameListForUserGrade();
}
