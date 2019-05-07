package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.AheadRealRepaymentMapper;
 import com.ptpl.model.AheadRealRepayment;
import com.ptpl.service.AheadRealRepaymentServiceI;
/**
 * 
 * 提前实际还款记录业务层
 * AheadRealRepaymentServiceI
 * 创建人:cjm
 * 时间：2017年01月04日 11:50:25
 * @version 1.0.0
 *
 */
public class AheadRealRepaymentServiceImpl implements AheadRealRepaymentServiceI{
	
	@Autowired
	private AheadRealRepaymentMapper aheadRealRepaymentMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(提前实际还款记录数据增加方法) 
	* @param @param aheadRealRepayment
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(AheadRealRepayment aheadRealRepayment) {
 		return aheadRealRepaymentMapper.insert(aheadRealRepayment);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(提前实际还款记录数据增加方法，非空值) 
    * @param @param aheadRealRepayment
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(AheadRealRepayment aheadRealRepayment) {
 		return aheadRealRepaymentMapper.insertSelective(aheadRealRepayment);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(提前实际还款记录根据Id 删除方法) 
     * @param @param aheadRealRepayment
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return aheadRealRepaymentMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(提前实际还款记录更新方法) 
     * @param @param aheadRealRepayment
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(AheadRealRepayment aheadRealRepayment) {
 		return aheadRealRepaymentMapper.updateById(aheadRealRepayment);
	}
	 /**
     * 
     * @Title: findAheadRealRepayments
     * @Description: TODO(提前实际还款记录查询全部) 
     * @param @param aheadRealRepayment
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<AheadRealRepayment> findAheadRealRepayments(AheadRealRepayment aheadRealRepayment) {
 		return aheadRealRepaymentMapper.findAheadRealRepayments(aheadRealRepayment);
	}
	
    
    /**
     * 
     * @Title: findAheadRealRepaymentByConditions
     * @Description: TODO(提前实际还款记录 条件查询) 
     * @param @param aheadRealRepayment
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
    public List<AheadRealRepayment> findAheadRealRepaymentByConditions(AheadRealRepayment aheadRealRepayment){
		return aheadRealRepaymentMapper.findAheadRealRepaymentByConditions(aheadRealRepayment);
	}
  	/**
     * 
    * @Title: findAheadRealRepaymentById
    * @Description: TODO(根据Id查询对应的提前实际还款记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return AheadRealRepayment    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public AheadRealRepayment findAheadRealRepaymentById(BigDecimal id) {
 		return aheadRealRepaymentMapper.findAheadRealRepaymentById(id);
	}
	 /**
     * 
    * @Title: findAheadRealRepaymentByRordernoAndBacthNo 
    * @Description: TODO(根据还款流水号查询 和批次号是否存在) 
    * @param @param Rorderno
    * @param @return  参数说明 
    * @return AheadRealRepayment    返回类型 
    * @author cjm
    * @throws
     */
	@Override
    public AheadRealRepayment findAheadRealRepaymentByRordernoAndBacthNo(String Rorderno,String bacthNo){
    	return aheadRealRepaymentMapper.findAheadRealRepaymentByRordernoAndBacthNo(Rorderno, bacthNo);
    }
	
	/**
     * 
    * @Title: deleteByRrealbatchno 
    * @Description: TODO(根据还款批次号删除记录) 
    * @param @param Rrealbatchno
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author   cjm  
    * @throws
     */
	@Override
	public int deleteByRrealbatchno(String Rrealbatchno) {
 		return aheadRealRepaymentMapper.deleteByRrealbatchno(Rrealbatchno);
	}
}
