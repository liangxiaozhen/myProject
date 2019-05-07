package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AwardPackageMapper;
import com.ptpl.model.AwardPackage;
import com.ptpl.service.AwardPackageServiceI;

public class AwardPackageServiceImpl extends BaseServiceImpl<AwardPackage>implements AwardPackageServiceI {
	@Autowired
	private AwardPackageMapper awardpackagemapper;
	
	@Override
	public List<AwardPackage> selectAwardPackage(String apno) {
		return awardpackagemapper.seletAwardPackage(apno);
	}

	@Override
	public List<AwardPackage> selectapnoAndapname(AwardPackage awardPackage) {
		return awardpackagemapper.selectapnoAndapname(awardPackage);
	}

	/**
	 * @author pxl
	 * @date 2016-11-25
	 * 根据礼品包的属性获得某个礼品包
	 */
	public AwardPackage getAwardPackage(AwardPackage awardPackage) {
		return awardpackagemapper.getAwardPackage(awardPackage);
	}

	/**
	 * 根据奖品包名称获取对应的奖品包，但不等于本身
	 */
	public AwardPackage gainAwardPackage(AwardPackage ap) {
		return awardpackagemapper.gainAwardPackage(ap);
	}

}
