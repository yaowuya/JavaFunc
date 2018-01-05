package com.cn.controller.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 钟锐锋 on 2018/1/4.
 */
public class JobBController implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始执行定时任务B......");
    }
}
