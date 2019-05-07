package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserGradeExpMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.UserGrade;
import com.ptpl.model.UserGradeExp;
import com.ptpl.service.UserGradeServiceI;

public class UserGradeServiceImpl implements UserGradeServiceI {

	@Autowired
	private UserGradeMapper userGradeMapper;
	@Autowired
	private UserGradeExpMapper userGradeExpMapper;

	@Override
	public String getCodeByUserGradeName(BigDecimal code) {
		return userGradeMapper.getCodeByUserGradeName(code);
	}

	@Override
	public void addUserGrade(UserGrade userGrade) {
		/*
		 * //避免添加的时候添加重复的会员等级编号,会员等级名称可以重复,比如说1,2,3级都是青铜会员等 BigDecimal ugrade =
		 * userGrade.getUgrade(); String ugradename = userGrade.getUgradename();
		 * String addman = userGrade.getAddman(); String remark =
		 * userGrade.getRemark(); UserGrade u = new UserGrade(); int row =
		 * userGradeMapper.getByUgrade(userGrade.getUgrade()); if(row>0){
		 * JOptionPane.showMessageDialog(null, "请填入正确的会员等级编号"); return; } int
		 * row2 = userGradeMapper.getByUgradeName(userGrade.getUgradename());
		 * if(row2>0){ JOptionPane.showMessageDialog(null, "请填入正确的会员名称");
		 * return; } u.setUgrade(ugrade); u.setAddman(addman);
		 * u.setRemark(remark); u.setUgradename(ugradename); if
		 * (userGrade.getAddtime() != null) {
		 * u.setAddtimeStr(sf.format(userGrade.getAddtime()));
		 * 
		 * }
		 */
		userGradeMapper.insertUserGrade(userGrade);
	}

	@Override
	public int updateUserGrade(UserGrade userGrade) {
		/*
		 * List<UserGrade> userGrades = userGradeMapper.getUserGradeAll(null);
		 * try { for (UserGrade userGrade2 : userGrades) {
		 * if(userGrade2.getUgrade()==userGrade.getUgrade()){
		 * //加入你修改的会员等级编号和数据库比对,如果有就请重新输入 throw new Exception("请重新输入一个会员等级编号");
		 * } if(userGrade2.getUgradename()==userGrade.getUgradename()){ throw
		 * new Exception("请重新输入一个会员等级名称"); } } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return userGradeMapper.updateUserGrade(userGrade);
	}

	@Override
	public List<UserGrade> getAll(UserGrade userGrade) {
		return userGradeMapper.getUserGradeAll(userGrade);
	}

	@Override
	public UserGrade getByIdToUsergrade(BigDecimal id) {
		return userGradeMapper.getById(id);
	}

	@Override
	public int getByTrem(BigDecimal code) {
		return userGradeMapper.getByUgrade(code);
	}

	@Override
	public int getByTrem2(String code) {
		return userGradeMapper.getByUgradeName(code);
	}

	@Override
	public UserGrade selectByUgrade(BigDecimal id) {
		return userGradeMapper.selectByUgrade(id);
	}

	@Override
	public UserGrade getusergrade(BigDecimal code) {
		// TODO Auto-generated method stub
		return userGradeMapper.getusergrade(code);
	}

	@Override
	public List<UserGrade> getGradeList(List<BigDecimal> item) {
		return userGradeMapper.getGradeList(item);
	}

	/** ------------------------------------------------------------------ */
	/** ------------------------------------------------------------------ */
	/** --------------------会员等级变更需求后-------------------------------------- */
	/** ------------------------------------------------------------------ */
	/** ------------------------------------------------------------------ */
	/** ------------------------------------------------------------------ */
	@Override
	public List<UserGrade> getGroupByUgradeAndUgradeName() {
		return userGradeMapper.getGroupByUgradeAndUgradeName();
	}

	@Override
	public List<UserGrade> selective(UserGrade userGrade) {
		// TODO Auto-generated method stub
		return userGradeMapper.selective(userGrade);
	}

	@Override
	public int insertSelective(UserGrade userGrade) {
		// 新增会员等级-->排列序号
		int intRankNo = userGrade.getRankno().intValue();
		// 新增会员等级-->购买积分
		int intPoints = userGrade.getNeedpoints().intValue();
		// 新增会员地记-->购买金额
		double doubleAmount = userGrade.getNeedamount();
		/*
		 * 判断排列序号，购买积分，购买金额
		 */
		if (intRankNo > 0 && intPoints >= 0 && doubleAmount >= 0.0) {
			userGrade.setIscanuseamount((short) 0);
			userGrade.setIscanusepoints((short) 0);
			/*
			 * 积分>0,可以用积分购买
			 */
			if (userGrade.getNeedpoints().intValue() > 0)
				userGrade.setIscanusepoints((short) 1);
			/*
			 * 金额>0,可以用现金购买
			 */
			if (userGrade.getNeedamount().intValue() > 0)
				userGrade.setIscanuseamount((short) 1);
			/*
			 * 定向升级 -1为不定向升级
			 */
			BigDecimal snlid = new BigDecimal(userGrade.getIsspecify());
			if (snlid.shortValue() == -1) {
				userGrade.setIsspecify((short) 2);
			} else {
				userGrade.setIsspecify((short) 1);
				// 定向名单列表ID
				userGrade.setSnlid(snlid);
			}

			List<UserGrade> userGradeList = userGradeMapper.selectiveForNormal(null);
			/*
			 * 遍历所有的会员等级
			 */
			for (UserGrade uGrade : userGradeList) {
				// 如果设置的排列序号已存在，从这个排列序号开始所有的都往后+1
				if (uGrade.getRankno().intValue() == intRankNo) {
					intRankNo++;
					uGrade.setRankno((short) intRankNo);
					userGradeMapper.updateByPrimaryKeySelective(uGrade);
				}
			}
			return userGradeMapper.insertSelective(userGrade);
		}
		return -1;
	}

	@Override
	public UserGrade selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return userGradeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserGrade record) {
		return userGradeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int verifyName(UserGrade userGrade) {
		return userGradeMapper.verifyName(userGrade);
	}

	@Override
	public List<UserGrade> selectiveForStop(UserGrade userGrade) {
		// TODO Auto-generated method stub
		return userGradeMapper.selectiveForStop(userGrade);
	}

	@Override
	public List<UserGrade> selectiveForNormal(UserGrade userGrade) {
		// TODO Auto-generated method stub
		return userGradeMapper.selectiveForNormal(userGrade);
	}

	@Override
	public int updateToStop(UserGrade userGrade) {
		int rankNo = userGrade.getRankno().intValue();
		List<UserGrade> list = userGradeMapper.selectiveForNormal(null);
		for (UserGrade ug : list) {
			// 循环中的序号，大于当前序号的都-1
			int no = ug.getRankno().intValue();
			if (no > rankNo) {
				no--;
				ug.setRankno((short) no);
				userGradeMapper.updateByPrimaryKeySelective(ug);
			}
		}
		return userGradeMapper.updateToStop(userGrade);
	}

	@Override
	public List<UserGrade> selectForBonusPointsAndCash(Short rankNo) {
		// TODO Auto-generated method stub
		return userGradeMapper.selectForBonusPointsAndCash(rankNo);
	}

	@Override
	public int updateByPrimaryKey(UserGrade userGrade) {
		UserGrade ug = userGradeMapper.selectByPrimaryKey(userGrade.getId());
		// 排列序号
		int intRankNo = userGrade.getRankno().intValue();
		// 购买积分
		int intPoints = userGrade.getNeedpoints().intValue();
		// 购买金额
		double doubleAmount = userGrade.getNeedamount();
		// 当前状态
		int status = userGrade.getStatus().intValue();
		// 旧状态
		int oldStatus = ug.getStatus().intValue();
		/*
		 * 判断等级编号，排列序号，购买积分，购买金额
		 */
		if (intRankNo > 0 && intPoints >= 0 && doubleAmount >= 0.0) {
			userGrade.setIscanuseamount((short) 0);
			userGrade.setIscanusepoints((short) 0);
			/*
			 * 积分>0,可以用积分购买
			 */
			if (userGrade.getNeedpoints().intValue() > 0)
				userGrade.setIscanusepoints((short) 1);
			/*
			 * 金额>0,可以用现金购买
			 */
			if (userGrade.getNeedamount().intValue() > 0)
				userGrade.setIscanuseamount((short) 1);
			/*
			 * 定向升级 -1为不定向升级
			 */
			BigDecimal snlid = new BigDecimal(userGrade.getIsspecify());
			if (snlid.shortValue() == -1) {
				userGrade.setIsspecify((short) 2);
			} else {
				userGrade.setIsspecify((short) 1);
				// 定向名单列表ID
				userGrade.setSnlid(snlid);
			}

			if (status != 2) {
				if (status != oldStatus && status == 0) {
					UserGradeExp userGradeExp = new UserGradeExp();
					userGradeExp.setUgrade(ug.getUgrade().shortValue());
					userGradeExp.setStatus((short) 0);
					userGradeExpMapper.updateByStatus(userGradeExp);
				}
				int oldRankNo = ug.getRankno().intValue();
				List<UserGrade> userGradeList = userGradeMapper.selectiveForNormal(null);
				/*
				 * 排列序号 从小到大 2-->8 intRankNo 编辑后的排列序号 编辑后的排列序号大于原先的排列序号
				 */
				if (intRankNo > oldRankNo) {
					for (UserGrade uGrade : userGradeList) {
						int no = uGrade.getRankno().intValue();
						if (no > oldRankNo && no <= intRankNo) {
							no--;
							uGrade.setRankno((short) no);
							userGradeMapper.updateByPrimaryKeySelective(uGrade);
						}
					}
				}
				/*
				 * 排列序号 从大到小 8-->2 intRankNo 编辑后的排列序号 编辑后的排列序号小于原先的排列序号
				 */
				if (intRankNo < oldRankNo) {
					for (UserGrade uGrade : userGradeList) {
						// 2,3,4,5,6,7。全部加1 变为 3,4,5,6,7,8。
						// userGrade的排列序号变为2
						if (uGrade.getId().intValue() != userGrade.getId().intValue()) {
							// 排列序号 +1
							int no = uGrade.getRankno().intValue();
							// 循环的当前的排列序号必须小于编辑的会员等级的原先的排列
							if (intRankNo == no) {
								intRankNo++;
								uGrade.setRankno((short) intRankNo);
								userGradeMapper.updateByPrimaryKeySelective(uGrade);
							}
						}
					}
				}
				return userGradeMapper.updateByPrimaryKey(userGrade);
			} else if (oldStatus == 0) {
				return updateToStop(userGrade);
			} else {
				return -2;
			}
		}
		return -1;
	}

	@Override
	public int updateForStop(UserGrade userGrade) {
		// 购买积分
		int intPoints = userGrade.getNeedpoints().intValue();
		// 购买金额
		double doubleAmount = userGrade.getNeedamount();
		/*
		 * 验证等级名称
		 */
		int iden = userGradeMapper.verifyName(userGrade);
		/*
		 * 判断等级名称是否存在
		 */
		if (iden == 0 && userGrade.getUgrade() == null) {
			/*
			 * 判断等级编号，排列序号，购买积分，购买金额
			 */
			if (intPoints >= 0 && doubleAmount >= 0.0) {
				userGrade.setIscanuseamount((short) 0);
				userGrade.setIscanusepoints((short) 0);
				/*
				 * 积分>0,可以用积分购买
				 */
				if (userGrade.getNeedpoints().intValue() > 0)
					userGrade.setIscanusepoints((short) 1);
				/*
				 * 金额>0,可以用现金购买
				 */
				if (userGrade.getNeedamount().intValue() > 0)
					userGrade.setIscanuseamount((short) 1);
				/*
				 * 定向
				 */
				BigDecimal snlid = new BigDecimal(userGrade.getIsspecify());
				if (snlid.shortValue() == -1) {
					userGrade.setIsspecify((short) 2);
				} else {
					userGrade.setIsspecify((short) 1);
					// 定向名单列表ID
					userGrade.setSnlid(snlid);
				}

			}
			return userGradeMapper.updateByPrimaryKeySelective(userGrade);
		}
		return -1;
	}

	@Override
	public int updateToNormal(UserGrade userGrade) {
		int rankNo = userGrade.getRankno();
		List<UserGrade> list = userGradeMapper.selectiveForNormal(null);
		for (UserGrade ug : list) {
			// 循环中的序号，大于当前序号的都-1
			int no = ug.getRankno().intValue();
			if (no >= rankNo) {
				no++;
				ug.setRankno((short) no);
				userGradeMapper.updateByPrimaryKeySelective(ug);
			}
		}
		return userGradeMapper.updateByPrimaryKeySelective(userGrade);
	}
}
