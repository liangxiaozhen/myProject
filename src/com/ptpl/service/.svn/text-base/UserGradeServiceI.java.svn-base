package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserGrade;

public interface UserGradeServiceI {
	/**
	 * 根据会员编号获取会员等级名称
	 * @param a
	 * @return
	 */
	String getCodeByUserGradeName(BigDecimal code);
	/**
	 * 添加一个会员等级
	 * @param userGrade
	 */
    void addUserGrade(UserGrade userGrade) throws Exception;
    /**
     * 修改用户等级表
     * @param userGrade
     * @return
     */
    int updateUserGrade(UserGrade userGrade);
    /**
     * 查询所有会员等级
     * @param userGrade
     * @return
     */
    List<UserGrade> getAll(UserGrade userGrade);
    /**
     * 根据id查询一个会员等级对象
     * @param id
     * @return
     */
    UserGrade getByIdToUsergrade(BigDecimal id);
    /**
     * 根据会员等级编号查询数据库有无重复数据
     * @param code
     * @return
     */
    int  getByTrem(BigDecimal code);
    /**
     * 根据会员名称查询数据库有无重复数据
     * @param code
     * @return
     */
    int  getByTrem2(String code);
    /**
     * 
    * @Title: selectByUgrade 
    * @Description: TODO(根据会员等级编号查询对应的会员等级信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return UserGrade    返回类型 
    * @author chenjiaming
    * @throws
     */
    UserGrade selectByUgrade(BigDecimal id);
 
    //根据会员名查出会员等级
    UserGrade getusergrade(BigDecimal code);
    
    /**
     * @param:getGradeList
     * @author liuj
     * @return 筛选后的会员等级
     * */
    List<UserGrade> getGradeList(List<BigDecimal> item);
    /**------------------------------------------------------------------*/
    /**--------------------会员等级变更需求--------------------------------------*/
    /**
     * 分组查询会员等级和会员名称
     * @return
     */
    List<UserGrade> getGroupByUgradeAndUgradeName();
    /**
     * 动态查询
     * @param userGrade 条件
     * @return
     */
    List<UserGrade> selective(UserGrade userGrade);
    /**
     * 查询正常会员等级
     * @param userGrade 条件
     * @return
     */
    List<UserGrade> selectiveForNormal(UserGrade userGrade);
    /**
     * 查询废弃会员等级
     * @param userGrade 条件
     * @return
     */
    List<UserGrade> selectiveForStop(UserGrade userGrade);
    /**
     * 废弃会员等级
     * @param id
     * @return
     */
    int updateToStop(UserGrade userGrade);
    /**
     * 会员等级为废弃状态下的编辑
     * @author 作者 xiaoy: 
     * @version 创建时间：2017年4月17日 下午6:36:14 
     * @param userGrade
     * @return
     */
    int updateForStop(UserGrade userGrade);
    
    /**
     * 会员等级状态改为正常
     * @author 作者 xiaoy: 
     * @version 创建时间：2017年4月17日 下午6:43:15 
     * @param userGrade
     * @return
     */
    int updateToNormal(UserGrade userGrade);
    /**
     * 验证等级名称
     * @param userGrade
     * @return
     */
    int verifyName(UserGrade userGrade);
    /**
     *  查询大于当前用户等级的所有会员
     */
    List<UserGrade> selectForBonusPointsAndCash(Short rankNo);
    
    int insertSelective(UserGrade record);

    UserGrade selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(UserGrade record);
    
    int updateByPrimaryKey(UserGrade record);
}
