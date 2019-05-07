package junit.test;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.UserTender;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import com.ptpl.web.util.GenerateGfundsIntRecordUtil;

public class Test1 {
	
	private UserTenderServiceI usertenderService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        usertenderService = (UserTenderServiceI) ac.getBean("userTenderService");
    }
	
	@Test
	public void test(){
//		GenerateGfundsIntRecordUtil.generateGfundsIntRecord(new BigDecimal("1501"));
		UserTender ut = new UserTender();
		ut.setId(new BigDecimal(1630));
		ut.setTstatus((short) 1);
		usertenderService.updateByPrimaryKeySelective(ut);
		ut.setTstatus((short) 0);
		usertenderService.updateByPrimaryKeySelective(ut);
	}
	

}
