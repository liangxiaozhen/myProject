package com.ptpl.model;

import java.math.BigDecimal;

public class RoleResource {
    private BigDecimal id;

    private BigDecimal resourceId;

    private BigDecimal roleId;
    
    private String resourceIdStr;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigDecimal resourceId) {
        this.resourceId = resourceId;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }

	public String getResourceIdStr() {
		return resourceIdStr;
	}

	public void setResourceIdStr(String resourceIdStr) {
		this.resourceIdStr = resourceIdStr;
	}
    
    
}