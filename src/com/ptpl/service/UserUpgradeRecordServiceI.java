package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserUpgradeRecord;
/**
 * 用户等级升级记录ServiceI
 * @author xiaoy
 *
 * @date 2016年11月28日 上午11:06:59
 */
public interface UserUpgradeRecordServiceI {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserUpgradeRecord record);

    int insertSelective(UserUpgradeRecord record);

    UserUpgradeRecord selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserUpgradeRecord record);

    int updateByPrimaryKey(UserUpgradeRecord record);
    
    List<UserUpgradeRecord> selective(UserUpgradeRecord record);
    
	/**
	 * 查询会员升级记录
	 * @param baseid 用户ID
	 * @return
	 */
	List<UserUpgradeRecord> selectByBaseID (BigDecimal baseid);
	/**
	 * 查询上一次非体验会员升级记录
	 * @param baseID
	 * @return
	 */
	List<UserUpgradeRecord> selectOlduGradeByBaseID(BigDecimal baseID);
}