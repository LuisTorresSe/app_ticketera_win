package com.win.ticket.application.port.in.closeTicketUseCase;

import com.win.ticket.domain.Diagnosis;
import lombok.Getter;

@Getter
public class CloseTicketCommand {

    private Long managerId;
    private Long ticketId;

    public CloseTicketCommand(Long managerId, Long ticketId) {
        this.managerId = managerId;
        this.ticketId = ticketId;
    }

}
