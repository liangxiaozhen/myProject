package com.ptpl.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepayMentAuditDeal;
import com.ptpl.model.UserAccount;
import com.ptpl.web.util.HuifuParams;

/**
 * 
 * 还款记录业务层
 * RepayMentServiceI
 * 创建人:chenjiaming
 * 时间：2016年09月08日 14:46:55
 * @version 1.0.0
 *
 */
public interface RepayMentServiceI {

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(还款记录数据增加方法) 
	* @param @param repayMent
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author chenjiaming
	* @throws
	 */
    int insert(RepayMent repayMent);

    /**
     * 
    * @Title: insertSelective 
    * @Description: TODO(还款记录数据增加方法，非空值) 
    * @param @param repayMent
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
     */
    int insertSelective(RepayMent repayMent);
     /**
     * 
     * @Title: deleteById 
     * @Description: TODO(还款记录根据Id 删除方法) 
     * @param @param repayMent
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    int deleteById(BigDecimal id);
      /**
     * 
     * @Title: updateById 
     * @Description: TODO(还款记录更新方法) 
     * @param @param repayMent
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
     int  updateById(RepayMent repayMent); 
     /**
     * 
     * @Title: findRepayMents
     * @Description: TODO(还款记录查询全部) 
     * @param @param repayMent
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author chenjiaming
     * @throws
     */
    List<RepayMent> findRepayMents(RepayMent repayMent);
    
     /**
     * 
    * @Title: findRepayMentById
    * @Description: TODO(根据Id查询对应的还款记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author chenjiaming
    * @throws
     */
    RepayMent findRepayMentById(BigDecimal id);
    /**
     * 
    * @Title: findRepayMentsByBaseId 
    * @Description: TODO(根据用户Id查询对应的还款记录 信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author chenjiaming
    * @throws
     */
    List<RepayMent> findRepayMentsByBaseId(RepayMent repayMent);
    /**
     * 
    * @Title: findRepayMentByRorderno 
    * @Description: TODO(根据还款流水号查询对应的还款记录信息) 
    * @param @param utorderno
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author chenjiaming
    * @throws
     */
    RepayMent findRepayMentByRorderno(String rorderno);
     /**
     * 
    * @Title: findRepayMentByConditions 
    * @Description: TODO(条件查询 返回单个) 
    * @param @param maps
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author cjm
    * @throws
     */
    RepayMent findRepayMentByConditions(Map<String,Object> maps);
    /**
     * 
    * @Title: findRepayMentByConditions 
    * @Description: TODO(条件查询 返回多个) 
    * @param @param maps
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author cjm
    * @throws
     */
    List<RepayMent> findListRepayMentByConditions(Map<String,Object> maps);
    /**
     * 根据标的ID,借款人ID,投资人ID查找已经成功还款的最大期数
    * @Title: findInaccountInfoRepayMentMaxPeriodsByConditions 
    * @Description: TODO(根据标的ID,借款人ID,投资人ID查找已经成功还款的最大期数) 
    * @param @param maps
    * @param @return  参数说明 
    * @return RepayMent    返回类型 
    * @author cjm
    * @throws
     */
    RepayMent findInaccountInfoRepayMentMaxPeriodsByConditions(Map<String,Object> maps);
    /**
     * 查询原始标最后一期的还款时间
     * @param @param repayment
     * @param @return
     * @return RepayMent
     * @author jiangxueyou
     */
  	RepayMent getEndRtimeforRepayMent(RepayMent repayment);
  	/**
  	 * 查询债转当月后面每个月原始标应收的剩余利息
  	 * @param @param repayment
  	 * @param @return
  	 * @return Double
  	 * @author jiangxueyou
  	 */
  	Double  getAmountforRepayMent(RepayMent repayment);
  	/**
  	 * 查询没有债转前应收的总利息
  	 * @param @param repayment
  	 * @param @return
  	 * @return Double
  	 * @author jiangxueyou
  	 */
  	Double  getTotalforRepayMent(RepayMent repayment);
  	/**
  	 * 新逻辑,获取当前债转时间之前的最近的还款时间<和最大期数
  	 * @param @param repayment
  	 * @param @return
  	 * @return RepayMent
  	 * @author jiangxueyou
  	 */
  	RepayMent getRtimeAndPeriodsforRepayMent(RepayMent repayment);
  	/**
  	 * 新逻辑,获取当前债转时间之前的最近的还款时间>和最大期数
  	 * @param @param repayment
  	 * @param @return
  	 * @return RepayMent
  	 * @author jiangxueyou
  	 */
  	RepayMent getRtimeAndPeriodsforRepayMent2(RepayMent repayment);
  	/**
  	 * 逾期查询上一次正常还款的时间
  	 * @param @param repayment
  	 * @param @return
  	 * @return RepayMent
  	 * @author jiangxueyou
  	 */
  	RepayMent getrepaystatus(RepayMent repayment);
  	/**
  	 * 查询每期的还款时间
  	 * @param @param repayment
  	 * @param @return
  	 * @return RepayMent
  	 * @author jiangxueyou
  	 */
  	RepayMent getAndPeriods(RepayMent repayment);
  	/**
  	 * 查询承接人承接后所能收到的利息
  	 * @param @param repayment
  	 * @param @return
  	 * @return Double
  	 * @author jiangxueyou
  	 */
  	Double getTotalPeriods(RepayMent repayment);
  	/**
  	 * 
  	* @Title: countRepayMentByConditions 
  	* @Description: TODO(条件查询 返回总条数) 
  	* @param @param maps
  	* @param @return  参数说明 
  	* @return Integer    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	Integer countRepayMentByConditions(Map<String,Object> maps);
	/**查询剩余本金*/
  	Double getTotalAmountRepayMent(RepayMent repayment);
  	/**查询逾期待收本金*/
  	RepayMent getOverduePrincipal(RepayMent repayment);
  	/**查询投资人的还款计划!针对于逾期*/
  	 List<RepayMent>  selectRepayMentList(RepayMent repayment);
  	/**
  	 * 
  	* @Title: doInsertPlusPayoutRecord 
  	* @Description: TODO(还款时生成增益清算记录) 
  	* @param @param repayMents  参数说明 
  	* @return void    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	void doInsertPlusPayoutRecord(List<RepayMent> repayMents,RepayMent repayMent,Short clearmode);
   
	/**
  	 * 查询正常标的情况下,没有还款的最小期数 
  	 * @param @param repayment
  	 * @param @return
  	 * @return RepayMent
  	 * @author jiangxueyou
  	 */
  	RepayMent getPeriodsforRepayMent(RepayMent repayment);
  	/**
  	 * 获取已还款金额
  	 * @param @param repayment
  	 * @param @return
  	 * @return Double
  	 * @author jiangxueyou
  	 */
  	Double selectAlreadyRepaymentAmount(RepayMent repayment);
  	
  	/**
  	 * 
  	* @Title: findAheadRepayRordernoJoinCheck 
  	* @Description: TODO(和提前还款 实际到账金额联查 ) 
  	* @param @param maps
  	* @param @return  参数说明 
  	* @return List<RepayMent>    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	List<RepayMent> findAheadRepayRordernoJoinCheck(Map<String,Object> maps);
	/**
  	 * 
  	* @Title: findJoinCheckRepayMentByRorderno 
  	* @Description: TODO(联表查询  根据还款流水号) 
  	* @param @param rorderno
  	* @param @return  参数说明 
  	* @return RepayMent    返回类型 
  	* @author cjm
  	* @throws
  	 */
  	RepayMent findJoinCheckRepayMentByRorderno(String rorderno);
	/**
  	 * 查询没有还款的那期的还款计划
  	 * @param @param repayment
  	 * @param @return
  	 * @return RepayMent
  	 * @author jiangxueyou
  	 */
  	RepayMent selectRepayMent(RepayMent repayment);
  	 
  	/**
  	 * 
  	* @Title: findListRepayMentJoinTendAndUserInfoByConditions 
  	* @Description: TODO(联表查询  借款人and投资人and标的信息) 
  	* @param @param maps
  	* @param @return    设定文件 
  	* @return List<RepayMent>    返回类型 
  	* @author   cjm  
  	* @throws
  	 */
  	List<RepayMent> findListRepayMentJoinTendAndUserInfoByConditions(Map<String,Object> maps);
  	 
}
