package com.win.subticket.adapter;

import com.win.subticket.adapter.out.SubticketPersistenceAdapter;
import com.win.subticket.adapter.out.SubticketRepository;
import com.win.subticket.application.port.out.SubticketPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubticketSpringConfig {
    @Bean
    public SubticketPort subticketPort(SubticketRepository subticketRepository) {
        return new SubticketPersistenceAdapter(subticketRepository);
    }

}
