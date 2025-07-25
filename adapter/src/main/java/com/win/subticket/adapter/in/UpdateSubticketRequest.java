package com.win.subticket.adapter.in;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UpdateSubticketRequest {

    @NotNull(message = "El ID del ticket es obligatorio")
    private Long ticketId;

    @NotNull(message = "El ID del subticket es obligatorio")
    private Long subticketId;

    @NotNull(message = "El ID del manager que actualiza es obligatorio")
    private UUID updateManagerId;

    @NotNull(message = "La fecha del evento es obligatoria")
    private LocalDateTime createEventAt;

    @NotNull(message = "La fecha de reporte es obligatoria")
    private LocalDateTime dateReportPext;

    @NotNull(message = "El campo 'card' es obligatorio")
    @Min(value = 0, message = "El valor de 'card' debe ser mayor o igual a 0")
    private Integer card;

    @NotNull(message = "El campo 'port' es obligatorio")
    @Min(value = 0, message = "El valor de 'port' debe ser mayor o igual a 0")
    private Integer port;

    @NotBlank(message = "La CTO no puede estar vacía")
    private String cto;

    private String commentary;
    private String city;

    private Integer countClient;
}
