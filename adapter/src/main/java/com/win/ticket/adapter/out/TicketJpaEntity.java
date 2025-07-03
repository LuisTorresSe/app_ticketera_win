package com.win.ticket.adapter.out;


import com.win.managerat.adapter.out.ManagerAtJpaEntity;
import com.win.subticket.adapter.out.SubticketJpaEntity;
import com.win.subticket.domain.Subticket;
import com.win.ticket.domain.Diagnosis;
import com.win.ticket.domain.TicketReport;
import com.win.ticket.domain.TicketStatus;
import com.win.ticket.domain.TicketType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private String codeTicket;

    @ManyToOne
    @JoinColumn(name = "manager_at_aperture_manager_id")
    private ManagerAtJpaEntity managerAtAperture;
    @ManyToOne
    @JoinColumn(name = "manager_at_close_manager_id")
    private ManagerAtJpaEntity managerAtClose;

    @Enumerated(EnumType.STRING)
    private TicketStatus statusTicket;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Enumerated(EnumType.STRING)
    private TicketReport report;

    @Enumerated(EnumType.STRING)
    private Diagnosis diagnosis;

    private LocalDateTime createAtEvent;
    private LocalDateTime closeAtEvent;
    private Boolean unavailability;
    private String nodeAffected;
    private String oltAffected;
    private String comment;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<SubticketJpaEntity> subTickets = new HashSet<>();


}
