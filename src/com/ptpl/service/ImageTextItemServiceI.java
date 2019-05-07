package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ui.ImageTextItemVO;

public interface ImageTextItemServiceI {
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
    
    /**
     * 获取图文项目VO LIST
     * @return
     */
    List<ImageTextItemVO>listImageTextItemVO();
}
