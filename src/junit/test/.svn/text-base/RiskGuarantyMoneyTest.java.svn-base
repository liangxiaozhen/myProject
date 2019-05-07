package junit.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
/**
 * @author:liuqh
 * @date:2016年07月14日 15:09:51
 * @description:标的风险保证金设置
 */
public class RiskGuarantyMoneyTest {
	private RiskGuarantyMoneyServiceI riskGuarantyMoneyService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        riskGuarantyMoneyService=(RiskGuarantyMoneyServiceI) ac.getBean("riskGuarantyMoneyService");
    }
	
	
	@Test
	public void test(){
		System.out.println(riskGuarantyMoneyService);
	}


	@Test
	public void testAddRiskGuarantyMoney_insert(){
		for(int i=1;i<=50;i++){
			RiskGuarantyMoney riskGuarantyMoney = new RiskGuarantyMoney();
			riskGuarantyMoney.setRiskgmno("编号"+i);
			riskGuarantyMoney.setRgmrecman("小A"+i);
			riskGuarantyMoney.setChargetype((short)1);
			riskGuarantyMoney.setMinrgmmoney(11.2);
			riskGuarantyMoney.setMaxrgmmoney(33.33);
			riskGuarantyMoney.setRgmquota(33.0);
			riskGuarantyMoney.setRgmpercent(1.2);
			riskGuarantyMoney.setMaxrgmfee(8.3);
			riskGuarantyMoney.setUgrade("0101");
			riskGuarantyMoney.setRgmrate(1.23);
			riskGuarantyMoney.setMaxrgmamount(333.0);
			riskGuarantyMoney.setIstemplet((short)1);
			
			riskGuarantyMoneyService.insert(riskGuarantyMoney);
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		RiskGuarantyMoney condition = new RiskGuarantyMoney();
		condition.setId(new BigDecimal(49));
//		condition.setIstemplet((short)0);
		List<RiskGuarantyMoney> riskGuarantyMoneyList = riskGuarantyMoneyService.selectByCondition(condition);
		for (RiskGuarantyMoney riskGuarantyMoney : riskGuarantyMoneyList) {
			System.out.println(riskGuarantyMoney);
		}
	}
	

	@Test
	public void testUpdateRiskGuarantyMoney(){
		RiskGuarantyMoney riskGuarantyMoney = new RiskGuarantyMoney();
		riskGuarantyMoney.setId(new BigDecimal(50));
		riskGuarantyMoney.setIstemplet((short)2);
		riskGuarantyMoneyService.update(riskGuarantyMoney);
	}
	

	@Test
	public void testDeleteRiskGuarantyMoney(){
		for(int i=1;i<=50;i++){
			riskGuarantyMoneyService.delete(new BigDecimal(i));
		}
	}
	

}
