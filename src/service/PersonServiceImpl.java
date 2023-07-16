package service;

import factory.RepoFactory;
import model.Person;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.PersonRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {

    PersonRepo personRepo = RepoFactory.getInstance();

    @Override
    public List<Person> showAll() throws SQLException {
        if (!personRepo.getAll().isEmpty()) {
            return personRepo.getAll();
        }
        return null;                //хочу вернуть сообщение
    }

    @Override
    public boolean findPersonByname(String name) throws SQLException {
        Optional<Person> personExist = personRepo.findByName(name);
        if (personExist.isPresent()) {
            personRepo.findByName(name);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePersonByname(String name, int updateDay, int updateMonth, int updateYear) throws SQLException {
        Optional<Person> personExist = personRepo.findByName(name);
        if (personExist.isPresent()) {
            personRepo.updateByName(name, updateDay, updateMonth, updateYear);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePersonByName(String name) throws SQLException {
        Optional<Person> personExist = personRepo.findByName(name);
        if (personExist.isPresent()) {
            personRepo.removeByName(name);
            return true;
        }
        return false;
    }

    public List<Person> showPersonWithInterval(int daysBefore) throws SQLException {

        List<Person> oncomingList = new ArrayList<>();
        List<Person> list = personRepo.getAll();

        for (Person p : list) {
            int personBdayDate = p.getBirthDate().getDayOfYear();
            int todaysDate = LocalDate.now().getDayOfYear();
            int periodBetween = personBdayDate - todaysDate;
            if (periodBetween >= 0 && periodBetween <= daysBefore) {
                oncomingList.add(p);
            }
        }
        if (!oncomingList.isEmpty()) {
            return oncomingList;
        } else return null;
    }

    public static List<Person> readFromExcel(String filePath) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(filePath);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int numberOfRows = sheet.getLastRowNum();

        List<Person> tempList = new ArrayList<>();

            for (int i = 0; i <= numberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);

                    String name = row.getCell(0).getStringCellValue();
                    LocalDate birthDate = row.getCell(2).getLocalDateTimeCellValue().toLocalDate();
                    tempList.add(new Person(birthDate, name));
            }
        return tempList;
    }
}
