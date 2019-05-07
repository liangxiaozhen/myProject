package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.RemoveName;
import com.ptpl.service.RemoveNameServiceI;

public class RemoveNameTest {
	private RemoveNameServiceI removeNameService;

	@Before
	public void before()
	{
		// 使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{
				"spring/spring-mvc.xml", "spring/applicationContext-*.xml"});
		// 从Spring容器中根据bean的id取出我们要使用的userService对象
		removeNameService = (RemoveNameServiceI) ac
				.getBean("removeNameService");
	}
	@Test
	public void test()
	{
		List<RemoveName> removeNames = removeNameService.selective(null);
		System.out.println(removeNames.size());
	}
	@Test
	public void add()
	{
		for (int i = 1; i <= 200; i++)
		{
			RemoveName removeName = new RemoveName();
			if (i % 2 == 0)
			{
				removeName.setNametype("提现");
				removeName.setNameno("GJPCRYMD20160630");
				removeName.setName("二级羊毛客");
				removeName.setLoginname("歉意" + i);
				removeName.setRealname("酷狗蘑菇" + i);
				removeName.setAddman("老司机");
				removeName.setRemark("羊毛客");
			} else
			{
				removeName.setNametype("充值");
				removeName.setNameno("GJPCRYMD20160821");
				removeName.setName("一级小号");
				removeName.setLoginname("龙啸" + i);
				removeName.setRealname("大哥" + i);
				removeName.setAddman("卷笔刀");
				removeName.setRemark("小号");
			}
			removeName.setBaseid(new BigDecimal(i));
			removeName.setAddtime(new Date());
			removeNameService.insertSelective(removeName);
		}
	}
	@Test
	public void select(){
		String str=removeNameService.selectNameNoForName("一级小号");
		System.out.println(str);
	}

}
