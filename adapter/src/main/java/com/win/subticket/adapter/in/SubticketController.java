package com.win.subticket.adapter.in;

import com.win.serverdown.application.port.in.CreateServerDownCommand;
import com.win.subticket.application.port.in.CreateSubticketCommand;
import com.win.subticket.application.port.in.CreateSubticketUseCase;
import com.win.subticket.domain.Subticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/subticket")
public class SubticketController {
    private final CreateSubticketUseCase createSubticketUseCase;
    public SubticketController(CreateSubticketUseCase createSubticketUseCase) {
        this.createSubticketUseCase = createSubticketUseCase;

    }
    @PostMapping
    ResponseEntity<Subticket> addSubticket(@RequestBody RequestSubticketDto request) {

        Set<CreateServerDownCommand> commandServerDown =  request.requestServerDown().stream()
                .map(
                        requestServerDown ->
                                new CreateServerDownCommand(requestServerDown.subticketId(), requestServerDown.clientId())
                ).collect(Collectors.toSet());

        CreateSubticketCommand command = new CreateSubticketCommand(
                request.createManagerId(),
                request.ticketId(),
                request.dateReportPext(),
                request.card(),
                request.port(),
                request.cto(),
                request.commentary(),
                commandServerDown
        );
            Subticket response = createSubticketUseCase.execute(command);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
