package com.win.subticket.adapter.out;

import com.win.managerat.adapter.out.ManagerAtJpaEntity;
import com.win.serverdown.adapter.out.ServerDownJpaEntity;
import com.win.subticket.domain.SubticketStatus;
import com.win.ticket.adapter.out.TicketJpaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "subtickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubticketJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subticketId;

    private String subticketCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_manager_at_id")
    private ManagerAtJpaEntity createManagerAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "close_manager_at_id")
    private ManagerAtJpaEntity closeManagerAt;

    private LocalDateTime createEventAt;
    private LocalDateTime closeEventAt;
    private LocalDateTime dateReportPext;
    private LocalDateTime dateStopLabores;
    private LocalDateTime dateStartLabores;

    private Duration timeStopWork;
    private Duration timeEvent;
    private Duration timeAffected;
    private Duration timeSolutionsEventPext;
    private Duration timeReportPext;

    private Integer card;
    private Integer port;

    private String ctoAffected; // revisar si esta parte puede unico ya que en un mismo ticket no puede haber ctos iguales
    private String city;

    private String causeProblem;
    private Integer countClient;
    private Boolean badPraxis;
    private String solutions;

    @Enumerated(EnumType.STRING)
    private SubticketStatus status;

    private String commentary;
    private String responsable;

    @OneToMany(mappedBy = "subticket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ServerDownJpaEntity> serverDowns;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketJpaEntity ticket;
}
