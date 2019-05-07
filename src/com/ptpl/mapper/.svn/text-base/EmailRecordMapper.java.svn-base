package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.EmailRecord;

/**
 * 
 * 邮件发送记录Dao接口层
 * EmailRecordMapper
 * 创建人:chenjiaming
 * 时间：2016年08月23日 17:43:09
 * @version 1.0.0
 *
 */
public interface EmailRecordMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(邮件发送记录数据增加方法) 
	* @param @param emailRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(EmailRecord emailRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(邮件发送记录数据增加方法，非空值) 
    * @param @param emailRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(EmailRecord emailRecord);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(邮件发送记录根据Id 删除方法) 
     * @param @param emailRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(邮件发送记录 更新方法) 
     * @param @param emailRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(EmailRecord emailRecord); 
     /**
     * 
     * @Title: findEmailRecords
     * @Description: TODO(邮件发送记录 查询全部) 
     * @param @param emailRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<EmailRecord> findEmailRecords(EmailRecord emailRecord);
    
     /**
     * 
    * @Title: findEmailRecordById 
    * @Description: TODO(根据Id查询对应的邮件发送记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    EmailRecord findEmailRecordById(BigDecimal id);
}
