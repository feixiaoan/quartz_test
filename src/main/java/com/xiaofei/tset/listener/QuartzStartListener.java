package com.xiaofei.tset.listener;

import com.xiaofei.tset.controller.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzStartListener {
    public static void main(String[] args) {

        //通过schedulerFactory获取调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler= null;

        try{
            //通过schedulerFactory获取一个调度器
            scheduler = schedulerFactory.getScheduler();

            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定的job类
            JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("JobName","JobGroupName").build();

            //定义调度规则
            //simpleTrigger
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger","SimpleTriggerGroup")
//                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3).withRepeatCount(6))
//                    .startNow().build();

            //cron表达式 每五秒执行一次
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger1","CronTriggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                    .startNow().build();

            //把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job,trigger);

            //启动调度
            scheduler.start();

            Thread.sleep(11000);
            //停止调度
            scheduler.shutdown();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
