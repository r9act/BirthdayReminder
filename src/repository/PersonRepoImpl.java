package repository;

import exceptions.PersonNotFoundException;
import model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonRepoImpl implements PersonRepo {

    private final List<Person> list;

    public PersonRepoImpl(List<Person> list) {
        this.list = list;
    }

    @Override
    public List<Person> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public Person findByName(String name) {

        for (Person p : list) {
            if (p.getName().equals(name)) {                 //не совсем понятно как возвращается либо p либо exception
                return p;
            }
        }
        throw new PersonNotFoundException("Person not found!");
    }

    @Override
    public void updateByName(String name, int updateDay, int updateMonth, int updateYear) {

        LocalDate newDate = LocalDate.of(updateYear, updateMonth, updateDay);

        for (Person p : list) {
            if (p.getName().equals(name)) {
                p.setBirthDate(newDate);
                System.out.println("Person " + name + " updated successfully!");
            }
        }
    }
//Через итератор
//    public void removeByName(String name){
//        Iterator<Person> itr = list.iterator();
//        while (itr.hasNext()){
//            Person next = itr.next();
//            if(next.getName().equals(name)) itr.remove();
//        }
//    }

    @Override
    public void removeByName(String name) {
        if (list.removeIf(p -> p.getName().equals(name))) {
            System.out.println("Person " + name + " was removed!");
        } else throw new PersonNotFoundException("Person not found!");

    }

    @Override
    public void removeAll() {
        list.clear();
        System.out.println("Repo was cleared successfully!");
    }
}

