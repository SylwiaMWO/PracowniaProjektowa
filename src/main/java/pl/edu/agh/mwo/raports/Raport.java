package pl.edu.agh.mwo.raports;

import java.util.List;
import java.util.Map;

public interface Raport {
    public Map<String, Double> analyze(List<List<List<List<Object>>>> workbookDataList);
}
