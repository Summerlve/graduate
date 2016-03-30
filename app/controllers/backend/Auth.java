package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import form.AdminForm;
import models.Admin;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.login;
import java.util.Optional;

/**
 * Created by Summer on 16/3/17.
 */
public class Auth extends Controller {
    public Result index () {
        if (session("user_id") != null) return redirect(controllers.backend.routes.Dashboard.index());
        return ok(login.render("登陆", Form.form(AdminForm.class)));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public Result login () {
        Form<AdminForm> adminForm = Form.form(AdminForm.class).bindFromRequest();

        if (adminForm.hasErrors()) return badRequest();

        String username = adminForm.get().getUsername();
        String password = adminForm.get().getPassword();
        Optional<Admin> result = Admin.auth(username, password);

        if (!result.isPresent()) return redirect(controllers.backend.routes.Auth.index());

        session("user_id", String.valueOf(result.get().getId()));
        session("user_name", String.valueOf(result.get().getUsername()));

        return redirect("/dashboard");
    }

    @Restrict(@Group("ADMIN"))
    public Result logout () {
        session("user_id", null);
        return ok();
    }
}
