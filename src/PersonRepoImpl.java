import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonRepoImpl implements PersonRepo {
                                                                    //переделать на Map
    private static List<Person> list = new ArrayList<>();            //почему нельзя сделать final как в NotesRepoImpl

    @Override
    public List<Person> addFromFile() {                                 //лишний метод?
        try {
            PersonRepoImpl.list = Parser.readFromExcel("baza.xlsx");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return PersonRepoImpl.list;
    }

    @Override
    public List<Person> showAll() {
        return new ArrayList<>(list);
    }

    @Override
    public List<Person> showOncoming() {
        List<Person> oncomingList = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        int currentDay = localDate.getDayOfMonth();
        int currentMonth = localDate.getMonthValue();

        for (Person p : list) {
            String line = p.getDate();

            String[] numbers = line.split("/");
            int day = Integer.parseInt(numbers[0]);
            int month = Integer.parseInt(numbers[1]);

            switch (month - currentMonth) {
                case 0:
                    if (day - currentDay >= 0 && day - currentDay <= 3) {
                        oncomingList.add(p);
                    }
                    break;
                case 1:
                    if (currentDay >= 27 && day <= 3) oncomingList.add(p);
                    break;
            }
        }
        if (oncomingList.isEmpty()) System.out.println("No oncoming BDays!");
        return new ArrayList<>(oncomingList);
    }

    @Override
    public Person findByName(String name) {
        for (Person p : list) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new RuntimeException("Person " + name + " not found!");
    }

    @Override
    public List<Person> updateByName(String name, String newDate) {
        for (Person p : list) {
            if (p.getName().equals(name)) {
                p.setDate(newDate);
                return new ArrayList<>(list);
            }
        }
        throw new RuntimeException("Person " + name + " not found!");
    }

    @Override
    public List<Person> removeByName(String name) {
        for (Person p : list) {
            if (p.getName().equals(name)) {
                list.remove(p);
                return new ArrayList<>(list);
            }
        }
        throw new RuntimeException("Person " + name + " not found!");
    }

    @Override
    public List<Person> removeAll() {
        list.clear();
        return new ArrayList<>(list);
    }

}
