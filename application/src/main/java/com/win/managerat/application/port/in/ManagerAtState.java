package com.win.managerat.application.port.in;

import lombok.Getter;

import java.util.UUID;


public record ManagerAtState(UUID managerId, String managerName)
{ }
