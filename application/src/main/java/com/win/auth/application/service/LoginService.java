package com.win.auth.application.service;

import com.win.auth.application.port.in.*;
import com.win.auth.application.port.out.AuthPort;
import com.win.managerat.domain.ManagerAt;
import com.win.permission.domain.Permission;
import com.win.role.domain.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginService implements LoginUseCase {

    private final AuthPort authPort;

    public LoginService(AuthPort authPort) {
        this.authPort = authPort;
    }

    @Override
    public LoginState execute(LoginCommand command) {
        ManagerAt managerAt = authPort.findByEmail(command.email()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email: " + command.email())
        );

        boolean isValidPassword = managerAt.comparePassword(command.password());

        if (!isValidPassword) {
            throw new IllegalArgumentException("Invalid password");
        }

        Role roleOfUser = managerAt.getRole();

        Set<String> permissions = roleOfUser.getPermissions().stream()
                .map(Permission::getPermissionName)
                .collect(Collectors.toSet());

        String role = roleOfUser.getRoleName();

        return new LoginState(
                managerAt.getManagerId(),
                managerAt.getEmail(),
                managerAt.getFullname(),
                role,
                permissions);
    }
}
