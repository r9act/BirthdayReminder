import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        PersonRepoImpl repo = new PersonRepoImpl();
        //addFromFile
        repo.addFromFile();
        //showAll
        System.out.println(repo.showAll());
        //findByName
        System.out.println(repo.findByName("Антон Юсов"));
        //updateByName
        repo.updateByName("Антон Юсов", "55/55");
        System.out.println(repo.showAll());
        //removeByName
        repo.removeByName("Андрей Селивоненко");
        System.out.println(repo.showAll());
        //showOncoming
        System.out.println(repo.showOncoming());
        //removeAll
//        repo.removeAll();
//        System.out.println(repo.showAll());


//        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM");
//
//        LocalDate localDate = LocalDate.now();
//        Date date1 = dateFormat1.parse(String.valueOf(localDate));
//        System.out.println(dateFormat2.format(date1));
//
//        String line = "21/6";
//        Date date2 = dateFormat2.parse(line);
//        System.out.println(dateFormat2.format(date2));
//
//        System.out.println(date1.compareTo(date2));
    }
}

//    LocalDate date = LocalDate.now();
//    int day = date.getDayOfMonth();
//    int month = date.getMonthValue();
//        System.out.println(day);
//                System.out.println(month);

