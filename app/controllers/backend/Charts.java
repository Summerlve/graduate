package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import json.ChartData;
import models.Admin;
import models.Area;
import models.House;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.charts;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Summer on 16/3/17.
 */
@Restrict(@Group("ADMIN"))
public class Charts extends Controller {
    @SuppressWarnings("unchecked")
    public Result index () {
        Optional<Admin> user = (Optional<Admin>) ctx().args.get("user");

        List<String> territories = Area.find.all().stream()
                .map(Area::getTerritory)
                    .distinct()
                        .collect(Collectors.toList());

        return ok(charts.render("图表", user.get(), territories));
    }

    public Result data (String territoryName) {
        List<House> houses = House.find.all();

        Long sold = houses.stream()
                .filter(value -> value.getState().getName().equals("已售出"))
                .filter(value -> value.getBuildingId().getArea().getTerritory().equals(territoryName))
                .count();

        Long ordered = houses.stream()
                .filter(value -> value.getState().getName().equals("已预订"))
                .filter(value -> value.getBuildingId().getArea().getTerritory().equals(territoryName))
                .count();

        Long unsold = houses.stream()
                .filter(value -> value.getState().getName().equals("未售出"))
                .filter(value -> value.getBuildingId().getArea().getTerritory().equals(territoryName))
                .count();

        Long unfinished = houses.stream()
                .filter(value -> value.getState().getName().equals("未完成"))
                .filter(value -> value.getBuildingId().getArea().getTerritory().equals(territoryName))
                .count();


        Map<String, List<Area>> groupedTerritory = new HashMap<>();

        Area.find.all().stream().forEach(area -> {
            Optional
                .ofNullable(area.getTerritory())
                .ifPresent(
                    territory -> {
                        if (groupedTerritory.containsKey(territory))
                        {
                            groupedTerritory.get(territory).add(area);
                        }
                        else
                        {
                            List<Area> areas = new ArrayList<Area>();
                            areas.add(area);
                            groupedTerritory.put(territory, areas);
                        }
                    });});

        List<ChartData.Territory> territory_data = new ArrayList<>();

        groupedTerritory.forEach(
                (k, v) -> {
                    ChartData.Territory territory = new ChartData.Territory();
                    territory_data.add(territory);
                    territory.name = k;
                    territory.unsold = 0L;
                    territory.sold = 0L;
                    territory.ordered = 0L;
                    territory.unfinished = 0L;

                    v.stream().forEach(area -> {
                        area.getBuildings().stream().forEach(building -> {
                            building.getHouses().stream().forEach(house -> {
                                if (house.getState().getName().equals("已售出")) territory.sold ++;
                                if (house.getState().getName().equals("已预订")) territory.ordered ++;
                                if (house.getState().getName().equals("未售出")) territory.unsold ++;
                                if (house.getState().getName().equals("未完成")) territory.unfinished ++;
                            });});});});

        ChartData chartData = new ChartData();
        chartData.sold = sold;
        chartData.ordered = ordered;
        chartData.unsold = unsold;
        chartData.unfinished = unfinished;
        chartData.territory_data = territory_data;

        // houses filter
        houses = houses.stream().filter(value -> value.getBuildingId().getArea().getTerritory().equals(territoryName)).collect(Collectors.toList());
        chartData.houses = houses;

        return ok(Json.toJson(chartData));
    }
}
