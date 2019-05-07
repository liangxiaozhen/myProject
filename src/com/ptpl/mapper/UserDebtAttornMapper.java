package com.ptpl.mapper;

import com.ptpl.model.UserDebtAttorn;
import java.math.BigDecimal;
import java.util.List;

public interface UserDebtAttornMapper {
    /**
	 * 根据id查询债转设置对象
	* @Title: selectByPrimaryKey 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return  参数说明 
	* @return UserDebtAttorn    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	UserDebtAttorn selectByPrimaryKey(BigDecimal id);
	/**
	 * 删除债转设置对象
	* @Title: deleteByPrimaryKey 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	void deleteByPrimaryKey(BigDecimal id);
	/**
	 * c:if添加债转设置对象
	* @Title: insertSelective 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userDebtAttorn  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	void insertSelective(UserDebtAttorn userDebtAttorn);
	/**
	 * @return 
	 * c:if修改债转设置对象
	* @Title: updateByPrimaryKeySelective 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userDebtAttorn  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	int updateByPrimaryKeySelective(UserDebtAttorn userDebtAttorn);
	/**
	 * 三表联查
	* @Title: getAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userDebtAttorn
	* @param @return  参数说明 
	* @return List<UserDebtAttorn>    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	List<UserDebtAttorn> getAll();
	/**
	 * 查询用户债转设置表(包括导航栏)
	* @Title: getAllList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return  参数说明 
	* @return List<UserDebtAttorn>    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	List<UserDebtAttorn> getAllList(UserDebtAttorn ub);
	/**新增你我贷的页面查询条件
	 * 查询用户债转设置表
	 * @Title: getAllListCode 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return  参数说明 
	 * @return List<UserDebtAttorn>    返回类型 
	 * @author jiangxueyou
	 * @throws
	 */
	List<UserDebtAttorn> getAllListCode(UserDebtAttorn ub);
	/**
	 * 根据债转订单号查询用户债转设置对象
	* @Title: getdaordernoAndtenderid 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userdebtattorn
	* @param @return  参数说明 
	* @return UserDebtAttorn    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	UserDebtAttorn getdaorderno(String  daorderno);
	/**
	 * 连表查询数据
	* @Title: getCjZz 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return  参数说明 
	* @return List<UserDebtAttorn>    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	List<UserDebtAttorn> getCjZz(BigDecimal baseid);
	/**
	 * 根据订单号查询用户债转设置表记录
	 * @param @param torderno
	 * @param @return
	 * @return List<UserDebtAttorn>
	 * @author jiangxueyou
	 */
	List<UserDebtAttorn> selectByTorderNo(String torderno);
}