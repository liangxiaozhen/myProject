package com.ptpl.mapper;

import com.ptpl.model.GuaranteeFee;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月12日 22:44:14
 * @description:标的担保费率设置
 */
public interface GuaranteeFeeMapper extends BaseMapper<GuaranteeFee>{
	
	/**
	 * 根据主键ID查询标的担保费设置详情
	 * @param id
	 * @return
	 */
	GuaranteeFee selectByPrimaryKey(BigDecimal id);
	
	//根据tid查询信息
	List<GuaranteeFee> selectgurfeeBytid(BigDecimal tid);

    List<String> selectGradeBytid(BigDecimal tid);

    List<GuaranteeFee> selectAllWithoutOne(BigDecimal id);
}