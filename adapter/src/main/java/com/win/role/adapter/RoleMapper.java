package com.win.role.adapter;

import com.win.permission.adapter.PermissionMapper;
import com.win.permission.domain.Permission;
import com.win.role.domain.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {

    public static Role toEntity(RoleJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        Set<Permission> permissions = jpaEntity.getPermissions()
                .stream()
                .map(PermissionMapper::toEntity)
                .collect(Collectors.toSet());

        return new Role(
                jpaEntity.getRoleId(),
                jpaEntity.getRoleName(),
                permissions
        );
    }

    public static RoleJpaEntity toJpaEntity(Role entity) {
        if (entity == null) return null;

        return RoleJpaEntity.builder()
                .roleId(entity.getRoleId())
                .roleName(entity.getRoleName())
                .permissions(
                        entity.getPermissions()
                                .stream()
                                .map(PermissionMapper::toJpaEntity)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
