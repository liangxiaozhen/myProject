package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.SMSChannelMapper;
import com.ptpl.model.SMSChannel;
import com.ptpl.service.SMSChannelServiceI;

/**
 * 短信通道管理ServiceImpl
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月27日 上午11:25:34
 *
 */
public class SMSChannelServiceImpl implements SMSChannelServiceI {

	@Autowired
	private SMSChannelMapper mapper;

	@Override
	public int insert(SMSChannel record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SMSChannel record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<SMSChannel> selective(SMSChannel channel) {
		return mapper.selective(channel);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public SMSChannel selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SMSChannel channel) {
		return mapper.updateByPrimaryKeySelective(channel);
	}
}
