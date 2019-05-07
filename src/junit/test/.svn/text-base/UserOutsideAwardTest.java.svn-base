package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.AwardPackage;
import com.ptpl.model.UserOutsideAward;
import com.ptpl.model.UserPostAddress;
import com.ptpl.service.UserOutsideAwardServiceI;
import com.ptpl.service.UserPostAddressI;
import com.ptpl.service.UserRechargeServiceI;

public class UserOutsideAwardTest {
	@Autowired
    private UserOutsideAwardServiceI userOutsideAwardService;
	
	@Autowired
	private UserPostAddressI userPostAddressService;
	@Before
	public void before(){
		//使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext.xml"});
		//从Spring容器中根据bean的id取出我们要使用的userService对象
		userPostAddressService = (UserPostAddressI) ac.getBean("userPostAddressService");
		userOutsideAwardService = (UserOutsideAwardServiceI) ac.getBean("userOutsideAwardService");
	}
	
//	@Test
//	public void add(){
//		UserPostAddress postAddress=new UserPostAddress();
//		for (int i = 1; i <10 ; i++) {
//			postAddress.setId(new BigDecimal(1));
//			postAddress.setBaseid(new BigDecimal(231+i));
//			postAddress.setAwardno("21"+i);
//			postAddress.setAddressProvince("湖北省");
//			postAddress.setAddressCity("随州市");
//			postAddress.setAddressDistrict("曾都区");
//			postAddress.setAddressStreet("万和镇");
//			postAddress.setZipcode("441300");
//			postAddress.setRecipients("老六");
//			postAddress.setMobliephone("15072631536");
//			postAddress.setTelephone("0755-3487");
//			postAddress.setRemark("这是一条测试");
//		    userPostAddressService.insertSelective(postAddress);
//		}
//	}
	
    //更新
//	@Test
//	public void testUpdateAwardPackage(){
//		UserPostAddress postAddress=new UserPostAddress();
//		postAddress.setId(new BigDecimal(2));
//		postAddress.setRemark("这真的只是一条测试语句");
//		userPostAddressService.updateByPrimaryKeySelective(postAddress);
//	}
	//删除
//	@Test
//	public void testDeleteGfundsInt(){
//		userPostAddressService.deleteByPrimaryKey(new BigDecimal(10));
//	}
//    @Test
//	public void testselectbyID(){
//		UserPostAddress postAddress=userPostAddressService.selectByuserID(new BigDecimal(217));
//		if(postAddress==null){
//			System.out.println("99999");
//		}else{
//		System.out.println(postAddress.getId());
//		}
//	}
//	@Test
//    public void testgetalluserarawd(){
//       List<UserOutsideAward> awards=userOutsideAwardService.selectAll();
//       System.out.println(awards.size());
//       for (UserOutsideAward userOutsideAward : awards) {
//		 System.out.println(userOutsideAward.getUserBaseAccountInfo().getLoginname());
//	}
//    }
	
}
