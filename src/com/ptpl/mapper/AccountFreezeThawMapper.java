package com.ptpl.mapper;

import java.util.List;

import com.ptpl.model.AccountFreezeThaw;

public interface AccountFreezeThawMapper {
		void insert(AccountFreezeThaw record); 
		void insertSelective(AccountFreezeThaw record); 
		/**
		 * 根据唯一标识查询解冻冻结记录
		* @Title: getByTrxid 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param trxid 汇付返回唯一标识
		* @param @return  参数说明 
		* @return AccountFreezeThaw    返回类型 
		* @author jiangxueyou
		* @throws
		 */
		AccountFreezeThaw getByTrxid(String trxid);
		/**
		 * 根据订单号查询冻结记录
		* @Title: getByOrdId 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param OrdId
		* @param @return  参数说明 
		* @return AccountFreezeThaw    返回类型 
		* @author jiangxueyou
		* @throws
		 */
		AccountFreezeThaw getByOrdId(String OrdId);
		/**
		 * 解冻的时候修改是否处理,冻结解冻的时候修改状态
		* @Title: update 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param record  参数说明 
		* @return void    返回类型 
		* @author jiangxueyou
		* @throws
		 */
		void update(AccountFreezeThaw record);
		/**
		 * 查询全部
		* @Title: queryAll 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @return  参数说明 
		* @return List<AccountFreezeThaw>    返回类型 
		* @author jiangxueyou
		* @throws
		 */
		List<AccountFreezeThaw> queryAll(AccountFreezeThaw aft);
		/**
		 * 页面条件查询
		 * @param @param accountfreezethaw
		 * @param @return
		 * @return List<AccountFreezeThaw>
		 * @author jiangxueyou
		 */
		List<AccountFreezeThaw> getLableSelect(AccountFreezeThaw accountfreezethaw);
}
