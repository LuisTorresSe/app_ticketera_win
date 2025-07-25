package com.win.subticket.application.port.in;

import com.win.managerat.application.port.in.ManagerAtState;
import com.win.managerat.domain.ManagerAt;
import com.win.serverdown.application.port.in.GetServerDownState;
import com.win.serverdown.domain.ServerDown;
import com.win.subticket.domain.Subticket;
import com.win.subticket.domain.SubticketStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class SubticketState {

    private Long subticketId;
    private String subticketCode; // NECESARIO CREATE
    private ManagerAtState createManagerAt; // NECESARIO CREATE
    private ManagerAtState closeManagerAt;

    private LocalDateTime createEventAt; // AUTOMATICO CREATE
    private LocalDateTime closeEventAt;

    private LocalDateTime dateReportPext; // NECESARIO CREATE

    private LocalDateTime dateStopLabores;
    private LocalDateTime dateStartLabores;

    private Integer card; // NECESARIO
    private Integer port; // NECESARIO
    private String ctoAffected; // NECESARIO
    private String city; // AUTOMATICO POR LA CTO
    private String causeProblem; // al momento del cierre
    private Integer countClient; // automaticamente por la CTO
    private Boolean badPraxis; // al finalizar cierre del subticket
    private String solutions; // al finalizar cierre del subticket
    private SubticketStatus statusSubticket; // AUTOMATICO PENDIENTE AL CREAR
    private String commentary; // NULO AL CREAR
    private String responsable;

    private List<GetServerDownState> serverdowns;
    public static SubticketState from(Subticket subticket) {
        return SubticketState.builder()
                .subticketId(subticket.getSubticketId())
                .subticketCode(subticket.getSubticketCode())
                .createManagerAt(ManagerAtState.from(subticket.getCreateManagerAt()))
                .closeManagerAt(subticket.getCloseManagerAt() != null ? ManagerAtState.from(subticket.getCloseManagerAt()) : null)
                .createEventAt(subticket.getCreateEventAt())
                .closeEventAt(subticket.getCloseEventAt())
                .dateReportPext(subticket.getDateReportPext())
                .dateStopLabores(subticket.getDateStopLabores())
                .dateStartLabores(subticket.getDateStartLabores())
                .card(subticket.getCard())
                .port(subticket.getPort())
                .ctoAffected(subticket.getCtoAffected())
                .city(subticket.getCity())
                .causeProblem(subticket.getCauseProblem())
                .countClient(subticket.getCountClient())
                .badPraxis(subticket.getBadPraxis())
                .solutions(subticket.getSolutions())
                .statusSubticket(subticket.getStatusSubticket())
                .commentary(subticket.getCommentary())
                .responsable(subticket.getResponsable())
                .serverdowns(subticket.getServerDowns().stream()
                        .map(
                                GetServerDownState::from)
                        .toList())
                .build();
    }

}
