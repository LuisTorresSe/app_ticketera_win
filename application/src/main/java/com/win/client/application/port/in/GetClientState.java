package com.win.client.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetClientState {
    private Long clientId;
    private String statusAccount;
    private String documentCi;
    private String serialNumber;
    private String orderCode;
    private String portGpon;
    private String descriptionDepartament;
    private String descriptionDistrict;
    private String descriptionBox;
    private String codeBox;
    private String contrata;

    public static GetClientState from(com.win.client.domain.Client client) {
        if (client == null) return null;

        return GetClientState.builder()
                .clientId(client.getClientId())
                .statusAccount(client.getStatus())
                .documentCi(client.getDocumentCi())
                .serialNumber(client.getSerialNumber())
                .orderCode(client.getOrderCode())
                .portGpon(client.getPortGpon())
                .descriptionDepartament(client.getDescriptionDepartament())
                .descriptionDistrict(client.getDescriptionDistrict())
                .descriptionBox(client.getDescriptionBox())
                .codeBox(client.getCodeBoxWin())
                .contrata(client.getContrata())
                .build();
    }
}
