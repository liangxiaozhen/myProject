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

import com.ptpl.mapper.OverdueRecordMapper;
import com.ptpl.model.OverdueRecord;
import com.ptpl.service.OverdueRecordServiceI;
/**
 * 
 * 标的提前还款奖励单个投资人记录 测试类
 * OverdueRecordTest
 * 创建人:cjm
 * 时间：2017年02月10日 16:05:47
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class OverdueRecordTest {

	 @Autowired
	 private OverdueRecordServiceI overdueRecordMapper;
	 
  	 
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
	 * @Title: findOverdueRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findOverdueRecords(){
	     OverdueRecord overdueRecord = new  OverdueRecord();
		 List<OverdueRecord> overdueRecords = overdueRecordMapper.findOverdueRecords(overdueRecord);
  		 for(OverdueRecord overdueRecord1 :overdueRecords){
			System.out.println("=========================="+overdueRecord1.getRemark());
  			}
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
		 	OverdueRecord overdueRecord = new OverdueRecord();
		 	overdueRecord.setId(new BigDecimal(8));
	  		overdueRecord.setOrorderno("232143243254342532"); //逾期还款流水号
	 		overdueRecord.setOrid(new BigDecimal(11)); //逾期还款设置表Id
	 		overdueRecord.setTenderid(new BigDecimal(133)); //标号ID
	 		overdueRecord.setRorderno("3433234"); //还款流水号
	 		overdueRecord.setRbatchno("23343345"); //还款批次号
	  		overdueRecord.setBmanid(new BigDecimal(223)); //借款用户ID
	 		overdueRecord.setInvestorid(new BigDecimal(1455)); //投资人ID
	 		overdueRecord.setProxyaccountid(new BigDecimal(145)); //代还款人ID
	 		overdueRecord.setNeedrdate(new Date()); //应还款日期
	 		overdueRecord.setPeriods(11); //还款期数(第几期)
	 		overdueRecord.setOday(11); //逾期天数
	 		overdueRecord.setOtotalamount(2010.00); //欠投资人逾期总金额    不含假现金  本金+类现金
	 		overdueRecord.setAdvototalamount(2100.00); //垫付总金额
	 		overdueRecord.setOtotalint(151.00); //欠投资人逾期总利息
	 		overdueRecord.setAdvototalint(115.00); //垫付总利息
	 		overdueRecord.setOamount(1512.00); //逾期本金
	 		overdueRecord.setAdvoamount(1123.00); //垫付逾期本金
	 		overdueRecord.setOavoucher(1415.00); //逾期类现金
	 		overdueRecord.setAdvoavoucher(14313.00); //垫付类现金
	 		overdueRecord.setOalvoucher(1213.00); //逾期假现金
	 		overdueRecord.setAdvoalvoucher(1145.00); //垫付假现金
	 		overdueRecord.setOamountint(1310.00); //逾期本金利息
	 		overdueRecord.setAdvoamountint(114.00); //垫付本金利息
	 		overdueRecord.setOavoucherint(111.00); //逾期类现金利息
	 		overdueRecord.setAdvoavoucherint(1413.00); //垫付类现金利息
	 		overdueRecord.setOalvoucherint(121.00); //逾期假现金利息
	 		overdueRecord.setAdvoalvoucherint(111.00); //垫付假现金利息
	 		overdueRecord.setOinterest(0121.00); //逾期加息劵利息
	 		overdueRecord.setAdvointerest(121.00); //垫付加息劵利息
	 		overdueRecord.setOfee(0121.00); //滞纳金
	 		overdueRecord.setAdvofee(141.00); //垫付滞纳金
	 		overdueRecord.setAdvpaytype("0110"); //垫付方式（00000）
	 		overdueRecord.setIsdeal((short)2); //是否处理（0否，1是
	 		overdueRecord.setIsblending((short)2); //是否系统勾兑（0未勾兑，1已勾兑）
	 		overdueRecord.setIsmanblending((short)2); //是否人工勾兑（0未勾兑，1已勾兑）
	 		overdueRecord.setSysbtime(new Date()); //系统勾兑时间
	 		overdueRecord.setManbtime(new Date()); //人工勾兑时间
	 		overdueRecord.setPaycompany("汇付天下1"); //托管通道公司（汇付天下）
	 		overdueRecord.setSysrectime(new Date()); //系统勾兑接收数据时间 第一次
	 		overdueRecord.setReceivetime(new Date()); //人工勾兑接收数据时间 第一次
	 		overdueRecord.setReqquerydata("请求数据包12"); //请求数据包
	 		overdueRecord.setRecresultdata("接收数据包12"); //接收数据包
	 		overdueRecord.setIsaudit((short)2); //是否审核
	 		overdueRecord.setAuditman("shendfd2"); //审核人
	 		overdueRecord.setAudittime(new Date()); //审核时间
	 		overdueRecord.setMadetime(new Date()); //创建时间
	 		overdueRecord.setDealdate(new Date()); //清算日期
	 		overdueRecord.setRemark("备注测试222"); //备注
	 		int count = overdueRecordMapper.updateById(overdueRecord);
			if(count > 0){
				 System.out.println("===================更新成功");
			}else{
				 System.out.println("===================更新不成功");
			}
	 }
	 
	  /**
	  * 
	 * @Title: findOverdueRecordById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findOverdueRecordById(){
 		
		 OverdueRecord overdueRecord= overdueRecordMapper.findOverdueRecordById(new BigDecimal(4));
		 System.out.println(overdueRecord.getRemark());
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
  		 int count = overdueRecordMapper.deleteById(new BigDecimal(2));
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
 		 for(int i = 0 ;i<=230;i++){
  			 OverdueRecord overdueRecord = new OverdueRecord();
 			 BigDecimal bd = new BigDecimal(i);
 			 overdueRecord.setId(bd);
  			 int count = 0;
 			 count = overdueRecordMapper.insertSelective(overdueRecord);
 			if(count > 0){
 				System.out.println("数据添加成功.."+i);
 			}else{
 				System.out.println("数据添加失败"+i);
 			}
  		 }
 
 	 }
 	 
 	 @Test
 	 public void insert2(){
 		OverdueRecord overdueRecord = new OverdueRecord();
  		overdueRecord.setOrorderno("2321432432543425"); //逾期还款流水号
 		overdueRecord.setOrid(new BigDecimal(1)); //逾期还款设置表Id
 		overdueRecord.setTenderid(new BigDecimal(33)); //标号ID
 		overdueRecord.setRorderno("34334"); //还款流水号
 		overdueRecord.setRbatchno("2334345"); //还款批次号
  		overdueRecord.setBmanid(new BigDecimal(23)); //借款用户ID
 		overdueRecord.setInvestorid(new BigDecimal(455)); //投资人ID
 		overdueRecord.setProxyaccountid(new BigDecimal(45)); //代还款人ID
 		overdueRecord.setNeedrdate(new Date()); //应还款日期
 		overdueRecord.setPeriods(1); //还款期数(第几期)
 		overdueRecord.setOday(1); //逾期天数
 		overdueRecord.setOtotalamount(200.00); //欠投资人逾期总金额    不含假现金  本金+类现金
 		overdueRecord.setAdvototalamount(200.00); //垫付总金额
 		overdueRecord.setOtotalint(15.00); //欠投资人逾期总利息
 		overdueRecord.setAdvototalint(15.00); //垫付总利息
 		overdueRecord.setOamount(152.00); //逾期本金
 		overdueRecord.setAdvoamount(123.00); //垫付逾期本金
 		overdueRecord.setOavoucher(145.00); //逾期类现金
 		overdueRecord.setAdvoavoucher(1433.00); //垫付类现金
 		overdueRecord.setOalvoucher(123.00); //逾期假现金
 		overdueRecord.setAdvoalvoucher(145.00); //垫付假现金
 		overdueRecord.setOamountint(130.00); //逾期本金利息
 		overdueRecord.setAdvoamountint(14.00); //垫付本金利息
 		overdueRecord.setOavoucherint(11.00); //逾期类现金利息
 		overdueRecord.setAdvoavoucherint(143.00); //垫付类现金利息
 		overdueRecord.setOalvoucherint(12.00); //逾期假现金利息
 		overdueRecord.setAdvoalvoucherint(11.00); //垫付假现金利息
 		overdueRecord.setOinterest(012.00); //逾期加息劵利息
 		overdueRecord.setAdvointerest(12.00); //垫付加息劵利息
 		overdueRecord.setOfee(012.00); //滞纳金
 		overdueRecord.setAdvofee(14.00); //垫付滞纳金
 		overdueRecord.setAdvpaytype("0000"); //垫付方式（00000）
 		overdueRecord.setIsdeal((short)1); //是否处理（0否，1是
 		overdueRecord.setIsblending((short)1); //是否系统勾兑（0未勾兑，1已勾兑）
 		overdueRecord.setIsmanblending((short)1); //是否人工勾兑（0未勾兑，1已勾兑）
 		overdueRecord.setSysbtime(new Date()); //系统勾兑时间
 		overdueRecord.setManbtime(new Date()); //人工勾兑时间
 		overdueRecord.setPaycompany("汇付天下"); //托管通道公司（汇付天下）
 		overdueRecord.setSysrectime(new Date()); //系统勾兑接收数据时间 第一次
 		overdueRecord.setReceivetime(new Date()); //人工勾兑接收数据时间 第一次
 		overdueRecord.setReqquerydata("请求数据包"); //请求数据包
 		overdueRecord.setRecresultdata("接收数据包"); //接收数据包
 		overdueRecord.setIsaudit((short)1); //是否审核
 		overdueRecord.setAuditman("shendfd"); //审核人
 		overdueRecord.setAudittime(new Date()); //审核时间
 		overdueRecord.setMadetime(new Date()); //创建时间
 		overdueRecord.setDealdate(new Date()); //清算日期
 		overdueRecord.setRemark("备注测试"); //备注
 		int count = 0; 
 		count = overdueRecordMapper.insertSelective(overdueRecord);
  		if(count > 0){
 			System.out.println("保存成功");
 		}else{
 			System.out.println("保存失败");
 		}
  		
 	 }
  
}
