package com.ptpl.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.service.RepayMentBaseDealI;
 
public class RepayMentBaseDealJob extends QuartzJobBean{

	@Autowired
	@Qualifier("repayMentBaseDealImpl")
	private RepayMentBaseDealI repayMentBaseDealImpl;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		repayMentBaseDealImpl.CheckRepayMentOverdue();
	}
 
	public void setRepayMentBaseDealImpl(RepayMentBaseDealI repayMentBaseDealImpl) {
		this.repayMentBaseDealImpl = repayMentBaseDealImpl;
	}
}
