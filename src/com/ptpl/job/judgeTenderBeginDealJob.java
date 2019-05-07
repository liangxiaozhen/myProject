package com.ptpl.job;

import com.ptpl.service.TenderItemServiceI;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 定时判断标是否到了投标期
 * @author :liuqh
 * @date 2017/6/28 9:36
 */
public class judgeTenderBeginDealJob extends QuartzJobBean {
    @Autowired
    @Qualifier("tenderItemService")
    private TenderItemServiceI tenderItemService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("*******************************************************************");
        Date date = new Date();
        System.out.println("当前时间为："+date+"*******************定时判断标是否到了投标期*****************************************");
        tenderItemService.updateStatusAndOnAndDownTo();
    }

    public TenderItemServiceI getTenderItemService() {
        return tenderItemService;
    }

    public void setTenderItemService(TenderItemServiceI tenderItemService) {
        this.tenderItemService = tenderItemService;
    }
}
