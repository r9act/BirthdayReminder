import java.util.List;

public interface PersonRepo {
    List<Person> addFromFile();         //почему предлагает сделать void
    List<Person> showAll();
    List<Person> showOncoming();
    Person findByName(String name);
    List<Person> updateByName(String name, String newDate);   //почему предлагает сделать void
    List<Person> removeByName(String name);                     //почему предлагает сделать void
    List<Person> removeAll();

}
