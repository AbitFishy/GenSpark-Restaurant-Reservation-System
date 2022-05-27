package com.genspark.backend.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.genspark.backend.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    DEV(Sets.newHashSet(SYSTEM_READ, SYSTEM_WRITE, USER_READ, USER_WRITE));
    private final Set<ApplicationUserPermission> permissions;

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }



}
