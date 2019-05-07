package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.mapper.RepaymentDetailMapper;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 还款批量详情记录 测试类
 * RepaymentrepaymentDetailTest
 * 创建人:cjm
 * 时间：2017年08月05日 18:41:12
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class RepaymentDetailTest {

	 @Autowired
	 private RepaymentDetailMapper repaymentDetailMapper;
	 
	 @Autowired
	 private RepaymentDetailServiceI repaymentDetailServiceI;
	 
	 @Autowired
	 private RepayMentBaseDealI repayMentBaseDealI;

	 @Autowired
	 private RepayMentServiceI repaymentServiceI;
	 
	 
	 
	 @Test
	 public void copyRepaymentDeail(){
		 RepayMent repayMent = repaymentServiceI.findRepayMentById(new BigDecimal(9983));
		 if(repayMent != null){
			 
			 RepaymentDetail repaymentDetail  = repayMentBaseDealI.copyRepaymentDetailByRepayMent(repayMent);
		 	System.out.println(repaymentDetail.getId());     //id
		 	System.out.println(repaymentDetail.getRorderno());     //还款流水号
		 	System.out.println(repaymentDetail.getRbatchno());     //还款批次号
		 	System.out.println(repaymentDetail.getOutaccountid());     //借款用户ID
		 	System.out.println(repaymentDetail.getInaccountid());     //投资用户ID
		 	System.out.println(repaymentDetail.getProxyaccountid());     //代还款人ID
		 	System.out.println(repaymentDetail.getPmiscrecmanid());     //平台杂项收款人id
		 	System.out.println(repaymentDetail.getUtorderno());     //投标/债转订单号
		 	System.out.println(repaymentDetail.getTenderid());     //标号ID
		 	System.out.println(repaymentDetail.getPeriods());     //还款期数（第几期）
		 	System.out.println(repaymentDetail.getIsdarepay());     //是否债转还款 0 否 1 是
		 	System.out.println(repaymentDetail.getRmode());     //还款模式（0初始  1人工，2系统，3线下 ）
		 	System.out.println(repaymentDetail.getRprincipalint());     //该批次还款总金额（本息）
		 	System.out.println(repaymentDetail.getRptotalamount());     //该批次还款总金额（不含利息）
		 	System.out.println(repaymentDetail.getRptotalint());     //该批次还款总利息
		 	System.out.println(repaymentDetail.getRamount());     //还款本金金额
		 	System.out.println(repaymentDetail.getRinterest());     //还款本金利息
		 	System.out.println(repaymentDetail.getRvoucher());     //该批次还款类现金
		 	System.out.println(repaymentDetail.getRvoucherint());     //该批次还款类现金的利息
		 	System.out.println(repaymentDetail.getRlvoucher());     //该批次还款假现金
		 	System.out.println(repaymentDetail.getRlvoucherint());     //rlvoucherint
		 	System.out.println(repaymentDetail.getRintfee());     //该批次加息劵利息
		 	System.out.println(repaymentDetail.getRestprincipal());     //剩余本金
		 	System.out.println(repaymentDetail.getTransferprincipal());     //已转让本金（不含增益）
		 	System.out.println(repaymentDetail.getRestvoucher());     //剩余类现金
		 	System.out.println(repaymentDetail.getDisablevoucher());     //失效类现金
		 	System.out.println(repaymentDetail.getDisablevoucherint());     //失效类现金利息
		 	System.out.println(repaymentDetail.getRestlvoucher());     //剩余假现金
		 	System.out.println(repaymentDetail.getDisablelvoucher());     //失效假现金
		 	System.out.println(repaymentDetail.getRestocamount());     //剩余滞纳金
		 	System.out.println(repaymentDetail.getDisableocamount());     //失效滞纳金
		 	System.out.println(repaymentDetail.getVrestocamount());     //剩余类现金滞纳金
		 	System.out.println(repaymentDetail.getDisablevocamount());     //失效类现金滞纳金
		 	System.out.println(repaymentDetail.getRestintprofit());     //剩余加息卷收益
		 	System.out.println(repaymentDetail.getDisableintprofit());     //失效加息卷收益
		 	System.out.println(repaymentDetail.getRestamountintprofit());     //剩余本金产生的利息
		 	System.out.println(repaymentDetail.getRestvoucherintprofit());     //剩余类现金产生的利息
		 	System.out.println(repaymentDetail.getRestlvoucherintprofit());     //剩余假现金产生的利息
		 	System.out.println(repaymentDetail.getAuthcode());     //投标申请授权码
		 	System.out.println(repaymentDetail.getOverdueamount());     //逾期滞纳金额
		 	System.out.println(repaymentDetail.getInterestexpense());     //投标利息的管理费
		 	System.out.println(repaymentDetail.getIsproxyrepay());     //是否代偿（1是，0否）
		 	System.out.println(repaymentDetail.getIsoverdue());     //是否逾期（0否，1是）
		 	System.out.println(repaymentDetail.getIsahead());     //是否提前（0否，1是
		 	System.out.println(repaymentDetail.getRepaystatus());     //还款状态(1待还款  2审核中 2待处理  4处理中 5还款失败 6已还款)
		 	System.out.println(repaymentDetail.getRetcode());     //银行返回码
		 	System.out.println(repaymentDetail.getIssubmit());     //是否提交（1是，0否）
		 	System.out.println(repaymentDetail.getRtime());     //还款时间（计划表生成时间）
		 	System.out.println(repaymentDetail.getSubmittime());     //借款人提交还款时间
		 	System.out.println(repaymentDetail.getOperatetime());     //实际还款操作时间
		 	System.out.println(repaymentDetail.getRprealtime());     //还款实际到账日期
		 	System.out.println(repaymentDetail.getIsaudit());     //是否审核 1是 0否
		 	System.out.println(repaymentDetail.getAuditman());     //审核人
		 	System.out.println(repaymentDetail.getAudittime());     //审核时间
		 	System.out.println(repaymentDetail.getAddtime());     //制表时间
		 	System.out.println(repaymentDetail.getRemark());     //备注
		 }
	 }
  
	 /**
	  * 
	 * @Title: findRepaymentFrzs 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findRepaymentDetail(){
		 RepaymentDetail repaymentDetail = new RepaymentDetail();
		 repaymentDetail.setIsdarepay((short)1);
		 repaymentDetail.setRorderno("20170805193045118549"); 
		 RepaymentDetail repaymentDetail2 = repaymentDetailServiceI.findRepaymentDetail(repaymentDetail);
 		 System.out.println(repaymentDetail2.getRorderno());
	 }
	 
	 /**
	  * 
	 * @Title: findRepaymentFrzs 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findRepaymentDetails(){
		 RepaymentDetail repaymentDetail = new RepaymentDetail();
		 repaymentDetail.setIsdarepay((short)1);
//		 repaymentDetail.setRorderno("20170805193045118549"); 
		 List<RepaymentDetail> repaymentDetail2 = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
		 for(RepaymentDetail eeepaymentDetail : repaymentDetail2){
			 
			 System.out.println(eeepaymentDetail.getRorderno());
		 }
	 }
	 
	  /**
	  * 
	 * @Title: updateById 
	 * @Description: TODO(更新 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void updateById(){
			RepaymentDetail repaymentDetail = new RepaymentDetail();
	 		repaymentDetail.setId(new BigDecimal(6));     //id
	 		repaymentDetail.setRorderno(StringUtil.getNo());     //还款流水号
	 		repaymentDetail.setRbatchno(StringUtil.getNo());     //还款批次号
	 		repaymentDetail.setOutaccountid(new BigDecimal(111));     //借款用户ID
	 		repaymentDetail.setInaccountid(new BigDecimal(111));     //投资用户ID
	 		repaymentDetail.setProxyaccountid(new BigDecimal(111));     //代还款人ID
	 		repaymentDetail.setPmiscrecmanid(new BigDecimal(111));     //平台杂项收款人id
	 		repaymentDetail.setUtorderno(StringUtil.getNo());     //投标/债转订单号
	 		repaymentDetail.setTenderid(new BigDecimal(111));     //标号ID
	 		repaymentDetail.setPeriods(2);     //还款期数（第几期）
	 		repaymentDetail.setIsdarepay((short)0);     //是否债转还款 0 否 1 是
	 		repaymentDetail.setRmode((short)2);     //还款模式（0初始  1人工，2系统，3线下 ）
	 		repaymentDetail.setRprincipalint(100.00);     //该批次还款总金额（本息）
	 		repaymentDetail.setRptotalamount(100.00);     //该批次还款总金额（不含利息）
	 		repaymentDetail.setRptotalint(100.00);     //该批次还款总利息
	 		repaymentDetail.setRamount(100.00);     //还款本金金额
	 		repaymentDetail.setRinterest(100.00);     //还款本金利息
	 		repaymentDetail.setRvoucher(100.00);     //该批次还款类现金
	 		repaymentDetail.setRvoucherint(100.00);     //该批次还款类现金的利息
	 		repaymentDetail.setRlvoucher(100.00);     //该批次还款假现金
	 		repaymentDetail.setRlvoucherint(100.00);     //rlvoucherint
	 		repaymentDetail.setRintfee(100.00);     //该批次加息劵利息
	 		repaymentDetail.setRestprincipal(100.00);     //剩余本金
	 		repaymentDetail.setTransferprincipal(100.00);     //已转让本金（不含增益）
	 		repaymentDetail.setRestvoucher(100.00);     //剩余类现金
	 		repaymentDetail.setDisablevoucher(100.00);     //失效类现金
	 		repaymentDetail.setDisablevoucherint(100.00);     //失效类现金利息
	 		repaymentDetail.setRestlvoucher(100.00);     //剩余假现金
	 		repaymentDetail.setDisablelvoucher(100.00);     //失效假现金
	 		repaymentDetail.setRestocamount(100.00);     //剩余滞纳金
	 		repaymentDetail.setDisableocamount(100.00);     //失效滞纳金
	 		repaymentDetail.setVrestocamount(100.00);     //剩余类现金滞纳金
	 		repaymentDetail.setDisablevocamount(100.00);     //失效类现金滞纳金
	 		repaymentDetail.setRestintprofit(100.00);     //剩余加息卷收益
	 		repaymentDetail.setDisableintprofit(100.00);     //失效加息卷收益
	 		repaymentDetail.setRestamountintprofit(100.00);     //剩余本金产生的利息
	 		repaymentDetail.setRestvoucherintprofit(100.00);     //剩余类现金产生的利息
	 		repaymentDetail.setRestlvoucherintprofit(100.00);     //剩余假现金产生的利息
	 		repaymentDetail.setAuthcode("11111111");     //投标申请授权码
	 		repaymentDetail.setOverdueamount(100.00);     //逾期滞纳金额
	 		repaymentDetail.setInterestexpense(100.00);     //投标利息的管理费
	 		repaymentDetail.setIsproxyrepay((short)0);     //是否代偿（1是，0否）
	 		repaymentDetail.setIsoverdue((short)0);     //是否逾期（0否，1是）
	 		repaymentDetail.setIsahead((short)0);     //是否提前（0否，1是
	 		repaymentDetail.setRepaystatus((short)2);     //还款状态(1待还款  2审核中 2待处理  4处理中 5还款失败 6已还款)
	 		repaymentDetail.setRetcode("111000000");     //银行返回码
	 		repaymentDetail.setIssubmit((short)0);     //是否提交（1是，0否）
	 		repaymentDetail.setRtime(new Date());     //还款时间（计划表生成时间）
	 		repaymentDetail.setSubmittime(new Date());     //借款人提交还款时间
	 		repaymentDetail.setOperatetime(new Date());     //实际还款操作时间
	 		repaymentDetail.setRprealtime(new Date());     //还款实际到账日期
	 		repaymentDetail.setIsaudit((short)0);     //是否审核 1是 0否
	 		repaymentDetail.setAuditman("审核人000");     //审核人
	 		repaymentDetail.setAudittime(new Date());     //审核时间
	 		repaymentDetail.setAddtime(new Date());     //制表时间
	 		repaymentDetail.setRemark("审核人0001");     //备注
	  		 
		 int count = repaymentDetailServiceI.updateById(repaymentDetail);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findRepaymentFrzById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findRepaymentDetailById(){
 		
		 RepaymentDetail repaymentDetail= repaymentDetailServiceI.findRepaymentDetailById(new BigDecimal(6));
		 System.out.println(repaymentDetail.getId());
	 }
  /**
	  * 
	 * @Title: deleteById 
	 * @Description: TODO(删除 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	  @Test
	 public void deleteById(){
  		 int count = repaymentDetailServiceI.deleteById(new BigDecimal(4));
 		 if(count > 0){
 			 System.out.println("=============================删除成功");
 		 }else{
 			 System.out.println("=============================删除失败");
 		 }
	 }
	 
	  /**
	  * 
	 * @Title: insertSelective 
	 * @Description: TODO(新增 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
 	 @Test
	 public void insertSelective(){
 		RepaymentDetail repaymentDetail = new RepaymentDetail();
 		repaymentDetail.setId(new BigDecimal(100));     //id
 		repaymentDetail.setRorderno(StringUtil.getNo());     //还款流水号
 		repaymentDetail.setRbatchno(StringUtil.getNo());     //还款批次号
 		repaymentDetail.setOutaccountid(new BigDecimal(100));     //借款用户ID
 		repaymentDetail.setInaccountid(new BigDecimal(100));     //投资用户ID
 		repaymentDetail.setProxyaccountid(new BigDecimal(100));     //代还款人ID
 		repaymentDetail.setPmiscrecmanid(new BigDecimal(100));     //平台杂项收款人id
 		repaymentDetail.setUtorderno(StringUtil.getNo());     //投标/债转订单号
 		repaymentDetail.setTenderid(new BigDecimal(100));     //标号ID
 		repaymentDetail.setPeriods(1);     //还款期数（第几期）
 		repaymentDetail.setIsdarepay((short)1);     //是否债转还款 0 否 1 是
 		repaymentDetail.setRmode((short)1);     //还款模式（0初始  1人工，2系统，3线下 ）
 		repaymentDetail.setRprincipalint(10.00);     //该批次还款总金额（本息）
 		repaymentDetail.setRptotalamount(10.00);     //该批次还款总金额（不含利息）
 		repaymentDetail.setRptotalint(10.00);     //该批次还款总利息
 		repaymentDetail.setRamount(10.00);     //还款本金金额
 		repaymentDetail.setRinterest(10.00);     //还款本金利息
 		repaymentDetail.setRvoucher(10.00);     //该批次还款类现金
 		repaymentDetail.setRvoucherint(10.00);     //该批次还款类现金的利息
 		repaymentDetail.setRlvoucher(10.00);     //该批次还款假现金
 		repaymentDetail.setRlvoucherint(10.00);     //rlvoucherint
 		repaymentDetail.setRintfee(10.00);     //该批次加息劵利息
 		repaymentDetail.setRestprincipal(10.00);     //剩余本金
 		repaymentDetail.setTransferprincipal(10.00);     //已转让本金（不含增益）
 		repaymentDetail.setRestvoucher(10.00);     //剩余类现金
 		repaymentDetail.setDisablevoucher(10.00);     //失效类现金
 		repaymentDetail.setDisablevoucherint(10.00);     //失效类现金利息
 		repaymentDetail.setRestlvoucher(10.00);     //剩余假现金
 		repaymentDetail.setDisablelvoucher(10.00);     //失效假现金
 		repaymentDetail.setRestocamount(10.00);     //剩余滞纳金
 		repaymentDetail.setDisableocamount(10.00);     //失效滞纳金
 		repaymentDetail.setVrestocamount(10.00);     //剩余类现金滞纳金
 		repaymentDetail.setDisablevocamount(10.00);     //失效类现金滞纳金
 		repaymentDetail.setRestintprofit(10.00);     //剩余加息卷收益
 		repaymentDetail.setDisableintprofit(10.00);     //失效加息卷收益
 		repaymentDetail.setRestamountintprofit(10.00);     //剩余本金产生的利息
 		repaymentDetail.setRestvoucherintprofit(10.00);     //剩余类现金产生的利息
 		repaymentDetail.setRestlvoucherintprofit(10.00);     //剩余假现金产生的利息
 		repaymentDetail.setAuthcode("1000000");     //投标申请授权码
 		repaymentDetail.setOverdueamount(10.00);     //逾期滞纳金额
 		repaymentDetail.setInterestexpense(10.00);     //投标利息的管理费
 		repaymentDetail.setIsproxyrepay((short)1);     //是否代偿（1是，0否）
 		repaymentDetail.setIsoverdue((short)1);     //是否逾期（0否，1是）
 		repaymentDetail.setIsahead((short)1);     //是否提前（0否，1是
 		repaymentDetail.setRepaystatus((short)1);     //还款状态(1待还款  2审核中 2待处理  4处理中 5还款失败 6已还款)
 		repaymentDetail.setRetcode("000000000");     //银行返回码
 		repaymentDetail.setIssubmit((short)1);     //是否提交（1是，0否）
 		repaymentDetail.setRtime(new Date());     //还款时间（计划表生成时间）
 		repaymentDetail.setSubmittime(new Date());     //借款人提交还款时间
 		repaymentDetail.setOperatetime(new Date());     //实际还款操作时间
 		repaymentDetail.setRprealtime(new Date());     //还款实际到账日期
 		repaymentDetail.setIsaudit((short)1);     //是否审核 1是 0否
 		repaymentDetail.setAuditman("审核人");     //审核人
 		repaymentDetail.setAudittime(new Date());     //审核时间
 		repaymentDetail.setAddtime(new Date());     //制表时间
 		repaymentDetail.setRemark("审核人");     //备注
  		 
  			int count = repaymentDetailServiceI.insertSelective(repaymentDetail);;
  			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  		  
  	 }
}
