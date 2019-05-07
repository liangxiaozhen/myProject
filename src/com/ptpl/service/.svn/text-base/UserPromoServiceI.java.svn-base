package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserPromo;
/**
 * 用户推广设置 ServiceI
 * 
 * @author xiaoy
 *
 * @date 2016年9月30日 上午10:20:27
 */
public interface UserPromoServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insertSelective(UserPromo record);
	
	int insertForRegister(UserBaseAccountInfo ubai,String promoCode,HttpServletRequest request,HttpServletResponse response);
	UserPromo selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKey(UserPromo record);

	int updateBySupName(UserPromo record);
	
	int updatePromoCode(String oldSupPromoCode,String newSupPromoCode);

	List<UserPromo> selective(UserPromo userPromo);

	UserPromo selectByPromoCode(String promoCode);

	List<UserPromo> selectBySupName(String supName);

	List<UserPromo> getID();
}
