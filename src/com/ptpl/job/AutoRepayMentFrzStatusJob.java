package com.ptpl.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.model.TenderItem;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.ThirdRepayMentDealI;
/**
 * 定时处理批次还款状态为【取消】/【冻结成功】任务
 * @author admin
 *
 */
public class AutoRepayMentFrzStatusJob extends QuartzJobBean{

	@Autowired
	@Qualifier("repayMentBaseDealImpl")
	private RepayMentBaseDealI repayMentBaseDealImpl;
	
	@Autowired
	@Qualifier("tenderItemService")
	private TenderItemServiceI tenderItemService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		TenderItem tenderItem = new TenderItem();
 		tenderItem.setTstatus((short)7);//还款中
 		List<TenderItem> tenderItems = tenderItemService.selectByCondition(tenderItem);
 		if(tenderItems.size() > 0){
 			for(TenderItem tenderItem2 : tenderItems){
  				repayMentBaseDealImpl.repayMentFrzStatusDeal(tenderItem2);
 			}
 		}
	}
 

	public void setRepayMentBaseDealImpl(RepayMentBaseDealI repayMentBaseDealImpl) {
		this.repayMentBaseDealImpl = repayMentBaseDealImpl;
	}


	public void setTenderItemService(TenderItemServiceI tenderItemService) {
		this.tenderItemService = tenderItemService;
	}
 
	  

	 
 }
