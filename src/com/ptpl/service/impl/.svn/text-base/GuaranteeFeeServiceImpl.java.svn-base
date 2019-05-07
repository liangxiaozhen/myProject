package com.ptpl.service.impl;

import com.ptpl.mapper.GuaranteeFeeMapper;
import com.ptpl.mapper.GuaranteeInfoMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.GuaranteeFee;
import com.ptpl.model.TenderItem;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author:liuqh
 * @date:2016年07月12日 22:44:14
 * @description:标的担保费率设置
 */
public class GuaranteeFeeServiceImpl extends BaseServiceImpl<GuaranteeFee> implements GuaranteeFeeServiceI {
	
	@Autowired
	GuaranteeInfoMapper guaranteeInfoMapper;
	
	@Autowired
	GuaranteeFeeMapper guaranteeFeeMapper;

	@Autowired
    private TenderItemServiceI tenderItemService;

	@Autowired
    private UserGradeMapper userGrade;
	
	@Override
	public List<GuaranteeFee> selectByConditionAndDecorateGuaranteeid(GuaranteeFee condition) {
		//因为居间表中没有tno这个字段，页面根据条件“标号”查询时，要根据标号找到标id再根据标id去居间表中查询
        if (condition.getTno() != null && !condition.getTno().equals("")) {
            TenderItem t = tenderItemService.seltendbytno(condition.getTno());
            if(t!=null){
               condition.setTid(t.getId());
            }else{
                return null;
            }
        }
		return PublicUtil.decorateList(selectByCondition(condition), userGrade,null,guaranteeInfoMapper);
	}
	
	@Override
	public GuaranteeFee selectByPrimaryKey(BigDecimal id) {
		return guaranteeFeeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<GuaranteeFee> selectgurfeeBytid(BigDecimal tid) {
		return guaranteeFeeMapper.selectgurfeeBytid(tid);
	}

	@Override
	public List<String> selectGradeBytid(BigDecimal tid) {
		return guaranteeFeeMapper.selectGradeBytid(tid);
	}

	@Override
	public List<GuaranteeFee> selectAllWithoutOne(BigDecimal id) {
		return guaranteeFeeMapper.selectAllWithoutOne(id);
	}
}
