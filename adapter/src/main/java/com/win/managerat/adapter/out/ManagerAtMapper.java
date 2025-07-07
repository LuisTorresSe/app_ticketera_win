package com.win.managerat.adapter.out;

import com.win.managerat.domain.ManagerAt;
import com.win.role.adapter.RoleMapper;
import com.win.role.domain.Role;

public class ManagerAtMapper {


    public static ManagerAt toEntity(ManagerAtJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        Role role = RoleMapper.toEntity(jpaEntity.getRole());

        return new ManagerAt(
                jpaEntity.getId(),
                jpaEntity.getManagerId(),
                jpaEntity.getFullname(),
                role,
                jpaEntity.getEmail(),
                jpaEntity.getPassword()
        );
    }

    public static ManagerAtJpaEntity toJpaEntity(ManagerAt entity) {
        if (entity == null) return null;

        return ManagerAtJpaEntity.builder()
                .id(entity.getId())
                .managerId(entity.getManagerId())
                .fullname(entity.getFullname())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(RoleMapper.toJpaEntity(entity.getRole()))
                .build();
    }

}
