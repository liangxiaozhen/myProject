package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AheadRepayAwardRecord;

/**
 * 
 * 标的提前还款奖品补偿记录业务层
 * AheadRepayAwardRecordServiceI
 * 创建人:cjm
 * 时间：2016年10月21日 11:34:25
 * @version 1.0.0
 *
 */
public interface AheadRepayAwardRecordServiceI {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖品补偿记录数据增加方法) 
	* @param @param aheadRepayAwardRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
    int insert(AheadRepayAwardRecord aheadRepayAwardRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖品补偿记录数据增加方法，非空值) 
    * @param @param aheadRepayAwardRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
    int insertSelective(AheadRepayAwardRecord aheadRepayAwardRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖品补偿记录根据Id 删除方法) 
     * @param @param aheadRepayAwardRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖品补偿记录更新方法) 
     * @param @param aheadRepayAwardRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
     int  updateById(AheadRepayAwardRecord aheadRepayAwardRecord); 
     /**
     * 
     * @Title: findAheadRepayAwardRecords
     * @Description: TODO(标的提前还款奖品补偿记录查询全部) 
     * @param @param aheadRepayAwardRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
    List<AheadRepayAwardRecord> findAheadRepayAwardRecords(AheadRepayAwardRecord aheadRepayAwardRecord);
    
     /**
     * 
    * @Title: findAheadRepayAwardRecordById
    * @Description: TODO(根据Id查询对应的标的提前还款奖品补偿记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return AheadRepayAwardRecord    返回类型 
    * @author cjm
    * @throws
     */
    AheadRepayAwardRecord findAheadRepayAwardRecordById(BigDecimal id);
  
    /**
     * 
    * @Title: deleteByArbatchno 
    * @Description: TODO(根据还款批次号删除) 
    * @param @param Arbatchno
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author   cjm  
    * @throws
     */
    int deleteByArbatchno(String Arbatchno);
}
