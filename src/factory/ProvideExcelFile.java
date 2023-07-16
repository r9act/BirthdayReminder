package factory;

import model.Person;
import service.PersonServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProvideExcelFile {

    public static List<Person> getPersonList() throws IOException {
        Scanner sc = new Scanner(System.in);
//        String filePath = sc.nextLine();
        String filePath = "baza.xlsx";
        return PersonServiceImpl.readFromExcel(filePath);
    }
}
