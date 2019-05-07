package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.UserGrade;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * @author:liuqh
 * @date:2016年07月12日 23:13:12
 * @description:标的利息管理费设置
 */
public class InterestExpenseTest {
	private InterestExpenseServiceI interestExpenseService;
	
	private UserGradeServiceI userGradeService;
	
	
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        interestExpenseService=(InterestExpenseServiceI) ac.getBean("interestExpenseService");
        userGradeService=(UserGradeServiceI) ac.getBean(UserGradeServiceI.class);
        
    }
	
	@Test
	public void Test(){
		InterestExpense interestExpense = interestExpenseService.selectInterestExpenseById(new BigDecimal(1187));
		System.out.println(interestExpense.getUgrade());
  		List<Integer> pa1 = StringUtil.pars(interestExpense.getUgrade());
		System.out.println(JSON.toJSON(pa1));
		for(Integer integer : pa1){
			UserGrade userGrade = new UserGrade();
			userGrade.setUgrade(new BigDecimal(integer));
			List<UserGrade> userGrades = userGradeService.selective(userGrade);
 			if(userGrades.size() > 0){
				
				UserGrade userGrade2 = userGrades.get(0);
				System.out.println(userGrade2.getUgradename());
			}
			
			 
		}
		
	}
	
//	@Test
//	public void selAllTest(){
//		List<InterestExpense> expenses = interestExpenseService.selectByCondition(null);
//		for(InterestExpense expense : expenses){
// 			System.out.println(expense.getId());  //
//			System.out.println(expense.getGfitype());  //计算方式（1根据用户等级，2根据标的风险等级
//			System.out.println(expense.getTtype()); //标的类型（1000000…） 30位
//			System.out.println(expense.getUgrade());  //会员等级
//			System.out.println(expense.getIepercent());  //利息管理费百份比
//			System.out.println(expense.getMaxiefee());  //该段最高利息管理收费金额
//			System.out.println(expense.getIsaudit());  //资金清算是否需要审
//			System.out.println(expense.getIstemplet());  //是否为模板（0否，1是）
//			System.out.println(expense.getAddman());  //添加人
//			System.out.println(expense.getAddtime()); //添加时间
//			System.out.println(expense.getRemark());  //备注
//		}
//	   
//	}
	
//	@Test
//	public void selTest(){
//		InterestExpense expense = interestExpenseService.selectInterestExpenseById(new BigDecimal(1165));
//		System.out.println(expense.getId());  //
//		System.out.println(expense.getGfitype());  //计算方式（1根据用户等级，2根据标的风险等级
//		System.out.println(expense.getTtype()); //标的类型（1000000…） 30位
//		System.out.println(expense.getUgrade());  //会员等级
//		System.out.println(expense.getIepercent());  //利息管理费百份比
//		System.out.println(expense.getMaxiefee());  //该段最高利息管理收费金额
//		System.out.println(expense.getIsaudit());  //资金清算是否需要审
//		System.out.println(expense.getIstemplet());  //是否为模板（0否，1是）
//		System.out.println(expense.getAddman());  //添加人
//		System.out.println(expense.getAddtime()); //添加时间
//		System.out.println(expense.getRemark());  //备注
//	   
//	}
	
	@Test
	public void insertTest(){
		for(int i = 0;i<10;i++){
			
			InterestExpense expense = new InterestExpense();
			expense.setId(new BigDecimal(13));  //
			expense.setGfitype((short)1);  //计算方式（1根据用户等级，2根据标的风险等级
			expense.setTtype(StringUtil.getPlaceholder(30));  //标的类型（1000000…） 30位
			expense.setUgrade(StringUtil.getPlaceholder(51));  //会员等级
			expense.setIepercent(0.15);  //利息管理费百份比
			expense.setMaxiefee(551.00);  //该段最高利息管理收费金额
			expense.setIsaudit((short)0);  //资金清算是否需要审
			expense.setIstemplet((short)0);  //是否为模板（0否，1是）
			expense.setAddman("添加人5555");  //添加人
			expense.setAddtime(new Date()); //添加时间
			expense.setRemark("测试备注信息555");  //备注
			int count = 0;
			count = interestExpenseService.insertSelective(expense);
			if(count > 0){
				System.out.println("保存成功");
			}else{
				System.out.println("保存失败");
			}
		}
	}

//	@Test
//	public void updateTest(){
//		InterestExpense expense = new InterestExpense();
//		expense.setId(new BigDecimal(1164));  //
//		expense.setGfitype((short)1);  //计算方式（1根据用户等级，2根据标的风险等级
//		expense.setTtype(StringUtil.setPlaceholder(StringUtil.getPlaceholder(30),15));  //标的类型（1000000…） 30位
//		expense.setUgrade(StringUtil.setPlaceholder(StringUtil.getPlaceholder(51),5));  //会员等级
//		expense.setIepercent(0.82);  //利息管理费百份比
//		expense.setMaxiefee(8.00);  //该段最高利息管理收费金额
//		expense.setIsaudit((short)0);  //资金清算是否需要审
//		expense.setIstemplet((short)0);  //是否为模板（0否，1是）
//		expense.setAddman("添加人1");  //添加人
//		expense.setAddtime(new Date()); //添加时间
//		expense.setRemark("测试备注信息0");  //备注
//	    int count = 0;
//	    count = interestExpenseService.updateById(expense);
//	    if(count > 0){
//	    	System.out.println("更新成功");
//	    }else{
//	    	System.out.println("更新失败");
//	    }
//	}
	
	
//	@Test
//	public void delTest(){
// 	    int count = 0;
//	    count = interestExpenseService.delete(new BigDecimal(1164));
//	    if(count > 0){
//	    	System.out.println("更新成功");
//	    }else{
//	    	System.out.println("更新失败");
//	    }
//	}
	
//	@Test
//	public void test(){
//		System.out.println(interestExpenseService);
//	}
//
//
//	@Test
//	public void testAddInterestExpense_insert(){
//		for(int i=1;i<=50;i++){
//			InterestExpense interestExpense = new InterestExpense();
//			interestExpense.setId(new BigDecimal(i));
//			interestExpense.setIntexpno("利息管理费编号"+i);
//			interestExpense.setIerecman("利息管理费收款人"+i);
//			interestExpense.setUgrade("0011");
//			//interestExpense.setIequota(11.02);
//			interestExpense.setIepercent(2.03);
//			interestExpense.setMiniefee(0.36);
//			interestExpense.setMaxiefee(0.36);
//			interestExpense.setIstemplet((short)1);
//			
//			interestExpenseService.insert(interestExpense);
//		}
//	}
//	
//	
//	
//
//	@Test
//	public void testSelectTenderItemByCondition(){
//		InterestExpense condition = new InterestExpense();
////		condition.setId(new BigDecimal(2));
//		condition.setUgrade("1111");
//		List<InterestExpense> interestExpenseList = interestExpenseService.selectByCondition(condition);
//		for (InterestExpense interestExpense : interestExpenseList) {
//			System.out.println(interestExpense);
//		}
//	}
//	
//
//	@Test
//	public void testUpdateInterestExpense(){
//		InterestExpense interestExpense = new InterestExpense();
//		interestExpense.setId(new BigDecimal(50));
//		interestExpense.setIerecman("xxx");
//		interestExpenseService.update(interestExpense);
//	}
//	
//
//	@Test
//	public void testDeleteInterestExpense(){
//		interestExpenseService.delete(new BigDecimal(50));
//	}
	

}
