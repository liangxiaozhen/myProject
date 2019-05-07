package com.ptpl.model;

import java.math.BigDecimal;
import java.util.List;
/**
 * 
* @ClassName: SystemRole 
* @Package com.ptpl.model 
* @Description: TODO(角色实体类) 
* @author chenjiaming
* @date 2016年6月27日 下午1:57:00 
* @version V1.0
 */
public class SystemRole{
	/*主键Id*/
    private BigDecimal id;
    /*角色名称*/
    private String rolename;
    /*角色资源实体 一对一关系*/
    private SystemResource systemResource;
    /*角色与用户的关系是 一个角色有一个或多个用户 */
    private List<AdminUser> adminusers;
    

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

	public List<AdminUser> getAdminusers() {
		return adminusers;
	}

	public void setAdminusers(List<AdminUser> adminusers) {
		this.adminusers = adminusers;
	}

	public SystemResource getSystemResource() {
		return systemResource;
	}

	public void setSystemResource(SystemResource systemResource) {
		this.systemResource = systemResource;
	}
    
    
}