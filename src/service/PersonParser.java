package service;

import model.Person;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonParser {

    public static List<Person> readFromExcel(String directory) throws IOException, ParseException {

        XSSFWorkbook workbook = new XSSFWorkbook(directory);
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

