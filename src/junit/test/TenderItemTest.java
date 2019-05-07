package junit.test;

import com.ptpl.mapper.TenderItemMapper;
import com.ptpl.model.TenderItem;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author:liuqh
 * @date:2016年07月14日 15:50:16
 * @description:标的设置
 */
public class TenderItemTest {
	private TenderItemServiceI tenderItemService;
	private TenderItemMapper tenderItemMapper;
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        tenderItemService=(TenderItemServiceI) ac.getBean("tenderItemService");
    }

	@Test
	public void test11(){
		System.out.println(tenderItemService);
		TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(684));
		System.out.println(tenderItem+"-=====================================");
		
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("loanappid", new BigDecimal("222"));
		
		TenderItem tenderItem2 = tenderItemService.findTenderItemByCondition(maps).get(0);
		System.out.println(tenderItem2.getTno());
	}
	
	@Test
	public void test(){
		System.out.println(tenderItemService);
	}


	@Test
	public void testAddTenderItem_insert(){
		for(int i=1;i<=50;i++){
			TenderItem tenderItem = new TenderItem();
			tenderItem.setLoanappid(new BigDecimal(i));
			tenderItem.setTno("标号"+i);
			tenderItem.setTname("AAA"+i);
			tenderItem.setValuedate(new Date());
			tenderItem.setTamount(11.2);
			tenderItem.setFinishtamount(333.00);
			tenderItem.setTpro((short)1);
			tenderItem.setTbegintime(new Date());
			tenderItem.setTendtime(new Date());
			tenderItem.setLoantime(3);
			tenderItem.setDayormonth("月");
			tenderItem.setTinterest(1.2);
			tenderItem.setTdesc("优质标");
			tenderItem.setTstatus((short)1);
			tenderItem.setPmiscpayman("A"+i);
			tenderItem.setPmiscrecman("B"+i);
			tenderItem.setOnettimes((short)1);
			tenderItem.setCrestrict("android");
			tenderItem.setIsfaketender((short)1);
			tenderItem.setIsautotender((short)1);
			tenderItem.setTtype((short)1);
			tenderItem.setIsacancel((short)1);
			tenderItem.setAllowcugrade("1111");
			tenderItem.setIssetgfundsint((short)1);
			tenderItem.setRepaymenttype((short)1);
			tenderItem.setRepaymentpro((short)1);
			tenderItem.setRepaysetmode((short)1);
			tenderItem.setRepayman("C"+i);
			tenderItem.setIsapartrepay((short)1);
			tenderItem.setRepaytimepoint("aaa");
			tenderItem.setIsappointtender((short)1);
			tenderItem.setTpass("123ddfd"+i);
			tenderItem.setMinoncetamount(2.2);
			tenderItem.setMaxoncetamount(12.2);
			tenderItem.setRemovenameno("");
			tenderItem.setIsfailtc((short)1);
			tenderItem.setFailtcno("1fdc21f");
			tenderItem.setIsaoverduec((short)1);
			tenderItem.setOverduecno("sdf212");
			tenderItem.setIsaaheadrepay((short)1);
			tenderItem.setAheadrepayno("dfd222");
			tenderItem.setIsadebtattorn((short)1);
			tenderItem.setAheadrepayno("dfdeee22");
			tenderItem.setIsadebtattorn((short)1);
			tenderItem.setDebtattornno("ghgfh22");
			tenderItem.setIsaplus((short)1);
			tenderItem.setPlusno("eee11"+i);
			tenderItem.setIsamediacy((short)1);
			tenderItem.setMediacyfeeno("bd3r"+i);
			tenderItem.setIsaguarantee((short)1);
			tenderItem.setGuaranteefeeno("23234"+i);
			tenderItem.setIsaintexp((short)1);
			tenderItem.setIntexpno("54354rrtr");
			tenderItem.setIsariskgm((short)1);
			tenderItem.setRiskgmno("er22");
			tenderItem.setRepaymode((short)1);
			tenderItem.setBegininvestmoney(22.3);
			tenderItem.setIsmultiple((short)1);
			tenderItem.setTotalmoneyrestrict(333.2);
			tenderItem.setAveragenum(12300L);
			tenderItem.setAveragemoney(123.123);
			tenderItem.setIsaudit((short)1);
			tenderItem.setAuditman("fd2454");
			tenderItem.setAudittime(new Date());
			tenderItem.setAudittime(new Date());
			tenderItem.setRemark("xxxjjj");
			tenderItem.setIstemplet((short)1);
			
			tenderItemService.insert(tenderItem);
		}
	}
	
	
	

	@Test
	public void testSelectTenderItemByCondition(){
		TenderItem condition = new TenderItem();
//		condition.setId(new BigDecimal(49));
		//condition.setIstemplet((short)0);
		List<TenderItem> tenderItemList = tenderItemService.selectByCondition(condition);
		for (TenderItem tenderItem : tenderItemList) {
			System.out.println("===================="+tenderItem.getTno()+"==="+tenderItem.getTname());
		}
	}
	

	@Test
	public void testUpdateTenderItem(){
		TenderItem tenderItem = new TenderItem();
		tenderItem.setId(new BigDecimal(2367));
		tenderItem.setRemark("yyy");
		tenderItemService.update(tenderItem);
	}
	

	@Test
	public void testDeleteTenderItem(){
		tenderItemService.delete(new BigDecimal(50));
	}
	
	@Test
	public void testtoubio(){
	    boolean flag=AutoGenerateRepayMentUtil.checkGenerateRepay(new BigDecimal(903));
	    System.out.println(flag);
	}

}
