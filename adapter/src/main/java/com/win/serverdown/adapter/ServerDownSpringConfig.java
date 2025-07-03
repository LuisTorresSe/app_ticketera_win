package com.win.serverdown.adapter;

import com.win.client.application.port.out.ClientPort;
import com.win.serverdown.adapter.out.ServerDownPersistenceAdapter;
import com.win.serverdown.adapter.out.ServerDownRepository;
import com.win.serverdown.application.port.in.CreateServerDownUseCase;
import com.win.serverdown.application.port.out.ServerDownPort;
import com.win.serverdown.application.service.CreateServerDownService;
import com.win.subticket.application.port.out.SubticketPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerDownSpringConfig {

    @Bean
    public ServerDownPort serverDownPort(ServerDownRepository repository) {
        return new ServerDownPersistenceAdapter(repository);
    }


    @Bean
    public CreateServerDownUseCase createServerDownUseCase(ServerDownPort serverDownPort,
                                                           SubticketPort subticketPort,
                                                           ClientPort clientPort) {
        return new CreateServerDownService(serverDownPort, subticketPort, clientPort);
    }
}
