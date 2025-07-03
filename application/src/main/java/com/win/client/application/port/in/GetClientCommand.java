package com.win.client.application.port.in;

import lombok.Getter;

@Getter

public class GetClientCommand {
    private String cto;
    public GetClientCommand(String cto) {
        this.cto = cto;
    }
}
