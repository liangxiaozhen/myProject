package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RechargeRstr;

public interface RechargeRstrMapper {
     /**
      * 根据ID查询一个对象
      * @param id
      * @return
      */
     RechargeRstr selectByPrimaryKey(BigDecimal id);
     /**
      * 查询全部
      * @return
      */
     List<RechargeRstr> getAll(RechargeRstr rechargeRstr);
     /**
      * 根据ID删除一条数据
      * @return
      */
     int deleteByPrimaryKey(BigDecimal id);
  
     /**
      * 动态添加
      * @param rechargeRstr
      */
     void insertSelective(RechargeRstr rechargeRstr);
     /**
      * 根据ID修改一条数据
      * @param rechargeRstr
      * @return
      */
     int  updateByPrimaryKeySelective(RechargeRstr rechargeRstr);
     /**
      * 
      * @param @param ugradeString
      * @param @return
      * @return RechargeRstr
      * @author jiangxueyou
      */
	 RechargeRstr selectByUgrade(String ugradeString);
	/**
	 * 查询已经审核通过的对象
	 * @return
	 */
	List<RechargeRstr> getIsAudit(); 
	/**
	 * 根据会员等级,充值方式还有启用来查询充值设置表对象
	 * @param rechargeRstr
	 * @return
	 */
	RechargeRstr getUgradeAndRecharTypeAndIsuseForRechargeRstr(RechargeRstr rechargeRstr);
	
}
