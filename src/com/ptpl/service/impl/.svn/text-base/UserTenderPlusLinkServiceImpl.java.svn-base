package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.UserTenderPlusLinkMapper;
 import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.service.UserTenderPlusLinkServiceI;
/**
 * 
 * 投标增益使用关联业务层
 * UserTenderPlusLinkServiceI
 * 创建人:cjm
 * 时间：2016年11月09日 11:46:04
 * @version 1.0.0
 *
 */
public class UserTenderPlusLinkServiceImpl implements UserTenderPlusLinkServiceI{
	
	@Autowired
	private UserTenderPlusLinkMapper userTenderPlusLinkMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(投标增益使用关联数据增加方法) 
	* @param @param userTenderPlusLink
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(UserTenderPlusLink userTenderPlusLink) {
 		return userTenderPlusLinkMapper.insert(userTenderPlusLink);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(投标增益使用关联数据增加方法，非空值) 
    * @param @param userTenderPlusLink
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(UserTenderPlusLink userTenderPlusLink) {
 		return userTenderPlusLinkMapper.insertSelective(userTenderPlusLink);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(投标增益使用关联根据Id 删除方法) 
     * @param @param userTenderPlusLink
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return userTenderPlusLinkMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(投标增益使用关联更新方法) 
     * @param @param userTenderPlusLink
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(UserTenderPlusLink userTenderPlusLink) {
 		return userTenderPlusLinkMapper.updateById(userTenderPlusLink);
	}
	 /**
     * 
     * @Title: findUserTenderPlusLinks
     * @Description: TODO(投标增益使用关联查询全部) 
     * @param @param userTenderPlusLink
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<UserTenderPlusLink> findUserTenderPlusLinks(UserTenderPlusLink userTenderPlusLink) {
 		return userTenderPlusLinkMapper.findUserTenderPlusLinks(userTenderPlusLink);
	}
  	/**
     * 
    * @Title: findUserTenderPlusLinkById
    * @Description: TODO(根据Id查询对应的投标增益使用关联信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return UserTenderPlusLink    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public UserTenderPlusLink findUserTenderPlusLinkById(BigDecimal id) {
 		return userTenderPlusLinkMapper.findUserTenderPlusLinkById(id);
	}
	/**
     * 
    * @Title: findUserTenderPlusLinkByUtId 
    * @Description: TODO(根据标的ID 查询增益记录) 
    * @param @param utid
    * @param @return  参数说明 
    * @return List<UserTenderPlusLink>    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public List<UserTenderPlusLink> findUserTenderPlusLinkByUtId(BigDecimal utid) {
 		return userTenderPlusLinkMapper.findUserTenderPlusLinkByUtId(utid);
	}
	
	@Override
	public List<UserTenderPlusLink> selectByTidAndBaseid(UserTenderPlusLink userTenderPlusLink) {
		return userTenderPlusLinkMapper.selectByTidAndBaseid(userTenderPlusLink);
	}
}
