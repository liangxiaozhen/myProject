package com.ptpl.model;

import java.math.BigDecimal;

public class RelntResCode {
    private BigDecimal id;

    private String ecrespCode;

    private String secrespDesc;

    private String company;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEcrespCode() {
        return ecrespCode;
    }

    public void setEcrespCode(String ecrespCode) {
        this.ecrespCode = ecrespCode == null ? null : ecrespCode.trim();
    }

    public String getSecrespDesc() {
        return secrespDesc;
    }

    public void setSecrespDesc(String secrespDesc) {
        this.secrespDesc = secrespDesc == null ? null : secrespDesc.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}