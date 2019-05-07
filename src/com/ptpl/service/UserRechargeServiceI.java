package com.ptpl.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.asn1.ocsp.Request;

import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;

public interface UserRechargeServiceI {
	/**
	 * 查询所有记录,还包括导航栏查询功能
	 * @param record
	 * @return
	 * @throws Exception
	 */
	List<UserRecharge> getAll(UserRecharge record) throws Exception;
	/**
	 * 页面时间的查询
	 * @param @param record
	 * @param @return
	 * @return List<UserRecharge>
	 * @author jiangxueyou
	 */
	 List<UserRecharge> getAllCode(UserRecharge record);
	/**
	 * 根据id查询一条充值记录
	 * @param id
	 * @return
	 */
	UserRecharge queryKey(BigDecimal  id);
	/**
	 * 添加
	 * @param userRecharge
	 * @return
	 */
	int  add(UserRecharge userRecharge);
	/**
	 * 查询订单号有没有
	 * @param rechargeNo
	 * @return
	 */
	UserRecharge select(String rechargeNo);
	/**
	 * 修改
	 * @param userRecharge
	 * @return
	 */
	int update(UserRecharge userRecharge);
	/**
	 * 充值对账
	 * @param userRecharge
	 * @return
	 */
	List<UserRecharge> selectContrast(UserRecharge userRecharge);
	/**
	 * 如果中途中断充值操作的话,数据将会从数据库中删除
	 * @param rechargeno
	 */
	void delete(String rechargeno);
	  /**
     * 根据充值订单号和银行返回充值订单号查询充值记录
     * @param record
     * @return
     */
	UserRecharge getBankReturnNo(UserRecharge record);
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
     * 根据baseid查询充值记录
     * @param @param baseid
     * @param @return
     * @return List<UserRecharge>
     * @author jiangxueyou
     */
    List<UserRecharge>  getAllList(BigDecimal baseid);

	/**
	 * 获取当天的零点零时零分
	 * @param @return
	 * @return Long
	 * @author jiangxueyou
	 */
   Date DateZero(Date date);
   /**
	 * 获取当天的23点59分59秒
	 * @param @return
	 * @return Long
	 * @author jiangxueyou
	 */
   Long DateMax(Date date);
   /**
	 * 获取当前时间前六个月的那天
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	Date sixMonth(Date date);
	/**
	 * 获取当前时间前三个月的那天
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	Date threeMonth(Date date);
	/**
	 * 获取当前时间的前一个月的第一天,eg: 3.17 最近一个月的交易就是2.17号00:00:00到系统3.17的当前时间
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	Date oneMonth(Date date);
	/**
	 * 获取当前时间往前推一个星期的那天,eg: 3.17 号 往前推一个星期 得出的结论是3.10号
	 * @param @param date
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	Date oneWeek(Date date);
	/**
	 * 查询当天/一个星期内/一个月/三个月/半年/的充值记录
	 * @param @param dataMap
	 * @param @return
	 * @return List<UserRecharge>
	 * @author jiangxueyou
	 */
	List<UserRecharge> getLableSelect(UserRecharge userRecharge);
	/**
	 * 点击到充值页面的时候就更新用户账户表
	 * @param @param map
	 * @return void
	 * @author jiangxueyou
	 */
	int updateUserAccount(Map<String,String> map,UserAccount userAccount);
	/**
	 * 余额查询
	 * @param @param ufs
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 * @throws  
	 */
	Map<String,String> queryBlane(UserFsAccountInfo  ufs);
	/**
	 * 获取充值页面显示数据
	 * @param @param user
	 * @param @param ufs
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	Map<String,String> getUserInfo(UserBaseAccountInfo user,UserFsAccountInfo ufs);
	/**
	 * 获取短信验证码
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	Map<String,String> getmessage(UserBaseAccountInfo user,UserFsAccountInfo ufs);
	/**
	 * 确认充值
	 * @param @param userBaseAccountInfo
	 * @param @param money
	 * @param @param rechargeType
	 * @param @param sms_code
	 * @param @param sms_seq
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	Map<String, String> confirmRecharge(UserBaseAccountInfo user,UserFsAccountInfo ufs,HttpServletRequest request);
	/**
	 * 金额校验
	 * @param @param rechargeType
	 * @param @param money
	 * @param @param user
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	 String dmoney(String rechargeType,String money,UserBaseAccountInfo user);
	 /**
	  * 网银充值的时候,金额校验不通过的时候给的提示信息
	  * @param @param rechargeType
	  * @param @param money
	  * @param @param user
	  * @param @return
	  * @return String
	  * @author jiangxueyou
	  */
	 String dmoney2(String rechargeType,String money,UserBaseAccountInfo user);
	 /**
	  * 网银充值前保存一部分数据到充值记录
	  * @param @param sum
	  * @param @param money
	  * @param @param orderno
	  * @param @param user
	  * @param @param ufs
	  * @param @param reqMap
	  * @return void
	  * @author jiangxueyou
	 * @return 
	  */
	 int saveUserRecharge(String sum,String money,String orderno,UserBaseAccountInfo user,UserFsAccountInfo  ufs,LinkedHashMap<String,String> reqMap);
	 /**
	  * 快捷充值返回后修改数据
	  * @param @param hashMap
	  * @param @param baseid
	  * @return void
	  * @author jiangxueyou
	  */
	 String updateMessage(Map<String, String> hashMap, BigDecimal baseid);
	 /**
	  * 快捷充值
	  * @param @param user
	  * @param @param ufs
	  * @param @param request
	  * @param @return
	  * @return String
	  * @author jiangxueyou
	  */
	String shortcut(UserBaseAccountInfo user, UserFsAccountInfo ufs, HttpServletRequest request,String sms_seq);
	/**
	 * 发送请求去徽商
	 * @param @param reqMap
	 * @param @param string
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	String fenzhuang(LinkedHashMap<String,String> reqMap, String string) throws Exception;
	/**
	 * 姓名加密:返回*学友类型
	 * @param @param str
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	String nameEncryption(String str);
	/**
	 * 电子账号加密返回1234******4567类型
	 * @param @param str
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	String curstrnoEncryption(String str);

}
