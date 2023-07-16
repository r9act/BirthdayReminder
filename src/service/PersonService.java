package service;

import model.Person;

import java.util.List;

public interface PersonService {
    List<Person> showAll();
    boolean findPersonByname(String name);
    boolean updatePersonByname(String name, int updateDay, int updateMonth, int updateYear);
    boolean removePersonByName(String name);
    List<Person> showPersonWithInterval(int daysBefore);
    //List<Person> readFromExcel(String directory) throws ParseException, IOException;
}
