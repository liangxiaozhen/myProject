package junit.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.mapper.SystemRoleMapper;
import com.ptpl.mapper.UserMapper;
import com.ptpl.mapper.UserRechargeMapper;
import com.ptpl.model.SystemRole;
import com.ptpl.model.TenderItem;
import com.ptpl.service.TenderItemServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class FzhTest {

	 @Autowired
	 private UserMapper userMapper;
	 
	 @Autowired
	 private  UserRechargeMapper userRechargeMapper;
	 
	 
	 @Autowired
	 private  SystemRoleMapper systemRoleMapper;
	 @Autowired
	 private  TenderItemServiceI tenderItemServiceI;  
//	 @Test
//	 public void handle09(){
//			List<SystemRole> roles = systemRoleMapper.getAllSystemRole();
//			
//			PageInfo<SystemRole> pagehelper = new PageInfo<SystemRole>(roles);
//			System.out.println(pagehelper);
//	 }
	 
	 @Test
	 public void handle08(){
		 ApplicationContext content = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		 System.out.println(content);
		 String[] strings = content.getBeanDefinitionNames();
		 for(String string :strings){
			 System.out.println("==================="+string);
		 }
		 
	 }
	 
	 @Test
	 public void handle076(){
	  		TenderItem  tenderItem = tenderItemServiceI.findTenderItemById(new BigDecimal(684));
            System.out.println(tenderItem.getDayormonth());
            
            TenderItem  tenderItem2 = new TenderItem();
            tenderItem2.setId(new BigDecimal(685));
            List<TenderItem>  tenderItem3 =  tenderItemServiceI.selectByCondition(tenderItem2);
            System.out.println(tenderItem3.get(0).getDayormonth()+"===");
			 System.out.println("==================="+SpringContextHolder.getBean("interestExpenseRecordService"));
 		 
	 }
	 
//	 @Test
//	 public void handld07(){
//		 List<SystemRole> systemRoles = systemRoleMapper.getAllSystemRole();
//		 int count = systemRoleMapper.countSystemRole();
//		 //System.out.println("总数是====================="+count);
//		// System.out.println("=========================="+systemRoles);
//		 
//		 
////		 Iterator<SystemRole> iterator = systemRoles.iterator();
////		 while(iterator.hasNext()){
////			 System.out.println("===="+iterator.next().getRolename());
////		 }
//		 
//		 
//		// for(SystemRole systemRole :systemRoles){
//			 //System.out.println("=========================="+systemRole);
//			// System.out.println("===="+systemRole.getRolename());
// 		// }
//		 
//		 
//	 }
	 
	 
//	 @Test
//	 public void handld06(){
//		 SystemRole systemRole = new SystemRole();
//		 systemRole.setId(new BigDecimal(4));
//		 systemRole.setRolename("张三测试数据");
//		 int count = systemRoleMapper.updateById(systemRole);
//		 if(count > 0){
//			 System.out.println("===================更新成功");
//		 }else{
//			 System.out.println("===================更新不成功");
//		 }
//	 }
	 
	 
//	 @Test
//	 public void handld05(){
// 		 BigDecimal bd = new BigDecimal(2);
// 		 int count = systemRoleMapper.deleteById(bd);
// 		 if(count > 0){
// 			 System.out.println("=============================删除成功");
// 		 }else{
// 			 System.out.println("=============================删除失败");
// 		 }
//	 }
	 
// 	 @Test
//	 public void handld04(){
// 		 for(int i = 0 ;i<=230;i++){
//  			 SystemRole role = new SystemRole();
// 			 BigDecimal bd = new BigDecimal(i);
// 			 role.setId(bd);
// 			 role.setRolename("测试用户");
// 			 int count = 0;
// 			 count = systemRoleMapper.insertSelective(role);
// 			if(count > 0){
// 				System.out.println("数据添加成功.."+i);
// 			}else{
// 				System.out.println("数据添加失败"+i);
// 			}
//  		 }
// 	 }
//	 
	 
	 
//	 @Test
//	 public void handld03(){
//		 
//		 List<UserRecharge> userRechargeMappers = userRechargeMapper.getUserRecharges();
//		 for(UserRecharge userRecharge:userRechargeMappers){
//			 System.out.println(userRecharge.getId());
//		 }
//	 }
	
}
