package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AdminUser;

public interface AdminUserMapper {
    int insert(AdminUser adminUser);

    /**
     * 更新数据
     * @param adminUser
     * @return
     */
    int insertSelective(AdminUser adminUser);
    /*根据用户名查询密码*/
    AdminUser findAdminUserPSWByUsName(AdminUser adminUser);
    /**
     * 更新最后登录时间
     * @param record
     * @return
     */
    int updateAdminUser(AdminUser adminUser);
    
    /**
     * 根据账号或密码输入错误次数和禁止状态查询全部用户最后登录时间
     * @param record
     * @return
     */
    List<AdminUser> findAllAdminUserByForBid(AdminUser adminUser);
    /**
     * 根据邮箱查询邮箱是否存在
     * @param adminUser
     * @return
     */
    AdminUser findAdminUserByEamil(AdminUser adminUser); 
    /**
     * 查询全部用户
     * @return
     */
    List<AdminUser> findAdminUsers(AdminUser adminUser);
    
     /**
     * 
    * @Title: findRoleByAdminUserId 
    * @Description: TODO(根据角色ID查询对应的角色   服务自定义标签) 
    * @param @param adminUser
    * @param @return  参数说明 
    * @return AdminUser    返回类型 
    * @author chenjiaming
    * @throws
     */
    AdminUser findRoleByAdminUserId(Integer id);
 
    /**
     * 
    * @Title: findUserByRoleId 
    * @Description: TODO(根据角色ID查询已经分配的用户) 
    * @param @param id
    * @param @return  参数说明 
    * @return List<AdminUser>    返回类型 
    * @author chenjiaming
    * @throws
     */
    List<AdminUser> findUserByRoleId(Integer id);
    
    /**
     * 根据id获取对应的记录
     * @param bigDecimal
     * @return
     */
	AdminUser getAdminUserById(BigDecimal bigDecimal);
}