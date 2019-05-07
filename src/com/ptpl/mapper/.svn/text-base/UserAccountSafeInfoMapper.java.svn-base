package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;

public interface UserAccountSafeInfoMapper {
    int insert(UserAccountSafeInfo record);

    int insertSelective(UserAccountSafeInfo record);
    
    UserAccountSafeInfo selectByPrimaryKey(BigDecimal id);
    /**
     * 
    * @Title: update 
    * @Description: TODO(动态更新) 
    * @param @param userAccountSafeInfo
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int update(UserAccountSafeInfo userAccountSafeInfo);
    /**
     * 
    * @Title: getLoginPassWordByLoginName 
    * @Description: TODO(根据用户名查询密码) 
    * @param @param loginName
    * @param @return  参数说明 
    * @return UserAccountSafeInfo    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserAccountSafeInfo getLoginPassWordByLoginName(UserBaseAccountInfo userBaseAccountInfo);
    /**
     * 
    * @Title: selectByBaseId 
    * @Description: TODO(根据用户ID查询对应的用户账号安全信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return UserAccountSafeInfo    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserAccountSafeInfo selectByBaseId(BigDecimal id);
    /**
     * 
    * @Title: deleteByBaseId 
    * @Description: TODO(根据用户ID删除) 
    * @param @param id
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int deleteByBaseId(BigDecimal id);
    /**
     * 
    * @Title: getUserAccountsafeInfoCountByUgrade 
    * @Description: TODO(根据用户等级查询总数) 
    * @param @param ugrade
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int getUserAccountsafeInfoCountByUgrade(Integer ugrade);
    /**
     * 根据会员等级查询普通会员人数
     * @author xiaoy
     * @param ugrade
     * @return
     */
    int getCountByUgrade(Integer ugrade);
    /**
     * 根据会员等级查询体验会员人数
     * @author xiaoy
     * @param ugrade
     * @return
     */
    int getCountByUgradeExp(Integer ugrade);
  //查看所有用户的会员等级
    List<UserAccountSafeInfo> getmember();
    
    
    //根据会员等级查出旗下所有用户
    List<UserAccountSafeInfo> getuseradmin(Short ugrade);
    
    /**
     * @author shenggege
     * @Description: TODO(根据用户登录名查找用户ID) 
     */
    public UserAccountSafeInfo getByUsername (String username);
    
    //根据baseid查询
    UserAccountSafeInfo selectBaseId(BigDecimal baseid);

	Integer getIdByPwd(String pwd);
}