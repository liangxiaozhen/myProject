package com.ptpl.mapper;

import java.util.List;

import com.ptpl.model.DataDesc;

public interface DataDescMapper {
    int insert(DataDesc record);

    int insertSelective(DataDesc record);
    
    List<DataDesc> getDataDesc(String type);
}