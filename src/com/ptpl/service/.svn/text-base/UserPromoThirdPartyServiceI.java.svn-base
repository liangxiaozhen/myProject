package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserPromoThirdParty;
/**
 * 用户推广第三方公司设置 ServiceI
 * 
 * @author xiaoy
 *
 * @date 2016年9月30日 上午10:18:20
 */
public interface UserPromoThirdPartyServiceI {
	int deleteByPrimaryKey(BigDecimal id);
	
	int deleteByThirdPartyName(String thirdPartyName);
	
	int insertSelective(UserPromoThirdParty record);

	UserPromoThirdParty selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(UserPromoThirdParty record);
	
	int updateNameAndCode(String oldName,String code,String newName);

	List<UserPromoThirdParty> selective(UserPromoThirdParty userPromoThirdParty);
	
	int updateByUPID (BigDecimal upid);
}
