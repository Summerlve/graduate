package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import models.User;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import java.util.Optional;
import views.html.error.accessFailed;


/**
 * Created by Summer on 3/22/16.
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler {
    @Override
    public F.Promise<Optional<Subject>> getSubject(Http.Context context) {
        Long id = Optional.ofNullable(context.request().cookie("user"))
                            .map(cookie -> Long.valueOf(cookie.value()))
                            .orElse(-1l);

        return F.Promise.promise(() -> Optional.ofNullable(User.find.byId(id)));
    }

    @Override
    public F.Promise<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return F.Promise.pure(null);
    }

    @Override
    public F.Promise<Result> onAuthFailure(Http.Context context, String content) {
        return F.Promise.promise(() -> unauthorized(accessFailed.render()));
    }

    @Override
    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return null; // have no DRH
    }
}
