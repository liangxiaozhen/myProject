package junit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.controller.huifu.OraceTest;
import com.ptpl.model.DividedPayments;
import com.ptpl.service.OverdueRepayMentLogicDealI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
"classpath:mybatis/mybatis-config.xml"})
public class OverdueRepayMentTest {

	@Autowired
	private OverdueRepayMentLogicDealI overdueRepaymentLogicDealI;
	
	@Test
	public void test(){
		DividedPayments dividedPayments = OraceTest.getDividedPayment();
		boolean flag = overdueRepaymentLogicDealI.isOverdue(dividedPayments);
		System.out.println(flag+"=========");
	}
	
	@Test
	public void test2(){
		DividedPayments dividedPayments = OraceTest.getDividedPayment();
		Integer overDay = overdueRepaymentLogicDealI.getOverdueDay(dividedPayments);
		System.out.println(overDay);
	}
	
}
