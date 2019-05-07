package com.ptpl.mapper;

import java.util.List;

import com.ptpl.model.AwardPackage;

public interface AwardPackageMapper extends BaseMapper<AwardPackage>{
	/**
	 * @author lihs
	 * @param apno
	 * @System.out.println(以下方法均为手动颁奖所使用);
	 */
	//查询是否有当前的奖品，根据奖品编号来查找
	List<AwardPackage> seletAwardPackage(String apno );
	
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