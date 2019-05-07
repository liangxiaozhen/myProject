package com.ptpl.service;

import java.util.List;
import java.util.Map;

import com.ptpl.model.User;

public interface UserServiceI {

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
    
    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    User getUserById(long id);
    
    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateByPrimaryKey(User record);
    
    /**
     * 删除用户
     * @param 根据Id
     * @return
     */
    int deleteByPrimaryKey(long id);
    
    /**获取所有用户信息
     * @return List<User>
     */
    List<User> getAllUser(Map map);
    
    int findUserCount(Map map);
}