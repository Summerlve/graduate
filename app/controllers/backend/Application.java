package controllers.backend;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.index;

/**
 * Created by Summer on 16/3/17.
 */
public class Application extends Controller {
    public Result dashboard () {
        return ok(index.render());
    }
}
