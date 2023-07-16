package service;

import model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {
    List<Person> showAll() throws SQLException;
    boolean findPersonByname(String name) throws SQLException;
    boolean updatePersonByname(String name, int updateDay, int updateMonth, int updateYear) throws SQLException;
    boolean removePersonByName(String name) throws SQLException;
    List<Person> showPersonWithInterval(int daysBefore) throws SQLException;
    //List<Person> readFromExcel(String directory) throws ParseException, IOException;
}
