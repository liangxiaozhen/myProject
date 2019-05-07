package junit.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 import com.ptpl.mapper.SMSSendRecordMapper;
import com.ptpl.mapper.SMSSendRecordMapper;
 import com.ptpl.model.SMSSendRecord;
/**
 * 
 * 短信发送记录 测试类
 * SMSSendRecordTest
 * 创建人:chenjiaming
 * 时间：2016年09月02日 17:17:39
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class SMSSendRecordTest {

	 @Autowired
	 private SMSSendRecordMapper sMSSendRecordMapper;
	 
  	 
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
	 * @Title: findSMSSendRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findSMSSendRecords(){
	     SMSSendRecord sMSSendRecord = new  SMSSendRecord();
		 List<SMSSendRecord> sMSSendRecords = sMSSendRecordMapper.findSMSSendRecords(sMSSendRecord);
  		 for(SMSSendRecord sMSSendRecord1 :sMSSendRecords){
			System.out.println("=========================="+sMSSendRecord);
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
		 SMSSendRecord sMSSendRecord = new SMSSendRecord();
		 sMSSendRecord.setId(new BigDecimal(4));
 		 int count = sMSSendRecordMapper.updateByPrimaryKey(sMSSendRecord);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
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
  			 SMSSendRecord sMSSendRecord = new SMSSendRecord();
 			 BigDecimal bd = new BigDecimal(i);
 			 sMSSendRecord.setId(bd);
  			 int count = 0;
 			 count = sMSSendRecordMapper.insertSelective(sMSSendRecord);
 			if(count > 0){
 				System.out.println("数据添加成功.."+i);
 			}else{
 				System.out.println("数据添加失败"+i);
 			}
  		 }
 
 	 }
  
}
