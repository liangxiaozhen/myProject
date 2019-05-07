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

 import com.ptpl.mapper.UserRedEnvelopeMapper;
import com.ptpl.mapper.UserRedEnvelopeMapper;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.UserRedEnvelopeServiceI;
/**
 * 
 * 投标增益使用关联 测试类
 * UserRedEnvelopeTest
 * 创建人:cjm
 * 时间：2016年11月12日 16:06:13
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserRedEnvelopeTest {

	 @Autowired
	 private UserRedEnvelopeServiceI userRedEnvelopeMapper;
	 
  	 
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
		 
		 UserRedEnvelope userInterestRateCoupon = userRedEnvelopeMapper.findUserRedEnvelopeById(new BigDecimal(890)) ;
		  System.out.println(userInterestRateCoupon);
	 }
	 @Test
	 public void text(){
		 UserRedEnvelope envelope = new UserRedEnvelope();
		 envelope.setRedenvelope(10.00);
		 envelope.setRetype((short)3);
		 envelope.setId(new BigDecimal(890));
		 userRedEnvelopeMapper.insertSelective(envelope);
	 }
	  
}
