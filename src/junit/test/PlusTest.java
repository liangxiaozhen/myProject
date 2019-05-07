package junit.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.Plus;
import com.ptpl.service.PlusServiceI;
/**
 * @author:liuqh
 * @date:2016年07月14日 14:48:01
 * @description:标的增益设置
 */
public class PlusTest {
	private PlusServiceI plusService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        plusService=(PlusServiceI) ac.getBean("plusService");
    }
	
	
	@Test
	public void test(){
		System.out.println(plusService);
	}


	@Test
	public void testAddPlus_insert(){
		for(int i=1;i<=50;i++){
			Plus plus = new Plus();
			plus.setPlusno("编号"+i);
			plus.setIsaint((short)1);
			plus.setAonceint(2);
			plus.setAtotalint(1);
			plus.setAoneqrofit(11.2);
			plus.setIsavoucher((short)1);
			plus.setAoncevoucher(2);
			plus.setAtotalvoucher(2);
			plus.setAonevamount(1.23);
			plus.setIsalikevoucher((short)1);
			plus.setAoncelikevoucher(3);
			plus.setAtotallikevoucher(1);
			plus.setAonelvamount(3.33);
			plus.setAonceplusmode(4);
			plus.setAtotalplusmode(2);
			plus.setPayforplusman("增益清算付款人"+i);
			plus.setClearmode((short)1);
			plus.setIstemplet((short)1);
			plusService.insert(plus);
			
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		Plus condition = new Plus();
//		condition.setId(new BigDecimal(49));
		condition.setIstemplet((short)0);
		List<Plus> plusList = plusService.selectByCondition(condition);
		for (Plus plus : plusList) {
			System.out.println(plus);
		}
	}
	

	@Test
	public void testUpdatePlus(){
		Plus plus = new Plus();
		plus.setId(new BigDecimal(50));
		plus.setIstemplet((short)2);
		plusService.update(plus);
	}
	

	@Test
	public void testDeletePlus(){
		plusService.delete(new BigDecimal(50));
	}
	

}
