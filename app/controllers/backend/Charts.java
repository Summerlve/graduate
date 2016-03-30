package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.charts;

/**
 * Created by Summer on 16/3/17.
 */
@Restrict(@Group("ADMIN"))
public class Charts extends Controller {
    public Result index () {
        return ok(charts.render("图表", new User()));
    }

    public Result data () {
        return ok();
    }
}
