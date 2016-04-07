package controllers.frontend;

import models.House;
import play.Logger;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.error.notFound;
import views.html.frontend.buy;

/**
 * Created by Summer on 4/7/16.
 */
public class Buy extends Controller {
    public Result index (Long id) {
        Logger.info(id.toString());

        House house = House.find.byId(id);
        if (house == null) return notFound(notFound.render("404", "你访问的页面不存在"));

        return ok(buy.render("购买", house));
    }

    @Transactional
    public Result buy (Long id) {
       return ok();
    }
}
