package com.win.client.adapter.in;

import com.win.client.application.port.in.GetClientByCtoUseCase;
import com.win.client.application.port.in.GetClientCommand;
import com.win.client.application.port.in.GetClientState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    private final GetClientByCtoUseCase getClientByCtoUseCase;

    public ClientController(GetClientByCtoUseCase getClientByCtoUseCase) {
        this.getClientByCtoUseCase = getClientByCtoUseCase;
    }
    @GetMapping("cto")
    public ResponseEntity<Set<GetClientState>> getClientByCto (
            @RequestBody RequestGetClientByCto request
    ) {
        GetClientCommand command = new GetClientCommand(request.cto());
        Set<GetClientState> response = getClientByCtoUseCase.execute(command);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
