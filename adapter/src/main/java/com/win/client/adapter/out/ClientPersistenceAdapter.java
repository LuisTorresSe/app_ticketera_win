package com.win.client.adapter.out;

import com.win.client.application.port.out.ClientPort;
import com.win.client.domain.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientPersistenceAdapter implements ClientPort {

    private final ClientRepository clientRepository;
    public ClientPersistenceAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Set<Client>> getClientsByCto(String cto) {
        List<ClientJpaEntity> clientsJpa = clientRepository.findAllByCtoAndStatusNot(cto, "BAJAS");

        if (clientsJpa.isEmpty()) {
            return Optional.empty();
        }
        Set<Client> clients = clientsJpa.stream().map(
                clientJpaEntity -> ClientMapper.toEntity(clientJpaEntity)
        ).collect(Collectors.toSet());


        return Optional.of(clients);
    }
    @Override
    public Optional<Client> findById(Long id) {

        Optional<ClientJpaEntity> clientJpaEntity = clientRepository.findById(id);

        return clientJpaEntity.map(ClientMapper::toEntity);
    }

}
