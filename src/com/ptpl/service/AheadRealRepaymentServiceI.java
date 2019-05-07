package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AheadRealRepayment;

/**
 * 
 * 提前实际还款记录业务层
 * AheadRealRepaymentServiceI
 * 创建人:cjm
 * 时间：2017年01月04日 11:50:25
 * @version 1.0.0
 *
 */
public interface AheadRealRepaymentServiceI {

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
    int insert(AheadRealRepayment aheadRealRepayment);

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
    int insertSelective(AheadRealRepayment aheadRealRepayment);
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
    int deleteById(BigDecimal id);
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
     int  updateById(AheadRealRepayment aheadRealRepayment); 
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
    List<AheadRealRepayment> findAheadRealRepayments(AheadRealRepayment aheadRealRepayment);
    
    
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
    List<AheadRealRepayment> findAheadRealRepaymentByConditions(AheadRealRepayment aheadRealRepayment);
    
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
    AheadRealRepayment findAheadRealRepaymentById(BigDecimal id);
    /**
     * 
    * @Title: findAheadRealRepaymentByRordernoAndBacthNo 
    * @Description: TODO(根据还款流水号和批次号查询是否存在) 
    * @param @param Rorderno
    * @param @return  参数说明 
    * @return AheadRealRepayment    返回类型 
    * @author cjm
    * @throws
     */
    AheadRealRepayment findAheadRealRepaymentByRordernoAndBacthNo(String Rorderno,String bacthNo);
    
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
    int deleteByRrealbatchno(String Rrealbatchno);
   
}
