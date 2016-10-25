package scd.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import scd.core.quartz.JobRegistryService;
import scd.core.quartz.SchedulerConfiguration;

@Configuration
@ComponentScan
@PropertySource({ "classpath:config/core-application.properties" })
@Import({ SchedulerConfiguration.class })
public class CoreApplicationConfig {

    @Autowired
    private JobRegistryService jobService;

    // @PostConstruct
    // public void initial() {
    // ScheduleJob job = new ScheduleJob();
    // job.setId("EchoService");
    // job.setGroup("Core");
    // job.setName("Echo Service");
    // job.setObjectName("echoService");
    // job.setSchedule("0/30 * * * * ?");
    // jobService.registry(job);
    // }
}