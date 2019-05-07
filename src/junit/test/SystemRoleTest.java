package junit.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.mapper.SystemRoleMapper;
 import com.ptpl.model.SystemRole;
import com.ptpl.service.SystemRoleServiceI;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:mybatis/mybatis-config.xml"})
public class SystemRoleTest {

	@Autowired
	 public SystemRoleServiceI systemRoleMapper;
	
	
	/**
	 * 
	* @Title: update 
	* @Description: TODO(角色 根据id 查询对应的角色资源  测试) 
	* @param   参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
//	@Test
//	public void findRoles(){
//		Integer id = 1;
//    	SystemRole systemRoles= systemRoleMapper.findSystemRoleById(id);
//    	System.out.println(systemRoles.getRolename()+"========="+systemRoles.getSystemResource().getCheckflag()+"========="+
//    	systemRoles.getSystemResource().getNumbercode());
// 		  
//		
//	}
	
	
//	/**
//	 * 
//	* @Title: update 
//	* @Description: TODO(角色 查询测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void findRoles(){
//    	List<SystemRole> systemRoles= systemRoleMapper.findSystemRoles();
// 		 for(SystemRole systemRole2 : systemRoles){
// 			 System.out.println(systemRole2.getRolename());
// 			 System.out.println(systemRole2.getId());
//  		 }
//		
//	}
//	
	
//	/**
//	 * 
//	* @Title: update 
//	* @Description: TODO(角色 更改测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void update(){
//		SystemRole systemRole = new SystemRole();
//		systemRole.setId(new BigDecimal(6));
//		systemRole.setRolename("学友老大");
//		int count = 0;
//		count = systemRoleMapper.updateById(systemRole);
// 		if(count>0){
//			System.out.println("角色删除成功");
//		}else{
//			System.out.println("角色删除失败");
//		}
//		
//	}
	
//	 /**
//	  * 删除测试
//	 * @Title: delete 
//	 * @Description: TODO(角色 删除测试) 
//	 * @param   参数说明 
//	 * @return void    返回类型 
//	 * @author chenjiaming
//	 * @throws
//	  */
//	@Test
//	public void delete(){
//		SystemRole systemRole = new SystemRole();
//		systemRole.setId(new BigDecimal(9));
//		int count = 0;
//		count = systemRoleMapper.deleteById(systemRole);
// 		if(count>0){
//			System.out.println("角色删除成功");
//		}else{
//			System.out.println("角色删除失败");
//		}
//		
//	}
	
	
	
//	
//	/**
//	 * 角色增加测试
//	* @Title: save 
//	* @Description: TODO(角色 新增测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void handl08(){
//		SystemRole systemRole = new SystemRole();
// //		adminUser.setId(new BigDecimal(1));
//  		systemRole.setRolename("肖翼老干部老大");
// 		System.out.println(systemRole.getId()+"==========="+systemRole.getRolename());
//		int count = 0;
//		System.out.println(systemRoleMapper);
//		count = systemRoleMapper.insert(systemRole);
//		//System.out.println(count+"tiao");
//		if(count>0){
//			System.out.println("角色增加成功");
//		}else{
//			System.out.println("角色增加失败");
//		}
//	}
}
