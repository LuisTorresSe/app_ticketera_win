package com.win.subticket.application.port.in;


import com.win.SelfValidating;
import com.win.serverdown.application.port.in.CreateServerDownCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
public class CreateSubticketCommand extends SelfValidating<CreateSubticketCommand> {

    @NotNull(message = "El ID del manager es obligatorio")
    private final UUID createManagerId;

    @NotNull(message = "El ID del ticket es obligatorio")
    private final Long ticketId;

    @NotNull(message = "La fecha de reporte es obligatoria")
    private final LocalDateTime dateReportPext;

    @NotNull(message = "El campo 'card' es obligatorio")
    @Min(value = 0, message = "El valor de 'card' debe ser mayor o igual a 0")
    private final Integer card;

    @NotNull(message = "El campo 'port' es obligatorio")
    @Min(value = 0, message = "El valor de 'port' debe ser mayor o igual a 0")
    private final Integer port;



    @NotBlank(message = "La CTO no puede estar vacía")
    private final String cto;

    private final String commentary;

    public CreateSubticketCommand(
            UUID createManagerId,
            Long ticketId,
            LocalDateTime dateReportPext,
            Integer card,
            Integer port,
            String cto,
            String commentary

    ) {
        this.createManagerId = createManagerId;
        this.ticketId = ticketId;
        this.dateReportPext = dateReportPext;
        this.card = card;
        this.port = port;
        this.cto = cto;
        this.commentary = commentary;
        this.validateSelf();
    }
}