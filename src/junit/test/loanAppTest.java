package junit.test;

import com.ptpl.constant.Loanapp_Constant;
import com.ptpl.mapper.TenderItemMapper;
import com.ptpl.mapper.loanappMapper;
import com.ptpl.model.loanapp;
import com.ptpl.web.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 
 * 借款申请记录 测试类
 * loanAppTest
 * 创建人:cjm
 * 时间：2016年11月04日 14:29:23
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class loanAppTest {

	 @Autowired
	 private loanappMapper ioanAppMapper;
	 
	 @Autowired
	 private TenderItemMapper tenderItemMapper;
	 
	 @Test
	 public void test13232(){
		 
	 }
	 
	 @Test
	 public void test2(){
		 loanapp loanapp = new loanapp();
	  	loanapp.setBaseid(new BigDecimal(217));  //用户ID
	  	List<Map<BigDecimal,BigDecimal>> maps = ioanAppMapper.findloanappCountByBaseid(loanapp);
  		for(Map<BigDecimal,BigDecimal> map : maps){
			if(map.get("appstatus").equals(new BigDecimal("0"))){
 				System.out.println(map.get("counts"));
			}else if(map.get("appstatus").equals(new BigDecimal("3"))){
				System.out.println(map.get("counts"));
			}else if(map.get("appstatus").equals(new BigDecimal("4"))){
				System.out.println(map.get("counts"));
			}else if(map.get("appstatus").equals(new BigDecimal("5"))){
				System.out.println(map.get("counts"));
			} 
 		}
	  	
//	  	System.out.println(maps.get("appstatus"));
//	  	System.out.println(maps.get(0).get("counts"));
//	  	System.out.println(maps.get(2));
//	  	System.out.println(maps.get(3));
//	  	System.out.println(maps.get(4));
//	  	System.out.println(maps.get(5));
 	  	
	 }
	 
  	 @Test
  	 public void Test(){
  		loanapp loanapp = new loanapp();
  		loanapp.setBaseid(new BigDecimal(326));  //用户ID
  		loanapp.setLoanno(StringUtil.getNo());  //借款编号
  		loanapp.setLoantype((short)2);  //借款类型（1普通借款，2净值标
  		loanapp.setLoanamount(1000.0000001);  //借款金额
  		loanapp.setApptime(new Date());  //申请时间
  		loanapp.setAppday(3L);  //借款期限
  		loanapp.setLoanrate(0.12);  //借款利率
  		loanapp.setIsneedconfirm((short)2);  //是否需要借款人确认
  		loanapp.setAppstatus(Loanapp_Constant.T10);  //1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成

  		loanapp.setAuditman("系统");  //审核人
  		loanapp.setAudittime(new Date());  //审核时间
  		loanapp.setRemark("备注");  //备注
  		loanapp.setReceiptsamount(1000.00);  //已入账借款金额
  		loanapp.setFinishtime(new Date());  //完成时间
  		loanapp.setLoanname("嘉敏借款申请测试15");  //借款标题名称
  		loanapp.setLoanpurposedesc("小胖子娶媳妇");  //借款用途描述
  		loanapp.setRepaymenttype((short)1);  //还款方式（等额本息，等额本金，先息后本，一次性还本付息）
  		loanapp.setIsappointtender((short)1);  //是否为约标
  		loanapp.setIsautorepay((short)2);  //是否同意自动还款
  		loanapp.setUnit("年");  //单位(天，月，年)
  		loanapp.setProxyman("系统");  //代提交人  平台管理人员代用户提交
  		loanapp.setLiano(StringUtil.getNo());  //资料信息编号
  		int count = 0;
  		count = ioanAppMapper.insertSelective(loanapp);
  		if(count > 0){
  			System.out.println("添加成功");
  		}
  		
  	 }
  	 
  	 @Test
  	 public void updatetest(){
  		loanapp loanapp = ioanAppMapper.selectByPrimaryKey(new BigDecimal(903));
		loanapp.setAppstatus(Loanapp_Constant.T10);
		ioanAppMapper.updateByPrimaryKeySelective(loanapp);
  	 }
	 
  
}
