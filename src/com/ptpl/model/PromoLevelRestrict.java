package com.ptpl.model;

import java.math.BigDecimal;

public class PromoLevelRestrict {
	//ID
    private BigDecimal id;
    //推广层级
    private Short promolevel;
    //推广层级人数限制
    private Long levellimitnum;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Short getPromolevel() {
        return promolevel;
    }

    public void setPromolevel(Short promolevel) {
        this.promolevel = promolevel;
    }

    public Long getLevellimitnum() {
        return levellimitnum;
    }

    public void setLevellimitnum(Long levellimitnum) {
        this.levellimitnum = levellimitnum;
    }
}