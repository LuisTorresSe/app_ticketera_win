package com.win.subticket.application.service;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;
import com.win.subticket.application.port.in.SubticketState;
import com.win.subticket.application.port.in.UpdateSubticketCommand;
import com.win.subticket.application.port.in.UpdateSubticketUseCase;
import com.win.subticket.domain.Subticket;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;

public class UpdateSubticketService implements UpdateSubticketUseCase {

    private final TicketPort ticketPort;
    private final ManagerPort managerAtPort;

    public UpdateSubticketService(TicketPort ticketPort, ManagerPort managerAtPort) {
        this.ticketPort = ticketPort;
        this.managerAtPort = managerAtPort;
    }

    @Override
    public SubticketState execute(UpdateSubticketCommand cmd) {
        Ticket ticket = this.ticketPort.findById(cmd.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Subticket existingSubticket = ticket.getSubTickets().stream()
                .filter(s -> s.getSubticketId().equals(cmd.getSubticketId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subticket not found in ticket"));

        ManagerAt updateManager = this.managerAtPort.findById(cmd.getUpdateManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        // Actualiza el subticket
        Subticket updatedSubticket = existingSubticket.update(
                updateManager,
                cmd.getCreateEventAt(),
                cmd.getDateReportPext(),
                cmd.getCard(),
                cmd.getPort(),
                cmd.getCto(),
                cmd.getCommentary(),
                cmd.getCity(),
                cmd.getCountClient()
        );

        // Actualiza el ticket
        ticket.updateSubticket(updatedSubticket);

        // Persistencia
        ticketPort.save(ticket);

        return SubticketState.from(updatedSubticket);
    }
}
