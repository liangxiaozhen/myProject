package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.MessageTemplate;

/**
 * 短信内容模板ServiceI
 * @author 作者 xiaoy: 
 * @version 创建时间：2016年12月29日 下午3:34:25 
 *
 */
public interface MessageTemplateServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(MessageTemplate record);

	int insertSelective(MessageTemplate record);

	MessageTemplate selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(MessageTemplate record);

	int updateByPrimaryKey(MessageTemplate record);
	
	List<MessageTemplate> selective(MessageTemplate messageTemplate);
}
