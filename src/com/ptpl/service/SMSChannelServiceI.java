package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.GuaranteeInfo;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.UserUpgradeRule;

/**
 * 短信通道管理ServiceI
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月27日 上午11:24:29
 *
 */
public interface SMSChannelServiceI {
	int insert(SMSChannel record);

	// 添加短信通道信息
	int insertSelective(SMSChannel record);

	// 获取所有的短信通道信息
	List<SMSChannel> selective(SMSChannel channel);

	// 删除信息
	int deleteByPrimaryKey(BigDecimal id);

	// 通过id查看详情
	SMSChannel selectByPrimaryKey(BigDecimal id);

	// 更新
	int updateByPrimaryKeySelective(SMSChannel channel);
}
