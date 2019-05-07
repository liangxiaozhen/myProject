package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ImageTextItemMapper;
import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ui.ImageTextItemVO;
import com.ptpl.service.ImageTextItemServiceI;
/**
 * 图文设置ServiceImpl
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年2月10日 上午11:10:19 
 *
 */
public class ImageTextItemServiceImpl implements ImageTextItemServiceI {
	
	@Autowired
	ImageTextItemMapper mapper;
	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ImageTextItem record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(ImageTextItem record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public ImageTextItem selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ImageTextItem record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ImageTextItem record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ImageTextItem> listImageTextItem(ImageTextItem imageTextItem) {
		// TODO Auto-generated method stub
		return mapper.listImageTextItem(imageTextItem);
	}

	@Override
	public List<ImageTextItemVO> listImageTextItemVO() {
		/*
		 * 设置可用状态
		 */
		ImageTextItem item=new ImageTextItem();
		item.setIsuse((short)1);
		/*
		 * 查询并封装展示对象
		 */
		List<ImageTextItem> list=listImageTextItem(item);
		List<ImageTextItemVO> voList=new ArrayList<ImageTextItemVO>(list.size());
		if(list.size()>0){
			for(ImageTextItem image:list){
				/*
				 *创建展示对象，设置备注和图文项目名称 
				 */
				ImageTextItemVO vo=new ImageTextItemVO();
				vo.setItiname(image.getItiname());
				vo.setRemark(image.getRemark());
				voList.add(vo);
			}
		}
		return voList;
	}

}
