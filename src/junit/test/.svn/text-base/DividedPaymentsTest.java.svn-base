package junit.test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.SpringContextHolder;
 import com.ptpl.model.DividedPayments;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.UserBalanceQueryServiceI;
import com.ptpl.web.util.DividedPaymentsComparator;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 标的分期还款计划 测试类
 * DividedPaymentsTest
 * 创建人:chenjiaming
 * 时间：2016年09月27日 16:34:28
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class DividedPaymentsTest {

	 @Autowired
	 private DividedPaymentsServiceI dividedPaymentsMapper;
	 
  	 
	 @Test
	 public void handle05(){
		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		 System.out.println(content);
		 String[] strings = content.getBeanDefinitionNames();
		 for(String string :strings){
			 System.out.println("==================="+string);
		 }
		 
	 }
	 
	 @Test
	 public void handle035(){
		 DividedPayments payments4 = new DividedPayments();
		 payments4.setRepaystatusor((short)4);
		 payments4.setPlanstatus((short)1);//还款有效状态
		 payments4.setRepayisauditor((short)1);//审核状态  1待审核or2 审核通过  
 		 List<DividedPayments> dividedPayments = dividedPaymentsMapper.findDividedPaymentsByRepayMentAudit(payments4);
		 System.out.println(dividedPayments.size());
 		 for(DividedPayments dividedPayments2 : dividedPayments){
			 System.out.println("=======getPeriods==========="+dividedPayments2.getPeriods());
 			 System.out.println("=======getLoginname==========="+dividedPayments2.getBaseAccountInfo().getLoginname());
			 System.out.println("=======getTno()==========="+dividedPayments2.getTenderItem().getTno());
			 System.out.println("========getTname=========="+dividedPayments2.getTenderItem().getTname());
			 System.out.println("========getRepaystatus=========="+dividedPayments2.getMent().getRepaystatus());
			 
 		 }
	 }
	 
	 @Test
	 public void test4() throws Exception{
		 String UsrCustId = "6000060005590453";
//		 String result = SpringContextHolder.getBean(UserBalanceQueryServiceI.class).getQueryBalanceBgByUsrCustId(UsrCustId);
//		 System.out.println(result);
//		 JSONObject jsonObject = JSONObject.parseObject(result);
//		 System.out.println(jsonObject.getString("RespCode"));
//		 System.out.println(jsonObject.getString("AvlBal").replaceAll(",", ""));
   		 
	 }
	 
	 @Test
	 public void test2(){
			DividedPayments dividedPayment  = new DividedPayments();
			dividedPayment.setTenderid(new BigDecimal(340));//标的ID
			dividedPayment.setIscomplete((short)0);//是否还款完成1是0否
 			List<DividedPayments> dividedPayments = dividedPaymentsMapper.findDividedPaymentss(dividedPayment);
 			for(DividedPayments sividedPayment : dividedPayments){
 				System.out.println(sividedPayment.getPeriods()+"========before");
 			}
			Collections.sort(dividedPayments, new DividedPaymentsComparator());
			
			for(DividedPayments sividedPayment : dividedPayments){
				System.out.println(sividedPayment.getPeriods()+"=========after");
			}
	 }
	  /**
	  * 
	 * @Title: findDividedPaymentss 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findDividedPaymentss(){
	     DividedPayments dividedPayments = new  DividedPayments();
		 List<DividedPayments> dividedPaymentss = dividedPaymentsMapper.findDividedPaymentss(dividedPayments);
  		 for(DividedPayments dividedPayments1 :dividedPaymentss){
			System.out.println("=========================="+dividedPayments1.getRemark());
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
 		 Map<String,Object> hashMap = new HashMap<String,Object>();
		 hashMap.put("periods", 1);
		 hashMap.put("tenderid", new BigDecimal(339));
		 
		 DividedPayments dividedPayments = dividedPaymentsMapper.findDividedPaymentsByConditions(hashMap);
		  System.out.println(dividedPayments.getCpinterest());
	 }
	 
	  /**
	  * 
	 * @Title: findDividedPaymentsById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findDividedPaymentsById(){
 		
		 DividedPayments dividedPayments= dividedPaymentsMapper.findDividedPaymentsById(new BigDecimal(11));
		 System.out.println(dividedPayments.getRepayday());
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
  		 int count = dividedPaymentsMapper.deleteById(new BigDecimal(2));
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
 		 for(int i = 0 ;i<=230;i++){
  			 DividedPayments dividedPayments = new DividedPayments();
 			 BigDecimal bd = new BigDecimal(i);
 			 dividedPayments.setId(bd);
  			 int count = 0;
 			 count = dividedPaymentsMapper.insertSelective(dividedPayments);
 			if(count > 0){
 				System.out.println("数据添加成功.."+i);
 			}else{
 				System.out.println("数据添加失败"+i);
 			}
  		 }
 
 	 }
  	 
 	 @Test
	 public void insertSelective2(){
 		 for(int i =1 ;i<=10;i++){
    		DividedPayments dividedPayments = new DividedPayments();
   			dividedPayments.setDporderno(StringUtil.getNo());
   			dividedPayments.setTenderid(new BigDecimal(461));
   			dividedPayments.setPeriods(i);
   			dividedPayments.setRepayday(new Date());
   			dividedPayments.setCurrentpi(1080.00);
   			dividedPayments.setCpinterest(80.00);
   			dividedPayments.setCpprincipal(1000.00);
   			dividedPayments.setRemark("还款测试 备注信息第"+i+"条");
   			dividedPayments.setIsoverdue((short)0);
   			dividedPayments.setIscomplete((short)0);
   			int count = 0;
 			count = dividedPaymentsMapper.insertSelective(dividedPayments);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
 		 }
  
 	 }
}
