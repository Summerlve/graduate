package controllers.frontend;

import com.avaje.ebean.Ebean;
import form.SearchHouseForm;
import form.UserForm;
import json.OperationResult;
import models.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.frontend.index;
import views.html.frontend.search_result;
import views.html.frontend.house_detail;
import views.html.frontend.area_detail;
import views.html.frontend.buildings_of_area;
import views.html.error.not_found;
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

        if (searchForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误")));

        String building_kind = searchForm.get().getBuilding_kind();
        String space_kind = searchForm.get().getSpace_kind();

        Logger.info(building_kind);
        Logger.info(space_kind);

        List<House> houses = new ArrayList<>();

        Optional.ofNullable(BuildingKind.find.where().eq("name", building_kind).findUnique())
                .map(BuildingKind::getBuildings)
                .ifPresent(value -> {
                    value
                        .stream()
                        .forEach(building -> {
                            building
                                .getHouses().stream()
                                .filter(house -> {
                                    if (house.getState().getName().equals("未售出")) return true;
                                    else return false;
                                }).forEach(house -> {
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
        if (house == null) return notFound(not_found.render("404", "你访问的页面不存在"));

        return ok(house_detail.render("购买", house));
    }

    public Result buy (Long id) {
        Logger.info("buy" + id.toString());

        House house = House.find.byId(id);
        Logger.info(house.getId().toString());
        if (house == null) return badRequest(Json.toJson(new OperationResult(400, 1, "未找到此房屋")));

        Logger.info(house.getState().getName());
        if (house.getState().getName().equals("已售出")) return badRequest(Json.toJson(new OperationResult(400, 1, "房屋已经售出了")));

        Form<UserForm> userForm = Form.form(UserForm.class).bindFromRequest();
        if (userForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误")));

        String identifier = userForm.get().getIdentifier();
        String name = userForm.get().getName();
        String phone = userForm.get().getPhone();

        // 查看此用户是否存在, 如果存在就使用,如果不存在就存到数据库中
        User user = User.find.where().eq("sfz", identifier).findUnique();
        if (user == null) {
            user = new User();
            user.setSfz(identifier);
            user.setName(name);
            user.setTelephone(phone);
        }

        // save to db
        Ebean.beginTransaction();
        try {
            // maybe user update his's phone or name
            user.setName(name);
            user.setTelephone(phone);
            user.save();

            house.setUser(user);
            house.setState(HouseState.find.where().eq("name", "已售出").findUnique());
            house.save();

            Ebean.commitTransaction();
        }
        catch (Exception e) {
            e.printStackTrace();
            Ebean.rollbackTransaction();
            return internalServerError(Json.toJson(new OperationResult(500, 1, "服务器端错误")));
        }
        finally {
            Ebean.endTransaction();
        }

        return ok(Json.toJson(new OperationResult(200, 0, "操作成功")));
    }

    public Result area (Long id) {
        // get area from db
        Area area = Area.find.byId(id);
        if (area == null) return notFound(not_found.render("未找到小区", "未找到小区"));

        return ok(area_detail.render(area.getName(), area));
    }

    public Result building (Long id) {
        // get building from db
        Building building = Building.find.byId(id);
        if (building == null) return notFound(not_found.render("未找到楼栋", "未找到楼栋"));

        return ok(buildings_of_area.render(building.getArea().getName(), building));
    }
}
