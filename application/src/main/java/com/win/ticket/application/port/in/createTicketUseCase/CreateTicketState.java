package com.win.ticket.application.port.in.createTicketUseCase;

import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketStatus;
import com.win.ticket.domain.TicketType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicketState(
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
        TicketStatus status,
        String assignTo) {
}

