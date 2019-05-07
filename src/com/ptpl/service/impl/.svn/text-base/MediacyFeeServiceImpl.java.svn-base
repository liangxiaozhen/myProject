package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.MediacyFeeMapper;
import com.ptpl.model.MediacyFee;
import com.ptpl.service.MediacyFeeServiceI;

public class MediacyFeeServiceImpl  implements MediacyFeeServiceI {

    @Autowired
    private MediacyFeeMapper mediacyFeeMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mediacyFeeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MediacyFee record) {
		return mediacyFeeMapper.insert(record);
	}

	@Override
	public int insertSelective(MediacyFee record) {
		return mediacyFeeMapper.insertSelective(record);
	}

	@Override
	public MediacyFee selectByPrimaryKey(BigDecimal id) {
		return mediacyFeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MediacyFee record) {
		return mediacyFeeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MediacyFee record) {
		return mediacyFeeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MediacyFee> listMediacyFee(MediacyFee record) {
		return mediacyFeeMapper.listMediacyFee(record);
	}
}
