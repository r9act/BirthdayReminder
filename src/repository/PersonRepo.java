package repository;

import model.Person;

import java.util.List;

public interface PersonRepo {
    List<Person> getAll();
    Person findByName(String name);
    void updateByName(String name, int updateDay, int updateMonth, int updateYear);
    void removeByName(String name);
    void removeAll();
}
