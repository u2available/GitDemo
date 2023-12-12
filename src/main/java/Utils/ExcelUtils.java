package Utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static FileInputStream fis = null;
    private FileOutputStream fileOut = null;
    private static XSSFRow Row;
    private static XSSFCell Cell;
    private String path;

    public ExcelUtils(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            // ExcelWBook = (XSSFWorkbook) WorkbookFactory.create(fis);// WorkbookFactory

            // class to create the appropriate workbook (i.e.XSSFWorkbook)
            ExcelWBook = new XSSFWorkbook(fis);
            fis.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // find whether sheets exists
    /**
     * @param sheetName
     * @return
     */
    public static boolean isSheetExist(String sheetName) {
        int index = ExcelWBook.getSheetIndex(sheetName);
        if (index == -1) {
            index = ExcelWBook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }

    // returns the row count in a sheet using sheetname
    /**
     * @param sheetName
     * @return
     */

    public int getRowCount(String sheetName) {
        int index = ExcelWBook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            ExcelWSheet = ExcelWBook.getSheetAt(index);
            return ExcelWSheet.getLastRowNum();
            // int number = ExcelWSheet.getPhysicalNumberOfRows();

        }

    }

    // returns number of columns in a sheet
    /**
     * @param sheetName
     * @return
     */
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName))
            return -1;

        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        Row = ExcelWSheet.getRow(0); // get my row which starts from 0

        if (Row == null)
            return -1;

        return Row.getLastCellNum();

    }

    // get the Column number from Sheet using ColName
    /**
     * @param colName
     * @return
     */
    public int getColumnNumberUsingColname(String colName) {
        int colNum = 0;
        for (int i = 0; i < Row.getLastCellNum(); i++) {
            if (ExcelWSheet.getRow(0).getCell(i).getStringCellValue().equals(colName)) {
                colNum = i;
                break;
            }
        }
        return colNum;
    }

    // returns true if column is created successfully
    /**
     * @param sheetName, ColName
     * @return
     */
    public boolean addColumn(String sheetName, String colName) {

        try {
            fis = new FileInputStream(path);
            ExcelWBook = new XSSFWorkbook(fis);
            int index = ExcelWBook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFCellStyle style = ExcelWBook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.YELLOW.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            ExcelWSheet = ExcelWBook.getSheetAt(index);

            Row = ExcelWSheet.getRow(0);
            if (Row == null)
                Row = ExcelWSheet.createRow(0);

            // cell = row.getCell();
            // if (cell == null)
            // System.out.println(row.getLastCellNum());
            if (Row.getLastCellNum() == -1)
                Cell = Row.createCell(0);
            else
                Cell = Row.createCell(Row.getLastCellNum());

            Cell.setCellValue(colName);
            Cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    // returns true if column is removed successfully
    /**
     * @param sheetName, ColNum
     * @return
     */
    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!isSheetExist(sheetName))
                return false;

            for (int i = 0; i < getRowCount(sheetName); i++) {
                Row = ExcelWSheet.getRow(i);
                if (Row != null) {
                    Cell = Row.getCell(colNum);
                    if (Cell != null) {
                        Row.removeCell(Cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    // returns true if sheet is created successfully
    /**
     * @param sheetname
     * @return
     */
    public boolean addSheet(String sheetname) {

        try {
            ExcelWBook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is removed successfully
    /**
     * @param sheetName
     * @return
     */
    public boolean removeSheet(String sheetName) {
        int index = ExcelWBook.getSheetIndex(sheetName);
        if (index == -1)
            return false;
        try {
            ExcelWBook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // return true if column name exists
    /**
     * @param sheet, Colname
     * @return
     */
    public boolean verifycolumnname(String sheet, String colname) {
        boolean colnameexist = false;
        try {
            // check if sheet exists
            if (!isSheetExist(sheet))
                return false;

            ExcelWSheet = ExcelWBook.getSheet(sheet);
            Row = ExcelWSheet.getRow(0); // get my row which starts from 0
            for (int i = 0; i < Row.getLastCellNum(); i++) {
                if (ExcelWSheet.getRow(0).getCell(i).getStringCellValue().equals(colname))
                    return colnameexist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        return colnameexist;
    }

    // get cell data using Row and Column number
    /**
     * @param RowNum, ColNum
     * @return
     */

    public String getCellDataUsingRowAndColNum(int RowNum, int ColNum) throws Exception {

        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    // get cell data using RowNum and Column Name
    /**
     * @param RowNum, ColName
     * @return
     */
    public String getCellDataUsingRownumAndColname(int RowNum, String ColName) throws Exception {

        int colNum = 0;
        for (int i = 0; i < Row.getLastCellNum(); i++) {
            if (ExcelWSheet.getRow(0).getCell(i).getStringCellValue().equals(ColName)) {
                colNum = i;
                break;
            }
        }

        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(colNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Function to convert the excel sheet data from Map to Two dimensional array
     *
     * @param  interimResults containing the excel sheet data in map format
     * @return the sheet data as two dimensional array
     */
    private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) {
        Object[][] results = new Object[interimResults.size()][1];
        int index = 0;
        for (Map<String, String> interimResult : interimResults) {
            results[index++] = new Object[] { interimResult };
        }
        return results;
    }

    /**
     * Function to convert the List of rows and column data to Map
     *
     * @param  names the list of names containing the headers from sheet
     * @param  values the list of values containing the column data from sheet
     * @return the map where key as headers and values as column data
     */

    private static Map<String, String> transformTheListdatatoMap(List<String> names, List<String> values) {
        Map<String, String> results = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String key = names.get(i);
            String value = values.get(i);
            results.put(key, value);
        }
        return results;
    }

    /**
     * Function to read the column data in each row
     *
     * @param row pass the row number as input
     * @return list containing the data for each row
     */
    private static List<String> getValuesInEachRow(Integer row) {
        List<String> data = new ArrayList<>();
        XSSFRow rowdata = ExcelWSheet.getRow(row);
        for (int i = 0; i < rowdata.getLastCellNum(); i++) {
            Cell column = ExcelWSheet.getRow(row).getCell(i);
            if (column != null) {
                if (column.getCellType() == CellType.BLANK) {
                    data.add("");
                } else if (column.getCellType() == CellType.NUMERIC || column.getCellType() == CellType.FORMULA) {

                    String cellText = String.valueOf(column.getNumericCellValue());
                    if (DateUtil.isCellDateFormatted(column)) {

                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = column.getDateCellValue();
                        cellText = df.format(date);
                    }
                    data.add(cellText);
                } else if (column.getCellType() == CellType.BOOLEAN) {
                    data.add(String.valueOf(column.getBooleanCellValue()));
                } else {
                    String value = column.getStringCellValue();
                    data.add(value);
                }
            } else {
                data.add("");
            }
        }
        return data;
    }

    /**
     * Function to get the list of row numbers where a specific data is present in a column
     *
     * @param SHEET_NAME  the excel sheet
     * @param colname the column where data needs to be searched for
     * @return Arraylist containing row numbers where the data is present
     */

    public static ArrayList<Integer> getRowIndexInfoFromExcel(String SHEET_NAME, String colname)
            throws EncryptedDocumentException, IOException {

        ExcelWSheet = ExcelWBook.getSheet(SHEET_NAME);
        ArrayList<Integer> dataIndices = new ArrayList<>();
        int colIndex = 0;
        Row header = ExcelWSheet.getRow(0);
        // get index of desired column
        for (int i = 0; i < header.getLastCellNum(); i++) {
            if (header.getCell(i).getStringCellValue().equalsIgnoreCase(colname)) {
                colIndex = i;
                break;
            }
        }
        // find row numbers where the data is present
        for (Row row1 : ExcelWSheet) {
            Cell c = (XSSFCell) row1.getCell(colIndex);
            if (c != null) {
                dataIndices.add(row1.getRowNum());
            }
        }
        return dataIndices;
    }

    /**
     * Function to extract the row data
     *
     * @param sheetname  the excel sheet
     * @param colname the column where data needs to be searched for
     * @return Two dimensional object containing row and column data from sheet
     */

    public Object[][] extractRowData(String sheetname, String colname) throws EncryptedDocumentException, IOException {
        ArrayList<Integer> rowindex = getRowIndexInfoFromExcel(sheetname, colname);
        List<Map<String, String>> results = new ArrayList<>();
        boolean header = true;
        List<String> keys = null;
        for (Integer row : rowindex) {
            List<String> values = getValuesInEachRow(row);
            if (header) {
                header = false;
                keys = values;
                continue;
            }
            results.add(transformTheListdatatoMap(keys, values));
        }
        return asTwoDimensionalArray(results);
    }

    // Read the whole data from excel sheet
    /**
     * @param sheetname
     * @return the two dimensional object
     */
    public Object[][] ReadWholeExcelSheetData(String sheetname) throws IOException {
        Object data[][] = null;
        ExcelWSheet = ExcelWBook.getSheet(sheetname);
        int RowNum = getRowCount(sheetname);
        int ColNum = getColumnCount(sheetname);

        data = new Object[RowNum][ColNum];
        try {
            for (int i = 0; i < RowNum; i++) // Loop work for Rows
            {
                for (int j = 0; j < ColNum; j++) // Loop work for colNum
                {
                    Cell = ExcelWSheet.getRow(i + 1).getCell(j);
                    if (Cell != null) {
                        if (Cell.getCellType() == CellType.BLANK) {
                            data[i][j] = "";
                        } else if (Cell.getCellType() == CellType.NUMERIC || Cell.getCellType() == CellType.FORMULA) {

                            String cellText = String.valueOf(Cell.getNumericCellValue());
                            if (DateUtil.isCellDateFormatted(Cell)) {

                                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                                Date date = Cell.getDateCellValue();
                                cellText = df.format(date);
                            }
                            data[i][j] = cellText;
                        } else if (Cell.getCellType() == CellType.BOOLEAN) {
                            data[i][j] = String.valueOf(Cell.getBooleanCellValue());
                        } else {
                            String value = Cell.getStringCellValue();
                            data[i][j] = value;
                        }
                    } else {
                        data[i][j] = "";
                    }
                }

            }

        } catch (Exception e) {
            throw (e);
        }
        return data;
    }

}