package com.genspark.backend.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.genspark.backend.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(USER_ST_CANCEL,USER_ST_CONFIRM,USER_VIEW,USER_EDIT,USER_DELETE)),
    EMPLOY(Sets.newHashSet(EMPLOY_CREATE,ADMIN_EDIT,EMPLOY_DELETE,EMPLOY_VIEW,EMPLOY_ST_CONFIRM,ADMIN_ST_CANCEL,EMPLOY_ST_ARRIVED,EMPLOY_ST_COMPLETE)),
    ADMIN(Sets.newHashSet(ADMIN_CREATE,ADMIN_EDIT,ADMIN_DELETE,ADMIN_VIEW,ADMIN_ST_CONFIRM,ADMIN_ST_CANCEL,ADMIN_ST_ARRIVED,ADMIN_ST_COMPLETE));
    private final Set<ApplicationUserPermission> permissions;

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    // Authorities
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
