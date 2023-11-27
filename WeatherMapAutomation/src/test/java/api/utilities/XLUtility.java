package api.utilities;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path)
    {
        this.path=path;
    }
    public int getRowCount(String sheetName) throws IOException
    {
        fi= new FileInputStream(path);
        workbook= new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException
    {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        row=sheet.getRow(rownum);
        cell = row.getCell(colnum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data=formatter.formatCellValue(cell); // returns formatted value cell as a string
        }catch (Exception e){
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }
}