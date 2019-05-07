package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ptpl.model.China;
import com.ptpl.model.UserBankCard;

public interface UserBankCardMapper {
	
    int insert(UserBankCard record);
    
    int insertSelective(UserBankCard record);
    
    /**
     * 根据PID查询省份列表
     * @return
     */
    List<China> findProvinceByPid();
    
    /**
     * 根据省份name查询省份id
     * @param name
     * @return
     */
    China findIdByName(String name);
    
    /**
     * 根据省份ID查询相应的城市列表
     * @param pid
     * @return
     */
    List<China> findCityByPid(long pid);
    
    /**
     * 获取用户银行卡信息列表
     * @param card
     * @return
     */
    List<UserBankCard> findUserBankInfo(UserBankCard card);
    
    /**
     * 根据ID查询用户银行卡详情信息
     * @param id
     * @return
     */
    UserBankCard findDetailById(long id);
    
    /**
     * 根据卡号查询用户银行卡详情信息
     * @param cardno
     * @return
     */
    UserBankCard findDetailByCardNo(String cardno);
    
    /**
     * 删除用户银行卡信息
     * @param id
     * @return
     */
    int deleteUserBankInfo(long id);
    
    /**
     * 根据id修改用户银行卡信息
     * @param record
     * @return
     */
    int updateUserBankInfo(UserBankCard record);
    
    /**
     * 查询用户是否绑定快捷卡
     * @param userbankcard
     * @return
     */
    UserBankCard findIsFastBindCard(UserBankCard userbankcard);
   
    /**
     * 根据baseid修改用户默认银行卡信息
     * @param baseid
     * @return
     */
    int updateDefaultCard(UserBankCard userbankcard);

    /**
     * 查询用户默认取现卡
     * @param userbankcard
     * @return
     */
    UserBankCard findIsDefaultCard(UserBankCard userbankcard);
   
    /**
     * 根据baseid修改用户银行卡绑定状态（绑定快捷卡，将其余卡绑定状态改为已解绑）
     * @param baseid
     * @return
     */
    int updateBindStatus(UserBankCard userbankcard);
    
    /**
     * 根据baseid和卡号查询用户银行卡信息
     * @Title: selectByBaseIdAndCardNo
     * @Description: TODO(根据baseid和卡号查询用户银行卡信息)
     * @param userbankcard
     * @return UserBankCard    返回类型
     */
    UserBankCard selectByBaseIdAndCardNo(UserBankCard userbankcard);
    
    /**
     * 根据baseid查询用户当前绑定银行卡信息（徽商银行）
     * @Title: selectBoundCardByBaseId
     * @Description: TODO(根据baseid查询用户当前绑定银行卡信息（徽商银行）)
     * @param baseid
     * @return UserBankCard    返回类型
     */
    UserBankCard selectBoundCardByBaseId(BigDecimal baseid);
    
    /**
     * 根据baseid查询用户已解绑的银行卡信息（徽商银行）
     * @Title: selectUnBoundCardByBaseId
     * @Description: TODO(根据baseid查询用户已解绑的银行卡信息（徽商银行）)
     * @param baseid
     * @return List<UserBankCard>    返回类型
     */
    List<UserBankCard> selectUnBoundCardByBaseId(BigDecimal baseid);
    //根据baseid查询银行卡信息
    List<UserBankCard> getInfoByBaseid(BigDecimal baseid);
}