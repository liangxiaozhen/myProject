package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserBankCardMapper;
import com.ptpl.model.China;
import com.ptpl.model.UserBankCard;
import com.ptpl.service.UserBankCardServiceI;

public class UserBankCardServiceImpl implements UserBankCardServiceI{
	
	/**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用UserMapper时，Spring就会自动注入UserMapper
     */
    @Autowired
    private UserBankCardMapper userBankCardMapper;//注入dao
    
    @Override
    public int insertSelective(UserBankCard userbankcard){
    	return userBankCardMapper.insertSelective(userbankcard);
    }
    
    @Override
    public List<China> findProvinceByPid(){
    	return userBankCardMapper.findProvinceByPid();
    }
    
    @Override
    public List<China> findCityByPid(long pid){
    	return userBankCardMapper.findCityByPid(pid);
    }
    
    @Override
    public List<UserBankCard> findUserBankInfo(UserBankCard card){
    	return userBankCardMapper.findUserBankInfo(card);
    }
    
    @Override
    public UserBankCard findDetailById(long id){
    	return userBankCardMapper.findDetailById(id);
    }
    
    @Override
    public int deleteUserBankInfo(long id){
    	return userBankCardMapper.deleteUserBankInfo(id);
    }
    
    @Override
    public int updateUserBankInfo(UserBankCard record){
    	return userBankCardMapper.updateUserBankInfo(record);
    }
    
    @Override
    public China findIdByName(String name){
    	return userBankCardMapper.findIdByName(name);
    }

	@Override
	public UserBankCard findDetailByCardNo(String cardno) {
		return userBankCardMapper.findDetailByCardNo(cardno);
	}

	@Override
	public UserBankCard findIsFastBindCard(UserBankCard userbankcard) {
		return userBankCardMapper.findIsFastBindCard(userbankcard);
	}

	@Override
	public int updateDefaultCard(UserBankCard userbankcard) {
		return userBankCardMapper.updateDefaultCard(userbankcard);
	}

	@Override
	public UserBankCard findIsDefaultCard(UserBankCard userbankcard) {
		return userBankCardMapper.findIsDefaultCard(userbankcard);
	}

	@Override
	public int updateBindStatus(UserBankCard userbankcard) {
		return userBankCardMapper.updateBindStatus(userbankcard);
	}

	@Override
	public UserBankCard selectByBaseIdAndCardNo(UserBankCard userbankcard) {
		return userBankCardMapper.selectByBaseIdAndCardNo(userbankcard);
	}

	@Override
	public UserBankCard selectBoundCardByBaseId(BigDecimal baseid) {
		return userBankCardMapper.selectBoundCardByBaseId(baseid);
	}

	@Override
	public List<UserBankCard> selectUnBoundCardByBaseId(BigDecimal baseid) {
		return userBankCardMapper.selectUnBoundCardByBaseId(baseid);
	}

	@Override
	public List<UserBankCard> getInfoByBaseid(BigDecimal baseid) {
		// TODO Auto-generated method stub
		return userBankCardMapper.getInfoByBaseid(baseid);
	}
    
}
