package com.win.ticket.adapter.out;

import com.win.ticket.application.port.out.TicketPort;
import com.win.ticket.domain.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketPersistenceAdapter implements TicketPort {

    private final TicketRepository ticketRepository;
    public TicketPersistenceAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override


    public Ticket save(Ticket ticket) {
        TicketJpaEntity newTicket = TicketMapper.toJpaEntity(ticket);

        System.out.println(newTicket);
        TicketJpaEntity savedTicket = ticketRepository.save(newTicket);
        return TicketMapper.toDomainEntity(savedTicket);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id)
                .map(TicketMapper::toDomainEntity);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll().stream().map(TicketMapper::toDomainEntity).toList();
    }
}
