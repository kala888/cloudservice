package scd.core.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;

import lombok.extern.slf4j.Slf4j;

import org.joda.time.DateTime;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * 
 * sample from https://codeload.github.com/nikos/springboot-quartz-mongodb
 *
 * @author: kalaliu
 * @version: 1.0, Sep 8, 2016
 */
@Service
@ConditionalOnProperty(name = "quartz.enabled")
@Slf4j
public class JobRegistryService {

    @Value("${quartz.group}")
    private String               group;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private ApplicationContext   appContext;



    /**
     * 
     * Registry A job
     *
     * @param job
     * @throws Exception
     */
    public void registry(ScheduleJob job) {
        try {
            JobDetail jobDetail = createJobDetail(job);
            schedulerFactoryBean.getScheduler().addJob(jobDetail, true, true);
            if (!schedulerFactoryBean.getScheduler().checkExists(
                    new TriggerKey("trigger" + job.getId(), job.getGroup()))) {
                schedulerFactoryBean.getScheduler().scheduleJob(createCronTrigger(jobDetail, job));
            }
        } catch (Exception e) {
            log.error("Could registry job:", e);
        }

    }



    /**
     * 
     * create a schduler triger by cron
     *
     * @param jobDetail
     * @param job
     * @return
     * @throws ParseException
     */
    private CronTrigger createCronTrigger(JobDetail jobDetail, ScheduleJob job) throws ParseException {
        return newTrigger().forJob(jobDetail).withIdentity("trigger" + job.getId(), job.getGroup()).withPriority(100)
                .withSchedule(cronSchedule(job.getSchedule())).startAt(DateTime.now().plusSeconds(3).toDate()).build();
    }



    /**
     * 
     * create job detail
     *
     * @param job
     * @return
     * @throws Exception
     */
    private JobDetail createJobDetail(ScheduleJob job) throws Exception {
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setKey(new JobKey(job.getId(), job.getGroup()));
        jobDetail.setJobClass((Class<? extends Job>) appContext.getBean(job.getObjectName()).getClass());
        jobDetail.setDurability(true);
        // JobDataMap map = new JobDataMap();
        // map.put("name", "HaHa");
        // map.put(MyJobTwo.COUNT, 1);
        // jobDetail.setJobDataMap(map);
        return jobDetail;
    }



    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }



    /**
     * @param pGroup
     *            the group to set
     */
    public void setGroup(String pGroup) {
        group = pGroup;
    }

}