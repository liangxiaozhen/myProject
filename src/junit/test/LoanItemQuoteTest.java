package junit.test;

import com.ptpl.model.LoanItemQuote;
import com.ptpl.service.LoanItemQuoteServiceI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：liuqh
 * @return:
 * @remark：测试项目引用的crud
 */
public class LoanItemQuoteTest {
	private LoanItemQuoteServiceI loanItemQuoteService;

	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        loanItemQuoteService=(LoanItemQuoteServiceI) ac.getBean("loanItemQuoteService");
    }
	
	
	@Test
	public void test(){
    	//查出公共自填类的项目
        List<LoanItemQuote> public_ZiTians = loanItemQuoteService.public_ZiTian();
        //查出公共选择类的项目(及其对应的选择项也查出来)
        List<LoanItemQuote> public_XuanZes = loanItemQuoteService.public_XuanZe();
        //把公共自填类的项目和公共选择类的项目放在一起
        List<LoanItemQuote> loanItemQuotes = new ArrayList<LoanItemQuote>();
        loanItemQuotes.addAll(public_ZiTians);
        loanItemQuotes.addAll(public_XuanZes);
		System.out.println(loanItemQuotes);
	}

	
	

}
