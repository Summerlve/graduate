package controllers.backend;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.avaje.ebean.Ebean;
import form.OperationOfBuilding;
import form.UserForm;
import json.OperationResult;
import models.Admin;
import models.User;
import play.data.Form;
import play.libs.Json;
import json.UserInfo;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.backend.user_manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Summer on 16/5/24.
 */

@Restrict(@Group("ADMIN"))
public class UserManager extends Controller {
    private static final double pageSize = 10d;

    @SuppressWarnings("unchecked")
    public Result index(Integer page) {
        Optional<Admin> user = (Optional) ctx().args.get("user");

        List<User> users = User.find.where().orderBy("id asc").findPagedList(page - 1, (int)this.pageSize).getList();

        Integer pages = (int)Math.ceil(User.find.all().size() / this.pageSize);
        List<Integer> pagesList = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            pagesList.add(i);
        }

        return ok(user_manager.render("消费者管理", user.get(), users, pagesList, page));
    }

    @BodyParser.Of(BodyParser.FormUrlEncoded.class)
    public Result change(Long id) {
        Form<UserForm> userForm = Form.form(UserForm.class).bindFromRequest();
        if (userForm.hasErrors()) return badRequest("表单数据错误");

        User user = User.find.byId(id);
        if (user == null) return badRequest("消费者不存在");

        UserForm data = userForm.get();
        String sfz = data.getIdentifier();
        String name = data.getName();
        String phone = data.getPhone();

        Ebean.beginTransaction();
        try
        {
            user.setSfz(sfz);
            user.setName(name);
            user.setTelephone(phone);
            user.save();

            Ebean.commitTransaction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Ebean.rollbackTransaction();
            return internalServerError("服务器端错误");
        }
        finally
        {
            Ebean.endTransaction();
        }

        return this.index(1);
    }

    public Result info(Long id) {
        User user = User.find.byId(id);
        if (user == null) return badRequest(Json.toJson(new OperationResult(404, 1, "消费者不存在")));
        return ok(Json.toJson(new UserInfo(user)));
    }
}
