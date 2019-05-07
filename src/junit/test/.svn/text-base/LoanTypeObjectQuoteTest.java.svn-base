package junit.test;

import com.ptpl.model.LoanTypeObjectQuote;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml",
		"classpath:mybatis/mybatis-config.xml"})
public class LoanTypeObjectQuoteTest {
     @Autowired
	 private LoanTypeObjectQuoteServiceI LoanTypeObjectQuoteServic;

     /**
      *  测试按条件查找
      * @author :liuqh
      * @date 2017/6/24 17:32
      */
     @Test
    public void method(){
         LoanTypeObjectQuote loanTypeObjectQuote = new LoanTypeObjectQuote();
         loanTypeObjectQuote.setSerialno((short)1);
         List<LoanTypeObjectQuote> loanTypeObjectQuotes = LoanTypeObjectQuoteServic.gettypeObjectQuotes(loanTypeObjectQuote);
         for (LoanTypeObjectQuote l:loanTypeObjectQuotes){
             System.out.println(l);
         }
     }
}
