package com.win.managerat.adapter.out;

import com.win.managerat.application.port.out.ManagerPort;
import com.win.managerat.domain.ManagerAt;

import java.util.Optional;
import java.util.UUID;

public class ManagerPersistenceAdapter implements ManagerPort {
    private final ManagerAtRepository managerAtRepository;

    public ManagerPersistenceAdapter(ManagerAtRepository managerAtRepository) {
        this.managerAtRepository = managerAtRepository;
    }
    @Override
    public Optional<ManagerAt> findById(UUID id) {

        return this.managerAtRepository.findByManagerId(id).map(ManagerAtMapper::toEntity);
    }
}
