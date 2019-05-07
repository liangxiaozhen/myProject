package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.UserGrade;

public interface UserGradeMapper {
	/**
	 * 根据会员等级编号查询会员名称
	 * @param code
	 * @return
	 */
	String getCodeByUserGradeName(BigDecimal code);
	/**
	 * 添加一个会员等级
	 * @param userGrade
	 */
    void insertUserGrade(UserGrade userGrade);
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
    List<UserGrade> getUserGradeAll(UserGrade userGrade);
    /**
     * 根据id查询一个会员等级对象
     * @param id
     * @return
     */
    UserGrade getById(BigDecimal id);
    /**
     * 根据会员等级编号查询数据库有无重复数据
     * @param code
     * @return
     */
    int  getByUgrade(BigDecimal code);
    /**
     * 根据会员名称查询数据库有无重复数据
     * @param code
     * @return
     */
    int  getByUgradeName(String code);
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

    /**
     * @param:getGradeList
     * @author liuj
     * @return 筛选后的会员等级
     * */
    List<UserGrade> getGradeList(List<BigDecimal> item);
    
    //根据会员名查出会员等级
    UserGrade getusergrade(BigDecimal code);
    
    /**------------------------------------------------------------------*/
    /**--------------------会员等级变更需求--------------------------------------*/
    /**
     * 分组查询会员等级和等级名称
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
