package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.DividedPayments;

/**
 * 
 * 标的分期还款计划Dao接口层
 * DividedPaymentsMapper
 * 创建人:chenjiaming
 * 时间：2016年09月27日 16:34:28
 * @version 1.0.0
 *
 */
public interface DividedPaymentsMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的分期还款计划数据增加方法) 
	* @param @param dividedPayments
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(DividedPayments dividedPayments);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的分期还款计划数据增加方法，非空值) 
    * @param @param dividedPayments
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(DividedPayments dividedPayments);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的分期还款计划根据Id 删除方法) 
     * @param @param dividedPayments
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
     /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的分期还款计划 更新方法) 
     * @param @param dividedPayments
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    
     int  updateById(DividedPayments dividedPayments); 
     /**
     * 
     * @Title: findDividedPaymentss
     * @Description: TODO(标的分期还款计划 查询全部) 
     * @param @param dividedPayments
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     
    List<DividedPayments> findDividedPaymentss(DividedPayments dividedPayments);
    
     /**
     * 
    * @Title: findDividedPaymentsById 
    * @Description: TODO(根据Id查询对应的标的分期还款计划 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    
    DividedPayments findDividedPaymentsById(BigDecimal id);
    
    /**
     * 
    * @Title: findDividedPaymentsByConditions 
    * @Description: TODO(条件查找 DividedPayments) 
    * @param @param hashMap
    * @param @return  参数说明 
    * @return DividedPayments    返回类型 
    * @author cjm
    * @throws
     */
    DividedPayments  findDividedPaymentsByConditions(Map<String,Object> hashMap);
    /**
     * 查询一个标中是否有逾期
    * @Title: findisoverdue 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param dividedPayments
    * @param @return  参数说明 
    * @return List<DividedPayments>    返回类型 
    * @author jiangxueyou
    * @throws
     */
    List<DividedPayments> findisoverdue(DividedPayments dividedPayments);
    
    /**
     * 
    * @Title: findDividedPaymentsByRepayMentAudit 
    * @Description: TODO(查询 审核通过的标的分期还款计划信息 ) 
    * @param @param dividedPayments
    * @param @return    设定文件 
    * @return List<DividedPayments>    返回类型 
    * @author   cjm  
    * @throws
     */
    List<DividedPayments> findDividedPaymentsByRepayMentAudit(DividedPayments dividedPayments);
}
