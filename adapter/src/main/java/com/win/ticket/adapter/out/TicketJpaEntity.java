package com.win.ticket.adapter.out;


import com.win.managerat.adapter.out.ManagerAtJpaEntity;
import com.win.subticket.adapter.out.SubticketJpaEntity;
import com.win.subticket.domain.Subticket;
import com.win.ticket.domain.*;
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
    @JoinColumn(name = "manager_at_aperture_manager_id" , referencedColumnName = "id")
    private ManagerAtJpaEntity managerAtAperture;
    @ManyToOne
    @JoinColumn(name = "manager_at_close_manager_id", referencedColumnName = "id")
    private ManagerAtJpaEntity managerAtClose;

    @Enumerated(EnumType.STRING)
    private TicketStatus statusTicket;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Enumerated(EnumType.STRING)
    private TicketReport report;

    @Enumerated(EnumType.STRING)
    private Diagnosis diagnosis;

    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;

    private LocalDateTime createAtEvent;
    private LocalDateTime closeAtEvent;
    private Boolean unavailability;
    private String nodeAffected;
    private String oltAffected;
    private String reasonForPause;
    private String assignTo;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "managerAt_change_status_manager_id", referencedColumnName = "id")
    private ManagerAtJpaEntity managerAtChangeStatus;
    private LocalDateTime statusChangedAt;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<SubticketJpaEntity> subTickets = new HashSet<>();


}
