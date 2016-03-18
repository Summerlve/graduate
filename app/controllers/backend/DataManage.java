package controllers.backend;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.data_manage;

/**
 * Created by Summer on 16/3/17.
 */
public class DataManage extends Controller {
    public Result index () {
        return ok(data_manage.render());
    }
}
