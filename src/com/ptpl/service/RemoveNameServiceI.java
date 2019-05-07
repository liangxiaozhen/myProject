package com.ptpl.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialTime;

public interface RemoveNameServiceI {
	/**
	 * 获取 启用状态 小名单目录
	 * @return
	 */
	List<RemoveName> getRemoveName ();
	/**
	 * 获取 标的设置 排除名单编号列表 
	 * @return
	 */
	List<String> getTenderitemRemoveNameNo();
	/**
	 * 获取 充值设置 排除名单编号列表
	 * @return
	 */
	List<String> getRechargeRstrRemoveNameNo();
	/**
	 * 获取 提现设置 排除名单编号列表
	 * @return
	 */
	List<String> getWithdrawsCashRstrRemoveNameNo();
	/**
	 * 获取 标的债权转让 排除名单编号列表
	 * @return
	 */
	List<String> getDebtAttornRemoveNameNo();
	/**
	 * 获取 手动颁奖设置 获奖用户名列表编号
	 * @return
	 */
	List<String> getManualAwardRewardLNNo();
	/**
	 * 查询存在并启用的小名单 
	 * @param nameno
	 * @return
	 */
	RemoveName selectRemoveName(String nameno);
	/**
	 * 查看目录下所有名单 
	 * @param name   子目录
	 * @param nameType 主目录
	 * @return
	 */
	List<RemoveName> selectByNameTypeName(String name,String nameType);
	/**
	 * 动态查询 排除名单
	 * 
	 * @param record
	 * @return
	 */
	List<RemoveName> selective(RemoveName record);
	/**
	 * 查询小名单目录
	 * 
	 * @param record
	 * @return
	 */
	List<RemoveName> selectiveNameType(RemoveName record);
	/**
	 * 查询 排除名单名称 和排除名单编号
	 * 
	 * @return
	 */
	List<RemoveName> selectNameAndNameNo();
	/**
	 * 查询 名单编号
	 * 
	 * @param name
	 *            名单名称
	 * @return
	 */
	String selectNameNoForName(String name);
	/**
	 * 查询 名单名称
	 * 
	 * @param nameno
	 *            名单编号
	 * @return
	 */
	String selectNameForNameNo(String nameno);
	/**
	 * 查询 奖品 提现 时间报
	 * 
	 * @param timeType
	 *            时间包类型 如：奖品
	 * @return
	 */
	List<RemoveName> getNameNoAndName(String nameType);
	/**
	 * 查询 名单编号
	 * 
	 * @param name
	 *            名单名称
	 * @param nameType
	 *            名单类型
	 * @return
	 */
	String selectNameNoForNT(String name, String nameType);
	/**
	 * 查询 排除名单名称和类型
	 * 
	 * @param timeno
	 *            时间包编号 
	 * @return
	 */
	RemoveName selectNameAndNameType(String nameno);
	/**
	 * 获取 名单名称，名单编号，名单类型
	 * @return
	 */
	List<RemoveName> selectNameNamNoNameType();
	/**
	 * 分组   名单名称，名单编号，名单类型
	 * @return
	 */
	List<RemoveName> getGroupNameno();
	/**
	 * 删除目录
	 * @param nameno  名单编号
	 * @return
	 */
	int deleteName(String nameno);
	/**
	 * 分组查询小名单主目录
	 * @param nameType
	 * @return
	 */
	List<RemoveName> getGroupNameType ();
	/**
	 * 查询小名单子目录
	 * @param nameType 主目录
	 * @return
	 */
	List<RemoveName> getGroupName(String nameType);
	/**
	 * 启用
	 * @param name
	 * @param nameType
	 * @return
	 */
	int updateIsUse (String name,String nameType);
	/**
	 * 停用
	 * @param name
	 * @param nameType
	 * @return
	 */
	int updateCancelUse (String name,String nameType);
	/**
	 * 查询小名单目录人数
	 * @param rmoveName
	 * @return
	 */
	int selectCount(RemoveName rmoveName);
	/**
	 * 编辑小名单目录
	 * @param rmoveName
	 * @return
	 */
	int updateNameType(RemoveName rmoveName);
	/**
	 *	查询小名单中的用户
	 * @return
	 */
	List<RemoveName> selectListForInsert(RemoveName rName);
	/**
	 * -- 查询所有用户 --
	 * @param userName
	 * @return
	 */
	List<RemoveName> selectUserForInsert(String userName);
	/**
	 * 根据会员等级查询用户
	 * 
	 */
	List<RemoveName> selectUserUgradeForInsert(String ugrade);
	/**
	 * 查询baseID在指定的小名单中是否存在
	 */
	RemoveName selectByBaseID(String baseID,String name,String nameType);
	/**
	 * 查询是否有当前名单
	 * @author lihs
	 */
	List<RemoveName> selectbyRewardlnno(String rewardlnno);
	
	/**
	 * 查询是否有当前名单
	 * @author lihs
	 */
	RemoveName selectloginname(String loginname);
	
	
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(RemoveName record);

	RemoveName selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(RemoveName record);
	/**
	 * 根据排除人名单编号查询名单
	 * @param nameno
	 * @return
	 */
	List<RemoveName>  getRemove(String nameno);
	
	/**
	 * 查询用户是否存在小名单中
	 * @param removeName
	 * @return
	 */
	RemoveName selectByNameNoAndBaseId(RemoveName removeName);
	/**
	 * 查询名单下的用户（非模糊查询）
	 * @param name
	 * @return
	 */
	List<RemoveName> getUser(String name);
	/**
	 * 根据大名单，查出其下所有用户
	 */
	List<RemoveName> getUserNameMax(String rewardname_nameType1);
	/**
	 * 查询小名单下的所有用户
	 */
	List<RemoveName> getUserNameMin(String rewardname_name1);
	/**
	 * 根据用户名查出其baseID
	 */
	RemoveName getBaseId(String name);
	
	/**
	 * @author pxl
	 * @date   2016-11-15
	 * 根据名单编号  找出RemoveName
	 */
	List<RemoveName> getRemoveNameByNameNo(String namecontent);
	
	//根据大名单查询其下的所有小名单
	List<RemoveName> getGroupNameByNameType(String addOrDebarText);
}

