package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptpl.model.AheadRepayAwardRecord;
import com.ptpl.model.UserBaseAccountInfo;

public interface UserBaseAccountInfoServiceI {

	int insert(UserBaseAccountInfo ubai);

	int insertSelective(UserBaseAccountInfo ubai);

	public UserBaseAccountInfo selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(UserBaseAccountInfo record);

	public UserBaseAccountInfo getUserBaseAccountInfoByOneCondition(UserBaseAccountInfo ubai);

	/**
	 * 注册新用户
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年3月17日 下午5:11:44
	 * @param ubai
	 * @param pwd
	 * @param regType
	 *            注册类型
	 * @param originclient 注册来源
	 * @return
	 */
	int insertForRegister(UserBaseAccountInfo ubai, String pwd, Short regType, Short originclient,String promoCode,HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 获取所有用户信息
	 * 
	 * @return List<UserBaseAccountInfo>
	 */
	List<UserBaseAccountInfo> getAllUserBaseAccountInfo(Map map);

	/**
	 * 
	 * @Title: deleteById @Description: TODO(用户基本信息根据Id 删除方法) @param @param
	 *         userBaseAccountInfo @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	int deleteById(BigDecimal id);

	/**
	 * 
	 * @Title: findUserBaseAccountInfoById @Description: TODO(根据ID查询对应的用户基本信息
	 *         信息) @param @param id @param @return 参数说明 @return WCCAwardRule
	 *         返回类型 @author chenjiaming @throws
	 */
	UserBaseAccountInfo getUserBaseAccountInfoById(BigDecimal id);

	/**
	 * 
	 * @Title: findUserBaseAccountInfos @Description: TODO(用户基本信息
	 *         查询全部) @param @param userBaseAccountInfo @param @return
	 *         参数说明 @return int 返回类型 @author chenjiaming @throws
	 */
	List<UserBaseAccountInfo> getUserBaseAccountInfos(UserBaseAccountInfo userBaseAccountInfo);

	/**
	 * 
	 * @Title: findUserBaseAccountInfos @Description: TODO(用户基本信息 查询全部
	 *         用户名，手机号码，邮箱，真实名称模糊搜索) @param @param
	 *         userBaseAccountInfo @param @return 参数说明 @return int 返回类型 @author
	 *         chenjiaming @throws
	 */
	List<UserBaseAccountInfo> getUserBaseAccountInfosBlurred(UserBaseAccountInfo userBaseAccountInfo);

	// 通过该用户名，判断用户是否存在
	List<UserBaseAccountInfo> userbase(String loginname);

	/**
	 * 
	 * @Title: getUserBaseAccountInfoAndUserFSAccountInfo @Description:
	 *         TODO(关联用户托管账号查询用户基本信息) @param @param accountInfo @param @return
	 *         参数说明 @return List<UserBaseAccountInfo> 返回类型 @author cjm @throws
	 */
	List<UserBaseAccountInfo> getUserBaseAccountInfoAndUserFSAccountInfo(UserBaseAccountInfo accountInfo);

	/**
	 * 
	 * @Title: getUserBaseAccountInfoAndUserFSAccountInfoById @Description:
	 *         TODO(根据用户主键ID关联用户托管账号查询用户基本信息) @param @param id @param @return
	 *         参数说明 @return UserBaseAccountInfo 返回类型 @author cjm @throws
	 */
	UserBaseAccountInfo getUserBaseAccountInfoAndUserFSAccountInfoById(BigDecimal id);

	/**
	 * @author lhs
	 * @Description: TODO(根据用户登录名查找用户ID)
	 */
	UserBaseAccountInfo getuserloginname(String loginname);

	/**
	 * @author shenggege
	 * @Description: TODO(通过用户id查询用户信息)
	 * @param id
	 * @return
	 */
	UserBaseAccountInfo selectByBaseId(BigDecimal id);

	/**
	 * @author shenggege
	 * @Description: TODO(更新用户信息)
	 * @param id
	 * @return
	 */
	int update(UserBaseAccountInfo userBaseAccountInfo);

	/*
	 * 查询指定普通会员等级中的用户
	 */
	List<UserBaseAccountInfo> selectUsersByNormaluGrade(Short ugrade);

	/*
	 * 查询指定体验会员等级中的用户
	 */
	List<UserBaseAccountInfo> selectUsersBysTasteuGrade(Short ugrade);

	/**
	 * 查询部分
	 */
	List<UserBaseAccountInfo> getByUserBaseAccountInfo(UserBaseAccountInfo userBaseAccountInfo);

	/**
	 * 通过用户名来查询头像fck
	 */
	UserBaseAccountInfo getHeadImg(String loginname);

	/**
	 * 用户修改图片fck
	 */

	int updateHeaderImg(UserBaseAccountInfo accountInfo);

	List<UserBaseAccountInfo> getBaseInfoAll();

	/**
	 * 获取定向名单
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年4月15日 下午2:29:35
	 * @param SNLID
	 *            定向名单列表ID
	 * @return
	 */
	Set<UserBaseAccountInfo> getUserForSNLID(BigDecimal SNLID);

	//联合信息安全表查询
	UserBaseAccountInfo getUbaiAndUasiById(BigDecimal bigDecimal);

	//通过手机号联合信息安全表查询
	UserBaseAccountInfo getUbaiAndUasiByPhone(String session_phone);
	
	 //根据地用户名和密码查询对象 
     List<UserBaseAccountInfo> getByUsername1(Map<String,Object> map);
     
     // 查询是否有此手机号码  6/5
     UserBaseAccountInfo getmobliepho(String mobile);
     
 	/**
 	 * 用于用户等级编辑 UserGradeEditorManager
 	 * 查询用户名 真实姓名 会员等级
 	 * @author 作者 xiaoy: 
 	 * @version 创建时间：2017年7月10日 上午10:39:09 
 	 * @param map
 	 * @return
 	 */
 	List<UserBaseAccountInfo> listUserforUserGradeEditor (Map<String,Object> map);
 	
 	/**
	 * 用于用户等级编辑 UserGradeEditorManager
	 * 查询用户名 真实姓名 会员等级
	 * @author 作者 xiaoy: 
	 * @version 创建时间：2017年7月10日 下午2:10:50 
	 * @param id
	 * @return
	 */
	UserBaseAccountInfo getUserforUserGradeEditor(BigDecimal id);
}
