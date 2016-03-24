package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.Admin;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import java.util.Optional;
import views.html.backend.login;


/**
 * Created by Summer on 3/22/16.
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler {
    @Override
    public F.Promise<Optional<Subject>> getSubject(Http.Context context) {
        String username = Optional.ofNullable(context.session().get("username")).orElse("");
        return F.Promise.promise(() -> Optional.ofNullable(Admin.findbyUsername(username)));
    }

    @Override
    public F.Promise<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return F.Promise.pure(Optional.empty());
    }

    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String content) {
        return F.Promise.pure(unauthorized(login.render("请登录")));
    }

    @Override
    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return F.Promise.pure(Optional.empty());
    }
}
