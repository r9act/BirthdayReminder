import java.util.List;

public interface PersonRepo {
    List<Person> showAll();
    List<Person> showOncoming();
    Person findByName(String name);
    List<Person> updateByName(String name, String newDate);   //почему предлагает сделать void
    List<Person> removeByName(String name);                     //почему предлагает сделать void
    List<Person> removeAll();

}
