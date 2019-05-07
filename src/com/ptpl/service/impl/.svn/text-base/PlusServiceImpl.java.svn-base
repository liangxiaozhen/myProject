package com.ptpl.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.PlusMapper;
import com.ptpl.model.Plus;
import com.ptpl.service.PlusServiceI;

/**
 * @author:liuqh
 * @date:2016年07月14日 14:48:01
 * @description:标的增益设置
 */
public class PlusServiceImpl extends BaseServiceImpl<Plus> implements PlusServiceI {

	@Autowired
	private PlusMapper plusMapper;
	@Override
	public Plus findPlusByTid(BigDecimal tid) {
 		return plusMapper.findPlusByTid(tid);
	}
	
}
 