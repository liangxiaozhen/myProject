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

 import com.ptpl.mapper.UserFsAccountInfoMapper;
import com.ptpl.mapper.UserFsAccountInfoMapper;
 import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserFsAccountInfoServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserFsAccountInfoTest {

	 @Autowired
	 private UserFsAccountInfoServiceI userFsAccountInfoMapper;
	 
  	 
	 @Test
	 public void handle05(){
		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		 System.out.println(content);
		 String[] strings = content.getBeanDefinitionNames();
		 for(String string :strings){
			 System.out.println("==================="+string);
		 }
		 
	 }
	 
	 @Test
	 public void hand(){
		 UserFsAccountInfo userFsAccountInfo   = userFsAccountInfoMapper.findUserFsAccountInfoByBaseId(new BigDecimal(272));
		 System.out.println(userFsAccountInfo.getMercustid());
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
	     UserFsAccountInfo userFsAccountInfo = new  UserFsAccountInfo();
		 List<UserFsAccountInfo> userFsAccountInfos = userFsAccountInfoMapper.findUserFsAccountInfos(userFsAccountInfo);
  		 for(UserFsAccountInfo userFsAccountInfo1 :userFsAccountInfos){
			System.out.println("=========================="+userFsAccountInfo);
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
		 UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
		 userFsAccountInfo.setId(new BigDecimal(4));
 		 int count = userFsAccountInfoMapper.updateById(userFsAccountInfo);
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
  		 int count = userFsAccountInfoMapper.deleteById(new BigDecimal(2));
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
   			 UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
  			 userFsAccountInfo.setBaseid(new BigDecimal(217));
    		 int count = 0;
 			 count = userFsAccountInfoMapper.insertSelective(userFsAccountInfo);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  
 	 }
  
}
