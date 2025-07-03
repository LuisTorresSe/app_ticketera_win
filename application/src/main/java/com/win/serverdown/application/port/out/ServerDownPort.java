package com.win.serverdown.application.port.out;

import com.win.serverdown.domain.ServerDown;

import java.util.Optional;

public interface ServerDownPort {

    Optional<ServerDown> findById(Long id );

}
