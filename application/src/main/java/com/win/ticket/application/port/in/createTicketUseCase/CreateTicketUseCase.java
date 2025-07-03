package com.win.ticket.application.port.in.createTicketUseCase;

public interface CreateTicketUseCase {

    CreateTicketState execute (CreateTicketCommand command);
}
