package com.win.auth.application.service;

import com.win.auth.application.port.in.LoginState;
import com.win.auth.application.port.in.LoginUseCase;

public class LoginService implements LoginUseCase {
    @Override
    public LoginState execute(String email, String password) {
        return null;
    }
}
