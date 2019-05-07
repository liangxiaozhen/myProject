package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.EmailChannelMapper;
import com.ptpl.model.EmailChannel;
import com.ptpl.service.EmailChannelServiceI;

public class EmailChannelServiceImpl implements EmailChannelServiceI{
	@Autowired
	EmailChannelMapper emailchannelmapper;
	@Override
	//查询所有的邮箱通道
	public List<EmailChannel> selectAll() {		
		return emailchannelmapper.selectAll();
	}
	@Override
	//添加通道
	public int insert(EmailChannel record) {
		return emailchannelmapper.insert(record);
	}
	@Override
	//删除通道
	public int deleteByPrimaryKey(BigDecimal id) {
		return emailchannelmapper.deleteByPrimaryKey(id);
	}
	@Override
	//根据iD找具体对象
	public EmailChannel selectByPrimaryKey(BigDecimal id) {
		return emailchannelmapper.selectByPrimaryKey(id);
	}
	@Override
	//修改通道
	public int updateByPrimaryKeySelective(EmailChannel record) {
		return emailchannelmapper.updateByPrimaryKeySelective(record);
	}
	
 	/**
     * 
    * @Title: findEmailChannelsByEmailChannel 
    * @Description: TODO(条件查询) 
    * @param @param emailChannel
    * @param @return    设定文件 
    * @return List<EmailChannel>    返回类型 
    * @author   cjm  
    * @throws
     */
	@Override
	public List<EmailChannel> findEmailChannelsByEmailChannel(EmailChannel emailChannel) {
 		return emailchannelmapper.findEmailChannelsByEmailChannel(emailChannel);
	}
	
	
}
