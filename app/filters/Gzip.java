package filters;

import javax.inject.Inject;
import play.http.HttpFilters;
import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;


/**
 * Created by Summer on 16/4/18.
 */
public class Gzip implements HttpFilters {
    @Inject
    GzipFilter  gzipFilter;

    public EssentialFilter[] filters () {
        return new EssentialFilter[] {gzipFilter};
    }
}
