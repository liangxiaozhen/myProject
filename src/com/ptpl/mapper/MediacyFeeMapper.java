package com.ptpl.mapper;

import com.ptpl.model.MediacyFee;
import java.math.BigDecimal;
import java.util.List;

public interface MediacyFeeMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(MediacyFee record);

    int insertSelective(MediacyFee record);

    MediacyFee selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(MediacyFee record);

    int updateByPrimaryKey(MediacyFee record);
    /**
     * 按条件查询
     * @author 作者 xiaoy: 
     * @version 创建时间：2017年6月24日 下午3:04:43 
     * @param record
     * @return
     */
    List<MediacyFee> listMediacyFee(MediacyFee record);
}