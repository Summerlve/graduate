package models;

import be.objectify.deadbolt.core.models.Role;

/**
 * Created by Summer on 3/22/16.
 */
public enum FakeRole implements Role {
    ADMIN;

    @Override
    public String getName() {
        return name();
    }
}
