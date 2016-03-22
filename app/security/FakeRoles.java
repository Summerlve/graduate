package security;

import be.objectify.deadbolt.core.models.Role;

/**
 * Created by Summer on 3/22/16.
 */
public enum FakeRoles implements Role {
    COMMON_USER,
    ADMIN;

    @Override
    public String getName() {
        return name();
    }
}
