package junit.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.service.ActivityAwardListServiceI;
/**
 * @author：liuqh
 * @return:
 * @remark：测试获奖名单：增删查改
 */
public class ActivityAwardListTest extends BaseController{
	private ActivityAwardListServiceI activityAwardListService;
	@Before
    public void before(){
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-mvc.xml","spring/applicationContext-*.xml"});
        //从Spring容器中根据bean的id取出我们要使用的activityAwardListService对象
        activityAwardListService=(ActivityAwardListServiceI) ac.getBean("activityAwardListService");
    }
	
	@Test
	public void aal_update(){
		//更新奖品名单的处理人和处理时间
	  //  AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER); 
	    ActivityAwardList activityAwardList = new ActivityAwardList();
	    activityAwardList.setId(new BigDecimal(1629));
	    activityAwardList.setDealman("admin");//处理人
	    activityAwardList.setDealtime(new Date());//处理时间
	    activityAwardListService.update(activityAwardList);
	}
	
	
	/*给用户名加密*/
	@Test
	public void test1(){
		List<ActivityAwardList> aalList = activityAwardListService.getSelectstatus();
		for(ActivityAwardList a:aalList){
			if(StringUtils.isNotBlank(a.getUsername())){
				a.setUsername(setEncrypt(a.getUsername()));
				ActivityAwardList aa = new ActivityAwardList();
				aa.setId(a.getId());
				aa.setUsername(a.getUsername());
				activityAwardListService.update(aa);
			}
		}
		//System.out.println(activityAwardListService);
	}
	/**
	 * @author：liuqh
	 * @return:
	 * @remark：测试插入非缺省属性的获奖名单
	 *//*
	@Test
	public void testActivityAwardList_insert(){
//		Award award = new Award();
//		award.setAno("55");
//		award.setAname("55奖品");
//		award.setAddtime(new Date());
//		award.setEndtime(new Date());
//		System.out.println(award);
//		System.out.println(awardService.insert(award)+"88888888");
		for(int i=1;i<=50;i++){
			ActivityAwardList aal = new ActivityAwardList();
	//		aal.setActid(new BigDecimal(i));
			aal.setBaseid(new BigDecimal(i));
			aal.setUsername("小A"+i);
			aal.setAwardno("奖品编号"+i);
			aal.setAwardname("奖品名称"+i);
			aal.setAwardmoney(33.0+i);
			aal.setAwardquantity(i);
			aal.setStatus((short)1);
			aal.setMademan("制作人"+i);
			aal.setMadetime(new Date());
			aal.setAuditman("审核人"+i);
			aal.setAudittime(new Date());
			aal.setDealman("处理人");
			aal.setDealtime(new Date());
			aal.setRemark("备注:xx"+i);
			activityAwardListService.insert(aal);
		}
	}
	
	
	*//**
	 * @author：liuqh
	 * @return:
	 * @remark：根据任意条件查获奖名单
	 *//*
	@Test
	public void testSelectActivityAwardListByCondition(){
		ActivityAwardList condition = new ActivityAwardList();
		condition.setId(new BigDecimal("2"));
		condition.setUsername("xxx");
//		List<ActivityAwardList> list = activityAwardListService.selectByCondition(condition);
		for (ActivityAwardList aal : list) {
			System.out.println(aal);
		}
	}
	
	*//**
	 * @author：liuqh
	 * @return:
	 * @remark：测试修改获奖名单
	 *//*
	@Test
	public void testUpdateActivityAwardList(){
		ActivityAwardList aal = new ActivityAwardList();
		aal.setId(new BigDecimal("2"));
		aal.setUsername("xxx");
		aal.setRemark("aabbcc");
		activityAwardListService.update(aal);
	}
	
	*//**
	 * @author：liuqh
	 * @return:
	 * @remark：测试删除获奖名单
	 *//*
	@Test
	public void testDeleteActivityAwardList(){
		activityAwardListService.delete(new BigDecimal(3));
	}*/


}
