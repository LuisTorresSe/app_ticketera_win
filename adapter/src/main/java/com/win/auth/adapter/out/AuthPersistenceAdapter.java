package com.win.auth.adapter.out;

import com.win.auth.application.port.out.AuthPort;
import com.win.managerat.adapter.out.ManagerAtJpaEntity;
import com.win.managerat.adapter.out.ManagerAtMapper;
import com.win.managerat.adapter.out.ManagerAtRepository;
import com.win.managerat.domain.ManagerAt;

import java.util.Optional;
import java.util.UUID;

public class AuthPersistenceAdapter implements AuthPort {
    private ManagerAtRepository managerAtRepository;

    public AuthPersistenceAdapter(ManagerAtRepository managerAtRepository) {
        this.managerAtRepository = managerAtRepository;
    }


    @Override
    public Optional<ManagerAt> findByEmail(String email) {
        return managerAtRepository.findByEmail(email)
                .map(ManagerAtMapper::toEntity);
    }
}
