package com.ptpl.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.BacthFileRecord_Constant;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.TradeFileDetailServiceI;

/**
 * 交易明细流水文件 下载 定时器
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年5月12日 下午2:46:37
 *
 */
public class TradeFileDetailFileDownJob extends QuartzJobBean {

	@Autowired
	private BacthFileRecordServiceI bacthFileRecordService;
	@Autowired
	private TradeFileDetailServiceI tradeFileDetailService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("交易流水明细文件定时器开启：======");
		String fileNameStart=HSignUtil.FILEBANKCODE + "EVE" + HSignUtil.PRODUCT + "-";
		BacthFileRecord bacthFileRecord = new BacthFileRecord();
		bacthFileRecord.setGetFileName(fileNameStart+"20160510");
		bacthFileRecord.setFileType(BacthFileRecord_Constant.EVE);
		// 查询是否有重复添加
		List<BacthFileRecord> list = bacthFileRecordService.getBacthFileRecords(bacthFileRecord);
		if (list.size() == 0) {
			bacthFileRecord.setFilePath("D:\\bacthfile\\800114\\EVE\\20160510");
			bacthFileRecord.setIsDealResult((short) 0);
			bacthFileRecord.setPName("干将P2P金融");
			bacthFileRecord.setCoinstCode(HSignUtil.COINSTCODE);
			bacthFileRecord.setSendFileName(fileNameStart+"20160510");
			bacthFileRecord.setSendResult((short) 1);
			bacthFileRecord.setIsSend((short) 1);
			bacthFileRecordService.insert(bacthFileRecord);
		} else {
			bacthFileRecord = list.get(0);
		}
		boolean flag = tradeFileDetailService.tradeFileDetailDeal(bacthFileRecord);
		if (flag) {
			bacthFileRecord.setIsDealResult((short) 1);
			bacthFileRecord.setDealTime(new Date());
			bacthFileRecord.setDealResult((short) 1);
		} else {
			bacthFileRecord.setIsDealResult((short) 1);
			bacthFileRecord.setDealTime(new Date());
			bacthFileRecord.setDealResult((short) 0);
		}
		bacthFileRecordService.update(bacthFileRecord);
	}

	public void setBacthFileRecordService(BacthFileRecordServiceI bacthFileRecordService) {
		this.bacthFileRecordService = bacthFileRecordService;
	}

	public void setTradeFileDetailService(TradeFileDetailServiceI tradeFileDetailService) {
		this.tradeFileDetailService = tradeFileDetailService;
	}

}
