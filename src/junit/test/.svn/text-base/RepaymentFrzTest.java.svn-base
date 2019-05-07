package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ptpl.mapper.RepaymentFrzMapper;
import com.ptpl.mapper.RepaymentFrzMapper;
 import com.ptpl.model.RepaymentFrz;
import com.ptpl.service.RepaymentFrzServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * 还款冻结解冻记录 测试类
 * RepaymentFrzTest
 * 创建人:cjm
 * 时间：2017年04月21日 16:55:59
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class RepaymentFrzTest {

	 @Autowired
	 private RepaymentFrzMapper repaymentFrzMapper;
	 
	 @Autowired
	 private RepaymentFrzServiceI repaymentFrzServiceI;
	 
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
	 * @Title: findRepaymentFrzs 
	 * @Description: TODO(查询全部信息 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 
	 public void findRepaymentFrzs(){
	     RepaymentFrz repaymentFrz = new  RepaymentFrz();
	     repaymentFrz.setProduct("121221");
		 RepaymentFrz repaymentFrzs2 = repaymentFrzMapper.findRepaymentFrz(repaymentFrz);
		 System.out.println(repaymentFrzs2+"================================");
		 
//		 RepaymentFrz repaymentFrz = new  RepaymentFrz();
//	     repaymentFrz.setProduct("121221");
//		 List<RepaymentFrz> repaymentFrzs2 = repaymentFrzMapper.findRepaymentFrzs(repaymentFrz);
//   		 for(RepaymentFrz repaymentFrz1 :repaymentFrzs2){
//			System.out.println("=========================="+repaymentFrz1);
//  			}
	 }
	 
	 @Test
	 @Transactional
	 public void CallBackTest(){
		    try{
		    	RepaymentFrz repaymentFrz = new RepaymentFrz();
		    	repaymentFrz.setRetcode("测试");     //银行返回码
		    	repaymentFrz.setAuditman("测试");    //审核人  无需审核时，审核人为系统
		    	repaymentFrz.setCardnbr("测试");   //电子账号
		    	repaymentFrzServiceI.insertSelective(repaymentFrz);
		    	
		    	RepaymentFrz repaymentFrz1 = new RepaymentFrz();
		    	repaymentFrz1.setRetcode("测试1dfdsfddgfgfdgfdffffffffffffff");     //银行返回码
		    	repaymentFrz1.setAuditman("测试1");    //审核人  无需审核时，审核人为系统
		    	repaymentFrz1.setCardnbr("测试1");   //电子账号
 		    	repaymentFrzServiceI.insertSelective(repaymentFrz1);
		    	
		    	RepaymentFrz repaymentFrz2 = new RepaymentFrz();
		    	repaymentFrz2.setRetcode("测试2");     //银行返回码
		    	repaymentFrz2.setAuditman("测试2");    //审核人  无需审核时，审核人为系统
		    	repaymentFrz2.setCardnbr("测试2");   //电子账号
		    	repaymentFrzServiceI.insertSelective(repaymentFrz2);
		    	
		    	RepaymentFrz repaymentFrz3 = new RepaymentFrz();
		    	repaymentFrz3.setRetcode("测试3");     //银行返回码
		    	repaymentFrz3.setAuditman("测试3");    //审核人  无需审核时，审核人为系统
		    	repaymentFrz3.setCardnbr("测试3");   //电子账号
		    	repaymentFrzServiceI.insertSelective(repaymentFrz3);
		    	
		    	RepaymentFrz repaymentFrz4 = new RepaymentFrz();
		    	repaymentFrz4.setRetcode("测试4"); //银行返回码
		    	repaymentFrz4.setAuditman("测试4"); //审核人  无需审核时，审核人为系统
		    	repaymentFrz4.setCardnbr("测试4"); //电子账号
		    	repaymentFrzServiceI.insertSelective(repaymentFrz4);
		    	
		    }catch(Exception e){
		    	e.printStackTrace();
 		    	throw new RuntimeException();
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
		 RepaymentFrz repaymentFrz = new RepaymentFrz();
		 repaymentFrz.setId(new BigDecimal(363));
			 repaymentFrz.setBaseid(new BigDecimal(122)); //用户ID
			 repaymentFrz.setFrztime(new Date());     //冻结时间
			repaymentFrz.setReturntime(new Date());     //银行冻结返回时间
			repaymentFrz.setBatchno(StringUtil.getNo());    //还款批次号 
			repaymentFrz.setSerino(StringUtil.getNo());     //冻结申请流水号
			repaymentFrz.setAmount(new BigDecimal(101.00));     //冻结金额
			repaymentFrz.setProduct("0121221");     //标的编号
			repaymentFrz.setRemark("备12注");     //备注
			repaymentFrz.setThawserino(StringUtil.getNo());     //解冻申请流水号
			repaymentFrz.setThawtime(new Date());     //解冻时间
			repaymentFrz.setThawreturntime(new Date());     //解冻返回时间
			repaymentFrz.setStatus((short)1);     //还款状态(1待还款  2审核中 3待处理  4处理中 5还款失败 6已还款 )
			repaymentFrz.setSuccesscount(11);     //成功笔数
			repaymentFrz.setFailcount(11);     //失败笔数
			repaymentFrz.setRetcode("1000230000");     //银行返回码
			repaymentFrz.setIssubmit((short)1);     //是否提交（1是，0否）
			repaymentFrz.setIsblending((short)1);     //是否系统勾兑（0未勾兑，1已勾兑） 勾兑冻结记录
			repaymentFrz.setIsmanblending((short)1);     //是否人工勾兑（0未勾兑，1已勾兑
			repaymentFrz.setSysbtime(new Date());     //系统勾兑时间
			repaymentFrz.setManbtime(new Date());     //人工勾兑时间
			repaymentFrz.setSysrectime(new Date());     //系统勾兑接收数据时间 第一次
			repaymentFrz.setReceivetime(new Date());     //人工勾兑接收数据时间 第一次
			repaymentFrz.setReqquerydata("1请求数据包");     //请求数据包
			repaymentFrz.setRecresultdata("1接收数据包");     //接收数据包
			repaymentFrz.setSubmittime(new Date());     //借款人提交还款时间
			repaymentFrz.setRprealtime(new Date());     //还款实际到账日期
			repaymentFrz.setIsaudit((short)1);     //是否审核审核人  无需审核时，审核人为系统
			repaymentFrz.setAuditman("1审核人");    //审核人  无需审核时，审核人为系统
		    repaymentFrz.setAudittime(new Date());    //审核时间
			repaymentFrz.setCardnbr("1电子账号");   //电子账号
 		 int count = repaymentFrzMapper.updateById(repaymentFrz);
		 if(count > 0){
			 System.out.println("===================更新成功");
		 }else{
			 System.out.println("===================更新不成功");
		 }
	 }
	 
	  /**
	  * 
	 * @Title: findRepaymentFrzById 
	 * @Description: TODO(根据ID查找 测试) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @Test
	 public void findRepaymentFrzById(){
 		
		 RepaymentFrz repaymentFrz= repaymentFrzMapper.findRepaymentFrzById(new BigDecimal(363));
		 System.out.println(repaymentFrz.getId());
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
  		 int count = repaymentFrzMapper.deleteById(new BigDecimal(363));
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
 		  
  			 RepaymentFrz repaymentFrz = new RepaymentFrz();
   			 repaymentFrz.setBaseid(new BigDecimal(1)); //用户ID
   			 repaymentFrz.setFrztime(new Date());     //冻结时间
   			repaymentFrz.setReturntime(new Date());     //银行冻结返回时间
   			repaymentFrz.setBatchno(StringUtil.getNo());    //还款批次号 
   			repaymentFrz.setSerino(StringUtil.getNo());     //冻结申请流水号
   			repaymentFrz.setAmount(new BigDecimal(10.00));     //冻结金额
   			repaymentFrz.setProduct("121221");     //标的编号
   			repaymentFrz.setRemark("备注");     //备注
   			repaymentFrz.setThawserino(StringUtil.getNo());     //解冻申请流水号
   			repaymentFrz.setThawtime(new Date());     //解冻时间
   			repaymentFrz.setThawreturntime(new Date());     //解冻返回时间
   			repaymentFrz.setStatus((short)2);     //还款状态(1待还款  2审核中 3待处理  4处理中 5还款失败 6已还款 )
   			repaymentFrz.setSuccesscount(10);     //成功笔数
   			repaymentFrz.setFailcount(10);     //失败笔数
   			repaymentFrz.setRetcode("0000000");     //银行返回码
   			repaymentFrz.setIssubmit((short)2);     //是否提交（1是，0否）
   			repaymentFrz.setIsblending((short)2);     //是否系统勾兑（0未勾兑，1已勾兑） 勾兑冻结记录
   			repaymentFrz.setIsmanblending((short)2);     //是否人工勾兑（0未勾兑，1已勾兑
   			repaymentFrz.setSysbtime(new Date());     //系统勾兑时间
   			repaymentFrz.setManbtime(new Date());     //人工勾兑时间
   			repaymentFrz.setSysrectime(new Date());     //系统勾兑接收数据时间 第一次
   			repaymentFrz.setReceivetime(new Date());     //人工勾兑接收数据时间 第一次
   			repaymentFrz.setReqquerydata("请求数据包");     //请求数据包
   			repaymentFrz.setRecresultdata("接收数据包");     //接收数据包
   			repaymentFrz.setSubmittime(new Date());     //借款人提交还款时间
   			repaymentFrz.setRprealtime(new Date());     //还款实际到账日期
   			repaymentFrz.setIsaudit((short)2);     //是否审核审核人  无需审核时，审核人为系统
   			repaymentFrz.setAuditman("审核人");    //审核人  无需审核时，审核人为系统
    		repaymentFrz.setAudittime(new Date());    //审核时间
   			repaymentFrz.setCardnbr("电子账号");   //电子账号
  			 int count = 0;
 			 count = repaymentFrzMapper.insertSelective(repaymentFrz);
 			if(count > 0){
 				System.out.println("数据添加成功..");
 			}else{
 				System.out.println("数据添加失败");
 			}
  		  
  	 }
  
}
