package com.win.serverdown.domain;

import com.win.client.domain.Client;
import com.win.subticket.domain.Subticket;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerDown {
    Long breakDownId;
    Client client;

    public ServerDown(Client client) {
        this.client = client;
    }

    public static ServerDown reconstructor(Long breakDownId, Client client) {
        ServerDown serverDown = new ServerDown(); // Usamos el constructor sin argumentos
        serverDown.breakDownId = breakDownId;
        serverDown.client = client;
        return serverDown;
    }



}
