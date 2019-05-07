package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.mapper.TradeFileDetailMapper;
import com.ptpl.model.AllTradeFileDetail;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.RemoveName;
import com.ptpl.model.TradeFileDetail;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.model.WithdrawsCashRstr;
import com.ptpl.service.AllTradeFileDetailServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.TradeFileDetailServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.service.WithdrawsCashRstrServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

public class WithdrawsCashRstrTest {
	private WithdrawsCashRstrServiceI wdcRstrService;
	private TradeFileDetailServiceI tradeFileDetailService;
	private AllTradeFileDetailServiceI allTradeFileDetailService;
	private BacthFileRecordServiceI bacthFileRecordService;
	private UserFsAccountInfoServiceI userFsAccountInfoService;
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	private UserBankCardServiceI userBankCardService;
	private UserWithdrawsCashServiceI userWithdrawsCashService;
	private UserPromoServiceI userPromoService;
	private RemoveNameServiceI removeNameService;
	private SMSSendServiceI smsSendService;

	@Before
	public void before() {
		// 使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-mvc.xml", "spring/applicationContext-*.xml" });
		// 从Spring容器中根据bean的id取出我们要使用的userService对象
		// wdcRstrService = (WithdrawsCashRstrServiceI)
		// ac.getBean("withdrawsCashRstrService");
		// tradeFileDetailService = (TradeFileDetailServiceI)
		// ac.getBean("tradeFileDetailService");
		// allTradeFileDetailService = (AllTradeFileDetailServiceI)
		// ac.getBean("allTradeFileDetailService");
		// bacthFileRecordService = (BacthFileRecordServiceI)
		// ac.getBean("bacthFileRecordService");
		// userFsAccountInfoService = (UserFsAccountInfoServiceI)
		// ac.getBean("userFsAccountInfoService");
		// userBaseAccountInfoService = (UserBaseAccountInfoServiceI)
		// ac.getBean("userBaseAccountInfoService");
		// userBankCardService = (UserBankCardServiceI)
		// ac.getBean("userbankcardService");
		// userWithdrawsCashService=(UserWithdrawsCashServiceI)ac.getBean("userWithdrawsCashService");
		// userPromoService = (UserPromoServiceI)
		// ac.getBean("userPromoService");
		// removeNameService=(RemoveNameServiceI)
		// ac.getBean("removeNameService");
		smsSendService = (SMSSendServiceI) ac.getBean("smsSendService");
	}

	@Test
	public void test12() {
		 smsSendService.SMSSend4Code("18273293855", "123456");
		
	}

	@Test
	public void test11() {
		List<RemoveName> list = removeNameService.selective(null);
		for (RemoveName rn : list) {
			rn.setRealname(AES.getEncrypt(rn.getRealname()));
			rn.setLoginname(AES.getEncrypt(rn.getLoginname()));
			removeNameService.updateByPrimaryKeySelective(rn);
		}
	}

	@Test
	public void test10() {
		List<UserPromo> list = userPromoService.selective(null);
		for (UserPromo up : list) {
			up.setLoginname(AES.getEncrypt(up.getLoginname()));
			up.setName(AES.getEncrypt(up.getName()));
			userPromoService.updateByPrimaryKey(up);
		}
	}

	@Test
	public void test9() {
		List<UserWithdrawsCash> list = userWithdrawsCashService.selective(null);
		for (UserWithdrawsCash cash : list) {
			cash.setUsrcustid(AES.getEncrypt(cash.getUsrcustid()));
			cash.setUsername(AES.getEncrypt(cash.getUsername()));
			cash.setCardno(AES.getEncrypt(cash.getCardno()));
			userWithdrawsCashService.updateByCashNo(cash);
		}
	}

	@Test
	public void test8() {
		List<UserBankCard> list = userBankCardService.findUserBankInfo(null);
		for (UserBankCard card : list) {
			card.setUsername(AES.getEncrypt(card.getUsername()));
			card.setCardno(AES.getEncrypt(card.getCardno()));
			System.out.println(userBankCardService.updateUserBankInfo(card));
		}
	}

	@Test
	public void test7() {
		List<UserBaseAccountInfo> list = userBaseAccountInfoService.getUserBaseAccountInfos(null);
		for (UserBaseAccountInfo ubai : list) {
			ubai.setEmail(AES.getEncrypt(ubai.getEmail()));
			ubai.setMobilephone(AES.getEncrypt(ubai.getMobilephone()));
			ubai.setCertificationnumber(AES.getEncrypt(ubai.getCertificationnumber()));
			ubai.setRealname(AES.getEncrypt(ubai.getRealname()));
			ubai.setLoginname(AES.getEncrypt(ubai.getLoginname()));
			System.out.println(userBaseAccountInfoService.updateByPrimaryKeySelective(ubai));
		}
	}

	@Test
	public void test6() {
		/*
		 * List<UserFsAccountInfo> list =
		 * userFsAccountInfoService.listUserFsAccountInfo(); for
		 * (UserFsAccountInfo ufsai : list) {
		 * ufsai.setUsrcustid(AES.getEncrypt(ufsai.getUsrcustid()));
		 * ufsai.setUsrname(AES.getEncrypt(ufsai.getUsrname()));
		 * ufsai.setFsmobile(AES.getEncrypt(ufsai.getFsmobile()));
		 * System.out.println(userFsAccountInfoService.updateById(ufsai)); }
		 */
	}

	@Test
	public void test() {
		List<WithdrawsCashRstr> list = wdcRstrService.selective(null);
		System.out.println(list.size());
	}

	@Test
	public void test1() {
		TradeFileDetail tfd;
		for (int i = 0; i < 20; i++) {
			tfd = new TradeFileDetail();
			tfd.setEntertime(new Date());
			tfd.setRemark("junit测试" + i);
			tradeFileDetailService.insertSelective(tfd);
		}
		List<TradeFileDetail> list = tradeFileDetailService.listTradeFileDetail(null);
		System.out.println(list.size());
	}

	@Test
	public void test2() {
		AllTradeFileDetail atfd;
		for (int i = 0; i < 20; i++) {
			atfd = new AllTradeFileDetail();
			atfd.setEntertime(new Date());
			atfd.setRemark("junit测试" + i);
			allTradeFileDetailService.insertSelective(atfd);
		}
		List<AllTradeFileDetail> list = allTradeFileDetailService.listAllTradeFileDetail(null);
		System.out.println(list.size());
	}

	@Test
	public void test3() {
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFileType((short) 5); // 文件类型 5交易流水明细
		bacthFileRecord.setFilePath("/bacthfile/800114/eve/20160510");// 文件路径
		// bacthFileRecord.setSendFileName("3004-EVE0054-20160510");
		bacthFileRecord.setGetFileName("3004-EVE0054-20160510");// 下载文件名称
		bacthFileRecord.setCoinstCode("800114");
		bacthFileRecord.setPName("干将P2P平台");
		bacthFileRecord.setIsSend((short) 1);// 是否发送银行
		bacthFileRecord.setIsDealResult((short) 0);
		bacthFileRecord.setSendResult((short) 1);
		bacthFileRecord.setDealResult((short) 0);
		int iden = bacthFileRecordService.insert(bacthFileRecord);
		System.out.println(iden);
	}

	@Test
	public void test4() {
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFileType((short) 6); // 文件类型 5交易流水明细
		bacthFileRecord.setFilePath("/bacthfile/800114/aleve/20160510");// 文件路径
		// bacthFileRecord.setSendFileName("3004-EVE0054-20160510");
		bacthFileRecord.setGetFileName("3004-ALEVE0054-20160510");// 下载文件名称
		bacthFileRecord.setCoinstCode("800114");
		bacthFileRecord.setPName("干将P2P平台");
		bacthFileRecord.setIsSend((short) 1);// 是否发送银行
		bacthFileRecord.setIsDealResult((short) 0);
		bacthFileRecord.setSendResult((short) 1);
		bacthFileRecord.setDealResult((short) 0);
		int iden = bacthFileRecordService.insert(bacthFileRecord);
		System.out.println(iden);
	}

	@Test
	public void test5() {
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFilePath("D:/bacthfile/800114/aleve/20160510");
		bacthFileRecord.setFileType((short) 5);
		bacthFileRecord.setIsDealResult((short) 0);
		bacthFileRecord.setGetFileName("3004-ALEVE0054-20160510");
		System.out.println(tradeFileDetailService.tradeFileDetailDeal(bacthFileRecord));
	}
}
