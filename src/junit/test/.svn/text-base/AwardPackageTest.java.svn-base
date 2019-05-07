package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.AwardPackage;
import com.ptpl.service.AwardPackageServiceI;
/**
 * @author：liuqh
 * @return:
 * @remark：测试奖品包：增删查改
 */
public class AwardPackageTest {
	private AwardPackageServiceI awardPackageService;
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的awardPackageService对象
        awardPackageService=(AwardPackageServiceI) ac.getBean("awardPackageService");
    }
	@Test
	public void test(){
		System.out.println(awardPackageService);
	}
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试插入非缺省属性的奖品包
	 */
	@Test
	public void testAwardPackage_insert(){
//		Award award = new Award();
//		award.setAno("55");
//		award.setAname("55奖品包");
//		award.setAddtime(new Date());
//		award.setEndtime(new Date());
//		System.out.println(award);
//		System.out.println(awardService.insert(award)+"88888888");
		for(int i=66;i<=66;i++){
			AwardPackage ap = new AwardPackage();
			ap.setApname("情人节大礼包"+i);
			ap.setApno("奖品包包编号"+i);
			ap.setIscancel((short)1);
			ap.setQuantityall(1000L);
			ap.setQuantityrest(1000L);
			ap.setInvirtseq("站内虚拟编号"+i);
			ap.setInvirtqty(100);
			ap.setInvirtno("奖品包编号"+i);
			ap.setInvirtattr((short)i);
			ap.setInvirtname("奖品包名称"+i);
			
			ap.setOutvirtseq("站外虚拟编号"+i);
			ap.setOutvirtqty(2000);
			ap.setOutvirtno("站外虚拟奖品包编号"+i);
			ap.setOutvirtattr((short)2);
			ap.setOutvirtname("站外虚拟奖品包名称"+i);
			
			ap.setOutrealseq("站外实体拟编号"+i);
			ap.setOutrealqty(500);
			ap.setOutrealno("站外实体奖品包编号"+i);
			ap.setOutrealattr((short)3);
			ap.setOutrealname("站外实体奖品包名称"+i);
			
			ap.setAddtime(new Date());
			ap.setAddman("A"+i);
			ap.setRemark("备注：xxxxxx"+i);
			
			awardPackageService.insert(ap);
		}
	}
	
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：根据任意条件查奖品包列表
	 */
	@Test
	public void testSelectAwardPackageByCondition(){
		AwardPackage condition = new AwardPackage();
		condition.setId(new BigDecimal("51"));
		condition.setRemark("aabbcc11");
		List<AwardPackage> apl = awardPackageService.selectByCondition(condition);
		for (AwardPackage ap : apl) {
			System.out.println(ap);
		}
	}
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试修改奖品包
	 */
	@Test
	public void testUpdateAwardPackage(){
		AwardPackage ap = new AwardPackage();
		ap.setId(new BigDecimal("51"));
		ap.setRemark("aabbcc11");
		awardPackageService.update(ap);
	}
	
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试删除奖品包
	 */
	@Test
	public void testDeleteGfundsInt(){
		awardPackageService.delete(new BigDecimal(63));
	}
	
	
}
