package com.win.ticket.adapter.in;

import com.win.ticket.domain.TicketStatus;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;
import java.util.UUID;

public record RequestChangeTicketStatusDto
        (
                @NotNull
                Long ticketId,
                @NotNull
                UUID managerId,
                TicketStatus status,
                Optional<String> reasonForPause
        ){
}
