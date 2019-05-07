package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleResource;
import com.ptpl.model.SystemResource;

public interface SystemResourceMapper {
    int insert(SystemResource systemResource);

    int insertSelective(SystemResource systemResource);
    
    /**
     * 
    * @Title: findSystemResources 
    * @Description: TODO(查找全部角色资源) 
    * @param @return  参数说明 
    * @return List<SystemResource>    返回类型 
    * @author chenjiaming
    * @throws
     */
    List<SystemResource> findSystemResources();
    
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
    * @Title: deleteById 
    * @Description: TODO(删除方法) 
    * @param @param id
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int deleteById(BigDecimal id);
    
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
    * @Title: findSystemResources 
    * @Description: TODO(查找全部角色资源 根据用户ID) 
    * @param @return  参数说明 
    * @return List<SystemResource>    返回类型 
    * @author chenjiaming
    * @throws
     */
    List<SystemResource> getSystemResources(AdminUser adminUser);
      
 }