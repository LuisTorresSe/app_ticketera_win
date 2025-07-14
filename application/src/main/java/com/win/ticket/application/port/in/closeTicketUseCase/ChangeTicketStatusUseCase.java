package com.win.ticket.application.port.in.closeTicketUseCase;

public interface ChangeTicketStatusUseCase {

    ChangeTicketStatusState execute (ChangeTicketStatusCommand command);
}
