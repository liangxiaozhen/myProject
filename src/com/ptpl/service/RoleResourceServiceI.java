package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.RoleResource;
import com.ptpl.model.RoleUser;
import com.ptpl.model.SystemResource;

public interface RoleResourceServiceI {
	int insert(RoleResource roleResource);

    int insertSelective(RoleResource roleResource);
    /**
     * 
    * @Title: deleteByRoleId 
    * @Description: TODO(根据角色ID删除角色资源) 
    * @param @param roleId
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int deleteByRoleId(BigDecimal roleId);
    
    /**
     * 
    * @Title: findResourcesByRoleId 
    * @Description: TODO(根据角色ID查找是否有角色资源Idd) 
    * @param @param roleId
    * @param @return  参数说明 
    * @return RoleResource    返回类型 
    * @author chenjiaming
    * @throws
     */
    RoleResource findResourcesByRoleId(BigDecimal roleId);
  
}
