package com.win.ticket.application.port.in.closeTicketUseCase;

public interface CloseTicketUseCase {

    CloseTicketState execute (CloseTicketCommand command);
}
