//package junit.test;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
//
//import com.alibaba.fastjson.JSON;
//import com.ptpl.model.AccInExRecord;
//import com.ptpl.model.DataDesc;
//import com.ptpl.model.User;
//import com.ptpl.model.UserAccount;
//import com.ptpl.model.UserAccountSafeInfo;
//import com.ptpl.model.UserBaseAccountInfo;
//import com.ptpl.service.AccInExRecordServiceI;
//import com.ptpl.service.DataDescServiceI;
//import com.ptpl.service.UserAccountSafeInfoServiceI;
//import com.ptpl.service.UserAccountServiceI;
//import com.ptpl.service.UserBaseAccountInfoServiceI;
//import com.ptpl.service.UserServiceI;
//
//
//
//
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
//public class MybatisTest {
//	
//	private UserServiceI userService;
//	private UserBaseAccountInfoServiceI userBaseAccountInfoService ;
//	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
//	private UserAccountServiceI userAccountService;
//	private DataDescServiceI dataDescService;
//	private OracleSequenceMaxValueIncrementer accountnumber; 
//	private AccInExRecordServiceI accInExRecordService;
//	
//	@Before
//    public void before(){
//        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
//        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext.xml"});
//        //从Spring容器中根据bean的id取出我们要使用的userService对象
//        userService = (UserServiceI) ac.getBean("userService");
//        userBaseAccountInfoService = (UserBaseAccountInfoServiceI)ac.getBean("userBaseAccountInfoService");
//        userAccountSafeInfoService = (UserAccountSafeInfoServiceI)ac.getBean("userAccountSafeInfoService");
//        userAccountService = (UserAccountServiceI)ac.getBean("userAccountService");
//        dataDescService = (DataDescServiceI)ac.getBean("dataDescService");
//        accountnumber = (OracleSequenceMaxValueIncrementer)ac.getBean("accountnumber");
//        accInExRecordService = (AccInExRecordServiceI)ac.getBean("accInExRecordService");
//    }
//	//用户收支记录
//	@Test
//	public void insert(){
//		Double sum = 0d;
//		for (int i=0;i<20;i++){
//			AccInExRecord accInExRecord = new AccInExRecord();
//			accInExRecord.setBaseid(BigDecimal.valueOf(276));
//			accInExRecord.setDescription("汇付充值");
//			accInExRecord.setType(Short.valueOf("1"));
//			accInExRecord.setBalance(10000D*(i+1));
//			accInExRecord.setInamount(10000D);
//			accInExRecord.setOutamount(0D);
//			accInExRecord.setRecordtime(new Date());
//			accInExRecord.setRemark("汇付测试数据");
//			accInExRecordService.insert(accInExRecord);
//		}
//		
//	}
//	
//	//根据baseID获取用户账户余额信息
//	@Test
//	public void getuserAccount(){
//		UserAccount userAccount = userAccountService.getUserAccountByBaseId(BigDecimal.valueOf(232));
//		if(userAccount!=null){
//			System.out.println(userAccount.getBaseid());
//		}
//		String jsonStr = JSON.toJSONString(userAccount);
//		System.out.println(jsonStr);
//	}
//	//获取数据项描述
//	@Test
//	public void getDataDesc(){
//		//professionType  businessType
//		List<DataDesc> dataDesc =  dataDescService.getDataDesc("businessType");
//		for(DataDesc da : dataDesc){
//			System.out.println(da.getItemname());
//		}
//		//System.out.println(accountnumber.nextStringValue());
//	}
//	//根据动态条件获取用户信息
//	@Test
//	public void getUserBaseAccountInfoByOneCondition(){
//		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//		ubai.setLoginname("雷神");
//		UserBaseAccountInfo ubai1 = userBaseAccountInfoService.getUserBaseAccountInfoByOneCondition(ubai);
//		System.out.println(ubai1.getLoginname() + "" + ubai1.getAddressCity());
//	}
//	//用户账户表插入数据
//	@Test
//	public void insertUserAccount(){
//		UserAccount userAccount = new UserAccount();
//		userAccount.setBaseid(BigDecimal.valueOf(206));
//		userAccountService.insert(userAccount);
//	}
//	//校验用户加密后的密码
//	@Test
//	public void BCryptTest(){
//		UserAccountSafeInfo uasi = new UserAccountSafeInfo();
//		uasi = userAccountSafeInfoService.getUserAccountSafeInfoById(BigDecimal.valueOf(1));
//		String pass = "abc123";
//		boolean boo = BCrypt.checkpw(pass, uasi.getLoginpassword());
//		System.out.println(boo);
//	}
//	
//	@Test
//	public void addUserBase(){
//		for (int i=0;i<2;i++){
//			
//		
//		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//		ubai.setAccountnumber(12345678l+i+"");
//		ubai.setAccounttype((short) 2);
//		ubai.setEmail("23426820@qq.com");
//		ubai.setAddressCity("深圳");
//		ubai.setAddressDistrict("罗湖");
//		ubai.setAddressProvince("广东");
//		ubai.setCertificationnumber("123214345436547");
//		ubai.setCertificationtype((short) 1);
//		ubai.setAddressStreet("金融街666");
//		ubai.setEmailverifydate(new Date());
//		ubai.setIsactive((short) 1);
//		ubai.setIsemailverify((short) 1);
//		ubai.setIsmobileverify((short) 1);
//		ubai.setIsreally((short) 1);
//		ubai.setIsreally((short) 1);
//		ubai.setLoginname("admin");
//		ubai.setMobileverifydate(new Date());
//		ubai.setEmailverifydate(new Date());
//		ubai.setTelephone("020-11111111");
//		ubai.setLogintype((short) 1);
//		ubai.setStatus((short) 1);
//		ubai.setOriginclient((short) 1);
//		ubai.setRemark("xinzhuce");
//		ubai.setRegtype((short) 2);
//		ubai.setRegdate(new Date());
//		ubai.setRealname("广州");
//		ubai.setReferralinfo("");
//		ubai.setReferralno("123123123");
//		ubai.setReferralselfno("10000");
//		ubai.setMobilephone("13800138000");
//		ubai.setProfession("1");
//		userBaseAccountInfoService.insert(ubai);
//		}
//	}
//	
//	@Test
//	public void testQuery(){		
//		User users = (User) userService.getUserById(266);
//		String ss="";
//		System.out.println(users.getUserName());
//	}
//	
//	@Test
//	public void testAdd(){
////		for (int i=0;i<100;i++){
////			User user = new User();
////			user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
////			user.setUserName("白虎神皇xdp");
////			user.setUserBirthday(new Date());
////			user.setUserSalary(1000000d);
////			userService.addUser(user);
////		}
//		
//		
//	}
//	
//}
