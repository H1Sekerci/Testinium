package ExcelFile;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcel {

    public String readData(int index) throws Exception {
        String UserDirectory = System.getProperty("user.dir");
        UserDirectory +="\\src\\main\\resources\\TestiniumExcel.xlsx" ;
        File src = new File(UserDirectory);
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xsf = new XSSFWorkbook(fis);
        XSSFSheet sheet = xsf.getSheetAt(0);
        String data = sheet.getRow(index).getCell(0).getStringCellValue();
        xsf.close();
        return  data;
    }
}
