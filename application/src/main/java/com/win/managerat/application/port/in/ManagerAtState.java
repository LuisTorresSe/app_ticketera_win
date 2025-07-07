package com.win.managerat.application.port.in;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ManagerAtState {

    private final UUID managerId;
    private final String managerName;

    public ManagerAtState(UUID managerId, String managerName) {
        this.managerId = managerId;
        this.managerName = managerName;
    }
}
