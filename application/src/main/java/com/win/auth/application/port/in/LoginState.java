package com.win.auth.application.port.in;

import java.util.Set;
import java.util.UUID;

public record LoginState(
        UUID userId,
        String email,
        String fullName,
        String roles,
        Set<String> permissions
) {}
