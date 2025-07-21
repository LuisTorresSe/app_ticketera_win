package com.win.ticket.domain;

import com.win.managerat.domain.ManagerAt;

import com.win.subticket.domain.Subticket;
import com.win.subticket.domain.SubticketStatus;
import com.win.ticket.domain.exception.CannotCloseTicketException;
import com.win.ticket.domain.exception.InvalidDateException;
import com.win.ticket.domain.exception.TicketAlreadyCloseException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ticket {

    private Long ticketId;
    private String codeTicket;

    private ManagerAt managerAtAperture;
    private ManagerAt managerAtClose;
    private TicketStatus statusTicket;

    private TicketType type;
    private TicketReport report;
    private Diagnosis diagnosis;

    private LocalDateTime createAtEvent;
    private LocalDateTime closeAtEvent;
    private Boolean unavailability;
    private String nodeAffected;
    private String oltAffected;
    private String comment;
    private String assignTo;
    private Optional<String> reasonForPause;
    private LocalDateTime statusChangedAt;
    private ManagerAt managerAtChangeStatus;
    private Set<Subticket> subTickets;

    public static Ticket createTicket(
            TicketType type,
            TicketReport report,
            Diagnosis diagnosis,
            ManagerAt managerAtAperture,
            LocalDateTime createAtEvent,
            Boolean unavailability,
            String nodeAffected,
            String oltAffected,
            String comment,
            String assignTo
    ) {
        Ticket newTicket = new Ticket();
        newTicket.type = type;
        newTicket.report = report;
        newTicket.diagnosis = diagnosis;
        newTicket.createAtEvent = createAtEvent;
        newTicket.unavailability = unavailability;
        newTicket.nodeAffected = nodeAffected;
        newTicket.oltAffected = oltAffected;
        newTicket.comment = comment;
        newTicket.managerAtAperture = managerAtAperture;
        newTicket.statusTicket = TicketStatus.PENDIENTE; // Asignar un estado inicial
        newTicket.subTickets = new HashSet<>(); // Inicializar col
        newTicket.assignTo = assignTo;
        newTicket.verifyEventDate(createAtEvent);
        return newTicket;
    }

    public void update(
             TicketType type,
             ManagerAt managerAtAperture,
             TicketReport report,
             Diagnosis diagnosis,
             LocalDateTime createAtEvent,
             Boolean unavailability,
             String nodeAffected,
             String oltAffected
    ) {
        if (type != null) this.type = type;
        if (managerAtAperture != null) this.managerAtAperture = managerAtAperture;
        if (report != null) this.report = report;
        if (diagnosis != null) this.diagnosis = diagnosis;
        if (createAtEvent != null) this.createAtEvent = createAtEvent;
        if (unavailability != null) this.unavailability = unavailability;
        if (nodeAffected != null) this.nodeAffected = nodeAffected;
        if (oltAffected != null) this.oltAffected = oltAffected;
    }

    public static Ticket reconstructor(
            Long ticketId,
            String codeTicket,
            TicketType type,
            TicketReport report,
            Diagnosis diagnosis,
            ManagerAt managerAtAperture,
            ManagerAt managerAtClose,
            TicketStatus statusTicket,
            LocalDateTime createAtEvent,
            LocalDateTime closeAtEvent,
            Boolean unavailability,
            String nodeAffected,
            String oltAffected,
            String comment,
            String assignTo,
            Set<Subticket> subTickets
    ) {

        Ticket newTicket = new Ticket();
        newTicket.ticketId = ticketId;
        newTicket.codeTicket = codeTicket;
        newTicket.type = type;
        newTicket.report = report;
        newTicket.diagnosis = diagnosis;
        newTicket.managerAtAperture = managerAtAperture;
        newTicket.managerAtClose = managerAtClose;
        newTicket.statusTicket = statusTicket;
        newTicket.createAtEvent = createAtEvent;
        newTicket.closeAtEvent = closeAtEvent;
        newTicket.unavailability = unavailability;
        newTicket.nodeAffected = nodeAffected;
        newTicket.oltAffected = oltAffected;
        newTicket.comment = comment;
        newTicket.assignTo = assignTo;
        newTicket.subTickets = subTickets != null ? new HashSet<>(subTickets) : new HashSet<>();
        return newTicket;
    }

    private void verifyEventDate(LocalDateTime createAtEvent) {
        if (createAtEvent == null) {
            throw new InvalidDateException("Event date cannot be null");
        }
        if (createAtEvent.isAfter(LocalDateTime.now())) {
            throw new InvalidDateException("Event date cannot be in the future");
        }
    }

    public void changeCreateAtEvent() {
        this.createAtEvent = subTickets.stream()
                .map(Subticket::getCreateEventAt)
                .min(LocalDateTime::compareTo)
                .orElse(this.createAtEvent);
    }


    public void generateCodeTicket() {
        if (this.ticketId == null) return;
        String chartInitialType = this.type.name().substring(0, 1).toUpperCase();
        this.codeTicket = String.format("W-CR-%05d-%s", this.ticketId, chartInitialType);
    }

    public void changeStatus(ManagerAt managerAt, TicketStatus newStatus, Optional<String> reasonForPause) {
        if (managerAt == null) {
            throw new CannotCloseTicketException("Manager at close cannot be null");
        }

        if (this.isClosed()) {
            throw new TicketAlreadyCloseException("Ticket is already closed");
        }

        if(newStatus.equals(TicketStatus.EN_EJECUCION)) {
            this.statusTicket = newStatus;
            this.statusChangedAt = LocalDateTime.now();

        }

        if(newStatus.equals(TicketStatus.EN_PAUSA)) {
            this.statusTicket = newStatus;
            if(reasonForPause.isEmpty()) {
                throw new InvalidDateException("Reason for pause cannot be null");
            }
            this.reasonForPause = reasonForPause;
            this.statusChangedAt = LocalDateTime.now();
        }
        if(newStatus.equals(TicketStatus.PENDIENTE)) {
            this.statusTicket = newStatus;
            this.statusChangedAt = LocalDateTime.now();
        }

        if(newStatus.equals(TicketStatus.SOLUCIONADO)) {
            boolean hasPendingSubtickets = this.subTickets.stream()
                    .anyMatch(subticket -> SubticketStatus.PENDIENTE.equals(subticket.getStatusSubticket()));

            if (hasPendingSubtickets) {
                throw new CannotCloseTicketException("Cannot close ticket with pending subtickets");
            }

            this.statusTicket = newStatus;
            this.managerAtClose = managerAt;
            this.closeAtEvent = LocalDateTime.now();
        }

    }

    public void assignReport(TicketReport report) {
        if (this.isClosed()) {
            throw new TicketAlreadyCloseException("Cannot assign report to closed ticket");
        }
        this.report = report;
    }

    public void assignDiagnosis(Diagnosis diagnosis) {
        if (this.isClosed()) {
            throw new TicketAlreadyCloseException("Cannot assign diagnosis to closed ticket");
        }
        this.diagnosis = diagnosis;
    }

    public void addSubticket(Subticket subticket) {
        if (subticket == null) {
            throw new IllegalArgumentException("Subticket cannot be null");
        }
        if (this.isClosed()) {
            throw new IllegalStateException("Cannot add subticket to closed ticket");
        }
        this.subTickets.add(subticket);
    }

    public void removeSubticket(Subticket subticket) {
        if (subticket == null) {
            throw new IllegalArgumentException("Subticket cannot be null");
        }
        this.subTickets.remove(subticket);
    }

    public void addComment(String comment) {
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Comment cannot be null or empty");
        }
        this.comment = comment;
    }

    public boolean isClosed() {

        return TicketStatus.SOLUCIONADO.equals(this.statusTicket);
    }


}
