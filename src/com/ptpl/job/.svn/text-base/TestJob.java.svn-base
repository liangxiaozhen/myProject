package com.ptpl.job;

import java.math.BigDecimal;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.service.UserAccountSafeInfoServiceI;

public class TestJob extends QuartzJobBean {

	@Autowired
	@Qualifier("userAccountSafeInfoService")
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.getUserAccountSafeInfoById(BigDecimal.valueOf(263));
		
		System.out.println(userAccountSafeInfo.getBaseid());
		
	}

	public UserAccountSafeInfoServiceI getUserAccountSafeInfoService() {
		return userAccountSafeInfoService;
	}

	public void setUserAccountSafeInfoService(UserAccountSafeInfoServiceI userAccountSafeInfoService) {
		this.userAccountSafeInfoService = userAccountSafeInfoService;
	}
	
	

}
