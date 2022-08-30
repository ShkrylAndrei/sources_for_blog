package info.shkryl;

import info.shkryl.job.DateTimeJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        JobDetail job = new JobDetail();
        job.setName("JobDateTime");
        job.setJobClass(DateTimeJob.class);

        CronTrigger trigger = new CronTrigger();
        trigger.setName("DateTimeTrigger");
        try {
            trigger.setCronExpression("0/10 * * * * ?");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
