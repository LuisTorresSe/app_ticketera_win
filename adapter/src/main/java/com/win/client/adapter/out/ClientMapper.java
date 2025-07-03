package com.win.client.adapter.out;

import com.win.client.domain.Client;

public class ClientMapper {


    public static Client toEntity(ClientJpaEntity jpaEntity) {
        Client client = Client.reconstructor(
                jpaEntity.getClientId(),
                jpaEntity.getTypeDocument(),
                jpaEntity.getDocumentCi(),
                jpaEntity.getSerialNumber(),
                jpaEntity.getPortGpon(),
                jpaEntity.getOlt(),
                jpaEntity.getCto(),
                jpaEntity.getOrderCode(),
                jpaEntity.getStatus(),
                jpaEntity.getDescriptionBox(),
                jpaEntity.getContrata(),
                jpaEntity.getDescriptionTypeBox(),
                jpaEntity.getCodeBoxWin(),
                jpaEntity.getCodeBoxOpti(),
                jpaEntity.getDescriptionPlan(),
                jpaEntity.getDescriptionLatitudeBox(),
                jpaEntity.getDescriptionLongitudeBox(),
                jpaEntity.getDescriptionDirectionBox(),
                jpaEntity.getDescriptionDirectionClient(),
                jpaEntity.getDescriptionDepartament(),
                jpaEntity.getDescriptionProvince(),
                jpaEntity.getDescriptionUbigeo(),
                jpaEntity.getDescriptionDistrict(),
                jpaEntity.getDescriptionProvinceSite(),
                jpaEntity.getDescriptionDepartamentSite()
        );
        return client;
    }

    // Método para convertir de Client (Entity) a ClientJpaEntity
    public static ClientJpaEntity toJpaEntity(Client client) {
        ClientJpaEntity jpaEntity = new ClientJpaEntity(
                client.getClientId(),
                client.getTypeDocument(),
                client.getDocumentCi(),
                client.getSerialNumber(),
                client.getPortGpon(),
                client.getOlt(),
                client.getCto(),
                client.getOrderCode(),
                client.getStatus(),
                client.getDescriptionBox(),
                client.getContrata(),
                client.getDescriptionTypeBox(),
                client.getCodeBoxWin(),
                client.getCodeBoxOpti(),
                client.getDescriptionPlan(),
                client.getDescriptionLatitudeBox(),
                client.getDescriptionLongitudeBox(),
                client.getDescriptionDirectionBox(),
                client.getDescriptionDirectionClient(),
                client.getDescriptionDepartament(),
                client.getDescriptionProvince(),
                client.getDescriptionUbigeo(),
                client.getDescriptionDistrict(),
                client.getDescriptionProvinceSite(),
                client.getDescriptionDepartamentSite()
        );
        return jpaEntity;
    }
}