package com.win.auth.adapter;

import com.win.auth.adapter.out.AuthPersistenceAdapter;
import com.win.auth.application.port.in.LoginUseCase;
import com.win.auth.application.port.out.AuthPort;
import com.win.auth.application.service.LoginService;
import com.win.client.application.port.out.ClientPort;
import com.win.managerat.adapter.out.ManagerAtRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public AuthPort authPort(ManagerAtRepository managerAtRepository) {
        return new AuthPersistenceAdapter(managerAtRepository);
    }


    @Bean
    public LoginUseCase loginUseCase(AuthPort authPort) {
        return new LoginService(authPort);
    }
}
