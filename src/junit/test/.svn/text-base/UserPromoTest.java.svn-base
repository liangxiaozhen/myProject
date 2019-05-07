package junit.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.StringUtil;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.PromoAgentGradeProfitServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;

public class UserPromoTest {
	private UserPromoServiceI userPromoService;
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	private PromoAgentGradeProfitServiceI promoAgentGradeProfitService;
	private List<UserPromo> users = new ArrayList<UserPromo>();
	private int i = 0;
	@Before
	public void before()
	{
		// 使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[]{"spring/spring-mvc.xml", "spring/applicationContext-*.xml"});
		// 从Spring容器中根据bean的id取出我们要使用的userService对象

		userPromoService = (UserPromoServiceI) ac.getBean("userPromoService");
	}
	@Test
	public void tt()
	{
		System.out.println(userPromoService.selectBySupName("测试用户").size());
		
	}
	@Test
	public void test2()
	{
		PromoAgentGradeProfit p = new PromoAgentGradeProfit();
		p.setAddman("飞鸟");
		p.setAddtime(new Date());
		p.setProxygrade(3);
		p.setProfitgrade((short) 2);
		p.setProxygradename("黄金");
		p.setProfit(3d);
		p.setRemark("黄金推广");
		promoAgentGradeProfitService.insertSelective(p);
	}
	
	@Test
	public void test1()
	{
		UserPromoThirdParty userPromoThirdParty = new UserPromoThirdParty();
		for (int i = 1; i <= 10; i++)
		{
			userPromoThirdParty.setUpid(new BigDecimal(i));
			userPromoThirdParty.setThirdpartycode("6000020160327145342");
			userPromoThirdParty.setThirdpartyname("双乾支付");
			userPromoThirdParty.setIsopen((short) 0);
			userPromoThirdPartyService.insertSelective(userPromoThirdParty);
		}

	}
	@Test
	public void test()
	{
		UserPromo userPromo = new UserPromo();
		for (int i = 1; i <= 10; i++)
		{
			userPromo.setName("周君2" + i);
			userPromo.setLoginname("君哥1" + i);
			//userPromo.setProxygrade(1);
			userPromo.setSupname("老干部2" + i);
			userPromo.setSuploginname("老司机1" + i);
			userPromo.setIsopenfsinfo((short) 1);
			userPromo.setPromocode("IUdsM" + i);
			userPromo.setSuppromocode("PfdAS" + i);
			userPromo.setRegdate(new Date());
			userPromo.setPromonum(0l);
			userPromo.setPromofee(0d);
			userPromo.setAdminremark("分润5块");
			userPromo.setSubremark("下级");
			userPromo.setIsmodify((short) 0);
			userPromoService.insertSelective(userPromo);
		}
	}
	@Test
	public void select()
	{
		UserPromo user = userPromoService.selectByPrimaryKey(new BigDecimal("380"));
		if (user != null)
		{
			String supCode = user.getSuppromocode();
			if (StringUtil.isNotEmpty(supCode))
			{
				UserPromo user1 = userPromoService.selectByPromoCode(supCode);
				if (user1 != null)
				{
					String supCode1 = user.getSuppromocode();
					if (StringUtil.isNotEmpty(supCode1))
					{
						UserPromo user2 = userPromoService.selectByPromoCode(supCode1);
					}
				}
			}
		}
	}
	@Test
	public void selectBySupPromoCode()
	{
		UserPromo user = userPromoService.selectByPrimaryKey(new BigDecimal("374"));
		String supCode = user.getSuppromocode();
		if (StringUtil.isNotEmpty(supCode))
		{
			select1(supCode);
		}
		for (UserPromo userPromo : users)
		{
			System.out.println(userPromo.getName() + "=======" + userPromo.getLoginname());
		}
	}
	public void select1(String code)
	{
		UserPromo userPromo = userPromoService.selectByPromoCode(code);
		String supCode = userPromo.getSuppromocode();
		System.out.println(userPromo.getLoginname() + "=====" + userPromo.getName());
		if (StringUtil.isNotEmpty(supCode))
		{
			select1(supCode);
			users.add(userPromo);
		}
	}
}
