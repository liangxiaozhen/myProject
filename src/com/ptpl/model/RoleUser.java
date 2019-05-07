package com.ptpl.model;
 
import java.math.BigDecimal;
/**
 * 
* @ClassName: RoleUser 
* @Package com.ptpl.model 
* @Description:用户角色表实体类 
* @author chenjiaming
* @date 2016年6月27日 上午10:13:13 
* @version V1.0
 */
public class RoleUser {
	/**主键Id**/
    private BigDecimal id;
    /**用户ID**/
    private BigDecimal userId;
    /**角色ID**/
     private BigDecimal roleId;

    public BigDecimal getId() {
        return id;
    }

     
    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getRoleId() {
        return roleId;
    }

    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
}