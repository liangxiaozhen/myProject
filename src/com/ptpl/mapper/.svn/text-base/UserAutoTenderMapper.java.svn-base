package com.ptpl.mapper;

import com.ptpl.model.UserAutoTender;
import java.math.BigDecimal;
import java.util.List;

public interface UserAutoTenderMapper {
	/**
	 * 根据主键ID删除自动投标计划
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增自动投标计划
     * @param userAutoTender
     * @return
     */
    int insert(UserAutoTender userAutoTender);

    /**
     * 新增自动投标计划（参数可选）
     * @param userAutoTender
     * @return
     */
    int insertSelective(UserAutoTender userAutoTender);

    /**
     * 根据主键ID查询自动投标计划
     * @param id
     * @return
     */
    UserAutoTender selectByPrimaryKey(BigDecimal id);

    /**
     * 根据BASEID更新自动投标计划（参数可选）
     * @param userAutoTender
     * @return
     */
    int updateByBaseIdSelective(UserAutoTender userAutoTender);

    /**
     * 根据主键ID更新自动投标计划
     * @param userAutoTender
     * @return
     */
    int updateByPrimaryKey(UserAutoTender userAutoTender);

    /**
     * 查询全部用户投标计划
     * @param userAutoTender
     * @return
     */
    List<UserAutoTender> selectAll(UserAutoTender userAutoTender);

    /**
     * 查询当前用户投标计划
     * @param baseid
     * @return
     */
    UserAutoTender selectTenderPlanByBaseId(BigDecimal baseid);
}