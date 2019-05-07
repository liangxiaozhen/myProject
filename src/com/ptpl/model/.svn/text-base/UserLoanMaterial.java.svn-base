package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author liuj
 * 用户借款审核资料记录
 */
public class UserLoanMaterial {
    private BigDecimal id;//主键

    private BigDecimal baseid;//用户ID

    private String loanno;//借款编号

    private Short materialtype;//资料类型(1公共，2补充)

    private String materialname;//资料名称

    private String materialcontent;//资料内容

    private String remark;//备注

    private String materialpic;//资料图片

    private Date addtime;//提交时间

    private Short auditstatus;//审核状态(0.未审核1.审核通过)

    private String auditman;//审核人

    private Date audittime;//审核时间

    private String linno;//资料编号

    private List<UserLoanMaterial> infoNeeds;//获取自填类资料

    private List<UserLoanMaterial> infoPresets;//获取选择类风险

    private UserBaseAccountInfo accountInfo;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getBaseid() {
        return baseid;
    }

    public void setBaseid(BigDecimal baseid) {
        this.baseid = baseid;
    }

    public String getLoanno() {
        return loanno;
    }

    public void setLoanno(String loanno) {
        this.loanno = loanno == null ? null : loanno.trim();
    }

    public Short getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(Short materialtype) {
        this.materialtype = materialtype;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname == null ? null : materialname.trim();
    }

    public String getMaterialcontent() {
        return materialcontent;
    }

    public void setMaterialcontent(String materialcontent) {
        this.materialcontent = materialcontent == null ? null : materialcontent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getMaterialpic() {
		return materialpic;
	}

	public void setMaterialpic(String materialpic) {
		this.materialpic = materialpic;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Short getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(Short auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getAuditman() {
		return auditman;
	}

	public void setAuditman(String auditman) {
		this.auditman = auditman;
	}

	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}

	public List<UserLoanMaterial> getInfoNeeds() {
		return infoNeeds;
	}

	public void setInfoNeeds(List<UserLoanMaterial> infoNeeds) {
		this.infoNeeds = infoNeeds;
	}

	public List<UserLoanMaterial> getInfoPresets() {
		return infoPresets;
	}

	public void setInfoPresets(List<UserLoanMaterial> infoPresets) {
		this.infoPresets = infoPresets;
	}

	public UserBaseAccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(UserBaseAccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getLinno() {
		return linno;
	}

	public void setLinno(String linno) {
		this.linno = linno;
	}
}