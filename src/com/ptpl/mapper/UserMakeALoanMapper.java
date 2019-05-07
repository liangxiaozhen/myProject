package com.ptpl.mapper;

import com.ptpl.model.UserMakeALoan;
import java.math.BigDecimal;
import java.util.List;

/**
 * 投标放款记录Mapper
 * @author zhenglm
 *
 */
public interface UserMakeALoanMapper {
	/**
	 * 根据主键ID删除投标放款记录
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 新增投标放款记录
     * @param record
     * @return
     */
    int insert(UserMakeALoan record);

    /**
     * 新增投标放款记录（参数可选）
     * @param record
     * @return
     */
    int insertSelective(UserMakeALoan record);

    /**
     * 根据主键ID查询投标放款记录（关联用户基本信息表、标的设置表）
     * @param id
     * @return
     */
    UserMakeALoan selectByPrimaryKey(BigDecimal id);

    /**
     * 根据主键ID更新投标放款记录（参数可选）
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserMakeALoan record);

    /**
     * 根据放款订单号更新投标放款记录（参数可选）
     * @param record
     * @return
     */
    int updateByMLoanOrderNoSelective(UserMakeALoan record);
    
    /**
     * 获取投标放款记录列表（关联用户基本信息表、标的设置表）
     * @param record
     * @return
     */
    List<UserMakeALoan> selectAllLoansRecord(UserMakeALoan record);
    
    /**
     * 获取投标放款记录
     * @param record
     * @return
     */
    List<UserMakeALoan> selectAll(UserMakeALoan record);
    
    /**
     * 根据投标订单号查询投标放款记录
     * @param orderno
     * @return
     */
    List<UserMakeALoan> selectByOrderNo(String orderno);
    
    /**
     * 根据放款订单号查询投标放款记录
     * @param orderno
     * @return
     */
    UserMakeALoan selectByMLoanOrderNo(String orderno);
}