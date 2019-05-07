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

 import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.mapper.UserAccountMapper;
 import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserAccountTest {

	 @Autowired
	 private UserAccountServiceI userAccountMapper;
	 
	 @Autowired
	 private UserBaseAccountInfoServiceI userBaseAccountServiceI;
	 
	 @Autowired
	 private UserAccountSafeInfoServiceI userAccountsafeInfo ;
	 
	 @Test
	 public void getCount23(){
  		 List<UserBaseAccountInfo> count = userBaseAccountServiceI.getUserBaseAccountInfoAndUserFSAccountInfo(null);
  		 for(UserBaseAccountInfo userBaseAccountInfo : count){
  			 System.out.println(userBaseAccountInfo.getUserfsaccountinfo().getMercustid());
  		 }
 	 }
	 
	 @Test
	 public void getCount(){
		Integer userAccountSafeInfo = new Integer(2);
 		 int count = userAccountsafeInfo.getUserAccountsafeInfoCountByUgrade(userAccountSafeInfo);
		 System.out.println(count+"==================");
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
	    
	     UserAccount userAccount= userAccountMapper.getUserAccountByBaseId(new BigDecimal(263));
  		  System.out.println(userAccount.getAvlredenvelope()+"==========");
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
//	 @Test
//	 public void handld04(){
//	     UserAccount userAccount = new  UserAccount();
//		 List<UserAccount> userAccounts = userAccountMapper.findUserAccounts(userAccount);
//  		 for(UserAccount userAccount1 :userAccounts){
//			System.out.println("=========================="+userAccount);
//  			}
//	 }
	 
	  /**
	  * 
	 * @Title: handld04 
	 * @Description: TODO(更新 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
//	 @Test
//	 public void handld03(){
//		 UserAccount userAccount = new UserAccount();
//		 userAccount.setId(new BigDecimal(4));
// 		 int count = userAccountMapper.updateById(userAccount);
//		 if(count > 0){
//			 System.out.println("===================更新成功");
//		 }else{
//			 System.out.println("===================更新不成功");
//		 }
//	 }
	 
	 
  /**
	  * 
	 * @Title: handld04 
	 * @Description: TODO(删除 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
//	 public void handld02(){
//  		 int count = userAccountMapper.deleteById(new BigDecimal(2));
// 		 if(count > 0){
// 			 System.out.println("=============================删除成功");
// 		 }else{
// 			 System.out.println("=============================删除失败");
// 		 }
//	 }
	 
	  /**
	  * 
	 * @Title: handld04 
	 * @Description: TODO(新增 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
//	  */
// 	 @Test
//	 public void handld01(){
// 		 for(int i = 0 ;i<=230;i++){
//  			 UserAccount userAccount = new UserAccount();
// 			 BigDecimal bd = new BigDecimal(i);
// 			 userAccount.setId(bd);
//  			 int count = 0;
// 			 count = userAccountMapper.insertSelective(userAccount);
// 			if(count > 0){
// 				System.out.println("数据添加成功.."+i);
// 			}else{
// 				System.out.println("数据添加失败"+i);
// 			}
//  		 }
// 
// 	 }
//  
}
