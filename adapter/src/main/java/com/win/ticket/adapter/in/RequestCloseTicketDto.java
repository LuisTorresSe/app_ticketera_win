package com.win.ticket.adapter.in;

import jakarta.validation.constraints.NotNull;

public record RequestCloseTicketDto
        (
                @NotNull
                Long ticketId,
                @NotNull
                Long managerId
        ){
}
