package com.ptpl.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.UserBonus_Constant;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBonusPointsServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/user/activity")
public class UserAwardController extends BaseController{

	 @Autowired
	 private ActivityAwardListServiceI activityAwardListService;//获奖人
	 @Autowired
	 private UserRedEnvelopeServiceI userRedEnvelopeService;//用户获奖红包
	 @Autowired
	 private UserBonusPointsServiceI userBonusPointsService;//用户积分表
	 @Autowired
	 private UserAccountServiceI userAccountService;//用户账户表
	 @Autowired
	 private UserInterestRateCouponServiceI userInterestRateCouponService;//加息券
	 	 
	/**
	 * 我的礼品
	 * @throws Exception 
	 */
	@RequestMapping("/userAward")
	public ModelAndView userAward(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//System.out.println("进来了....");
		//request.getRequestDispatcher("/WEB-INF/jsp/user/manager/coupon.jsp").forward(request, response);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/manager/activity/coupon");
		return mv;
	}
	
	//获奖信息  站外奖品
	@RequestMapping("/queryAwardInfo")
	public ModelAndView queryAwardInfo(HttpServletRequest request,ActivityAwardList aals){
		
		//处理分页
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
	    int num= 1;
	    int size=9;
	    if(pageNum != null && ! "".equals(pageNum)){
		    num = Integer.parseInt(pageNum);
	    }
	    if(pageSize != null && !"".equals(pageSize)){
		    size= Integer.parseInt(pageSize);
	    }
	    String sortString = "id.desc";
	    Order.formString(sortString);
	   
	    PageHelper.startPage(num,size);
		
		UserBaseAccountInfo userBaseinfo=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		aals.setUsername(setEncrypt(userBaseinfo.getLoginname()));
		List<ActivityAwardList> aalist = activityAwardListService.getAwardInfo(aals);
		for(ActivityAwardList a:aalist){
			a.setUsername(getDecrypt(a.getUsername()));
		}
		PageInfo<ActivityAwardList> pagehelper = new PageInfo<ActivityAwardList>(aalist);
		pagehelper.setFirstPage(1);
		int lastPageNum = 0;
		if(pagehelper.getTotal()% size==0){
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("statusmaps",ActAward_Constant.STATUS_MAP);
		mv.setViewName("user/manager/activity/awardInfo");
		return mv;
		
	}
	
	//地址确认  ui弹窗
	@RequestMapping("/addressSureUi")
	public ModelAndView addressSureUi(HttpServletRequest request,String id){
		
		System.out.println("进来了。。。");
		//根据id查询出对应的记录
		ActivityAwardList aal = activityAwardListService.getActivityAwardListById(new BigDecimal(id));
		aal.setUsername(getDecrypt(aal.getUsername()));
		ModelAndView mv = new ModelAndView();
		mv.addObject("aal",aal);
		mv.setViewName("user/manager/activity/awardAddress");
		/*mv.setViewName("user/manager/activity/awardInfo");*/
		return mv;
	}
	
	//地址确认
	@RequestMapping("/addressSure")
	public void addressSure(HttpServletResponse response,String id,String remark) throws IOException{
		
		//根据id来更新获奖名单的收货地址（remark）
		ActivityAwardList aal = new ActivityAwardList();
		aal.setId(new BigDecimal(id));
		aal.setRemark(remark);
		aal.setStatus((short)5);//状态由待确认改为已确认
		int row = activityAwardListService.sureAwardRemark(aal);
		
		Map<String,String> map = new HashMap<String,String>();
		if(row>0){
			map.put("result", "success");
 		}else{
 			map.put("result", "fail");
 		}
		
		String jsonStr = JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
		
	}
	
	//活动奖励
	@RequestMapping("/activityAward")
	public ModelAndView activityAward(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		UserBaseAccountInfo ubai=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//根据用户的id以及时间段来查询获得的红包
		UserRedEnvelope ure = new UserRedEnvelope();
		ure.setBaseid(ubai.getId());
		ure.setStatus((short)3);//已领取的红包
		ure.setPeriod("最近三个月");//默认最近三个月
		List<UserRedEnvelope> ureList = userRedEnvelopeService.findRedEnvelopeListThreeMonths(ure);
		for(UserRedEnvelope u:ureList){
			System.out.println("u: "+u.getId()+u.getRetype());
		}
		
		PageInfo<Object> pagehelper = initPagehelper(map,ureList);
		
		//查询用户账号的红包记录
		UserAccount ua = userAccountService.getUserAccountByBaseId(ubai.getId());
		//用户红包
		Double redenvelope = ua.getRedenvelope();
		String[] re = new DecimalFormat("#0.00").format(redenvelope).split("\\.");
		//可用红包
		Double avlredenvelope = ua.getAvlredenvelope();
		String[] ae = new DecimalFormat("#0.00").format(avlredenvelope).split("\\.");
		//冻结红包
		Double freezeredenvelope =  ua.getFreezeredenvelope();
		String[] fe = new DecimalFormat("#0.00").format(freezeredenvelope).split("\\.");		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("re0",re[0]);
		mv.addObject("re1",re[1]);
		mv.addObject("ae0",ae[0]);
		mv.addObject("ae1",ae[1]);
		mv.addObject("fe0",fe[0]);
		mv.addObject("fe1",fe[1]);
		mv.addObject("period","最近三个月");
		mv.addObject("ua",ua);//用户红包记录
		mv.setViewName("user/manager/activity/morecode");
		return mv;
	}
	
	//我的加息券
	@RequestMapping("/queryCoupon")
	public ModelAndView queryCoupon(HttpServletRequest request){
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String isuse = request.getParameter("isuse");//状态
		String cSort = request.getParameter("cSort");//状态
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		UserBaseAccountInfo ubai = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//根据用户的id来查询用户的加息券
		UserInterestRateCoupon uirc = new UserInterestRateCoupon();
		uirc.setBaseid(ubai.getId());
		uirc.setIsuse(Short.valueOf(isuse));
		uirc.setcSort(cSort);//排序方式
		List<UserInterestRateCoupon> uircList =  userInterestRateCouponService.findCouponByBaseId(uirc);
		for(UserInterestRateCoupon u:uircList){
			System.out.println(u);
		}
		
		PageInfo<Object> pagehelper = initPagehelper(map,uircList);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.setViewName("user/manager/activity/raiseCoupon");
		return mv;
		
	}
	
	//根据时间段来查询用户已经领取的红包
	@RequestMapping("/queryByDate")
	public ModelAndView queryByDate(HttpServletRequest request) throws ParseException{
	
		//处理分页
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
	    int num= 1;
	    int size=9;
	    if(pageNum != null && ! "".equals(pageNum)){
		    num = Integer.parseInt(pageNum);
	    }
	    if(pageSize != null && !"".equals(pageSize)){
		    size= Integer.parseInt(pageSize);
	    }
	    String sortString = "id.desc";
	    Order.formString(sortString);
	   
	    PageHelper.startPage(num,size);
		
		//开始时间
		String startdate = request.getParameter("startdate");
		//结束时间
		String enddate = request.getParameter("enddate");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date start = sdf.parse(startdate);
		//Date end = sdf.parse(enddate);
		
		UserBaseAccountInfo ubai=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//根据用户的id以及时间段来查询获得的红包
		UserRedEnvelope ure = new UserRedEnvelope();
		ure.setBaseid(ubai.getId());
		ure.setStatus((short)3);//已领取的红包
		ure.setStartdate(startdate);//开始时间段
		ure.setEnddate(enddate);//结束时间段
		List<UserRedEnvelope> ureList =  userRedEnvelopeService.findUserRedEnvelope(ure);	
		for(UserRedEnvelope u:ureList){
			System.out.println(u.getId()+"---"+u.getRetype());
		}
		
		
		PageInfo<UserRedEnvelope> pagehelper = new PageInfo<UserRedEnvelope>(ureList);
		pagehelper.setFirstPage(1);
		int lastPageNum = 0;
		if(pagehelper.getTotal()% size==0){
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		
		//查询用户账号的红包记录
		UserAccount ua = userAccountService.getUserAccountByBaseId(ubai.getId());
		//用户红包
		Double redenvelope = ua.getRedenvelope();
		String[] re = new DecimalFormat("#0.00").format(redenvelope).split("\\.");
		//可用红包
		Double avlredenvelope = ua.getAvlredenvelope();
		String[] ae = new DecimalFormat("#0.00").format(avlredenvelope).split("\\.");
		//冻结红包
		Double freezeredenvelope =  ua.getFreezeredenvelope();
		String[] fe = new DecimalFormat("#0.00").format(freezeredenvelope).split("\\.");
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("startdate",startdate);
		mv.addObject("enddate",enddate);
		mv.addObject("re0",re[0]);
		mv.addObject("re1",re[1]);
		mv.addObject("ae0",ae[0]);
		mv.addObject("ae1",ae[1]);
		mv.addObject("fe0",fe[0]);
		mv.addObject("fe1",fe[1]);
		/*mv.setViewName("user/manager/activity/userRed");*/
		mv.setViewName("user/manager/activity/morecode");
		return mv;
	}
	
	//最近三个月  最近一周  最近一月  已领取的红包
	@RequestMapping("/periodOfTime")
	public ModelAndView periodOfTime(HttpServletRequest request) throws UnsupportedEncodingException{
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String period = URLDecoder.decode(request.getParameter("period"),"UTF-8");
		
		UserBaseAccountInfo ubai=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//根据用户的id以及时间段来查询获得的红包
		UserRedEnvelope ure = new UserRedEnvelope();
		ure.setBaseid(ubai.getId());
		ure.setStatus((short)3);//已领取的红包
		ure.setPeriod(period);
		
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		List<UserRedEnvelope> ureList = userRedEnvelopeService.findRedEnvelopeListThreeMonths(ure);
		
		for(UserRedEnvelope u:ureList){
			System.out.println("u: "+u.getId() + u.getRetype());
		}
		
		PageInfo<Object> pagehelper = initPagehelper(map,ureList);
		
		//查询用户账号的红包记录
		UserAccount ua = userAccountService.getUserAccountByBaseId(ubai.getId());
		//用户红包
		Double redenvelope = ua.getRedenvelope();
		String[] re = new DecimalFormat("#0.00").format(redenvelope).split("\\.");
		//可用红包
		Double avlredenvelope = ua.getAvlredenvelope();
		String[] ae = new DecimalFormat("#0.00").format(avlredenvelope).split("\\.");
		//冻结红包
		Double freezeredenvelope =  ua.getFreezeredenvelope();
		String[] fe = new DecimalFormat("#0.00").format(freezeredenvelope).split("\\.");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("re0",re[0]);
		mv.addObject("re1",re[1]);
		mv.addObject("ae0",ae[0]);
		mv.addObject("ae1",ae[1]);
		mv.addObject("fe0",fe[0]);
		mv.addObject("fe1",fe[1]);
		mv.addObject("period",period);
		/*mv.setViewName("user/manager/activity/userRed");*/
		mv.setViewName("user/manager/activity/morecode");
		return mv;
	}
	
	//用户红包详情
	@RequestMapping("/redDetail")
	public ModelAndView redDetail(BigDecimal id){
		
		System.out.println("id: "+id);
		UserRedEnvelope detail =  userRedEnvelopeService.selectByPrimaryKey(id); // 红包详情信息
		detail.getName().setLoginname(getDecrypt(detail.getName().getLoginname()));
		ModelAndView mv = new ModelAndView();
		mv.addObject("reddetail",detail);
		mv.addObject("redctype", ActAward_Constant.RECTYPE_MAP.get(detail.getRectype())); // 红包获取方式类型
		mv.addObject("redtype", ActAward_Constant.RETYPE_MAP.get(detail.getRetype())); //  // 红包类型
		mv.addObject("redstatus", ActAward_Constant.STATUS_MAP.get(detail.getStatus()));// 红包发放状态
		mv.addObject("redisuse", ActAward_Constant.AWARD_MAP.get(detail.getIsuse())); // 红包状态
		mv.addObject("redisaudit", ActAward_Constant.ISAUDIT_MAP.get(detail.getIsaudit())); // 是否审核 
		mv.setViewName("user/manager/activity/userRedEnvelope_Detail");
		return mv;
	}
	//我的积分
	@RequestMapping("/exchange")
	public ModelAndView exchange(HttpServletRequest request){
		
		//查询用户账户的积分
		UserBaseAccountInfo ubai =(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER); 	
		UserAccount ua =  userAccountService.getUserAccountByBaseId(ubai.getId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("ua",ua);
		mv.setViewName("user/manager/activity/exchange");
		return mv;
		
	}
	
	//最近三个月    积分记录
	@RequestMapping("/integralRecode")
	public ModelAndView integralRecode(HttpServletRequest request){
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		UserBonusPoints ubp = new UserBonusPoints();
		UserBaseAccountInfo ubai = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		ubp.setBaseid(ubai.getId());//用户的id
		ubp.setPeriod("threeMonths");//最近三个月
		//根据用户的id来查询用户的积分
		List<UserBonusPoints> ubpList =  userBonusPointsService.findPointsByBaseid(ubp);
		
		PageInfo<Object> pagehelper = initPagehelper(map,ubpList);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.setViewName("user/manager/activity/integralRecode");
		return mv;
	}
	
	//时间段积分记录
	@RequestMapping("/integralRecodePeriod")
	public ModelAndView integralRecodePeriod(HttpServletRequest request){
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		UserBonusPoints ubp = new UserBonusPoints();
		UserBaseAccountInfo ubai = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		ubp.setBaseid(ubai.getId());
		ubp.setStartdate(startdate);
		ubp.setEnddate(enddate);
		List<UserBonusPoints> ubpList = userBonusPointsService.findPointsByPeriod(ubp);
		
		PageInfo<Object> pagehelper = initPagehelper(map,ubpList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.setViewName("user/manager/activity/integralRecode");
		return mv;
		
	}
	
	//查看用户积分详情
	@RequestMapping("/integralDetail")
	public ModelAndView integralDetail(String id){
		
		//根据用户id查询用户的积分详情
		UserBonusPoints detail = userBonusPointsService.findPointsDetailById(new BigDecimal(id));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pointdetail", detail);
		mv.addObject("bptype", UserBonus_Constant.RECTYPE_MAP.get(detail.getBptype())); // 积分来源MAP
		mv.addObject("status", UserBonus_Constant.STATUS_MAP.get(detail.getStatus())); // 积分状态MAP
		mv.addObject("isaudit", UserBonus_Constant.ISAUDIT_MAP.get(detail.getIsaudit())); // 是否审核MAP
		mv.setViewName("user/manager/activity/userBonusPoints_Detail");
		return mv;
		
	}
	
	//初始化分页相关信息
	public void initPage(Map<String,Object> map, String pageNum, String pageSize){
		Integer num = 1;
		Integer size = 9;
		
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}

		PageHelper.startPage(num, size);
		map.put("pageSize", size);
	}
	
	public  PageInfo<Object> initPagehelper(Map map,List list){
		
		PageInfo<Object> pagehelper = new PageInfo<Object>(list);
		pagehelper.setFirstPage(1);
		Integer lastPageNum =0;
		Integer size = (Integer)map.get("pageSize");
		
		if(pagehelper.getTotal()%size==0){
			lastPageNum = (int)pagehelper.getTotal()/size;
		}else{
			lastPageNum = (int)pagehelper.getTotal()/size + 1 ;
		}
		pagehelper.setLastPage(lastPageNum);
		return pagehelper;
	}
}
