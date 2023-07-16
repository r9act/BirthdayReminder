package repository;

import model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepo {
    List<Person> getAll() ;
    Optional<Person> findByName(String name);
    Optional<Person> updateByName(String name, int updateDay, int updateMonth, int updateYear);
    Optional<String> removeByName(String name);
    String removeAll();
}
