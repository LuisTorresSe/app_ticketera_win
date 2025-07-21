package com.win.ticket.adapter.in;


import com.win.ApiResponse;
import com.win.ticket.application.port.in.*;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusCommand;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusState;
import com.win.ticket.application.port.in.closeTicketUseCase.ChangeTicketStatusUseCase;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketCommand;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketState;
import com.win.ticket.application.port.in.createTicketUseCase.CreateTicketUseCase;
import org.springframework.data.repository.query.Param;
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
    private final UpdatedTicketUseCase updatedTicketUseCase;


    public TicketController(
            CreateTicketUseCase createTicketUseCase,
            ChangeTicketStatusUseCase changeTicketStatusUseCase,
            GetAllTicketUseCase getAllTicketUseCase,
            UpdatedTicketUseCase updatedTicketUseCase, UpdatedTicketUseCase updatedTicketUseCase1
    ) {
        this.createTicketUseCase = createTicketUseCase;
        this.changeTicketStatusUseCase = changeTicketStatusUseCase;
        this.getAllTicketUseCase = getAllTicketUseCase;
        this.updatedTicketUseCase = updatedTicketUseCase1;
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
                request.assignTo(),
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
        return new ResponseEntity<>( ApiResponse.success(response), HttpStatus.OK);
    }

    @PutMapping("updated/{id}")
    public ResponseEntity<ApiResponse<GetTicketState>> updateTicket(@RequestBody UpdatedTicketRequest request,  @PathVariable("id") Long ticketId){
        UpdatedTicketCommand command = new UpdatedTicketCommand(
                ticketId,
                request.managerId(),
                request.type(),
                request.report(),
                request.diagnosis(),
                request.createAtEvent(),
                request.unavailability(),
                request.nodeAffected(),
                request.oltAffected()
                );

        GetTicketState response = updatedTicketUseCase.execute(command);

        return new ResponseEntity<>(ApiResponse.success(response), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GetTicketState>> findAll() {
        return new ResponseEntity<>(getAllTicketUseCase.execute(), HttpStatus.OK);
    }




}
