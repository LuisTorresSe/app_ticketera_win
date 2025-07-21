package com.win.ticket.application.port.in;

import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketType;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdatedTicketCommand
        (
                Long ticketId,
                 UUID managerId,
                 TicketType type,
                 TicketReport report,
                 Diagnosis diagnosis,
                 LocalDateTime createAtEvent,
                 Boolean unavailability,
                 String nodeAffected,
                 String oltAffected
        ){


}
