package controllers.backend;

import models.Admin;
import models.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.login;
import java.util.Map;

/**
 * Created by Summer on 16/3/17.
 */
public class Login extends Controller {
    public Result index () {
        return ok(login.render("登陆"));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public Result auth () {
        Map<String, String[]> form = request().body().asFormUrlEncoded();
        String adminId = form.get("user")[0];

        if (adminId == null && adminId.equals("")) return badRequest("need valiead username");

        if (adminId != null && !adminId.equals("")) {
            Admin admin = Admin.find.byId(Long.valueOf(adminId));
            if (admin == null) {
                return unauthorized("用户未找到或者你没有登陆的权限");
            }
            session("user", String.valueOf(admin.getId()));
        }

        return ok();
    }
}
