package info.shkryl.schedule;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;

import java.util.Date;

public class Trigger {
    public static SimpleTrigger create(Date startTime){
     return TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "FirstGroup")
                .startAt(startTime)
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(3)
                                .repeatForever()
                                .withMisfireHandlingInstructionIgnoreMisfires())
                .build();
    }
}
