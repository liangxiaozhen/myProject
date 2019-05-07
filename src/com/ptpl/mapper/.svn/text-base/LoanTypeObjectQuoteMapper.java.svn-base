package com.ptpl.mapper;

import com.ptpl.model.LoanTypeObjectQuote;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 借款类型对象设置
 */
public interface LoanTypeObjectQuoteMapper {


    //添加
    int insert(LoanTypeObjectQuote record);

    //添加
    int insertSelective(LoanTypeObjectQuote record);

    //根据id查询信息
    LoanTypeObjectQuote selectByPrimaryKey(BigDecimal id);

    //更新
    int updateByPrimaryKeySelective(LoanTypeObjectQuote record);

    //更新
    int updateByPrimaryKey(LoanTypeObjectQuote record);
    
    //获取所有信息
    List<LoanTypeObjectQuote> gettypeObjectQuotes(LoanTypeObjectQuote loanTypeObjectQuote);
    
    //获取启用的标的类型
    List<LoanTypeObjectQuote> selectIsuse();
    
    //通过标号获取信息
    LoanTypeObjectQuote selectBySerialNo(BigDecimal serialno);

    //获取最后一个序号
    Short selectMaxSerialNo();
}