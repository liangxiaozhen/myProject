package junit.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.mapper.DebtAttornMapper;
import com.ptpl.mapper.UserDebtAttornMapper;
import com.ptpl.mapper.UserDebtAttornMapper;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.service.UserDebtAttornServiceI;
/**
 * 
 * 投标记录 测试类
 * UserDebtAttornTest
 * 创建人:chenjiaming
 * 时间：2016年10月14日 10:38:05
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserDebtAttornTest {

	 @Autowired
	 private DebtAttornMapper userDebtAttornMapper;
	 
//  	 @Test
//  	 public void hasnd(){
//  		List<DebtAttorn> UserDebtAttorns =userDebtAttornMapper.getAll();
//  		for(DebtAttorn userDebtAttorn : UserDebtAttorns){
//  			System.out.println("====="+userDebtAttorn.getTenderitem().getIsadebtattorn());
//  		}
//  	 }
  	 @Test
  	 public void hasnd2(){
  		List<Short> a= new ArrayList<Short>();
  		a.add((short)1);
  		a.add((short)3);
  		a.add((short)4);
  		a.add((short)6);
  		a.add((short)7);
  		a.add((short)9);
  		a.add((short)0);
  		Short max = 0;
		for (int i = 0; i < a.size(); i++) {
			Short temp = a.get(i);
			if(temp>max){
				max = temp;
			}
		}
		System.out.println(max);
  	 }
  	 
  	
//	 @Test
//	 public void handle05(){
//		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
//		 System.out.println(content);
//		 String[] strings = content.getBeanDefinitionNames();
//		 for(String string :strings){
//			 System.out.println("==================="+string);
//		 }
//		 
//	 }
//	 
//	 
//	  /**
//	  * 
//	 * @Title: findUserDebtAttorns 
//	 * @Description: TODO(查询全部信息 测试) 
//	 * @param   参数说明 
//	 * @return void    返回类型 
//	 * @author chenjiaming
//	 * @throws
//	  */
//	 @Test
//	 public void findUserDebtAttorns(){
//	     UserDebtAttorn userDebtAttorn = new  UserDebtAttorn();
//		 List<UserDebtAttorn> userDebtAttorns = userDebtAttornMapper.findUserDebtAttorns(userDebtAttorn);
//  		 for(UserDebtAttorn userDebtAttorn1 :userDebtAttorns){
//			System.out.println("=========================="+userDebtAttorn);
//  			}
//	 }
//	 
//	  /**
//	  * 
//	 * @Title: updateById 
//	 * @Description: TODO(更新 测试) 
//	 * @param   参数说明 
//	 * @return void    返回类型 
//	 * @author chenjiaming
//	 * @throws
//	  */
//	 @Test
//	 public void updateById(){
//		 UserDebtAttorn userDebtAttorn = new UserDebtAttorn();
//		 userDebtAttorn.setId(new BigDecimal(4));
// 		 int count = userDebtAttornMapper.updateById(userDebtAttorn);
//		 if(count > 0){
//			 System.out.println("===================更新成功");
//		 }else{
//			 System.out.println("===================更新不成功");
//		 }
//	 }
//	 
//	  /**
//	  * 
//	 * @Title: findUserDebtAttornById 
//	 * @Description: TODO(根据ID查找 测试) 
//	 * @param   参数说明 
//	 * @return void    返回类型 
//	 * @author chenjiaming
//	 * @throws
//	  */
//	 @Test
//	 public void findUserDebtAttornById(){
// 		
//		 UserDebtAttorn userDebtAttorn= userDebtAttornMapper.findUserDebtAttornById(new BigDecimal(25));
//		 System.out.println(userDebtAttorn.getId());
//	 }
//  /**
//	  * 
//	 * @Title: deleteById 
//	 * @Description: TODO(删除 测试) 
//	 * @param   参数说明 
//	 * @return void    返回类型 
//	 * @author chenjiaming
//	 * @throws
//	  */
//	  @Test
//	 public void deleteById(){
//  		 int count = userDebtAttornMapper.deleteById(new BigDecimal(2));
// 		 if(count > 0){
// 			 System.out.println("=============================删除成功");
// 		 }else{
// 			 System.out.println("=============================删除失败");
// 		 }
//	 }
//	 
//	  /**
//	  * 
//	 * @Title: insertSelective 
//	 * @Description: TODO(新增 测试) 
//	 * @param   参数说明 
//	 * @return void    返回类型 
//	 * @author chenjiaming
//	 * @throws
//	  */
// 	 @Test
//	 public void insertSelective(){
// 		 for(int i = 0 ;i<=230;i++){
//  			 UserDebtAttorn userDebtAttorn = new UserDebtAttorn();
// 			 BigDecimal bd = new BigDecimal(i);
// 			 userDebtAttorn.setId(bd);
//  			 int count = 0;
// 			 count = userDebtAttornMapper.insertSelective(userDebtAttorn);
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
