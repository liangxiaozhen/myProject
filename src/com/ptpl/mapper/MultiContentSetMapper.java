package com.ptpl.mapper;

import com.ptpl.model.MultiContentSet;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 选择类多选内容设置表
 */
public interface MultiContentSetMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);

    //添加
    int insert(MultiContentSet record);

    //添加
    int insertSelective(MultiContentSet record);

    //根据id查询信息
    MultiContentSet selectByPrimaryKey(BigDecimal id);

    //更新
    int updateByPrimaryKeySelective(MultiContentSet record);

    //更新
    int updateByPrimaryKey(MultiContentSet record);
    
    //查询所有信息
    List<MultiContentSet> selectAllMult(MultiContentSet contentSet);
    
    //根据编号查询信息
    List<MultiContentSet> selectByMultiNo(String multino);
    
    //获取最后一个编号
    String selectLastNo();
    
    //查询所有启用的子项目
    List<MultiContentSet> selectALLisneedmult(); 
    
    //根据项目编号更新
    int updateByMultiNo(MultiContentSet record);
    
}