package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;
import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Summer on 3/22/16.
 */

@Singleton
public class PrivilegeCache implements HandlerCache {
    private final DeadboltHandler defaultHandler = new PrivilegeHandler();
    private final Map<String, DeadboltHandler> handlers = new HashMap<>();

    public PrivilegeCache() {
        handlers.put("defaultHandler", defaultHandler);
    }

    @Override
    public DeadboltHandler get() {
        return defaultHandler;
    }

    @Override
    public DeadboltHandler apply(final String key) {
        return handlers.get(key);
    }
}
