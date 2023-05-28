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

                    XSSFWorkbook workbook = new XSSFWorkbook(file);
                    List<List<List<Object>>> workbookData = new ArrayList<>();

                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        XSSFSheet sheet = workbook.getSheetAt(i);

                        List<List<Object>> sheetData = new ArrayList<>();

                        List<Object> sheetNameRow = new ArrayList<>();
                        sheetNameRow.add(sheet.getSheetName());
                        sheetData.add(sheetNameRow);

                        Iterator<Row> rowIterator = sheet.iterator();
                        while (rowIterator.hasNext()) {
                            Row row = rowIterator.next();

                            List<Object> rowData = new ArrayList<>();
                            Iterator<Cell> cellIterator = row.cellIterator();

                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
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

                            sheetData.add(rowData);
                        }

                        workbookData.add(sheetData);
                    }
                    file.close();

                    for (List<List<Object>> sheet : workbookData) {
                        for (List<Object> row : sheet) {
                            for (Object cell : row) {
                                System.out.print(cell + " ");
                            }
                            System.out.println("");
                        }
                        System.out.println("End of sheet");
                    }

                    workbookDataList.add(workbookData);

                } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return workbookDataList;
    }
}




