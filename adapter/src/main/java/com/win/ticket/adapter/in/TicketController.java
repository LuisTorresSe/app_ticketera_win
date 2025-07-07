package com.win.ticket.adapter.in;


import com.win.ticket.application.port.in.GetAllTicketUseCase;
import com.win.ticket.application.port.in.GetTicketState;
import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketCommand;
import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketState;
import com.win.ticket.application.port.in.closeTicketUseCase.CloseTicketUseCase;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketCommand;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketState;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketUseCase;
import com.win.ticket.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@CrossOrigin("http://localhost:5173")
public class TicketController {

    private final CreateTicketUseCase createTicketUseCase;
    private final CloseTicketUseCase closeTicketUseCase;
    private final GetAllTicketUseCase getAllTicketUseCase;

    public TicketController(
            CreateTicketUseCase createTicketUseCase,
            CloseTicketUseCase closeTicketUseCase,
            GetAllTicketUseCase getAllTicketUseCase
    ) {
        this.createTicketUseCase = createTicketUseCase;
        this.closeTicketUseCase = closeTicketUseCase;
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

    @PostMapping("close")
    public ResponseEntity<CloseTicketState> closeTicket(
            @RequestBody RequestCloseTicketDto request
    ){
        CloseTicketCommand command = new CloseTicketCommand(
                request.managerId(), request.ticketId()
        );
        CloseTicketState response = closeTicketUseCase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GetTicketState>> findAll() {
        return new ResponseEntity<>(getAllTicketUseCase.execute(), HttpStatus.OK);
    }
}
