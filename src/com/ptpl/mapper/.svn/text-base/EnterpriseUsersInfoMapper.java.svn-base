package com.ptpl.mapper;

import com.ptpl.model.EnterpriseUsersInfo;
import java.math.BigDecimal;
/**
 * @author liuj
 * 企业用户资料信息mapper
 */
import java.util.List;
public interface EnterpriseUsersInfoMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(EnterpriseUsersInfo record);

    int insertSelective(EnterpriseUsersInfo record);

    EnterpriseUsersInfo selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(EnterpriseUsersInfo record);

    int updateByPrimaryKey(EnterpriseUsersInfo record);
    
    //获取担保企业的信息
    List<EnterpriseUsersInfo> selectAll();
    
    //通过baseid查询信息
    List<EnterpriseUsersInfo> selectBybaseID(BigDecimal baseid);
}