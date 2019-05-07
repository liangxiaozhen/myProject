package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.PromoRegInfo;
/**
 * 推广码注册用户记录(无根注册)ServiceI 接口
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年1月16日 下午4:08:49 
 *
 */
public interface PromoRegInfoServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(PromoRegInfo record);

	int insertSelective(PromoRegInfo record);

	PromoRegInfo selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(PromoRegInfo record);

	int updateByPrimaryKey(PromoRegInfo record);
	
	List<PromoRegInfo> selective(PromoRegInfo promoRegInfo);
}
