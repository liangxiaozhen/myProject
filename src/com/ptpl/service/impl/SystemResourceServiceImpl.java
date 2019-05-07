package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.SystemResourceMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleResource;
import com.ptpl.model.RoleUser;
import com.ptpl.model.SystemResource;
import com.ptpl.service.SystemResourceServiceI;

public class SystemResourceServiceImpl implements SystemResourceServiceI{
	@Autowired
	private SystemResourceMapper systemResourceMapper;
	
	@Override
	public int insert(SystemResource systemResource) {
 		return systemResourceMapper.insert(systemResource);
	}

	@Override
	public int insertSelective(SystemResource systemResource) {
 		return systemResourceMapper.insertSelective(systemResource);
	}
 
	TreeMap<String,List<HashMap<String,Object>>> childrenMap = null;
	/**
	 * 
	* @Title: getChildrenSystemResourcesById 
	* @Description: TODO(获取全部根角色资源 tm_tree 类型) 
	* @param @param id  参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
 	@Override
	public HashMap<String, Object> findSystemResources() {
		//返回类型
		HashMap<String,Object> hashMaps = new HashMap<String,Object>();
		//装全部子类角色资源
		childrenMap = new TreeMap<String,List<HashMap<String,Object>>>();
		//查找全部角色资源
		List<SystemResource> systemResources = systemResourceMapper.findSystemResources();
 		//判断角色资源是否为空
		if(systemResources !=null && systemResources.size() > 0){
			List<HashMap<String,Object>> lists = new ArrayList<HashMap<String,Object>>();
 			for(SystemResource SystemResource : systemResources){
				HashMap<String,Object> maps = new HashMap<String,Object>();
				//角色资源菜单名称
				maps.put("name",SystemResource.getMenuname());
				//角色资源url
				maps.put("url",SystemResource.getResourceurl());
				//角色资源Id
				maps.put("opid",SystemResource.getId());
				maps.put("pid",SystemResource.getId());
				lists.add(maps);
				getChildrenSystemResourcesById(SystemResource.getId());
  			}
 			hashMaps.put("root", lists);
 			hashMaps.put("children", childrenMap);
 			return hashMaps;
		}else{
 			return null;
 		}
	}

	/**
	 * 
	* @Title: getChildrenSystemResourcesById 
	* @Description: TODO(根据角色资源父类id获取对应的子类角色资源) 
	* @param @param id  参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 private void getChildrenSystemResourcesById(BigDecimal id){
		List<SystemResource> childrenSystemResources = systemResourceMapper.findChildrenSystemResources(id);
		if(childrenSystemResources !=null && childrenSystemResources.size() > 0){
			List<HashMap<String,Object>> lists = new ArrayList<HashMap<String,Object>>();
			for(SystemResource childrenSystemResource : childrenSystemResources){
				HashMap<String,Object> maps = new HashMap<String,Object>();
				//角色资源菜单名称
				maps.put("name",childrenSystemResource.getMenuname());
				//角色资源url
				maps.put("url",childrenSystemResource.getResourceurl());
				//角色资源Id
				maps.put("opid",childrenSystemResource.getId());
				maps.put("pid",childrenSystemResource.getId());
				lists.add(maps);
				//获取角色资源子类的子类资源
				getChildrenSystemResourcesById(childrenSystemResource.getId());
 			}
			childrenMap.put(id+"", lists);
		}
 	 }
 
	@Override
	public List<SystemResource> findSystemResources2() {
 		return systemResourceMapper.findSystemResources();
	}

	@Override
	public List<SystemResource> findChildrenSystemResources(BigDecimal id) {
 		return systemResourceMapper.findChildrenSystemResources(id);
	}

	@Override
	public int deleteById(BigDecimal id) {
 		return systemResourceMapper.deleteById(id);
	}

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
	public int deleteByFathernumber(String Fathernumber){
		return systemResourceMapper.deleteByFathernumber(Fathernumber);
    }
	@Override
	public int updateById(SystemResource systemResource) {
 		return systemResourceMapper.updateById(systemResource);
	}

	/**
	 * 
	* @Title: findIndexSystemResources 
	* @Description: TODO(查询用户可见菜单 用户可见菜单) 
	* @param @param id  参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@Override
	public List<HashMap<String, Object>> findIndexSystemResources(HttpServletRequest request,AdminUser adminUser) {
		String path = request.getContextPath();
 		String basePath;
 		int prot = request.getServerPort();
 		if(prot == 80){
	 		 basePath = request.getScheme() + "://"+ request.getServerName()+ path;
 		}else{
 			 basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path;
 		}
 		//查找全部角色资源
		List<SystemResource> systemResources = systemResourceMapper.getSystemResources(adminUser);
  		//判断角色资源是否为空
		if(systemResources !=null && systemResources.size() > 0){
			List<HashMap<String,Object>> lists = new ArrayList<HashMap<String,Object>>();
 			for(SystemResource systemResource : systemResources){
				HashMap<String,Object> maps = new HashMap<String,Object>();
				//角色资源菜单名称
				maps.put("text",systemResource.getMenuname());
				maps.put("collapsed", true);//默认二级导航收缩
				List<SystemResource> childrenSystemResources = systemResourceMapper.findChildrenSystemResources(systemResource.getId());
				if(childrenSystemResources !=null && childrenSystemResources.size() > 0){
					List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
					for(SystemResource childrenSystemResource : childrenSystemResources){
						HashMap<String,Object> map = new HashMap<String,Object>();
						//角色资源Id
						map.put("id",childrenSystemResource.getId()+"");
						//子角色资源菜单名称
						map.put("text",childrenSystemResource.getMenuname());
						//角色资源url
						map.put("href",basePath+childrenSystemResource.getResourceurl());
						list.add(map);
  		 			}
					maps.put("items", list);
 				}
 			  lists.add(maps);
     		}
  			return lists;
 		}else{
 			return null;
 		}
	}
  
  }
