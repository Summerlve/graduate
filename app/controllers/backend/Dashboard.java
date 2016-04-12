package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Admin;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.backend.dashboard;

import java.util.Optional;

/**
 * Created by Summer on 16/3/17.
 */

@Restrict(@Group("ADMIN"))
public class Dashboard extends Controller {
    public F.Promise<Result> index () {
        Optional<Admin> user = (Optional) ctx().args.get("user");
        return F.Promise.promise(() -> Results.ok(dashboard.render("控制台", user.get())));
    }
}
