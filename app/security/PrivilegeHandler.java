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
public class PrivilegeHandler extends AbstractDeadboltHandler {
    @Override
    public F.Promise<Optional<Subject>> getSubject(Http.Context context) {
        Long userId = Optional.ofNullable(context.session().get("user_id")).map(value -> Long.valueOf(value)).orElse(-1l);
        return F.Promise.promise(() -> Optional.ofNullable(Admin.find.byId(userId)));
    }

    @Override
    public F.Promise<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return F.Promise.pure(Optional.empty());
    }

    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String content) {
        return F.Promise.promise(() -> redirect("/dashboard/login"));
    }

    @Override
    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return F.Promise.pure(Optional.empty());
    }
}
