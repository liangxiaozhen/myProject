package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AdminUser;
import com.ptpl.model.CancelValidate;

/**
 * 屏蔽安全验证设置业务层
 * CancelValidateServiceI
 * @date 2016年11月25日 15:42:00
 * @author shenggege
 * @version 1.0.0
 *
 */
public interface CancelValidateServiceI {

	/**
	 * @Title: insert
	 * @Description: TODO(屏蔽安全验证设置数据增加方法)
	 * @param @param CancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int insert(CancelValidate cancelValidate);
	
	/**
	 * @Title: insertSelective
	 * @Description: TODO(屏蔽安全验证设置数据增加方法,非空值)
	 * @param @param CancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int insertSelective(CancelValidate cancelValidate);
	
	/**
	 * @Title: deleteById
	 * @Description: TODO(屏蔽安全验证设置根据Id 删除方法)
	 * @param @param CancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int deleteById(BigDecimal id);
	
	/**
	 * @Title: deleteByBaseId
	 * @Description: TODO(屏蔽安全验证设置根据用户Id 删除方法)
	 * @param @param CancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int deleteByBaseId(BigDecimal id);
	
	/**
	 * @Title: updateById
	 * @Description: TODO(屏蔽安全验证设置更新方法)
	 * @param @param CancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int updateById(CancelValidate cancelValidate);
	
	/**
	 *@Title: findCancelValidate
	 *@Description: TODO(屏蔽安全验证设置查询全部)
	 *@param @param cancelValidate
	 *@param @return 参数说明
	 *@return int 返回类型
	 *@author shenggege
	 *@throws
	 */
	List<CancelValidate> findCancelValidates(CancelValidate cancelValidate);
	
	/**
	 * @Title: getByUsername
	 * @Description: TODO(屏蔽安全验证设置根据用户名查找用户id)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	
	public CancelValidate getByUsername(String username);
	
  /**
   * 
   * @Title: findCancelValidateById 
   * @Description: TODO(根据Id查询对应的屏蔽安全验证设置 信息) 
   * @param @param id
   * @param @return  参数说明 
   * @return WCCAwardRule    返回类型 
   * @author shenggege
   * @throws
   */
   CancelValidate findCancelValidateById(BigDecimal id);

   //通过单条条件查询整条信息
   List<CancelValidate> selectCancelValidateByCondition(CancelValidate cancelValidate);

}
