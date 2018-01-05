package com.cn.service.quartz;

import com.cn.controller.quartz.JobAController;
import com.cn.controller.quartz.JobBController;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by 钟锐锋 on 2018/1/4.
 */
public class TestJobService {
    public static void main(String[] args) throws SchedulerException {
        //获取工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //调度器
        Scheduler scheduler = schedulerFactory.getScheduler();

        //定时任务A
        JobKey jobkeyA = new JobKey("JobA", "groupA");
        JobDetail jobDetailA = JobBuilder.newJob(JobAController.class)
                .withIdentity(jobkeyA)
                .build();

        //定时任务B
        JobKey jobKeyB = new JobKey("JobB", "groupB");
        JobDetail jobDetailB = JobBuilder.newJob(JobBController.class)
                .withIdentity(jobKeyB)
                .build();

        //定义触发的条件
        //每个5秒触发一次
        //CronTrigger – 允许UNIX cron表达式来指定日期和时间来运行作业
        Trigger triggerA = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerNameA", "groupA")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        //每个1秒触发一次
        //SimpleTrigger – 允许设置开始时间，结束时间，重复间隔
        Trigger triggerB = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerNameB", "groupB")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1).repeatForever())
                .build();

        //4. 启动定时任务
        scheduler.start();
        scheduler.scheduleJob(jobDetailA,triggerA);
        scheduler.scheduleJob(jobDetailB,triggerB);
    }
}
