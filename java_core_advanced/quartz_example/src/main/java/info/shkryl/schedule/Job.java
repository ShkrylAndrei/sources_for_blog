package info.shkryl.schedule;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;

public class Job {
    public static JobDetail create() {
        return JobBuilder.newJob(GetDataJob.class)
                .withIdentity("myJob", "FirstGroup").build();
    }
}
