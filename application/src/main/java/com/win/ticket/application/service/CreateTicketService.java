package com.win.ticket.application.service;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;

import com.win.ticket.application.exception.NotFoundManagerAtException;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketCommand;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketState;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketUseCase;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;


public class CreateTicketService implements CreateTicketUseCase {
    private final ManagerPort managerPort;
    private final TicketPort ticketPort;

    public CreateTicketService(ManagerPort managerPort, TicketPort ticketPort) {
        this.managerPort = managerPort;
        this.ticketPort = ticketPort;
    }

    @Override
    public CreateTicketState execute(CreateTicketCommand command) {

        ManagerAt managerAt = managerPort.findById(command.getManagerId()).orElseThrow(
                () -> new NotFoundManagerAtException("Manager not found"));

        Ticket newTicket = Ticket.createTicket(
                command.getType(),
                command.getReport(),
                command.getDiagnosis(),
                managerAt,
                command.getCreateAtEvent(),
                command.getUnavailability(),
                command.getNodeAffected(),
                command.getOltAffected(),
                command.getComment()
        );

        Ticket savedTicket = ticketPort.save(newTicket);

        savedTicket.generateCodeTicket();

        Ticket updateTicket =  ticketPort.save(savedTicket);

        return new CreateTicketState(
                updateTicket.getTicketId(),
                updateTicket.getCodeTicket(),
                managerAt.getManagerId(),
                updateTicket.getType(),
                updateTicket.getReport(),
                updateTicket.getDiagnosis(),
                updateTicket.getCreateAtEvent(),
                updateTicket.getUnavailability(),
                updateTicket.getNodeAffected(),
                updateTicket.getOltAffected(),
                updateTicket.getComment(),
                updateTicket.getStatusTicket()
        );
    }
}
