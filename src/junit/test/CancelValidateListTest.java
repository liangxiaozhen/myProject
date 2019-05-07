package junit.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.CancelValidate;
import com.ptpl.service.CancelValidateServiceI;
import com.ptpl.service.DebtAttornServiceI;

public class CancelValidateListTest {
	private CancelValidateServiceI cancelValidateService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        cancelValidateService=(CancelValidateServiceI) ac.getBean("cancelValidateService");
    }
	
	@Test
	public void text(){
	  CancelValidate validate=cancelValidateService.getByUsername("上门服务");
	  System.out.println(validate.getUsername());
	}
}
