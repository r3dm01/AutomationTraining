package Utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {

    XSSFWorkbook ExcelWBook;
    XSSFSheet ExcelWSheet;

    public ExcelUtils(String filepath){

        try{

            File srcFile = new File(filepath);
            FileInputStream fis = new FileInputStream(srcFile);
            ExcelWBook = new XSSFWorkbook(fis);
        }catch (Exception exc){
                exc.printStackTrace();
        }
    }

    public String getData(int SheetNo, int row, int column){

        ExcelWSheet = ExcelWBook.getSheetAt(SheetNo);
        return ExcelWSheet.getRow(row).getCell(column).getStringCellValue();
    }

    public int getRowCount(int sheetIndex){
       int row = ExcelWBook.getSheetAt(sheetIndex).getLastRowNum();
       row = row+1;
       return row;

    }

}
