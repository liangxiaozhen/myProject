package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ImageTextSetting;

/**
 * 图文内容添加Mapper
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月10日 上午10:38:55
 *
 */
public interface ImageTextSettingMapper {
	int deleteByPrimaryKey(BigDecimal id);

	int insert(ImageTextSetting record);

	int insertSelective(ImageTextSetting record);

	ImageTextSetting selectByPrimaryKey(BigDecimal id);

	int updateByPrimaryKeySelective(ImageTextSetting record);

	int updateByPrimaryKey(ImageTextSetting record);
	/**
	 * 获取图文内容添加List
	 * @return
	 */
	List<ImageTextSetting> listImageTextSetting (ImageTextSetting record);
	
	/**
	 * 获取指定项目名称下排序最高的序号
	 * @param itino
	 * @return
	 */
	Long maxImageTextSettingBySerialNo(Integer itino);
}