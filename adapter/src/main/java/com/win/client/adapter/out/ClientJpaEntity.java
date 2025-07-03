package com.win.client.adapter.out;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(length = 400)
    private String typeDocument;

    @Column(length = 400)
    private String documentCi;

    @Column(length = 400)
    private String serialNumber;

    @Column(length = 400)
    private String portGpon;

    @Column(length = 400)
    private String olt;

    @Column(length = 400)
    private String cto;

    @Column(length = 400)
    private String orderCode;

    @Column(length = 400)
    private String status;

    @Column(length = 400)
    private String descriptionBox;

    @Column(length = 400)
    private String contrata;

    @Column(length = 400)
    private String descriptionTypeBox;

    @Column(length = 400)
    private String codeBoxWin;

    @Column(length = 400)
    private String codeBoxOpti;

    @Column(length = 400)
    private String descriptionPlan;

    @Column(length = 400)
    private String descriptionLatitudeBox;

    @Column(length = 400)
    private String descriptionLongitudeBox;

    @Column(length = 400)
    private String descriptionDirectionBox;

    @Column(length = 400)
    private String descriptionDirectionClient;

    @Column(length = 400)
    private String descriptionDepartament;

    @Column(length = 400)
    private String descriptionProvince;

    @Column(length = 400)
    private String descriptionUbigeo;

    @Column(length = 400)
    private String descriptionDistrict;

    @Column(length = 400)
    private String descriptionProvinceSite;

    @Column(length = 400)
    private String descriptionDepartamentSite;
}
