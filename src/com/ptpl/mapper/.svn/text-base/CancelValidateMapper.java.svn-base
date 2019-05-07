package com.ptpl.mapper;

import com.ptpl.model.AdminUser;
import com.ptpl.model.CancelValidate;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * 屏蔽安全验证设置Dao接口层
 * CancelValidate
 * @author shenggege
 * @date 2016年11月25号
 * @version 1.0.0
 * 
 */
public interface CancelValidateMapper {
    
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
	 * @Description: TODO(屏蔽安全验证设置数据增方法,非空值)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int insertSelective(CancelValidate cancelValidate);
	
	/**
	 * @Title: deleteById
	 * @Description: TODO(屏蔽安全验证设置根据ID 删除方法)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int deleteById(BigDecimal id);
	
	/**
	 * @Title: deleteById
	 * @Description: TODO(屏蔽安全验证设置根据用户 删除方法)
	 * @param @param cancelvalidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int deleteByBaseId(BigDecimal id);
	
	/**
	 * @Title: updateById
	 * @Description: TODO(屏蔽安全验证设置 更新方法)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	int updateById(CancelValidate cancelValidate);
	
	/**
	 * @Title: findCancelValidate
	 * @Description: TODO(屏蔽安全验证设置 查询全部)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
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