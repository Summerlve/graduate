package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import json.ChartData;
import models.Admin;
import models.House;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.charts;

import java.util.List;
import java.util.Optional;

/**
 * Created by Summer on 16/3/17.
 */
@Restrict(@Group("ADMIN"))
public class Charts extends Controller {
    @SuppressWarnings(value = "unchecked")
    public Result index () {
        Optional<Admin> user = (Optional<Admin>) ctx().args.get("user");
        return ok(charts.render("图表", user.get()));
    }

    public Result data () {
        List<House> houses = House.find.all();

        Long sold = houses.stream()
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

        Long unsold = houses.stream()
                .filter(value -> {
                    if (value.getState().getName().equals("未售出")) return true;
                    else return false;
                })
                .count();

        Long unfinished = houses.stream()
                .filter(value -> {
                    if (value.getState().getName().equals("未完成")) return true;
                    else return false;
                })
                .count();

        ChartData chartData = new ChartData();
        chartData.sold = sold;
        chartData.ordered = ordered;
        chartData.unsold = unsold;
        chartData.unfinished = unfinished;
        chartData.houses = houses;

        return ok(Json.toJson(chartData));
    }
}
