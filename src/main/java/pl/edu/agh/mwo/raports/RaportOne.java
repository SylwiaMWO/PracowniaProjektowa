package pl.edu.agh.mwo.raports;

import java.util.*;

public class RaportOne {

    public Map<String, Double> analyze(List<List<List<List<Object>>>> workbookDataList) {

        Map<String, Double> sheetTimeSums = new HashMap<>();


        for (List<List<List<Object>>> workbookData : workbookDataList) {


            for (List<List<Object>> sheetData : workbookData) {


                List<Object> firstRow = sheetData.get(0);
                String sheetName = (String) firstRow.get(0);


                double sheetSum = 0;


                for (int i = 2; i < sheetData.size(); i++) {
                    List<Object> row = sheetData.get(i);


                    if (row.size() >= 3) {
                        // Get the "Czas [h]" cell, assume it's a Double
                        Double cellValue = (Double) row.get(2);
                        sheetSum += cellValue;
                    }
                }


                sheetTimeSums.put(sheetName, sheetTimeSums.getOrDefault(sheetName, 0.0) + sheetSum);
            }
        }


        for (Map.Entry<String, Double> entry : sheetTimeSums.entrySet()) {
            System.out.println("Project: " + entry.getKey() + ", Total Time: " + entry.getValue());
        }


        return sheetTimeSums;
    }
}
