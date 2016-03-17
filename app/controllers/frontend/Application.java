package controllers.frontend;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Summer on 16/3/17.
 */
public class Application extends Controller {
    public Result index () {
        return ok("frontend ok");
    }
}
