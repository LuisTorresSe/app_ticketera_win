package com.win.ticket.domain;

import com.win.managerat.domain.ManagerAt;
import com.win.role.domain.Role;
import com.win.subticket.domain.Subticket;
import com.win.ticket.domain.exception.CannotCloseTicketException;
import com.win.ticket.domain.exception.InvalidDateException;
import com.win.ticket.domain.exception.TicketAlreadyCloseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class TicketTest {

    private ManagerAt managerAtAperture;
    private ManagerAt managerAtClose;
    private LocalDateTime validEventDate;
    private Ticket ticket;
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role(UUID.randomUUID(), "ADMIN", new HashSet<>());
        managerAtAperture = new ManagerAt(1L, UUID.randomUUID(), "John Doe", role, "john@example.com", "password123");
        managerAtClose = new ManagerAt(2L, UUID.randomUUID(), "Jane Doe", role, "jane@example.com", "password456");
        validEventDate = LocalDateTime.now().minusHours(1);
        
        ticket = Ticket.createTicket(
            TicketType.PROACTIVO,
            TicketReport.NOC,
            Diagnosis.LOS,
            managerAtAperture,
            validEventDate,
            true,
            "Node1",
            "OLT1",

            "Initial comment"
        );
    }

    @Test
    @DisplayName("Debería crear un ticket válido con todos los campos requeridos")
    void shouldCreateValidTicket() {
        assertThat(ticket).isNotNull();
        assertThat(ticket.getStatusTicket()).isEqualTo(TicketStatus.PENDIENTE);
        assertThat(ticket.getType()).isEqualTo(TicketType.PROACTIVO);
        assertThat(ticket.getReport()).isEqualTo(TicketReport.NOC);
        assertThat(ticket.getDiagnosis()).isEqualTo(Diagnosis.LOS);
        assertThat(ticket.getManagerAtAperture()).isEqualTo(managerAtAperture);
        assertThat(ticket.getCreateAtEvent()).isEqualTo(validEventDate);
        assertThat(ticket.getUnavailability()).isTrue();
        assertThat(ticket.getNodeAffected()).isEqualTo("Node1");
        assertThat(ticket.getOltAffected()).isEqualTo("OLT1");
        assertThat(ticket.getComment()).isEqualTo("Initial comment");
        assertThat(ticket.getSubTickets()).isEmpty();
    }

    @Test
    @DisplayName("Debería generar un código de ticket válido")
    void shouldGenerateValidTicketCode() {
        ticket = Ticket.reconstructor(
            123L, null, TicketType.PROACTIVO, TicketReport.NOC,
            Diagnosis.LOS, managerAtAperture, null, TicketStatus.PENDIENTE,
            validEventDate, null, true, "Node1", "OLT1", "Comment",
            new HashSet<>()
        );

        ticket.generateCodeTicket();
        assertThat(ticket.getCodeTicket()).isEqualTo("W-CR-00123-P");
    }

    @Test
    @DisplayName("Debería lanzar excepción al crear ticket con fecha futura")
    void shouldThrowExceptionWhenCreateDateIsInFuture() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        
        assertThatThrownBy(() -> Ticket.createTicket(
            TicketType.PROACTIVO,
            TicketReport.NOC,
            Diagnosis.LOS,
            managerAtAperture,
            futureDate,
            true,
            "Node1",
            "OLT1",
            "Comment"
        )).isInstanceOf(InvalidDateException.class)
          .hasMessageContaining("Event date cannot be in the future");
    }

    @Test
    @DisplayName("Debería cerrar ticket correctamente cuando no hay subtickets pendientes")
    void shouldChangeStatusSuccessfully() {
        ticket.changeStatus(managerAtClose,"SOLUCIONADO");
        
        assertThat(ticket.getStatusTicket()).isEqualTo(TicketStatus.SOLUCIONADO);
        assertThat(ticket.getManagerAtClose()).isEqualTo(managerAtClose);
        assertThat(ticket.getCloseAtEvent()).isNotNull();
    }

    @Test
    @DisplayName("No debería cerrar ticket cuando hay subtickets pendientes")
    void shouldNotChangeStatusWithPendingSubtickets() {
        // Crear un subticket pendiente
        Subticket pendingSubticket = Subticket.create(
            "SUB-001",
            managerAtAperture,
            LocalDateTime.now().minusHours(1),
            1,
            1,
            "CTO-001",
            "Test subticket",
            "Lima",
            new HashSet<>()
        );
        ticket.addSubticket(pendingSubticket);

        assertThatThrownBy(() -> ticket.changeStatus(managerAtClose))
            .isInstanceOf(CannotCloseTicketException.class)
            .hasMessageContaining("Cannot close ticket with pending subtickets");
    }

    @Test
    @DisplayName("No debería permitir modificaciones después de cerrar el ticket")
    void shouldNotAllowModificationsAfterClosing() {
        ticket.changeStatus(managerAtClose);

        assertThatThrownBy(() -> ticket.assignReport(TicketReport.ATC))
            .isInstanceOf(TicketAlreadyCloseException.class);

        assertThatThrownBy(() -> ticket.assignDiagnosis(Diagnosis.CTO_DEGRADADA))
            .isInstanceOf(TicketAlreadyCloseException.class);

        // Crear un nuevo subticket para la prueba
        Subticket newSubticket = Subticket.create(
            "SUB-002",
            managerAtAperture,
            LocalDateTime.now().minusHours(1),
            1,
            1,
            "CTO-002",
            "Test subticket",
            "Lima",
            new HashSet<>()
        );

        assertThatThrownBy(() -> ticket.addSubticket(newSubticket))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Debería manejar correctamente la adición y eliminación de subtickets")
    void shouldHandleSubticketsCorrectly() {
        Subticket subticket = Subticket.create(
            "SUB-003",
            managerAtAperture,
            LocalDateTime.now().minusHours(1),
            1,
            1,
            "CTO-003",
            "Test subticket", "Lima",
            new HashSet<>()
        );
        
        ticket.addSubticket(subticket);
        assertThat(ticket.getSubTickets()).hasSize(1);
        assertThat(ticket.getSubTickets()).contains(subticket);

        ticket.removeSubticket(subticket);
        assertThat(ticket.getSubTickets()).isEmpty();
    }

    @Test
    @DisplayName("Debería validar correctamente los comentarios")
    void shouldValidateComments() {
        assertThatThrownBy(() -> ticket.addComment(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Comment cannot be null or empty");

        assertThatThrownBy(() -> ticket.addComment(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Comment cannot be null or empty");

        assertThatThrownBy(() -> ticket.addComment("   "))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Comment cannot be null or empty");

        ticket.addComment("Valid comment");
        assertThat(ticket.getComment()).isEqualTo("Valid comment");
    }
} 