package com.win.serverdown.adapter.out;

import com.win.managerat.adapter.out.ManagerAtMapper;
import com.win.serverdown.application.port.out.ServerDownPort;
import com.win.serverdown.domain.ServerDown;

import java.util.Optional;

public class ServerDownPersistenceAdapter implements ServerDownPort {

    private final ServerDownRepository repository;
    public ServerDownPersistenceAdapter(ServerDownRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ServerDown> findById(Long id) {
        return this.repository.findById(id).map(
                ServerDownMapper::toDomain
        );
    }
}
