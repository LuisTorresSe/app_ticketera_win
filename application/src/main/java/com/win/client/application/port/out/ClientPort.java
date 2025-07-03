package com.win.client.application.port.out;

import com.win.client.domain.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientPort {

    Optional<Set<Client>> getClientsByCto(String cto);

    Optional<Client> findById(Long id);

}
