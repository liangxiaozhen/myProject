package junit.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;

public class BacthFileRecordTest {

	private BacthFileRecordServiceI bacthFileRecordService;//批量文件记录表
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的activityAwardListService对象
        bacthFileRecordService=(BacthFileRecordServiceI) ac.getBean("bacthFileRecordService");
    }
	
	@Test
	public void bfr_update(){
		
		//更新批量文件记录表
    	BacthFileRecord bfr = new BacthFileRecord();
    	bfr.setId(new BigDecimal(4));
    	bfr.setDealTime(new Date());//处理文件时间
    	bfr.setIsDealResult((short)1);//是否已处理结果文件
    	bfr.setDealResult((short)1);//处理结果
    	bacthFileRecordService.update(bfr);
	}
	
	@Test
	public void bfr_insert(){
		
		//插入批量文件记录
		BacthFileRecord bfr = new BacthFileRecord();
		bfr.setFilePath("D:\\batchfile\\800114\\trqt\\20170503\\");
		bfr.setSendFileName("3004-TRQT-800114-000045-20160510");
		bfr.setGetFileName("3004-TRQTRES-800114-000045-20160510");
		bfr.setCoinstCode("800114");
		bfr.setPName("干将p2p平台");
		bfr.setIsDealResult((short)0);//是否已处理文件
		bfr.setFileType((short)2);//业务文件类型
		bfr.setIsSend((short)0);
        bfr.setSendResult((short)0);//发送结果	
        int row = bacthFileRecordService.insert(bfr);
	}
}
