package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.RechargeRate;

public interface RechargeRateServiceI {
	   
	/**
	 * 纯添加
	 * @param record
	 * @return
	 * @throws Exception 
	 */
    int addRechargeRate(RechargeRate rechargeRate) throws Exception;
   
    /**
     * 删除表,可以根据ID
     * @param record 引用字段
     * @return
     */
    long delete(BigDecimal id);
    /**
     * 根据表的id查到整张充值费率设置表的详细信息
     * @param id
     * @return
     * @throws Exception 
     */
    RechargeRate getById(BigDecimal id) throws Exception;
    /**
     * 根据会员等级,充值方式,金额范围查询出一些相关数据
     * record  表示条件,可以多个,可以一个
     */
    int getByTerm(RechargeRate record);

    
    /**
    /**
     * 查询所有的充值费率表
     * @return
     * @throws Exception 
     */
    List<RechargeRate> getAll2(RechargeRate recrod) throws Exception;
    List<RechargeRate> getAll();
    /**
     * 根据getById得到信息表
     * 修改id为XXX的那张费率表的信息
     */
    int  update(RechargeRate rechargeRate); 
 
    /**
     * 根据会员等级,收费类型,银行编码获取数据库里面的分段最大金额
     */
    RechargeRate getMaxMoney(RechargeRate record);
   /**
    * 删除的时候查询最大金额用的
    * @param record
    * @return
    */
    int getByTermMaxMoney(String ugrade,short chargetype,short rechartype,String bankcode,double maxmoney);
    List<RechargeRate> getTremAll(RechargeRate rechargeRate);
    /**
     * 获取已经审核通过的对象
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
    /**
     * 根据充值方式查询到充值费率表记录,主要用于页面返回参数
     * @param @param sum
     * @param @return
     * @return RechargeRate
     * @author jiangxueyou
     */
    public RechargeRate getRechargeRate(String sum);
}
