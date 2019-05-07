package junit.test;

import com.ptpl.mapper.GlobalSettingMapper;
import com.ptpl.model.GlobalSetting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/6/24 0024.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:mybatis/mybatis-config.xml"})


public class GlobalSettingTest {

    @Autowired
    private GlobalSettingMapper mapper;


    @Test
    public void  updateTest(){
        GlobalSetting record = new GlobalSetting();
        record.setId(new BigDecimal(40));
        record.setWebsitename("钱钱");
        record.setTitle("www.my");
        record.setKeyworld("查找");
        record.setDescription("哈哈哈哈");
        record.setStrstatus("111000110000000000000000000000");
        record.setAnnualrate(20);
        record.setGjidcode("8888888");
        record.setUrgentmethod(2);
        record.setGlobalverifycode(3);
        record.setUpgrade(2);
        record.setPresetstr("GG");
        record.setAuthtimes(5);
        record.setpREAccount("2");
        record.setpFeeAccount("3");
        record.setAutorptimesltd(6);
        record.setAutorpstinvl(2);
        record.setNewerBidRule(2);
        record.setNewerBidCount(1);
        record.setNewerBidAmount(new Float(5555));
        record.setNewerBidDayLimit(42);
        record.setFailTTime(666);
       // int i = mapper.insertSelective(record);
         int i = mapper.updateByPrimaryKeySelective(record);
        System.out.println("i="+i);
        //int updateByPrimaryKeySelective(GlobalSetting record);
    }
        @Test
        public  void deleteTest(){

            int i = mapper.deleteByPrimaryKey(new BigDecimal(25));
            System.out.println("i="+i);


        }


}
