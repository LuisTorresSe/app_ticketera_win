package com.win.ticket.application.port.in;

public interface UpdatedTicketUseCase {
    GetTicketState execute(UpdatedTicketCommand command);
}
