package scd.core.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service
public class EchoService implements Job {

    @Override
    public void execute(JobExecutionContext pContext) throws JobExecutionException {
        System.out.println("Echo from Scheduler");
    }

}
