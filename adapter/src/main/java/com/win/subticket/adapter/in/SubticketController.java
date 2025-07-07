package com.win.subticket.adapter.in;

import com.win.serverdown.application.port.in.CreateServerDownCommand;
import com.win.subticket.application.port.in.CreateSubticketCommand;
import com.win.subticket.application.port.in.CreateSubticketUseCase;
import com.win.subticket.application.port.in.SubticketState;
import com.win.subticket.domain.Subticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/subticket")
@CrossOrigin("http://localhost:5173")
public class SubticketController {
    private final CreateSubticketUseCase createSubticketUseCase;
    public SubticketController(CreateSubticketUseCase createSubticketUseCase) {
        this.createSubticketUseCase = createSubticketUseCase;

    }
    @PostMapping
    ResponseEntity<SubticketState> addSubticket(@RequestBody RequestSubticketDto request) {

        CreateSubticketCommand command = new CreateSubticketCommand(
                request.createManagerId(),
                request.ticketId(),
                request.dateReportPext(),
                request.card(),
                request.port(),

                request.cto(),
                request.commentary()

        );
        SubticketState response = createSubticketUseCase.execute(command);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
