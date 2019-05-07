package com.ptpl.mapper;

import com.ptpl.model.LoanInfoPreset;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 借款资料选择类项目设置
 */
public interface LoanInfoPresetMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);

    //添加
    int insert(LoanInfoPreset record);

    //添加
    int insertSelective(LoanInfoPreset record);

    //根据id查询信息
    LoanInfoPreset selectByPrimaryKey(BigDecimal id);

    //更新
    int updateByPrimaryKeySelective(LoanInfoPreset record);

    //更新
    int updateByPrimaryKey(LoanInfoPreset record);
    
    //查询所有信息
    List<LoanInfoPreset> selectAllPreset(LoanInfoPreset infoPreset);
    
    //查询最后一个编号
    String selectPresetNo();
    
    //通过引用资料查找资料本身(公共资料)
    List<LoanInfoPreset> selectloanByQuote();
    
    //通过引用资料查找资料本身(补充资料)
    List<LoanInfoPreset> selectloanByQuoteyy();
    
    //查询所有启用且未被引用的项目
    List<LoanInfoPreset> selectPresetByiscite();
    
    
}