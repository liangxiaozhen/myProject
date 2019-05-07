package com.ptpl.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 体验会员等级设置
 * 
 * @author xiaoy
 *
 * @date 2016年11月26日 上午10:51:49
 */
public class UserGradeExp {
	/** ID */
	private BigDecimal id;
	/** 等级序号 */
	private Short ugrade;
	/** 会员等级 */
	private UserGrade userGrade = new UserGrade();
	/** 购买金额 */
	private Double amount;
	/** 有效期 （天） */
	private Integer expirytime;
	/** 设置人 */
	private String addman;
	/** 设置时间 */
	private Date addtime;
	/** 备注 */
	private String remark;
	/** 状态* 0.停用 1.启用 */
	private Short status;
	/** 人数 */
	private int number;
	/**体验会员定向名单列表ID*/
	private BigDecimal expsnlid;
	/** 是否体验会员定向 1.定向,2.不定向 */
	private Short isexpspecific;
	public BigDecimal getId()
	{
		return id;
	}

	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	public Short getUgrade()
	{
		return ugrade;
	}

	public void setUgrade(Short ugrade)
	{
		this.ugrade = ugrade;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public Integer getExpirytime()
	{
		return expirytime;
	}

	public void setExpirytime(Integer expirytime)
	{
		this.expirytime = expirytime;
	}

	public String getAddman()
	{
		return addman;
	}

	public void setAddman(String addman)
	{
		this.addman = addman == null ? null : addman.trim();
	}

	public Date getAddtime()
	{
		return addtime;
	}

	public void setAddtime(Date addtime)
	{
		this.addtime = addtime;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark == null ? null : remark.trim();
	}

	public UserGrade getUserGrade()
	{
		return userGrade;
	}

	public void setUserGrade(UserGrade userGrade)
	{
		this.userGrade = userGrade;
	}

	public Short getStatus()
	{
		return status;
	}

	public void setStatus(Short status)
	{
		this.status = status;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public BigDecimal getExpsnlid() {
		return expsnlid;
	}

	public void setExpsnlid(BigDecimal expsnlid) {
		this.expsnlid = expsnlid;
	}

	public Short getIsexpspecific() {
		return isexpspecific;
	}

	public void setIsexpspecific(Short isexpspecific) {
		this.isexpspecific = isexpspecific;
	}
}