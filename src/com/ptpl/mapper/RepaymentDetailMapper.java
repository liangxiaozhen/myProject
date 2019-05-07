package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RepaymentDetail;

/**
 * 
 * 还款批量详情记录Dao接口层
 * RepaymentDetailMapper
 * 创建人:cjm
 * 时间：2017年08月05日 18:41:12
 * @version 1.0.0
 *
 */
public interface RepaymentDetailMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(还款批量详情记录数据增加方法) 
	* @param @param repaymentDetail
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(RepaymentDetail repaymentDetail);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(还款批量详情记录数据增加方法，非空值) 
    * @param @param repaymentDetail
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(RepaymentDetail repaymentDetail);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(还款批量详情记录根据Id 删除方法) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(还款批量详情记录 更新方法) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(RepaymentDetail repaymentDetail); 
     /**
     * 
     * @Title: findRepaymentDetails
     * @Description: TODO(还款批量详情记录 查询全部) 
     * @param @param repaymentDetail
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<RepaymentDetail> findRepaymentDetails(RepaymentDetail repaymentDetail);
    
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
    RepaymentDetail findRepaymentDetail(RepaymentDetail repaymentDetail);
    
     /**
     * 
    * @Title: findRepaymentDetailById 
    * @Description: TODO(根据Id查询对应的还款批量详情记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    RepaymentDetail findRepaymentDetailById(BigDecimal id);
}
