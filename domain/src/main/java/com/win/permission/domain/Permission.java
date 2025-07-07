package com.win.permission.domain;

import lombok.Getter;

@Getter
public class Permission {
    private final Long permissionId;
    private final String permissionName;

    public Permission(Long permissionId, String permissionName) {
        if (permissionId == null || permissionId < 0) throw new IllegalArgumentException("Permission id cannot be negative");
        if (permissionName == null || permissionName.isEmpty()) throw new IllegalArgumentException("Permission name cannot be null or empty");
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }





}
