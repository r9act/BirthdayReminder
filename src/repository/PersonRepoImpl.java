package repository;

import model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Person> findByName(String name) {               //как получить ИМЯ+ДР -- можем изъять это из Optional.of(p)

        for (Person p : list) {
            if (p.getName().equals(name)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Person> updateByName(String name, int updateDay, int updateMonth, int updateYear) {

        LocalDate newDate = LocalDate.of(updateYear, updateMonth, updateDay);

        for (Person p : list) {
            if (p.getName().equals(name)) {
                p.setBirthDate(newDate);
                return Optional.of(p);
            }
        }
        return Optional.empty();
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
    public Optional<String> removeByName(String name) {         //вернул вместо Person, имя, потому что из-за лямбды переменная p не в скопе
        if (list.removeIf(p -> p.getName().equals(name))) {
            return Optional.of(name);
            //System.out.println("Person " + name + " was removed!");
        }
        return Optional.empty();
    }

    @Override
    public String removeAll() {
        list.clear();
        return "Repo was cleared successfully!";
    }
}

