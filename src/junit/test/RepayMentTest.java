package junit.test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.mapper.RepayMentMapper;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepayMentAuditDeal;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 还款记录 测试类
 * RepayMentTest
 * 创建人:chenjiaming
 * 时间：2016年09月08日 14:46:55
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml","classpath:spring/spring-mvc.xml"})
public class RepayMentTest {

	 @Autowired
	 private RepayMentMapper repayMentMapper;
	 
	 @Autowired
	 private RepayMentServiceI repayMentServiceI;
	 
	 @Autowired
	 private RepayMentBaseDealI repayMentBaseDealI;
	 
	// 模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;  
	 
    @Test
    public void test(){
    	RepayMent repayMent = repayMentServiceI.findRepayMentById(new BigDecimal(10014));
    	System.out.println(repayMent.getInterestexpense()+"before");
    	Map<String,Object> res = repayMentBaseDealI.calculateNormalRepayemInterestManageFee(repayMent);
    	System.out.println(res.get(RepayMent_Constant.FALG));
     	System.out.println(res.get(RepayMent_Constant.INTERESTEXPENSE));
    	System.out.println(res.get(RepayMent_Constant.INTERESTMANAGEFEE));
            	
    	System.out.println(repayMent.getInterestexpense()+"after");
    }
    
      
//      @Test
// 	 public void test4(){
// 		 Map<String,Object> hashMap2 = new HashMap<String,Object>();
//	   	 hashMap2.put("tenderid",new BigDecimal("1461"));
//	   	 hashMap2.put("periods", Integer.valueOf(1));
//	   	 hashMap2.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
//	   	 hashMap2.put("repaystatus", (short)5);//还款状态(1未还款，2已还款，3已提前还款，4处理中,5提前还款处理中)
//	   	 hashMap2.put("isaudit", (short)2);//是否审核 0待审核 1客服审核中 2审核通过 3审核不通过
//  	   	 List<RepayMent> repayMents = repayMentMapper.findAheadRepayRordernoJoinCheck(hashMap2);
//  	   	 System.out.println(repayMents.size());
//  	   	 for(RepayMent ment : repayMents){
//  	   		 System.out.println(ment.getAheadRealRepayment().getRinterest()+"====");
//  	   	 }
// 	 }
      
       
	 
//	 @Test
//	 public void test3(){
//		 	Map<String,Object> repayMentsMap = new HashMap<String,Object>();
//	        repayMentsMap.put("repaystatus", (short)1);//未还款
//	        repayMentsMap.put("planstatus", (short)1);//是否有效 1有效 2无效
////	        repayMentsMap.put("periods", 1);//还款期数
//	        repayMentsMap.put("tenderId", new BigDecimal(340));//标ID
//	        repayMentsMap.put("rownum", 2);//还款计划状态(1有效，2无效)rownum
//	        List<RepayMent> repayMents = repayMentMapper.findListRepayMentByConditions(repayMentsMap);
//	        System.out.println(repayMents.size());
//	 }
//	 
//	 @Test
//	 public void redf(){
//		 /*
//			 * 提前还款
//			 * 1：本金：一次性还完剩余本金，利息：按天数算 还款日到前一次还款日或截标日之间的天数*天息
//			 * 2：
//			 * 
//			 * */
//			TenderItem tenderItem = OraceTest.getTenderItem();//标的信息
//			Short Isadebtattorn = tenderItem.getIsadebtattorn();//是否允许提前还款
//			System.out.println(Isadebtattorn);
//			if(Isadebtattorn.equals((short)1)){//允许提前还款
//				AheadRepay aheadRepay = OraceTest.getAheadRepay();//标的提前还款设置
//				System.out.println("===========");
//				
//				
//				
//			}else{
//				System.out.println("=======dfd====");
//			}
//	 }
//  	 
//	 @Test
//	 public void hand(){
//		 String rorderno = StringUtil.getNo();//还款流水号
//		 Map<String,String> rordernos = new  HashMap<String,String>();//还款流水号
//		 for(int i = 0;i<3;i++){
//			 rordernos.put("rorderno", rorderno);
// 			 System.out.println(rordernos.get("rorderno")+"==========="+rorderno);
// 			 Map<String,Object> hashMap = new HashMap<String,Object>();
//  			 hashMap.put("rorderno", rordernos.get("rorderno"));//投标订单号 根据具体投标记录对应还款
//  			 RepayMent repayMent2 = repayMentMapper.findRepayMentByConditions(hashMap);
//  			 if(repayMent2 !=null){
//  				 
//  				 System.out.println(repayMent2.getRorderno());
//  			 }
//		     RepayMent repayMent = new RepayMent();
//  	  		 repayMent.setRorderno(rorderno);//还款流水号
//  	  		 repayMent.setRbatchno("dfds");//还款批次号
//  	  	     repayMentMapper.insertSelective(repayMent);
//  	  	     rorderno = StringUtil.getNo();//还款流水号
// 		 }
//	 }
//	 
////	 @Test
////	 public void handle05(){
////		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
////		 System.out.println(content);
////		 String[] strings = content.getBeanDefinitionNames();
////		 for(String string :strings){
////			 System.out.println("==================="+string);
////		 }
////		 
////	 }
////	 
////	 
////	  /**
////	  * 
////	 * @Title: findRepayMents 
////	 * @Description: TODO(查询全部信息 测试) 
////	 * @param   参数说明 
////	 * @return void    返回类型 
////	 * @author chenjiaming
////	 * @throws
////	  */
////	 @Test
////	 public void findRepayMents(){
//// 		 List<RepayMent> repayMents = repayMentMapper.findRepayMents(null);
////  		 for(RepayMent repayMent1 :repayMents){
////  			 int opid = repayMent1.getId().intValue();
////			  if(opid %2 == 0){
////				  repayMent1.setRepaystatus((short)1);
////				  repayMent1.setRtime(new Date());
////				  if(opid > 40){
////					  repayMent1.setIsaudit((short)1);
////				  }else{
//// 					  repayMent1.setIsaudit((short)3);
////				  }
////				  repayMentMapper.updateById(repayMent1);
////				  System.out.println("保存成功");
////			  }else{
////				  repayMent1.setRepaystatus((short)2);
////				  if(opid > 40){
////					  repayMent1.setIsaudit((short)0);
////				  }else{
//// 					  repayMent1.setIsaudit((short)2);
////				  }
////				  repayMentMapper.updateById(repayMent1);
////				  System.out.println("保存成功");
////			  }
////			  if(opid >= 70){
////				  repayMent1.setRepaystatus((short)3);
////				  repayMent1.setIsaudit((short)0);
////				  repayMentMapper.updateById(repayMent1);
////				  System.out.println("保存成功");
////			  }
////  			}
////	 }
////	 
////	  /**
////	  * 
////	 * @Title: updateById 
////	 * @Description: TODO(更新 测试) 
////	 * @param   参数说明 
////	 * @return void    返回类型 
////	 * @author chenjiaming
////	 * @throws
////	  */
	 @Test
	 public void updateById(){
 		 RepayMent repayMent = new RepayMent(); 
 		 repayMent.setId(new BigDecimal(7097));
 		 repayMent.setRprealtime(new Date());
		 int count = 0;
		 count =  repayMentMapper.updateById(repayMent);
		 if(count > 0){
			 System.out.println("更新成功");
		 }else{
			 System.out.println("更新失败");
		 }
	 }
////	 
////	  /**
////	  * 
////	 * @Title: findRepayMentById 
////	 * @Description: TODO(根据ID查找 测试) 
////	 * @param   参数说明 
////	 * @return void    返回类型 
////	 * @author chenjiaming
////	 * @throws
////	  */
	 @Test
	 public void findRepayMentById(){
//// 		
		 RepayMent repayMent= repayMentMapper.findRepayMentById(new BigDecimal(6172));
////		 System.out.println(repayMent.getId());
////		 System.out.println(repayMent.getRorderno());	 
////		 System.out.println(repayMent.getOutaccountid());	 
////		 System.out.println(repayMent.getInaccountid());	 
////		 System.out.println(	repayMent.getProxyaccountid()); 
////		 System.out.println(repayMent.getTenderid());	 
////		 System.out.println(repayMent.getPeriods());	 
////		 System.out.println(repayMent.getIsdarepay());	 
////		 System.out.println(repayMent.getRmode());	 
////		 System.out.println(repayMent.getRprincipalint());  
////		 System.out.println(repayMent.getRamount());	 
////		 System.out.println(repayMent.getRinterest());	 
////		 System.out.println(	repayMent.getRtime()); 
////		 System.out.println(repayMent.getIsaudit());	 
////		 System.out.println(repayMent.getAuditman()); 
////		 System.out.println(repayMent.getRemark());	 
////		 System.out.println(	repayMent.getAudittime());	
//		 System.out.println( repayMent.getId());
//		 System.out.println( repayMent.getRorderno());
//		 System.out.println( repayMent.getRbatchno());
//		 System.out.println( repayMent.getOutaccountid());
//		 System.out.println(repayMent.getInaccountid());
//		 System.out.println( repayMent.getProxyaccountid());
//		 System.out.println( repayMent.getPmiscrecmanid());
//		 System.out.println(repayMent.getUtorderno());
//		 System.out.println( repayMent.getTenderid());
//		 System.out.println( repayMent.getPeriods());
//		 System.out.println(repayMent.getIsdarepay());
//		 System.out.println(repayMent.getRmode());
//		 System.out.println(repayMent.getRprincipalint());
//		 System.out.println(repayMent.getRamount());
//		 System.out.println(repayMent.getRinterest());
//		 System.out.println(repayMent.getBpenalty());
//		 System.out.println(repayMent.getOverdueamount());
//		 System.out.println( repayMent.getInterestexpense());
//		 System.out.println(repayMent.getIsproxyrepay());
//		 System.out.println(repayMent.getFee());
//		 System.out.println(repayMent.getRtime());
//		 System.out.println(repayMent.getIsaudit());
//		 System.out.println(repayMent.getRepaystatus());
//		 System.out.println( repayMent.getIsblending());
//		 System.out.println( repayMent.getIsmanblending());
//		 System.out.println(repayMent.getSysbtime());
//		 System.out.println(repayMent.getManbtime());
//		 System.out.println( repayMent.getPaycompany());
//		 System.out.println( repayMent.getSysrectime());
//		 System.out.println( repayMent.getReceivetime());
//		 System.out.println(repayMent.getReqquerydata());
//		 System.out.println( repayMent.getRecresultdata()); 
//		 System.out.println( repayMent.getAuditman());
//		 System.out.println(repayMent.getAudittime());
//		 System.out.println( repayMent.getPlanstatus());
//		 System.out.println(repayMent.getRemark());
//		 System.out.println( repayMent.getRvoucher());
//		 System.out.println( repayMent.getTransferprincipal());
//		 System.out.println(repayMent.getRestvoucher());
//		 System.out.println( repayMent.getDisablevoucher());
//		 System.out.println(repayMent.getRestlvoucher());
//		 System.out.println(repayMent.getDisablelvoucher());
//		 System.out.println(repayMent.getRestocamount());
//		 System.out.println(repayMent.getDisableocamount());
//		 System.out.println(repayMent.getVrestocamount());
//		 System.out.println( repayMent.getDisablevocamount());
//		 System.out.println(repayMent.getRestintprofit());
//		 System.out.println(repayMent.getDisableintprofit());
//		 System.out.println(repayMent.getRestamountintprofit());
//		 System.out.println(repayMent.getRestvoucherintprofit());
//		 System.out.println(repayMent.getRestlvoucherintprofit());
//		 System.out.println(repayMent.getRestprincipal());
//		 System.out.println(repayMent.getUtorderno());
//		 System.out.println(repayMent.getDaorderno());
	 }
////	 /**
////	 * 
////	 * @Title: deleteById 
////	 * @Description: TODO(删除 测试) 
////	 * @param   参数说明 
////	 * @return void    返回类型 
////	 * @author chenjiaming
////	 * @throws
////	 */
////	 @Test
////	 public void deleteById(){
////  		 int count = repayMentMapper.deleteById(new BigDecimal(3));
//// 		 if(count > 0){
//// 			 System.out.println("=============================删除成功");
//// 		 }else{
//// 			 System.out.println("=============================删除失败");
//// 		 }
////	 }
////	 
////	 /**
////		 * 
////		 * @Title: insert 
////		 * @Description: TODO(删除 测试) 
////		 * @param   参数说明 
////		 * @return void    返回类型 
////		 * @author chenjiaming
////		 * @throws
////		 */
//		 @Test
//		 public void insert(){
//			 int count = 0;
//			 RepayMent repayMent = new RepayMent(); 
//			 repayMent.setId(new BigDecimal(350));
//			 repayMent.setRorderno("测试");
//			 repayMent.setRbatchno("测试");
//			 repayMent.setOutaccountid(new BigDecimal(14));
//			 repayMent.setInaccountid(new BigDecimal(44));
//			 repayMent.setProxyaccountid(new BigDecimal(22));
//			 repayMent.setPmiscrecmanid(new BigDecimal(22));
//			 repayMent.setUtorderno("测试");
//			 repayMent.setTenderid(new BigDecimal(453));
//			 repayMent.setPeriods(14);
//			 repayMent.setIsdarepay((short)2);
//			 repayMent.setRmode((short)2);
//			 repayMent.setRprincipalint(14.00);
//			 repayMent.setRamount(133.00);
//			 repayMent.setRinterest(16.00);
//			 repayMent.setBpenalty(30.00);
//			 repayMent.setOverdueamount(234.00);
//			 repayMent.setInterestexpense(44.00);
//			 repayMent.setIsproxyrepay((short)1);
//			 repayMent.setFee(11.00);
//			 repayMent.setRtime(new Date());
//			 repayMent.setIsaudit((short)3);
//			 repayMent.setRepaystatus((short)1);
//			 repayMent.setIsblending((short)1);
//			 repayMent.setIsmanblending((short)2);
//			 repayMent.setSysbtime(new Date());
//			 repayMent.setManbtime(new Date());
//			 repayMent.setPaycompany("汇付天下2");
//			 repayMent.setSysrectime(new Date());
//			 repayMent.setReceivetime(new Date());
//			 repayMent.setReqquerydata("测试");
//			 repayMent.setRecresultdata("测试"); 
//			 repayMent.setAuditman("多福多寿12");
//			 repayMent.setAudittime(new Date());
//			 repayMent.setPlanstatus((short)1);
//			 repayMent.setRemark("测试");
//			 repayMent.setRvoucher(100.00);
//			 repayMent.setTransferprincipal(100.00);
//			 repayMent.setRestvoucher(100.00);
//			 repayMent.setDisablevoucher(100.00);
//			 repayMent.setRestlvoucher(100.00);
//			 repayMent.setDisablelvoucher(100.00);
//			 repayMent.setRestocamount(100.00);
//			 repayMent.setDisableocamount(100.00);
//			 repayMent.setVrestocamount(100.00);
//			 repayMent.setDisablevocamount(100.00);
//			 repayMent.setRestintprofit(100.00);
//			 repayMent.setDisableintprofit(100.00);
//			 repayMent.setRestamountintprofit(100.00);
//			 repayMent.setRestvoucherintprofit(100.00);
//			 repayMent.setRestlvoucherintprofit(100.00);
//			 repayMent.setRestprincipal(100.00);
//			 repayMent.setUtorderno(StringUtil.getNo());
//			 repayMent.setDaorderno(StringUtil.getNo());
//			 count =  repayMentMapper.insert(repayMent);
// 	 		 if(count > 0){
//	 			 System.out.println("=============================添加成功");
//	 		 }else{
//	 			 System.out.println("=============================添加失败");
//	 		 }
//		 }
////	  /**
////	  * 
////	 * @Title: insertSelective 
////	 * @Description: TODO(新增 测试) 
////	 * @param   参数说明 
////	 * @return void    返回类型 
////	 * @author chenjiaming
////	 * @throws
////	  */
//// 	 @Test
////	 public void insertSelective(){
////// 		 for(int i = 0 ;i<=230;i++){
//////  			 RepayMent repayMent = new RepayMent();
////// 			 BigDecimal bd = new BigDecimal(i);
////// 			 repayMent.setId(bd);
//////  			 int count = 0;
////// 			 count = repayMentMapper.insertSelective(repayMent);
////// 			if(count > 0){
////// 				System.out.println("数据添加成功.."+i);
////// 			}else{
////// 				System.out.println("数据添加失败"+i);
////// 			}
//////  		 }
//// 		for(int i = 0;i<30;i++){
////	 		RepayMent repayMent = new RepayMent();
////	 		repayMent.setRorderno(StringUtil.getActno("HK"));
////			repayMent.setOutaccountid(new BigDecimal(217));
////			if(i%2 == 0){
////				repayMent.setInaccountid(new BigDecimal(297));
////				repayMent.setProxyaccountid(new BigDecimal(267));
////				repayMent.setTenderid(new BigDecimal(461));
////				repayMent.setPeriods(new Integer(5));
////				repayMent.setIsdarepay((short)1);
////				repayMent.setRmode((short)1);
////				repayMent.setRprincipalint(3.00);
////				repayMent.setRamount(102.00);
////				repayMent.setRinterest(121.00);
////				repayMent.setRtime(StringUtil.getDateByString("2016-9-8 12:20:30", "yyyy-MM-dd hh:mm:ss"));
////				repayMent.setIsaudit((short)1);
////				repayMent.setAuditman("小胖www子1");
////				repayMent.setRemark("备注信息,小胖子有2块地wwww");
////				repayMent.setRepaystatus((short)2);
////			}else{
////				repayMent.setInaccountid(new BigDecimal(263));
////				repayMent.setProxyaccountid(new BigDecimal(272));
////				repayMent.setTenderid(new BigDecimal(501));
////				repayMent.setPeriods(new Integer(4));
////				repayMent.setIsdarepay((short)0);
////				repayMent.setRmode((short)2);
////				repayMent.setRprincipalint(3323.00);
////				repayMent.setRamount(122.00);
////				repayMent.setRinterest(111.00);
////				repayMent.setRtime(StringUtil.getDateByString("2016-9-9 12:20:30", "yyyy-MM-dd hh:mm:ss"));
////				repayMent.setIsaudit((short)3);
////				repayMent.setAuditman("小胖www子1");
////				repayMent.setRemark("备注信息,wwww小胖子有2块地"); 
////				repayMent.setRepaystatus((short)3);
//// 			}
//// 			repayMent.setAudittime(new Date());
////			int count = 0;
////			count = repayMentMapper.insertSelective(repayMent);
////			if(count > 0){
////				System.out.println("数据添加成功..");
////			}else{
////				System.out.println("数据添加失败");
////			}
//// 		}
//// 	 }
////  
}
