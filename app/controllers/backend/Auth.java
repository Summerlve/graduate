package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import form.AdminForm;
import json.OperationResult;
import models.Admin;
import org.apache.commons.codec.binary.Hex;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.login;

import java.security.MessageDigest;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
        if (adminForm.hasErrors()) return badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误")));

        String username = adminForm.get().getUsername();
        String password = adminForm.get().getPassword();
        String passwordHash = "";

        // get password hash
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] temp = md.digest(password.getBytes("UTF8"));
            passwordHash = new String(Hex.encodeHex(temp));
            Logger.info(passwordHash);
        }
        catch (Exception e) {
            e.printStackTrace();
            return internalServerError(Json.toJson(new OperationResult(500, 1, "服务器端错误")));
        }

        Optional<Admin> result = Admin.auth(username, passwordHash);
        if (!result.isPresent()) return badRequest(Json.toJson(new OperationResult(400, 1, "认证错误")));

        session("user_id", String.valueOf(result.get().getId()));
        session("user_name", String.valueOf(result.get().getUsername()));

        return ok(Json.toJson(new OperationResult(200, 0, "操作成功")));
    }

    @Restrict(@Group("ADMIN"))
    public Result logout () {
        // remove cookie
        session().clear();

        return redirect(controllers.backend.routes.Auth.login());
    }
}
