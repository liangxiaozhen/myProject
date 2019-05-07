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

 import com.ptpl.mapper.ThirdPbLoginMapper;
import com.ptpl.mapper.ThirdPbLoginMapper;
 import com.ptpl.model.ThirdPbLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class ThirdPbLoginTest {

	 @Autowired
	 private ThirdPbLoginMapper thirdPbLoginMapper;
	 
	 @Test
	 public void handle06(){
		 ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
		 thirdPbLogin.setThirdpname("s微博");
		 thirdPbLogin.setThirdpuid("5241896411");
		 ThirdPbLogin thirdPbLogin2 = thirdPbLoginMapper.selectByThirdUserIdAndThirdName(thirdPbLogin);
		 if(thirdPbLogin2 != null){
			 System.out.println(thirdPbLogin2.getStatus());
		 }else{
			 System.out.println("dsdfds");
		 }
	 }
  	 
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
	     ThirdPbLogin thirdPbLogin = new  ThirdPbLogin();
		 List<ThirdPbLogin> thirdPbLogins = thirdPbLoginMapper.findThirdPbLogins(thirdPbLogin);
  		 for(ThirdPbLogin thirdPbLogin1 :thirdPbLogins){
			System.out.println("=========================="+thirdPbLogin);
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
		 ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
		 thirdPbLogin.setId(new BigDecimal(4));
 		 int count = thirdPbLoginMapper.updateById(thirdPbLogin);
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
  		 int count = thirdPbLoginMapper.deleteById(new BigDecimal(2));
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
  			 ThirdPbLogin thirdPbLogin = new ThirdPbLogin();
 			// BigDecimal bd = new BigDecimal(1);
 			 //thirdPbLogin.setId(new BigDecimal(1));
  			thirdPbLogin.setBaseid(new BigDecimal(1));
  			thirdPbLogin.setThirdpuid("1");
  			thirdPbLogin.setStatus(new Short("2"));
  			thirdPbLogin.setAuthbinddate(new Date());
  			thirdPbLogin.setThirdpname("微博");
  			 int count = 0;
 			 count = thirdPbLoginMapper.insert(thirdPbLogin);
 			if(count > 0){
 				System.out.println("数据添加成功.."+1);
 			}else{
 				System.out.println("数据添加失败"+1);
 			}
 
 	 }
  
}
