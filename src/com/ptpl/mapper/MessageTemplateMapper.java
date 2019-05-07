package com.ptpl.mapper;

import com.ptpl.model.MessageTemplate;
import java.math.BigDecimal;
import java.util.List;
/**
 * 短信内容模板Mapper
 * @author 作者 xiaoy: 
 * @version 创建时间：2016年12月29日 下午3:33:52 
 *
 */
public interface MessageTemplateMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(MessageTemplate record);

	int insertSelective(MessageTemplate record);

	MessageTemplate selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(MessageTemplate record);

	int updateByPrimaryKey(MessageTemplate record);
	
	List<MessageTemplate> selective(MessageTemplate messageTemplate);
}