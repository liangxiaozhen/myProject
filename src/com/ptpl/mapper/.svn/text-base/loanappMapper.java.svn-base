package com.ptpl.mapper;

import com.ptpl.model.loanapp;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface loanappMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(loanapp record);

    int insertSelective(loanapp record);

    loanapp selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(loanapp record);

    int updateByPrimaryKey(loanapp record);
    List< loanapp > selectBaseByid(int id);
    List<loanapp> selectloanAll();
    //条件查询
    List<loanapp> selectloaninfos(loanapp record);
    /**
     * 
    * @Title: findloanappCountByBaseid 
    * @Description: TODO(借款记录 分类条数查询) 
    * @param @param record
    * @param @return  参数说明 
    * @return List<Map<String,Integer>>    返回类型 
    * @author cjm
    * @throws
     */
    List<Map<BigDecimal,BigDecimal>> findloanappCountByBaseid(loanapp record);
    
    //查询所得的审核成功的借款申请
    List<loanapp> selectloanAppbyappStatus();
    
    //根据条件查找借款信息
    List<loanapp> intervalloanapp(loanapp loanapp);
    //查询每个借款对应的已还款期限
    int selectcount(BigDecimal id);
    
    //日期筛选期间
    List<loanapp> selectdaterange(String startdate,String enddate);
    
    //按照条件搜索
    List<loanapp> selectByParam(Map<String,Object> map);

    List<loanapp> selectloanappAudit(loanapp loanapp);
}