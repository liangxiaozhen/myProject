package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.exception.MoneyException;
import com.ptpl.model.RechargeRstr;

public interface RechargeRstrServiceI {
	 /**
     * 根据ID查询一个对象
     * @param id
     * @return
     */
    RechargeRstr getByIdToRechargeRstr(BigDecimal id);
    /**
     * 查询全部
     * @return
     */
    List<RechargeRstr> getAll(RechargeRstr record);
    /**
     * 根据ID删除一条数据
     * @return
     */
    int deleteRechargeRstr(BigDecimal id);
    /**
     * 添加
     * @param rechargeRstr
     * @throws MoneyException 
     */
    void saveRechargeRstr(RechargeRstr rechargeRstr) throws MoneyException;
    /**
     * 根据ID修改一条数据
     * @param rechargeRstr
     * @return
     * @throws MoneyException 
     */
    int  updateRechargeRstr(RechargeRstr rechargeRstr) throws MoneyException;
    /**
     * 根据会员等级查询充值设置表
     * @param @param ugradeString
     * @param @return
     * @return RechargeRstr
     * @author jiangxueyou
     */
	RechargeRstr getByUgradeToRechargeRstr(String ugradeString);
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
