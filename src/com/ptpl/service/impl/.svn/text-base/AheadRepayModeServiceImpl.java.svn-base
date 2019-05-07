package com.ptpl.service.impl;

import com.ptpl.mapper.AheadRepayModeMapper;
import com.ptpl.mapper.TenderItemMapper;
import com.ptpl.model.AheadRepayMode;
import com.ptpl.model.TenderItem;
import com.ptpl.service.AheadRepayModeServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AheadRepayModeServiceImpl implements AheadRepayModeServiceI {

	@Autowired
	private AheadRepayModeMapper mapper;
	@Autowired
	private TenderItemMapper tenderItemMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AheadRepayMode record) {
		return mapper.insertSelective(record);
	}

	@Override
	public AheadRepayMode selectByPrimaryKey(BigDecimal id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AheadRepayMode record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AheadRepayMode> selectModeBytid(BigDecimal tid) {
		return mapper.selectModeBytid(tid);
	}

	@Override
	public List<AheadRepayMode> selectModeByStyle(AheadRepayMode aheadRepayMode) {
		if(aheadRepayMode.getTenderitem()!=null&&!aheadRepayMode.getTenderitem().getTno().equals("")){
			String tno = aheadRepayMode.getTenderitem().getTno();
			TenderItem tenderitem = tenderItemMapper.seltendbytno(tno);
			if(tenderitem!=null){
				aheadRepayMode.setTid(tenderitem.getId());
			}else{
				return null;
			}
		}
		return mapper.selectModeByStyle(aheadRepayMode);
	}

	@Override
	public List<AheadRepayMode> selectModeByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectModeByMap(map);
	}
}
