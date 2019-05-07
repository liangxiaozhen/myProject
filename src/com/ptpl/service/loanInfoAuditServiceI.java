package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.Loaninfoaudit;

/**
 * 
 * @author lihs
 *@date:2016年08月19日 16:18:00
 * @description:借贷人信息审核
 */
public interface loanInfoAuditServiceI  {
	//新增审核信息
  int  insert(Loaninfoaudit model);
  //查询所有
  List< Loaninfoaudit> selectBaseByid(int id);
  //单条审核信息
  Loaninfoaudit selectByPrimaryKey(BigDecimal id);
  //删除单条审核记录
  int deleteByPrimaryKey(BigDecimal id);
  //修改信息
  int updateByPrimaryKeySelective(Loaninfoaudit record);
//查询所有审核信息
  List<Loaninfoaudit> selectAll();
//根据条件查找
  List<Loaninfoaudit> selectIdAndauditstatus(Loaninfoaudit record);
  //查看详细资料
  Loaninfoaudit selectminute(BigDecimal id);
  //查询出当前用户所有已审核通过的资料编号
  List<Loaninfoaudit> selectliano(BigDecimal baseid);
  //根据编号查出当前对象
  Loaninfoaudit selectloaninfo(String liano);
}
