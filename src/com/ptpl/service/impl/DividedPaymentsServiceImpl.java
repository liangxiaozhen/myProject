package com.ptpl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.DividedPaymentsMapper;
 import com.ptpl.model.DividedPayments;
import com.ptpl.service.DividedPaymentsServiceI;
/**
 * 
 * 标的分期还款计划业务层
 * DividedPaymentsServiceI
 * 创建人:chenjiaming
 * 时间：2016年09月27日 16:34:28
 * @version 1.0.0
 *
 */
public class DividedPaymentsServiceImpl implements DividedPaymentsServiceI{
	
	@Autowired
	private DividedPaymentsMapper dividedPaymentsMapper;
	
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
	@Override
	public int insert(DividedPayments dividedPayments) {
 		return dividedPaymentsMapper.insert(dividedPayments);
	}
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
	@Override
	public int insertSelective(DividedPayments dividedPayments) {
 		return dividedPaymentsMapper.insertSelective(dividedPayments);
	}
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
	@Override
	public int deleteById(BigDecimal id) {
 		return dividedPaymentsMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的分期还款计划更新方法) 
     * @param @param dividedPayments
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(DividedPayments dividedPayments) {
 		return dividedPaymentsMapper.updateById(dividedPayments);
	}
	 /**
     * 
     * @Title: findDividedPaymentss
     * @Description: TODO(标的分期还款计划查询全部) 
     * @param @param dividedPayments
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<DividedPayments> findDividedPaymentss(DividedPayments dividedPayments) {
 		return dividedPaymentsMapper.findDividedPaymentss(dividedPayments);
	}
  	/**
     * 
    * @Title: findDividedPaymentsById
    * @Description: TODO(根据Id查询对应的标的分期还款计划信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public DividedPayments findDividedPaymentsById(BigDecimal id) {
 		return dividedPaymentsMapper.findDividedPaymentsById(id);
	}
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
	@Override
	public DividedPayments findDividedPaymentsByConditions(Map<String, Object> hashMap) {
 		return dividedPaymentsMapper.findDividedPaymentsByConditions(hashMap);
	}
	@Override
	public List<DividedPayments> findisoverdue(DividedPayments dividedPayments) {
		return dividedPaymentsMapper.findisoverdue(dividedPayments);
	}
	
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
	public List<DividedPayments> findDividedPaymentsByRepayMentAudit(DividedPayments dividedPayments){
    	return dividedPaymentsMapper.findDividedPaymentsByRepayMentAudit(dividedPayments);
    }
}
