package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AheadRepayPlatformRecord;

/**
 * 
 * 标的提前还款奖励平台记录Dao接口层
 * AheadRepayPlatformRecordMapper
 * 创建人:cjm
 * 时间：2016年10月21日 12:19:20
 * @version 1.0.0
 *
 */
public interface AheadRepayPlatformRecordMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖励平台记录数据增加方法) 
	* @param @param aheadRepayPlatformRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(AheadRepayPlatformRecord aheadRepayPlatformRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖励平台记录数据增加方法，非空值) 
    * @param @param aheadRepayPlatformRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(AheadRepayPlatformRecord aheadRepayPlatformRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖励平台记录根据Id 删除方法) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖励平台记录 更新方法) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(AheadRepayPlatformRecord aheadRepayPlatformRecord); 
     /**
     * 
     * @Title: findAheadRepayPlatformRecords
     * @Description: TODO(标的提前还款奖励平台记录 查询全部) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<AheadRepayPlatformRecord> findAheadRepayPlatformRecords(AheadRepayPlatformRecord aheadRepayPlatformRecord);
    
     /**
     * 
    * @Title: findAheadRepayPlatformRecordById 
    * @Description: TODO(根据Id查询对应的标的提前还款奖励平台记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    AheadRepayPlatformRecord findAheadRepayPlatformRecordById(BigDecimal id);
    
    /**
     * 
    * @Title: deleteByRorderno 
    * @Description: TODO(根据还款批次号删除) 
    * @param @param Rorderno
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author   cjm  
    * @throws
     */
    int deleteByRorderno(String Rorderno);
}
