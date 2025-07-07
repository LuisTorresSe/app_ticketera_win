package com.win.ticket.application.port.out;

import com.win.ticket.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketPort {

    Ticket save(Ticket ticket);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();

}
