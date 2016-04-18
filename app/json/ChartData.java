package json;

import models.House;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 4/13/16.
 */
public class ChartData {
    public Long sold;
    public Long ordered;
    public Long unsold;
    public Long unfinished;

    public List<House> houses = new ArrayList<>();
}
