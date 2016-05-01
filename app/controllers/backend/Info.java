package controllers.backend;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.avaje.ebean.Ebean;
import form.InfoForm;
import json.OperationResult;
import models.Admin;
import org.apache.commons.codec.binary.Hex;
import play.Logger;
import play.data.Form;
import play.libs.F;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.backend.info;

import java.security.MessageDigest;
import java.util.Optional;

/**
 * Created by Summer on 4/13/16.
 */

@Restrict(@Group("ADMIN"))
public class Info extends Controller {
    public F.Promise<Result> index () {
        Optional<Admin> user = (Optional) ctx().args.get("user");
        return F.Promise.promise(() -> Results.ok(info.render("个人信息", user.get())));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public F.Promise<Result> change() {
        Form<InfoForm> infoForm = Form.form(InfoForm.class).bindFromRequest();
        if (infoForm.hasErrors()) return F.Promise.promise(() -> badRequest(Json.toJson(new OperationResult(400, 1, "表单数据错误"))));

        // get data from form
        String username = infoForm.get().getUsername();
        String password = infoForm.get().getPassword();
        String passwordAgain = infoForm.get().getPassword_again();
        String sfz = infoForm.get().getSfz();
        String phone = infoForm.get().getPhone();

        if (!password.equals(passwordAgain)) return F.Promise.promise(() -> internalServerError(Json.toJson(new OperationResult(400, 1, "两次输入的密码不一样"))));

        Admin admin = (Admin)((Optional) ctx().args.get("user")).get();

        // get password hash
        String passwordHash;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] temp = md.digest(password.getBytes("UTF8"));
            passwordHash = new String(Hex.encodeHex(temp));
            Logger.info(passwordHash);
        }
        catch (Exception e) {
            e.printStackTrace();
            return F.Promise.promise(() -> internalServerError(Json.toJson(new OperationResult(500, 1, "服务器端错误"))));
        }

        // save to db
        Ebean.beginTransaction();
        try {
            admin.setPasswordHash(passwordHash);
            admin.setSfz(sfz);
            admin.setTelephone(phone);
            admin.setUsername(username);

            admin.save();
            Ebean.commitTransaction();
        }
        catch (Exception e) {
            e.printStackTrace();
            Ebean.rollbackTransaction();
            return F.Promise.promise(() -> internalServerError(Json.toJson(new OperationResult(500, 1, "服务器端错误"))));
        }
        finally {
            Ebean.endTransaction();
        }

        return F.Promise.promise(() -> ok(Json.toJson(new OperationResult(200, 0, "操作成功"))));
    }
}
