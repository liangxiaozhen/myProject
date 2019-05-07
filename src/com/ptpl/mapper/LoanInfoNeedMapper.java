package com.ptpl.mapper;

import com.ptpl.model.LoanInfoNeed;
import java.math.BigDecimal;
/**
 * @author liuj
 * 借款资料自填类选项设置
 */
import java.util.List;
public interface LoanInfoNeedMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);

    //添加
    int insert(LoanInfoNeed record);

    //添加
    int insertSelective(LoanInfoNeed record);

    //根据id查询信息
    LoanInfoNeed selectByPrimaryKey(BigDecimal id);

     //根据id查询信息(用于封装成json发给海网的，字段会比selectByPrimaryKey（）少一些)
    LoanInfoNeed selectByPrimaryKey2(BigDecimal id);

    //更新
    int updateByPrimaryKeySelective(LoanInfoNeed record);

    //更新
    int updateByPrimaryKey(LoanInfoNeed record);
    
    //查询所有信息
    List<LoanInfoNeed> selectAll(LoanInfoNeed infoNeed);
    
    //获取最后一个编号
    String selectNeedNo(); 
    
    //通过资料引用查找资料本身(文本资料-公共资料)
    List<LoanInfoNeed> selectloanByQuote();
    
    //通过资料引用查找资料本身(图片资料-公共资料)
    List<LoanInfoNeed> selectloanBypic();
    
    //通过资料引用查找资料本身(文本资料-补充资料)
    List<LoanInfoNeed> selectloanByQuoteyy();
    
    //通过资料引用查找资料本身(图片资料-补充资料)
    List<LoanInfoNeed> selectloanBypicyy();
    
    //查询所有启用且未被引用的项目
    List<LoanInfoNeed> selectNeedByiscite();
    
    
    
}