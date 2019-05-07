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

 import com.ptpl.mapper.UserAuthorizationMapper;
import com.ptpl.mapper.UserAuthorizationMapper;
 import com.ptpl.model.UserAuthorization;
import com.ptpl.service.UserAuthorizationServiceI;
/**
 * 
 * 用户授权记录 测试类
 * UserAuthorizationTest
 * 创建人:cjm
 * 时间：2017年02月27日 17:00:38
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserAuthorizationTest {

	 @Autowired
	 private UserAuthorizationServiceI userAuthorizationMapper;
	 
  	 
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
	 * @Title: findUserAuthorizations 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findUserAuthorizations(){
	     UserAuthorization userAuthorization = new  UserAuthorization();
		 List<UserAuthorization> userAuthorizations = userAuthorizationMapper.findUserAuthorizations(userAuthorization);
  		 for(UserAuthorization userAuthorization1 :userAuthorizations){
			System.out.println("=========================="+userAuthorization1);
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
		 UserAuthorization  userAuthorization = new UserAuthorization();
		 userAuthorization.setId(new BigDecimal(3));
		  userAuthorization.setBaseid(new BigDecimal(332)); ///*账户基本信息表ID*/
		  userAuthorization.setMoneymoremoreid("363123562"); // /*用户的乾多多标识*/
		  userAuthorization.setMercustid("5456465462"); //    /*平台乾多多标识*/
		  userAuthorization.setAuthorizetype("1232");/*授权类型 123表示 1.投标 2.还款 3.二次分配审核*/ 
		  userAuthorization.setAuthorizestatus("0012");/*授权状态 011含义 0关闭1开通*/
		  userAuthorization.setRandomtimestamp("534564132");
		  userAuthorization.setRemark("数据添加测试");
		  userAuthorization.setAddtime(new Date());
		  userAuthorization.setUpdatetime(new Date());
		  int count = 0;
		  count = userAuthorizationMapper.updateById(userAuthorization);
		  if(count > 0){
			  System.out.println("更新成功");
		  }else{
			  System.out.println("更失败");
		  }
	 }
	 
	  /**
	  * 
	 * @Title: findUserAuthorizationById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findUserAuthorizationById(){
 		
		 UserAuthorization userAuthorization= userAuthorizationMapper.findUserAuthorizationById(new BigDecimal(2));
		 System.out.println(userAuthorization.getId());
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
  		 int count = userAuthorizationMapper.deleteById(new BigDecimal(2));
 		 if(count > 0){
 			 System.out.println("=============================删除成功");
 		 }else{
 			 System.out.println("=============================删除失败");
 		 }
	 }
	  
	  @Test
	  public void insert(){
 		  UserAuthorization  userAuthorization = new UserAuthorization();
		  userAuthorization.setBaseid(new BigDecimal(33)); ///*账户基本信息表ID*/
		  userAuthorization.setMoneymoremoreid("36312356"); // /*用户的乾多多标识*/
		  userAuthorization.setMercustid("545646546"); //    /*平台乾多多标识*/
		  userAuthorization.setAuthorizetype("123");/*授权类型 123表示 1.投标 2.还款 3.二次分配审核*/ 
		  userAuthorization.setAuthorizestatus("001");/*授权状态 011含义 0关闭1开通*/
		  userAuthorization.setRandomtimestamp("53456413");
		  userAuthorization.setRemark("数据添加测试");
		  userAuthorization.setAddtime(new Date());
		  userAuthorization.setUpdatetime(new Date());
 		  int count = 0;
		  count = userAuthorizationMapper.insert(userAuthorization);
		  if(count > 0){
			  System.out.println("保存成功");
		  }else{
			  System.out.println("保存失败");
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
  			 UserAuthorization userAuthorization = new UserAuthorization();
 			 BigDecimal bd = new BigDecimal(i);
 			 userAuthorization.setId(bd);
  			 int count = 0;
 			 count = userAuthorizationMapper.insertSelective(userAuthorization);
 			if(count > 0){
 				System.out.println("数据添加成功.."+i);
 			}else{
 				System.out.println("数据添加失败"+i);
 			}
  		 }
 
 	 }
  
}
