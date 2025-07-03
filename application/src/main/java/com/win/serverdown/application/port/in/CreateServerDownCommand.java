package com.win.serverdown.application.port.in;

import lombok.Getter;

@Getter
public class CreateServerDownCommand {
    private final Long subticketId;
    private final Long clientId;
    public CreateServerDownCommand(Long subticketId, Long clientId) {
        this.subticketId = subticketId;
        this.clientId = clientId;
    }
}
