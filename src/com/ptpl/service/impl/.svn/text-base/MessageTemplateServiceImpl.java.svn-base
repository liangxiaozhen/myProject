package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.MessageTemplateMapper;
import com.ptpl.model.MessageTemplate;
import com.ptpl.service.MessageTemplateServiceI;

/**
 * 短信内容模板ServiceImpl
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月29日 下午3:35:19
 *
 */
public class MessageTemplateServiceImpl implements MessageTemplateServiceI {

	@Autowired
	private MessageTemplateMapper messageTemplateMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MessageTemplate record) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.insert(record);
	}

	@Override
	public int insertSelective(MessageTemplate record) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.insertSelective(record);
	}

	@Override
	public MessageTemplate selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MessageTemplate record) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MessageTemplate record) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MessageTemplate> selective(MessageTemplate messageTemplate) {
		// TODO Auto-generated method stub
		return messageTemplateMapper.selective(messageTemplate);
	}
}
