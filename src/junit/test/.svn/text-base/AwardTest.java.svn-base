package junit.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.Award;
import com.ptpl.service.AwardServiceI;
/**
 * @author：liuqh
 * @return:
 * @remark：测试奖品：增删查改
 */
public class AwardTest {
	private AwardServiceI awardService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        awardService=(AwardServiceI) ac.getBean("awardService");
    }
	
	
	@Test
	public void test(){
		System.out.println(awardService);
	}
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试插入非缺省属性的奖品
	 */
	@Test
	public void testAddAward_insert(){
//		Award award = new Award();
//		award.setAno("55");
//		award.setAname("55奖品");
//		award.setAddtime(new Date());
//		award.setEndtime(new Date());
//		System.out.println(award);
//		System.out.println(awardService.insert(award)+"88888888");
		for(int i=100;i<=100;i++){
			Award award = new Award();
			award.setAno(String.valueOf(i));
			award.setAname("奖品"+i);
			award.setAtype((short)1);
			award.setAttribute((short)1);
			award.setAllswitch((short)0);
			award.setSubswitch((short)0);
			award.setTradetype((short)1);
			award.setEndtime(new Date());
			award.setWcrestdate("2016年5月23日,2016年7月8日");
			award.setWcrestrict(1000.00);
			award.setWctype((short)0);
			award.setTattribute("134");
			award.setTdayrestrict(26);
			award.setTmlrrestrict(0.05);
			award.setTmhrrestrict(0.12);
			award.setTrtype((short)1);
			award.setTminmoney(100.00);
			award.setTmaxmoney(10000.00);
			award.setCashorvoucher(5.00);
			award.setPoints(100L);
			award.setQuantityall(1000L);
			award.setQuantityrest(1000L);
			award.setAddtime(new Date());
			award.setAddman("后台管理员"+i);
			award.setRemark("这只是一个备注"+i);
			award.setIscancel((short) 1);
			award.setUgrade("等级1");
			award.setUsagerights((short)1);
			award.setSpecialtno("信用标");
			award.setRemovenameno("排除名单1");
			awardService.insert(award);
		}
	}
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：根据任意条件查奖品列表
	 */
	@Test
	public void testSelectTenderItemByCondition(){
		Award condtition = new Award();
		condtition.setRemark("这只是一个备注37");
		List<Award> awardList = awardService.selectByCondition(condtition);
		for (Award award : awardList) {
			System.out.println(award);
		}
	}
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试修改奖品
	 */
	@Test
	public void testUpdateAward(){
		Award award = new Award();
		award.setId(new BigDecimal(221));
		award.setRemark("kkkk");
		awardService.update(award);
	}
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试删除奖品
	 */
	@Test
	public void testDeleteAward(){
		awardService.delete(new BigDecimal(221));
	}
	
	/**
	 * @author：liuqh
	 * @return:Date 
	 * @remark：该方法可以让传进去的日期加i天并返回
	 */
	public Date addDate(Date date,int i){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, i);
		return cal.getTime();
	}

}
