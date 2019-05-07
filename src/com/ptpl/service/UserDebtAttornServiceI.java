package com.ptpl.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ptpl.model.DattornRNameLink;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.DebtAttornFee;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserTender;
import com.ptpl.web.util.HuifuParams;

/**
 * 债转设置接口
 * @author admin
 *
 */
public interface UserDebtAttornServiceI {
	/**
	 * 根据id查询债转设置对象
	 * @param @param id
	 * @param @return
	 * @return UserDebtAttorn
	 * @author jiangxueyou
	 */
	UserDebtAttorn selectByPrimaryKey(BigDecimal id);
	/**
	 * 删除债转设置对象
	 * @param @param id
	 * @return void
	 * @author jiangxueyou
	 */
	void deleteByPrimaryKey(BigDecimal id);
	/**
	 * 条件添加债转设置对象
	 * @param @param userDebtAttorn
	 * @return void
	 * @author jiangxueyou
	 */
	void insertSelective(UserDebtAttorn userDebtAttorn);
	/**
	 * 条件修改债转设置对象
	 * @param @param userDebtAttorn
	 * @return void
	 * @author jiangxueyou
	 */
	int updateByPrimaryKeySelective(UserDebtAttorn userDebtAttorn);
	/**
	 * 查询用户债转设置表,无条件查询
	 * @param @return
	 * @return List<UserDebtAttorn>
	 * @author jiangxueyou
	 */
	List<UserDebtAttorn> getAll();
	/**
	 * 条件查询用户债转设置表(包括导航栏)
	 * @param @param ub  
	 * @param @return
	 * @return List<UserDebtAttorn>
	 * @author jiangxueyou
	 */
	List<UserDebtAttorn> getAllList(UserDebtAttorn ub);
	/**
	 * 根据债转订单号查询用户债转设置对象
	 * @param @param daorderno 债转订单号
	 * @param @return
	 * @return UserDebtAttorn  返回单个用户债转对象
	 * @author jiangxueyou
	 */
	UserDebtAttorn getdaorderno(String daorderno);
	/**
	 * 连表查询已上架的标
	 * @param @param baseid 用户id
	 * @param @return
	 * @return List<UserDebtAttorn> 返回用户债转集合
	 * @author jiangxueyou
	 */
	List<UserDebtAttorn> getCjZz(BigDecimal baseid);
	/**
	 * 获取手续费,手续费收取模式,债转手续费收取类型
	 * @param @param userdebtattorn
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	String  amountvalidation(UserDebtAttorn userdebtattorn,Double amount);
	/**
	 * 保存用户债转记录
	 * @param @param userdebtattorn
	 * @param @param userTender
	 * @param @param user
	 * @param @param d1
	 * @param @param udapass
	 * @param @param data
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	String saveUserDebtarron(UserDebtAttorn userdebtattorn,UserTender userTender,UserBaseAccountInfo user,Date d1,String udapass,String data);
	/**
	 * 判断出让人手续费足与不足
	 * @param @param coefficient 金额债转系数
	 * @param @param debta		 手续费对象
	 * @param @param daamount	 债转金额
	 * @param @param data		 接收的字符串
	 * @param @return
	 * @return String			返回值做判断
	 * @author jiangxueyou
	 */
	String quotaAndAttornrate(DebtAttornFee debta,UserTender userTender,String data);
	/**
	 * 判断债转次数够不够
	 * @param @param ubList  		用户债转对象集合
	 * @param @param userdebtattorn	用户债转对象
	 * @param @param daamount		债转金额
	 * @param @param restprincipal	剩余金额
	 * @param @param datimes		债转次数
	 * @param @return
	 * @return String				返回值做判断
	 * @author jiangxueyou
	 */
	String ublistmanytimes(BigDecimal baseid,UserTender userTender,UserDebtAttorn userdebtattorn);
	/**
	 * 验证持有天数是否大于等于手续费持有天数
	 * @param @param days    此标持有天数 
	 * @param @param haday   标的债转设置中的持有天数
	 * @param @return
	 * @return boolean		 判断是否满足
	 * @author jiangxueyou
	 */
	boolean dayyanz(int days,int haday);
	/**
	 * 获取用户债转记录中总债转金额
	 * @param @param ubList   用户债转对象集合
	 * @param @return
	 * @return Double		  返回总额
	 * @author jiangxueyou
	 */
	 Double TraversalAmount(List<UserDebtAttorn>  ubList);
	/**
	 * 排出人员名单验证
	 * @param @param tid	   标号id	
	 * @param @param baseid	     用户id
	 * @param @param validata  出让人,购买人的标志:1表示要获取承接人是否在排除人名单中,2表示出让人
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	boolean removenoyanz(BigDecimal tid,BigDecimal baseid,int validata);
	/**
	 * 会员等级验证
	 * @param @param ugradeStr   会员等级字符串
	 * @param @param ugrade		 要比较的会员等级
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	boolean ugradeyanz(String ugradeStr,short ugrade);
	/**
	 * 先决判断条件(正常标和逾期标都会用到的)
	 * @param @param userTender    投标记录
	 * @param @param d			      当前时间
	 * @param @param repayMent	   投资人还款计划
	 * @param @param identifying	逾期标正常标的标识:1为正常标,2为逾期标
	 * @param @param SpecialMark	标记正常标;逾期宽限期的正常标1;
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	String algorithm(UserTender userTender,Date d,RepayMent repayMent) throws Exception;
	/**
	 * 算出让利息和出让滞纳金
	 * @param @param orderno    	投标订单号
	 * @param @param identifying   逾期标正常标的标识:1为正常标,2为逾期标
	 * @param @param periods		期数
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	String normal(UserTender userTender,String periods) throws Exception;
	/**
	 * 持有时间的算法
	 * @param @param d			  当前时间
	 * @param @param userTender   投标记录
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	int CYdays(Date d,UserTender userTender);
	/**
	 * 查询是否是固定
	 * @param @param uda
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	int isfixedFun(UserDebtAttorn uda);
	/**
	 * 算出年利率
	 * @param @param uda
	 * @param @param proceeds 承接人总收益
	 * @param @param CJRAmount 承接人付了多少钱
	 * @param @return
	 * @return Double 返回年利率
	 * @author jiangxueyou
	 */
	Double yearrate(UserDebtAttorn uda,Double proceeds,Double CJRAmount);
	/**
	 * 固定的时候算出让人出让时距离原始标上个还款日(起息日)的时间差
	 * @param @param uda
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	int fixeddays(UserDebtAttorn uda);
	/**
	 * 算出承接人承接后所收到的总利息
	 * @param @param userTender
	 * @param @param repayMent
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	Double totalFee(UserTender userTender,RepayMent repayMent);
	/**
	 * 返回当期还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent returnObject(UserTender userTender,Date date);
	/**
	 * 返回上一期还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent frontObject(UserTender userTender,Date date);
	/**
	 * 查询逾期未还款的最小期数
	 * @param @param userTender
	 * @param @param d
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent overdueFrontNalRepayment(UserTender userTender,Date date);
	/**
	 * 查询每期的还款计划
	 * @param @param userTender
	 * @param @param periods
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent everyRepayment(UserTender userTender, int periods);
	/**
	 * 查询此标的具体投资人还款计划
	 * @param @param user
	 * @param @param userTender
	 * @param @return
	 * @return List<RepayMent>
	 * @author jiangxueyou
	 */
	List<RepayMent> rmlist(UserBaseAccountInfo user,UserTender  userTender);
	/**
	 * 提前还款下架所有在债转的标,如果修改成功就返回成功,失败就返回false
	 * @param @param TenderId
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	boolean undercarriageUserTender(BigDecimal TenderId);
	/**
	 * 保存出让人的用户账户实录和收支记录(收入记录+手续费记录)
	 * @param @param huifuParams
	 * @param @param userdebtattorn
	 * @param @param userid
	 * @param @param falg
	 * @return void
	 * @author jiangxueyou
	 */
	void updateUserAccount(HuifuParams huifuParams,UserDebtAttorn userdebtattorn,String orderno);
	/**
	 * 更新承接人的用户账户信息和收支记录信息
	 * @param @param userid
	 * @param @param huifuParams
	 * @return void
	 * @author jiangxueyou
	 */
	void updateCJUserAccount(BigDecimal userid,HuifuParams huifuParams,UserDebtAttorn userdebtattorn,String orderno);
   /**
	 * 更新投标记录
	 * @param @param huifuParams
	 * @param @param daorderno
	 * @return void
	 * @author jiangxueyou
	 */
	int updateUserTender(HuifuParams huifuParams,String OrdId);
	/**
	 * 根据订单号查询用户债转设置表记录
	 * @param @param torderno
	 * @param @return
	 * @return List<UserDebtAttorn>
	 * @author jiangxueyou
	 */
	List<UserDebtAttorn> selectByTorderNo(String torderno);
	/**
	 * 获取投资人还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @param identifying
	 * @param @param tItem
	 * @param @param periods
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent getRepayMent(UserTender userTender,String periods);
	/**重载
	 * 获取投资人还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @param identifying
	 * @param @param tItem
	 * @param @param periods
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent getRepayMent(UserTender userTender,Date d,String Daproperty,TenderItem tItem,String periods);
	/**
	 * 获取提前还款的最大的那一期
	 * @param @param tender
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	RepayMent prepayment(UserTender tender);
	/**
	 * 获取包含的所有元素
	 * @param @param snlid
	 * @param @return
	 * @return Set<UserBaseAccountInfo>
	 * @author jiangxueyou
	 */
	Set<UserBaseAccountInfo> getSpecialNameList(BigDecimal snlid);
	/**
    * 判断在不在定向名单中
    * @param @param snlid
    * @param @param baseid
    * @param @return
    * @return boolean
    * @author jiangxueyou
    */
   boolean ugradeFalsePublic(BigDecimal snlid,BigDecimal baseid);
   /**
	 * 获取利息债转系数的限制
	 * @param @param userTender
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double debtInterest(UserTender userTender);
	/**
	 * 获取此笔债转金额预计可以获取多少利息
	 * @param @param userTender
	 * @param @param coefficient
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	public Double coeffcient(UserTender userTender);
	/**
	 * 算出金额债转系数的范围
	 * @param @param userTender
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double Amountcoeffcient(UserTender userTender);
	/**
	 * 查询已经还款的金额
	 * @param @param userdebtattorn
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double selectAlreadyRepaymentAmount(UserDebtAttorn userdebtattorn);
	/**
	 * 部分提前还款需要下架的方法
	 * @param @param rocd
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	boolean partcarriageUserTender(String rocd);
	/**
  	 * 查询没有还款的那期的还款计划
  	 * @param @param userTender
  	 * @param @return
  	 * @return UserTender
  	 * @author jiangxueyou
  	 */
  	RepayMent selectRepayMent(UserTender userTender);
	/**
	 * 返回债转还款时间+宽限期后的时间
	 * @param @param repayment
	 * @param @param tenderItem
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	Date returnDate(RepayMent repayment,TenderItem tenderItem);
	/**新增你我贷的页面查询条件
	 * 查询用户债转设置表
	 * @Title: getAllListCode 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return  参数说明 
	 * @return List<UserDebtAttorn>    返回类型 
	 * @author jiangxueyou
	 * @throws
	 */
	List<UserDebtAttorn> getAllListCode(UserDebtAttorn ub);
}
