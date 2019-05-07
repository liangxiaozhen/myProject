package com.ptpl.mapper;

import com.ptpl.model.UserCommonMaterial;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author Administrator
 * 用户公共资料记录
 */
public interface UserCommonMaterialMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserCommonMaterial record);

    int insertSelective(UserCommonMaterial record);

    UserCommonMaterial selectByPrimaryKey(BigDecimal id);

    List<UserCommonMaterial> selectByTenderitemId(BigDecimal id);

    int updateByPrimaryKeySelective(UserCommonMaterial record);

    int updateByPrimaryKey(UserCommonMaterial record);
    
    //通过用户id查询信息
    List<UserCommonMaterial> selectAllByBaseid(BigDecimal baseid);
    
    //通过用户id删除信息
    int delectBybaseid(BigDecimal baseid);

    List<UserCommonMaterial> selectByLIQNo(String liqno);


}