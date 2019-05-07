package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.ui.AppSession_Constant;
import com.ptpl.mapper.UserBaseAccountInfoMapper;
import com.ptpl.model.ActiveObjectList;
import com.ptpl.model.AheadRepayAwardRecord;
import com.ptpl.model.RemoveName;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.ActiveObjectListServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.MyMapSessionId;
import com.ptpl.web.util.StringUtil;

/**
 * 
 * @ClassName: UserBaseAccountInfoServiceImpl
 * @Package com.ptpl.service.impl
 * @Description: TODO( 用户基本信息 Service层实现类)
 * @author chenjiaming
 * @date 2016年7月14日 下午3:29:18
 * @version V1.0
 */
public class UserBaseAccountInfoServiceImpl implements UserBaseAccountInfoServiceI {

	@Autowired
	private UserBaseAccountInfoMapper userBaseAccountInfoMapper;
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;
	@Autowired
	private UserPromoServiceI userPromoService;
	@Autowired
	private RemoveNameServiceI removeNameService;;
	@Autowired
	private ActiveObjectListServiceI activeObjectListService;

	private UserAccountSafeInfo userAccountSafeInfo;

	private UserBaseAccountInfo userBaseAccountInfo;

	/**
	 * 
	 * @Title: insert @Description: TODO(用户基本信息数据增加方法) @param @param
	 *         userBaseAccountInfo @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	@Override
	public int insert(UserBaseAccountInfo ubai) {
		return userBaseAccountInfoMapper.insert(ubai);
	}

	/**
	 * 
	 * @Title: insertSelective @Description: TODO(用户基本信息数据增加方法) @param @param
	 *         userBaseAccountInfo @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	@Override
	public int insertSelective(UserBaseAccountInfo ubai) {
		return userBaseAccountInfoMapper.insertSelective(ubai);
	}

	/**
	 * 
	 * @Title: findUserBaseAccountInfoById @Description: TODO(根据ID查询对应的用户基本信息
	 *         信息) @param @param id @param @return 参数说明 @return WCCAwardRule
	 *         返回类型 @author chenjiaming @throws
	 */
	@Override
	public UserBaseAccountInfo selectByPrimaryKey(BigDecimal id) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfoById(id);
	}

	/**
	 * 
	 * @Title: update @Description: TODO(用户基本信息 根据条件更新方法) @param @param
	 *         userBaseAccountInfo @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(UserBaseAccountInfo record) {
		return userBaseAccountInfoMapper.update(record);
	}

	/**
	 * 
	 * @Title: findUserBaseAccountInfoById @Description: TODO(根据条件查询对应的用户基本信息
	 *         信息) @param @param id @param @return 参数说明 @return WCCAwardRule
	 *         返回类型 @author chenjiaming @throws
	 */
	@Override
	public UserBaseAccountInfo getUserBaseAccountInfoByOneCondition(UserBaseAccountInfo ubai) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfoByOneCondition(ubai);
	}

	@Override
	public int deleteById(BigDecimal id) {
		return userBaseAccountInfoMapper.deleteById(id);
	}

	/**
	 * 
	 * @Title: findUserBaseAccountInfoById @Description: TODO(根据ID查询对应的用户基本信息
	 *         信息) @param @param id @param @return 参数说明 @return WCCAwardRule
	 *         返回类型 @author chenjiaming @throws
	 */
	@Override
	public UserBaseAccountInfo getUserBaseAccountInfoById(BigDecimal id) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfoById(id);
	}

	/**
	 * 
	 * @Title: findUserBaseAccountInfos @Description: TODO(用户基本信息
	 *         查询全部) @param @param userBaseAccountInfo @param @return
	 *         参数说明 @return int 返回类型 @author chenjiaming @throws
	 */
	@Override
	public List<UserBaseAccountInfo> getUserBaseAccountInfos(UserBaseAccountInfo userBaseAccountInfo) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfos(userBaseAccountInfo);
	}

	@Override
	public List<UserBaseAccountInfo> getAllUserBaseAccountInfo(Map map) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.getAllUserBaseAccountInfo(map);
	}

	@Override
	public List<UserBaseAccountInfo> getUserBaseAccountInfosBlurred(UserBaseAccountInfo userBaseAccountInfo) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfosBlurred(userBaseAccountInfo);
	}

	@Override
	public List<UserBaseAccountInfo> userbase(String loginname) {
		return userBaseAccountInfoMapper.userbase(loginname);
	}

	/**
	 * 
	 * @Title: getUserBaseAccountInfoAndUserFSAccountInfo @Description:
	 *         TODO(关联用户托管账号查询用户基本信息) @param @param accountInfo @param @return
	 *         参数说明 @return List<UserBaseAccountInfo> 返回类型 @author cjm @throws
	 */
	@Override
	public List<UserBaseAccountInfo> getUserBaseAccountInfoAndUserFSAccountInfo(UserBaseAccountInfo accountInfo) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfoAndUserFSAccountInfo(accountInfo);
	}

	/**
	 * 
	 * @Title: getUserBaseAccountInfoAndUserFSAccountInfoById @Description:
	 *         TODO(根据用户主键ID关联用户托管账号查询用户基本信息) @param @param id @param @return
	 *         参数说明 @return UserBaseAccountInfo 返回类型 @author cjm @throws
	 */
	@Override
	public UserBaseAccountInfo getUserBaseAccountInfoAndUserFSAccountInfoById(BigDecimal id) {
		return userBaseAccountInfoMapper.getUserBaseAccountInfoAndUserFSAccountInfoById(id);
	}

	@Override
	public UserBaseAccountInfo getuserloginname(String loginname) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.getuserloginname(loginname);
	}

	@Override
	public UserBaseAccountInfo selectByBaseId(BigDecimal id) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.selectByBaseId(id);
	}

	@Override
	public int update(UserBaseAccountInfo userBaseAccountInfo) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.update(userBaseAccountInfo);
	}

	@Override
	public List<UserBaseAccountInfo> selectUsersByNormaluGrade(Short ugrade) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.selectUsersByNormaluGrade(ugrade);
	}

	@Override
	public List<UserBaseAccountInfo> selectUsersBysTasteuGrade(Short ugrade) {
		return userBaseAccountInfoMapper.selectUsersBysTasteuGrade(ugrade);
	}

	@Override
	public List<UserBaseAccountInfo> getByUserBaseAccountInfo(UserBaseAccountInfo userBaseAccountInfo) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.getByUserBaseAccountInfo(userBaseAccountInfo);
	}

	@Override
	public int insertForRegister(UserBaseAccountInfo ubai, String pwd, Short regType, Short originclient,
			String promoCode, HttpServletRequest request, HttpServletResponse response) {
		/* 用户基本信息 */
		int iden1 = setUserBaseAccountInfo(ubai, regType, originclient);
		if (iden1 > 0) {
			if (originclient.intValue() == 1) {
				request.getSession().setAttribute(Session_Constant.USER,
						BaseController.getDecryptionUserBaseAccountInfoDetail(ubai));
			} else if (originclient.intValue() == 2) {
				request.getSession().setAttribute(AppSession_Constant.APPUSER,
						BaseController.getDecryptionUserBaseAccountInfoDetail(ubai));
			}
			String sessionId = request.getSession().getId();
			MyMapSessionId m = MyMapSessionId.getInstance();
			m.AddSession(ubai.getLoginname(), sessionId);// 将sessionId以登入名为key，sessionId为value保存
		}
		// ID
		BigDecimal id = ubai.getId();
		/* 用户账户信息 */
		int iden2 = setUserAccount(id);
		/* 用户账户安全信息 */
		int iden3 = setUserAccountSafeInfo(id, pwd);
		if (iden3 > 0) {
			request.getSession().setAttribute(Session_Constant.USERACCOUNTSAFEINFO, userAccountSafeInfo);
		}
		// 推广信息
		int iden4 = userPromoService.insertForRegister(ubai, promoCode, request, response);
		/* 用户托管信息 */
		int iden5 = setUserFsAccountInfo(id);
		return iden1 + iden2 + iden3 + iden4 + iden5 == 5 ? 1 : 0;
	}

	/**
	 * 用户托管信息
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月9日 下午6:20:06
	 * @param id
	 * @return
	 */
	private int setUserFsAccountInfo(BigDecimal id) {
		UserFsAccountInfo ufai = new UserFsAccountInfo();
		/* baseid */
		ufai.setBaseid(id);
		/* 默认未开通 */
		ufai.setIsopenfsinfo((short) 0);
		/* 交易密码未设置 */
		ufai.setTradepass((short) 0);
		return userFsAccountInfoService.insertSelective(ufai);
	}

	/**
	 * 用户基本账户信息
	 * 
	 * @param ubai
	 * @param regcookie
	 */
	private int setUserBaseAccountInfo(UserBaseAccountInfo ubai, Short regType, Short originclient) {
		// 账户类型 个人
		ubai.setAccounttype((short) 1);
		// 注册类型
		ubai.setRegtype(regType);
		// 注册来源
		ubai.setOriginclient(originclient);
		// 注册时间
		ubai.setRegdate(new Date());
		// 是否激活
		ubai.setIsactive((short) 1);
		// 是否实名认证
		ubai.setIsreally((short) 0);
		/* 手机是否验证 */
		ubai.setIsmobileverify((short) 1);
		/* 邮箱是否验证 */
		ubai.setIsemailverify((short) 0);
		/* 用户状态 */
		ubai.setStatus((short) 1);
		/* 是否激活 */
		ubai.setIsactive((short) 1);
		/* 是否实名认证 */
		ubai.setIsreally((short) 0);
		return userBaseAccountInfoMapper.insertSelective(ubai);
	}

	/**
	 * 用户账户 用于注册
	 * 
	 * @param ubai
	 */
	private int setUserAccount(BigDecimal id) {
		UserAccount userAccount = new UserAccount();
		userAccount.setBaseid(id);
		return userAccountService.insert(userAccount);
	}

	/**
	 * 用户账户安全信息 用于注册
	 * 
	 * @param ubai
	 * @param hashed
	 */
	private int setUserAccountSafeInfo(BigDecimal id, String hashed) {
		userAccountSafeInfo = new UserAccountSafeInfo();
		// 登录密码(加密后)
		userAccountSafeInfo.setLoginpassword(hashed);
		// baseID
		userAccountSafeInfo.setBaseid(id);
		// 风险等级
		userAccountSafeInfo.setRisklevel((short) 1);
		// 会员等级
		userAccountSafeInfo.setUgrade((short) 0);
		// 会员类型
		userAccountSafeInfo.setUgradetype((short) 1);
		// 账户状态 1正常
		userAccountSafeInfo.setStatus((short) 1);
		return userAccountSafeInfoService.insert(userAccountSafeInfo);
	}

	@Override
	public int updateHeaderImg(UserBaseAccountInfo accountInfo) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.updateHeaderImg(accountInfo);
	}

	@Override
	public UserBaseAccountInfo getHeadImg(String loginname) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.getHeadImg(loginname);
	}

	@Override
	public List<UserBaseAccountInfo> getBaseInfoAll() {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.getBaseInfoAll();
	}

	@Override
	public Set<UserBaseAccountInfo> getUserForSNLID(BigDecimal expSNLID) {
		Set<UserBaseAccountInfo> ubaiList = new HashSet<UserBaseAccountInfo>();
		List<ActiveObjectList> aoList = activeObjectListService.selectBySNLId(expSNLID);
		for (ActiveObjectList aol : aoList) {
			short or = aol.getIsRightOrExcept();
			short type = aol.getNameType();
			String nameContent = aol.getNameContent();
			// 1 包含
			if (or == 1) {
				// 大名单
				if (type == 1) {
					List<RemoveName> list = removeNameService.getUserNameMax(nameContent);
					for (RemoveName rname : list) {
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper
								.getUserBaseAccountInfoById(rname.getBaseid());
						ubaiList.add(ubai);
					}
				}
				// 小名单
				if (type == 2) {
					List<RemoveName> list = removeNameService.getRemoveNameByNameNo(nameContent);
					for (RemoveName rname : list) {
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper
								.getUserBaseAccountInfoById(rname.getBaseid());
						ubaiList.add(ubai);
					}
				}
				// 用户名
				if (type == 3) {
					UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getuserloginname(nameContent);
					ubaiList.add(ubai);
				}
				// 会员等级
				if (type == 4) {
					String[] userGradeStr = StringUtil.getPlaceholderArr(nameContent);
					if (userGradeStr.length > 0) {
						List<UserAccountSafeInfo> uasiList = new ArrayList<UserAccountSafeInfo>();
						for (String userGrade : userGradeStr) {
							List<UserAccountSafeInfo> list = userAccountSafeInfoService
									.getuseradmin(Short.valueOf(userGrade));
							uasiList.addAll(list);
						}
						for (UserAccountSafeInfo uasi : uasiList) {
							UserBaseAccountInfo ubai = userBaseAccountInfoMapper
									.getUserBaseAccountInfoById(uasi.getBaseid());
							ubaiList.add(ubai);
						}
					}
				}
			}
			// 排除
			if (or == 2) {
				// 大名单
				if (type == 1) {
					List<RemoveName> list = removeNameService.getUserNameMax(nameContent);
					for (RemoveName rname : list) {
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper
								.getUserBaseAccountInfoById(rname.getBaseid());
						ubaiList.remove(ubai);
					}
				}
				// 小名单
				if (type == 2) {
					List<RemoveName> list = removeNameService.getRemoveNameByNameNo(nameContent);
					for (RemoveName rname : list) {
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper
								.getUserBaseAccountInfoById(rname.getBaseid());
						ubaiList.remove(ubai);
					}
				}
				// 用户名
				if (type == 3) {
					UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getuserloginname(nameContent);
					ubaiList.remove(ubai);
				}
				// 会员等级
				if (type == 4) {
					String[] userGradeStr = StringUtil.getPlaceholderArr(nameContent);
					if (userGradeStr.length > 0) {
						List<UserAccountSafeInfo> uasiList = new ArrayList<UserAccountSafeInfo>();
						for (String userGrade : userGradeStr) {
							List<UserAccountSafeInfo> list = userAccountSafeInfoService
									.getuseradmin(Short.valueOf(userGrade));
							uasiList.addAll(list);
						}
						for (UserAccountSafeInfo uasi : uasiList) {
							UserBaseAccountInfo ubai = userBaseAccountInfoMapper
									.getUserBaseAccountInfoById(uasi.getBaseid());
							ubaiList.remove(ubai);
						}
					}
				}
			}
		}
		return ubaiList;
	}

	// 联合信息安全表查询
	public UserBaseAccountInfo getUbaiAndUasiById(BigDecimal bigDecimal) {
		return userBaseAccountInfoMapper.getUbaiAndUasiById(bigDecimal);
	}

	// 通过手机号联合信息安全表查询
	public UserBaseAccountInfo getUbaiAndUasiByPhone(String session_phone) {
		return userBaseAccountInfoMapper.getUbaiAndUasiByPhone(session_phone);
	}

	// 名和密码查询对象
	@Override
	public List<UserBaseAccountInfo> getByUsername1(Map<String, Object> map) {
		// System.out.println("}}}}}}}"+map.get("loginName")+"
		// "+map.get("Password"));
		return userBaseAccountInfoMapper.getAllUserBaseAccountInfo(map);
	}

	// 查询是否有此手机号码 6/5
	@Override
	public UserBaseAccountInfo getmobliepho(String mobile) {
		// TODO Auto-generated method stub
		return userBaseAccountInfoMapper.getmobliepho(mobile);
	}

	// 用于用户等级编辑 UserGradeEditorManager
	// 查询用户名 真实姓名 会员等级
	@Override
	public List<UserBaseAccountInfo> listUserforUserGradeEditor(Map<String, Object> map) {
		List<UserBaseAccountInfo> list = userBaseAccountInfoMapper.listUserforUserGradeEditor(map);
		for (UserBaseAccountInfo ubai : list) {
			decryptName(ubai);
		}
		return list;
	}

	@Override
	public UserBaseAccountInfo getUserforUserGradeEditor(BigDecimal id) {
		UserBaseAccountInfo ubai=userBaseAccountInfoMapper.getUserforUserGradeEditor(id);
		decryptName(ubai);
		return ubai;
	}
	
	/**
	 * 解密 登录名 用户名
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年7月10日 下午2:14:25 
	 * @param ubai
	 */
	public void decryptName(UserBaseAccountInfo ubai) {
		String loginname = ubai.getLoginname();
		String realname = ubai.getRealname();
		if (StringUtils.isNotEmpty(loginname)) {
			ubai.setLoginname(AES.getDecrypt(loginname));
		}
		if (StringUtils.isNotEmpty(realname)) {
			ubai.setRealname(AES.getDecrypt(realname));
		}
	}

}