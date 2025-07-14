package com.win.auth.application.port.in;

public interface LoginUseCase {

    LoginState execute(String email, String password);


}
