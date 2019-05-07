package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserGradeExp;
/**
 * 体验会员等级设置ServiceI
 * @author xiaoy
 *
 * @version 2016年11月26日 上午10:59:00
 */
public interface UserGradeExpServiceI {

    int insert(UserGradeExp record);

    int insertSelective(UserGradeExp record);

    UserGradeExp selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserGradeExp record);

    int updateByPrimaryKey(UserGradeExp record);
    
    int updateByStatus(UserGradeExp record);
    
    List<UserGradeExp> selective(UserGradeExp record);
    
    List<UserGradeExp> selectForUgrade(BigDecimal rankno);
    
    int deleteByPrimaryKey(BigDecimal id);
}
