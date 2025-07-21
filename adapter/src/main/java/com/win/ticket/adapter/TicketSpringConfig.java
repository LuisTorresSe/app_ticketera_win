package com.win.ticket.adapter;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.subticket.application.port.in.CloseSubticketUseCase;
import com.win.subticket.application.service.CloseSubticketService;
import com.win.ticket.adapter.out.TicketPersistenceAdapter;
import com.win.ticket.adapter.out.TicketRepository;

import com.win.ticket.application.port.in.GetAllTicketUseCase;
import com.win.ticket.application.port.in.UpdatedTicketUseCase;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusUseCase;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketUseCase;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.application.service.ChangeTicketStatusService;
import com.win.ticket.application.service.CreateTicketService;
import com.win.ticket.application.service.GetAllTicketService;
import com.win.ticket.application.service.UpdatedTicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketSpringConfig {

    @Bean
    public CreateTicketUseCase createTicketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new CreateTicketService(managerPort, ticketPort);
    }

    @Bean
    public UpdatedTicketUseCase updatedTicketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new UpdatedTicketService(ticketPort,managerPort);
    }


    @Bean
   public TicketPort ticketPort(TicketRepository ticketRepository) {
        return new TicketPersistenceAdapter(ticketRepository);
    }

    @Bean
    public ChangeTicketStatusUseCase closeTicketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new ChangeTicketStatusService(ticketPort, managerPort);
    }

    @Bean
    public GetAllTicketUseCase getAllTicketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new GetAllTicketService(ticketPort);
    }


    @Bean
    public CloseSubticketUseCase closeSubticketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new CloseSubticketService(ticketPort, managerPort);
    }
}
