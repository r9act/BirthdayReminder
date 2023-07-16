package repository;

import model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersonRepo {
    List<Person> getAll() throws SQLException;

    Optional<Person> findByName(String name) throws SQLException;

    Optional<Person> updateByName(String name, int updateDay, int updateMonth, int updateYear) throws SQLException;

    Optional<Person> removeByName(String name) throws SQLException;

    void removeAll() throws SQLException;

    void addAll() throws SQLException, ClassNotFoundException;

    Optional<Person> addOne(String name, int updateDay, int updateMonth, int updateYear) throws SQLException;

}
