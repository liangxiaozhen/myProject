package com.ptpl.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.ThirdRepayMentDealI;

/**
 *  
* @ClassName: RepayMentDealFileJob 
* @Description: TODO(处理到期还款结果文件) 
* @author cjm 
* @date 2017年6月28日 上午9:50:19 
*
 */
public class RepayMentDealFileJob extends QuartzJobBean {

	@Autowired
	@Qualifier("thirdRepayMentDealImpl")
	private ThirdRepayMentDealI thirdRepayMentDealImpl;
	
	@Autowired
	@Qualifier("bacthFileRecordService")
	private BacthFileRecordServiceI bacthFileRecordService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setFileType((short)4);//业务文件类型  1.开户   2.红包转账  3融资扣款   4 到期还款 ....
		bacthFileRecord.setIsSend((short)1);//是否已发送到银行     0.否   1.是
		bacthFileRecord.setSendResult((short)1);//发送结果（是否成功）   0.失败    1.成功
		bacthFileRecord.setIsDealResult((short)0);//是否已处理结果文件     0.否   1.是
		bacthFileRecord.setDealResult((short)0);//处理结果（是否成功）0.否  1.是
		List<BacthFileRecord> bacthFileRecords = bacthFileRecordService.getAllBacthFileRecord(bacthFileRecord);
		if(bacthFileRecords.size() > 0){
			for(BacthFileRecord bacthFileRecord2 : bacthFileRecords ){
				thirdRepayMentDealImpl.repayMentFileDeal(bacthFileRecord2);//处理文件
			}
		}
 	}

	public ThirdRepayMentDealI getThirdRepayMentDealImpl() {
		return thirdRepayMentDealImpl;
	}

	public void setThirdRepayMentDealImpl(ThirdRepayMentDealI thirdRepayMentDealImpl) {
		this.thirdRepayMentDealImpl = thirdRepayMentDealImpl;
	}

	public BacthFileRecordServiceI getBacthFileRecordService() {
		return bacthFileRecordService;
	}

	public void setBacthFileRecordService(BacthFileRecordServiceI bacthFileRecordService) {
		this.bacthFileRecordService = bacthFileRecordService;
	}
 }
