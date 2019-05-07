package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @author 作者 xiaoy: 
 * @version 创建时间：2016年12月29日 下午3:29:54 
 *
 */
public class MessageTemplate {
	/*主键*/
    private BigDecimal id;
    /*短信类型 用字母*/
    private String name;
    /*短信模板内容*/
    private String content;
    /*添加时间*/
    private Date addtime;
    /*添加人*/
    private String addman;
    /*备注*/
    private String remark;
    /*短信方式 1文字2语音*/
    private String type;
    /*可用变量*/
    private String variable;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
}