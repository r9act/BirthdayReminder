import factory.RepoFactory;
import factory.ServiceFactory;
import job.BirthdayJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import repository.PersonRepo;
import service.PersonService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, SchedulerException, SQLException, ClassNotFoundException {


        PersonRepo personRepo = RepoFactory.getInstance();
//        System.out.println(personRepo.getAll());
        personRepo.addAll();
//        System.out.println(personRepo.updateByName("Антон Юсов", 2,2,2222));
//        System.out.println(personRepo.getAll());
//        System.out.println(personRepo.removeByName("Антон Юсов"));

//        System.out.println(personRepo.addOne("Хуеплет", 1, 1, 2000));
//        personRepo.removeAll();
        PersonService personService = ServiceFactory.getInstance();

       //System.out.println(personService.findPersonByname("Антон Юсов"));

//        System.out.println(service.findPersonByname("Антон Юсовd"));
//        System.out.println(service.updatePersonByname("Антон Юсовd", 11,11,1111));
//        System.out.println(personService.removePersonByName("Антон Юсовd"));
//        System.out.println(personRepo.getAll());


        JobDetail job = JobBuilder.newJob(BirthdayJob.class)
                .withIdentity("dummyJobName", "group1")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(10).repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}



