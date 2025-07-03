package com.win.subticket.application.port.out;

import com.win.subticket.domain.Subticket;

import java.util.Optional;

public interface SubticketPort {

    Subticket save(Subticket subticket);
    Optional<Subticket> findById(Long id);
}
