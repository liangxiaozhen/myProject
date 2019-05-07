package junit.test;

import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.huifu.OraceTest;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 投标记录 测试类
 * UserTenderTest
 * 创建人:chenjiaming
 * 时间：2016年10月09日 14:46:27
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class UserTenderTest {

	 @Autowired
	 private UserTenderServiceI userTenderMapper;
	 
	 @Autowired
	 private  UserFsAccountInfoServiceI userFsAccountInfoService;
	 
	 @Test
	 public void test(){
		 UserTender ut = new UserTender();
		    ut.setOrderno("20161009155813105752");								// 投标订单号
			ut.setAmount(100.00);			// 投标金额
			ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL);			// 投标方式-手动
			ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO);				// 还款完成-否
//			ut.setFeerate(Double.valueOf(huifuParams.getMaxTenderRate()));		// 手续费率（默认0）
			ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT);		// 转账类型
 			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_FREEZE);			// 冻结
 			ut.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT);
			ut.setPaycompany("汇付天下");											// 投标通道公司
			UserFsAccountInfo outaccountid = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId("6000060005590042");
			UserFsAccountInfo inaccountid = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId("6000060005590006");
			ut.setOutaccountid(outaccountid.getBaseid());/** 投资方ID */
			ut.setInaccountid(inaccountid.getBaseid());/** 借款方ID */
			ut.setTendertime(new Date());
			ut.setTenderid(new BigDecimal(236));/** 标的号ID */
			ut.setRemark("发标500条测试");
			ut.setIsda(TenderRecord_Constant.ISDA_NO);							// 是否债转-未债转
			ut.setTbegintime(new Date());
			ut.setOriginclient("pc");
			int count = 0;
			count = userTenderMapper.insertSelective(ut);
			if(count > 0){
				System.out.println("保存成功");
			}else{
				System.out.println("保存失败");
			}
	 }
	  
	 @Test
	 public void test2(){
			TenderItem tenderItem =	OraceTest.getTenderItem();//标的信息
			Map<String,Object> hashMap = new HashMap<String,Object>();
			hashMap.put("tenderid", tenderItem.getId());
			List<UserTender> userTenders = userTenderMapper.findUserTenderRecord(hashMap);
			int i = 0;
			for(UserTender userTender : userTenders){
				i++;
				System.out.println(userTender.getId()+"======getId======");
				System.out.println(i+"======i======");
				
			}
			
 	 }
 	 /**
 	  *  根据标id查询投标状态为：1.待审核 4.已完成 5.处理中 6.处理失败,当标的投标状态为这几种中的任意一种时，这个标就不可以流标
 	  * @author :liuqh
 	  * @date 2017/6/26 10:22
 	  */
 	 @Test
	public void findTenderByTstatusAndTenderid(){
		 List<UserTender> userTenders = userTenderMapper.findTenderByTstatusAndTenderid(new BigDecimal(1884));
		 for (UserTender ut:userTenders){
			 System.out.println(ut.toString());
		 }
	 }


	 @Test
	public void testCount(){
		 List<UserTender> list = userTenderMapper.findRecordByBaseId(new BigDecimal(1017));
		for(UserTender u:list){
			System.out.println(u.toString());
		}
	 }
  
}
