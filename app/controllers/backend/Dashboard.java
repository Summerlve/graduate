package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import form.SearchUserForm;
import json.DashboardData;
import json.OperationResult;
import json.UserSearchResult;
import models.Admin;
import models.House;
import models.User;
import play.Logger;
import play.data.Form;
import play.libs.F;
import play.libs.Json;
import play.mvc.BodyParser;
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

        data.setTop_owner(topowner);
        data.sold = sold;
        data.ordered = ordered;
        data.unsold = unsold;
        data.unfinished = unfinished;

        return F.Promise.promise(() -> ok(Json.toJson(data)));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public F.Promise<Result> search () {
        Form<SearchUserForm> searchUserForm = Form.form(SearchUserForm.class).bindFromRequest();
        if (searchUserForm.hasErrors()) return F.Promise.promise(() -> badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误"))));

        // check user is exist
        User user = User.findBySfz(searchUserForm.get().getSfz());
        if (user == null) return F.Promise.promise(() -> badRequest(Json.toJson(new OperationResult(400, 1, "用户不存在"))));

        Logger.info(String.valueOf(user.getHouses().size()));

        // gen json result
        UserSearchResult result = new UserSearchResult();
        result.houses = user.getHouses();
        result.sfz = user.getSfz();
        result.name = user.getName();
        result.phone = user.getTelephone();

        return F.Promise.promise(() -> ok(Json.toJson(result)));
    }
}
