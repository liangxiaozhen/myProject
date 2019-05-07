package junit.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.OverdueCompensate;
import com.ptpl.service.OverdueCompensateServiceI;
/**
 * @author:liuqh
 * @date:2016年07月14日 14:13:02
 * @description:标的逾期代偿设置
 */
public class OverdueCompensateTest {
	private OverdueCompensateServiceI overdueCompensateService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        overdueCompensateService=(OverdueCompensateServiceI) ac.getBean("overdueCompensateService");
    }
	
	
	@Test
	public void test(){
		System.out.println(overdueCompensateService);
	}


	@Test
	public void testAddOverdueCompensate_insert(){
		for(int i=1;i<=50;i++){
			OverdueCompensate overdueCompensate = new OverdueCompensate();
			overdueCompensate.setOverduecno("编号"+i);
			overdueCompensate.setCmanno("代偿人"+i);
			overdueCompensate.setGraceperiod(2);
			overdueCompensate.setDaylatefeerate(2.2);
			overdueCompensate.setIsprecoveryon((short)1);
			overdueCompensate.setPmiscrecman("小C"+i);
			overdueCompensate.setOcminmoney(3.33);
			overdueCompensate.setOcmaxmoney(55.3);
			overdueCompensate.setOccquota(6.6);
			overdueCompensate.setToccrate(3.45);
			overdueCompensate.setMincfees(6.5);
			overdueCompensate.setMaxcfees(7.6);
			overdueCompensate.setIsupayon((short)1);
			overdueCompensate.setUgrade("1101");
			overdueCompensate.setPfprincipalrate(3.54);
			overdueCompensate.setMaxpfprincipal(34.4);
			overdueCompensate.setPfintrate(1.2);
			overdueCompensate.setMaxpfint(2.3);
			overdueCompensate.setLatefeerate(1.3);
			overdueCompensate.setMaxlatefee(333.33);
			overdueCompensate.setIstemplet((short)1);
			overdueCompensateService.insert(overdueCompensate);
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		OverdueCompensate condition = new OverdueCompensate();
//		condition.setId(new BigDecimal(3));
		condition.setIstemplet((short)0);
		List<OverdueCompensate> overdueCompensateList = overdueCompensateService.selectByCondition(condition);
		for (OverdueCompensate overdueCompensate : overdueCompensateList) {
			System.out.println(overdueCompensate);
		}
	}
	

	@Test
	public void testUpdateOverdueCompensate(){
		OverdueCompensate overdueCompensate = new OverdueCompensate();
		overdueCompensate.setId(new BigDecimal(50));
		overdueCompensate.setIstemplet((short)1);
		overdueCompensateService.update(overdueCompensate);
	}
	

	@Test
	public void testDeleteOverdueCompensate(){
		overdueCompensateService.delete(new BigDecimal(52));
	}
	

}
