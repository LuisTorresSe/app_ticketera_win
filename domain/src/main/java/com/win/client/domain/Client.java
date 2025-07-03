package com.win.client.domain;


import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
public class Client {
    private Long clientId;
    private String typeDocument;
    private String documentCi;
    private String serialNumber;
    private String portGpon;
    private String olt;
    private String cto;
    private String orderCode;
    private String status;
    private String descriptionBox;
    private String contrata;
    private String descriptionTypeBox;

    private String codeBoxWin;
    private String codeBoxOpti;
    private String descriptionPlan;
    private String descriptionLatitudeBox;
    private String descriptionLongitudeBox;
    private String descriptionDirectionBox;
    private String descriptionDirectionClient;
    private String descriptionDepartament;
    private String descriptionProvince;
    private String descriptionUbigeo;
    private String descriptionDistrict;
    private String descriptionProvinceSite;
    private String descriptionDepartamentSite;
    public static Client reconstructor(Long clientId,
                                       String typeDocument,
                                       String documentCi,
                                       String serialNumber,
                                       String portGpon,
                                       String olt,
                                       String cto,
                                       String orderCode,
                                       String status,
                                       String descriptionBox,
                                       String contrata,
                                       String descriptionTypeBox,
                                       String codeBoxWin,
                                       String codeBoxOpti,
                                       String descriptionPlan,
                                       String descriptionLatitudeBox,
                                       String descriptionLongitudeBox,
                                       String descriptionDirectionBox,
                                       String descriptionDirectionClient,
                                       String descriptionDepartament,
                                       String descriptionProvince,
                                       String descriptionUbigeo,
                                       String descriptionDistrict,
                                       String descriptionProvinceSite,
                                       String descriptionDepartamentSite) {

        Client client = new Client();
        client.clientId = clientId;
        client.typeDocument = typeDocument;
        client.documentCi = documentCi;
        client.serialNumber = serialNumber;
        client.portGpon = portGpon;
        client.olt = olt;
        client.cto = cto;
        client.orderCode = orderCode;
        client.status = status;
        client.descriptionBox = descriptionBox;
        client.contrata = contrata;
        client.descriptionTypeBox = descriptionTypeBox;

        client.codeBoxWin = codeBoxWin;
        client.codeBoxOpti = codeBoxOpti;
        client.descriptionPlan = descriptionPlan;
        client.descriptionLatitudeBox = descriptionLatitudeBox;
        client.descriptionLongitudeBox = descriptionLongitudeBox;
        client.descriptionDirectionBox = descriptionDirectionBox;
        client.descriptionDirectionClient = descriptionDirectionClient;
        client.descriptionDepartament = descriptionDepartament;
        client.descriptionProvince = descriptionProvince;
        client.descriptionUbigeo = descriptionUbigeo;
        client.descriptionDistrict = descriptionDistrict;
        client.descriptionProvinceSite = descriptionProvinceSite;
        client.descriptionDepartamentSite = descriptionDepartamentSite;


        return client;
    }

}
