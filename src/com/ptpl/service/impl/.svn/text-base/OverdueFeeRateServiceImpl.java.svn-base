package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.OverdueFeeRateMapper;
import com.ptpl.model.OverdueFeeRate;
import com.ptpl.service.OverdueFeeRateServiceI;

public class OverdueFeeRateServiceImpl implements OverdueFeeRateServiceI{

	@Autowired
	private OverdueFeeRateMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OverdueFeeRate record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(OverdueFeeRate record) {
		return mapper.insertSelective(record);
	}

	@Override
	public OverdueFeeRate selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(OverdueFeeRate record) {
		return mapper.updateByPrimaryKey(record);
	}

	 /**
     * 
    * @Title: findOverdueFeeRatesByTid 
    * @Description: TODO(根据标ID查找逾期滞纳金费率信息) 
    * @param @return  参数说明 
    * @return List<OverdueFeeRate>    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public List<OverdueFeeRate> findOverdueFeeRatesByTid(BigDecimal Tid) {
 		return mapper.findOverdueFeeRatesByTid(Tid);
	}

}
