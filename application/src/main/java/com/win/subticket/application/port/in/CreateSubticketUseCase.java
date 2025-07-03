package com.win.subticket.application.port.in;

import com.win.subticket.domain.Subticket;

public interface CreateSubticketUseCase {

    Subticket execute(CreateSubticketCommand command);
}
