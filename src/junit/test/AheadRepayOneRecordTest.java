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

import com.ptpl.mapper.AheadRepayOneRecordMapper;
import com.ptpl.model.AheadRepayOneRecord;
import com.ptpl.service.AheadRepayOneRecordServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 标的提前还款奖励单个投资人记录 测试类
 * AheadRepayOneRecordTest
 * 创建人:cjm
 * 时间：2016年10月21日 09:48:17
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class AheadRepayOneRecordTest {

	 @Autowired
	 private AheadRepayOneRecordServiceI aheadRepayOneRecordMapper;
	 
  	 
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
	 * @Title: findAheadRepayOneRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRepayOneRecords(){
	     AheadRepayOneRecord aheadRepayOneRecord = new  AheadRepayOneRecord();
		 List<AheadRepayOneRecord> aheadRepayOneRecords = aheadRepayOneRecordMapper.findAheadRepayOneRecords(aheadRepayOneRecord);
  		 for(AheadRepayOneRecord aheadRepayOneRecord1 :aheadRepayOneRecords){
			System.out.println("=========================="+aheadRepayOneRecord1.getRemark());
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
		 	 AheadRepayOneRecord aheadRepayOneRecord = new AheadRepayOneRecord();
		 	 aheadRepayOneRecord.setId(new BigDecimal(25));
			 aheadRepayOneRecord.setTenderid(new BigDecimal(25));
			 aheadRepayOneRecord.setArorderno(StringUtil.getNo());
			 aheadRepayOneRecord.setArid(new BigDecimal(255));
			 aheadRepayOneRecord.setRorderno(StringUtil.getNo());
			 aheadRepayOneRecord.setPrinpoorint(15.00);
			 aheadRepayOneRecord.setBpenalty(25.00);
			 aheadRepayOneRecord.setPenaltytype((short)15);
			 aheadRepayOneRecord.setIsgrant((short)05);
			 aheadRepayOneRecord.setIsaudit((short)12);
			 aheadRepayOneRecord.setAudittime(new Date());
			 aheadRepayOneRecord.setAuditman("小胖子22314");
			 aheadRepayOneRecord.setMadetime(new Date());
			 aheadRepayOneRecord.setPayoutdate(new Date());
			 aheadRepayOneRecord.setRemark("备注564512");
 		 int count = aheadRepayOneRecordMapper.updateById(aheadRepayOneRecord);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findAheadRepayOneRecordById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRepayOneRecordById(){
 		
		 AheadRepayOneRecord aheadRepayOneRecord= aheadRepayOneRecordMapper.findAheadRepayOneRecordById(new BigDecimal(25));
		 System.out.println(aheadRepayOneRecord.getRemark());
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
  		 int count = aheadRepayOneRecordMapper.deleteById(new BigDecimal(21));
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
  			 AheadRepayOneRecord aheadRepayOneRecord = new AheadRepayOneRecord();
  			 aheadRepayOneRecord.setTenderid(new BigDecimal(23));
  			 aheadRepayOneRecord.setArorderno(StringUtil.getNo());
  			 aheadRepayOneRecord.setArid(new BigDecimal(256));
  			 aheadRepayOneRecord.setRorderno(StringUtil.getNo());
  			 aheadRepayOneRecord.setPrinpoorint(1.00);
  			 aheadRepayOneRecord.setBpenalty(2.00);
  			 aheadRepayOneRecord.setPenaltytype((short)1);
  			 aheadRepayOneRecord.setIsgrant((short)0);
  			 aheadRepayOneRecord.setIsaudit((short)1);
  			 aheadRepayOneRecord.setAudittime(new Date());
  			 aheadRepayOneRecord.setAuditman("小胖子");
  			 aheadRepayOneRecord.setMadetime(new Date());
  			 aheadRepayOneRecord.setPayoutdate(new Date());
  			 aheadRepayOneRecord.setRemark("备注");
    		 int count = 0;
 			 count = aheadRepayOneRecordMapper.insert(aheadRepayOneRecord);
 			 if(count > 0){
 				System.out.println("数据添加成功..");
 			 }else{
 				System.out.println("数据添加失败");
 			 }
  		  
 
 	 }
  
}
