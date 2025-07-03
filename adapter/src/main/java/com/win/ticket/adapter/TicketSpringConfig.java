package com.win.ticket.adapter;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.ticket.adapter.out.TicketPersistenceAdapter;
import com.win.ticket.adapter.out.TicketRepository;

import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketUseCase;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketUseCase;
import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.application.service.CloseTicketService;
import com.win.ticket.application.service.CreateTicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketSpringConfig {

    @Bean
    public CreateTicketUseCase createTicketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new CreateTicketService(managerPort, ticketPort);
    }

    @Bean
   public TicketPort ticketPort(TicketRepository ticketRepository) {
        return new TicketPersistenceAdapter(ticketRepository);
    }

    @Bean
    public CloseTicketUseCase closeTicketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new CloseTicketService(ticketPort, managerPort);
    }
}
