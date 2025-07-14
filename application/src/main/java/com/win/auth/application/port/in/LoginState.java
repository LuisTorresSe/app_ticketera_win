package com.win.auth.application.port.in;

import java.util.Map;

public record LoginState(
        Map<String, String> data,
        boolean success,
        String message
) {
}
