package com.ptpl.model.ui;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 图文内容展示对象 VO
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月22日 下午7:37:30
 *
 */
public class ImageTextSettingVO {
	/* ID */
	private BigDecimal id;
	/** 发布时间 */
	private String issuetime;
	/** 标题 */
	private String title;
	/** 标题图 */
	private String titlepic;
	/** 内容简述 */
	private String resume;
	/** 具体内容 */
	private String content;
	/** 超级链接 */
	private String hyperlink;
	/** 项目名称 */
	private String itiname;
	/** 副标题1 */
	private String subtitle1;
	/** 副标题2 */
	private String subtitle2;
	/** 副标题3 */
	private String subtitle3;
	/** 序号 */
	private Long serialno;

	public String getIssuetime() {
		return issuetime;
	}

	public void setIssuetime(String issuetime) {
		this.issuetime = issuetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlepic() {
		return titlepic;
	}

	public void setTitlepic(String titlepic) {
		this.titlepic = titlepic;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getItiname() {
		return itiname;
	}

	public void setItiname(String itiname) {
		this.itiname = itiname;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public String getSubtitle1() {
		return subtitle1;
	}

	public void setSubtitle1(String subtitle1) {
		this.subtitle1 = subtitle1;
	}

	public String getSubtitle2() {
		return subtitle2;
	}

	public void setSubtitle2(String subtitle2) {
		this.subtitle2 = subtitle2;
	}

	public String getSubtitle3() {
		return subtitle3;
	}

	public void setSubtitle3(String subtitle3) {
		this.subtitle3 = subtitle3;
	}

	public Long getSerialno() {
		return serialno;
	}

	public void setSerialno(Long serialno) {
		this.serialno = serialno;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
}
