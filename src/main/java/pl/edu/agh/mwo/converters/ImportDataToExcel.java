package pl.edu.agh.mwo.converters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class ImportDataToExcel {
    public static void printHashMap(HashMap<String, String[]> hashMapWithValues) {
        for (String key : hashMapWithValues.keySet()) {
            System.out.format("%10s", key);
        }
        System.out.println();

        int arrayLength = getArrayLength(hashMapWithValues);
        for (int i = 0; i < arrayLength; i++) {
            for (String[] values : hashMapWithValues.values()) {
                if (i < values.length) {
                    System.out.format("%10s",values[i]);
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private static int getArrayLength(HashMap<String, String[]> hashMap) {
        int maxArrayLength = 0;
        for (String[] values : hashMap.values()) {
            maxArrayLength = Math.max(maxArrayLength, values.length);
        }
        return maxArrayLength;
    }

    public static void readHashMapToExcelReport_1_3(Map<String, Double> mapToRead, String columnName, String fileName, String outputPath) throws IOException {
        int i = 0;
        String filename = outputPath + fileName +".xls" ;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Raport");
        HSSFRow rowHead = sheet.createRow((short)0);
        rowHead.createCell(0).setCellValue(columnName);
        rowHead.createCell(1).setCellValue("Czas");
        for(String key : mapToRead.keySet()){
            HSSFRow row = sheet.createRow((short)i+1);
            row.createCell(0).setCellValue(key);
            row.createCell(1).setCellValue(mapToRead.get(key));
            i+=1;
        }
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
    }


}
