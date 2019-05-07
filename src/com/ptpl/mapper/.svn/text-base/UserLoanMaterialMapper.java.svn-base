package com.ptpl.mapper;

import java.math.BigDecimal;
/**
 * @author liuj
 * 用户借款审核资料记录
 */
import java.util.List;

import com.ptpl.model.UserLoanMaterial;
public interface UserLoanMaterialMapper {
	//删除
    int deleteByPrimaryKey(BigDecimal id);

    //添加
    int insert(UserLoanMaterial record);

    //添加
    int insertSelective(UserLoanMaterial record);

    //根据id查询信息
    UserLoanMaterial selectByPrimaryKey(BigDecimal id);

    //更新
    int updateByPrimaryKeySelective(UserLoanMaterial record);

    //更新
    int updateByPrimaryKey(UserLoanMaterial record);
    
    //获取所有的信息
    List<UserLoanMaterial> selectAllUserLoanmat(UserLoanMaterial loanMaterial);
    
    //通过借款编号和用户id查询信息
    int updateMaterialBynonid(String loanno,BigDecimal baseid);
    
    //同步公共资料时判断是否已经插入
    List<UserLoanMaterial> selectTogether(UserLoanMaterial loanMaterial);
    
    //根据用户id个借款编号删除公共(同步更新资料)
    int deleteBylondid(UserLoanMaterial loanMaterial);
    
    //管理员查看用户个人借款资料
    List<UserLoanMaterial> lookuserloanMaterial(String loanno);
    
    //审核确认(查看用户资料是否全部审核完毕)
    List<UserLoanMaterial> isaudok(String loanno);
    
    //查询该条借款对应的用户资料的全部数据
    int selectallsize(String loanno);
    
    //查询该条借款对应的合格的用户资料
    int selectbyok(String loanno);
    
}