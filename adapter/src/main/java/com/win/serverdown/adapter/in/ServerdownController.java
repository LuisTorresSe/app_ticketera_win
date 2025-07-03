package com.win.serverdown.adapter.in;

import com.win.serverdown.application.port.in.CreateServerDownCommand;
import com.win.serverdown.application.port.in.CreateServerDownUseCase;
import com.win.serverdown.application.port.in.ServerDownState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/serverdown")
public class ServerdownController {
    private final CreateServerDownUseCase createServerDownUseCase;
    public ServerdownController(CreateServerDownUseCase createServerDownUseCase) {
        this.createServerDownUseCase = createServerDownUseCase;
    }

    public ResponseEntity<ServerDownState> create(@RequestBody RequestServerDown request)
    {
        CreateServerDownCommand command = new CreateServerDownCommand(request.subticketId(), request.clientId());

        return new ResponseEntity<>(createServerDownUseCase.execute(command), HttpStatus.CREATED);
    }
}
