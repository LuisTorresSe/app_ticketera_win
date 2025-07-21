package com.win.permission.adapter;

import com.win.permission.domain.Permission;

public class PermissionMapper {

    public static Permission toEntity(PermissionJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return new Permission(
                jpaEntity.getId(),
                jpaEntity.getPermissionId(),
                jpaEntity.getPermissionName());
    }

    public static PermissionJpaEntity toJpaEntity(Permission entity) {
        if (entity == null) return null;

        return PermissionJpaEntity.builder()
                .id(entity.getId())
                .permissionId(entity.getPermissionId())
                .permissionName(entity.getPermissionName())
                .build();
    }
}
