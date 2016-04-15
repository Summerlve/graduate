package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import json.DashboardData;
import models.Admin;
import models.House;
import models.User;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import util.Pair;
import views.html.backend.dashboard;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Summer on 16/3/17.
 */

@Restrict(@Group("ADMIN"))
public class Dashboard extends Controller {
    public F.Promise<Result> index () {
        Optional<Admin> user = (Optional) ctx().args.get("user");
        return F.Promise.promise(() -> Results.ok(dashboard.render("控制台", user.get())));
    }

    public F.Promise<Result> data () {
        DashboardData data = new DashboardData();

        List<Pair<String, Integer>> topowner = User.find.all().stream()
            .sorted((pre, lat) -> {
                if (pre.getHouses().size() > lat.getHouses().size()) return -1;
                else if (pre.getHouses().size() < lat.getHouses().size()) return 1;
                else return 0;
            })
            .map(value -> new Pair<>(value.getName(), value.getHouses().size()))
            .limit(4)
            .collect(Collectors.toList());

        List<House> houses = House.find.all();

        Long selled = houses.stream()
                .filter(value -> {
                    if (value.getState().getName().equals("已售出")) return true;
                    else return false;
                })
                .count();

        Long ordered = houses.stream()
                .filter(value -> {
                    if (value.getState().getName().equals("已预订")) return true;
                    else return false;
                })
                .count();

        Long unselled = houses.stream()
                .filter(value -> {
                    if (value.getState().getName().equals("未售出")) return true;
                    else return false;
                })
                .count();

        Long unfinish = houses.stream()
                .filter(value -> {
                    if (value.getState().getName().equals("未完成")) return true;
                    else return false;
                })
                .count();

        data.setTop_owner(topowner);
        data.sold = selled;
        data.ordered = ordered;
        data.unsold = unselled;
        data.unfinished = unfinish;

        return F.Promise.promise(() -> ok(Json.toJson(data)));
    }
}
