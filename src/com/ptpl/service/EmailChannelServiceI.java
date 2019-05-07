package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.EmailChannel;

public interface EmailChannelServiceI {
	//查询所有的邮箱通道
	List<EmailChannel> selectAll();
	
	//添加通道
	int insert(EmailChannel record);
	
	//删除通道
	int deleteByPrimaryKey(BigDecimal id);
	
	//根据iD找对象
	EmailChannel selectByPrimaryKey(BigDecimal id);
	
	//修改通道
	int updateByPrimaryKeySelective(EmailChannel record);
	
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
    List<EmailChannel> findEmailChannelsByEmailChannel(EmailChannel emailChannel);
}
