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

 import com.ptpl.mapper.UserInterestRateCouponMapper;
import com.ptpl.mapper.UserInterestRateCouponMapper;
 import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.service.UserInterestRateCouponServiceI;
/**
 * 
 * 投标增益使用关联 测试类
 * UserInterestRateCouponTest
 * 创建人:cjm
 * 时间：2016年11月12日 16:06:45
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserInterestRateCouponTest {

	 @Autowired
	 private UserInterestRateCouponServiceI userInterestRateCouponMapper;
	 
  	 
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
	 public void test1(){
		 
 		UserInterestRateCoupon userInterestRateCoupon = userInterestRateCouponMapper.findUserInterestRateCouponById(new BigDecimal(889)) ;
		  System.out.println(userInterestRateCoupon);
	 }
	 @Test
	 public void test(){
		 UserInterestRateCoupon userInterestRateCoupon = new UserInterestRateCoupon();
		 userInterestRateCoupon.setId(new BigDecimal(889));
 		 userInterestRateCoupon.setIcrate(0.01);
 		 int count = 0;
 		 count = userInterestRateCouponMapper.insertSelective(userInterestRateCoupon);
		 if(count > 0){
			 System.out.println("=============");
		 }
	 }
	   
  
}
