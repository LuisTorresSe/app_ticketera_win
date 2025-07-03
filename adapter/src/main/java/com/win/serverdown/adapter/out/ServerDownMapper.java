package com.win.serverdown.adapter.out;

import com.win.client.adapter.out.ClientJpaEntity;
import com.win.client.adapter.out.ClientMapper;
import com.win.client.domain.Client;
import com.win.serverdown.domain.ServerDown;
import com.win.subticket.adapter.out.SubticketJpaEntity;

public class ServerDownMapper {

    public static ServerDown toDomain(ServerDownJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        Client client = ClientMapper.toEntity(jpaEntity.getClient());

        return ServerDown.reconstructor(
                jpaEntity.getBreakDownId(),
                client
        );
    }

    public static ServerDownJpaEntity toJpaEntity(ServerDown domainEntity, SubticketJpaEntity subticketJpa) {
        if (domainEntity == null) return null;

        ClientJpaEntity clientJpaEntity = ClientMapper.toJpaEntity(domainEntity.getClient());

        return ServerDownJpaEntity.builder()
                .breakDownId(domainEntity.getBreakDownId())
                .client(clientJpaEntity)
                .subticket(subticketJpa) // se asigna la relación
                .build();
    }
}
