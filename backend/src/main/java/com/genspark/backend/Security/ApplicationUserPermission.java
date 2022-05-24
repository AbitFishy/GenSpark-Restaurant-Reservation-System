package com.genspark.backend.Security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    SYSTEM_READ("system:read"),
    SYSTEM_WRITE("system:write");
    private final String permission;
    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
