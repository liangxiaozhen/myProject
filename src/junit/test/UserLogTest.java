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

 import com.ptpl.mapper.UserLogMapper;
import com.ptpl.mapper.UserLogMapper;
 import com.ptpl.model.UserLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserLogTest {

	 @Autowired
	 private UserLogMapper userLogMapper;
	 
  	 
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
	     UserLog userLog = new  UserLog();
		 List<UserLog> userLogs = userLogMapper.findUserLogs(userLog);
  		 for(UserLog userLog1 :userLogs){
			System.out.println("=========================="+userLog);
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
		 UserLog userLog = new UserLog();
		 userLog.setId(new BigDecimal(1));
		 userLog.setLogintime(new Date());
 		 int count = userLogMapper.updateById(userLog);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
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
	 public void handld02(){
 		 BigDecimal bd = new BigDecimal(2);
 		 UserLog userLog = new UserLog();
 		 userLog.setId(new BigDecimal(2));
 		 int count = userLogMapper.deleteById(userLog);
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
   			 UserLog userLog = new UserLog();
 			 BigDecimal bd = new BigDecimal(2);
 			 userLog.setId(bd);
  			 int count = 0;
 			 count = userLogMapper.insert(userLog);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  	 }
 }
