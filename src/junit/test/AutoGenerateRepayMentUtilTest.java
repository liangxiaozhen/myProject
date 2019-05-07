package junit.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SerializationUtils;

import com.alibaba.fastjson.JSONObject;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.controller.huifu.HuifuTransferController;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.AheadRepayMentLogicI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import com.ptpl.web.util.StringUtil;

 
/**
 * 
 * 标的利息管理费记录 测试类
 * AutoGenerateRepayMentUtilTest
 * 创建人:cjm
 * 时间：2016年10月15日 17:34:03
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class AutoGenerateRepayMentUtilTest {
 
	@Autowired
	private AheadRepayMentLogicI aheadRepayMentLogicI;
	
	@Autowired
	private RepayMentServiceI repayMentServiceI;
	
	@Autowired
	private TenderItemServiceI tenderItemServiceI;
		@Test
		public void hadsa(){
			RepayMent repayment = repayMentServiceI.findRepayMentById(new BigDecimal(1806));
 			boolean flag = aheadRepayMentLogicI.hasInvestorAheadRepayMent(repayment);
 			System.out.println(flag);
		}
		
	   @Test
	   public void hand32(){
//		 List<TenderItem> tenderItems =  tenderItemServiceI.findTenderItemByCondition(null);
//		 for(TenderItem item : tenderItems){
//			 System.out.println("=======Id========"+item.getId());
//			 System.out.println("=======Id========"+item.getTno());
//			 AutoGenerateRepayMentUtil.AutoGenerateDividedPayments(item.getId());
//			 AutoGenerateRepayMentUtil.AutoGenerateRepayMents(item.getId());
//		 }  
		 
		 
// 	 	   boolean str = AutoGenerateRepayMentUtil.AutoGenerateDividedPayments(new BigDecimal(1961));
// 	 	   System.out.println(str+"=============================");
//	 	  Map<String,Object> dfgf =  AutoGenerateRepayMentUtil.AutoGenerateRepayMents(new BigDecimal(1961));
//	 	  System.out.println(dfgf+"=============================");
//	 	 AutoGenerateRepayMentUtil.AutoGenerateDividedPayments(new BigDecimal(1621));
//	 	   System.out.println("+===================================="+dfgf.get("boolean"));
//	 	   System.out.println("+===================================="+dfgf.get("message"));
	 	   
	 	   
//	 	  RepayMent ment = new RepayMent();
//	        ment.setId(new BigDecimal(1));
//	        ment.setAddtime(new Date());
//	       System.out.println(SerializationUtils.serialize(ment)); 
//		   AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(new BigDecimal("1321"), "6000060004191208", "6000060005290553", "20161219111203530588");
	   }
	
   @Test
   public void hand(){
	   //BigDecimal tenderItemId,String buyCustId,String sellCustId,String ordId
	   //第一次债权转让
// 	   AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(new BigDecimal(340), "6000060005590603", "6000060005590676","20161027111629382405");
// 	  AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(new BigDecimal(340), "6000060005590603", "6000060005590122","20161027111629382406");//20161027111629382405
// 	 AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(new BigDecimal(340), "6000060005590122", "6000060005590603","20161027111629382406");//20161027111629382405
	  
	   
	   
// 	   AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(new BigDecimal(340), "6000060005590248", "6000060005590676","20161027111629382407");
 	  
	   //债权承接人债权转让
// 	   AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(new BigDecimal(340), "6000060005590499", "6000060005590603","20161027111629382406");
	   
   }
   
   
   
//   @Test
//   public void hand3rfe2(){
// 	   DecimalFormat decimalFormat = new DecimalFormat("########################0.00");
//		 String transAmt = decimalFormat.format(0.01 + 0.2).toString();//转账金额
//		 String inCustId = "6000060005590499";//入账客户号
//		 String ordId    = StringUtil.getNo();//转账流水号
//		 String result = "";
//		 result   = HuifuTransferController.doTransferParams(null, transAmt, inCustId, ordId);
//		 System.out.println(result);
//		 if(StringUtil.isNotEmpty(result)){
//			 JSONObject jsonObject = JSONObject.parseObject(result);
//			 String respCode =  jsonObject.getString("RespCode");//应答返回码
// 			 if(respCode != null && respCode.equals("000")){
//				 
//			 }
//		 }
//   }
}
