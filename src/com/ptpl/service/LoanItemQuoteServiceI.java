package com.ptpl.service;

import com.ptpl.model.LoanItemQuote;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LIUJ
 * 借款资料项目引用设置
 */
public interface LoanItemQuoteServiceI {
	//删除
    int deleteByPrimaryKey(BigDecimal id);
    
    //添加
    int insertSelective(LoanItemQuote record);
    
    //根据id查询信息
    LoanItemQuote selectByPrimaryKey(BigDecimal id);
     
    //更新
    int updateByPrimaryKeySelective(LoanItemQuote record);
    
    //查询所有信息
    List<LoanItemQuote> selectAllQuote(LoanItemQuote itemQuote);
    
    List<LoanItemQuote> selectByConditionLoaninfo(LoanItemQuote itemQuote);
    
    //获取最后一个编号
    String selectNeedNo();
    
    //通过编号查询资料引用
    LoanItemQuote selectloanByLiqno(String linno);

    //把选择类的的关联查询出来
    List<LoanItemQuote> public_XuanZe();

    //把自填类的关联查询出来
    List<LoanItemQuote> public_ZiTian();

      //查询所有公共资料的信息
    List<LoanItemQuote> selectAllQuote_GongGong();

    //查询所有补充资料的信息
    List<LoanItemQuote> selectAllQuote_BuChong();

    //获取最后一个排序号
    Short selectMaxSeriesno1();

     //根据liqno更改是否在引用的状态
    int updateByLiqno(LoanItemQuote itemQuote);
}
