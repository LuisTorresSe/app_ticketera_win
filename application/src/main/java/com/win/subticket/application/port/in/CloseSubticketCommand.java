package com.win.subticket.application.port.in;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record CloseSubticketCommand (
         UUID managerId,
         Long ticketId,
         Long subticketId,
         LocalDateTime finalEvent,
         String causaRaiz,
         String solution,
         String responsableEvent,
         Optional<String> commentary,
         Optional<Boolean> badPraxis,
         Optional<String> postSLA
){
}
