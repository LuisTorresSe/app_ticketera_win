package com.win.ticket.application.port.in;

import com.win.ticket.domain.Ticket;

import java.util.List;

public interface GetAllTicketUseCase {

    List<GetTicketState> execute();

}
