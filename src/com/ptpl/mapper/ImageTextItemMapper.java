package com.ptpl.mapper;

import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ui.ImageTextItemVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 图文项目设置Mapper
 * @author 作者 xiaoy: 
 * @version 创建时间：2017年2月10日 上午10:38:42 
 *
 */
public interface ImageTextItemMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(ImageTextItem record);

    int insertSelective(ImageTextItem record);

    ImageTextItem selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(ImageTextItem record);

    int updateByPrimaryKey(ImageTextItem record);
    /**
     * 获取图文项目List
     * @param imageTextItem
     * @return
     */
    List<ImageTextItem> listImageTextItem(ImageTextItem imageTextItem);
}