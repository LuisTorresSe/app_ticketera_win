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
}
