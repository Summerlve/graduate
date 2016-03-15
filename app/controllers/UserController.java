package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.users.index;

/**
 * Created by Summer on 16/3/15.
 */
public class UserController extends Controller {
    public Result index (final String page, final String limit) {
        return ok(index.render(page, limit));
    }

    public Result one (final Long id) {
        return ok(id.toString());
    }

}
