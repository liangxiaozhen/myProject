package com.ptpl.job;

import com.ptpl.service.TenderItemServiceI;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 定时判断流标
 * @author :liuqh
 * @date 2017/6/28 9:36
 */
public class judgeTenderFailDealJob extends QuartzJobBean {
    @Autowired
    @Qualifier("tenderItemService")
    private TenderItemServiceI tenderItemService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("*******************************************************************");
        Date date = new Date();
        System.out.println("当前时间为："+date+"*******************定时自动流标*****************************************");
        tenderItemService.updateStatusAndOnAndDownTo3();
    }

    public TenderItemServiceI getTenderItemService() {
        return tenderItemService;
    }

    public void setTenderItemService(TenderItemServiceI tenderItemService) {
        this.tenderItemService = tenderItemService;
    }
}
