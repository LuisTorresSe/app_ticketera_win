package com.win.ticket.application.port.in.closeTicketUseCase;

import com.win.ticket.domain.Diagnosis;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CloseTicketCommand {

    private UUID managerId;
    private Long ticketId;

    public CloseTicketCommand(UUID managerId, Long ticketId) {
        this.managerId = managerId;
        this.ticketId = ticketId;
    }

}
