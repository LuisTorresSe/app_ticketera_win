package com.win.ticket.application.service;

import com.win.managerat.application.exception.NotFoundManagerAtException;
import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;
import com.win.ticket.application.exception.NotFoundTicketException;
import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketCommand;
import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketState;
import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketUseCase;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;

public class CloseTicketService implements CloseTicketUseCase {
    private final TicketPort ticketPort;
    private final ManagerPort managerPort;
    public CloseTicketService(TicketPort ticketPort, ManagerPort managerPort) {
        this.ticketPort = ticketPort;
        this.managerPort = managerPort;
    }

    @Override
    public CloseTicketState execute(CloseTicketCommand command) {
        Ticket ticket = ticketPort.findById(command.getTicketId()).orElseThrow(
                () -> new NotFoundTicketException("Ticket not found")
        );

        ManagerAt manager = managerPort.findById(command.getManagerId()).orElseThrow(
                () -> new NotFoundManagerAtException("Manager not found")
        );
        ticket.closeTicket(manager);

        Ticket update  = ticketPort.save(ticket);

        return CloseTicketState.builder()
                .ticketId(update.getTicketId())
                .managerId(update.getManagerAtClose().getManagerId())
                .status(update.getStatusTicket())
                .build();
    }
}
