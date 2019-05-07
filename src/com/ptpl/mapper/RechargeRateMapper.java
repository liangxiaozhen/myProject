package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.RechargeRate;

public interface RechargeRateMapper {
	/**
	 * 纯添加
	 * @param record
	 * @return
	 */
    int insert(RechargeRate record);
    /**
     * 有条件的添加
     * @param record
     * @return
     */
    int insertSelective(RechargeRate record);
    
    /**
     * 删除表,可以根据ID
     * @param record 引用字段
     * @return
     */
    long delete(BigDecimal  id);
    /**
     * 根据表的id查到整张充值费率设置表的详细信息
     * @param id
     * @return
     */
    RechargeRate getById(BigDecimal id);
    /**
     * 根据会员等级,充值方式,金额范围查询出一些相关数据
     */
    int getByTerm(RechargeRate record);
  
    /**
     * 查询所有的充值费率表
     * @return
     */
    List<RechargeRate> getAll2(RechargeRate rechargeRate);
    List<RechargeRate> getAll();
    /**
     * 根据getById得到信息表
     * 修改id为XXX的那张费率表的信息
     */
    int  update(RechargeRate rechargeRate); 
    /**
     * 根据会员等级,收费类型,银行编码获取数据库里面的分段最大金额
     */
    RechargeRate getByMaxMoney(RechargeRate rechargeRate);
    /**
     * 删除的时候查询最大金额用
     * @param rechargeRate
     * @return
     */
    int getByTermMaxMoney(String ugrade,short chargetype,short rechartype,String bankcode,double maxmoney);
    /**
     * 根据条件查询,主要是针对导航栏
     * @param rechargeRate
     * @return
     */
    List<RechargeRate> getTremAll(RechargeRate rechargeRate);
    /**
     * 根据会员编号查询会员等级
     */
    List<String> getCodeByUgrade(String  ugrade);
    /**
     * 从查询已经审核通过的对象
     * @return
     */
    List<RechargeRate> getIsAudit();
    /**
     * 根据会员等级查询对象
     * @param ugrade
     * @return
     */
    List<RechargeRate> getUgrade(String ugrade);
    /**
     * 根据会员等级+充值方式+启用来查询到充值费率设置表的记录,做手续费的处理
     * @param @param rechargeRate
     * @return void
     * @author jiangxueyou
     */
    RechargeRate getUgradeAndRecharTypeAndIsuseForRechargeRate(RechargeRate rechargeRate);
    /**
     * 根据充值方式查询充值费率表
     * @param @param rechartype
     * @param @return
     * @return RechargeRate
     * @author jiangxueyou
     */
    RechargeRate selectRechartypeByRechargeRate(short rechartype);
    
}