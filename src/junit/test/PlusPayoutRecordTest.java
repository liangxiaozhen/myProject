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

 import com.ptpl.mapper.PlusPayoutRecordMapper;
import com.ptpl.mapper.PlusPayoutRecordMapper;
 import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.service.PlusPayoutRecordServiceI;
import com.ptpl.web.util.StringUtil;

import oracle.net.aso.p;
/**
 * 
 * 标的增益清算记录 测试类
 * PlusPayoutRecordTest
 * 创建人:cjm
 * 时间：2016年11月23日 14:30:07
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class PlusPayoutRecordTest {

	 @Autowired
	 private PlusPayoutRecordServiceI plusPayoutRecordMapper;
	 
  	 
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
	 * @Title: findPlusPayoutRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findPlusPayoutRecords(){
	     PlusPayoutRecord plusPayoutRecord = new  PlusPayoutRecord();
		 List<PlusPayoutRecord> plusPayoutRecords = plusPayoutRecordMapper.findPlusPayoutRecords(plusPayoutRecord);
  		 for(PlusPayoutRecord plusPayoutRecord1 :plusPayoutRecords){
			System.out.println("=========================="+plusPayoutRecord1.getPaycompany());
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
		 	PlusPayoutRecord plusPayoutRecord = new PlusPayoutRecord();
		 	plusPayoutRecord.setId(new BigDecimal(1));
		 	plusPayoutRecord.setPporderno(StringUtil.getNo());//增益清算流水号
	 		plusPayoutRecord.setPpid(new BigDecimal(2122));//标的增益设置表Id
	 		plusPayoutRecord.setTenderid(new BigDecimal(2222));//标号ID
	 		plusPayoutRecord.setUsetenderid(new BigDecimal(2322));//投标id
	 		plusPayoutRecord.setPaymanid(new BigDecimal(2362));//付款人ID
	 		plusPayoutRecord.setInvestorid(new BigDecimal(2362));//投资人ID
	 		plusPayoutRecord.setIntprofit(102.00);//加息券收益
	 		plusPayoutRecord.setVoucherprofit(1002.01);//类现金收益
	 		plusPayoutRecord.setLikevoucherprofit(12.22);//假现金收益
	 		plusPayoutRecord.setClearmode((short)1);//清算方式（1结标，2首期，3按期，4尾期）
	 		plusPayoutRecord.setIsgrant((short)1);////是否发放（0否，1是
	 		plusPayoutRecord.setIsblending((short)1);//是否系统勾兑（0未勾兑，1已勾兑）
	 		plusPayoutRecord.setIsmanblending((short)1);//是否人工勾兑（0未勾兑，1已勾兑）
	 		plusPayoutRecord.setSysbtime(new Date());//系统勾兑时间
	 		plusPayoutRecord.setManbtime(new Date());//人工勾兑时间
	 		plusPayoutRecord.setPaycompany("汇付天下1");//托管通道公司（汇付天下）
	 		plusPayoutRecord.setSysrectime(new Date());//系统勾兑接收数据时间 第一次
	 		plusPayoutRecord.setReceivetime(new Date());//人工勾兑接收数据时间 第一次
	 		plusPayoutRecord.setReqquerydata("接收数据包1");//请求数据包
	 		plusPayoutRecord.setRecresultdata("接收数据包1");//接收数据包
	 		plusPayoutRecord.setMadetime(new Date());//创建时间
	 		plusPayoutRecord.setIsaudit((short)1);//是否审核0否1是
	 		plusPayoutRecord.setAudittime(new Date());//审核时间
	 		plusPayoutRecord.setAuditman("测试1");//审核人
	 		plusPayoutRecord.setPayoutdate(new Date());//清算时间
	 		plusPayoutRecord.setRemark("备注1");//备注
	 		plusPayoutRecord.setRorderno(StringUtil.getNo());//还款流水号
	  		 int count = 0;
	 		 count = plusPayoutRecordMapper.updateById(plusPayoutRecord);
				if(count > 0){
					System.out.println("数据更新成功..");
				}else{
					System.out.println("数据更新失败");
				}
	 }
	 
	  /**
	  * 
	 * @Title: findPlusPayoutRecordById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findPlusPayoutRecordById(){
 		
		 PlusPayoutRecord plusPayoutRecord= plusPayoutRecordMapper.findPlusPayoutRecordById(new BigDecimal(1));
		 System.out.println(plusPayoutRecord.getId()+"-======"+plusPayoutRecord.getPaycompany());
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
  		 int count = plusPayoutRecordMapper.deleteById(new BigDecimal(2));
  		 
 		 if(count > 0){
 			 System.out.println("=============================删除成功");
 		 }else{
 			 System.out.println("=============================删除失败");
 		 }
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
		 public void findPlusPayoutRecordByPporderno(){
			  PlusPayoutRecord plusPayoutRecord = plusPayoutRecordMapper.findPlusPayoutRecordByPporderno("20161124165323183714");
			  System.out.println(plusPayoutRecord.getReqquerydata()+""+plusPayoutRecord.getRemark());
	  		 
	 		 
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
// 		PlusPayoutRecord plusPayoutRecord = new PlusPayoutRecord();
// 		plusPayoutRecord.setPporderno(StringUtil.getNo());//增益清算流水号
// 		plusPayoutRecord.setPpid(new BigDecimal(212));//标的增益设置表Id
// 		plusPayoutRecord.setTenderid(new BigDecimal(222));//标号ID
// 		plusPayoutRecord.setUsetenderid(new BigDecimal(232));//投标id
// 		plusPayoutRecord.setPaymanid(new BigDecimal(236));//付款人ID
// 		plusPayoutRecord.setInvestorid(new BigDecimal(329));//投资人ID
// 		plusPayoutRecord.setIntprofit(1.00);//加息券收益
// 		plusPayoutRecord.setVoucherprofit(10.01);//类现金收益
// 		plusPayoutRecord.setLikevoucherprofit(1.22);//假现金收益
// 		plusPayoutRecord.setClearmode((short)3);//清算方式（1结标，2首期，3按期，4尾期）
// 		plusPayoutRecord.setIsgrant((short)0);////是否发放（0否，1是
// 		plusPayoutRecord.setIsblending((short)0);//是否系统勾兑（0未勾兑，1已勾兑）
// 		plusPayoutRecord.setIsmanblending((short)0);//是否人工勾兑（0未勾兑，1已勾兑）
// 		plusPayoutRecord.setSysbtime(new Date());//系统勾兑时间
// 		plusPayoutRecord.setManbtime(new Date());//人工勾兑时间
// 		plusPayoutRecord.setPaycompany("汇付天下");//托管通道公司（汇付天下）
// 		plusPayoutRecord.setSysrectime(new Date());//系统勾兑接收数据时间 第一次
// 		plusPayoutRecord.setReceivetime(new Date());//人工勾兑接收数据时间 第一次
// 		plusPayoutRecord.setReqquerydata("接收数据包");//请求数据包
// 		plusPayoutRecord.setRecresultdata("接收数据包");//接收数据包
// 		plusPayoutRecord.setMadetime(new Date());//创建时间
// 		plusPayoutRecord.setIsaudit((short)0);//是否审核0否1是
// 		plusPayoutRecord.setAudittime(new Date());//审核时间
// 		plusPayoutRecord.setAuditman("测试");//审核人
// 		plusPayoutRecord.setPayoutdate(new Date());//清算时间
// 		plusPayoutRecord.setRemark("备注");//备注
// 		plusPayoutRecord.setRorderno(StringUtil.getNo());//还款流水号
//  		 int count = 0;
// 		 count = plusPayoutRecordMapper.insertSelective(plusPayoutRecord);
//			if(count > 0){
//				System.out.println("数据添加成功..");
//			}else{
//				System.out.println("数据添加失败");
//			}
 
			for(int i = 0;i<=60;i++){
				PlusPayoutRecord plusPayoutRecord = new PlusPayoutRecord();
		 		plusPayoutRecord.setPporderno(StringUtil.getNo());//增益清算流水号
		 		plusPayoutRecord.setPpid(new BigDecimal(212));//标的增益设置表Id
		 		plusPayoutRecord.setTenderid(new BigDecimal(222));//标号ID
		 		plusPayoutRecord.setUsetenderid(new BigDecimal(232));//投标id
		 		plusPayoutRecord.setPaymanid(new BigDecimal(236));//付款人ID
		 		plusPayoutRecord.setInvestorid(new BigDecimal(329));//投资人ID
		 		plusPayoutRecord.setIntprofit(0.01);//加息券收益
		 		plusPayoutRecord.setVoucherprofit(10.01);//类现金收益
		 		plusPayoutRecord.setLikevoucherprofit(0.22);//假现金收益
		 		plusPayoutRecord.setClearmode((short)3);//清算方式（1结标，2首期，3按期，4尾期）
		 		plusPayoutRecord.setIsgrant((short)0);////是否发放（0否，1是
		 		plusPayoutRecord.setIsblending((short)0);//是否系统勾兑（0未勾兑，1已勾兑）
		 		plusPayoutRecord.setIsmanblending((short)0);//是否人工勾兑（0未勾兑，1已勾兑）
		 		plusPayoutRecord.setSysbtime(new Date());//系统勾兑时间
		 		plusPayoutRecord.setManbtime(new Date());//人工勾兑时间
		 		plusPayoutRecord.setPaycompany("汇付天下");//托管通道公司（汇付天下）
		 		plusPayoutRecord.setSysrectime(new Date());//系统勾兑接收数据时间 第一次
		 		plusPayoutRecord.setReceivetime(new Date());//人工勾兑接收数据时间 第一次
		 		plusPayoutRecord.setReqquerydata("接收数据包");//请求数据包
		 		plusPayoutRecord.setRecresultdata("接收数据包");//接收数据包
		 		plusPayoutRecord.setMadetime(new Date());//创建时间
		 		plusPayoutRecord.setIsaudit((short)0);//是否审核0否1是
		 		plusPayoutRecord.setAudittime(new Date());//审核时间
		 		plusPayoutRecord.setAuditman("测试");//审核人
		 		plusPayoutRecord.setPayoutdate(new Date());//清算时间
		 		plusPayoutRecord.setRemark("备注");//备注
		 		plusPayoutRecord.setRorderno(StringUtil.getNo());//还款流水号
		  		 int count = 0;
		 		 count = plusPayoutRecordMapper.insertSelective(plusPayoutRecord);
					if(count > 0){
						System.out.println("数据添加成功..");
					}else{
						System.out.println("数据添加失败");
					}
		 
			}
 	 }
  
}
