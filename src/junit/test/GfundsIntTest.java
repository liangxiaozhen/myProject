package junit.test;

import com.ptpl.model.GfundsInt;
import com.ptpl.service.GfundsIntServiceI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author：liuqh
 * @return:
 * @remark：测试站岗利息的增查改
 */
public class GfundsIntTest {
	private GfundsIntServiceI gfundsIntService;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        gfundsIntService=(GfundsIntServiceI) ac.getBean("gfundsIntService");
    }
	
	
	@Test
	public void test(){
		System.out.println(gfundsIntService);
	}
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试插入非缺省属性的站岗利息
	 */
	@Test
	public void testAddGfundsInt_insert(){
//		GfundsInt gfundsInt = new GfundsInt();
//		gfundsInt.setAno("55");
//		gfundsInt.setAname("55站岗利息");
//		gfundsInt.setAddtime(new Date());
//		gfundsInt.setEndtime(new Date());
//		System.out.println(gfundsInt);
//		System.out.println(gfundsIntService.insert(gfundsInt)+"88888888");
		for(int i=1;i<=50;i++){
			GfundsInt gfundsInt = new GfundsInt();
			gfundsInt.setGfundsintno("站岗利息编号"+i);
			gfundsInt.setClearmethod((short)1);
			gfundsInt.setUgrade("0011010");
			gfundsInt.setMinmoney(0.00);
			gfundsInt.setMaxmoney(500.00);
			gfundsInt.setQuota(10.00);
			gfundsInt.setDayawardrate(0.02);
			gfundsInt.setMaxcompensate(50.00);
			gfundsInt.setIstemplet((short)0);
			
			gfundsIntService.insert(gfundsInt);
		}
	}
	
	
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：根据任意条件查站岗利息列表
	 */
	@Test
	public void testSelectTenderItemByCondition(){
		GfundsInt condition = new GfundsInt();
		condition.setId(new BigDecimal(2));
		condition.setGfundsintno("站岗利息编号111");
		List<GfundsInt> gfundsIntList = gfundsIntService.selectByCondition(condition);
		for (GfundsInt gfundsInt : gfundsIntList) {
			System.out.println(gfundsInt);
		}
	}
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试修改站岗利息
	 */
	@Test
	public void testUpdateGfundsInt(){
		GfundsInt gfundsInt = new GfundsInt();
		gfundsInt.setId(new BigDecimal(54));
		gfundsInt.setGfundsintno("站岗利息编号xx");
		gfundsInt.setClearmethod((short)1);
		gfundsInt.setUgrade("1100");
		gfundsIntService.update(gfundsInt);
	}

	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试删除站岗利息
	 */
	@Test
	public void testDeleteGfundsInt(){
		gfundsIntService.delete(new BigDecimal(54));
	}


}