package com.win.subticket.domain;

import com.win.managerat.domain.ManagerAt;
import com.win.serverdown.domain.ServerDown;
import com.win.ticket.domain.Ticket;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Subticket {
    private Long subticketId;
    private String subticketCode; // NECESARIO CREATE
    private ManagerAt createManagerAt; // NECESARIO CREATE
    private ManagerAt closeManagerAt;

    private LocalDateTime createEventAt; // AUTOMATICO CREATE
    private LocalDateTime closeEventAt;

    private LocalDateTime dateReportPext; // NECESARIO CREATE

    private LocalDateTime dateStopLabores;
    private LocalDateTime dateStartLabores;

    private Duration timeStopWork;
    private Duration timeEvent;
    private Duration timeAffected;
    private Duration timeSolutionsEventPext;
    private Duration timeReportPext;
    private Duration fallTime;

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
    private String responsable; // esto lo pone el asesor al final
    private String statusPosSLA;


    private Set<ServerDown> serverDowns; // nullO SE BUSCA POR LA CTO A LOS CLIENTE Y LUEGO SE ACEPTA SI AGREGA O NO


    public static Subticket create(
            String subticketCode,
            ManagerAt createManagerAt,
            LocalDateTime createEventAt,
            LocalDateTime dateReportPext,
            Integer card,
            Integer port,
            String ctoAffected,
            String commentary,
            String city,
            Set<ServerDown> serverdowns
    ) {
        Subticket subticket = new Subticket();
        subticket.subticketCode = subticketCode;
        subticket.createManagerAt = createManagerAt;
        subticket.createEventAt = createEventAt;
        subticket.dateReportPext = dateReportPext;
        subticket.card = card;
        subticket.port = port;
        subticket.ctoAffected = ctoAffected;
        subticket.commentary = commentary;
        subticket.statusSubticket = SubticketStatus.PENDIENTE;
        subticket.serverDowns = new HashSet<>(serverdowns);
        subticket.city = city;
        return subticket;
    }

    public static Subticket reconstructor(Integer card,
                                          String causeProblem,
                                          String city,
                                          LocalDateTime closeEventAt,
                                          ManagerAt closeManagerAt,
                                          String commentary,
                                          Integer countClient,
                                          LocalDateTime createEventAt,
                                          ManagerAt createManagerAt,
                                          String ctoAffected,
                                          LocalDateTime dateReportPext,
                                          LocalDateTime dateStartLabores,
                                          LocalDateTime dateStopLabores,
                                          Integer port,
                                          String responsable,
                                          Set<ServerDown> servsDowns,
                                          String solutions,
                                          SubticketStatus statusSubticket,
                                          String subticketCode,
                                          Long subticketId,
                                          Duration timeAffected,
                                          Duration timeEvent,
                                          Duration timeReportPext,
                                          Duration timeSolutionsEventPext,
                                          Duration timeStopWork) {

        Subticket subticket = new Subticket(); // se crea una instancia
        subticket.card = card;
        subticket.causeProblem = causeProblem;
        subticket.city = city;
        subticket.closeEventAt = closeEventAt;
        subticket.closeManagerAt = closeManagerAt;
        subticket.commentary = commentary;
        subticket.countClient = countClient;
        subticket.createEventAt = createEventAt;
        subticket.createManagerAt = createManagerAt;
        subticket.ctoAffected = ctoAffected;
        subticket.dateReportPext = dateReportPext;
        subticket.dateStartLabores = dateStartLabores;
        subticket.dateStopLabores = dateStopLabores;
        subticket.port = port;
        subticket.responsable = responsable;
        subticket.serverDowns = servsDowns;
        subticket.solutions = solutions;
        subticket.statusSubticket = statusSubticket;
        subticket.subticketCode = subticketCode;
        subticket.subticketId = subticketId;
        subticket.timeAffected = timeAffected;
        subticket.timeEvent = timeEvent;
        subticket.timeReportPext = timeReportPext;
        subticket.timeSolutionsEventPext = timeSolutionsEventPext;
        subticket.timeStopWork = timeStopWork;

        return subticket;
    }

    public void setDurationTimeStopWork() {
        if (dateStartLabores != null && dateStopLabores != null) {
            this.timeStopWork = Duration.between(dateStartLabores, dateStopLabores);
        } else {
            this.timeStopWork = null;
        }
    }


    public void setDurationTimeEvent() {
        if (createEventAt != null && closeEventAt != null) {
            this.timeEvent = Duration.between(createEventAt, closeEventAt);
        } else {
            this.timeEvent = null;
        }
    }

    public void setDurationtimeSolutionsEventPext() {
        if (dateReportPext != null && closeEventAt != null) {
            this.timeSolutionsEventPext = Duration.between(dateReportPext, closeEventAt);
        } else {
            this.timeSolutionsEventPext = null;
        }
    }

    public void setDurationTimeReportPext() {
        if (createEventAt != null && dateReportPext != null) {
            this.timeReportPext = Duration.between(createEventAt, dateReportPext);
        } else {
            this.timeReportPext = null;
        }
    }

    public void setDurationFallTime() {
        if(createEventAt != null) {
            LocalDateTime now = LocalDateTime.now();
            this.fallTime = Duration.between(createEventAt, now);
        }
    }

    public void addServerDown(ServerDown serverDown) {
        if (serverDown == null) {
            throw new IllegalArgumentException("Subticket cannot be null");
        }
        if(SubticketStatus.SOLUCIONADO.equals(statusSubticket) ){
            throw new IllegalArgumentException("Subticket is already Solucionado");
        }
        serverDowns.add(serverDown);
    }

    public void setCounClient(int countClient) {
        if(countClient < 0){
            throw new IllegalArgumentException("Subticket count cannot be negative");
        }
        this.countClient = countClient;
    }

    public void close(ManagerAt managerAt){
        this.closeManagerAt = managerAt;
        this.statusSubticket = SubticketStatus.SOLUCIONADO;
    }

    public void assignSolution(String solution) {
        this.solutions = solution;
    }

    public void assignCloseEventAt(LocalDateTime date) {
        this.closeEventAt = date;
    }

    public void assignBadPraxis() {
        this.badPraxis = true;
    }

    public void assignCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void assignCauseProblem(String causeProblem) {
        this.causeProblem = causeProblem;
    }

    public void assingResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void assingPostSLA(String postSLA) {
        this.statusPosSLA = postSLA;
    }
}
