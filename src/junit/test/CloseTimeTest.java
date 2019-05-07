package junit.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.CloseTime;
import com.ptpl.service.CloseTimeServiceI;

public class CloseTimeTest {
	private CloseTimeServiceI closeTimeService;

	@Before
	public void before()
	{
		// 使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{
				"spring/spring-mvc.xml", "spring/applicationContext-*.xml"});
		// 从Spring容器中根据bean的id取出我们要使用的userService对象
		closeTimeService = (CloseTimeServiceI) ac.getBean("closeTimeService");
	}
	@Test
	public void test()
	{
		List<CloseTime> close = closeTimeService.selective(null);
		System.out.println(close.size());
	}
	@Test
	public void add()
	{
		for(int i=0;i<50;i++){
		CloseTime cTime = new CloseTime();
		cTime.setAddman("大力神");
		cTime.setAddtime(new Date());
		cTime.setBtime(new Date(new Date().getTime() + 654200));
		cTime.setEtime(new Date(new Date().getTime() + 2654200));
		cTime.setTimetype("提现");
		//cTime.setTimeno("GJGBSJ20160709");
		cTime.setTimename("节假日");
		closeTimeService.insertSelective(cTime);
		}
	}
}
