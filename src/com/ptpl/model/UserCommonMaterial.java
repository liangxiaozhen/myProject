package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author LIUJ
 *         用户公共资料记录
 */
public class UserCommonMaterial {
    private BigDecimal id;//主键

    private BigDecimal baseid;//用户ID

    private String liqno;//引用资料编号

    private String materialname;//资料名称

    private String materialcontent;//资料类容

    private Date addtime;//操作时间

    private String remark;//备注

    private String materialpic;//资料图片

    //接收自填资料的值
    private List<UserCommonMaterial> commonneeds;

    //接收选择资料的值
    private List<UserCommonMaterial> commonpreset;

    //接收用户公共资料
    private List<UserCommonMaterial> UserCommonMaterials;

    public List<UserCommonMaterial> getUserCommonMaterials() {
        return UserCommonMaterials;
    }

    public void setUserCommonMaterials(List<UserCommonMaterial> userCommonMaterials) {
        UserCommonMaterials = userCommonMaterials;
    }

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

    public String getLiqno() {
        return liqno;
    }

    public void setLiqno(String liqno) {
        this.liqno = liqno == null ? null : liqno.trim();
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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
        this.materialpic = materialpic == null ? null : materialpic.trim();
    }

    public List<UserCommonMaterial> getCommonneeds() {
        return commonneeds;
    }

    public void setCommonneeds(List<UserCommonMaterial> commonneeds) {
        this.commonneeds = commonneeds;
    }

    public List<UserCommonMaterial> getCommonpreset() {
        return commonpreset;
    }

    public void setCommonpreset(List<UserCommonMaterial> commonpreset) {
        this.commonpreset = commonpreset;
    }
}