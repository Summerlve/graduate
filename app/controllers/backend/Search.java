package controllers.backend;

import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Summer on 16/4/15.
 */
public class Search extends Controller {
    public F.Promise<Result> index () {
        return F.Promise.promise(() -> ok());
    }

    public F.Promise<Result> search () {
        return F.Promise.promise(() -> ok());
    }
}
