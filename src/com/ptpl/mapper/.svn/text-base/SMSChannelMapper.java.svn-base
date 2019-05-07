package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.SMSChannel;
/**
 * 短信通道管理Mapper 
 * @author 作者 xiaoy: 
 * @version 创建时间：2016年12月27日 上午11:23:55 
 *
 */
public interface SMSChannelMapper {
	int insert(SMSChannel channel);

	// 添加
	int insertSelective(SMSChannel channel);

	// 获取所有的短信通道信息
	List<SMSChannel> selective(SMSChannel channel);

	// 删除信息
	int deleteByPrimaryKey(BigDecimal id);

	// 通过id查看详情
	SMSChannel selectByPrimaryKey(BigDecimal id);

	// 更新
	int updateByPrimaryKeySelective(SMSChannel channel);
}