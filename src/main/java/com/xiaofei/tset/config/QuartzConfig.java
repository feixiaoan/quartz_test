package com.xiaofei.tset.config;

import com.xiaofei.tset.controller.HelloJob;
import com.xiaofei.tset.controller.MyTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(HelloJob.class).withIdentity("HellJob").storeDurably().build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {

        //设定执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");

        //返回任务触发器

        return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
                .withIdentity("HelloJob")
                .withSchedule(scheduleBuilder)
                .build();

    }
}
