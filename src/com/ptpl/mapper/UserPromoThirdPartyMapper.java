package com.ptpl.mapper;

import com.ptpl.model.UserPromoThirdParty;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户推广第三方公司设置 Mapper
 * 
 * @author xiaoy
 *
 * @version 2016年9月30日 上午9:35:19
 */
public interface UserPromoThirdPartyMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int deleteByThirdPartyName(String thirdPartyName);

	int insertSelective(UserPromoThirdParty record);

	UserPromoThirdParty selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(UserPromoThirdParty record);
	
	int updateNameAndCode(String oldName,String code,String newName);

	List<UserPromoThirdParty> selective(UserPromoThirdParty userPromoThirdParty);
	
	int updateByUPID (BigDecimal upid);
}