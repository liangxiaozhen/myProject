package junit.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.controller.BaseController;
import com.ptpl.model.ActiveObjectList;
import com.ptpl.service.ActiveObjectListServiceI;

/**
 * 业务对象名单的获奖拼单进行加密(针对服务器的数据)
 * @author admin
 *
 */
public class ActiveObjectListTest extends BaseController{

	private ActiveObjectListServiceI activeObjectListService;
	@Before
	public void before(){
		//使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的activityAwardListService对象
        activeObjectListService = (ActiveObjectListServiceI)ac.getBean("activeObjectListService");
	}
	
	@Test
	public void aos_update(){
		ActiveObjectList aos = new ActiveObjectList();
		aos.setNameType((short)3);
		List<ActiveObjectList> list = activeObjectListService.selectActiveObjectLists(aos);
		for(ActiveObjectList a: list){
			System.out.println("a: "+a);
			ActiveObjectList aa = new ActiveObjectList();
			aa.setId(a.getId());
			aa.setNameContent(setEncrypt(a.getNameContent()));
			activeObjectListService.updateByPrimaryKeySelective(aa);
		}
	}
	
}
