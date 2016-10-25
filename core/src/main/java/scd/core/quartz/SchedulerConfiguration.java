package scd.core.quartz;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfiguration {

    private static final String QUARTZ_PROPERTIES = "quartz.properties";
    public static final String  CONTEXT_KEY       = "applicationContext";

    @Value("${quartz.group}")
    private String              group             = "";

    @Autowired
    private ApplicationContext  appContext;



    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }



    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // factory.setApplicationContext(appContext);
        factory.setApplicationContextSchedulerContextKey(CONTEXT_KEY);
        factory.setConfigLocation(new ClassPathResource(QUARTZ_PROPERTIES));
        factory.setWaitForJobsToCompleteOnShutdown(true);

        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory);
        factory.setSchedulerName(group);
        // factory.afterPropertiesSet();

        return factory;
    }

}