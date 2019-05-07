package com.ptpl.mapper;

import com.ptpl.model.Loaninfoaudit;
import java.math.BigDecimal;
import java.util.List;

public interface LoaninfoauditMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Loaninfoaudit record);

    int insertSelective(Loaninfoaudit record);

    Loaninfoaudit selectByPrimaryKey(BigDecimal id);
    
    Loaninfoaudit selectminute(BigDecimal id);

    int updateByPrimaryKeySelective(Loaninfoaudit record);

    int updateByPrimaryKey(Loaninfoaudit record);

    List< Loaninfoaudit > selectBaseByid(int id);

    List<Loaninfoaudit> selectAll();

    List<Loaninfoaudit> selectIdAndauditstatus(Loaninfoaudit record);
    
    List<Loaninfoaudit> selectliano(BigDecimal baseid);
    
    Loaninfoaudit selectloaninfo(String liano);
}