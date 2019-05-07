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

import com.ptpl.mapper.RoleUserMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RoleUser;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:mybatis/mybatis-config.xml"})
public class AdminUserRoleTest{
 
	@Autowired
	private AdminUserServiceI adminUserServiceI;
	
	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Autowired
	private AdminUserServiceI adminUserService;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	
	@Test
	public void test45489(){
		UserBaseAccountInfo userBaseAccountInfo = new UserBaseAccountInfo();
		userBaseAccountInfo.setId(new BigDecimal("877"));
		userBaseAccountInfo.setCertificationtype("002");
		userBaseAccountInfoServiceI.update(userBaseAccountInfo);
	}
	
	
//	@Test
//	public void test(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//		 String[] strings = context.getBeanDefinitionNames();
//		 for(String string:strings){
//			 System.out.println("=========================================================="+string);
//		 }
//	}
	
	
//	/**
//	 * 管理员模块 角色添加测试
//	 * @author admin
//	 */
//	@Test
//	public void inserIntoRoleUser(){
// 		int count = 0;
//		RoleUser roleUser = new RoleUser();
//		roleUser.setUserId(new BigDecimal(1));
//		roleUser.setRoleId(new BigDecimal(1));
//		count = roleUserMapper.insert(roleUser);
//		if(count >0){
//			System.out.println("数据保存成功====================");
//		}else{
//			System.out.println("数据保存不成功====================");
//			
//		}
//			 
// 		
//	}
}
