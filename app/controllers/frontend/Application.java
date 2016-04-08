package controllers.frontend;

import form.AdminForm;
import form.SearchHouseForm;
import form.UserForm;
import json.OperationResult;
import models.Area;
import models.BuildingKind;
import models.House;
import play.data.Form;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.error.notFound;
import views.html.frontend.index;
import views.html.frontend.search_result;
import views.html.frontend.house_detail;
import play.Logger;
import java.util.*;

/**
 * Created by Summer on 16/3/17.
 */
public class Application extends Controller {
    public Result index () {
        List areas = Area.find.all();
        List buildingKinds = BuildingKind.find.all();

        return ok(index.render("买房子", areas, buildingKinds));
    }

    public Result search () {
        Form<SearchHouseForm> searchForm = Form.form(SearchHouseForm.class).bindFromRequest();

        if (searchForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1)));

        String building_kind = searchForm.get().getBuilding_kind();
        String space_kind = searchForm.get().getSpace_kind();

        Logger.info(building_kind);
        Logger.info(space_kind);

        List<House> houses = new ArrayList<>();

        Optional.ofNullable(BuildingKind.find.where().eq("name", building_kind).findUnique())
                .map(BuildingKind::getBuildings)
                .ifPresent(value -> {
                    value.stream().forEach(building -> {
                        building.getHouses().stream().forEach(house -> {
                            Integer space = house.getSpace();
                            if (space_kind.equals("<100")) {
                                if (space <= 100) houses.add(house);
                            }
                            else if (space_kind.equals("100_140")) {
                                if (space > 100 && space <= 140) houses.add(house);
                            }
                            else if (space_kind.equals("140_200")) {
                                if (space > 140 && space <= 200) houses.add(house);
                            }
                            else if (space_kind.equals(">200")) {
                                if (space > 200) houses.add(house);
                            }
                        });
                    });
                });

        return ok(search_result.render("搜索结果", houses));
    }

    public Result house (Long id) {
        Logger.info(id.toString());

        House house = House.find.byId(id);
        if (house == null) return notFound(notFound.render("404", "你访问的页面不存在"));

        return ok(house_detail.render("购买", house));
    }

    @Transactional
    public Result buy (Long id) {
        Logger.info(id.toString());

        House house = House.find.byId(id);
        if (house == null) return badRequest(Json.toJson(new OperationResult(400, 1)));

        Form<UserForm> userForm = Form.form(UserForm.class).bindFromRequest();
        if (userForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1)));

        String identifier = userForm.get().getIdentifier();
        String name = userForm.get().getName();
        String phone = userForm.get().getPhone();



        return ok(Json.toJson(new OperationResult(200, 0)));
    }
}
