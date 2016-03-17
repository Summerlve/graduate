package controllers.backend;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.index;
import views.html.backend.charts;
import views.html.backend.login;

/**
 * Created by Summer on 16/3/17.
 */
public class Application extends Controller {
    public Result dashboard () {
        return ok(index.render("管理中心", new User()));
    }

    public Result charts () {
        return ok(charts.render("管理中心", new User()));
    }

    public Result login () {
        return ok(login.render("登陆"));
    }
}
