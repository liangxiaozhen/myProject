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

 import com.ptpl.mapper.InterestExpenseRecordMapper;
import com.ptpl.mapper.InterestExpenseRecordMapper;
 import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.service.InterestExpenseRecordServiceI;
/**
 * 
 * 标的利息管理费记录 测试类
 * InterestExpenseRecordTest
 * 创建人:cjm
 * 时间：2016年10月13日 16:56:29
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class InterestExpenseRecordTest {

	 @Autowired
	 private InterestExpenseRecordServiceI interestExpenseRecordMapper;
	 
  	 
	 @Test
	 public void handle05(){
		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		 System.out.println(content);
		 String[] strings = content.getBeanDefinitionNames();
		 for(String string :strings){
			 System.out.println("==================="+string);
		 }
		 
	 }
	 
	 
	  /**
	  * 
	 * @Title: findInterestExpenseRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findInterestExpenseRecords(){
	     InterestExpenseRecord interestExpenseRecord = new  InterestExpenseRecord();
		 List<InterestExpenseRecord> interestExpenseRecords = interestExpenseRecordMapper.findInterestExpenseRecords(interestExpenseRecord);
  		 for(InterestExpenseRecord interestExpenseRecord1 :interestExpenseRecords){
			System.out.println("=========================="+interestExpenseRecord1);
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
		 InterestExpenseRecord interestExpenseRecord = new InterestExpenseRecord();
	 		interestExpenseRecord.setIeorderno("312232");
	 		interestExpenseRecord.setRorderno("4324232");
	 		interestExpenseRecord.setIeid(new BigDecimal(122));
	 		interestExpenseRecord.setTenderid(new BigDecimal(22));
	 		interestExpenseRecord.setIntexpmanid(new BigDecimal(34));
	 		interestExpenseRecord.setInvestorid(new BigDecimal(13));
	 		interestExpenseRecord.setIntexpfee(2.00);
	 		interestExpenseRecord.setIetype((short)2);
	 		interestExpenseRecord.setIsdeal((short)21);
	 		interestExpenseRecord.setIsaudit((short)1);
	 		interestExpenseRecord.setAudittime(new Date());
	 		interestExpenseRecord.setAuditman("sfddsf2");
	 		interestExpenseRecord.setMadetime(new Date());
	 		interestExpenseRecord.setPayoutdate(new Date());
	 		interestExpenseRecord.setRemark("备注2");
		 interestExpenseRecord.setId(new BigDecimal(1));
 		 int count = interestExpenseRecordMapper.updateById(interestExpenseRecord);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findInterestExpenseRecordById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findInterestExpenseRecordById(){
 		
		 InterestExpenseRecord interestExpenseRecord= interestExpenseRecordMapper.findInterestExpenseRecordById(new BigDecimal(1));
		 System.out.println(interestExpenseRecord.getAuditman());
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
  		 int count = interestExpenseRecordMapper.deleteById(new BigDecimal(2));
 		 if(count > 0){
 			 System.out.println("=============================删除成功");
 		 }else{
 			 System.out.println("=============================删除失败");
 		 }
	 }
	 @Test
	  public  void hand(){
		 InterestExpenseRecord expenseRecord = interestExpenseRecordMapper.findInterestExpenseRecordByRorderno("20161013175430597651");
		 System.out.println(expenseRecord.getIeorderno());
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
 		InterestExpenseRecord interestExpenseRecord = new InterestExpenseRecord();
 		interestExpenseRecord.setIeorderno("31232");
 		interestExpenseRecord.setRorderno("432432");
 		interestExpenseRecord.setIeid(new BigDecimal(11));
 		interestExpenseRecord.setTenderid(new BigDecimal(12));
 		interestExpenseRecord.setIntexpmanid(new BigDecimal(14));
 		interestExpenseRecord.setInvestorid(new BigDecimal(15));
 		interestExpenseRecord.setIntexpfee(1.00);
 		interestExpenseRecord.setIetype((short)1);
 		interestExpenseRecord.setIsdeal((short)2);
 		interestExpenseRecord.setIsaudit((short)3);
 		interestExpenseRecord.setAudittime(new Date());
 		interestExpenseRecord.setAuditman("sfddsf");
 		interestExpenseRecord.setMadetime(new Date());
 		interestExpenseRecord.setPayoutdate(new Date());
 		interestExpenseRecord.setRemark("备注");
 		int count = 0;
 		count = interestExpenseRecordMapper.insertSelective(interestExpenseRecord);
 		if(count > 0){
 			System.out.println("成功");
 		}else{
 			System.out.println("失败");
 		}
 	 }
  
}
