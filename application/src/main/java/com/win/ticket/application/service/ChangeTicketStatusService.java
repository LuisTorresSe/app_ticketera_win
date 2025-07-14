package com.win.ticket.application.service;

import com.win.managerat.application.exception.NotFoundManagerAtException;
import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;
import com.win.ticket.application.exception.NotFoundTicketException;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusCommand;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusState;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusUseCase;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;

public class ChangeTicketStatusService implements ChangeTicketStatusUseCase {
    private final TicketPort ticketPort;
    private final ManagerPort managerPort;
    public ChangeTicketStatusService(TicketPort ticketPort, ManagerPort managerPort) {
        this.ticketPort = ticketPort;
        this.managerPort = managerPort;
    }

    @Override
    public ChangeTicketStatusState execute(ChangeTicketStatusCommand command) {
        Ticket ticket = ticketPort.findById(command.getTicketId()).orElseThrow(
                () -> new NotFoundTicketException("Ticket not found")
        );

        ManagerAt manager = managerPort.findById(command.getManagerId()).orElseThrow(
                () -> new NotFoundManagerAtException("Manager not found")
        );


        ticket.changeStatus(manager, command.getStatus(), command.getReasonForPause());

        Ticket update  = ticketPort.save(ticket);

        return ChangeTicketStatusState.builder()
                .ticketId(update.getTicketId())
                .managerId(manager.getManagerId())
                .status(update.getStatusTicket())
                .build();
    }
}
