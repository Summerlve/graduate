package modules;

import be.objectify.deadbolt.java.cache.HandlerCache;
import com.google.inject.Singleton;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;
import security.PrivilegeCache;

/**
 * Created by Summer on 3/22/16.
 */
public class PrivilegeHook extends Module {
    @Override
    public Seq<Binding<?>> bindings(Environment environment, Configuration configuration) {
        return seq(bind(HandlerCache.class).to(PrivilegeCache.class).in(Singleton.class));
    }
}
