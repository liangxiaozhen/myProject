package junit.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.SpecialTime;
import com.ptpl.service.SpecialTimeServiceI;

public class SpecialTimeTest {
	private SpecialTimeServiceI specialTimeService;

	@Before
	public void before()
	{
		// 使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{
				"spring/spring-mvc.xml", "spring/applicationContext-*.xml"});
		// 从Spring容器中根据bean的id取出我们要使用的userService对象
		specialTimeService = (SpecialTimeServiceI) ac
				.getBean("specialTimeService");
	}
	@Test
	public void test()
	{
		List<SpecialTime> list = specialTimeService.selective(null);
		System.out.println(list.size());
	}
	@Test
	public void add()
	{
		SpecialTime sTime = new SpecialTime();
		sTime.setTimetype("提现");
		sTime.setTimename("节假日");
		sTime.setTimeno("GJTDSJ20160705135213");
		sTime.setWlevel((short) 1);
		sTime.setBtime(new Date());
		sTime.setEtime(new Date(116, 9, 7, 12, 54, 32));
		sTime.setAddtime(new Date());
		sTime.setAddman("黄飞鸿");
		sTime.setRemark("黄飞鸿参观总部，人员不够");
		sTime.setMinmoney(0d);
		sTime.setMaxmoney(5000d);
		for (int i = 0; i < 20; i++)
		{
			specialTimeService.insertSelective(sTime);
		}
	}
}
