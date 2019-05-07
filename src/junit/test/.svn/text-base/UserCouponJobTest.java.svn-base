package junit.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;

/**
 * 测试使用券的定时器
 *
 */
public class UserCouponJobTest {

	private UserInterestRateCouponServiceI userInterestRateCouponService;//加息券

	@Before
	public void before(){
		//启动spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
		//从Spring容器中根据bean的id取出我们要使用的userInterestRateCouponService对象
		userInterestRateCouponService=(UserInterestRateCouponServiceI) ac.getBean("userInterestRateCouponService");
	}
	
	@Test
	public void UserCouponController(){
		
		List<UserInterestRateCoupon> uircList = userInterestRateCouponService.findUserInterestRateCoupon();
	
		System.out.println("uuuu: "+uircList.get(0));
		System.out.println("uuid: "+uircList.get(0).getId());
		UserInterestRateCoupon u = new UserInterestRateCoupon();
		long nowDate = System.currentTimeMillis();
		long date = uircList.get(0).getIcfailtime().getTime();
		if(nowDate > date){
			u.setId(uircList.get(0).getId());
			u.setIsuse((short)5);//已到期
			userInterestRateCouponService.updataCouponById(u);
		}
		
		/*long nowDate = System.currentTimeMillis();
		for(UserInterestRateCoupon u:uircList){
			long date =u.getIcfailtime().getTime();
			if(nowDate > date){
				//说明使用券已经到期
				u.setIsuse((short)5);//已到期
				//userInterestRateCouponService.updataCouponById(u);
			}
		}*/
		
	}
	
}
