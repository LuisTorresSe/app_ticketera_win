package com.win.subticket.application.service;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;
import com.win.subticket.application.port.in.CloseSubticketCommand;
import com.win.subticket.application.port.in.CloseSubticketState;
import com.win.subticket.application.port.in.CloseSubticketUseCase;
import com.win.subticket.application.port.in.SubticketState;
import com.win.subticket.domain.Subticket;
import com.win.ticket.application.exception.NotFoundManagerAtException;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CloseSubticketService implements CloseSubticketUseCase {

    private final TicketPort ticketPort;
    private final ManagerPort managerPort;


    public CloseSubticketService(TicketPort ticketPort, ManagerPort managerPort) {
        this.ticketPort = ticketPort;
        this.managerPort = managerPort;
    }

    @Override
    public CloseSubticketState execute(CloseSubticketCommand command) {

        ManagerAt manager = this.managerPort.findById(command.managerId()).orElseThrow(
                ()-> new NotFoundManagerAtException("El identificador del asesor no existe en el sistema")
        );

        Ticket ticket = ticketPort.findById(command.ticketId()).orElseThrow(
                () -> new IllegalArgumentException("No se ha encontrado el ticket solicitado  "));

        Optional<Subticket> optionalSubticket = ticket.getSubTickets().stream()
                .filter(subticket -> subticket.getSubticketId().equals(command.subticketId()))
                .findFirst();

        Subticket subticket = optionalSubticket
                .orElseThrow(() -> new IllegalArgumentException("No se ha encontrado el subticket solicitado."));

        command.badPraxis().ifPresent(badPraxis -> { subticket.assignBadPraxis();});
        command.commentary().ifPresent(subticket::assignCommentary);
        subticket.assignSolution(command.solution());
        subticket.assignCloseEventAt(command.finalEvent());
        subticket.assingResponsable(command.responsableEvent());
        subticket.assignCauseProblem(command.causaRaiz());

        command.postSLA().ifPresent(subticket::assingPostSLA);

        subticket.close(manager);

        ticketPort.save(ticket);

        return new CloseSubticketState(
                ticket.getTicketId(),
                subticket.getSubticketCode(),
                subticket.getCtoAffected(),
                subticket.getCard().toString(),
                subticket.getPort().toString(),
                subticket.getCity(),
                subticket.getCountClient(),
                subticket.getCreateEventAt(),
                subticket.getDateReportPext(),
                subticket.getCreateManagerAt().getFullname(),
                subticket.getStatusSubticket(),
                ticket.getNodeAffected(),
                ticket.getOltAffected(),
                subticket.getCloseManagerAt().getFullname(),
                subticket.getCloseEventAt(),
                subticket.getCauseProblem(),
                subticket.getBadPraxis(),
                subticket.getSolutions(),
                subticket.getStatusPosSLA(),
                subticket.getCommentary(),
                subticket.getResponsable()

        );
    }
}
