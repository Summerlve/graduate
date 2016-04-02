package controllers.frontend;

import models.Area;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.frontend.index;

import java.util.List;

/**
 * Created by Summer on 16/3/17.
 */
public class Application extends Controller {
    public Result index () {
        List areas = Area.find.all();

        return ok(index.render("买房子", areas));
    }
}
