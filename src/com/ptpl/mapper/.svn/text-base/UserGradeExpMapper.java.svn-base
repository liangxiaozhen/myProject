package com.ptpl.mapper;

import com.ptpl.model.UserGradeExp;
import java.math.BigDecimal;
import java.util.List;
/**
 * 体验会员等级设置Mapper
 * 
 * @author xiaoy
 *
 * @version 2016年11月26日 上午10:53:40
 */
public interface UserGradeExpMapper {
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