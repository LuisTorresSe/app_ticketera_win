package com.win.ticket.adapter.in;

import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketType;

import java.time.LocalDateTime;

public record RequestCreateTicketDto(Long managerId,
                                     TicketType type,
                                     TicketReport report,
                                     Diagnosis diagnosis,
                                     LocalDateTime createAtEvent,
                                     Boolean unavailability,
                                     String nodeAffected,
                                     String oltAffected,
                                     String comment) {
}
