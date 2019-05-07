package com.ptpl.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;

/**
 * 用户领取红包之后判断类现金，假现金券的失效时间(未到期  可使用)
 */
public class UserRedJob extends QuartzJobBean{

	 @Autowired
	 private UserRedEnvelopeServiceI userRedEnvelopeService;//用户获奖红包
	
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		System.out.println("红包券进来了。。。。");
		
		List<UserRedEnvelope> ureList = userRedEnvelopeService.findRedEnvelopeListByStatus();
		long now = System.currentTimeMillis();
		for(UserRedEnvelope u:ureList){
			long date = u.getRefailtime().getTime();
			if(now > date){
				//已过期
				u.setIsuse((short)5);
				userRedEnvelopeService.updateRedEnvById(u);
			}
		}
		
	}

	public UserRedEnvelopeServiceI getUserRedEnvelopeService() {
		return userRedEnvelopeService;
	}

	public void setUserRedEnvelopeService(UserRedEnvelopeServiceI userRedEnvelopeService) {
		this.userRedEnvelopeService = userRedEnvelopeService;
	}

}
