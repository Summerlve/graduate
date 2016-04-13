package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Admin;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.charts;

import java.util.Optional;

/**
 * Created by Summer on 16/3/17.
 */
@Restrict(@Group("ADMIN"))
public class Charts extends Controller {
    public Result index () {
        Optional<Admin> user = (Optional) ctx().args.get("user");
        return ok(charts.render("图表", user.get()));
    }

    public Result data () {
        return ok();
    }
}
