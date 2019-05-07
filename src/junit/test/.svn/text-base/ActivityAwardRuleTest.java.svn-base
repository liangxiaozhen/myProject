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

 import com.ptpl.mapper.ActivityAwardRuleMapper;
import com.ptpl.mapper.ActivityAwardRuleMapper;
 import com.ptpl.model.ActivityAwardRule;
import com.ptpl.service.ActivityAwardRuleServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 标的活动奖励规则 测试类
 * ActivityAwardRuleTest
 * 创建人:chenjiaming
 * 时间：2016年08月26日 16:14:51
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class ActivityAwardRuleTest {

	 @Autowired
	 private ActivityAwardRuleServiceI activityAwardRuleMapper;
	 
  	 
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
	 * @Title: findActivityAwardRules 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findActivityAwardRules(){
	     ActivityAwardRule activityAwardRule = new  ActivityAwardRule();
	     //activityAwardRule.setActid(new BigDecimal(2));
		 List<ActivityAwardRule> activityAwardRules = activityAwardRuleMapper.findActivityAwardRules(activityAwardRule);
  		 for(ActivityAwardRule activityAwardRule1 :activityAwardRules){
			System.out.println("=========================="+activityAwardRule);
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
		 ActivityAwardRule activityAwardRule = new ActivityAwardRule();
		 activityAwardRule.setId(new BigDecimal(33));
		 activityAwardRule.setAuditman("小胖子");
		 activityAwardRule.setAudittime(new Date());
 		 int count = activityAwardRuleMapper.updateById(activityAwardRule);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findActivityAwardRuleById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findActivityAwardRuleById(){
 		
		 ActivityAwardRule activityAwardRule= activityAwardRuleMapper.findActivityAwardRuleById(new BigDecimal(2));
		 System.out.println(activityAwardRule.getId()+"==="+activityAwardRule.getAuditman());
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
  		 int count = activityAwardRuleMapper.deleteById(new BigDecimal(2));
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
   			 ActivityAwardRule activityAwardRule = new ActivityAwardRule();
   			activityAwardRule.setActid(new BigDecimal(21));
  			activityAwardRule.setAuditman("蒋学友1");
  			activityAwardRule.setAudittime(new Date());
  			activityAwardRule.setAwardclass("累投1");
  			activityAwardRule.setAwardcopies(15);
  			activityAwardRule.setAwardid(new BigDecimal(11));
  			activityAwardRule.setAwardmax(101.00);
  			activityAwardRule.setAwardmin(11.00);
  			activityAwardRule.setAwardname("iphone1手机");
  			activityAwardRule.setAwardprize("奖项名称：带头大哥1");
  			activityAwardRule.setAwardratio(11.00);
  			activityAwardRule.setAwardtype((short)21);
  			activityAwardRule.setCookierestrict(31);
  			activityAwardRule.setDistributemode((short)21);
  			activityAwardRule.setDistributetype((short) 11);
  			activityAwardRule.setFirsttendermoney(1221.00);
  			activityAwardRule.setFirsttendertime(StringUtil.getDateByString("2016-08-06", "yyyy-MM-dd"));
  			activityAwardRule.setIprestrict(2);
  			activityAwardRule.setIsaudit((short)1);
  			activityAwardRule.setQuota(1002.00);
  			activityAwardRule.setRanking("金额排名1");
  			activityAwardRule.setRegeditdayrest(201);
  			activityAwardRule.setRemark("蒋学友很帅,他很烦恼1...");
  			activityAwardRule.setTmaxmoney(101.00);
  			activityAwardRule.setTminmoney(21.00);
  			 int count = 0;//Selective
 			 count = activityAwardRuleMapper.insertSelective(activityAwardRule);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  
 	 }
  
}
