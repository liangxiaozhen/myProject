package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.SySNoticeBiz;

/**
 * 系统通知业务设置Mapper
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2016年12月26日 下午4:32:21
 *
 */
public interface SySNoticeBizMapper {
	int insert(SySNoticeBiz record);

	int insertSelective(SySNoticeBiz record);

	SySNoticeBiz selectByPrimaryKey(BigDecimal id);

	int deleteByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(SySNoticeBiz sysNoticeBiz);

	List<SySNoticeBiz> selective(SySNoticeBiz sysNoticeBiz);
}