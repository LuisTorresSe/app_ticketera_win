package com.win.serverdown.application.port.in;

import lombok.Getter;

@Getter
public class ServerDownState {
    private final Long serverdownId;
    private final Long subticketId;
    private final Long clientId;
    public ServerDownState(Long serverdownId, Long subticketId, Long clientId) {
        this.serverdownId = serverdownId;
        this.subticketId = subticketId;
        this.clientId = clientId;
    }
}
