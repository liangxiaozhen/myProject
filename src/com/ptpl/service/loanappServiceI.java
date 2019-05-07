package com.ptpl.service;

import com.ptpl.model.loanapp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author lihs
 * @date:2016年08月23日 15:03:00
 * @description:借贷人借贷申请
 */
public interface loanappServiceI {
    //添加
    int insert(loanapp record);

    //添加
    int insertSelective(loanapp record);

    //根据iD查对象
    List<loanapp> selectbaseid(int id);

    //删除
    int deleteByPrimaryKey(BigDecimal id);

    //根据iD查对象
    loanapp selectByPrimaryKey(BigDecimal id);

    // 选择性修改
    int updateByPrimaryKeySelective(loanapp record);

    int updateByPrimaryKey(loanapp record);

    //查寻所有
    List<loanapp> selectloanAll();

    //根据条件查询
    List<loanapp> selectloaninfos(loanapp record);

    /**
     * @param @param  record
     * @param @return 参数说明
     * @return List<Map<String,Integer>>    返回类型
     * @throws
     * @Title: findloanappCountByBaseid
     * @Description: TODO(借款记录 分类条数查询)
     * @author cjm
     */
    List<Map<BigDecimal, BigDecimal>> findloanappCountByBaseid(loanapp record);


    /**
     * @return
     * @Description :查询审核成功的借款申请
     */
    List<loanapp> selectloanAppbyappStatus();

    //根据条件查找借款信息
    List<loanapp> intervalloanapp(loanapp loanapp);

    //查询每个借款对应的已还款期限
    int selectcount(BigDecimal id);

    //日期筛选期间
    List<loanapp> selectdaterange(String startdate, String enddate);

    //按照条件搜索
    List<loanapp> selectByParam(Map<String, Object> map);

    void insertOrUpdateLoanappAndOftenLoan(loanapp loanapp, String isoften);
    //查待审核的
    List<loanapp> selectloanappAudit(loanapp loanapp);
}
