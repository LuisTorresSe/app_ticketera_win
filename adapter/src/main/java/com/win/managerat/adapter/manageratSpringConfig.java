package com.win.managerat.adapter;

import com.win.managerat.adapter.out.ManagerAtRepository;
import com.win.managerat.adapter.out.ManagerPersistenceAdapter;
import com.win.managerat.application.port.out.ManagerPort;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class manageratSpringConfig {

    @Bean
    public ManagerPort managerPort(ManagerAtRepository managerAtRepository) {
        return new ManagerPersistenceAdapter(managerAtRepository);
    }
}
