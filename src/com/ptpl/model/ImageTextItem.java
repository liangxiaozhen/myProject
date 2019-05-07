package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 *  图文项目设置实体类
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年2月10日 上午10:33:12 
 *
 */
public class ImageTextItem {
	/**ID*/
    private BigDecimal id;
    /**图文项目编号*/
    private Integer itino;
    /**图文项目名称*/
    private String itiname;
    /**是否可用*/
    private Short isuse;
    /**添加时间*/
    private Date addtime;
    /**添加人*/
    private String addman;
    /**备注*/
    private String remark;
    /*排列顺序  0 升序 1  降序*/
    private Short sort;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getItino() {
        return itino;
    }

    public void setItino(Integer itino) {
        this.itino = itino;
    }

    public String getItiname() {
        return itiname;
    }

    public void setItiname(String itiname) {
        this.itiname = itiname == null ? null : itiname.trim();
    }

    public Short getIsuse() {
        return isuse;
    }

    public void setIsuse(Short isuse) {
        this.isuse = isuse;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddman() {
        return addman;
    }

    public void setAddman(String addman) {
        this.addman = addman == null ? null : addman.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}
}