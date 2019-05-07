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

import com.ptpl.mapper.AheadRepayPlatformRecordMapper;
import com.ptpl.model.AheadRepayPlatformRecord;
import com.ptpl.service.AheadRepayPlatformRecordServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 标的提前还款奖励平台记录 测试类
 * AheadRepayPlatformRecordTest
 * 创建人:cjm
 * 时间：2016年10月21日 12:19:20
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class AheadRepayPlatformRecordTest {

	 @Autowired
	 private AheadRepayPlatformRecordServiceI aheadRepayPlatformRecordMapper;
	 
  	 
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
	 * @Title: findAheadRepayPlatformRecords 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRepayPlatformRecords(){
	     AheadRepayPlatformRecord aheadRepayPlatformRecord = new  AheadRepayPlatformRecord();
		 List<AheadRepayPlatformRecord> aheadRepayPlatformRecords = aheadRepayPlatformRecordMapper.findAheadRepayPlatformRecords(aheadRepayPlatformRecord);
  		 for(AheadRepayPlatformRecord aheadRepayPlatformRecord1 :aheadRepayPlatformRecords){
			System.out.println("=========================="+aheadRepayPlatformRecord1.getArorderno());
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
		 AheadRepayPlatformRecord aheadRepayPlatformRecord = new AheadRepayPlatformRecord();
			aheadRepayPlatformRecord.setTenderid(new BigDecimal(219));
			aheadRepayPlatformRecord.setSpecialtenderid(new BigDecimal(219));
			aheadRepayPlatformRecord.setArorderno(StringUtil.getNo());
			aheadRepayPlatformRecord.setArid(new BigDecimal(229));
			aheadRepayPlatformRecord.setRorderno(StringUtil.getNo());
			aheadRepayPlatformRecord.setPmiscrecman("平台杂项收款人546");
			aheadRepayPlatformRecord.setBmanid(new BigDecimal(259));
			aheadRepayPlatformRecord.setProxyaccountid(new BigDecimal(269));
			aheadRepayPlatformRecord.setAllnorecieveint(125.00);
			aheadRepayPlatformRecord.setAmendspamount(135.00);
			aheadRepayPlatformRecord.setIsdeal((short)1);
			aheadRepayPlatformRecord.setIsaudit((short)1);
			aheadRepayPlatformRecord.setIsblending((short)1);
			aheadRepayPlatformRecord.setIsmanblending((short)1);
			aheadRepayPlatformRecord.setSysbtime(new Date());
			aheadRepayPlatformRecord.setManbtime(new Date());
			aheadRepayPlatformRecord.setPaycompany("汇付天下2");
			aheadRepayPlatformRecord.setSysrectime(new Date());
			aheadRepayPlatformRecord.setReceivetime(new Date());
			aheadRepayPlatformRecord.setReqquerydata("请求数据包2");
			aheadRepayPlatformRecord.setRecresultdata("接收数据包2");
			aheadRepayPlatformRecord.setAudittime(new Date());
			aheadRepayPlatformRecord.setAuditman("审核人2");
			aheadRepayPlatformRecord.setMadetime(new Date());
			aheadRepayPlatformRecord.setPayoutdate(new Date());
			aheadRepayPlatformRecord.setRemark("备注信息测试2");
		 aheadRepayPlatformRecord.setId(new BigDecimal(4));
 		 int count = aheadRepayPlatformRecordMapper.updateById(aheadRepayPlatformRecord);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findAheadRepayPlatformRecordById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findAheadRepayPlatformRecordById(){
 		
		 AheadRepayPlatformRecord aheadRepayPlatformRecord= aheadRepayPlatformRecordMapper.findAheadRepayPlatformRecordById(new BigDecimal(2));
		 System.out.println(aheadRepayPlatformRecord.getPaycompany());
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
  		 int count = aheadRepayPlatformRecordMapper.deleteById(new BigDecimal(2));
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
   			AheadRepayPlatformRecord aheadRepayPlatformRecord = new AheadRepayPlatformRecord();
   			aheadRepayPlatformRecord.setTenderid(new BigDecimal(216));
   			aheadRepayPlatformRecord.setSpecialtenderid(new BigDecimal(218));
   			aheadRepayPlatformRecord.setArorderno(StringUtil.getNo());
   			aheadRepayPlatformRecord.setArid(new BigDecimal(219));
   			aheadRepayPlatformRecord.setRorderno(StringUtil.getNo());
   			aheadRepayPlatformRecord.setPmiscrecman("平台杂项收款人");
   			aheadRepayPlatformRecord.setBmanid(new BigDecimal(229));
   			aheadRepayPlatformRecord.setProxyaccountid(new BigDecimal(239));
   			aheadRepayPlatformRecord.setAllnorecieveint(12.00);
   			aheadRepayPlatformRecord.setAmendspamount(13.00);
   			aheadRepayPlatformRecord.setIsdeal((short)0);
   			aheadRepayPlatformRecord.setIsaudit((short)0);
   			aheadRepayPlatformRecord.setIsblending((short)0);
   			aheadRepayPlatformRecord.setIsmanblending((short)0);
   			aheadRepayPlatformRecord.setSysbtime(new Date());
   			aheadRepayPlatformRecord.setManbtime(new Date());
   			aheadRepayPlatformRecord.setPaycompany("汇付天下");
   			aheadRepayPlatformRecord.setSysrectime(new Date());
   			aheadRepayPlatformRecord.setReceivetime(new Date());
   			aheadRepayPlatformRecord.setReqquerydata("请求数据包");
   			aheadRepayPlatformRecord.setRecresultdata("接收数据包");
   			aheadRepayPlatformRecord.setAudittime(new Date());
   			aheadRepayPlatformRecord.setAuditman("审核人");
   			aheadRepayPlatformRecord.setMadetime(new Date());
   			aheadRepayPlatformRecord.setPayoutdate(new Date());
   			aheadRepayPlatformRecord.setRemark("备注信息测试");
    			 int count = 0;
 			 count = aheadRepayPlatformRecordMapper.insert(aheadRepayPlatformRecord);
 			if(count > 0){
 				System.out.println("数据添加成功.." );
 			}else{
 				System.out.println("数据添加失败" );
 			}
  
 	 }
  
}
