package factory;

import model.Person;
import service.PersonParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class PersonListProvider {

    public static List<Person> getPersonList(){
        try {
            return PersonParser.readFromExcel("baza.xlsx");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}