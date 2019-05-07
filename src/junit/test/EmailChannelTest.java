package junit.test;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.model.EmailChannel;
import com.ptpl.service.EmailChannelServiceI;
/**
 * 
 * 用户授权记录 测试类
 * EmailChannelTest
 * 创建人:cjm
 * 时间：2017年03月16日 16:56:54
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class EmailChannelTest {

	 @Autowired
	 private EmailChannelServiceI emailChannelMapper;
	 
  	 
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
	 public void test(){
		 EmailChannel emailChannel = new EmailChannel();
		 emailChannel.setIsuse((short)1);//是否启用1禁用0启用
		 List<EmailChannel> emailChannels = emailChannelMapper.findEmailChannelsByEmailChannel(emailChannel);
		 boolean falg = false;
		 if(emailChannels.size() > 0){
			 System.out.println(emailChannels.size()+"============");
			 EmailChannel channel2 = null;
			 Random random = new Random();
			 if(emailChannels.size() == 1){
				 channel2 = emailChannels.get(0);
			 }else{
				 int j =  random.nextInt(emailChannels.size() - 1);
  				 System.out.println(j);
				 channel2 = emailChannels.get(j);
			 }
  		 }
	 }
	  
}
