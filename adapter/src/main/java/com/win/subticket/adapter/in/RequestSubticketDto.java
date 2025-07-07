package com.win.subticket.adapter.in;

import com.win.serverdown.adapter.in.RequestServerDown;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record RequestSubticketDto(
        @NotNull
        UUID createManagerId,

        @NotNull
        Long ticketId,
        LocalDateTime dateReportPext,
        Integer card,
        Integer port,
        String cto,
        @Nullable
        String commentary,
        String city
) {

}
