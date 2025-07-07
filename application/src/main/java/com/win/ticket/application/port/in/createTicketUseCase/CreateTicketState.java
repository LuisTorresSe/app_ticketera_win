package com.win.ticket.application.port.in.createTicketUseCase;


import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketStatus;
import com.win.ticket.domain.TicketType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CreateTicketState {
    private final Long ticketId;
    private final String codeTicket;
    private final UUID createManagerId;
    private final TicketType type;
    private final TicketReport report;
    private final Diagnosis diagnosis;
    private final LocalDateTime createAtEvent;
    private final Boolean unavailability;
    private final String nodeAffected;
    private final String oltAffected;
    private final String comment;
    private final TicketStatus status;

    public CreateTicketState(
            Long ticketId,
            String codeTicket,
            UUID createManagerId,
            TicketType type,
            TicketReport report,
            Diagnosis diagnosis,
            LocalDateTime createAtEvent,
            Boolean unavailability,
            String nodeAffected,
            String oltAffected,
            String comment,
            TicketStatus status
    ) {
        this.ticketId = ticketId;
        this.codeTicket = codeTicket;
        this.createManagerId = createManagerId;
        this.type = type;
        this.report = report;
        this.diagnosis = diagnosis;
        this.createAtEvent = createAtEvent;
        this.unavailability = unavailability;
        this.nodeAffected = nodeAffected;
        this.oltAffected = oltAffected;
        this.comment = comment;
        this.status = status;
    }
}

