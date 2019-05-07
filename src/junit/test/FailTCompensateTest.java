package junit.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.FailTCompensate;
import com.ptpl.service.FailTCompensateServiceI;
/**
 * @author:liuqh
 * @date:2016年07月12日 11:06:22
 * @description:流标补偿设置
 */
public class FailTCompensateTest {
	private FailTCompensateServiceI failTCompensateService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        failTCompensateService=(FailTCompensateServiceI) ac.getBean("failTCompensateService");
    }
	
	
	@Test
	public void test(){
		System.out.println(failTCompensateService);
	}


	@Test
	public void testAddFailTCompensate_insert(){
		for(int i=1;i<=50;i++){
			FailTCompensate failTCompensate = new FailTCompensate();
			failTCompensate.setFailtcno("流标补偿编号"+i);
			failTCompensate.setIsintcompensateon((short)0);
			failTCompensate.setIntugrade("1111");
			failTCompensate.setMinmoney(1.00);
			failTCompensate.setMaxmoney(10.00);
			failTCompensate.setQuota(5.00);
			failTCompensate.setDayawardrate(0.01);
			failTCompensate.setMaxcompensate(12.00);
			failTCompensate.setIsawardcompensateon((short)0);
			failTCompensate.setAwardname("小A");
			failTCompensate.setAwardquota(100.00);
			failTCompensate.setAwardrate(0.02);
			failTCompensate.setAwardmaxmoney(10.2);
			failTCompensate.setIstemplet((short)0);
			failTCompensateService.insert(failTCompensate);
		}
	}
	
	@Test
	public void testSelectTenderItemByCondition(){
		FailTCompensate condition = new FailTCompensate();
//		condition.setId(new BigDecimal(1));
		condition.setIstemplet((short)1);
		List<FailTCompensate> failTCompensateList = failTCompensateService.selectByCondition(condition);
		for (FailTCompensate failTCompensate : failTCompensateList) {
			System.out.println(failTCompensate);
		}
	}
	

	@Test
	public void testUpdateFailTCompensate(){
		FailTCompensate failTCompensate = new FailTCompensate();
		failTCompensate.setId(new BigDecimal(50));
		failTCompensate.setIsintcompensateon((short)1);
		failTCompensateService.update(failTCompensate);
	}
	

	@Test
	public void testDeleteFailTCompensate(){
		failTCompensateService.delete(new BigDecimal(50));
	}
	

}
