package junit.test;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.mapper.AdminUserMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.SMSSendServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:mybatis/mybatis-config.xml"})
public class AdminUserTest {
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@Autowired
	private AdminUserServiceI adminUserService;
	
	@Autowired
	private SMSSendServiceI sMSSendServiceI;
	
	@Autowired
	private RepayMentBaseDealI repayMentBaseDealI;
	
//	@Test
//	public void testre(){
//		repayMentBaseDealI.CheckRepayMentOverdue();//更新是否逾期
//	}
	
//	@Test
//	public void handld(){
//  		AdminUser adminUser2 =  adminUserService.findRoleByAdminUserId(2);
//		System.out.println(adminUser2.getSystemRole().getRolename()+"=========================");
//	}
 
	
	/**
	 * 查询全部用户测试
	 */
//	@Test
//	public void handle10(){
//     	List<AdminUser> adminusers = adminUserMapper.findUserByRoleId(8);
//   		 for(AdminUser adminuser : adminusers){
// 			   
//  			 System.out.println(adminuser.getId());
//		 }
//	}
	
//	@Test
//	public void handle(){
//		AdminUser adminUser = new AdminUser();
//		long nowTimer = System.currentTimeMillis();
//		long timer = (1000*60*1);
//		adminUser.setErrorcount(5);
//		adminUser.setForbid("1");
//		List<AdminUser> adminUsers = adminUserService.findAllAdminUserByForBid(adminUser);
// 		for(AdminUser adminUserss: adminUsers){
// 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// 			adminUserss.setLogintimestr(sdf.format(adminUserss.getLogintime()));
//   			try {
//				long srTimer = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(adminUserss.getLogintimestr()).getTime();
//				long newTimer = nowTimer-srTimer;
//  				if(newTimer >=timer){
//					adminUserss.setForbid("0");
//					adminUserss.setErrorcount(0);
//					adminUserService.updateAdminUser(adminUserss);
//					System.out.println("更新成功！！");
//				}else{
//					System.out.println("条件不满足，进了这里");
//				}
// 			} catch (ParseException e) {
// 				System.out.println("更新不成功！！");		
// 				e.printStackTrace();
//			}
//     	}
//	}

	@Test
	public void handle05(){
		String arr[] = {"张是多少三","李多岁的四","王老速贷王","加多宝","李说的是刚","猩定时猩","张飞测试","测试张三","李删除四","王查抄老王","等等张三","李地方四","王老地方王"};
// 		 for(int i = 0;i<13;i++){
// 		    AdminUser admin = new AdminUser();
 		    String password = BCrypt.hashpw("123456", BCrypt.gensalt());
 		    System.out.println(password);
// 		     admin.setUsername("1234334356"+i+"@qq.com");
//		     admin.setFullname(arr[i]);
//		     admin.setMobilephone("1352346904"+i+"");
//		     admin.setRemark(""+arr[i]+"--测试数据");
////		    admin.setErrorcount(5);
////		    admin.setForbid("1");
//		    admin.setPassword(password);
//  		    admin.setLogintime(new Date());
//  		    admin.setErrorcount(0);
//  		    admin.setForbid("0");
//  		    admin.setRegdate(new Date());
//  		    admin.setModifypwdtime(new Date());
// 		   int count =  adminUserMapper.insertSelective(admin);
//		   if(count > 0){
//			   System.out.println("添加成功.....");
//		   }else{
//			   System.out.println("添加失败.....");
//		   }
//		 }
   	}
 
	
	
//	@Test
//	public void handld04(){
// 		AdminUser user = new AdminUser();
//  		user.setUsername("admin");
//  		user.setPassword("chenjiamings");
// 		AdminUser user1 = adminUserMapper.findAdminUserPSWByUsName(user);
//		boolean flag = BCrypt.checkpw(user.getPassword(),user1.getPassword());
//  		if(flag){
//			System.out.println("查询成功...");
// 		}else{
//			System.out.println("查询失败...");
//		}
	
//	@Test
//	public void handld02(){
// 		AdminUser user = new AdminUser();
//  		user.setUsername("admin");
//  		user.setPassword("chenjiaming");
// 		AdminUser user1 = adminUserMapper.findAdminUserPSWByUsName(user);
// 		System.out.println(user.getUsername());
// 		System.out.println(user1);
//		if(user1 !=null){
//			System.out.println("查询成功..."+user1.getPassword());
//			String str = user1.getPassword();
//			System.out.println(BCrypt.checkpw(user.getPassword(),user1.getPassword()));
//		}else{
//			System.out.println("查询失败...");
//		}
		
		
//		AdminUser user = new AdminUser();
//		user.setUsername("admin1");
//		user.setPassword("123456");
//		AdminUser user1 = adminUserMapper.findAdminUserByUsNameAndPassW(user);
//		if(user1 !=null){
//			System.out.println("查询成功...");
//		}else{
//			System.out.println("查询失败...");
//		}
//	}
	
	
//	@Test
//	public void handle09(){
//			String email = "789@qq.com";
//			String username = "789@qq.com";
//		    String password = "789";
// 		    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());//加密
//		//	System.out.println(BCrypt.checkpw(password, hashed));//密码匹配
//		    AdminUser admin = new AdminUser();
//		    admin.setId(new BigDecimal(6));
//		    admin.setEmail(email);
//		    admin.setUsername(username);
//		    admin.setPassword(hashed);
//		    admin.setLogintime(new Date());
//		   // System.out.println("加密后的密码是==========="+admin.getPassword());
//		   int count =  adminUserMapper.insertSelective(admin);
//		   if(count > 0){
//			   System.out.println("保存成功.....");
//		   }else{
//			   System.out.println("保存失败.....");
//		   }
//			//$2a$10$S9L6lYn5.CjhD4TlaFmPaOGxI9YTFvADhO8YrBDWJhjS7xEF4wtEW
//			System.out.println(hashed);//$2a$10$bmjh3nsVPhEQoQ7xGmWPO.xLrYfySpKUTEbLlmuNI6em4VxQ5W/YK
// 	}

//	@Test
//	public void handle(){
//		System.out.println("===================测试");
//	}
}
