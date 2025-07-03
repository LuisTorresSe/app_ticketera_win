package com.win.ticket.adapter.out;

import com.win.managerat.adapter.out.ManagerAtJpaEntity;
import com.win.managerat.adapter.out.ManagerAtMapper;
import com.win.managerat.domain.ManagerAt;
import com.win.subticket.adapter.out.SubticketJpaEntity;
import com.win.subticket.adapter.out.SubticketMapper;
import com.win.subticket.domain.Subticket;
import com.win.ticket.domain.*;

import java.util.Set;
import java.util.stream.Collectors;

public class TicketMapper {

    public static Ticket toDomainEntity(TicketJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        ManagerAt managerAperture = jpaEntity.getManagerAtAperture() != null
                ? ManagerAtMapper.toEntity(jpaEntity.getManagerAtAperture())
                : null;

        ManagerAt managerClose = jpaEntity.getManagerAtClose() != null
                ? ManagerAtMapper.toEntity(jpaEntity.getManagerAtClose())
                : null;

        Set<Subticket> subtickets = jpaEntity.getSubTickets() != null
                ? jpaEntity.getSubTickets().stream()
                .map(SubticketMapper::toEntity)
                .collect(Collectors.toSet())
                : Set.of();

        return Ticket.reconstructor(
                jpaEntity.getTicketId(),
                jpaEntity.getCodeTicket(),
                jpaEntity.getType(),
                jpaEntity.getReport(),
                jpaEntity.getDiagnosis(),
                managerAperture,
                managerClose,
                jpaEntity.getStatusTicket(),
                jpaEntity.getCreateAtEvent(),
                jpaEntity.getCloseAtEvent(),
                jpaEntity.getUnavailability(),
                jpaEntity.getNodeAffected(),
                jpaEntity.getOltAffected(),
                jpaEntity.getComment(),
                subtickets
        );
    }

    public static TicketJpaEntity toJpaEntity(Ticket domain) {
        if (domain == null) return null;

        ManagerAtJpaEntity managerAperture = domain.getManagerAtAperture() != null
                ? ManagerAtMapper.toJpaEntity(domain.getManagerAtAperture())
                : null;

        ManagerAtJpaEntity managerClose = domain.getManagerAtClose() != null
                ? ManagerAtMapper.toJpaEntity(domain.getManagerAtClose())
                : null;

        TicketJpaEntity jpaEntity = TicketJpaEntity.builder()
                .ticketId(domain.getTicketId())
                .codeTicket(domain.getCodeTicket())
                .type(domain.getType())
                .report(domain.getReport())
                .diagnosis(domain.getDiagnosis())
                .statusTicket(domain.getStatusTicket())
                .createAtEvent(domain.getCreateAtEvent())
                .closeAtEvent(domain.getCloseAtEvent())
                .unavailability(domain.getUnavailability())
                .nodeAffected(domain.getNodeAffected())
                .oltAffected(domain.getOltAffected())
                .comment(domain.getComment())
                .managerAtAperture(managerAperture)
                .managerAtClose(managerClose)
                .build();

        // Relación bidireccional: cada Subticket debe tener el TicketJpaEntity asignado
        Set<SubticketJpaEntity> subticketsJpa = domain.getSubTickets() != null
                ? domain.getSubTickets().stream()
                .map(sub -> SubticketMapper.toJpa(sub, jpaEntity))
                .collect(Collectors.toSet())
                : Set.of();

        jpaEntity.setSubTickets(subticketsJpa);

        return jpaEntity;
    }
}
