package controllers.backend;

import models.Admin;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.login;
import java.util.Map;
import static java.lang.System.out;

/**
 * Created by Summer on 16/3/17.
 */
public class Auth extends Controller {
    public Result index () {
        if (session("user_id") != null) return redirect("/dashboard");
        return ok(login.render("登陆"));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public Result login () {
        Map<String, String[]> form = request().body().asFormUrlEncoded();

        String username = form.get("username")[0];
        String password = form.get("password")[0];

        Map<String, Object> result = Admin.auth(username, password);
        Boolean isAccess = (Boolean)result.get("isAccess");
        Long id = (Long)result.get("id");

        out.println(isAccess);
        if (!isAccess) return unauthorized("用户名或密码不正确");

        session("user_id", String.valueOf(id));
        return redirect("/dashboard");
    }

    public Result logout () {
        return ok();
    }
}
