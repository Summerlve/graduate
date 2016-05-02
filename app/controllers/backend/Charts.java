package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import json.ChartData;
import models.Admin;
import models.Area;
import models.House;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.charts;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Summer on 16/3/17.
 */
@Restrict(@Group("ADMIN"))
public class Charts extends Controller {
    @SuppressWarnings("unchecked")
    public Result index () {
        Optional<Admin> user = (Optional<Admin>) ctx().args.get("user");
        return ok(charts.render("图表", user.get()));
    }

    public Result data () {
        List<House> houses = House.find.all();

        Long sold = houses.stream()
                .filter(value -> value.getState().getName().equals("已售出"))
                .count();

        Long ordered = houses.stream()
                .filter(value -> value.getState().getName().equals("已预订"))
                .count();

        Long unsold = houses.stream()
                .filter(value -> value.getState().getName().equals("未售出"))
                .count();

        Long unfinished = houses.stream()
                .filter(value -> value.getState().getName().equals("未完成"))
                .count();


//        Map<String, List<Area>> groupedTerritory = new HashMap<>();
//
//        Area.find.all().stream().forEach(area -> {
//            Optional
//                .ofNullable(area.getTerritory())
//                .ifPresent(
//                    territory -> {
//                        if (groupedTerritory.containsKey(territory)) groupedTerritory.get(territory).add(area);
//                        else groupedTerritory.put(territory, Arrays.asList(area));
//                    });});
//
//        groupedTerritory.forEach(
//                (k, v) ->{
//
//                });

        ChartData chartData = new ChartData();
        chartData.sold = sold;
        chartData.ordered = ordered;
        chartData.unsold = unsold;
        chartData.unfinished = unfinished;
        chartData.houses = houses;

        return ok(Json.toJson(chartData));
    }
}
