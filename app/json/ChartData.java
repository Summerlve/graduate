package json;

import models.House;
import util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Summer on 4/13/16.
 */
public class ChartData {
    public Long sold;
    public Long ordered;
    public Long unsold;
    public Long unfinished;
    public List<House> houses = new ArrayList<>();
    public List<Territory> territory_data = new ArrayList<>();

    public static class Territory {
        public String name;
        public Long sold;
        public Long ordered;
        public Long unsold;
        public Long unfinished;
    }
}
