package com.java.uploadfile.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private static int totalRows = 0;
    private static int totalCells = 0;
    private static String errorInfo;

    public ExcelUtil() {
    }

    public static int getTotalRows() {
        return totalRows;
    }

    public static int getTotalCells() {
        return totalCells;
    }

    public static String getErrorInfo() {
        return errorInfo;
    }

    public List<List<String>> read(InputStream inputStream, boolean isExcel2003)
            throws IOException {
        List<List<String>> dataLst = null;
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(inputStream);
        } else {
            wb = new XSSFWorkbook(inputStream);
        }
        dataLst = readDate(wb);
        return dataLst;
    }

    private List<List<String>> readDate(Workbook wb)
    {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        Sheet sheet = wb.getSheetAt(0);
        totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows >= 1 && sheet.getRow(0) != null)
        {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }

        for (int r = 0; r < totalRows; r++)
        {
            Row row = sheet.getRow(r);
            if (row == null)
            {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            for (int c = 0; c < getTotalCells(); c++)
            {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell)
                {
                    switch (cell.getCellType())
                    {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cellValue = cell.getNumericCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            cellValue = cell.getCellFormula() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            cellValue = "invalid";
                            break;
                        default:
                            cellValue = "N/A";
                            break;
                    }
                }
                rowLst.add(cellValue);
            }
            dataLst.add(rowLst);
        }
        return dataLst;
    }

}
