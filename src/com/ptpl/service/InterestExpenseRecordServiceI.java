package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.InterestExpenseRecord;

/**
 * 
 * 标的利息管理费记录业务层
 * InterestExpenseRecordServiceI
 * 创建人:cjm
 * 时间：2016年10月13日 16:56:29
 * @version 1.0.0
 *
 */
public interface InterestExpenseRecordServiceI {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的利息管理费记录数据增加方法) 
	* @param @param interestExpenseRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
    int insert(InterestExpenseRecord interestExpenseRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的利息管理费记录数据增加方法，非空值) 
    * @param @param interestExpenseRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
    int insertSelective(InterestExpenseRecord interestExpenseRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的利息管理费记录根据Id 删除方法) 
     * @param @param interestExpenseRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的利息管理费记录更新方法) 
     * @param @param interestExpenseRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
     int  updateById(InterestExpenseRecord interestExpenseRecord); 
     /**
     * 
     * @Title: findInterestExpenseRecords
     * @Description: TODO(标的利息管理费记录查询全部) 
     * @param @param interestExpenseRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    List<InterestExpenseRecord> findInterestExpenseRecords(InterestExpenseRecord interestExpenseRecord);
    
    /**
    * 
    * @Title: findInterestExpenseRecord
    * @Description: TODO(标的利息管理费记录 查询) 
    * @param @param interestExpenseRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
    */
    InterestExpenseRecord findInterestExpenseRecord(InterestExpenseRecord interestExpenseRecord);
     /**
     * 
    * @Title: findInterestExpenseRecordById
    * @Description: TODO(根据Id查询对应的标的利息管理费记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return InterestExpenseRecord    返回类型 
    * @author cjm
    * @throws
     */
    InterestExpenseRecord findInterestExpenseRecordById(BigDecimal id);
    /**
     * 
    * @Title: findInterestExpenseRecordByRorderno 
    * @Description: TODO(根据还款流水号rorderno查询对应的标的利息管理费记录 信息) 
    * @param @param rorderno
    * @param @return  参数说明 
    * @return InterestExpenseRecord    返回类型 
    * @author cjm
    * @throws
     */
    InterestExpenseRecord findInterestExpenseRecordByRorderno(String rorderno);
  
   
}