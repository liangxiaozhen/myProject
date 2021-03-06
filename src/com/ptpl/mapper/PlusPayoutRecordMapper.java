package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.PlusPayoutRecord;

/**
 * 
 * 标的增益清算记录Dao接口层
 * PlusPayoutRecordMapper
 * 创建人:cjm
 * 时间：2016年11月23日 14:30:07
 * @version 1.0.0
 *
 */
public interface PlusPayoutRecordMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的增益清算记录数据增加方法) 
	* @param @param plusPayoutRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(PlusPayoutRecord plusPayoutRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的增益清算记录数据增加方法，非空值) 
    * @param @param plusPayoutRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(PlusPayoutRecord plusPayoutRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的增益清算记录根据Id 删除方法) 
     * @param @param plusPayoutRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(标的增益清算记录 更新方法) 
     * @param @param plusPayoutRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(PlusPayoutRecord plusPayoutRecord); 
     /**
     * 
     * @Title: findPlusPayoutRecords
     * @Description: TODO(标的增益清算记录 查询全部) 
     * @param @param plusPayoutRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<PlusPayoutRecord> findPlusPayoutRecords(PlusPayoutRecord plusPayoutRecord);
    
     /**
     * 
    * @Title: findPlusPayoutRecordById 
    * @Description: TODO(根据Id查询对应的标的增益清算记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    PlusPayoutRecord findPlusPayoutRecordById(BigDecimal id);
    /**
     * 
    * @Title: findPlusPayoutRecordByPporderno 
    * @Description: TODO(根据增益清算流水号查询信息) 
    * @param @param pporderno
    * @param @return  参数说明 
    * @return PlusPayoutRecord    返回类型 
    * @author cjm
    * @throws
     */
    PlusPayoutRecord findPlusPayoutRecordByPporderno(String pporderno);
    /**
     * 
    * @Title: findPlusPayoutRecordByRorderno 
    * @Description: TODO(根据还款流水号查询信息) 
    * @param @param rorderno
    * @param @return  参数说明 
    * @return PlusPayoutRecord    返回类型 
    * @author cjm
    * @throws
     */
    PlusPayoutRecord findPlusPayoutRecordByRorderno(String rorderno);
}
