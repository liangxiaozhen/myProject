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

 import com.ptpl.mapper.ActivityRuleMapper;
import com.ptpl.mapper.ActivityRuleMapper;
 import com.ptpl.model.ActivityRule;
import com.ptpl.service.ActivityRuleServiceI;
/**
 * 
 * 标的活动规则 测试类
 * ActivityRuleTest
 * 创建人:chenjiaming
 * 时间：2016年08月25日 12:03:21
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class ActivityRuleTest {

	 @Autowired
	 private ActivityRuleServiceI activityRuleMapper;
	 
  	 
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
	 * @Title: findActivityRules 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findActivityRules(){
	     ActivityRule activityRule = new  ActivityRule();
	    // activityRule.setId(new BigDecimal(25));
	     activityRule.setStatus((short)2);
		 List<ActivityRule> activityRules = activityRuleMapper.findActivityRules(activityRule);
  		 for(ActivityRule activityRule1 :activityRules){
			System.out.println("=========================="+activityRule1.getId());
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
		 ActivityRule activityRule = new ActivityRule();
		 activityRule.setId(new BigDecimal(128));
		 activityRule.setAuditman("小胖子");
		 activityRule.setAudittime(new Date());
 		 int count = activityRuleMapper.updateById(activityRule);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	 /**
	  * 
	 * @Title: findActivityRuleById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findActivityRuleById(){
 		
		 ActivityRule activityRule= activityRuleMapper.findActivityRuleById(new BigDecimal(25));
		 System.out.println(activityRule.getId());
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
  		 int count = activityRuleMapper.deleteById(new BigDecimal(25));
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
 		 for(int i = 23;i<=40;i++){
   			 ActivityRule activityRule = new ActivityRule();
   			activityRule.setId(new BigDecimal(i));
   			activityRule.setActbtime(new Date());
 			activityRule.setActetime(new Date());
 			activityRule.setActname("123123123");
 			activityRule.setActno("123123123");
 			activityRule.setActtype((short)3);
 			activityRule.setAuditman("123123123");
 			activityRule.setAudittime(new Date());
 			activityRule.setCanceltno("123123123");
 			activityRule.setCrestrict("123123123");
 			activityRule.setGtype((short)3);
 			activityRule.setIsaudit((short)3);
 			activityRule.setIsauditalist((short)3);
 			activityRule.setRemark("123123123");
 			activityRule.setRemovenameno("123123123");
 			activityRule.setSettime(new Date());
 			activityRule.setSpecifytno("123123123");
 			activityRule.setStatus((short)3);
 			activityRule.setTattribute("123123123");
 			activityRule.setTctype((short)3);
 			activityRule.setTmhrrestrict(123123123.00);
 			activityRule.setTmlrrestrict(123123123.00);
 			activityRule.setUgrade("1000000123123123000000000000000000000000");
  			 int count = 0;
 			 count = activityRuleMapper.insert(activityRule);
 			if(count > 0){
 				System.out.println("数据添加成功.."+i );
 			}else{
 				System.out.println("数据添加失败" +i);
 			}
  
 		 }
 	 }
}
