package com.win.client.adapter;

import com.win.client.adapter.out.ClientPersistenceAdapter;
import com.win.client.adapter.out.ClientRepository;
import com.win.client.application.port.in.GetClientByCtoUseCase;
import com.win.client.application.port.out.ClientPort;
import com.win.client.application.service.GetClienteByCtoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ClientPort getClientPort(ClientRepository clientRepository) {
        return new ClientPersistenceAdapter(clientRepository);
    }

    @Bean
    public GetClientByCtoUseCase getClientByCtoUseCase(ClientPort clientPort) {
        return new GetClienteByCtoService(clientPort);
    }
}
