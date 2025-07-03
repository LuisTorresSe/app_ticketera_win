package com.win.ticket.application.port.in.closeTicketUseCase;

import com.win.ticket.domain.TicketStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CloseTicketState {
    private Long managerId;
    private Long ticketId;
    private TicketStatus status;
}
