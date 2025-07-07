package com.win.subticket.application.service;

import com.win.client.application.exception.NotFoundClientException;
import com.win.client.application.port.out.ClientPort;
import com.win.client.domain.Client;
import com.win.managerat.application.exception.NotFoundManagerAtException;
import com.win.managerat.application.port.in.ManagerAtState;
import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;
import com.win.serverdown.domain.ServerDown;
import com.win.subticket.application.exception.AlreadySubticketWithCtoException;
import com.win.subticket.application.port.in.CreateSubticketCommand;
import com.win.subticket.application.port.in.CreateSubticketUseCase;
import com.win.subticket.application.port.in.SubticketState;
import com.win.subticket.domain.Subticket;
import com.win.ticket.application.exception.NotFoundTicketException;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateSubticketService implements CreateSubticketUseCase {

    private final ManagerPort managerPort;
    private final TicketPort ticketPort;
    private final ClientPort clientPort;

    public CreateSubticketService(ManagerPort managerPort, TicketPort ticketPort, ClientPort clientPort) {
        this.managerPort = managerPort;
        this.ticketPort = ticketPort;
        this.clientPort = clientPort;
    }

    @Override
    public SubticketState execute(CreateSubticketCommand command) {

        ManagerAt manager = this.managerPort.findById(command.getCreateManagerId())
                .orElseThrow(() -> new NotFoundManagerAtException("ManagerAt not found"));

        Ticket ticket = this.ticketPort.findById(command.getTicketId())
                .orElseThrow(() -> new NotFoundTicketException("No se puede crear un subticket sin un ticket"));

        if(!ticket.getSubTickets().isEmpty()) {
            boolean exists = ticket.getSubTickets().stream()
                    .anyMatch(subticket -> subticket.getCtoAffected().equals(command.getCto()));
            if (exists) {
                throw new AlreadySubticketWithCtoException("Subticket with CtoAffected already exists");
            }
        }

        final int SIZE_SUBTICKET = ticket.getSubTickets().size() +1;

        final String code = ticket.getCodeTicket()+'_'+SIZE_SUBTICKET;

        Set<Client> clientBycto = clientPort.getClientsByCto(command.getCto()).orElseThrow(
                () -> new NotFoundClientException("Client not found")
        );

        Set<ServerDown> serversdowns = clientBycto.stream().map(
                ServerDown::new
        ).collect(Collectors.toSet());

        int countClients = clientBycto.size();

        Subticket newSubticket = Subticket.create(
                code,
                manager,
                command.getDateReportPext(),
                command.getCard(),
                command.getPort(),
                command.getCto(),
                command.getCommentary(),
                serversdowns
        );

        newSubticket.setCounClient(countClients);

        ticket.addSubticket(newSubticket);


        Ticket updatedTicket = ticketPort.save(ticket);

        Subticket foundLastTicket =  updatedTicket.getSubTickets().stream()
                .filter(s -> s.getCtoAffected().equals(command.getCto()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subticket not found after save"));

        ManagerAtState createManager = foundLastTicket.getCreateManagerAt()!= null ?
            new ManagerAtState(
                foundLastTicket.getCreateManagerAt().getManagerId(),foundLastTicket.getCreateManagerAt().getFullname())
        : null;
        ManagerAtState closeManager = foundLastTicket.getCloseManagerAt()!= null ?
                new ManagerAtState(
                        foundLastTicket.getCloseManagerAt().getManagerId(),foundLastTicket.getCloseManagerAt().getFullname())
                : null;


        return  SubticketState.builder()
                .subticketId(foundLastTicket.getSubticketId())
                .subticketCode(foundLastTicket.getSubticketCode())
                .createManagerAt(createManager)
                .closeManagerAt(closeManager)
                .city(foundLastTicket.getCity())
                .badPraxis(foundLastTicket.getBadPraxis())
                .card(foundLastTicket.getCard())
                .commentary(foundLastTicket.getCommentary())
                .port(foundLastTicket.getPort())
                .causeProblem(foundLastTicket.getCauseProblem())
                .createEventAt(foundLastTicket.getCreateEventAt())
                .closeEventAt(foundLastTicket.getCloseEventAt())
                .ctoAffected(foundLastTicket.getCtoAffected())
                .countClient(foundLastTicket.getCountClient())
                .statusSubticket(foundLastTicket.getStatusSubticket())
                .solutions(foundLastTicket.getSolutions())
                .responsable(foundLastTicket.getResponsable())
                .dateStartLabores(foundLastTicket.getDateStartLabores())
                .dateReportPext(foundLastTicket.getDateReportPext())
                .dateStopLabores(foundLastTicket.getDateStopLabores())
                .build();
    }
}
