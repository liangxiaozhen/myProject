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

 import com.ptpl.mapper.EmailRecordMapper;
import com.ptpl.mapper.EmailRecordMapper;
 import com.ptpl.model.EmailRecord;
import com.ptpl.service.EmailRecordServiceI;
import com.ptpl.web.util.SendMailTempalte;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class EmailRecordTest {

	 @Autowired
	 private EmailRecordServiceI emailRecordMapper;
	 
  	 
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
	 * @Title: handld04 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void handld04(){
	     EmailRecord emailRecord = new  EmailRecord();
		 List<EmailRecord> emailRecords = emailRecordMapper.findEmailRecords(null);
  		 for(EmailRecord emailRecord1 :emailRecords){
			System.out.println("=========================="+emailRecord1.getEmail());
  			}
	 }
	 
	  /**
	  * 
	 * @Title: handld04 
	 * @Description: TODO(更新 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void handld03(){
 		 EmailRecord emailRecord = emailRecordMapper.findEmailRecordById(new BigDecimal(32));
		  System.out.println(emailRecord.getEmail());
	 }
	 
	 
  /**
	  * 
	 * @Title: handld04 
	 * @Description: TODO(删除 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void handld02(){
  		 int count = emailRecordMapper.deleteById(new BigDecimal(4));
 		 if(count > 0){
 			 System.out.println("=============================删除成功");
 		 }else{
 			 System.out.println("=============================删除失败");
 		 }
	 }
	 
	  /**
	  * 
	 * @Title: handld04 
	 * @Description: TODO(新增 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
 	 @Test
	 public void handld01(){
   			 EmailRecord emailRecord = new EmailRecord();
   			 emailRecord.setId(new BigDecimal(2));
  			 emailRecord.setReissuetime(new Date());
 			 emailRecord.setRemark("dsfsfdsf");
 			 emailRecord.setEmail("4545d4f564@aqww.cd");
 			 emailRecord.setSendtime(new Date());
 			 emailRecord.setSendtype((short)1);
 			 emailRecord.setBaseid(new BigDecimal(1));
 			 emailRecord.setEmailcontent("郵箱驗證碼");
 			 emailRecord.setUsername("fdsfdsf");
  			 int count = 0;
 			 count = emailRecordMapper.insert(emailRecord);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  		 }
 }
