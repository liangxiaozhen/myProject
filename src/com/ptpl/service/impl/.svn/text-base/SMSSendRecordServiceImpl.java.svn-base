package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.SMSSendRecordMapper;
import com.ptpl.model.SMSSendRecord;
import com.ptpl.service.SMSSendRecordServiceI;

/**
 * 
 * 短信发送记录业务层 SMSSendRecordServiceI 创建人:chenjiaming 时间：2016年08月24日 14:32:31
 * 
 * @version 1.0.0
 *
 */
public class SMSSendRecordServiceImpl implements SMSSendRecordServiceI {

	@Autowired
	private SMSSendRecordMapper sMSSendRecordMapper;

	/**
	 * 
	 * @Title: insert @Description: TODO(短信发送记录数据增加方法) @param @param
	 * sMSSendRecord @param @return 参数说明 @return int 返回类型 @author
	 * chenjiaming @throws
	 */
	@Override
	public int insert(SMSSendRecord sMSSendRecord) {
		return sMSSendRecordMapper.insert(sMSSendRecord);
	}

	/**
	 * 
	 * @Title: insertSelective @Description:
	 * TODO(短信发送记录数据增加方法，非空值) @param @param sMSSendRecord @param @return
	 * 参数说明 @return int 返回类型 @author chenjiaming @throws
	 */
	@Override
	public int insertSelective(SMSSendRecord sMSSendRecord) {
		return sMSSendRecordMapper.insertSelective(sMSSendRecord);
	}

	/**
	 * 
	 * @Title: findSMSSendRecords @Description: TODO(短信发送记录查询全部) @param @param
	 * sMSSendRecord @param @return 参数说明 @return int 返回类型 @author
	 * chenjiaming @throws
	 */
	@Override
	public List<SMSSendRecord> findSMSSendRecords(SMSSendRecord sMSSendRecord) {
		return sMSSendRecordMapper.findSMSSendRecords(sMSSendRecord);
	}

	@Override
	public SMSSendRecord selectByPrimaryKey(BigDecimal id) {
		return sMSSendRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SMSSendRecord record) {
		return sMSSendRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SMSSendRecord record) {
		return sMSSendRecordMapper.updateByPrimaryKey(record);
	}

}
