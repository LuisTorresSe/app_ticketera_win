package com.win.ticket.application.port.in.closeTicketUseCase;

import com.win.ticket.domain.TicketStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CloseTicketState {
    private UUID managerId;
    private Long ticketId;
    private TicketStatus status;
}
