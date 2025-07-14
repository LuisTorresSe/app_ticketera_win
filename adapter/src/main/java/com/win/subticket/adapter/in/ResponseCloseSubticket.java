package com.win.subticket.adapter.in;

import com.win.subticket.application.port.in.CloseSubticketState;
import com.win.subticket.domain.Subticket;
import com.win.subticket.domain.SubticketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseCloseSubticket(
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
) {}