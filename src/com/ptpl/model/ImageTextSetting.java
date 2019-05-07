package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 图文内容添加实体类
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月10日 上午10:36:10
 *
 */
public class ImageTextSetting {
	/** ID */
	private BigDecimal id;
	/** 排序号 */
	private Long serialno;
	/** 图文项目编号 */
	private Integer itino;
	/** 发布时间 */
	private Date issuetime;
	/** 标题 */
	private String title;
	/** 副标题1 */
	private String subtitle1;
	/** 副标题2 */
	private String subtitle2;
	/** 副标题3 */
	private String subtitle3;
	/** 标题图 */
	private String titlepic;
	/** 内容简述 */
	private String resume;
	/** 具体内容 */
	private String content;
	/** 发布人 */
	private String operator;
	/** 备注 */
	private String remark;
	/** 超链接 */
	private String hyperlink;
	/** 启用 0 不启用 1 启用 */
	private Short isuse;
	/*PC端  0 不启用 1 启用*/ 
	private Short pcterminal;
	/*Android端  0 不启用 1 启用*/
	private Short androidterminal;
	/*IOS端  0 不启用 1 启用*/
	private Short iosterminal;
	/*WAP端  0 不启用 1 启用*/
	private Short wapterminal;
	/** 图文项目 */
	private ImageTextItem imageTextItem =new ImageTextItem();

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Long getSerialno() {
		return serialno;
	}

	public void setSerialno(Long serialno) {
		this.serialno = serialno;
	}

	public Integer getItino() {
		return itino;
	}

	public void setItino(Integer itino) {
		this.itino = itino;
	}

	public Date getIssuetime() {
		return issuetime;
	}

	public void setIssuetime(Date issuetime) {
		this.issuetime = issuetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getSubtitle1() {
		return subtitle1;
	}

	public void setSubtitle1(String subtitle1) {
		this.subtitle1 = subtitle1 == null ? null : subtitle1.trim();
	}

	public String getSubtitle2() {
		return subtitle2;
	}

	public void setSubtitle2(String subtitle2) {
		this.subtitle2 = subtitle2 == null ? null : subtitle2.trim();
	}

	public String getSubtitle3() {
		return subtitle3;
	}

	public void setSubtitle3(String subtitle3) {
		this.subtitle3 = subtitle3 == null ? null : subtitle3.trim();
	}

	public String getTitlepic() {
		return titlepic;
	}

	public void setTitlepic(String titlepic) {
		this.titlepic = titlepic == null ? null : titlepic.trim();
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume == null ? null : resume.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public Short getIsuse() {
		return isuse;
	}

	public void setIsuse(Short isuse) {
		this.isuse = isuse;
	}

	public ImageTextItem getImageTextItem() {
		return imageTextItem;
	}

	public void setImageTextItem(ImageTextItem imageTextItem) {
		this.imageTextItem = imageTextItem;
	}

	public Short getPcterminal() {
		return pcterminal;
	}

	public Short getAndroidterminal() {
		return androidterminal;
	}

	public Short getIosterminal() {
		return iosterminal;
	}

	public Short getWapterminal() {
		return wapterminal;
	}

	public void setPcterminal(Short pcterminal) {
		this.pcterminal = pcterminal;
	}

	public void setAndroidterminal(Short androidterminal) {
		this.androidterminal = androidterminal;
	}

	public void setIosterminal(Short iosterminal) {
		this.iosterminal = iosterminal;
	}

	public void setWapterminal(Short wapterminal) {
		this.wapterminal = wapterminal;
	}
	
}