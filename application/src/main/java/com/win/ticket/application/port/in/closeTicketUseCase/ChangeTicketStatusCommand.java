package com.win.ticket.application.port.in.closeTicketUseCase;

import com.win.ticket.domain.TicketStatus;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

@Getter
public class ChangeTicketStatusCommand {

    private UUID managerId;
    private Long ticketId;
    private TicketStatus status;
    private Optional<String> reasonForPause;

    public ChangeTicketStatusCommand(UUID managerId,
                                     Long ticketId,
                                     TicketStatus status,
                                     Optional<String> reasonForPause) {
        this.managerId = managerId;
        this.ticketId = ticketId;
        this.status = status;
        reasonForPause.ifPresent(reasonForPause1 -> this.reasonForPause = Optional.of(reasonForPause1));

    }
}
