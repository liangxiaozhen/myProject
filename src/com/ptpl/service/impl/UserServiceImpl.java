package com.ptpl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptpl.mapper.UserMapper;
import com.ptpl.model.User;
import com.ptpl.service.UserServiceI;

/**
 * @author gacl
 * 使用@Service注解将UserServiceImpl类标注为一个service
 * service的id是userService
 */

public class UserServiceImpl implements UserServiceI {

    /**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用UserMapper时，Spring就会自动注入UserMapper
     */
    @Autowired
    private UserMapper userMapper;//注入dao
    
    
    public void addUser(User user) {
        userMapper.insert(user);
    }
    
    
    public User getUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
    
    public List<User> getAllUser(Map map) {
        return userMapper.getAllUser(map);
    }

    public int findUserCount(Map map){
		return userMapper.findUserCount(map);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(record);
	}


	@Override
	public int deleteByPrimaryKey(long id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}
	
}