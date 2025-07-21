package com.win.permission.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Permission {
    private final Long id;
    private final UUID permissionId;
    private final String permissionName;

    public Permission(Long id, UUID permissionId, String permissionName) {
        if (permissionId == null ) throw new IllegalArgumentException("Permission id cannot be negative");
        if (permissionName == null || permissionName.isEmpty()) throw new IllegalArgumentException("Permission name cannot be null or empty");
        this.id = id;
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }





}
