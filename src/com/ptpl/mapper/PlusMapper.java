package com.ptpl.mapper;

import java.math.BigDecimal;

import com.ptpl.model.Plus;

/**
 * @author:liuqh
 * @date:2016年07月14日 14:48:01
 * @description:标的增益设置
 */
public interface PlusMapper extends BaseMapper<Plus>{
	Plus findPlusByTid(BigDecimal tid);
}