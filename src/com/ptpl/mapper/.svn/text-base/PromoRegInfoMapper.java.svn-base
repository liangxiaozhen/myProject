package com.ptpl.mapper;

import com.ptpl.model.PromoRegInfo;
import java.math.BigDecimal;
import java.util.List;

/**
 * 推广码注册用户记录(无根注册)Mapper
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年1月16日 下午3:41:03
 *
 */
public interface PromoRegInfoMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(PromoRegInfo record);

	int insertSelective(PromoRegInfo record);

	PromoRegInfo selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(PromoRegInfo record);

	int updateByPrimaryKey(PromoRegInfo record);
	
	List<PromoRegInfo> selective(PromoRegInfo promoRegInfo);
}