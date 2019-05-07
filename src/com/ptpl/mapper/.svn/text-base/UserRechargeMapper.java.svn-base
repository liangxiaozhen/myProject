package com.ptpl.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ptpl.model.UserRecharge;

public interface UserRechargeMapper {
    int insert(UserRecharge record);

    int insertSelective(UserRecharge record);
    /**
     * 有条件查询
     * @param record  引用
     * @return
     */
    List<UserRecharge> getAll(UserRecharge record);
    /**
     * 页面时间的查询
     * @param @param record
     * @param @return
     * @return List<UserRecharge>
     * @author jiangxueyou
     */
    List<UserRecharge> getAllCode(UserRecharge record);
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    UserRecharge queryKey(BigDecimal	 id);
    /**
     * 查询订单号有没有
     * @param rechargeNo
     * @return
     */
    UserRecharge getRechargeNo(String rechargeNo);
    /**
     * 根据充值订单号和银行返回充值订单号查询充值记录
     * @param record
     * @return
     */
    UserRecharge getBankReturnNo(UserRecharge record);
    /**
     * 修改用户的充值记录!
     * @param userRecharge
     * @return
     */
    int update(UserRecharge  userRecharge);
   /**
    * 充值对账查询
    * @param userRecharge
    * @return
    */
    List<UserRecharge>  selectContrast(UserRecharge userRecharge);
    void delete(String rechargeno);
    /**
     * 查询当天的充值记录
     * @return
     */
    List<UserRecharge> selectAmountList(UserRecharge userRecharge);
    /**
     * 为了定位再次充值的方法
     * @return
     */
    List<UserRecharge> getUuid(String uuid);
    /**
     * 为了定位再次充值的方法
     * @return
     */
    UserRecharge getUuidAndId(UserRecharge userRecharge);
    /**
     * 为了定位标识有无点击再次充值
     * @return
     */
    List<UserRecharge> getUrid(String urid);
    /**
     * 查询已经勾兑并且状态为取消的数据有哪些
     * @param userRecharge
     * @return
     */
    List<UserRecharge> getIsblendingAndIsmanblendingAndStatus();
    /**
     * 根据baseid查询当前用户的充值记录
     * @param @param baseid
     * @param @return
     * @return List<UserRecharge>
     * @author jiangxueyou
     */
	List<UserRecharge> getAllList(BigDecimal baseid);
	/**
	 * 
	 * @param @param baseid
	 * @param @return
	 * @return List<UserRecharge>
	 * @author jiangxueyou
	 */
	List<UserRecharge> getLableSelect(UserRecharge userRecharge);
}