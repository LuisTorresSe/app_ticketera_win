package com.win.ticket.application.port.in.createTicketUseCase;

import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CreateTicketCommand {

    private UUID managerId;
    private TicketType type;
    private TicketReport report;
    private Diagnosis diagnosis;
    private LocalDateTime createAtEvent;
    private Boolean unavailability;
    private String nodeAffected;
    private String oltAffected;
    private String comment;

    public CreateTicketCommand(TicketType type,
                               TicketReport report,
                               Diagnosis diagnosis,
                               LocalDateTime createAtEvent,
                               Boolean unavailability,
                               String nodeAffected,
                               String oltAffected,
                               UUID managerId,
                               String comment) {
        this.type = type;
        this.managerId= managerId;
        this.report = report;
        this.diagnosis = diagnosis;
        this.createAtEvent = createAtEvent;
        this.unavailability = unavailability;
        this.nodeAffected = nodeAffected;
        this.oltAffected = oltAffected;
        this.comment = comment;
    }
}
