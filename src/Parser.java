import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {


    public static List<Person> readFromExcel(String file) throws IOException, ParseException {

//        FileInputStream file = new FileInputStream(new File("baza.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int numberOfRows = sheet.getLastRowNum();

        List<Person> tempList  = new ArrayList<>();

        for (int i = 0; i <= numberOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            String date = row.getCell(3).getStringCellValue();

            String[] numbersString = date.split("/");
            int day = Integer.parseInt(numbersString[0]);
            int month = Integer.parseInt(numbersString[1]);
            tempList.add(new Person(day+"/"+month, name));
        }
        return tempList;            //список объектов класса Person (String date, String name)
    }
}

