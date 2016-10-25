package scd.core.quartz;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * Listens on quartz Job lifecycle events to save them into a MongoDB history
 * collection, only finished jobs (whether successful or not are saved).
 *
 * @author Niko Schmuck
 * @see org.quartz.plugins.history.LoggingJobHistoryPlugin
 */
public class JobHistoryListener implements SchedulerPlugin, JobListener {

    private String               name;
    private Scheduler            scheduler;
    private JobHistoryRepository repository;



    @Override
    public void initialize(String pname, Scheduler scheduler, ClassLoadHelper classLoadHelper)
            throws SchedulerException {
        this.name = pname;
        this.scheduler = scheduler;
        scheduler.getListenerManager().addJobListener(this, EverythingMatcher.allJobs());
    }



    @Override
    public String getName() {
        return name;
    }



    @Override
    public void start() {
        // retrieve Spring application context to setup
        try {
            System.out.println("Available context keys: {}" + Arrays.asList(scheduler.getContext().getKeys()));
            ApplicationContext ctx = (ApplicationContext) scheduler.getContext()
                    .get(SchedulerConfiguration.CONTEXT_KEY);
            System.out.println("Retrieving mongo client from context: {}"
                    + Arrays.asList(scheduler.getContext().getKeys()));
            repository = ctx.getBean(JobHistoryRepository.class);
        } catch (SchedulerException e) {
            System.out.println("nable to retrieve application context from quartz scheduler" + e);
        }
    }



    @Override
    public void shutdown() {
        // nothing to do...
    }



    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        // nothing to do...
    }



    @Override
    public void jobWasExecuted(final JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("jobWasExecuted :: {}" + context);
        if (StringUtils.isEmpty(jobException)) {
            repository.add(new HashMap<String, Object>() {
                /**
                 * 
                 */
                private static final long serialVersionUID = 1689554145532392956L;

                {
                    put("ts", new Date());
                    put("name", context.getJobDetail().getKey().getName());
                    put("group", context.getJobDetail().getKey().getGroup());
                    put("started", context.getFireTime());
                    put("runtime", context.getJobRunTime());
                    put("refireCount", context.getRefireCount());
                    put("result", String.valueOf(context.getResult()));
                }
            });
            // TODO: have explict field: hasException: true / false ?
        } else {
            repository.add(new HashMap<String, Object>() {
                /**
                 * 
                 */
                private static final long serialVersionUID = -5346455552005854259L;

                {
                    put("ts", new Date());
                    put("name", context.getJobDetail().getKey().getName());
                    put("group", context.getJobDetail().getKey().getGroup());
                    put("started", context.getFireTime());
                    put("runtime", context.getJobRunTime());
                    put("refireCount", context.getRefireCount());
                    put("errMsg", jobException.getMessage());
                    put("jobException", jobException.getMessage());
                }
            });
        }
    }



    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed :: {}" + context);
        repository.add(new HashMap<String, Object>() {
            /**
             * 
             */
            private static final long serialVersionUID = 5334420855817727036L;

            {
                put("ts", new Date());
                put("name", context.getJobDetail().getKey().getName());
                put("group", context.getJobDetail().getKey().getGroup());
                put("started", context.getFireTime());
                put("runtime", context.getJobRunTime());
                put("refireCount", context.getRefireCount());
                put("veto", true);
            }
        });
    }

}
