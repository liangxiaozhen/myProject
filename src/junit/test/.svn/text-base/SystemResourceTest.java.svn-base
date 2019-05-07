package junit.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ptpl.mapper.SystemResourceMapper;
import com.ptpl.model.AdminUser;
import com.ptpl.model.SystemResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml","classpath:mybatis/mybatis-config.xml"})
public class SystemResourceTest {
	
	
	@Autowired
	private SystemResourceMapper systemResourceMapper;
	
	@Test
	public void test(){
		AdminUser adminUser = new AdminUser();
		adminUser.setId(new BigDecimal(2));
		List<SystemResource>  systemResources = systemResourceMapper.getSystemResources(adminUser);
		
		for( SystemResource resource : systemResources){
			System.out.println("=========="+resource.getMenuname());
			System.out.println("=========="+resource.getId());
			
		}
	}
	
//	/**
//	 * 
//	* @Title: hand05 
//	* @Description: TODO(删除方法测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void hand05(){
//		 SystemResource systemResource = new SystemResource();
//  		 systemResource.setId(new BigDecimal(20));
//		 int count = 0 ;
//		 count = systemResourceMapper.deleteById(systemResource.getId());
//		 if(count >0){
//			 System.out.println("=======================================保存成功");
//		 }else{
//			 System.out.println("=======================================保存不成功");
//
//		 }
//	}
	
	/**
	 * 
	* @Title: hand03 
	* @Description: TODO(角色资源更新测试) 
	* @param   参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
//	@Test
//	public void hand04(){
//		 SystemResource systemResource = new SystemResource();
//		 systemResource.setMenuname("是是是");
//		 systemResource.setNumbercode("sdsds");
//		 systemResource.setOperatortype("dsdsd");
//		 systemResource.setResourceurl("dsdsdccc");;
// 		 
//		 systemResource.setId(new BigDecimal(20));
//		 int count = 0 ;
//		 count = systemResourceMapper.updateById(systemResource);
//		 if(count >0){
//			 System.out.println("=======================================保存成功");
//		 }else{
//			 System.out.println("=======================================保存不成功");
//
//		 }
//	}
	
//	/**
//	 * 
//	* @Title: hand03 
//	* @Description: TODO(角色资源添加测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void hand03(){
//		 SystemResource resource = new SystemResource();
//		 resource.setMenuname("测试菜单");
//		 int count = 0 ;
//		 count = systemResourceMapper.insertSelective(resource);
//		 if(count >0){
//			 System.out.println("=======================================保存成功");
//		 }else{
//			 System.out.println("=======================================保存不成功");
//
//		 }
//	}
	
	
	
//	/**
//	 * 
//	* @Title: hand03 
//	* @Description: TODO(全部子角色查询 测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void hand03(){
// 		List<SystemResource> systemResources =systemResourceMapper.findChildrenSystemResources(new BigDecimal(1));
//		for(SystemResource systemResource :systemResources){
//			System.out.println("=====getId========="+systemResource.getId());
//			System.out.println("======getResourceurl========"+systemResource.getResourceurl());
//			System.out.println("=====getNumbercode========="+systemResource.getNumbercode());
//			System.out.println("=======getFathernumber======="+systemResource.getFathernumber());
//			System.out.println("=====getMenuname========="+systemResource.getFathernumber());
//			System.out.println("======getMenuname()========"+systemResource.getMenuname());
//			System.out.println("=====getCheckflag(========="+systemResource.getCheckflag());
//			System.out.println("=======getOperatortype======="+systemResource.getOperatortype());
//			System.out.println("===============================");
//  		}
//	}
	
//	/**
//	 * 
//	* @Title: hand02 
//	* @Description: TODO(查询全部角色资源测试) 
//	* @param   参数说明 
//	* @return void    返回类型 
//	* @author chenjiaming
//	* @throws
//	 */
//	@Test
//	public void hand02(){
//		List<SystemResource> systemResources =systemResourceMapper.findSystemResources();
//		for(SystemResource systemResource :systemResources){
//			System.out.println("=====getId========="+systemResource.getId());
//			System.out.println("======getResourceurl========"+systemResource.getResourceurl());
//			System.out.println("=====getNumbercode========="+systemResource.getNumbercode());
//			System.out.println("=======getFathernumber======="+systemResource.getFathernumber());
//			System.out.println("=====getMenuname========="+systemResource.getFathernumber());
//			System.out.println("======getMenuname()========"+systemResource.getMenuname());
//			System.out.println("=====getCheckflag(========="+systemResource.getCheckflag());
//			System.out.println("=======getOperatortype======="+systemResource.getOperatortype());
//			System.out.println("===============================");
//  		}
//	}
	
	
//	@Test
//	public void hand01(){
//		System.out.println("测试语句");
//	}
}
