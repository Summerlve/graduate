package security;

import be.objectify.deadbolt.core.models.Permission;


/**
 * Created by Summer on 3/22/16.
 */
public enum FakePermission implements Permission {
    COMMON_USER_PERMISSION,
    ADMIN_PERMISSION;

    @Override
    public String getValue() {
        return name();
    }
}
