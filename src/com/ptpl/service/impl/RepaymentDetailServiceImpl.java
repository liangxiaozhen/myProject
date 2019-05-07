package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.RepaymentDetailMapper;
 import com.ptpl.model.RepaymentDetail;
import com.ptpl.service.RepaymentDetailServiceI;
/**
 * 
 * 还款批量详情记录业务层
 * RepaymentDetailServiceI
 * 创建人:cjm
 * 时间：2017年08月05日 18:41:12
 * @version 1.0.0
 *
 */
public class RepaymentDetailServiceImpl implements RepaymentDetailServiceI{
	
	@Autowired
	private RepaymentDetailMapper repaymentDetailMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(还款批量详情记录数据增加方法) 
	* @param @param repaymentDetail
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(RepaymentDetail repaymentDetail) {
 		return repaymentDetailMapper.insert(repaymentDetail);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(还款批量详情记录数据增加方法，非空值) 
    * @param @param repaymentDetail
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(RepaymentDetail repaymentDetail) {
 		return repaymentDetailMapper.insertSelective(repaymentDetail);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(还款批量详情记录根据Id 删除方法) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return repaymentDetailMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(还款批量详情记录更新方法) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(RepaymentDetail repaymentDetail) {
 		return repaymentDetailMapper.updateById(repaymentDetail);
	}
	 /**
     * 
     * @Title: findRepaymentDetails
     * @Description: TODO(还款批量详情记录查询全部) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<RepaymentDetail> findRepaymentDetails(RepaymentDetail repaymentDetail) {
 		return repaymentDetailMapper.findRepaymentDetails(repaymentDetail);
	}
	 /**
     * 
     * @Title: findRepaymentDetail
     * @Description: TODO(还款批量详情记录查询全部单条信息) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	public RepaymentDetail findRepaymentDetail(RepaymentDetail repaymentDetail){
    	return repaymentDetailMapper.findRepaymentDetail(repaymentDetail);
    }
    
  	/**
     * 
    * @Title: findRepaymentDetailById
    * @Description: TODO(根据Id查询对应的还款批量详情记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return RepaymentDetail    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public RepaymentDetail findRepaymentDetailById(BigDecimal id) {
 		return repaymentDetailMapper.findRepaymentDetailById(id);
	}
}
