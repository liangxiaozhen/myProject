package junit.test;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.controller.BaseController;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.service.RedEnveLopeItemServiceI;

public class RedEnveLopeItemTest extends BaseController{

	private RedEnveLopeItemServiceI redEnveLopeItemService;
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的activityAwardListService对象
        redEnveLopeItemService=(RedEnveLopeItemServiceI) ac.getBean("redEnveLopeItemService");
    }
	
	@Test
	public void update(){
		List<RedEnveLopeItem> list = redEnveLopeItemService.selectByCondition(null);
		for(RedEnveLopeItem r: list){
			System.out.println("r: "+r);
			if(StringUtils.isNotBlank(r.getUsername())&&r.getId()!=new BigDecimal(422)){
				RedEnveLopeItem re = new RedEnveLopeItem();
				re.setId(r.getId());
				re.setUsername(setEncrypt(r.getUsername()));
				redEnveLopeItemService.updateByPrimaryKeySelective(re);
			}
		}
	}
	
}
