package com.win.serverdown.application.service;

import com.win.client.application.exception.NotFoundClientException;
import com.win.client.application.port.out.ClientPort;
import com.win.client.domain.Client;
import com.win.serverdown.application.port.in.CreateServerDownCommand;
import com.win.serverdown.application.port.in.ServerDownState;
import com.win.serverdown.application.port.in.CreateServerDownUseCase;
import com.win.serverdown.application.port.out.ServerDownPort;
import com.win.serverdown.domain.ServerDown;
import com.win.subticket.application.exception.NotFoundSubticket;
import com.win.subticket.application.port.out.SubticketPort;
import com.win.subticket.domain.Subticket;

public class CreateServerDownService implements CreateServerDownUseCase {
    private final ServerDownPort serverDownPort;
    private final SubticketPort subticketPort;
    private final ClientPort clientPort;


    public CreateServerDownService(ServerDownPort serverDownPort, SubticketPort subticketPort, ClientPort clientPort) {
        this.serverDownPort = serverDownPort;
        this.subticketPort = subticketPort;
        this.clientPort = clientPort;
    }

    @Override
    public ServerDownState execute(CreateServerDownCommand command) {

        Subticket subticket = subticketPort.findById(command.getSubticketId()).orElseThrow(
                ()-> new NotFoundSubticket("Subticket Id not found")
        );

        Client client = clientPort.findById(command.getClientId()).orElseThrow(
                ()-> new NotFoundClientException("Client Id not found")
        );

        final String ci = client.getDocumentCi();

        ServerDown newServerDown = new ServerDown(client);

        subticket.addServerDown(newServerDown);

        Subticket savedSubticket = subticketPort.save(subticket);

        ServerDown serverDown =  savedSubticket.getServerDowns().stream()
                .filter(s -> s.getClient().getDocumentCi().equals(ci))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("serverdown not found after save"));


        return new ServerDownState(
                serverDown.getBreakDownId(),
                subticket.getSubticketId(),
                command.getClientId());
    }
}
