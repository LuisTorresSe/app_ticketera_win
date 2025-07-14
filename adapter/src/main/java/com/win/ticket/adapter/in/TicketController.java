package com.win.ticket.adapter.in;


import com.win.ApiResponse;
import com.win.subticket.application.port.in.CloseSubticketUseCase;
import com.win.ticket.application.port.in.GetAllTicketUseCase;
import com.win.ticket.application.port.in.GetTicketState;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusCommand;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusState;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusUseCase;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketCommand;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketState;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@CrossOrigin("http://localhost:5173")
public class TicketController {

    private final CreateTicketUseCase createTicketUseCase;
    private final ChangeTicketStatusUseCase changeTicketStatusUseCase;
    private final GetAllTicketUseCase getAllTicketUseCase;



    public TicketController(
            CreateTicketUseCase createTicketUseCase,
            ChangeTicketStatusUseCase changeTicketStatusUseCase,
            GetAllTicketUseCase getAllTicketUseCase, CloseSubticketUseCase closeSubticketUseCase
    ) {
        this.createTicketUseCase = createTicketUseCase;
        this.changeTicketStatusUseCase = changeTicketStatusUseCase;
        this.getAllTicketUseCase = getAllTicketUseCase;

    }

    @PostMapping
    public ResponseEntity<CreateTicketState> createTicket
            (
                    @RequestBody RequestCreateTicketDto request
            ) {
        CreateTicketCommand command = new CreateTicketCommand(
                request.type(),
                request.report(),
                request.diagnosis(),
                request.createAtEvent(),
                request.unavailability(),
                request.nodeAffected(),
                request.oltAffected(),
                request.managerId(),
                request.comment()
        );
        CreateTicketState response =  createTicketUseCase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("changeStatus")
    public ResponseEntity<ApiResponse<ChangeTicketStatusState>> closeTicket(
            @RequestBody RequestChangeTicketStatusDto request
    ){
        ChangeTicketStatusCommand command = new ChangeTicketStatusCommand(
                request.managerId(),
                request.ticketId(),
                request.status(),
                request.reasonForPause()
        );

        ChangeTicketStatusState response = changeTicketStatusUseCase.execute(command);
        return new ResponseEntity<>(new ApiResponse<>(response), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GetTicketState>> findAll() {
        return new ResponseEntity<>(getAllTicketUseCase.execute(), HttpStatus.OK);
    }
}
