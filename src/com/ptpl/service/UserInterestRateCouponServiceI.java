package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserInterestRateCoupon;

/**
 * 用户使用券Service
 * @author zhenglm
 *
 */
public interface UserInterestRateCouponServiceI {
	
	/**
	 * 新增用户使用券明细
	 * @param record
	 * @return
	 */
    int insert(UserInterestRateCoupon record);

    /**
     * 新增用户使用券明细（参数可选）
     * @param record
     * @return
     */
    int insertSelective(UserInterestRateCoupon record);
    
    /**
     * 条件查询用户使用券列表（关联用户基本信息表）
     * @param coupon
     * @return
     */
    List<UserInterestRateCoupon> findCouponList(UserInterestRateCoupon coupon);
    
    /**
     * 根据主键查看用户使用券详情（关联用户基本信息表）
     * @param id
     * @return
     */
    UserInterestRateCoupon findCouponDetailById(BigDecimal id);
    
    /**
     * 根据baseid查询用户是否使用了使用券
     * @param coupon
     * @return
     */
    List<UserInterestRateCoupon> findIsUseByBaseid(UserInterestRateCoupon coupon);
    
    /**
     * 根据baseid查询用户已过期的使用券
     * @param coupon
     * @return
     */
    List<UserInterestRateCoupon> findOverdueByBaseid(UserInterestRateCoupon coupon);

    /**
     * 更新用户使用券记录（参数可选）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserInterestRateCoupon record);

    
    /**
	 * @author pxl
	 * 将券作废掉
	 */
    int updataCouponById(UserInterestRateCoupon coupon);

    
    /**
     * 根据ID查询用户使用券记录
     * @param id
     * @return
     */
    UserInterestRateCoupon findUserInterestRateCouponById(BigDecimal id);

    /**
     * 根据用户的id来查询用户的加息券
     */
	List<UserInterestRateCoupon> findCouponByBaseId(UserInterestRateCoupon uirc);
	/**
	 * 查找所有的记录
	 */
	List<UserInterestRateCoupon> findUserInterestRateCoupon();
}
