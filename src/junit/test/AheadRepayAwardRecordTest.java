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

 import com.ptpl.mapper.AheadRepayAwardRecordMapper;
import com.ptpl.mapper.AheadRepayAwardRecordMapper;
 import com.ptpl.model.AheadRepayAwardRecord;
import com.ptpl.service.AheadRepayAwardRecordServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 标的提前还款奖品补偿记录 测试类
 * AheadRepayAwardRecordTest
 * 创建人:cjm
 * 时间：2016年10月21日 11:34:25
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class AheadRepayAwardRecordTest {

	 @Autowired
	 private AheadRepayAwardRecordServiceI aheadRepayAwardRecordMapper;
	 
  	 
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
	 * @Title: findAheadRepayAwardRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRepayAwardRecords(){
	     AheadRepayAwardRecord aheadRepayAwardRecord = new  AheadRepayAwardRecord();
		 List<AheadRepayAwardRecord> aheadRepayAwardRecords = aheadRepayAwardRecordMapper.findAheadRepayAwardRecords(aheadRepayAwardRecord);
  		 for(AheadRepayAwardRecord aheadRepayAwardRecord1 :aheadRepayAwardRecords){
			System.out.println("=========================="+aheadRepayAwardRecord1.getRemark());
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
		 AheadRepayAwardRecord aheadRepayAwardRecord = new AheadRepayAwardRecord();
			aheadRepayAwardRecord.setTenderid(new BigDecimal(2134));
			aheadRepayAwardRecord.setArorderno(StringUtil.getNo());
			aheadRepayAwardRecord.setArid(new BigDecimal(2654));
			aheadRepayAwardRecord.setRorderno(StringUtil.getNo());
			aheadRepayAwardRecord.setPawardname("平台奖励4");
			aheadRepayAwardRecord.setPawardno("1234564");
			aheadRepayAwardRecord.setPawardcount(104.00);
			aheadRepayAwardRecord.setPluspoorint(204.00);
			aheadRepayAwardRecord.setPlusppenalty(304.00);
			aheadRepayAwardRecord.setPluspaward("平台奖励奖品4");
			aheadRepayAwardRecord.setPluspawardcount(204);
			aheadRepayAwardRecord.setIsgrant((short)2);
			aheadRepayAwardRecord.setIsaudit((short)3);
			aheadRepayAwardRecord.setAudittime(new Date());
			aheadRepayAwardRecord.setAuditman("审核人3");
			aheadRepayAwardRecord.setMadetime(new Date());
			aheadRepayAwardRecord.setPayoutdate(new Date());
			aheadRepayAwardRecord.setRemark("备注备注34");
			aheadRepayAwardRecord.setId(new BigDecimal(4));
	 		int count = aheadRepayAwardRecordMapper.updateById(aheadRepayAwardRecord);
			if(count > 0){
				 System.out.println("===================更新成功");
			}else{
				 System.out.println("===================更新不成功");
			}
	 }
	 
	  /**
	  * 
	 * @Title: findAheadRepayAwardRecordById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRepayAwardRecordById(){
 		
		 AheadRepayAwardRecord aheadRepayAwardRecord= aheadRepayAwardRecordMapper.findAheadRepayAwardRecordById(new BigDecimal(2));
		 System.out.println(aheadRepayAwardRecord.getArorderno());
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
  		 int count = aheadRepayAwardRecordMapper.deleteById(new BigDecimal(2));
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
   			AheadRepayAwardRecord aheadRepayAwardRecord = new AheadRepayAwardRecord();
   			aheadRepayAwardRecord.setTenderid(new BigDecimal(213));
   			aheadRepayAwardRecord.setArorderno(StringUtil.getNo());
   			aheadRepayAwardRecord.setArid(new BigDecimal(265));
   			aheadRepayAwardRecord.setRorderno(StringUtil.getNo());
   			aheadRepayAwardRecord.setPawardname("平台奖励");
   			aheadRepayAwardRecord.setPawardno("123456");
   			aheadRepayAwardRecord.setPawardcount(10.00);
   			aheadRepayAwardRecord.setPluspoorint(20.00);
   			aheadRepayAwardRecord.setPlusppenalty(30.00);
   			aheadRepayAwardRecord.setPluspaward("平台奖励奖品");
   			aheadRepayAwardRecord.setPluspawardcount(20);
   			aheadRepayAwardRecord.setIsgrant((short)0);
   			aheadRepayAwardRecord.setIsaudit((short)0);
   			aheadRepayAwardRecord.setAudittime(new Date());
   			aheadRepayAwardRecord.setAuditman("审核人");
   			aheadRepayAwardRecord.setMadetime(new Date());
   			aheadRepayAwardRecord.setPayoutdate(new Date());
   			aheadRepayAwardRecord.setRemark("备注备注");
   			aheadRepayAwardRecord.setInvestorid(new BigDecimal(223));
   			aheadRepayAwardRecord.setPluspawardno("54564");
   			
   			  
   			int count = 0;
 			count = aheadRepayAwardRecordMapper.insert(aheadRepayAwardRecord);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  	 }
  
}
