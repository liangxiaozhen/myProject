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

 import com.ptpl.mapper.UserTenderPlusLinkMapper;
import com.ptpl.mapper.UserTenderPlusLinkMapper;
 import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.service.UserTenderPlusLinkServiceI;
/**
 * 
 * 投标增益使用关联 测试类
 * UserTenderPlusLinkTest
 * 创建人:cjm
 * 时间：2016年11月09日 11:46:04
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserTenderPlusLinkTest {

	 @Autowired
	 private UserTenderPlusLinkServiceI userTenderPlusLinkMapper;
	 
	 @Test
	 public void handle06(){
		 List<UserTenderPlusLink>  UserTenderPlusLinks = userTenderPlusLinkMapper
				 .findUserTenderPlusLinkByUtId(new BigDecimal(12));
		 for(UserTenderPlusLink UserTenderPlusLink :UserTenderPlusLinks){
			 System.out.println(UserTenderPlusLink.getReid()+"==========");
			 System.out.println(UserTenderPlusLink.getReid());
			 
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
	 * @Title: findUserTenderPlusLinks 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findUserTenderPlusLinks(){
	     UserTenderPlusLink userTenderPlusLink = new  UserTenderPlusLink();
		 List<UserTenderPlusLink> userTenderPlusLinks = userTenderPlusLinkMapper.findUserTenderPlusLinks(userTenderPlusLink);
  		 for(UserTenderPlusLink userTenderPlusLink1 :userTenderPlusLinks){
			System.out.println("=========================="+userTenderPlusLink1.getReid());
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
		 UserTenderPlusLink userTenderPlusLink = new UserTenderPlusLink();
		 userTenderPlusLink.setId(new BigDecimal(1));
		 userTenderPlusLink.setUtid(new BigDecimal(1));
			userTenderPlusLink.setIcid(new BigDecimal(1));
			userTenderPlusLink.setReid(new BigDecimal(1));
 		 int count = userTenderPlusLinkMapper.updateById(userTenderPlusLink);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findUserTenderPlusLinkById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findUserTenderPlusLinkById(){
 		
		 UserTenderPlusLink userTenderPlusLink= userTenderPlusLinkMapper.findUserTenderPlusLinkById(new BigDecimal(1));
		 System.out.println(userTenderPlusLink.getReid());
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
  		 int count = userTenderPlusLinkMapper.deleteById(new BigDecimal(1));
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
 		 
  			 UserTenderPlusLink userTenderPlusLink = new UserTenderPlusLink();
//  			 userTenderPlusLink.setId(new BigDecimal(1));
  			userTenderPlusLink.setUtid(new BigDecimal(937));
//  			userTenderPlusLink.setIcid(new BigDecimal(889));
  			userTenderPlusLink.setReid(new BigDecimal(890));
  			
  			 int count = 0;
 			 count = userTenderPlusLinkMapper.insert(userTenderPlusLink);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  		 
 
 	 }
  
}
