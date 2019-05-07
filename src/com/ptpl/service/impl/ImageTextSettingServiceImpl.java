package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.StringUtil;
import com.ptpl.mapper.ImageTextItemMapper;
import com.ptpl.mapper.ImageTextSettingMapper;
import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ImageTextSetting;
import com.ptpl.model.ui.ImageTextSettingVO;
import com.ptpl.service.ImageTextItemServiceI;
import com.ptpl.service.ImageTextSettingServiceI;

/**
 * 图文内容添加ServiceImpl
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月13日 上午11:52:54
 *
 */
public class ImageTextSettingServiceImpl implements ImageTextSettingServiceI {

	@Autowired
	private ImageTextSettingMapper mapper;
	@Autowired
	private ImageTextItemServiceI imageTextItemService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		ImageTextSetting image = mapper.selectByPrimaryKey(id);
		int serialNo = image.getSerialno().intValue();
		int itino = image.getItino();
		ImageTextSetting select = new ImageTextSetting();
		select.setItino(itino);
		List<ImageTextSetting> list = mapper.listImageTextSetting(select);
		for (ImageTextSetting setting : list) {
			int no = setting.getSerialno().intValue();
			if (no > serialNo) {
				setting.setSerialno(no - 1L);
				mapper.updateByPrimaryKeySelective(setting);
			}
		}
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ImageTextSetting record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(ImageTextSetting record) {
		/*
		 * 序号为空时，查找同一个项目名称下序号最大的 +1
		 */
		int itino = record.getItino();
		if (!(record.getSerialno() != null)) {
			Long serialNo = mapper.maxImageTextSettingBySerialNo(itino);
			if (serialNo != null) {
				record.setSerialno(serialNo + 1);
			} else {
				record.setSerialno(1L);
			}
		} else {
			/*
			 * 序号不为空时，按插入的序号进行重新排列
			 */
			Long serialNo = record.getSerialno();
			ImageTextSetting selectByITINo = new ImageTextSetting();
			selectByITINo.setItino(itino);
			List<ImageTextSetting> list = mapper.listImageTextSetting(selectByITINo);
			for (ImageTextSetting setting : list) {
				Long no = setting.getSerialno();
				if (no.equals(serialNo)) {
					serialNo += 1;
					setting.setSerialno(serialNo);
					mapper.updateByPrimaryKeySelective(setting);
				}
			}
		}
		return mapper.insertSelective(record);
	}

	@Override
	public ImageTextSetting selectByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ImageTextSetting record) {
		Long no = mapper.selectByPrimaryKey(record.getId()).getSerialno();
		Long serialNo = record.getSerialno();
		int itino = record.getItino();
		if (!serialNo.equals(no)) {
			ImageTextSetting selectByITINo = new ImageTextSetting();
			selectByITINo.setItino(itino);
			List<ImageTextSetting> list = mapper.listImageTextSetting(selectByITINo);
			// 序号减小
			if (serialNo.intValue() < no.intValue()) {
				for (ImageTextSetting setting : list) {
					if (!setting.getId().equals(record.getId())) {
						if (serialNo.equals(setting.getSerialno())) {
							serialNo += 1;
							setting.setSerialno(serialNo);
							mapper.updateByPrimaryKeySelective(setting);
						}
					}
				}
			}
			// 序号增大
			if (serialNo.intValue() > no.intValue()) {
				for (ImageTextSetting setting : list) {
					if (!setting.getId().equals(record.getId())) {
						if (setting.getSerialno() > no && setting.getSerialno() <= serialNo) {
							setting.setSerialno(setting.getSerialno() - 1);
							mapper.updateByPrimaryKeySelective(setting);
						}
					}
				}
			}
		}
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ImageTextSetting record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ImageTextSetting> listImageTextSetting(ImageTextSetting record) {
		// TODO Auto-generated method stub
		return mapper.listImageTextSetting(record);
	}

	@Override
	public Long maxImageTextSettingBySerialNo(Integer itino) {
		// TODO Auto-generated method stub
		return mapper.maxImageTextSettingBySerialNo(itino);
	}

	// @Override
	// public List<ImageTextSettingVO> ListImageTextSettingVO() {
	// /*
	// * 设置可用状态
	// */
	// ImageTextSetting setting = new ImageTextSetting();
	// setting.setIsuse((short) 1);
	// return getImageTextSettingVO(setting);
	// }
	//
	/**
	 * 查询并封装展示对象
	 */
	@Override
	public List<ImageTextSettingVO> getImageTextSettingVO(Integer itino, String title) {
		/*
		 * 设置可用状态
		 */
		ImageTextItem item = imageTextItemService.selectByPrimaryKey(new BigDecimal(itino));
		ImageTextSetting setting = new ImageTextSetting();
		setting.setTitle(title);
		setting.setItino(itino);
		setting.setIsuse((short) 1);
		setting.getImageTextItem().setSort(item.getSort());
		setting.getImageTextItem().setIsuse((short) 1);
		setting.setPcterminal((short) 1);
		// 查询数据
		List<ImageTextSetting> list = listImageTextSetting(setting);
		List<ImageTextSettingVO> voList = new ArrayList<ImageTextSettingVO>(list.size());
		if (list.size() > 0) {
			if (StringUtil.isNotEmpty(title)) {
				for (ImageTextSetting image : list) {
					ImageTextSettingVO vo = new ImageTextSettingVO();
					// 标题
					vo.setTitle(image.getTitle());
					// 内容
					vo.setContent(image.getContent());
					// 简介
					vo.setResume(image.getResume());
					// 发布时间
					vo.setIssuetime(sdf.format(image.getIssuetime()));
					// 副标题1
					vo.setSubtitle1(image.getSubtitle1());
					// 副标题2
					vo.setSubtitle2(image.getSubtitle2());
					// 副标题3
					vo.setSubtitle3(image.getSubtitle3());
					voList.add(vo);
				}
			} else {
				for (ImageTextSetting image : list) {
					ImageTextSettingVO vo = new ImageTextSettingVO();
					// 标题
					vo.setTitle(image.getTitle());
					// 发布时间
					vo.setIssuetime(sdf.format(image.getIssuetime()));
					// 超链接
					vo.setHyperlink(image.getHyperlink());
					// 标题图
					vo.setTitlepic(image.getTitlepic());
					// 简介
					vo.setResume(image.getResume());
					// 副标题1
					vo.setSubtitle1(image.getSubtitle1());
					// 副标题2
					vo.setSubtitle2(image.getSubtitle2());
					// 副标题3
					vo.setSubtitle3(image.getSubtitle3());
					voList.add(vo);
				}
			}
		}
		return voList;
	}

	@Override
	public int updateByContent(ImageTextSetting record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ImageTextSettingVO> listImageTextSettingForUI(ImageTextSetting record) {
		List<ImageTextSetting> list = mapper.listImageTextSetting(record);
		List<ImageTextSettingVO> voList = new ArrayList<ImageTextSettingVO>(list.size());
		for (ImageTextSetting image : list) {
			ImageTextSettingVO vo = new ImageTextSettingVO();
			// ID
			vo.setId(image.getId());
			// 标题
			vo.setTitle(image.getTitle());
			// 内容
			vo.setContent(image.getContent());
			// 简介
			vo.setResume(image.getResume());
			// 发布时间
			vo.setIssuetime(sdf.format(image.getIssuetime()));
			// 标题图
			vo.setTitlepic(image.getTitlepic());
			// 超链接
			vo.setHyperlink(image.getHyperlink());
			// 副标题1
			vo.setSubtitle1(image.getSubtitle1());
			// 副标题2
			vo.setSubtitle2(image.getSubtitle2());
			// 副标题3
			vo.setSubtitle3(image.getSubtitle3());
			voList.add(vo);
		}
		return voList;
	}
}
