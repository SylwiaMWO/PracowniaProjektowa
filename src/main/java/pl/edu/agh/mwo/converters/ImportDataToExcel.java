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

    public static void readHashMapToExcel(HashMap<String, String[]> mapToRead) throws IOException {
        int rowWidth = mapToRead.size();
        int i = 0;
        int arrayLength = getArrayLength(mapToRead);
        String filename = "/home/students/j/g/jgrela/pracownia/PracowniaProjektowa/DataRaport.xls" ;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Raport");
        HSSFRow rowHead = sheet.createRow((short)0);
        for(String key : mapToRead.keySet()){
            rowHead.createCell(i).setCellValue(key);
            i+=1;
        }
        for(int step = 0; step < arrayLength; step++){
            int j = 0;
            HSSFRow row = sheet.createRow((short)step+1);
            for(String key : mapToRead.keySet()){
                row.createCell(j).setCellValue(mapToRead.get(key)[step]);
                j+=1;
            }
        }
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
    }
    public static void readHashMapToExcelReport_1_3(Map<String, Double> mapToRead, String columnName, String fileName) throws IOException {
        int i = 0;
        String filename = "/home/students/j/g/jgrela/pracownia/PracowniaProjektowa/" + fileName +".xls" ;
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
