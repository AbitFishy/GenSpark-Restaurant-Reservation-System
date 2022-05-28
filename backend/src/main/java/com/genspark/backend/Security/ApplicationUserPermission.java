package com.genspark.backend.Security;

public enum ApplicationUserPermission {
/*    USER_READ("user:read"),
    USER_WRITE("user:write"),
    SYSTEM_READ("system:read"),
    SYSTEM_WRITE("system:write"),
    */

    USER_CREATE("user:create"),
    USER_EDIT("user:edit"),
    USER_DELETE("user:delete"),
    USER_VIEW("user:view"),
    USER_ST_CONFIRM("user:stconfirm"),
    USER_ST_CANCEL("user:stcancel"),

    EMPLOY_CREATE("employ:create"),
    EMPLOY_EDIT("employ:edit"),
    EMPLOY_DELETE("employ:delete"),
    EMPLOY_VIEW("employ:view"),
    EMPLOY_ST_CONFIRM("employ:stconfirm"),
    EMPLOY_ST_CANCEL("employ:stcancel"),
    EMPLOY_ST_ARRIVED("employ:starrived"),
    EMPLOY_ST_COMPLETE("employ:stcomplete"),


    ADMIN_CREATE("admin:create"),
    ADMIN_EDIT("admin:edit"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_VIEW("admin:view"),
    ADMIN_ST_CONFIRM("admin:stconfirm"),
    ADMIN_ST_CANCEL("admin:stcancel"),
    ADMIN_ST_ARRIVED("admin:starrived"),
    ADMIN_ST_COMPLETE("admin:stcomplete");


    private final String permission;
    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
