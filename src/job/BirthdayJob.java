package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import service.PersonService;
import service.PersonServiceImpl;

public class BirthdayJob implements Job {

    PersonService personService = new PersonServiceImpl();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        personService.showPersonWithInterval(30);
    }
}
