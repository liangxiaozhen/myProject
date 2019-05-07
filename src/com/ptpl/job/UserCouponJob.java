package com.ptpl.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.service.UserInterestRateCouponServiceI;

/**
 * 用户领取使用券之后判断券的失效时间(未到期  可使用)
 */
public class UserCouponJob extends QuartzJobBean{

	@Autowired
	@Qualifier("userInterestRateCouponService")
	private UserInterestRateCouponServiceI userInterestRateCouponService;//加息券
	

	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		System.out.println("进来了，使用券定时器。。。。");
		
		List<UserInterestRateCoupon> uircList = userInterestRateCouponService.findUserInterestRateCoupon();
		/*for(UserInterestRateCoupon u:uircList){
			System.out.println("uuuu: "+u);
		}*/
		
		long nowDate = System.currentTimeMillis();
		for(UserInterestRateCoupon u:uircList){
			long date =u.getIcfailtime().getTime();
			if(nowDate > date){
				//说明使用券已经到期
				u.setIsuse((short)5);//已到期
				userInterestRateCouponService.updataCouponById(u);
			}
		}
		
	}
	
	public UserInterestRateCouponServiceI getUserInterestRateCouponService() {
		return userInterestRateCouponService;
	}

	public void setUserInterestRateCouponService(UserInterestRateCouponServiceI userInterestRateCouponService) {
		this.userInterestRateCouponService = userInterestRateCouponService;
	}

}
