package job;

import factory.OncomingBirthdaysFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import service.OncomingBirthdays;

public class BirthdayJob implements Job {

    OncomingBirthdays oncomingBirthdays = OncomingBirthdaysFactory.getInstance();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        oncomingBirthdays.showPersonWithInterval(30);
    }
}
