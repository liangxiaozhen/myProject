package com.ptpl.mapper;

import com.ptpl.model.UserPromo;
import java.math.BigDecimal;
import java.util.List;
/**
 * 用户推广设置 Mapper
 * 
 * @author xiaoy
 *
 * @version 2016年9月30日 上午9:34:52
 */
public interface UserPromoMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(UserPromo record);

	UserPromo selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKey(UserPromo record);
	
	int updateBySupName(UserPromo record);
	
	int updatePromoCode(String oldSupPromoCode,String newSupPromoCode);

	List<UserPromo> selective(UserPromo userPromo);

	UserPromo selectByPromoCode(String promoCode);

	List<UserPromo> selectBySupName(String supName);
	
	List<UserPromo> getID();
}