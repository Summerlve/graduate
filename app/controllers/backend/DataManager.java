package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Admin;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.data_manager;

import java.util.Optional;

/**
 * Created by Summer on 16/3/17.
 */

@Restrict(@Group("ADMIN"))
public class DataManager extends Controller {
    public Result index () {
        Optional<Admin> user = (Optional) ctx().args.get("user");
        return ok(data_manager.render("数据管理", user.get()));
    }
}
