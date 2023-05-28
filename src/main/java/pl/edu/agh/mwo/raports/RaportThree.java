package pl.edu.agh.mwo.raports;

import java.util.*;

public class RaportThree implements Raport {

    public Map<String, Double> analyze(List<List<List<List<Object>>>> workbookDataList) {

        Map<String, Double> taskTimeSums = new HashMap<>();

        for (List<List<List<Object>>> workbookData : workbookDataList) {
            for (List<List<Object>> sheetData : workbookData) {

                for (int i = 2; i < sheetData.size(); i++) {
                    List<Object> row = sheetData.get(i);


                    if (row.size() >= 3) {

                        String taskName = (String) row.get(1);

                        Double cellValue = (Double) row.get(2);

                        taskTimeSums.put(taskName, taskTimeSums.getOrDefault(taskName, 0.0) + cellValue);
                    }
                }
            }
        }


        for (Map.Entry<String, Double> entry : taskTimeSums.entrySet()) {
            System.out.println("Zadanie: " + entry.getKey() + ", Czas pracy: " + entry.getValue() +"h");
        }


        return taskTimeSums;
    }
}
