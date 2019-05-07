package junit.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBalanceQueryServiceI;
import com.ptpl.service.UserRechargeServiceI;

public class TestUserRecharge {
	@Autowired
	private static UserRechargeServiceI userRechargeService;
	@Autowired
	private static UserAccountServiceI userAccountService;
	@Autowired
	private static UserBalanceQueryServiceI	userBalanceQueryService;
	@Before
	public void before(){
		//使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext.xml"});
		//从Spring容器中根据bean的id取出我们要使用的userService对象
		
		userRechargeService = (UserRechargeServiceI) ac.getBean("userRechargeService");
		userBalanceQueryService = (UserBalanceQueryServiceI) ac.getBean("userBalanceQueryService");
	}
	@Test
	public void test() throws Exception{
		String UsrCustId="6000060004191208";
    	UserFsAccountInfo ufai = new UserFsAccountInfo();
    	ufai.setUsrcustid(UsrCustId);
    	ufai.setBaseid(new BigDecimal(263));
    	userBalanceQueryService.saveUserBalance(ufai);
	}
	@Test
	public void add(){
		UserRecharge  ur = new UserRecharge();
		ur.setId(new BigDecimal(6));
		ur.setBaseid(new BigDecimal(219));;
		ur.setRechargeno("2222");
		ur.setBankreturnno("3333"); 
		ur.setAmount(500.3);
		ur.setStarttime(new Date());
		ur.setEndtime(new Date());
		ur.setSyschktime(new Date());
		ur.setApplyman("Xueyoiu");
		ur.setChecktime(new Date());
		ur.setCheckman("张无忌");
		ur.setRechargetype((short)1);
		ur.setBankname("霸王银行1");
		ur.setPaycompany("汇付天下1");
		ur.setReceivetime(new Date());
		ur.setIsblending((short)1);
		ur.setIsmanblending((short)1);
		ur.setIsexceptions((short)1);
		ur.setStatus((short)4);
		ur.setOriginclient((short)1);
		ur.setRemark("好难得搞哦");
		ur.setReqquerydata("请求数据包");
		ur.setRecresultdata("返回数据包");
		ur.setRecharfee(3.5);
		ur.setRecharrate(0.3);
		userRechargeService.add(ur);
	}

}
