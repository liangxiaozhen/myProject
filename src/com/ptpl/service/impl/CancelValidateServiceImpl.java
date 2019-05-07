package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.CancelValidateMapper;
import com.ptpl.mapper.UserBaseAccountInfoMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.model.CancelValidate;
import com.ptpl.service.CancelValidateServiceI;

/**
 * 屏蔽安全验证设置业务层
 * CancelValidate
 * @date 2016年11月25日
 * @author shenggege
 * @version 1.0.0
 *
 */
public class CancelValidateServiceImpl implements CancelValidateServiceI{
	
	@Autowired
	private CancelValidateMapper cancelValidateMapper;
	
	/**
	 * @Title: insert
	 * @Description: TODO(屏蔽安全验证设置数据增加方法)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	@Override
	public int insert(CancelValidate cancelValidate) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.insert(cancelValidate);
	}
	
	/**
	 * @Title: insertSelective
	 * @Description: TODO(屏蔽安全验证设置数据增加方法,非空值)
	 * @param @param CancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	@Override
	public int insertSelective(CancelValidate cancelValidate) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.insertSelective(cancelValidate);
	}

	/**
	 * @Title: getByUsername
	 * @Description: TODO(屏蔽安全验证设置根据用户名查找用户id)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	@Override
	public CancelValidate getByUsername(String username){
		// TODO Auto-generated method stub
		return cancelValidateMapper.getByUsername(username);
	}
	
	/**
	 * @Title: deleteById
	 * @Description: TODO(屏蔽安全验证设置根据id 删除方法)
	 * @param @param cancelValidate
	 * @param @return 参数说明
	 * @return int 返回类型
	 * @author shenggege
	 * @throws
	 */
	@Override
	public int deleteById(BigDecimal id) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.deleteById(id);
	}

	/**
     * 
     * @Title: deleteByBaseId 
     * @Description: TODO(屏蔽安全验证设置根据用户Id 删除方法) 
     * @param @param cancelValidate
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author shenggege
     * @throws
     */
	@Override
	public int deleteByBaseId(BigDecimal id) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.deleteByBaseId(id);
	}

	/**
     * 
     * @Title: updateById 
     * @Description: TODO(屏蔽安全验证设置更新方法) 
     * @param @param cancelValidate
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author sheng
     * @throws
     */
	@Override
	public int updateById(CancelValidate cancelValidate) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.updateById(cancelValidate);
	}

	 /**
     * 
     * @Title: findCancelValidate
     * @Description: TODO(屏蔽安全验证查询全部) 
     * @param @param cancelValidate
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author sheng
     * @throws
     */
	@Override
	public List<CancelValidate> findCancelValidates(CancelValidate cancelValidate) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.findCancelValidates(cancelValidate);
	}

	@Override
	public CancelValidate findCancelValidateById(BigDecimal id) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.findCancelValidateById(id);
	}

	@Override
	public List<CancelValidate> selectCancelValidateByCondition(CancelValidate cancelValidate) {
		// TODO Auto-generated method stub
		return cancelValidateMapper.selectCancelValidateByCondition(cancelValidate);
	}

	
}
