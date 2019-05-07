package com.ptpl.mapper;

import com.ptpl.model.UserUpgradeRecord;
import java.math.BigDecimal;
import java.util.List;
/**
 * 用户等级提升记录Mapper
 * @author xiaoy
 *
 * @version 2016年11月28日 下午12:00:46
 */
public interface UserUpgradeRecordMapper {
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