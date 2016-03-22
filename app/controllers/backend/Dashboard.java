package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.User;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import security.FakeRoles;
import views.html.backend.index;

/**
 * Created by Summer on 16/3/17.
 */


public class Dashboard extends Controller {
    @Restrict(@Group("ADMIN"))
    public F.Promise<Result> index () {
        return F.Promise.promise(() -> Results.ok(index.render("管理中心", new User())));
    }
}
