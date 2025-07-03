package com.win.managerat.adapter.out;

import com.win.managerat.domain.ManagerAt;

public class ManagerAtMapper {

   public static ManagerAt toEntity(ManagerAtJpaEntity jpaEntity) {
       if (jpaEntity == null) {
           return null;
       }

        ManagerAt entity  = ManagerAt.reconstructor(
                jpaEntity.getManagerId(),
                jpaEntity.getName()
        );
        return entity;
    };

   public  static ManagerAtJpaEntity toJpaEntity(ManagerAt entity) {
       if (entity == null) {
           return null;
       }
        ManagerAtJpaEntity jpaEntity = ManagerAtJpaEntity.builder()
                .managerId(entity.getManagerId())
                .name(entity.getName())
                .build();
        return jpaEntity;
    }


}
