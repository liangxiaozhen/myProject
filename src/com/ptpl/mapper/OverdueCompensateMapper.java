package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.OverdueCompensate;

/**
 * @author:liuqh
 * @date:2016年07月14日 14:13:02
 * @description:标的逾期代偿设置
 */
public interface OverdueCompensateMapper extends BaseMapper<OverdueCompensate>{
	//根据tid查询信息
	List<OverdueCompensate> selectoverPenBytid(BigDecimal tid);

    List<String> selectGradeByTid(BigDecimal tid);
}