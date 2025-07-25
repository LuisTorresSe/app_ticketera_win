package com.win.subticket.adapter;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.subticket.adapter.out.SubticketPersistenceAdapter;
import com.win.subticket.adapter.out.SubticketRepository;
import com.win.subticket.application.port.in.UpdateSubticketUseCase;
import com.win.subticket.application.port.out.SubticketPort;
import com.win.subticket.application.service.UpdateSubticketService;
import com.win.ticket.application.port.out.TicketPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubticketSpringConfig {
    @Bean
    public SubticketPort subticketPort(SubticketRepository subticketRepository) {
        return new SubticketPersistenceAdapter(subticketRepository);
    }

    @Bean
    public UpdateSubticketUseCase updateSubticketUseCase(TicketPort ticketPort, ManagerPort managerPort) {
        return new UpdateSubticketService(ticketPort, managerPort);
    }
}
