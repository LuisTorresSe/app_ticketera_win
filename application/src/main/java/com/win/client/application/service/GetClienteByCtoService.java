package com.win.client.application.service;

import com.win.client.application.exception.NotFoundClientException;
import com.win.client.application.port.in.GetClientByCtoUseCase;
import com.win.client.application.port.in.GetClientCommand;
import com.win.client.application.port.in.GetClientState;
import com.win.client.application.port.out.ClientPort;
import com.win.client.domain.Client;
import com.win.managerat.application.exception.NotFoundManagerAtException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GetClienteByCtoService implements GetClientByCtoUseCase {
    private final ClientPort clientPort;
    public GetClienteByCtoService(ClientPort clientPort) {
        this.clientPort = clientPort;
    }
    @Override
    public Set<GetClientState> execute(GetClientCommand command) {
        Set<Client> clientList = clientPort.getClientsByCto(command.getCto()).orElseThrow(
                () -> new NotFoundClientException("Client's not found"+command.getCto())
        );

        Set<GetClientState> clientStateSet = clientList.stream().map(
                client-> GetClientState
                        .builder()
                        .clientId(client.getClientId())
                        .contrata(client.getContrata())
                        .codeBox(client.getCto())
                        .orderCode(client.getOrderCode())
                        .portGpon(client.getPortGpon())
                        .serialNumber(client.getSerialNumber())
                        .descriptionDepartament(client.getDescriptionDepartament())
                        .descriptionBox(client.getDescriptionBox())
                        .documentCi(client.getDocumentCi())
                        .descriptionUbigeo(client.getDescriptionUbigeo())
                        .statusAccount(client.getStatus())
                        .build()
        ).collect(Collectors.toSet());

        return clientStateSet;
    }
}
