package pl.edu.agh.mwo.converters;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class data_to_excel {
    HashMap<String, String[]> report_one = new HashMap<>();
    HashMap<String, String[]> report_two = new HashMap<>();
    HashMap<String, String[]> report_three = new HashMap<>();

    public void initializeHashMaps(){
        report_one.put("Projekty:", new String[]{});
        report_one.put("Czas:", new String[]{});
    }


    /*Printowanie hashmapy w formacie:
    * klucz1    klucz2    klucz3
    * danek_1   dane_k2   dane_k3
    * danek_1   dane_k2   dane_k3
    * danek_1   dane_k2   dane_k3
    * danek_1   dane_k2   dane_k3
    * */
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

    /*Kurde nie wiem czy zadziała, będziemy testować na produkcji :P */
    public void readHashMapToExcel(HashMap<String, String[]> mapToRead){
        int rowWidth = mapToRead.size();
        int i = 0;
        int arrayLength = getArrayLength(mapToRead);
        String filename = "C:/Raport.xls" ;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Raport");
        HSSFRow rowHead = sheet.createRow((short)0);
        for(String key : mapToRead.keySet()){
            rowHead.createCell(i).setCellValue(key);
            i+=1;
        }
        for(int step = 0; i < arrayLength; i++){
            int j = 0;
            HSSFRow row = sheet.createRow((short)step+1);
            for(String key : mapToRead.keySet()){
                row.createCell(j).setCellValue(mapToRead.get(key)[step+1]);
                j+=1;
            }
        }
    }

}
