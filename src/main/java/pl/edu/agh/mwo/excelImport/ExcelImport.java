package pl.edu.agh.mwo.excelImport;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelImport{


        public List<List<List<List<Object>>>> excelImport(ArrayList<File> fileList) {

            List<List<List<List<Object>>>> workbookDataList = new ArrayList<>();

            for (File fileIn : fileList) {

                System.out.println(fileIn.getPath());

                try {
                    FileInputStream file = new FileInputStream(fileIn);

                    //Create Workbook instance holding reference to .xlsx file
                    XSSFWorkbook workbook = new XSSFWorkbook(file);

                    // Create a list to hold all data
                    List<List<List<Object>>> workbookData = new ArrayList<>();

                    // Iterate through each sheet one by one
                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        XSSFSheet sheet = workbook.getSheetAt(i);

                        // Create a list to hold all rows in a sheet
                        List<List<Object>> sheetData = new ArrayList<>();

                        // Add sheet name as first element of sheetData
                        List<Object> sheetNameRow = new ArrayList<>();
                        sheetNameRow.add(sheet.getSheetName());
                        sheetData.add(sheetNameRow);

                        //Iterate through each rows one by one
                        Iterator<Row> rowIterator = sheet.iterator();
                        while (rowIterator.hasNext()) {
                            Row row = rowIterator.next();

                            // Create a list to hold all cells in a row
                            List<Object> rowData = new ArrayList<>();

                            //For each row, iterate through all the columns
                            Iterator<Cell> cellIterator = row.cellIterator();

                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
                                //Check the cell type and format accordingly
                                switch (cell.getCellType()) {
                                    case NUMERIC:
                                        if (cell.getAddress().getColumn() == 0) {
                                            rowData.add(cell.getDateCellValue());
                                            break;
                                        } else
                                            rowData.add(cell.getNumericCellValue());
                                        break;
                                    case STRING:
                                        rowData.add(cell.getStringCellValue());
                                        break;
                                }
                            }

                            // Add the row data to the sheet list
                            sheetData.add(rowData);
                        }

                        // Add the sheet data to the main list
                        workbookData.add(sheetData);
                    }
                    file.close();

                    //Print the excel data
                    for (List<List<Object>> sheet : workbookData) {
                        for (List<Object> row : sheet) {
                            for (Object cell : row) {
                                System.out.print(cell + " ");
                            }
                            System.out.println("");
                        }
                        System.out.println("End of sheet");
                    }

                    // Add the workbook data to the workbook data list
                    workbookDataList.add(workbookData);


                } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Return the workbook data list
        return workbookDataList;
    }
}


