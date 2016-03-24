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
public class Login extends Controller {
    public Result index () {
        if (session("username") != null) return redirect("/dashboard");
        return ok(login.render("登陆"));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public Result auth () {
        Map<String, String[]> form = request().body().asFormUrlEncoded();

        String username = form.get("username")[0];
        String password = form.get("password")[0];

        boolean isAccess = Admin.auth(username, password);
        out.println(isAccess);

        if (!isAccess) return unauthorized("用户名或密码不正确");

        session("username", username);
        return redirect("/dashboard");
    }
}
