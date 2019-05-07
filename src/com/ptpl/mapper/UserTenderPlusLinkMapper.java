package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserTenderPlusLink;

/**
 * 
 * 投标增益使用关联Dao接口层
 * UserTenderPlusLinkMapper
 * 创建人:cjm
 * 时间：2016年11月09日 11:46:04
 * @version 1.0.0
 *
 */
public interface UserTenderPlusLinkMapper {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(投标增益使用关联数据增加方法) 
	* @param @param userTenderPlusLink
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(UserTenderPlusLink userTenderPlusLink);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(投标增益使用关联数据增加方法，非空值) 
    * @param @param userTenderPlusLink
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(UserTenderPlusLink userTenderPlusLink);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(投标增益使用关联根据Id 删除方法) 
     * @param @param userTenderPlusLink
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(投标增益使用关联 更新方法) 
     * @param @param userTenderPlusLink
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(UserTenderPlusLink userTenderPlusLink); 
     /**
     * 
     * @Title: findUserTenderPlusLinks
     * @Description: TODO(投标增益使用关联 查询全部) 
     * @param @param userTenderPlusLink
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<UserTenderPlusLink> findUserTenderPlusLinks(UserTenderPlusLink userTenderPlusLink);
    
     /**
     * 
    * @Title: findUserTenderPlusLinkById 
    * @Description: TODO(根据Id查询对应的投标增益使用关联 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return WCCAwardRule    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserTenderPlusLink findUserTenderPlusLinkById(BigDecimal id);
    
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
    List<UserTenderPlusLink> findUserTenderPlusLinkByUtId(BigDecimal utid);
    
    /**
     * 根据标ID和baseid查找使用增益记录
     * @Title: selectByTidAndBaseid
     * @param userTenderPlusLink
     * @return List<UserTenderPlusLink>    返回类型
     */
    List<UserTenderPlusLink> selectByTidAndBaseid(UserTenderPlusLink userTenderPlusLink);
}
