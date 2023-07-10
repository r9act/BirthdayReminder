package service;

import factory.RepoFactory;
import model.Person;
import repository.PersonRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OncomingBirthdays {

    private final PersonRepo repo = RepoFactory.getInstance();

    public List<Person> showPersonWithInterval(int daysBefore) {

        List<Person> oncomingList = new ArrayList<>();
        List<Person> list = repo.getAll();

        for (Person p : list) {
            int personBdayDate = p.getBirthDate().getDayOfYear();
            int todaysDate = LocalDate.now().getDayOfYear();

            int periodBetween = personBdayDate - todaysDate;

            if (periodBetween >= 0 && periodBetween <= daysBefore) {
                oncomingList.add(p);

            }

        }
        System.out.println(oncomingList);
        return oncomingList;
//        throw new RuntimeException("No any birthday in " + daysBefore + " days!");
    }
}

