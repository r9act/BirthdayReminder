package service;

import factory.RepoFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import repository.PersonRepo;

public class SimpleJob implements Job {

    PersonRepo repo = RepoFactory.getInstance();
    OncomingBirthdays oncomingBirthdays = new OncomingBirthdays();


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        oncomingBirthdays.showPersonWithInterval(20);
    }
}
