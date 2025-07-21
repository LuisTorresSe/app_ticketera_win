package com.win.ticket.application.port.in.createTicketUseCase;

import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CreateTicketCommand {

    private final UUID managerId;
    private final TicketType type;
    private final TicketReport report;
    private final Diagnosis diagnosis;
    private final LocalDateTime createAtEvent;
    private final String assignTo;
    private final Boolean unavailability;
    private final String nodeAffected;
    private final String oltAffected;
    private final String comment;

    public CreateTicketCommand(TicketType type,
                               TicketReport report,
                               Diagnosis diagnosis,
                               LocalDateTime createAtEvent,
                               Boolean unavailability,
                               String assignTo,
                               String nodeAffected,
                               String oltAffected,
                               UUID managerId,
                               String comment

    ) {
        this.type = type;
        this.managerId= managerId;
        this.report = report;
        this.diagnosis = diagnosis;
        this.assignTo = assignTo;
        this.createAtEvent = createAtEvent;
        this.unavailability = unavailability;
        this.nodeAffected = nodeAffected;
        this.oltAffected = oltAffected;
        this.comment = comment;
    }
}
