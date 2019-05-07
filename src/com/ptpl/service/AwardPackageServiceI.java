package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.Award;
import com.ptpl.model.AwardPackage;
import com.ptpl.model.TenderItem;

public interface AwardPackageServiceI extends BaseService<AwardPackage>{
	/**
	 * @author lihs
	 * @param apno
	 * @System.out.println(以下方法均为手动颁奖js验证);
	 */
	//查询当前奖品编号是否存在
	List<AwardPackage> selectAwardPackage(String apno);
	
	//查询奖品名与奖品编号是否符合
	List<AwardPackage> selectapnoAndapname(AwardPackage awardpackage);
	
	/**
	 * @author pxl
	 * @date 2016-11-25
	 * 根据礼品包的属性获得某个礼品包
	 */
	AwardPackage getAwardPackage(AwardPackage awardPackage);
	
	/**
	 * @param ap
	 * @return
	 * 根据奖品包名称获取对应的奖品包，但不等于本身
	 */
	AwardPackage gainAwardPackage(AwardPackage ap);
	
}
