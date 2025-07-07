package com.win.serverdown.application.port.in;

import com.win.client.application.port.in.GetClientState;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetServerDownState {

    private final Long serverdownId;
    private final Long subticketId;
    private final GetClientState client;

}
