package com.win.ticket.application.port.in;

import com.win.managerat.application.port.in.ManagerAtState;
import com.win.subticket.application.port.in.SubticketState;
import com.win.subticket.domain.Subticket;
import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketStatus;
import com.win.ticket.domain.TicketType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class GetTicketState {

       private final Long ticketId;
       private final String codeTicket;
       private final ManagerAtState managerAtAperture;
       private final ManagerAtState managerAtClose;
       private final TicketStatus statusTicket;
       private final TicketType ticketType;
       private final TicketReport ticketReport;
       private final Diagnosis diagnosis;
       private final LocalDateTime createdAt;
       private final LocalDateTime closedAt;
       private final boolean unavailability;
       private final String nodeAffected;
       private final String oltAffected;
       private final String comment;
       private final Set<SubticketState> subtickets;

    public GetTicketState(Long ticketId, String codeTicket,
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
                          Set<SubticketState> subtickets) {
        this.ticketId = ticketId;
        this.codeTicket = codeTicket;
        this.managerAtAperture = managerAtAperture;
        this.managerAtClose = managerAtClose;
        this.statusTicket = statusTicket;
        this.ticketType = ticketType;
        this.ticketReport = ticketReport;
        this.diagnosis = diagnosis;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
        this.unavailability = unavailability;
        this.nodeAffected = nodeAffected;
        this.oltAffected = oltAffected;
        this.comment = comment;
        this.subtickets = subtickets;
    }
}
