package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.UserTender;

/**
 * @author zhenglm
 * @description 投标记录Mapper
 */
public interface UserTenderMapper {

    /**
     * 新增投标记录
     *
     * @param record
     * @return
     */
    int insert(UserTender record);

    /**
     * 新增投标记录（参数可选）
     *
     * @param tender
     * @return
     */
    int insertSelective(UserTender tender);

    /**
     * 获取用户投标记录列表
     *
     * @param condition
     * @return
     */
    List<UserTender> findUserTenderRecord(Map<String, Object> condition);

    /**
     * 查询用户投标记录列表 （关联用户用户基本信息表、标的设置表）
     *
     * @param tender
     * @return
     */
    List<UserTender> findTenderList(UserTender tender);

    /**
     * 获取用户投标记录详情（关联用户用户基本信息表、标的设置表）
     *
     * @param id
     * @return
     */
    UserTender findUserTenderById(long id);

    /**
     * 根据标id查询投标状态为：1.待审核 4.已完成 5.处理中 6.处理失败,当标的投标状态为这几种中的任意一种时，这个标就不可以流标
     *
     * @author :liuqh
     * @date 2017/6/26 9:48
     */
    List<UserTender> findTenderByTstatusAndTenderid(BigDecimal tenderid);

    /**
     * 根据主键ID更新投标记录
     *
     * @param tender
     * @return
     */
    int updateByPrimaryKeySelective(UserTender tender);

    /**
     * 根据投标订单号orderNO更新投标记录
     *
     * @param tender
     * @return
     */
    int updateByOrderNO(UserTender tender);

    /**
     * 查询待放款投标记录（关联标的设置表、用户账户基本信息表、投标放款记录表）
     *
     * @param tno
     * @return
     */
    List<UserTender> selectPendingLoanByTno(String tno);

    /**
     * 查询我的投资记录（关联标的设置表）
     *
     * @param status
     * @return
     */
    List<UserTender> findMyTenderRecord(UserTender tender);

    /**
     * 根据标的号ID更新起息日
     *
     * @param tender
     * @return
     */
    int updateValueDateByTenderId(UserTender tender);

    /**
     * @param tender
     * @return UserTender
     * @Title: selectInitialOrderByBaseidAndTid
     * @Description: TODO(查询投资人同一标的下状态为初始的投标订单)
     */
    UserTender selectInitialOrderByBaseidAndTid(UserTender tender);

    /**
     * 根据登录用户的id连表查询相关记录
     *
     * @param @param  record
     * @param @return 参数说明
     * @return List<UserTender>    返回类型
     * @throws
     * @Title: findUserTender
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author jiangxueyou
     */
    List<UserTender> findAllUserTender(BigDecimal record);

    /**
     * 根据登录的用户id连表查询可债转的标
     *
     * @param @param  record
     * @param @return 参数说明
     * @return List<UserTender>    返回类型
     * @throws
     * @Title: findUserTenderisadebtattorn
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author jiangxueyou
     */
    List<UserTender> findUserTenderisadebtattorn(BigDecimal record);

    /**
     * 根据页面条件查询可转让的标
     *
     * @param @param  userTender
     * @param @return 参数说明
     * @return List<UserTender>    返回类型
     * @throws
     * @Title: findUserTenderisadebtattornAndCode
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author jiangxueyou
     */
    List<UserTender> findUserTenderisadebtattornAndCode(UserTender userTender);

    /**
     * @param @param  utorderno
     * @param @return 参数说明
     * @return UserTender    返回类型
     * @throws
     * @Title: findUserTenderByUtorderno
     * @Description: TODO(根据投标订单号查询)
     * @author cjm
     */
    UserTender findUserTenderByOrderno(String utorderno);

    /**
     * 根据投标订单号和债转标识查询原始投标订单号
     *
     * @param @param  orderno
     * @param @return
     * @return UserTender
     * @author jiangxueyou
     */
    UserTender findUserTenderByOrdernoAndUtProperty(String orderno);

    /**
     * 根据托管方流水号LoanNo查询投标记录
     *
     * @param loanno
     * @return UserTender    返回类型
     * @Title: selectByLoanNo
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    UserTender selectByLoanNo(String loanno);

    /**
     * 查询放款中、放款成功和放款失败的记录
     *
     * @param tenderid
     * @return List<UserTender>    返回类型
     * @Title: findIsFailTC
     * @Description: TODO(查询放款中、放款成功和放款失败的记录)
     */
    List<UserTender> findIsFailTC(BigDecimal tenderid);

    /*
    * 查找用户有多少次投标
    */
    List<UserTender> findRecordByBaseId(BigDecimal baseId);
}