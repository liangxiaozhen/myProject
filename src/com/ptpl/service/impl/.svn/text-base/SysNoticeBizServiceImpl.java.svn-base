package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.SpecialNameListMapper;
import com.ptpl.mapper.SySNoticeBizMapper;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.SySNoticeBiz;
import com.ptpl.service.SysNoticeBizServiceI;

/**
 * 系统通知业务设置ServiceImpl
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年1月4日 下午2:59:10
 *
 */
public class SysNoticeBizServiceImpl implements SysNoticeBizServiceI {
	@Autowired
	private SySNoticeBizMapper mapper;
	@Autowired
	private SpecialNameListMapper snlMapper;

	@Override
	public int insert(SySNoticeBiz record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SySNoticeBiz record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SySNoticeBiz selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SySNoticeBiz sysNoticeBiz) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(sysNoticeBiz);
	}

	@Override
	public List<SySNoticeBiz> selective(SySNoticeBiz sysNoticeBiz) {
		// TODO Auto-generated method stub
		List<SpecialNameList> snlList = snlMapper.getSpecialNameLists(null);
		List<SySNoticeBiz> sysList = mapper.selective(sysNoticeBiz);
		for (SySNoticeBiz sys : sysList) {
			if (sys.getSnlid() != null) {
				for (SpecialNameList snl : snlList) {
					if (sys.getSnlid().intValue() == snl.getId().intValue()) {
						sys.setSnlname(snl.getBusinessName());
						break;
					}
				}
			}
		}
		return sysList;
	}

}
