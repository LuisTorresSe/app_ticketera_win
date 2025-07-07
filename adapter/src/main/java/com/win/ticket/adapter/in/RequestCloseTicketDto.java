package com.win.ticket.adapter.in;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RequestCloseTicketDto
        (
                @NotNull
                Long ticketId,
                @NotNull
                UUID managerId
        ){
}
