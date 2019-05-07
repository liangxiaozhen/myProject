package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.OverdueRecord;

/**
 * 
 * 标的提前还款奖励单个投资人记录Dao接口层
 * OverdueRecordMapper
 * 创建人:cjm
 * 时间：2017年02月10日 16:05:47
 * @version 1.0.0
 *
 */
public interface OverdueRecordMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖励单个投资人记录数据增加方法) 
	* @param @param overdueRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(OverdueRecord overdueRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖励单个投资人记录数据增加方法，非空值) 
    * @param @param overdueRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(OverdueRecord overdueRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖励单个投资人记录根据Id 删除方法) 
     * @param @param overdueRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖励单个投资人记录 更新方法) 
     * @param @param overdueRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(OverdueRecord overdueRecord); 
     /**
     * 
     * @Title: findOverdueRecords
     * @Description: TODO(标的提前还款奖励单个投资人记录 查询全部) 
     * @param @param overdueRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<OverdueRecord> findOverdueRecords(OverdueRecord overdueRecord);
    
     /**
     * 
    * @Title: findOverdueRecordById 
    * @Description: TODO(根据Id查询对应的标的提前还款奖励单个投资人记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    OverdueRecord findOverdueRecordById(BigDecimal id);
}
