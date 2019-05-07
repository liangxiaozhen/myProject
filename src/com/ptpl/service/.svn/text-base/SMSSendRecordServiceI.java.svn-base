package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.SMSSendRecord;

/**
 * 
 * 短信发送记录业务层
 * SMSSendRecordServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月24日 14:32:31
 * @version 1.0.0
 *
 */
public interface SMSSendRecordServiceI {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(短信发送记录数据增加方法) 
	* @param @param sMSSendRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(SMSSendRecord sMSSendRecord);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(短信发送记录数据增加方法，非空值) 
    * @param @param sMSSendRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(SMSSendRecord sMSSendRecord); 
     /**
     * 
     * @Title: findSMSSendRecords
     * @Description: TODO(短信发送记录查询全部) 
     * @param @param sMSSendRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<SMSSendRecord> findSMSSendRecords(SMSSendRecord sMSSendRecord);
 
    
    SMSSendRecord selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SMSSendRecord record);

    int updateByPrimaryKey(SMSSendRecord record);
   
}
