package com.ptpl.mapper;

import com.ptpl.model.WithdrawsCashCoupon;
import java.math.BigDecimal;
import java.util.List;

public interface WithdrawsCashCouponMapper {
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(提抵卷活动规则数据增加方法) 
	* @param @param withdrawsCashCoupon
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(WithdrawsCashCoupon withdrawsCashCoupon);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(提抵卷活动规则数据增加方法，非空值) 
    * @param @param withdrawsCashCoupon
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(WithdrawsCashCoupon withdrawsCashCoupon);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(提抵卷活动规则根据Id 删除方法) 
     * @param @param withdrawsCashCoupon
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(WithdrawsCashCoupon withdrawsCashCoupon);
    /**
     * 
    * @Title: selectByPrimaryKey 
    * @Description: TODO(根据ID查询对应的信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WithdrawsCashCoupon    返回类型 
    * @author chenjiaming
    * @throws
     */
    WithdrawsCashCoupon selectByPrimaryKey(BigDecimal id);
      /**
     * 
     * @Title: update 
     * @Description: TODO(提抵卷活动规则更新方法) 
     * @param @param withdrawsCashCoupon
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateByPrimaryKeySelective(WithdrawsCashCoupon withdrawsCashCoupon); 
     /**
     * 
     * @Title: findWithdrawsCashCoupons
     * @Description: TODO(提抵卷活动规则查询全部) 
     * @param @param withdrawsCashCoupon
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<WithdrawsCashCoupon> findWithdrawsCashCoupons(WithdrawsCashCoupon withdrawsCashCoupon);
}