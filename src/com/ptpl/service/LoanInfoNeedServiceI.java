package com.ptpl.service;

import com.ptpl.model.LoanInfoNeed;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 借款资料自填类选项设置
 */
public interface LoanInfoNeedServiceI {
	//删除
	int deleteByPrimaryKey(BigDecimal id);

    //新增
    int insertSelective(LoanInfoNeed record);

    //根据id查找信息
    LoanInfoNeed selectByPrimaryKey(BigDecimal id);

     //根据id查找信息
    LoanInfoNeed selectByPrimaryKey2(BigDecimal id);
    
    //跟新
    int updateByPrimaryKey(LoanInfoNeed record);
    
    //更新
    int updateByPrimaryKeySelective(LoanInfoNeed record);
    
    //查询所有信息
    List<LoanInfoNeed> selectAll(LoanInfoNeed infoNeed);
    
    List<LoanInfoNeed> selectByConditionLoaninfo(LoanInfoNeed infoNeed);
    
    //获取最后一个编号
    String selectNeedNo();
    
    //通过资料引用查找资料本身(文本资料-公共资料)
    List<LoanInfoNeed> selectloanByQuote();
    
    //通过资料引用查找资料本身(图片资料-引用资料)
    List<LoanInfoNeed> selectloanBypic();
    
    //通过资料引用查找资料本身(文本资料-补充资料)
    List<LoanInfoNeed> selectloanByQuoteyy();
    
    //通过资料引用查找资料本身(图片资料-补充资料)
    List<LoanInfoNeed> selectloanBypicyy();
    
    //查询所有启用且未被引用的项目
    List<LoanInfoNeed> selectNeedByiscite();
}
