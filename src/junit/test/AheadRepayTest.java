package junit.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.AheadRepay;
import com.ptpl.service.AheadRepayServiceI;
/**
 * @author:liuqh
 * @date:2016年07月12日 17:31:29
 * @description:
 */
public class AheadRepayTest {
	private AheadRepayServiceI aheadRepayService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        aheadRepayService=(AheadRepayServiceI) ac.getBean("aheadRepayService");
    }
	
	
	@Test
	public void test(){
		System.out.println(aheadRepayService);
	}


	@Test
	public void testAddAheadRepay_insert(){
		for(int i=2;i<=50;i++){
			AheadRepay aheadRepay = new AheadRepay();
			aheadRepay.setAheadrepayno("提前还款编号"+i);
			aheadRepay.setIspicompensateon((short)0);
			aheadRepay.setMinnoreceiveint(0.05);
			aheadRepay.setMaxnoreceiveint(0.3);
			aheadRepay.setAwardtype((short)1);
			aheadRepay.setLoanpenaltyname("借款人罚金奖励名称"+i);
			aheadRepay.setPenaltyquota(100.0);
			aheadRepay.setPenaltyrate(0.1);
			aheadRepay.setMaxpenalty(1000.00);
			aheadRepay.setPawardname("平台奖励名称"+i);
			aheadRepay.setPawardquota(50.0);
			aheadRepay.setPawardrate(0.05);
			aheadRepay.setMaxpaward(50.0);
			aheadRepay.setIspluscompensateon((short)0);
			aheadRepay.setMinplusnoreceiveint(0.03);
			aheadRepay.setMaxplusnoreceiveint(0.06);
			aheadRepay.setPlusawardtype((short)1);
			aheadRepay.setPluspenaltyname("增益平台罚金奖励名称"+i);
			aheadRepay.setPluspenaltyquota(10.0);
			aheadRepay.setPluspenaltyrate(0.2);
			aheadRepay.setPlusmaxpenalty(6.01);
			aheadRepay.setPluspawardname("增益平台奖励名称"+i);
			aheadRepay.setPluspawardquota(20.0);
			aheadRepay.setPluspawardrate(0.3);
			aheadRepay.setPlusmaxpaward(10.2);
			aheadRepay.setIsforplatformon((short)1);
			aheadRepay.setAwardrecman("奖励平台收款人"+i);
			aheadRepay.setMinallnoreceiveint(0.25);
			aheadRepay.setMaxallnoreceiveint(1.36);
			aheadRepay.setAwardplatquota(0.36);
			aheadRepay.setAwardplatrate(0.34);
			aheadRepay.setAwardplatminmoney(0.85);
			aheadRepay.setAwardplatmaxmoney(2.35);
			aheadRepay.setIstemplet((short)1);
			
			aheadRepayService.insert(aheadRepay);
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		AheadRepay condition = new AheadRepay();
		condition.setIspicompensateon((short)1);
//		condition.setId(new BigDecimal(2));
		List<AheadRepay> aheadRepayList = aheadRepayService.selectByCondition(condition);
		for (AheadRepay aheadRepay : aheadRepayList) {
			System.out.println(aheadRepay);
		}
	}
	

	@Test
	public void testUpdateAheadRepay(){
		AheadRepay aheadRepay = new AheadRepay();
		aheadRepay.setId(new BigDecimal(2));
		aheadRepay.setIspicompensateon((short)0);
		aheadRepayService.update(aheadRepay);
	}
	

	@Test
	public void testDeleteAheadRepay(){
		aheadRepayService.delete(new BigDecimal(2));
	}
	

}
