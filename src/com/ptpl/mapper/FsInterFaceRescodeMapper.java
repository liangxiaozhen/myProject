package com.ptpl.mapper;

import com.ptpl.model.FsInterFaceRescode;
import java.math.BigDecimal;

public interface FsInterFaceRescodeMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(FsInterFaceRescode record);

    int insertSelective(FsInterFaceRescode record);

    FsInterFaceRescode selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(FsInterFaceRescode record);

    int updateByPrimaryKey(FsInterFaceRescode record);
}