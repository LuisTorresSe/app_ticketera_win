package com.win.ticket.application.service;

import com.win.managerat.application.port.in.ManagerAtState;
import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;
import com.win.subticket.application.port.in.SubticketState;
import com.win.ticket.application.exception.NotFoundTicketException;
import com.win.ticket.application.port.in.*;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;

public class UpdatedTicketService implements UpdatedTicketUseCase {
    private final TicketPort ticketPort;
    private final ManagerPort managerPort;

    public UpdatedTicketService(TicketPort ticketPort, ManagerPort managerPort) {
        this.ticketPort = ticketPort;
        this.managerPort = managerPort;
    }

    @Override
    public GetTicketState execute(UpdatedTicketCommand command) {
        Ticket findTicket = ticketPort.findById(command.ticketId()).orElseThrow(
                () -> new NotFoundTicketException("El ticket no existe")
        );

        ManagerAt findManager = managerPort.findById(command.managerId()).orElseThrow(
                () -> new NotFoundTicketException("El manager no existe")
        );

        findTicket.update(
                command.type(),
                findManager,
                command.report(),
                command.diagnosis(),
                command.createAtEvent(),
                command.unavailability(),
                command.nodeAffected(),
                command.oltAffected(),
                command.emailStatus()
        );

        Ticket savedTicket = ticketPort.save(findTicket);
        return TicketStateMapper.toState(savedTicket);
    }
}
