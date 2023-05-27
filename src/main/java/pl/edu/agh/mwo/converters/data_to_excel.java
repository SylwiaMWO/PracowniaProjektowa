package pl.edu.agh.mwo.converters;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class data_to_excel {
    HashMap<String, String[]> report_one = new HashMap<>();
    HashMap<String, String[]> report_two = new HashMap<>();
    HashMap<String, String[]> report_three = new HashMap<>();

    public void initializeHashMaps(){
        report_one.put("Projekty:", new String[]{});
        report_one.put("Czas:", new String[]{});
    }

    public void readListToHashMap(HashMap<String, String[]> mapToRead, HashMap<String, String[]> mapToWrite){

    }
}
