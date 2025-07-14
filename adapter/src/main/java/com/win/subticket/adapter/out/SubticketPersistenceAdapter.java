package com.win.subticket.adapter.out;


import com.win.subticket.application.port.out.SubticketPort;
import com.win.subticket.domain.Subticket;

import java.util.Optional;

public class SubticketPersistenceAdapter implements SubticketPort {
    private SubticketRepository subticketRepository;
    public SubticketPersistenceAdapter(SubticketRepository subticketRepository) {
        this.subticketRepository = subticketRepository;
    }

    @Override
    public Subticket save(Subticket subticket) {
    return null;
    }

    @Override
    public Optional<Subticket> findById(Long id) {
        return subticketRepository.findById(id).map(
                SubticketMapper::toEntity
        );
    }
}
