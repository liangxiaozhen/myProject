package com.ptpl.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.service.ThirdRepayMentDealI;
/**
 * 定时还款任务
 * @author admin
 *
 */
public class AutoRepayMentJob extends QuartzJobBean{

	@Autowired
	@Qualifier("thirdRepayMentDealImpl")
	private ThirdRepayMentDealI thirdRepayMentDealImpl;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
 		thirdRepayMentDealImpl.timTaskRepayMent();
	}

	public void setThirdRepayMentDealImpl(ThirdRepayMentDealI thirdRepayMentDealImpl) {
		this.thirdRepayMentDealImpl = thirdRepayMentDealImpl;
	}
 }
