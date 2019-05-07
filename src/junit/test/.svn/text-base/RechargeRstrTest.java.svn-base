package junit.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.RechargeRstr;
import com.ptpl.service.RechargeRateServiceI;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.service.UserServiceI;

public class RechargeRstrTest {
	
	@Autowired
	private static RechargeRstrServiceI rechargeRstrService;
	@Before
	public void before(){
		//使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext.xml"});
		//从Spring容器中根据bean的id取出我们要使用的userService对象
		
		rechargeRstrService = (RechargeRstrServiceI) ac.getBean("rechargeRstrService");
	}
	@Test
	public static void test() throws Exception {
		RechargeRstr rr = new RechargeRstr();
		rr.setId(new BigDecimal(1));
	    rr.setAddman("张三");
	    /*rr.setAddtime(new Date());
	    rr.setAuditman("王五");
	    rr.setAudittime(new Date());
	    rr.setDaymoneyrest(500.0);  //日充值金额
	    rr.setLowestmoney(100.0);  //单笔充值最低金额
	    rr.setHightestmoney(200.0);//单笔最高金额
	    rr.setPaycompany("双乾支付");
	    rr.setIsuse((short)1);   //是否启用
	    rr.setIsaudit((short)1); //1表示审核,0表示不审核
	    rr.setRemovenameno("9527");  //排除人员名单
	    rr.setRemark("我是新手,我来了!!!");
	    rr.setUgrade("普通会员");
	    rr.setRechartype((short)1); //1,网银 2,企业网银 3,快捷
*/	   
	    System.out.println(rr.getAddman());
	  /*  rechargeRstrService.saveRechargeRstr(rr);*/
	    }

}
