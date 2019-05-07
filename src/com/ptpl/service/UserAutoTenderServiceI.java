package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserAutoTender;

public interface UserAutoTenderServiceI {
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
