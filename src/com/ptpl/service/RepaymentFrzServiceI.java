package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RepaymentFrz;

/**
 * 
 * 还款冻结解冻记录业务层
 * RepaymentFrzServiceI
 * 创建人:cjm
 * 时间：2017年04月21日 16:55:59
 * @version 1.0.0
 *
 */
public interface RepaymentFrzServiceI {

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
    int insert(RepaymentFrz repaymentFrz);

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
    int insertSelective(RepaymentFrz repaymentFrz);
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
    int deleteById(BigDecimal id);
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
     int  updateById(RepaymentFrz repaymentFrz); 
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
    List<RepaymentFrz> findRepaymentFrzs(RepaymentFrz repaymentFrz);
    
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
    RepaymentFrz findRepaymentFrzById(BigDecimal id);
    /**
     * 
     * @Title: findRepaymentFrz
     * @Description: TODO(条件还款冻结解冻记录查询) 
     * @param @param repaymentFrz
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    RepaymentFrz findRepaymentFrz(RepaymentFrz repaymentFrz);
    
    
}
