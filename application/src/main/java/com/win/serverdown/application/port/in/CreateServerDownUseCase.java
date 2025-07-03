package com.win.serverdown.application.port.in;

public interface CreateServerDownUseCase {
    ServerDownState execute(CreateServerDownCommand command);
}
