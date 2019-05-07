package com.ptpl.web.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ptpl.constant.RegActAwardRule_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.ActivityList;
import com.ptpl.model.Award;
import com.ptpl.model.RegActAwardRule;
import com.ptpl.model.RegActivityRule;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.ActivityListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RegActAwardRuleServiceI;
import com.ptpl.service.RegActivityRuleServiceI;
import com.ptpl.service.UserBankCardServiceI;

/**
 * @ClassName: CreateActivityAwardList
 * @Description: TODO(注册活动获奖名单生成)
 * @author zhenglm
 * @date 2017年1月12日 下午4:53:20
 */
public class CreateActivityAwardList {

	/** 活动列表Service */
	private static ActivityListServiceI activityListService = SpringContextHolder
			.getBean(ActivityListServiceI.class);

	/** 注册活动规则Service */
	private static RegActivityRuleServiceI regActivityRuleService = SpringContextHolder
			.getBean(RegActivityRuleServiceI.class);

	/** 注册活动奖励规则Service */
	private static RegActAwardRuleServiceI regActAwardRuleService = SpringContextHolder
			.getBean(RegActAwardRuleServiceI.class);

	/** 获奖名单Service */
	private static ActivityAwardListServiceI activityAwardListService = SpringContextHolder
			.getBean(ActivityAwardListServiceI.class);

	/** 奖品Service */
	private static AwardServiceI awardService = SpringContextHolder
			.getBean(AwardServiceI.class);

	/** 用户银行卡Service */
	private static UserBankCardServiceI userBankCardService = SpringContextHolder
			.getBean(UserBankCardServiceI.class);
	
	/**
	 * @Title: CreateRegAwardList
	 * @Description: TODO(检索注册活动生成获奖名单)
	 * @param business
	 * @param request
	 * @param date
	 * @return void
	 */
	public static void CreateRegAwardList(Short business, HttpServletRequest request, Date date){
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		if(user == null)
			return;
		ActivityList al = new ActivityList();
		al.setActtype((short) 1); // 活动类型-1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖
		al.setStatus((short) 2);  // 活动状态-0.待完善，1待执行，2.执行中，3.已完成，4.已停用，5.已作废，6.已过期
		List<ActivityList> activityList = activityListService.getActivityList(al); // 查询执行中的注册活动
		for(ActivityList activity : activityList){
			String actno = activity.getActno(); // 获取注册活动编号
			RegActivityRule regActivityRule = regActivityRuleService.selectByActNo(actno); // 根据活动编号查询注册主活动
			BigDecimal actid = regActivityRule.getId(); // 获取注册活动id
			RegActAwardRule awardRule = new RegActAwardRule();
			awardRule.setActid(actid);  // 获取注册活动id
			awardRule.setBusiness(business); // 业务类型-1.注册完成，2.姓名匹配，3.手机号验证，4.开通托管账户，5.银行卡绑定，6.安保密码设置，7.邮箱验证
			RegActAwardRule regActAwardRule = regActAwardRuleService.selectByActIdAndBusiness(awardRule); // 根据注册活动ID和业务类型查询具体子业务 
			Date regDate = user.getRegdate();   // 用户注册日期
			Date beginDate = regActivityRule.getActbtime();  // 活动开始日期
			Date endDate = regActivityRule.getActetime(); // 活动结束日期
			if(regActAwardRule != null){
				String c = isClient(request);
				String Crestrict = regActAwardRule.getCrestrict();
				if(Crestrict.contains(c)){
					if(date.getTime() >= beginDate.getTime() && date.getTime() <= endDate.getTime()){
						if(business==RegActAwardRule_Constant.BUSINESS_REGISTER){ // 注册完成业务
							generateList(regActAwardRule, user, regActivityRule, date);
						}else if(business==RegActAwardRule_Constant.BUSINESS_BANKCARD){ // 银行卡绑定业务-只有第一次绑定有效
							UserBankCard card = new UserBankCard();
							card.setBaseid(user.getId());
							List<UserBankCard> CardList = userBankCardService.findUserBankInfo(card); // 查询用户绑定银行卡
							if(CardList.size()==1){ // 第一次绑定有效
								if(regActivityRule.getIsconsiderregtime()==1){
									if(regDate.getTime() >= beginDate.getTime() && regDate.getTime() <= endDate.getTime()){ // 判断注册完成时间是否在注册活动时间范围内
										long time = (date.getTime()-regDate.getTime())/(1000*3600); // 距离注册时间多少小时
										Integer finishTime = regActAwardRule.getFinishtime();
										if(Arith.sub(finishTime, time) >= 0){ // 业务完成时间在限定时间内
											generateList(regActAwardRule, user, regActivityRule, date);
										}
									}
								}else{
									generateList(regActAwardRule, user, regActivityRule, date);
								}
							}
						}else{
							if(regActivityRule.getIsconsiderregtime()==1){
								if(regDate.getTime() >= beginDate.getTime() && regDate.getTime() <= endDate.getTime()){ // 判断注册完成时间是否在注册活动时间范围内
									long time = (date.getTime()-regDate.getTime())/(1000*3600); // 距离注册时间多少小时
									Integer finishTime = regActAwardRule.getFinishtime();
									if(Arith.sub(finishTime, time) >= 0){ // 业务完成时间在限定时间内
										generateList(regActAwardRule, user, regActivityRule, date);
									}
								}
							}else{
								generateList(regActAwardRule, user, regActivityRule, date);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @Title: generateList
	 * @Description: TODO(生成获奖名单)
	 * @param regActAwardRule
	 * @param user
	 * @param regActivityRule
	 * @param date
	 * @return void
	 */
	private static void generateList(RegActAwardRule regActAwardRule, UserBaseAccountInfo user, RegActivityRule regActivityRule, Date date){
		String actno = regActivityRule.getActno();
		ActivityAwardList awardList = new ActivityAwardList();
		awardList.setActid(actno); 																// 活动编号
		awardList.setBaseid(user.getId());														// 用户ID
		awardList.setUsername(user.getLoginname());												// 用户名
		Award award = awardService.selectByPrimaryKey(regActAwardRule.getAwardid());			
		awardList.setAwardno(award.getAno());													// 奖品编号
		awardList.setAwardname(award.getAname());												// 奖品名称
		awardList.setAwardattribute(award.getAttribute());										// 奖品属性
		awardList.setAwardmoney(regActAwardRule.getQuota());									// 获奖金额
		awardList.setAwardquantity(regActAwardRule.getAwardcopies());							// 奖品个数
		if(regActivityRule.getIsauditalist()==1){ // 需要审核获奖名单
			awardList.setStatus((short) 1);														// 发放状态（1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败）
		}else if(regActivityRule.getIsauditalist()==2){ // 不需要审核获奖名单
			if(award.getAtype()==1){ // 站内奖品-待处理
				awardList.setStatus((short) 2);													// 发放状态（1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败）
			}else if(award.getAtype()==2 || award.getAtype()==3){ // 站外奖品-待确认
				awardList.setStatus((short) 4);													// 发放状态（1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败）
			}
		}
		awardList.setMadetime(new Date());														// 制作时间（获奖名单生成时间）
		int rows = 0;
		rows = activityAwardListService.insertSelective(awardList);
		if(rows > 0){
			ActivityList al = new ActivityList();
			al.setActno(actno);
			ActivityList activityList = activityListService.getActListByAl(al);
			Map<String, BigDecimal> map = activityAwardListService.selectTimesAndNumber(actno); // 统计获奖名单的获奖人次和人数
			if(activityList.getExecutetime() == null){ // 执行时间为第一个人的获奖时间
				activityList.setExecutetime(date); // 执行时间
			}
			activityList.setAwardtimes(map.get("awardtimes")); // 获奖人次
			activityList.setAwardnumber(map.get("awardnumber")); // 获奖人数
			activityListService.updateByPrimaryKeySelective(activityList); // 更新活动列表的获奖人次和人数
		}
	}
	
	/**
	 * @Title: isClient
	 * @Description: TODO(判断客户端来源)
	 * @param request
	 * @return String
	 */
    public static String isClient(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if(userAgent == null || userAgent.indexOf("windows nt") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "1";
        }
        if(userAgent == null || userAgent.indexOf("android") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "2";
        }
        if(userAgent == null || userAgent.indexOf("iphone") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "3";
        }
        if(userAgent == null || userAgent.indexOf("wap") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "4";
        }
        if(userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "5";
        }
        return "";
    }
}
