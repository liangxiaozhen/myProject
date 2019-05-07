package junit.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.MediacyFee;
import com.ptpl.service.MediacyFeeServiceI;
/**
 * @author:liuqh
 * @date:2016年07月13日 17:47:06
 * @description:标的居间费设置
 */
public class MediacyFeeTest {
	private MediacyFeeServiceI mediacyFeeService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        mediacyFeeService=(MediacyFeeServiceI) ac.getBean("mediacyFeeService");
    }
	
	
	@Test
	public void test(){
		System.out.println(mediacyFeeService);
	}


	@Test
	public void testAddMediacyFee_insert(){
		for(int i=1;i<=50;i++){
			MediacyFee mediacyFee = new MediacyFee();
			mediacyFee.setMediacyfeeno("编号"+i);
			mediacyFee.setMrecman("小C");
			mediacyFee.setChargetype((short)1);
			mediacyFee.setMinnmmoney(33.3);
			mediacyFee.setMaxnmmoney(55.3);
			mediacyFee.setMfquota(12.2);
			mediacyFee.setMfpercent(2.3);
			mediacyFee.setMinmffee(33.33);
			mediacyFee.setMaxmffee(3.4);
			mediacyFee.setMfrate(0.32);
			mediacyFee.setMaxmffee(1.2);
			mediacyFee.setIstemplet((short)1);
			
			mediacyFeeService.insert(mediacyFee);
		}
	}
}
