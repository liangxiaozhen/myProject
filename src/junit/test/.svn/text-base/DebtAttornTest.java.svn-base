package junit.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.CancelValidate;
import com.ptpl.model.DebtAttorn;
import com.ptpl.service.CancelValidateServiceI;
import com.ptpl.service.DebtAttornServiceI;
/**
 * @author:liuqh
 * @date:2016年07月12日 19:17:23
 * @description:标的债权转让设置测试
 */
public class DebtAttornTest {
	private DebtAttornServiceI debtAttornService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        debtAttornService=(DebtAttornServiceI) ac.getBean("debtAttornService");
    }
	
	@Test
	public void test(){
		System.out.println(debtAttornService);
	}


	@Test
	public void testAddDebtAttorn_insert(){
		for(int i=2;i<=50;i++){
			DebtAttorn debtAttorn = new DebtAttorn();
			debtAttorn.setDebtattornno("债权转让编号"+i);
			debtAttorn.setIsdebtaudit((short)1);
			debtAttorn.setIsocdebt((short)1);
			debtAttorn.setAownergrade("111");
			debtAttorn.setRemovenameno("债权转让编号"+i);
			debtAttorn.setHoldday(12);
			debtAttorn.setIntervalday(25);
			debtAttorn.setIsasplit((short)1);
			debtAttorn.setAttornmoney(11.00);
			debtAttorn.setMinattornratio(12.00);
			debtAttorn.setMaxattornratio(23.01);
			debtAttorn.setApurchasergrade("1001");
			debtAttorn.setNoapnameno("ddd");
			debtAttorn.setIsabuyallorpart((short)1);
			debtAttorn.setIsadafeeon((short)1);
			debtAttorn.setUgrade("101");
			debtAttorn.setMinattornmoney(25.01);
			debtAttorn.setMaxattornmoney(12.05);
			debtAttorn.setQuota(3.36);
			debtAttorn.setAttornrate(5.23);
			debtAttorn.setMinfee(3.33);
			debtAttorn.setMaxfee(5.02);
			debtAttorn.setIstemplet((short)1);
			debtAttornService.insert(debtAttorn);
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		DebtAttorn condition = new DebtAttorn();
//		condition.setId(new BigDecimal(5));
		condition.setIsdebtaudit((short)2);
		List<DebtAttorn> debtAttornList = debtAttornService.selectByCondition(condition);
		for (DebtAttorn debtAttorn : debtAttornList) {
			System.out.println(debtAttorn);
		}
	}
	

	@Test
	public void testUpdateDebtAttorn(){
		DebtAttorn debtAttorn = new DebtAttorn();
		debtAttorn.setId(new BigDecimal(5));
		debtAttorn.setIsdebtaudit((short)2);
		debtAttornService.update(debtAttorn);
	}
	

	@Test
	public void testDeleteDebtAttorn(){
		debtAttornService.delete(new BigDecimal(3));
	}
	

}
