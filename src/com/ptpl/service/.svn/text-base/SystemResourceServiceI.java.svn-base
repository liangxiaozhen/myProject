package com.ptpl.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleResource;
import com.ptpl.model.RoleUser;
import com.ptpl.model.SystemResource;

public interface SystemResourceServiceI {

	int insert(SystemResource systemResource);

    int insertSelective(SystemResource systemResource);
    
    /**
     * 
    * @Title: findSystemResources 
    * @Description: TODO(查找全部角色资源 菜单管理模块使用) 
    * @param @return  参数说明 
    * @return List<SystemResource>    返回类型 
    * @author chenjiaming
    * @throws
     */
    List<SystemResource> findSystemResources2();
    
    /**
     * 
    * @Title: findChildrenSystemResources 
    * @Description: TODO(查找全部角色资源) 
    * @param @param systemResource
    * @param @return  参数说明 
    * @return List<SystemResource>    返回类型 
    * @author chenjiaming
    * @throws
     */
    List<SystemResource> findChildrenSystemResources(BigDecimal id);
    
    /**
     * 
    * @Title: findSystemResources 
    * @Description: TODO(查找全部角色资源，返回到前台tm_tree 数据类型 角色分配权限使用) 
    * @param @return  参数说明 
    * @return List<SystemResource>    返回类型 
    * @author chenjiaming
    * @throws
     */
    HashMap<String,Object> findSystemResources();
    
    /**
     * 
    * @Title: updateById 
    * @Description: TODO(更新方法) 
    * @param @param systemResource
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int updateById(SystemResource systemResource);
    
    /**
     * 
    * @Title: deleteByFathernumber 
    * @Description: TODO(根据父级id 删除) 
    * @param @param Fathernumber
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author   cjm  
    * @throws
     */
    int deleteByFathernumber(String Fathernumber);
    /**
     * 
    * @Title: deleteById 
    * @Description: TODO(删除方法 根据主键ID删除) 
    * @param @param id
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int deleteById(BigDecimal id);
    
    
 
    List<HashMap<String, Object>> findIndexSystemResources(HttpServletRequest request,AdminUser adminUser);
   }
