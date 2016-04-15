package json;


import util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/4/15.
 */
public class DashboardData {
    public Long sold;
    public Long ordered;
    public Long unsold;
    public Long unfinished;
    public List<TopOwner> top_owner = new ArrayList<>();

    private class TopOwner {
        public String name;
        public Integer num;

        public TopOwner () {

        }

        public TopOwner (String name, Integer num) {
            this.name = name;
            this.num = num;
        }
    }

    public void setTop_owner(List<Pair<String, Integer>> owner) {
        owner.stream().map(value -> new TopOwner(value.fst, value.snd)).forEach(this.top_owner::add);
    }
}
