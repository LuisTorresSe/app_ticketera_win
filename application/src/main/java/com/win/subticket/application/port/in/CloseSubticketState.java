package com.win.subticket.application.port.in;

import com.win.subticket.domain.SubticketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record  CloseSubticketState(
        Long ticketId,
        String code,
        String cto,
        String card,
        String port,
        String city,
        int clientCount,
        LocalDateTime eventStartDate,
        LocalDateTime reportedToPextDate,
        String creator,
        SubticketStatus status,
        String node,
        String olt,
        // Closing fields
        String closingAdvisor,
        LocalDateTime eventEndDate,
        String rootCause,
        Boolean badPraxis,
        String solution,
        String statusPostSLA,
        String comment,
        String eventResponsible
) {
}
