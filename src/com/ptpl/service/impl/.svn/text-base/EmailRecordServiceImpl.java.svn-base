package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.EmailRecordMapper;
 import com.ptpl.model.EmailRecord;
import com.ptpl.service.EmailRecordServiceI;
/**
 * 
 * 邮件发送记录业务层
 * EmailRecordServiceI
 * 创建人:chenjiaming
 * 时间：2016年08月23日 17:43:09
 * @version 1.0.0
 *
 */
public class EmailRecordServiceImpl implements EmailRecordServiceI{
	
	@Autowired
	private EmailRecordMapper emailRecordMapper;
	
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
	@Override
	public int insert(EmailRecord emailRecord) {
 		return emailRecordMapper.insert(emailRecord);
	}
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
	@Override
	public int insertSelective(EmailRecord emailRecord) {
 		return emailRecordMapper.insertSelective(emailRecord);
	}
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
	@Override
	public int deleteById(BigDecimal id) {
 		return emailRecordMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(邮件发送记录更新方法) 
     * @param @param emailRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public int updateById(EmailRecord emailRecord) {
 		return emailRecordMapper.updateById(emailRecord);
	}
	 /**
     * 
     * @Title: findEmailRecords
     * @Description: TODO(邮件发送记录查询全部) 
     * @param @param emailRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
	@Override
	public List<EmailRecord> findEmailRecords(EmailRecord emailRecord) {
 		return emailRecordMapper.findEmailRecords(emailRecord);
	}
  	/**
     * 
    * @Title: findEmailRecordById
    * @Description: TODO(根据Id查询对应的邮件发送记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
	@Override
	public EmailRecord findEmailRecordById(BigDecimal id) {
 		return emailRecordMapper.findEmailRecordById(id);
	}
}
