package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import service.PersonService;
import service.PersonServiceImpl;

import java.sql.SQLException;

public class BirthdayJob implements Job {

    PersonService personService = new PersonServiceImpl();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            personService.showPersonWithInterval(30);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
