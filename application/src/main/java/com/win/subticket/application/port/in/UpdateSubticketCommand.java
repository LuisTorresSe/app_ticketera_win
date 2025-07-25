package com.win.subticket.application.port.in;

import com.win.SelfValidating;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UpdateSubticketCommand extends SelfValidating<UpdateSubticketCommand> {

    private final Long ticketId;

    @NotNull(message = "El ID del subticket es obligatorio")
    private final Long subticketId;

    @NotNull(message = "El ID del manager que actualiza es obligatorio")
    private final UUID updateManagerId;

    @NotNull(message = "La fecha del evento es obligatoria")
    private final LocalDateTime createEventAt;

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

    private final String city;

    private final int countClient;

    public UpdateSubticketCommand(
            Long ticketId,
            Long subticketId,
            UUID updateManagerId,
            LocalDateTime createEventAt,
            LocalDateTime dateReportPext,
            Integer card,
            Integer port,
            String cto,
            String commentary,
            String city,
            int countClient
    ) {
        this.ticketId = ticketId;
        this.subticketId = subticketId;
        this.updateManagerId = updateManagerId;
        this.createEventAt = createEventAt;
        this.dateReportPext = dateReportPext;
        this.card = card;
        this.port = port;
        this.cto = cto;
        this.commentary = commentary;
        this.city = city;
        this.countClient = countClient;
        this.validateSelf();
    }
}
