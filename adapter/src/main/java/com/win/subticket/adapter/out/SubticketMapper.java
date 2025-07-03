package com.win.subticket.adapter.out;

import com.win.managerat.adapter.out.ManagerAtMapper;
import com.win.managerat.domain.ManagerAt;
import com.win.serverdown.adapter.out.ServerDownJpaEntity;
import com.win.serverdown.adapter.out.ServerDownMapper;
import com.win.serverdown.domain.ServerDown;
import com.win.subticket.adapter.out.SubticketJpaEntity;
import com.win.subticket.domain.Subticket;
import com.win.ticket.adapter.out.TicketJpaEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class SubticketMapper {

    public static Subticket toEntity(SubticketJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }

        ManagerAt managerAperture = ManagerAtMapper.toEntity(jpaEntity.getCreateManagerAt());
        ManagerAt managerAtClose = ManagerAtMapper.toEntity(jpaEntity.getCloseManagerAt());

        Set<ServerDown> serverDowns = jpaEntity.getServerDowns() != null
                ? jpaEntity.getServerDowns().stream()
                .map(ServerDownMapper::toDomain)
                .collect(Collectors.toSet())
                : Set.of();

        return Subticket.reconstructor(
                jpaEntity.getCard(),
                jpaEntity.getCauseProblem(),
                jpaEntity.getCity(),
                jpaEntity.getCloseEventAt(),
                managerAtClose,
                jpaEntity.getCommentary(),
                jpaEntity.getCountClient(),
                jpaEntity.getCreateEventAt(),
                managerAperture,
                jpaEntity.getCtoAffected(),
                jpaEntity.getDateReportPext(),
                jpaEntity.getDateStartLabores(),
                jpaEntity.getDateStopLabores(),
                jpaEntity.getPort(),
                jpaEntity.getResponsable(),
                serverDowns,
                jpaEntity.getSolutions(),
                jpaEntity.getStatus(),
                jpaEntity.getSubticketCode(),
                jpaEntity.getSubticketId(),
                jpaEntity.getTimeAffected(),
                jpaEntity.getTimeEvent(),
                jpaEntity.getTimeReportPext(),
                jpaEntity.getTimeSolutionsEventPext(),
                jpaEntity.getTimeStopWork()
        );
    }

    public static SubticketJpaEntity toJpa(Subticket domain, TicketJpaEntity ticketJpaEntity) {
        if (domain == null) {
            return null;
        }

        SubticketJpaEntity entity = new SubticketJpaEntity();
        entity.setSubticketId(domain.getSubticketId());
        entity.setSubticketCode(domain.getSubticketCode());
        entity.setCreateManagerAt(ManagerAtMapper.toJpaEntity(domain.getCreateManagerAt()));
        entity.setCloseManagerAt(ManagerAtMapper.toJpaEntity(domain.getCloseManagerAt()));

        entity.setCreateEventAt(domain.getCreateEventAt());
        entity.setCloseEventAt(domain.getCloseEventAt());
        entity.setDateReportPext(domain.getDateReportPext());
        entity.setDateStopLabores(domain.getDateStopLabores());
        entity.setDateStartLabores(domain.getDateStartLabores());

        entity.setTimeStopWork(domain.getTimeStopWork());
        entity.setTimeEvent(domain.getTimeEvent());
        entity.setTimeAffected(domain.getTimeAffected());
        entity.setTimeSolutionsEventPext(domain.getTimeSolutionsEventPext());
        entity.setTimeReportPext(domain.getTimeReportPext());

        entity.setCard(domain.getCard());
        entity.setPort(domain.getPort());
        entity.setCtoAffected(domain.getCtoAffected());
        entity.setCity(domain.getCity());
        entity.setCauseProblem(domain.getCauseProblem());
        entity.setCountClient(domain.getCountClient());
        entity.setBadPraxis(domain.getBadPraxis());
        entity.setSolutions(domain.getSolutions());
        entity.setStatus(domain.getStatusSubticket());
        entity.setCommentary(domain.getCommentary());
        entity.setResponsable(domain.getResponsable());

        // Relación inversa
        entity.setTicket(ticketJpaEntity);

        // Mapeo de ServerDowns
        Set<ServerDownJpaEntity> serverDownJpaSet = domain.getServerDowns() != null
                ? domain.getServerDowns().stream()
                .map(sd -> ServerDownMapper.toJpaEntity(sd, entity))
                .collect(Collectors.toSet())
                : Set.of();

        entity.setServerDowns(serverDownJpaSet);

        return entity;
    }
}
