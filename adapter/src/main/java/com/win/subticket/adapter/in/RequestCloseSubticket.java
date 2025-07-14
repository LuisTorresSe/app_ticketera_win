package com.win.subticket.adapter.in;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record RequestCloseSubticket(
        Long ticketId,
        Long subticketId,
        UUID managerId,
        LocalDateTime eventEndDate,
        String causeRoot,
        String solution,
        String eventResponsible,
        Optional<String> commentary,
        Optional<Boolean> badPraxis,
        Optional<String> statusPostSLA
) {}
