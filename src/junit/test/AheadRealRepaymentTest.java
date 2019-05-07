package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 import com.ptpl.mapper.AheadRealRepaymentMapper;
import com.ptpl.mapper.AheadRealRepaymentMapper;
 import com.ptpl.model.AheadRealRepayment;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 提前实际还款记录 测试类
 * AheadRealRepaymentTest
 * 创建人:cjm
 * 时间：2017年01月04日 11:50:25
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class AheadRealRepaymentTest {

	 @Autowired
	 private AheadRealRepaymentServiceI aheadRealRepaymentMapper;
	 
  	 
	 @Test
	 public void handle05(){
		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		 System.out.println(content);
		 String[] strings = content.getBeanDefinitionNames();
		 for(String string :strings){
			 System.out.println("==================="+string);
		 }
		 
	 }
	 
	 
	  /**
	  * 
	 * @Title: findAheadRealRepayments 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRealRepayments(){
//	     AheadRealRepayment aheadRealRepayment = new  AheadRealRepayment();
//		 List<AheadRealRepayment> aheadRealRepayments = aheadRealRepaymentMapper.findAheadRealRepayments(aheadRealRepayment);
//  		 for(AheadRealRepayment aheadRealRepayment1 :aheadRealRepayments){
//			System.out.println("=========================="+aheadRealRepayment1);
//  			}
		 
		 AheadRealRepayment aheadRealRepayment = aheadRealRepaymentMapper.findAheadRealRepaymentByRordernoAndBacthNo("fdfd", "fdfd");
		 System.out.println(aheadRealRepayment.getRorderno());
	 }
	 
	  /**
	  * 
	 * @Title: updateById 
	 * @Description: TODO(更新 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void updateById(){
		 AheadRealRepayment aheadRealRepayment = new AheadRealRepayment();
		 	aheadRealRepayment.setId(new BigDecimal(7));
		 	 aheadRealRepayment.setRorderno(StringUtil.getNo()); //还款流水号 同还款计划表流水号
			 aheadRealRepayment.setRrealbatchno(StringUtil.getNo()); //还款批次号
			 aheadRealRepayment.setRptotalamount(101.00); //提前还款实际真现金
			 aheadRealRepayment.setRvoucher(111.00); //提前还款实际类现金
			 aheadRealRepayment.setRprincipalint(121.00); //提前还款实际总金额（本息）本金+类现金+实际总利息
			 aheadRealRepayment.setRptotalint(131.00); //提前还款实际总利息 	本金利息+类现金利息
			 aheadRealRepayment.setNorectotalint(141.00); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
			 aheadRealRepayment.setRinterest(151.00); //提前还款实际本金利息
			 aheadRealRepayment.setNorecrinterest(161.00); //提前还款欠收本金利息
			 aheadRealRepayment.setRvoucherint(171.00); //提前还款实际类现金的利息
			 aheadRealRepayment.setNorecrvoucherint(181.00); //提前还款欠收类现金的利息
			 aheadRealRepayment.setRlvoucherint(191.00); //提前还款实际假现金的利息
			 aheadRealRepayment.setNorecrlvoucherint(201.00); //提前还款欠收假现金的利息
			 aheadRealRepayment.setRintfee(211.00); //提前还款实际加息劵利息
			 aheadRealRepayment.setNorecrintfee(221.00); //提前还款欠收加息劵利息
			 aheadRealRepayment.setOweintpenalty(231.00); //欠收投资人利息罚金
			 aheadRealRepayment.setInterestexpense(241.00); //投标利息的管理费 提前还款实际总利息 * 百分比
			 aheadRealRepayment.setRepaystatus((short)11); //还款状态(1未还款，2已提前还款，3处理中,4处理失败)
			 aheadRealRepayment.setIsblending((short)10); //是否系统勾兑（0未勾兑，1已勾兑
			 aheadRealRepayment.setIsmanblending((short)10); //是否人工勾兑（0未勾兑，1已勾兑）
			 aheadRealRepayment.setSysbtime(new Date()); //系统勾兑时间
			 aheadRealRepayment.setManbtime(new Date()); //人工勾兑时间
			 aheadRealRepayment.setPaycompany("huif111"); //托管通道公司（汇付天下
			 aheadRealRepayment.setSysrectime(new Date()); //系统勾兑接收数据时间 第一次
			 aheadRealRepayment.setReceivetime(new Date()); //人工勾兑接收数据时间 第一次
			 aheadRealRepayment.setReqquerydata("请求测试数据包11"); //请求数据包
			 aheadRealRepayment.setRecresultdata("接收测试数据包11"); //接收数据包
			 aheadRealRepayment.setRemark("备注信息测试测试测试11"); //备注
 		 int count = aheadRealRepaymentMapper.updateById(aheadRealRepayment);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findAheadRealRepaymentById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRealRepaymentById(){
 		
		 AheadRealRepayment aheadRealRepayment= aheadRealRepaymentMapper.findAheadRealRepaymentById(new BigDecimal(4));
		 System.out.println(aheadRealRepayment.getId());
	 }
  /**
	  * 
	 * @Title: deleteById 
	 * @Description: TODO(删除 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	  @Test
	 public void deleteById(){
  		 int count = aheadRealRepaymentMapper.deleteById(new BigDecimal(2));
 		 if(count > 0){
 			 System.out.println("=============================删除成功");
 		 }else{
 			 System.out.println("=============================删除失败");
 		 }
	 }
	 
	  /**
	  * 
	 * @Title: insertSelective 
	 * @Description: TODO(新增 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
 	 @Test
	 public void insertSelective(){
   			 AheadRealRepayment aheadRealRepayment = new AheadRealRepayment();
  			 aheadRealRepayment.setRorderno(StringUtil.getNo()); //还款流水号 同还款计划表流水号
  			 aheadRealRepayment.setRrealbatchno(StringUtil.getNo()); //还款批次号
  			 aheadRealRepayment.setRptotalamount(10.00); //提前还款实际真现金
  			 aheadRealRepayment.setRvoucher(11.00); //提前还款实际类现金
  			 aheadRealRepayment.setRprincipalint(12.00); //提前还款实际总金额（本息）本金+类现金+实际总利息
  			 aheadRealRepayment.setRptotalint(13.00); //提前还款实际总利息 	本金利息+类现金利息
  			 aheadRealRepayment.setNorectotalint(14.00); //提前还款欠收总利息 本金欠收利息+类现金欠收利息  因为（假现金、加息劵）的利息平台出
  			 aheadRealRepayment.setRinterest(15.00); //提前还款实际本金利息
  			 aheadRealRepayment.setNorecrinterest(16.00); //提前还款欠收本金利息
  			 aheadRealRepayment.setRvoucherint(17.00); //提前还款实际类现金的利息
  			 aheadRealRepayment.setNorecrvoucherint(18.00); //提前还款欠收类现金的利息
  			 aheadRealRepayment.setRlvoucherint(19.00); //提前还款实际假现金的利息
  			 aheadRealRepayment.setNorecrlvoucherint(20.00); //提前还款欠收假现金的利息
  			 aheadRealRepayment.setRintfee(21.00); //提前还款实际加息劵利息
  			 aheadRealRepayment.setNorecrintfee(22.00); //提前还款欠收加息劵利息
  			 aheadRealRepayment.setOweintpenalty(23.00); //欠收投资人利息罚金
  			 aheadRealRepayment.setInterestexpense(24.00); //投标利息的管理费 提前还款实际总利息 * 百分比
  			 aheadRealRepayment.setRepaystatus((short)1); //还款状态(1未还款，2已提前还款，3处理中,4处理失败)
  			 aheadRealRepayment.setIsblending((short)0); //是否系统勾兑（0未勾兑，1已勾兑
  			 aheadRealRepayment.setIsmanblending((short)0); //是否人工勾兑（0未勾兑，1已勾兑）
  			 aheadRealRepayment.setSysbtime(new Date()); //系统勾兑时间
  			 aheadRealRepayment.setManbtime(new Date()); //人工勾兑时间
  			 aheadRealRepayment.setPaycompany("huif"); //托管通道公司（汇付天下
  			 aheadRealRepayment.setSysrectime(new Date()); //系统勾兑接收数据时间 第一次
  			 aheadRealRepayment.setReceivetime(new Date()); //人工勾兑接收数据时间 第一次
  			 aheadRealRepayment.setReqquerydata("请求测试数据包"); //请求数据包
  			 aheadRealRepayment.setRecresultdata("接收测试数据包"); //接收数据包
  			 aheadRealRepayment.setRemark("备注信息测试测试测试"); //备注
  			 aheadRealRepayment.setAddtime(new Date());
      		 int count = 0;
 			 count = aheadRealRepaymentMapper.insert(aheadRealRepayment);
 			 if(count > 0){
 				System.out.println("数据添加成功..");
 			 }else{
 				System.out.println("数据添加失败");
 			 }
  		  
 
 	 }
  
}
