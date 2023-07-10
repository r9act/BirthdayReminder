import factory.RepoFactory;
import job.BirthdayJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import repository.PersonRepo;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, SchedulerException {

        PersonRepo repo = RepoFactory.getInstance();
        System.out.println(repo.findByName("Антон Юсов"));
        repo.updateByName("Антон Юсоа", 11, 11, 1111);
        System.out.println(repo.getAll());

        JobDetail job = JobBuilder.newJob(BirthdayJob.class)
                .withIdentity("dummyJobName", "group1")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInHours(24).repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}



