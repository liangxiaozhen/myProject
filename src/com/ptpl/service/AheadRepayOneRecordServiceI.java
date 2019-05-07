package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AheadRepayOneRecord;

/**
 * 
 * 标的提前还款奖励单个投资人记录业务层
 * AheadRepayOneRecordServiceI
 * 创建人:cjm
 * 时间：2016年10月21日 09:48:17
 * @version 1.0.0
 *
 */
public interface AheadRepayOneRecordServiceI {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖励单个投资人记录数据增加方法) 
	* @param @param aheadRepayOneRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
    int insert(AheadRepayOneRecord aheadRepayOneRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖励单个投资人记录数据增加方法，非空值) 
    * @param @param aheadRepayOneRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
    int insertSelective(AheadRepayOneRecord aheadRepayOneRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖励单个投资人记录根据Id 删除方法) 
     * @param @param aheadRepayOneRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖励单个投资人记录更新方法) 
     * @param @param aheadRepayOneRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
     int  updateById(AheadRepayOneRecord aheadRepayOneRecord); 
     /**
     * 
     * @Title: findAheadRepayOneRecords
     * @Description: TODO(标的提前还款奖励单个投资人记录查询全部) 
     * @param @param aheadRepayOneRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    List<AheadRepayOneRecord> findAheadRepayOneRecords(AheadRepayOneRecord aheadRepayOneRecord);
    
     /**
     * 
    * @Title: findAheadRepayOneRecordById
    * @Description: TODO(根据Id查询对应的标的提前还款奖励单个投资人记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return AheadRepayOneRecord    返回类型 
    * @author cjm
    * @throws
     */
    AheadRepayOneRecord findAheadRepayOneRecordById(BigDecimal id);
    
    /**
     * 
    * @Title: deleteByArbatchno 
    * @Description: TODO(根据提前还款批次号删除记录) 
    * @param @param Arbatchno
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author   cjm  
    * @throws
     */
    int deleteByArbatchno(String Arbatchno);
  
   
}
