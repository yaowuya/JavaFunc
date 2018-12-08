package com.cn.controller.quartz;

import com.cn.common.quartz.result.BaseResult;
import com.cn.common.quartz.result.BootstrapTableResult;
import com.cn.service.quartz.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 定时任务控制层
 * @author admin
 * @date 2017-11-25 18:49
 */

@Controller
@RequestMapping("/scheduleJob")
public class ScheduleJobController {
    public static Logger log = LoggerFactory.getLogger(ScheduleJobController.class);

    @Autowired
    ScheduleJobService scheduleJobService;


    /**
     * 查询所有的定时任务，用于页面加载时显示表格数据
     * @param pageSize 每页显示数量
     * @param pageNumber 页数
     * @return BootstrapTableResult
     */
    @RequestMapping("listAllJob")
    @ResponseBody
    public BootstrapTableResult listAllJob(int pageSize, int pageNumber) {
        BootstrapTableResult bootstrapTableResult = scheduleJobService.listAllJob(pageSize, pageNumber);
        return bootstrapTableResult;
    }

    /**
     * 暂停定时任务
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping("pauseJob")
    @ResponseBody
    public BaseResult pauseJob(int jobId) {
        scheduleJobService.pauseJob(jobId);
        return new BaseResult(1, "success", "定时任务暂停成功");
    }

    /**
     * 恢复定时任务
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping("resumeJob")
    @ResponseBody
    public BaseResult resumeJob(int jobId){
        scheduleJobService.resumeJob(jobId);
        return new BaseResult(1, "success", "定时任务恢复成功");
    }

    /**
     * 立即执行定时任务
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping("runOnce")
    @ResponseBody
    public BaseResult runOnce(int jobId){
        scheduleJobService.runOnce(jobId);
        return new BaseResult(1, "success", "立即执行定时任务成功");
    }

    /**
     * 更新时间表达式
     * @param id
     * @param cronExpression
     * @return BaseResult
     */
    @RequestMapping("updateCron")
    @ResponseBody
    public BaseResult updateCron(int id,String cronExpression){
        scheduleJobService.updateCron(id,cronExpression);
        return new BaseResult(1, "success", "更新时间表达式成功");
    }
}
