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

    public static GetServerDownState from(com.win.serverdown.domain.ServerDown serverDown) {
        if (serverDown == null) {
            return null;
        }

        return GetServerDownState.builder()
                .serverdownId(serverDown.getBreakDownId())
                .client(GetClientState.from(serverDown.getClient()))
                .build();
    }



}
