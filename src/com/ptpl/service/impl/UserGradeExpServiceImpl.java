package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.mapper.UserAccountSafeInfoMapper;
import com.ptpl.mapper.UserGradeExpMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.mapper.UserUpgradeRecordMapper;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.model.UserGradeExp;
import com.ptpl.model.UserUpgradeRecord;
import com.ptpl.service.UserGradeExpServiceI;

/**
 * 体验会员等级设置ServiceImpl
 * 
 * @author xiaoy
 *
 * @version 2016年11月26日 上午11:00:10
 */
public class UserGradeExpServiceImpl implements UserGradeExpServiceI {
	@Autowired
	private UserGradeExpMapper userGradeExpMapper;
	@Autowired
	private UserGradeMapper userGradeMapper;
	@Autowired
	private UserAccountSafeInfoMapper userAccountSafeInfoMapper;
	@Autowired
	private UserAccountMapper userAccountMapper;
	@Autowired
	private UserUpgradeRecordMapper userUpgradeRecordMapper;

	@Override
	public int insert(UserGradeExp record) {
		return userGradeExpMapper.insert(record);
	}

	@Override
	public int insertSelective(UserGradeExp record) {
		return userGradeExpMapper.insertSelective(record);
	}

	@Override
	public UserGradeExp selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		UserGradeExp userGradeExp = userGradeExpMapper.selectByPrimaryKey(id);
		UserGrade userGrade = userGradeMapper.selectByUgrade(new BigDecimal(userGradeExp.getUgrade()));
		userGradeExp.getUserGrade().setUgradename(userGrade.getUgradename());
		return userGradeExp;
	}

	@Override
	public int updateByPrimaryKeySelective(UserGradeExp record) {
		Short expStatus = record.getStatus();
		if (expStatus.equals((short) 1)) {
			UserGradeExp userGradeExp = userGradeExpMapper.selectByPrimaryKey(record.getId());
			UserGrade userGrade = new UserGrade();
			userGrade.setUgrade(new BigDecimal(userGradeExp.getUgrade()));
			userGrade = userGradeMapper.selectiveForNormal(userGrade).get(0);
			Short status = userGrade.getStatus();
			if (status.equals((short) 1)) {
				return userGradeExpMapper.updateByPrimaryKeySelective(record);
			}
			return -1;
		}
		return userGradeExpMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserGradeExp record) {
		// TODO Auto-generated method stub
		return userGradeExpMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserGradeExp> selective(UserGradeExp record) {
		// TODO Auto-generated method stub
		return userGradeExpMapper.selective(record);
	}

	@Override
	public int updateByStatus(UserGradeExp record) {
		// TODO Auto-generated method stub
		return userGradeExpMapper.updateByStatus(record);
	}

	@Override
	public List<UserGradeExp> selectForUgrade(BigDecimal rankno) {
		// TODO Auto-generated method stub
		return userGradeExpMapper.selectForUgrade(rankno);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		UserGradeExp userGradeExp = userGradeExpMapper.selectByPrimaryKey(id);
		List<UserAccountSafeInfo> uasiList = userAccountSafeInfoMapper.getuseradmin(userGradeExp.getUgrade());
		for (UserAccountSafeInfo uasi : uasiList) {
			if (uasi.getUgradetype().intValue() == 2) {
				UserAccount userAccount = userAccountMapper.getUserAccountByBaseId(uasi.getBaseid());
				UserUpgradeRecord userUp = userUpgradeRecordMapper.selectOlduGradeByBaseID(uasi.getBaseid()).get(0);
				UserUpgradeRecord upRecord = new UserUpgradeRecord();
				upRecord.setBaseid(uasi.getBaseid());
				upRecord.setBonuspoints(userAccount.getBonuspoints().longValue());
				upRecord.setDeductbonuspoints(0L);
				upRecord.setDealmode((short) 3);
				upRecord.setDealtime(new Date());
				upRecord.setGrade(userUp.getGrade());
				upRecord.setPayamount(0d);
				upRecord.setPaytype((short) 3);
				upRecord.setOldgrade(uasi.getUgrade().intValue());
				upRecord.setRemark("体验会员等级被删除");
				userUpgradeRecordMapper.insertSelective(upRecord);
				/*
				 * 用户账户安全信息
				 */
				uasi.setUgradetype((short) 1);
				uasi.setUgrade(userUp.getGrade().shortValue());
				userAccountSafeInfoMapper.update(uasi);
			}
		}
		return userGradeExpMapper.deleteByPrimaryKey(id);
	}

}
