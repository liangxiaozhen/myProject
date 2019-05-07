package junit.test;

import com.ptpl.model.GuaranteeFee;
import com.ptpl.service.GuaranteeFeeServiceI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author:liuqh
 * @date:2016年07月12日 22:44:14
 * @description:
 */
public class GuaranteeFeeTest {
	private GuaranteeFeeServiceI guaranteeFeeService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        guaranteeFeeService=(GuaranteeFeeServiceI) ac.getBean("guaranteeFeeService");
    }
	
	
	@Test
	public void test(){
		System.out.println(guaranteeFeeService);
	}


	@Test
	public void testAddGuaranteeFee_insert(){
		for(int i=1;i<=50;i++){
			GuaranteeFee guaranteeFee = new GuaranteeFee();
			guaranteeFee.setId(new BigDecimal(i));
			guaranteeFee.setGuaranteefeeno("担保费编号"+i);
			/*guaranteeFee.setGuaranteeid("担保公司Id");*/
			guaranteeFee.setGfrecman("担保服务费收款人");
			guaranteeFee.setChargetype((short)2);
			guaranteeFee.setMinnmmoney(11.1);
			guaranteeFee.setMaxnmmoney(22.3);
			guaranteeFee.setGfquota(20.0);
			guaranteeFee.setGfpercent(3.3);
			guaranteeFee.setMingffee(0.25);
			guaranteeFee.setMaxgffee(25.2);
			guaranteeFee.setGfrate(0.32);
			guaranteeFee.setMingfamount(0.52);
			guaranteeFee.setMaxgfamount(3.00);
			guaranteeFee.setIstemplet((short)0);
			
			guaranteeFeeService.insert(guaranteeFee);
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		GuaranteeFee condition = new GuaranteeFee();
//		condition.setId(new BigDecimal(1));
		condition.setChargetype((short)1);
		List<GuaranteeFee> guaranteeFeeList = guaranteeFeeService.selectByCondition(condition);
		for (GuaranteeFee guaranteeFee : guaranteeFeeList) {
			System.out.println(guaranteeFee);
		}
	}
	

	@Test
	public void testUpdateGuaranteeFee(){
		GuaranteeFee guaranteeFee = new GuaranteeFee();
		guaranteeFee.setId(new BigDecimal(49));
		guaranteeFee.setGuaranteefeeno("xxx");
		guaranteeFeeService.update(guaranteeFee);
	}
	

	@Test
	public void testDeleteGuaranteeFee(){
		guaranteeFeeService.delete(new BigDecimal(948));
	}
	
    @Test
    public void testSelectAll(){
        List<GuaranteeFee> guaranteeFees = guaranteeFeeService.selectAllWithoutOne(new BigDecimal(945));
        System.out.println(guaranteeFees.size());
    }
}
