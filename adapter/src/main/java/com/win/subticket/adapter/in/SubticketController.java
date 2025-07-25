package com.win.subticket.adapter.in;

import com.win.ApiResponse;
import com.win.subticket.application.port.in.*;
import com.win.subticket.domain.Subticket;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/subticket")
@CrossOrigin("http://localhost:5173")
public class SubticketController {
    private final CreateSubticketUseCase createSubticketUseCase;
    private final CloseSubticketUseCase closeSubticketUseCase;
    private final UpdateSubticketUseCase updateSubticketUseCase;
    public SubticketController(CreateSubticketUseCase createSubticketUseCase,
                               CloseSubticketUseCase closeSubticketUseCase, UpdateSubticketUseCase updateSubticketUseCase) {
        this.createSubticketUseCase = createSubticketUseCase;
        this.closeSubticketUseCase = closeSubticketUseCase;
        this.updateSubticketUseCase = updateSubticketUseCase;
    }
    @PostMapping
    ResponseEntity<SubticketState> addSubticket(@RequestBody RequestSubticketDto request) {
        CreateSubticketCommand command = new CreateSubticketCommand(
                request.createManagerId(),
                request.ticketId(),
                request.eventStartDate(),
                request.reportedToPextDate(),
                request.card(),
                request.port(),
                request.cto(),
                request.commentary(),
                request.city()
        );
        SubticketState response = createSubticketUseCase.execute(command);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    @PutMapping("/close")
    public ResponseEntity<ApiResponse<ResponseCloseSubticket>> closeSubticket(

            @RequestBody RequestCloseSubticket request
    ) {
        // Construir el comando con todos los datos
        CloseSubticketCommand command = new CloseSubticketCommand(
                request.managerId(),
                request.ticketId(),
                request.subticketId(),
                request.eventEndDate(),
                request.causeRoot(),
                request.solution(),
                request.eventResponsible(),
                request.commentary(),
                request.badPraxis(),
                request.statusPostSLA()
        );

        // Ejecutar el caso de uso
         CloseSubticketState state = closeSubticketUseCase.execute(command);

        // Crear la respuesta
        ResponseCloseSubticket response = new ResponseCloseSubticket(
               state.ticketId(),
                state.code(),
                state.cto(),
                state.card(),
                state.port(),
                state.city(),
                state.clientCount(),
                state.eventStartDate(),
                state.reportedToPextDate(),
                state.creator(),
                state.status(),
                state.node(),
                state.olt(),
                state.closingAdvisor(),
                state.eventEndDate(),
                state.rootCause(),
                state.badPraxis(),
                state.solution(),
                state.statusPostSLA(),
                state.comment(),
                state.eventResponsible()
        );

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse.success(response));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<SubticketState>> updateSubticket(
            @Valid @RequestBody UpdateSubticketRequest request
    ) {
        UpdateSubticketCommand command = new UpdateSubticketCommand(
                request.getTicketId(),
                request.getSubticketId(),
                request.getUpdateManagerId(),
                request.getCreateEventAt(),
                request.getDateReportPext(),
                request.getCard(),
                request.getPort(),
                request.getCto(),
                request.getCommentary(),
                request.getCity(),
                request.getCountClient()
        );

        SubticketState response = this.updateSubticketUseCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse.success(response));
    }

}
