package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import com.ptpl.mapper.RepaymentFrzMapper;
 import com.ptpl.model.RepaymentFrz;
import com.ptpl.service.RepaymentFrzServiceI;
/**
 * 
 * 还款冻结解冻记录业务层
 * RepaymentFrzServiceI
 * 创建人:cjm
 * 时间：2017年04月21日 16:55:59
 * @version 1.0.0
 *
 */
public class RepaymentFrzServiceImpl implements RepaymentFrzServiceI{
	
	@Autowired
	private RepaymentFrzMapper repaymentFrzMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(还款冻结解冻记录数据增加方法) 
	* @param @param repaymentFrz
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(RepaymentFrz repaymentFrz) {
 		return repaymentFrzMapper.insert(repaymentFrz);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(还款冻结解冻记录数据增加方法，非空值) 
    * @param @param repaymentFrz
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(RepaymentFrz repaymentFrz) {
 		return repaymentFrzMapper.insertSelective(repaymentFrz);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(还款冻结解冻记录根据Id 删除方法) 
     * @param @param repaymentFrz
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return repaymentFrzMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(还款冻结解冻记录更新方法) 
     * @param @param repaymentFrz
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(RepaymentFrz repaymentFrz) {
 		return repaymentFrzMapper.updateById(repaymentFrz);
	}
	 /**
     * 
     * @Title: findRepaymentFrzs
     * @Description: TODO(还款冻结解冻记录查询全部) 
     * @param @param repaymentFrz
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<RepaymentFrz> findRepaymentFrzs(RepaymentFrz repaymentFrz) {
 		return repaymentFrzMapper.findRepaymentFrzs(repaymentFrz);
	}
	
	@Override
	public RepaymentFrz findRepaymentFrz(RepaymentFrz repaymentFrz) {
 		return repaymentFrzMapper.findRepaymentFrz(repaymentFrz);
	}
  	/**
     * 
    * @Title: findRepaymentFrzById
    * @Description: TODO(根据Id查询对应的还款冻结解冻记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return RepaymentFrz    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public RepaymentFrz findRepaymentFrzById(BigDecimal id) {
 		return repaymentFrzMapper.findRepaymentFrzById(id);
	}
	 
	 
}
