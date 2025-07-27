package com.win.ticket.application.port.in;

import com.win.managerat.application.port.in.ManagerAtState;
import com.win.subticket.application.port.in.SubticketState;
import com.win.ticket.domain.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;


public record GetTicketState(
        Long ticketId,
        String codeTicket,
        ManagerAtState managerAtAperture,
        ManagerAtState managerAtClose,
        TicketStatus statusTicket,
        TicketType ticketType,
        TicketReport ticketReport,
        Diagnosis diagnosis,
        LocalDateTime createdAt,
        LocalDateTime closedAt,
        boolean unavailability,
        String nodeAffected,
        String oltAffected,
        String comment,
        String assignTo,
        Set<SubticketState> subtickets,
        EmailStatus emailStatus
)
{ }
