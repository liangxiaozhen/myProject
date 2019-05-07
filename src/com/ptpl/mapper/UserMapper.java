package com.ptpl.mapper;

import java.util.List;
import java.util.Map;

import com.ptpl.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**获取所有用户信息
     * @return List<User>
     */
    List<User> getAllUser(Map map);
    
    int findUserCount(Map map);
}