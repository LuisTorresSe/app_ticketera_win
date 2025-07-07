package com.win.role.domain;

import com.win.permission.domain.Permission;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class Role {
    private final UUID roleId;
    private final String roleName;
    private Set<Permission> permissions;
    public Role(UUID roleId, String roleName, Set<Permission> permissions) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissions = new HashSet<>(permissions);
    }

    public boolean hasPermission(String permissionName) {
        return permissions.stream().anyMatch(permission-> permission.getPermissionName().equals(permissionName));
    }

    public void addPermission(Permission permission) {
        if(permission == null) {
            throw new IllegalArgumentException("Permission cannot be null.");
        }
        permissions.add(permission);
    }

    public Set<Permission> getPermissions() {
        return Collections.unmodifiableSet(permissions);
    }

    public void removePermission(Permission permission) {
        if(permission == null) {
            throw new IllegalArgumentException("Permission cannot be null.");
        }
        permissions.remove(permission);
    }


}
