package com.xiaofei.tset.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaofei.tset.entity.QuartzBean;
import com.xiaofei.tset.service.IQuartzService;
import com.xiaofei.tset.utils.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaofei
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    /**
     * 注入调度任务
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private IQuartzService quartzService;


    @RequestMapping("page")
    public IPage<QuartzBean> PageList(Page page, QuartzBean quartzBean) {
        IPage<QuartzBean> list = quartzService.list(page, quartzBean);
        return list;
    }



    /**
     * 创建定时任务
     * @param quartzBean
     * @return
     */
    @PostMapping("/create")
    public String createJob(QuartzBean quartzBean) {
        System.out.println(quartzBean);

        boolean save = quartzService.save(quartzBean);
        if (save == false) {
            return "插入失败";
        }
        try{
            if (quartzBean.getStatus() == 0) {
                return "插入成功";
            }
            QuartzUtils.createScheduleJob(scheduler,quartzBean);
            return  "创建并执行成功";
        }catch (Exception e) {
            return "创建失败";
        }
    }

    /**
     * 暂停定时任务
     * @return
     */
    @RequestMapping("/pause")
    public String pauseJob() {

        try{
            QuartzUtils.pauseScheduleJob(scheduler,"test1");
        }catch (Exception e) {
            return "暂停失败";
        }

        return "暂停成功";
    }

    /**
     * 运行一次
     * @return
     */
    @RequestMapping("runOnce")
    public String runOnce(){

        try{
            QuartzUtils.runOnce(scheduler,"test1");
        }catch (Exception e) {
            return "运行一次失败";
        }

        return "运行一次成功";
    }

    /**
     * 启动定时任务
     * @return
     */
    @RequestMapping("resume")
    public String resume() {

        try{
            QuartzUtils.resumeScheduleJob(scheduler,"test1");
        }catch (Exception e) {
            return "启动失败";
        }

        return "启动成功";
    }

    /**
     * 删除定时任务
     * @return
     */
    @RequestMapping("delete")
    public String deleteJob(Integer id) {

        try{
            QuartzUtils.deleteScheduleJob(scheduler,"test1");

        }catch (Exception e) {
            return "删除失败";
        }

        return "删除成功";
    }

    /**
     * 更新定时任务
     * @param quartzBean 定时任务信息
     * @return
     */
    @RequestMapping("update")
    public String updateJob(QuartzBean quartzBean) {

        try{
            quartzBean.setJobClass("com.xiaofei.tset.controller.MyTask");
            quartzBean.setJobName("test1");
            quartzBean.setCronExpression("*/2 * * * * ?");
            QuartzUtils.updateScheduleJob(scheduler,quartzBean);
        }catch (Exception e) {
            return "更新失败";
        }

        return "更新成功";
    }


}
