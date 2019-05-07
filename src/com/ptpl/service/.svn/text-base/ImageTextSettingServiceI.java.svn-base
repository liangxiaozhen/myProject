package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ImageTextSetting;
import com.ptpl.model.ui.ImageTextSettingVO;

/**
 * 图文内容添加ServiceI
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月13日 上午11:51:32
 *
 */
public interface ImageTextSettingServiceI {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(ImageTextSetting record);

	int insertSelective(ImageTextSetting record);

	ImageTextSetting selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(ImageTextSetting record);

	int updateByContent(ImageTextSetting record);

	int updateByPrimaryKey(ImageTextSetting record);

	/**
	 * 获取图文内容添加List
	 * 
	 * @return
	 */
	List<ImageTextSetting> listImageTextSetting(ImageTextSetting record);

	/**
	 * 获取图文内容添加List
	 * 
	 * @return
	 */
	List<ImageTextSettingVO> listImageTextSettingForUI(ImageTextSetting record);

	/**
	 * 获取指定项目名称下排序最高的序号
	 * 
	 * @param itino
	 * @return
	 */
	Long maxImageTextSettingBySerialNo(Integer itino);

	/**
	 * 获取图文内容 VO LIST
	 * 
	 * @param itiname
	 *            图文项目名称
	 * @return
	 */
	// List<ImageTextSettingVO> ListImageTextSettingVO();
	/**
	 * 获取图文内容VO List
	 * 
	 * @param itiname
	 *            图文项目名称
	 * @return
	 */
	List<ImageTextSettingVO> getImageTextSettingVO(Integer itino, String title);

}
