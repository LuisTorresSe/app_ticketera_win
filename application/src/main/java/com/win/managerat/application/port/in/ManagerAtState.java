package com.win.managerat.application.port.in;

import com.win.managerat.domain.ManagerAt;
import lombok.Getter;

import java.util.UUID;


public record ManagerAtState(UUID managerId, String managerName)
{
    public static ManagerAtState from(ManagerAt manager) {
        if (manager == null) {
            return null;
        }
        return new ManagerAtState(manager.getManagerId(), manager.getFullname());
    }
}
