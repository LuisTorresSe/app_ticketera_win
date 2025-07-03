package com.win.client.application.port.in;

import java.util.List;
import java.util.Set;

public interface GetClientByCtoUseCase {

    Set<GetClientState> execute(GetClientCommand command);
}
